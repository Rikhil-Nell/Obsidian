# 📈 Transconductance

> **Chapter 2.2.3**: How well does a MOSFET convert voltage to current?

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Define transconductance and explain its importance
- Calculate gm using three different formulas
- Distinguish between process (kn) and device (βn) transconductance
- Choose the right gm formula based on given information

---

## 1️⃣ What is Transconductance?

### Definition

Transconductance measures how effectively a transistor converts a **voltage change** at the gate into a **current change** at the drain.

$$\boxed{g_m = \frac{\partial I_D}{\partial V_{GS}}\bigg|_{V_{DS} = const}}$$

### The Amplifier Analogy 🎚️

Think of gm as the "gain knob" of a transistor:
- High gm = sensitive; small voltage change → large current change
- Low gm = less sensitive
- Units: A/V or S (Siemens) or mA/V or 1/Ω

### Why It Matters

| Application | Role of gm |
|-------------|------------|
| Voltage gain | Av ∝ gm |
| Current drive | Higher gm = faster circuits |
| Noise | Input noise ∝ 1/gm |

---

## 2️⃣ The Three Forms of gm (CRITICAL!)

In saturation, gm can be expressed three ways. **Memorize all three!**

### Form 1: In terms of overdrive

$$\boxed{g_m = \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})}$$

**Use when**: You know VGS and VTH

**Insight**: gm increases linearly with overdrive (for fixed W/L)

---

### Form 2: In terms of drain current

$$\boxed{g_m = \sqrt{2\mu_n C_{ox} \frac{W}{L} I_D}}$$

**Use when**: You know ID and device dimensions

**Insight**: gm increases with √ID (for fixed dimensions)

---

### Form 3: In terms of ID and overdrive

$$\boxed{g_m = \frac{2I_D}{V_{GS} - V_{TH}}}$$

**Use when**: You know ID and overdrive but not μnCox

**Insight**: Trade-off between ID and overdrive visible

---

## 3️⃣ Process vs Device Transconductance

### Process Transconductance (kn or k'n)

$$\boxed{k_n = \mu_n C_{ox}}$$

| Parameter | Description | Typical NMOS | Typical PMOS |
|-----------|-------------|--------------|--------------|
| μn, μp | Carrier mobility | 350-550 cm²/V·s | 100-220 cm²/V·s |
| Cox | Oxide capacitance | depends on tox | same |
| kn, kp | Process transconductance | 100-200 μA/V² | 40-80 μA/V² |

**Key Point**: kn is a **technology parameter** - same for all transistors in a process!

### Device Transconductance (βn)

$$\boxed{\beta_n = \mu_n C_{ox} \frac{W}{L} = k_n \frac{W}{L}}$$

**Key Point**: βn varies with each transistor's **W/L ratio**

### Relationship

$$I_D = \frac{1}{2}\beta_n(V_{GS} - V_{TH})^2 = \frac{1}{2}k_n\frac{W}{L}(V_{GS} - V_{TH})^2$$

---

## 4️⃣ Calculating kn and βn from Given Data

### Example: Finding kn

**Given**: μn = 550 cm²/V·s, tox = 100 Å, ε₀ = 8.854×10⁻¹⁴ F/cm, εsio2 = 3.9

**Step 1**: Calculate Cox
$$C_{ox} = \frac{\varepsilon_0 \cdot \varepsilon_{sio2}}{t_{ox}} = \frac{8.854 \times 10^{-14} \times 3.9}{100 \times 10^{-8}}$$
$$C_{ox} = 3.45 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Calculate kn
$$k_n = \mu_n C_{ox} = 550 \times 3.45 \times 10^{-7}$$
$$k_n = 1.90 \times 10^{-4} \text{ A/V}^2 = 190 \text{ μA/V}^2$$

### Example: Finding βn

**Given**: kn = 110 μA/V², W = 10 μm, L = 0.35 μm

$$\beta_n = k_n \frac{W}{L} = 110 \times \frac{10}{0.35} = 3143 \text{ μA/V}^2$$

---

## 5️⃣ gm in Different Regions

| Region | gm Expression |
|--------|---------------|
| Saturation | μnCox(W/L)(VGS - VTH) |
| Triode | μnCox(W/L)VDS |
| Subthreshold | ID/(ξVT) where ξ ≈ 1.5 |

### Why gm Drops in Triode

In triode, gm = μnCox(W/L)VDS:
- As VDS decreases (device goes deeper into triode)
- gm decreases proportionally
- **This is why we use saturation for amplification!**

---

## 6️⃣ gm Dependencies - Visual Summary

```
         gm                          gm                         gm
          │                           │                          │
          │     ╱                     │         ╱                │   ╲
          │   ╱                       │       ╱                  │     ╲
          │ ╱                         │     ╱                    │       ╲
          │╱                          │   ╱                      │         ╲
          └─────────→                 └─────────→                └─────────→
             VGS - VTH                    ID                    VGS - VTH
                                                               (ID constant)
         W/L constant                W/L constant              
         gm ∝ (VGS-VTH)              gm ∝ √ID                 gm ∝ 1/(VGS-VTH)
```

---

## 7️⃣ PMOS Transconductance

Same expressions, different mobility:

$$g_m = \mu_p C_{ox} \frac{W}{L} |V_{GS} - V_{TH}|$$

$$g_m = \sqrt{2\mu_p C_{ox} \frac{W}{L} I_D}$$

$$g_m = \frac{2I_D}{|V_{GS} - V_{TH}|}$$

> **Note**: Since μp ≈ 0.5μn, PMOS has lower gm for same dimensions and bias.

---

## 8️⃣ Quick Reference: Which Formula to Use?

| Given Information | Use This Formula |
|-------------------|------------------|
| VGS, VTH, μnCox, W/L | gm = μnCox(W/L)(VGS - VTH) |
| ID, μnCox, W/L | gm = √(2μnCoxID(W/L)) |
| ID, VGS, VTH | gm = 2ID/(VGS - VTH) |
| kn, W/L, overdrive | gm = kn(W/L)(VGS - VTH) |
| βn, overdrive | gm = βn(VGS - VTH) |

---

## 🔢 Worked Example

**Problem**: Find gm for NMOS with:
- μn = 560 cm²/V·s
- tox = 90 Å  
- VG = 2.5V, VTH = 0.65V
- W = 2 μm, L = 0.25 μm

**Solution**:

**Step 1**: Calculate Cox
$$C_{ox} = \frac{8.854 \times 10^{-14} \times 3.9}{90 \times 10^{-8}} = 3.84 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Calculate kn
$$k_n = 560 \times 3.84 \times 10^{-7} = 2.15 \times 10^{-4} \text{ A/V}^2 = 215 \text{ μA/V}^2$$

**Step 3**: Calculate βn
$$\beta_n = 215 \times \frac{2}{0.25} = 1720 \text{ μA/V}^2$$

**Step 4**: Calculate gm
$$g_m = \beta_n (V_{GS} - V_{TH}) = 1720 \times (2.5 - 0.65)$$
$$g_m = 1720 \times 1.85 = 3182 \text{ μA/V} = 3.18 \text{ mA/V}$$

**Answer**: gm = 3.18 mS (or 3.18 mA/V)

---

## 🔗 Concept Links

- **Previous**: [I/V Characteristics](02_iv_characteristics.md)
- **Next**: [Second-Order Effects](04_second_order_effects.md)
- **Formulas**: [Formula Sheet - Transconductance](08_formula_sheet_ultimate.md#transconductance)
- **Problems**: [Q1(b), Q2(c) in Worked Problems](07_worked_problems.md)

---

## ✅ Self-Check Questions

1. What are the units of transconductance?
2. If ID doubles and W/L stays constant, what happens to gm?
3. Which form of gm shows that higher overdrive means higher gm?
4. Why is kn called "process" transconductance?
5. For a given current, does NMOS or PMOS have higher gm?

<details>
<summary>Click for Answers</summary>

1. A/V, S (Siemens), mA/V, or 1/Ω
2. gm increases by √2 (since gm ∝ √ID)
3. Form 1: gm = μnCox(W/L)(VGS - VTH)
4. Because it depends only on process parameters (μn, Cox), not on device geometry
5. NMOS (because μn > μp)

</details>
