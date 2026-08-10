# Sequential Circuits and Clocking

> Concept: latches and registers, master-slave, C2MOS, TSPCR, pulse registers, dual-edge, pipelining, NORA-CMOS, and Schmitt trigger. Together these are the *memory* element of digital design and they bring timing constraints (setup, hold, $t_{c-q}$) and clock-skew issues that are exam-favourites.

## What "Sequential" Means

In a **combinational** circuit, the output depends only on current inputs. In a **sequential** circuit, the output depends on *current inputs and past inputs* — i.e., the circuit has memory. This is achieved by feeding outputs back into inputs through storage elements clocked by a periodic signal.

A canonical synchronous sequential system: combinational logic block (CLB) computes the *next state* and *outputs* from the *inputs* and *current state*. Registers, clocked by a global clock, store the state. On every rising edge, the next state is captured and becomes the current state for the following cycle.

### Important Sequential Timing Parameters

| Parameter | Definition |
|---|---|
| **$t_{su}$ (setup time)** | Data must be stable for $t_{su}$ before the active clock edge. |
| **$t_{hold}$ (hold time)** | Data must remain stable for $t_{hold}$ after the active clock edge. |
| **$t_{c-q}$** (clock-to-Q) | Worst-case propagation from clock edge to Q output. |
| **$t_{cd}$** (contamination delay) | Minimum propagation through register or logic. |

### Minimum Clock Period

For a register-CLB-register path:

$$
\boxed{\,T_{clk}\ge t_{c-q}+t_{p,logic}+t_{su}\,}
$$

### Hold Constraint

$$
\boxed{\,t_{cd,reg}+t_{cd,logic}\ge t_{hold}\,}
$$

If a path has too little contamination delay, new data can race through and corrupt the next register before the clock edge "closes". Holds bite when paths are too short.

---

## Bistability — Why Cross-Coupled Inverters Store Data

Two inverters in a loop have a combined VTC with three intersection points: A, B, C. Points A and B are **stable**; any small perturbation is amplified back to the same state. Point C is **metastable** — any deviation grows exponentially until A or B is reached.

Mathematically: at point C the loop gain is $>1$ but unstable; at A and B the loop gain is $<1$. So the cross-coupled inverter pair (often drawn as $I_1$ and $I_2$ with $V_{i1}=V_{out2}$ and $V_{i2}=V_{out1}$) holds either logic 0 or logic 1 indefinitely, with no static path drawing supply current beyond leakage.

---

## Latches — Level Sensitive

### Multiplexer-Based Latch

The most robust scheme. A 2:1 multiplexer selects between the data input D (in transparent phase) and the latch output (in hold phase). Built with two transmission gates and two inverters (the loop).

```
       D ──── TG1 ──┬───── inverter ──── Q
                    │
                    └─ TG2 ── inverter ── (feedback)
        CLK feeds TG1, CLK̄ feeds TG2
```

- **CLK = high (positive latch)**: TG1 ON, TG2 OFF — D propagates to Q. Latch is *transparent*.
- **CLK = low**: TG1 OFF, TG2 ON — feedback loop closes, Q holds previous value.

### nMOS-Only Pass-Transistor Variant

Use single nMOS pass-transistors instead of full TGs. Saves clock load (2 transistors instead of 4) but suffers $V_{Tn}$ drop on logic 1 → reduced noise margin and static power in next inverter.

### Sizing Concern

For the cross-coupled feedback path inside the latch, the data drive must overpower the feedback. Either use a TG to break the feedback during sample, or size the feedback inverter weak (longer channel) — the *ratioed* approach, which saves transistors but constrains layout.

---

## Master-Slave Edge-Triggered Register

Cascade a *negative* latch (master) and a *positive* latch (slave). A "register" that captures only on the rising clock edge.

- **CLK low**: master transparent (sees D), slave hold.
- **CLK high**: master hold (capture), slave transparent (output appears at Q).

Transition only happens at the rising edge (data is sampled by the master and immediately passed to the slave on the same edge transition).

### Timing of TG-Based Master-Slave

- **Setup time**: $t_{su} = 3 t_{pd,inv} + t_{pd,TG}$ (for the standard topology — D must propagate through I1, T1, I3, I2 before clock edge).
- **$t_{c-q}$**: $t_{pd,TG}+t_{pd,inv}$ (just from QM through T3 and I6).
- **Hold time**: $\approx 0$ (T1 closes on the edge so further D changes don't reach internal nodes).

### Drawback

The TG-based master-slave register has **8 transistors of clock load per register**, so big chips spend a lot of dynamic power switching the clock through every flip-flop. Several alternatives reduce this — see C2MOS and TSPCR below.

---

## Clock Skew and Race Conditions

The clock signal arrives at different parts of the chip at slightly different times due to RC delay in clock routing. If the local clocks of the master and slave latches overlap (both high or both low briefly), several failure modes appear:

- **(1-1) overlap** in negative master / positive slave: both pass-transistors briefly conduct → direct path from D to Q → output flips on rising edge incorrectly.
- **(0-0) overlap** in nMOS-only register: both inverter feedbacks active → undefined node states.

### Mitigations

1. **Two non-overlapping clocks** $\phi_1, \phi_2$ with deliberate dead time $t_{non-overlap}$ where neither is high. During dead time the register is in high-impedance (pseudo-static) — leakage limits how long this can last.
2. **C2MOS latch** — uses a clocked-CMOS topology that is inherently insensitive to overlaps as long as inverting logic is not in the path.
3. **Single-phase schemes (TSPCR)** — only one clock signal needs distribution.

---

## C2MOS (Clocked-CMOS) Latch

Two stacked inverters where the supply path is gated by clock (top half) and clock-bar (bottom half).

```
  CLK̄──┤ Mp
        │
       Mp ──┐
   In─┤    ├── Out
       Mn──┘
        │
   CLK ─┤Mn
        │
       GND
```

When CLK = 1: top pMOS path active (Mp_clk̄ is on), bottom path closed by CLK==0 nMOS. Forms a regular CMOS inverter — **transparent**.

When CLK = 0: both clock-controlled transistors off → output high-impedance.

### Slide Rule for C2MOS Pipelines

A pipeline using C2MOS latches between blocks of static combinational logic is **race-free as long as all logic functions between the latches are non-inverting**. Inversions between C2MOS stages can create a (0-0) or (1-1) race that lets data leak through.

This is the foundation for **NORA-CMOS** pipelines (next).

---

## True Single-Phase Clocked Register (TSPCR)

A latch family using a *single* clock — no need to route both CLK and CLK̄.

Positive TSPC latch:
- When CLK = high: latch is in transparent mode, behaves like two cascaded inverters.
- When CLK = low: pull-down networks of both inverters disabled. Pull-ups hold output values.

Two cascaded TSPC latches form a register. ~12 transistors.

### Advantage

- Only one clock to route → simpler clock distribution, no overlap to worry about.
- Can **embed logic** into the latch (e.g., AND/OR before the storage element), reducing total path delay. Used in DEC Alpha EV4.

### Variants

A *reduced* TSPC variant uses only one CLK-controlled inverter, giving 8 transistors. Cost: some internal nodes don't see full $V_{DD}$ swing → reduced drive, limited $V_{DD}$ scaling.

---

## Pulse-Triggered Register

Generate a *short pulse* on every rising clock edge with a delay-line + AND gate. Use that pulse as the latch enable.

```
   CLK ──┬──────────────┐
         │              AND ── CLKG (short pulse)
         └─inverter chain delay┘
```

Since the latch is open only during the short pulse, the register effectively becomes edge-triggered.

### Pros and Cons

Pros:
- Very low setup time (≈ 0).
- Few transistors per register (the pulse generator is shared).

Cons:
- Hold time = pulse width — process variation in delay chain can violate hold.
- Verification complexity.

---

## Dual-Edge-Triggered Register

Sample on **both** rising and falling clock edges. Implemented as two parallel master-slave registers, multiplexed by tri-state drivers controlled by clock and clock-bar.

Advantage: clock distributes at *half* the frequency for the same throughput → halves clock-network power.

---

## Dynamic Latches and Registers

Drop the cross-coupled feedback inverters and rely on **charge stored on parasitic capacitance** to hold state.

- Sample with a TG.
- After TG closes, the gate capacitance of the next inverter holds the value.
- Refresh the value periodically (every clock cycle in a clocked register, every few ms in DRAM).

8 transistors for a fully dynamic positive-edge-triggered register. Storage time limited by leakage. Need refresh (which clocked registers automatically get).

### Race Conditions in Dynamic Registers

(0-0) overlap between CLK and CLK̄ creates a direct path from D to internal node 2. Hold-time constraint or careful clock generation prevents this.

(1-1) overlap is handled by enforcing a hold-time on the input.

---

## Pipelining

The throughput-boosting technique. Insert registers into a long combinational path so that the path is split into shorter sub-paths, each shorter than the original. Clock period becomes $T_{min}/N$ for $N$ pipeline stages (ignoring register overhead).

Example: $\log(|a-b|)$ implemented as adder + abs + log. Pipelined into 3 stages with registers between each, each stage has 1/3 the original delay → 3× throughput. Latency increases by 3× (3 cycles to produce one result), but throughput scales with frequency.

### Latch- vs. Register-Based Pipelines

- Register-based: each pipeline stage uses a master-slave register. Robust, simple, but registers are large.
- Latch-based (two-phase): use alternating positive/negative latches with $\phi_1$ and $\phi_2$. Smaller area but timing constraints are more delicate.

---

## NORA-CMOS for Pipelining

Replace the static CMOS combinational logic between C2MOS latches with **NORA dynamic logic** (alternating n-block and p-block).

- C2MOS latches handle clock-skew issues at register boundaries.
- NORA dynamic logic handles fast computation between them.
- No extra inverter buffers required (unlike domino).

This was used in high-performance microprocessors of the 1990s-2000s.

---

## Schmitt Trigger

A special inverter with **hysteresis**: two switching thresholds, $V_M^+$ (low → high crossing) and $V_M^-$ (high → low crossing), with $V_M^+>V_M^-$. The hysteresis voltage is $V_H=V_M^+-V_M^-$.

### Behaviour

- Slow input transitions produce **fast** output edges.
- Noise on the input within $V_H$ does not cause output toggling.

### CMOS Implementation

Add feedback transistors $M_3$ and $M_4$ to a basic CMOS inverter (adapted from slide):

- When output is low, $M_4$ (a pMOS in feedback path) is ON, adding to the pull-up network. This raises $V_M$ for the rising input case.
- When output is high, $M_3$ (an nMOS in feedback) is ON, adding to the pull-down network. This lowers $V_M$ for the falling input case.

### Uses

- Cleaning up noisy clock or asynchronous inputs.
- Squaring up slow signal edges (oscillator outputs, level converters).
- Reducing direct-path/short-circuit currents in subsequent stages by ensuring fast transitions.

---

## Common Exam Mistakes

- Confusing setup and hold. Setup is *before* edge; hold is *after*.
- Saying "no clock skew = no race condition". Some races (1-1 overlap in master-slave with TGs) are not skew-induced; they're inherent to the topology if both clocks aren't perfectly anti-phase.
- Forgetting that dynamic latches need *refresh* — leakage will eventually destroy state.
- Drawing TSPC with two clocks. It uses *one*.
- Stating that pipelining reduces latency. It increases latency, but boosts throughput.

## Self-Check Questions

1. Why does a master-slave register have $t_{hold}\approx 0$?
   <details><summary>Answer</summary>The master TG closes on the active clock edge, isolating the master node from D. Any change in D after the edge cannot reach the master, so the input doesn't need to remain stable.</details>

2. Why must logic between C2MOS latches be non-inverting?
   <details><summary>Answer</summary>An inversion can flip a "safe" precharge value into the "unsafe" value for the next stage during overlap, allowing data to race through. Non-inverting logic preserves the polarity that keeps overlapping clocks safe.</details>

3. Why is TSPCR popular despite its 12 transistors?
   <details><summary>Answer</summary>Single clock distribution avoids non-overlap timing requirements, allows clock skew tolerance, and the latch can absorb adjacent logic (embedded NAND/AND), reducing total stage delay.</details>

4. Why does the Schmitt trigger reduce direct-path current in downstream gates?
   <details><summary>Answer</summary>It produces sharp output transitions even from slow inputs. Sharp inputs to subsequent gates spend less time in the simultaneous-on region, reducing short-circuit power.</details>

5. Why does dual-edge clocking save power?
   <details><summary>Answer</summary>The clock can run at half the frequency for the same throughput. Clock distribution uses $\alpha CV^2 f$, so halving $f$ halves clock power.</details>

## Concept Links

- Previous: [[13_dynamic_and_domino_logic]]
- Next: [[15_memory]]
- Related: [[12_pass_transistor_and_transmission_gate_logic]] (TG-based latches), [[09_power_dissipation]] (clock-power impact)
- Formulas: [[18_formula_sheet#sequential-timing]]
