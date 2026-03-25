# 08 - Inverting Amplifier 📉 (EXAM CRITICAL)

## Overview

The **Inverting Amplifier** is the most commonly used op-amp configuration. The input signal is applied to the inverting terminal, producing an output that is **180° out of phase** with the input.

> **Analogy:** Like a mirror that not only reflects but also magnifies (or reduces). The image is flipped (inverted) and scaled.

---

## Ideal Inverting Amplifier

### Circuit Diagram

```
          Rf
    ┌────/\/\/────┐
    │             │
Vi ─┤─/\/\/─┬─────┴──► Vo
    │  R1   │
    │      (●) Virtual Ground (≈0V)
    │    (-)│ 
    │       ├──┐
    └──►(+)─┘  │ Op-Amp
        │      │
       GND     └──►
```

### Key Analysis Points

1. **Virtual Ground:** Due to negative feedback, $V_- = V_+ = 0V$
2. **No current into op-amp:** Ideal op-amp has infinite input impedance
3. **KCL at inverting node:** Current through R1 = Current through Rf

---

## Derivation of Gain

### Step 1: Apply KCL at inverting node

$$I_1 = I_f$$

### Step 2: Express currents

$$\frac{V_i - V_-}{R_1} = \frac{V_- - V_o}{R_f}$$

### Step 3: Apply Virtual Ground ($V_- = 0$)

$$\frac{V_i - 0}{R_1} = \frac{0 - V_o}{R_f}$$

$$\frac{V_i}{R_1} = -\frac{V_o}{R_f}$$

### Step 4: Solve for gain

$$\boxed{A_v = \frac{V_o}{V_i} = -\frac{R_f}{R_1}}$$

---

## Understanding the Negative Sign

| Input Vi | Output Vo | Interpretation |
|----------|-----------|----------------|
| Positive | Negative | 180° phase shift |
| Negative | Positive | Inversion |
| AC signal | Inverted AC | Phase reversal |

The **negative sign** indicates **phase inversion**, NOT attenuation!

---

### Worked Example 1: Basic Gain Calculation

**Problem:** For an inverting amplifier, $R_f = 10$ kΩ and $R_1 = 1$ kΩ. Find the closed-loop voltage gain.

**Solution:**

$$A_v = -\frac{R_f}{R_1} = -\frac{10\text{k}}{1\text{k}} = \boxed{-10}$$

The amplifier provides a gain of 10 with phase inversion.

If $V_i = 0.5V$: $V_o = -10 \times 0.5V = -5V$

---

## Input and Output Impedances (Ideal)

### Input Impedance

$$\boxed{Z_{in} = R_1}$$

Because the inverting input is at virtual ground, the input "sees" only R1.

### Output Impedance

$$\boxed{Z_{out} \approx 0}$$

Due to negative feedback, output impedance is very low.

---

## Practical Inverting Amplifier

Real op-amps have:
- Finite open-loop gain ($A_{OL} = A$)
- Non-zero output impedance ($R_o$)
- Finite input impedance ($R_i$)

### Practical Circuit Model

```
            Rf
     ┌────/\/\/────────────────────┐
     │                             │
Vi ──┼──/\/\/──┬───────────┬───────┴──► Vo
     │   R1    │           │
     │         │          ─┴─ Ro
     │        Ri          ─┬─
     │         │           │
     │      (-)│    ┌──────┴───┐
     │         ├────┤  A·Vid   ├───
     │      (+)│    └──────┬───┘
     └────►────┘           │
          │                │
         GND              GND
```

---

## Practical Gain Formula

For a practical op-amp with finite gain A:

$$\boxed{A_{v(practical)} = -\frac{R_f/R_1}{1 + \frac{1}{A}\left(1 + \frac{R_f}{R_1} + \frac{R_f}{R_i}\right)}}$$

When $A \rightarrow \infty$ and $R_i \rightarrow \infty$, this reduces to the ideal formula.

### Simplified (for large A)

$$A_v \approx -\frac{R_f}{R_1} \cdot \frac{1}{1 + (1 + R_f/R_1)/A}$$

---

## Practical Input Resistance

$$\boxed{R_{in} = R_1 + \frac{R_f + R_o}{1 + A}}$$

For large A and small Ro:
$$R_{in} \approx R_1$$

---

## Practical Output Resistance

$$\boxed{R_{out} = \frac{R_o}{1 + A\beta}}$$

Where $\beta = \frac{R_1}{R_1 + R_f}$

For large $A\beta$:
$$R_{out} \approx \frac{R_o (R_1 + R_f)}{A \cdot R_1}$$

---

### Worked Example 2: Variable Gain

**Problem:** A variable resistance varies from 0 to 100 kΩ as Rf. With R1 = 10 kΩ, find minimum and maximum gain.

**Solution:**

When $R_f = 0$:
$$A_v = -\frac{0}{10\text{k}} = \boxed{0}$$ (unity follower behavior)

When $R_f = 100$ kΩ:
$$A_v = -\frac{100\text{k}}{10\text{k}} = \boxed{-10}$$

Gain range: **0 to -10**

---

### Worked Example 3: Output with DC Input

**Problem:** Inverting amplifier with R1 = 20 kΩ, Rf = 1 MΩ. Input = -40 mV. Find output.

**Solution:**

$$A_v = -\frac{R_f}{R_1} = -\frac{1000\text{k}}{20\text{k}} = -50$$

$$V_o = A_v \times V_i = (-50) \times (-40 \text{ mV}) = \boxed{+2V}$$

---

### Worked Example 4: Cascaded Amplifiers

**Problem:** Find V2 and V3 for Vin = 0.2V in cascaded inverting amplifiers.

```
Vin ──[Inv Amp 1]──► V2 ──[Inv Amp 2]──► V3
      Gain = -10          Gain = -5
```

**Solution:**

Stage 1: $V_2 = -10 \times 0.2V = -2V$

Stage 2: $V_3 = -5 \times V_2 = -5 \times (-2V) = \boxed{+10V}$

---

### Worked Example 5: Max Frequency for SR

**Problem:** Inverting amplifier, gain = -50 (1000kΩ/20kΩ), Input = -40 mV peak-to-peak, SR = 1 V/μs. Find max undistorted frequency.

**Solution:**

Output amplitude = $|A_v| \times V_{in(p-p)} = 50 \times 40 \text{ mV} = 2V_{pp}$

Peak voltage = $V_m = \frac{2V}{2} = 1V$

$$f_{max} = \frac{SR}{2\pi V_m} = \frac{1 \times 10^6}{2\pi \times 1} = \frac{10^6}{6.28} = \boxed{159 \text{ kHz}}$$

---

## Design Considerations

### Choosing R1 and Rf

| Consideration | Guideline |
|---------------|-----------|
| Gain | $\|A_v\| = R_f / R_1$ |
| Input Impedance | $Z_{in} = R_1$ (make large if needed) |
| Bias Current Effects | Use $R_1 \parallel R_f$ as Rcomp |
| Noise | Lower resistors = lower thermal noise |
| Typical Range | 1 kΩ to 1 MΩ |

### Adding Bias Compensation

$$\boxed{R_{comp} = R_1 \parallel R_f = \frac{R_1 \times R_f}{R_1 + R_f}}$$

Place at non-inverting input to ground.

---

## Key Formulas Summary

| Parameter | Formula |
|-----------|---------|
| **Ideal Gain** | $A_v = -\frac{R_f}{R_1}$ |
| **Input Impedance** | $Z_{in} = R_1$ |
| **Output Impedance** | $Z_{out} \approx 0$ (ideal) |
| **Compensation Resistor** | $R_{comp} = R_1 \parallel R_f$ |
| **Bandwidth** | $BW = \frac{GBP}{\|A_v\|}$ |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Forgetting negative sign | Output is INVERTED! |
| Thinking Zin = op-amp Zin | Zin = R1, not the internal 2MΩ |
| Using wrong Rf/R1 ratio | Double-check which is numerator |
| Ignoring virtual ground | Essential for derivation |
| Expecting gain < 1 only | Gain magnitude can be any value |

---

*Next: [09_noninverting_amplifier.md](09_noninverting_amplifier.md) - Non-Inverting Amplifier →*
