# Static CMOS Logic Gates

> Concept: build any combinational function from PUN + PDN, derive its delay/noise margin, and apply fan-in optimisation tricks (sizing, progressive sizing, input reordering, restructuring). This is the bread-and-butter design style.

## Pull-Up and Pull-Down Networks

A **complementary CMOS gate** is two networks:

- **Pull-down network (PDN)** — nMOS only, between output and GND. Pulls output to 0.
- **Pull-up network (PUN)** — pMOS only, between output and $V_{DD}$. Pulls output to 1.

The two networks are *duals* — for every input combination, exactly one network conducts. There is no DC path from $V_{DD}$ to GND in steady state, which is what gives static CMOS its low static power.

### Why nMOS in PDN, pMOS in PUN?

- **nMOS passes a strong 0** but a weak 1 (output high is degraded to $V_{DD}-V_T$).
- **pMOS passes a strong 1** but a weak 0 (output low is $|V_{Tp}|$).

So we want the *device that passes strong 0* in the path that produces 0 — i.e., nMOS in the PDN. Symmetrically, pMOS in the PUN. This guarantees full-rail $V_{OL}=0$ and $V_{OH}=V_{DD}$.

## Construction Rules

To synthesise a complementary CMOS gate from a Boolean expression:

1. Derive the **PDN** by writing the function with NMOS connectivity rules:
   - **AND** = nMOS in series.
   - **OR** = nMOS in parallel.
2. The **PUN** is the *dual* of the PDN: replace series with parallel and parallel with series, using pMOS devices.
3. Both networks have the same number of transistors as inputs.

### Rules Summary Table

| Boolean op | nMOS topology | pMOS topology |
|---|---|---|
| $X = \overline{AB}$ (NAND) | series | parallel |
| $X = \overline{A+B}$ (NOR) | parallel | series |
| $X = \overline{(A+B)C}$ | $(A\|B)$ in series with $C$ | $(A\,\&\,B)$ in parallel with $C$ |

For an $n$-input static CMOS gate you always end up with $2n$ transistors.

## Worked Example: 2-Input NAND

$X=\overline{AB}$:

- PDN: nMOS A and B in series. Output goes low only if both A=B=1.
- PUN: pMOS A and B in parallel. Output goes high if A=0 or B=0.

```
        VDD
   ┌─────┴─────┐
  Mp_A       Mp_B    (parallel pMOS)
   │           │
   └────────┬──┘
           Out
            │
           Mn_A   (series nMOS)
            │
           Mn_B
            │
           GND
```

## Worked Example: Complex Gate $\overline{(A+B)\cdot C\cdot D}$

PDN: $A$ and $B$ in parallel, that combo in series with $C$ in series with $D$. So the discharge path is "($A$ or $B$) and $C$ and $D$".

PUN dual: $A$ and $B$ in series (since they were parallel in PDN), that combo in parallel with $C$ in parallel with $D$.

This is the **Bubble Pushing / Boolean dualising** workflow used everywhere in this module.

## Static Properties — Pattern Dependence

In a NAND gate, the DC VTC depends on which input is varying because:

1. The strength of the PUN depends on how many pMOS are conducting.
2. The series nMOS in the PDN have **body effect**: the upper transistor's source can sit above ground, raising its $V_T$.

### Pattern-Dependent VTC Cases (NAND-2)

| Case | Inputs | Effective PUN | Effective PDN | VTC shift |
|---|---|---|---|---|
| (a) Both inputs sweep | $V_A=V_B=V_{in}$ | both pMOS in parallel | both nMOS in series, body effect on upper | reference |
| (b) Only $A$ sweeps, $B=V_{DD}$ | $V_B=V_{DD}$ | one pMOS ($M_p^B$) is OFF | both nMOS in series | shifted left (weaker PUN) |
| (c) Only $B$ sweeps, $A=V_{DD}$ | $V_A=V_{DD}$ | one pMOS OFF | both nMOS in series, $M_n^A$ acts like extra resistor | shifted left, mild body effect |

**Conclusion from slides:** propagation delay and noise margin are *input dependent* in static CMOS multi-input gates.

The general rule:

- Stronger PUN ⇒ better $NM_H$.
- Stronger PDN ⇒ better $NM_L$.

## Propagation Delay of CMOS Gates

Each transistor is modelled as $R_{eq}$ in series with an ideal switch; gate becomes an RC tree.

### NAND-2 Examples (slide section)

- Both pMOS ON (A=B=0 → output rises): $t_{pLH}=0.69(R_p/2)C_L$.
- One pMOS ON (worst case): $t_{pLH}=0.69 R_p C_L$.
- Both nMOS ON (A=B=1 → output falls): $t_{pHL}=0.69(2R_n)C_L$.

So *adding series transistors slows down the circuit*. Designers compensate by **widening** stacked transistors (e.g., width-3 each in NAND-3 to match unit inverter resistance).

### Worst-Case for Falling Output

When output is being pulled low through $n$ series nMOS, all the diffusion capacitance at every internal node also has to be discharged. Use Elmore delay (see [[08_cmos_inverter_dynamic_behavior]]):

$$
t_{pHL} \approx 0.69 \!\left(\sum_{k=1}^n k\,R_n\,C_{int,k}\right) + 0.69\,n\,R_n\,C_L
$$

Each lower transistor's resistance is shared by all upstream nodes.

## Fan-In and Fan-Out Effects

For an $n$-input NAND gate:

- **$t_{pLH}$**: the PUN has $n$ pMOS in parallel. Output capacitance grows linearly with $n$ (more drain diffusion). So $t_{pLH}\propto n$.
- **$t_{pHL}$**: the PDN has $n$ nMOS in series. Both resistance and total capacitance grow with $n$. So $t_{pHL}\propto n^2$ (quadratic).

This **quadratic fan-in dependence** is why nobody builds 8-input static NAND gates directly. The slide rules:

- Limit fan-in to 4–5.
- Decompose larger gates into a tree of smaller gates.
- Use NAND chains rather than NOR chains (logical effort favours NAND).

---

## Design Techniques for Large Fan-In

### 1. Transistor Sizing (Uniform)

Increase $W$ of all transistors. Lowers $R_{eq}$ but also raises $C_{int}$. Diminishing returns once intrinsic capacitance dominates. So this is a *gross* fix.

### 2. Progressive Transistor Sizing

In an $n$-stack, the resistance of the bottom transistor $M_1$ appears in *every* term of the Elmore delay (it sees the full capacitance). The next transistor $M_2$ appears $n-1$ times, etc.

Slide derivation gives:

$$
t_{pHL}\approx 0.69\,R_n[C_1+2C_2+3C_3+\dots+n C_n]
$$

Therefore size **bottom widest, top narrowest**:

$$
W_1 > W_2 > W_3 > \dots > W_n
$$

This reduces the heavy resistance on the bottleneck branch without bloating capacitances near the output.

### 3. Input Reordering (Critical Path Last)

If one input arrives later than the others, place its transistor **closest to the output**. Reason: by the time the late input transitions, the internal nodes below it are already discharged by the earlier inputs, so the late path only has to discharge the output node — the smallest $RC$.

### 4. Logic Restructuring

A 6-input NOR has $t_{pHL}\propto 36$ (quadratic). Two 3-input NORs feeding an AND-equivalent has $t_{pHL}\propto 9+9+\text{glue}$. Total can drop ~3–5×.

So *partitioning* deep gates into a *balanced tree* of smaller gates helps both speed and glitching.

### 5. Use the Right Logic Family

Static CMOS is robust but slow for large fan-in. Alternatives:

- Pseudo-nMOS (smaller area, weaker noise margins) — see [[12_pass_transistor_and_transmission_gate_logic]].
- Dynamic / Domino logic (much faster, more complex clocking) — see [[13_dynamic_and_domino_logic]].

---

## Power Consumption in Static CMOS Logic

Dynamic power per node: $P=\alpha_{0\to 1}\,C_L\,V_{DD}^2\,f_{clk}$.

The **switching activity factor** $\alpha_{0\to 1}$ has two parts:

- **Static (function-determined):** depends only on logic and input statistics. For independent inputs, $\alpha_{0\to 1}=P_0\cdot P_1$.
- **Dynamic (timing-determined):** glitching from unbalanced delays.

### Inter-Signal Correlations

If inputs are correlated, the simple product formula fails. Use **conditional probabilities**:

$$
P(A\cdot B) = P(A)\cdot P(B|A)
$$

Common gotcha when computing activity in adders or multipliers where carry-out and sum bits depend on each other.

### Glitching Power

Long combinational chains (carry chains in ripple-carry adders) glitch heavily. Each stage's output toggles even though the final value is correct. Each toggle dissipates a full $C_L V_{DD}^2$ of energy.

### Reducing Switching Activity

| Technique | What it does |
|---|---|
| **Logic restructuring** | Convert chains to balanced trees. Example: 4-AND tree has lower glitching than 4-AND chain. |
| **Input reordering** | Place high-activity inputs deeper in the gate so they affect smaller capacitances. From slides: $\alpha_{int}=0.09$ vs $0.0196$ for two orderings of the same gate. |
| **Time-multiplexing** | Sometimes hurts more than it helps. Doubles activity even though shared capacitance is the same. |
| **Operand isolation / clock gating** | Prevent unused datapaths from switching. |
| **Pre-computation** | Compute predicate; only enable expensive logic if needed. |

---

## Common Exam Mistakes

- Drawing PUN and PDN with the same device type. Always nMOS in PDN, pMOS in PUN.
- Forgetting body effect on stacked transistors. Upper $V_T$ rises.
- Treating fan-in $n$ as linear in delay. NAND-$n$ pull-down is quadratic.
- Using $\alpha=P\cdot(1-P)$ formula when inputs are correlated.
- Putting the critical-path input at the bottom of the stack rather than near the output.

## Self-Check Questions

1. Why does NAND outperform NOR in static CMOS for the same fan-in?
   <details><summary>Answer</summary>NAND stacks nMOS (lower resistance per device) in series; NOR stacks pMOS (higher resistance per device) in series. Plus pMOS series stacks need much wider devices to match an inverter, blowing up input capacitance.</details>

2. Why does input reordering reduce delay when inputs arrive at different times?
   <details><summary>Answer</summary>If the late input is near the output, it only needs to discharge the output capacitance because the internal nodes below it are already discharged by the earlier-arriving inputs. The late path's RC product is smaller.</details>

3. Why is the NAND-2 VTC asymmetric depending on which input switches?
   <details><summary>Answer</summary>The PUN strength is fixed (one or both pMOS conducting), but the PDN's stack experience differs: which nMOS is at the bottom vs top changes the body-effect $V_T$ shift on the upper device.</details>

4. Why does increasing transistor width help less and less as you go bigger?
   <details><summary>Answer</summary>Wider gates have lower $R$ but proportionally larger intrinsic capacitance ($C_{int}\propto W$). Once $C_{int}\gg C_{ext}$, sizing only swaps "less external load delay" for "more internal load delay" with no net gain.</details>

## Concept Links

- Previous: [[10_logical_effort]]
- Next: [[12_pass_transistor_and_transmission_gate_logic]]
- Related: [[13_dynamic_and_domino_logic]] (alternative for high fan-in), [[09_power_dissipation]] (activity factor)
- Formulas: [[18_formula_sheet#static-cmos-logic]]
