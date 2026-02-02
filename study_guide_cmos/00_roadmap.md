# 🗺️ CMOS Study Guide: Learning Roadmap

> **Your exam is tomorrow. This roadmap shows you the fastest path through the material.**

---

## 📌 The Big Picture: What This Course Is Really About

At its core, this course answers **one question**:

> **How do we build a switch out of silicon, and how do we predict its behavior mathematically?**

That switch is the **MOSFET** (Metal-Oxide-Semiconductor Field-Effect Transistor) — the building block of every modern processor, memory chip, and digital circuit.

---

## 🔗 How Everything Connects

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        THE GRAND CONCEPT MAP                                │
└─────────────────────────────────────────────────────────────────────────────┘

    SEMICONDUCTOR PHYSICS               MOS STRUCTURE
    ┌─────────────────────┐            ┌─────────────────────┐
    │ • Doping (P/N-type) │            │ • Metal-Oxide-Si    │
    │ • Carrier conc.     │────────────│ • Energy bands      │
    │ • Fermi Level (ΦF)  │            │ • Work functions    │
    └─────────────────────┘            └──────────┬──────────┘
                                                  │
                                                  ▼
                              ┌─────────────────────────────────┐
                              │     THREE OPERATING REGIONS     │
                              │  ┌───────────────────────────┐  │
                              │  │ ACCUMULATION → DEPLETION  │  │
                              │  │        → INVERSION        │  │
                              │  └───────────────────────────┘  │
                              └───────────────┬─────────────────┘
                                              │
                                              ▼
    ┌─────────────────────────────────────────────────────────────────────────┐
    │                    THRESHOLD VOLTAGE (VT0)                              │
    │  "The Price of Admission" — How much gate voltage to turn ON the switch │
    │                                                                         │
    │  VT0 = ΦGC - 2ΦF - QB0/Cox - Qox/Cox                                    │
    └────────────────────────────────┬────────────────────────────────────────┘
                                     │
                                     ▼
    ┌─────────────────────────────────────────────────────────────────────────┐
    │                    CURRENT EQUATIONS (ID)                               │
    │  Once the switch is ON, how much current flows?                         │
    │                                                                         │
    │  LINEAR:     ID = (k'/2)(W/L)[2(VGS-VT)VDS - VDS²]                      │
    │  SATURATION: ID = (k'/2)(W/L)(VGS-VT)²                                  │
    └────────────────────────────────┬────────────────────────────────────────┘
                                     │
                    ┌────────────────┴────────────────┐
                    ▼                                 ▼
    ┌───────────────────────────┐     ┌───────────────────────────┐
    │    SHORT CHANNEL EFFECTS  │     │     MOSFET CAPACITANCE    │
    │  What breaks when L is    │     │  Parasitic caps that      │
    │  too small?               │     │  affect speed             │
    │  • VT roll-off            │     │  • Oxide caps (Cgs, Cgd)  │
    │  • Velocity saturation    │     │  • Junction caps (Csb)    │
    │  • DIBL, punch-through    │     │                           │
    └───────────────────────────┘     └───────────────────────────┘
```

---

## 📚 File Reading Order

| Order | File | Topic | Time Est. | Priority |
|:-----:|------|-------|:---------:|:--------:|
| 1 | `01_foundation_semiconductor_physics.md` | Doping, Fermi level, carriers | 15 min | ⭐⭐⭐ |
| 2 | `02_mos_structure_fundamentals.md` | Accumulation/Depletion/Inversion | 20 min | ⭐⭐⭐ |
| 3 | `03_threshold_voltage_mastery.md` | VT0 formula & body effect | 25 min | ⭐⭐⭐ |
| 4 | `04_current_equations_complete.md` | ID formulas, GCA, regions | 25 min | ⭐⭐⭐ |
| 5 | `05_formula_sheet_unified.md` | All formulas + units | 10 min | ⭐⭐⭐ |
| 6 | `06_short_channel_effects_decoded.md` | SCE, ion implant, DIBL | 20 min | ⭐⭐ |
| 7 | `07_exam_logic_flowchart.md` | Problem-solving strategy | 10 min | ⭐⭐⭐ |
| 8 | `08_quirks_and_gotchas.md` | Common mistakes | 10 min | ⭐⭐ |
| 9 | `09_scaling_summary.md` | Scaling laws | 10 min | ⭐ |
| 10 | `10_mosfet_capacitances.md` | Oxide & junction caps, Problem 5/6 | 20 min | ⭐⭐ |
| 11 | `11_latchup_and_reliability.md` | Latch-up, HCI, reliability | 15 min | ⭐ |

> **Legend**: ⭐⭐⭐ = Must know | ⭐⭐ = Likely on exam | ⭐ = Good to know

---

## 🎯 Exam Topic Breakdown

Based on the lecture material, here's what you're likely to face:

### High-Probability Topics (60% of exam)
1. **Work Function & Flat-Band** (Problem 1 style)
   - Given: ΦF, gate material work function, χ
   - Find: ΦGC, VFB

2. **Threshold Voltage Calculation** (Problem 2 style)
   - Given: NA, ND, tox, Nox
   - Find: ΦF, ΦGC, Cox, QB0, VT0

3. **Drain Current Calculation** (Problem 3 style)
   - Given: device parameters, bias voltages
   - Find: Operating region (Linear/Saturation), ID

4. **Short Channel Effect** (Problem 4 style)
   - Given: short channel device with ion implant
   - Find: VT shift, modified threshold

### Medium-Probability Topics (25% of exam)
4. **Body Effect** — How VSB changes VT
5. **Channel Length Modulation** — The λ factor
6. **Scaling** — Full vs constant-voltage scaling effects
7. **Junction Capacitance** (Problem 5/6 style)
   - Given: doping, junction area, voltage swing
   - Find: Cj0, Keq, Ceq

### Lower-Probability Topics (15% of exam)
8. **Gate Capacitances** — Cgs, Cgd, Cgb by region
9. **Narrow Channel Effects**
10. **DIBL, punch-through, velocity saturation** (conceptual)
11. **Latch-up mechanism** and prevention

---

## 🧠 Key Analogies to Remember

| Concept | Analogy |
|---------|---------|
| **Threshold Voltage (VT)** | "Price of admission" to the club (channel formation) |
| **Inversion Layer** | A "bridge" of electrons connecting source to drain |
| **Depletion Region** | A "no-man's land" emptied of mobile carriers |
| **Gate Oxide (Cox)** | A "capacitor dielectric" storing charge to control channel |
| **Short Channel Effect** | Source & drain "steal" charge that should belong to gate |
| **Body Effect** | Substrate "fights back" against channel formation |

---

## ⚡ Quick-Start: If You Only Have 1 Hour

**Focus on these in order:**
1. `05_formula_sheet_unified.md` — Memorize the formulas
2. `07_exam_logic_flowchart.md` — Learn the problem-solving process
3. `03_threshold_voltage_mastery.md` — Understand VT0 components
4. `04_current_equations_complete.md` — Know Linear vs Saturation

---

## 🔄 What You Already Know (Problem 2 Level)

You mentioned you understand up to Problem 2. That means you likely know:
- ✅ Basic MOS structure
- ✅ What threshold voltage means
- ✅ How to calculate VT0 from given parameters

**Your gaps are probably:**
- ❓ How VT0 *leads to* current equations (the bridge)
- ❓ GCA — what it means conceptually
- ❓ Short channel effects — charge sharing vs implant shift
- ❓ Unit conversions and sign conventions

This study guide will fill those gaps.

---

## 📖 How to Use This Guide

1. **First pass**: Read files 1-5 for understanding
2. **Second pass**: Focus on formula sheet and flowchart
3. **Practice**: Work through Problems 2, 3, 4 from lectures using the flowchart
4. **Night before**: Review `08_quirks_and_gotchas.md` for common traps

**Good luck on your exam! 🎓**

---

*Next: [01_foundation_semiconductor_physics.md](./01_foundation_semiconductor_physics.md)*
