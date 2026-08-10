# Zone SOPs - VIT-AP CyberSec Fest 2026

Use this as the operating one-pager for each zone. Fill names and phone numbers before T-7.

## Common SOP for all zones

- Owner: `<name>` | Backup: `<name>` | Escalation: Ops Chief -> Event Director
- Open checklist: power, internet, signage, queue markers, QR scan test, safety check
- Slot cadence: default 45 minutes session + 10 minutes reset unless noted
- Capacity control: hard cap enforced, no overfill; overflow goes to wait queue
- Incident logging: every disruption > 5 min logged with timestamp and action taken
- Close checklist: equipment count, damage log, pending rewards, handover note

## 1) RFID Lock Hunt + Puzzle Box

- Objective: participants solve clue trail and unlock RFID lockbox
- Capacity: 30 active hunters/hour, 6 at lock station at once
- Assets: 3 RFID locks, 8 active keycards, 2 spare readers, clue kit v1-v4
- Staff: 1 owner, 2 referees, 2 runners, 1 anti-tamper spotter
- Rules: no force/opening attempts with tools; only event-issued cards
- Reset: key logic rotates every 2 hours, clue cards rotated every 4 hours
- Win condition: valid unlock under timed attempt window (max 3 attempts/session)
- Failure fallback: if reader fails, switch to spare reader; if lock fails, move to backup lockbox

## 2) Hardware Badge + IoT Village

- Objective: hands-on build + secure firmware learning
- Capacity: 40 benches per batch
- Assets: MCU kits, solder stations, ESD mats, goggles, first-aid, spare components
- Staff: 1 owner, 4 mentors, 2 safety marshals, 1 inventory controller
- Tracks: beginner blink; sensor integration; basic firmware hardening
- Safety: PPE mandatory in solder area; no food/liquids near benches
- Reset: tool cleanup and kit replenishment every batch
- Win condition: badge powers on, challenge test passes, QR completion issued
- Failure fallback: if solder section saturates, route overflow to no-solder firmware station

## 3) Gaming Arena

- Objective: keep engagement high and distribute crowd load
- Capacity: depends on rigs/consoles; publish current capacity in app
- Assets: rigs/consoles, LAN switch, controller/peripheral spares, anti-cheat setup
- Staff: 1 owner, 2 bracket admins, 1 technical support, 1 queue controller
- Format: 20-minute slots, fixed mini-brackets, no custom match length
- Reset: account logout + peripheral wipe + station health check after each slot
- Win condition: bracket points and passport points
- Failure fallback: if a station fails, auto-remap match and credit wait-time bonus

## 4) Web Exploit Dojo

- Objective: teach web security through sandboxed vulnerable apps
- Capacity: 50/session
- Assets: isolated lab targets, student guide, hint cards, scoreboard connector
- Staff: 1 owner, 2 instructors, 2 floor helpers
- Rules: only in-scope targets; no attacks outside lab VLAN
- Reset: containers reset every session; credentials rotated per block
- Win condition: challenge flags submitted + mitigation explanation
- Failure fallback: if infra degrades, switch to guided walkthrough mode

## 5) OSINT Corner

- Objective: timed investigations on synthetic/public datasets
- Capacity: 35/session
- Assets: challenge packets, metadata tools, timed scoreboard forms
- Staff: 1 owner, 1 challenge master, 1 proctor
- Rules: no doxxing, no real-person harassment, no private data scraping
- Reset: new scenario every hour
- Win condition: highest validated evidence score
- Failure fallback: move to offline packet mode if network fails

## 6) Forensics Sprint

- Objective: rapid DFIR mini-cases (pcap, memory, logs, stego)
- Capacity: 40/session
- Assets: case images, analysis VMs/tool USBs, answer validator
- Staff: 1 owner, 2 case moderators, 1 scoring admin
- Reset: case rotates hourly, top teams reviewed for integrity
- Win condition: most correct artifacts + shortest resolution time
- Failure fallback: distribute static case bundle and collect offline answers

## 7) AI Red-Team Mini-Lab

- Objective: prompt injection and defense in controlled AI sandbox
- Capacity: 30/session
- Assets: sandbox models, attack cards, defense checklist, report template
- Staff: 1 owner, 2 mentors, 1 safety/compliance monitor
- Rules: no policy-violating external model abuse; sandbox-only testing
- Reset: model state reset every session
- Win condition: exploit + mitigation pair validated by mentor panel
- Failure fallback: run tabletop-only attack chain drill if model endpoint fails

## 8) Sponsor Demo Street

- Objective: structured sponsor engagement with technical activity
- Capacity: dynamic; one queue per stall
- Assets: stalls, power drops, internet profiles, signage, QR lead forms
- Staff: 1 zone owner, 1 sponsor relations rep, 2 floor stewards
- Rules: each demo block must include interactive component
- Reset: booth turnover checklist every 2 hours
- Win condition: participant completions and sponsor challenge leaderboard points
- Failure fallback: reroute sessions to adjacent booths and publish delays in app

## Zone handover template (end of shift)

- Shift start/end: `<time>`
- Sessions completed: `<count>`
- Incidents: `<yes/no + details>`
- Consumables used: `<list>`
- Pending actions for next shift: `<list>`
