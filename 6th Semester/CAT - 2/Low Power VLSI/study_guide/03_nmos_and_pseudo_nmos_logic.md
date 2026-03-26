# 03 - NMOS and Pseudo-NMOS Logic

---

## Learning Objectives

After studying this section, you will be able to:

- Explain the structure and operation of NMOS logic gates with different pull-up configurations
- Distinguish between depletion-mode and enhancement-mode NMOS pull-ups
- Describe why NMOS logic has static power dissipation
- Explain the concept of ratioed logic and how Pseudo-NMOS gates work
- Calculate the transistor count advantage of Pseudo-NMOS over full CMOS
- Analyze the trade-offs between noise margin, speed, and power in Pseudo-NMOS
- Compare NMOS, Pseudo-NMOS, and full CMOS logic across all key parameters

---

## Ground-Up Explanation

### Historical Context

Before CMOS became the dominant logic family, **NMOS logic** was the primary technology used in early microprocessors (including the original Intel 4004 and 8080). NMOS logic uses only n-channel MOSFETs, which made fabrication simpler because only one type of transistor needed to be manufactured. However, NMOS logic has a fundamental problem: it consumes **static power** continuously whenever the output is LOW.

**Analogy**: NMOS logic is like a light bulb connected through a switch. When the switch is ON (output LOW), current flows continuously through the bulb (load resistor), wasting power even while doing nothing. CMOS fixed this by replacing the always-on bulb with a smart switch that only turns on when needed.

---

## NMOS Logic

### Basic Structure

NMOS logic implements functions using a **pull-down network (PDN)** made of NMOS transistors and a **load device** at the output. The load device pulls the output HIGH when the PDN is not conducting.

The load device can be:
1. A resistor (resistive load NMOS logic)
2. A depletion-mode NMOS transistor (depletion-load NMOS logic)
3. An enhancement-mode NMOS transistor (enhancement-load NMOS logic)

![[nmos_logic_inverter.png]]

### Basic Operation

- When the input is **LOW** (0): The NMOS pull-down transistor is OFF, and the output is pulled HIGH through the load device
- When the input is **HIGH** (1): The NMOS pull-down transistor is ON, and the output is pulled LOW to ground. However, current continuously flows from $V_{DD}$ through the load device and through the pull-down transistor to GND

This continuous current flow when the output is LOW is the fundamental problem of NMOS logic: **static power dissipation**.

---

## Depletion-Mode NMOS Pull-Up

In this arrangement, a **depletion-mode** NMOS transistor serves as the pull-up load. A depletion-mode transistor has a negative threshold voltage, meaning it is ON even when $V_{GS} = 0$.

![[nmos_depletion_pullup.png]]

### Key Characteristics

| Property | Description |
|----------|-------------|
| **Static dissipation** | HIGH - rail-to-rail current flows when $V_{in}$ = logic 1 |
| **Output transition (1 to 0)** | Begins when $V_{in}$ exceeds $V_t$ of the pull-down device |
| **Charging behavior** | Pull-up device is initially non-saturated, presenting lower resistance for charging capacitive loads |
| **Output HIGH level** | Can reach full $V_{DD}$ because the depletion device is always ON |

### Advantages

- Output can swing to full $V_{DD}$ (better than enhancement-load)
- Higher drive current for charging loads
- Better switching characteristics than enhancement-load

### Disadvantages

- Significant static power consumption
- Continuous current when output is LOW
- More complex fabrication (requires depletion implant)

---

## Enhancement-Mode NMOS Pull-Up

In this configuration, an **enhancement-mode** NMOS transistor (with its gate connected to $V_{DD}$ or a separate $V_{GG}$) serves as the load.

![[nmos_enhancement_pullup.png]]

### Key Characteristics

| Property | Description |
|----------|-------------|
| **Static dissipation** | HIGH when $V_{in}$ = logic 1 (current flows through both transistors) |
| **Output HIGH voltage** | Cannot reach $V_{DD}$ if $V_{GG} = V_{DD}$; output limited to $V_{DD} - V_{Tn}$ |
| **Clock-derived $V_{GG}$** | If $V_{GG}$ is derived from a switching source (clock), dissipation can be greatly reduced |
| **Extra supply** | If $V_{GG} > V_{DD}$, an additional supply rail is required |

### The $V_{DD} - V_{Tn}$ Problem

When the enhancement-mode pull-up transistor tries to charge the output to $V_{DD}$:
- As the output voltage rises, $V_{GS}$ of the pull-up decreases
- When the output reaches $V_{DD} - V_{Tn}$, $V_{GS} = V_{Tn}$, and the transistor turns OFF
- The output cannot charge beyond $V_{DD} - V_{Tn}$

This results in a **degraded logic HIGH level**, which reduces noise margins and can cause issues in cascaded logic stages.

---

## Characteristics of NMOS Logic (Summary)

### Uses Only NMOS Transistors

- Simpler fabrication process (single transistor type)
- Smaller area compared to early CMOS implementations
- Historically important in the evolution of digital ICs

### Speed Advantage over PMOS

- Electrons (carriers in NMOS) have **higher mobility** than holes (carriers in PMOS)
- Typical electron mobility: $\mu_n \approx 1350$ cm$^2$/V$\cdot$s in silicon
- Typical hole mobility: $\mu_p \approx 480$ cm$^2$/V$\cdot$s in silicon
- Therefore, NMOS circuits are approximately **2-3 times faster** than PMOS-only logic

### Static Power Dissipation

- When the output is LOW, a continuous current flows from $V_{DD}$ through the load to GND
- This results in **much higher power consumption** compared to CMOS
- The static power is: $P_{static} = V_{DD} \cdot I_{static}$

### Advantages of NMOS Logic

| Advantage | Explanation |
|-----------|-------------|
| Faster than PMOS logic | Higher electron mobility |
| Fewer transistors than CMOS | N+1 vs 2N transistors per gate |
| Simpler design structure | Only one type of transistor |
| Historically important | Foundation of early microprocessors |

### Disadvantages of NMOS Logic

| Disadvantage | Explanation |
|--------------|-------------|
| High static power consumption | Continuous current when output is LOW |
| Poor noise margin vs CMOS | Degraded output levels |
| Output HIGH voltage is weaker | Depends on load device type |
| More heat generation | Due to continuous static current |
| Not suitable for battery-operated systems | Power consumption is too high |

---

## Pseudo-NMOS Logic

### Motivation: Reducing Transistor Count

Full complementary CMOS requires 2N transistors for an N-input gate. For gates with high fan-in (many inputs), the PMOS pull-up network becomes complex and area-expensive because each PMOS transistor must be sized 2-3x larger than the corresponding NMOS (to compensate for lower hole mobility).

**Pseudo-NMOS logic** is an attempt to reduce transistor count by replacing the entire PUN with a **single unconditional PMOS load device** whose gate is permanently connected to ground. This makes it always ON, similar to a constant current source.

**Analogy**: If CMOS is like having dedicated teams for both pull-up and pull-down (each team member corresponding to one input), Pseudo-NMOS simplifies the pull-up team to just one person who is always pulling up. The pull-down team still has one member per input.

### Structure

![[pseudo_nmos_gate.png]]

The Pseudo-NMOS gate consists of:
- An **NMOS pull-down network** that implements the logic function (identical to the PDN in CMOS)
- A **single grounded-gate PMOS transistor** as the load (always ON because $V_{GS} = -V_{DD}$)

### Transistor Count

$$\boxed{\text{Pseudo-NMOS transistor count} = N + 1}$$

Compared to CMOS's $2N$ transistors, this is a significant saving, especially for high fan-in gates:

| Inputs (N) | CMOS (2N) | Pseudo-NMOS (N+1) | Savings |
|------------|-----------|-------------------|---------|
| 2 | 4 | 3 | 25% |
| 4 | 8 | 5 | 37.5% |
| 8 | 16 | 9 | 43.75% |
| 16 | 32 | 17 | 46.9% |

### Operation

**Output HIGH** ($V_{OH}$): When all PDN transistors are OFF, the PMOS load pulls the output to $V_{DD}$. Since the PMOS gate is at GND, $V_{GS} = -V_{DD}$, keeping it strongly ON. The output reaches **full $V_{DD}$** (assuming $V_{OL} < V_{Tn}$ so that all PDN devices are truly OFF).

**Output LOW** ($V_{OL}$): When the PDN conducts, there is a **fight** (voltage divider) between the always-ON PMOS load and the conducting NMOS pull-down network. The output does not reach 0 V but settles at some small positive voltage determined by the ratio of PMOS to NMOS conductances.

![[pseudo_nmos_inverter_vt.png]]

### Why It Is Called "Ratioed" Logic

The output LOW voltage and the overall functionality of the gate **depend on the ratio** between the NMOS and PMOS transistor sizes:
- If the NMOS is sized too small relative to the PMOS, the output LOW level will be too high, potentially causing logic errors
- If the NMOS is sized very large relative to the PMOS, the output LOW will be closer to 0 V, but the gate becomes larger and slower for pull-up transitions

This is why the circuit is called **ratioed logic** -- the correct operation depends on getting the transistor width ratio right.

### Sizing Trade-offs

The sizing of the load device relative to the pull-down devices affects:

| Parameter | Larger PMOS Load | Smaller PMOS Load |
|-----------|-----------------|------------------|
| $V_{OL}$ (output LOW) | Higher (worse) | Lower (better) |
| Pull-up speed | Faster | Slower |
| Static power | Higher | Lower |
| Noise margin | Worse | Better |

![[pseudo_nmos_sizing.jpg]]

### Advantages of Pseudo-NMOS Logic

| Advantage | Explanation |
|-----------|-------------|
| **Reduced transistor count** | N + 1 instead of 2N, saving silicon area |
| **Smaller area** | Less routing complexity, more compact design |
| **Potentially faster rise time** | Single PMOS load simplifies the pull-up path |
| **Simpler layout** | Fewer interconnections in the pull-up network |

### Disadvantages of Pseudo-NMOS Logic

| Disadvantage | Explanation |
|--------------|-------------|
| **Static power dissipation** | During LOW output, both PMOS and NMOS conduct, creating a direct current path from $V_{DD}$ to GND |
| **Reduced noise margins** | Output LOW is not exactly 0 V, reducing the LOW noise margin |
| **Voltage swing degradation** | LOW level depends on sizing; not full rail-to-rail in worst cases |
| **Careful sizing required** | Performance depends on transistor ratio; improper sizing leads to logic failure |
| **Non-zero static current** | Continuous power consumption when output is LOW |

---

## Comprehensive Comparison: CMOS vs NMOS vs Pseudo-NMOS

| Parameter | Full CMOS | NMOS (Depletion Load) | Pseudo-NMOS |
|-----------|-----------|----------------------|-------------|
| **Transistor count** | 2N | N + 1 | N + 1 |
| **Static power** | ~Zero (ideal) | High | Moderate-High |
| **$V_{OH}$** | $V_{DD}$ | $V_{DD}$ | $V_{DD}$ |
| **$V_{OL}$** | 0 V | ~0.1-0.2 V | Depends on ratio |
| **Noise margin** | Excellent | Moderate | Reduced |
| **Speed** | Good | Fast (NMOS only) | Good |
| **Area** | Largest | Smallest | Small |
| **Complexity** | High (both PUN/PDN) | Medium | Low |
| **Use case** | General purpose, low power | Legacy, high speed | Area-constrained, moderate speed |

---

## Common Mistakes

1. **Forgetting that Pseudo-NMOS has static power**: Unlike CMOS, Pseudo-NMOS always draws current when output is LOW
2. **Confusing transistor counts**: CMOS = 2N, Pseudo-NMOS = N+1, not the other way around
3. **Ignoring the ratioed nature**: Pseudo-NMOS output LOW level depends on transistor sizing
4. **Thinking NMOS logic has no output HIGH problem**: Enhancement-load NMOS outputs are limited to $V_{DD} - V_{Tn}$
5. **Assuming depletion-mode = enhancement-mode**: Depletion devices have negative $V_t$ (always ON at $V_{GS} = 0$)

---

## Self-Check Questions

**Q1**: Why does NMOS logic consume static power but CMOS does not?
> In NMOS logic, when the output is LOW, the pull-down NMOS is ON and the load device is also ON (always connected to $V_{DD}$), creating a direct current path from $V_{DD}$ to GND. In CMOS, either the PUN or PDN is always OFF, so no DC path exists.

**Q2**: How many transistors does a 6-input Pseudo-NMOS NOR gate require?
> N + 1 = 6 + 1 = 7 transistors (6 NMOS in parallel for PDN + 1 PMOS load)

**Q3**: What is the maximum output HIGH voltage of an enhancement-mode NMOS pull-up with $V_{GG} = V_{DD}$?
> $V_{OH} = V_{DD} - V_{Tn}$ (the pull-up transistor turns off when $V_{GS} = V_{Tn}$)

**Q4**: Why is Pseudo-NMOS called "ratioed logic"?
> Because the output LOW voltage and noise margins depend on the ratio of NMOS to PMOS transistor sizes. If the ratio is wrong, the gate may not function correctly.

**Q5**: In a Pseudo-NMOS gate, what determines the output LOW level?
> It is determined by the voltage divider action between the always-ON PMOS load and the conducting NMOS PDN. The relative conductances ($g_{PMOS}$ vs $g_{NMOS}$) set $V_{OL}$.

---

## Concept Links

- Full CMOS logic fundamentals: [02_cmos_logic.md](./02_cmos_logic.md)
- Pass Transistor Logic as another reduced-transistor approach: [04_pass_transistor_logic_and_dcvsl.md](./04_pass_transistor_logic_and_dcvsl.md)
- Dynamic logic eliminates static power while keeping low transistor count: [05_dynamic_logic_and_domino.md](./05_dynamic_logic_and_domino.md)
- Pseudo-NMOS concept motivates the introduction of Dynamic Logic: [05_dynamic_logic_and_domino.md](./05_dynamic_logic_and_domino.md#motivation)
- Static power dissipation relates to leakage: [07_leakage_currents.md](./07_leakage_currents.md)
- Transistor count formulas: [14_formula_sheet_ultimate.md](./14_formula_sheet_ultimate.md#transistor-counts)
