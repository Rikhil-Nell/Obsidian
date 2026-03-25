# 03 - DC Characteristics of Op-Amp 📊

## Overview

DC characteristics describe how the op-amp behaves with **static (unchanging) signals**. These are imperfections that exist even when no AC signal is applied.

> **Analogy:** Even when your car is "perfectly" aligned, it might still drift slightly to one side. Op-amps have similar "drifts" called offsets.

---

## The Four DC Characteristics

```
┌─────────────────────────────────────────────────────────────┐
│                   DC CHARACTERISTICS                        │
├──────────────────┬──────────────────┬───────────────────────┤
│  Input Bias      │  Input Offset    │  Input Offset         │
│  Current (IB)    │  Current (IOS)   │  Voltage (VOS)        │
├──────────────────┴──────────────────┴───────────────────────┤
│                 Total Output Offset Voltage                 │
└─────────────────────────────────────────────────────────────┘
```

---

## 1️⃣ Input Bias Current (IB)

### Definition
The **average** of the currents that flow into the inverting and non-inverting input terminals.

$$\boxed{I_B = \frac{I_{B1} + I_{B2}}{2}}$$

Where:
- $I_{B1}$ = Current into inverting input (-)
- $I_{B2}$ = Current into non-inverting input (+)

### Why Does It Exist?
- Op-amp input stage uses BJT transistors
- BJTs require base current for operation
- This current flows even with no input signal

### IC 741 Values
- Typical: **80 nA**
- Maximum: **500 nA**

---

## 2️⃣ Input Offset Current (IOS)

### Definition
The **difference** between the two input bias currents.

$$\boxed{I_{OS} = |I_{B1} - I_{B2}|}$$

### Why Does It Exist?
- Input transistors can never be perfectly matched
- Manufacturing variations cause current imbalance

### Key Relationship
$$I_{OS} << I_B$$ (Offset current is much smaller than bias current)

### Effect on Output
Output offset voltage due to offset current:
$$\boxed{V_{oo}|_{I_{OS}} = I_{OS} \times R_f}$$

---

### Worked Example: Bias and Offset Current

**Problem:** An op-amp has $I_{B1} = 400$ nA and $I_{B2} = 300$ nA. Find $I_B$ and $I_{OS}$.

**Solution:**

$$I_B = \frac{I_{B1} + I_{B2}}{2} = \frac{400 + 300}{2} = \frac{700}{2} = \boxed{350 \text{ nA}}$$

$$I_{OS} = |I_{B1} - I_{B2}| = |400 - 300| = \boxed{100 \text{ nA}}$$

---

## 3️⃣ Input Offset Voltage (VOS)

### Definition
The small DC voltage that must be applied between inputs to make output exactly zero (when it should be zero).

$$\boxed{V_{OS} = V_+ - V_- \text{ (required to null output)}}$$

### Why Does It Exist?
- Mismatches in internal transistors
- Mismatches in active load resistors
- Process variations during manufacturing

### IC 741 Values
- Typical: **2 mV**
- Maximum: **6 mV**

### Effect on Output
$$\boxed{V_{oo}|_{V_{OS}} = V_{OS} \times \left(1 + \frac{R_f}{R_1}\right)}$$

---

## 4️⃣ Total Output Offset Voltage

The total offset at output is the combination of all offset effects:

### Without Compensation Resistor
$$\boxed{V_{OT} = \pm V_{OS}\left(1 + \frac{R_f}{R_1}\right) \pm I_B \cdot R_f}$$

### With Compensation Resistor ($R_{comp}$)
$$\boxed{V_{OT} = \pm V_{OS}\left(1 + \frac{R_f}{R_1}\right) \pm I_{OS} \cdot R_f}$$

---

## Bias Current Compensation

### The Problem
IB flowing through resistors creates unwanted voltage at inputs.

### The Solution
Add a **compensation resistor** at the non-inverting input:

$$\boxed{R_{comp} = R_1 \parallel R_f = \frac{R_1 \times R_f}{R_1 + R_f}}$$

### How It Works
```
          Rf
    ┌────/\/\/────┐
    │             │
Vi ─┤─/\/\/─┬─────┴──► Vout
    │  R1   │
    │       ◄─── Virtual Ground
    │    (-) ┌───┐
    │       ─┤   ├─ Op-Amp
    │    (+) └───┘
    │       │
    └──/\/\/┴─── GND
       Rcomp
```

The voltage drop across Rcomp cancels the offset at the inverting input!

---

### Worked Example: Total Output Offset

**Problem:** Given:
- $R_f = 10$ kΩ
- $R_1 = 2$ kΩ  
- $V_{OS} = 5$ mV (max)
- $I_{OS} = 50$ nA (max)
- $I_B = 200$ nA (max)

Find the maximum output offset voltage **with compensation**.

**Solution:**

With Rcomp, we use IOS instead of IB:

$$V_{OT} = V_{OS}\left(1 + \frac{R_f}{R_1}\right) + I_{OS} \times R_f$$

$$V_{OT} = 5 \text{ mV} \times \left(1 + \frac{10\text{k}}{2\text{k}}\right) + 50 \text{ nA} \times 10 \text{k Ω}$$

$$V_{OT} = 5 \text{ mV} \times 6 + 0.5 \text{ mV}$$

$$V_{OT} = 30 \text{ mV} + 0.5 \text{ mV} = \boxed{30.5 \text{ mV}}$$

---

## Thermal Drift

### What Is It?
Changes in $V_{OS}$, $I_B$, and $I_{OS}$ due to temperature changes.

### Drift Specifications
$$\boxed{\frac{\Delta V_{OS}}{\Delta T}} \quad \text{and} \quad \boxed{\frac{\Delta I_{OS}}{\Delta T}}$$

Units: μV/°C for voltage drift, nA/°C for current drift

### What Causes Drift?
1. Change in temperature (ΔT) - **most serious**
2. Change in supply voltage
3. Change in time (aging)

---

### Worked Example: Thermal Drift

**Problem:** An op-amp has:
- $\frac{\Delta V_{OS}}{\Delta T} = 6$ μV/°C
- $\frac{\Delta I_{OS}}{\Delta T} = 0.5$ nA/°C
- $R_f = 100$ kΩ, $R_1 = 10$ kΩ

Output is nulled at 25°C. If temperature rises to 75°C, find the output change.

**Solution:**

Temperature change: $\Delta T = 75 - 25 = 50°C$

**Change due to VOS drift:**
$$\Delta V_{OS} = 6 \text{ μV/°C} \times 50°C = 300 \text{ μV} = 0.3 \text{ mV}$$

$$\Delta V_{out}|_{V_{OS}} = \Delta V_{OS} \times \left(1 + \frac{R_f}{R_1}\right) = 0.3 \text{ mV} \times 11 = \boxed{3.3 \text{ mV}}$$

**Change due to IOS drift:**
$$\Delta I_{OS} = 0.5 \text{ nA/°C} \times 50°C = 25 \text{ nA}$$

$$\Delta V_{out}|_{I_{OS}} = \Delta I_{OS} \times R_f = 25 \text{ nA} \times 100 \text{k Ω} = \boxed{2.5 \text{ mV}}$$

---

## Summary of DC Formulas

| Parameter | Formula | Units |
|-----------|---------|-------|
| Input Bias Current | $I_B = \frac{I_{B1} + I_{B2}}{2}$ | nA |
| Input Offset Current | $I_{OS} = \|I_{B1} - I_{B2}\|$ | nA |
| Compensation Resistor | $R_{comp} = R_1 \parallel R_f$ | Ω |
| Output Offset (VOS only) | $V_{oo} = V_{OS}(1 + \frac{R_f}{R_1})$ | mV |
| Output Offset (IOS only) | $V_{oo} = I_{OS} \times R_f$ | mV |
| Total Offset (with Rcomp) | $V_{OT} = V_{OS}(1 + \frac{R_f}{R_1}) + I_{OS} R_f$ | mV |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Confusing IB and IOS | IB = average, IOS = difference |
| Forgetting Rcomp | Parallel combination of R1 and Rf |
| Not using || for parallel | $R_{comp} = \frac{R_1 R_f}{R_1 + R_f}$, not R1 + Rf |
| Ignoring thermal drift | Always consider in precision applications |
| Using IB instead of IOS with Rcomp | With compensation, IOS becomes dominant |

---

## Quick Reference Table: IC 741 DC Specs

| Parameter | Symbol | Typical | Maximum |
|-----------|--------|---------|---------|
| Input Offset Voltage | $V_{OS}$ | 2 mV | 6 mV |
| Input Bias Current | $I_B$ | 80 nA | 500 nA |
| Input Offset Current | $I_{OS}$ | 20 nA | 200 nA |

---

*Next: [04_ac_characteristics.md](04_ac_characteristics.md) - AC Characteristics & Frequency Response →*
