---
company: Oximy
role: Founding Engineer
status: Rejected
stage: Application
priority: High
interestingness: 10
compensation: 25L-60L
domain: AI Infrastructure
applied: 2026-06-18
last_contact: 2026-06-20
follow_up:
next_step:
founder: Naman Ambavi
tech:
  - ClickHouse
  - Analytics
  - Event Systems
rejection: "[[Rejections/Oximy]]"
---

# Oximy

## Why I Applied

- One of the few YC companies whose technical writeup immediately caught my attention.
- Problems are infrastructure-first rather than model-first.
- Strong overlap with interests in event systems, observability, coordination, runtime architecture, and correctness.
- Founding engineer role with meaningful ownership.
- Team appears to value builders over credentials and years of experience.

---

## Interesting Problems

### Canonical Event Model

Everything in the company appears to revolve around converting fragmented AI usage into a single canonical event representation.

Interesting because:
- Similar to event sourcing systems.
- Similar to agent runtime state normalization.
- Direct overlap with my thoughts around async event primitives.

### Parser Drift

Providers silently changing schemas.
Example:
- Field moves.
- Parser succeeds.
- Data becomes wrong.

Interesting because:
- Hard failure is easy.
- Silent corruption is difficult.

### Identity Resolution

Determining that Person A, Device A, Slack Account A, and ChatGPT Account A belong together — without guessing.

Interesting because:
- Graph problem.
- Trust problem.
- Enterprise attribution problem.

### Cost Reconstruction

Vendors do not expose the data enterprises actually care about. Need to reconstruct usage, attribution, cost, and ROI from multiple imperfect signals.

### Analytics at Scale

Append-only event storage. Challenges: deduplication, backfills, replay, idempotency, billion-row analytics.

---

## Notes From Research

### Company Thesis

"The system of record for how the world uses AI."

Primary goal: understand AI usage across organizations, attribute usage correctly, measure ROI, enable governance.

### Technical Themes

- Event systems
- Canonical schemas
- Identity graphs
- Analytics engineering
- Data correctness
- Observability
- Enterprise infrastructure

### What Stood Out

Unlike many AI startups, Oximy's hardest problems are not model problems. Most difficult work appears to be data engineering, infrastructure, event processing, attribution, and enterprise deployment.

### Relevance To Me

Strong overlap: AI systems, agent infrastructure, runtime architecture, telephony event pipelines.

Weak overlap: ClickHouse, analytics engineering, large-scale SQL, identity graph systems.

---

## Scoring

- Interestingness: 10
- Startup Fit: 10
- Skill Match: 6
- Compensation: 8
- Long-Term Value: 10

---

## Outreach Log

| Date | Action |
| --- | --- |
| 2026-06-18 | Applied through YC |
| 2026-06-18 | Sent LinkedIn request to Naman |
| 2026-06-18 | Naman replied and confirmed application received |
| 2026-06-18 | Follow-up discussion regarding company |
| 2026-06-20 | Rejection received |

---

## Related

- Rejection: [[Rejections/Oximy]]
- Learn: [[Learn/SQL]], [[Learn/Analytics Engineering]], [[Learn/Event Systems]]
