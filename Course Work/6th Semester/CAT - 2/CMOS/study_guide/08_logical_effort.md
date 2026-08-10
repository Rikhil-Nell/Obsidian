# Logical Effort

## Learning Objectives

After this section you will understand:
- What logical effort is and why it was invented
- How to compute logical effort for any gate (inverter, NAND, NOR)
- What parasitic delay is and how to look it up
- The linear delay model: $d = gh + p$
- How to analyze multi-stage logic networks
- Branching effort and path effort
- How to find the minimum delay and optimal gate sizes for any path

---

## What is Logical Effort?

**The Big Picture:** We need a fast way to compare different logic gate topologies and decide:
1. What is the best way to implement a function?
2. How many stages of logic should we use?
3. How wide should each transistor be?

Logical effort gives us a simple, elegant framework to answer all three questions.

**Analogy:** Think of different trucks delivering packages. A pickup truck (inverter) can carry packages easily. A semi-truck (complex gate) can carry more but is slower to accelerate. Logical effort measures this "inherent slowness" of each gate type.

---

## The Linear Delay Model

The delay of any logic gate can be expressed as:

$$\boxed{d = f + p = g \cdot h + p}$$

Where:
- $d$ = total delay of the gate (in units of $\tau = 3RC$, same as FO1 inverter delay)
- $f = g \cdot h$ = **effort delay** (depends on what you're driving)
- $g$ = **logical effort** (inherent property of the gate type)
- $h$ = **electrical effort** = $C_{out}/C_{in}$ (fan-out ratio)
- $p$ = **parasitic delay** (inherent delay even with no load)

![[linear_delay_model.png]]

---

## Computing Logical Effort ($g$)

### Definition

$$\boxed{g = \frac{\text{Input capacitance of the gate}}{\text{Input capacitance of an inverter delivering the same output current}}}$$

In simpler terms: logical effort measures how much more input capacitance a gate needs compared to an inverter to drive the same load.

- An inverter has $g = 1$ by definition
- Any gate more complex than an inverter has $g > 1$

### Inverter ($g = 1$)

![[logical_effort_inverter.png]]

- NMOS: 1 unit width, capacitance = $C$
- PMOS: 2 unit width, capacitance = $2C$
- Total input capacitance: $C_{in,inv} = 3C$
- Logical effort: $g = 3C/3C = 1$

### 2-Input NAND ($g = 4/3$)

![[logical_effort_nand2.png]]

- Two NMOS in series: each must be 2x unit width = $2C$ each
- Two PMOS in parallel: each is 2x unit width = $2C$ each
- Total input capacitance per input: $2C + 2C = 4C$
- Logical effort: $g = 4C/3C = 4/3$

### 3-Input NAND ($g = 5/3$)

![[logical_effort_nand3.png]]

- Three NMOS in series: each must be 3x = $3C$
- Three PMOS in parallel: each is 2x = $2C$
- Input capacitance per input: $3C + 2C = 5C$
- Logical effort: $g = 5C/3C = 5/3$

### General NAND Formula

$$\boxed{g_{NAND,n} = \frac{n + 2}{3}}$$

where $n$ = number of inputs.

### 2-Input NOR ($g = 5/3$)

![[logical_effort_nor2.png]]

- Two NMOS in parallel: each is 1x = $C$
- Two PMOS in series: each must be 4x = $4C$
- Input capacitance per input: $C + 4C = 5C$
- Logical effort: $g = 5C/3C = 5/3$

### 3-Input NOR ($g = 7/3$)

![[logical_effort_nor3.png]]

- Three NMOS in parallel: 1x each = $C$
- Three PMOS in series: 6x each = $6C$
- Input capacitance per input: $C + 6C = 7C$
- Logical effort: $g = 7C/3C = 7/3$

### General NOR Formula

$$\boxed{g_{NOR,n} = \frac{2n + 1}{3}}$$

### Logical Effort Summary Table

![[logical_effort_table.png]]

| Gate | Inputs | Logical Effort $g$ |
|------|--------|---------------------|
| Inverter | 1 | 1 |
| NAND2 | 2 | 4/3 |
| NAND3 | 3 | 5/3 |
| NAND4 | 4 | 6/3 = 2 |
| NOR2 | 2 | 5/3 |
| NOR3 | 3 | 7/3 |
| NOR4 | 4 | 9/3 = 3 |

**Key insight:** NOR gates have higher logical effort than NAND gates with the same number of inputs. This is why NAND gates are preferred in CMOS design.

---

## Parasitic Delay ($p$)

The parasitic delay is the delay of a gate with **no external load** -- it comes from the gate's own internal (diffusion) capacitances.

![[parasitic_delay_calculation.png]]

![[parasitic_delay_table.png]]

| Gate | Parasitic Delay $p$ (in units of $p_{inv}$) |
|------|----------------------------------------------|
| Inverter | 1 |
| NAND2 | 2 |
| NAND3 | 3 |
| NOR2 | 2 |
| NOR3 | 3 |

**General rule:** $p \approx n \cdot p_{inv}$ for an $n$-input gate.

---

## Single Gate Examples

### Example: Ring Oscillator Frequency

![[ring_oscillator_problem.png]]

**Q:** Estimate the frequency of an $N$-stage ring oscillator.

**A:** Each inverter drives one identical inverter:
- $g = 1$, $h = 1$ (fanout of 1), $p = 1$
- $d = g \cdot h + p = 1 \times 1 + 1 = 2$

Period = $2N$ stage delays (signal traverses ring twice for one complete cycle):
$$T = 2 \times 2N = 4N$$

$$\boxed{f = \frac{1}{4N}}$$

---

## Multi-Stage Logic Networks

### Path Effort

For a path with $N$ stages, the key quantities are:

$$\boxed{G = \prod_{i=1}^{N} g_i} \quad \text{(path logical effort)}$$

$$\boxed{H = \frac{C_{out,path}}{C_{in,path}}} \quad \text{(path electrical effort)}$$

$$\boxed{F = G \cdot H \cdot B} \quad \text{(path effort)}$$

Where $B$ = path branching effort (see below).

### Minimum Delay of a Path

If each stage bears the **same effort** $\hat{f}$:

$$\hat{f} = F^{1/N}$$

The **minimum delay** of an $N$-stage path:

$$\boxed{D = N \cdot F^{1/N} + P}$$

where $P = \sum_{i=1}^{N} p_i$ is the total parasitic delay.

### Computing Gate Sizes (Working Backwards)

Once you know the optimal stage effort $\hat{f}$, compute the electrical effort of each stage:

$$h_i = \frac{\hat{f}}{g_i}$$

Then work **backwards** from the load:

$$C_{in,i} = \frac{C_{out,i}}{h_i}$$

### Multi-Stage Example

![[multistage_example.png]]

![[multistage_sizing_example.png]]

---

## Branching Effort

### What is Branching?

When a gate drives multiple loads, not all the load is on the critical path. The **branching effort** captures this:

$$\boxed{b_i = \frac{C_{on-path} + C_{off-path}}{C_{on-path}}}$$

The path branching effort is:

$$\boxed{B = \prod_i b_i}$$

And the path effort becomes:

$$\boxed{F = G \cdot H \cdot B}$$

### Branching Example

![[path_branch_circuit.png]]

![[path_branch_solution.png]]

---

## Worked Example: Minimum Delay Path

![[min_delay_path_problem.png]]

**Q:** (a) Estimate the minimum delay from A to B. (b) Choose transistor sizes to achieve this delay.

**Solution (a):**

![[min_delay_path_solution.png]]

1. Compute $G = \prod g_i$ (product of logical efforts along the path)
2. Compute $H = C_{out}/C_{in}$ (ratio of output to input capacitance)
3. Compute $B$ (product of branching efforts)
4. $F = G \cdot H \cdot B$
5. Determine $N$ (number of stages)
6. $\hat{f} = F^{1/N}$
7. $D_{min} = N \cdot \hat{f} + P$

**Solution (b):** Work backwards from the load:
- $y = 45 \times (5/3)/5 = 15$
- $x = (15 + 15) \times (5/3)/5 = 10$

---

## Best Stage Effort

The optimal stage effort that minimizes delay is approximately:

$$\boxed{\hat{f}_{opt} \approx 3.6}$$

This is found by minimizing $N \cdot F^{1/N} + P$ with respect to $N$. The exact value depends on the parasitic delay, but $\hat{f} \approx 3.6$ is a robust rule of thumb (consistent with the "fanout of 4" rule from [Inverter Sizing](./06_inverter_sizing.md) since $g_{inv} \cdot f_{opt} \approx 1 \times 4 = 4 \approx 3.6 + p$).

---

## Asymmetric Logic Gates

![[asymmetric_gates.png]]

In asymmetric gates, different inputs have different logical efforts because transistors are sized unequally. The "worst case" input (with highest logical effort) determines the delay on that path.

---

## Logical Effort Summary

![[logical_effort_summary.png]]

| Concept | Formula |
|---------|---------|
| Stage delay | $d = g \cdot h + p$ |
| Path effort | $F = G \cdot H \cdot B$ |
| Optimal stage effort | $\hat{f} = F^{1/N}$ |
| Minimum path delay | $D = N \cdot F^{1/N} + P$ |
| Gate sizing (backward) | $C_{in,i} = C_{out,i} \cdot g_i / \hat{f}$ |
| Logical effort (NAND-$n$) | $(n+2)/3$ |
| Logical effort (NOR-$n$) | $(2n+1)/3$ |
| Parasitic delay ($n$-input) | $\approx n \cdot p_{inv}$ |

---

## Common Mistakes

1. **Forgetting that logical effort $g$ is always relative to an inverter**: $g_{inv} = 1$ by definition. All other gates have $g > 1$
2. **Confusing electrical effort $h$ with fanout**: $h = C_{out}/C_{in}$ of a stage (correct), not the number of gates driven
3. **Not including branching effort**: If the gate drives off-path loads, $B > 1$, and you must include it in $F$
4. **Working sizes forward instead of backward**: Always work backward from the load to the input to find gate sizes
5. **Using wrong parasitic delay**: $p_{NAND3} = 3$, not $5/3$. Parasitic delay is NOT the same as logical effort

---

## Self-Check Questions

**Q1:** What is the logical effort of a 4-input NAND gate?

> **A:** $g = (4+2)/3 = 2$

**Q2:** A 2-input NOR gate drives a load of $30C$ and has input capacitance $5C$. What is the delay?

> **A:** $h = 30C/5C = 6$. $d = g \cdot h + p = (5/3)(6) + 2 = 10 + 2 = 12$

**Q3:** For a path with $G = 2$, $H = 16$, $B = 1$, $N = 3$: what is the minimum delay?

> **A:** $F = 2 \times 16 \times 1 = 32$. $\hat{f} = 32^{1/3} \approx 3.17$. $D = 3 \times 3.17 + P$.

---

## Concept Links

- RC modeling that underlies logical effort is in [RC Delay & Elmore](./07_rc_delay_and_elmore.md)
- Chain sizing using the fanout-of-4 rule is in [Inverter Sizing](./06_inverter_sizing.md)
- Gate transistor sizing comes from [Dynamic Characteristics](./05_dynamic_characteristics.md)
- All logical effort formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#logical-effort)
