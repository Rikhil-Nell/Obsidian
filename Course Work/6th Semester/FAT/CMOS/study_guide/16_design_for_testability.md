# Design for Testability (DFT)

> Concept: fault models, controllability, observability, ad-hoc and structured DFT (scan, boundary scan), Built-In Self Test (BIST), and LFSRs. Memorise the fault types, the testing flow, and why DFT changes how circuits are designed.

## Why Testing Is Hard

Once a chip is fabricated, the *only* way to verify it works is through its **package pins**. Internal nodes are inaccessible. With billions of transistors:

- The number of possible input combinations explodes.
- Internal faults may not propagate to observable outputs.
- High-frequency operation makes tester equipment expensive and signal integrity a problem.
- Test-vector generation must be *automatic* (ATPG) since manual approaches don't scale.

So DFT is about *adding circuitry* to make a chip easier to test, accepting some area/delay overhead in exchange for production-line testability.

---

## Fault Sources

Physical defects from manufacturing or wear:

- Defects in silicon substrate
- Photolithographic defects (incomplete patterning)
- Mask contamination, scratches
- Process variations and abnormalities
- Oxide defects

These produce **electrical faults**:

- Shorts (bridging faults between adjacent metal lines)
- Opens (broken wires or contacts)
- Transistor stuck-on or stuck-open
- Resistive shorts/opens
- Excessive $V_T$ shift
- Excessive steady-state current

Which in turn produce **logical faults**:

- **Stuck-at-0** (s-a-0)
- **Stuck-at-1** (s-a-1)
- **Bridging faults** (AND-type or OR-type when two lines short)
- **Delay faults** (gate too slow at speed)

## Standard Fault Models

The **single stuck-at fault** model assumes one line is permanently fixed at 0 or 1. It's the workhorse of ATPG because it's simple and catches most real failures.

For an $n$-input circuit, there are roughly $2n$ stuck-at faults to consider (each line can be s-a-0 or s-a-1).

Other models:

- **Stuck-open / stuck-on** for transistor-level faults.
- **Delay faults** for path slowdown.
- **Bridging** (AND-bridge: both lines pulled low; OR-bridge: both pulled high).

## Controllability and Observability

Two ATPG primitives:

- **Controllability** — can the test engineer set a specified node to a known value by manipulating only primary inputs?
- **Observability** — can the value at a specified node be inferred by observing only primary outputs?

A circuit is **fully testable** only if every internal node is both controllable and observable. Common obstacles:

- **Reconvergent fanout** — an input fans out to multiple paths that recombine. Setting one path may force the other paths to specific values, blocking the test for some faults.
- **Redundant logic** — provably unreachable faults; the redundant gate can be removed.
- **Asynchronous feedback** — race conditions defeat deterministic testing.

### ATPG Algorithms

- **D-algorithm** (the original): provably finds a test if one exists, but exponential in worst case.
- **PODEM (Path-Oriented Decision Making)**: more efficient, follows specific paths.
- **FAN (FANout-oriented)**: heuristics around fanout points.

---

## Ad-Hoc DFT

Hand-crafted, design-specific testability improvements.

### Partition-and-Mux

A long sequential chain is hard to drive from primary inputs. Solution: insert multiplexers that bypass parts of the chain by feeding test signals from primary inputs directly to internal nodes.

Cost: extra muxes add area and delay; control signals for the muxes must be routed.

### Initialise Sequential Circuits

After power-up, registers hold random states. Always include synchronous (or asynchronous) **preset** and **clear** signals to bring registers to known states before testing.

### Disable Internal Oscillators / Clocks

When testing, you don't want the chip's own oscillators making timing unpredictable. OR the oscillator with a "test mode" disable, then OR in an externally-applied test clock.

### Avoid Asynchronous and Redundant Logic

- Async logic has race conditions that are non-deterministic, defeating fault simulation.
- Redundant gates have undetectable stuck-at faults; remove them or redesign.

These rules sound trivial but are routinely violated in practice — DFT engineers spend a lot of time fixing them.

---

## Scan-Based DFT (Structured Approach)

The most common modern DFT technique. Replace each register with a **scan register** that has two modes:

```
   Normal mode:   D ──┐
                      ├── (selectable input) ──── master-slave register ──── Q
   Test mode:    SI ──┘  (scan-in serial input)
```

A *2:1 mux* in front of every flip-flop selects between normal data $D$ and the scan-in input $SI$. The Q output of one flip-flop drives both its normal fanout and the SI of the next register in the scan chain.

In **test mode**:
- All registers are linked into a single long shift register (the *scan path*).
- Bits are clocked in serially from a scan-in pin.
- After the scan path is loaded, mode switches to *normal* for one cycle, capturing the combinational logic's response into the registers.
- Switch back to *scan mode* and shift the captured values out to a scan-out pin.

This **reduces sequential testing to combinational testing**: ATPG only needs to find tests for the combinational logic between registers, since registers are now both fully controllable (via scan-in) and fully observable (via scan-out).

### Scan Test Sequence (slide steps)

1. Enter test mode.
2. Verify scan path itself by shifting in/out a known pattern.
3. Shift in (scan in) the desired register state vector.
4. Apply the test vector to primary inputs.
5. Switch to normal mode for one cycle, allow propagation.
6. Pulse the clock to capture the combinational outputs into the registers.
7. Return to test mode; scan out captured values, scan in next test vector.
8. Repeat until all faults are tested.

### Cost

- Each register pays one mux + extra scan wiring.
- Some delay overhead (mux in the data path).
- Typically 5–20% area increase. Universally adopted in industry because the testability gain dwarfs the cost.

---

## Boundary Scan (JTAG / IEEE 1149.1)

Extends scan idea to the **chip boundary** — connect input-output pads into a scan chain. Once standardised, it lets boards be tested without per-pin probes.

```
   PAD_IN_1 ── boundary cell ── core
                  │
                  scan in
                  │
   ...           scan chain
                  │
                  scan out
                  │
   PAD_OUT_n ── boundary cell ── pad output driver
```

In **normal mode**, boundary cells pass signals transparently between core and pads. In **test mode**, scan vectors can be loaded into boundary cells to drive pads (or capture inputs from pads), enabling tests for board-level shorts/opens between chips.

The standard four-pin **TAP** (Test Access Port): TCK (clock), TMS (mode select), TDI (data in), TDO (data out). Optional TRST (reset). Industry-standard since 1990; in every modern microcontroller and FPGA.

---

## Built-In Self Test (BIST)

Generate test vectors and analyse responses **on the chip itself** — no external tester required.

### BIST Components

1. **Pseudo-Random Pattern Generator (PRPG)** — typically an LFSR (below) — produces test vectors.
2. **Output Response Analyser (ORA)** — compresses the chip's responses into a small *signature* (often via another LFSR acting as a Multi-Input Signature Register, MISR).
3. **Comparison logic** — compares the captured signature to a known good value to declare pass/fail.

### Online vs Offline BIST

- **Offline**: chip enters dedicated test mode; user/system triggers BIST.
- **Online (concurrent)**: BIST runs continuously during normal operation, useful for detecting in-field failures (memory ECC is a related concept).

### Linear Feedback Shift Register (LFSR)

A shift register with XOR feedback from selected taps. Generates a **maximal-length pseudo-random sequence** of $2^n-1$ patterns for an $n$-bit LFSR, given a *primitive polynomial* feedback.

```
   ─── FF1 ── FF2 ── FF3 ── FF4 ───┐
        │              │            │
        XOR ◄──────────┘            │
                                    │
   feedback ◄───────────────────────┘
```

Why LFSRs are perfect for BIST:

- Compact (just register + a few XORs).
- Fast (one cycle per new pattern).
- Pseudo-random sequences cover most stuck-at faults with high probability after enough cycles.

A signature analyser (MISR) is structurally an LFSR with multiple inputs XORed in, compressing chip output bits into a signature.

---

## Comparing DFT Approaches

| Approach | Pro | Con |
|---|---|---|
| Ad-hoc | minimal area | per-design effort, hard to maintain |
| Scan | fully controllable/observable, reduces seq → comb test | adds mux per flop, scan-chain length affects test time |
| Boundary scan | board-level testing without probes | only tests pad/core interface |
| BIST | no external tester, self-test in field | adds significant area for PRPG/MISR; pseudo-random doesn't always cover all faults |

In modern industry: scan + BIST + boundary scan are typically all used together.

---

## Common Exam Mistakes

- Confusing controllability with observability. Controllability = drive a node; Observability = read a node.
- Saying "scan-test makes sequential testing unnecessary". It transforms sequential into combinational; sequential ATPG is still needed for the scan-mode setup itself.
- Forgetting that LFSRs need primitive polynomials for maximum-length sequences.
- Drawing boundary scan as a chain through the *core*. It's at the *boundary* — the I/O pads.
- Listing only stuck-at as fault model. Bridging, stuck-open/on, and delay faults are all part of the spec.

## Self-Check Questions

1. Why does a redundant gate produce undetectable faults?
   <details><summary>Answer</summary>By definition, redundant logic doesn't affect the output. So a fault on it cannot propagate to a primary output and can't be detected. Such gates should be removed unless deliberately added for hazard suppression.</details>

2. Why is reconvergent fanout problematic for ATPG?
   <details><summary>Answer</summary>Setting an input value forces the same value down all fanout paths. When those paths reconverge, you may not be able to construct an input that produces the right propagation conditions for a fault on one branch.</details>

3. Why does scan-based DFT *reduce* sequential testing to combinational testing?
   <details><summary>Answer</summary>Scan replaces uncontrollable register state with controllable scan inputs and uncontrollable register state observation with scan outputs. The combinational logic between registers can be tested as a stand-alone combinational circuit using stored register values as additional pseudo-inputs and pseudo-outputs.</details>

4. Why is an LFSR preferred over a counter for BIST pattern generation?
   <details><summary>Answer</summary>Counters produce sequential, predictable patterns that don't exercise circuit behaviour broadly. LFSRs produce pseudo-random sequences with good fault coverage and span all $2^n-1$ non-zero states with primitive polynomial feedback.</details>

5. Why does boundary scan need a separate clock (TCK)?
   <details><summary>Answer</summary>The chip's normal clock isn't running during board-level boundary tests, and the test must work even when system clocks are off. TCK is a slow, externally-applied clock for the scan operation.</details>

## Concept Links

- Previous: [[15_memory]]
- Next: [[17_worked_problems]]
- Related: [[14_sequential_circuits]] (registers are the scannable elements), [[06_scaling_and_short_channel_effects]] (delay faults are exacerbated by scaling)
- Formulas: [[18_formula_sheet#testability]]
