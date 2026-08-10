# ⚠️ Quirks and Gotchas

> **These are the mistakes that lose points. Don't be that student.**

---

## 📌 Unit Conversion Disasters

### The tox Trap

**Problem**: tox is often given in nm or Å, but formulas use cm.

| Given | Convert to cm | Multiply by |
|-------|---------------|-------------|
| 10 nm | 10⁻⁶ cm | 10⁻⁷ |
| 500 Å | 5×10⁻⁶ cm | 10⁻⁸ |
| 50 nm | 5×10⁻⁶ cm | 10⁻⁷ |

**Example mistake:**
```
✗ Cox = 3.45×10⁻¹³ / 50nm = ... (forgot to convert!)
✓ Cox = 3.45×10⁻¹³ / (50×10⁻⁷) = 6.9×10⁻⁸ F/cm²
```

### The W/L Trap

**Problem**: W and L might be in μm but you're dividing them.

**Good news**: If both are in the same unit, the ratio is dimensionless!
```
W = 10 μm, L = 1 μm → W/L = 10 (no units needed)
```

**But watch out**: If the problem asks for absolute values (like gm calculation), you may need consistent units.

### F/cm² vs F/m² 

The PDF uses **CGS (cm-based)** system. Textbooks sometimes use SI.

| Parameter | CGS | SI | Conversion |
|-----------|-----|----|--------------| 
| εox | 3.45×10⁻¹³ F/cm | 3.45×10⁻¹¹ F/m | ×10² |
| εSi | 1.04×10⁻¹² F/cm | 1.04×10⁻¹⁰ F/m | ×10² |
| Cox | F/cm² | F/m² | ×10⁴ |
| NA | cm⁻³ | m⁻³ | ×10⁶ |

> **Rule**: Stick with one system throughout the problem. The PDF uses CGS.

---

## 📌 Sign Convention Confusion

### ΦF Sign

**For P-type substrate (NMOS):**
$$\phi_F = -\frac{kT}{q}\ln\left(\frac{N_A}{n_i}\right)$$

- NA > ni (always true for doped Si)
- ln(NA/ni) is positive
- Negative sign in front
- **ΦF is NEGATIVE**

**Common mistake:**
```
✗ ΦF = +0.35 V (forgot the negative sign)
✓ ΦF = -0.35 V
```

### -2ΦF in VT0

Since ΦF is negative for P-type:
$$-2\phi_F = -2 \times (-0.35) = +0.70 \text{ V}$$

**This adds to VT0** — it's the voltage needed to bend bands for inversion.

### QB0 Sign

QB0 is the depletion charge — consists of **negative acceptor ions**:
$$Q_{B0} = -\sqrt{4q\varepsilon_{Si}N_A|\phi_F|} < 0$$

In the VT0 formula:
$$-\frac{Q_{B0}}{C_{ox}} = -\frac{(\text{negative})}{(\text{positive})} = \text{positive}$$

**This also adds to VT0.**

### Qox Sign

Oxide charge is typically **positive** (fixed positive charges):
$$Q_{ox} = q \cdot N_{ox} > 0$$

In the VT0 formula:
$$-\frac{Q_{ox}}{C_{ox}} = -\frac{(\text{positive})}{(\text{positive})} = \text{negative}$$

**This subtracts from VT0** — positive oxide charge makes inversion easier.

---

## 📌 Notation Nightmares

### The N-Family

| Symbol | Meaning | Units | Context |
|--------|---------|-------|---------|
| ni | Intrinsic carrier concentration | cm⁻³ | Physical constant (~1.45×10¹⁰) |
| NA | Acceptor (dopant) concentration | cm⁻³ | Substrate doping (P-type) |
| ND | Donor (dopant) concentration | cm⁻³ | Gate or S/D doping (N-type) |
| NI | Ion implant dose | **cm⁻²** | Surface/areal density! |
| Nox | Oxide charge density | **cm⁻²** | Interface charge |

> ⚠️ **Critical**: NI and Nox are per unit AREA (cm⁻²), while NA and ND are per unit VOLUME (cm⁻³)!

**Spotting the difference:**
- If it's ~10¹⁶ cm⁻³, it's probably doping (NA or ND)
- If it's ~10¹⁰ - 10¹¹ cm⁻², it's probably surface charge or implant (NI or Nox)

### The V-Family

| Symbol | Meaning | Notes |
|--------|---------|-------|
| VT, VT0 | Threshold voltage | What we calculate |
| VGS | Gate-to-source voltage | Applied bias |
| VDS | Drain-to-source voltage | Applied bias |
| VSB | Source-to-body voltage | Body effect bias |
| VDSAT | Saturation voltage | = VGS - VT |
| VFB | Flat-band voltage | = ΦGC |

**Don't confuse:**
- VT (threshold) with kT/q (thermal voltage, ~26 mV)
- VDSAT (pinch-off point) with VDS (actual drain voltage)

### The Φ-Family

| Symbol | Meaning | Sign (for NMOS) |
|--------|---------|-----------------|
| ΦF | Fermi potential | Negative (P-type) |
| ΦM | Metal work function | Positive (~4.1 V for Al) |
| ΦS | Silicon work function | Positive |
| ΦGC | Gate-channel work function diff | Can be either |
| φs | Surface potential | Positive at inversion |
| φ₀ | Junction built-in potential | Positive |

---

## 📌 Formula Mixups

### Linear vs Saturation Current

**Linear Region:**
$$I_D = k'\frac{W}{L}\left[(V_{GS}-V_T)V_{DS} - \frac{V_{DS}^2}{2}\right]$$

**Saturation Region:**
$$I_D = \frac{k'}{2}\frac{W}{L}(V_{GS}-V_T)^2$$

**Common mistakes:**
1. Forgetting the **1/2** in saturation formula (or using it in linear)
2. Using saturation formula when VDS < VGS - VT
3. Squaring the wrong term

**Memory trick:**
- **L**inear: has V**DS** terms (device sees drain voltage)
- **S**aturation: **S**quared (VGS-VT)², no VDS (device "saturated", ignores drain)

### Region Boundary

**Saturation starts when:** VDS = VGS - VT = VDSAT

At this point:
- Linear formula gives: ID = (k'/2)(W/L)(VGS-VT)² ← same as saturation!
- The two formulas **agree at the boundary**

### Body Effect Formula

$$V_T = V_{T0} + \gamma(\sqrt{|2\phi_F| + V_{SB}} - \sqrt{|2\phi_F|})$$

**Common mistakes:**
1. Forgetting the **-√|2ΦF|** subtraction (it's a shifted square root)
2. Using wrong sign for ΦF inside the square root (use |2ΦF|, always positive)
3. Applying body effect when VSB = 0 (unnecessary, VT = VT0)

---

## 📌 Short-Channel Gotchas

### Implant Shift Direction

**P-type implant into NMOS:**
- Adds acceptors → harder to invert → VT goes **UP** (more positive)
- Formula: ΔVT = **+** qNI/Cox

**N-type implant into NMOS:**
- Adds donors → partially compensates → VT goes **DOWN**
- Formula: ΔVT = **-** qNI/Cox

**Remember**: "P for Positive shift, N for Negative shift" (in NMOS)

### Short-Channel Always Subtracts

VT roll-off due to charge sharing **always reduces** VT:
$$V_T(short) = V_T(long) - \Delta V_{T(SCE)}$$

The subtraction is because source/drain "help" the gate.

### xdD Depends on VDS

Source junction depletion (xdS) is at equilibrium:
$$x_{dS} = \sqrt{\frac{2\varepsilon_{Si}\phi_0}{qN_A}}$$

Drain junction is reverse-biased by VDS:
$$x_{dD} = \sqrt{\frac{2\varepsilon_{Si}(\phi_0 + V_{DS})}{qN_A}}$$

**If VDS = 0 in the problem, they're equal!**

---

## 📌 Calculation Shortcuts

### ln(10) ≈ 2.3

Useful for Fermi potential:
$$\phi_F = -0.026 \times \ln\left(\frac{N_A}{n_i}\right)$$

If NA = 10¹⁶ and ni = 1.45×10¹⁰:
$$\frac{N_A}{n_i} = \frac{10^{16}}{1.45\times10^{10}} \approx 0.69 \times 10^6$$
$$\ln(0.69 \times 10^6) = \ln(0.69) + \ln(10^6) \approx -0.37 + 6\times2.3 = 13.4$$
$$\phi_F \approx -0.026 \times 13.4 = -0.35 \text{ V}$$

### Quick Cox Estimation

For tox in nm:
$$C_{ox} \approx \frac{35}{t_{ox}(nm)} \times 10^{-9} \text{ F/cm}^2$$

Examples:
- tox = 10 nm → Cox ≈ 3.5×10⁻⁸ F/cm² ≈ 35 fF/μm²
- tox = 5 nm → Cox ≈ 7×10⁻⁸ F/cm² ≈ 70 fF/μm²

### Quick k' Estimation

$$k' = \mu_n \times C_{ox}$$

For μn = 400 cm²/V·s and Cox = 3.5×10⁻⁷ F/cm²:
$$k' = 400 \times 3.5\times10^{-7} = 140 \times 10^{-6} = 140 \text{ μA/V}^2$$

**Rule of thumb**: k' is usually between 50-500 μA/V² for typical technologies.

---

## 📌 Exam Red Flags

### When the Problem Says... | Watch For...

| Problem States | Be Careful About |
|----------------|------------------|
| "Short channel device" | Need to apply SCE correction |
| "Ion implant" or NI given | Add implant shift before SCE |
| "Body effect" or VSB ≠ 0 | Use VT formula with γ |
| "Channel length modulation" | Include (1+λVDS) in saturation |
| "Depletion-mode MOSFET" | VT is negative! Device ON at VGS=0 |
| "Very small VDS" | Use linear approximation for rDS |
| "Find region of operation" | Must check BOTH VGS>VT AND VDS comparison |

---

## 📌 Final Checklist Before Submitting

### For VT Calculation:
- [ ] ΦF has correct sign (negative for P-type substrate)
- [ ] tox converted to correct units (cm)
- [ ] QB0 is negative, -QB0/Cox is positive
- [ ] Qox is positive, -Qox/Cox is negative
- [ ] Final VT0 is positive for enhancement NMOS
- [ ] Ion implant: P-type adds, N-type subtracts
- [ ] Short-channel: always subtracts from VT

### For Current Calculation:
- [ ] Checked VGS > VT first (device ON?)
- [ ] Compared VDS to VGS-VT for region
- [ ] Used correct formula for region
- [ ] Factor of 1/2 in right place
- [ ] Final answer has reasonable magnitude (μA to mA typical)

### For All Problems:
- [ ] Units are consistent throughout
- [ ] Answer has correct units in final form
- [ ] Order of magnitude makes sense

---

## 📌 Quick Quiz: Catch the Mistakes

Find the error in each statement:

1. "ΦF = +0.35 V for P-type substrate"
2. "Cox = 3.45×10⁻¹³ / 50nm = 6.9×10⁻¹⁵ F/cm²"
3. "NI = 2×10¹¹ cm⁻³ ion implant dose"
4. "In saturation, ID = k'(W/L)(VGS-VT)²"
5. "VDS = 1.2V, VGS-VT = 1.0V, so device is in linear region"

<details>
<summary>Answers</summary>

1. **Sign error**: ΦF should be **negative** (-0.35 V) for P-type
2. **Unit error**: Should convert 50nm = 50×10⁻⁷ cm, giving Cox = 6.9×10⁻⁸ F/cm²
3. **Unit error**: NI should be cm⁻² (areal), not cm⁻³ (volume)
4. **Missing factor**: Should be ID = **(k'/2)**(W/L)(VGS-VT)²
5. **Region error**: VDS (1.2V) > VGS-VT (1.0V), so it's **saturation**, not linear

</details>

---

*Previous: [07_exam_logic_flowchart.md](07_exam_logic_flowchart.md) | Next: [09_scaling_summary.md](09_scaling_summary.md)*
