# Switching Power Dissipation

## Learning Objectives
After this section, you will understand:
- How switching power is generated in CMOS circuits
- Complete derivation of the switching power formula
- The role of node transition factor and switching activity
- How voltage scaling affects switching power

---

## What is Switching Power?

Switching power is the power dissipated when output node voltages transition between logic levels (0 ↔ VDD). It occurs due to **charging and discharging** of capacitive loads.

**Key Insight:** This is the dominant component of dynamic power (30-50% of total power in modern technologies).

---

## Capacitance Components

When a CMOS gate drives a load, the total output capacitance consists of:

```
Total Load Capacitance (CL)
├── Output Capacitance (Cout)
│   └── Drain junction capacitances of output transistors
│
├── Interconnect Capacitance (Cwire)
│   └── Metal lines between gates
│   └── Dominant in sub-micron technologies!
│
└── Input Capacitance (Cin)
    └── Gate oxide capacitances of driven gates
```

![[cmos_inverter_charging.png]]

---

## Energy per Switching Event

### Derivation

Consider a 0 → VDD transition (charging the load capacitance):

**Step 1:** Energy drawn from supply
$$E_{supply} = \int_0^T V_{DD} \cdot i(t) \, dt$$

**Step 2:** Express current in terms of capacitance
$$i(t) = C_L \frac{dV_{out}}{dt}$$

**Step 3:** Substitute and integrate
$$E_{supply} = \int_0^{V_{DD}} V_{DD} \cdot C_L \, dV_{out} = C_L V_{DD}^2$$

**Step 4:** Energy stored in capacitor
$$E_{stored} = \frac{1}{2} C_L V_{DD}^2$$

**Step 5:** Energy dissipated in PMOS resistance
$$E_{dissipated} = E_{supply} - E_{stored} = \frac{1}{2} C_L V_{DD}^2$$

> **Key Result:** Half the energy from the supply is stored in the capacitor, and half is dissipated as heat in the PMOS transistor during charging.

During discharge (VDD → 0):
- The stored energy $\frac{1}{2} C_L V_{DD}^2$ is dissipated in the NMOS transistor

### Total Energy per Complete Cycle
$$\boxed{E_{cycle} = C_L V_{DD}^2}$$

---

## Switching Power Formula

If the gate switches at frequency $f$ (one 0→1 and one 1→0 transition per clock):

$$\boxed{P_{switching} = f C_L V_{DD}^2}$$

### With Switching Activity Factor

Not every clock cycle results in a transition. Define:
- $\alpha$ = switching activity factor = probability of transition per clock cycle

$$\boxed{P_{switching} = \alpha f C_L V_{DD}^2}$$

| Variable | Description | Typical Range |
|----------|-------------|---------------|
| $\alpha$ | Switching activity | 0.1 - 0.5 |
| $f$ | Clock frequency | MHz - GHz |
| $C_L$ | Load capacitance | fF - pF |
| $V_{DD}$ | Supply voltage | 0.5V - 5V |

---

## Node Transition Factor

For complex gates with internal nodes, we need to account for all switching capacitances:

$$P_{switching} = f \sum_i T_i C_i V_i^2$$

where:
- $T_i$ = node transition factor for node $i$
- $C_i$ = capacitance at node $i$
- $V_i$ = voltage swing at node $i$ (may be less than $V_{DD}$)

---

## Switching Activity Calculation

For a gate with output $Y$:

$$\alpha = P_{0 \to 1} = P_0 \cdot P_1$$

where:
- $P_0$ = probability that output is 0
- $P_1$ = probability that output is 1

### Example: 2-Input NAND Gate

| A | B | Y (NAND) |
|---|---|----------|
| 0 | 0 | 1 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

With equal input probabilities:
- $P_1 = P(Y=1) = 3/4$
- $P_0 = P(Y=0) = 1/4$
- $\alpha = P_0 \cdot P_1 = \frac{3}{4} \times \frac{1}{4} = \frac{3}{16}$

### Switching Probabilities for Common Gates

| Gate | $P_1$ (Y=1) | $P_0$ (Y=0) | $\alpha$ |
|------|-------------|-------------|----------|
| Inverter | 1/2 | 1/2 | 1/4 |
| 2-input AND | 1/4 | 3/4 | 3/16 |
| 2-input OR | 3/4 | 1/4 | 3/16 |
| 2-input NAND | 3/4 | 1/4 | 3/16 |
| 2-input NOR | 1/4 | 3/4 | 3/16 |
| 2-input XOR | 1/2 | 1/2 | 1/4 |

---

## Voltage Scaling Impact

Since $P \propto V_{DD}^2$, reducing voltage dramatically reduces power:

| Voltage Change | Power Reduction |
|----------------|-----------------|
| 5V → 3.3V | 56% reduction |
| 5V → 1V | 96% reduction |
| 3.3V → 1V | 91% reduction |

**Trade-off:** Lower voltage → Higher delay

$$t_p \propto \frac{C_L V_{DD}}{(V_{DD} - V_T)^2}$$

As $V_{DD}$ approaches $V_T$, delay increases dramatically!

![[switching_waveform.png]]

---

## Key Observations

1. **Independence from transistor parameters:** Switching power depends on $C_L$, $V_{DD}$, not on transistor sizes or mobility

2. **Quadratic voltage dependence:** This is why voltage scaling is the most effective power reduction technique

3. **Linear frequency dependence:** Reducing frequency proportionally reduces power

4. **Capacitance matters:** In deep sub-micron, interconnect capacitance dominates

---

## Common Mistakes

1. **Forgetting the switching activity factor** - Power formula without $\alpha$ assumes 100% activity
2. **Ignoring internal node transitions** - Complex gates have internal parasitic capacitances
3. **Assuming full voltage swing** - Some nodes may have partial swings
4. **Not considering interconnect** - Can dominate in modern technologies

---

## Self-Check Questions

<details>
<summary>1. Why is half the energy lost during charging?</summary>

When charging a capacitor through a resistor (PMOS transistor) from a constant voltage source:
- Energy supplied = $C_L V_{DD}^2$
- Energy stored = $\frac{1}{2} C_L V_{DD}^2$
- Energy dissipated in resistance = $\frac{1}{2} C_L V_{DD}^2$

This is independent of the resistance value! It's a fundamental property of RC charging.
</details>

<details>
<summary>2. What is the switching activity of a clock signal?</summary>

A clock signal transitions once per cycle (0→1 or 1→0), so:
- For a 50% duty cycle: $P_0 = P_1 = 0.5$
- $\alpha = 0.5 \times 0.5 = 0.25$ for each edge
- Total activity (both edges) = 0.5 per cycle
</details>

<details>
<summary>3. Why is voltage scaling more effective than frequency scaling?</summary>

Power = $\alpha f C_L V_{DD}^2$
- Halving frequency reduces power by 50%
- Halving voltage reduces power by 75% (quadratic effect)

Voltage scaling has a squared effect on power reduction.
</details>

---

## Worked Example

**Problem:** A CMOS inverter drives a load of 50 fF at 1 GHz with VDD = 1.2V. The switching activity is 0.2. Calculate the switching power.

**Solution:**
$$P_{sw} = \alpha f C_L V_{DD}^2$$
$$P_{sw} = 0.2 \times 10^9 \times 50 \times 10^{-15} \times (1.2)^2$$
$$P_{sw} = 0.2 \times 50 \times 10^{-6} \times 1.44$$
$$\boxed{P_{sw} = 14.4 \, \mu W}$$

---

## Concept Links

- **Previous:** [Sources of Power Dissipation](./02_sources_of_power_dissipation.md)
- **Next:** [Short-Circuit Power](./04_short_circuit_power.md)
- **Related:**
  - [Glitching Power](./06_glitching_power.md) - Another dynamic power component
  - [Voltage Scaling](./12_parallelism.md) - Using parallelism for voltage scaling
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#switching-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Sources of Power](./02_sources_of_power_dissipation.md) | Switching Power | [Short-Circuit Power](./04_short_circuit_power.md) |
