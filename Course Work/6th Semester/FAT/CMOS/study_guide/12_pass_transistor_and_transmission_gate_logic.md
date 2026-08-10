# Pass-Transistor and Transmission-Gate Logic (incl. Pseudo-nMOS & DCVSL)

> Concept: alternative logic styles that trade transistor count, area, swing, or robustness against full static CMOS. Each of the four families covered (Pseudo-nMOS, DCVSL, Pass-transistor, Transmission gate) has a *specific* tradeoff you must memorise.

## Why Have Other Styles?

Static complementary CMOS is robust but pays the price of **2N transistors** for N inputs and **quadratic delay** in fan-in. For very high-speed or area-critical designs, alternative logic families can win on:

- **Area / transistor count** (Pseudo-nMOS, pass-transistor).
- **Speed** (Pass-transistor, DCVSL via differential complementary signals).
- **Differential signalling** (DCVSL, CPL — both polarities available).

The penalty is always one of: *static power*, *signal swing*, *ratioed sizing*, or *clocking complexity*.

---

## 1. Pseudo-nMOS Logic

Replace the entire pMOS PUN with a single grounded-gate pMOS load.

```
            VDD
             │
        ──── Mp_load (gate tied to GND, always ON)
             │
            Out
             │
           PDN (nMOS network = same as static CMOS)
             │
            GND
```

For an $n$-input gate: only $n+1$ transistors (vs $2n$ for static CMOS).

### Behaviour

- When PDN is OFF: pMOS pulls Out high, but only to $V_{DD}$ if no current flows. With pMOS always ON, $V_{OH}=V_{DD}$ (no gate disconnect needed).
- When PDN is ON: pMOS *and* nMOS conduct simultaneously, drawing static current. Output settles at a voltage division dictated by $R_p$ vs $R_{PDN}$. To get a low $V_{OL}$, need $R_p\gg R_{PDN}$ — this makes pseudo-nMOS a **ratioed** logic family.

### Tradeoffs vs Static CMOS

| | Static CMOS | Pseudo-nMOS |
|---|---|---|
| Transistor count | $2n$ | $n+1$ |
| Static power | ~0 | substantial (always-on pMOS pulls current when PDN is on) |
| $V_{OH}$ | $V_{DD}$ | $V_{DD}$ |
| $V_{OL}$ | 0 | $>0$ (ratio-set) |
| Noise margin | high | reduced (esp. $NM_L$) |

### When To Use

Where area beats power and noise margin: ROM bit-lines, bus pull-ups, NORA stages.

---

## 2. Differential Cascode Voltage Switch Logic (DCVSL)

A dynamic-flavoured static logic family that uses **two complementary nMOS networks** (PDN and PDN') and **cross-coupled pMOS load** to provide differential outputs.

```
            VDD
        ┌────┴────┐
       Mp1       Mp2   (cross-coupled pMOS)
        │         │
       Out       Out̄
        │         │
       PDN      PDN'  (complementary nMOS networks)
        │         │
       GND       GND
```

The two PDNs are duals: when PDN conducts, PDN' is open, and vice versa.

### How It Works

When PDN starts to discharge, $V_{Out}$ falls. This turns ON $M_{p2}$, which charges $\overline{Out}$ high. The high $\overline{Out}$ turns OFF $M_{p1}$, finishing the swing. Result: rail-to-rail differential outputs **without static power** (unlike pseudo-nMOS), and **both polarities** are available simultaneously.

### Pros and Cons

Pros:
- Rail-to-rail differential outputs.
- No static power.
- Differential property useful for fast logic (no need for separate inverter to derive complement; signals stay in sync).

Cons:
- Still **ratioed** — you have to size pMOS load weak enough that PDN can fight it.
- Doubles wire count (need both polarities everywhere).
- Higher dynamic power because both rails switch on every transition.

### Worked Idea: When Useful

Multiplier compressors, fast adders, and any block where complementary signals would otherwise need extra inverters.

---

## 3. Pass-Transistor Logic (PTL)

The big idea: use transistors as **switches that pass logic values directly** rather than as drivers from the supply rails.

### Basic Pass-Transistor AND

To realise $F=A\cdot B$ with two nMOS switches:

```
        A ─── M1 ─── F
              │
              B  (gate)
        ─── M2 ─── F   (gate from B̄, drain to GND)
              │
              B̄
```

Logic: when $B=1$, $M_1$ ON, $F=A$. When $B=0$, $M_2$ ON, $F=0$. Two transistors instead of six (NAND2). Result: $F=A$ if $B=1$, $0$ if $B=0$ → $F=A\cdot B$.

### The Threshold Drop Problem

When an nMOS pass-transistor passes a logic 1 ($V_{drain}=V_{DD}$, $V_{gate}=V_{DD}$), the output node only charges to $V_{DD}-V_{Tn}$. As the source voltage rises, $V_{GS}$ falls below $V_T$ and current shuts off.

So nMOS *passes a strong 0* but a *weak 1*. PMOS does the opposite.

Consequences:
- **Reduced output swing** → reduced noise margins.
- **Static power** in the next-stage inverter, because its input may sit above $V_{Tn}$ but below $V_{DD}$, and that pMOS leaks.
- **Cascading is broken** — multiple PTL stages compound the threshold drop.

### Energy Saving

A nice property: charging from 0 to $V_{DD}-V_{Tn}$ rather than $V_{DD}$ uses *less* energy:

$$
E = C_L\,V_{DD}\,(V_{DD}-V_{Tn})
$$

vs $C_L V_{DD}^2$ for full swing. So PTL can be lower-power for repeated switching, **if the threshold drop is acceptable**.

### Differential / Complementary Pass-Transistor Logic (CPL)

Use both true and complementary inputs to build differential outputs (similar to DCVSL but with pass-transistor topology). Slide-mentioned advantages:

- Some complex gates (XOR, XNOR, full adders) implement very efficiently with few transistors.
- Both polarities available everywhere.
- Modular design — every gate uses the same topology, only inputs are permuted.

Still suffers from threshold-drop, so a swing-restoring stage is usually inserted.

### Restoring Pass-Transistor Logic — Level Restorer

Add a **PMOS feedback transistor** $M_r$ from $V_{DD}$ back to the floating node $X$. When the next-stage inverter's output flips low (because $X$ is high enough to register as 1), $M_r$ turns ON and pulls $X$ all the way to $V_{DD}$, eliminating the threshold drop.

```
   PT network → X → inverter → Out
                    ↑
                    └── Mr (pMOS), gate from Out
```

This makes the design **ratioed**: $M_r$ must be weaker than the nMOS pass-transistor so that input data can override it during a transition. Sizing $M_r$ too strong means the input cannot override and the gate fails. Sizing too weak means slow restoration and ineffective level restoration.

### Multiple-Threshold Approach

Use **zero-$V_T$ nMOS** for pass-transistors. They pass full $V_{DD}$ without drop. But they leak heavily when "off", so the rest of the chip must use normal-$V_T$ devices. Enabled by multi-$V_T$ process options.

---

## 4. Transmission Gate (TG) Logic

The simplest fix to the threshold-drop problem: put nMOS in **parallel** with pMOS, control with complementary signals.

```
       In ────┬─── nMOS ────┬── Out
              │             │
              ╪──── pMOS ───╪
              │      │      │
              │      C̄      │
              C
```

When $C=1$, $\overline C=0$: both transistors ON. nMOS passes strong 0, pMOS passes strong 1, so the gate passes any value with no $V_T$ drop.

When $C=0$: both OFF, output disconnected (high-impedance).

### Properties

- **Bidirectional** switch.
- **Full swing** (no threshold drop).
- **Six transistors** for a 2:1 mux (vs 8 in static CMOS).
- Generates $\overline C$ from $C$ → needs an inverter on the control line.

### Common TG-Based Circuits

- **2:1 multiplexer**:
$$
F = S\cdot A + \overline S\cdot B
$$
Implemented with two TGs (one passing $A$ when $S=1$, one passing $B$ when $S=0$). Six transistors counting the inverter on $S$.

- **XOR**:
$$
F = A\oplus B = \overline A B + A \overline B
$$
Built with one inverter pair + one TG. Four transistors total in the elegant version.

- **Latches** (covered in [[14_sequential_circuits]]).

### Effective Resistance of a TG

The parallel combination is

$$
R_{TG} = R_n\,\|\,R_p
$$

This is roughly half of either single device, so TGs are also fast. Series TGs in chains add up — so practical TG chains stay short (≤ 4) before a buffer is added.

---

## Performance / Family Comparison Table

| Feature | Static CMOS | Pseudo-nMOS | DCVSL | Pass-transistor | Transmission gate |
|---|---|---|---|---|---|
| Transistor count | $2n$ | $n+1$ | $2n+2$ | $n$ to $2n$ | similar to PT |
| Static power | ~0 | high | ~0 | ~0 | ~0 |
| Output swing | full | reduced ($V_{OL}>0$) | full | reduced ($V_{OH}<V_{DD}$) | full |
| Differential outputs | no | no | yes | optional (CPL) | optional |
| Ratioed | no | yes | yes | yes (with restorer) | no |
| Notes | most robust | area-efficient | both polarities, no static | small but needs restorer | excellent for muxes/XOR/latches |

---

## Common Exam Mistakes

- Saying pass-transistor logic has full swing. nMOS pass-transistors lose $V_T$.
- Forgetting that pseudo-nMOS draws DC current when output is low.
- Treating TG and PT as the same. TG uses *both* nMOS and pMOS in parallel and gives full swing.
- Cascading pass-transistor stages without a restorer or buffer.
- Ignoring that DCVSL needs both inputs **and** their complements at every gate.

## Self-Check Questions

1. Why does an nMOS pass-transistor "lose" $V_T$ when passing a 1?
   <details><summary>Answer</summary>To stay ON, $V_{GS}$ must exceed $V_T$. As the source rises toward the drain (1), $V_{GS}$ falls. When $V_{GS}=V_T$ the transistor cuts off, leaving the source stuck at $V_{DD}-V_{Tn}$.</details>

2. Why is pseudo-nMOS called "ratioed"?
   <details><summary>Answer</summary>The output low voltage depends on the resistance ratio between the always-on pMOS load and the conducting nMOS PDN. Different sizing gives different $V_{OL}$, so logic correctness depends on size ratio.</details>

3. Why is the level restorer pMOS sized small (weak)?
   <details><summary>Answer</summary>It must yield to the pass-transistor when input data needs to flip. If the restorer were strong, it would fight the input and prevent the node from changing.</details>

4. Why are TGs popular in latches and muxes but not in long chains?
   <details><summary>Answer</summary>The series resistance of multiple TGs adds up linearly, so delay grows quickly. Latches and muxes use 1–2 TGs which is fine; arithmetic chains need buffers.</details>

5. When does DCVSL beat single-ended static CMOS?
   <details><summary>Answer</summary>When the next stage already needs both polarities (e.g., differential bus, fast XOR, multiplier compressors). The wire and area overhead is justified by skipping the inverter delay on the complementary signal.</details>

## Concept Links

- Previous: [[11_static_cmos_logic]]
- Next: [[13_dynamic_and_domino_logic]]
- Related: [[14_sequential_circuits]] (TG-based latches), [[09_power_dissipation]] (static power in pseudo-nMOS)
- Formulas: [[18_formula_sheet#alternative-logic-styles]]
