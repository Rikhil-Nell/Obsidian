# 10 - Op-Amp Applications 🛠️

## Overview

This section covers practical op-amp circuits that perform useful functions:
1. Voltage Follower (Buffer)
2. Summing Amplifier (Adder)
3. Subtractor (Difference Amplifier)
4. Integrator
5. Differentiator

---

## 1️⃣ Voltage Follower (Buffer)

### Circuit
```
Vi ──►(+)
          ├──► Vo = Vi
      (-)─┘
```

### Characteristics
$$\boxed{A_v = 1}$$
$$\boxed{V_o = V_i}$$

| Property | Value |
|----------|-------|
| Gain | 1 (Unity) |
| Phase | 0° (In-phase) |
| Input Z | Very High |
| Output Z | Very Low |

### Applications
- **Impedance matching** between stages
- **Buffer** high-impedance sensors
- **Isolation** between circuits

---

## 2️⃣ Summing Amplifier (Adder)

### Circuit (Inverting Summer)
```
V1 ──/\/\/─┐ R1
           │
V2 ──/\/\/─┤ R2    Rf
           ├──/\/\/─┬──► Vo
V3 ──/\/\/─┤ R3     │
           │       (-)
          (●)       ├──
         Virtual   (+)
         Ground    │
                  GND
```

### Output Equation

$$\boxed{V_o = -R_f\left(\frac{V_1}{R_1} + \frac{V_2}{R_2} + \frac{V_3}{R_3}\right)}$$

### Special Case: Equal Resistors

If $R_1 = R_2 = R_3 = R$:

$$\boxed{V_o = -\frac{R_f}{R}(V_1 + V_2 + V_3)}$$

If additionally $R_f = R$:

$$\boxed{V_o = -(V_1 + V_2 + V_3)}$$

### Applications
- **Audio mixing** (combine multiple audio signals)
- **DAC (Digital-to-Analog Converter)** using weighted resistors
- **Signal combining**

---

### Worked Example: Summing Amplifier

**Problem:** Calculate output for $V_1 = 1V$, $V_2 = 2V$, $V_3 = 3V$ with $R_1 = R_2 = R_3 = 10$ kΩ and $R_f = 10$ kΩ.

**Solution:**

$$V_o = -\frac{R_f}{R}(V_1 + V_2 + V_3) = -\frac{10\text{k}}{10\text{k}}(1 + 2 + 3)$$

$$V_o = -1 \times 6 = \boxed{-6V}$$

---

## 3️⃣ Subtractor (Difference Amplifier)

### Circuit
```
         R2
V1 ──/\/\/──┬───(-)
            │       ├──► Vo
            Rf      │
         ┌──/\/\/──┘
         │
V2 ──/\/\/──┬───(+)
         R1 │
            │
         ───┴─── GND (via R3)
            R3
```

### For Equal Resistors ($R_1 = R_2 = R_3 = R_f$):

$$\boxed{V_o = V_2 - V_1}$$

### General Formula:

$$\boxed{V_o = \frac{R_f}{R_1}(V_2 - V_1)}$$

(When configured as unity-gain subtractor with matched resistors)

### Derivation Using Superposition

**Due to V2 alone (V1 = 0):**
- Non-inverting input sees V2 through voltage divider
- Acts as non-inverting amplifier

**Due to V1 alone (V2 = 0):**
- Acts as inverting amplifier
- Output due to V1 = $-\frac{R_f}{R_1} \times V_1$

**Total Output:**
$$V_o = V_2 - V_1 \text{ (for unity gain subtractor)}$$

### Applications
- **Differential signal measurement**
- **Noise rejection** (common-mode signals cancel)
- **Instrumentation amplifiers**

---

## 4️⃣ Integrator

### What Does It Do?

An integrator produces an output proportional to the **integral** (area under the curve) of the input signal over time.

> **Analogy:** Like filling a bucket with water. The output tells you the total amount of water, not the flow rate.

### Circuit
```
          Cf
    ┌─────┤├─────┐
    │             │
Vi ─┤─/\/\/─┬─────┴──► Vo
    │  R1   │
    │      (●) Virtual Ground
    │    (-)│
    │       ├──
    └──►(+)─┘
        │
       GND
```

### Output Equation

$$\boxed{V_o(t) = -\frac{1}{R_1 C_f}\int V_i(t) \, dt}$$

In frequency domain:
$$\boxed{\frac{V_o}{V_i} = -\frac{1}{j\omega R_1 C_f}}$$

### Magnitude Response

$$|A| = \frac{1}{\omega R_1 C_f} = \frac{1}{2\pi f R_1 C_f}$$

- **At low frequencies:** Gain is HIGH (problematic!)
- **At high frequencies:** Gain decreases at -20 dB/decade

### Practical Integrator

**Problem with ideal integrator:**
- DC gain → ∞ (output saturates)
- Input offset causes drift

**Solution:** Add resistor $R_f$ in parallel with $C_f$

```
          Cf
    ┌─────┤├─────┐
    │   ──/\/\/──│ Rf (parallel)
    │             │
Vi ─┤─/\/\/─┬─────┴──► Vo
    │  R1   │
```

This limits DC gain to $-R_f/R_1$

### Example: Integrating a Square Wave

Input: Square wave → Output: Triangle wave

```
Input:          Output (Ideal):
 ┌──┐  ┌──┐         /\    /\
 │  │  │  │       /    \/    \
─┘  └──┘  └─    /              \
```

---

## 5️⃣ Differentiator

### What Does It Do?

A differentiator produces an output proportional to the **rate of change** of the input.

> **Analogy:** Like a speedometer showing how fast position changes, not the position itself.

### Circuit
```
          Rf
    ┌────/\/\/────┐
    │             │
Vi ─┤──┤├──┬──────┴──► Vo
    │  C1  │
    │     (●) Virtual Ground
    │   (-)│
    │      ├──
    └──►(+)┘
       │
      GND
```

### Output Equation

$$\boxed{V_o(t) = -R_f C_1 \frac{dV_i}{dt}}$$

In frequency domain:
$$\boxed{\frac{V_o}{V_i} = -j\omega R_f C_1}$$

### Magnitude Response

$$|A| = \omega R_f C_1 = 2\pi f R_f C_1$$

- **At low frequencies:** Gain is LOW
- **At high frequencies:** Gain INCREASES at +20 dB/decade

### Practical Consideration

**Problem:** High-frequency noise is amplified!

**Solution:** Add series resistor $R_1$ with input capacitor to limit high-frequency gain.

### Example: Differentiating a Triangle Wave

Input: Triangle wave → Output: Square wave

```
Input:          Output:
  /\    /\       ┌──┐  ┌──┐
/    \/    \     │  │  │  │
              ───┘  └──┘  └─
```

---

## Summary Table

| Circuit | Function | Output Equation | Key Formula |
|---------|----------|-----------------|-------------|
| **Summing** | Add signals | $-R_f(\Sigma V_n/R_n)$ | Weighted sum |
| **Subtractor** | Subtract signals | $V_2 - V_1$ | Difference |
| **Integrator** | Integrate | $-\frac{1}{RC}\int V_i \, dt$ | Area under curve |
| **Differentiator** | Differentiate | $-RC \frac{dV_i}{dt}$ | Rate of change |

---

## Input-Output Waveform Relationships

| Input | Integrator Output | Differentiator Output |
|-------|-------------------|----------------------|
| DC | Ramp (saturates) | Zero |
| Sine | -Cosine (90° lag) | +Cosine (90° lead) |
| Square | Triangle | Spikes at edges |
| Triangle | Parabola | Square |
| Ramp | Parabola | DC |

---

### Worked Example: Current Through Load

**Problem:** Op-amp circuit with 5V at non-inverting input (virtual ground at emitter). 1 kΩ resistor at emitter. Find load current.

**Solution:**

By virtual ground: Emitter voltage = 5V

Current through 1 kΩ:
$$I = \frac{V_E}{R} = \frac{5V}{1\text{k}} = \boxed{5 \text{ mA}}$$

This current equals the load current (emitter follower action).

---

### Worked Example: Output of Summing Amplifier

**Problem:** $V_1 = 2V$, $V_2 = -3V$, $V_3 = 4V$. All input resistors = 10 kΩ, $R_f = 20$ kΩ.

**Solution:**

$$V_o = -\frac{R_f}{R}(V_1 + V_2 + V_3) = -\frac{20\text{k}}{10\text{k}}(2 - 3 + 4)$$

$$V_o = -2 \times 3 = \boxed{-6V}$$

---

## Key Formulas Summary

| Application | Formula |
|-------------|---------|
| **Summer** | $V_o = -R_f(\frac{V_1}{R_1} + \frac{V_2}{R_2} + ...)$ |
| **Subtractor** | $V_o = V_2 - V_1$ (unity gain) |
| **Integrator** | $V_o = -\frac{1}{RC}\int V_i \, dt$ |
| **Differentiator** | $V_o = -RC\frac{dV_i}{dt}$ |
| **Integrator Transfer** | $H(j\omega) = -\frac{1}{j\omega RC}$ |
| **Differentiator Transfer** | $H(j\omega) = -j\omega RC$ |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Forgetting negative sign | All inverting configs have negative output |
| Ignoring DC with integrator | Practical integrator needs Rf parallel to Cf |
| High-frequency noise in differentiator | Add series R to limit gain |
| Wrong resistor ratios for subtractor | Must be matched for unity gain subtraction |

---

*Next: [11_internal_circuit.md](11_internal_circuit.md) - Internal Circuit of Op-Amp →*
