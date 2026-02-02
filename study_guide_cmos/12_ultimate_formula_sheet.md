# 📋 CMOS ULTIMATE FORMULA SHEET

> **One sheet to rule them all. Print this. Memorize this. Ace the exam.**

---

# SECTION 1: FUNDAMENTAL CONSTANTS & MATERIAL PROPERTIES

## 1.1 Universal Constants

| Constant | Symbol | Value | Notes |
|----------|--------|-------|-------|
| Electron charge | $q$ | $1.6 \times 10^{-19}$ C | Fundamental charge |
| Boltzmann constant | $k$ | $1.38 \times 10^{-23}$ J/K | Use with T in Kelvin |
| Thermal voltage (300K) | $V_T = \frac{kT}{q}$ | **0.026 V** = 26 mV | Memorize this! |
| Room temperature | $T$ | 300 K | Standard assumption |

## 1.2 Silicon Properties (Si)

| Property | Symbol | Value (CGS) | Value (SI) |
|----------|--------|-------------|------------|
| Permittivity | $\varepsilon_{Si}$ | $1.04 \times 10^{-12}$ F/cm | $1.04 \times 10^{-10}$ F/m |
| Relative permittivity | $\varepsilon_{r,Si}$ | 11.7 | 11.7 |
| Intrinsic carrier conc. | $n_i$ | $1.45 \times 10^{10}$ cm⁻³ | At 300K |
| Band gap | $E_g$ | **1.12 eV** | At 300K |
| Half band gap | $\frac{E_g}{2q}$ | **0.56 V** | Appears in ΦGC |
| Electron affinity | $\chi$ | **4.05 eV** | Some texts use 4.15 eV |

## 1.3 Silicon Dioxide Properties (SiO₂)

| Property | Symbol | Value (CGS) | Value (SI) |
|----------|--------|-------------|------------|
| Permittivity | $\varepsilon_{ox}$ | $3.45 \times 10^{-13}$ F/cm | $3.45 \times 10^{-11}$ F/m |
| Relative permittivity | $\varepsilon_{r,ox}$ | 3.9 | 3.9 |
| Breakdown field | $E_{BD}$ | ~10-15 MV/cm | Reliability limit |

## 1.4 Work Functions (Reference Values)

| Material | Work Function | Notes |
|----------|--------------|-------|
| Aluminum (Al) | 4.1 eV | Metal gate |
| N+ Polysilicon | ~4.05 eV + ΦF(poly) | Depends on doping |
| P+ Polysilicon | ~5.17 eV - ΦF(poly) | Depends on doping |

## 1.5 Unit Conversions (CRITICAL!)

$$\boxed{1 \text{ nm} = 10^{-7} \text{ cm} = 10^{-9} \text{ m}}$$
$$\boxed{1 \text{ Å} = 10^{-8} \text{ cm} = 10^{-10} \text{ m}}$$
$$\boxed{1 \text{ μm} = 10^{-4} \text{ cm} = 10^{-6} \text{ m}}$$

---

# SECTION 2: FERMI LEVEL & DOPING

## 2.1 Fermi Potential (ΦF)

**For P-type substrate (NMOS):**
$$\boxed{\phi_F = -\frac{kT}{q} \ln\left(\frac{N_A}{n_i}\right) = -0.026 \ln\left(\frac{N_A}{1.45 \times 10^{10}}\right)}$$

**For N-type substrate (PMOS):**
$$\boxed{\phi_F = +\frac{kT}{q} \ln\left(\frac{N_D}{n_i}\right) = +0.026 \ln\left(\frac{N_D}{1.45 \times 10^{10}}\right)}$$

### Quick Reference Table

| Doping (cm⁻³) | ln(N/nᵢ) | ΦF for P-type |
|---------------|----------|---------------|
| $10^{15}$ | 11.14 | -0.29 V |
| $10^{16}$ | 13.44 | **-0.35 V** |
| $10^{17}$ | 15.74 | -0.41 V |
| $10^{18}$ | 18.04 | -0.47 V |

### Sign Convention
- **P-type**: ΦF is **NEGATIVE**
- **N-type**: ΦF is **POSITIVE**
- $|2\phi_F|$ is always **POSITIVE** (absolute value)

## 2.2 Mass Action Law & Carrier Concentrations

$$\boxed{n \cdot p = n_i^2}$$

**For P-type (NA >> ni):**
$$p \approx N_A, \quad n \approx \frac{n_i^2}{N_A}$$

**For N-type (ND >> ni):**
$$n \approx N_D, \quad p \approx \frac{n_i^2}{N_D}$$

---

# SECTION 3: WORK FUNCTION & FLAT-BAND (Problem 1)

## 3.1 Silicon Work Function

$$\boxed{\phi_S = \chi + \frac{E_g}{2q} - \phi_F = 4.05 + 0.56 - \phi_F}$$

## 3.2 Work Function Difference

**For metal gate:**
$$\boxed{\Phi_{GC} = \phi_M - \phi_S}$$

**For polysilicon gate:**
$$\boxed{\Phi_{GC} = \phi_{F(gate)} - \phi_{F(substrate)} - \frac{E_g}{2q}}$$

### Example: N+ poly on P-type
- ΦF(gate) = +0.55 V (given or calculated for N+ poly)
- ΦF(sub) = -0.35 V (for NA = 10¹⁶)
- ΦGC = 0.55 - (-0.35) - 0.56 = **+0.34 V**

## 3.3 Flat-Band Voltage

$$\boxed{V_{FB} = \Phi_{GC} - \frac{Q_{ox}}{C_{ox}}}$$

---

# SECTION 4: OXIDE CAPACITANCE

## 4.1 Gate Oxide Capacitance (per unit area)

$$\boxed{C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}} = \frac{3.45 \times 10^{-13}}{t_{ox} \text{ (in cm)}}}$$

### Quick Reference Table

| tox | tox (cm) | Cox (F/cm²) | Cox (fF/μm²) |
|-----|----------|-------------|--------------|
| 5 nm | 5×10⁻⁷ | 6.9×10⁻⁷ | 69 |
| 10 nm | 10⁻⁶ | 3.45×10⁻⁷ | 34.5 |
| 50 nm | 5×10⁻⁶ | 6.9×10⁻⁸ | 6.9 |
| 500 Å | 5×10⁻⁶ | 6.9×10⁻⁸ | 6.9 |

### Quick Formula (tox in nm)
$$C_{ox} \approx \frac{34.5}{t_{ox}(\text{nm})} \text{ fF/μm}^2$$

---

# SECTION 5: DEPLETION REGION

## 5.1 Depletion Width

**General formula:**
$$\boxed{x_d = \sqrt{\frac{2\varepsilon_{Si}\phi_s}{qN_A}}}$$

**Maximum depletion width (at inversion, φs = 2|ΦF|):**
$$\boxed{x_{dm} = \sqrt{\frac{4\varepsilon_{Si}|\phi_F|}{qN_A}} = \sqrt{\frac{2\varepsilon_{Si} \cdot 2|\phi_F|}{qN_A}}}$$

## 5.2 Depletion Charge Density

**At inversion (VSB = 0):**
$$\boxed{Q_{B0} = -\sqrt{4q\varepsilon_{Si}N_A|\phi_F|}}$$

**With body bias (VSB ≠ 0):**
$$\boxed{Q_B = -\sqrt{2q\varepsilon_{Si}N_A(|2\phi_F| + V_{SB})}}$$

### Sign Convention
- $Q_{B0}$ is **NEGATIVE** (acceptor ions in P-type)
- $-Q_{B0}/C_{ox}$ contributes **POSITIVELY** to VT0

---

# SECTION 6: THRESHOLD VOLTAGE (Problem 2)

## 6.1 Complete VT0 Formula

$$\boxed{V_{T0} = \Phi_{GC} - 2\phi_F - \frac{Q_{B0}}{C_{ox}} - \frac{Q_{ox}}{C_{ox}}}$$

### Component-by-Component Breakdown

| Component | Formula | Sign (NMOS) | Physical Meaning |
|-----------|---------|-------------|------------------|
| ΦGC | Work function diff | +/- | Built-in potential |
| $-2\phi_F$ | $-2 \times (\text{negative})$ | **+** | Band bending for inversion |
| $-Q_{B0}/C_{ox}$ | $-(\text{negative})/(\text{positive})$ | **+** | Offset depletion charge |
| $-Q_{ox}/C_{ox}$ | $-(\text{positive})/(\text{positive})$ | **-** | Offset oxide charge |

### Oxide Charge
$$Q_{ox} = q \cdot N_{ox}$$
Where $N_{ox}$ is in **cm⁻²** (surface density)

## 6.2 Body Effect

$$\boxed{V_T = V_{T0} + \gamma\left(\sqrt{|2\phi_F| + V_{SB}} - \sqrt{|2\phi_F|}\right)}$$

**Body effect coefficient:**
$$\boxed{\gamma = \frac{\sqrt{2q\varepsilon_{Si}N_A}}{C_{ox}}}$$

- Units: $\gamma$ has units of **V^(1/2)**
- Typical value: 0.3 - 0.5 V^(1/2)

### Edge Cases
- If $V_{SB} = 0$: $V_T = V_{T0}$ (no body effect)
- If $V_{SB} > 0$ (NMOS): $V_T$ **increases**

## 6.3 Ion Implantation Adjustment

$$\boxed{\Delta V_T = \frac{q \cdot N_I}{C_{ox}}}$$

| Implant Type | Effect on VT (NMOS) | Sign of ΔVT |
|--------------|---------------------|-------------|
| P-type (Boron) | Increases VT | **+** |
| N-type (Phosphorus) | Decreases VT | **-** |

- $N_I$ is in **cm⁻²** (areal dose, NOT volume concentration!)

---

# SECTION 7: DRAIN CURRENT (Problem 3)

## 7.1 Process Parameter

$$\boxed{k' = \mu_n \cdot C_{ox}}$$

| Typical Values | Range |
|----------------|-------|
| μn (electrons) | 200-500 cm²/V·s |
| μp (holes) | 100-250 cm²/V·s |
| k' | 50-500 μA/V² |

## 7.2 Operating Regions

### Cutoff (OFF)
**Condition:** $V_{GS} < V_T$
$$\boxed{I_D = 0}$$

### Linear (Triode) Region
**Condition:** $V_{GS} > V_T$ **AND** $V_{DS} < V_{GS} - V_T$

$$\boxed{I_D = k'\frac{W}{L}\left[(V_{GS} - V_T)V_{DS} - \frac{V_{DS}^2}{2}\right]}$$

**Alternate form:**
$$I_D = \frac{k'}{2}\frac{W}{L}\left[2(V_{GS} - V_T)V_{DS} - V_{DS}^2\right]$$

**Small VDS approximation (resistor):**
$$I_D \approx k'\frac{W}{L}(V_{GS} - V_T)V_{DS}$$

$$\boxed{r_{DS} = \frac{1}{k'\frac{W}{L}(V_{GS} - V_T)}}$$

### Saturation Region
**Condition:** $V_{GS} > V_T$ **AND** $V_{DS} \geq V_{GS} - V_T$

$$\boxed{I_D = \frac{k'}{2}\frac{W}{L}(V_{GS} - V_T)^2}$$

**With channel length modulation:**
$$\boxed{I_D = \frac{k'}{2}\frac{W}{L}(V_{GS} - V_T)^2(1 + \lambda V_{DS})}$$

### Saturation Voltage (Pinch-off)
$$\boxed{V_{DSAT} = V_{GS} - V_T}$$

### Inversion Layer Charge (Channel Charge)

$$Q_I(y) = -C_{ox}[V_{GS} - V_T - V_C(y)]$$

- At source (y=0): $V_C = 0$
- At drain (y=L): $V_C = V_{DS}$
- At pinch-off: $Q_I = 0$ when $V_C = V_{GS} - V_T$

### Effective Channel Length (with CLM)

$$L_{eff} = L - \Delta L$$

- ΔL = pinched-off region length
- $I_D \propto 1/L_{eff}$

## 7.3 Region Determination Flowchart

```
Is VGS > VT?
   NO  → CUTOFF (ID = 0)
   YES → Is VDS < VGS - VT?
            YES → LINEAR
            NO  → SATURATION
```

---

# SECTION 8: SHORT CHANNEL EFFECTS (Problem 4)

## 8.1 Threshold Voltage Roll-off (Charge Sharing)

$$\boxed{V_{T0(short)} = V_{T0(long)} - \Delta V_{T0}}$$

**Simplified form:**
$$\Delta V_{T0} = \frac{|Q_{B0}|}{C_{ox}} \cdot \frac{\Delta L_S + \Delta L_D}{2L}$$

**Detailed form:**
$$\boxed{\Delta V_{T0} = \frac{|Q_{B0}|}{C_{ox}} \cdot \frac{x_j}{L}\left[\sqrt{1 + \frac{2x_{dS}}{x_j}} + \sqrt{1 + \frac{2x_{dD}}{x_j}} - 2\right]}$$

## 8.2 Junction Depletion Depths

**Source side:**
$$\boxed{x_{dS} = \sqrt{\frac{2\varepsilon_{Si}\phi_0}{qN_A}}}$$

**Drain side (includes VDS):**
$$\boxed{x_{dD} = \sqrt{\frac{2\varepsilon_{Si}(\phi_0 + V_{DS})}{qN_A}}}$$

**Built-in junction potential:**
$$\boxed{\phi_0 = \frac{kT}{q}\ln\left(\frac{N_A \cdot N_D}{n_i^2}\right) = 0.026 \ln\left(\frac{N_A \cdot N_D}{(1.45 \times 10^{10})^2}\right)}$$

### Edge Cases
- If $V_{DS} = 0$: $x_{dS} = x_{dD}$ (symmetric)
- Short channel **always lowers** VT (ΔVT0 is subtracted)
- Ion implant **adds** to VT before SCE correction

## 8.3 Velocity Saturation

$$v = \frac{\mu_n E}{1 + E/E_c}$$

- $E_c$ (critical field) ≈ 10⁴ V/cm for electrons
- At high fields, $v \rightarrow v_{sat} \approx 10^7$ cm/s

**Modified saturation voltage:**
$$V_{DSAT} = \frac{(V_{GS} - V_T) \cdot E_c \cdot L}{(V_{GS} - V_T) + E_c \cdot L}$$

## 8.4 DIBL (Drain-Induced Barrier Lowering)

$$V_T = V_{T0} - \eta \cdot V_{DS}$$

- η (DIBL coefficient) ≈ 0.01 - 0.1

## 8.5 Narrow Channel Effect

$$\boxed{\Delta V_{T0(narrow)} = \frac{q N_A x_{dm}}{C_{ox}} \cdot K \cdot \frac{x_{dm}}{W}}$$

- K ≈ π/2 for semicircular edge depletion
- Effect: **Increases** VT for narrow devices
- Proportional to (xdm/W)

## 8.6 Mobility Degradation

**Vertical field effect:**
$$\mu_n = \frac{\mu_{n0}}{1 + \theta(V_{GS} - V_T)}$$

Where θ ≈ 0.01 - 0.1 V⁻¹

**High lateral field effect:**
$$\mu_{eff} = \frac{\mu_n}{1 + \mu_n V_{DS}/(v_{sat} L)}$$

## 8.7 Subthreshold Current

$$\boxed{I_{sub} \propto \exp\left(\frac{q(V_{GS} - V_T)}{nkT}\right)}$$

- n = subthreshold swing factor (typically 1.0-1.5)
- Subthreshold swing: S = n × 60 mV/decade (at 300K)
- Ideal: S = 60 mV/decade

---

# SECTION 9: MOSFET CAPACITANCES (Problem 5 & 6)

## 9.1 Gate Overlap Capacitances (Always Present)

$$\boxed{C_{GSO} = C_{GDO} = C_{ox} \cdot W \cdot L_D}$$

Where $L_D$ = overlap length (lateral diffusion)

## 9.2 Gate-Channel Capacitances (By Region)

| Region | $C_{gs}$ | $C_{gd}$ | $C_{gb}$ |
|--------|----------|----------|----------|
| **Cutoff** | 0 | 0 | $C_{ox} \cdot W \cdot L$ |
| **Linear** | $\frac{1}{2}C_{ox} \cdot W \cdot L$ | $\frac{1}{2}C_{ox} \cdot W \cdot L$ | 0 |
| **Saturation** | $\frac{2}{3}C_{ox} \cdot W \cdot L$ | 0 | 0 |

**Total capacitances (including overlap):**
- Cutoff: $C_{GS} = C_{ox}WL_D$, $C_{GD} = C_{ox}WL_D$, $C_{GB} = C_{ox}WL$
- Linear: $C_{GS} = C_{ox}W(\frac{L}{2} + L_D)$, $C_{GD} = C_{ox}W(\frac{L}{2} + L_D)$
- Saturation: $C_{GS} = C_{ox}W(\frac{2L}{3} + L_D)$, $C_{GD} = C_{ox}WL_D$

## 9.3 Junction Capacitance

**Built-in potential:**
$$\boxed{\phi_0 = \frac{kT}{q}\ln\left(\frac{N_A \cdot N_D}{n_i^2}\right)}$$

**Zero-bias capacitance (per unit area):**
$$\boxed{C_{j0} = \sqrt{\frac{q\varepsilon_{Si}}{2\phi_0} \cdot \frac{N_A N_D}{N_A + N_D}}}$$

For $N_D \gg N_A$ (heavily doped S/D):
$$C_{j0} \approx \sqrt{\frac{q\varepsilon_{Si}N_A}{2\phi_0}}$$

**Voltage-dependent capacitance:**
$$\boxed{C_j = \frac{C_{j0}}{(1 - V/\phi_0)^m}}$$

| Junction Type | Grading Coefficient (m) |
|---------------|------------------------|
| Abrupt | 0.5 |
| Linearly graded | 0.33 |

### Sign Convention for V
- **Reverse bias**: V is **negative** (makes $1 - V/\phi_0 > 1$, reduces $C_j$)
- **Forward bias**: V is **positive** (increases $C_j$)

## 9.4 Large-Signal Equivalent Capacitance

**Voltage equivalence factor (for m = 0.5, abrupt junction):**
$$\boxed{K_{eq} = \frac{2\phi_0}{V_2 - V_1}\left[\sqrt{1 - \frac{V_1}{\phi_0}} - \sqrt{1 - \frac{V_2}{\phi_0}}\right]}$$

**Equivalent capacitance for voltage swing V₁ to V₂:**
$$\boxed{C_{eq} = C_{j0} \cdot A \cdot K_{eq}}$$

## 9.5 Sidewall Junction Capacitance

**Sidewall zero-bias capacitance (per unit length):**
$$\boxed{C_{jsw0} = C_{j0,sw} \cdot x_j}$$

**Total sidewall capacitance:**
$$C_{sw} = C_{jsw0} \cdot P \cdot K_{eq,sw}$$

Where P = perimeter of diffusion region

## 9.6 Source-Drain Series Resistance

$$\boxed{R_{SD} = R_{sheet} \cdot \frac{L_{diff}}{W} + R_C}$$

- Rsheet = sheet resistance of diffusion (Ω/□)
- Ldiff = length of diffusion region
- RC = contact resistance

**Effect on drain current:**
$$I_D = I_{D,ideal} \cdot \frac{1}{1 + g_m R_{SD}}$$

---

# SECTION 10: MOSFET SCALING

## 10.1 Scaling Factor S

$$\text{New dimension} = \frac{\text{Old dimension}}{S}$$

## 10.2 Scaling Comparison Table

| Parameter | Full Scaling | Constant Voltage |
|-----------|-------------|------------------|
| W, L, tox, xj | ÷ S | ÷ S |
| VDD, VT | **÷ S** | **× 1** |
| Doping (NA, ND) | × S | **× S²** |
| Electric field | × 1 | **× S** |
| Cox | × S | × S |
| Current (ID) | ÷ S | **× S** |
| Power/device | **÷ S²** | **× S** |
| Power density | × 1 | **× S³** ⚠️ |
| Delay | ÷ S | ÷ S |

### Key Insight
- **Full scaling**: Power density constant, but need scaled voltages
- **Constant voltage**: Heat problem (power density × S³)!

---

# SECTION 11: SIGN CONVENTIONS SUMMARY

| Parameter | NMOS (P-sub) | PMOS (N-sub) |
|-----------|--------------|--------------|
| Substrate type | P-type | N-type |
| Channel carriers | Electrons | Holes |
| ΦF (substrate) | **Negative** | **Positive** |
| VT0 (enhancement) | **Positive** | **Negative** |
| QB0 | **Negative** | **Positive** |
| γ | **Positive** | **Negative** |
| VSB (typical) | ≥ 0 | ≤ 0 |
| VGS to turn ON | > VT (positive) | < VT (negative) |
| Ion implant (P-type) | Raises VT | Lowers |VT| |

---

# SECTION 12: COMMON NUMERICAL VALUES

## 12.1 "Plug and Chug" Ready Values

| Quantity | For NA = 10¹⁶ cm⁻³ |
|----------|-------------------|
| ΦF | -0.35 V |
| 2\|ΦF\| | 0.70 V |
| φ₀ (with ND = 10¹⁷) | ~0.76 V |
| xdm | ~0.3 μm |

| Quantity | For tox = 10 nm |
|----------|-----------------|
| Cox | 3.45 × 10⁻⁷ F/cm² |
| Cox | 34.5 fF/μm² |

| Quantity | For μn = 400 cm²/V·s, Cox = 3.45×10⁻⁷ |
|----------|--------------------------------------|
| k' | 138 μA/V² |

## 12.2 Useful Approximations

$$\ln(10) \approx 2.3$$
$$\ln(10^6) \approx 13.8$$
$$\sqrt{2} \approx 1.41$$
$$\sqrt{3} \approx 1.73$$

---

# FINAL CHECKLIST

Before submitting any answer:

- [ ] ΦF has correct sign (negative for P-type)
- [ ] tox converted to cm (1 nm = 10⁻⁷ cm)
- [ ] QB0 is negative, -QB0/Cox is positive
- [ ] NI is in cm⁻² (areal), NA is in cm⁻³ (volume)
- [ ] Checked VGS > VT before using ID formula
- [ ] Compared VDS to (VGS - VT) for region
- [ ] ½ factor only in saturation current formula
- [ ] Short-channel correction **subtracts** from VT
- [ ] Ion implant (P-type into NMOS) **adds** to VT

---

**GOOD LUCK! 🎓**
