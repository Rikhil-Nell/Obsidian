# 07 - Leakage Currents in Deep Submicrometer Transistors

---

## Learning Objectives

After studying this section, you will be able to:

- Explain why leakage current is a major concern in deep submicrometer technologies
- Describe all six major leakage mechanisms in detail
- Explain the physics behind reverse-bias pn junction leakage and band-to-band tunneling
- Derive and explain the subthreshold leakage current equation
- Describe DIBL and its effect on threshold voltage
- Explain the narrow-width effect and threshold rolloff
- Describe gate oxide tunneling and distinguish between FN tunneling and direct tunneling
- Explain hot carrier injection and the barrier heights for electrons vs holes
- Describe the GIDL mechanism including field crowding and BTBT
- Explain punchthrough and calculate the punchthrough voltage
- Relate all leakage mechanisms to power dissipation in modern CMOS

---

## Ground-Up Explanation

### Why Leakage Matters

In ideal CMOS logic, transistors are either fully ON or fully OFF, and no current flows in the OFF state. However, in reality, especially in **deep submicrometer (DSM)** technologies (channel lengths below 0.25 µm, especially below 90 nm), significant current flows even when transistors are supposed to be OFF.

This unwanted current is called **leakage current**, and it contributes to **static power dissipation**:

$$\boxed{P_{static} = V_{DD} \cdot I_{leakage}}$$

In modern nanometer technologies, leakage power can be **comparable to or even exceed dynamic switching power**, making it the dominant power consumption mechanism in standby mode.

**Analogy**: Think of a water faucet. Even when you close it tightly, a small amount of water drips through. In large plumbing systems with thousands of faucets (transistors), these tiny drips add up to significant water (power) waste. As faucets get smaller (transistor scaling), the seals become less effective, and the dripping gets worse.

### The Six Major Leakage Mechanisms

1. **Reverse-bias pn junction leakage** ($I_1$)
2. **Subthreshold leakage** ($I_2$)
3. **Gate oxide tunneling leakage** ($I_3$)
4. **Hot carrier injection** ($I_4$)
5. **Gate-Induced Drain Leakage (GIDL)** ($I_5$)
6. **Punchthrough leakage** ($I_6$)

---

## 1. Reverse-Bias pn Junction Leakage ($I_1$)

### Physical Mechanism

In a MOSFET, the drain and source regions form pn junctions with the substrate (or well). During normal operation, these junctions are typically **reverse biased**:
- For NMOS: n+ drain/source in p-type substrate, with $V_{drain} > V_{substrate}$
- For PMOS: p+ drain/source in n-type well, with $V_{drain} < V_{well}$

A reverse-biased pn junction carries a small leakage current with **two main components**:

| Component | Mechanism |
|-----------|-----------|
| **Minority carrier diffusion/drift** | Near the edge of the depletion region, minority carriers are swept across |
| **Electron-hole pair generation** | In the depletion region itself, thermal generation creates carrier pairs |

The junction leakage current is a function of:
- **Junction area**: Larger junctions have more leakage
- **Doping concentration**: Higher doping increases the electric field

### Band-to-Band Tunneling (BTBT)

When **both n and p regions are heavily doped**, a new mechanism dominates: **band-to-band tunneling (BTBT)**.

BTBT occurs when:
- A **high electric field** (> 10$^6$ V/cm) exists across the reverse-biased junction
- The total potential drop across the junction exceeds the semiconductor **bandgap energy** ($E_g \approx 1.12$ eV for silicon)
- Under this condition, the **valence band of the p-region aligns energetically with the conduction band of the n-region**
- Electrons can tunnel directly from the valence band to the conduction band through the narrow depletion region **without thermal excitation**

BTBT current increases **exponentially** with:
- Higher junction doping
- Higher reverse bias voltage
- Shallower junction depth

This becomes significant in heavily doped, short-channel devices where electric fields are extremely high.

---

## 2. Subthreshold Leakage ($I_2$)

### The Most Dominant Leakage Mechanism

Subthreshold leakage is the most significant leakage component in modern DSM devices. It occurs when $V_{GS} < V_{th}$ -- the transistor is supposed to be OFF, but a small current still flows.

### Physics of Weak Inversion

**Weak inversion** is the operating region where:
- Gate voltage is below the threshold voltage ($V_G < V_{th}$)
- But sufficient to create a **small concentration of minority carriers** at the surface
- A **weak inversion layer** forms
- Current flows primarily due to **diffusion** (not drift)

**Why diffusion dominates in weak inversion**:
1. The number of mobile carriers is small (weak inversion = few carriers)
2. The longitudinal electric field along the channel is also small (most of $V_{DS}$ drops across the reverse-biased drain-substrate junction)
3. Therefore, **drift current is negligible**
4. The current is mainly due to **diffusion of carriers** from source to drain

### Key Behavior

In weak inversion:
- The carrier concentration at the surface increases **exponentially** with gate voltage
- Therefore, the drain current shows **exponential dependence** on $V_{GS}$
- On a semilog plot ($\log I_D$ vs $V_{GS}$), this appears as a **straight line**

### Subthreshold Current Equation

$$\boxed{I_{sub} = I_0 \cdot e^{\frac{V_{GS} - V_{th}}{n \cdot V_T}} \cdot \left(1 - e^{-\frac{V_{DS}}{V_T}}\right)}$$

Where:
| Symbol | Meaning |
|--------|---------|
| $I_0$ | Process-dependent current (related to W/L) |
| $V_{GS}$ | Gate-to-source voltage |
| $V_{th}$ | Threshold voltage |
| $n$ | Subthreshold swing coefficient (typically 1.0-1.5) |
| $V_T$ | Thermal voltage = $kT/q$ ≈ 26 mV at room temperature |
| $V_{DS}$ | Drain-to-source voltage |

**Critical insight**: If $V_G$ increases slightly, the exponent increases, so the current increases **very rapidly**. This exponential dependence is what makes subthreshold leakage so sensitive to threshold voltage.

### Subthreshold Swing

The **subthreshold swing** ($S$) is defined as the change in $V_{GS}$ needed to change $I_{sub}$ by one decade (factor of 10):

$$\boxed{S = n \cdot \frac{kT}{q} \cdot \ln 10 \approx n \cdot 60 \text{ mV/decade at room temperature}}$$

The ideal minimum value of $S$ is **60 mV/decade** (when $n = 1$). In practice, $n > 1$, so $S > 60$ mV/decade.

### Factors Increasing Subthreshold Leakage

Four major factors increase subthreshold current:

#### A. Drain-Induced Barrier Lowering (DIBL)

DIBL occurs when the drain voltage reduces the potential barrier at the source-channel junction, increasing leakage even when the device is OFF.

**In long-channel devices**:
- When high $V_{DS}$ is applied, the drain depletion region does not extend significantly into the channel
- The source-channel energy barrier remains **almost unchanged**
- The gate retains strong control over the channel

**In short-channel devices**:
- The drain depletion region **penetrates deep into the channel** (because the channel is short)
- The drain electric field reaches the source side
- This **lowers the source-channel energy barrier**
- Electrons flow from source even when $V_{GS} < V_{th}$
- The drain significantly influences channel control, reducing gate effectiveness

$$\boxed{V_{th,eff} = V_{th0} - \eta \cdot V_{DS} \quad \text{(DIBL reduces } V_{th}\text{)}}$$

where $\eta$ is the DIBL coefficient. DIBL does not change the subthreshold slope $S$ but does **lower $V_{th}$**.

#### B. Body Effect

The body (substrate) bias modulates $V_{th}$, affecting the subthreshold current.

#### C. Narrow-Width Effect

The decrease in gate width modulates the threshold voltage. There are mainly **three ways** that narrow width affects $V_{th}$:
- Edge effects from isolation regions
- Variations in depletion charge distribution
- Fringing field effects

#### D. Threshold Voltage Rolloff (Short Channel Effect)

In short-channel devices:
- Source and drain depletion regions extend into the channel
- Source-drain distance becomes comparable to vertical depletion width
- This creates a **2-D electric field** distribution
- Part of the channel is already depleted before gate voltage is applied
- The gate needs to invert **less bulk charge**, thereby **lowering $V_{th}$**

The effect becomes more severe at **high drain voltage**: higher $V_{DS}$ increases depletion width, pushing more charge into the channel, further reducing $V_{th}$.

### Temperature Dependence

Subthreshold leakage is **highly temperature-sensitive**:
- $V_T = kT/q$ increases with temperature
- $V_{th}$ decreases with temperature
- Both effects increase subthreshold current exponentially
- $I_{OFF}$ can increase by **5-10× for every 30°C rise** in temperature

---

## 3. Gate Oxide Tunneling Leakage ($I_3$)

### Physical Mechanism

As technology scales, the gate oxide thickness ($t_{ox}$) is reduced aggressively (below 3-4 nm in modern devices). The thin oxide creates:
- Very high electric field across the oxide ($E_{ox} = V_{ox}/t_{ox}$)
- The potential barrier (3.1 eV for Si-SiO₂ interface) becomes **narrow enough** for quantum mechanical tunneling

Electrons can tunnel through the oxide from the substrate to the gate (or vice versa), creating a gate leakage current.

### Two Types of Tunneling

#### Fowler-Nordheim (FN) Tunneling

- Occurs at **high electric fields** across the oxide
- Electrons tunnel through a **triangular potential barrier**
- The oxide barrier is tilted by the electric field
- Electrons tunnel through only part of the barrier (near the top)
- Dominates for thicker oxides at high voltages

#### Direct Tunneling

- Occurs at **very thin oxides** (< 3-4 nm)
- Electrons tunnel through the **entire oxide barrier** (trapezoidal barrier)
- Does not require high voltage -- even at moderate $V_{GS}$, direct tunneling occurs
- Dominates in modern sub-100nm devices
- This is the primary concern in modern technology nodes

### Energy Band Diagrams

The tunneling mechanism can be understood from energy band diagrams of the MOS capacitor:

**(a) Flat-band condition**: No tunneling, barrier is intact
**(b) Positive gate bias**: Electrons from substrate tunnel to gate
**(c) Negative gate bias**: Electrons from gate tunnel to substrate

### Impact

Gate oxide tunneling:
- Destroys the assumption of **infinite gate input impedance**
- Increases **static power consumption**
- Becomes exponentially worse as $t_{ox}$ decreases
- Motivated the development of **high-k dielectrics** (like HfO₂) as gate oxide replacements

---

## 4. Hot Carrier Injection ($I_4$)

### Physical Mechanism

In short-channel transistors, the high electric field near the Si-SiO₂ interface (especially near the drain) can accelerate carriers to very high energies. These energetic carriers are called **"hot" carriers**.

If a hot carrier gains sufficient energy, it can **cross the interface potential barrier** and enter the gate oxide. This is called **hot carrier injection**.

### Electron vs Hole Injection

| Parameter | Electrons | Holes |
|-----------|-----------|-------|
| **Barrier height** | 3.1 eV | 4.5 eV |
| **Effective mass** | Lower | Higher |
| **Injection probability** | Higher (more likely) | Lower (less likely) |

Since electrons have a **lower effective mass** and face a **lower barrier** (3.1 eV vs 4.5 eV), electron injection from the substrate to the oxide is much more likely than hole injection.

### Effects

- Degrades device reliability over time
- Changes $V_{th}$ due to charge trapping in oxide
- Reduces device lifetime
- Worsens with scaling (higher fields in shorter channels)

---

## 5. Gate-Induced Drain Leakage (GIDL) ($I_5$)

### Physical Mechanism

GIDL is a leakage current caused by **very high electric fields** in the **drain-gate overlap region**, particularly when:
- The gate is at **low or negative voltage**
- The drain is at **high voltage**

### Step-by-Step Mechanism

**Step 1: Low Negative Gate Bias**
- Holes accumulate at the silicon surface under the gate
- The surface behaves like a heavily doped p-region
- The depletion layer at the surface near the drain becomes **very narrow**
- Because the depletion region is narrow, the electric field becomes **highly concentrated**
- This is called **field crowding**

**Step 2: High Negative Gate Bias**
- Under strong negative gate bias, the n+ drain region under the gate can become **depleted** or even **inverted**
- This further increases the electric field in the overlap region
- The peak electric field becomes extremely high

**Step 3: Carrier Generation**
As the electric field increases:
- **Band-to-band tunneling (BTBT)** becomes dominant
- Electrons tunnel from valence band to conduction band
- Electron-hole pairs are generated near the surface

**Step 4: Current Flow**
- The newly generated minority carriers are swept laterally into the substrate (substrate is at lower potential)
- This creates a **leakage current path** from drain to substrate through the overlap region

### Why It's Called "Gate-Induced"

The leakage is called gate-induced drain leakage because:
- The leakage is **triggered by the electric field created between the gate and drain**
- The gate voltage strongly influences the depletion region shape and field intensity
- Even though the current flows near the drain, it is **induced by the gate bias**

### Factors Increasing GIDL

| Factor | Effect |
|--------|--------|
| Thinner oxide ($t_{ox}$) | Higher electric field for same voltage |
| Higher drain voltage ($V_D$) | Larger potential difference in overlap region |
| Moderate drain doping | Affects depletion region width and field strength |
| Negative gate bias | Creates accumulation and field crowding |

---

## 6. Punchthrough ($I_6$)

### Physical Mechanism

Punchthrough occurs when the **depletion regions of the source and drain extend into the channel and merge**, creating a direct current path even when $V_{GS} = 0$.

### Step-by-Step Process

1. In short-channel devices, source and drain are very close
2. Each forms a depletion region with the substrate
3. As channel length decreases, the separation between depletion regions decreases
4. Increasing reverse bias ($V_{DS}$) further widens these depletion regions
5. When depletion regions **merge**, punchthrough occurs

### Effects of Punchthrough

Once punchthrough occurs:
- The potential barrier between source and drain is **lowered**
- Majority carriers from the source can **directly enter the substrate**
- Some carriers are **collected by the drain**
- Subthreshold current increases **significantly**
- OFF-state leakage increases
- Subthreshold slope degrades
- The device loses gate control

### Punchthrough Voltage

The punchthrough voltage ($V_{PT}$) estimates the value of $V_{DS}$ at which punchthrough occurs (at $V_{GS} = 0$):

$$\boxed{V_{PT} = \frac{q \cdot N_B \cdot (L - W_j)^2}{2 \cdot \epsilon_s}}$$

Where:
| Symbol | Meaning |
|--------|---------|
| $q$ | Electron charge (1.6 × 10$^{-19}$ C) |
| $N_B$ | Doping concentration of the bulk |
| $L$ | Channel length |
| $W_j$ | Junction width |
| $\epsilon_s$ | Permittivity of silicon |

**Key relationship**: $V_{PT}$ decreases with shorter channel length ($L$), confirming that punchthrough is a **short-channel effect**.

---

## Summary: All Six Leakage Mechanisms

| # | Mechanism | Cause | Key Region | Scaling Trend |
|---|-----------|-------|------------|---------------|
| 1 | Reverse-bias pn junction | Junction area, doping | Source/drain junctions | Moderate increase |
| 2 | Subthreshold leakage | $V_{GS} < V_{th}$, low $V_{th}$ | Channel (weak inversion) | **Exponential increase** |
| 3 | Gate oxide tunneling | Thin $t_{ox}$, quantum tunneling | Gate oxide | **Exponential increase** |
| 4 | Hot carrier injection | High electric field near drain | Drain-oxide interface | Moderate |
| 5 | GIDL | High field in gate-drain overlap | Gate-drain overlap | Significant increase |
| 6 | Punchthrough | Depletion region merging | Source-drain channel | Increases with scaling |

---

## Common Mistakes

1. **Confusing subthreshold leakage with gate leakage**: Subthreshold is channel current below $V_{th}$; gate leakage is current through the oxide
2. **Forgetting that DIBL affects $V_{th}$ but NOT subthreshold swing**: DIBL lowers $V_{th}$ without changing $S$
3. **Mixing up FN and direct tunneling**: FN = partial triangular barrier (thick oxide, high voltage); Direct = full trapezoidal barrier (thin oxide, moderate voltage)
4. **Thinking GIDL occurs when gate is positive**: GIDL is a **negative (or low) gate bias** phenomenon
5. **Confusing barrier heights**: Electrons = 3.1 eV; Holes = 4.5 eV at Si-SiO₂ interface
6. **Forgetting temperature sensitivity**: Subthreshold leakage increases 5-10× per 30°C

---

## Self-Check Questions

**Q1**: Which leakage mechanism is most dominant in modern sub-100nm technologies?
> Subthreshold leakage, because $V_{th}$ is aggressively reduced for performance, causing exponential increase in OFF-state current.

**Q2**: What is the ideal minimum subthreshold swing at room temperature?
> 60 mV/decade (when $n = 1$ and $T = 300K$: $S = 2.3 \times kT/q = 2.3 \times 26$ mV ≈ 60 mV/decade)

**Q3**: Why does DIBL lower the threshold voltage?
> In short channels, the drain depletion region extends toward the source, lowering the source-channel barrier. This means less gate voltage is needed to enable current flow, effectively reducing $V_{th}$.

**Q4**: What are the two types of gate oxide tunneling, and when does each dominate?
> FN tunneling: dominates in thicker oxides at high voltages (triangular barrier). Direct tunneling: dominates in very thin oxides (< 3-4 nm) at moderate voltages (trapezoidal barrier).

**Q5**: Calculate the punchthrough voltage for $N_B = 10^{17}$ cm$^{-3}$, $L = 0.1$ µm, $W_j = 0.02$ µm.
> $V_{PT} = \frac{1.6 \times 10^{-19} \times 10^{23} \times (0.08 \times 10^{-6})^2}{2 \times 1.04 \times 10^{-12}}$
> $= \frac{1.6 \times 10^{4} \times 6.4 \times 10^{-15}}{2.08 \times 10^{-12}} \approx 0.049$ V
> (Very low -- confirming punchthrough is extremely likely in 100nm devices)

---

## Concept Links

- CMOS static power context: [02_cmos_logic.md](./02_cmos_logic.md#3-leakage-power-advanced-nodes)
- Deep submicrometer design issues (builds on this): [08_deep_submicrometer_design_issues.md](./08_deep_submicrometer_design_issues.md)
- All leakage formulas: [14_formula_sheet_ultimate.md](./14_formula_sheet_ultimate.md#leakage-currents)
- Dynamic logic charge leakage sensitivity: [05_dynamic_logic_and_domino.md](./05_dynamic_logic_and_domino.md)
