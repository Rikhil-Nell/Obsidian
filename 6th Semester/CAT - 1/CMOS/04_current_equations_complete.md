# ⚡ Current Equations Complete

> **Now we bridge the gap: Once VT tells us the switch is ON, how much current actually flows?**

---

## 📌 The Missing Link: VT → Current

Here's the conceptual bridge you've been looking for:

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     FROM STATIC TO ACTIVE                                   │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   VT0 tells us:  "How much gate voltage to CREATE the channel"              │
│                         ↓                                                   │
│   Once VGS > VT:  Channel EXISTS (inversion layer connects S to D)          │
│                         ↓                                                   │
│   Now apply VDS:  Current flows through the channel!                        │
│                         ↓                                                   │
│   ID equations:  Tell us HOW MUCH current flows                             │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

**VT is the "price of admission"** — pay it with gate voltage, and the transistor opens.
**ID equations are "how much traffic"** — once open, how many electrons flow per second?

---

## 📌 The Gradual Channel Approximation (GCA)

Before we derive current equations, we need to understand the key simplification that makes everything tractable:

### What GCA Assumes

The electric field **along the channel** (Ey, from source to drain) is much smaller than the field **perpendicular to the channel** (Ex, from gate into substrate):

$$|E_y| \ll |E_x|$$

### Why This Matters

```
               Gate
        ─────────────────────
            ↓ ↓ ↓ ↓ ↓       ← Ex (strong, creates channel)
        ═══════════════════  ← Channel (inversion layer)
        →→→→→→→→→→→→→→→→→→→  ← Ey (weaker, pushes electrons S→D)
        Source             Drain
```

- **Ex** creates and controls the channel depth (via VGS)
- **Ey** drives current along the channel (via VDS)

GCA says: "At any point y along the channel, the channel charge QI(y) depends only on the **local** voltage at that point, not on what's happening elsewhere."

### When GCA Works

✅ Long channels where VDS is distributed gradually

### When GCA Breaks Down  

❌ At **pinch-off** (drain end, saturation)
❌ Very short channels (high Ey)

> **Key Insight**: GCA lets us treat the channel like a series of infinitesimal resistors and integrate to get total current.

---

## 📌 The Channel: A Voltage-Controlled Resistor

With GCA, here's how we think about the channel:

### Inversion Layer Charge

At any point y along the channel:

$$Q_I(y) = -C_{ox}\left[V_{GS} - V_T - V_C(y)\right]$$

Where:
- QI(y) = mobile electron charge per unit area (negative)
- Cox = oxide capacitance per unit area
- VGS = gate-to-source voltage
- VT = threshold voltage
- VC(y) = channel voltage at position y (relative to source)

**Boundary conditions:**
- At source (y = 0): VC(0) = 0
- At drain (y = L): VC(L) = VDS

### Incremental Resistance

The resistance of a small segment dy:

$$dR = \frac{dy}{W \cdot \mu_n \cdot |Q_I(y)|}$$

### Current Flow

Using Ohm's law for the segment:

$$dV_C = I_D \cdot dR$$

Integrate from source to drain → get the current equation!

---

## 📌 Linear (Triode) Region

### Physical Picture

When **VDS is small**, the channel exists uniformly from source to drain:

```
        Gate (VGS > VT)
        ─────────────────────
        ═══════════════════  ← Uniform channel
        Source    →→→→→    Drain
          (0V)   current   (small VDS)
```

The channel acts like a **resistor** whose value depends on VGS.

### Condition for Linear Region

$$V_{GS} > V_T \quad \text{AND} \quad V_{DS} < V_{GS} - V_T$$

The second condition ensures the channel exists all the way to the drain.

### Current Equation (Linear Region)

$$\boxed{I_D = \mu_n C_{ox} \frac{W}{L} \left[(V_{GS} - V_T)V_{DS} - \frac{V_{DS}^2}{2}\right]}$$

Or equivalently:

$$\boxed{I_D = \frac{k'}{2} \frac{W}{L} \left[2(V_{GS} - V_T)V_{DS} - V_{DS}^2\right]}$$

Where:
- **μn** = electron mobility (cm²/V·s)
- **k' = μn·Cox** = process transconductance parameter (A/V²)
- **W** = channel width
- **L** = channel length
- **W/L** = aspect ratio

### Small VDS Approximation (Resistor Mode)

When VDS is very small (VDS << 2(VGS - VT)):

$$I_D \approx \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_T) V_{DS}$$

This is **Ohm's Law** with a voltage-controlled resistance:

$$r_{DS} = \frac{V_{DS}}{I_D} = \frac{1}{\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_T)} = \frac{1}{k' \frac{W}{L} (V_{GS} - V_T)}$$

---

## 📌 Saturation Region

### Physical Picture: Pinch-Off

As VDS increases, the channel becomes **non-uniform** — thinner near the drain:

```
        Gate (VGS > VT)
        ─────────────────────
                             
        ══════════════════╗  ← Channel pinches off at drain!
        Source    →→→→→   ╚═ Drain
          (0V)   current   (large VDS)
```

At **VDS = VGS - VT**, the channel charge at the drain end goes to zero:
$$Q_I(L) = -C_{ox}[V_{GS} - V_T - V_{DS}] = 0$$

This is called **pinch-off**.

### Condition for Saturation

$$V_{GS} > V_T \quad \text{AND} \quad V_{DS} \geq V_{GS} - V_T$$

### What Happens at Pinch-Off?

- Channel ends *before* the drain
- Electrons reach pinch-off point, then are **swept** to drain by high electric field
- Current is **limited by how many electrons reach the pinch-off point**
- Current becomes **(almost) independent of VDS**

### Current Equation (Saturation Region)

$$\boxed{I_D = \frac{1}{2} \mu_n C_{ox} \frac{W}{L} (V_{GS} - V_T)^2}$$

Or:

$$\boxed{I_D = \frac{k'}{2} \frac{W}{L} (V_{GS} - V_T)^2}$$

> **Key Insight**: In saturation, ID depends on **(VGS - VT)²** — a square-law device!

---

## 📌 Channel Length Modulation (λ)

In real devices, ID in saturation isn't perfectly constant — it has a slight slope.

### Why?

As VDS increases beyond VDSAT:
- The pinch-off point moves toward the source
- Effective channel length **decreases**: L' = L - ΔL
- Current **increases** slightly

### Modified Saturation Equation

$$I_D = \frac{k'}{2} \frac{W}{L} (V_{GS} - V_T)^2 (1 + \lambda V_{DS})$$

Where **λ** is the **channel length modulation coefficient** (V⁻¹).

- λ is typically 0.01 - 0.1 V⁻¹
- Smaller L → larger λ (short channels are worse)

---

## 📌 Operating Region Summary

### The Decision Tree

```
                    Is VGS > VT?
                         │
            ┌────────────┴────────────┐
            │ NO                      │ YES
            ▼                         ▼
        CUTOFF                Is VDS < VGS - VT?
        ID = 0                        │
                         ┌────────────┴────────────┐
                         │ YES                     │ NO
                         ▼                         ▼
                      LINEAR                   SATURATION
           ID = k'(W/L)[(VGS-VT)VDS - VDS²/2]    ID = (k'/2)(W/L)(VGS-VT)²
```

### Visual: I-V Characteristics

```
    ID ↑
       │                    Saturation
       │               ╱─────────────────── VGS4
       │          ╱───╱─────────────────── VGS3  
       │     ╱───╱───╱─────────────────── VGS2
       │╱───╱───╱───╱───────────────────   VGS1 (just above VT)
       ┼───┬───┬───┬───┬───┬───┬───────→ VDS
       │   │   │   │   
       │   │   │   └ VGS4 - VT
       │   │   └ VGS3 - VT
       │   └ VGS2 - VT
       └ VGS1 - VT
           
           Linear region ←→ Saturation region
                    (boundary: VDS = VGS - VT)
```

The dashed parabola VDS = VGS - VT separates linear from saturation.

---

## 📌 Key Parameters

| Symbol | Name | Formula | Typical Units |
|--------|------|---------|---------------|
| μn | Electron mobility | Given | cm²/V·s |
| Cox | Oxide capacitance | εox/tox | F/cm² |
| k' | Process transconductance | μn × Cox | μA/V² |
| kn | Device transconductance | k' × (W/L) | μA/V² |
| W | Channel width | Given | μm |
| L | Channel length | Given | μm |
| λ | Channel length mod. | Given/fitted | V⁻¹ |
| VT | Threshold voltage | Calculated | V |

---

## 📌 Worked Example: Problem 3 Style

**Given:**
- L = 0.4 μm, tox = 8 nm = 8 × 10⁻⁷ cm
- μn = 450 cm²/V·s
- VT = 0.7 V
- W/L = 8 μm / 0.8 μm = 10

**Find**: (a) Cox and k' | (b) VGS and VDS for ID = 100 μA in saturation | (c) VGS for rDS = 1000 Ω

**Solution:**

**(a) Calculate Cox and k':**

$$C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}} = \frac{3.45 \times 10^{-13}}{8 \times 10^{-7}} = 4.31 \times 10^{-7} \text{ F/cm}^2$$

Converting: 4.31 × 10⁻⁷ F/cm² = 4.31 × 10⁻³ F/m² = 4.31 fF/μm²

$$k' = \mu_n \times C_{ox} = 450 \times 4.31 \times 10^{-7} = 194 \times 10^{-6} \text{ A/V}^2 = 194 \text{ μA/V}^2$$

**(b) For saturation with ID = 100 μA:**

$$I_D = \frac{k'}{2} \frac{W}{L} (V_{GS} - V_T)^2$$

$$100 \times 10^{-6} = \frac{194 \times 10^{-6}}{2} \times 10 \times (V_{GS} - 0.7)^2$$

$$100 = 970 \times (V_{GS} - 0.7)^2$$

$$(V_{GS} - 0.7)^2 = 0.103$$

$$V_{GS} - 0.7 = 0.321$$

$$V_{GS} = 1.02 \text{ V}$$

For saturation: VDS ≥ VGS - VT = 1.02 - 0.7 = **0.32 V** minimum

**(c) For rDS = 1000 Ω (linear region, small VDS):**

$$r_{DS} = \frac{1}{k' \frac{W}{L} (V_{GS} - V_T)}$$

$$1000 = \frac{1}{194 \times 10^{-6} \times 10 \times (V_{GS} - 0.7)}$$

$$V_{GS} - 0.7 = \frac{1}{194 \times 10^{-6} \times 10 \times 1000} = 0.515$$

$$V_{GS} = 1.22 \text{ V}$$

---

## 📌 Formula Summary Card

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        DRAIN CURRENT FORMULAS                               │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   CUTOFF (VGS < VT):                                                        │
│   ID = 0                                                                    │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   LINEAR (VGS > VT, VDS < VGS - VT):                                        │
│   ID = k'(W/L) × [(VGS - VT)×VDS - VDS²/2]                                  │
│                                                                             │
│   Small VDS approximation:                                                  │
│   ID ≈ k'(W/L) × (VGS - VT) × VDS                                           │
│   rDS = 1 / [k'(W/L)(VGS - VT)]                                             │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   SATURATION (VGS > VT, VDS ≥ VGS - VT):                                    │
│   ID = (k'/2)(W/L) × (VGS - VT)²                                            │
│                                                                             │
│   With channel length modulation:                                           │
│   ID = (k'/2)(W/L) × (VGS - VT)² × (1 + λVDS)                               │
│                                                                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│   KEY PARAMETERS:                                                           │
│   k' = μn × Cox                                                             │
│   Cox = εox / tox                                                           │
│   VDSAT = VGS - VT  (saturation boundary)                                   │
│                                                                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📝 Quick Check

1. ❓ What does GCA assume about electric fields?
2. ❓ In linear region, ID is proportional to ___?
3. ❓ In saturation, ID is proportional to ___?
4. ❓ What is the boundary condition for saturation?
5. ❓ What does channel length modulation do to saturation current?

<details>
<summary>Answers</summary>

1. Ey << Ex (field along channel << field perpendicular to surface)
2. VDS (and VGS-VT) — linear in VDS
3. (VGS - VT)² — quadratic "square law"
4. VDS = VGS - VT (pinch-off occurs)
5. Makes ID increase slightly with VDS (non-ideal, but real)

</details>

---

*Previous: [03_threshold_voltage_mastery.md](03_threshold_voltage_mastery.md) | Next: [05_formula_sheet_unified.md](05_formula_sheet_unified.md)*
