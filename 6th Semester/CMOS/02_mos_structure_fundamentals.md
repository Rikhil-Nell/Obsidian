# 🏗️ MOS Structure Fundamentals

> **The MOS capacitor is the heart of every MOSFET. Master this, and the transistor makes sense.**

---

## 📌 The MOS Capacitor Structure

The **MOS** (Metal-Oxide-Semiconductor) structure is essentially a **capacitor**:

```
┌─────────────────────────────────────────────────┐
│                METAL GATE                       │ ← Can apply voltage VG
│               (or Polysilicon)                  │
├─────────────────────────────────────────────────┤
│              OXIDE (SiO₂)                       │ ← Insulator (tox thick)
│            (Thin dielectric layer)              │
├─────────────────────────────────────────────────┤
│                                                 │
│         P-TYPE SILICON SUBSTRATE                │ ← Doped with NA
│        (Also called "body" or "bulk")           │
│                                                 │
└─────────────────────────────────────────────────┘
                      ↓
                 SUBSTRATE TERMINAL (grounded or biased)
```

**Key components:**
- **Gate**: Metal (Al) or heavily-doped Polysilicon — applies electric field
- **Oxide**: Silicon dioxide (SiO₂) — insulator, ~5-50 nm thick
- **Semiconductor**: P-type silicon substrate — where the magic happens

---

## 📌 The Flat-Band Condition

Before we apply any voltage, look at what happens when we connect the three materials:

### The Problem: Work Function Mismatch

The gate (metal/polysilicon) and silicon have **different work functions**. This means when they're connected, electrons will flow to equalize the Fermi levels — creating built-in voltage drops!

### Work Function Difference (ΦGC)

$$\Phi_{GC} = \Phi_{M} - \Phi_{S}$$

For **polysilicon gate** (N+ doped) on **P-type substrate**:
$$\Phi_{GC} = \phi_{F(gate)} - \phi_{F(substrate)} - \frac{E_g}{2q}$$

Where:
- ΦF(gate) is positive (N+ doped polysilicon)
- ΦF(substrate) is negative (P-type)
- Eg/2q ≈ 0.56 V (half band gap)

> **Typical value for NMOS**: ΦGC ≈ -0.9 V to -1.0 V (depends on doping)

### Flat-Band Voltage (VFB)

The voltage you'd need to apply to **undo** all built-in band bending:

$$V_{FB} = \Phi_{GC} - \frac{Q_{ox}}{C_{ox}}$$

Where Qox is any fixed oxide charge (usually positive, so it makes VFB more negative).

---

## 📌 Worked Example: Problem 1 Style

### Problem

Consider a MOS structure with:
- P-type silicon substrate with ΦF = -0.35 V
- Silicon dioxide layer
- Aluminum gate (work function = 4.1 eV)
- Electron affinity of silicon χ = 4.15 eV
- Assume no oxide or interface charges

**Find**: The built-in potential difference (work function difference) and flat-band voltage.

### Solution

**Step 1: Calculate the work function of the doped silicon**

$$\phi_S = \chi + \frac{E_g}{2q} - \phi_F = 4.15 + 0.56 - (-0.35) = 4.15 + 0.56 + 0.35 = 5.06 \text{ V}$$

**Step 2: Calculate the work function difference**

$$\Phi_{GC} = \phi_M - \phi_S = 4.1 - 5.06 = -0.96 \text{ V}$$

**Step 3: Calculate the flat-band voltage**

Since Qox = 0 (no oxide charges given):

$$V_{FB} = \Phi_{GC} - \frac{Q_{ox}}{C_{ox}} = -0.96 - 0 = -0.96 \text{ V}$$

**Interpretation**: 
- The negative flat-band voltage means that at VG = 0, the bands are already bent.
- To "flatten" the bands (undo the built-in potential), we'd need to apply VG = -0.96 V.
- This built-in band bending affects the threshold voltage!

---

## 📌 The Three Operating Regions

Depending on the gate voltage VG, the MOS capacitor behaves differently. Let's trace through what happens as we increase VG from negative to positive (for P-type substrate):

### 1️⃣ ACCUMULATION (VG < 0)

**What happens:**
- Negative gate voltage **attracts holes** to the surface
- Hole concentration at surface **increases** (accumulates)
- No depletion, no inversion — just more holes at the interface

```
Gate:  [- - - - -]  ← Negative voltage
Oxide: ─────────────
       ⊕ ⊕ ⊕ ⊕ ⊕ ⊕  ← Holes pile up at surface
       P-type bulk
```

**Energy bands**: Bend **upward** at surface

**Analogy**: Like pressing down on a crowd — people (holes) pack together at the barrier.

---

### 2️⃣ DEPLETION (0 < VG < VT)

**What happens:**
- Small positive gate voltage **repels holes** away from surface
- Mobile holes leave, leaving behind **fixed negative acceptor ions** (NA⁻)
- A **depletion region** forms — no mobile carriers, only fixed charge

```
Gate:  [+ + + + +]  ← Small positive voltage
Oxide: ─────────────
       - - - - - -  ← Fixed acceptor ions (no mobile carriers)
       ⊕ ⊕ ⊕ ⊕ ⊕ ⊕  ← Holes pushed away
       P-type bulk
```

**Energy bands**: Bend **downward** at surface

**Key parameter — Depletion Width (xd):**

$$x_d = \sqrt{\frac{2\varepsilon_{Si} \phi_s}{q N_A}}$$

Where:
- εSi = 1.04 × 10⁻¹² F/cm (permittivity of silicon)
- φs = surface potential (how much bands have bent)
- q = 1.6 × 10⁻¹⁹ C
- NA = acceptor doping

**Depletion Charge (per unit area):**

$$Q_B = -q N_A x_d = -\sqrt{2 q \varepsilon_{Si} N_A \phi_s}$$

Negative sign because it's negative fixed charge (acceptor ions).

---

### 3️⃣ INVERSION (VG > VT)

**What happens:**
- Large positive gate voltage **attracts electrons** to the surface
- Electron concentration at surface **exceeds hole concentration**
- The surface becomes **effectively N-type** — an *inversion layer* forms!

```
Gate:  [+ + + + +]  ← Large positive voltage
Oxide: ─────────────
       ⊖ ⊖ ⊖ ⊖ ⊖ ⊖  ← Electrons form INVERSION LAYER
       - - - - - -  ← Depletion region (fixed acceptors)
       ⊕ ⊕ ⊕ ⊕ ⊕ ⊕  ← Holes in bulk
       P-type bulk
```

**Energy bands**: Bend **strongly downward** — Ei crosses below EF at surface

**This is the KEY moment**: When Ei at the surface crosses EF, electrons become the majority at the surface!

---

## 📌 When Does Inversion Happen?

### The 2|ΦF| Condition

**Definition**: Surface is *inverted* when the electron concentration at the surface equals the hole concentration in the bulk.

**Mathematically**: This happens when the surface potential φs = -2ΦF (for P-type, ΦF is negative, so φs is positive)

$$\phi_s = |2\phi_F| = 2\frac{kT}{q}\ln\left(\frac{N_A}{n_i}\right)$$

**Example**: If NA = 10¹⁶ cm⁻³:
- ΦF = -0.026 × ln(10¹⁶/1.45×10¹⁰) = -0.026 × 13.45 = **-0.35 V**
- 2|ΦF| = **0.70 V** ← Surface must bend down by this much

### Maximum Depletion Width (xdm)

Once inversion begins, the depletion region stops growing! Any additional gate charge is balanced by the inversion layer electrons, not by more depletion.

$$x_{dm} = \sqrt{\frac{2\varepsilon_{Si} |2\phi_F|}{q N_A}} = \sqrt{\frac{4\varepsilon_{Si} |\phi_F|}{q N_A}}$$

### Maximum Depletion Charge (QB0)

$$Q_{B0} = -\sqrt{2 q \varepsilon_{Si} N_A |2\phi_F|} = -\sqrt{4 q \varepsilon_{Si} N_A |\phi_F|}$$

---

## 📌 The Oxide Capacitance (Cox)

The oxide layer acts as a parallel-plate capacitor:

$$C_{ox} = \frac{\varepsilon_{ox}}{t_{ox}}$$

| Parameter | Value | Unit |
|-----------|-------|------|
| εox | 3.45 × 10⁻¹³ F/cm | (permittivity of SiO₂) |
| εox | 3.45 × 10⁻¹¹ F/m | (SI units) |
| tox | Given in problem | cm or m |
| Cox | Calculated | F/cm² or F/m² |

> **Unit trap alert!** Always check if you're using cm or m. The PDF uses CGS (cm) mostly.

**Example**: tox = 500 Å = 50 nm = 50 × 10⁻⁷ cm = 5 × 10⁻⁶ cm

$$C_{ox} = \frac{3.45 \times 10^{-13}}{5 \times 10^{-6}} = 6.9 \times 10^{-8} \text{ F/cm}^2$$

---

## 📌 Visualizing the Regions with Band Diagrams

```
ACCUMULATION (VG < 0):        DEPLETION (0 < VG < VT):        INVERSION (VG > VT):
                              
    EC ─────╱                     EC ───────╲                    EC ─────────╲
           ╱                              ╲                               ╲
─ ─ EF ───╱─ ─                    ─ ─ EF ───╲─ ─                   ─ ─ EF ───  ╲─ ─
         ╱                                ╲                                 ╲
─ ─ Ei ─╱─ ─ ─                    ─ ─ Ei ───╲─ ─                   ─ ─ Ei ─────╲ ─ ─
       ╱                                  ╲                                   ╲
    EV ╱                           EV ─────╲                        EV ─────────╲
                                                                     
Bands bend UP                  Bands bend DOWN              Bands bend STRONGLY down
(more holes at surface)        (holes repelled)             Ei crosses BELOW EF
                                                            → ELECTRONS at surface!
```

---

## 📌 Oxide and Interface Charges (Qox)

Real oxides aren't perfect. They contain fixed positive charges:

1. **Fixed oxide charge (Qf)**: Trapped at Si-SiO₂ interface
2. **Interface trap charge (Qit)**: At the interface, depends on voltage
3. **Mobile ionic charge**: Na⁺, K⁺ contamination

For simplicity, we lump these as:
$$Q_{ox} = q \cdot N_{ox}$$

Where Nox is the surface charge density (charges/cm²).

**Effect**: Positive oxide charge makes it *easier* to form inversion → lowers VT

---

## 📌 Summary: What Creates the Channel?

```
┌─────────────────────────────────────────────────────────────────┐
│                    THE INVERSION LAYER                           │
│                                                                  │
│   When VG > VT:                                                  │
│   • Surface potential reaches 2|ΦF|                              │
│   • Electrons outnumber holes at surface                         │
│   • A thin N-type layer forms in P-type substrate                │
│   • This layer connects SOURCE to DRAIN → current can flow!      │
│                                                                  │
│   This is the "CHANNEL" of the MOSFET                            │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📌 Key Formulas Summary

| Quantity | Formula | Notes |
|----------|---------|-------|
| Surface potential for inversion | φs = 2\|ΦF\| | |
| Depletion width | xd = √(2εSi·φs / q·NA) | |
| Max depletion width | xdm = √(4εSi\|ΦF\| / q·NA) | At onset of inversion |
| Depletion charge | QB = -√(2q·εSi·NA·φs) | Negative (acceptor ions) |
| Max depletion charge | QB0 = -√(4q·εSi·NA\|ΦF\|) | At VSB = 0 |
| Oxide capacitance | Cox = εox / tox | Per unit area |

---

## 📝 Quick Check

Before moving on:

1. ❓ In accumulation, are holes attracted or repelled from the surface?
2. ❓ What type of charge is in the depletion region?
3. ❓ What surface potential condition defines "inversion"?
4. ❓ Why does the depletion region stop growing after inversion?
5. ❓ What's the physical meaning of the inversion layer?

<details>
<summary>Answers</summary>

1. Attracted (negative gate attracts positive holes)
2. Fixed negative acceptor ions (immobile, NA⁻)
3. φs = 2|ΦF| (band bending equals twice Fermi potential)
4. Because new electrons appear to balance gate charge instead of more depletion
5. A thin layer of electrons at the P-type surface — acts as an N-type conductor connecting source and drain

</details>

---

*Previous: [01_foundation_semiconductor_physics.md](01_foundation_semiconductor_physics.md) | Next: [03_threshold_voltage_mastery.md](03_threshold_voltage_mastery.md)*
