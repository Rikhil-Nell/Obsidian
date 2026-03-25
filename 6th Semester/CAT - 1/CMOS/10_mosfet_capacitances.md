# 🔌 MOSFET Capacitances: Complete Guide

> **Speed isn't just about current — it's about how fast you can charge the capacitors.**

---

## 📌 Why Capacitances Matter

Every time a MOSFET switches:
1. Gate capacitances must be charged/discharged
2. Junction capacitances store/release charge
3. These determine **switching speed** and **power dissipation**

$$\text{Delay} \propto \frac{C \cdot V}{I}$$

Smaller capacitances = faster circuits!

---

## 📌 The Big Picture: Where Are the Capacitances?

```
                    VG
                     │
        ┌────────────┴────────────┐
        │         GATE            │
        │    C_GSO ↓    ↓ C_GDO   │  ← Overlap capacitances
        ├────┬─────────────┬──────┤
        │////│   C_gs      │//////│  ← Gate-to-channel capacitances
        │////│      C_gb   │//////│
        │////│   C_gd      │//////│
        ├────┴─────────────┴──────┤
   VS ──┤ n+ │  P-type     │ n+   ├── VD
        │    │  substrate  │      │
        │    │    C_sb     │ C_db │  ← Junction capacitances
        │    └──────┬──────┘      │
        └───────────┴─────────────┘
                    │
                   VB (substrate)
```

---

## 📌 Two Types of Capacitances

### 1. Oxide-Related Capacitances (Gate Capacitances)
- Between gate and other terminals
- Depend on operating region (cutoff/linear/saturation)
- Include overlap capacitances (always present)

### 2. Junction Capacitances
- Between source/drain and substrate
- Due to pn-junction depletion regions
- Depend on reverse bias voltage

---

## 📌 Overlap Capacitances

### What They Are

Due to the mask alignment, the gate **overlaps** the source/drain regions by a distance LD:

```
        ←─── LM (mask length) ───→
        ┌─────────────────────────┐
        │          GATE           │
        ├──┬────────────────────┬─┤
           │←LD→│  L (channel) │←LD→│
        ═══╧════╧══════════════╧════╧═══
        Source                     Drain
```

**Actual channel length:**
$$L = L_M - 2L_D$$

### Overlap Capacitance Formulas

$$C_{GSO} = C_{ox} \cdot W \cdot L_D$$
$$C_{GDO} = C_{ox} \cdot W \cdot L_D$$

| Parameter | Unit |
|-----------|------|
| CGSO, CGDO | F (total) or F/μm (per width) |
| Cox | F/cm² |
| W | cm |
| LD | cm |

> **Key Point**: Overlap capacitances are **always present**, regardless of operating region!

---

## 📌 Gate-Channel Capacitances by Region

The distributed capacitance between gate and channel is shared between source and drain differently depending on operating mode:

### Cutoff Region (VGS < VT)

**No channel exists** — gate "sees" only the substrate through the oxide.

$$C_{gs} = 0$$
$$C_{gd} = 0$$
$$C_{gb} = C_{ox} \cdot W \cdot L$$

```
┌─────────────────────────┐
│         GATE            │
├─────────────────────────┤  ← All capacitance to substrate
│   Depletion region      │
│   (no inversion layer)  │
└─────────────────────────┘
```

### Linear (Triode) Region

**Channel exists uniformly** — capacitance shared equally between source and drain.

$$C_{gs} = \frac{1}{2} C_{ox} \cdot W \cdot L$$
$$C_{gd} = \frac{1}{2} C_{ox} \cdot W \cdot L$$
$$C_{gb} = 0$$

```
┌─────────────────────────┐
│         GATE            │
├─────────────────────────┤
│═════════════════════════│  ← Uniform channel shields substrate
└─────────────────────────┘
      ↑               ↑
   Cgs = Cgd (equal split)
```

### Saturation Region

**Channel pinched off at drain** — no connection between gate and drain through channel.

$$C_{gs} = \frac{2}{3} C_{ox} \cdot W \cdot L$$
$$C_{gd} = 0$$
$$C_{gb} = 0$$

```
┌─────────────────────────┐
│         GATE            │
├───────────────────╲─────┤
│═══════════════════ ╲    │  ← Channel ends before drain
└───────────────────────╲─┘
      ↑                   ↑
    2/3 Cox·WL          No Cgd
```

> **The 2/3 factor**: Comes from integrating the non-uniform charge distribution in the pinched-off channel.

---

## 📌 Summary Table: Gate Capacitances

| Component | Cutoff | Linear | Saturation |
|-----------|--------|--------|------------|
| Cgs (channel) | 0 | ½ Cox·W·L | ⅔ Cox·W·L |
| Cgd (channel) | 0 | ½ Cox·W·L | 0 |
| Cgb | Cox·W·L | 0 | 0 |
| CGSO (overlap) | Cox·W·LD | Cox·W·LD | Cox·W·LD |
| CGDO (overlap) | Cox·W·LD | Cox·W·LD | Cox·W·LD |

### Total Capacitances (including overlap)

| Region | CGS (total) | CGD (total) | CGB (total) |
|--------|-------------|-------------|-------------|
| Cutoff | Cox·W·LD | Cox·W·LD | Cox·W·L |
| Linear | Cox·W·(½L + LD) | Cox·W·(½L + LD) | 0 |
| Saturation | Cox·W·(⅔L + LD) | Cox·W·LD | 0 |

---

## 📌 Junction Capacitances

### The Physical Picture

Source and drain are n+ regions in p-type substrate, forming **pn-junctions**. These junctions have depletion regions that store charge.

```
                     n+ Drain
                  ┌─────────────┐
                  │             │←── W (width)
                  │    xj ↕     │
     ╔════════════╧═════════════╧════════════╗
     ║         Depletion Region              ║
     ╠═══════════════════════════════════════╣
     ║              P-substrate              ║
     ╚═══════════════════════════════════════╝
```

**Five junction surfaces:**
1. Bottom (facing substrate) — Area = W × Y
2. Two sides parallel to channel — Area = xj × Y each
3. Two sides perpendicular to channel — Area = xj × W each
4. One side facing channel — Area = xj × W

### The Two Types of Junction Interfaces

| Interface | Adjacent to | Doping | Capacitance |
|-----------|-------------|--------|-------------|
| Bottom (surface 5) | P-substrate | NA | Cj (bottom) |
| Sidewalls (2,3,4) | P+ channel-stop | NA(sw) > NA | Cj(sw) (higher) |
| Channel side (1) | P-substrate + channel | NA | Different treatment |

### Junction Capacitance Formula

For a reverse-biased pn-junction:

$$C_j = \frac{C_{j0}}{(1 - V/\phi_0)^m}$$

Where:
- **Cj0** = zero-bias capacitance (per unit area)
- **V** = applied voltage (negative for reverse bias)
- **φ₀** = built-in junction potential
- **m** = grading coefficient (0.5 for abrupt, 0.33 for graded)

### Zero-Bias Junction Capacitance

$$C_{j0} = \sqrt{\frac{q \varepsilon_{Si}}{2\phi_0} \cdot \frac{N_A N_D}{N_A + N_D}}$$

For heavily doped source/drain (ND >> NA):
$$C_{j0} \approx \sqrt{\frac{q \varepsilon_{Si} N_A}{2\phi_0}}$$

### Built-in Junction Potential

$$\phi_0 = \frac{kT}{q} \ln\left(\frac{N_A \cdot N_D}{n_i^2}\right) = V_T \cdot \ln\left(\frac{N_A \cdot N_D}{n_i^2}\right)$$

Where VT = kT/q = 0.026 V at 300K (thermal voltage).

---

## 📌 Sidewall Junction Capacitance

The sidewalls are adjacent to the **P+ channel-stop implant**, which has higher doping:

$$C_{j0,sw} = \sqrt{\frac{q \varepsilon_{Si} N_{A(sw)}}{2\phi_{0,sw}}}$$

$$\phi_{0,sw} = \frac{kT}{q} \ln\left(\frac{N_{A(sw)} \cdot N_D}{n_i^2}\right)$$

### Per-Unit-Length Sidewall Capacitance

Since all sidewalls have the same depth xj:

$$C_{jsw0} = C_{j0,sw} \cdot x_j \quad \text{(F/cm)}$$

### Total Sidewall Capacitance

For a diffusion region with perimeter P:

$$C_{sw} = C_{jsw0} \cdot P \cdot K_{eq(sw)}$$

Where Keq is the voltage equivalence factor (explained below).

---

## 📌 Large-Signal Equivalent Capacitance

For transient analysis, we need an **average** capacitance over a voltage swing:

### The Problem

Capacitance varies with voltage:
$$C_j(V) = \frac{C_{j0}}{(1 - V/\phi_0)^m}$$

How do we get a single number for circuit analysis?

### The Solution: Voltage Equivalence Factor (Keq)

For a voltage swing from V1 to V2:

$$K_{eq} = \frac{1}{V_2 - V_1} \int_{V_1}^{V_2} \frac{dV}{(1 - V/\phi_0)^m}$$

For **abrupt junctions (m = 0.5)**:

$$K_{eq} = \frac{2\phi_0}{V_2 - V_1} \left[\sqrt{1 - \frac{V_1}{\phi_0}} - \sqrt{1 - \frac{V_2}{\phi_0}}\right]$$

### Equivalent Large-Signal Capacitance

$$C_{eq} = C_{j0} \cdot A \cdot K_{eq}$$

Where A is the junction area.

> **Note**: For reverse bias, V is negative, so (1 - V/φ₀) > 1, making Cj < Cj0.

---

## 📌 Worked Example: Problem 5 Style

### Problem

Consider an abrupt pn-junction with:
- ND = 10¹⁹ cm⁻³ (n-type, heavily doped)
- NA = 10¹⁶ cm⁻³ (p-type substrate)
- Junction area A = 20 μm × 20 μm = 400 μm²
- Reverse bias changes from V1 = 0 to V2 = -5 V

Find: (a) Zero-bias capacitance Cj0, (b) Equivalent capacitance for the voltage swing

### Solution

**(a) Calculate Cj0**

**Step 1: Built-in potential**
$$\phi_0 = 0.026 \times \ln\left(\frac{10^{16} \times 10^{19}}{(1.45 \times 10^{10})^2}\right)$$
$$\phi_0 = 0.026 \times \ln\left(\frac{10^{35}}{2.1 \times 10^{20}}\right)$$
$$\phi_0 = 0.026 \times \ln(4.76 \times 10^{14})$$
$$\phi_0 = 0.026 \times 33.8 = 0.88 \text{ V}$$

**Step 2: Zero-bias capacitance per unit area**

Since ND >> NA:
$$C_{j0} = \sqrt{\frac{q \varepsilon_{Si} N_A}{2\phi_0}}$$
$$C_{j0} = \sqrt{\frac{1.6 \times 10^{-19} \times 1.04 \times 10^{-12} \times 10^{16}}{2 \times 0.88}}$$
$$C_{j0} = \sqrt{\frac{1.66 \times 10^{-15}}{1.76}} = \sqrt{9.45 \times 10^{-16}}$$
$$C_{j0} = 3.07 \times 10^{-8} \text{ F/cm}^2$$

**Step 3: Total zero-bias capacitance**

Area = 400 μm² = 400 × 10⁻⁸ cm² = 4 × 10⁻⁶ cm²
$$C_{j0,total} = 3.07 \times 10^{-8} \times 4 \times 10^{-6} = 1.23 \times 10^{-13} \text{ F} = 0.123 \text{ pF}$$

---

**(b) Calculate equivalent capacitance**

**Step 1: Voltage equivalence factor**

V1 = 0, V2 = -5 V, φ₀ = 0.88 V

$$K_{eq} = \frac{2 \times 0.88}{-5 - 0} \left[\sqrt{1 - \frac{0}{0.88}} - \sqrt{1 - \frac{-5}{0.88}}\right]$$
$$K_{eq} = \frac{1.76}{-5} \left[1 - \sqrt{1 + 5.68}\right]$$
$$K_{eq} = -0.352 \times [1 - \sqrt{6.68}]$$
$$K_{eq} = -0.352 \times [1 - 2.58]$$
$$K_{eq} = -0.352 \times (-1.58) = 0.556$$

**Step 2: Equivalent capacitance**
$$C_{eq} = C_{j0,total} \times K_{eq} = 0.123 \times 0.556 = 0.068 \text{ pF}$$

> **Physical insight**: The equivalent capacitance (0.068 pF) is smaller than zero-bias (0.123 pF) because reverse bias widens the depletion region.

---

## 📌 Worked Example: Problem 6 Style

### Problem

For an NMOS with:
- NA = 2 × 10¹⁵ cm⁻³ (substrate)
- ND = 10¹⁹ cm⁻³ (source/drain)
- NA(sw) = 4 × 10¹⁶ cm⁻³ (sidewall p+ doping)
- tox = 45 nm
- xj = 1.0 μm = 10⁻⁴ cm
- Voltage swing: V1 = -0.5 V to V2 = -5 V

Find: (a) Zero-bias bottom and sidewall capacitances, (b) Voltage equivalence factors

### Solution

**(a) Zero-bias capacitances**

**Bottom junction (substrate):**

$$\phi_0 = 0.026 \times \ln\left(\frac{2 \times 10^{15} \times 10^{19}}{(1.45 \times 10^{10})^2}\right)$$
$$\phi_0 = 0.026 \times \ln(9.5 \times 10^{13}) = 0.026 \times 32.2 = 0.84 \text{ V}$$

$$C_{j0} = \sqrt{\frac{1.6 \times 10^{-19} \times 1.04 \times 10^{-12} \times 2 \times 10^{15}}{2 \times 0.84}}$$
$$C_{j0} = \sqrt{1.98 \times 10^{-16}} = 1.41 \times 10^{-8} \text{ F/cm}^2$$

**Sidewall junction (p+ channel-stop):**

$$\phi_{0,sw} = 0.026 \times \ln\left(\frac{4 \times 10^{16} \times 10^{19}}{(1.45 \times 10^{10})^2}\right)$$
$$\phi_{0,sw} = 0.026 \times \ln(1.9 \times 10^{15}) = 0.026 \times 35.2 = 0.92 \text{ V}$$

$$C_{j0,sw} = \sqrt{\frac{1.6 \times 10^{-19} \times 1.04 \times 10^{-12} \times 4 \times 10^{16}}{2 \times 0.92}}$$
$$C_{j0,sw} = \sqrt{3.62 \times 10^{-15}} = 6.02 \times 10^{-8} \text{ F/cm}^2$$

**Per-unit-length sidewall capacitance:**
$$C_{jsw0} = C_{j0,sw} \times x_j = 6.02 \times 10^{-8} \times 10^{-4} = 6.02 \times 10^{-12} \text{ F/cm}$$

---

**(b) Voltage equivalence factors**

**For bottom junction:**

V1 = -0.5 V, V2 = -5 V, φ₀ = 0.84 V

$$K_{eq} = \frac{2 \times 0.84}{-5 - (-0.5)} \left[\sqrt{1 - \frac{-0.5}{0.84}} - \sqrt{1 - \frac{-5}{0.84}}\right]$$
$$K_{eq} = \frac{1.68}{-4.5} \left[\sqrt{1.60} - \sqrt{6.95}\right]$$
$$K_{eq} = -0.373 \times [1.26 - 2.64] = -0.373 \times (-1.38) = 0.515$$

**For sidewall junction:**

V1 = -0.5 V, V2 = -5 V, φ₀,sw = 0.92 V

$$K_{eq,sw} = \frac{2 \times 0.92}{-4.5} \left[\sqrt{1 - \frac{-0.5}{0.92}} - \sqrt{1 - \frac{-5}{0.92}}\right]$$
$$K_{eq,sw} = -0.409 \times [\sqrt{1.54} - \sqrt{6.43}]$$
$$K_{eq,sw} = -0.409 \times [1.24 - 2.54] = -0.409 \times (-1.30) = 0.532$$

---

## 📌 Source-Drain Series Resistance

Another parasitic effect in small devices:

$$R_{S/D} = R_{sheet} \times \frac{L_{diff}}{W} + R_{contact}$$

Where:
- Rsheet = sheet resistance of diffusion (Ω/□)
- Ldiff = length of diffusion region
- Rcontact = contact resistance

**Effect**: Reduces effective VDS and VGS seen by the intrinsic transistor.

---

## 📌 Capacitance Formula Summary

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                       CAPACITANCE FORMULAS                                  │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  OXIDE CAPACITANCES:                                                        │
│  • Cox = εox / tox                         (per unit area)                  │
│  • CGSO = CGDO = Cox × W × LD              (overlap)                        │
│                                                                             │
│  GATE-CHANNEL (distributed):                                                │
│  • Cutoff:     Cgs = 0, Cgd = 0, Cgb = Cox×W×L                              │
│  • Linear:     Cgs = Cgd = ½Cox×W×L, Cgb = 0                                │
│  • Saturation: Cgs = ⅔Cox×W×L, Cgd = 0, Cgb = 0                             │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  JUNCTION CAPACITANCES:                                                     │
│  • φ₀ = (kT/q) × ln(NA×ND/ni²)             (built-in potential)             │
│  • Cj0 = √(q×εSi×NA / 2φ₀)                 (zero-bias, per area)            │
│  • Cj = Cj0 / (1 - V/φ₀)^m                 (voltage-dependent)              │
│  • m = 0.5 (abrupt), 0.33 (graded)                                          │
│                                                                             │
│  LARGE-SIGNAL EQUIVALENT:                                                   │
│  • Keq = (2φ₀/(V2-V1)) × [√(1-V1/φ₀) - √(1-V2/φ₀)]   (for m=0.5)            │
│  • Ceq = Cj0 × Area × Keq                                                   │
│                                                                             │
│  SIDEWALL:                                                                  │
│  • Cjsw0 = Cj0,sw × xj                     (per unit perimeter)             │
│  • Csw = Cjsw0 × Perimeter × Keq,sw                                         │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📝 Quick Check

1. ❓ In saturation, what is Cgd (channel component)?
2. ❓ Why is sidewall capacitance per unit length calculated differently?
3. ❓ Does reverse bias increase or decrease junction capacitance?
4. ❓ What is the grading coefficient for an abrupt junction?
5. ❓ Where do overlap capacitances come from?

<details>
<summary>Answers</summary>

1. Zero — channel is pinched off, no connection to drain
2. Because all sidewalls have the same depth xj, so we normalize by length instead of area
3. Decreases — wider depletion = less capacitance per unit area
4. m = 0.5
5. From gate overlapping source/drain diffusion regions (by distance LD)

</details>

---

*Previous: [09_scaling_summary.md](09_scaling_summary.md) | Next: [11_latchup_and_reliability.md](11_latchup_and_reliability.md)*
