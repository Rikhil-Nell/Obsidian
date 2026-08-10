---
company: Periodic Labs
role: Product Engineer
status: Rejected
stage: Screening
priority: High
interestingness: 10
compensation: $250K–$350K base + equity
domain: AI Scientist / Autonomous Labs / Physical Sciences
location: Menlo Park, CA (in-person) — visa sponsorship yes
source: Ashby
applied: 2026-06-21
last_contact: 2026-06-24
follow_up:
next_step: Rejected at screening — post-mortem in [[Rejections/Periodic Labs]]
founder: Liam Fedus, Ekin Dogus Cubuk (Dogus Cubuk) + team (Bahdanau, Passos, etc.)
tech:
  - AI Agents
  - Autonomous Labs
  - RL / Physical Environment
  - ML Systems
  - Robotics
  - Materials Science
  - Megatron
  - vLLM
rejection: "[[Rejections/Periodic Labs]]"
---

# Periodic Labs

## Why This Is Interesting

**Highest problem-quality company in the pipeline.** Full stop.

Periodic is not building another AI wrapper. They are building **AI scientists** and the **autonomous laboratories** for them to operate — closing the loop between conjecture, experiment, and learning. Nature is the RL environment.

Core insight from their thesis: frontier models have exhausted internet text (~10T tokens). Scientific progress requires **acting in the physical world** — experiments produce GBs of data that exist nowhere else, including valuable negative results never published.

**Why this hits every interest:**
- Agent runtimes that **do things**, not just talk
- Infrastructure for autonomous systems (labs = production environment for agents)
- Event/experiment loops — hypothesis → action → observation → learn
- Verifiable environments (physics) — same appeal as code/math for AI progress
- "Intelligence is necessary but not sufficient" — ideas must match reality (data correctness instinct)
- Superconductors, semiconductors, Moore's Law, fusion — real physical world stakes

**Founding team is absurd:**
- Co-created **ChatGPT**, DeepMind **GNoME**, OpenAI **Operator/Agent**
- **Bahdanau** (neural attention mechanism)
- **MatterGen**, scaled autonomous physics labs
- Materials discoveries of the last decade
- Backed: a16z, Felicis, DST, NVentures, Accel — Bezos, Eric Schmidt, Jeff Dean, Elad Gil
- ~$200M raise at ~$1B valuation (Aug 2025 reporting)

This is the kind of company where the problem is worth dedicating years to — even if the bar to get in is brutal.

---

## Interesting Problems

### AI Scientist Loop

Conjecture → autonomous experiment → learn from results. Not re-reading textbooks forever — **try the idea and see if it holds.** Agent orchestration where actions have physical consequences and verifiable outcomes.

### Autonomous Laboratories as Data Moat

Robots synthesize materials, characterize properties, generate experimental data at GB scale per run. Data that does not exist on the internet. Negative results included. The lab is both **training environment** and **competitive moat**.

### Physical Sciences as Verifiable RL

Physics experiments: high signal-to-noise, relatively fast, simulatable. AI progressed fastest where results are verifiable (math, code) — **nature is the next RL environment.**

### Applied Targets

- Superconductors at higher temperatures → transportation, power grids
- Semiconductor heat dissipation (active customer work)
- Custom agents for engineers to interpret experimental data and iterate faster
- Space, defense, semiconductors — trillions in R&D spend

### Mid-Training on Physical Reality

Strategy: optimize against physical reality, not internet text. Mid-training and RL encoding deep domain knowledge. Weekly sessions where physicists teach LLMs quantum mechanics reasoning and ML researchers learn physics.

---

## Notes From Research

### Company Thesis

"Accelerate science." Create an AI scientist. Build autonomous labs for them to operate. Starting in physical sciences because technological progress is limited by our ability to **design the physical world.**

Website framing: science works by conjecturing, experimenting, learning. Autonomous labs central — they give AI scientists tools to **act.**

### Founders / Key People

**Liam Fedus (William Fedus)** — Co-founder, ex-OpenAI VP  
**Ekin Dogus Cubuk (Dogus Cubuk)** — Co-founder, DeepMind materials (GNoME)

Team includes: **Dzmitry Bahdanau**, Alexandre Passos, Costa Huang (Tülu 3, OLMo2, CleanRL), Vincent Moens, Rishabh Agarwal, Eric Toberer, and many physicists/chemists/simulation experts.

### Funding & Backers

a16z-led ~$200M at ~$1B valuation. Felicis, DST, NVentures (NVIDIA), Accel. Individuals: Jeff Bezos, Eric Schmidt, Jeff Dean, Elad Gil.

### Technical Stack (from job postings / inference)

- Training: Megatron-LM, DeepSpeed, FSDP, TorchTitan
- Inference: vLLM, SGLang
- Simulation: COMSOL, ANSYS
- Lab: robotics control, CAD, CUDA
- Agents: custom agents for customer engineers (Forward Deployed LLM Systems role)

### Open Roles (likely fit vs stretch)

**Plausible fits for Rikhil:**
- **Product Engineer** ← best fit in their job board (see below)
- Forward Deployed Engineer — LLM Systems
- Software Engineer
- Research Engineer — Data
- Automation Engineer
- Don't See Your Role? Apply Here

**Stretch / wrong profile:**
- Research Scientist, Condensed Matter Theory
- Research Scientist, Materials Synthesis
- Multiphysics Simulation Scientist
- Nanofabrication Intern

---

## Target Role — Product Engineer

**This is the role.** Not research scientist. Not ML systems at Megatron scale.

### What they want

Build the **agentic software layer** between frontier models, scientific data, lab systems, and scientists. Environment where materials scientists work alongside AI agents to plan experiments, run analyses, coordinate workflows, turn results into decisions.

- Prototype fast, ship continuously
- Agentic products: copilots, experiment planning, workflow automation — **beyond chat**
- Design how scientists direct, supervise, collaborate with AI agents
- Work with model researchers on eval signals, telemetry, feedback loops
- Own end to end: data model, backend, APIs, UX, deployment, observability
- Spend time in the lab with scientists — turn bottlenecks into products
- **One of first product hires** — define product engineering culture

### Mechanics

| Field | Value |
|---|---|
| Education | Bachelor's or **similar experience** |
| Location | **Menlo Park, CA** (SF soon) — in-person |
| Comp | $250K–$350K base + equity |
| Visa | Yes — sponsorship available |
| Application | **Google Scholar link requested** |

### Fit read for this specific role

**This is Clink + agent platform work at civilization scale.** Orchestrating tools and agents, integrating systems, feedback loops, scientist-facing workflows — not training GNoME.

| | |
|---|---|
| Skill match (this role) | **8/10** — was 6/10 for generic Periodic |
| Google Scholar | **Gap** — no publications; may auto-filter |
| Location | **Blocker** — Menlo Park in-person; visa possible but final year India |
| "First product hire" | **Opportunity** — they want someone to define the function, not fill a slot |

**Google Scholar:** Fair ask for this building. You don't have it. Options: leave blank / N/A with GitHub + production portfolio, or don't apply. "Similar experience" in education line is the loophole — shipped agent products count. They may still reject. Applying is a lottery ticket, not an insult.

**The grin is correct.** Being in that room would be insane. Imposter syndrome would be immense. That doesn't mean you couldn't contribute — it means the room would also contain people whose imposter syndrome points the other direction (world-class physicists who can't ship a FastAPI backend in a week).

### Relevance To Me

**Strong overlap:**
- Agent systems, orchestration, tool-use, acting in environments
- Infrastructure thinking — labs as platform for agents
- Clink multi-agent, voice agents with real-world consequences
- Thesis alignment with agent OS / coordination / verifiable loops
- "Hardcore engineer" — dirty problems, systems that must work

**Severe gaps:**
- Team bar: ChatGPT/GNoME/Bahdanau tier — ML research depth expected
- Physics/chemistry/materials domain — not your background
- ML systems at scale: Megatron, distributed training, mid-training — likely beyond current proof
- Probably US-based, lab-adjacent — remote from India is long shot
- Student / no PhD — team includes Stanford/Northwestern professors on advisory board

**Honest read:** Most interesting company in the pipeline. **Product Engineer** is the right role — not research tracks. Google Scholar + Menlo Park + student timeline are the real filters, not "can you orchestrate agents."

### Similar Companies

- Oximy (data correctness — weaker scale, more accessible)
- Aaru (simulation/prediction — closer agent theme, more software)
- Nothing else in pipeline at this problem tier

---

## Scoring

- Interestingness: **10** — dream problem space for long-term goals
- Startup Fit: 7 — elite small team, but may not want ECE undergrad remotely
- Skill Match: **8** (Product Engineer role) — agent orchestration, platform, feedback loops; not physics/ML research
- Compensation: **10** — $250–350K + equity, visa sponsorship
- Long-Term Value: **10** — would redefine trajectory

---

## Outreach Log

| Date | Action |
| --- | --- |
| | Discovered company — reacted to launch/about page content |

---

| Date | Action |
| --- | --- |
| 2026-06-21 | Discovered company — launch/about page |
| 2026-06-21 | Identified **Product Engineer** as target role — best fit in job board |
| 2026-06-21 | Reaction logged: imposter syndrome + excitement; Google Scholar gap noted; role asks Menlo Park in-person |
| 2026-06-21 | **Applied** — Product Engineer via Ashby. Sent it anyway: Google Scholar gap + Menlo Park + student timeline acknowledged implicitly by applying. Lottery ticket. |

---

## Application Record — 2026-06-21

**Role:** Product Engineer  
**Portal:** https://jobs.ashbyhq.com/periodic-labs  
**Status:** Submitted

**Known filters against profile:**
- No Google Scholar / publications
- Menlo Park in-person; final year India (~2027 graduation)
- Visa sponsorship available on posting — timeline still awkward

**Likely positioning (confirm / paste exact answers when available):**
- Shipped work: Clink multi-agent platform and/or TargetDial production voice
- Systems thinking: Agentic Village → async event primitive arc (`RECORD-agentic-village-async`)
- Bridge: agentic layer between users and AI agents — same shape as scientists + lab + frontier models

**Expected outcome:** High reject probability on Scholar or location. Still worth the data point.

---

## Application Strategy — Product Engineer

**If applying:**

1. **Google Scholar:** Don't have one. Use **GitHub** (github.com/Rikhil-Nell) as primary proof. In cover/free text: "No publications — similar experience via production agent systems at scale." Don't fake a Scholar profile.
2. **Lead with:** `STORY-clink` — agentic layer between users and AI, orchestration, feedback loops, first product/platform hire energy
3. **Bridge:** "I've built the environment where non-technical users (cafe owners) work alongside AI agents — same problem, different domain: scientists + lab data + frontier models"
4. **Second beat:** Production voice agents — verifiable outcomes, not chat quality
5. **Location:** Must address Menlo Park + visa + graduation timeline (~2027). Same honesty as Aaru — cannot pretend local
6. **Do not:** Claim materials science cred or cite papers you don't have

**Portal:** https://jobs.ashbyhq.com/periodic-labs

**Expected outcome:** Likely auto-reject on Scholar or location. Worth one shot if the grin doesn't fade — rejection note would still be valuable data.

---

## Related

- Interviews:
- Rejection: [[Rejections/Periodic Labs]]
- Learn:
