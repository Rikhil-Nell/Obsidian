# Recon — Event HQ


>[!danger] 
>*Owner:** Rikhil Nellimarla · **Partner clubs:** OSC + Null Chapter · **Advisory escalation:** Dr. Sibi Chakkaravarty
>**Funding baseline:** INR 4,97,000 (IIT-M linked, security-first objective) · **Target scale:** 600 participants, 80 workforce, 3 days
>**Event window:** Apr 25–27 · **Venue anchor:** SAC + campus distributed zones


## Quick Links

### 📄 Docs

- [Recon — Agenda & Run of Show](https://www.notion.so/Recon-Agenda-Run-of-Show-65f96a56b9ba43df8d503b28d299a51e?pvs=21)
- [Recon — Logistics](https://www.notion.so/Recon-Logistics-14efbba5c0c44105b5cf8ad667f6829a?pvs=21)
- [Recon — Sponsors & Partners](https://www.notion.so/Recon-Sponsors-Partners-dc8410c3f03c4dabb91a183783763aaa?pvs=21)
- [Recon — Comms & Marketing](https://www.notion.so/Recon-Comms-Marketing-e4ec8daac8cc4ab694052c08e86d7a92?pvs=21)

### 📋 Ops Playbooks

- [Recon — Risk Register](https://www.notion.so/Recon-Risk-Register-1206301103964681b90df3512e5cc8a8?pvs=21)
- [Recon — Volunteer Handbook](https://www.notion.so/Recon-Volunteer-Handbook-19b2e40001e8445aa18259768fbab9fb?pvs=21)
- [Recon — Zone SOPs](https://www.notion.so/Recon-Zone-SOPs-a49ef27cd1774166977dd8d7fe9a056b?pvs=21)

---

## 0) Mission and Success Definition

**Mission:** Run a national-level, security-focused, DEFCON-style campus event with high technical credibility, high engagement, and safe operations under strict university constraints.

### Primary Outcomes (must hit)

- 2 keynote/talk blocks delivered without delay.
- 1 overnight CTF delivered end-to-end.
- 1 overnight KOTH delivered end-to-end.
- 10+ side events run on published schedule.
- No legal or policy breach (especially networking/security).
- Zero critical safety incidents.

### Secondary Outcomes (good to hit)

- 15+ sponsors/partners actively participating.
- 600 registrations, 450+ active attendance, 250+ outstation attendees.
- Media footprint: live social, campus screens, recap assets.

---

## 1) Non-Negotiables (Hard Guardrails)

>[!danger] 
>- All offensive security activities must be sandboxed and explicitly in-scope.
>- No testing against real university infrastructure or public internet targets unless formal written approval exists.
>- Campus IT restrictions are assumed hostile/unstable; core operations cannot depend on campus Wi-Fi.
>- Every event zone has 1 named owner and 1 backup owner.
>- Any side event not green by T-7 days is dropped automatically.
>- Any event lacking rulebook + safety brief + reset procedure does not open.
>- All prize logic is written before Day 1 and frozen. 


---

## 2) Operating Model (Feature-Max but Resilient)

Use a modular architecture:

- **Layer A (Flagship):** Talks, CTF, KOTH, awards.
- **Layer B (Side villages):** 10+ rotating activities.
- **Layer C (Engagement fabric):** App, points/passport, leaderboards, screens.
- **Layer D (Sponsor fabric):** stalls, sponsor challenges, recruiting touchpoints.

### Failure Isolation Rule

- If one side event fails, flagship must continue unaffected.
- If app fails, manual QR + paper fallback must still run scoring/check-in.

---

## 3) Organizational Chart (50-Person Workforce)

### Core Command (13)

|Role|Owner|Reg No.|Contact|
|---|---|---|---|
|Event Director|Rikhil Nellimarla|23BEC7030|7386175224|
|Chief Technical Officer|Abhiram Venkat Sai Adabala|23BCE8643|7338299721|
|Operations Chief|Mohammed Faariz|||
|Cheppali Chanu|24BCE7489|||
|23BCE20115|7010616263|||
|7989313174||||
|Technical Infrastructure Lead|Izhaan Raza|||
|Surya Theja Dhommalapati|24BCA7544|||
|24BCS7011|990515819|||
|7075052734||||
|CTF/KOTH Competition Director|Vikhyat Shajee Nambair|||
|Swarnim Bandekar|23BCB7137|||
|24BCB7157|7795351504|||
|9945721305||||
|Program and Speakers Lead|Aditya J Shettigar|23MIC7018|8861248515|
|Sponsorship and Partnerships Lead|Ayushi Tomar|24BCA7064|8938967796|
|Design, Media, and Broadcast Lead|Jahnvi Kotangale|23BCE8201|9082812563|
|Volunteer and Logistics Lead|Reet Mishra|||
|A. Dharineesh|23BCE8992|||
|23BCE7490|8639627939|||
|6374853503||||

### Functional Pods

- **Infra + Security Ops** (10)
- **Competition Ops** (8)
- **Side Event Ops** (12)
- **Registration + Crowd + Helpdesk** (6)
- **Stage + AV + Speaker Ops** (6)
- **Design/Screens** (4)
- **Media Team** (10)
- **Floaters and Incident Response** (4)

### Staffing Principle

- No one works more than 10 active duty hours/day.
- Overnight shifts must rotate; next-day rest is mandatory.

---

## 4) Master Timeline (Mar 18 → Apr 25)

### T-38 to T-30

- Freeze event name, theme, identity, logo, domain, registration stack.
- Finalize venue map and zone capacities.
- Publish sponsor deck, open outreach to 250+ companies.

### T-29 to T-21

- Finalize CTF platform and KOTH architecture.
- Publish first speaker line-up and registration opens.
- Publish rulebooks (CTF/KOTH/side events).

### T-20 to T-14

- Vendor lock: internet, routers, power backup, print, merch, badges.
- Finalize lodging and food partner MoUs.
- Side events complete dry run #1.

### T-13 to T-7

- Full technical rehearsal with fake load.
- Incident drills: internet fail, power fail, app fail, speaker no-show.
- Final sponsor commitments and slot allocations freeze at T-10.

### T-6 to T-1

- Print packs, signage install plan, roster by hour.
- Final app content freeze at T-3.
- Campus screen content and emergency templates ready.

---

## 5) Infrastructure Blueprint

### Network Architecture

- Dedicated event network stack (not primary campus Wi-Fi dependent).
- Segmentation:
    - **VLAN 1:** core ops (restricted)
    - **VLAN 2:** CTF participants
    - **VLAN 3:** KOTH arena
    - **VLAN 4:** sponsor/demo internet
    - **VLAN 5:** public attendee guest internet (rate-limited)
- Firewall deny-by-default between VLANs.

### Hosting

- **CTF:** CTFd (managed/self-hosted cloud) with daily snapshot backups.
- **KOTH:** isolated target pool with automated reset every 30–60 min.
- **App backend:** cloud-hosted with CDN + uptime monitor + fallback page.

### Fallbacks

- Backup 4G/5G enterprise routers for critical systems.
- Read-only backup scoreboard webpage.
- Manual score capture sheet for absolute fallback.

---

## 6) Competition Format

### CTF (Overnight #1)

- **Start** Day 1 at 18:00, **end** Day 2 at 06:00.
- Jeopardy style categories: web, pwn, crypto, forensics, misc, OSINT.
- 20–30 challenges, tiered difficulty, dynamic hints.
- Team size: 1–4.
- Anti-cheat: account/device/IP checks, writeup audit for top teams.

### KOTH (Overnight #2)

- **Start** Day 2 at 22:00, **end** Day 3 at 06:00.
- 8–12 target boxes, point accrual per hold interval.
- Red/Blue hybrid optional for finals.
- Mandatory target reset windows to prevent dead boxes.

### Prize Logic

- **CTF winners:** top 3 + category specials.
- **KOTH winners:** top 3 + first blood + longest hold.
- **Side-event passport:** winner pool (top 20 point earners).

---

## 7) Side Events Catalog (Detailed SOP)

### 7.1 RFID Lock Hunt (combined with lock puzzle box)

**Concept** — RFID-enabled lockbox challenge with campus clue trail. Participants discover clue cards/markers that map to RFID key sequence logic.

**Flow**

1. Register at Hunt Desk, receive Hunt ID and clue starter card.
2. Find campus clues (8–12 nodes) leading to valid RFID key pattern.
3. Return to Hunt Zone and attempt unlock (max 3 attempts/session).
4. If unlocked, claim timed reward + leaderboard points.

**Infra** — 3 RFID locks + 8 programmable cards + 2 spare readers. 1 referee console with reset software/log sheet. CCTV/volunteer oversight for tampering.

**Rules** — No force, no lock mansipulation tools. Only provided RFID cards allowed. Physical tampering = disqualification.

**Reset cadence** — Every 2 hours rotate valid key logic.

### 7.2 Hardware Badge + IoT Village (combined zone)

**Concept** — Build a simple interactive badge (LED, buzzer, NFC/RFID optional). IoT mini-labs around badge hacking and secure firmware basics.

**Tracks**

- _Beginner:_ solder + blink + serial debug.
- _Intermediate:_ sensor readouts + button challenge.
- _Security:_ firmware hardening basics, default creds, OTA risks.

**Infra** — 40 solder stations across rotations. Component kits: MCU board, LEDs, resistors, sensor pack, battery. Fire-safe solder setup and ESD mats.

**Rules** — PPE mandatory (goggles in solder area). One trained mentor per 10 seats.

**Output** — Participants leave with badge and challenge completion QR.

### 7.3 Gaming Arena (engagement + sponsor footfall)

**Format** — Short-format tournaments: FIFA/Valorant/CS2 (subject to machine capacity). 20-min slot model with queue management in app.

**Infra** — 12–20 gaming rigs or console setups. Dedicated LAN switch and anti-cheat configs.

**Scoring** — Not part of core security ranking, but gives passport points.

### 7.4 Web Exploit Dojo

**Concept** — Controlled vulnerable apps (OWASP top 10 style).

**Flow** — Guided modules every 45 min. Beginner and advanced lanes.

**Safety** — Targets are local sandbox only. Explicit scope shown on wall and in app.

### 7.5 OSINT Corner

**Concept** — Time-boxed investigation puzzles using public data and synthetic personas.

**Challenges** — Social graph mapping, location inference, metadata extraction. No doxxing, no real-person harassment.

### 7.6 Forensics Sprint

**Concept** — 30-min mini-cases: memory dump, pcap, log triage, stego basics.

**Flow** — Rolling case drops every hour. Individual or pairs.

### 7.7 AI Red-Team Mini-Lab

**Concept** — Prompt injection, jailbreak defenses, model misuse case study.

**Format** — Attack/defend tabletop + hands-on sandbox.

**Deliverable** — Teams submit exploit chain and mitigation notes.

### 7.8 Sponsor Demo Street

**Concept** — Sponsor stalls with fixed demo blocks and micro-challenges.

**Good sponsored event examples:**

- Resume review and fast-track interview desk.
- Secure coding speed challenge.
- API bug hunt in sponsor sandbox.
- Reverse engineering mini-puzzle with swag rewards.
- Cloud security quest with voucher prizes.

**Commercial guardrail** — No pure sales pitches longer than 10 min without interactive component.

### 7.9 Bug Bounty App Quest (secret campaign)

**Design principle** — Keep it a discoverable easter egg, but legally scoped.

**Implementation** — Event app includes hidden challenge path and fake rogue-machine narrative. True target is isolated challenge host in event infrastructure.

**Rules** — In-scope assets listed in app legal page. Out-of-scope attacks = immediate disqualification.

### 7.10 Security Career Clinic

**Format** — CV triage, GitHub profile clinic, roadmap advising, internship prep.

**Value** — High engagement for non-hardcore participants.

---

## 8) Event App Requirements (Minimum Viable + Fallback)

### Must-Have Features

- Registration QR and check-in.
- Live schedule and zone map.
- Live queues by zone.
- Scoreboard and passport points.
- Push notifications.
- Rulebooks and legal scope pages.

### Nice-to-Have

- Team formation board.
- Feedback pulse every 6 hours.

### Fallback

- Static microsite + Telegram/WhatsApp broadcast backup.

---

## 9) Full 3-Day Schedule with Rest Windows

### Day 1 (Apr 25)

- **08:00–12:00** — Final setup + technical check + staff briefing.
- **12:00–13:30** — Lunch + volunteer shift handoff.
- **13:30–14:00** — Gates open and check-in.
- **14:00–15:00** — Inauguration.
- **15:00–16:00** — Keynote/Talk 1.
- **16:15–17:15** — Keynote/Talk 2.
- **17:15–18:00** — Break + CTF briefing.
- **18:00–06:00** — Overnight CTF (with side events running in reduced mode until 01:00).

### Day 2 (Apr 26)

- **06:00–08:00** — CTF close + provisional scoring + participant breakfast.
- **08:00–12:30** — Mandatory participant rest block (no heavy sessions).
- **10:00–12:00** — Soft activities only (career clinic, sponsor booths quiet mode).
- **12:30–14:00** — Lunch.
- **14:00–16:00** — Workshops + village blocks.
- **16:00–18:30** — Sponsor demos and challenge street.
- **18:30–20:00** — Dinner + KOTH briefing.
- **20:00–22:00** — Warmup and qualification windows.
- **22:00–06:00** — Overnight KOTH.

### Day 3 (Apr 27)

- **06:00–08:30** — KOTH close + adjudication + breakfast.
- **08:30–12:30** — Mandatory rest block (low intensity only).
- **12:30–14:00** — Lunch.
- **14:00–16:00** — Finals highlights + lightning talks + winner verification.
- **16:00–17:00** — Awards and closing ceremony.
- **17:00–20:00** — Teardown and asset recovery.

---

## 10) Crowd Flow and Capacity Controls

### Capacity Model

- Main hall: fixed seated capacity with hard cap.
- Side zones: each has occupancy cap and queue threshold.
- App displays red/amber/green crowd indicators.

### Controls

- Wristband or QR color coding by participant type.
- Staggered starts for side events.
- Overflow waiting lounges with live stream.

### Queue Policy

- 15 min grace per slot.
- No-shows auto-release seats.

---

## 11) Outstation Logistics (Lodging, Travel, Food)

### Audience Segments

- In-campus students.
- Outstation participants (city/state).
- Speakers/sponsors.

### Lodging Plan

- **Tier A:** speaker/sponsor hotel block near campus.
- **Tier B:** participant hostel/partner budget hotels.
- **Emergency overbooking buffer:** 10% rooms.

### Travel Desk

- Pre-arrival guide with nearest station/airport routes.
- Shuttle windows at fixed intervals.
- Night safety transport for post-midnight movement.

### Food Plan

- 3 meal windows + midnight snack for overnight tracks.
- Veg/non-veg clearly labeled; allergy markers mandatory.
- Water points every major zone.

---

## 12) Merch and Identity System

### Merch Lineup

- Lanyard + RFID enabled badge (where feasible).
- Sticker packs, tees, limited challenge coins, sponsor swag.

### Distribution

- Core kit at check-in.
- Unlockable merch via points milestones.

### Anti-Loss

- Controlled inventory sheet with hourly reconciliation.

---

## 13) Sponsor Management SOP

### Tiering

- Title sponsor, gold sponsor, community sponsor.

### Deliverables by Tier

- Branding locations, stage mentions, app banner placement.
- Stall size and power/network allotment.
- Challenge slot rights.

### Process

- One sponsor owner per account.
- Written statement of work for every sponsor.
- No custom last-minute commitments after T-10.

---

## 14) Security, Legal, and Compliance

### Documents Required

- Participant code of conduct.
- Competition rules and scope.
- Vulnerability disclosure policy for event targets.
- Media consent and data policy.

### Operational Controls

- Isolated challenge infra only.
- Full logs for challenge environments.
- Incident report form with response SLA.

---

## 15) Incident Response Runbook

### Severity Levels

- **SEV-1:** Life safety or complete event outage.
- **SEV-2:** Core track degraded (CTF/KOTH/app/network).
- **SEV-3:** Single zone impact.

### Escalation Chain

- Zone owner → Ops Chief → Event Director → Dr. Sibi Chakkaravarty (institutional escalation).

### Common Incidents

- **Internet outage:** switch to backup router, freeze scoring window, public announcement.
- **Power issues:** UPS route for core infra, postpone only affected zones.
- **Crowd surge:** temporary zone closure and queue reroute.
- **Speaker no-show:** swap with backup lightning talk block.

---

## 16) Finance Control and Procurement

### Budget Envelope (Baseline)

|Category|Amount (₹)|
|---|---|
|Infra/network/cloud|1,10,000|
|Speakers and hospitality|1,20,000|
|Prizes|80,000|
|Branding/media/print|50,000|
|Venue/logistics|60,000|
|Side event materials|30,000|
|**Contingency**|**47,000**|
|**Total**|**4,97,000**|

### Controls

- Every spend tagged to cost center.
- Daily burn tracker from T-20 onward.
- Two-signature approval above threshold amount.

---

## 17) Documentation Set to Create Immediately

### Mandatory Docs

- [ ] Master schedule (hour-by-hour)
- [ ] Zone SOP sheets (1 pager each)
- [ ] Volunteer handbook with escalation matrix
- [ ] Sponsor SOW templates
- [ ] Risk register and mitigation tracker
- [ ] Asset inventory and return checklist

---

## 18) 72-Hour Pre-Event Checklist (Must Pass)

- [ ] CTF and KOTH full simulation complete
- [ ] All side events have reset scripts and owners
- [ ] App + fallback channels tested
- [ ] Power and internet backup validated
- [ ] Food, lodging, transport confirmations locked
- [ ] Host and emcee scripts printed
- [ ] Emergency response contacts distributed

---

## 19) Day-of Command Protocol

- Command room opens 2 hours before gates.
- Status update every 90 minutes from each zone.
- One single source of truth dashboard for operations.
- Any schedule change must be approved by Ops Chief and announced across app + screens.

---

## 20) Post-Event Closure

- Winner verification and payout timeline published within 24 hours.
- Sponsor report pack in 7 days (footfall, engagement, brand impressions).
- Incident and lessons-learned review in 10 days.
- Publish next-edition intent note while momentum is high.

---

## Appendix A — Zone Owner Template

For each zone, define:

- Owner + backup + contact.
- Capacity + slot length + reset time.
- Equipment list + spare list.
- Safety constraints.
- Failure fallback.

## Appendix B — Minimum Volunteer Brief

- What this zone does.
- How participants enter and queue.
- What to do when equipment fails.
- What behavior requires escalation.
- Whom to call at each severity level.

## Appendix C — Suggested KPIs

- Registration to attendance conversion.
- Average queue time per zone.
- CTF/KOTH uptime.
- App engagement and notification open rates.
- Sponsor satisfaction score.
- Incident count by severity.

---

[Recon — Agenda & Run of Show](https://www.notion.so/Recon-Agenda-Run-of-Show-65f96a56b9ba43df8d503b28d299a51e?pvs=21)

[Recon — Logistics](https://www.notion.so/Recon-Logistics-14efbba5c0c44105b5cf8ad667f6829a?pvs=21)

[Recon — Sponsors & Partners](https://www.notion.so/Recon-Sponsors-Partners-dc8410c3f03c4dabb91a183783763aaa?pvs=21)

[Recon — Comms & Marketing](https://www.notion.so/Recon-Comms-Marketing-e4ec8daac8cc4ab694052c08e86d7a92?pvs=21)

[Recon — Risk Register](https://www.notion.so/Recon-Risk-Register-1206301103964681b90df3512e5cc8a8?pvs=21)

[Recon — Volunteer Handbook](https://www.notion.so/Recon-Volunteer-Handbook-19b2e40001e8445aa18259768fbab9fb?pvs=21)

[Recon — Zone SOPs](https://www.notion.so/Recon-Zone-SOPs-a49ef27cd1774166977dd8d7fe9a056b?pvs=21)

[Recon — Tasks](https://www.notion.so/0658302ea7de455f9919847f6896dde9?pvs=21)

[Recon — Schedule](https://www.notion.so/4c5a3565c48b4c78b2beea73c2743eca?pvs=21)