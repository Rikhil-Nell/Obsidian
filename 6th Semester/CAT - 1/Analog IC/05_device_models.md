# 📐 MOS Device Models

> **Chapter 2.4**: Layout, capacitances, small-signal model, and SPICE parameters

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Draw and explain MOS device layout
- Calculate all MOS capacitances
- Apply the complete small-signal model
- Understand SPICE Level-1 parameters

---

## 1️⃣ MOS Device Layout

### Bird's-Eye View

```
          Contact Windows
             ↓     ↓
    ┌────────┼─────┼────────┐
    │   n+   │     │   n+   │
    │ Source │Gate │ Drain  │
    │        │     │        │
    │   ●    │     │   ●    │  ← Metal contacts
    │   ●    │█████│   ●    │
    │        │█Poly│        │
    └────────┼─────┼────────┘
             │  W  │
             └──┬──┘
                L
```

### Key Layout Features

| Feature | Purpose |
|---------|---------|
| Poly Gate | Controls channel |
| Contact Windows | Connect to metal |
| n+ Source/Drain | High doping for low resistance |
| Metal Wires | Interconnects |

### Folded Structure

To reduce capacitance, use **folded** layout:

```
Standard:                    Folded:
┌────────────────────┐      ┌─────────┬─────────┐
│  S    Gate    D    │      │  S  Gate  D  Gate  S  │
│  (W)         (W)   │      │ (W/2)   (W/2)    (W/2)│
└────────────────────┘      └─────────┴─────────┘

CDB = W·E·Cj + 2(W+E)Cjsw   CDB = (W/2)·E·Cj + 2(W/2+E)Cjsw
```

> **Benefit**: Folding reduces drain capacitance significantly!

---

## 2️⃣ MOS Capacitances

### The Five Capacitances

```
        CGD
    ┌────┴────┐
    │    G    │
    │   ╱│╲   │
CGS─┤  ╱ │ ╲  ├─CGB
    │ ╱  │  ╲ │
    │    │    │
    S    │    D
    │    │    │
   CSB   │   CDB
    │    B    │
    └────┴────┘
```

### Capacitance Values by Region

| Region | CGS | CGD | CGB |
|--------|-----|-----|-----|
| Off | WCov | WCov | WLCox·Cd/(WLCox+Cd) |
| Deep Triode | WLCox/2 + WCov | WLCox/2 + WCov | ≈ 0 |
| Saturation | (2/3)WLCox + WCov | WCov | ≈ 0 |

### Component Capacitances

**Overlap Capacitance** (per unit width):
$$C_{GS,overlap} = C_{GD,overlap} = W \cdot C_{ov}$$

**Junction Capacitance**:
$$C_{DB} = C_{SB} = \underbrace{W \cdot E \cdot C_j}_{\text{bottom}} + \underbrace{2(W + E) \cdot C_{jsw}}_{\text{sidewall}}$$

**Voltage-Dependent Junction**:
$$C_j = \frac{C_{j0}}{(1 + V_R/\Phi_B)^m}$$

---

## 3️⃣ Small-Signal Model

### Basic Model (λ = γ = 0)

```
        G           D
        o───────────o
                    │
        ┌───────────┤
        │           │
       (+)          │
    Vgs │    ◄──────┤ gm·Vgs
       (-)          │
        │           │
        └───────────┤
                    │
        o───────────o
        S
```

### With Output Resistance (λ ≠ 0)

```
        G           D
        o───────────o
                    │
        ┌───────────┤
        │     ┌─────┤
       (+)    │     │
    Vgs │     │  rO │
       (-)    │     │
        │     │     │
        └─────┤─────┤
              │     │
              ◄─────┤ gm·Vgs
              │
        o─────┴─────o
        S
```

### Complete Model (with body effect)

```
        G               D
        o───────────────o
                        │
        ┌───────────────┤
        │         ┌─────┤
    Vgs(+)        │   rO│
       (-)        │     │
        │         │     │
        └─────────┤─────┤
                  │     │
         gmb·Vbs ─┼─────┤── gm·Vgs
                  │     │
          (+)     │     │
       Vbs │      │     │
          (-)     │     │
        B  o──────┴─────┤
                        │
        S  o────────────o
```

### Small-Signal Parameter Formulas

| Parameter | Formula |
|-----------|---------|
| gm | μnCox(W/L)(VGS - VTH) |
| rO | 1/(λID) |
| gmb | ηgm where η = γ/(2√(2ΦF + VSB)) |

### The η Factor

$$\eta = \frac{g_{mb}}{g_m} = \frac{\gamma}{2\sqrt{2\Phi_F + V_{SB}}}$$

Typically η ≈ 0.2 - 0.3

---

## 4️⃣ Impedance Looking into Source

### With λ = 0

$$\boxed{R_{in,source} = \frac{1}{g_m + g_{mb}} \approx \frac{1}{g_m(1 + \eta)}}$$

### With λ ≠ 0

$$R_{in,source} = \frac{1}{g_m + g_{mb}} \| r_O$$

---

## 5️⃣ Diode-Connected Device

When gate and drain are shorted:

```
        ┌───┬───┐
    Vx ─┤   │   ├─ Ix
        │   G   │
        │   │   D
        │   └───┤
        │       │
        S───────┘
```

### Impedance

$$\boxed{Z_{diode} = \frac{1}{g_m + g_{mb}} \| r_O \approx \frac{1}{g_m}}$$

---

## 6️⃣ SPICE Level-1 Parameters

| Parameter | NMOS | PMOS | Description | Units |
|-----------|------|------|-------------|-------|
| VTO | 0.7 | -0.8 | Threshold voltage | V |
| GAMMA | 0.45 | 0.4 | Body-effect coefficient | V^(1/2) |
| PHI | 0.9 | 0.8 | 2ΦF | V |
| UO | 350 | 100 | Mobility | cm²/V·s |
| LAMBDA | 0.1 | 0.2 | CLM coefficient | V⁻¹ |
| TOX | 9 nm | 9 nm | Oxide thickness | m |
| NSUB | 9×10¹⁴ | 5×10¹⁴ | Substrate doping | cm⁻³ |
| CJ | 0.56 mF/m² | 0.94 mF/m² | Junction capacitance | F/m² |
| CJSW | 0.35 pF/m | 0.32 pF/m | Sidewall capacitance | F/m |
| CGDO | 0.4 nF/m | 0.3 nF/m | Overlap capacitance | F/m |

### SPICE Level-1 Equations

**Cutoff** (VGS < VTH):
$$I_D = 0$$

**Triode** (VGS > VTH, VDS < VGS - VTH):
$$I_D = \mu_n C_{ox} \frac{W}{L}\left[(V_{GS} - V_{TH})V_{DS} - \frac{V_{DS}^2}{2}\right]$$

**Saturation** (VGS > VTH, VDS ≥ VGS - VTH):
$$I_D = \frac{1}{2}\mu_n C_{ox} \frac{W}{L}(V_{GS} - V_{TH})^2(1 + \lambda V_{DS})$$

---

## 7️⃣ NMOS vs PMOS Comparison

| Property | NMOS | PMOS | Ratio |
|----------|------|------|-------|
| μ (mobility) | ~350 | ~100 | 3.5:1 |
| Current drive | Higher | Lower | ~2:1 |
| gm (for same ID) | Higher | Lower | √2:1 |
| rO | Higher | Lower | ~1:1 |
| Speed | Faster | Slower | — |

> **Design Implication**: Use NMOS where possible for better performance!

---

## 🔢 Worked Example: Folded Device Capacitance

**Problem**: Calculate CDB for standard vs folded structure with:
- W = 10 μm, E = 3 μm
- Cj = 0.5 fF/μm², Cjsw = 0.2 fF/μm

**Standard Structure**:
$$C_{DB} = W \cdot E \cdot C_j + 2(W + E) \cdot C_{jsw}$$
$$C_{DB} = 10 \times 3 \times 0.5 + 2(10 + 3) \times 0.2$$
$$C_{DB} = 15 + 5.2 = 20.2 \text{ fF}$$

**Folded Structure (2 fingers)**:
$$C_{DB} = \frac{W}{2} \cdot E \cdot C_j + 2\left(\frac{W}{2} + E\right) \cdot C_{jsw}$$
$$C_{DB} = 5 \times 3 \times 0.5 + 2(5 + 3) \times 0.2$$
$$C_{DB} = 7.5 + 3.2 = 10.7 \text{ fF}$$

**Reduction**: 20.2 → 10.7 fF (**47% reduction!**)

---

## 🔗 Concept Links

- **Previous**: [Second-Order Effects](04_second_order_effects.md)
- **Next**: [Common-Source Stage](06_common_source_stage.md)
- **Formulas**: [Formula Sheet - Capacitances](08_formula_sheet_ultimate.md#mos-capacitances)
- **Problems**: [Q4, Q5 in Worked Problems](07_worked_problems.md)

---

## ✅ Self-Check Questions

1. In saturation, what is CGD approximately equal to?
2. Why does folding reduce drain capacitance?
3. What does gmb represent physically?
4. Why is η typically less than 1?
5. Which SPICE parameter represents the body effect?

<details>
<summary>Click for Answers</summary>

1. CGD ≈ WCov (overlap only, no channel contribution)
2. Reduces the bottom-plate area while maintaining same total W
3. The transconductance from bulk to drain (back-gate effect)
4. Because γ/(2√(2ΦF + VSB)) < 1 for typical values
5. GAMMA (γ)

</details>
