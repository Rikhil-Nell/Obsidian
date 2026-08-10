# 01 Introduction: RAM and Module Context

## Learning objectives

After this section you should be able to:

- Define RAM and contrast **random access** with sequential access.
- Explain **volatility** and why portable systems drive low-power RAM research.
- List where **active** vs **standby** power matter in memory systems.

## Ground-up explanation

**Random-Access Memory (RAM)** stores digital data in addressable locations. The defining property for exams is often stated as: **read/write time is approximately independent of which address is accessed** (organized addressing), unlike a tape where position matters.

RAM is **volatile**: it holds data only while power is applied. Remove power and stored information is lost (for the volatile types in this module: SRAM and DRAM cells as taught here).

**Why low power?** Mobile phones, laptops, and portable systems pushed **circuit-level techniques** to cut **active** power (switching during reads/writes) and **standby** power (leakage and retention when idle).

## Analogies

- **RAM addressing** is like a spreadsheet: any cell is one jump away; you do not scan from row 1 every time.
- **Volatility** is like a whiteboard erased when the room lights turn off.

## Key formulas and facts

No heavy equations on intro slides. Link to dynamic power context: see [Formula sheet](./11_formula_sheet_ultimate.md#dynamic-power-scaling).

## Worked examples

Source deck had no numeric examples in extraction. See [Worked problems](./10_worked_problems.md).

## Common mistakes

- Confusing **RAM** (general) with **SRAM** or **DRAM** (specific implementations).
- Forgetting that **constant access time** is a *defining* feature of random-access organization in this course framing.

## Self-check

1. Why is RAM called volatile?

<details>
<summary>Answer</summary>

Stored state depends on powered devices (charge on a capacitor in DRAM, holding state in cross-coupled inverters in SRAM). No power implies loss of retained data for these volatile memories.

</details>

2. Name two application classes that motivated low-power RAM.

<details>
<summary>Answer</summary>

Examples from material: mobile phones, laptops, portable systems in general.

</details>

## Concept links

- Next: [SRAM architecture](./02_sram_architecture_and_cell.md)
- Power categories: [Low-power SRAM](./04_low_power_sram.md)
