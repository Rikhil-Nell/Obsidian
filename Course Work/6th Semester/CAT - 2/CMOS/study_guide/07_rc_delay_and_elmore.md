# RC Delay Model & Elmore Delay

## Learning Objectives

After this section you will understand:
- Extended delay definitions (propagation, contamination, slack, critical path)
- Setup and hold time concepts for flip-flops
- How to model any transistor as an RC element
- The simplified diffusion capacitance model
- How sizing affects R and C simultaneously
- The Elmore delay model for RC trees (exam-critical technique)

---

## Extended Delay Definitions

### Propagation vs Contamination Delay

| Delay | Symbol | Definition | Think of it as... |
|-------|--------|------------|-------------------|
| **Propagation delay** | $t_{pd}$ | Maximum time from input 50% to output 50% | "Worst case -- guaranteed done by this time" |
| **Contamination delay** | $t_{cd}$ | Minimum time from input 50% to output 50% | "Output holds its old value for at least this long" |

**Analogy:** If you order food delivery, the propagation delay is "your food will arrive within 45 minutes" (upper bound). The contamination delay is "your food cannot possibly arrive before 15 minutes" (lower bound).

### Rise and Fall Time (Alternate Definition)

$$t_r = \text{time from 20\% to 80\% of } V_{DD}$$
$$t_f = \text{time from 80\% to 20\% of } V_{DD}$$
$$\text{Edge rate: } t_{rf} = \frac{t_r + t_f}{2}$$

### Timing Analysis Concepts

- **Arrival time**: When data actually arrives at a node
- **Required time**: When data must arrive to meet timing
- **Slack** = Required time - Arrival time
  - **Positive slack**: circuit meets timing (good)
  - **Negative slack**: circuit is too slow (bad -- must fix)
- **Critical path**: The path with the **smallest (most negative) slack** = the bottleneck

### Setup and Hold Time

![[setup_hold_time.png]]

- **Setup time ($t_{setup}$)**: Data must be stable **BEFORE** the clock edge by this amount
- **Hold time ($t_{hold}$)**: Data must remain stable **AFTER** the clock edge by this amount

If either is violated, the flip-flop enters a metastable state (unpredictable output).

---

## RC Model of a Transistor

### Concept

Every MOS transistor can be modeled as a simple **resistor** (for its channel) in combination with **capacitors** (for its terminals).

### Unit Transistor Model

For a **unit-width** transistor (minimum size):
- NMOS: resistance $R$, capacitance $C$
- PMOS: resistance $\approx 2R$, capacitance $\approx 2C$ (approximately, due to wider sizing for equal drive)

### For a transistor of width $k$ (i.e., $k \times$ unit width):

![[sizing_impact_rc.png]]

| Parameter | NMOS (width $k$) | PMOS (width $k$) |
|-----------|------------------|-------------------|
| Channel resistance | $R/k$ | $2R/k$ |
| Gate capacitance | $kC$ | $kC$ |
| Diffusion capacitance | $kC$ | $kC$ |

**Key insight:** Wider transistors have LOWER resistance but HIGHER capacitance. There is always a trade-off.

### Simplified Diffusion Capacitance Model

$$\boxed{C_s = A_S \times C_{jbs} + P_S \times C_{jbsw}}$$

Where:
- $A_S = D \times W$ = area of diffusion region
- $P_S = 2W + 2D$ = perimeter of diffusion region
- $C_{jbs}$ = unit area capacitance to body
- $C_{jbsw}$ = sidewall capacitance per unit length

---

## Equivalent Circuit of an Inverter

### Fanout-of-1 Inverter

![[equivalent_circuit_inverter.png]]

A unit inverter has:
- NMOS: width 1 (resistance $R$, diffusion cap $C$, gate cap $C$)
- PMOS: width 2 (resistance $R$, diffusion cap $2C$, gate cap $2C$)
- Total gate capacitance seen by driver: $C + 2C = 3C$
- Total diffusion capacitance at output: $C + 2C = 3C$

When input rises (NMOS ON, PMOS OFF):
- Only the NMOS resistance path matters
- Capacitors between constant supplies are removed (no charge/discharge)

---

## Elmore Delay Model

### What is Elmore Delay?

The Elmore delay is a way to estimate the delay through an **RC tree** (a circuit with no loops, only resistors and capacitors branching from a source).

**Analogy:** Imagine water flowing through a branching pipe system. The Elmore delay estimates how long it takes for water to reach a specific endpoint, considering that each pipe has resistance (friction) and each junction has a tank (capacitance).

![[elmore_delay_rc_tree.png]]

### The Formula

$$\boxed{t_{pd} = \sum_{i} C_i \cdot R_{i \rightarrow s}}$$

Where:
- Sum is over **ALL nodes** $i$ in the tree
- $C_i$ = capacitance at node $i$
- $R_{i \rightarrow s}$ = **shared resistance** on the path from source to BOTH node $i$ AND the output node (leaf)

---

## Worked Examples: Elmore Delay

### Problem 1: 2nd Order RC System

![[elmore_delay_2nd_order.png]]

**Q:** Compute the Elmore delay for $V_{out}$ in a 2nd-order RC system with $R_1$, $C_1$ at node $n_1$, and $R_2$, $C_2$ at node $V_{out}$.

**Solution:**

The circuit has a source and two nodes:
- At node $n_1$: capacitance $C_1$, resistance to source = $R_1$
- At node $V_{out}$: capacitance $C_2$, resistance to source = $R_1 + R_2$

$$\boxed{t_{pd} = R_1 C_1 + (R_1 + R_2)C_2}$$

### Problem 2: Unit Inverter Driving $m$ Identical Inverters

![[elmore_inverter_fanout_m.png]]

**Q:** Estimate the Elmore delay for a unit inverter driving $m$ identical unit inverters.

**Solution:**

Each load inverter presents $3C$ units of gate capacitance (total: $3mC$).

The output node also sees $3C$ from the drain diffusions of the driving inverter (parasitic capacitance).

Total capacitance: $(3 + 3m)C$. Resistance: $R$.

$$\boxed{t_{pd} = (3 + 3m)RC}$$

### Problem 3: Driver of Width $w$

![[elmore_width_w_driver.png]]

**Q:** If the driver is $w$ times unit size, what is the delay?

**Solution:**

- Resistance decreases by $w$: $R/w$
- Diffusion capacitance increases by $w$: $3wC$
- Load capacitance unchanged: $3mC$

$$\boxed{t_{pd} = (3w + 3m)\frac{RC}{w} = (3 + 3m/w)RC}$$

**Define fanout** $h = m/w$ (load capacitance / input capacitance):

$$\boxed{t_{pd} = (3 + 3h)RC}$$

### Problem 4: FO4 Delay

![[fo4_delay_problem.png]]

**Q:** In a 65nm process, $R = 10k\Omega$ and $C = 0.1 fF$. Compute the delay for fanout-of-4.

**Solution:**

$$RC = 10k\Omega \times 0.1 fF = 1 ps$$
$$t_{pd} = (3 + 3 \times 4) \times 1 ps = 15 ps$$

This is the **FO4 delay** -- a key benchmark for gate performance. In a 65nm process, FO4 $\approx$ 15 ps.

---

## NAND Gate Delay Analysis

### Problem 5: 3-Input NAND Transistor Sizing

![[nand3_transistor_sizing.png]]

**Q:** Sketch a 3-input NAND gate with transistor widths chosen to achieve effective rise and fall resistance equal to $R$ (same as unit inverter).

**Solution:**
- **NMOS** (series): Three in series, each must be 3x unit width so that $R/3 + R/3 + R/3 = R$
- **PMOS** (parallel): Each must be 2x unit width so that in worst case (only one ON), resistance = $R$

Input capacitance per input: $3C(\text{NMOS}) + 2C(\text{PMOS}) = 5C$

### Problem 6: 3-Input NAND Delay

![[nand3_delay_problem.png]]

![[nand3_delay_solution.png]]

**Q:** Estimate $t_{pdf}$ and $t_{pdr}$ for the 3-input NAND gate driving $h$ identical NAND gates.

**Falling transition** (all inputs HIGH, NMOS stack conducts):

$$\boxed{t_{pdf} = (12 + 5h)RC}$$

Breakdown using Elmore delay:
- Node $n_1$: $C_1 = 3C$, $R_{shared} = R/3$, contribution: $C$
- Node $n_2$: $C_2 = 3C$, $R_{shared} = 2R/3$, contribution: $2C$  
- Node $Y$: $C_Y = (9 + 5h)C$, $R_{shared} = R$, contribution: $(9 + 5h)C \cdot R$

**Rising transition** (worst case: outer input falls, inner two remain HIGH):

$$\boxed{t_{pdr} = (15 + 5h)RC}$$

The PMOS pulls up through resistance $R$. Internal nodes $n_1$ and $n_2$ also need charging, each with capacitance $3C$ and shared resistance $R$.

---

## Common Mistakes

1. **Confusing "shared path" in Elmore delay**: The resistance $R_{i \rightarrow s}$ is ONLY the resistance on the path that is common to BOTH the source-to-$i$ and source-to-leaf paths
2. **Forgetting parasitic capacitance**: The driving gate's own diffusion capacitance (3C for an inverter) is always present, even with zero load
3. **Using wrong resistance for PMOS**: PMOS has $\sim 2R$ per unit width, not $R$
4. **Not accounting for internal nodes in NAND/NOR**: Series stacks create internal diffusion nodes with their own capacitance

---

## Self-Check Questions

**Q1:** What is the Elmore delay of a single resistor $R$ driving a single capacitor $C$?

> **A:** $t_{pd} = RC$. The simplest case.

**Q2:** An inverter with width 2 drives 8 identical inverters. What is the fanout $h$ and delay?

> **A:** $h = m/w = 8/2 = 4$. $t_{pd} = (3 + 3 \times 4)RC = 15RC$.

**Q3:** Why is $t_{pdr}$ larger than $t_{pdf}$ for a NAND gate?

> **A:** During rise, only one PMOS conducts (resistance $R$), and the internal NMOS stack nodes must also be charged. During fall, all three NMOS transistors conduct simultaneously in series.

---

## Concept Links

- The basic RC delay model is introduced in [Dynamic Characteristics](./05_dynamic_characteristics.md)
- Chain optimization using these RC concepts is in [Inverter Sizing](./06_inverter_sizing.md)
- Logical effort builds on the parasitic delay concept from Elmore in [Logical Effort](./08_logical_effort.md)
- All Elmore delay formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#elmore-delay)
