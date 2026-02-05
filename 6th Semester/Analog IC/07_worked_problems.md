# 📝 Worked Problems

> **All Exam Questions from Question Papers — Fully Solved with Concept Links**

---

## How to Use This Section

Each problem is:
1. **Labeled by source** (Paper 1, 2, or 3)
2. **Tagged with concepts used** → Click links to review those topics
3. **Solved step-by-step** with all calculations shown
4. **Boxed answers** for quick reference

---

# 📄 Question Paper 1

---

## Q1. Oxide Capacitance & Transconductance (10M)

**Useful Data**: ni = 1.45×10¹⁰ cm⁻³, εsio2 = 3.9, εsi = 11.8, ε0 = 8.854×10⁻¹⁴ F/cm

A CMOS process produces gate oxides with tox = 100 Å. The FET carrier mobilities are μn = 550 cm²/V·s and μp = 210 cm²/V·s.

### Part (a): Calculate oxide capacitance per unit area

**Concepts Used**: [I/V Characteristics - Cox](./02_iv_characteristics.md#2-oxide-capacitance-critical-for-calculations), [Formula Sheet - Oxide Capacitance](./08_formula_sheet_ultimate.md#oxide-capacitance)

**Solution**:

**Step 1**: Convert tox to cm
$$t_{ox} = 100 \text{ Å} = 100 \times 10^{-8} \text{ cm} = 10^{-6} \text{ cm}$$

**Step 2**: Calculate Cox
$$C_{ox} = \frac{\varepsilon_0 \cdot \varepsilon_{sio2}}{t_{ox}} = \frac{8.854 \times 10^{-14} \times 3.9}{10^{-6}}$$
$$C_{ox} = \frac{3.453 \times 10^{-13}}{10^{-6}} = 3.453 \times 10^{-7} \text{ F/cm}^2$$

$$\boxed{C_{ox} = 3.45 \times 10^{-7} \text{ F/cm}^2 = 34.5 \text{ fF/μm}^2}$$

---

### Part (b): Find process and device transconductance for nFET and pFET

**Concepts Used**: [Transconductance](./03_transconductance.md#3-process-vs-device-transconductance)

**Solution**:

**Process Transconductance (kn, kp)**:
$$k_n = \mu_n C_{ox} = 550 \times 3.453 \times 10^{-7}$$
$$\boxed{k_n = 1.90 \times 10^{-4} \text{ A/V}^2 = 190 \text{ μA/V}^2}$$

$$k_p = \mu_p C_{ox} = 210 \times 3.453 \times 10^{-7}$$
$$\boxed{k_p = 7.25 \times 10^{-5} \text{ A/V}^2 = 72.5 \text{ μA/V}^2}$$

> **Note**: Device transconductance βn = kn(W/L) requires specific W/L values.

---

## Q2. Drain Current with Body Effect (10M)

An nFET with W = 10 μm and L = 0.35 μm is built in a process where kn = 110 μA/V² and VTH0 = 0.70V. Assume γ = 0.08 V^(1/2), and 2|φF| = 0.58 V.

Calculate drain current if VGS = 2V, VDS = 2V, and VSB = 1V.

**Concepts Used**: [Second-Order Effects - Body Effect](./04_second_order_effects.md#1-body-effect-back-gate-effect), [I/V Characteristics - Saturation](./02_iv_characteristics.md#6-saturation-region)

**Solution**:

**Step 1**: Calculate VTH with body effect
$$V_{TH} = V_{TH0} + \gamma\left(\sqrt{2|\phi_F| + V_{SB}} - \sqrt{2|\phi_F|}\right)$$
$$V_{TH} = 0.70 + 0.08\left(\sqrt{0.58 + 1} - \sqrt{0.58}\right)$$
$$V_{TH} = 0.70 + 0.08\left(\sqrt{1.58} - \sqrt{0.58}\right)$$
$$V_{TH} = 0.70 + 0.08(1.257 - 0.762)$$
$$V_{TH} = 0.70 + 0.08 \times 0.495 = 0.70 + 0.0396$$
$$\boxed{V_{TH} = 0.74 \text{ V}}$$

**Step 2**: Check operating region
$$V_{GS} - V_{TH} = 2 - 0.74 = 1.26 \text{ V}$$
$$V_{DS} = 2 \text{ V} > 1.26 \text{ V} \rightarrow \textbf{Saturation}$$

**Step 3**: Calculate ID (saturation)
$$I_D = \frac{1}{2} k_n \frac{W}{L}(V_{GS} - V_{TH})^2$$
$$I_D = \frac{1}{2} \times 110 \times 10^{-6} \times \frac{10}{0.35} \times (1.26)^2$$
$$I_D = 55 \times 10^{-6} \times 28.57 \times 1.588$$
$$I_D = 55 \times 28.57 \times 1.588 \times 10^{-6}$$
$$\boxed{I_D = 2.495 \text{ mA} \approx 2.5 \text{ mA}}$$

---

## Q3. Rn and Rp with Width Sizing (10M)

A process has tox = 9.5 nm. The particle mobilities are μn = 540 cm²/V·s and μp = 220 cm²/V·s. An nFET and pFET are made both with W = 12 μm, L = 0.35 μm. Both have gate voltage VG = 3.3V, while VTHn = 0.65V and VTHp = -0.74V.

### Part (a): Find Rn and Rp for the two transistors

**Concepts Used**: [I/V Characteristics - On-Resistance](./02_iv_characteristics.md#5-deep-triode-region)

**Solution**:

**Step 1**: Calculate Cox
$$t_{ox} = 9.5 \text{ nm} = 9.5 \times 10^{-7} \text{ cm}$$
$$C_{ox} = \frac{3.9 \times 8.854 \times 10^{-14}}{9.5 \times 10^{-7}} = \frac{3.453 \times 10^{-13}}{9.5 \times 10^{-7}}$$
$$C_{ox} = 3.635 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Calculate Rn (NMOS)
Assuming VS = 0 for NMOS: VGS = VG = 3.3V
$$R_n = \frac{1}{\mu_n C_{ox} \frac{W}{L}(V_{GS} - V_{THn})}$$
$$R_n = \frac{1}{540 \times 3.635 \times 10^{-7} \times \frac{12 \times 10^{-4}}{0.35 \times 10^{-4}} \times (3.3 - 0.65)}$$
$$R_n = \frac{1}{540 \times 3.635 \times 10^{-7} \times 34.29 \times 2.65}$$
$$R_n = \frac{1}{1.96 \times 10^{-4} \times 34.29 \times 2.65}$$
$$R_n = \frac{1}{17.82 \times 10^{-3}}$$
$$\boxed{R_n = 56.1 \text{ Ω}}$$

**Step 3**: Calculate Rp (PMOS)
For PMOS with source at VDD = 3.3V: |VGS| = 3.3 - 0 = 3.3V, |VGS| - |VTHp| = 3.3 - 0.74 = 2.56V
$$R_p = \frac{1}{\mu_p C_{ox} \frac{W}{L}(|V_{GS}| - |V_{THp}|)}$$
$$R_p = \frac{1}{220 \times 3.635 \times 10^{-7} \times 34.29 \times 2.56}$$
$$R_p = \frac{1}{7.02 \times 10^{-3}}$$
$$\boxed{R_p = 142.5 \text{ Ω}}$$

---

### Part (b): Find pFET width for Rp = 0.8Rn

**Concepts Used**: [I/V Characteristics - On-Resistance](./02_iv_characteristics.md#5-deep-triode-region)

**Solution**:

For equal overdrive and resistance scaling:
$$R_p = \frac{1}{\mu_p C_{ox} \frac{W_p}{L}(|V_{GS}| - |V_{THp}|)}$$

To get Rp = 0.8Rn:
$$\frac{R_n}{R_p} = \frac{1}{0.8} = 1.25$$

$$\frac{\mu_p (W_p/L)(|V_{GS}| - |V_{THp}|)}{\mu_n (W_n/L)(V_{GS} - V_{THn})} = 1.25$$

$$\frac{W_p}{W_n} = 1.25 \times \frac{\mu_n}{\mu_p} \times \frac{(V_{GS} - V_{THn})}{(|V_{GS}| - |V_{THp}|)}$$
$$\frac{W_p}{W_n} = 1.25 \times \frac{540}{220} \times \frac{2.65}{2.56}$$
$$\frac{W_p}{W_n} = 1.25 \times 2.45 \times 1.035 = 3.17$$

$$\boxed{W_p = 3.17 \times 12 = 38.1 \text{ μm}}$$

---

## Q4. CMOS Process Types (10M)

**Elaborate the nwell, pwell, twin tub CMOS process, discuss with diagrams and comment on role of doping in the process.**

**Concepts Used**: [MOS Device Physics - CMOS Technology](./01_mos_device_physics.md#4-cmos-technology)

**Solution**:

### N-Well Process

The most common CMOS process. Uses p-type substrate with n-wells for PMOS devices.

```
        NMOS Region            PMOS Region
    ┌─────────────────┬────────────────────┐
    │                 │                    │
    │  n+  Gate  n+   │   p+  Gate  p+     │
    │  S  [poly] D    │   S  [poly] D      │
    │─────────────────┤                    │
    │                 │      n-well        │
    │    (channel)    │        ↓           │
    │                 │    ┌───────────┐   │
    │   p-substrate   │    │           │   │
    │                 │    │  (n-type) │   │
    └─────────────────┴────┴───────────┴───┘
              p-substrate
```

**Advantages**:
- Lower fabrication complexity
- Good noise immunity
- Well-established process

### P-Well Process

Uses n-type substrate with p-wells for NMOS devices.

```
        NMOS Region            PMOS Region
    ┌─────────────────┬────────────────────┐
    │                 │                    │
    │  n+  Gate  n+   │   p+  Gate  p+     │
    │  S  [poly] D    │   S  [poly] D      │
    │                 ├────────────────────│
    │    p-well       │                    │
    │      ↓          │      (channel)     │
    │  ┌─────────┐    │                    │
    │  │ (p-type)│    │   n-substrate      │
    └──┴─────────┴────┴────────────────────┘
              n-substrate
```

### Twin-Tub (Dual-Well) Process

Both n-well and p-well on a lightly doped substrate.

```
    ┌─────────────────┬────────────────────┐
    │     NMOS        │        PMOS        │
    │  n+  Gate  n+   │   p+  Gate  p+     │
    │  S  [poly] D    │   S  [poly] D      │
    │─────────────────┼────────────────────│
    │    p-well       │      n-well        │
    │  ┌─────────┐    │    ┌───────────┐   │
    │  │ (p-type)│    │    │ (n-type)  │   │
    └──┴─────────┴────┴────┴───────────┴───┘
         Lightly doped substrate (p⁻ or n⁻)
```

**Advantages**:
- Independent optimization of NMOS and PMOS
- Better threshold control
- Used in modern high-performance CMOS

### Role of Doping

| Region | Doping Level | Purpose |
|--------|--------------|---------|
| Source/Drain | Heavy (n⁺ or p⁺, ~10¹⁹-10²⁰ cm⁻³) | Low resistance, good ohmic contacts |
| Channel | Light (~10¹⁵-10¹⁷ cm⁻³) | Controllable threshold voltage |
| Well | Medium (~10¹⁶-10¹⁷ cm⁻³) | Device isolation, body effect control |
| Substrate | Light (~10¹⁵ cm⁻³) | Base material |

$$\boxed{\text{Heavy doping} \rightarrow \text{Low resistance, High } V_{TH}}$$
$$\boxed{\text{Light doping} \rightarrow \text{Better threshold control, Lower } V_{TH}}$$

---

## Q5. SPICE Model and Level-1 Equations (10M)

**Elaborate SPICE model and using Level-1 equations identify various parameters defined to characterize the NMOS and PMOS devices.**

**Concepts Used**: [Device Models - SPICE Parameters](./05_device_models.md#6-spice-level-1-parameters)

**Solution**:

### SPICE Level-1 Model Equations

**Cutoff Region** (VGS < VTH):
$$I_D = 0$$

**Triode Region** (VGS > VTH, VDS < VGS - VTH):
$$I_D = \mu C_{ox} \frac{W}{L}\left[(V_{GS} - V_{TH})V_{DS} - \frac{V_{DS}^2}{2}\right]$$

**Saturation Region** (VGS > VTH, VDS ≥ VGS - VTH):
$$I_D = \frac{1}{2}\mu C_{ox} \frac{W}{L}(V_{GS} - V_{TH})^2(1 + \lambda V_{DS})$$

### SPICE Level-1 Parameters

| Parameter | Symbol | NMOS Typical | PMOS Typical | Units |
|-----------|--------|--------------|--------------|-------|
| Zero-bias threshold | VTO | 0.7 | -0.8 | V |
| Body effect coeff. | GAMMA | 0.45 | 0.4 | V^(1/2) |
| Surface potential | PHI | 0.9 | 0.8 | V |
| Mobility | UO | 350 | 100 | cm²/V·s |
| CLM coefficient | LAMBDA | 0.1 | 0.2 | V⁻¹ |
| Oxide thickness | TOX | 9×10⁻⁹ | 9×10⁻⁹ | m |
| Substrate doping | NSUB | 9×10¹⁴ | 5×10¹⁴ | cm⁻³ |

### Derived Parameters

$$C_{ox} = \frac{\varepsilon_{ox}}{TOX}$$

$$V_{TH} = VTO + GAMMA\left(\sqrt{PHI + V_{SB}} - \sqrt{PHI}\right)$$

$$\boxed{\text{Level-1 is suitable for hand calculations and basic circuit simulation}}$$

---

# 📄 Question Paper 2

---

## Q1. Oxide Capacitance & Transconductance (10M)

An n-channel MOSFET has μn = 560 cm²/V·s and tox = 90 Å. VG = 2.5V, VTH = 0.65V, L = 0.25 μm, W = 2 μm.

### Part (a): Calculate Cox in F/cm²

**Concepts Used**: [I/V Characteristics - Cox](./02_iv_characteristics.md#2-oxide-capacitance-critical-for-calculations)

**Solution**:
$$t_{ox} = 90 \text{ Å} = 90 \times 10^{-8} \text{ cm} = 9 \times 10^{-7} \text{ cm}$$
$$C_{ox} = \frac{3.9 \times 8.854 \times 10^{-14}}{9 \times 10^{-7}} = \frac{3.453 \times 10^{-13}}{9 \times 10^{-7}}$$
$$\boxed{C_{ox} = 3.84 \times 10^{-7} \text{ F/cm}^2}$$

---

### Part (b): Find process transconductance kn

**Concepts Used**: [Transconductance](./03_transconductance.md#3-process-vs-device-transconductance)

**Solution**:
$$k_n = \mu_n C_{ox} = 560 \times 3.84 \times 10^{-7}$$
$$\boxed{k_n = 2.15 \times 10^{-4} \text{ A/V}^2 = 215 \text{ μA/V}^2}$$

---

### Part (c): Find device transconductance βn

**Concepts Used**: [Transconductance](./03_transconductance.md#3-process-vs-device-transconductance)

**Solution**:
$$\beta_n = k_n \frac{W}{L} = 215 \times \frac{2}{0.25}$$
$$\boxed{\beta_n = 215 \times 8 = 1720 \text{ μA/V}^2}$$

---

## Q2. pFET Resistance and Region (10M)

A pFET is described by μp = 220 cm²/V·s and VDD = 3.3V, VG = 1.0V, |VTp| = 0.8V, W = 14 μm, L = 0.5 μm, and tox = 11.5 nm.

Find the pFET resistance Rp of the device, and discuss region of its operation.

**Concepts Used**: [I/V Characteristics - On-Resistance](./02_iv_characteristics.md#5-deep-triode-region), [I/V Characteristics - Region ID](./02_iv_characteristics.md#1-the-three-operating-regions)

**Solution**:

**Step 1**: Calculate Cox
$$t_{ox} = 11.5 \text{ nm} = 11.5 \times 10^{-7} \text{ cm}$$
$$C_{ox} = \frac{3.9 \times 8.854 \times 10^{-14}}{11.5 \times 10^{-7}} = 3.0 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Determine PMOS voltages
- Source at VDD = 3.3V (highest potential for PMOS)
- VGS = VG - VS = 1.0 - 3.3 = -2.3V
- |VGS| = 2.3V

**Step 3**: Check if ON and find overdrive
$$|V_{GS}| - |V_{THp}| = 2.3 - 0.8 = 1.5V > 0 \rightarrow \textbf{ON}$$

**Step 4**: Region of operation
For Rp calculation, assume deep triode (VDS small compared to overdrive).

**Step 5**: Calculate Rp
$$R_p = \frac{1}{\mu_p C_{ox} \frac{W}{L}(|V_{GS}| - |V_{THp}|)}$$
$$R_p = \frac{1}{220 \times 3.0 \times 10^{-7} \times \frac{14 \times 10^{-4}}{0.5 \times 10^{-4}} \times 1.5}$$
$$R_p = \frac{1}{220 \times 3.0 \times 10^{-7} \times 28 \times 1.5}$$
$$R_p = \frac{1}{2.77 \times 10^{-3}}$$
$$\boxed{R_p = 361 \text{ Ω}}$$

$$\boxed{\text{Region: Deep Triode (for resistance calculation)}}$$

---

## Q3. Body Effect Coefficient and VTH (10M)

An nFET has tox = 120 Å. The p-type bulk region is doped with boron at NA = 8×10¹⁴ cm⁻³. VTH0n = 0.55V and W/L = 10.

### Part (a): Calculate body bias coefficient γ

**Concepts Used**: [Second-Order Effects - Body Effect](./04_second_order_effects.md#1-body-effect-back-gate-effect), [Formula Sheet](./08_formula_sheet_ultimate.md#body-effect-coefficient)

**Solution**:

$$\gamma = \frac{\sqrt{2q\varepsilon_{si}N_A}}{C_{ox}}$$

**Step 1**: Calculate Cox
$$t_{ox} = 120 \text{ Å} = 1.2 \times 10^{-6} \text{ cm}$$
$$C_{ox} = \frac{3.9 \times 8.854 \times 10^{-14}}{1.2 \times 10^{-6}} = 2.88 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Calculate numerator
$$\sqrt{2q\varepsilon_{si}N_A} = \sqrt{2 \times 1.6 \times 10^{-19} \times 1.04 \times 10^{-12} \times 8 \times 10^{14}}$$
$$= \sqrt{2.66 \times 10^{-16}} = 1.63 \times 10^{-8} \text{ C/cm}^2$$

**Step 3**: Calculate γ
$$\gamma = \frac{1.63 \times 10^{-8}}{2.88 \times 10^{-7}}$$
$$\boxed{\gamma = 0.057 \text{ V}^{1/2}}$$

---

### Part (b): Device threshold if VSB = 2V

**Concepts Used**: [Second-Order Effects - Body Effect](./04_second_order_effects.md#1-body-effect-back-gate-effect)

**Solution**:

First, calculate 2ΦF:
$$\Phi_F = \frac{kT}{q}\ln\frac{N_A}{n_i} = 0.026 \times \ln\frac{8 \times 10^{14}}{1.45 \times 10^{10}}$$
$$\Phi_F = 0.026 \times \ln(5.52 \times 10^{4}) = 0.026 \times 10.92 = 0.284 \text{ V}$$
$$2\Phi_F = 0.568 \text{ V}$$

$$V_{TH} = V_{TH0} + \gamma\left(\sqrt{2\Phi_F + V_{SB}} - \sqrt{2\Phi_F}\right)$$
$$V_{TH} = 0.55 + 0.057\left(\sqrt{0.568 + 2} - \sqrt{0.568}\right)$$
$$V_{TH} = 0.55 + 0.057(1.603 - 0.754)$$
$$V_{TH} = 0.55 + 0.057 \times 0.849 = 0.55 + 0.048$$
$$\boxed{V_{TH} = 0.598 \text{ V} \approx 0.6 \text{ V}}$$

---

### Part (c): Calculate drain current with VGS = 3V, VDS = 3V, VSB = 3V

**Concepts Used**: [I/V Characteristics - Saturation](./02_iv_characteristics.md#6-saturation-region), [Second-Order Effects](./04_second_order_effects.md)

**Solution**:

**Step 1**: Find VTH at VSB = 3V
$$V_{TH} = 0.55 + 0.057\left(\sqrt{0.568 + 3} - \sqrt{0.568}\right)$$
$$V_{TH} = 0.55 + 0.057(1.889 - 0.754) = 0.55 + 0.065$$
$$V_{TH} = 0.615 \text{ V}$$

**Step 2**: Check region
$$V_{GS} - V_{TH} = 3 - 0.615 = 2.385 \text{ V}$$
$$V_{DS} = 3 \text{ V} > 2.385 \text{ V} \rightarrow \textbf{Saturation}$$

**Step 3**: Calculate ID
Need kn from Cox:
$$k_n = \mu_n C_{ox} = 540 \times 2.88 \times 10^{-7} = 1.56 \times 10^{-4} \text{ A/V}^2$$

$$I_D = \frac{1}{2} k_n \frac{W}{L}(V_{GS} - V_{TH})^2$$
$$I_D = \frac{1}{2} \times 1.56 \times 10^{-4} \times 10 \times (2.385)^2$$
$$I_D = 0.78 \times 10^{-3} \times 5.688 = 4.44 \times 10^{-3}$$
$$\boxed{I_D = 4.44 \text{ mA}}$$

---

## Q4. Layout Structure (10M)

**Elaborate the layout structure of PMOS and NMOS device using diagram, and discuss on process type.**

**Concepts Used**: [Device Models - Layout](./05_device_models.md#1-mos-device-layout), [MOS Device Physics - CMOS](./01_mos_device_physics.md#4-cmos-technology)

**Solution**:

### NMOS Layout (Top View)

```
                Metal Contact     Gate (Poly)     Metal Contact
                    ↓                ↓                ↓
    ┌───────────────┬───────────────┬───────────────┐
    │               │               │               │
    │    ●   ●      │     ████████  │      ●   ●    │
    │  Contact      │      Poly    │    Contact    │
    │  Windows      │      Gate    │    Windows    │
    │               │               │               │
    │    n+ (S)     │   Channel    │    n+ (D)     │
    │               │   (p-sub)    │               │
    └───────────────┴───────────────┴───────────────┘
    ←──── Area ────→←─── L ───→←──── E ────→
           W (perpendicular to page)
```

### PMOS Layout (Top View)

```
    Same structure as NMOS, but:
    - n+ Source/Drain → p+ Source/Drain
    - p-substrate → n-well
    
    ┌───────────────────────────────────────────────┐
    │              N-WELL BOUNDARY                  │
    │  ┌───────────────┬─────────┬───────────────┐  │
    │  │    p+ (S)     │  Gate   │    p+ (D)     │  │
    │  │   Contact     │  Poly   │   Contact     │  │
    │  └───────────────┴─────────┴───────────────┘  │
    │              N-WELL REGION                    │
    └───────────────────────────────────────────────┘
```

### Cross-Section Comparison

```
NMOS Cross-Section:
                    Gate (Poly)
                       │
    ┌──────────────────┼──────────────────┐
    │     n+ (S)       │      n+ (D)      │  ← Heavy doping
    ├──────────────────┼──────────────────┤
    │                 SiO₂                │  ← Gate oxide (tox)
    ├──────────────────┼──────────────────┤
    │        p-type substrate             │
    │         (channel region)            │
    └─────────────────────────────────────┘

PMOS Cross-Section (in n-well process):
                    Gate (Poly)
                       │
    ┌──────────────────┼──────────────────┐
    │     p+ (S)       │      p+ (D)      │  ← Heavy doping
    ├──────────────────┼──────────────────┤
    │                 SiO₂                │
    ├──────────────────┼──────────────────┤
    │           n-well region             │
    │         (channel region)            │
    └─────────────────────────────────────┘
           (in p-substrate)
```

### Key Layout Dimensions

| Dimension | Symbol | Description |
|-----------|--------|-------------|
| W | Width | Channel width, perpendicular to current flow |
| L | Length | Channel length, distance S to D |
| E | Extension | Drain extension for contacts |
| A | Area | Source/Drain area for junction capacitance |

$$\boxed{C_{junction} = W \cdot E \cdot C_j + 2(W + E) \cdot C_{jsw}}$$

---

## Q5. Second-Order Effects (10M)

**Discuss different second order effects in a MOS device and elaborate their implication in design using current voltage equation.**

**Concepts Used**: [Second-Order Effects](./04_second_order_effects.md) (entire module)

**Solution**:

### 1. Body Effect (Back-Gate Effect)

**Cause**: Non-zero source-bulk voltage (VSB ≠ 0)

**Effect**: Threshold voltage increases with VSB

$$V_{TH} = V_{TH0} + \gamma\left(\sqrt{2\Phi_F + V_{SB}} - \sqrt{2\Phi_F}\right)$$

**Design Implications**:
- Reduces overdrive voltage when source is elevated
- Decreases gm for given gate voltage
- Must account for in stacked transistor circuits

---

### 2. Channel-Length Modulation (CLM)

**Cause**: Pinch-off point moves toward source as VDS increases

**Effect**: Current increases slightly with VDS in saturation

$$I_D = \frac{1}{2}\mu C_{ox}\frac{W}{L}(V_{GS} - V_{TH})^2(1 + \lambda V_{DS})$$

**Design Implications**:
- Finite output resistance: rO = 1/(λID)
- Limits voltage gain
- More severe in short-channel devices (λ ∝ 1/L)

---

### 3. Subthreshold Conduction

**Cause**: Weak inversion when VGS < VTH

**Effect**: Exponential (not quadratic) dependence on VGS

$$I_D = I_0 \exp\left(\frac{V_{GS}}{\xi V_T}\right)$$

**Design Implications**:
- Leakage current in "OFF" state
- Critical for low-power design
- Useful for ultra-low-power analog circuits

---

### Summary Table

| Effect | Parameter | Impact on ID | Design Consideration |
|--------|-----------|--------------|---------------------|
| Body Effect | γ | Reduces (via VTH↑) | Account in stacked devices |
| CLM | λ | Increases slightly | Limits gain, use longer L |
| Subthreshold | ξ | Exponential leakage | Power/speed tradeoff |

$$\boxed{\text{Complete equation: } I_D = \frac{1}{2}\mu C_{ox}\frac{W}{L}(V_{GS} - V_{TH}(V_{SB}))^2(1 + \lambda V_{DS})}$$

---

# 📄 Question Paper 3

---

## Q1. Drain Current with Body Effect (10M)

An nFET with W = 10 μm and L = 0.35 μm is built in a process where kn = 110 μA/V² and VTn0 = 0.70V. Assume γ = 0.08 V^(1/2), and 2|φF| = 0.58 V. Calculate drain current if VGS = 2V, VDS = 2V and VSB = 1V.

*(Same as Paper 1, Q2)*

**Concepts Used**: [Second-Order Effects - Body Effect](./04_second_order_effects.md#1-body-effect-back-gate-effect), [I/V Characteristics](./02_iv_characteristics.md)

**Solution**: (See Paper 1, Q2)

$$\boxed{I_D = 2.5 \text{ mA}}$$

---

## Q2. Rn and Rp with Width Sizing (10M)

*(Same as Paper 1, Q3)*

**Part (a)**: 
$$\boxed{R_n = 56.1 \text{ Ω}, \quad R_p = 142.5 \text{ Ω}}$$

**Part (b)**:
$$\boxed{W_p = 38.1 \text{ μm}}$$

---

## Q3. Layout Structure (10M)

*(Same as Paper 2, Q4)*

**Concepts Used**: [Device Models - Layout](./05_device_models.md#1-mos-device-layout)

See Paper 2, Q4 for complete solution with diagrams.

---

## Q4. Small-Signal Gain Analysis - Fig. 1 (10M)

Find the gain of the circuit (Fig. 1) using small signal model. Vb is a DC voltage.

**Circuit**: CS stage with resistive load and current source bias

```
        VDD
         │
        RD
         │
    M2 ──┼──── Vout
         │
    Vb──┤
         │
 Vin ──┬─┤M1
       │ │
       │ └───
       │
      GND
```

**Concepts Used**: [Common-Source Stage](./06_common_source_stage.md#2-cs-with-resistive-load), [Device Models - Small-Signal](./05_device_models.md#3-small-signal-model)

**Solution**:

**Step 1**: Identify topology
- M1 is the input transistor (CS stage)
- M2 acts as current source (Vb is DC, so vgs2 = 0)
- RD is the load resistor

**Step 2**: Small-signal circuit
- M1: Current source gm1·vgs1 with parallel rO1
- M2: Only rO2 appears (since vgs2 = 0)
- RD in series

**Step 3**: Calculate output impedance looking into M2 drain
- M2 in saturation, gate fixed: looks like rO2

**Step 4**: Total output resistance
$$R_{out} = R_D \| r_{O1} \| r_{O2}$$

If rO1, rO2 >> RD:
$$R_{out} \approx R_D$$

**Step 5**: Voltage gain
$$\boxed{A_v = -g_{m1}(R_D \| r_{O1} \| r_{O2})}$$

If rO terms are much larger than RD:
$$\boxed{A_v \approx -g_{m1} R_D}$$

---

## Q5. Output Impedance - Fig. 2 (10M)

Find the output impedance of circuit given in Fig. 2, use small signal model.

**Circuit**: CS stage with source degeneration (R2) and resistive load (R1)

```
        VDD
         │
        R1
         │
    M2 ──┼──── Vo
         │
 Vin ──┬─┤M1
       │ │
      R2 │
       │ │
      GND─┘
```

**Concepts Used**: [Device Models - Small-Signal](./05_device_models.md#3-small-signal-model), [Common-Source Stage](./06_common_source_stage.md)

**Solution**:

**Step 1**: Set Vin = 0 (no input signal), apply test voltage Vx at output

**Step 2**: Small-signal model
- M1 has gm1·vgs with rO1
- M2 has gm2·vgs2 with rO2
- Looking into drain of M1 from output

**Step 3**: Looking into M1 drain (with R2 at source)

For a CS stage with source degeneration, output impedance seen at drain is:
$$R_{out,M1} = r_{O1}(1 + g_{m1}R_2) + R_2$$

(This is the drain-to-ground impedance including source degeneration boost)

**Step 4**: For M2 (if it's a load transistor)
$$R_{out,M2} = r_{O2}$$ (if gate is fixed)

**Step 5**: Total output impedance
$$\boxed{Z_{out} = R_1 \| r_{O2} \| [r_{O1}(1 + g_{m1}R_2) + R_2]}$$

Simplified (if R1 and rO values are comparable):
$$\boxed{Z_{out} = R_1 \| R_{out,M1} \| r_{O2}}$$

If R2 provides significant degeneration:
$$\boxed{Z_{out} \approx R_1 \| r_{O1}(1 + g_m R_2)}$$

> The source degeneration resistor R2 boosts the output impedance by factor (1 + gmR2).

---

# 📊 Summary: Question Types & Concept Coverage

| Question Type | Papers | Concepts Used |
|---------------|--------|---------------|
| Cox calculation | 1,2 | [I/V Chars](./02_iv_characteristics.md), [Formula Sheet](./08_formula_sheet_ultimate.md) |
| kn, βn calculation | 1,2 | [Transconductance](./03_transconductance.md) |
| ID with body effect | 1,2,3 | [Second-Order](./04_second_order_effects.md), [I/V Chars](./02_iv_characteristics.md) |
| Rn, Rp calculation | 1,3 | [I/V Chars](./02_iv_characteristics.md) |
| γ calculation | 2 | [Second-Order](./04_second_order_effects.md) |
| VTH with VSB | 2,3 | [Second-Order](./04_second_order_effects.md) |
| CMOS process | 1,3 | [MOS Physics](./01_mos_device_physics.md) |
| Layout structure | 2,3 | [Device Models](./05_device_models.md) |
| SPICE Level-1 | 1 | [Device Models](./05_device_models.md) |
| Second-order effects | 2 | [Second-Order](./04_second_order_effects.md) |
| Small-signal gain | 3 | [CS Stage](./06_common_source_stage.md) |
| Output impedance | 3 | [Device Models](./05_device_models.md), [CS Stage](./06_common_source_stage.md) |
