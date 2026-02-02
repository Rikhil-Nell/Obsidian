# 📐 MOSFET Scaling Summary

> **How transistors change when we shrink them. This is why we can fit billions on a chip.**

---

## 📌 Why Scaling Matters

Every ~2 years, transistor dimensions shrink. This enables:
- More transistors per chip (Moore's Law)
- Faster switching
- Lower power per operation
- Lower cost per transistor

But scaling isn't free — physics imposes limits.

---

## 📌 The Scaling Factor S

When we scale, we reduce dimensions by a factor **S** (where S > 1):

$$\text{New size} = \frac{\text{Old size}}{S}$$

**Example**: S = 2 means we halve all dimensions (100nm → 50nm)

---

## 📌 Two Scaling Philosophies

### Full Scaling (Constant-Field Scaling)

**Philosophy**: Keep the electric field constant as we shrink.

**How**:
- Reduce all dimensions by S
- Reduce all voltages by S
- Increase doping by S

**Result**: Electric fields stay the same, device physics unchanged (ideally).

### Constant-Voltage Scaling

**Philosophy**: Keep supply voltage the same (easier for system design).

**How**:
- Reduce all dimensions by S
- Keep voltages constant
- Increase doping by S² (to maintain field control)

**Result**: Electric fields increase, more stress on device.

---

## 📌 Master Scaling Table

| Parameter | Symbol | Full Scaling | Constant Voltage |
|-----------|--------|--------------|------------------|
| **Dimensions** | | | |
| Channel length | L | ÷ S | ÷ S |
| Channel width | W | ÷ S | ÷ S |
| Oxide thickness | tox | ÷ S | ÷ S |
| Junction depth | xj | ÷ S | ÷ S |
| **Voltages** | | | |
| Supply voltage | VDD | ÷ S | × 1 |
| Threshold voltage | VT | ÷ S | × 1 |
| **Doping** | | | |
| Substrate doping | NA | × S | × S² |
| **Derived Parameters** | | | |
| Oxide capacitance | Cox | × S | × S |
| Electric field | E | × 1 | × S |
| **Currents** | | | |
| Drain current | ID | ÷ S | × S |
| Current density | ID/(W·L) | × S | × S³ |
| **Power** | | | |
| Power per device | P = ID×VDD | ÷ S² | × S |
| Power density | P/(W·L) | × 1 | × S³ |
| **Speed** | | | |
| Gate delay | τ ∝ CV/I | ÷ S | ÷ S |
| **Area** | | | |
| Device area | W × L | ÷ S² | ÷ S² |

---

## 📌 Full Scaling: Detailed Analysis

### What Stays Constant
- Electric field (by design)
- Power density
- Current density (approximately)

### What Improves
- **Device area**: ÷ S² (4× smaller with S=2)
- **Power per transistor**: ÷ S² (significant!)
- **Delay**: ÷ S (faster)
- **Power-delay product**: ÷ S³ (major efficiency gain)

### What Gets Challenging
- Need scaled-down voltage supplies
- Need tighter threshold control
- Interconnect delays become significant

### Derivation Example: Saturation Current

Original:
$$I_D = \frac{k'}{2}\frac{W}{L}(V_{GS}-V_T)^2 = \frac{\mu_n C_{ox}}{2}\frac{W}{L}V_{GT}^2$$

After scaling:
- W → W/S
- L → L/S (ratio W/L unchanged)
- Cox → S·Cox (thinner oxide)
- VGT → VGT/S

$$I_D' = \frac{\mu_n (SC_{ox})}{2}\frac{W/S}{L/S}\left(\frac{V_{GT}}{S}\right)^2 = \frac{\mu_n C_{ox}}{2}\frac{W}{L}\frac{V_{GT}^2}{S} = \frac{I_D}{S}$$

Current reduces by S ✓

---

## 📌 Constant-Voltage Scaling: Detailed Analysis

### Why We Use It
- Multiple voltage supplies are impractical
- Interface with older circuits
- Standard I/O voltage levels

### What Stays Constant
- Voltage levels
- W/L ratio

### What Improves
- **Device area**: ÷ S²
- **Delay**: ÷ S
- **Current**: × S (more current per transistor)

### What Gets Worse (Problems!)
- **Power density**: × S³ (catastrophic heat!)
- **Electric field**: × S (stress, reliability)
- **Hot carriers**: More damage
- **Oxide breakdown**: Higher risk

### Derivation Example: Power Density

Power per device:
$$P = I_D \times V_{DD}$$

After constant-voltage scaling:
- ID → S × ID (from analysis)
- VDD → VDD (unchanged)
- Area → Area/S²

$$P' = (S \cdot I_D) \times V_{DD} = S \cdot P$$

Power density:
$$\frac{P'}{Area'} = \frac{S \cdot P}{Area/S^2} = S^3 \times \frac{P}{Area}$$

Power density increases by S³ ⚠️

---

## 📌 Real-World Scaling

Modern processes use a **hybrid approach**:

1. **Dimensions** scale aggressively (like full scaling)
2. **Voltage** scales, but slowly (VDD went from 5V → 3.3V → 1.8V → 1.2V → 0.8V...)
3. **Threshold** engineered to balance leakage vs. speed
4. **New structures** (FinFET, GAA) to maintain gate control

### Technology Node vs. Actual Size

| "Node Name" | Actual Gate Length | Year (approx) |
|-------------|-------------------|---------------|
| 180nm | ~180nm | 1999 |
| 90nm | ~50nm | 2004 |
| 45nm | ~35nm | 2007 |
| 22nm | ~25nm | 2012 |
| 14nm | ~20nm | 2014 |
| 7nm | ~7nm fin pitch | 2018 |
| 5nm | ~5nm fin pitch | 2020 |
| 3nm | GAA transistors | 2022+ |

> **Note**: Modern "node names" are marketing terms, not actual dimensions!

---

## 📌 Scaling Limits

### Fundamental Limits

1. **Quantum tunneling**: Electrons tunnel through thin oxide (<~1 nm)
2. **Atomic granularity**: Can't have "half an atom" of dopant
3. **Thermal noise**: kT/q ≈ 26mV sets minimum voltage swing
4. **Leakage**: Subthreshold current increases exponentially

### Practical Limits

1. **Heat removal**: Can only dissipate ~100-150 W/cm²
2. **Manufacturing**: Lithography, alignment, defects
3. **Cost**: Each new node costs more to develop
4. **Interconnects**: Wires don't scale as well as transistors

---

## 📌 Exam-Relevant Scaling Questions

### Typical Problem Types

1. **"How does X change with scaling factor S?"**
   - Look up in the table
   - Show derivation if asked

2. **"Compare full vs constant-voltage scaling"**
   - Full: Voltages scale → lower power density
   - Constant: Voltages fixed → higher power density

3. **"Why can't we scale forever?"**
   - Quantum effects
   - Leakage
   - Heat
   - Manufacturing

### Sample Problem

**Q**: If a transistor is scaled by S = 2 using full scaling, what happens to:
(a) Drain current
(b) Power per transistor
(c) Gate delay

**A**:
(a) ID → ID/2 (reduced by S)
(b) P → P/4 (reduced by S²)
(c) τ → τ/2 (reduced by S) = faster!

---

## 📌 Quick Reference Card

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         SCALING QUICK REFERENCE                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   FULL SCALING (Constant Field):                                            │
│   • Dimensions, Voltages: ÷ S                                               │
│   • Doping: × S                                                             │
│   • Current: ÷ S                                                            │
│   • Power: ÷ S²          ← THE BIG WIN                                      │
│   • Speed: ÷ S (faster)                                                     │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   CONSTANT-VOLTAGE SCALING:                                                 │
│   • Dimensions: ÷ S                                                         │
│   • Voltages: unchanged                                                     │
│   • Doping: × S²                                                            │
│   • Current: × S                                                            │
│   • Power density: × S³  ← THE BIG PROBLEM                                  │
│   • Speed: ÷ S (same improvement)                                           │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   BOTH APPROACHES:                                                          │
│   • Area: ÷ S²                                                              │
│   • Capacitance (Cox): × S                                                  │
│   • W/L ratio: unchanged                                                    │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📝 Quick Check

1. ❓ In full scaling, what happens to power per transistor?
2. ❓ In constant-voltage scaling, why is power density a problem?
3. ❓ Which scaling keeps electric field constant?
4. ❓ What happens to Cox when tox is reduced by S?
5. ❓ Why can't we scale VT to arbitrarily low values?

<details>
<summary>Answers</summary>

1. Reduces by S² (major advantage!)
2. It increases by S³ — leads to overheating
3. Full scaling (constant-field)
4. Cox increases by S (inverse relationship with tox)
5. Subthreshold leakage increases exponentially as VT decreases

</details>

---

## 📌 End of Study Guide

**Congratulations!** You've completed all 10 files of the study guide.

### Recommended Review Order for Tomorrow:
1. `07_exam_logic_flowchart.md` — Know the problem-solving process
2. `05_formula_sheet_unified.md` — Have formulas ready
3. `08_quirks_and_gotchas.md` — Avoid common mistakes
4. `03_threshold_voltage_mastery.md` — Understand VT deeply
5. `04_current_equations_complete.md` — Know when to use which formula

### Good luck on your exam! 🎓

---

*Previous: [08_quirks_and_gotchas.md](08_quirks_and_gotchas.md) | [Return to Roadmap](00_roadmap.md)*
