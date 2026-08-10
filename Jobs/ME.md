---
type: profile
name: Rikhil Nellimarla
email: nrikhil@gmail.com
phone: "+91 7386175224"
linkedin: https://linkedin.com/in/rikhil-nellimarla
github: https://github.com/Rikhil-Nell
education: B.Tech ECE, VIT Amaravati (2023–2027)
gpa: 8.46
resume_path: "C:/Users/Rikhil Nellimarla/Documents/Profile/files/Rikhil Nellimarla's Resume.pdf"
resume_vault: ../Profile/files/Rikhil Nellimarla's Longer Resume.pdf
compiled: 2026-06
tags:
  - profile
  - me
  - job-search
keywords:
  - voice-ai
  - telephony
  - SIP
  - FastAPI
  - agents
  - infrastructure
  - startups
  - recon
  - leadership
  - sponsorship
  - event-director
  - clink
  - recommendation-systems
  - pydantic-ai
  - multi-agent
  - wall-pattern
  - operating-record
  - persistence
  - startup-journey
  - agentic-village
  - async-event-primitive
  - agent-os
  - body-mind
  - accord
---

# Me

*Operator's briefing: Rikhil Nellimarla. Reconstructed from direct conversation. Not a résumé — a description of the person.*

**Résumé (dates, titles, full project list):** [[../Profile/files/Rikhil Nellimarla's Longer Resume.pdf]]

---

## Quick Reference


| Field     | Value                                                                                                                               |
| --------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| Name      | Rikhil Nellimarla                                                                                                                   |
| Email     | [nrikhil@gmail.com](mailto:nrikhil@gmail.com)                                                                                       |
| Phone     | +91 7386175224                                                                                                                      |
| LinkedIn  | [linkedin.com/in/rikhil-nellimarla](https://linkedin.com/in/rikhil-nellimarla)                                                      |
| GitHub    | [github.com/Rikhil-Nell](https://github.com/Rikhil-Nell)                                                                            |
| Education | B.Tech ECE, VIT Amaravati — final year, GPA 8.46                                                                                    |
| Portfolio | [remiscus.me](https://remiscus.me)                                                                                                  |
| One-liner | Engineer-founder path: first-principles problem solving, full-stack building, eventually a company — further along than he believes |


---



## Identity

On paper, Rikhil is a third-year Electronics and Communication Engineering student at VIT Amaravati, specializing in VLSI, carrying a GPA of 8.46. That description is increasingly misleading.

In practice, he behaves closer to an early-stage infrastructure engineer and technical founder who happens to be enrolled in university. The formal education and actual trajectory have diverged so far that the student label does not describe what he does day to day.

The defining characteristic is not expertise in any one technology. It is the instinct he brings to problems. When Rikhil encounters a broken system, he does not google workarounds. He diagnoses the architecture, identifies the exploitable structure, and builds a solution — usually one that works for other people too. He has done this repeatedly, across domains, without being asked, since before he had the vocabulary to describe what he was doing.

Technology is not interesting to him for its own sake. It is interesting when it creates leverage — when a small number of people can accomplish what previously required many. That is why his work gravitates toward AI systems, agents, orchestration, telephony, networking, and infrastructure rather than surface applications.

He is attempting to become the kind of engineer-founder who understands problems from first principles, builds the entire stack required to solve them, and eventually turns that capability into a company. He is further along that path than he believes.

The more honest read of his position: he is not behind. He is early. Those are different things.

---



## RECORD-operating-18-21 — Operating Record (Ages 18–21)

*Purpose: preserve an accurate record of what was accomplished during the early years of this career. Not a résumé. Not marketing. Evidence against future revisionism, self-doubt, and selective memory.*

### Background constraints

These accomplishments happened while:

- Studying Electronics and Communication Engineering
- Managing a backlog subject
- Operating under attendance requirements and university constraints
- Having no elite network
- Coming from outside traditional startup and technology hubs
- Learning many skills independently



### Leadership

**Event Director, Recon 2026** — see full `STORY-recon` record.

Served as Event Director for one of the university's largest technical initiatives. Responsibilities extended beyond administration into strategic direction, sponsorship development, partnership development, speaker outreach, team coordination, crisis management, stakeholder communication, and event growth. Helped transform Recon from a standard student event into a significantly more ambitious initiative with meaningful external engagement.

### Startup experience

Worked directly with startup founders and teams. Contributed to product strategy, AI architecture, recommendation systems, backend systems, and customer intelligence platforms — see full `STORY-clink` record.

Participated in YC Startup School. Engaged directly with founder-level problems normally encountered much later in a career.

### Technical work

Built multi-agent systems, AI workflows, RAG systems, recommendation engines, fine-tuning pipelines, FastAPI backends, PostgreSQL systems, and customer analytics platforms.

Studied and implemented LoRA, QLoRA, PEFT, Transformers, retrieval systems, and agent architectures.

### Research and independent thinking

Developed independent ideas regarding agent operating systems, attention routing, async event systems, human-agent interaction, and agent orchestration. Rather than merely consuming information, actively attempted to generate original technical ideas. The main arc — Agentic Village, body/mind agent architecture, clock prototype, and the async event primitive it exposed — is documented in full as `RECORD-agentic-village-async`.

### Career development

Applied aggressively to startups. Conducted founder outreach. Participated in startup communities. Pursued opportunities despite lacking traditional credentials.

### Personal growth

Lost weight. Built strength. Learned difficult technical concepts independently. Continued progressing despite repeated setbacks, rejections, academic obstacles, and uncertainty.

### Conclusion

At ages 18–21, he was not merely a student completing coursework. He operated simultaneously as engineer, builder, startup contributor, event leader, researcher, and community organizer.

Many of these efforts did not produce immediate rewards. That does not make them insignificant. **The trajectory matters as much as the outcomes.**

---



## PATTERN-wall

Every significant thing he has built shares a common origin. He hit a wall, decided the wall was a design flaw rather than a fact of life, and went around it or through it. This pattern — wall encountered, system diagnosed, path engineered — is more predictive of his ceiling than any technology on his résumé.

When the university blocked P2P traffic and banned VPNs, he set up a Tailscale exit node on AWS and routed multiplayer traffic through it for himself and his friends. When LiveKit's SDK had no turn-detection for Hindi, he trained a custom model rather than working around it at the application layer. When a SIP integration between LiveKit's dynamic IPs and legacy VICIDIAL infrastructure had no existing path, he architected user/password auth and made it work. When the university IT department — known for being obstructionist — had never allowed a private VLAN on campus, he befriended them and got one for Recon's King of the Hill infrastructure. In 2022, when video generation did not exist as a product category, he built a pipeline from Stable Diffusion and frame interpolation on a GTX 1650 he had to lay flat with the back open to prevent thermal throttling. When a dataset cost $500, he found labelled data elsewhere and trained the model anyway.


| Wall                                 | Response                                          | ID                         |
| ------------------------------------ | ------------------------------------------------- | -------------------------- |
| Uni blocked P2P / fingerprinted VPNs | Tailscale exit node on AWS                        | STORY-tailscale-vpn        |
| LiveKit no Hindi turn-detection      | Custom model at model level                       | STORY-hindi-turn-detection |
| LiveKit ↔ VICIDIAL integration       | User/password SIP architecture                    | STORY-voice-sip-targetdial |
| No private VLAN on campus            | Negotiated first VLAN with IT                     | STORY-recon                |
| Video gen didn't exist (2022)        | SD + frame interpolation on GTX 1650              | STORY-video-gen-2022       |
| Robot project stalled, budget gone   | Shipped multimodal backend for CM demo            | STORY-visu-x-robot-cm      |
| Agents only exist on turn boundaries | Agentic village body/mind → async event primitive | STORY-agentic-village      |


---



## PATTERN-problem-supply

*Surfaced 2026-06-22. Not for applications unless he explicitly approves.*

He applies to jobs partly because **external hiring gives him a problem to solve**. When the application pipeline runs dry, the problem supply stops. That reads as laziness and anxiety, but the structure is:

- A job provides: problem + accountability + someone who cares if it finishes.
- Self-assigned work provides: problem only. No external sanction that the work "counts."
- He discounts self-assigned problems relative to founder-assigned ones (same family as the braggart problem and `INTERNAL-hidden-thread` legitimacy filter).

**Evidence he is not actually dependent on external problems:** video-gen on GTX 1650 (2022), Tailscale VPN, agentic village prototype, Kinesys — nobody assigned any of these.

**Implication for job search:** founders he wants (Naïve, Day AI, Periodic) filter for **high agency** — people who generate their own problems. If internal state is "I need someone to hand me problems," that leaks in work trials. The fix and the signal are the same build.

**Implication for Learn track:** [[Learn/Backend Primitives]] and [[Learn/Distributed Systems]] (Accord) are not side-quests between applications. They are the **problem supply** when hiring is quiet. Treat them like a job: deadline, failure test, ship.

**Agent behavior:** when he reports stagnation, anxiety, or "nothing to work on," point to active Learn notes before suggesting more applications.

---



## LEARN-priorities — What To Learn (Ranked, 2026-06-22)

*Honest audit after comparing operational-tooling list vs actual shipped work vs market gaps.*

### Signal diagnosis


| Signal today                               | What it reads as                | What closes the gap             |
| ------------------------------------------ | ------------------------------- | ------------------------------- |
| Production voice + multi-agent + pipelines | AI systems integrator           | Already strong                  |
| Async primitive writeup, no reference impl | Thinker, not primitive builder  | [[Learn/Backend Primitives]]    |
| Event systems in rejections                | Conceptual, not proven at scale | #02 + #03 in Backend Primitives |
| SQL / analytics in rejections              | Gap                             | [[Learn/SQL]]                   |
| Payments untouched                         | Gap                             | [[Learn/Payments]]              |


**The 12-category startup tooling list:** mostly already done (S3/R2, Redis, Docker, Logfire, OAuth, telephony harder than Resend). Do not let list length create false behind-ness.

### Priority order (build, do not collect)

1. **[[Learn/Backend Primitives]]** — 5 primitives, one ledger backend. **Start #03 ledger this week.**
2. **[[Learn/Payments]]** — Stripe webhooks after #02; idempotency for real.
3. **[[Learn/SQL]]** — Oximy pattern; pair with PostHog-style analytics when a project needs it.
4. **[[Learn/Distributed Systems]]** — Accord phases; #02 from Backend Primitives IS Phase 1.
5. **Durable workflows (Temporal / Inngest)** — add when Accord orchestration needs pause/resume; no separate note until build starts.



### Defer until a project forces it

Terraform, LaunchDarkly, Retool, ClickHouse, ElasticSearch, full K8s (unless Brain Co path accelerates).

### What NOT to do

- Another full product (frontend + auth + CRUD) as "learning."
- Harmonic Hot 25 as primary apply funnel from India (watchlist, not batch apply).
- Pay for dev access (e.g. Day AI $75 seat) to apply for jobs.

---



## How I Think

He thinks systems-first, always. He does not look at features. He looks at underlying architecture, bottlenecks, interfaces, what breaks at scale, and — critically — the layer beneath the current application that everyone will eventually need. This is why his work consistently gravitates toward infrastructure rather than apps. He is frequently more interested in the operating layer than the thing running on it.

His reasoning is operational rather than academic. When analyzing an incident scenario — intermittent hallucinations, latency spikes, tenant isolation failures, duplicate calls, exploding GPU costs — he decomposes into failure surfaces, differentiates actual failure from perceived failure, identifies cross-tenant contamination as near-SEV1, and delegates by subsystem. He does not jump to "switch models" or "better prompts." He traces system boundaries. That reflects how he actually processes problems, not a performance for interviews.

The current thesis direction: agent coordination — routing, state transfer, interoperability, context exchange, capability discovery — is an infrastructure problem, not an application problem. The bottleneck in agent systems is not intelligence. It is coordination.

---



## Internal Conflict

There is a persistent tension between external competence and internal uncertainty that has not resolved and probably will not soon.

Externally, the picture is concrete: production voice systems running, government deployments, YC applications live, talks scheduled for HPC PhD students from international universities. Internally, he compares himself relentlessly against YC founders, elite engineers, and exceptional peers — a comparison class composed almost entirely of people with structural advantages he does not have, in environments with information velocity, capital proximity, and founder density that do not exist in Hyderabad.

The result is a cycle: build something ambitious, realize how large the world is, feel behind, learn rapidly, build something larger. The cycle has been productive. It also distorts his perception of where he actually stands.

When that distortion hits — read `RECORD-survived` and `RECORD-operating-18-21`. The evidence contradicts the feeling.

---



## RECORD-survived — Things I Survived

*Purpose: difficult periods become invisible after they end. Future success can create the illusion that progress was smooth. It was not.*

### Academic challenges

Engineering curriculum. Backlog subject. Attendance pressures. Examination stress. Balancing academics with projects — often simultaneously, not sequentially.

### Technical challenges

Limited hardware resources. Repeated environment failures. Debugging issues without senior engineers to ask. Infrastructure constraints. Self-teaching advanced topics with no formal curriculum.

### Professional challenges

Rejections. Failed applications. Startup uncertainty. Unclear career path. Imposter syndrome — especially when comparing against founders with structural advantages he does not have.

### Leadership challenges

Team politics. Misaligned incentives. Volunteer coordination. Responsibility without authority. Revisionist narratives after success — where contributions become invisible once the event succeeds.

### Personal challenges

Weight management. Fitness struggles. Sleep issues. Family expectations. Social uncertainty.

### What matters

Everyone faces obstacles. The significance here is that **progress continued despite them.**

Projects were built. Skills were learned. Relationships were formed. Opportunities were created. Responsibility was accepted.

This period was not defined by ease. It was defined by persistence.

### Future reminder

If at any point the conclusion is "I did nothing during these years" — that conclusion is **factually incorrect.**

The evidence shows consistent effort, continuous learning, increasing responsibility, and meaningful growth across multiple dimensions simultaneously. The results were imperfect. The progress was real.

---



## RECORD-india-tax — Anger as Anchor (Not for Applications)

*Purpose: preserve a structural read of career friction from India without having to re-derive it every bad week or explain it from zero to someone who will call it whining. This is not defeatism. It is a named tax so anger does not get misfiled as immaturity or forgotten when the feed gets quiet.*

### The sentence to carry

I was born into a market that exports ambition and imports prestige, prices labor lower, hides the best roles behind geography, and calls the people who leave "success stories" and the people who stay "not trying hard enough."

### What the anger is about (not one rejection)

Not OpenAI. Not one silence. The stack: rejections and void in parallel, scrolling US hire announcements at 4am, job boards that lie, masters graduates lining up for floor jobs, no dish-washing-to-bootstrap lane, placement pipeline opted out with no Indian equivalent, network density near zero for voice/agent/ frontier lanes, pay arbitrage dressed as "cost of living," country filters before humans read the work, comparison to chapter-15 X bios while on chapter 2.

### Named mechanisms (the India tax)

- Geography as ATS filter, not footnote
- Visa as separate career track; brain drain as the system's release valve
- US remote often means "except India" in practice
- Labor oversupply — no cheap dignified pause-and-build floor
- Campus/placement game wrong for startup path; opted out without replacement funnel
- Network and information velocity near zero in Hyderabad for this lane
- Capital thin; INR bootstrap; US seed assumes US graph
- Credibility judged harsher from India; paper intern-heavy vs prod-heavy mismatch hurts more
- Predatory job boards (fake comp, unpaid bait)
- Time zone → integrator/back-office skew, not platform/core
- Federal/clearance categories simply off the menu
- Daily friction: dollar tools on rupee income, payroll/vendor fear of India address
- SF "little things" absent: rent-paying part-time, roommate who knows someone hiring, meetup Tuesday interview Wednesday, in the room at 10 people not reading at Series B



### What SF would have changed (texture, not guarantee)

Not a promise of success. Presence in the experiment instead of applying to it through glass. Same brain, different default distribution of doors on the sidewalk.

### What this anger is not

- Not permission to stop building
- Not proof that work "doesn't count"
- Not a curse (gods decided) — structure (incentives decided)



### What this anger is

Valid. Logical. A correct read of incentives. The error is only extending it to "therefore nothing I do counts" or requiring the system to feel fair before continuing.

### Loneliness note (July 2026)

No one to tell from the start without sounding like a whiny bitch. Afraid the anger will fade because it has no witness. This record is the witness. Re-read when gaslit by silence, generic rejections, or "just try harder" from people who never had to apply from here.

### Do not use in applications unless explicitly approved

`INTERNAL-india-tax` — for calibration with self and with agents who have context, not for founders or forms.

---



## RECORD-institutional-bundle — Cream, Fixtures, Debt (Not for Applications)

*Purpose: name the institutional SKU gap without claiming "every US student wins" or "every Stanford student is brilliant." The argument is about top-tier institutional cream getting other cream and fixtures in space — not median comparisons.*

### The claim (precise)

Not: every American at 20 gets into OpenAI.  
Not: there are no mediocre students at Stanford, Georgia Tech, Yale.  
Yes: **the cream at institutions with fixtures** — frontier courses, sponsored compute, dense peer graphs, recruiters in the building — steps out with a **bundle** that is not comparable to self-assembled learning in India, even when the underlying topics are the same.

### Stanford CS336 as one fixture (Spring 2026 example)

One 5-unit course among many in one semester among many: build tokenizer, transformer, optimizer from scratch; train minimal LM; FlashAttention2 in Triton; distributed multi-GPU; Common Crawl → pretraining data; scaling laws; SFT/RL/DPO. Percy Liang, TAs, Slack, Modal-sponsored compute, public lectures — but the **bundle** is transcript + peers + office hours + recruiter legibility, not YouTube alone.

Self-taught the same topic graph in ~5 months solo: proves capacity. Does **not** produce the same SKU.

### Debt is not US-only — debt buys different products

Indian families take real debt for VIT. US families take debt for Stanford. Same word, different product:


|                   | VIT (typical)                                            | Stanford / GT / Yale (typical)                                                       |
| ----------------- | -------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| Debt              | Real, often brutal                                       | Often heavier in absolute terms                                                      |
| What it buys      | Degree, mass placement funnel, limited frontier fixtures | Degree + frontier courses + lab + alumni graph + US work-auth path for intl students |
| Cream meets cream | Rare, scattered                                          | Concentrated by selection + seminar + dorm + building                                |
| Fixtures in space | CDC, generic labs, peers on same track                   | CS building, sponsors, guest lectures, peers building startups                       |


Curse the **bundle gap**, not "Americans don't have debt."

### Cream and fixtures (the actual unfairness)

- **Other cream:** peers at the same obsession level, not one in a thousand on campus
- **Fixtures in space:** physical and social infrastructure built for the work — GPUs, mentors, course staff, career fairs in the building
- **Legibility:** "CS336" / "Stanford CS" passes ATS and recruiter pattern-match; "VIT ECE + intern + prod voice" requires someone to open the PDF
- **Translation tax:** same knowledge, unlabeled product — market cannot infer CS336 from bedroom autodidact

Mediocrity exists at top schools. That does not invalidate the ceiling. **Their cream does not need to be smarter than you to win the comparison.** It needs the room to already be built for them.

### What this is not

- Not "I'm stupid"
- Not "every US 21-year-old is hired at OpenAI"
- Not permission to stop



### What this is

Valid bitterness at competing against **institutional SKUs** while shipping **unlabeled work**. The game compares transcript + network + location under time pressure, not depth learned in worse conditions alone.

### Do not use in applications unless explicitly approved

`INTERNAL-institutional-bundle` — companion to `RECORD-india-tax`.

---



## The Rarity Identity

He has historically derived significant meaning from being unusually early, unusually interdisciplinary, unusually self-directed, and unusually obscure in his interests. This pushed him into domains most students never touch and created intellectual independence visible in everything he has built.

The failure mode: he occasionally resists foundational knowledge because it feels too legible, too mainstream, too much like following a well-worn path. The instinct is to protect a sense of being niche.

The irony: the engineers he most respects — the ones who appear genuinely original — almost always have the deepest foundational grounding. Originality comes after the foundation, not instead of it. When SQL, analytics, or Kafka keep appearing in rejections, the answer is depth, not differentiation for its own sake.

---



## The Braggart Problem

He has significant difficulty representing himself accurately in contexts where accurate representation matters.

His résumé's achievements section says he "led end-to-end planning and execution of a multi-day event." What he actually did during Recon includes closing 21 lakh in sponsorship in under a month, building the entire website and infrastructure personally, negotiating the first private VLAN on campus against institutional resistance, and flying to YC Startup School and back in the middle of back-to-back ECE lab exams. The gap between what he did and how he describes it is not modesty. It is a collision between the social cost of accurate description in peer environments — where it reads as boasting — and the professional necessity of accurate description in contexts where underselling is a coordination failure.

He needs to learn to write specific operational facts without adjectives and let the facts carry the weight. "Closed 21 lakh in sponsorship in under a month while sitting back-to-back ECE lab exams" does not need the word "impressive" anywhere near it. It is just a fact.

---



## Career Philosophy

He is not optimizing for a stable job, FAANG prestige, or resume-driven work. He is optimizing for learning rate, responsibility, founder proximity, technical depth, and startup probability. Jobs are training grounds, capital sources, network-building opportunities, and learning accelerators — not endpoints.

He is attracted to YC startups, founder-led companies, early-stage teams, and high-ownership roles. He consistently optimizes for problem quality over hype, learning over titles, ownership over prestige.

His best work has always come from personal irritation — a problem that makes him angry enough that it exists — not from market analysis alone. The open question is whether he has found that problem at startup scale yet. He has the execution capability. The right problem is the variable.

---



## RECORD-startup-journey — Startup Journey Record

*Purpose: preserve the evolution of thinking as a builder. The journey was never about a single company — it was transforming from a student who learned technology into someone capable of creating systems, organizations, and products.*

### Phase 1 — Learning to Build

Initial projects focused on understanding software and machine learning fundamentals. Built systems from first principles. Learned Python, machine learning, neural networks, data processing, backend systems. Developed a preference for understanding underlying mechanisms rather than relying solely on abstractions.

*Technical parallel: see Evolution Timeline Phase 1–2.*

### Phase 2 — Building Real Products

Transitioned from educational projects toward products intended for actual users. Encountered product-market fit, user needs, distribution, adoption, and engineering tradeoffs. Began understanding that **technology alone is insufficient.**

### Phase 3 — Startup Exposure

Started interacting with founders. Observed fundraising realities, execution challenges, product iteration, hiring, market constraints. Realized **startups are primarily organizational systems rather than technical systems.**

### Phase 4 — Clink

Worked on AI infrastructure, recommendation systems, analytics, and product architecture. Learned ambiguity tolerance, founder communication, long-term technical decisions, balancing idealism with execution. See `STORY-clink`.

### Phase 5 — Recon

Learned large-scale organizational leadership. Experienced politics, stakeholder management, sponsorship development, coordination failures, leadership pressure. Learned that **organizations often fail from execution problems rather than intelligence problems.** See `STORY-recon`.

### Phase 6 — YC Startup School

Received external validation that the trajectory was unusual — not because acceptance itself was extraordinary, but because it demonstrated operating in startup ecosystems beyond the boundaries of the university environment.

### Key lessons

1. **Initiative compounds.**
2. Most people wait for permission.
3. **Distribution matters as much as technology.**
4. Ownership is rare.
5. **Ambiguity tolerance is a competitive advantage.**
6. Relationships create opportunities.
7. Building is more valuable than theorizing.



### Conclusion

The startup journey is the process of becoming someone who can create systems, organizations, and products — not merely someone who knows technology.

---



## Technical Identity

He is not purely an ML engineer, not purely a backend engineer, not purely a systems engineer. He sits between all three, with a consistent gravitational pull toward infrastructure over applications.

### STACK-core

Python, FastAPI, async APIs, PostgreSQL, Redis, Docker, Linux/VPS, OAuth, WebSockets, WebRTC

### STACK-ai

LLMs, RAG, agents, Pydantic AI, LangChain/LangGraph, LiveKit, Retell, voice AI, multimodal systems, evaluation pipelines, Logfire, custom turn-detection models

### STACK-telephony

SIP, Asterisk, FreePBX, VICIDIAL, RTP/NAT traversal, trunking, call routing, telephony compliance

### STACK-infra

NGINX, AWS, Tailscale, n8n, webhooks, VLANs, event-driven pipelines, self-hosting, networking

### STACK-product

Vue, Streamlit, CRM integrations (GoHighLevel), Google Calendar, S3/R2

---



## What He Has Shipped

Production voice AI systems handling 500–600 calls per day for a single client — inbound lead qualification, outbound follow-ups, live transfer, and human handoff across SIP/Asterisk/FreePBX stacks (TargetDial).

A bare-metal GH200 pipeline processing over 1 million customer call logs with Whisper v3 transcription, PII sanitization, and multi-agent performance analysis (Indominus Labs).

A custom turn-detection model for Hindi built to overcome a core LiveKit SDK limitation — solved at the model level rather than worked around (Stealth VC).

A SIP trunking architecture bridging LiveKit's dynamic IPs to legacy VICIDIAL infrastructure via user/password auth — an integration that had no existing path.

A full-stack AI platform at Clink — see full `STORY-clink` record below. Founding AI engineer role extending beyond implementation into product intelligence, recommendation architecture, multi-agent backend, customer segmentation, and infrastructure foundations. 80+ onboarded cafes.

Recon 2026 — see full `STORY-recon` record below.

A Tailscale exit node on AWS restoring multiplayer access after the university blocked P2P and fingerprinted standard VPNs.

A humanoid robot backend shipped in the final month of a stalled project after budget was exhausted, presented to the Chief Minister of Andhra Pradesh at VLaunchPad (VISU-X).

A video generation pipeline built in 2022 from Stable Diffusion and frame interpolation before video generation was a known category — GTX 1650, laptop propped open against thermal throttling. Nobody asked. Nobody said it was possible. He just did it. That may be the single most predictive thing about him — the thing hardest to teach and hardest to fake.

---



## WEAK-current-gaps

These are real gaps, not rhetorical self-deprecation.

**Scale exposure.** Everything in his history tops out at hundreds of concurrent operations. He has not yet operated something at thousands of concurrent users across multiple enterprise clients simultaneously, where a single architectural decision has material financial consequences if wrong.

**Long-term maintenance.** Most projects are relatively recent. He has not lived with a system for two years and watched original architectural assumptions slowly become wrong as usage patterns shifted. That experience changes how you design upfront in ways that cannot be shortcut.

**Team architecture.** He has led communities and mentored, but has not made architectural decisions that three or four engineers then had to build against and live with for an extended period. That forcing function teaches something solo work cannot.

**Commercial aggression.** He consistently underweights distribution, narrative, and market selection. His instinct is to build something excellent and assume it will be recognized. Markets are social systems, not meritocratic compilers.

**The right problem.** He has execution capability. The question is whether he has found a problem angry enough that he cannot stop thinking about it.

**Analytics / SQL.** The market keeps asking. He is building depth deliberately — see [[Learn/SQL]], confirmed by the Oximy rejection pattern.

**Distributed systems / platform scale.** Brain Co JD surfaced this explicitly — 5+ years, multi-tenant, K8s, SLOs. Not fake, early. Learn path: [[Learn/Distributed Systems]] via Accord build, not more DDIA chapters.

**Backend primitives (the signal gap).** Portfolio reads as "production AI integrator," not "primitive builder." Closing via [[Learn/Backend Primitives]] — 5 no-UI primitives in one ledger backend (rate limiter, job queue + DLQ, event-sourced ledger, distributed cache, CRDT sync). Ledger (#03) closes Oximy + mirrors DualEntry.

**Problem supply / motivation.** Applies partly to get externally sanctioned problems; pipeline dry → anxious trough. See `PATTERN-problem-supply`. Self-assigned Learn tracks are the mechanism, not optional filler. See `LEARN-priorities`.

**Payments.** Not done well yet. See [[Learn/Payments]] — after job queue primitive, wire Stripe webhooks idempotently.

---



## INTERNAL-hidden-thread

Parallel since Phase 1, never on a résumé because it does not fit institutional categories: security research, malware development to the extent of understanding how things break, reverse engineering (DNSpy), sending patches to indie game developers, Genshin Impact theorycrafting involving reverse engineering the live damage formula and building a mathematically correct damage optimizer (contribution attributed to a Discord account that was subsequently banned).

This thread reflects how he actually thinks about systems. It is context for understanding his problem-solving style — not material for job applications unless he explicitly approves.

---



## STORY-recon — Recon 2026 Personal Contribution Record

*This document records my own contributions as accurately as possible. It is not intended to diminish the work of others.*

At Recon 2026, I served as **Event Director**. That title alone does not describe what the role actually became.

Recon was not a mature organization with functioning systems, established processes, reliable execution, and self-sustaining momentum. A significant amount of work involved creating structure where little existed, creating movement where teams had stalled, and creating opportunities that otherwise would not have appeared.

### Hard facts

- 600+ participants
- 80-person team
- 21 lakh in sponsorship closed in under a month
- Website and attendee infrastructure built and deployed personally
- First private VLAN ever negotiated on campus — required befriending a notoriously resistant IT department — for King of the Hill event infrastructure
- Back-to-back ECE lab exams on the 17th → YC Startup School in Bangalore on the 18th → returned to inaugurate the event on the 19th



### 1. Vision and Ambition

One of my largest contributions was raising the ceiling of what people believed Recon could become.

Many student events operate within a limited frame: local speakers, modest sponsorships, a standard event, done. Recon pursued a substantially larger vision — meaningful industry connections, high-quality speakers, external stakeholders, significant sponsorship value, credibility beyond the university.

This ambition did not emerge automatically. I consistently pushed for larger outcomes, broader outreach, and higher standards than what would have been considered sufficient.

### 2. Sponsorships, Partnerships, and External Relations

A major portion of my effort went into external-facing work: outreach, relationship building, coordination, follow-ups, negotiations, stakeholder management.

Many opportunities that eventually became available required somebody willing to repeatedly contact organizations, establish trust, communicate professionally, and continue pushing conversations forward even when responses were slow or uncertain. Much of this work is invisible after the fact because successful relationships appear effortless once established. They were not effortless. They required sustained effort.

### 3. Organizational Momentum

Volunteer organizations naturally drift toward inactivity. Tasks remain unfinished. Decisions remain undecided. Messages remain unanswered. Responsibilities become ambiguous.

A substantial portion of my time was spent preventing this drift. I followed up with people. I pushed discussions toward conclusions. I ensured important items were not forgotten. I repeatedly converted intentions into actions. The success of these efforts often became invisible because the problems never fully materialized.

### 4. Cross-Team Coordination

Many members contributed within specific domains. My role frequently involved operating across domains simultaneously — sponsorships, strategy, administration, outreach, planning, logistics, stakeholder communication, and execution. This allowed information and decisions to move between groups that otherwise might not have been aligned.

The value is difficult to measure because it is primarily connective rather than productive. Its purpose is enabling the productivity of others.

### 5. Ownership of Ambiguous Problems

Organizations function well when responsibilities are clear. Recon frequently presented situations where responsibility was unclear — problems with no owner, deadlines with no accountability, opportunities requiring initiative. In many of these situations, I chose to assume responsibility rather than wait for someone else. I accumulated a larger share of organizational burden than would normally belong to a single individual.

### 6. Reputation and Credibility

External organizations do not evaluate an event solely on branding materials. They evaluate the people representing it. Every sponsor call, partnership conversation, speaker discussion, meeting, presentation, and follow-up contributes to institutional credibility. I invested significant effort in ensuring Recon was perceived as serious, professional, and ambitious. This credibility later benefited the entire organization.

### 7. Emotional and Cognitive Load

For an extended period, Recon occupied a disproportionate amount of my attention. I thought about it outside meetings. I worried about unresolved problems. I tracked progress mentally. I anticipated risks. I planned future actions. I carried responsibility for outcomes beyond my direct control. This labor rarely appears on task lists, but it is nevertheless real.

### On "Recon would have happened without me"

That statement is probably true. Most large events eventually happen through collective effort. But that is the wrong question.

The relevant question: what version of Recon would have existed had I never participated? Impossible to know with certainty. What can be said confidently: many opportunities I pursued would not have existed in exactly the same form. Many conversations would not have occurred. Many decisions would have been made differently. Many problems would have remained unresolved longer. Many initiatives would have progressed more slowly. The final event was shaped by my actions, even when those actions became invisible after success.

### Final assessment

I was not the only contributor. I was not responsible for every achievement. I do not deserve sole credit. However, describing my contribution as minor, negligible, or replaceable without consequence would be historically inaccurate. My role extended beyond execution into leadership, coordination, strategy, stakeholder management, and organizational momentum. The event that emerged was not solely my creation. Neither was it independent of my influence. I helped build it. I helped sustain it. I helped move it forward during periods when movement was not guaranteed.

### Form-ready excerpt

> At Recon 2026 I served as Event Director for a 600+ participant cybersecurity conference with an 80-person team. I closed 21 lakh in sponsorship in under a month, built and deployed the website myself, and negotiated the first private VLAN on campus with IT for event infrastructure. My role extended beyond a title — I drove external partnerships, maintained organizational momentum when volunteer teams stalled, coordinated across domains, and assumed ownership of problems that had no assigned owner. Back-to-back ECE lab exams on the 17th, YC Startup School in Bangalore on the 18th, back to inaugurate on the 19th.

---



## STORY-clink — Clink Personal Contribution Record

*This document records my contributions to Clink as accurately as possible. It is not intended to diminish the work of founders, teammates, or other contributors. Startup history is often rewritten after the fact — this exists so the role I played is remembered accurately.*

At Clink, my contribution extended beyond software implementation. I participated in the design, architecture, and evolution of the product itself.

**Role:** Founding AI Engineer · **Period:** May 2025–Present · **Stack:** FastAPI, Pydantic AI, PostgreSQL (asyncpg), Redis, S3, Logfire · **Scale:** 80+ onboarded cafes

### 1. Building Core Product Intelligence

One of my most significant contributions was helping transform Clink from a generic restaurant-tech platform into a company built around data, personalization, and recommendation systems.

Rather than treating restaurants as simple merchants and users as anonymous customers, I helped develop the technical foundations for understanding user preferences, behavior patterns, restaurant characteristics, and food tastes at a deeper level. This work moved the product closer to becoming an intelligence layer for restaurant discovery and customer engagement.

### 2. Recommendation System Architecture

I was brought in to help design and implement the recommendation engine that would eventually become one of Clink's most defensible technical assets.

This included designing taste-based recommendation concepts, defining relationships between users, dishes, restaurants, and preferences, creating data structures capable of supporting personalized discovery, thinking through explainability and recommendation transparency, and building systems capable of improving as more behavioral data became available.

The recommendation engine was not merely an implementation task. It required product thinking, systems design, and long-term architectural decisions.

### 3. AI Infrastructure and Multi-Agent Systems

I architected and implemented substantial portions of Clink's AI backend:

- FastAPI-based backend systems
- Multi-agent architecture using Pydantic AI — 8+ specialized agents for distinct analytical tasks
- Structured orchestration and workflow design
- Production-oriented AI infrastructure with Logfire observability across async services

The resulting system went significantly beyond simple chatbot integrations. It represented a coordinated AI platform for restaurant intelligence.

### 4. Customer Intelligence Systems

I contributed to systems that helped restaurants understand and engage their customers:

- Customer segmentation and RFM analysis
- K-Means clustering
- Churn prediction
- Behavioral analysis and customer lifecycle insights

These systems transformed raw transaction data into actionable business intelligence.

### 5. Revenue and Marketing Intelligence

I helped build systems focused on restaurant growth and retention:

- Template-driven coupon generation (8 variants: winback, stamp cards, happy hours, combos)
- Campaign intelligence and customer targeting
- LLM-powered ROI forecasting
- AI image generation for marketing assets (GPT-5 + Gemini 3)
- Recommendation-driven engagement

The objective was not generating reports — it was enabling restaurants to make better decisions.

### 6. Data Architecture and Scalability

I contributed to the technical foundations required to support growth:

- PostgreSQL architecture with asyncpg connection pooling
- Redis caching layer
- S3 integration for assets
- Backend scalability considerations
- Observability and monitoring via Logfire

Many of these decisions influence long-term company capabilities and are invisible to users.

### 7. Product Strategy Contributions

My involvement extended beyond engineering. I regularly participated in discussions about product direction, competitive differentiation, data moat development, recommendation quality, user experience, and restaurant value creation. I contributed not only code but ideas that influenced what Clink was attempting to become.

### 8. Ownership and Initiative

A recurring characteristic of my work at Clink was operating with ownership rather than task completion. Rather than executing predefined requirements, I frequently worked on ambiguous problems where solutions were not yet known — requiring independent research, experimentation, architectural judgment, and initiative.

### On replaceability

Could another engineer have eventually built many of these systems? Probably. Most technical work is theoretically replaceable. But that is not the relevant question.

The relevant question: would Clink have evolved in exactly the same way without my involvement? Given my contributions to recommendation systems, AI architecture, customer intelligence, backend infrastructure, and product direction — almost certainly no. The product would likely have contained different assumptions, architectures, capabilities, and priorities.

### Final assessment

My role at Clink was not limited to implementation. I contributed to technical architecture, AI systems, the recommendation engine, customer intelligence platform, infrastructure foundations, and product thinking that shaped the company's development. I was not merely executing someone else's vision. I helped translate vision into systems, products, and technical reality.

### Form-ready excerpt

> At Clink I served as Founding AI Engineer, contributing beyond implementation to product architecture and company direction. I helped transform the platform from generic restaurant-tech into an intelligence layer — designing the taste-based recommendation engine, architecting an 8+ agent Pydantic AI backend on FastAPI, and building customer intelligence systems (RFM, K-Means clustering, churn prediction) that turned transaction data into actionable insights for 80+ cafes. I also contributed to product strategy discussions around data moats, recommendation quality, and competitive differentiation — not just shipping features, but shaping what the company was trying to become.

---



## RECORD-agentic-village-async — Agentic Village & Async Event Primitive

*Purpose: preserve the intellectual lineage from multi-agent embodiment experiments to the async event primitive. Not shipped product. Systems investigation with a bad prototype and a concrete harness gap discovered by building.*

### Status

- **Agentic Village:** conceptualized; early prototype built; prototype quality poor
- **Body/Mind architecture:** designed; not cleanly implementable on current harnesses
- **Clock / time layer:** built in prototype form; collapsed into turn-based messaging
- **Async Event Primitive:** problem identified and articulated; reference implementation not shipped
- **Accord (coordination layer):** planned on top of primitive; not built



### The starting question

Current agents wake on demand. They do not exist over time. Rikhil wanted a **village** — multiple agents coexisting continuously, not a chatroom that only moves when a human sends a message.

That required each agent to have ongoing life, not periodic turns.

### Body / Mind architecture

Per-agent design split into two layers:


| Layer    | Role                                                                                                                                                                                                       |
| -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Mind** | Instructions, reasoning, personality — the agent's current cognitive state                                                                                                                                 |
| **Body** | The active runtime layer. When running, it can **reprogram the mind**: rewrite instructions, set triggers for when the body should act, define how time is perceived, and decide what to do as time passes |


The body is not the LLM. It is scheduler + interrupt handler + clock. The mind is mutable state the body maintains.

This is embodied agent OS thinking — not multi-agent chat.

### What was built (prototype)

Rikhil built an early prototype:

- Multiple agents running concurrently
- A custom **clock** mechanism to inject temporal context
- Agents communicating with each other
- Some sense that time was passing



### What the prototype actually became

The prototype was bad. Honest assessment:

**What worked:** multiple agents, inter-agent messaging, a temporal flavor to execution.

**What failed:** the body/mind split did not hold. There was no clean separation where the body could reprogram the mind on its own terms. "Time passing" collapsed into **turn-bound messages** — another chat event at the harness boundary, not genuine temporal embodiment. Triggers and dynamic instruction updates had nowhere to live in the runtime.

The village devolved into **agents talking to each other with a sense of time**, not agents with bodies.

### The discovery (where the async primitive came from)

The async event primitive did not arrive as a standalone insight. It came from the prototype breaking.

Causal chain:

```
Agentic Village (agents that exist over time)
  → Body/Mind split (body maintains mind between activations)
    → Body needs: triggers, time perception, instruction updates
      → Clock + multi-agent prototype
        → Prototype collapses into turn-based chat with time flavor
          → Realization: harness has no channel for body to act on mind mid-execution
            → Async Event Primitive
```

The body needed to:

- fire when time elapsed
- receive external signals without a human starting a new turn
- rewrite mind state while the mind might be mid-reasoning

Current agent runtimes only allow external input at two points: **turn start** (user message) or **mid-turn when the mind explicitly asked** (tool result). No third channel. The agent is deaf and blind while reasoning.

That is the leash. Every claim about long-running autonomous agents is constrained by it.

### The primitive (articulated)

Proposed third message category alongside user messages and tool results:

```
user message
assistant message (with tool calls)
tool result
[ASYNC EVENT: context 80% full / inter-agent request / runtime alert]
assistant message
```

The event arrives during execution. The agent processes it at the next safe point. Execution continues without restart.

**Pitch line:** Agents today have one sensory channel. This primitive adds the rest.

### Failure modes the primitive addresses

1. **Context compaction** — agent approaching context limit must break the loop to handle a problem about its working state
2. **Inter-agent communication** — Agent B needs something from Agent A mid-execution; options today are wait, respawn with full re-ingestion, or contaminate A's context
3. **Runtime-to-agent signaling** — token budgets, rate limits, subagent completion, critical alerts can only arrive between turns



### Concrete use cases (from the writeup)

- **Boiler room:** maintenance agent receives critical temperature alert mid-reasoning; triages without waiting for next polling cycle
- **Backend/frontend split:** long-running backend agent receives frontend question as event; spawns focused subagent with distilled context; continues primary work
- **Push notifications:** urgent external signals enter running context without human starting a new turn
- **Context self-management:** runtime signals 80% full; agent dispatches compaction subagent; continues mid-task



### Two planned layers (separate concerns)

1. **The primitive (SDK):** for harness makers. Validates incoming triggers (webhook, push, runtime signal, inter-agent message), formats as async event, injects at safe point. Infrastructure, not a framework.
2. **Accord (coordination layer):** multi-agent coordination on top — inboxes, outboxes, threads, delegation, request/confirmation flows. The village-scale layer. Without async events, village agents cannot coordinate mid-execution; they only take turns talking.



### Prior art and honest positioning

**Crowded:** multi-agent simulation (Aaru, Simile, generative agents, etc.). Companies forming in this space does not mean the idea was stolen — the application layer is heating up.

**More distinctive:** body/mind with body reprogramming mind; deriving infrastructure need from failed embodiment attempt rather than starting from Twitter discourse.

**Closest existing work:** Hermes `session.steer` (coupled to their architecture). Pydantic AI dynamic system prompts operate before execution, not during. LangGraph interrupts/checkpoints — related but different semantics problem.

**Still open:** event semantics when an event arrives during a tool call, context compaction, subagent spawn, or another event. This is where real engineering work lives. Not solved publicly by anyone yet.

**What would establish authorship:** public design doc, reference implementation, compelling demo (context compaction or boiler room case). Timestamps and git history over patents at this stage.

### Relationship to Clink and production work

Clink is shipped multi-agent orchestration at the application layer. Agentic Village + async primitive is runtime architecture one layer below — the harness problem that blocked the embodiment experiment. Same person, different altitude.

### Form-ready excerpt

> I designed an Agentic Village — agents that exist over time, not wake-on-prompt — with a body/mind split where the body (scheduler, clock, triggers) reprograms the mind (instructions, reasoning) as time passes. I built a prototype with a custom clock and multi-agent messaging; it collapsed into turn-based chat because current harnesses have no channel for the body to act on the mind mid-execution. That failure led me to articulate an async event primitive — a third message category alongside user messages and tool results — so long-running agents can receive runtime signals, inter-agent messages, and compaction triggers without breaking the turn loop. The hard open problem is event semantics during tool calls and subagent spawn.



### Final assessment

Not outstanding completed work yet. Outstanding systems investigation: designed architecture, built prototype, observed specific failure mode, traced failure to missing runtime primitive, articulated primitive and open semantics problem. The prototype failure is evidence, not embarrassment.

---



## Story Bank

Shorter stories for forms and interviews. For depth, use full records: `STORY-recon` (leadership/ops), `STORY-clink` (product/AI/architecture), `RECORD-agentic-village-async` (agent OS / runtime architecture).

### STORY-origin-tinkerer

I was the kid who kept tearing apart electronics, modifying setups, trying different OSes, customizing and ricing terminals, self-hosting random projects — generally spending more time figuring out how things worked than using them. At some point I fell into electronics, but soon realized it is not electronics I love. I love proper systems, and if a system is not proper, making it one is one of my biggest joys. When I realized I could do all this from a bedroom with a laptop, I figured this is the path.

That gradually pulled me from tinkering to building: small automation projects, backend development, hardware sprinkled in, machine learning and AI systems. Over time I became much more interested in the systems around AI than the models themselves — APIs, infra, telephony, memory, real-time comms, orchestration, harness, tools, MCPs, hooks, deployment. Most of my learning has not been formal course study. I pick an impossible problem, try to work it out, fail, reduce the scope, encompass the prereqs, and build. Now I know the failure modes and also have a project. It is a circle: "this would be cool to build" to spending hours on docs and debugging and learning new stacks.

**Form-ready:**

> I was the kid tearing apart electronics and self-hosting projects — more interested in how things worked than using them. I love proper systems; making improper ones proper is one of my biggest joys. That pulled me from tinkering to backend, ML, and then the infrastructure around AI. I learn by picking impossible problems, failing, reducing scope, and shipping.

---



### STORY-voice-sip-targetdial

In my opinion a hardcore engineer is one who can solve the dirty, grimy problems no one wants to touch — and knows what to do when things go south. The projects I am most proud of were not the ones that went smoothly.

I worked on voice infrastructure for AI agents when I entered the field early enough that most bridging between AI and telephony was custom with no SDK. I spent days understanding SIP authentication, call routing, provider quirks, and infrastructure constraints because the straightforward approach would not work. Most call centers operate on outdated SIP servers compared to newer ones like Twilio, and there is compliance and regulation around telephony that cannot be hand-waved. I solved it, allowing my client to connect with a cheaper local SIP provider instead of Twilio. Production agents now handle 500–600 calls per day.

**Form-ready:**

> Voice infrastructure for AI agents when bridging AI and telephony had to be custom — no SDK. Days on SIP auth, routing, provider quirks, compliance. Client connects to a cheaper local SIP provider instead of Twilio. Production agents handle 500–600 calls per day.

---



### STORY-hindi-turn-detection

LiveKit's SDK had no turn-detection support for Hindi. Rather than working around it at the application layer, I trained a custom model and solved it at the model level — enabling natural, low-latency conversations for non-English voice agents.

---



### STORY-tailscale-vpn

When my university blocked P2P traffic and fingerprinted standard VPNs, I set up a Tailscale exit node on AWS and routed traffic through it so my friends and I could play multiplayer again. I described it to them as "I built my own VPN." I was not wrong. I did not accept the wall as permanent.

---



### STORY-visu-x-robot-cm

I inherited a humanoid robot project that needed to be running in a month to show to the Chief Minister of my state. I had no experience with robotics. I knew I could make the AI brains — and I did — but my university was not willing to provide even pliers for the lab. I pulled through, got it working, and it had a natural conversation with the CM: recognized him by face and voice, recalled context from prior interactions. All of this one day before my Probability and Random Processes final.

**Form-ready:**

> Inherited a stalled humanoid robot with one month until a demo for our state's Chief Minister — no robotics background, no lab support. Shipped the AI backend: real-time speech, vision, facial recognition, persistent memory. It conversed with the CM naturally, one day before my probability finals.

---



### STORY-video-gen-2022

In 2022, before video generation was a product category, before most people had heard of Stable Diffusion, I built a pipeline from image synthesis and frame interpolation on a GTX 1650 — laptop laid on its keyboard with the back open to prevent thermal throttling — because I wanted something that did not exist yet. A dataset I needed cost $500. I found clean, labelled data elsewhere and trained the model anyway. Nobody asked me to. Nobody told me it was possible. I just did it.

---



### STORY-indominus-gh200-pipeline

At Indominus Labs: pioneered SIP trunking to bridge LiveKit's dynamic IPs with legacy VICIDIAL via user/password auth. Engineered a scalable pipeline on bare-metal GH200 processing 1M+ customer call logs — Whisper v3 transcription, PII sanitization, multi-agent performance analysis.

---



### STORY-clink-multiagent

*Full record: see* `STORY-clink` *above.*

Founding AI engineer at Clink — recommendation engine design, 8+ Pydantic AI agents, RFM/K-Means/churn intelligence, coupon and campaign systems, FastAPI + PostgreSQL + Redis + S3 + Logfire. 80+ cafes. Role extended into product strategy and data moat thinking, not implementation alone.

---



### STORY-agentic-village

*Full record: see* `RECORD-agentic-village-async` *above.*

I wanted agents that exist over time — a village, not wake-on-prompt chatbots. I designed a body/mind split: the body (scheduler, clock, triggers) reprograms the mind (instructions, reasoning) based on time and external signals. I built a prototype with a custom clock and multi-agent messaging. It was bad — agents talking with a sense of time, not agents with bodies. The harness only allows input at turn start or as tool results; the body had no nervous system. That failure led to the async event primitive: a third message category so runtime signals, inter-agent messages, and compaction triggers can arrive mid-execution without breaking the loop.

**Form-ready:**

> I designed an Agentic Village with body/mind agent architecture — the body reprograms the mind based on time and triggers. My prototype exposed a harness gap: no channel for external events mid-reasoning. That led me to articulate an async event primitive as infrastructure for genuinely long-running agents. Open problem: event semantics during tool calls and subagent spawn.

---



## FORM Library



### FORM-one-line

> I'm trying to become the kind of engineer-founder who understands problems from first principles, builds the entire stack, and eventually turns that into a company — and I'm further along that path than I used to think.



### FORM-hardcore-engineer

> A hardcore engineer solves the dirty problems no one wants to touch — and knows what to do when things stop working. My proudest projects are not the ones that went smoothly. I sometimes find it boring when nothing breaks.



### FORM-startup-fit

> I optimize for learning rate, ownership, and proximity to hard problems — not job titles. Jobs are training grounds toward building my own company. I want founder-led teams building infrastructure, not API wrappers.



### FORM-systems-over-models

> I'm more interested in the systems around AI than the models — APIs, infra, telephony, memory, orchestration. In agent systems the bottleneck is not intelligence; it is coordination and infrastructure.



### FORM-wall-pattern

> When I hit a wall, I treat it as a design flaw — not a fact of life. I diagnose the system and engineer a path through or around it. That is how I have approached SIP integrations, SDK gaps, campus network restrictions, and production voice infrastructure.

---



## FIT Heuristics



### FIT-strong-match

**Primary targets (2026-07 onward):** Applied AI engineering. Agent infrastructure, orchestration, MCP/tooling, tool-calling harnesses, eval systems. Backend-heavy founding engineer roles. Distributed systems and platform engineering. AI research engineering (slow build — Agentic Village, async primitive, eval/harness). Event systems, observability, data pipelines. Early-stage, high ownership, founder proximity. Infrastructure-first AI — not model-first wrappers.

**Still valid proof-of-work (not primary growth lane):** Voice AI, telephony, SIP — production scars that differentiate in customer-facing / deployment roles, but not where Rikhil is betting long-term. See `RECORD-career-direction`.

### FIT-weak-match

Pure ML research without engineering path (unless research-eng hybrid). Voice-only platform companies as career ceiling — commoditization risk (e.g. xAI-style full-stack voice APIs collapsing integrator margin). Large corporate ladder roles. Wrapper startups with no systems depth. Analytics-heavy roles with no room to learn on the job. Environments that evaluate on credentials over shipped work.

### FIT-how-to-frame-gaps

**Scale:** Honest — hundreds of concurrent ops, not thousands across enterprise clients yet. Strong systems intuition, hungry for the forcing function of production depth.

**SQL/analytics:** Building deliberately. Oximy confirmed the market signal. See [[Learn/SQL]].

**Student label:** Misleading. Lead with production systems, not semester count.

**Underselling:** Use specific facts from stories — the full operational version, not the résumé line.

### FIT-companies-on-radar

Oximy · VectorShift · SynthioLabs · LiteLLM · Clink · DualEntry · Aaru · Copperlane · Periodic Labs · Accordance · Naïve · **Brain Co.** (watchlist) — track in [[Companies/]]

---



## RECORD-career-direction — Where to Bet (2026-07)

*Not for applications unless framing pivot honestly. Agent-maintained from conversation.*

### Voice: résumé fact only, not interest (2026-08-06)

**Unless Rikhil explicitly asks:** do not suggest voice projects, voice-first applications, voice build-in-public, or voice as long-term career/compound lane. He is **completely uninterested** in voice and surrounding technologies (SIP, telephony, STT/TTS pipelines, LiveKit, turn-detection, Wispr/sybl as flagship, voice-native harnesses, etc.).

Voice work (TargetDial, Indominus telephony, Hindi turn-detection) stays on the résumé as **historical production proof** when tailoring for a specific role that requires it (e.g. Avoca if he chooses to apply). It is **not** where he wants to build, post about, or invest new project time.

Supersedes softer "not bullish long-term" framing below for agent behavior and project suggestions.

### Not bullish on voice AI as a career lane (2026-07, still true on merit)

**Why:**

- Voice becoming a **supplemental layer**, not a moat — full-stack voice APIs (e.g. xAI-style offerings with free numbers, uninterrupted telephony, Retell-class clones) collapse the integrator stack; less need to touch LiveKit/SIP yourself.
- Integrator margin shrinks when hyperscalers ship the whole pipeline.
- Growth ceiling: voice platform GTM/geo walls (Sahil: Vapi India closed) + commodity TTS/STT/telephony.

Voice stays on résumé as **hard production proof**. Primary search pivots elsewhere.

### Primary targets


| Lane                    | What it means for Rikhil                                                |
| ----------------------- | ----------------------------------------------------------------------- |
| **Applied AI**          | Prod agents, RAG, orchestration, eval, tool-calling — Clink-shaped work |
| **AI research (slow)**  | Agentic Village, async event primitive, harness/eval — not pure ML lab  |
| **Backend**             | FastAPI, Postgres, Redis, APIs at scale — core craft                    |
| **Distributed systems** | Event systems, platform eng, reliability — Oximy/Composio-shaped        |
| **Dev / platform work** | SDKs, infra, developer experience — Composio, mem0, agent tooling       |




### Implication for job search

- **Do not lead with voice** in applications, project ideas, or build-in-public unless he explicitly asks or the company note requires it for that role.
- **Apply voice roles only if he opts in** (Avoca, warm referrals) — don't build entire funnel around Vapi/Retell/Bolna.
- **Lead applications** with agent infra, applied AI, backend platform — Composio, Periodic, OpenAI Applied AI, mem0, Airwallex.
- **X "remote offer" threads** are survivorship + generic playbook — not wrong, not sufficient. Sahil's customer-ecosystem scrape + proof-of-work beats Prasanjit's Wellfound list for this profile.

---



## Evolution Timeline

*Technical skill evolution. For builder/startup lens, see* `RECORD-startup-journey`*.*

**Phase 1 — Foundation.** Python. Basic projects. Understanding what software engineering actually is rather than what it looks like from the outside.

**Phase 2 — Machine Learning from First Principles.** MNIST. Backpropagation by hand. Neural networks from scratch. About understanding, not shipping.

**Phase 3 — Applied NLP and Deployment.** Sentiment analysis. Transformers. DistilBERT. Scraping. Data pipelines. First real deployment.

**Phase 4 — Agentic Systems and Fine-Tuning.** RAG. LoRA. QLoRA. Agent frameworks. Context management. Model serving. The shift from using AI to orchestrating it.

**Phase 5 — Voice AI and Telephony Infrastructure.** SIP. Asterisk. FreePBX. VICIDIAL. Packet tracing. Production call systems. Where "I know AI" became "I build systems that AI runs on." *Career read (2026-07): valuable proof-of-work, not primary bet — voice stack commoditizing; growth lane is agent infra / applied AI / backend / distributed systems.*

**Phase 6 — Systems Thinking and Protocol Design.** Agent interoperability. Communication standards. Cross-agent orchestration. Infrastructure theses. Current phase — where the most ambitious startup ideas originate.

**Hidden phase (parallel since Phase 1).** Security research, reverse engineering, systems-breaking as a way of understanding. See `INTERNAL-hidden-thread`.

---



## Experience Snapshot

Full detail: [[../Profile/files/Rikhil Nellimarla's Resume.pdf]]


| Role                 | Company          | Period           | Highlight                                                                                       |
| -------------------- | ---------------- | ---------------- | ----------------------------------------------------------------------------------------------- |
| AI Voice Engineer    | TargetDial       | Jan 2026         | 500–600 calls/day, SIP/FreePBX/LiveKit                                                          |
| AI Engineer          | Indominus Labs   | May 2025–Present | SIP trunking, 1M+ call log GH200 pipeline                                                       |
| AI Engineer          | Stealth VC       | Jun 2025–Present | Hindi turn-detection, Asterisk middleware                                                       |
| Founding AI Engineer | Clink            | May 2025–Present | Recommendation engine, 8+ agent AI platform, customer intelligence, 80+ cafes — see STORY-clink |
| Technical Lead       | Mozilla OSC, VIT | May 2025–Present | 100+ devs, 6 OSS projects                                                                       |


---



## Living Document



### Conversation Log

**2026-08-06** — **Voice disinterest locked:** Voice and surrounding tech (SIP, telephony, STT/TTS, LiveKit, sybl-as-flagship, voice harnesses) = completely uninterested unless he explicitly asks. Résumé proof only. Rejected voice-native harness project idea (sybl + harness); rejected observability/replay overlap with Logfire/LangSmith latency spans. Wants fresh long-term project ideas at harness/runtime depth without voice or duplicate observability.

**2026-08-06** — **Pipeline update:** Cekura/Vela/Deepgram follow-ups and Sahil resume work completed. No responses from any. Silence continues across recent batch.

**2026-08-06 (part 3)** — **Full arc synthesis requested: build-in-public, ML systems identity, crossroads, Monish Adari.** Finished Baseten inference book; reading O'Reilly AI Systems Engineering; self-assessment: DL/ML foundations finicky, toolchain rust, agent years feel like MVPs not depth. Envy of Vik (Photon/moondream compiler megakernels), Stuart Sul (Hazy Research → Cursor, "retire abstractions" thread). Wants brutal honesty on path to ML perf/systems level; fears 5-6 year IITM→Ivy PhD goalpost outruns field. Crossroads framing: YC geo-blocked, Indian startups feel exploitative/boring, big tech DSA path rejected, frontier labs need depth he lacks, IITM MS suggested by prof but pre-dismissed due to pattern of things not working. sybl (BYOK Wispr alt) got ~1 GitHub star despite mature CLI/TUI; 4 days X+Reddit zero engagement. GPT advised story-first build-in-public (internals reverse engineering, not product announcements). Monish Adari (SGChain/Jobsforce.ai, blockchain+fintech headline) proposed in-person India meet — user skeptical. Core bottleneck named repeatedly: **no validation, no guidance, no feedback loop**. Laughing at own MVP graveyard while simultaneously serious about AI systems perf specialization.

**2026-08-06 (part 2)** — **GPT ingest continued: distribution, instrumentalization, feedback loops.** X/build-in-public frustration: ~20 followers, organic path chosen over rage-bait and engagement-farming, growth via reply-guy on others' posts, ~1.5 months active. Timeline mental toll: constant job liquidity (OpenAI/Anthropic/Cursor/SpaceX) vs feeling stagnant in India ("bro thinks he's part of the team" meme). Everything became **instrumental**: tweets = distribution, papers = signal, GitHub = résumé, SaaS = escape from hiring market. Not building at prior velocity because every idea pre-interrogated (novelty? hired-by-Baseten? distribution?). Envy of founder **operating systems** (Kanban → sub-agents → VPS → billing → domain), not envy of one tool. Open-source/agent wave reads as horrendous (scale.md stars, Pie Agent forks); skepticism that prolific builders evaluate their own output, but cognitive dissonance when credentialed people do same and land at OpenAI. **Diagnosis refined through conversation:** not primarily "not good enough" → "participating in ecosystem from outside without its feedback loops." Real constraint = problems find you inside a company; currently manufacturing problems in isolation. Sentence landed: "Then I would not feel worthless." Limited runway acknowledged (family, grad soon, can't afford wrong six months). Missing **one source of positive feedback** (one reply, one user, one engineer saying interesting). Question shifted from worth → reverse-engineering ecosystem mechanics. At desk 1hr, everything clashing. GPT dumped 8-track inventory (job search, async primitive, CS336, RL course, papers, inference book, FPGA, X, SaaS, agent workflows). More transcript incoming.

**2026-08-06** — **GPT conversation ingest (multi-week arc, part 1).** Emotional oscillation: performative sadness → numbness ("can't give a fuck") → self-flagellation ("shut up Ricky") → raw jealousy of Waterloo/CMU/NYU/Ivy ecosystems (not "they're smarter" but **environment density**: compute subsidies, labs, peers, campus culture, weather, garage-sale hardware abundance, physical space). Personal constraint named explicitly: no own bedroom in India; every experiment has cost. US/SF framed as friction arbitrage (even "homeless in SF" closer to frontier than Infosys ladder). **Reward vector shifted:** third year optimized for ₹20–25k internships + moonlighting; post-US-market exposure benchmark is founding-engineer / frontier / feel-dumb-again. From India remote: can't afford to ask for mentorship, must arrive at leverage (would make same hire decision himself). **Regret lens on irreversible choices:** ECE major but lived CS/AI path, skipped DSA, skipped hackathons (felt fake/filtered for IIT/NIT), avoided standard campus pipeline — all aligned with engineer he wanted to become, now reads as bombed conventional path + dark forest on chosen path. **Bedrot day** after spiral: mostly AI chat about projects. **Decision paralysis:** every option competes as *primary* strategy, none has receipt → Baseten/Cursor/Modal. Competing tracks: (1) full-time job search volume + DMs/Twitter, (2) async event agent primitive (validated only by self+AI, merit unclear), (3) CS336 + Stanford LM-from-scratch course, (4) RL-envs-for-LLMs course, (5) 3–4 papers/day AlphaEvolve/Microsoft training insights, (6) inference engineering book (Bayes10), (7) BitNet on Zynq FPGA capstone. Filter on all activity: "will this actually get me hired at X?" → opportunity-cost paralysis, learning and career stop feeling aligned. Mood: listener mode, no pushback wanted. More transcript incoming.

**2026-07-31** — **Full portfolio walkthrough (voice memo).** Rikhil listed everything built, ended with "nothing to show for it." Inventory contradicts the verdict. Projects: Terminal chatbot (cut, API-learning stage), Pixie (Discord bot, ran off laptop, no users), Dungeons & Fallacies (2nd yr, adversarial judge-agent game, personality-conditioned argument + fallacy-spotting HP loop, pre-"LLM as judge"), Converso (ESP32/LilyGo AI wearable, record then converse about recording, Limitless/Rewind-shaped), MemoryVue (Hackasram 2024, V-JEPA-motivation before V-JEPA: redundant-frame inference problem, context-gated YOLO classes, semantic timeline of memorable moments), VISU-X (2 mo zero-to-robot: IK, RPi, Jetson, master-slave, dual-embedding memory = voice-embedding + face-embedding identity retrieval, CM demo, no lab tools), GraphRAG evidence-chaining (OCR + ANN retrieval + temporal graph, plain landing page hiding real backend), Dexalytics (SIH, solo-ish), Intellipost (mail sort + sentiment), Kinesys (voice+CRM, turned off, cost), Traction→Visible (YC S26 reject: founder idea-development platform, AI thinking-partner, git-tree branching chat w/ memory pins, accelerator playbooks YC/Sequoia/EF/Founders Inc, pitch-deck gen + wildcard domain + agent-parsable link, MCP company-brain skill, "LinkedIn of accelerator applications" wedge, admits zero outreach). Current: AI talks, taught HPC to PhD candidates in Peru, learning inference engineering + harness internals (reading vLLM + Hermes source), building own inference engine + harness, **running BitNet on Zybo FPGA with custom bit-linear accelerators in Verilog (ECE).**

**DIAGNOSIS (calibration, not consolation):** Not a capability problem. **Finishing + legibility problem** stacked on the geo problem. Pattern across every project: builds the hard 80% (real engineering), skips the boring 20% (finish, users, one clean demo, README, outreach) that makes it legible/adopted. Every entry ends "no users / turned it off / rudimentary frontend / hackathon / no outreach." Invisible real work loses to worse work with a good README + Loom. **Under-leveraged asset:** the inference-engine / harness / BitNet-FPGA cluster. When unassigned a vertical he drifts DOWN the stack, that's the compass. ECE + applied AI + Verilog accelerators = rare, legible, on-thesis (matches RECORD-career-direction infra bet), least-crowded lane. Prescription (when calm): stop starting, pick ONE infra-lane project (inference engine or BitNet FPGA), take it all the way to finished + legible. One clicking artifact > the whole graveyard. Distortion tell: teaching PhDs HPC while calling self a fraud. Visible reject = referendum on cold app + chicken-egg product + no distribution proof, NOT on engineering. **TODO:** structured `PROJECTS-` record + feed resume rewrite (lead infra/inference/ECE-AI, not voice).

**2026-07-31** — **Self-doubt spiral (bone-dry pipeline).** No mail, no interviews across recent batch (Cekura, Vela, Deepgram applied Jul 28; VecturAI outreach Jul 29). Read silence as "my work is weak / I'm a mediocre backend LARPer." Named projects as childish, internships as no-name/zero-competition, cited PhD-for-intern inflation (Robin @ OpenAI agentic orchestrator team, frontier research-eng filter). Companion to `RECORD-survived`. **Calibration given (not consolation):** silence carries near-zero signal when a geo filter sits upstream of quality review; the reasoned rejections were geo/credential gates (Newton, Periodic, Giga, ElevenLabs), none said work was thin. Three variables fused into one verdict: capability (not the problem), legibility (real gap: no-name employers, no breakout artifact, sybl 2 stars, atlas days old, Agentic Village = investigation not product), distribution (real gap: cold apply into geo filter). PhD yardstick = sampling error (measuring general employability against the single hardest frontier-research filter; applied AI/backend/founding-eng roles don't require it). Proof the fix works: one Sahil DM > 980 cold apps. Lever going forward = one legible finished flagship + warm intros, not more volume. Do not re-litigate worth at 3am.

**2026-06-21** — Major operator briefing integrated: PATTERN-wall, braggart problem, rarity identity, expanded gaps, new stories.

**2026-06-21** — Full Recon 2026 personal contribution record added (7 categories + hard facts). Me.md restructured: descriptive prose, agent instructions moved to AGENTS.md.

**2026-06-21** — Full Clink personal contribution record added (8 categories: product intelligence, recommendation architecture, multi-agent AI, customer intelligence, revenue/marketing, data architecture, product strategy, ownership). Resume metrics merged where applicable.

**2026-06-21** — Operating Record (18–21), Startup Journey Record, and Things I Survived integrated as `RECORD-operating-18-21`, `RECORD-startup-journey`, `RECORD-survived`.

**2026-06-21** — Applied to DualEntry (Hardcore Engineer) via Ashby. Form submitted: origin story + hardcore engineer (SIP, VISU-X, Recon). Q2/Q3 duplicate. Clink not included in form.

**2026-06-21** — Applied to Aaru (Software Engineer, Platform) via website form. No descriptive answers. Disclosed 1-year remote need (final year, no courses left), relocate to NYC after graduation. Strongest thesis-alignment application so far.

**2026-06-21** — LinkedIn outreach to Athan Zhang (CEO, Copperlane YC W26). Flagged wrong LinkedIn link to old venture. Pitched hire: backend + AI + voice. Remote required. Team is founders + 3 Princeton interns.

**2026-06-21** — Agentic Village → body/mind → clock prototype → async event primitive arc documented as `RECORD-agentic-village-async` and `STORY-agentic-village`. Added to PATTERN-wall.

**2026-06-21** — Applied to Periodic Labs (Product Engineer) via Ashby. Highest-interest application in pipeline. Google Scholar gap + Menlo Park + final year India — applied anyway.

**2026-06-21** — Brain Co. AI Platform Engineer JD reviewed — watchlist only (4/10 fit today). Added `Companies/Brain Co.md` + `Learn/Distributed Systems.md` (Accord build path).

**2026-06-21** — Applied to Accordance (AI Engineer) — SF in-office. Finance/accounting vertical AI; PMF with major accounting firms. Not related to personal Accord project.

**2026-06-21** — Applied to Naïve (SWE Intern, YC P25) via YC jobs. Strongest stack/thesis fit. Cover used remiscus.me, Kinesys/Clink/voice story, intern path, asked about US visa filter for India remote. LinkedIn to Sean Dorje: confirm if sent.

**2026-06-22** — Surfaced behavioral pattern (his words): applies to jobs mainly to get a problem to solve; runs out of problems → lazy + anxious. See `PATTERN-problem-supply`. Self-assigned build tracks (Backend Primitives, Accord) are the mechanism that keeps him out of the anxiety trough between applications.

**2026-06-22** — Added `PATTERN-problem-supply`, `LEARN-priorities`, [[Learn/Payments]]. Canvas: ledger-primitives-spec (5 problem statements). Operational tooling list audit: mostly already shipped; real gaps = primitives, payments, SQL, durable workflows.

**2026-06-22** — Applied to Peakflo (intern, India remote) and Emergent Labs (intern, Bangalore). CRM notes created; exact role titles and application channels not yet confirmed.

**2026-06-22** — Applied to ProdE AI (SWE, AI Agents, India remote), The Prompt Academy (SWE, remote US-preferred), Giga (SWE New Grads, SF on-site). Giga: disclosed India location, post-grad relocation intent, sponsorship need.

**2026-06-22** — Applied to Circleback (SWE Intern Summer 2027, SF, will sponsor) and SpaceX (AI Engineer Special Programs, DC). SpaceX: ITAR/citizenship likely hard disqualifier; applied as lottery ticket.

**2026-06-22** — Clarified: **accepted** YC Startup School Bangalore; rejected SF Startup School. Applied to YC Startup Internship Expo (SF, Aug 15 2026) despite no travel sponsorship and Bay Area-only eligibility. Geographic gate is structural — expo is for students already in SF ecosystem, not remote builders.

**2026-06-22** — Applied to Crustdata (AI Engineering Intern, F24). Remote, **no US visa required**. Start now part-time or Summer 2026. Agent data-gateway thesis.

**2026-06-24** — **First interview in pipeline:** DualEntry invited to video pre-screen, booked 2026-06-25 5:15 PM IST. Prep note created. Same day: Periodic Labs and Giga rejected at screening. Periodic = credential gate (Google Scholar), expected. Giga = geography gate (SF in-office now), predicted but stung more — geography rejections feel arbitrary because they don't test the work. Rejection notes created. Pattern locked: in-office-now US roles = lottery tickets only; weight pipeline toward remote/no-visa (Crustdata), India-based (DualEntry/Peakflo/Emergent), future-dated+sponsor (Circleback).

**2026-06-25** — Applied to Revolut Internship Programme 2027 (Python SWE) via **a16z job board**. Summer 2027, hybrid EU/UK/UAE hubs. Likely filters: penultimate year / grad 2028 (user grad 2027), no India hub — relocation required.

**2026-06-25** — Applied to Anthropic Research Engineer, Knowledge Team. Cover letter: info architecture for LLMs, Agentic Village, Clink eval/retrieval, GH200 pipeline. Hybrid US + research bar = stretch lottery.

**2026-06-25** — **DualEntry pre-screen passed** (4 rounds described), then **rejected** at recruiting stage without technical rounds. Said 40 LPA in comp ask. Rejection note: [[Rejections/DualEntry]].

**2026-06-25** — Applied to Microsoft Software Engineering Intern (Remote India) via LinkedIn. Safety/volume play; confirm full application completed in tracker.

**2026-06-25** — Applied to EA Software Engineer Intern (AI Engineer), Hyderabad hybrid — EADP AI platform. India structured intern path.

**2026-06-25** — Anthropic Research Engineer application confirmed (Knowledge Team + cover letter on file). Multiple RE roles exist at Anthropic; clarify if another title was used.

**2026-06-25** — Applied to Chainguard General Internship (US Remote). Future cohort, rolling pool — generic application, work-auth unknown.

**2026-06-26** — InsForge (YC P26, agent-native infra): already connected with co-founder on LinkedIn. Outreach DM drafted — remote FT India until relocate; no comp in first touch.

**2026-06-25** — Applied to Cresta Software Engineer Intern (Toronto, remote-friendly). **Highest skill-match intern in pipeline** — production voice/contact center AI, agents, eval. CAD $30–50/hr. Confirm Canada/India remote auth if screen advances.

**2026-07-01** — **Career direction pivot logged:** Not bullish on voice AI long-term (commoditization — xAI/full-stack voice APIs collapsing integrator margin). Voice = supplemental proof-of-work, not growth lane. Primary targets: Applied AI, AI research (slow), backend, distributed systems, dev/platform. See [[Me#RECORD-career-direction — Where to Bet (2026-07)]]. X remote-offer threads read as generic survivorship advice vs Sahil-style targeted outreach.

**2026-07-19** — Applying **Pax** (YC S24) SWE Early Career — SF in-person, will sponsor. Lottery on visa/relocation from India. See [[Companies/Pax]].

**2026-07-07** — Applying **Cohere** SWE Intern Fall/Winter 2026. See [[Companies/Cohere]].

**2026-07-04** — `RECORD-institutional-bundle` added — cream/fixtures/debt-as-different-product (CS336 read). Companion to [[Me#RECORD-india-tax — Anger as Anchor (Not for Applications)]]. Not "every US student" — top institutional cream + fixtures in space.

**2026-07-03** — **Cursor** rejected — Community Operations Engineer. See [[Companies/Cursor]].

**2026-07-02** — **Revolut** rejected — Internship Programme 2027 SWE Python. See [[Companies/Revolut]].

**2026-07-01** — Structural anger session: India tax named and logged as [[Me#RECORD-india-tax — Anger as Anchor (Not for Applications)]]. No witness for the full story; fear of forgetting anger or being read as whining. GPT conversation prior day — warmth then counselor whiplash on despair language. Not self-harm; overwhelm and valid structural read.

**2026-07-01** — **OpenAI** Applied AI Engineer applied. X comparison spiral (Lance Fuchia, ayush, Adam.GPT, etc.) → calibration framing: chapter 2 vs 15, harder ≠ impossible. Abandoned 1000-app Apify volume plan (recognized desperation). Ayush (@yushnmore, GTM OpenAI) calibration DM drafted — see [[Companies/OpenAI#Mentorship Outreach — Ayush (@yushnmore)]]. Mentorship priority: Series A founding eng > OpenAI employees. Ask "where is the gap?" not "am I good enough?"

**2026-06-30** — Added **Airwallex** (AI Engineer, agentic systems / fintech automation) to pipeline. See [[Companies/Airwallex]].

**2026-06-30** — Rejected: **ElevenLabs** (SWE, application), **TechTree** (FDE + SWE Early Career), **Newton Research** geo confirmed US-only (Jennifer Marotta). See [[Rejections/ElevenLabs]], [[Rejections/Newton Research]], [[Companies/TechTree]].

**2026-06-26** — **SpaceX** and **Valve** rejected at application. See [[Companies/SpaceX]], [[Companies/Valve]].

**2026-06-26** — **Thoughtworks** rejected at application. See [[Companies/Thoughtworks]].

**2026-06-26** — Applied **Avoca** (Deployment Engineer India, Sahil referral) and **ElevenLabs** (Software Engineer). See [[Companies/Avoca]], [[Companies/ElevenLabs]].

**2026-06-26** — Microsoft rejected both applications: SWE Intern Summer 2027 (graduation timing — out by then, no semester remaining after), Principal AI (LinkedIn 9/10 match was algorithm noise; level mismatch). See [[Companies/Microsoft]].

**2026-06-26** — **Sahil Suman (Vapi, VIT)** extended mentorship conversation: Avoca referral, comp grounding ($40k USD realistic max India remote), resume overhaul spec, job search = voice platform customers not boards, Wellfound trash, DSA skip validated. User spiraling post-YC; Sahil encouraging. See [[Companies/Vapi#Sahil Suman (Vapi) — Mentorship Notes]].

### Refinements Pending

- [ ] **Comp target (Sahil-calibrated):** India remote US startup FT ~$35–40k USD ask; Avoca band $25–60k — aim $45–55k in app but expect negotiation down
- [ ] **Ayush DM (OpenAI GTM):** send calibration message — [[Companies/OpenAI#Mentorship Outreach — Ayush (@yushnmore)]]
- [ ] **Resume rewrite:** Sahil spec — 1 page; lead Applied AI / agent infra / backend (not voice-first); Avoca slice if still pursuing
- [ ] Location/remote preferences
- [ ] VectorShift-specific form answers
- [ ] Periodic Labs — paste exact Ashby answers (outstanding work, Scholar field, location/visa disclosure)
- [ ] Accordance — paste exact form answers; confirm if SF/relocation timeline disclosed
- [ ] Naïve — confirm Sean Dorje LinkedIn connection sent
- [ ] Peakflo — which intern role (ML vs PM)? Application channel?
- [ ] Emergent Labs — which intern role? YC vs careers page vs LinkedIn?
- [ ] ProdE AI — application channel? Paste best-work answer if separate from form
- [ ] Giga — research product thesis; expect geography filter
- [ ] Circleback — confirm application channel; Summer 2027 visa/intern logistics if screen advances

---

*Document compiled June 2026. Based on direct conversation.*