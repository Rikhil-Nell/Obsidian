# Semiconductor Memory

> Concept: classify memories by access mode/volatility, understand SRAM, DRAM (1T, 3T), ROM/EPROM/EEPROM cells, and PLA/PAL programmable logic. The exam often asks for cell schematics, read/write operations, and area-vs-speed tradeoffs.

## Why Memory Matters

In any digital chip, transistor count for **storage** typically exceeds transistor count for **logic**. Cache, register file, frame buffer, configuration ROM — all use specialised cell topologies optimised for area and reliability over speed. Storage capacity per chip roughly doubles every two years, driven by aggressive shrink rules in memory-only processes.

## Memory Characteristics

Three design metrics:

- **Area efficiency** — bits per unit area. Drives cost per bit.
- **Access speed** — latency to read/write. Determines memory-bound system performance.
- **Power consumption** — important even in idle (retention) modes for portable systems.

## Classification Axes

| Axis | Categories |
|---|---|
| **Operation mode** | Read–Write (SRAM, DRAM, EEPROM, Flash) vs Read-Only (Mask ROM) |
| **Storage volatility** | Volatile (SRAM, DRAM) vs Non-volatile (Mask ROM, EPROM, EEPROM, Flash) |
| **Access pattern** | Random-access (most modern memories) vs Non-random (SAM = Serial Access, CAM = Content Addressable) |

---

## Memory Array Organisation

A memory array is a 2-D grid of cells. Each cell stores one bit and is connected to a **word line** (row) and one or more **bit lines** (column).

```
                  Bit lines (BL)
                 │   │   │   ...
   Word ─────────cell─cell─cell───
   line 0
   Word ─────────cell─cell─cell───
   line 1
   ...
```

For an array of $2^N\times 2^M$ cells:

- **Row decoder** activates one of $2^N$ word lines based on $N$ address bits.
- **Column decoder/mux** selects one (or several) of $2^M$ bit lines based on $M$ address bits.
- **Sense amplifier** detects the small voltage difference on bit lines during read.
- **Precharge / write driver** for write operations.

Access to a particular bit therefore needs a row select + column select + sense or write.

---

## SRAM (Static RAM) — 6T Cell

The classic six-transistor SRAM cell:

```
       VDD                  VDD
        │                    │
       Mp1                  Mp2
        │                    │
   Q ◄──┴────┐         ┌─────┴── Q̄
             │         │
             Mn1       Mn2
             │         │
            GND       GND

   WL ─── Mn3 (access) ─── BL
   WL ─── Mn4 (access) ─── BL̄
```

The two cross-coupled inverters (Mn1/Mp1 and Mn2/Mp2) form a bistable element. The two access nMOS (Mn3, Mn4) are the read/write pass-gates controlled by the word line WL. BL and BL̄ are the bit lines.

### States

| State | WL | BL drive | Behaviour |
|---|---|---|---|
| **Hold (standby)** | 0 | floating | Cell isolated; cross-coupled inverters retain Q and Q̄. |
| **Read** | 1 | both BLs precharged to $V_{DD}$ | Cell pulls one BL down (the side with Q=0). Sense amp detects the differential voltage. |
| **Write** | 1 | force one BL high, other low | Bit-line drivers overpower the cell, flipping Q and Q̄. |

### Sizing for Read Stability

During a read, the cell internal storage node pulls a precharged BL down. If the access transistor is too strong, BL "wins" and pulls the storage node up enough to flip the cell — a destructive read. So:

- Access transistors moderate (β ≈ 1 of inverter pull-down).
- Inverter pull-down stronger than access (cell ratio).

### Sizing for Write

During write, the bit-line driver must overpower the cross-coupled feedback to flip the cell. Pull-up devices weak relative to access transistors (pull-up ratio).

So an SRAM cell has *two* sizing constraints (read stability, writeability) — both ratioed.

### Why 6T

- Stable indefinitely as long as $V_{DD}$ holds.
- Fast access.
- Robust against single-event upsets if cell is sized properly.
- Penalty: 6 transistors → low density relative to DRAM.

Typical use: caches, register files, on-chip buffers.

---

## DRAM (Dynamic RAM)

Stores a bit as **charge on a capacitor**, not in a feedback loop. Charge leaks → must refresh periodically (typically every few ms).

### 1T DRAM Cell (Modern)

```
   WL ─── Mn (access) ─── storage node
                          │
                         C_s (storage capacitor, fabricated in trench or stacked)
                          │
                         Plate (often tied to V_DD/2)
```

Just one transistor and one capacitor per bit. Smallest cell possible → highest density.

| Operation | Mechanism |
|---|---|
| **Write 1** | WL=1, BL=$V_{DD}$. C_s charges to $V_{DD}-V_{Tn}$. |
| **Write 0** | WL=1, BL=0. C_s discharges to 0. |
| **Read** | Precharge BL to $V_{DD}/2$. Then activate WL. Charge sharing between C_s and BL capacitance creates a small voltage shift on BL. Sense amp detects sign. **Destructive** — must rewrite afterwards. |
| **Refresh** | Read every cell periodically and rewrite to restore charge. |

### 3T DRAM Cell

Older variant with separate read and write devices:

```
   Write_WL ─── M_w ──┬── M_storage (stores charge on its gate)
                      │              │
                      │             Read_BL ── M_r ── Read_WL
   Write_BL ──────────┘
```

- Write transistor M_w pulls the storage gate high or low based on Write_BL.
- The stored value modulates M_storage's conductance.
- Read M_r passes M_storage's conduction state onto Read_BL during read.

Pros: separate read/write paths → faster.
Cons: 3 transistors and 4 control lines → less dense than 1T.

### 2T and 4T Variants

Intermediate cell counts. Largely obsolete now — 1T won on density.

### SRAM vs DRAM Comparison

| | SRAM | DRAM |
|---|---|---|
| Cell size | 6T | 1T + 1C |
| Density | low | high |
| Speed | very fast | slower (precharge + sense) |
| Refresh | none | required |
| Cost/bit | high | low |
| Power (active) | medium | high (refresh) |
| Power (idle) | low | high (refresh) |
| Process | standard CMOS | special memory process (deep trench/stack capacitor) |

---

## ROM Variants

### Mask Programmable ROM

The bit pattern is encoded in a *mask* during fabrication — non-volatile and read-only.

A bit cell is typically just an nMOS transistor that conducts (logic 0 or 1, depending on convention) on its word line, or no transistor at all (open circuit). Some manufacturers encode the bit by changing the threshold via implant.

### EPROM (Erasable Programmable ROM)

Uses **floating-gate avalanche-injection MOS (FAMOS)** transistors. The cell has:
- A normal control gate (driven by word line).
- A *floating gate* between the control gate and the channel — completely surrounded by oxide so charge can be trapped indefinitely.

**Programming**: apply high $V_{DS}$ and $V_{GS}$ → hot electrons gain enough energy to tunnel into the floating gate. Trapped charge raises the threshold of the cell — the cell appears "off" at normal $V_{GS}$ → reads as a 0 (or 1, depending on convention).

**Erasing**: expose the chip to **ultraviolet light** through a quartz window. UV photons eject the trapped electrons. All cells reset together.

### EEPROM (Electrically Erasable Programmable ROM)

Replaces UV erase with electrical erase using **Fowler-Nordheim tunnelling**:

- **Programming**: hot-electron injection or Fowler-Nordheim tunnelling.
- **Erasing**: Fowler-Nordheim tunnelling under high gate-to-source voltage.

Cell uses a **FLOTOX (floating-gate tunnelling-oxide)** transistor with a thin tunnelling oxide region.

Allows byte-by-byte erase (vs entire chip in EPROM). Slower writes than RAM but no UV needed.

Flash memory is a variant of EEPROM that erases in large blocks for higher density.

---

## Programmable Logic Devices (PLD)

A black-box chip with programmable switches and logic gates that lets you implement arbitrary combinational (and sometimes sequential) functions.

### Programmable Logic Array (PLA)

- **Programmable AND plane** + **Programmable OR plane**.
- Each input goes to the AND plane to produce **product terms** (minterms or general products).
- Each product term feeds the OR plane to combine into outputs (Sum-of-Products).
- Both planes are user-programmable, so any SOP function is implementable.

### Programmable Array Logic (PAL)

- **Programmable AND plane** + **Fixed OR plane**.
- Each output has a fixed number of product terms in its OR.
- Less flexible than PLA but cheaper to manufacture and faster.

### Worked Example — Implementing on PLA

Implement
$$
X = \overline A B C + A B C + \overline A \overline B \overline C
$$
$$
Y = A B C + A \overline B C
$$

Steps:

1. Use input inverters to make both polarities of A, B, C available.
2. Program AND plane: connect rows for $\overline A B C$, $ABC$, $\overline A\overline B\overline C$, $A\overline B C$ (4 product terms, one row each).
3. Program OR plane: column $X$ ORs rows 1, 2, 3; column $Y$ ORs rows 2, 4.
4. Done — outputs $X$ and $Y$ realised.

PLA layout is regular and easy to autoroute, which is why early ASIC tools heavily used it.

---

## Sense Amplifiers

A read on SRAM and DRAM produces a small voltage difference (50–100 mV) between BL and BL̄. The **sense amplifier** is a clocked latch (typically cross-coupled inverters with NMOS switch tail) that amplifies this difference to full $V_{DD}$ swing rapidly.

Pre-charged before read, equalised, then activated to sense the differential. Critical for memory speed.

---

## Common Exam Mistakes

- Stating that DRAM doesn't need refresh in an active system. It always does — refresh is just hidden in the controller.
- Confusing EPROM (UV erase) with EEPROM (electrical erase).
- Drawing 6T SRAM with only 4 transistors. The cell is 6 transistors: two cross-coupled inverters (4) + two access transistors (2).
- Writing PLA as "fixed AND plane + programmable OR" — that's PAL.
- Forgetting the destructive read of DRAM.

## Self-Check Questions

1. Why does an SRAM cell need ratioed sizing for read stability?
   <details><summary>Answer</summary>During read, the cell internal node fights the precharged bit-line through the access transistor. If access is too strong, the bit line wins and flips the cell. The pull-down inverter must be stronger than the access transistor.</details>

2. Why is a 1T DRAM cell read destructive?
   <details><summary>Answer</summary>Charge sharing between $C_s$ and $C_{BL}$ during read drains most of the stored charge from $C_s$. The cell must be rewritten to restore the original level.</details>

3. Why does EPROM use UV light for erase?
   <details><summary>Answer</summary>UV photons have enough energy to eject electrons trapped on the floating gate. Without an electrical erase mechanism, this is the only way to remove trapped charge from FAMOS cells.</details>

4. Why is PLA more flexible than PAL?
   <details><summary>Answer</summary>Both AND and OR planes are programmable in PLA, so each output can have any subset of any product terms. PAL has fixed OR plane, restricting each output to a small predetermined set of product terms.</details>

5. Why is 6T SRAM not as dense as 1T DRAM?
   <details><summary>Answer</summary>Six transistors and routing for two bit lines + word line take far more area than a single transistor + capacitor. Density advantages mean DRAM dominates for main memory; SRAM dominates for caches where speed matters.</details>

## Concept Links

- Previous: [[14_sequential_circuits]]
- Next: [[16_design_for_testability]]
- Related: [[01_fabrication_processes]] (memory uses special process options), [[09_power_dissipation]] (memory leakage dominates many SoCs)
- Formulas: [[18_formula_sheet#memory]]
