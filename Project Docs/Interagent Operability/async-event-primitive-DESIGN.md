# Asynchronous Event Primitive for Agent Runtimes — Design & Prototype Spec

**Author:** Rikhil Nellimarla
**Date:** 2026-06-27
**Status:** Design doc + prototype specification (pre-implementation)

---

## 0. One-line framing

Agents today receive external information through exactly two channels: a user message at
the start of a turn, or a tool result mid-turn. This document specifies a third channel —
an **authenticated, multi-origin event that can be delivered into an agent while it is
executing** — and a prototype that demonstrates it with no model retraining and no
framework fork.

> Important honesty note (see §2): the *mechanism* of mid-execution injection already
> exists in fragmented, single-origin, unauthenticated forms across several runtimes. The
> contribution here is **not** "inventing the channel." It is specifying a typed,
> multi-origin, forgery-resistant version and the semantics around it.

---

## 1. Problem

The turn boundary is a leash. While an agent reasons, it cannot receive information it did
not explicitly ask for. This produces three concrete failure modes:

1. **Latency-critical signals** (e.g. a monitoring agent receiving a critical alert) are
   only seen at the next turn/iteration boundary.
2. **Inter-agent requests** require either waiting for a turn to end, spawning a fresh
   instance that re-ingests context, or contaminating context.
3. **Runtime → agent signaling** (token budget, rate limits, subagent completion) has no
   delivery path except between turns.

### 1.1 What the problem is NOT (corrected during research)

- It is **not** "agents can't run long enough to need this." The dominant long-running
  pattern (the Ralph loop) deliberately runs agents for hours by *externalizing state to
  disk and restarting with a fresh context every iteration*. Duration is already handled.
  Leading with "agents run long" invites the rebuttal "we just use Ralph."
- The **context-compaction** motivation is therefore weak as a flagship: Ralph solves
  compaction by restart, not by mid-turn self-management.

### 1.2 The legitimacy that survives scrutiny

The primitive matters specifically for agents that **cannot restart and cannot wait**:

- **Latency-bound** reactive agents (monitoring, live ops, trading, voice).
- **Non-restartable sessions** whose state does not externalize cleanly to disk — e.g. a
  live telephony/voice call with a human on the line, an in-flight negotiation, anything
  with non-reproducible side effects. The Ralph "restart with fresh context" escape hatch
  does not exist for these.

This is the defensible scope, and it maps directly onto real production systems (voice /
SIP / LiveKit telephony agents).

---

## 2. Prior art — where the conclusions came from

| System | What it does | Why it's not this |
|---|---|---|
| **Hermes `session.steer`** ([PR #12116](https://github.com/NousResearch/hermes-agent/pull/12116)) | Injects a mid-run note after the next tool call, no interrupt, no new turn. | **Single-origin (human only).** Rendered as text appended to the last tool result, tagged `[USER STEER (injected mid-run, not tool output): …]`. The tag is a **trusted string** — forgeable by any poisoned tool result. Design explicitly punts on hard semantics ("role alternation preserved", "cache-safe"). |
| **Claude Agent SDK** (streaming input / `ClaudeSDKClient`) | Writes additional input into an active query's stream; picked up at the next tool boundary. Plus `interrupt()`. | Mechanism exists and ships, but injected **as a user message** — no semantic distinction between "human said" and "runtime signals". |
| **OpenAI Realtime API** (`conversation.item.create`, out-of-band responses) | Injects items into a live session; parallel/out-of-band responses. | Built for voice/VAD streaming; not a general typed agent-event abstraction. |
| **Google A2A protocol** (Linux Foundation) | Agent-to-agent transport: webhook push notifications, long-running tasks, delegation. | **Explicitly "Opaque Execution" in the spec preamble** — A2A standardizes transport *between* agents and deliberately does **not** define what happens inside the loop when a message arrives. Its mid-task model requires the agent to pause (`input-required`) and wait. |

**Conclusion from prior art:** the diagonal through all of these — an event that is
(a) delivered while the agent is mid-execution, (b) crosses a trust boundary, and
(c) carries typed, authenticated provenance — is unoccupied. A2A leaves it open *by
design*; Hermes occupies only (a), single-origin and unauthenticated.

---

## 3. Key technical constraint (why no retraining is needed)

No frontier model has been trained on an "async event" message type. The model's message
vocabulary is fixed by the provider chat template (OpenAI: `system`/`developer`/`user`/
`assistant`/`tool`; Anthropic: `user`/`assistant` + content blocks). Therefore the event
**must be rendered into an existing role** at the wire boundary, with a textual convention
the model is *instructed* to interpret.

This is not a workaround — it is precisely what Pydantic AI already does internally:
`_wrap_non_leading_system_prompts` renders a mid-conversation system prompt as a
`UserPromptPart` tagged `<system>…</system>` because the wire format has no slot for it.

**Implications baked into the design:**

- **Typing + authentication live in the harness, never in the model.** The harness
  verifies before injecting; the model only ever sees already-trusted, delimited text.
  (Asking the model to verify is itself an injection hole.)
- **Origin maps onto the instruction hierarchy** (OpenAI: platform > developer > user >
  tool; `tool` is lowest trust). The role an event is rendered into determines its
  authority. A trusted runtime event should render at developer/system tier — *not* tool,
  which is why Hermes' tool-result approach is weak for anything privileged.
- **Provenance signing has precedent:** Anthropic already cryptographically signs
  `thinking` blocks (`signature` field). Signed content blocks are not exotic.

---

## 4. Two security threats (kept distinct)

- **Threat A — ingress:** who may emit an event. Handled *upstream* by the webhook/
  websocket layer (auth tokens, endpoint authz). **Deferred** — genuinely a separate,
  well-understood layer.
- **Threat B — in-context forgery:** once the event is rendered as text, any other content
  the model sees (tool result, scraped page, document) can forge the same tag. This threat
  is **intrinsic to the injection point** and is the difference between a primitive and a
  hack. It is *the* security property this project owns.

### 4.1 Forgery mitigation at the text layer (no crypto, no training)

- **Per-session nonce in the tag:** `<async_event:{NONCE} …>`. The skill/system prompt
  instructs the model to treat only blocks bearing the exact session nonce as real.
- **Sanitize:** strip/redact the nonce from *all* untrusted content (tool returns, fetched
  docs) before it enters context.
- Forgery then requires guessing a secret the attacker cannot observe.

### 4.2 Stronger mitigation (Phase 2, requires open model)

A genuinely **reserved special token** that the chat template refuses to accept from
user-supplied content is unforgeable by construction. Only feasible on an open model you
control — hence it belongs in the reference model / paper, not in a pitch to a closed
provider.

---

## 5. The event envelope (SMTP-like)

Minimal v1, designed to grow into a coordination layer ("Accord") without a schema break.

```python
class AgentEvent(BaseModel):
    sender: Literal["runtime", "agent", "world"]  # maps to a TRUST TIER, not just a label
    type: str                                      # for programmatic routing
    priority: Literal["low", "normal", "high"]     # the latency case
    body: str | dict                               # payload
    correlation_id: str | None = None              # request -> confirmation threads
    id: str                                         # idempotency / dedup
    signature: str | None = None                   # verified in harness before render
```

---

## 6. Prototype spec (Phase 1 — no GPU, no fork)

**Stack:** Pydantic AI, single open or API model, a run loop. No fork of pydantic-ai
required — inject via existing `UserPromptPart`, mirroring `_wrap_non_leading_system_prompts`.

**Components:**

1. `AgentEvent` schema (§5).
2. `verify(event)` — harness-side check (nonce / HMAC). Unverified events are **dropped**,
   never rendered.
3. `render_verified_event(event) -> UserPromptPart` — emits the nonce-tagged block at the
   chosen trust tier.
4. `sanitize(untrusted_text)` — strips the session nonce from tool/web/doc content.
5. A run loop that splices verified events into `message_history` between iterations.
6. A **skill / instruction block** teaching the model how to interpret events: handle vs.
   defer by priority, honor sender trust tier, ignore any event lacking the session nonce.

**Demo — four beats (≤ 90s, screen-recorded):**

1. Agent is mid-task (ideally a non-restartable scenario, e.g. a simulated live call).
2. A **runtime** event arrives (`sender="runtime", type="context_warning", priority="high"`)
   — agent acknowledges and adapts without losing the task.
3. A **world/agent** event arrives — handled differently *because the agent knows the
   origin*.
4. A tool result attempts to **forge** a runtime event — it is **rejected** (no valid
   nonce). This is the demo's strongest beat.

**Why this is the right first artifact:** it proves the convention needs no model change
and no fork — "a harness convention plus verification, working" — which is far easier for a
maintainer to evaluate than a forked branch.

---

## 7. Phase 2 (optional) — reserved-token fine-tune + paper

- Small open model (Qwen2.5-3B / Llama-3.2-3B), QLoRA on free Colab/Kaggle or ~$0.30/hr
  rental. **Compute is not the blocker.**
- Synthetic dataset distilled from a frontier model = the **semantics contract**
  operationalized (correct behavior for: event during tool call, two events with priority,
  forged event, etc.). This labeling is the valuable, free, unpublished core.
- Reserved special token for forgery-resistance (§4.2).
- Eval: trained model vs. in-context baseline. Honest expected finding: convention suffices
  for *behavior*; the reserved token is required for *forgery-resistance*. A nuanced result,
  rigorously shown, is publishable as an arXiv preprint + reproducible repo.

---

## 8. Open questions (the hard, unclaimed part = the real contribution)

These are the semantics A2A left "opaque" and Hermes punted on. Answering them is the work:

1. Event arrives **during a tool call** — buffer until the tool returns, or interrupt?
2. Event arrives **during compaction / a subagent spawn** — ordering and visibility?
3. **Two events** arrive simultaneously — ordering and coalescing guarantees.
4. **Delivery semantics** — at-least-once vs at-most-once; idempotency via `id`.
5. **Trust tiers** — exactly which sender maps to which instruction-hierarchy level, and
   what the model is permitted to act on from each.

---

## 9. Kill criteria (decide honestly)

Build only if both hold; otherwise stop:

1. It can be framed as **A2A-compatible** (the in-loop delivery profile A2A omits) rather
   than a competing standard.
2. The **forged-event-rejected** demo is real and reproducible.

If the convention shows no advantage a maintainer cares about, and no one in the A2A /
harness-maintainer circle engages after the artifact exists — that is the signal to let it
go. Validation comes from one other person wanting it, not from self-conviction.

---

## 10. Sequencing

1. Phase 1 prototype + 90s demo (this week, no GPU, no fork).
2. Short problem writeup that ends *at the working demo* (the §2 map + the gap).
3. Reach out to 2–3 specific people with the artifact in hand (A2A working group; a harness
   maintainer). Small, specific ask — not "mentor me / build my idea."
4. Only if there is pull: Phase 2 fine-tune + preprint, and/or the spec.
