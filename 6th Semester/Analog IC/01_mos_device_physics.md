# 🔬 MOS Device Physics

> **Chapter 2.1-2.2**: From structure to I/V characteristics

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Identify all parts of a MOSFET structure
- Explain how a MOSFET works as a switch
- Distinguish between NMOS and PMOS devices
- Understand CMOS technology (nwell, pwell, twin-tub)

---

## 1️⃣ MOSFET Structure

### The Water Pipe Analogy 🚰

Think of a MOSFET like a water pipe with a valve:
- **Source (S)**: Where water comes FROM
- **Drain (D)**: Where water goes TO
- **Gate (G)**: The valve that controls flow (but doesn't touch the water!)
- **Bulk (B)**: The pipe material itself

The key insight: **The gate controls whether current flows, but no current flows INTO the gate.**

### Physical Structure (NMOS)

```
                Gate (Poly)
                    │
    ┌───────────────┴───────────────┐
    │           Gate Oxide          │  ← tox (oxide thickness)
    │           (SiO₂)              │
────┴───┬───────────────────┬───────┴────
   n⁺   │                   │   n⁺       ← Heavily doped
 Source │     Channel       │  Drain
        │   (p-substrate)   │
        └───────────────────┘
                 │
              p-substrate              ← Lightly doped
                 │
              Bulk (B)
```

### Key Dimensions

| Dimension | Symbol | Description | Typical Value |
|-----------|--------|-------------|---------------|
| Channel Length | L | Distance S → D | 40 nm - 1 μm |
| Channel Width | W | Perpendicular to L | 0.5 - 50 μm |
| Oxide Thickness | tox | Gate insulator | 1 - 20 nm |
| Effective Length | Leff | L - 2LD | L - side diffusion |

$$L_{eff} = L_{drawn} - 2L_D$$

> **💡 Remember**: The W/L ratio determines the "strength" of the transistor!

---

## 2️⃣ How a MOSFET Turns ON

### The Capacitor Analogy 🔋

The gate-oxide-substrate forms a **capacitor**:
- Gate = top plate
- Channel area = bottom plate  
- Oxide = dielectric

When you apply positive voltage to the gate:
1. **VG small**: Holes in p-substrate repelled → **Depletion region forms**
2. **VG = VTH**: Electrons attracted to interface → **Inversion layer begins**
3. **VG > VTH**: Strong inversion → **Channel connects S to D**

### Visual: Formation of Channel

```
VG < VTH (OFF):          VG = VTH:              VG > VTH (ON):
    Gate                    Gate                    Gate
    │                       │                       │
┌───┴───┐               ┌───┴───┐               ┌───┴───┐
│ Oxide │               │ Oxide │               │ Oxide │
├───────┤               ├───────┤               ├───────┤
│       │               │ - - - │ ← depletion   │=======│ ← channel!
│  n+ n+│               │  n+ n+│               │  n+═n+│
└───────┘               └───────┘               └───────┘
 No channel              Starting               Conducting!
```

---

## 3️⃣ NMOS vs PMOS

### Structure Comparison

| Feature | NMOS | PMOS |
|---------|------|------|
| Substrate | p-type | n-type (or n-well) |
| Source/Drain | n⁺ doped | p⁺ doped |
| Carriers | Electrons | Holes |
| Turns ON when | VGS > VTH (positive) | VGS < VTH (negative) |
| VTH sign | Positive | Negative |
| Mobility | Higher (μn ≈ 350 cm²/V·s) | Lower (μp ≈ 100 cm²/V·s) |

### Circuit Symbols

```
    NMOS                    PMOS
    
      D                       S
      │                       │
      ├──┤                    ├──┤
  G ──┤  │                G ──┤  │ (with bubble/bar)
      ├──┤                    ├──┤
      │                       │
      S                       D
      │                       │
      B                       B
```

> **💡 Memory Trick**: 
> - NMOS: **N**egative carriers (electrons) flow when gate is **P**ositive
> - PMOS: **P**ositive carriers (holes) flow when gate is **N**egative

---

## 4️⃣ CMOS Technology

### Why Both NMOS and PMOS?

CMOS (Complementary MOS) uses BOTH types for:
- Low power consumption
- Full voltage swing
- Robust noise margins

### The Problem: Different Substrates

NMOS needs p-substrate, PMOS needs n-substrate. 
**Solution**: Put one device in a "well"!

### Three Process Types

#### 1. N-Well Process (Most Common)
```
        NMOS                    PMOS
          │                       │
    p-substrate              n-well in p-substrate
    
    ┌───────────────────────────────────────┐
    │  n+ ═══ n+   │      │  p+ ═══ p+      │
    │     NMOS     │      │     PMOS        │
    │  p-substrate │      │    n-well       │
    └───────────────────────────────────────┘
                    p-substrate
```

#### 2. P-Well Process
- n-substrate with NMOS in p-well
- Less common

#### 3. Twin-Tub (Both Wells)
```
    ┌────────────────────────────────────────┐
    │     NMOS          │        PMOS        │
    │    in p-well      │      in n-well     │
    └────────────────────────────────────────┘
                   substrate
```

### Role of Doping

| Region | Doping | Purpose |
|--------|--------|---------|
| Source/Drain | Heavy (n⁺ or p⁺) | Low resistance contact |
| Channel | Light (substrate) | Controllable by gate |
| Well | Medium | Isolate device type |

> **Exam Tip**: Be ready to draw and label these structures! (10M question type)

---

## 5️⃣ Understanding Threshold Voltage

### What is VTH?

The **minimum gate voltage** needed to create an inversion layer (turn ON the transistor).

### Factors Affecting VTH

$$\boxed{V_{TH} = \Phi_{MS} + 2\Phi_F + \frac{Q_{dep}}{C_{ox}}}$$

| Factor | Symbol | Effect on VTH |
|--------|--------|---------------|
| Work function diff | ΦMS | Adds offset |
| Fermi potential | ΦF | Depends on doping |
| Depletion charge | Qdep | More charge → higher VTH |
| Oxide capacitance | Cox | Thinner oxide → lower term |

### Adjusting VTH by Implantation

- Add p⁺ implant under gate → **increases VTH** (more voltage needed to deplete)
- Add n⁺ implant under gate → **decreases VTH**

---

## 6️⃣ Quick Reference Table

| Parameter | NMOS | PMOS |
|-----------|------|------|
| Substrate | p-type | n-type/n-well |
| S/D doping | n⁺ | p⁺ |
| VTH typical | +0.3 to +0.7 V | -0.3 to -0.8 V |
| ON condition | VGS > VTH | VGS < VTH |
| Bulk connection | Ground (most negative) | VDD (most positive) |
| Current drive | Higher | Lower (~0.5×) |

---

## 🔗 Concept Links

- **Next**: [I/V Characteristics](./02_iv_characteristics.md) — How current flows
- **Formula Reference**: [Formula Sheet](./08_formula_sheet_ultimate.md)
- **Problems Using This**: [Questions 4, 5 in Worked Problems](./07_worked_problems.md)

---

## ✅ Self-Check Questions

1. Why is the gate-oxide capacitor important for MOSFET operation?
2. What happens to the depletion region as VG increases?
3. Why does NMOS have higher current drive than PMOS?
4. In an n-well process, where is the PMOS fabricated?
5. How can VTH be adjusted during fabrication?

<details>
<summary>Click for Answers</summary>

1. It creates the electric field that attracts/repels charges to form the channel
2. Width increases until inversion occurs, then stays relatively constant
3. Electron mobility (μn) > hole mobility (μp)
4. Inside the n-well
5. By ion implantation of dopants into the channel region

</details>
