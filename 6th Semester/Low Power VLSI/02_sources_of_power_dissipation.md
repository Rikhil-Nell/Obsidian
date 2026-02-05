# Sources of Power Dissipation

## Learning Objectives
After this section, you will understand:
- The three main components of power dissipation in CMOS
- The complete CMOS power equation
- The difference between peak and average power
- How power is distributed in modern processors

---

## The Complete Power Equation

The total power dissipation in CMOS circuits consists of three main components:

$$\boxed{P_{total} = \underbrace{\alpha f C_L V_{DD}^2}_{\text{Dynamic}} + \underbrace{V_{DD} I_{peak}(P_{01} + P_{10})}_{\text{Short-circuit}} + \underbrace{V_{DD} I_{leak}}_{\text{Leakage}}}$$

| Component | Contribution (Modern Tech) | Trend |
|-----------|---------------------------|-------|
| Dynamic Power | 30-50% | Decreasing relatively |
| Short-circuit Power | ~10% | Decreasing absolutely |
| Leakage Power | 20-70% | **Increasing rapidly** |

---

## Power Classification

![[power_classification.png]]

```
Power Dissipation
├── Dynamic Power (Active Mode)
│   ├── Switching Power ──────────► Charging/discharging capacitances
│   ├── Short-Circuit Power ──────► Both transistors ON briefly
│   └── Glitching Power ──────────► Spurious transitions
│
└── Static Power (Idle Mode)
    └── Leakage Power ────────────► Multiple mechanisms (I1-I6)
```

---

## Analogy: Power as Water Flow

Think of power dissipation like water flowing through pipes:

| Power Component | Water Analogy |
|-----------------|---------------|
| **Switching Power** | Water used to fill/empty a tank repeatedly |
| **Short-circuit Power** | Water that leaks through a partially closed valve |
| **Leakage Power** | Water that drips even when the valve is "closed" |

The key insight: Even when you think everything is "off," there's still leakage!

---

## Peak vs Average Power

Two important metrics for power measurement:

![[total_power_equation.jpg]]

### Peak Power
- **Definition:** Maximum instantaneous power consumed
- **When it occurs:** Usually during power-on or high-activity periods
- **Problems caused:**
  - Melting of interconnections
  - Power-line glitches
  - IR drop issues

### Average Power
- **Definition:** Mean power over a time period
- **Formula:** $P_{avg} = \frac{1}{T}\int_0^T P(t)dt$
- **Problems caused:**
  - Packaging challenges
  - Cooling requirements
  - Battery drain

![[peak_vs_average_power.png]]

> **Critical Insight:** For battery-operated devices, average power determines battery life, but peak power determines packaging and cooling requirements.

---

## Power vs Energy

These terms are often confused but have different meanings:

| Metric | Definition | Unit | Impact |
|--------|------------|------|--------|
| **Power** | Instantaneous rate of energy consumption | Watts (W) | Cooling, packaging |
| **Energy** | Integration of power over time | Joules (J) | Battery life |

$$E = \int_0^T P(t) \, dt$$

**Example:** Two approaches to complete a task:

| Approach | Power | Time | Energy |
|----------|-------|------|--------|
| Approach 1 | High | Short | $P_1 \times t_1$ |
| Approach 2 | Low | Long | $P_2 \times t_2$ |

If $E_1 = E_2$, battery life is the same, but Approach 1 has higher cooling requirements.

---

## Power Distribution in Modern Processors

Based on research (Gonzalez et al.), power in microprocessors is distributed as:

```
┌────────────────────────────────────────────┐
│               TOTAL POWER                  │
├────────────────┬───────────────┬───────────┤
│   Clock (~33%) │ Memory (~33%) │Logic/Wires│
│                │               │   (~33%)  │
└────────────────┴───────────────┴───────────┘
```

In nanometer technologies:
- Nearly 1/3 of power is **leakage**
- High-speed I/O is a growing component
- Cores account for clock, logic, and wires collectively

---

## Key Formulas

### Instantaneous Power
$$P(t) = V(t) \cdot I(t)$$

### Energy over Time Period
$$E = \int_0^T P(t) \, dt$$

### Average Power
$$P_{avg} = \frac{E}{T}$$

### Power in a Resistor
$$P_R = I^2 R = \frac{V^2}{R}$$

### Power from Voltage Source
$$P_{supply} = V_{DD} \cdot I_{DD}$$

### Energy Stored in Capacitor
$$E_C = \frac{1}{2}C V_C^2$$

---

## Common Mistakes

1. **Confusing power and energy** - Power is instantaneous, energy is cumulative
2. **Ignoring leakage in modern technologies** - Can be 20-70% of total power
3. **Focusing only on dynamic power** - All three components matter
4. **Neglecting peak power** - Can cause reliability issues even if average is acceptable

---

## Self-Check Questions

<details>
<summary>1. What are the three main components of power in CMOS?</summary>

1. Dynamic (switching) power - charging/discharging capacitances
2. Short-circuit power - simultaneous conduction of PMOS and NMOS
3. Leakage power - currents when transistors should be off
</details>

<details>
<summary>2. Why is leakage power becoming more significant?</summary>

With technology scaling:
- Threshold voltages decrease → more subthreshold leakage
- Gate oxides become thinner → more gate tunneling
- Channel lengths decrease → more short-channel effects (DIBL, punchthrough)
Leakage can now be 20-70% of total power in modern processes.
</details>

<details>
<summary>3. For battery life, which is more important: peak or average power?</summary>

Average power determines battery life since it represents total energy consumed over time. Peak power is important for packaging and cooling design but doesn't directly impact battery capacity requirements.
</details>

---

## Concept Links

- **Previous:** [Need for Low Power Design](./01_need_for_low_power_design.md)
- **Next:** [Switching Power Dissipation](./03_switching_power_dissipation.md)
- **Related:**
  - [Short-Circuit Power](./04_short_circuit_power.md)
  - [Leakage Power](./05_leakage_power.md)
  - [Glitching Power](./06_glitching_power.md)
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#power-equations)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Need for Low Power](./01_need_for_low_power_design.md) | Sources of Power | [Switching Power](./03_switching_power_dissipation.md) |
