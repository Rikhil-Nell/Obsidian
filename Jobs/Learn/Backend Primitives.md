---
skill: Backend Primitives
source: Self-directed (GOAT backend projects image) + Oximy/DualEntry/Accord convergence
priority: High
status: Not Started
project: One unified ledger-core backend (5 primitives in one system)
---

# Backend Primitives

## Why It Matters

The growth edge. Every project so far has been **product-shaped** (frontend + auth + CRUD) and that's now tedious because it's outgrown. The gap is **pure backend primitives** — concurrency, consistency, failure semantics — built with no UI to hide behind.

**Signal problem this closes:** Current portfolio reads as *"production AI systems integrator"* (telephony, multi-agent, pipelines) — real, but **not** *"distributed systems primitive builder."* A skeptical founder sees "wires powerful things together," not "designs the hard core." These 5 primitives flip that.

Convergence — one build, multiple gaps:

- [[Rejections/Oximy]] — "couldn't demonstrate production-scale event infrastructure" → ledger (#03)
- [[Companies/DualEntry]] — double-entry accounting is literally project #03
- [[Learn/Event Systems]] — append-only, replay, dedup → #02 + #03
- [[Learn/Distributed Systems]] — queue/idempotency/cache map to Accord phases
- `RECORD-agentic-village-async` in [[Me]] — job queue + DLQ is the same family as async runtime signaling

---

## The Build: One Backend, Five Primitives

A **bank/ledger core** as the spine. Generates infinite random data, demands correctness, and 4 of 5 primitives are things a real bank backend needs. Don't over-invest in the domain — accounts, transfers, balances, random seed. The product is the primitives.

```
                    ┌─────────────────────────────┐
   client ──▶  [01 Rate Limiter]  ──▶  API
                    └─────────────────────────────┘
                                │ enqueue transfer
                                ▼
                    [02 Job Queue + DLQ]  ──▶ worker pool
                                │ apply
                                ▼
                    [03 Event-Sourced Ledger]  ◀── source of truth
                                │ project balances
                                ▼
                    [05 Distributed Cache]  ──▶ fast balance reads

   [04 Collaborative Sync]  ──▶ separate surface (transaction tags/notes)
```

Money flows **01 → 02 → 03 → 05** (one coherent pipeline). #04 is a deliberate side-surface because CRDTs and money do not mix — and knowing why is the lesson.

Specs are **behavior-only, no stack mandated**. Build in whatever you're fast in. Treat each as a spec, not a tutorial: no boilerplate, no starter repo. The constraint is the lesson.

**Visual spec (canvas):** [Ledger primitives spec](C:/Users/Rikhil Nellimarla/.cursor/projects/c-Users-Rikhil-Nellimarla-Documents-Obsidian-Jobs/canvases/ledger-primitives-spec.canvas.tsx) — open beside chat in Cursor.

---

## 01 — Token Bucket Rate Limiter (edge · beginner)

**Problem:** Rate-limit the transfer API. Each account/key gets a token bucket: N tokens, refill R/sec, burst B. Request consumes a token; empty → reject with retry hint. Limits hold **across multiple server instances**, not per-process.

**Hard constraint (lesson):** Refill-and-consume must be **atomic under concurrency**. Two requests on two workers, one token left → exactly one succeeds. No token double-spend.

**Prove it:**
- 1000 concurrent requests at 100-token bucket → exactly 100 pass.
- Two server instances sharing state → global limit, not 2×.
- Idle then burst → burst to B, then throttle to R.

**Teaches:** atomicity, shared state, distributed counters.

---

## 02 — Reliable Job Queue with Dead-Letter Recovery (async spine · easy-med)

**Problem:** API enqueues transfer jobs; worker pool consumes and applies to the ledger. Failures retry with backoff. Too many failures → **dead-letter queue** for inspection + replay.

**Hard constraint (lesson):** **At-least-once delivery + idempotent application.** Worker crashes *after* applying but *before* ack → on redelivery the transfer must not apply twice. Idempotency keys, not hope.

**Prove it:**
- Kill worker mid-job → redelivered, ledger correct, no double-apply.
- Permanently-failing job → lands in DLQ after max retries, doesn't block queue.
- Replay from DLQ → succeeds, no duplicate side effects.
- 10k jobs, 5 workers → each applied exactly once, balances reconcile.

**Teaches:** delivery guarantees, idempotency, retries/backoff, failure isolation.

**Pairs with 01** — edge protection + async layer. Build this pair after the ledger exists.

---

## 03 — Event-Sourced Double-Entry Ledger (core · intermediate · BUILD FIRST)

**Problem:** Source of truth is an **append-only event log**, never mutable rows. Each transfer = two entries (debit + credit) summing to zero. Balances are a **projection** rebuilt by replaying events. Reconstruct any balance at any historical offset by replaying to it.

**Hard constraint (lesson):**
- **Double-entry invariant:** every transaction nets zero; system-wide debits = credits, always.
- **No overdrafts** (unless allowed) — enforced against projected state at apply time.
- **Replay determinism:** rebuilding from log = identical state every time.
- Events immutable. Corrections are compensating events, never edits.

**Prove it:**
- 10k random transfers → sum of balances = initial total (money conserved).
- Wipe projection, replay log → identical balances.
- Concurrent transfers from same account → no overdraft, no lost update.
- Query "balance as of event #5000" → correct historical state.

**Teaches:** event sourcing, immutability, projections, replay, consistency. **Closes Oximy + mirrors DualEntry.**

---

## 05 — Self-Healing Distributed Cache (read layer over 03 · expert)

**Problem:** Don't replay the log on every read. Distributed cache in front of balance projection, sharded across nodes. Node dies → keys redistribute, cache **self-heals** by repopulating from the ledger, never serving stale/wrong balances.

**Hard constraint (lesson):**
- **Invalidation on write:** transfer invalidates/updates affected balances; reads never contradict the ledger.
- **Node failure ≠ wrong answers:** kill a node mid-op → reads hit another node or fall through to ledger, never stale money.
- Consistent hashing → losing a node reshuffles minimal keys.

**Prove it:**
- Write → cached balance reflects/invalidates before next read.
- Kill cache node under load → reads stay correct, latency degrades gracefully, keys rebalance.
- Every cached balance vs fresh replay → zero mismatches.

**Teaches:** caching, invalidation, consistent hashing, partition tolerance. **Truth vs speed.**

**Pairs with 03** — ledger is truth, cache is speed; never let speed lie about money.

---

## 04 — Real-Time Collaborative Sync (outlier · advanced · build LAST)

**Deliberately off the money path.** CRDTs give conflict-free eventual consistency — *exactly wrong* for balances. Wedge onto a surface where eventual consistency is correct:

**Problem:** Multiple operators/devices live-edit **transaction metadata** — tags, categories, notes. Two edit the same transaction's tags offline, reconnect → **merge without conflict or lost edits**, converge identically on all clients.

**Hard constraint (lesson):**
- **Convergence:** all replicas reach identical state regardless of op order (CRDT/OT).
- **Offline-tolerant:** edit disconnected, sync on reconnect, no overwrite.
- **No central lock:** concurrent edits merge, don't block.

**Prove it:**
- Two clients edit same tags offline → reconnect → identical merged state, no lost tag.
- Random partition + heal across 3 clients → all converge.
- Final state across replicas → byte-identical.

**Teaches:** CRDTs/OT, convergence, offline-first. **Meta-lesson: which consistency model for which data** — money on the event log, metadata on CRDTs. That architectural judgment is the senior systems signal.

---

## Build Order

```
03 Ledger        ← core, first (just log + projection, no UI)
02 Job Queue     ← feed transfers async into ledger
01 Rate Limiter  ← guard ingress (pairs with 02)
05 Cache         ← speed layer over 03
04 CRDT Sync     ← separate metadata surface, last
```

| Group | Teaches together |
|---|---|
| 03 alone | Consistency, immutability, replay — the spine |
| 01 + 02 | Edge + async + at-least-once + idempotency |
| 03 + 05 | Truth vs speed, invalidation, failure handling |
| 04 solo | Consistency-model judgment (outlier on purpose) |

---

## Definition of Done (per primitive)

- README written **as a spec** (behavior + hard constraint + proof tests)
- The failure test actually runs and passes (kill the worker, fire the concurrency, replay the log)
- Git history shows the breakage and the fix — that's the credibility, not a clean final commit
- One short note: what broke, what you learned, what you'd design differently

---

## Progress

- [ ] 03 Event-sourced ledger
- [ ] 02 Job queue + DLQ
- [ ] 01 Rate limiter
- [ ] 05 Distributed cache
- [ ] 04 CRDT sync
- [ ] All five integrated in one backend
- [ ] README-as-spec + failure tests for each

---

## Related

- Learn: [[Learn/Distributed Systems]] (02 feeds Accord Phase 1), [[Learn/Event Systems]], [[Learn/SQL]], [[Learn/Payments]]
- Companies: [[Companies/DualEntry]] (ledger), [[Companies/Oximy]] (events), [[Companies/Brain Co]] (platform)
- Profile: [[Me]] — `WEAK-current-gaps`, `RECORD-agentic-village-async`
- Rejections: [[Rejections/Oximy]]
