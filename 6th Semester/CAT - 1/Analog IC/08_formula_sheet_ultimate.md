# 📋 Analog IC Ultimate Formula Sheet

> **Quick Reference for Exams** — All formulas from Razavi Chapters 2-3

---

## 🔬 Physical Constants

| Constant | Symbol | Value | Units |
|----------|--------|-------|-------|
| Permittivity of free space | ε₀ | 8.854 × 10⁻¹⁴ | F/cm |
| Relative permittivity of Si | ε_si | 11.8 | — |
| Relative permittivity of SiO₂ | ε_ox | 3.9 | — |
| Permittivity of Si | ε_si | 1.04 × 10⁻¹² | F/cm |
| Permittivity of SiO₂ | ε_ox | 3.45 × 10⁻¹³ | F/cm |
| Boltzmann constant | k | 1.38 × 10⁻²³ | J/K |
| Electron charge | q | 1.6 × 10⁻¹⁹ | C |
| Thermal voltage (T=300K) | V_T = kT/q | 26 mV | V |
| Intrinsic carrier concentration | n_i | 1.45 × 10¹⁰ | cm⁻³ |

---

## 📐 Unit Conversions

| From | To | Multiply by |
|------|-----|-------------|
| Å (Angstrom) | nm | 0.1 |
| Å | cm | 10⁻⁸ |
| nm | cm | 10⁻⁷ |
| μm | cm | 10⁻⁴ |
| μm | m | 10⁻⁶ |
| fF | F | 10⁻¹⁵ |
| μA | A | 10⁻⁶ |

---

## ⚡ Oxide Capacitance

$$\boxed{C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}} = \frac{\varepsilon_0 \cdot \varepsilon_{r,ox}}{t_{ox}}}$$

| Variable | Description | Typical Value |
|----------|-------------|---------------|
| C_ox | Oxide capacitance per unit area | ~17.25 fF/μm² (for t_ox = 20Å) |
| ε_ox | Permittivity of SiO₂ | 3.45 × 10⁻¹³ F/cm |
| ε_r,ox | Relative permittivity of SiO₂ | 3.9 |
| t_ox | Gate oxide thickness | 1-20 nm |

> **Quick Calc**: For t_ox = 20Å → C_ox ≈ 17.25 fF/μm²  
> Scale proportionally: C_ox ∝ 1/t_ox

---

## 🎯 Threshold Voltage

### Basic Threshold Voltage

$$\boxed{V_{TH} = \Phi_{MS} + 2\Phi_F + \frac{Q_{dep}}{C_{ox}}}$$

where:
- Φ_MS = Work function difference (poly-gate vs substrate)
- Φ_F = Fermi potential = (kT/q) ln(N_sub/n_i)
- Q_dep = Depletion region charge = √(4qε_si|Φ_F|N_sub)

### Threshold Voltage with Body Effect

$$\boxed{V_{TH} = V_{TH0} + \gamma\left(\sqrt{2\Phi_F + V_{SB}} - \sqrt{2\Phi_F}\right)}$$

| Variable | Description | Typical Value |
|----------|-------------|---------------|
| V_TH0 | Threshold at V_SB = 0 | 0.3 - 0.7 V |
| γ | Body-effect coefficient | 0.3 - 0.4 V^(1/2) |
| 2Φ_F | ≈ 2 × Fermi potential | 0.6 - 0.9 V |
| V_SB | Source-bulk voltage | variable |

### Body Effect Coefficient

$$\boxed{\gamma = \frac{\sqrt{2q\varepsilon_{si}N_{sub}}}{C_{ox}}}$$

---

## 📊 Drain Current Equations

### Triode Region (V_DS < V_GS - V_TH)

$$\boxed{I_D = \mu_n C_{ox} \frac{W}{L} \left[(V_{GS} - V_{TH})V_{DS} - \frac{1}{2}V_{DS}^2\right]}$$

### Deep Triode Region (V_DS ≪ 2(V_GS - V_TH))

$$\boxed{I_D \approx \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH}) V_{DS}}$$

### Saturation Region (V_DS ≥ V_GS - V_TH)

$$\boxed{I_D = \frac{1}{2}\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})^2}$$

### Saturation with Channel-Length Modulation

$$\boxed{I_D = \frac{1}{2}\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})^2 (1 + \lambda V_{DS})}$$

### Subthreshold Region (V_GS < V_TH)

$$I_D = I_0 \exp\left(\frac{V_{GS}}{\xi V_T}\right)$$

where ξ ≈ 1.5 (subthreshold slope factor)

---

## 🔄 On-Resistance

### Deep Triode Region

$$\boxed{R_{on} = \frac{1}{\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})}}$$

### For NMOS (R_n)

$$R_n = \frac{1}{\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{THn})}$$

### For PMOS (R_p)

$$R_p = \frac{1}{\mu_p C_{ox} \frac{W}{L} (|V_{GS}| - |V_{THp}|)}$$

---

## 📈 Transconductance

### Definition

$$g_m = \frac{\partial I_D}{\partial V_{GS}}\bigg|_{V_{DS} = const}$$

### Three Equivalent Forms (Saturation)

$$\boxed{g_m = \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})}$$

$$\boxed{g_m = \sqrt{2\mu_n C_{ox} \frac{W}{L} I_D}}$$

$$\boxed{g_m = \frac{2I_D}{V_{GS} - V_{TH}}}$$

### Process Transconductance

$$\boxed{k_n = \mu_n C_{ox}} \quad \text{(units: μA/V²)}$$

### Device Transconductance

$$\boxed{\beta_n = \mu_n C_{ox} \frac{W}{L} = k_n \frac{W}{L}} \quad \text{(units: μA/V²)}$$

### Triode Region Transconductance

$$g_m = \mu_n C_{ox} \frac{W}{L} V_{DS}$$

---

## 🔧 Output Resistance

$$\boxed{r_O = \frac{1}{\lambda I_D} \approx \frac{1 + \lambda V_{DS}}{\lambda I_D}}$$

### Intrinsic Gain

$$\boxed{g_m r_O = \frac{2}{\lambda(V_{GS} - V_{TH})}}$$

---

## 🌊 Body Transconductance

$$\boxed{g_{mb} = \frac{\partial I_D}{\partial V_{BS}} = g_m \cdot \frac{\gamma}{2\sqrt{2\Phi_F + V_{SB}}}}$$

$$\boxed{g_{mb} = \eta \cdot g_m}$$

where η = g_mb/g_m ≈ 0.25 typically

---

## 🔌 MOS Capacitances

### Gate-Oxide Capacitance

$$C_{ox,total} = W \cdot L \cdot C_{ox}$$

### Overlap Capacitance

$$C_{GS,overlap} = C_{GD,overlap} = W \cdot C_{ov}$$

### Operating Region Capacitances

| Region | C_GS | C_GD |
|--------|------|------|
| Off | WC_ov | WC_ov |
| Deep Triode | WLC_ox/2 + WC_ov | WLC_ox/2 + WC_ov |
| Saturation | (2/3)WLC_ox + WC_ov | WC_ov |

### Junction Capacitance

$$C_j = \frac{C_{j0}}{(1 + V_R/\Phi_B)^m}$$

$$C_{DB} = C_{SB} = W \cdot E \cdot C_j + 2(W + E) \cdot C_{jsw}$$

---

## 📡 Small-Signal Model Parameters

| Parameter | Formula | Description |
|-----------|---------|-------------|
| g_m | μC_ox(W/L)(V_GS - V_TH) | Transconductance |
| r_O | 1/(λI_D) | Output resistance |
| g_mb | ηg_m | Body transconductance |
| η | γ/(2√(2Φ_F + V_SB)) | Body effect factor |

---

## 🎛️ Common-Source Stage Gain

### With Resistive Load

$$\boxed{A_v = -g_m R_D}$$

### With r_O included

$$\boxed{A_v = -g_m (r_O \| R_D)}$$

### With Diode-Connected Load (NMOS)

$$A_v = -\frac{g_{m1}}{g_{m2} + g_{mb2}} = -\frac{g_{m1}}{g_{m2}(1 + \eta)}$$

$$\boxed{A_v = -\sqrt{\frac{(W/L)_1}{(W/L)_2}} \cdot \frac{1}{1 + \eta}}$$

### With Diode-Connected PMOS Load

$$\boxed{A_v = -\sqrt{\frac{\mu_n(W/L)_1}{\mu_p(W/L)_2}}}$$

### With Current-Source Load

$$\boxed{A_v = -g_m (r_{O1} \| r_{O2})}$$

### With Active Load (Inverter)

$$\boxed{A_v = -(g_{m1} + g_{m2})(r_{O1} \| r_{O2})}$$

### Intrinsic Gain (Max Possible)

$$\boxed{A_v = -g_m r_O}$$

---

## 📊 SPICE Level-1 Parameters

| Parameter | NMOS | PMOS | Description |
|-----------|------|------|-------------|
| VTO | 0.7 V | -0.8 V | Threshold voltage |
| GAMMA | 0.45 V^(1/2) | 0.4 V^(1/2) | Body effect coefficient |
| PHI | 0.9 V | 0.8 V | 2Φ_F |
| UO | 350 cm²/V·s | 100 cm²/V·s | Mobility |
| LAMBDA | 0.1 V⁻¹ | 0.2 V⁻¹ | CLM coefficient |
| TOX | 9 nm | 9 nm | Oxide thickness |
| NSUB | 9×10¹⁴ cm⁻³ | 5×10¹⁴ cm⁻³ | Substrate doping |

---

## ⚠️ Sign Conventions

| Parameter | NMOS | PMOS |
|-----------|------|------|
| V_TH | Positive (+) | Negative (-) |
| V_GS for ON | V_GS > V_TH | V_GS < V_TH (more negative) |
| V_DS for saturation | V_DS ≥ V_GS - V_TH | V_DS ≤ V_GS - V_TH |
| I_D direction | Into drain | Out of drain |
| Current flow | Electrons: S → D | Holes: D → S |

---

## ✅ Exam Checklist

Before submitting your answer, verify:

- [ ] **Units**: Convert all values to consistent units (usually cm, V, A)
- [ ] **t_ox in cm**: 100 Å = 10⁻⁶ cm, 10 nm = 10⁻⁶ cm
- [ ] **Region check**: Verify V_DS vs (V_GS - V_TH) for correct equation
- [ ] **Body effect**: Include if V_SB ≠ 0
- [ ] **CLM**: Include (1 + λV_DS) if λ given and device in saturation
- [ ] **Sign**: PMOS has negative V_TH and reversed inequalities
- [ ] **Mobility ratio**: μ_n ≈ 2-3 × μ_p typically
- [ ] **Formula selection**: Use correct gm formula based on given variables
