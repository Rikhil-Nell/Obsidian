---
company: ElevenLabs
role: Software Engineer
status: Rejected
stage: Application
priority: High
interestingness: 9
compensation: TBD
domain: Voice AI / TTS / Audio ML Platform
location: Remote-first (confirm India eligibility)
source: Job board / careers site
applied: 2026-06-26
last_contact: 2026-06-30
follow_up: 2026-09-25
next_step: Reapply to ElevenAPI Full-Stack Engineer (remote-global) after 90-day block lifts (~2026-09-25). Answers drafted below.
rejection: "[[Rejections/ElevenLabs]]"
founder:
tech:
  - Python
  - Voice models
  - TTS/STT
  - Backend platform
  - React/TypeScript

---

# ElevenLabs

## Why This Matters

- **Thesis company** — voice models as product, not vendor checkbox. User builds downstream (500–600 calls/day, SIP, turn detection, TTS latency in prod).
- **Role fit** — Python backend, platform on voice models, no formal degree required, proof-of-work culture.
- **Not integrator layer** — upstream from TargetDial/Vapi customer work.

**Friction:**
- Remote-first but India hire unclear
- Competitive; founder-mindset bar
- No warm referral

---

## Application Notes

Form answers led with TargetDial prod voice (SIP, 500–600 calls/day), Hindi turn-detection model, Clink agents secondary. ElevenLabs API usage answered honestly per actual integration history.

---

## ElevenAPI Full-Stack Engineer (target reapply ~2026-09-25)

Different, better-fit role than June SWE: **Full-Stack Engineer, ElevenAPI dashboard** (developer-facing DX, API keys, usage analytics, observability views, error/health visibility, DX experiments, OSS SDK contributions). **Remote, globally executable** (no geo gate). Python + TS/React. No degree required, artifact-based.

**Positioning:** lead as their power-user developer + observability builder, NOT "model/infra layer" (this is the dashboard, not the models). Clink observability/analytics maps 1:1 to JD.

**Drafted answers (ready to paste on reapply):**

*What makes you excited about the ElevenLabs mission?* — Been on the developer side of this exact dashboard (built voice agents, shipped tools on speech APIs). Know where the surfaces are smooth vs where you tab out to docs/Discord. Want to build the platform I use, not integrate around it. Global + impact-over-titles = place I can ship.

*Hard problem?* — Indominus SIP/VICIDIAL bridge: LiveKit dynamic IPs vs legacy static IP-auth SIP, no integration path. First-principles SIP auth/routing, user/password auth bridge, cheaper local SIP vs Twilio, 500–600 calls/day. "When the straightforward path doesn't exist, the fix is one layer below." (Alt: Hindi turn-detection custom model.)

*Proud recent achievement?* — Clink founding AI eng: 8+ agent Pydantic AI on FastAPI, customer intelligence (segmentation/churn/campaign), full Logfire observability across async services, 80+ cafes. Emphasis on analytics/observability surfaces = same problem as ElevenAPI dashboard.

*Open source:* work in **sybl** (github.com/Rikhil-Nell/sybl, PyPI, BYOK dictation on speech APIs). Note: built on Deepgram/Groq STT not ElevenLabs — don't oversell as "your stack."

## Outreach Log

| Date | Action |
| --- | --- |
| 2026-06-26 | Applied — Software Engineer. Form: Why ElevenLabs, most impactful build (TargetDial), success metrics, ElevenLabs usage. |
| 2026-06-30 | **Rejected** — Ashby email, generic pass. See [[Rejections/ElevenLabs]]. |
| 2026-08-01 | Attempted ElevenAPI Full-Stack Engineer (remote-global, strong fit). **Blocked** — 90-day per-domain application limit (June 26 SWE app). Eligible to reapply ~2026-09-25. Answers drafted and saved above. |

## Related

- Interviews:
- Rejection: [[Rejections/ElevenLabs]]
- Learn:
