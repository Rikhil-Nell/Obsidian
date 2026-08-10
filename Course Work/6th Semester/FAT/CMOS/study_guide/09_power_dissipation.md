# Power Dissipation in CMOS

> Concept: dissect the four power components (dynamic switching, short-circuit, glitching, leakage), compute them with activity factor, walk through the slide problems, understand why low-power matters in nanometer CMOS.

## The Master Equation

Slide eq. (Power 1) sums everything into one expression:

$$
\boxed{\,P_{total} = \alpha\,f\,C_L\,V_{DD}^2 \;+\; V_{DD}\,I_{peak}(P_{01}+P_{10}) \;+\; V_{DD}\,I_{leak}\,}
$$

The three terms are dynamic switching, short-circuit, and leakage respectively. In modern nodes:

- Dynamic ≈ 30–50% (and shrinking relatively)
- Short-circuit ≈ 10% (decreasing absolutely)
- Leakage ≈ 20–70% (and **increasing**)

Glitching power is treated as a sub-component of the dynamic term in this convention.

## Why Low Power Matters

The slides motivate this with a long list. The exam-friendly version:

- **Battery life** for portable devices.
- **Cooling cost** scales with dissipation; expensive packaging beyond a few W/cm$^2$.
- **Reliability** drops as temperature rises (electromigration, oxide stress).
- **Environmental impact**: 80% of office equipment energy comes from computing per EPA estimates.
- **Density limits**: power density ($W/\text{cm}^2$) is what physically limits how dense logic can be packed.

Recognise that scaling does **not** automatically reduce power any more — it once did, but in deep-submicron processes leakage dominates and aggressive $V_{DD}$ scaling is needed to keep dynamic power in check.

---

## 1. Dynamic Switching Power

When an output node charges from $0$ to $V_{DD}$, the supply gives up energy

$$
E_{supply} = C_L\,V_{DD}^2
$$

Half is stored on the capacitor, half is dissipated as heat in the pMOS during charging. When the node discharges, the stored half is dissipated in the nMOS. So **per full cycle (one rise + one fall)**:

$$
E_{cycle} = C_L\,V_{DD}^2
$$

If the node switches at average frequency $f_{sw}$, average power is

$$
P_{dynamic} = f_{sw}\,C_L\,V_{DD}^2
$$

Most nodes don't switch every clock cycle. Define **activity factor** $\alpha$ as the probability that the node switches from 0 to 1 in a given cycle. Then $f_{sw}=\alpha\cdot f_{clk}$ and:

$$
\boxed{\,P_{dynamic} = \alpha\,C_L\,V_{DD}^2\,f_{clk}\,}
$$

Key observations:
- Quadratic in $V_{DD}$ — **biggest knob** for power reduction.
- Linear in $C_L$ — sized layout matters.
- Linear in $f_{clk}$ — faster ⇒ more power.
- Linear in $\alpha$ — depends on **logic function and input statistics**.

### Activity Factor for a Logic Node

For a node $i$ with probability $P_i$ of being 1:

$$
\alpha_i = P_i (1-P_i)
$$

(per cycle, one transition). For random uncorrelated inputs ($P=0.5$), $\alpha=0.25$.

Different gates produce different output probabilities:
- AND of independent inputs: $P_{out}=\prod P_i$.
- OR: $P_{out}=1-\prod(1-P_i)$.
- XOR: $P_{out}=P_A(1-P_B)+(1-P_A)P_B$.

### Worked Example — Slide Problem 3 (4-input AND, tree vs chain)

For 4 inputs each with $P=0.5$:

**Tree (two AND2s + one AND2 combining them):**
- After first AND2: $P=0.25$, $\alpha=0.25\cdot 0.75=0.1875$.
- Same for the parallel AND2.
- After final AND2: $P=0.0625$, $\alpha=0.0625\cdot 0.9375\approx 0.0586$.

**Chain (AND4 done as 3 cascaded AND2s):**
- After 1st: $P=0.25$, $\alpha=0.1875$.
- After 2nd: $P=0.125$, $\alpha=0.109$.
- After 3rd: $P=0.0625$, $\alpha=0.0586$.

The tree and the chain reach the same final $P$, but **internal node activities differ**. Chains tend to have higher total switching power because each intermediate node toggles more often relative to its capacitance. Topology choice matters.

### Worked Example — Slide Problem 2 (system-level switching power)

System: $V_{DD}=1$ V, 65 nm process, $\lambda=25$ nm, $1\times 10^9$ transistors, $50\times 10^6$ in logic, rest in memory. Average widths: logic $12\lambda$, memory $4\lambda$. Activity factors: logic 0.1, memory 0.02. Capacitance: $1$ fF/$\mu$m gate + $0.8$ fF/$\mu$m diffusion = $1.8$ fF/$\mu$m total. $f=1$ GHz.

**Logic capacitance:**
$$
C_{logic} = 50\times10^6\cdot 12\cdot 0.025\,\mu\text{m}\cdot 1.8\,\text{fF}/\mu\text{m} = 27\,\text{nF}
$$

**Memory capacitance:**
$$
C_{mem} = 950\times10^6\cdot 4\cdot 0.025\,\mu\text{m}\cdot 1.8\,\text{fF}/\mu\text{m} = 171\,\text{nF}
$$

**Switching power:**
$$
P_{sw} = [(0.1)(27\,\text{nF})+(0.02)(171\,\text{nF})](1\,\text{V})^2(10^9\,\text{Hz}) = 6.1\,\text{W}
$$

So memory dominates the *capacitance*, but its low activity makes logic and memory contribute roughly equally to power.

---

## 2. Short-Circuit Power

During an input transition, both nMOS and pMOS may briefly conduct simultaneously, creating a direct path from $V_{DD}$ to ground.

Slide model: assume the resulting current pulse is approximately triangular with peak $I_{peak}$ and duration tied to input rise/fall time. Per switching event:

$$
E_{sc} \approx \frac{1}{2}\,V_{DD}\,I_{peak}\,t_{sc}
$$

with $t_{sc}$ = duration during which both transistors are in saturation. Then:

$$
P_{sc} = E_{sc}\,f
$$

Short-circuit power **increases** when:
- Input transitions are slow (long overlap),
- Output load is small (so output swings during the transition),
- $V_{DD}\gg V_T$ (longer fraction of input is in transition region).

It **decreases** when:
- Input edges are sharp,
- Output is heavily loaded (output cannot move much during transition, so input crosses transistor quickly).

A useful rule: short-circuit power ≤ ~10% of dynamic power if rise/fall times are kept comparable.

---

## 3. Glitching Power

A *glitch* is a transient unnecessary transition caused by **delay imbalance**. Example: in a chain XOR or in a multiplier tree, signals arrive at different times. A node may settle to 0, briefly go to 1, and back to 0, before the final correct value.

Each glitch consumes the same dynamic energy as a useful transition. Glitching can add 20–40% to dynamic power in poorly balanced logic.

Mitigations:

- **Path-balanced design** — equalise arrival times to every gate.
- **Input reordering** — late-arriving signals near the gate output (less internal switching).
- **Logic restructuring** — convert long chains to balanced trees.
- **Pipelining and registering**.

---

## 4. Static / Leakage Power

Even when the chip isn't switching, current still flows through OFF transistors. The slides enumerate **eight components** (memorise these — common fill-in question):

| Symbol | Mechanism |
|---|---|
| $I_1$ | Reverse-biased pn-junction leakage |
| $I_2$ | Sub-threshold leakage |
| $I_3$ | DIBL (drain-induced barrier lowering) |
| $I_4$ | GIDL (gate-induced drain leakage) |
| $I_5$ | Channel punch-through |
| $I_6$ | Narrow-channel effect on $V_T$ |
| $I_7$ | Gate-oxide tunnelling |
| $I_8$ | Hot-carrier injection (degrades over time) |

**Sub-threshold leakage** is dominant in modern CMOS:

$$
\boxed{\,I_{sub} = I_{0}\,e^{(V_{GS}-V_T)/(n V_{th})}\!\left(1-e^{-V_{DS}/V_{th}}\right)\,}
$$

with thermal voltage $V_{th}=kT/q\approx 26$ mV at room temperature.

The **sub-threshold slope** $S_S = n\,V_{th}\,\ln 10$ tells you how many millivolts of $V_{GS}$ change cuts leakage by 10×. Best case ~60 mV/decade; real CMOS 80–110 mV/decade.

### Reverse-Biased Junction Leakage

Each n+/p-substrate junction acts like a reverse-biased diode:

$$
I_D = I_S\!\left(e^{qV/kT}-1\right)
$$

Per-device leakage is small (0.1–0.5 nA) but multiplied by a billion transistors it sums up. Doubles for every 10 °C rise.

### Gate Leakage

For $t_{ox}\le 2$ nm, electrons tunnel directly through the oxide. Gate becomes a leaky capacitor. Per device 1–10 nA/$\mu$m of gate area. Mitigated by high-k dielectrics.

### Worked Example — Slide Problem 5 (system-level static power)

Same system: $1\times 10^9$ transistors, mostly memory. Sub-threshold: 100 nA/$\mu$m for low-$V_T$, 10 nA/$\mu$m for high-$V_T$. Gate: 5 nA/$\mu$m. 5% of logic is on critical paths and uses low-$V_T$.

**Width totals:**
- Low-$V_T$ width: $50\times10^6\cdot 0.05\cdot 12\cdot 0.025\,\mu\text{m}=0.75\times10^6\,\mu\text{m}$.
- High-$V_T$ width (rest of logic + all memory): $109.25\times10^6\,\mu\text{m}$.

**Sub-threshold (averaged over OFF transistors, factor of 1/2 because half are ON):**
$$
I_{sub}=\tfrac{1}{2}\!\left[(0.75\times10^6)(100\,\text{nA/}\mu\text{m})+(109.25\times10^6)(10\,\text{nA/}\mu\text{m})\right]\approx 584\,\text{mA}
$$

**Gate (all transistors leak independent of state):**
$$
I_{gate}=\tfrac{1}{2}\!\left[(0.75+109.25)\times10^6\,\mu\text{m}\right](5\,\text{nA/}\mu\text{m})\approx 275\,\text{mA}
$$

**Static power:**
$$
P_{static}=(584+275)\,\text{mA}\cdot 1\,\text{V}\approx 859\,\text{mW}
$$

So even in this hypothetical 1 GHz, 1 V chip, leakage alone is nearly a full watt. Adding the 6.1 W dynamic from the previous problem, total is ~7 W.

---

## Power-Delay Product (PDP) and Energy-Delay Product (EDP)

$$
\text{PDP} = P\cdot t_p
$$

PDP measures **average energy per switching event**. Smaller is more efficient.

But PDP can be made arbitrarily small by lowering $V_{DD}$ — at the cost of slower circuits. So pure PDP doesn't capture the speed/power tradeoff.

$$
\text{EDP} = P\cdot t_p^2 \quad \text{or}\quad E\cdot t_p
$$

**Energy-delay product** rewards designs that are both fast and energy-efficient. Useful as a process- or topology-comparison metric.

### Voltage Sweeping the EDP

- Higher $V_{DD}$: $t_p$ falls but $E\propto V_{DD}^2$ rises.
- Lower $V_{DD}$: $E$ falls, but $t_p$ explodes near $V_T$.
- A minimum exists at some $V_{DD}^*$ — exactly where designers want to operate for energy-constrained applications.

---

## Low-Power Design Techniques (handy summary table)

| Technique | Targets | Cost |
|---|---|---|
| Lower $V_{DD}$ | Dynamic ($V^2$), short-circuit | Speed loss, lower noise margin |
| Reduce $C_L$ via layout | Dynamic | Tighter design rules |
| Clock gating | Dynamic | Clock-tree complexity |
| Operand isolation | Dynamic, glitching | Extra latches |
| Logic restructuring (balanced trees) | Glitching | Harder synthesis |
| Multi-$V_T$ cells (high-$V_T$ off critical paths) | Sub-threshold leakage | Some delay, library complexity |
| Power gating (header/footer switches) | Leakage in idle blocks | Wake-up time, retention |
| Body biasing / RBB / ABB | Leakage | Triple-well, area |
| High-k + metal gate | Gate leakage | Process complexity |
| Stacked transistors (extra series device) | Sub-threshold leakage | Delay |

---

## Common Exam Mistakes

- Forgetting the $V_{DD}^2$ in dynamic power.
- Using $\alpha=1$ for a node whose input is uncorrelated random data (correct value is 0.25).
- Treating short-circuit power as a separate power source rather than tied to *input* edge rate.
- Confusing PDP with EDP — they answer different questions.
- Believing scaling reduces leakage automatically.
- Listing only a few leakage components when the slide explicitly enumerates eight.

## Self-Check Questions

1. Why is dynamic energy $\tfrac{1}{2}C_LV_{DD}^2$ stored, but $C_LV_{DD}^2$ drawn from supply?
   <details><summary>Answer</summary>The supply must do work both against the existing voltage and to fill the capacitor. Half the supplied energy ends up stored, half is dissipated as heat in the pMOS during charging.</details>

2. Why does heavy load *reduce* short-circuit power?
   <details><summary>Answer</summary>Because the output cannot move much while the input is in transition. Less output swing during the simultaneous-on window means less total $V_{DD}\cdot I$ time.</details>

3. Why does leakage scale exponentially while dynamic power scales linearly with $V_T$?
   <details><summary>Answer</summary>Sub-threshold $I_{sub}\propto e^{-V_T/(nV_{th})}$ — exponential. Dynamic power doesn't depend on $V_T$ to first order, only on $V_{DD}$ and $\alpha$.</details>

4. Why does putting late-arriving inputs near the gate output reduce glitching?
   <details><summary>Answer</summary>Internal nodes settle on early inputs first; the late input mostly affects only the output, which limits how many internal capacitances toggle unnecessarily.</details>

## Concept Links

- Previous: [[08_cmos_inverter_dynamic_behavior]]
- Next: [[10_logical_effort]]
- Related: [[06_scaling_and_short_channel_effects]] (leakage mechanisms), [[13_dynamic_and_domino_logic]] (charge-sharing / leakage in dynamic logic)
- Formulas: [[18_formula_sheet#power]]
