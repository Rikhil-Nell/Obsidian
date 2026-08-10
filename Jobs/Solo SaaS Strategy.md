---
type: strategy
topic: Solo SaaS to $10k/mo net
created: 2026-06-26
status: Active
decision: Pending — choose between EAA (#14) and Med-spa no-show killer (#5)
---

# Solo SaaS → $10k/mo Net — Strategy Note

> The goal: ~$10k/mo **in pocket after Indian tax**. Not revenue. Not a fundraise. A bootstrapped, solo, cash-flowing product.

## 1. The frame (the math that governs everything)

- $10k/mo net → ~$120k/yr in pocket.
- At ~35% effective Indian tax (slab + surcharge + cess past ₹1cr): need **~$15–18k/mo pre-tax profit**.
- AI SaaS margins run 65–85% (worse than classic SaaS — inference isn't free). So:
  - **Target ≈ $23k MRR ≈ $240k ARR.**
- Software is **opex, not capex.** One-time setup < $1,500 (entity, domain, legal templates).

### The price point IS the scale

| Price/mo | Customers for $23k MRR |
|---|---|
| $99 | ~232 |
| $299 | ~77 |
| $499 | ~46 |
| $999 | ~23 |
| $1,500 | ~15 |

Sweet spot for a **no-audience solo founder doing cold outbound: $300–1,500/mo (15–77 customers).** Anything needing 200+ SMB logos is a slog.

### Steady-state P&L @ $23k MRR (after credits expire, outbound-led)

| Line | Text SaaS | Voice SaaS |
|---|---|---|
| Revenue | $23,000 | $23,000 |
| COGS (LLM/infra/STT/Stripe/ops) | −$3,200 | −$4,700 |
| Gross profit | ~$19.8k (86%) | ~$18.3k (80%) |
| OpEx (outreach tools, light ads, misc) | −$1,500 | −$1,500 |
| Pre-tax profit | ~$18.3k | ~$16.8k |
| Tax (~35%) | −$6.4k | −$5.9k |
| **Net in pocket** | **~$11.9k** | **~$10.9k** |

**Holds only if growth is outbound-led** (marketing = tools, not ad spend). Paid-ads acquisition at $23k MRR doesn't net $10k — you'd need ~$30k MRR first.

## 2. The credit runway (YC Startup School)

| Credit | Covers | Note |
|---|---|---|
| $25k AWS + $25k Azure | Infra | ~12–24 mo free |
| $2,500 GPT + $500 Anthropic | LLM reasoning | months of MVP runway |
| **$15,000 Deepgram** | Speech-to-text | **The real edge — subsidizes the worst COGS line in a voice stack for ~1 yr** |

**Use the credits as a 12-month clock, not a cushion.** Year 1 COGS ≈ $0 out of pocket → hit $10k net at lower MRR, OR plow the saved $3–4.5k/mo into growth before the meter turns on.

## 3. The 20-wedge validation (researched 2026-06-26)

Thesis going in: *"pick wedges too narrow for funded/YC startups to bother."* **That thesis is broken.** Every category verticalized in 2026 — narrowness no longer protects. The binding constraint is **distribution (no audience)**, so winners are all: **org buyer + cold-outbound-native + ROI or live-compliance gate.**

### Ranked verdict

| # | Wedge | Verdict | Why |
|---|---|---|---|
| 14 | **EAA/WCAG accessibility monitoring for EU e-commerce (10+ emp)** | **GO** | Only wedge with a *live, enforced* law + fines landing now (€5k–€1M), recurring (low churn), listable buyers, free-site-scan outbound hook. Incumbents are legally-discredited overlays or US-ADA-focused. |
| 5 | **Med-spa no-show killer (outbound voice confirm/reschedule)** | **GO** | Pain = a dollar figure in the cold email ($8k–33k/mo lost per spa). Uses the Deepgram edge. Low build/liability. Trivially listable buyers. ~23–38 customers. |
| 20 | **Vertical RAG/hallucination "verification gate" (non-healthcare regulated vertical: legal / prior-auth / financial)** | **MAYBE (strong)** | Matches AI-infra identity, sells to a compliance budget, solo-buildable, ROI/audit-gated. Healthcare already crowding — pick a vertical the scribe crowd ignored. |
| 9 | Niche claims-scrubbing / denial mgmt (PT or BH, EHR-agnostic) | MAYBE | Best customer-count economics (~30–115) + realest documented pain + high WTP. BUT high HIPAA/liability/rules-engine build, and **voice credits are useless here.** |
| 3 | AI voice maintenance-triage for residential property mgrs | MAYBE | Good ACV math, but Frontdesk & CallSphere already own AppFolio/Buildium. |
| 6 | Voice→inspection reports (commercial PCA / single trade only) | MAYBE | The only voice wedge with zero PHI, but home-inspection is swarmed by 6+ 2026 voice-first startups — must go sub-niche. |
| 17 | Voice-agent eval/regression harness | MAYBE→SKIP | Best skill fit + realest pain, but **Coval raised $28M Series A (Jun 2026)**; only survivable as one regulated vertical's script-compliance gate. |
| 10 | Prior-auth / appeal drafting (PT-only) | SKIP/MAYBE | BH owned by Cendri + Undenied; PT open-ish but no voice leverage, high build. |
| 1 | Voice intake for PI/immigration law | SKIP | ClaireAI/KaiCalls shipped it with native Litify sync. |
| 2 | Vet voice receptionist | SKIP | $49/mo price war; ~153 customers needed. |
| 4 | DACH-German medical phone agent | SKIP | GDPR/BSI C5 protect incumbents; US Deepgram credit edge is **illegal to use there**. |
| 7 | Veterinary AI scribe | SKIP | "Vet underserved" is a 2023 thesis; 2026 = VetRec (YC) + ScribbleVet (acquired) + ~12 others. |
| 8 | Allied-health scribe (horizontal) | SKIP | Each vertical has a purpose-built, often EMR-embedded incumbent (HelloNote 5,000+ practices). |
| 11 | ISO 20022 / pain.001 for SMB exporters | SKIP | The Nov-2026 deadline binds **banks, not your buyer**; canonical tool (`pain001`) is free/OSS. |
| 12 | EU AI Act doc generator | SKIP | Deadline pushed to **Dec 2027** + obligations lightened; free competitor (Legalithm) through 2028. |
| 13 | Shadow-AI audit + policy gen | SKIP | No deadline/fine; discovery half is incumbent-owned, policy half is free everywhere. |
| 15 | RFP/security-questionnaire autoresponder (MSPs) | SKIP | ~15 competitors incl. Bidara in the exact niche; $5–15 DIY Claude alternative crushes price. |
| 16 | Per-customer LLM cost attribution | SKIP | 5+ solo founders already shipped it; free Helicone header; broke/high-churn buyer. |
| 18 | On-prem LLM observability (regulated) | SKIP | Observability is free/bundled (Langfuse); real wedge = enterprise compliance sales → death for solo. |
| 19 | Prompt-injection / LLM security scanning | SKIP | Consolidating into Check Point/Palo Alto/Cisco; solo founders can't sell security. |

## 4. Cross-cutting conclusions

1. **Narrowness stopped being a moat.** Every vertical AI niche already has 2026 purpose-built competitors. The new moat is **distribution + integration depth + being the founder who answers the email** — not "nobody else thought of this."
2. **The binding constraint is distribution, not build.** Every survivor is an *org-buyer, cold-outbound, ROI/compliance-gated* sale — the exact motion a no-audience founder can run. Classic bottom-up dev tooling (needs OSS/audience) is the wrong shape for Rikhil *right now*.
3. **Strategic tension: the Deepgram edge and the best-money wedges barely overlap.** Only #5 (and weakly #6) actually use voice. The strongest standalone demand signal (#14 EAA) uses none of the credits.
4. **"Deadline" ≠ urgency.** The famous deadline plays (ISO 20022, EU AI Act) failed on inspection — the deadline either binds the wrong party or got postponed. **Only a *live, currently-enforced, fining* regime (EAA) is real WTP.**
5. **Identity check:** the dev-tooling pull is real but it's the hardest lane for a no-audience solo founder. The only AI-infra wedge that survives (#20) survives precisely *because it stopped being dev tooling and became a compliance product.*

## 5. The two finalists — the actual decision

| | **#14 — EAA accessibility monitoring** | **#5 — Med-spa no-show killer** |
|---|---|---|
| Demand signal | Strongest (enforced law, fines now) | Strong (hard dollar ROI) |
| Uses Deepgram credits | No | **Yes** |
| Build difficulty | Med (axe-core scan + monitor + docs) | Low (outbound voice + scheduling) |
| Distribution | Free live-site scan as cold hook | Dollar-figure cold email |
| Churn | Low (ongoing legal exposure) | Medium (SMB) |
| Competition | Discredited overlays / US-ADA | Named voice competitors exist |
| Customers @ $23k MRR | ~38–150 ($150–600/mo) | ~23–38 ($600–1k/mo) |
| Risk | Doesn't leverage credits | Crowded; must out-execute on niche |

**Recommendation:** these two are the finalists. #14 has the best raw demand signal and lowest churn but ignores the credits; #5 presses the unfair Deepgram advantage with the cleanest cold-email pitch but faces named competitors. #20 is the identity-aligned dark horse if Rikhil wants to stay in AI-infra.

## 6. Next step (go/no-go test)

Pick ONE finalist, then the real validation per the thesis: **list the first 50–100 named buyers by hand.** If you can't enumerate them, it's not a real wedge.

- **#14:** pull 10+ employee EU e-commerce sellers via BuiltWith / Shopify-EU / country directories; run a free WCAG/EN-301-549 scan on each as the outbound hook.
- **#5:** pull med-spas in 2–3 US metros (Google Maps / industry directories); estimate each one's monthly no-show loss for the cold email.
- **#20:** pick the non-healthcare regulated vertical (legal citation verification looks most open); list legal-AI / prior-auth / financial-research startups + their compliance officers.

## 7. The vehicle reframe (2026-06-26) — services before SaaS

After the 20-wedge research, the honest read of Rikhil's constraints (no domain knowledge, no audience/distribution, incumbents already hold the integrations, needs cash soon) is that **the SaaS *product* vehicle is the worst fit, not the wrong person.** Every objection that kills a SaaS play is neutralised by a **services / productized-service** vehicle:

| Constraint | SaaS vehicle | Services vehicle |
|---|---|---|
| No domain knowledge | Fatal — build the wrong thing | Learn it on the client's dime |
| No distribution/audience | Fatal | Cold outbound + closing skill (Recon muscle) |
| Incumbents have integrations | Must out-build | **Use their tools (Retell/Vapi/CRMs) to deliver** |
| Needs money soon | 6–12 mo to MRR | Weeks to first invoice |

**Key insight:** the existence of Retell/Vapi + CRMs with open APIs is an *advantage* for a service provider — you ship in days and compete against the business owner's inertia, not against the platform.

### Services math to $10k/mo net
- ~$15k/mo pre-tax → **5 clients × $3k/mo retainer**, or setup ($2–3k) + $800–1,200/mo management.
- **5 clients, not 77.** ~5–10 hrs/client/month once live.
- Directly answers "nobody pays for my AI skills" — paid this month, no audience required.
- The voice lane (#5/#6) is rescued here: same work, delivered as a **productized service**, not sold as SaaS.

**Path:** services → do the same setup 5–10× → discover the repeatable core + real domain pain → *then* productize the thing you've already sold by hand. Earn the roadmap, don't guess it.

**Decision pending:** does Rikhil run the services/productized-service vehicle (recommended on-ramp), or insist on a pure SaaS build (#14 EAA the only clean GO there)?

---

*Sources: full cited research in the four validation subagent runs (2026-06-26). Key facts — EAA enforceable since 28 Jun 2025 with fines €5k–€1M; EU AI Act high-risk obligations delayed to Dec 2027; Coval $28M Series A Jun 2026; Lakera acquired by Check Point ~$300M Sep 2025; VetRec YC + ScribbleVet acquired Jan 2026.*
