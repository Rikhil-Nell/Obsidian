# 🔬 Foundation: Semiconductor Physics

> **Before we can understand the MOSFET, we need to understand the material it's made of: Silicon.**

---

## 📌 What is Silicon?

Silicon (Si) is a **semiconductor** — a material that's neither a good conductor (like copper) nor a good insulator (like rubber). Its conductivity can be **controlled** by:
1. **Temperature**
2. **Adding impurities (doping)**
3. **Applying electric fields** ← This is what makes MOSFETs work!

### The Silicon Crystal

Silicon atoms have 4 valence electrons. In a crystal, each atom shares its electrons with 4 neighbors, forming **covalent bonds**. At absolute zero (0 K), all electrons are locked in bonds — no current can flow.

At room temperature (~300 K), some bonds break due to thermal energy, creating:
- **Free electrons** (negative charge, can move)
- **Holes** (positive "absence of electron", also moves!)

---

## 📌 Intrinsic vs Extrinsic Semiconductors

### Intrinsic Silicon (Pure)

In pure silicon at room temperature:
- Equal numbers of electrons and holes
- Both are called **intrinsic carrier concentration**: `ni`

> **Key Value**: `ni = 1.45 × 10¹⁰ cm⁻³` at 300 K for Silicon

This is a **tiny** number. Pure silicon is almost an insulator!

### Extrinsic Silicon (Doped)

To make silicon useful, we add impurities — this is called **doping**.

---

## 📌 Doping: P-Type vs N-Type

### N-Type Doping (Donors)

Add atoms with **5 valence electrons** (e.g., Phosphorus, Arsenic)

```
        Si        Si        Si
          \      / \      /
           Si ── P ── Si      ← Phosphorus has 5 electrons
          /      \ /      \      One is "extra" and becomes FREE!
        Si        Si        Si
                  ↓
             Free electron (n-type)
```

- **Donor concentration**: `ND` (atoms/cm³)
- **Majority carriers**: Electrons
- **Minority carriers**: Holes

**Carrier concentrations in N-type:**
```
n ≈ ND          (electrons ≈ donor concentration)
p = ni²/ND      (holes are scarce)
```

### P-Type Doping (Acceptors)

Add atoms with **3 valence electrons** (e.g., Boron)

```
        Si        Si        Si
          \      / \      /
           Si ── B ── Si      ← Boron has only 3 electrons
          /      \ /      \      One bond is MISSING = Hole!
        Si        Si        Si
                  ↓
             Hole (p-type)
```

- **Acceptor concentration**: `NA` (atoms/cm³)
- **Majority carriers**: Holes
- **Minority carriers**: Electrons

**Carrier concentrations in P-type:**
```
p ≈ NA          (holes ≈ acceptor concentration)
n = ni²/NA      (electrons are scarce)
```

---

## 📌 The Mass Action Law

> **No matter how you dope silicon, this law always holds:**

$$n \cdot p = n_i^2$$

| Symbol | Meaning | Typical Value |
|--------|---------|---------------|
| `n` | Electron concentration | Varies with doping |
| `p` | Hole concentration | Varies with doping |
| `ni` | Intrinsic carrier conc. | 1.45 × 10¹⁰ cm⁻³ |

**Example**: If `NA = 10¹⁶ cm⁻³` (P-type):
- `p ≈ 10¹⁶ cm⁻³` (majority)
- `n = ni²/p = (1.45×10¹⁰)²/(10¹⁶) ≈ 2.1×10⁴ cm⁻³` (minority — very few!)

---

## 📌 The Fermi Level & Fermi Potential

### Energy Band Diagram Basics

Every material has energy levels where electrons can exist:

```
     ─────────────────────  Ec (Conduction Band) 
                            ↑
                            │ Band Gap (Eg ≈ 1.12 eV for Si)
                            │
     - - - - - - - - - - -  Ei (Intrinsic Fermi Level) ← middle of gap
                            │
                            ↓
     ─────────────────────  Ev (Valence Band)
```

- **Ec**: Minimum energy for a free electron (conduction)
- **Ev**: Maximum energy for bound electrons (valence)
- **Ei**: The Fermi level in *intrinsic* (pure) silicon — exactly mid-gap

### The Fermi Level (EF)

The **Fermi level** is the energy at which there's a 50% probability of finding an electron.

- In **N-type**: EF moves UP (closer to Ec) — more electrons available
- In **P-type**: EF moves DOWN (closer to Ev) — more holes available

```
N-TYPE:                          P-TYPE:
─────── Ec                       ─────── Ec
   ↑                                
  EF ← Fermi level                - - - Ei
- - - Ei                          ↓
                                 EF ← Fermi level
─────── Ev                       ─────── Ev
```

### The Fermi Potential (ΦF)

The **Fermi potential** measures how far EF has moved from Ei:

$$\phi_F = \frac{E_i - E_F}{q}$$

**For P-type silicon (substrate of NMOS):**
$$\phi_F = -\frac{kT}{q} \ln\left(\frac{N_A}{n_i}\right)$$

**For N-type silicon:**
$$\phi_F = +\frac{kT}{q} \ln\left(\frac{N_D}{n_i}\right)$$

| Parameter | Value at 300 K |
|-----------|----------------|
| kT/q | 0.026 V (≈ 26 mV) |
| Thermal voltage | Also written as VT (not threshold!) |

> **Key Insight**: For P-type, ΦF is **negative**. For N-type, ΦF is **positive**.

---

## 📌 Work Function and Electron Affinity

When electrons escape from a material, they need to overcome an energy barrier:

### Electron Affinity (qχ)

Energy to move an electron from **conduction band** to **vacuum (free space)**

- **Silicon**: χ = 4.05 eV (some sources say 4.15 eV)

### Work Function (qΦ)

Energy to move an electron from **Fermi level** to **vacuum**

**For silicon:**
$$q\phi_s = q\chi + \frac{E_g}{2} - q\phi_F$$

Or equivalently:
$$\phi_s = \chi + \frac{E_g}{2q} - \phi_F$$

**For metal (like Aluminum):**
- Aluminum: Φm ≈ 4.1 eV

> **Why this matters**: When metal touches silicon in a MOS structure, their work function *difference* creates a built-in voltage!

---

## 📌 Summary Table: Key Parameters

| Symbol | Name | Formula/Value | Sign Convention |
|--------|------|---------------|-----------------|
| `ni` | Intrinsic carrier conc. | 1.45 × 10¹⁰ cm⁻³ | Always positive |
| `NA` | Acceptor concentration | Given (P-type) | Positive |
| `ND` | Donor concentration | Given (N-type) | Positive |
| `ΦF` | Fermi potential | -kT/q × ln(NA/ni) | Negative for P-type |
| `χ` | Electron affinity (Si) | 4.05 eV | Positive |
| `Eg` | Band gap (Si) | 1.12 eV | Positive |
| `kT/q` | Thermal voltage | 0.026 V at 300 K | Positive |

---

## 📌 Why This Matters for MOSFETs

1. **The substrate is P-type doped** → We know `NA`, can calculate `ΦF`
2. **The gate is metal or polysilicon** → We know its work function
3. **The difference in work functions** → Creates built-in electric fields
4. **These fields bend energy bands** → Lead to accumulation/depletion/inversion

> **Bridge to Next Topic**: When we stack Metal-Oxide-Semiconductor together, the Fermi levels must align. This alignment causes band bending — which is the key to understanding how the MOS capacitor works!

---

## 📝 Quick Check

Before moving on, make sure you can answer:

1. ❓ What type of doping creates more electrons?
2. ❓ In P-type silicon, which are majority carriers?
3. ❓ What's the Mass Action Law?
4. ❓ Is ΦF positive or negative for P-type substrate?
5. ❓ What does a higher NA do to the Fermi level position?

<details>
<summary>Answers</summary>

1. N-type (donors like Phosphorus)
2. Holes
3. n × p = ni² (always)
4. Negative (EF is below Ei)
5. Moves EF further down (more negative ΦF, more p-type character)

</details>

---

*Previous: [00_roadmap.md](00_roadmap.md) | Next: [02_mos_structure_fundamentals.md](02_mos_structure_fundamentals.md)*
