# 02 - CMOS Logic

---

## Learning Objectives

After studying this section, you will be able to:

- Explain the structure and operation of complementary CMOS logic gates
- Describe the roles of Pull-Up Network (PUN) and Pull-Down Network (PDN)
- Explain why NMOS is used in PDN and PMOS is used in PUN
- Construct CMOS gates for any Boolean function using duality
- Calculate transistor count for an N-input CMOS gate
- List and explain the advantages and disadvantages of CMOS logic
- Describe the latch-up problem and its implications

---

## Ground-Up Explanation

### What is CMOS Logic?

CMOS stands for **Complementary Metal-Oxide-Semiconductor**. The word "complementary" is the key: CMOS logic uses **both** NMOS and PMOS transistors working together in a complementary fashion to implement logic functions. This is in contrast to older logic families like NMOS-only logic, which used just one type of transistor.

**Analogy**: Think of CMOS as a two-team relay system. Team PMOS (the pull-up team) is responsible for connecting the output to the power supply ($V_{DD}$) when the output should be logic 1. Team NMOS (the pull-down team) is responsible for connecting the output to ground ($V_{SS}$) when the output should be logic 0. Crucially, only one team is ever active at a time - they never both conduct simultaneously in steady state.

### The Fundamental Structure

A static CMOS gate consists of two complementary networks:

1. **Pull-Up Network (PUN)**: Made entirely of PMOS transistors. Its function is to provide a conducting path between $V_{DD}$ and the output whenever the output should be logic 1.

2. **Pull-Down Network (PDN)**: Made entirely of NMOS transistors. Its function is to provide a conducting path between $V_{SS}$ (ground) and the output whenever the output should be logic 0.

![[cmos_pun_pdn_structure.png]]

The PUN and PDN are constructed in a **mutually exclusive** fashion: when the PUN conducts, the PDN is off, and vice versa. This ensures that:
- There is **always** a defined path from the output to either $V_{DD}$ or $V_{SS}$ (no floating output)
- There is **never** a direct path from $V_{DD}$ to $V_{SS}$ in steady state (no static current)

---

## Why NMOS in PDN and PMOS in PUN?

This is a fundamental question in CMOS design, and the answer lies in the physics of MOS transistors and the concepts of "strong" and "weak" logic levels.

### NMOS Transistor as a Switch

- When the controlling signal (gate) is **HIGH**: NMOS is ON (conducting)
- When the controlling signal (gate) is **LOW**: NMOS is OFF (non-conducting)

### PMOS Transistor as a Switch

- When the controlling signal (gate) is **LOW**: PMOS is ON (conducting)
- When the controlling signal (gate) is **HIGH**: PMOS is OFF (non-conducting)

PMOS acts as an **inverse switch** compared to NMOS.

### Strong Zeros and Strong Ones

The critical reason for the NMOS-in-PDN and PMOS-in-PUN arrangement:

- **NMOS produces "strong zeros"**: An NMOS transistor can pull the output all the way down to GND (0 V). It passes logic 0 perfectly.
- **PMOS produces "strong ones"**: A PMOS transistor can charge the output all the way up to $V_{DD}$. It passes logic 1 perfectly.

![[nmos_pulldown_switch.png]]

If you tried to use a PMOS to pull down, it would only lower the output to $|V_{Tp}|$ (a threshold voltage above ground), not to 0 V. The PMOS turns off at that point and stops conducting. Similarly, if you used an NMOS to pull up, it would only raise the output to $V_{DD} - V_{Tn}$, not the full $V_{DD}$.

![[pmos_pullup_switch.png]]

This is why:
- **NMOS transistors are the preferred devices in the PDN** (they create strong zeros)
- **PMOS transistors are the preferred devices in the PUN** (they create strong ones)

---

## Constructing CMOS Gates

### Series and Parallel Connections

The topology of transistor connections determines the logic function:

**For NMOS (PDN)**:
- **Series connection** = AND function: All inputs must be HIGH for current to flow
- **Parallel connection** = OR function: At least one input HIGH allows current to flow

**For PMOS (PUN)**:
- **Series connection** = NOR function (because PMOS is active-low): All inputs must be LOW
- **Parallel connection** = NAND function: At least one input LOW allows current to flow

![[series_parallel_nmos.png]]
![[series_parallel_pmos.png]]

### The Duality Principle

A fundamental rule in CMOS design is that the PUN and PDN are **duals** of each other:

- A **parallel** connection in the PDN corresponds to a **series** connection in the PUN, and vice versa
- If the PDN implements function $F$ using certain series/parallel combinations, the PUN must implement $\bar{F}$ using the complementary topology

This means: to construct a CMOS gate, you can design one network (typically the PDN, since NMOS logic is more intuitive) and then derive the complementary network automatically by swapping series for parallel and NMOS for PMOS.

### Inherently Inverting

Complementary CMOS gates are **naturally inverting**. They can directly implement functions such as:
- NOT (inverter)
- NAND
- NOR
- XNOR

To realize a **non-inverting** function (AND, OR, XOR), an extra inverter stage must be added at the output.

### Transistor Count

$$\boxed{\text{Transistor count for N-input CMOS gate} = 2N}$$

An N-input CMOS gate requires N NMOS transistors in the PDN and N PMOS transistors in the PUN, for a total of 2N transistors.

---

## Example CMOS Gates

### CMOS Inverter

The simplest CMOS gate consists of one NMOS and one PMOS transistor (2 transistors total for 1 input):

- When input = 1: NMOS ON, PMOS OFF, output connected to GND (output = 0)
- When input = 0: NMOS OFF, PMOS ON, output connected to $V_{DD}$ (output = 1)

![[cmos_inverter_nand.png]]

### Two-Input CMOS NAND Gate

The NAND function ($\overline{A \cdot B}$) requires:
- **PDN**: Two NMOS transistors in **series** (both must be ON to pull output to GND, which happens only when A=1 AND B=1)
- **PUN**: Two PMOS transistors in **parallel** (either being ON pulls output to $V_{DD}$, which happens when A=0 OR B=0)

Total transistors: 4 (2 NMOS + 2 PMOS) for a 2-input gate

![[cmos_nand_gate.png]]

### Two-Input CMOS NOR Gate

The NOR function ($\overline{A + B}$) requires:
- **PDN**: Two NMOS transistors in **parallel** (either being ON pulls output to GND)
- **PUN**: Two PMOS transistors in **series** (both must be ON to pull output to $V_{DD}$)

Total transistors: 4 (2 NMOS + 2 PMOS) for a 2-input gate

### Complex Gate Example

For a function like $F = \overline{A \cdot B + C}$:
- **PDN**: (A in series with B) in parallel with C - all NMOS
- **PUN**: (A in parallel with B) in series with C - all PMOS (applying the duality principle)

---

## Advantages of CMOS Logic

### 1. Very Low Static Power Consumption

In steady state (whether output is logic 0 or logic 1), either the PUN or PDN is completely OFF. This means there is ideally **no direct current path** from $V_{DD}$ to GND. Power is mainly consumed during switching transitions (dynamic power).

$$P_{dynamic} = \alpha \cdot C_L \cdot V_{DD}^2 \cdot f$$

![[dynamic_power_formula.png]]

This makes CMOS ideal for battery-powered and portable devices where minimizing standby power is critical.

### 2. High Noise Immunity

CMOS provides excellent noise margins because:
- Output logic HIGH is a full $V_{DD}$ (strong 1)
- Output logic LOW is a full 0 V (strong 0)
- The noise margin is approximately $V_{DD}/2$ in both directions

These strong logic levels reduce the chance of logic errors due to noise and interference. This property makes CMOS very suitable for high-density integration where signals are closely packed.

### 3. High Input Impedance

The gate of a MOSFET is insulated by a thin oxide layer, which means the gate draws essentially **zero DC input current**. This provides:
- Near-infinite input impedance
- Large fan-out capability (one output can drive many inputs)
- Minimal loading effect on previous stages

### 4. Rail-to-Rail Output Voltage

The output voltage swings from 0 V to $V_{DD}$ (full rail-to-rail swing). This ensures strong logic levels and excellent signal integrity across long interconnect paths.

### 5. High Packing Density

CMOS technology supports very small transistor sizes (down to nanometer scale). This enables:
- Integration of millions to billions of transistors on a single chip
- Essential for modern microprocessors, SoCs, and memory chips
- Higher functionality per unit area

---

## Disadvantages of CMOS Logic

### 1. Dynamic Power Consumption

Although static power is very low, CMOS circuits consume significant power during switching. The dynamic power consumption is:

$$\boxed{P_{dynamic} = \alpha \cdot C_L \cdot V_{DD}^2 \cdot f}$$

At high frequencies and with large capacitive loads, the dynamic power can become very significant. This is the dominant power component in modern high-performance processors.

### 2. Short-Circuit Power

During switching transitions, there is a brief period when both PMOS and NMOS transistors conduct simultaneously. This creates a **direct current path** from $V_{DD}$ to GND, resulting in short-circuit current.

The short-circuit power is proportional to:
- The rise/fall time of the input signal
- The frequency of switching
- The supply voltage

While typically smaller than dynamic power, short-circuit power can contribute 10-15% of total power in some designs.

### 3. Leakage Power (Advanced Nodes)

In deep submicron technologies (below 90 nm), leakage currents increase dramatically due to:
- **Subthreshold leakage**: Current flows even when the transistor is "OFF" because the threshold voltage is reduced
- **Gate oxide tunneling**: Electrons tunnel through the very thin gate oxide
- **Junction leakage**: Reverse-biased pn junctions have non-zero current

Leakage becomes a major issue in nanometer technologies and can rival or exceed dynamic power dissipation. This topic is covered extensively in [07_leakage_currents.md](./07_leakage_currents.md).

### 4. Latch-Up Problem

Parasitic **bipolar transistors** exist within the CMOS structure (formed by the n-well, p-substrate, and source/drain diffusions). Under certain conditions, these parasitic devices can form a **Silicon Controlled Rectifier (SCR)**.

If the SCR is triggered (by supply voltage spikes, ESD events, or radiation):
- A low-resistance path is created between $V_{DD}$ and GND
- Very large current flows through this path
- The chip can **overheat and be permanently damaged**

Prevention methods include:
- Guard rings around sensitive transistors
- Proper well/substrate contacts
- ESD protection circuits
- Sufficient spacing between NMOS and PMOS devices

### 5. High Transistor Count

For an N-input gate, CMOS requires 2N transistors. This is twice the count of NMOS-only logic (which needs only N + 1 transistors for a pseudo-NMOS gate). While this increases area, the benefits of zero static power and full voltage swing typically outweigh this cost.

---

## Key Formulas

| Formula | Description |
|---------|-------------|
| $\text{Transistor count} = 2N$ | N-input CMOS gate |
| $P_{dynamic} = \alpha \cdot C_L \cdot V_{DD}^2 \cdot f$ | Dynamic power consumption |
| $P_{short-circuit} \propto t_{rise} \cdot V_{DD} \cdot f$ | Short-circuit power |

---

## Common Mistakes

1. **Drawing PUN with NMOS or PDN with PMOS**: Always use PMOS for pull-up and NMOS for pull-down
2. **Getting the duality wrong**: Series in PDN maps to parallel in PUN, and vice versa
3. **Forgetting that CMOS is naturally inverting**: You cannot directly make an AND gate without an extra inverter
4. **Ignoring the 2N rule**: For an N-input gate, always count 2N transistors
5. **Assuming CMOS has zero power consumption**: It has zero *static* power, but dynamic and leakage power are significant
6. **Confusing latch-up with latch**: Latch-up is a parasitic failure mode, not a storage element

---

## Self-Check Questions

**Q1**: How many transistors are needed for a 4-input CMOS NOR gate?
> $2 \times 4 = 8$ transistors (4 NMOS in parallel for PDN, 4 PMOS in series for PUN)

**Q2**: Why can't CMOS directly implement non-inverting functions?
> Because the PUN (PMOS) and PDN (NMOS) inherently create complementary outputs. The PUN connects to $V_{DD}$ when the function is FALSE (inverted). A non-inverting function requires an additional inverter stage.

**Q3**: What is the primary advantage of CMOS over NMOS-only logic?
> Zero static power consumption. In NMOS logic with a resistive or depletion load, current flows continuously when the output is LOW. In CMOS, one of the two networks is always OFF, so no DC current path exists.

**Q4**: Explain why NMOS passes a strong 0 but a weak 1.
> When NMOS pulls down, $V_{GS}$ remains at $V_{DD}$ (gate at $V_{DD}$, source at 0), keeping the transistor fully ON. But when trying to pass a 1, as the output rises toward $V_{DD}$, $V_{GS}$ decreases to $V_{DD} - V_{out}$. When $V_{out} = V_{DD} - V_{Tn}$, $V_{GS} = V_{Tn}$ and the transistor turns OFF, unable to charge further.

**Q5**: What causes latch-up in CMOS and how is it prevented?
> Parasitic bipolar transistors form an SCR between $V_{DD}$ and GND. Triggered by voltage spikes or ESD, it creates a destructive low-resistance path. Prevention: guard rings, proper well contacts, adequate spacing, ESD protection.

---

## Concept Links

- NMOS and Pseudo-NMOS alternatives to CMOS: [03_nmos_and_pseudo_nmos_logic.md](./03_nmos_and_pseudo_nmos_logic.md)
- Pass Transistor Logic as a reduced-transistor alternative: [04_pass_transistor_logic_and_dcvsl.md](./04_pass_transistor_logic_and_dcvsl.md)
- Dynamic power formula in formula sheet: [14_formula_sheet_ultimate.md](./14_formula_sheet_ultimate.md#dynamic-power)
- Leakage power in advanced CMOS: [07_leakage_currents.md](./07_leakage_currents.md)
- CMOS XOR gate for adder implementations: [11_low_voltage_logic_styles.md](./11_low_voltage_logic_styles.md#full-static-cmos)
