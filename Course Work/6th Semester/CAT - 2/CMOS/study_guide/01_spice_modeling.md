# SPICE Modeling of MOS Transistors

## Learning Objectives

After this section you will understand:
- What SPICE is and why it matters for VLSI design
- The three built-in MOS transistor models (LEVEL 1, 2, 3)
- When to use each model and what trade-offs they represent
- The key parameters and equations for each level

---

## What is SPICE?

**SPICE** stands for **Simulation Program with Integrated Circuit Emphasis**. Think of it as a "virtual lab bench" -- instead of building a real circuit with real transistors, you describe your circuit in a computer and SPICE simulates how it will behave.

**Analogy:** Imagine testing a bridge design. You could build a real bridge and drive trucks over it, or you could simulate it on a computer first. SPICE is the computer simulation for circuits. Every chip designed in the industry is simulated in SPICE (or derivatives like HSPICE, Spectre) before anyone spends millions fabricating it.

SPICE has **three built-in MOSFET models** of increasing complexity:

| Model | Name | Complexity | When to Use |
|-------|------|------------|-------------|
| LEVEL 1 (MOS1) | Square-law | Simplest | Hand calculations, quick estimates |
| LEVEL 2 (MOS2) | Analytical | Detailed | Moderate accuracy needs |
| LEVEL 3 (MOS3) | Semi-empirical | Most accurate | Real design, short-channel effects |

---

## The MOS Transistor Equivalent Circuit

Before diving into equations, understand what we are modeling. A MOSFET has parasitic elements beyond the ideal switch:

![[mosfet_equivalent_circuit.png]]

- **Drain-source current source**: The main "valve" controlled by gate voltage
- **Reverse-biased diodes**: The source-substrate and drain-substrate junctions (always reverse-biased in normal operation)
- **Parasitic resistances** $R_D$ and $R_S$: Resistance of the drain and source contacts/diffusions
- **Capacitances**: Between every pair of terminals (studied in detail in [05_dynamic_characteristics.md](./05_dynamic_characteristics.md))

---

## LEVEL 1 Model (Square-Law)

The **simplest** model. It uses the basic GCA (Gradual Channel Approximation) quadratic equations you learn in analog electronics.

**Analogy:** LEVEL 1 is like estimating travel time using "distance / average speed." It gives you a ballpark answer but ignores traffic, stop lights, and road conditions.

![[level1_equations_1.png]]

![[level1_equations_2.png]]

### Key Equations (LEVEL 1)

**Cutoff region** ($V_{GS} < V_T$):
$$I_D = 0$$

**Linear/Triode region** ($V_{GS} > V_T$ and $V_{DS} < V_{GS} - V_T$):
$$\boxed{I_D = k_n \left[ (V_{GS} - V_T) V_{DS} - \frac{V_{DS}^2}{2} \right]}$$

**Saturation region** ($V_{GS} > V_T$ and $V_{DS} \geq V_{GS} - V_T$):
$$\boxed{I_D = \frac{k_n}{2} (V_{GS} - V_T)^2 (1 + \lambda V_{DS})}$$

Where:
- $k_n = \mu_n C_{ox} (W/L)$ = transconductance parameter
- $\mu_n$ = electron mobility ($\text{cm}^2/\text{V}\cdot\text{s}$)
- $C_{ox}$ = gate oxide capacitance per unit area ($\text{F/cm}^2$)
- $V_T$ = threshold voltage (V)
- $\lambda$ = channel-length modulation parameter ($\text{V}^{-1}$)
- $W/L$ = width-to-length ratio (dimensionless)

**Key limitation:** Does NOT account for short-channel effects, velocity saturation, or subthreshold conduction.

---

## LEVEL 2 Model (Detailed Analytical)

LEVEL 2 removes the simplifying assumptions of LEVEL 1 by considering the **actual variation of the bulk depletion charge** along the channel.

**Analogy:** This is like estimating travel time while accounting for traffic density that varies along your route.

![[level2_equations_1.png]]

### What LEVEL 2 Adds
- **Bulk depletion charge dependence** on channel voltage (not assumed constant)
- **VDSAT calculation**: The saturation voltage is computed more accurately:

![[level2_vdsat.png]]

- **Saturation mode current** uses the computed VDSAT:

![[level2_saturation_current.png]]

### Second-Order Effects Included
- Short-channel threshold voltage shifts
- Subthreshold conduction (leakage when $V_{GS} < V_T$)
- Velocity saturation (carrier speed limit at high electric fields)
- Charge-controlled capacitances

---

## LEVEL 3 Model (Semi-Empirical)

LEVEL 3 is mostly based on **empirical equations** (curve-fitting to measured data) rather than pure physics. This gives better accuracy while keeping computation manageable.

**Analogy:** Instead of deriving travel time from physics of traffic flow, you measure actual travel times for different conditions and create a lookup/interpolation formula.

![[level3_equations.png]]

### Key Features
- Uses an empirical parameter $F_B$ that captures the 3D geometry effects on depletion charge:

![[level3_fb_parameter.png]]

- Parameters $V_T$, $F_s$, and $\mu_s$ are influenced by **short-channel effects**
- Parameter $F_n$ is influenced by **narrow-channel effects**

### When to Use LEVEL 3
- When you need accuracy for **short-channel** or **narrow-channel** devices
- When device dimensions ($L$) approach the depletion region widths
- Most practical industry simulations use BSIM models (evolution beyond LEVEL 3)

---

## Common Mistakes

1. **Confusing which region the transistor is in**: Always check $V_{GS}$ vs $V_T$ first (is it ON?), then check $V_{DS}$ vs $V_{GS} - V_T$ (linear or saturated?)
2. **Forgetting $\lambda$**: In LEVEL 1, the saturation current is NOT perfectly flat -- $\lambda$ gives a slight slope. Set $\lambda = 0$ only when told to "ignore channel-length modulation"
3. **Confusing $k_n$ and $k'_n$**: $k'_n = \mu_n C_{ox}$ is the process parameter; $k_n = k'_n (W/L)$ includes device sizing
4. **PMOS vs NMOS signs**: For PMOS, replace $V_{GS}$ with $V_{SG}$, $V_{DS}$ with $V_{SD}$, and use $|V_{TP}|$

---

## Self-Check Questions

**Q1:** What is the primary difference between LEVEL 1 and LEVEL 2 models?

> **A:** LEVEL 1 assumes constant bulk depletion charge along the channel. LEVEL 2 accounts for its dependence on channel voltage, giving more accurate I-V curves near saturation.

**Q2:** If a MOSFET has $V_{GS} = 2V$, $V_T = 0.5V$, and $V_{DS} = 1V$, what region is it in?

> **A:** $V_{GS} > V_T$ (ON), $V_{DS} = 1V < V_{GS} - V_T = 1.5V$, so it is in the **linear/triode** region.

**Q3:** Why do we need LEVEL 3 if LEVEL 2 is already physics-based?

> **A:** LEVEL 2 equations become inaccurate for very short channels and narrow widths. LEVEL 3 uses empirical fitting to real data, trading physical insight for accuracy.

---

## Concept Links

- The LEVEL 1 equations are used directly in [CMOS Inverter Static Analysis](./02_cmos_inverter_static.md) to derive VTC regions
- The $k_n$ and $k_p$ parameters define the ratio $k_R$ used in [CMOS Inverter Design](./03_cmos_inverter_design.md)
- All SPICE model formulas are compiled in [Formula Sheet](./10_formula_sheet_ultimate.md#spice-model-equations)
