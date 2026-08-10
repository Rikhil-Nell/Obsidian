# Inverter Sizing & Chain Optimization

## Learning Objectives

After this section you will understand:
- How the NMOS/PMOS width ratio ($\beta$) affects delay
- The optimal $\beta$ for minimum delay (not necessarily for symmetric VTC)
- How to size a chain of inverters to minimize total delay
- The effective fanout concept and why "fanout of 4" is optimal
- How to choose the optimal number of inverter stages

---

## The NMOS/PMOS Sizing Problem

We know from [CMOS Inverter Design](./03_cmos_inverter_design.md) that making the PMOS about 2.5x wider than the NMOS gives a **symmetric** VTC ($V_M = V_{DD}/2$). But is symmetric also **fastest**?

**Surprising answer: No!** The fastest inverter uses a **smaller** PMOS than the symmetric case.

### Why Does This Happen?

Making the PMOS wider has two competing effects:
1. **Reduces $R_{eq,p}$** (good for speed during LOW-to-HIGH transition)
2. **Increases $C_L$** (bad for speed because more capacitance to charge/discharge)

At some point, the capacitance penalty outweighs the resistance benefit.

---

## Optimal NMOS/PMOS Ratio

Define $\beta = (W/L)_p / (W/L)_n$ as the PMOS-to-NMOS width ratio.

### Load Capacitance with Ratio $\beta$

For two cascaded identical inverters, the load capacitance of the first gate:

$$C_L = C_{dn1} + C_{dp1} + C_{gn2} + C_{gp2} + C_w$$

Since all PMOS capacitances scale with $\beta$:
$$C_{dp1} \approx \beta \cdot C_{dn1}, \quad C_{gp2} \approx \beta \cdot C_{gn2}$$

$$C_L = (1 + \beta)(C_{dn1} + C_{gn2}) + C_w$$

### Propagation Delay Expression

$$t_p = 0.69 \cdot C_L \cdot \frac{R_{eq,n} + R_{eq,p}}{2}$$

Since $R_{eq,p} = r \cdot R_{eq,n}/\beta$ (where $r = R_{eq,p}/R_{eq,n}$ for identically-sized devices, typically $r \approx 2$):

$$t_p = 0.69 \cdot (1 + \beta)(C_{dn1} + C_{gn2}) \cdot R_{eq,n} \cdot \frac{1 + r/\beta}{2}$$

### Finding $\beta_{opt}$

Setting $\partial t_p / \partial \beta = 0$:

$$\boxed{\beta_{opt} = \sqrt{r} \approx \sqrt{2} \approx 1.4}$$

![[beta_opt_curve.png]]

**Key insight:** $\beta_{opt} \approx 1.4$, which is significantly less than the $\beta \approx 2.5$ needed for a symmetric VTC. Smaller PMOS = smaller area AND faster circuit!

When wiring capacitance dominates (large $C_w$): $\beta_{opt}$ approaches $r$ (about 2-2.5), matching the symmetric case.

---

## Sizing Inverters for Performance

### Intrinsic vs Extrinsic Delay

The propagation delay can be split into two components:

$$\boxed{t_p = t_{p0} + t_{p0} \cdot \frac{C_{ext}}{C_{int}}}$$

Where:
- $t_{p0} = 0.69 \cdot R_{eq} \cdot C_{int}$ = **intrinsic (unloaded) delay** -- delay due to the gate's own capacitance
- $C_{int}$ = intrinsic output capacitance (diffusion caps)
- $C_{ext}$ = extrinsic load (fanout + wiring)

![[sizing_performance_curve.png]]

### Effect of Sizing Factor $S$

If we scale a gate by factor $S$ (all widths multiplied by $S$):
- $R_{eq}$ decreases by $S$ (wider = lower resistance)
- $C_{int}$ increases by $S$ (wider = more diffusion capacitance)

$$C_{int} = S \cdot C_{i,ref}, \quad R_{eq} = R_{ref}/S$$

So the delay becomes:

$$\boxed{t_p = t_{p0}\left(1 + \frac{C_{ext}}{S \cdot C_{i,ref}}\right)}$$

**Key conclusions:**
1. The intrinsic delay $t_{p0}$ is **independent of sizing** -- it is a technology constant
2. Making $S$ very large reduces the extrinsic contribution but never below $t_{p0}$
3. There are diminishing returns to making gates larger

---

## Inverter Chain Sizing

### The Problem

You need to drive a large load $C_L$ from a small input gate $C_{g1}$ (typically minimum-sized). A single inverter cannot do this efficiently because the fanout ($C_L/C_{g1}$) would be enormous, causing huge delay.

**Solution:** Use a chain of $N$ progressively larger inverters.

![[inverter_chain_problem.png]]

### Delay of a Single Stage in the Chain

For the $j$-th inverter in a chain:

$$t_{p,j} = t_{p0}\left(1 + \frac{C_{g,j+1}}{C_{int,j}}\right) = t_{p0}(1 + f_j/\gamma)$$

Where:
- $f_j = C_{g,j+1}/C_{g,j}$ = effective fanout of stage $j$
- $\gamma = C_{int}/C_{g}$ = ratio of intrinsic to gate capacitance (technology-dependent, typically $\gamma \approx 1$)

### Total Delay of the Chain

$$\boxed{t_p = \sum_{j=1}^{N} t_{p,j} = t_{p0} \sum_{j=1}^{N}(1 + f_j/\gamma)}$$

### Minimizing Total Delay

Taking partial derivatives with respect to each gate size and setting to zero, the optimum is:

$$\boxed{C_{g,j} = \sqrt{C_{g,j-1} \cdot C_{g,j+1}}}$$

**Each gate size is the geometric mean of its neighbors.**

This means each stage has the **same effective fanout**:

$$\boxed{f = \sqrt[N]{F}, \quad \text{where } F = C_L/C_{g1} \text{ (overall fanout)}}$$

And the minimum total delay is:

$$\boxed{t_p = N \cdot t_{p0}\left(1 + \frac{\sqrt[N]{F}}{\gamma}\right)}$$

---

## Choosing the Optimal Number of Stages

Given $F = C_L/C_{g1}$, what value of $N$ gives the minimum delay?

Differentiating the delay expression with respect to $N$ and setting to zero:

For $\gamma = 0$ (ignoring self-loading): $f_{opt} = e \approx 2.72$

For $\gamma = 1$ (typical): $f_{opt} \approx 3.6$

**In practice, a common rule of thumb:**

$$\boxed{f_{opt} \approx 4 \quad (\text{fanout of 4 rule})}$$

This means each stage drives about 4 times the load of the previous stage.

![[optimal_stages_fanout4.png]]

### How to Use This

1. Given: $C_{g1}$ (input gate capacitance) and $C_L$ (load)
2. Compute: $F = C_L / C_{g1}$
3. Choose: $N = \text{round}(\log_4 F)$
4. Size each stage: $C_{g,j+1} = f \cdot C_{g,j}$ where $f = F^{1/N}$

---

## Worked Example: Inverter Chain

### Problem

Design an inverter chain to drive a load of $C_L = 64C$ from a minimum-sized inverter with $C_{g1} = C$. Use $\gamma = 1$ and $t_{p0} = 1$ (normalized).

### Solution

**Step 1:** $F = C_L/C_{g1} = 64C/C = 64$

**Step 2:** $N = \log_4(64) = 3$ stages

**Step 3:** Effective fanout per stage: $f = 64^{1/3} = 4$

**Step 4:** Stage sizes:
- Stage 1: $C_{g1} = C$ (given)
- Stage 2: $C_{g2} = 4C$
- Stage 3: $C_{g3} = 16C$
- Load: $C_L = 64C$

**Step 5:** Total delay:
$$t_p = 3 \times t_{p0}(1 + 4/1) = 3 \times 5 = 15 t_{p0}$$

Compare with a single stage: $t_p = t_{p0}(1 + 64/1) = 65 t_{p0}$. The chain is **4.3x faster**!

---

## Common Mistakes

1. **Using $\beta_{opt}$ for symmetric VTC**: $\beta_{opt} \approx 1.4$ gives minimum DELAY, not symmetric VTC ($\beta \approx 2.5$). Know which one the question asks for
2. **Forgetting $\gamma$**: The self-loading factor $\gamma$ changes the optimal fanout from $e$ to about $3.6$
3. **Using exactly $e$ or $3.6$ vs $4$**: In practice, use $f_{opt} = 4$ unless told otherwise. The difference between 3.6 and 4 is small
4. **Not considering odd/even inverter stages**: An odd number of stages inverts the signal. If you need non-inverting, you may need to add a stage (possibly increasing delay)

---

## Self-Check Questions

**Q1:** For minimum delay, should you use $\beta = 2.5$ or $\beta = 1.4$?

> **A:** $\beta_{opt} \approx 1.4$ for minimum delay. $\beta = 2.5$ gives a symmetric VTC but not the fastest gate.

**Q2:** You need to drive a load $C_L = 256C$ from a gate with $C_{g1} = C$. How many inverter stages do you need (using fanout-of-4)?

> **A:** $F = 256$, $N = \log_4(256) = 4$ stages.

**Q3:** What happens if you make an inverter chain with fanout of 2 per stage instead of 4?

> **A:** You need more stages ($N = \log_2(256) = 8$), and the total delay is HIGHER because the parasitic delay of each extra stage accumulates. The fanout-of-4 is optimal.

---

## Concept Links

- The $R_{eq}$ values used here come from [Dynamic Characteristics](./05_dynamic_characteristics.md)
- The capacitance components are detailed in [Dynamic Characteristics](./05_dynamic_characteristics.md)
- A more rigorous RC analysis is in [RC Delay & Elmore](./07_rc_delay_and_elmore.md)
- Logical effort generalizes this to non-inverter gates in [Logical Effort](./08_logical_effort.md)
- All sizing formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#inverter-sizing)
