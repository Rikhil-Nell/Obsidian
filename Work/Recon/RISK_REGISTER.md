# Risk Register - VIT-AP CyberSec Fest 2026

Use this as the live risk tracker. Update daily from T-20 and every 4 hours during event days.

## Severity scale

- Critical: threatens life safety or flagship continuity
- High: major operational/business impact, recoverable with escalation
- Medium: localized impact, manageable by zone lead
- Low: minor issue, track and improve

## Risk table

| ID | Risk | Severity | Probability | Trigger | Early Signal | Owner | Mitigation | Contingency |
|---|---|---|---|---|---|---|---|---|
| R1 | Campus internet restriction/reliability failure | Critical | High | packet loss/outage > 5 min | rising latency, DNS failures | Infra Lead | dedicated event network, backup 4G/5G, local cache | switch to backup uplink, freeze scoring windows |
| R2 | Power disruption in SAC | Critical | Medium | outage > 2 min | fluctuating supply alerts | Ops Chief | UPS for core infra, generator contract | pause affected zones, protect core tracks |
| R3 | CTF platform outage | Critical | Medium | service unreachable | 5xx spikes, monitor alerts | CTF Director | pre-event load tests, snapshots, redundancy | read-only board + manual scoring fallback |
| R4 | KOTH target corruption/dead boxes | High | Medium | boxes unresponsive | health check failures | Competition Ops | automated reset windows, spare targets | remove bad box, rebalance scoring |
| R5 | App failure during peak usage | High | Medium | app errors > threshold | crash reports/timeout spikes | Tech Lead | CDN, stress testing, reduced payload mode | static microsite + broadcast channels |
| R6 | Overcrowding in high-demand zones | High | High | occupancy exceeds cap | long unmanaged queues | Crowd Lead | app queue controls, slot caps, stewards | temporary closure + reroute |
| R7 | Safety incident in hardware/solder area | Critical | Low | burn/electrical issue | PPE non-compliance | Zone Owner (Hardware) | safety marshals, PPE checks, tool briefing | first aid + suspend zone |
| R8 | Unauthorized offensive activity out-of-scope | Critical | Medium | suspicious scans/complaints | IDS alerts, participant reports | Security Ops | strict scope policy, monitoring, warnings | disqualify + institutional escalation |
| R9 | Sponsor no-show or late setup | Medium | Medium | no check-in by slot -60 min | delayed comms | Sponsor Lead | T-10 freeze, reminders, backup fillers | reassign slot to clinic/challenge rerun |
| R10 | Speaker cancellation | High | Medium | cancellation notice | travel uncertainty | Program Lead | backup speaker pool, lightning talks | swap with panel/Q&A block |
| R11 | Volunteer no-show | Medium | Medium | absent +10 min | weak confirmations | Volunteer Lead | backup pool, shift reminders | deploy floaters and compress non-core zones |
| R12 | Food supply delay/shortage | High | Medium | meal delay > 30 min | vendor dispatch delay | Logistics Lead | multiple vendors, staggered service | issue snack packs + revised windows |
| R13 | Lodging overbooking for outstation participants | High | Low | no room availability | hotel occupancy warnings | Logistics Lead | 10% room buffer, secondary hotels | shuttle to backup lodging |
| R14 | Medical emergency participant/staff | Critical | Low | health incident | distress reports | Ops Chief | onsite medical desk, ambulance contact | activate emergency protocol |
| R15 | Theft/loss of equipment/merch | Medium | Medium | asset mismatch | repeated count drift | Inventory Lead | tagged assets, periodic reconciliation | freeze desk and audit |
| R16 | Reputation risk via social media backlash | High | Medium | viral complaint | negative trend spike | Media Lead | rapid response policy, factual updates | official statement + corrective actions |
| R17 | Legal/policy non-compliance in data handling | High | Low | complaint/report | unclear consent flow | Compliance Owner | explicit consent and data limits | suspend data feature and remediate |
| R18 | Severe weather affecting travel/attendance | Medium | Low | weather alerts | transport delays | Logistics Lead | travel advisories, flexible check-in | extend arrival windows |

## Heat map focus (top 6 priority)

- R1 network reliability
- R2 power continuity
- R3 CTF uptime
- R6 crowd control
- R8 out-of-scope security actions
- R12 food/logistics integrity

## Risk review cadence

- T-20 to T-7: daily 20-minute risk standup
- T-6 to T-1: twice daily review
- Event days: every 4 hours + immediate SEV-1/2 review

## Escalation SLAs

- Critical: immediate, war room in < 5 minutes
- High: acknowledged in < 10 minutes
- Medium: acknowledged in < 30 minutes
- Low: logged and reviewed same day

## Incident log template

- Timestamp:
- Zone:
- Risk ID:
- Description:
- Impact:
- Immediate action:
- Owner:
- ETA to recovery:
- Closure note:

## Go/No-Go checklist (T-1 evening)

- Network backup tested under load
- Core power backup confirmed
- CTF/KOTH smoke test green
- Zone owners and backups confirmed
- Medical/security contacts active
- Food/lodging confirmations locked
