# Dynamic and Domino Logic

> Concept: dynamic logic uses a clocked precharge step + an evaluation step to get high speed and low transistor count. Domino, NORA, and Zipper extensions fix the cascading problem. Charge leakage, charge sharing, capacitive coupling, and clock skew are the four failure modes you must understand.

## Why Dynamic Logic Exists

Static CMOS pays $2n$ transistors and quadratic delay for $n$-input gates. Dynamic logic uses $n+2$ transistors (one PDN + one precharge pMOS + one evaluation nMOS) and is **non-ratioed** — sizing of the precharge device only affects speed, not correctness.

Tradeoff: dynamic logic introduces a clock signal and must be re-precharged every cycle. The output is *not* a continuous static signal; it's stored as charge on the output capacitance.

---

## Basic Dynamic Gate Operation

```
            VDD
             │
            Mp (gate = CLK; pMOS precharge)
             │
            Out  ── CL (load capacitance)
             │
            PDN (n-input nMOS network, like static CMOS PDN)
             │
            Me (gate = CLK; nMOS evaluate)
             │
            GND
```

### Two-Phase Operation

**Phase 1: Precharge (CLK = 0)**
- $M_p$ ON: charges Out to $V_{DD}$.
- $M_e$ OFF: PDN cannot pull Out down even if inputs activate it.
- Output forced to logical 1 regardless of inputs.

**Phase 2: Evaluation (CLK = 1)**
- $M_p$ OFF.
- $M_e$ ON: PDN can now conditionally discharge Out.
- If PDN evaluates to TRUE (its function is satisfied), Out goes to 0.
- If PDN is OFF, Out stays at $V_{DD}$ (held by parasitic capacitance).

So dynamic gates implement: **Out = NOT(function realised by PDN)**, valid only during evaluation.

### Slide Example

PDN with $A\cdot B + C$ (i.e., $A$ in series with $B$, that combo in parallel with $C$): the gate computes

$$
Out = \overline{A\cdot B + C}
$$

equivalent to a static CMOS gate but with $n+2 = 5$ transistors instead of $2n=6$.

---

## Advantages of Dynamic Logic

From the slides:

1. **Transistor count**: $N+2$ vs $2N$ for fan-in $N$.
2. **Smaller load capacitance**: fewer drain diffusions and gate inputs at the output. ~50% less than static CMOS.
3. **Speed**: faster because $C_L$ smaller and only nMOS pull-down used (faster than pMOS).
4. **No static power** (in steady state — but precharge dissipates dynamic power).
5. **No short-circuit power** (PDN and PUN never conduct simultaneously — by design, $M_p$ and $M_e$ are gated by complementary CLK phases).
6. **No glitching** (output cannot rise during evaluation; only falls).
7. **Non-ratioed**: precharge transistor size affects only speed, not logic correctness.

---

## Disadvantages (the Four Failure Modes)

### 1. Charge Leakage Problem

During evaluation, if PDN is OFF, Out should stay at $V_{DD}$. But:

- Reverse-biased junction leakage of the bottom-most nMOS in PDN (drain-to-substrate).
- Sub-threshold leakage through PDN ($V_{GS}=0$ but $I_{sub}\ne 0$).
- Reverse-bias and sub-threshold leakage through $M_p$.

These slowly bleed charge off $C_L$. If the evaluation phase is too long, Out drifts low and a wrong 0 appears at the next stage.

### Mitigation: Bleeder / Keeper

Add a *weak* pMOS (the **keeper** or **bleeder**) tied to $V_{DD}$ with its gate driven by Out (or by a static inverter buffering Out). Like the level restorer in PTL.

```
            VDD
        ┌────┴────┐
       Mp        Mkeeper (weak)
        │         │
       Out ──────┘  (gate of keeper from inverter buffer of Out)
```

When Out is high, keeper is active and replenishes any leaked charge. When Out is being pulled low, the strong PDN overrides the weak keeper. Now we have a *quasi-static* node — robust against leakage but with some keeper-vs-PDN ratioing.

### 2. Charge Sharing Problem

The internal nodes within the PDN have parasitic capacitance ($C_a$, $C_b$, ...). After precharge they are *not* necessarily at $V_{DD}$ — they are at whatever voltage the previous evaluation cycle left them.

Example: PDN with stacked transistors $M_1$ (top) and $M_2$ (bottom). Internal node $V_x$. Suppose $V_x=0$ at the start of evaluation. If $M_1$'s input goes high but $M_2$'s stays low, $M_1$ turns on, but no path to ground exists. Charge redistributes between $C_L$ and $C_x$:

$$
V_{out,new} = V_{DD}\cdot \frac{C_L}{C_L+C_x}
$$

If $C_x\sim C_L/2$, the output drops by ~33%. Possibly registered as a 0 at the next stage. Wrong output.

### Mitigation

- Keeper transistor (also helps here).
- **Precharge internal nodes too** — extra small pMOS devices precharging $C_x$ alongside $C_L$.
- Make $C_L\gg C_x$ (sizing).

### 3. Capacitive Coupling / Clock Feedthrough

The dynamic node is high-impedance after precharge. Any capacitive coupling from a switching neighbour wire injects charge that perturbs the node:

- A coupling capacitor from a noisy net to Out can pull Out down enough to register as 0.
- The CLK signal itself couples through the gate-drain capacitance of $M_p$ when it transitions, creating a glitch on Out.

Mitigation: increase $C_L$, shield routing, use static buffer at the output (as in domino).

### 4. Cascading / Clock-Skew Problem

Two dynamic gates in series can fail. During the precharge phase, both outputs are at $V_{DD}$. When evaluation begins, the first gate may take time to discharge, but during that interval, its output is still high → the second gate starts evaluating *with the wrong* (precharged) input → second gate's PDN starts discharging → second output incorrectly drops, and there is no way to recover.

Even if eventually the first gate resolves correctly, the precious precharge on the second gate is already lost.

This is the *fundamental* problem solved by domino logic.

---

## Domino CMOS Logic

Insert a **static CMOS inverter** at the output of every dynamic gate.

```
                                          static inverter
   CLK──┤Mp                              ┌──────┐
        │                  Out_dyn ──────┤      ├── Out_buf
       Internal               (precharged    └──────┘
       (PDN)                   to VDD)
   CLK──┤Me                  
```

Now during precharge, $Out_{dyn}=V_{DD}$ and $Out_{buf}=0$. Cascaded domino gates see "0" at every input during precharge — so their PDNs are *off* and cannot prematurely discharge.

During evaluation, an output-low transition on $Out_{dyn}$ causes $Out_{buf}$ to rise. The chain of "rising edges" propagates from gate to gate like falling dominoes — hence the name.

### Properties of Domino

| | Domino |
|---|---|
| Cascading | YES — solves the dynamic cascading problem |
| Logic functions | only **non-inverting** (because inverter sits in series) |
| Output noise | improved (buffer drives static load) |
| Static power | small (only inverter has DC characteristics) |
| Speed | fast |
| Short-circuit power | none |
| Glitching power | none |

### Limitations

1. **Non-inverting only**: cannot directly express NAND or NOR — domino computes the negation of the PDN, then inverts back to "positive" form. So you can only build AND, OR, AND-OR, etc., not their inverses.

Two ways to handle inverting functions:
- **Bubble shifting** — push inversions through De Morgan's law to a place where they can be eliminated or absorbed by a static inverter elsewhere.
- **Dual-rail domino** — compute both $F$ and $\overline F$ in parallel, doubling area and power but fully restoring inverting capability.

2. **Higher switching activity** (every node toggles every clock cycle to either 0 or hold; precharge always returns to known state).

3. **Each gate requires a buffer** (extra delay and area).

---

## NORA CMOS Logic (NP-Domino, "No Race")

Alternate **n-block** dynamic gates with **p-block** dynamic gates:

- n-block: pMOS precharge, nMOS PDN, output goes high during precharge.
- p-block: nMOS predischarge, pMOS PUN, output goes low during precharge.

By alternating, the input to the next stage during precharge is *always* in the safe state for that stage's PDN/PUN. Specifically, an n-block produces high during precharge → p-block's pMOS PUN is OFF → no premature discharge. And vice versa.

### Properties

- Removes the need for the static inverter buffer between stages.
- Both polarities of signals available without dual-rail penalty.
- Used historically in DEC-Alpha 21064 (the first 250 MHz CMOS microprocessor).

Cost: more design complexity (alternating styles, careful timing).

---

## Sample Walk-Through

Suppose you have a 4-input domino AND realising $F=A\cdot B\cdot C\cdot D$:

```
        VDD
   CLK──┤Mp
         │
        X (dynamic node)──── inverter ── F
         │
        Mn_A
         │
        Mn_B
         │
        Mn_C
         │
        Mn_D
         │
   CLK──┤Me
         │
        GND
```

Behaviour:

- CLK=0: $X=V_{DD}$, $F=0$ regardless of inputs.
- CLK=1: if all $A,B,C,D=1$, $X$ discharges to 0, $F$ rises to 1.
- Otherwise $X$ stays high, $F=0$.

To compute $F = \overline{A\cdot B\cdot C\cdot D}$ (NAND), you'd need an inverter after $F$, or reorganise the logic via De Morgan into a domino-friendly form.

---

## Common Exam Mistakes

- Forgetting that domino can only realise non-inverting functions directly.
- Saying dynamic logic has no static power — leakage is real, especially in scaled processes.
- Confusing charge leakage with charge sharing. Leakage = charge bleeds away over time. Sharing = charge redistributes between output and internal capacitances during evaluation.
- Skipping the keeper/bleeder in long-evaluation-phase designs.
- Misordering NORA's stages (n-block, p-block must alternate).

## Self-Check Questions

1. Why is the precharge transistor non-ratioed?
   <details><summary>Answer</summary>It's only used to charge the output up; during evaluation the PDN must beat zero current, not the precharge transistor. So the precharge size affects speed but not correctness.</details>

2. Why does charge sharing happen even when the PDN is "off" during evaluation?
   <details><summary>Answer</summary>Some inputs may rise even if not all are high. A partial path activates, allowing charge to redistribute between the output capacitance and intermediate nodes that were left at low voltage from the previous cycle.</details>

3. Why does adding a static inverter solve the cascading problem?
   <details><summary>Answer</summary>The inverter converts the precharged-high dynamic output into a static-low signal. Downstream dynamic gates now see "0" at their inputs during precharge, so their PDNs are guaranteed off and cannot accidentally discharge.</details>

4. Why does NORA avoid the need for the inverter between stages?
   <details><summary>Answer</summary>Alternating n-blocks and p-blocks means the next stage's precharge polarity is opposite. The previous stage's precharge value falls in the safe range for the next stage's PDN/PUN to be off.</details>

5. Why is dynamic logic more sensitive to noise than static CMOS?
   <details><summary>Answer</summary>The dynamic node is high-impedance during evaluation — no low-resistance path holds it. Any capacitive coupling, leakage, or charge sharing can flip the apparent state.</details>

## Concept Links

- Previous: [[12_pass_transistor_and_transmission_gate_logic]]
- Next: [[14_sequential_circuits]]
- Related: [[09_power_dissipation]] (leakage components), [[14_sequential_circuits]] (TSPCR / NORA-CMOS for pipelining)
- Formulas: [[18_formula_sheet#dynamic-logic]]
