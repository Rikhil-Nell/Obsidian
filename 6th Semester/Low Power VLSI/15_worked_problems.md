# Worked Problems - Low Power VLSI Design

## Overview

This file contains solutions to all problems from the course materials. Each solution includes:
- Problem statement
- Step-by-step solution
- Backlinks to relevant concepts

---

## Problem 1: Static Power Calculation

> **Source:** Module 1, Problem 6

### Problem Statement

A digital CMOS IC operates at 15 MHz clock frequency consumes 130 mW, the same IC operating at 10 MHz clock frequency consumes 100 mW power (static and dynamic). Find Static Power?

### Relevant Concepts
- [Sources of Power Dissipation](./02_sources_of_power_dissipation.md)
- [Switching Power](./03_switching_power_dissipation.md)

### Solution

**Step 1:** Write the power equations for both frequencies

Total power = Static power + Dynamic power

$$P = P_{static} + P_{dynamic}$$

Since dynamic power is proportional to frequency:
$$P_{dynamic} = k \cdot f$$

**Step 2:** Set up simultaneous equations

At 15 MHz:
$$130 = P_{static} + k \cdot 15 \quad ...(1)$$

At 10 MHz:
$$100 = P_{static} + k \cdot 10 \quad ...(2)$$

**Step 3:** Subtract equation (2) from equation (1)

$$130 - 100 = k \cdot (15 - 10)$$
$$30 = 5k$$
$$k = 6 \text{ mW/MHz}$$

**Step 4:** Substitute back to find static power

From equation (2):
$$100 = P_{static} + 6 \times 10$$
$$100 = P_{static} + 60$$
$$\boxed{P_{static} = 40 \text{ mW}}$$

**Verification:**
- At 15 MHz: $40 + 6 \times 15 = 40 + 90 = 130$ mW ✓
- At 10 MHz: $40 + 6 \times 10 = 40 + 60 = 100$ mW ✓

---

## Problem 2: Dynamic Power for Logic Gates

> **Source:** Module 2, Problems 2/50-53

### Problem Statement

Calculate the dynamic power for all logic gates, using:
- Load Capacitance ($C_L$) = 1 pF
- $V_{DD}$ = 2.5 V
- Frequency ($f$) = 100 MHz

### Relevant Concepts
- [Switching Power](./03_switching_power_dissipation.md)
- [Formula Sheet - Switching Activity](./16_formula_sheet_ultimate.md#switching-activity)

### Solution

**Step 1:** Apply the switching power formula

$$P_{sw} = \alpha \cdot f \cdot C_L \cdot V_{DD}^2$$

**Step 2:** Calculate for each gate type

Using switching activity values from the switching probability table:

#### Inverter ($\alpha = 1/4 = 0.25$)
$$P_{inv} = 0.25 \times 100 \times 10^6 \times 1 \times 10^{-12} \times (2.5)^2$$
$$P_{inv} = 0.25 \times 10^{-4} \times 6.25$$
$$\boxed{P_{inv} = 156.25 \text{ μW}}$$

#### 2-Input AND Gate ($\alpha = 3/16 = 0.1875$)
$$P_{AND} = 0.1875 \times 10^{-4} \times 6.25$$
$$\boxed{P_{AND} = 117.19 \text{ μW}}$$

#### 2-Input OR Gate ($\alpha = 3/16 = 0.1875$)
$$\boxed{P_{OR} = 117.19 \text{ μW}}$$

#### 2-Input NAND Gate ($\alpha = 3/16 = 0.1875$)
$$\boxed{P_{NAND} = 117.19 \text{ μW}}$$

#### 2-Input NOR Gate ($\alpha = 3/16 = 0.1875$)
$$\boxed{P_{NOR} = 117.19 \text{ μW}}$$

#### 2-Input XOR Gate ($\alpha = 1/4 = 0.25$)
$$\boxed{P_{XOR} = 156.25 \text{ μW}}$$

### Summary Table

| Gate | $\alpha$ | Power (μW) |
|------|----------|------------|
| INV | 0.25 | 156.25 |
| AND2 | 0.1875 | 117.19 |
| OR2 | 0.1875 | 117.19 |
| NAND2 | 0.1875 | 117.19 |
| NOR2 | 0.1875 | 117.19 |
| XOR2 | 0.25 | 156.25 |

---

## Problem 3: Chip-Level Dynamic Power

> **Source:** Module 2, Problem 4/57

### Problem Statement

For a chip that contains the equivalent of 250,000 inverters, calculate the total dynamic power dissipated if 20% of the gates change value at any given instant of time at an average rate of f = 10 MHz.

Assume: $C_L$ = 10 fF per inverter, $V_{DD}$ = 2.5V

### Relevant Concepts
- [Switching Power](./03_switching_power_dissipation.md)
- [Sources of Power](./02_sources_of_power_dissipation.md)

### Solution

**Step 1:** Determine the effective switching activity

If 20% of gates switch at any instant:
$$\alpha_{eff} = 0.20$$

**Step 2:** Calculate total switched capacitance

$$C_{total} = N_{gates} \times C_L = 250,000 \times 10 \text{ fF} = 2.5 \times 10^6 \text{ fF} = 2.5 \text{ nF}$$

**Step 3:** Apply the power formula

$$P_{total} = \alpha \cdot f \cdot C_{total} \cdot V_{DD}^2$$
$$P_{total} = 0.20 \times 10 \times 10^6 \times 2.5 \times 10^{-9} \times (2.5)^2$$
$$P_{total} = 0.20 \times 10^7 \times 2.5 \times 10^{-9} \times 6.25$$
$$P_{total} = 0.20 \times 2.5 \times 6.25 \times 10^{-2}$$
$$\boxed{P_{total} = 31.25 \text{ mW}}$$

---

## Problem 4: System Static Power

> **Source:** Module 1, Problem 6/96

### Problem Statement

A digital system-on-chip in a 1V 65 nm process (with 50 nm drawn channel lengths and λ = 25 nm) has 1 billion transistors, of which 50 million are in logic gates and the remainder in memory arrays.

Given:
- Average logic transistor width: 12λ
- Average memory transistor width: 4λ
- Subthreshold leakage for low-VT: 100 nA/μm
- Subthreshold leakage for high-VT: 10 nA/μm
- Gate leakage: 5 nA/μm
- Junction leakage: negligible
- Memories use low-leakage (high-VT) devices everywhere
- Logic uses low-leakage devices except 5% of paths (critical, low-VT)

Estimate the static power consumption.

### Relevant Concepts
- [Leakage Power](./05_leakage_power.md)
- [Formula Sheet - Leakage](./16_formula_sheet_ultimate.md#leakage-currents)

### Solution

**Step 1:** Calculate total width of low-VT devices

Only 5% of logic transistors are low-VT:
$$W_{low-VT} = 50 \times 10^6 \times 0.05 \times 12\lambda \times 0.025 \text{ μm/λ}$$
$$W_{low-VT} = 50 \times 10^6 \times 0.05 \times 12 \times 0.025 \text{ μm}$$
$$W_{low-VT} = 0.75 \times 10^6 \text{ μm}$$

**Step 2:** Calculate total width of high-VT devices

95% of logic + all memory:
$$W_{high-VT} = [50 \times 10^6 \times 0.95 \times 12 + 950 \times 10^6 \times 4] \times 0.025$$
$$W_{high-VT} = [570 \times 10^6 + 3800 \times 10^6] \times 0.025$$
$$W_{high-VT} = 4370 \times 10^6 \times 0.025$$
$$W_{high-VT} = 109.25 \times 10^6 \text{ μm}$$

**Step 3:** Calculate subthreshold leakage current

Assuming half the transistors are OFF at any time:
$$I_{sub} = \frac{1}{2}[W_{low-VT} \times 100 + W_{high-VT} \times 10] \text{ nA}$$
$$I_{sub} = \frac{1}{2}[0.75 \times 10^6 \times 100 + 109.25 \times 10^6 \times 10]$$
$$I_{sub} = \frac{1}{2}[75 \times 10^6 + 1092.5 \times 10^6]$$
$$I_{sub} = \frac{1167.5 \times 10^6}{2} = 583.75 \times 10^6 \text{ nA}$$
$$I_{sub} = 584 \text{ mA}$$

**Step 4:** Calculate gate leakage current

All transistors contribute to gate leakage (assuming ON transistors have gate current):
$$I_{gate} = \frac{1}{2}(W_{low-VT} + W_{high-VT}) \times 5 \text{ nA/μm}$$
$$I_{gate} = \frac{1}{2}(0.75 + 109.25) \times 10^6 \times 5$$
$$I_{gate} = \frac{110 \times 10^6 \times 5}{2}$$
$$I_{gate} = 275 \times 10^6 \text{ nA} = 275 \text{ mA}$$

**Step 5:** Calculate total static power

$$P_{static} = V_{DD} \times (I_{sub} + I_{gate})$$
$$P_{static} = 1\text{V} \times (584 + 275) \text{ mA}$$
$$\boxed{P_{static} = 859 \text{ mW}}$$

---

## Problem 5: Switching Probability Derivation

> **Source:** Module 1, Problem 5/95

### Problem Statement

Derive the switching probabilities for the logic gates in the table.

### Relevant Concepts
- [Switching Power](./03_switching_power_dissipation.md#switching-activity-calculation)

### Solution

**Switching activity formula:**
$$\alpha = P_{0 \to 1} = P_0 \times P_1$$

Assuming equal probability for each input (0.5 for '0' and 0.5 for '1'):

#### 2-Input AND Gate

| A | B | Y |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

- $P(Y=1) = P_1 = 1/4$
- $P(Y=0) = P_0 = 3/4$
- $\alpha = P_0 \times P_1 = \frac{3}{4} \times \frac{1}{4} = \boxed{\frac{3}{16}}$

#### 3-Input AND Gate

- $P(Y=1) = 0.5^3 = 1/8$
- $P(Y=0) = 7/8$
- $\alpha = \frac{7}{8} \times \frac{1}{8} = \boxed{\frac{7}{64}}$

#### 2-Input OR Gate

| A | B | Y |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 1 |

- $P(Y=1) = 3/4$
- $P(Y=0) = 1/4$
- $\alpha = \frac{1}{4} \times \frac{3}{4} = \boxed{\frac{3}{16}}$

#### 2-Input NAND Gate

| A | B | Y |
|---|---|---|
| 0 | 0 | 1 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

- $P(Y=1) = 3/4$
- $P(Y=0) = 1/4$
- $\alpha = \frac{3}{4} \times \frac{1}{4} = \boxed{\frac{3}{16}}$

#### 2-Input NOR Gate

| A | B | Y |
|---|---|---|
| 0 | 0 | 1 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 0 |

- $P(Y=1) = 1/4$
- $P(Y=0) = 3/4$
- $\alpha = \frac{1}{4} \times \frac{3}{4} = \boxed{\frac{3}{16}}$

#### 2-Input XOR Gate

| A | B | Y |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

- $P(Y=1) = 2/4 = 1/2$
- $P(Y=0) = 2/4 = 1/2$
- $\alpha = \frac{1}{2} \times \frac{1}{2} = \boxed{\frac{1}{4}}$

### Summary Table

| Gate | $P_1$ | $P_0$ | $\alpha$ |
|------|-------|-------|----------|
| AND2 | 1/4 | 3/4 | 3/16 |
| AND3 | 1/8 | 7/8 | 7/64 |
| OR2 | 3/4 | 1/4 | 3/16 |
| NAND2 | 3/4 | 1/4 | 3/16 |
| NOR2 | 1/4 | 3/4 | 3/16 |
| XOR2 | 1/2 | 1/2 | 1/4 |

---

## Problem 6: Complex Circuit Power Dissipation

> **Source:** Module 2, Problems 3/54 and 5/58

### Problem Statement

Calculate the dynamic power dissipation of a circuit with multiple gates.

Given a circuit with:
- 3 NAND gates, 2 NOR gates, 1 XOR gate
- $C_L$ = 10 fF per gate
- $V_{DD}$ = 1.2 V
- $f$ = 500 MHz

### Relevant Concepts
- [Switching Power](./03_switching_power_dissipation.md)
- [Problem 5 above](#problem-5-switching-probability-derivation)

### Solution

**Step 1:** Calculate power per gate type

Common factor: $f \cdot C_L \cdot V_{DD}^2 = 500 \times 10^6 \times 10 \times 10^{-15} \times 1.44$
$$= 7.2 \times 10^{-6} = 7.2 \text{ μW}$$

**Step 2:** Apply switching activities

| Gate Type | Count | $\alpha$ | Power (μW) |
|-----------|-------|----------|------------|
| NAND | 3 | 3/16 | $3 \times 0.1875 \times 7.2 = 4.05$ |
| NOR | 2 | 3/16 | $2 \times 0.1875 \times 7.2 = 2.70$ |
| XOR | 1 | 1/4 | $1 \times 0.25 \times 7.2 = 1.80$ |

**Step 3:** Sum total power

$$P_{total} = 4.05 + 2.70 + 1.80 = \boxed{8.55 \text{ μW}}$$

---

## Quick Reference: Problem Types

| Problem Type | Key Formula | See Topic |
|--------------|-------------|-----------|
| Static power from frequency sweep | $P = P_s + k \cdot f$ | [Problem 1](#problem-1-static-power-calculation) |
| Gate dynamic power | $P = \alpha f C V^2$ | [Problem 2](#problem-2-dynamic-power-for-logic-gates) |
| Chip-level power | $P = \alpha f C_{total} V^2$ | [Problem 3](#problem-3-chip-level-dynamic-power) |
| Leakage power | $P = V_{DD} \cdot I_{leak}$ | [Problem 4](#problem-4-system-static-power) |
| Switching activity | $\alpha = P_0 \times P_1$ | [Problem 5](#problem-5-switching-probability-derivation) |

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Switched Capacitance](./14_switched_capacitance.md) | Worked Problems | [Formula Sheet](./16_formula_sheet_ultimate.md) |
