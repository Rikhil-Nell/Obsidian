---
skill: Distributed Systems
source: Brain Co JD + Oximy rejection pattern + async event / Accord build path
priority: High
status: Started
project: Accord (async event primitive + coordination layer)
---

# Distributed Systems

## Why It Matters

Appeared In:

- [[Companies/Brain Co]] — AI Platform Engineer JD (5+ years, K8s, multi-tenant, fan-out/fan-in, SLOs)
- [[Rejections/Oximy]] — production-scale event infrastructure gap
- [[Learn/Event Systems]] — adjacent; events are the substrate, DS is how you operate them reliably
- [[Learn/Backend Primitives]] — the 5-primitive ledger backend; project #02 (job queue + DLQ) IS Phase 1 of this note's Accord build

**The glass layer:** Interesting platform roles (Brain Co, Oximy-tier infra) assume scar tissue from systems that failed at scale. Reading DDIA gives vocabulary. **Building and breaking one orchestration platform gives qualification.**

This note is the curriculum. The project is **Accord** — built on the async event primitive from `RECORD-agentic-village-async` in [[Me]].

---

## Current State (Honest)

**Have:**

- Production backends (FastAPI, asyncpg, Redis) at hundreds-of-ops scale
- Agent orchestration (Clink multi-agent, voice loops 500–600 calls/day)
- Conceptual DS literacy (DDIA read, played with queues/K8s)
- Logfire observability on async services
- Event-system thinking (telephony pipelines, agent runtime state)

**Don't have:**

- Operated platform other engineers depend on at company scale
- Multi-tenant isolation under regulated constraints
- On-call war stories (duplicate events, cascading failures, quota blowups)
- Kubernetes production ownership
- Maintained same system 12+ months through usage shift

See `WEAK-current-gaps` in [[Me]].

---

## Learning Strategy

**Do not** learn distributed systems as a disconnected subject.

**Do** build Accord and force every concept through a real constraint:

```
Ingress (webhook / schedule / inter-agent message)
  → Async Event SDK (validate, format, inject at safe point)
    → Orchestrator (queue, fan-out, tenant scope)
      → Agent Workers (tool calls, idempotent side effects)
        → Audit + cost ledger + kill switch
          → Aggregate / reply
```

One queue. One orchestrator. One worker pool. Break it repeatedly. Document failure modes.

---

## Build Phases

### Phase 1 — Distributed workflow (2–3 weeks)

**Goal:** One Clink-shaped flow that cannot survive as a single process.

| Concept | Implementation |
|---|---|
| **Queueing** | Redis Streams or RabbitMQ between orchestrator and workers |
| **Idempotency** | Idempotency key on every ingress event; duplicate webhook = no duplicate run |
| **Retries + backoff** | Tool failure → retry with jitter; distinguish transient vs permanent |
| **At-least-once delivery** | Worker crash mid-run → what state remains? recovery path? |
| **Observability** | Trace ID from ingress through every agent step (Logfire) |
| **Failure injection** | Kill worker mid-tool-call; log and fix |

**Done when:** Can demo duplicate delivery handled correctly + trace visible end-to-end.

### Phase 2 — Multi-tenant agent platform (Brain Co-shaped)

**Goal:** Vertical teams can't invent their own abstraction — isolation by default.

| Concept | Implementation |
|---|---|
| **Tenant isolation** | Tenant A keys ≠ Tenant B keys; no shared tool_calls |
| **Scoped credentials** | Per-tool, per-tenant secrets; no god API key |
| **Cost / token budget** | Hard stop when tenant exceeds quota |
| **Kill switch** | Cancel in-flight run by run_id without orphan side effects |
| **Audit log** | Append-only: who, what agent, what tools, what data touched |
| **Async events** | Third channel: runtime signals mid-execution (context 80%, inter-agent request) |

**Done when:** Two tenants run concurrently; killing one doesn't affect the other; audit reconstructs a run.

### Phase 3 — Operate for 30 days

**Goal:** Close the maintenance gap from `WEAK-current-gaps`.

- Deploy on VPS (Docker Compose minimum)
- One small feature or fix per week
- Note what rots: schema drift, queue backlog, credential expiry, observability gaps

**Done when:** Written postmortem-style notes on at least 2 real incidents (even self-inflicted).

### Phase 4 — One infra primitive deeply (pick one)

**Option A — Kubernetes:** Deploy orchestrator with health checks, secrets (External Secrets or sealed), one HPA, basic network policy.

**Option B — Event streams:** If event thesis deepens — Kafka or Redpanda with consumer groups, replay, dedup window.

Not both at once. Depth beats checklist.

---

## Specific Concepts (Interview + Build Checklist)

### Core

- [ ] Consistency models (strong vs eventual — when each is acceptable)
- [ ] Idempotency keys and deduplication windows
- [ ] At-least-once / at-most-once / exactly-once (and why exactly-once is usually "effectively once")
- [ ] Retries, exponential backoff, jitter, circuit breakers
- [ ] Fan-out / fan-in (orchestrator → N workers → aggregate)
- [ ] Cold start vs always-on tradeoffs (Brain Co JD explicit ask)
- [ ] Backpressure and queue depth monitoring
- [ ] Distributed tracing and correlation IDs

### Platform / Agent-specific

- [ ] Multi-tenant credential lifecycle
- [ ] Token/cost attribution per tenant per run
- [ ] Sandboxed tool execution boundaries
- [ ] Prompt injection as supply-chain / tool-return attack surface
- [ ] Kill switch semantics during tool call
- [ ] Async event injection at "safe points" (open problem from `RECORD-agentic-village-async`)

### Ops

- [ ] SLOs for platform APIs (availability, latency p99)
- [ ] Runbooks for queue backlog, worker stall, provider rate limit
- [ ] Secrets rotation without downtime
- [ ] OAuth/OIDC for service-to-service (Brain Co mentions)

---

## Resources

**Reference (already read — revisit during build, not before):**

- *Designing Data-Intensive Applications* — Kleppmann (index by problem when stuck)

**Build-first:**

- Redis Streams docs (or RabbitMQ tutorials) — pick one queue
- Pydantic AI / existing Clink patterns — reuse agent worker code
- Logfire — tracing across orchestrator + workers

**When Phase 4 = K8s:**

- Kubernetes docs: Deployments, Services, Secrets, HPA
- One production-grade FastAPI-on-K8s blog post — implement, don't collect

---

## Progress

- [ ] Not Started
- [x] Started (conceptual + architecture doc exists)
- [ ] Phase 1 shipped
- [ ] Phase 2 shipped
- [ ] 30-day operation
- [ ] Interview Ready

---

## Notes

**2026-06-21** — Brain Co JD triggered imposter reaction. Reframe: not fake, early for Staff Platform tier. This learn track exists to convert async event / Accord thesis into proof that closes Oximy-scale and Brain Co-scale gaps.

**Pattern:** DS + events + SQL keep appearing. DS learning path is **through Accord**, not parallel to it.

**Public credibility:** When Phase 1 ships — README with failure modes, git history, one demo video (context compaction or boiler room from async event writeup).

---

## Related

- Companies: [[Companies/Brain Co]], [[Companies/Oximy]]
- Learn: [[Learn/Backend Primitives]], [[Learn/Event Systems]], [[Learn/SQL]]
- Profile: [[Me]] — `RECORD-agentic-village-async`, `STORY-agentic-village`, `WEAK-current-gaps`
- Rejections: [[Rejections/Oximy]]
