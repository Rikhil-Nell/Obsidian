---
skill: Event Systems
source: Oximy
priority: Medium
status: Started
---

# Event Systems

## Why It Matters

Appeared In:

- [[Rejections/Oximy]]
- [[Companies/Oximy]]

This is both a strength area and a gap. I understand event systems conceptually (telephony pipelines, agent runtime state) but Oximy rejected partly because I couldn't demonstrate production-scale event infrastructure.

---

## Specific Concepts

- Canonical event models
- Event sourcing
- Parser drift and schema evolution
- Identity resolution (graph problems)
- Deduplication, backfills, replay
- Idempotency at scale

---

## Resources

-

---

## Progress

- [ ] Not Started
- [x] Started
- [ ] Intermediate
- [ ] Comfortable
- [ ] Interview Ready

---

## Notes

Recurring interest across applications — event systems keep showing up in companies I find interesting. Gap is depth/proof, not interest. Build a project that demonstrates append-only event storage with replay and deduplication.

**Build path:** [[Learn/Distributed Systems]] — Accord phases cover idempotency, queueing, tenancy, async events.

**Payments:** [[Learn/Payments]] — Stripe webhooks after primitive #02.

---

## Related

- Companies: [[Companies/Oximy]]
- Rejections: [[Rejections/Oximy]]
- Learn: [[Learn/Analytics Engineering]]
