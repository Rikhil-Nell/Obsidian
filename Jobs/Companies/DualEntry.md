---
company: DualEntry
role: Hardcore Engineer (All Levels)
status: Rejected
stage: Recruiting
priority: High
interestingness: 8
compensation: India band (not $200K–$400K US); Rikhil mentioned 40 LPA in pre-screen — deflected equity split, to refine post-rounds
domain: AI-Native ERP / FinTech
location: Remote (India)
source: Ashby (dualentry.com careers)
applied: 2026-06-21
last_contact: 2026-06-25
follow_up:
next_step: Rejected post pre-screen — see [[Rejections/DualEntry]]
founder: Santiago Nestares, Benedict Dohmen
rejection: "[[Rejections/DualEntry]]"
tech:
  - Python
  - FastAPI
  - PostgreSQL
  - AI/LLM
  - Data Migration
  - ERP
  - AWS
  - Next.js

---

# DualEntry

## Why I Applied

- Role title is literally **Hardcore Engineer** — maps directly to how I define myself (`FORM-hardcore-engineer`, dirty problems, ownership, things going south)
- "Experience level doesn't matter; what matters is how ambitious you are" — plays to velocity/range over credentials
- Full ownership, high agency, ship extremely fast — startup operating mode I already run in
- AI-native company building infrastructure (ERP platform) not a wrapper — migration engine, ledger automation, continuous close
- Stack overlap: Python, FastAPI, PostgreSQL, AI systems — my core stack
- Compensation is exceptional: $200K–$400K base + $75K–$175K equity, remote India eligible
- Small team (~50 people), $100M+ raised, massive momentum — early enough to matter, resourced enough to ship
- Data correctness and systems at scale themes — adjacent to event systems / infra interests (financial ledger as canonical truth)

**Concerns going in:**
- Domain is finance/ERP — not my primary passion (voice, agents, event infra). Need to be honest about learning curve on accounting domain
- SQL/analytics/data engineering depth is likely core here — directly hits Oximy gap pattern
- Comp band may target senior US engineers; India remote tier unclear
- Intensity bar is explicit — good fit temperamentally, heavy alongside final year

---

## Interesting Problems

### AI-Powered Migration Engine

DualEntry's differentiator vs legacy ERP: migrate companies from NetSuite/SAP/Dynamics in **24–48 hours** vs typical 9+ month implementations. AI-powered data mapping across legacy schemas. This is a **data correctness + schema normalization** problem at enterprise scale — structurally similar to canonical event models and parser drift (Oximy), applied to financial data.

### Continuous Close / Ledger Automation

Processed $100B+ in journal entries with AI. Automating 90% of manual finance tasks — reconciliation, categorization, anomaly detection, flux analysis. Append-only ledger semantics, audit controls, multi-entity multi-currency. Hard problems: idempotency, deduplication, silent corruption vs hard failure — finance cannot tolerate silent wrong data.

### Multi-Entity at Scale

Customers range from $5M startups to NYSE-listed companies with hundreds of millions ARR. Signal Ventures: 20+ entities, one accountant. Streamside Parks: 36 instances into 1 ERP, 50+ entities, $97M acquisition. System design for complexity without consultant armies.

### 13,000+ Native Integrations

Integration surface area at ERP scale — APIs, webhooks, bank connections, document ingestion (PDF → structured entries). Platform problem, not feature problem.

### Founder-Market Fit as Product

Founders (Santiago Nestares, Benedict Dohmen) scaled previous company to $100M revenue across 20 countries, lived through failed NetSuite implementation ($150K, 9 months, clunky result). Built the product they needed. Strong execution signal — 350+ features shipped in 18 months.

---

## Notes From Research

### Company Thesis

"The AI ERP that just works." Replace legacy ERP (NetSuite, SAP, Sage, Dynamics) with AI-native platform built from scratch post-ChatGPT — not retrofitted cloud ERP.

Primary goal: CFOs close books same-day, automate manual finance work, go live in days not months, run lean finance teams at scale.

### Funding & Scale

- Founded 2024, NYC HQ, ~50 employees, 14 countries
- $90M Series A (Oct 2025) led by Lightspeed + Khosla, GV (Google Ventures), Contrary, Vesey
- $100M+ total raised in ~18 months
- Investors compare to category-definers: OpenAI, Anthropic, Ramp, Stripe, Affirm

### Founders

**Santiago Nestares** — Co-founder & CEO. Previously scaled company to $100M ARR, 20 countries. Built DualEntry after ERP pain.

**Benedict Dohmen** — Co-founder. Finance/accounting domain depth. Public voice on CPA shortage and AI transition in accounting.

### Technical Themes (inferred + reported)

- Python backend (FastAPI/Flask/Django class stack)
- PostgreSQL
- AWS cloud infra
- Next.js frontend
- AI/LLM for document processing, migration mapping, workflow automation
- High feature velocity — 350+ features in 18 months

### What Stood Out

Unlike AI wrapper startups, DualEntry's hardest problems are **platform + data + domain correctness** — migrations, ledger integrity, multi-entity accounting, audit compliance. AI is the engine, not the product pitch.

Lightspeed thesis: "AI-native architecture enables an entirely different business model" — eliminates traditional ERP friction (consultants, 9-month implementations, rigid workflows).

### Relevance To Me

**Strong overlap:**
- FastAPI, PostgreSQL, Python backend, AI agent/workflow systems
- High ownership / hardcore engineer culture
- Building systems others depend on — production gravity
- Data pipeline and correctness thinking (Oximy-adjacent)
- Clink customer intelligence / analytics patterns transfer partially

**Weak overlap:**
- Finance/accounting domain knowledge (GAAP, IFRS, rev rec, close process)
- ERP-specific workflows
- Enterprise sales cycle understanding
- Demonstrated SQL/analytics at finance scale

**Role framing:** Use `FORM-hardcore-engineer`, `STORY-voice-sip-targetdial`, `STORY-clink` (platform/product ownership), `PATTERN-wall`. Lead with builder identity, not student label.

### Potential Concerns

- Finance domain gap — can learn, but competitors may have fintech background
- Student / no degree — role says experience doesn't matter, but team may still filter
- India remote comp may differ from posted US band
- Intensity + final year ECE workload collision

### Glassdoor Risk Signals (reviewed 2026-06-24, 2.5/5 overall)

**Pattern, not noise.** Volume of detailed 1-star reviews from former employees across eng, sales, recruiting, NYC + Bengaluru. Recurring, consistent themes:

- **Founder track record:** Same founders ran **Benitago Group** — reviewers say *identical* Glassdoor pattern (work people to the ground, over-promise to investors/customers/employees). "Same playbook, different industry." This is the most damning signal — a repeat, not a first-time stumble.
- **Product correctness failures:** Multiple reports the "AI-native ERP" has GL integrity problems — debits/credits flipping, balance sheet + AR aging giving different numbers for same account/date. For an ERP this is existential. **Directly relevant to the Hardcore Engineer role** — you'd be building accuracy-critical systems on reportedly shaky foundations.
- **Marketing vs reality gap:** "24-hour migration" and "hundreds of live customers" claims disputed by former staff as overstated. The exact differentiators in this note (migration engine, $100B journal entries) may be hype-inflated.
- **Culture:** 9am–9pm, 6 days, weekend work publicly praised on Slack. Micromanagement, reactive leadership, "Slack decision making," 30-day data retention. India/Bengaluru reviews specifically flag recruiter harassment + promises not honored (working hours, level).
- **Comp is real:** Even critics confirm "they throw money at problems" — the pay is not fake. That's the draw and the trap.
- **5-star reviews look astroturfed:** Clustered timing, defensive "ignore the negative reviews / those are poor performers" language. One 1-star explicitly calls out the suspicious 5-star wave.

**What this changes:** Comp upside is likely real; everything around it (leadership integrity, product maturity, WLB, India promises) is high-risk. Take the screen for information + practice, but treat any offer with serious diligence. **Do NOT abandon own-startup track or burn final year on this without verifying India comp number, real hours, and talking to a former employee directly.**

### Similar Companies

- Ramp
- Rippling (finance modules)
- Modern Treasury
- Mercury
- Oximy (data correctness at scale — different domain, similar systems thinking)

---

## Scoring

- Interestingness: 8 — not dream domain (event/voice infra), but hard platform problems and exceptional company momentum
- Startup Fit: 9 — small team, high ownership, ambitious, well-funded, remote
- Skill Match: 7 — backend/AI stack strong; finance domain + analytics depth weaker
- Compensation: 10 — top of anything tracked so far if band applies
- Long-Term Value: 7 — great learning and capital; less aligned with long-term agent infra thesis than Oximy-tier companies

---

## Outreach Log

| Date | Action |
| --- | --- |
| 2026-06-21 | Applied via Ashby — Hardcore Engineer (All Levels), Remote India. Submitted application form (3 questions). No founder outreach. |
| 2026-06-24 | DualEntry Talent Team emailed — invited to video pre-screen. Booked 2026-06-25, 5:15–5:30 PM IST. Confirmed contact number requested. |
| 2026-06-25 | **Pre-screen passed.** Recruiter said 4 additional rounds. India comp band (not US $200–400K). Rikhil said 40 LPA; deflected equity split. |
| 2026-06-25 | **Rejected** — after pre-screen pass; did not advance to subsequent rounds (or rejected before rounds scheduled). |

---

## Application Form (Submitted 2026-06-21)

### Q1 — How did you get into computers?

> I was the kid who kept tearing apart electronics, modifying my setup, trying different OS, customizing and ricing my terminals, self-hosting random projects and generally spending more time figuring out how things worked than actually using them I would say, At some point I fell into electronics too, but soon I realized its not that I like electronics I like proper systems, and if a system is not proper then making it one is one my of my biggest joys in life, and when I realized that I could do all this from a bedroom with a laptop, I figured this is the path. This gradually pulled me from tinkering to building, I started with small automation projects, then backend development, some hardware sprinkled in there, then machine learning and AI systems, Over time I became much more interested in the systems around AI than the models themselves, API's, infra, telephony, memory, real-time comms, orchestration, harness, tools, mcps, hooks, deployment? no surface untouched. Most of my learning in this field hasn't been a formal course wise study, I pick an impossible problem try to work it out fail, reduce the scope, encompass the pre reqs and then build. now I know the failure modes and also have a project, its a circle really, "this would be cool to build" to spending hours on docs and debugging and learning new stacks.

**Maps to:** `STORY-origin-tinkerer`

---

### Q2 — Why are you a hardcore engineer?

> In my opinion hardcore engineer is one who can solve the dirty grimy problems no one wants to touch, and what they do when things go south and stop working. The projects I am most proud of were no the ones that went smoothly, I sometimes feel that things are too boring if nothing went wrong haha, for example, I worked on voice infrastructure for AI agents and because of how early I entered the field a lot of the bridging that needed to be done between AI and telephony was custom with no SDK, I spent days understanding SIP its authentication, call routing, provider quirks, and infrastructure constraints because the straight forward approach would just not work, it was tedious how most call centers operate on outdated SIP server compared to newer ones like twilio, the compliance and regulations around telephony, I finally solved it allowing my client to connect with a cheaper local SIP provider over using Twilio. On another project, I inherited a humanoid robot that I need to get up and running in a month to show to the Chief Minister of my state, I had no experience with robotics really, I knew I could make the AI brains for it that I made flawlessly but with a constrained environment of my university who weren't willing to provide even pliers for me to use in my lab, I pulled through got it working and it had a nice conversation with the CM recognized it was the CM by his face and voice, and recalled a lot of info, all this one day before my Probability and Random Process Finals btw. I get into these things constantly, I directed the biggest cyber security conference in south India, with over 600+ participants, within a month of being assigned the budget I had a deadline that kept shifting inactive student workforce who were scared for the upcoming two sets of exams, dealing with the administration of the university to approve payments for speakers and bookings and merch, planning and scoping and delegating tasks, also building and deploying and maintaining the website and mobile app the attendants would use, setting up vlans on the uni's unmapped network infra. Did I know much about cybersecurity? not really but I gave two lab exams back to back on 17th then went to YC startup school in Bangalore from Vijaywada on 18th flew back and inaugurated the event on 19th. That's just how I operate if it needs to be done, it has to be done.

**Note:** Same paragraph was submitted twice in the form (duplicate paste).

**Maps to:** `FORM-hardcore-engineer`, `STORY-voice-sip-targetdial`, `STORY-visu-x-robot-cm`, `STORY-recon`

---

### Q3 — List your achievements

*Same response as Q2 — duplicate content submitted for both hardcore engineer and achievements questions.*

---

## Post-Submission Review

**What landed well:**
- Origin story is authentic and distinctive — systems instinct, learning loop, infra-over-models framing
- Hardcore engineer definition matches their job title verbatim
- SIP/voice story is concrete production grit — no SDK, compliance, solved real client problem
- CM robot story shows deadline + unfamiliar domain + zero resources
- Recon shows leadership under chaos — 600+ participants, VLAN, website, YC flight between exams
- Tone matches their "intense, hands-on, high agency" posting

**What was undersold (for future forms):**
- Recon: no 21 lakh sponsorship figure, no 80-person team — see `STORY-recon` for full facts
- Clink not mentioned — platform/recommendation engine ownership would strengthen "achievements" Q
- Production metrics missing: 500–600 calls/day, 80+ cafes, 1M+ call logs
- Q2 and Q3 were identical — achievements question wasted

**Not yet done:**
- No LinkedIn outreach to Santiago Nestares or Benedict Dohmen
- Consider founder message if no Ashby response by follow_up date (2026-06-28)

---

## Application Notes

**Role highlights from posting:**
- Extremely intense, hands-on, full ownership
- High agency, obsession with building/shipping fast
- Language agnostic — learn fast assumed
- Visa sponsorship for NYC relocation possible within 2+ years

**If interview prep needed:** Lead with SIP + Clink platform work. Bridge to DualEntry via data correctness / migration engine / ledger integrity — same systems instinct, finance domain is learnable.

---

## Related

- Interviews: [[Interviews/DualEntry - Pre-Screen]]
- Rejection: [[Rejections/DualEntry]]
- Learn: [[Learn/SQL]], [[Learn/Analytics Engineering]]
