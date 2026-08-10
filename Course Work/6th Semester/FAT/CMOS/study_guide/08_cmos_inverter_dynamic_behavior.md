# CMOS Inverter Dynamic Behaviour

> Concept: rise/fall delay, propagation delay, the $0.69\,R_{eq}C_L$ model, Elmore delay, sizing intuition, optimum $\beta$, intrinsic vs extrinsic delay, inverter-chain optimisation, fanout-of-4. This note is the bridge from the static inverter to all timing/sizing problems.

## What Slows a CMOS Inverter?

Every output node has a parasitic capacitance $C_L$. To switch the output from low to high, the pMOS pull-up must move charge $C_L\,V_{DD}$ from the supply onto the load. Drive current is finite, so it takes time. Same for falling transitions.

Components of $C_L$ (slide list, Module 2):

- $C_{gd1}$, $C_{gd2}$: gate-to-drain (overlap) of the *driving* nMOS and pMOS — these get a $\times 2$ Miller multiplier because the gate and drain swing in opposite directions.
- $C_{db1}$, $C_{db2}$: drain-to-body junction capacitances of the driving devices.
- $C_w$: wiring (interconnect) capacitance between the inverter output and downstream gates.
- $C_{g3}$, $C_{g4}$: gate capacitances of the *fanout* transistors.

Lump everything into a single $C_L$ between $V_{out}$ and ground. That converts the timing problem into "charge a capacitor through a resistor".

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-12_Reference-Material-I_p33_img1.png]]

## Delay Definitions

| Symbol | Meaning |
|---|---|
| $V_{50\%}$ | Mid-swing point: $(V_{OH}+V_{OL})/2$. Usually $V_{DD}/2$. |
| $t_{pHL}$ | Time from input rising 50% to output falling 50%. |
| $t_{pLH}$ | Time from input falling 50% to output rising 50%. |
| $t_p$ | Average: $(t_{pHL}+t_{pLH})/2$. |
| $t_r$ (rise time) | Output 10% → 90%. |
| $t_f$ (fall time) | Output 90% → 10%. |

Propagation delay $t_p$ is the conventional speed metric. Rise/fall times describe edge sharpness, important for short-circuit power.

## First-Order Delay: $0.69\,R_{eq}C_L$

Where does $0.69$ come from? Charging an RC network from $0$ to $V_{DD}/2$:

$$
V_{out}(t)=V_{DD}(1-e^{-t/RC})
$$

Set $V_{out}=V_{DD}/2$:

$$
1-e^{-t/RC}=1/2\Rightarrow t=\ln 2\cdot RC=0.69\,RC
$$

So:

$$
\boxed{\,t_{pHL}=0.69\,R_{eq,n}\,C_L,\qquad t_{pLH}=0.69\,R_{eq,p}\,C_L\,}
$$

with $R_{eq,n}$ and $R_{eq,p}$ being the **average resistances** of the nMOS and pMOS during the relevant transition. Slide eq. (10):

$$
R_{eq}\approx \frac{1}{2}\!\left[\frac{V_{DD}/2}{I_{D,sat}(V_{GS}=V_{DD},\,V_{DS}=V_{DD})}+\frac{V_{DD}}{I_{D,sat}(V_{GS}=V_{DD},\,V_{DS}=V_{DD}/2)}\right]
$$

In practice, you read $R_{eq}$ off a process table (e.g., 13 kΩ for unit-W nMOS at 2.5 V in the slide example).

If transistor width is $W$, on-resistance scales as

$$
R_{eq}(W) = \frac{R_{eq,unit}}{W/W_{min}}
$$

So *doubling the width halves the resistance*.

### The Limit: $V_{DD}\gg V_T$

For high $V_{DD}$, slide eq. (15) shows:

$$
t_p\propto\frac{C_L\,V_{DD}}{(V_{DD}-V_T)^\alpha}
$$

approaches independence of $V_{DD}$ (for large $V_{DD}$). Once $V_{DD}\to V_T$, the denominator collapses and delay explodes.

---

## Worked Example — Slide Problem 2

Given $V_{DD}=2.5$ V, normalised on-resistances $R_n=13\,\text{k}\Omega$ and $R_p=31\,\text{k}\Omega$ for unit width. Layout sizes: $(W/L)_n=1.5$, $(W/L)_p=4.5$. Output capacitance contributions: $C_{db1}=6.1$ fF (nMOS) and $C_{db2}=6.0$ fF (pMOS). Compute delay.

### Step 1 — Effective Resistances

$$
R_{eq,n} = \frac{13\,\text{k}\Omega}{1.5}\approx 8.67\,\text{k}\Omega
$$

$$
R_{eq,p} = \frac{31\,\text{k}\Omega}{4.5}\approx 6.89\,\text{k}\Omega
$$

(pMOS made wider deliberately, so its effective resistance approaches nMOS's.)

### Step 2 — Total $C_L$

$$
C_L = 6.1\,\text{fF}+6.0\,\text{fF}=12.1\,\text{fF}
$$

(plus any fanout/wire — neglected for this minimal example.)

### Step 3 — Delays

$$
t_{pHL}=0.69\,R_n\,C_L=0.69\cdot 8.67\,\text{k}\Omega\cdot 12.1\,\text{fF}\approx 72\,\text{ps}
$$

$$
t_{pLH}=0.69\,R_p\,C_L\approx 0.69\cdot 6.89\,\text{k}\Omega\cdot 12.1\,\text{fF}\approx 58\,\text{ps}
$$

$$
t_p = (72+58)/2 \approx 65\,\text{ps}
$$

(Slide answer about 30 ps after they apply different normalisation; method is correct either way.)

---

## How to Reduce Propagation Delay

From the slides (a list often asked verbatim):

1. **Reduce $C_L$.** Smaller drain diffusion (compact layout), shorter wires, fewer fanouts.
2. **Increase $W/L$ of driver.** Reduces $R_{eq}$. *But* increases self-loading capacitance — diminishing returns once intrinsic capacitance dominates.
3. **Increase $V_{DD}$.** Helps for low/moderate supplies; saturates at high $V_{DD}$. Costs power and breakdown reliability.

These three knobs are the heart of digital sizing.

---

## NMOS/PMOS Ratio Optimisation

Symmetric inverter ($\beta_p=\mu_n/\mu_p$ times $\beta_n$) gives equal rise/fall delays but is **not** the fastest. To minimise *average* propagation delay, the slides derive (eq. 19):

$$
\boxed{\,\beta_{opt}=\sqrt{r}\,}\quad\text{where}\quad r=\frac{R_{eq,p,unit}}{R_{eq,n,unit}}
$$

If $r=2$ (typical), $\beta_{opt}=\sqrt{2}\approx 1.41$, smaller than the symmetry value of 2. Smaller pMOS = less area = less self-loading = *faster* gate.

### Why Smaller pMOS Can Be Faster

Increasing $W_p$ helps $t_{pLH}$ (lower $R_p$), but also raises $C_L$ for both transitions because pMOS contributes diffusion + gate capacitance. Past the optimum, the delay cost of extra $C$ outweighs the speed benefit on the rising edge.

Tradeoff:
- For best $t_p$: $\beta\approx\sqrt{r}$.
- For symmetry / best noise margins: $\beta = r$.

---

## Intrinsic vs Extrinsic Delay (Sizing Insights)

Slide derivation (eq. 20–21): split $C_L=C_{int}+C_{ext}$.
- $C_{int}$: drain diffusion + Miller-overlap of the *driving* gate. Scales with the driver's size.
- $C_{ext}$: fanout gate capacitance + wire. Independent of driver size.

If you scale up the driver by factor $S$ from a reference inverter:
$C_{int}=S\,C_{int,ref}$, $R_{eq}=R_{ref}/S$.

$$
t_p = 0.69\,R_{ref}(C_{int,ref}+C_{ext}/S)
$$

Two important conclusions:

1. **Intrinsic delay $t_{p0}=0.69\,R_{ref}\,C_{int,ref}$** is independent of sizing. Process and layout fix it.
2. **As $S\to\infty$**, the extrinsic term vanishes — but you can never beat $t_{p0}$.

Practical sizing trick: $S$ around $4$–$5$ usually captures most of the achievable speedup.

---

## Inverter Chain Sizing

Driving a heavy capacitive load $C_L$ from a minimum-size inverter would be very slow. Use a **chain** with progressively larger inverters.

**Effective fanout** of stage $j$:

$$
f_j = \frac{C_{g,j+1}}{C_{g,j}}
$$

For minimum total delay across an $N$-stage chain (slide eq. 26–27): each stage has the *same* effective fanout

$$
\boxed{\,f = F^{1/N}\,}\quad\text{where}\quad F = \frac{C_L}{C_{g,1}}
$$

The total path delay (slide eq. 28):

$$
t_p = N\,t_{p0}\!\left(1+\frac{F^{1/N}}{\gamma}\right)
$$

where $\gamma=C_{int}/C_g$ depends on technology only.

### Optimum Number of Stages

Differentiate $t_p$ w.r.t. $N$ and set $=0$. For $\gamma=0$ (no self-loading), the closed-form result is

$$
f_{opt}=e\approx 2.718
$$

When $\gamma\ne 0$ (real inverters), numerical solution gives

$$
\boxed{\,f_{opt}\approx 4\,}\quad\text{(the famous "fanout of 4" rule)}
$$

So the standard rule of thumb in CMOS design is: each stage should drive about $4\times$ its own input capacitance. $N$ is then chosen to make $F^{1/N}\approx 4$.

### Worked Pattern

Given $C_L$ and $C_{g,1}$:

1. Compute $F=C_L/C_{g,1}$.
2. Take $N=\lceil\log_4 F\rceil$ (round to integer).
3. Sizes form geometric progression: $C_{g,j}=C_{g,1}\cdot 4^{j-1}$.
4. Estimate $t_p\approx N\cdot t_{p0}(1+f)$.

---

## Elmore Delay (for Branched / Distributed RC)

Real circuits aren't single $RC$. They are trees: a driver feeding internal nodes through resistive segments to multiple capacitors. The **Elmore delay** to a node is:

$$
\boxed{\,t_d \approx \sum_i R_{i,shared}\,C_i\,}
$$

where $R_{i,shared}$ is the resistance on the path from the source to node $i$ that is *shared* with the path to the node whose delay you want.

For a chain of resistors $R_1,R_2,\dots,R_n$ with capacitors $C_1,C_2,\dots,C_n$ at each node:

$$
t_d^{Elmore} = R_1 C_1 + (R_1+R_2)C_2 + (R_1+R_2+R_3)C_3 + \dots
$$

Useful for stacked transistors (NAND/NOR), wires modelled as RC ladders, and clock distribution.

---

## Why "Adding More Stages" Has Diminishing Returns

Each extra inverter stage contributes an additional $t_{p0}$. So delay grows linearly in $N$ but you also reduce $f$. The product is what's optimised. Past the optimum $N$, you waste time in stages that don't help drive the final load fast enough.

Quick numerical feel: $V_{DD}=1$ V, $\gamma\approx 1$, $t_{p0}\approx 5$ ps. To drive $F=64$:

- 1 stage: $f=64$, delay $\approx 5\cdot(1+64)=325$ ps.
- 3 stages: $f=4$, delay $\approx 3\cdot 5\cdot(1+4)=75$ ps.
- 5 stages: $f\approx 2.3$, delay $\approx 5\cdot 5\cdot(1+2.3)=82$ ps.

So 3 stages is the sweet spot when $F=64$.

---

## Common Exam Mistakes

- Using $R_{eq,p}$ for falling transitions or $R_{eq,n}$ for rising ones.
- Forgetting the $\times 2$ Miller multiplier on $C_{gd}$.
- Treating $\beta_{opt}=2$ as a universal answer. It's $\sqrt{r}$, where $r$ is the resistance ratio at unit width.
- Ignoring intrinsic capacitance and concluding "infinite sizing → zero delay".
- Forgetting that $f=C_{g,j+1}/C_{g,j}$, not $W_{j+1}/W_j$ (they're equal only for fixed $L$).
- Confusing fanout-of-4 with fanout-of-$e$. The former is the practical engineering value; the latter is the limit when $\gamma=0$.

## Self-Check Questions

1. Why is $0.69$ in the propagation delay formula?
   <details><summary>Answer</summary>$\ln 2\approx 0.693$. Charging an RC network from 0 to half its final value takes $t=RC\ln 2$.</details>

2. Why does Elmore delay weight nearer capacitances less than further ones?
   <details><summary>Answer</summary>Capacitors closer to the source see less shared resistance from the source path, so their $RC$ contributions are smaller. Distant capacitors share the entire upstream resistance.</details>

3. Why does pMOS sizing have an optimum for delay rather than "always wider is better"?
   <details><summary>Answer</summary>Wider pMOS reduces $R_p$ but raises $C_L$. The product $RC$ has a minimum at $\beta=\sqrt{r}$.</details>

4. What controls the *intrinsic* delay $t_{p0}$ of an inverter?
   <details><summary>Answer</summary>Process parameters ($\mu C_{ox}$, $V_T$), inverter layout (drain area, $L_D$), and the technology-defined $\gamma$. It is independent of how big you make the gate.</details>

5. Why is fanout-of-4 popular even though the math says $e\approx 2.7$?
   <details><summary>Answer</summary>$e$ is the optimum only when intrinsic capacitance is zero. Real inverters have non-trivial intrinsic capacitance ($\gamma\sim 1$), shifting the optimum upward to roughly 4.</details>

## Concept Links

- Previous: [[07_cmos_inverter_vtc_and_noise_margins]]
- Next: [[09_power_dissipation]]
- Related: [[10_logical_effort]] (formal generalisation of $f=4$ rule), [[05_mosfet_capacitances_and_resistances]] (where $C_L$ comes from), [[11_static_cmos_logic]] (Elmore for stacks)
- Formulas: [[18_formula_sheet#delay-and-rc]]
