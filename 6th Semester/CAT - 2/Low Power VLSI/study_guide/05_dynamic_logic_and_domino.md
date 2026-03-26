# 05 - Dynamic Logic and Domino Logic

---

## Learning Objectives

After studying this section, you will be able to:

- Explain why dynamic logic was introduced and what problems it solves
- Describe the precharge and evaluation phases in detail
- Explain why straightforward cascading of dynamic gates fails
- Describe the structure and operation of Domino logic
- Explain why Domino logic can only implement non-inverting functions
- Analyze the importance of the evaluation transistor
- Describe the ripple precharge problem and its effects
- Compare dynamic logic and Domino logic with static CMOS

---

## Ground-Up Explanation

### Motivation: Why Dynamic Logic?

We have seen that:
- **Full CMOS** requires 2N transistors per gate and uses both PMOS and NMOS
- **Pseudo-NMOS** reduces this to N+1 transistors but introduces **static power dissipation**

The question is: **Can we achieve the low transistor count of Pseudo-NMOS while eliminating its static power consumption?**

The answer is **yes**, using **dynamic logic**. Dynamic logic achieves:
- Reduced transistor count similar to Pseudo-NMOS (N + 2 transistors)
- **Zero static power consumption** (like CMOS)
- The trade-off: it requires a **clock signal** and operates in distinct phases

**Analogy**: Think of dynamic logic like a bucket relay. During the "fill" phase (precharge), all buckets are filled with water to the brim ($V_{DD}$). During the "pour" phase (evaluate), some buckets selectively dump their water (discharge to GND) based on the logic inputs. The key insight is that you only use energy to fill the buckets, and selectively dumping them requires no steady-state power.

---

## Dynamic Logic Fundamentals (Section 3.2.2)

### Structure

A basic dynamic logic gate consists of:

1. **PMOS precharge transistor** ($M_p$): Controlled by the clock signal CLK. When CLK = 0, this transistor turns ON and charges the output node to $V_{DD}$
2. **NMOS pull-down network (PDN)**: Implements the desired logic function using NMOS transistors, identical to the PDN in complementary CMOS
3. **NMOS evaluation transistor** ($M_e$): Controlled by CLK. When CLK = 1, this transistor turns ON and connects the PDN to GND, enabling evaluation

**Total transistor count**: N + 2 (N for PDN + 1 precharge PMOS + 1 evaluation NMOS)

### Two Phases of Operation

The operation is divided into two major phases, controlled by the clock signal:

#### Phase 1: Precharge (CLK = 0)

- The PMOS precharge transistor $M_p$ is **ON** (because CLK = 0 means $V_{GS,PMOS} = -V_{DD}$)
- The output node is charged to $V_{DD}$ regardless of the inputs
- The NMOS evaluation transistor $M_e$ is **OFF** (CLK = 0 means $V_{GS,NMOS} = 0$)
- The PDN is disconnected from GND, so no current can flow
- **All outputs are initialized to logic '1' (HIGH)**

#### Phase 2: Evaluation (CLK = 1)

- The PMOS precharge transistor $M_p$ is **OFF** (CLK = 1)
- The NMOS evaluation transistor $M_e$ is **ON** (CLK = 1)
- The output is **conditionally discharged** based on inputs:
  - If the PDN conducts (inputs satisfy the logic function): output discharges to GND (logic '0')
  - If the PDN does not conduct: output stays at $V_{DD}$ (precharged level, logic '1')

### Why No Static Power?

The evaluation FET ($M_e$) eliminates any static power that would be consumed during the precharge period. Without $M_e$, if the PDN inputs happen to create a conducting path during precharge, current would flow directly from $V_{DD}$ (through $M_p$) to GND (through the PDN) -- this would be a short-circuit. The evaluation transistor **blocks this path** during precharge, ensuring that precharge and evaluation never overlap.

---

## The Cascading Problem

### Why Direct Cascading Fails

One of the most critical limitations of dynamic logic is that **straightforward cascading of dynamic gates does not work correctly**. This is a fundamental problem that led to the development of Domino logic.

**The scenario**:
Consider two cascaded dynamic gates, where the output of Gate 1 feeds the input of Gate 2.

1. During precharge (CLK = 0), **both outputs** are precharged to $V_{DD}$
2. Assume the primary input `In` makes a 0 → 1 transition
3. On the rising edge of CLK (evaluation begins):
   - Gate 1's output (`Out1`) starts to discharge (correct behavior)
   - Gate 2 sees `Out1` as HIGH initially (because it hasn't finished discharging yet)
   - Gate 2 starts to discharge `Out2` **incorrectly** based on the stale HIGH value of `Out1`

4. There is a **finite propagation delay** for `Out1` to discharge fully
5. During this delay, Gate 2's PDN is conducting because it sees `Out1 > V_{Tn}$
6. Precious charge is lost from `Out2` before `Out1` finishes transitioning

**Once `Out1` finally drops below $V_{Tn}$**, Gate 2's PDN turns off. But `Out2` is now at an **intermediate voltage** -- neither a clean HIGH nor a clean LOW.

**The critical difference from static logic**: In static gates, there is DC restoration -- the output will eventually settle to the correct level. In dynamic gates, the output relies on **capacitive charge storage**, so once charge is lost, it cannot be recovered.

---

## Domino Logic

### The Solution to Cascading

**Domino logic** solves the cascading problem by adding a **static inverter** after each dynamic gate. This simple addition has a profound effect.

### Structure

A Domino logic module consists of:
1. An **n-type dynamic logic block** (precharge PMOS + NMOS PDN + evaluation NMOS) -- same as basic dynamic logic
2. A **static inverter** at the output

### Operation

During precharge (CLK = 0):
- The dynamic node is charged to $V_{DD}$
- The inverter output is driven to **0** (because its input is HIGH)
- Therefore, **all Domino gate outputs start at 0** during precharge

During evaluation (CLK = 1):
- The dynamic node is conditionally discharged
- If discharged (dynamic node → 0): the inverter output transitions from 0 → 1
- If not discharged (dynamic node stays HIGH): the inverter output remains at 0

**Key insight**: During evaluation, Domino outputs can only make **0 → 1 transitions** (rising transitions). They never make 1 → 0 transitions during evaluation. This is what allows safe cascading.

### Why Cascading Works

When Domino gates are cascaded:
1. All outputs start at 0 during precharge
2. During evaluation, Gate 1's output can only go from 0 → 1
3. Gate 2 sees its input transition from 0 → 1 (not 1 → 0)
4. Since Gate 2's PDN only evaluates when its input goes HIGH, there is no premature discharge

The evaluation ripples through the chain like a line of falling dominos -- each gate evaluates only after the previous gate has completed -- hence the name **Domino logic**.

### Limitation: Only Non-Inverting Functions

Because Domino outputs can only make 0 → 1 transitions during evaluation, **Domino logic can only implement non-inverting logic functions**. The dynamic stage produces an inverted output (like CMOS), and the static inverter re-inverts it, yielding a non-inverted result.

Implementing inverting logic (like NAND, NOR, INV) requires additional techniques, which increases design complexity. Due to this limitation, pure Domino logic is less common in modern large-scale digital systems for general-purpose logic, but it is widely used in high-speed datapaths where non-inverting operations dominate.

### The Inverter Buffer Benefit

The static inverter at the output also provides:
- **Reduced capacitance** on the dynamic output node by separating internal and load capacitances
- **DC restoration**: The inverter provides strong drive capability
- **Improved noise immunity**: The inverter sharpens the signal and provides gain

---

## Importance of the Evaluation Transistor

### Why Can't We Simply Remove It?

Since all inputs of a Domino gate are forced to LOW during precharge (all previous stages output 0), it may seem that the evaluation transistor is unnecessary. Removing it appears attractive because:
- Reduces clock loading
- Improves pull-down drive strength
- Potentially increases evaluation speed

However, removing the evaluation transistor introduces **serious problems**:

### Problem 1: Ripple Precharge

Without the evaluation transistor, precharge **no longer occurs simultaneously** across all stages. Instead, it must propagate sequentially:

1. When CLK goes LOW, Gate 1 starts precharging
2. But if `In1` was HIGH during evaluation, the previous output was LOW, meaning Gate 2's input was HIGH
3. Gate 2 cannot precharge until Gate 1's output (after the inverter) transitions -- which requires Gate 1 to precharge first
4. This creates a sequential chain where each gate waits for the previous one

**Result**: Total precharge time approximately equals the critical path delay of the entire logic network, destroying the speed advantage of Domino logic.

### Problem 2: Increased Power Consumption

Without the evaluation transistor, during precharge:
- Both the pull-up (precharge PMOS) and pull-down devices may conduct simultaneously
- This creates a **direct current path** from $V_{DD}$ to GND
- Resulting in **short-circuit current** and unnecessary power loss

### Design Recommendation

It is **good design practice to always include evaluation transistors** in Domino logic circuits. The evaluation transistor:
- Isolates the PDN during precharge
- Allows fast and simultaneous charging of all dynamic nodes
- Maintains low power operation

---

## Advantages of Domino Logic

| Advantage | Explanation |
|-----------|-------------|
| **High speed** | Reduced input capacitance and fewer PMOS devices in pull-down path |
| **Lower transistor count** | Compared to static CMOS for complex gates |
| **Smaller area** | Efficient implementation of wide fan-in logic |
| **No static power** | Precharge/evaluate eliminates DC current paths |
| **Safe cascading** | Only rising transitions during evaluation |
| **Suitable for high-performance CPUs** | Used in high-speed datapath circuits |

## Disadvantages of Domino Logic

| Disadvantage | Explanation |
|--------------|-------------|
| **Requires clock signal** | Needs precharge and evaluate phases, adding clock distribution overhead |
| **Higher dynamic power** | Output is precharged every clock cycle, even if no evaluation is needed |
| **Noise sensitive** | Charge sharing and leakage can cause errors in the precharged node |
| **Only non-inverting logic** | Cannot directly implement NOT, NAND, NOR functions |
| **Design complexity** | Careful timing and sizing required to prevent charge loss |
| **Clock overhead** | Clock signal must be distributed to all dynamic gates |

---

## Comparison: Static CMOS vs Dynamic vs Domino

| Parameter | Static CMOS | Dynamic Logic | Domino Logic |
|-----------|-------------|---------------|--------------|
| **Transistor count** | 2N | N + 2 | N + 3 (including inverter) |
| **Static power** | Zero | Zero | Zero |
| **Clock required?** | No | Yes | Yes |
| **Output swing** | Rail-to-rail | Rail-to-rail | Rail-to-rail |
| **Speed** | Good | Very fast | Very fast |
| **Cascading** | Always works | Fails | Works (rising transitions only) |
| **Logic type** | Any function | Any function | Non-inverting only |
| **Noise immunity** | Excellent | Poor (charge sharing) | Moderate |
| **Design complexity** | Moderate | High | High |

---

## Common Mistakes

1. **Forgetting that Domino only supports non-inverting functions**: This is the most frequently tested limitation
2. **Confusing precharge and evaluation phases**: Precharge = CLK LOW (output → HIGH); Evaluation = CLK HIGH (conditional discharge)
3. **Thinking cascading works for basic dynamic gates**: It doesn't -- only Domino (with inverter) supports cascading
4. **Forgetting the evaluation transistor's purpose**: It prevents short-circuit current during precharge and enables simultaneous precharge
5. **Not understanding the ripple precharge problem**: Without $M_e$, precharge becomes sequential with delay equal to critical path

---

## Self-Check Questions

**Q1**: How many transistors does a 4-input Domino NAND gate require?
> N + 3 = 4 + 3 = 7 transistors (4 NMOS in PDN + 1 precharge PMOS + 1 evaluation NMOS + 2 for inverter)

**Q2**: Why can Domino logic gates be safely cascaded but basic dynamic gates cannot?
> Domino gates have a static inverter at the output that ensures all outputs start at 0 during precharge and can only make 0→1 transitions during evaluation. This means downstream gates never see a momentary HIGH that could cause premature discharge.

**Q3**: What happens if the evaluation transistor is removed from Domino logic?
> Two problems: (1) Precharge becomes sequential (ripple precharge), destroying the speed advantage. (2) Short-circuit current flows during precharge when both pull-up and pull-down conduct simultaneously, increasing power consumption.

**Q4**: Can a Domino gate implement a NOR function directly?
> No. Domino logic can only implement non-inverting functions (AND, OR). To implement NOR, you would need additional circuitry or combine it with static logic.

**Q5**: During which phase is the output node charged to $V_{DD}$?
> During the precharge phase (CLK = 0). The PMOS precharge transistor $M_p$ is ON, charging the dynamic node to $V_{DD}$.

---

## Concept Links

- Basic CMOS logic (motivation comparison): [02_cmos_logic.md](./02_cmos_logic.md)
- Pseudo-NMOS's static power motivates dynamic logic: [03_nmos_and_pseudo_nmos_logic.md](./03_nmos_and_pseudo_nmos_logic.md)
- DCSL as another clocked differential approach: [06_dcsl.md](./06_dcsl.md)
- Clock distribution power (clocking overhead): [01_clocking_and_clock_distribution.md](./01_clocking_and_clock_distribution.md)
- Domino XOR gate for adder design: [11_low_voltage_logic_styles.md](./11_low_voltage_logic_styles.md#domino-logic-styles)
