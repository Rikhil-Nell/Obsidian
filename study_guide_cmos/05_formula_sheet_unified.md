# 📋 Unified Formula Sheet

> **All formulas in one place. Print this out. Keep it next to you during practice.**

---

## 📌 Physical Constants

| Constant | Symbol | Value (CGS) | Value (SI) |
|----------|--------|-------------|------------|
| Electron charge | q | 1.6 × 10⁻¹⁹ C | 1.6 × 10⁻¹⁹ C |
| Permittivity of free space | ε₀ | 8.85 × 10⁻¹⁴ F/cm | 8.85 × 10⁻¹² F/m |
| Permittivity of Si | εSi | 1.04 × 10⁻¹² F/cm | 1.04 × 10⁻¹⁰ F/m |
| Permittivity of SiO₂ | εox | 3.45 × 10⁻¹³ F/cm | 3.45 × 10⁻¹¹ F/m |
| Relative permittivity (Si) | εr,Si | 11.7 | 11.7 |
| Relative permittivity (SiO₂) | εr,ox | 3.9 | 3.9 |
| Intrinsic carrier conc. (Si, 300K) | ni | 1.45 × 10¹⁰ cm⁻³ | 1.45 × 10¹⁶ m⁻³ |
| Band gap (Si) | Eg | 1.12 eV | 1.12 eV |
| Thermal voltage (300K) | kT/q | 0.026 V | 0.026 V |
| Electron affinity (Si) | χ | 4.05 eV | 4.05 eV |

> ⚠️ **The PDF primarily uses CGS units (cm)**. Most problems are in CGS.

---

## 📌 Static Physics Formulas

### Fermi Potential (ΦF)

**For P-type substrate (NMOS):**
$$\phi_F = -\frac{kT}{q} \ln\left(\frac{N_A}{n_i}\right)$$
- Result is **negative**
- |ΦF| typically 0.3 - 0.4 V

**For N-type substrate (PMOS):**
$$\phi_F = +\frac{kT}{q} \ln\left(\frac{N_D}{n_i}\right)$$
- Result is **positive**

| Variable | Unit |
|----------|------|
| ΦF | V |
| NA, ND | cm⁻³ |
| ni | cm⁻³ |
| kT/q | V |

---

### Work Function Difference (ΦGC)

**For polysilicon gate:**
$$\Phi_{GC} = \phi_{F(gate)} - \phi_{F(substrate)} - \frac{E_g}{2q}$$

**For metal gate:**
$$\Phi_{GC} = \phi_M - \left(\chi + \frac{E_g}{2q} - \phi_F\right) = \phi_M - \phi_S$$

| Variable | Unit |
|----------|------|
| ΦGC | V |
| ΦM | V |
| χ | V |
| Eg | eV |

---

### Depletion Region

**Depletion width (general):**
$$x_d = \sqrt{\frac{2 \varepsilon_{Si} \phi_s}{q N_A}}$$

**Maximum depletion width (at inversion):**
$$x_{dm} = \sqrt{\frac{4 \varepsilon_{Si} |\phi_F|}{q N_A}}$$

| Variable | Unit |
|----------|------|
| xd, xdm | cm |
| εSi | F/cm |
| φs | V |
| NA | cm⁻³ |
| q | C |

---

### Depletion Charge Density

**At inversion (VSB = 0):**
$$Q_{B0} = -\sqrt{4 q \varepsilon_{Si} N_A |\phi_F|}$$

**With body bias:**
$$Q_B = -\sqrt{2 q \varepsilon_{Si} N_A (|2\phi_F| + V_{SB})}$$

| Variable | Unit |
|----------|------|
| QB0, QB | C/cm² |
| εSi | F/cm |
| NA | cm⁻³ |
| ΦF | V |
| VSB | V |

---

### Oxide Capacitance

$$C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}}$$

| Variable | Unit (CGS) | Unit (SI) |
|----------|------------|-----------|
| Cox | F/cm² | F/m² |
| εox | F/cm | F/m |
| tox | cm | m |

**Unit conversions for tox:**
- 1 nm = 10⁻⁷ cm = 10⁻⁹ m
- 1 Å = 10⁻⁸ cm = 10⁻¹⁰ m

---

### Threshold Voltage

**Basic (VSB = 0):**
$$V_{T0} = \Phi_{GC} - 2\phi_F - \frac{Q_{B0}}{C_{ox}} - \frac{Q_{ox}}{C_{ox}}$$

**With body effect:**
$$V_T = V_{T0} + \gamma \left(\sqrt{|2\phi_F| + V_{SB}} - \sqrt{|2\phi_F|}\right)$$

**Body effect coefficient:**
$$\gamma = \frac{\sqrt{2 q \varepsilon_{Si} N_A}}{C_{ox}}$$

| Variable | Unit |
|----------|------|
| VT0, VT | V |
| γ | V^(1/2) |
| Cox | F/cm² |
| QB0, Qox | C/cm² |

---

### Ion Implantation Adjustment

$$\Delta V_T = \frac{q \cdot N_I}{C_{ox}}$$

- **P-type implant**: Add to VT (makes more positive for NMOS)
- **N-type implant**: Subtract from VT

| Variable | Unit |
|----------|------|
| NI | cm⁻² (areal density!) |
| Cox | F/cm² |
| ΔVT | V |

---

## 📌 Active Operation Formulas

### Process Parameters

$$k' = \mu_n \cdot C_{ox}$$

$$k_n = k' \cdot \frac{W}{L}$$

| Variable | Unit |
|----------|------|
| μn | cm²/V·s |
| Cox | F/cm² |
| k' | A/V² = μA/V² × 10⁻⁶ |
| W, L | μm or cm |

---

### Cutoff Region

**Condition:** VGS < VT

$$I_D = 0$$

---

### Linear (Triode) Region

**Condition:** VGS > VT **AND** VDS < VGS - VT

$$I_D = k' \frac{W}{L} \left[(V_{GS} - V_T)V_{DS} - \frac{V_{DS}^2}{2}\right]$$

**Or equivalently:**
$$I_D = \frac{k'}{2} \frac{W}{L} \left[2(V_{GS} - V_T)V_{DS} - V_{DS}^2\right]$$

**Small VDS (resistor mode):**
$$I_D \approx k' \frac{W}{L} (V_{GS} - V_T) V_{DS}$$

$$r_{DS} = \frac{1}{k' \frac{W}{L} (V_{GS} - V_T)}$$

| Variable | Unit |
|----------|------|
| ID | A |
| VGS, VDS, VT | V |
| rDS | Ω |

---

### Saturation Region

**Condition:** VGS > VT **AND** VDS ≥ VGS - VT

$$I_D = \frac{k'}{2} \frac{W}{L} (V_{GS} - V_T)^2$$

**With channel length modulation:**
$$I_D = \frac{k'}{2} \frac{W}{L} (V_{GS} - V_T)^2 (1 + \lambda V_{DS})$$

**Saturation voltage:**
$$V_{DSAT} = V_{GS} - V_T$$

| Variable | Unit |
|----------|------|
| ID | A |
| λ | V⁻¹ |

---

## 📌 Short Channel Effect Formulas

### Threshold Voltage Reduction (Charge Sharing)

$$V_{T0(short)} = V_{T0(long)} - \Delta V_{T0}$$

$$\Delta V_{T0} = \frac{Q_{B0}}{C_{ox}} \cdot \frac{\Delta L_S + \Delta L_D}{2L}$$

**Or the detailed form:**
$$\Delta V_{T0} = \frac{Q_{B0}}{C_{ox}} \cdot \frac{x_j}{L} \left[\sqrt{1 + \frac{2x_{dS}}{x_j}} + \sqrt{1 + \frac{2x_{dD}}{x_j}} - 2\right]$$

**Junction depletion depths:**
$$x_{dS} = \sqrt{\frac{2\varepsilon_{Si}(\phi_0)}{qN_A}}$$
$$x_{dD} = \sqrt{\frac{2\varepsilon_{Si}(\phi_0 + V_{DS})}{qN_A}}$$

**Built-in junction potential:**
$$\phi_0 = \frac{kT}{q} \ln\left(\frac{N_A \cdot N_D}{n_i^2}\right)$$

| Variable | Unit |
|----------|------|
| xj | cm (junction depth) |
| L | cm (channel length) |
| xdS, xdD | cm |
| φ₀ | V |
| ND | cm⁻³ (source/drain doping) |

---

### Narrow Channel Effect

$$\Delta V_{T0(narrow)} = \frac{q N_A x_{dm}}{C_{ox}} \cdot K \cdot \frac{x_{dm}}{W}$$

Where K is an empirical factor (~π/2 for quarter-circular edge).

---

### Velocity Saturation

$$v = \frac{\mu_n E}{1 + E/E_c}$$

Where Ec is the critical field (~10⁴ V/cm for electrons in Si).

$$V_{DSAT} = \frac{(V_{GS} - V_T) \cdot E_c \cdot L}{(V_{GS} - V_T) + E_c \cdot L}$$

---

## 📌 Capacitance Formulas

### Overlap Capacitances

$$C_{GSO} = C_{GDO} = C_{ox} \cdot W \cdot L_D$$

Where LD is the gate-source/gate-drain overlap length.

### Gate Capacitances (per mode)

| Mode | Cgs | Cgd | Cgb |
|------|-----|-----|-----|
| Cutoff | 0 | 0 | Cox·W·L |
| Linear | ½Cox·W·L | ½Cox·W·L | 0 |
| Saturation | ⅔Cox·W·L | 0 | 0 |

### Junction Capacitance

**Built-in potential:**
$$\phi_0 = \frac{kT}{q} \ln\left(\frac{N_A \cdot N_D}{n_i^2}\right)$$

**Zero-bias capacitance (per unit area):**
$$C_{j0} = \sqrt{\frac{q \varepsilon_{Si}}{2\phi_0} \cdot \frac{N_A N_D}{N_A + N_D}}$$

For ND >> NA: $C_{j0} \approx \sqrt{\frac{q \varepsilon_{Si} N_A}{2\phi_0}}$

**Voltage-dependent capacitance:**
$$C_j = \frac{C_{j0}}{(1 - V/\phi_0)^m}$$

Where m = 0.5 (abrupt) or 0.33 (graded), V is negative for reverse bias.

### Large-Signal Equivalent Capacitance (Problem 5/6)

**Voltage equivalence factor (for m = 0.5):**
$$K_{eq} = \frac{2\phi_0}{V_2 - V_1} \left[\sqrt{1 - \frac{V_1}{\phi_0}} - \sqrt{1 - \frac{V_2}{\phi_0}}\right]$$

**Equivalent capacitance:**
$$C_{eq} = C_{j0} \cdot A \cdot K_{eq}$$

### Sidewall Junction Capacitance

**Sidewall zero-bias capacitance (per unit length):**
$$C_{jsw0} = C_{j0,sw} \cdot x_j$$

Where xj = junction depth and Cj0,sw uses sidewall doping NA(sw).

**Total sidewall capacitance:**
$$C_{sw} = C_{jsw0} \cdot P \cdot K_{eq,sw}$$

Where P = perimeter of diffusion region.

| Variable | Unit |
|----------|------|
| Cj0 | F/cm² |
| Cjsw0 | F/cm |
| A | cm² |
| P | cm |
| Keq | dimensionless |

---

## 📌 MOSFET Scaling Summary

| Parameter | Full Scaling (Constant Field) | Constant Voltage Scaling |
|-----------|-------------------------------|--------------------------|
| Dimensions (W, L, tox) | ÷ S | ÷ S |
| Voltage (VDD, VT) | ÷ S | × 1 (unchanged) |
| Doping (NA, ND) | × S | × S² |
| Electric field | × 1 (unchanged) | × S |
| Cox | × S | × S |
| Current (ID) | ÷ S | × S |
| Power per transistor | ÷ S² | × S |
| Power density | × 1 | × S³ |
| Delay | ÷ S | ÷ S |

---

## 📌 Unit Conversion Quick Reference

| From | To | Multiply by |
|------|----|-------------|
| nm | cm | 10⁻⁷ |
| Å | cm | 10⁻⁸ |
| μm | cm | 10⁻⁴ |
| μm² | cm² | 10⁻⁸ |
| F/m² | F/cm² | 10⁻⁴ |
| cm²/V·s | m²/V·s | 10⁻⁴ |

---

## 📌 Sign Convention Reference

| Parameter | NMOS | PMOS |
|-----------|------|------|
| Substrate | P-type | N-type |
| Channel carriers | Electrons | Holes |
| ΦF (substrate) | Negative | Positive |
| VT (enhancement) | Positive | Negative |
| QB0 | Negative | Positive |
| γ | Positive | Negative |
| VSB (typical) | ≥ 0 | ≤ 0 |
| VGS for ON | > VT (positive) | < VT (negative) |

---

*Previous: [04_current_equations_complete.md](./04_current_equations_complete.md) | Next: [06_short_channel_effects_decoded.md](./06_short_channel_effects_decoded.md)*
