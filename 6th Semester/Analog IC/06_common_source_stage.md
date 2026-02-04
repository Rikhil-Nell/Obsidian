# 🎛️ Common-Source Stage

> **Chapter 3.3**: All CS amplifier configurations

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Calculate voltage gain for all CS configurations
- Compare different load types and their trade-offs
- Analyze output swing and linearity
- Design CS stages for specific gain requirements

---

## 1️⃣ CS Stage Overview

### The Basic Idea

A common-source amplifier:
- **Input**: Gate (Vgs)
- **Output**: Drain (Vout)  
- **Source**: Common to input and output grounds

### Why Common-Source?

| Configuration | Gain | Input Impedance | Use Case |
|---------------|------|-----------------|----------|
| Common-Source | High (-gmRD) | Very High | Voltage amplification |
| Common-Drain | ~1 | Very High | Buffers |
| Common-Gate | Moderate | Low (1/gm) | High frequency |

---

## 2️⃣ CS with Resistive Load

### Circuit

```
        VDD
         │
         RD
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤     ├─ 
      │  M1 │
      └──┬──┘
         │
        GND
```

### Small-Signal Analysis

$$\boxed{A_v = \frac{v_{out}}{v_{in}} = -g_m R_D}$$

### With rO Included

$$\boxed{A_v = -g_m (R_D \| r_O)}$$

### Key Insights

- **Negative gain** = Output inverts input
- **Higher gm** = Higher gain
- **Higher RD** = Higher gain BUT reduced swing and speed

### Worked Example

**Given**: gm = 2 mS, RD = 5 kΩ, rO = 50 kΩ

$$A_v = -g_m(R_D \| r_O) = -2 \times (5 \| 50) = -2 \times 4.55$$
$$A_v = -9.1$$

---

## 3️⃣ Large-Signal Analysis

### Transfer Characteristic

```
    Vout
     │
VDD ─┼─────────────────────
     │ ╲
     │  ╲← M1 in triode
     │   ╲
     │    ╲───────────── M1 in saturation
     │     slope = -gmRD
     │
     └──────────────────────→ Vin
           VTH    high
```

### Output Swing Limits

| Limit | Condition | Vout Value |
|-------|-----------|------------|
| Maximum | M1 in cutoff | VDD |
| Minimum | M1 in deep triode | VDD - IDRD → ~0 |

**Usable swing for linear operation**:
$$V_{out,min} = V_{GS} - V_{TH}$$ (M1 at edge of saturation)

---

## 4️⃣ CS with Diode-Connected Load

### Why Diode-Connected?

- Replaces resistor with a transistor
- Self-biasing
- Better trade-off between gain and swing in some cases

### NMOS Load

```
        VDD
         │
      ┌──┴──┐
      │  M2 │← Gate tied to drain
      └──┬──┘
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤ M1  │
      └──┬──┘
         │
        GND
```

### Diode-Connected Impedance

For M2 (diode-connected):
$$\boxed{Z_{M2} = \frac{1}{g_{m2} + g_{mb2}} \approx \frac{1}{g_{m2}(1 + \eta)}}$$

### Voltage Gain

$$A_v = -g_{m1} \cdot \frac{1}{g_{m2}(1+\eta)}$$

$$\boxed{A_v = -\frac{g_{m1}}{g_{m2}(1 + \eta)}}$$

### In Terms of W/L Ratios

Since gm ∝ √(W/L·ID) and ID is the same for both:

$$\boxed{A_v = -\sqrt{\frac{(W/L)_1}{(W/L)_2}} \cdot \frac{1}{1 + \eta}}$$

### PMOS Load Variation

```
        VDD
         │
      ┌──┴──┐
      │  M2 │← PMOS, gate to drain
      └──┬──┘
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤ M1  │← NMOS
      └──┬──┘
         │
        GND
```

No body effect on M2 (source at VDD = bulk for PMOS in n-well):

$$\boxed{A_v = -\frac{g_{m1}}{g_{m2}} = -\sqrt{\frac{\mu_n(W/L)_1}{\mu_p(W/L)_2}}}$$

---

## 5️⃣ CS with Current-Source Load

### The High-Gain Configuration

```
        VDD
         │
      ┌──┴──┐
 Vb ──┤ M2  │← Current source (saturation)
      └──┬──┘
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤ M1  │
      └──┬──┘
         │
        GND
```

### Why Current Source?

M2 in saturation provides very high output impedance (rO2).

### Voltage Gain

$$\boxed{A_v = -g_{m1}(r_{O1} \| r_{O2})}$$

### Intrinsic Gain

The maximum possible gain with current source load:

$$A_v = -g_m r_O = -\frac{2}{\lambda(V_{GS} - V_{TH})}$$

For practical values:
- λ ≈ 0.1 V⁻¹, VOV ≈ 0.2 V
- Av ≈ -100 (typical 10-100 range)

### Trade-off: gm vs rO

| More Overdrive | gm | rO | gmrO |
|----------------|----|----|------|
| Higher VGS-VTH | ↑ | ↓ (ID ↑) | May ↓ |
| Lower VGS-VTH | ↓ | ↑ | May ↑ |

The product gmrO often has an optimum!

---

## 6️⃣ CS with Active Load (CMOS Inverter)

### Circuit

```
        VDD
         │
      ┌──┴──┐
 Vin ─┤ M2  │← PMOS
      └──┬──┘
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤ M1  │← NMOS
      └──┬──┘
         │
        GND
```

### Voltage Gain

Both transistors contribute gm:

$$\boxed{A_v = -(g_{m1} + g_{m2})(r_{O1} \| r_{O2})}$$

### Key Features

- **Highest gain** of all CS configurations
- **Rail-to-rail output swing** possible
- Both devices ON provides high gm
- Limited by combined output resistance

---

## 7️⃣ CS with Triode Load

### Circuit

```
        VDD
         │
      ┌──┴──┐
 Vb ──┤ M2  │← Biased in triode (resistor-like)
      └──┬──┘
         │
         ├──── Vout
         │
      ┌──┴──┐
 Vin ─┤ M1  │
      └──┬──┘
         │
        GND
```

### Characteristics

M2 acts as a resistor:
$$R_{M2} = \frac{1}{\mu_p C_{ox}(W/L)_2(|V_{GS2}| - |V_{TH2}|)}$$

### Gain

$$A_v = -g_{m1} \cdot R_{M2}$$

### Pros and Cons

| Pros | Cons |
|------|------|
| Adjustable resistance via Vb | Non-linear resistance |
| No DC voltage drop like RD | Lower resistance = more W/L |
| Integrable on chip | Body effect if applicable |

---

## 8️⃣ Comparison Table

| Configuration | Gain Formula | Typical Av | Swing | Complexity |
|---------------|--------------|------------|-------|------------|
| Resistive Load | -gmRD | 5-20 | Limited | Low |
| Diode NMOS | -√((W/L)₁/(W/L)₂)/(1+η) | 3-10 | Good | Medium |
| Diode PMOS | -√(μn(W/L)₁/μp(W/L)₂) | 3-10 | Good | Medium |
| Current Source | -gm(rO1\|\|rO2) | 10-100 | Good | Medium |
| Active (Inverter) | -(gm1+gm2)(rO1\|\|rO2) | 20-200 | Best | High |
| Triode Load | -gmRon2 | 5-20 | Good | Low |

---

## 🔢 Worked Example: CS with Current Source Load

**Problem**: Find Av for NMOS M1 with PMOS current source M2:
- M1: gm1 = 1 mS, λ1 = 0.1 V⁻¹, ID = 0.5 mA
- M2: λ2 = 0.2 V⁻¹, ID = 0.5 mA (same current)

**Solution**:

**Step 1**: Calculate rO for each device
$$r_{O1} = \frac{1}{\lambda_1 I_D} = \frac{1}{0.1 \times 0.5 \times 10^{-3}} = 20 \text{ kΩ}$$
$$r_{O2} = \frac{1}{\lambda_2 I_D} = \frac{1}{0.2 \times 0.5 \times 10^{-3}} = 10 \text{ kΩ}$$

**Step 2**: Calculate parallel resistance
$$r_{O1} \| r_{O2} = \frac{20 \times 10}{20 + 10} = 6.67 \text{ kΩ}$$

**Step 3**: Calculate gain
$$A_v = -g_{m1}(r_{O1} \| r_{O2}) = -1 \times 6.67 = -6.67$$

**Answer**: Av = -6.67 (or |Av| = 6.67)

---

## 🔗 Concept Links

- **Previous**: [Device Models](./05_device_models.md)
- **Next**: [Worked Problems](./07_worked_problems.md)
- **Formulas**: [Formula Sheet - CS Stage](./08_formula_sheet_ultimate.md#common-source-stage-gain)
- **Prerequisites**: [Transconductance](./03_transconductance.md), [Second-Order Effects](./04_second_order_effects.md)

---

## ✅ Self-Check Questions

1. Why is the gain of CS stage negative?
2. In CS with diode load, how does (W/L)₂ affect gain?
3. What limits the gain in CS with current source load?
4. Why does PMOS diode load avoid body effect?
5. Which CS configuration gives highest gain?

<details>
<summary>Click for Answers</summary>

1. Because increased Vin → increased ID → decreased Vout (Vout = VDD - IDRD or equivalent)
2. Higher (W/L)₂ → higher gm2 → lower gain
3. The finite rO of both devices
4. Because source is at VDD = bulk (in n-well process), so VSB = 0
5. Active load (CMOS inverter) configuration

</details>
