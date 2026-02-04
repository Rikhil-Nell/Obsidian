# ⚡ I/V Characteristics

> **Chapter 2.2**: Drain current equations for all operating regions

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Calculate drain current in triode and saturation regions
- Identify which region a MOSFET is operating in
- Derive the I/V equations from first principles
- Apply the correct equation based on terminal voltages

---

## 1️⃣ The Three Operating Regions

### Region Decision Tree

```
Is the transistor ON? (VGS vs VTH)
│
├── NO (VGS < VTH for NMOS) → CUTOFF (ID ≈ 0)
│
└── YES (VGS > VTH for NMOS)
    │
    └── Check VDS vs (VGS - VTH):
        │
        ├── VDS < VGS - VTH → TRIODE (Linear)
        │
        └── VDS ≥ VGS - VTH → SATURATION
```

### Visual Summary

```
         ID
          │
          │         Saturation
          │    ╭────────────────────
          │   /│
          │  / │ VGS3
          │ /╭─┼─────────────────────
          │/ │ │ VGS2
          /──┼─┼───────────────────── 
         /│  │ │ VGS1
        / │  │ │
       ───┴──┴─┴───────────────────→ VDS
          │  │ │
          │  │ │
       Triode│ VGS2-VTH
          │    VGS3-VTH
          │
```

---

## 2️⃣ Oxide Capacitance (Critical for Calculations!)

The oxide capacitance per unit area is **fundamental** to all current equations.

$$\boxed{C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}} = \frac{\varepsilon_0 \cdot \varepsilon_{r,ox}}{t_{ox}}}$$

### Step-by-Step Calculation

**Given**: tox = 100 Å, ε₀ = 8.854 × 10⁻¹⁴ F/cm, εr,ox = 3.9

**Step 1**: Convert tox to cm
$$t_{ox} = 100 \text{ Å} = 100 \times 10^{-8} \text{ cm} = 10^{-6} \text{ cm}$$

**Step 2**: Calculate Cox
$$C_{ox} = \frac{8.854 \times 10^{-14} \times 3.9}{10^{-6}} = \frac{3.45 \times 10^{-13}}{10^{-6}}$$
$$C_{ox} = 3.45 \times 10^{-7} \text{ F/cm}^2$$

**Step 3**: Convert to common units
$$C_{ox} = 3.45 \times 10^{-7} \times 10^8 \text{ fF/μm}^2 = 34.5 \text{ fF/μm}^2$$

> **Quick Reference**: For tox = 20Å → Cox ≈ 17.25 fF/μm²

---

## 3️⃣ Channel Charge Density

### The Foundation Equation

The charge per unit length in the channel:

$$Q_d = W \cdot C_{ox} \cdot (V_{GS} - V(x) - V_{TH})$$

where V(x) is the potential along the channel.

### Key Insight
- At source (x=0): V(0) = 0, so Qd = WCox(VGS - VTH)
- At drain (x=L): V(L) = VDS

This variation in charge density along the channel leads to different regions!

---

## 4️⃣ Triode Region

### Condition
$$\boxed{V_{DS} < V_{GS} - V_{TH}}$$

The channel extends fully from source to drain.

### Current Equation

$$\boxed{I_D = \mu_n C_{ox} \frac{W}{L} \left[(V_{GS} - V_{TH})V_{DS} - \frac{1}{2}V_{DS}^2\right]}$$

### Alternative Form (using kn)

$$I_D = k_n \frac{W}{L} \left[(V_{GS} - V_{TH})V_{DS} - \frac{1}{2}V_{DS}^2\right]$$

where $k_n = \mu_n C_{ox}$ is the **process transconductance**.

### Derivation Intuition 🧠

1. Current = charge × velocity: $I = Q_d \cdot v$
2. Velocity = mobility × field: $v = \mu_n \cdot E = \mu_n \cdot \frac{dV}{dx}$
3. Integrate from x=0 to x=L
4. Result: parabolic relationship with VDS

---

## 5️⃣ Deep Triode Region

### Condition
$$V_{DS} \ll 2(V_{GS} - V_{TH})$$

### Simplified Equation

$$\boxed{I_D \approx \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH}) \cdot V_{DS}}$$

### The MOSFET as a Resistor! 

In deep triode, the device acts like a voltage-controlled resistor:

$$\boxed{R_{on} = \frac{V_{DS}}{I_D} = \frac{1}{\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})}}$$

**For NMOS**: $R_n = \frac{1}{\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{THn})}$

**For PMOS**: $R_p = \frac{1}{\mu_p C_{ox} \frac{W}{L} (|V_{GS}| - |V_{THp}|)}$

---

## 6️⃣ Saturation Region

### Condition
$$\boxed{V_{DS} \geq V_{GS} - V_{TH}}$$

The channel is "pinched off" near the drain.

### Current Equation (Basic)

$$\boxed{I_D = \frac{1}{2}\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})^2}$$

### What is Pinch-off? 

At the point where V(x) = VGS - VTH, the local charge density Qd → 0.
- The channel becomes very thin (pinched)
- Electrons shoot through the depletion region
- Current becomes relatively independent of VDS

### Visual: Pinch-off

```
VDS = VGS - VTH:              VDS > VGS - VTH:
      Gate                          Gate
      ┌───┐                         ┌───┐
      │   │                         │   │
  n+══════╲n+                   n+══════╲ n+
  S   channel D                 S   pinched D
              ↑                        ↑
         pinch-off              electrons shoot
           point                   through
```

---

## 7️⃣ Overdrive Voltage

The "effective" gate voltage controlling the channel:

$$\boxed{V_{OV} = V_{GS} - V_{TH}}$$

### Why It Matters

| Parameter | Depends on VOV as |
|-----------|-------------------|
| ID (sat) | VOV² |
| gm (sat) | VOV |
| VDS,min for sat | VOV |
| Ron | 1/VOV |

> **💡 Design Trade-off**: Higher VOV = more current but less voltage headroom!

---

## 8️⃣ PMOS Equations

For PMOS, same equations but with appropriate signs:

### Triode
$$I_D = -\mu_p C_{ox} \frac{W}{L} \left[(V_{GS} - V_{TH})V_{DS} - \frac{1}{2}V_{DS}^2\right]$$

### Saturation
$$I_D = -\frac{1}{2}\mu_p C_{ox} \frac{W}{L} (V_{GS} - V_{TH})^2$$

> **Note**: VGS, VDS, VTH are all **negative** for an ON PMOS device.
> The negative sign makes ID positive (flowing into drain terminal).

---

## 9️⃣ Region Identification Checklist

**Step 1**: Check if device is ON
- NMOS: Is VGS > VTH? 
- PMOS: Is VGS < VTH? (both negative)

**Step 2**: If ON, check region
- Calculate VGS - VTH (overdrive)
- Compare VDS to overdrive:
  - VDS < VGS - VTH → **Triode**
  - VDS ≥ VGS - VTH → **Saturation**

### Example Check

**Given**: NMOS with VGS = 2V, VDS = 2V, VTH = 0.7V

1. Is it ON? VGS = 2V > VTH = 0.7V ✓ YES
2. Overdrive: VGS - VTH = 2 - 0.7 = 1.3V
3. Compare: VDS = 2V > 1.3V → **Saturation** ✓

---

## 🔢 Worked Example: Drain Current Calculation

**Problem**: Calculate ID for an NMOS with:
- W = 10 μm, L = 0.35 μm
- kn = 110 μA/V²
- VTH0 = 0.7 V, γ = 0.08 V^(1/2), 2|φF| = 0.58 V
- VGS = 2V, VDS = 2V, VSB = 1V

**Solution**:

**Step 1**: Calculate VTH with body effect
$$V_{TH} = V_{TH0} + \gamma\left(\sqrt{2|\phi_F| + V_{SB}} - \sqrt{2|\phi_F|}\right)$$
$$V_{TH} = 0.7 + 0.08\left(\sqrt{0.58 + 1} - \sqrt{0.58}\right)$$
$$V_{TH} = 0.7 + 0.08(1.257 - 0.762) = 0.7 + 0.04 = 0.74V$$

**Step 2**: Check region
- VGS - VTH = 2 - 0.74 = 1.26V
- VDS = 2V > 1.26V → **Saturation**

**Step 3**: Calculate ID (saturation, ignoring λ)
$$I_D = \frac{1}{2} k_n \frac{W}{L}(V_{GS} - V_{TH})^2$$
$$I_D = \frac{1}{2} \times 110 \times \frac{10}{0.35} \times (1.26)^2$$
$$I_D = 55 \times 28.57 \times 1.588 = 2.495 \text{ mA}$$

**Answer**: ID ≈ 2.5 mA

---

## 🔗 Concept Links

- **Previous**: [MOS Device Physics](./01_mos_device_physics.md)
- **Next**: [Transconductance](./03_transconductance.md)
- **Formulas**: [Formula Sheet - I/V Section](./08_formula_sheet_ultimate.md#drain-current-equations)
- **Problems**: [Q1, Q2, Q3 in Worked Problems](./07_worked_problems.md)

---

## ✅ Self-Check Questions

1. What determines whether a MOSFET is in triode or saturation?
2. Why does ID become relatively independent of VDS in saturation?
3. In deep triode, the MOSFET acts like a _______ controlled by _______.
4. If VDS increases while VGS is constant, what happens to ID in saturation?
5. Why is the saturation current proportional to (VGS - VTH)²?

<details>
<summary>Click for Answers</summary>

1. The comparison between VDS and (VGS - VTH)
2. Because the channel is pinched off; further increases in VDS only extend the depletion region
3. Resistor; gate-source voltage (overdrive)
4. It remains approximately constant (slight increase due to CLM if λ ≠ 0)
5. Because the channel charge ∝ (VGS - VTH) and the average velocity ∝ (VGS - VTH)

</details>
