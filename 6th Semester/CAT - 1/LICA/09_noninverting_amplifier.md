# 09 - Non-Inverting Amplifier 📈 (EXAM CRITICAL)

## Overview

The **Non-Inverting Amplifier** applies input to the non-inverting terminal, producing an output that is **in-phase** with the input. The gain is always ≥ 1.

> **Analogy:** Like a zoom lens that only magnifies but doesn't flip the image. What you see is what you get, just bigger.

---

## Ideal Non-Inverting Amplifier

### Circuit Diagram

```
                Rf
         ┌────/\/\/────┐
         │             │
    ┌────┴─────────────┴──► Vo
    │
   (-)
    ├───┐
   (+)  │ Op-Amp
    │   │
Vi ─┘   └──►
    │
   ─┴─
    ─  R1
    │
   GND
```

Alternative simplified view:
```
Vi ──►(+)
          ├──────────► Vo
      (-)─┤
          │
     ┌────┴────┐
     │ R1/(R1+Rf)·Vo │ ◄── Voltage divider feedback
     └─────────┘
```

### Key Points

1. **Input at non-inverting terminal**
2. **Feedback through voltage divider** (R1 and Rf)
3. **Virtual short:** $V_- = V_+ = V_i$
4. **No phase inversion**

---

## Derivation of Gain

### Step 1: Virtual Short Principle

$$V_- = V_+ = V_i$$

### Step 2: Voltage Divider at Output

The voltage at the inverting input is:
$$V_- = V_o \times \frac{R_1}{R_1 + R_f}$$

### Step 3: Equate

$$V_i = V_o \times \frac{R_1}{R_1 + R_f}$$

### Step 4: Solve for Gain

$$\frac{V_o}{V_i} = \frac{R_1 + R_f}{R_1} = 1 + \frac{R_f}{R_1}$$

$$\boxed{A_v = 1 + \frac{R_f}{R_1}}$$

---

## Understanding the Gain

| Condition | Gain |
|-----------|------|
| $R_f = 0$ | $A_v = 1$ (voltage follower) |
| $R_f = R_1$ | $A_v = 2$ |
| $R_f = 9 \times R_1$ | $A_v = 10$ |
| $R_f = \infty$ | $A_v = \infty$ (open-loop) |

**Gain is ALWAYS ≥ 1** (cannot attenuate)
**Gain is ALWAYS POSITIVE** (no inversion)

---

## Comparison: Inverting vs Non-Inverting

| Feature | Inverting | Non-Inverting |
|---------|-----------|---------------|
| **Gain Formula** | $-R_f/R_1$ | $1 + R_f/R_1$ |
| **Minimum Gain** | Any (including < 1) | 1 |
| **Phase** | 180° inversion | 0° (in-phase) |
| **Input Impedance** | $R_1$ (moderate) | Very high (≈ op-amp $Z_{in}$) |
| **Virtual Ground** | Yes (at inverting input) | No |

---

### Worked Example 1: Basic Gain

**Problem:** For a non-inverting amplifier with $R_1 = 10$ kΩ and $R_f = 90$ kΩ, find the voltage gain.

**Solution:**

$$A_v = 1 + \frac{R_f}{R_1} = 1 + \frac{90\text{k}}{10\text{k}} = 1 + 9 = \boxed{10}$$

---

### Worked Example 2: Variable Gain Range

**Problem:** Variable resistance $R_f$ varies from 0 to 100 kΩ. $R_1 = 10$ kΩ. Find min and max gain.

**Solution:**

Minimum ($R_f = 0$):
$$A_{v(min)} = 1 + \frac{0}{10\text{k}} = \boxed{1}$$

Maximum ($R_f = 100$ kΩ):
$$A_{v(max)} = 1 + \frac{100\text{k}}{10\text{k}} = 1 + 10 = \boxed{11}$$

---

## The Voltage Follower (Buffer)

A special case where $R_f = 0$ and $R_1 = \infty$ (or just direct connection):

```
Vi ──►(+)
          ├──► Vo = Vi
      (-)─┘
```

### Properties

$$\boxed{A_v = 1}$$

- **Unity gain** (exactly 1)
- **Extremely high input impedance**
- **Very low output impedance**
- Used as a **buffer** between stages

### Applications

| Use Case | Why |
|----------|-----|
| Impedance matching | High input Z → Low output Z |
| Sensor interfacing | Doesn't load the sensor |
| Between stages | Isolates circuit sections |

---

## Input and Output Impedances

### Input Impedance

$$\boxed{Z_{in} = R_i(1 + A_{OL}\beta)}$$

Where $\beta = \frac{R_1}{R_1 + R_f}$

For practical purposes:
$$Z_{in} \approx \text{Very High (several MΩ)}$$

### Output Impedance

$$\boxed{Z_{out} = \frac{R_o}{1 + A_{OL}\beta}}$$

For practical purposes:
$$Z_{out} \approx \text{Very Low (< 1Ω)}$$

---

## Practical Non-Inverting Amplifier

### Analysis Method

Using KCL at input and output nodes:

**At non-inverting input:**
$$V_+ = V_i$$

**At inverting input (via feedback):**
$$V_- = V_o \times \frac{R_1}{R_1 + R_f}$$

**Virtual short:**
$$V_i = V_o \times \frac{R_1}{R_1 + R_f}$$

### Practical Gain (with finite A)

$$A_{v(practical)} = \frac{1 + R_f/R_1}{1 + \frac{1 + R_f/R_1}{A_{OL}}}$$

For large $A_{OL}$, this approaches the ideal gain.

---

### Worked Example 3: Output Voltage

**Problem:** Non-inverting amplifier with $R_1 = 2$ kΩ, $R_f = 8$ kΩ. Input = 1V. Supply = ±15V. Find output.

**Solution:**

$$A_v = 1 + \frac{8\text{k}}{2\text{k}} = 1 + 4 = 5$$

$$V_o = A_v \times V_i = 5 \times 1V = \boxed{5V}$$

(Within supply limits of ±13V, so no clipping)

---

### Worked Example 4: Finding R Values

**Problem:** Design a non-inverting amplifier with gain = 21. Use $R_1 = 1$ kΩ.

**Solution:**

$$A_v = 1 + \frac{R_f}{R_1}$$

$$21 = 1 + \frac{R_f}{1\text{k}}$$

$$\frac{R_f}{1\text{k}} = 20$$

$$R_f = \boxed{20 \text{ kΩ}}$$

---

## The Feedback Factor (β)

$$\boxed{\beta = \frac{R_1}{R_1 + R_f}}$$

This represents the fraction of output fed back to the input.

| β Value | Meaning |
|---------|---------|
| β = 1 | All output fed back (voltage follower) |
| β = 0.5 | Half fed back (gain = 2) |
| β = 0.1 | 10% fed back (gain = 10) |
| β → 0 | Open loop (gain → ∞) |

---

## Key Formulas Summary

| Parameter | Formula |
|-----------|---------|
| **Voltage Gain** | $A_v = 1 + \frac{R_f}{R_1}$ |
| **Feedback Factor** | $\beta = \frac{R_1}{R_1 + R_f}$ |
| **Gain in terms of β** | $A_v = \frac{1}{\beta}$ |
| **Input Impedance** | $Z_{in} \approx$ Very High |
| **Output Impedance** | $Z_{out} \approx$ Very Low |
| **Voltage Follower Gain** | $A_v = 1$ |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Forgetting the "+1" | Gain = 1 + Rf/R1, NOT just Rf/R1 |
| Thinking gain can be < 1 | Minimum gain is always 1 (use inverting for attenuation) |
| Expecting phase inversion | Non-inverting = in-phase output |
| Wrong input impedance | Zin is very high, NOT equal to R1 |
| Confusing with inverting | Non-inv input goes to (+), not (-) |

---

## Quick Comparison Reference

| Parameter | Inverting $(-\frac{R_f}{R_1})$ | Non-Inverting $(1+\frac{R_f}{R_1})$ |
|-----------|-------------------------------|-------------------------------------|
| Rf = R1 | -1 | 2 |
| Rf = 9×R1 | -9 | 10 |
| Rf = 0 | 0 | 1 |
| Phase | Inverted | Same |

---

*Next: [10_opamp_applications.md](10_opamp_applications.md) - Op-Amp Applications (Summing, Integrator, etc.) →*
