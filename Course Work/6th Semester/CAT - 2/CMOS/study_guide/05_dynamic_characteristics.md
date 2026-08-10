# Dynamic Characteristics & Propagation Delay

## Learning Objectives

After this section you will understand:
- What load capacitance ($C_L$) is made of and how to compute each component
- The definitions of propagation delay ($t_{pHL}$, $t_{pLH}$), rise time, and fall time
- How to compute delay using first-order RC analysis
- How supply voltage affects delay
- The trade-off between delay and power

---

## The Dynamic Problem

So far we've only analyzed the CMOS inverter in **steady state** (DC). But digital circuits switch constantly. The speed of a circuit depends on how fast the output can transition from HIGH to LOW or LOW to HIGH.

**Analogy:** Think of a water tank. The static analysis tells you where the water level settles. The dynamic analysis tells you how long it takes to fill or drain the tank. The "pipe width" is like the transistor's on-resistance, and the "tank volume" is like the capacitance.

---

## Load Capacitance: What Are We Charging?

The output node of an inverter sees a **lumped capacitance** $C_L$ that must be charged (0 to $V_{DD}$) or discharged ($V_{DD}$ to 0) during switching.

![[load_capacitance_components.png]]

$$\boxed{C_L = C_{gd,12} + C_{db,n} + C_{db,p} + C_w + C_g(\text{fanout})}$$

### Component Breakdown

![[capacitance_components_circuit.png]]

| Component | Description | Formula/Notes |
|-----------|-------------|---------------|
| $C_{gd}$ (Gate-Drain) | Miller capacitance from gate-drain overlap | $C_{gd} = 2 C_{GD0} \cdot W$ (factor of 2 because output swings 2x relative to input) |
| $C_{db,n}$, $C_{db,p}$ | Drain-body junction capacitance | Nonlinear (voltage-dependent), use $K_{eq}$ factor |
| $C_w$ | Wire/interconnect capacitance | Depends on wire length, width, spacing |
| $C_g$ (fanout) | Gate capacitance of driven gates | $C_g = C_{ox} \cdot W \cdot L$ per gate driven |

![[gate_drain_capacitance.png]]

### Capacitances NOT in $C_L$

- $C_{gs,n}$ and $C_{gs,p}$: Connected to the **input** node, not the output
- $C_{sb,n}$ and $C_{sb,p}$: Source-body junctions have no effect on output transient (connected to constant supplies)

![[lumped_capacitance_model.png]]

### Equivalent Model for Transient Analysis

After lumping all capacitances, the inverter becomes a simple problem: an RC circuit where the transistor acts as a resistor charging/discharging $C_L$.

---

## Delay-Time Definitions

![[delay_time_waveform.png]]

### Propagation Delay

$$\boxed{t_{pHL} = \text{time from input 50\% to output falling to 50\%}}$$
$$\boxed{t_{pLH} = \text{time from input 50\% to output rising to 50\%}}$$

**50% point:**
$$V_{50\%} = \frac{V_{DD}}{2}$$

### Average Propagation Delay

$$\boxed{t_p = \frac{t_{pHL} + t_{pLH}}{2}}$$

### Rise and Fall Times

$$t_r = \text{time for output to rise from } V_{10\%} \text{ to } V_{90\%}$$
$$t_f = \text{time for output to fall from } V_{90\%} \text{ to } V_{10\%}$$

Where $V_{10\%} = 0.1 V_{DD}$ and $V_{90\%} = 0.9 V_{DD}$.

---

## Propagation Delay: First-Order RC Analysis

### The Approach

Instead of solving the complex nonlinear transistor equations, we approximate each transistor as a **linear resistor** $R_{eq}$ during the transition. The delay then becomes an RC time constant problem.

![[nmos_discharge_curve.png]]

### Computing $R_{eq}$ (Equivalent Resistance)

The on-resistance of a MOSFET varies with voltage. We average it over the transition from $V_{DD}$ to $V_{DD}/2$:

![[propagation_delay_rc_analysis.png]]

$$\boxed{R_{eq,n} \approx \frac{1}{k_n(V_{DD} - V_{T,n} - V_{DSAT,n}/2)}}$$

### Delay Formulas

For a first-order RC network, the propagation delay is:

$$\boxed{t_{pHL} = 0.69 \cdot R_{eq,n} \cdot C_L}$$
$$\boxed{t_{pLH} = 0.69 \cdot R_{eq,p} \cdot C_L}$$

Where 0.69 = $\ln(2)$ (the RC time constant to reach 50%).

$$\boxed{t_p = 0.69 \cdot C_L \cdot \frac{R_{eq,n} + R_{eq,p}}{2}}$$

### Why 0.69?

For an RC circuit discharging from $V_{DD}$ to $V_{DD}/2$:
$$V(t) = V_{DD} \cdot e^{-t/RC}$$
$$\frac{V_{DD}}{2} = V_{DD} \cdot e^{-t_{p}/RC}$$
$$t_p = RC \cdot \ln(2) = 0.693 \cdot RC$$

### Detailed Expression for $t_{pHL}$

$$t_{pHL} = \frac{0.69 \cdot C_L}{k_n \cdot (V_{DD} - V_{T,n} - V_{DSAT,n}/2)}$$

When $V_{DD} \gg V_{T,n} + V_{DSAT,n}/2$:

$$t_{pHL} \approx \frac{0.69 \cdot C_L}{k_n \cdot V_{DSAT,n}/2}$$

---

## How Supply Voltage Affects Delay

![[delay_vs_supply_voltage.png]]

Key observations:
- As $V_{DD}$ increases, delay **decreases** (transistor drives harder)
- The relationship is approximately $t_p \propto V_{DD} / (V_{DD} - V_T)^2$ for velocity-saturated devices
- At very high $V_{DD}$, delay improvement saturates (diminishing returns)
- At very low $V_{DD}$ (near $V_T$), delay increases **dramatically**

---

## How to Minimize Delay

| Strategy | How it Helps | Trade-off |
|----------|-------------|-----------|
| Reduce $C_L$ | Directly reduces $t_p$ | Requires careful layout |
| Increase $W/L$ | Reduces $R_{eq}$ | Increases gate capacitance of this gate (hurts driving gate) |
| Increase $V_{DD}$ | Reduces $R_{eq}$ | Increases power as $V_{DD}^2$, reliability concerns |
| Shorter interconnects | Reduces $C_w$ | May not be possible in placement |
| Fewer fan-out gates | Reduces $C_g$ | Architecture constraint |

---

## Worked Example: Delay Calculation

![[problem2_delay_solution.png]]

### Problem

For a CMOS inverter with:
- $V_{DD} = 2.5V$
- NMOS: $R_{eq,n} \cdot (W/L) = 13 k\Omega$, $(W/L)_n = 1.5$
- PMOS: $R_{eq,p} \cdot (W/L) = 31 k\Omega$, $(W/L)_p = 4.5$
- $C_{db,n} = 6.1 fF$, $C_{db,p} = 6.0 fF$

Calculate the propagation delay.

### Solution

**Step 1:** Compute actual resistances:
- $R_{eq,n} = 13k/1.5 = 8.67 k\Omega$
- $R_{eq,p} = 31k/4.5 = 6.89 k\Omega$

**Step 2:** Compute total load capacitance $C_L$ (from all components)

**Step 3:** Apply: $t_{pHL} = 0.69 \cdot R_{eq,n} \cdot C_L$, $t_{pLH} = 0.69 \cdot R_{eq,p} \cdot C_L$

> Full solution in [Worked Problems](./09_worked_problems.md#problem-2-delay-calculation)

---

## Common Mistakes

1. **Using 0.7 instead of 0.69**: Both are approximations of $\ln(2)$, either is acceptable
2. **Forgetting the Miller effect on $C_{gd}$**: The gate-drain capacitance sees DOUBLE the voltage swing, so it appears as $2C_{GD0}W$ in the load
3. **Confusing $R_{eq}$ with $R_{on}$**: $R_{eq}$ is the AVERAGE resistance over the transition, not the instantaneous on-resistance
4. **Not accounting for intrinsic vs extrinsic delay**: The gate's own diffusion capacitance contributes to delay even with no external load

---

## Self-Check Questions

**Q1:** What are the 4 main components of load capacitance $C_L$?

> **A:** Gate-drain overlap ($C_{gd}$), drain-body junction ($C_{db}$), wiring ($C_w$), and fanout gate capacitance ($C_g$).

**Q2:** Why is there a factor of 2 in the gate-drain capacitance contribution?

> **A:** Because during a transition, the input and output move in opposite directions. The voltage across $C_{gd}$ changes by $2 \times$ the output swing (Miller effect).

**Q3:** If you double $V_{DD}$ (from 1.5V to 3V) with $V_T = 0.5V$, by roughly what factor does delay decrease?

> **A:** $t_p \propto 1/(V_{DD} - V_T)$. Original: $1/(1.5-0.5) = 1$; New: $1/(3-0.5) = 0.4$. Delay decreases by about 2.5x.

---

## Concept Links

- Load capacitance determines both delay and [Power Dissipation](./04_power_dissipation.md)
- The RC model is formalized further in [RC Delay & Elmore](./07_rc_delay_and_elmore.md)
- Optimizing delay through sizing is covered in [Inverter Sizing](./06_inverter_sizing.md)
- All delay formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#propagation-delay)
