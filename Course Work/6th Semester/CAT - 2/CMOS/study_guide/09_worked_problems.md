# Worked Problems - All Solved Problems from Lectures

All problems from the lecture slides are solved here, grouped by topic. For each problem:
- **Concepts Used** links to the relevant topic file
- **Formulas Used** links to the formula sheet
- **Step-by-step solution** with full working
- **Answer boxed** at the end

---

## CMOS Inverter Static Characteristics

### Problem 1: Noise Margin Calculation

**Source:** Slide 29, PPTX 12-12

![[problem1_parameters.png]]

**Given:**
- $V_{DD} = 2.5V$
- $V_{T,n} = 0.4V$, $V_{T,p} = -0.4V$
- $k_n = 115 \mu A/V^2$, $k_p = 57.5 \mu A/V^2$
- $k_R = k_p/k_n = 0.5$

**Find:** Noise margins $NM_L$ and $NM_H$

**Concepts Used:** [VIL/VIH derivation](./03_cmos_inverter_design.md), [Noise Margins](./02_cmos_inverter_static.md#noise-margins)

**Formulas Used:** [VIL formula](./10_formula_sheet_ultimate.md#vil-vih-derivations), [Noise margin definitions](./10_formula_sheet_ultimate.md#noise-margins)

**Solution:**

**Step 1:** Note that $k_R = 0.5 \neq 1$, so this is NOT a symmetric inverter.

**Step 2:** Calculate $V_{IL}$

At $V_{IL}$: NMOS is saturated, PMOS is linear. Using KCL ($I_{D,n} = I_{D,p}$):

From the VIL formula with $k_R = 0.5$:
- First find Vout at the $dV_{out}/dV_{in} = -1$ point
- Then substitute back to get $V_{IL}$

After solving: $V_{IL} \approx 1.03V$

**Step 3:** Calculate $V_{IH}$

At $V_{IH}$: NMOS is linear, PMOS is saturated.

After solving: $V_{IH} \approx 1.45V$

**Step 4:** Calculate noise margins

Since for CMOS: $V_{OL} = 0V$ and $V_{OH} = V_{DD} = 2.5V$

$$NM_L = V_{IL} - V_{OL} = 1.03 - 0 = 1.03V$$
$$NM_H = V_{OH} - V_{IH} = 2.5 - 1.45 = 1.05V$$

$$\boxed{NM_L = 1.03V, \quad NM_H = 1.05V}$$

**Verification:** Both noise margins are positive and substantial (> 1V), indicating good noise immunity. $NM_L \approx NM_H$ despite asymmetric $k_R$ because $V_{DD}$ is large relative to threshold voltages.

---

## Dynamic Characteristics & Propagation Delay

### Problem 2: Delay Calculation

**Source:** Slide 43, PPTX 12-12

![[problem2_delay_solution.png]]

**Given:**
- $V_{DD} = 2.5V$
- NMOS: Normalized on-resistance $R_n \cdot (W/L) = 13 k\Omega$, $(W/L)_n = 1.5$
- PMOS: Normalized on-resistance $R_p \cdot (W/L) = 31 k\Omega$, $(W/L)_p = 4.5$
- $C_{db,n} = 6.1 fF$, $C_{db,p} = 6.0 fF$

**Find:** Propagation delay

**Concepts Used:** [First-order RC delay](./05_dynamic_characteristics.md#propagation-delay-first-order-analysis)

**Formulas Used:** [Delay formula](./10_formula_sheet_ultimate.md#propagation-delay)

**Solution:**

**Step 1:** Compute actual on-resistances:
$$R_{eq,n} = \frac{13k\Omega}{(W/L)_n} = \frac{13k}{1.5} = 8.67 k\Omega$$
$$R_{eq,p} = \frac{31k\Omega}{(W/L)_p} = \frac{31k}{4.5} = 6.89 k\Omega$$

**Step 2:** Compute load capacitance $C_L$:
$$C_L = C_{db,n} + C_{db,p} + C_{gd} + C_{fanout}$$
(Use values from the slide for all capacitance components)

**Step 3:** Apply delay formulas:
$$t_{pHL} = 0.69 \times R_{eq,n} \times C_L$$
$$t_{pLH} = 0.69 \times R_{eq,p} \times C_L$$
$$t_p = \frac{t_{pHL} + t_{pLH}}{2}$$

$$\boxed{t_p = 0.69 \times C_L \times \frac{8.67 + 6.89}{2} k\Omega}$$

---

## Inverter Chain Sizing

### Problem 3: Inverter Chain Design

**Source:** Slide 53, PPTX 12-12

![[inverter_chain_problem.png]]

**Given:**
- Multiple inverter chain configurations (shown in figure)
- $t_{p0} = 1$ (unloaded delay, normalized)
- $\gamma = 1$

**Find:** Effective fanout ($f$) and propagation delay ($t_p$) for each configuration

**Concepts Used:** [Chain sizing](./06_inverter_sizing.md#inverter-chain-sizing)

**Formulas Used:** [Chain delay formula](./10_formula_sheet_ultimate.md#inverter-chain-optimization)

**Solution:**

For each chain configuration:

**Step 1:** Calculate overall fanout: $F = C_L / C_{g1}$

**Step 2:** Calculate per-stage fanout: $f = F^{1/N}$ where $N$ = number of stages

**Step 3:** Calculate total delay: $t_p = N \times t_{p0}(1 + f/\gamma)$

| Config | $F$ | $N$ | $f$ | $t_p$ |
|--------|-----|-----|-----|--------|
| Single stage | 64 | 1 | 64 | $1 \times (1 + 64) = 65$ |
| 2 stages | 64 | 2 | 8 | $2 \times (1 + 8) = 18$ |
| 3 stages | 64 | 3 | 4 | $3 \times (1 + 4) = 15$ |
| 4 stages | 64 | 4 | 2.83 | $4 \times (1 + 2.83) = 15.3$ |
| 6 stages | 64 | 6 | 2 | $6 \times (1 + 2) = 18$ |

$$\boxed{N = 3 \text{ stages (fanout of 4) gives minimum delay of } 15 t_{p0}}$$

---

## RC Delay & Elmore Delay Problems

### Problem 4: 2nd Order RC Elmore Delay

**Source:** Slide 17, PPTX 12-16

![[elmore_delay_2nd_order.png]]

**Given:** 2nd-order RC system with $R_1, C_1$ at node $n_1$ and $R_2, C_2$ at output $V_{out}$.

**Find:** Elmore delay for $V_{out}$

**Concepts Used:** [Elmore delay](./07_rc_delay_and_elmore.md#elmore-delay-model)

**Solution:**

Using the Elmore delay formula: $t_{pd} = \sum_i C_i \cdot R_{i \rightarrow s}$

- Node $n_1$: $C_1$, shared resistance to source = $R_1$
- Node $V_{out}$: $C_2$, shared resistance to source = $R_1 + R_2$

$$\boxed{t_{pd} = R_1 C_1 + (R_1 + R_2) C_2}$$

---

### Problem 5: Inverter Driving $m$ Gates (Elmore)

**Source:** Slide 18, PPTX 12-16

![[elmore_inverter_fanout_m.png]]

**Given:** Unit inverter driving $m$ identical unit inverters.

**Find:** Elmore delay

**Solution:**

- Load: $m \times 3C = 3mC$ (gate capacitance)
- Parasitic: $3C$ (drain diffusion of driver)
- Total: $(3 + 3m)C$
- Resistance: $R$

$$\boxed{t_{pd} = (3 + 3m)RC}$$

---

### Problem 6: Width-$w$ Driver (Fanout Definition)

**Source:** Slide 19, PPTX 12-16

![[elmore_width_w_driver.png]]

**Given:** Driver is $w$ times unit size, drives $m$ unit inverters.

**Find:** Delay and fanout definition

**Solution:**

- Resistance: $R/w$
- Diffusion capacitance: $3wC$
- Load: $3mC$

$$t_{pd} = (3w + 3m) \cdot \frac{RC}{w} = \left(3 + \frac{3m}{w}\right)RC$$

Define fanout $h = m/w$:

$$\boxed{t_{pd} = (3 + 3h)RC}$$

---

### Problem 7: FO4 Delay (65nm)

**Source:** Slide 20, PPTX 12-16

![[fo4_delay_problem.png]]

**Given:** $R = 10 k\Omega$, $C = 0.1 fF$ (65nm process), $h = 4$

**Find:** Delay in picoseconds

**Solution:**

$$RC = 10 \times 10^3 \times 0.1 \times 10^{-15} = 1 \times 10^{-12} = 1 ps$$
$$t_{pd} = (3 + 3 \times 4) \times 1 ps = 15 ps$$

$$\boxed{t_{pd} = 15 ps \text{ (FO4 delay in 65nm)}}$$

---

### Problem 8: 3-Input NAND Gate Delay

**Source:** Slides 21-22, PPTX 12-16

![[nand3_transistor_sizing.png]]

![[nand3_delay_solution.png]]

**Given:** 3-input NAND gate (from Problem 1 of PPTX 12-16) driving $h$ identical NAND gates.

**Find:** $t_{pdf}$ and $t_{pdr}$

**Solution:**

**Falling transition** (all inputs HIGH, NMOS stack discharges):

Using Elmore delay with 3 series NMOS (each $R/3$):
- Node $n_1$: $C = 3C$, $R_{shared} = R/3$ &rarr; contribution: $(3C)(R/3) = RC$
- Node $n_2$: $C = 3C$, $R_{shared} = 2R/3$ &rarr; contribution: $(3C)(2R/3) = 2RC$
- Node $Y$: $C = (9+5h)C$, $R_{shared} = R$ &rarr; contribution: $(9+5h)RC$

$$\boxed{t_{pdf} = (12 + 5h)RC}$$

**Rising transition** (worst case: outer input falls):

Single PMOS with resistance $R$. The shared resistance for all nodes is $R$:
- Node $n_1$: $3C$, $R_{shared} = R$ &rarr; $3RC$
- Node $n_2$: $3C$, $R_{shared} = R$ &rarr; $3RC$
- Node $Y$: $(9+5h)C$, $R_{shared} = R$ &rarr; $(9+5h)RC$

$$\boxed{t_{pdr} = (15 + 5h)RC}$$

---

## Logical Effort Problems

### Problem 9: Ring Oscillator Frequency

**Source:** Slide 14, PPTX 12-19

![[ring_oscillator_problem.png]]

**Given:** $N$-stage ring oscillator made of inverters.

**Find:** Oscillation frequency.

**Concepts Used:** [Logical Effort](./08_logical_effort.md#single-gate-examples)

**Solution:**

Each inverter: $g = 1$, $h = 1$ (drives one identical inverter), $p = 1$

Delay per stage: $d = gh + p = 1 + 1 = 2$

Period: Signal must traverse ring twice to return to same polarity: $T = 2 \times N \times d = 4N$

$$\boxed{f = \frac{1}{4N}}$$

---

### Problem 10: Multi-Stage Path Minimum Delay

**Source:** Slides 17-20, PPTX 12-19

![[min_delay_path_problem.png]]

**Given:** A multi-stage path from A to B with specified gates and loads.

**Find:** (a) Minimum delay, (b) Transistor sizes

**Concepts Used:** [Multi-stage networks](./08_logical_effort.md#multi-stage-logic-networks)

**Solution (a):**

1. List logical efforts: $g_1, g_2, g_3$ for each gate on the path
2. $G = g_1 \times g_2 \times g_3$
3. $H = C_{out}/C_{in}$
4. $B = \prod b_i$ (branching efforts)
5. $F = G \cdot H \cdot B$
6. $\hat{f} = F^{1/N}$
7. $D_{min} = N \cdot \hat{f} + P$

**Solution (b):** Work backward from load:

![[min_delay_path_solution.png]]

- $y = 45 \times (5/3)/5 = 15$
- $x = (15 + 15) \times (5/3)/5 = 10$

$$\boxed{x = 10, \quad y = 15}$$

---

### Problem 11: Path Branching Effort

**Source:** Slides 25-26, PPTX 12-19

![[path_branch_circuit.png]]

**Given:** Circuit with branching (off-path loads)

**Find:** Path effort $F$

**Solution:**

![[path_branch_solution.png]]

Compute branching effort at each node where the path splits, then multiply by logical and electrical efforts.

---

## Practice Problems (Additional)

### Practice 1: Symmetric Inverter Design

**Q:** Design a symmetric CMOS inverter ($V_M = V_{DD}/2$) given:
- $V_{DD} = 3.3V$, $V_{T,n} = 0.6V$, $|V_{T,p}| = 0.7V$
- $\mu_n/\mu_p = 2.5$
- $(W/L)_n = 1$ (minimum)

**A:** For $V_M = V_{DD}/2$ with $V_{T,n} = |V_{T,p}|$: need $k_R = 1$:

$k_R = (\mu_p/\mu_n) \times (W/L)_p/(W/L)_n = 1$

$(W/L)_p = (\mu_n/\mu_p) \times (W/L)_n = 2.5 \times 1 = 2.5$

Since $V_{T,n} \neq |V_{T,p}|$, use the full formula:

$k_R = \left(\frac{V_M - V_{T,n}}{V_{DD} + V_{T,p} - V_M}\right)^2 = \left(\frac{1.65 - 0.6}{3.3 - 0.7 - 1.65}\right)^2 = \left(\frac{1.05}{0.95}\right)^2 = 1.22$

$(W/L)_p = k_R \times (\mu_n/\mu_p) = 1.22 \times 2.5 = 3.05$

$$\boxed{(W/L)_p = 3.05}$$

---

### Practice 2: Power Calculation

**Q:** A chip has $10^7$ gates, each with average $C_L = 20 fF$. $V_{DD} = 1.0V$, $f_{clock} = 2 GHz$, $\alpha = 0.15$. Calculate total dynamic power.

**A:**
$$P = N \times C_L \times V_{DD}^2 \times \alpha \times f_{clock}$$
$$P = 10^7 \times 20 \times 10^{-15} \times 1.0^2 \times 0.15 \times 2 \times 10^9$$
$$P = 10^7 \times 20 \times 10^{-15} \times 0.3 \times 10^9 = 60 \times 10^{-15+7+9} = 60 \times 10^{1}$$

$$\boxed{P = 60 W}$$

---

### Practice 3: Elmore Delay with Branching

**Q:** A unit inverter drives 3 identical unit inverters and 2 are on the path. Compute the delay to the on-path load.

**A:**
Total load: $3 \times 3C = 9C$. Parasitic: $3C$. Total: $12C$.
The Elmore delay to ANY leaf node is the same since they all share the same resistance:
$$t_{pd} = (3 + 9)RC = 12RC$$

Fanout: $h = 3/1 = 3$, so $t_{pd} = (3 + 3 \times 3)RC = 12RC$ (consistent).

$$\boxed{t_{pd} = 12RC}$$

---

### Practice 4: Logical Effort Comparison

**Q:** Compare the delay of implementing $Y = \overline{AB}$ using (a) a NAND2 gate, and (b) an AND2 gate (NAND2 + inverter), both with electrical effort $H = 8$.

**A:**

**(a) NAND2 alone:**
- $G = 4/3$, $N = 1$, $F = GH = (4/3)(8) = 32/3$
- $D = 32/3 + 2 = 12.67$

**(b) NAND2 + INV:**
- $G = (4/3)(1) = 4/3$, $N = 2$, $F = (4/3)(8) = 32/3$
- $\hat{f} = (32/3)^{1/2} = 3.27$
- $D = 2 \times 3.27 + (2 + 1) = 6.54 + 3 = 9.54$

$$\boxed{\text{Two stages (b) is faster: } 9.54 < 12.67}$$

This shows that adding an inverter can actually REDUCE delay by distributing the effort more evenly.
