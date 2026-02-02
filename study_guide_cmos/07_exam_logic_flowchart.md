# 📊 Exam Logic Flowchart

> **Follow this step-by-step for any MOSFET problem. Don't skip steps.**

---

## 📌 The Master Problem-Solving Flowchart

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                    MOSFET EXAM PROBLEM: MASTER FLOWCHART                    │
└─────────────────────────────────────────────────────────────────────────────┘

START: Read the problem, identify what's being asked
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 1: EXTRACT PARAMETERS                                                  │
│                                                                             │
│ Check & note down:                                                          │
│ □ NA (substrate doping)         □ ND (gate/S-D doping)                      │
│ □ tox (oxide thickness)        □ Nox (interface charge)                     │
│ □ NI (ion implant dose)         □ xj (junction depth)                       │
│ □ W, L (dimensions)             □ μn (mobility)                             │
│ □ VGS, VDS, VSB (biases)        □ λ (if given)                              │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 2: CALCULATE FERMI POTENTIAL (ΦF)                                      │
│                                                                             │
│ For P-type substrate:                                                       │
│                                                                             │
│   ΦF = -0.026 × ln(NA / 1.45×10¹⁰)                                          │
│                                                                             │
│ Result should be NEGATIVE (typically -0.3 to -0.4 V)                        │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 3: CALCULATE OXIDE CAPACITANCE (Cox)                                   │
│                                                                             │
│   Cox = εox / tox = 3.45×10⁻¹³ / tox(cm)    [F/cm²]                         │
│                                                                             │
│ ⚠️ Convert tox to cm first! (1 nm = 10⁻⁷ cm, 1 Å = 10⁻⁸ cm)                 │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 4: CALCULATE WORK FUNCTION DIFFERENCE (ΦGC)                            │
│                                                                             │
│ For polysilicon gate:                                                       │
│   ΦGC = ΦF(gate) - ΦF(sub) - Eg/2q                                          │
│                                                                             │
│ Eg/2q = 0.56 V                                                              │
│ ΦF(gate): Calculate if ND(gate) given, or use provided value                │
│ ΦF(sub): From Step 2                                                         │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 5: CALCULATE DEPLETION CHARGE (QB0)                                    │
│                                                                             │
│   |2ΦF| = 2 × |ΦF|     (make it positive)                                   │
│                                                                             │
│   QB0 = -√(4 × q × εSi × NA × |ΦF|)                                         │
│       = -√(4 × 1.6×10⁻¹⁹ × 1.04×10⁻¹² × NA × |ΦF|)                          │
│                                                                             │
│ Result is NEGATIVE (in C/cm²)                                               │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 6: CALCULATE OXIDE CHARGE CONTRIBUTION                                 │
│                                                                             │
│   Qox = q × Nox = 1.6×10⁻¹⁹ × Nox                                           │
│                                                                             │
│ Result is POSITIVE (in C/cm²)                                               │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 7: ASSEMBLE VT0                                                        │
│                                                                             │
│   VT0 = ΦGC - 2ΦF - QB0/Cox - Qox/Cox                                       │
│                                                                             │
│   Since ΦF < 0: -2ΦF is positive                                            │
│   Since QB0 < 0: -QB0/Cox is positive                                       │
│   Since Qox > 0: -Qox/Cox is negative                                       │
│                                                                             │
│   VT0 = (+) + (+) + (+) + (-)  → Usually positive for NMOS                  │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 8: ION IMPLANT ADJUSTMENT (if applicable)                              │
│                                                                             │
│ Is there an ion implant (NI given)?                                         │
│                                                                             │
│ → YES (P-type implant):                                                     │
│     ΔVT = + q×NI/Cox                                                        │
│     VT0' = VT0 + ΔVT                                                        │
│                                                                             │
│ → YES (N-type implant):                                                     │
│     ΔVT = - q×NI/Cox                                                        │
│     VT0' = VT0 - ΔVT                                                        │
│                                                                             │
│ → NO: VT0' = VT0                                                            │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 9: SHORT-CHANNEL CORRECTION (if L is small)                            │
│                                                                             │
│ Is L comparaable to xj (roughly L < 2μm and xj given)?                      │
│                                                                             │
│ → YES:                                                                      │
│   1. Calculate φ0 = 0.026 × ln(NA×ND(S/D) / ni²)                            │
│   2. Calculate xdS = √(2εSi×φ0 / q×NA)                                      │
│   3. Calculate xdD = √(2εSi×(φ0+VDS) / q×NA)                                │
│   4. Calculate ΔL_S and ΔL_D                                                │
│   5. Calculate ΔVT0 (negative correction)                                   │
│   6. VT0'' = VT0' - ΔVT0                                                    │
│                                                                             │
│ → NO: VT0'' = VT0'                                                          │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 10: APPLY BODY EFFECT (if VSB ≠ 0)                                     │
│                                                                             │
│ Is VSB > 0 (for NMOS)?                                                      │
│                                                                             │
│ → YES:                                                                      │
│     γ = √(2q×εSi×NA) / Cox                                                  │
│     VT = VT0'' + γ×(√(|2ΦF|+VSB) - √|2ΦF|)                                  │
│                                                                             │
│ → NO (VSB = 0):                                                             │
│     VT = VT0''                                                              │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    │ ← NOW YOU HAVE VT! ←
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 11: DETERMINE OPERATING REGION                                         │
│                                                                             │
│                    Is VGS > VT?                                             │
│                         │                                                   │
│            ┌────────────┴────────────┐                                      │
│            │ NO                      │ YES                                  │
│            ▼                         ▼                                      │
│        ┌──────────┐         Is VDS < VGS - VT?                              │
│        │ CUTOFF   │                  │                                      │
│        │ ID = 0   │     ┌────────────┴────────────┐                         │
│        └──────────┘     │ YES                     │ NO                      │
│                         ▼                         ▼                         │
│                   ┌──────────┐              ┌──────────┐                    │
│                   │ LINEAR   │              │SATURATION│                    │
│                   │ Region   │              │ Region   │                    │
│                   └──────────┘              └──────────┘                    │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│ STEP 12: CALCULATE CURRENT                                                  │
│                                                                             │
│ First calculate k' = μn × Cox                                               │
│                                                                             │
│ CUTOFF:                                                                     │
│     ID = 0                                                                  │
│                                                                             │
│ LINEAR:                                                                     │
│     ID = k'×(W/L)×[(VGS-VT)×VDS - VDS²/2]                                   │
│                                                                             │
│ SATURATION:                                                                 │
│     ID = (k'/2)×(W/L)×(VGS-VT)²                                             │
│     (With λ: multiply by (1+λVDS))                                          │
│                                                                             │
│ For resistance (small VDS):                                                 │
│     rDS = 1 / [k'×(W/L)×(VGS-VT)]                                           │
└─────────────────────────────────────────────────────────────────────────────┘
    │
    ▼
   DONE! Check units and sanity of answer.
```

---

## 📌 Quick Reference: Common Calculation Sequences

### Sequence A: VT0 Only (Problem 2 style)
```
Given: NA, ND(gate), tox, Nox
Steps: 2 → 3 → 4 → 5 → 6 → 7
Output: VT0
```

### Sequence B: VT with Implant (Problem 4 style)
```
Given: NA, ND, tox, Nox, NI
Steps: 2 → 3 → 4 → 5 → 6 → 7 → 8
Output: VT0 with implant
```

### Sequence C: Full Short-Channel VT (Problem 4 advanced)
```
Given: NA, ND, tox, Nox, NI, L, xj, VDS
Steps: 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9
Output: VT0 with implant and SCE correction
```

### Sequence D: Current Calculation (Problem 3 style)
```
Given: W, L, tox, μn, VT, VGS, VDS
Steps: 3 (Cox) → k' → 11 (region) → 12 (ID)
Output: ID
```

### Sequence E: Find VGS for Given ID
```
Work backwards from current equation:
- If saturation: VGS = VT + √(2ID×L / (k'×W))
- If linear: Solve quadratic in VDS or use small-VDS approximation
```

---

## 📌 Sanity Checks

After you get an answer, verify:

| Check | Expected Range |
|-------|----------------|
| VT0 (enhancement NMOS) | 0.2 V - 1.0 V (positive) |
| VT0 (enhancement PMOS) | -0.2 V to -1.0 V (negative) |
| ΦF (P-type) | -0.3 V to -0.4 V |
| Cox | 10⁻⁸ to 10⁻⁷ F/cm² |
| k' | 50 - 500 μA/V² |
| ID (typical) | μA to mA range |
| γ (body effect) | 0.3 - 0.5 V^(1/2) |

---

## 📌 Common Pitfalls Checklist

Before submitting your answer:

- [ ] Did I convert tox to correct units (usually cm)?
- [ ] Did I use the right sign for ΦF (negative for P-type)?
- [ ] Did I remember QB0 is negative?
- [ ] Did I use NI (cm⁻²) vs NA (cm⁻³) correctly?
- [ ] Is my VT reasonable (not negative for enhancement NMOS)?
- [ ] Did I check VGS > VT before using current formulas?
- [ ] Did I compare VDS to (VGS - VT) for region selection?
- [ ] Are my final units correct (Amps, Volts, Ohms)?

---

## 📌 One-Page Formula Quick Reference

```
┌─────────────────────────────────────────────────────────────────────────────┐
│ STATIC PARAMETERS                                                           │
├─────────────────────────────────────────────────────────────────────────────┤
│ ΦF = -0.026 × ln(NA/1.45×10¹⁰)                [V, negative for P-type]      │
│ Cox = 3.45×10⁻¹³ / tox(cm)                     [F/cm²]                      │
│ ΦGC = ΦF(gate) - ΦF(sub) - 0.56                [V]                          │
│ QB0 = -√(4×1.6×10⁻¹⁹×1.04×10⁻¹²×NA×|ΦF|)      [C/cm², negative]             │
│ Qox = 1.6×10⁻¹⁹ × Nox                         [C/cm², positive]             │
│ VT0 = ΦGC - 2ΦF - QB0/Cox - Qox/Cox           [V]                           │
├─────────────────────────────────────────────────────────────────────────────┤
│ MODIFICATIONS                                                               │
├─────────────────────────────────────────────────────────────────────────────┤
│ Ion Implant:  ΔVT = +q×NI/Cox  (P-type adds, N-type subtracts)              │
│ Body Effect:  γ = √(2×1.6×10⁻¹⁹×1.04×10⁻¹²×NA) / Cox                        │
│               VT = VT0 + γ×(√(|2ΦF|+VSB) - √|2ΦF|)                          │
├─────────────────────────────────────────────────────────────────────────────┤
│ CURRENT EQUATIONS                                                           │
├─────────────────────────────────────────────────────────────────────────────┤
│ k' = μn × Cox                                                               │
│ Cutoff:      ID = 0                            (VGS < VT)                   │
│ Linear:      ID = k'(W/L)[(VGS-VT)VDS - VDS²/2] (VDS < VGS-VT)              │
│ Saturation:  ID = (k'/2)(W/L)(VGS-VT)²         (VDS ≥ VGS-VT)               │
│ Resistance:  rDS = 1/[k'(W/L)(VGS-VT)]         (small VDS)                  │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📌 Example: Full Problem Walkthrough

**Problem**: An NMOS has NA = 10¹⁶ cm⁻³, tox = 10 nm, μn = 400 cm²/V·s, W = 10 μm, L = 1 μm, VT = 0.5 V. Find ID when VGS = 1.5 V and VDS = 0.8 V.

**Solution**:

**Step 1**: Parameters extracted ✓

**Step 2-10**: VT is given as 0.5 V (skip calculation!)

**Step 3**: Cox = 3.45×10⁻¹³ / (10×10⁻⁷) = 3.45×10⁻⁷ F/cm²

**Step 11**: Region check
- VGS = 1.5 V > VT = 0.5 V ✓ (ON)
- VDS = 0.8 V vs VGS - VT = 1.5 - 0.5 = 1.0 V
- VDS < VGS - VT → **LINEAR region**

**Step 12**: Calculate current
- k' = 400 × 3.45×10⁻⁷ = 1.38×10⁻⁴ A/V² = 138 μA/V²
- ID = 138 × (10/1) × [(1.0)(0.8) - (0.8)²/2]
- ID = 1380 × [0.8 - 0.32]
- ID = 1380 × 0.48
- **ID = 662 μA**

**Sanity check**: For a 10μm/1μm device with ~1V overdrive, ~600 μA is reasonable ✓

---

*Previous: [06_short_channel_effects_decoded.md](./06_short_channel_effects_decoded.md) | Next: [08_quirks_and_gotchas.md](./08_quirks_and_gotchas.md)*
