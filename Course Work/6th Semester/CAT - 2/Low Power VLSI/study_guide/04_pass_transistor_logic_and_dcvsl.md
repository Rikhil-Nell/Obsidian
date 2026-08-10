# 04 - Pass Transistor Logic (PTL) and Differential Cascode Voltage Switch Logic (DCVSL)

---

## Learning Objectives

After studying this section, you will be able to:

- Explain the operating principle of pass transistor logic
- Define and distinguish between strong and weak logic levels
- Explain why NMOS passes strong 0 but weak 1 (and the opposite for PMOS)
- Calculate the voltage drop in NMOS pass transistor logic
- Describe the sneak path problem and how to mitigate it
- Compare NMOS and PMOS pass transistors across key parameters
- Explain the structure and operation of DCVSL gates
- Analyze the advantages and disadvantages of both PTL and DCVSL

---

## Ground-Up Explanation

### What is Pass Transistor Logic?

In conventional CMOS logic, transistors act as switches connecting the output to either $V_{DD}$ (through PMOS) or GND (through NMOS). The source node of each transistor is always connected to a **power supply rail**.

**Pass Transistor Logic (PTL)** takes a fundamentally different approach: the source node of the MOS transistor is connected to **input signals** rather than to the power supply voltage. This means the transistor passes (or blocks) a logic signal from one node to another, acting as a transmission gate controlled by its gate voltage.

**Analogy**: If CMOS logic is like a water system where faucets connect pipes to either the main water supply or the drain, PTL is like a system of valves that can redirect water from one pipe to another -- the water (signal) comes from another pipe, not directly from the main supply.

![[pass_transistor_and_gate.png]]

### Applications

Pass transistor circuits are widely used in:
- ROMs (Read-Only Memories)
- PLAs (Programmable Logic Arrays)
- Multiplexers
- XOR/XNOR gates (particularly efficient implementation)

---

## Strong and Weak Logic Levels

Understanding strong and weak logic is essential for PTL design. These concepts describe how well a transistor can drive an output to the desired voltage level.

### Definitions

| Term | Definition |
|------|-----------|
| **Strong '1'** | An output voltage very close to $V_{DD}$ (the positive supply rail) |
| **Weak '1'** | An output voltage that is above $V_{IH}$ (input HIGH threshold) but lower than a strong '1' |
| **Strong '0'** | An output voltage very close to $V_{SS}$ (ground, 0 V) |
| **Weak '0'** | An output voltage that is below $V_{IL}$ (input LOW threshold) but higher than a strong '0' |

### NMOS Pass Transistor: Passes Strong '0' but Weak '1'

This is one of the most important concepts in PTL design and a frequently asked exam question.

**Passing Logic '0' (Strong)**:
- Gate is HIGH ($V_{DD}$), input source is at 0 V
- $V_{GS} = V_{DD} - 0 = V_{DD}$ (large, transistor is strongly ON)
- The NMOS can pull the output all the way down to 0 V
- Result: **Strong '0'** at the output

**Passing Logic '1' (Weak)**:
- Gate is HIGH ($V_{DD}$), input source is at $V_{DD}$
- As the output node ($V_S$) charges from 0 toward $V_{DD}$:
  - $V_{GS} = V_{DD} - V_S$ decreases progressively
  - When $V_S = V_{DD} - V_{Tn}$, then $V_{GS} = V_{Tn}$ (minimum voltage for conduction)
  - The NMOS transistor **turns OFF** at this point
- The output cannot charge beyond $V_{DD} - V_{Tn}$
- Result: **Weak '1'** at the output (degraded by one threshold voltage)

$$\boxed{V_{OH,NMOS} = V_{DD} - V_{Tn} \quad \text{(weak '1')}}$$

### PMOS Pass Transistor: Passes Strong '1' but Weak '0'

The PMOS transistor exhibits the complementary behavior:

**Passing Logic '1' (Strong)**:
- Gate is LOW (0 V), input source is at $V_{DD}$
- $|V_{GS}| = V_{DD}$ (large, transistor is strongly ON)
- The PMOS can charge the output all the way to $V_{DD}$
- Result: **Strong '1'** at the output

**Passing Logic '0' (Weak)**:
- Gate is LOW (0 V), input source is at 0 V
- As the output discharges from $V_{DD}$ toward 0 V:
  - $|V_{GS}| = |0 - V_{out}| = V_{out}$ decreases
  - When $V_{out} = |V_{Tp}|$, then $|V_{GS}| = |V_{Tp}|$ (minimum for conduction)
  - The PMOS **turns OFF**
- The output cannot discharge below $|V_{Tp}|$
- Result: **Weak '0'** at the output

$$\boxed{V_{OL,PMOS} = |V_{Tp}| \quad \text{(weak '0')}}$$

### Strong and Weak Logic Summary Table

| Transistor | Passes Strong... | Passes Weak... |
|-----------|-----------------|----------------|
| **NMOS** | '0' (output reaches 0 V) | '1' (output limited to $V_{DD} - V_{Tn}$) |
| **PMOS** | '1' (output reaches $V_{DD}$) | '0' (output limited to $|V_{Tp}|$) |

**Key rule**: nMOS passes a strong logic '0' but a degraded logic '1'. The opposite is true for pMOS.

### The Source Voltage Rule

The source voltage in a MOS transistor is always the **lower** of $V_D$ and $V_G - V_T$. This fundamental relationship governs the voltage level that can be passed through a transistor.

---

## Advantages and Limitations of PTL

### Advantages

| Advantage | Explanation |
|-----------|-------------|
| **Not ratioed** | Pass transistors can be minimum geometry |
| **No VDD-to-GND path** | No standby power (no static current) |
| **Fewer transistors** | Simpler implementations for some functions |
| **Good for multiplexer designs** | Natural switch behavior |

### Limitations

| Limitation | Explanation |
|-----------|-------------|
| **Threshold voltage drop** | NMOS loses $V_{Tn}$ on HIGH; PMOS loses $|V_{Tp}|$ on LOW |
| **Sneak paths** | Created when two pass transistors are ON simultaneously, one connected to $V_{DD}$ and the other to GND |
| **Signal degradation** | Cascading multiple pass transistors causes cumulative voltage loss |
| **Slower for long chains** | Each stage adds delay and voltage degradation |

### PMOS vs NMOS PTL Comparison

| Parameter | NMOS PTL | PMOS PTL |
|-----------|----------|----------|
| Strong output | '0' (GND) | '1' ($V_{DD}$) |
| Weak output | '1' ($V_{DD} - V_{Tn}$) | '0' ($|V_{Tp}|$) |
| Speed | Faster (higher $\mu_n$) | Slower (lower $\mu_p$) |
| Preferred for | Pull-down paths | Pull-up paths |
| Threshold drop | On logic HIGH | On logic LOW |

### Solution: Transmission Gate

To achieve full voltage swing in both directions, a **transmission gate** (also called a complementary pass transistor) uses an NMOS and PMOS in parallel:
- NMOS passes strong '0' while PMOS passes strong '1'
- Together, they provide full rail-to-rail voltage swing
- Requires both the true signal and its complement at the gates

---

## Differential Cascode Voltage Switch Logic (DCVSL)

### Motivation

Both CMOS logic and ratioed logic (Pseudo-NMOS) have their limitations. CMOS requires 2N transistors, while Pseudo-NMOS has static power. DCVSL creates a **ratioed logic style that eliminates static currents and provides rail-to-rail swing** by combining two key concepts:

1. **Differential logic**: Each input is provided in complementary format (both $A$ and $\bar{A}$), and the gate produces complementary outputs
2. **Positive feedback**: A cross-coupled PMOS pair ensures that the load device is turned off when not needed

### Structure

A DCVSL gate consists of:
- **Two complementary NMOS pull-down networks** (PDN1 and PDN2) implementing the function and its complement
- **A cross-coupled PMOS pair** that provides positive feedback and acts as the load

![[cmos_pun_pdn_structure.png]]

### Operation

1. **Initial state**: Assume one output is HIGH and the other is LOW
2. When inputs change, one PDN starts conducting more strongly than the other
3. The conducting PDN begins to pull its output LOW
4. The cross-coupled PMOS transistors provide **positive feedback**: as one output drops, it turns ON the PMOS connected to the other output, reinforcing that output's HIGH level
5. The feedback mechanism ensures that the final outputs reach full $V_{DD}$ and GND (rail-to-rail swing)

### Key Feature: Transistor Sharing

In some logic functions such as XOR/XNOR, transistors can be **shared** between PDN1 and PDN2. This provides:
- Reduced total transistor count
- Smaller silicon area
- Lower parasitic capacitance
- Improved speed and lower implementation cost

### Cross-Over Current Problem

Despite the positive feedback mechanism, DCVSL has a power dissipation issue:
- During transitions, there is a period when **both a PMOS and a PDN are turned on simultaneously**
- This creates a **short-circuit path** from $V_{DD}$ to GND
- The resulting cross-over current increases power consumption

---

## Advantages of DCVSL

| Advantage | Explanation |
|-----------|-------------|
| **High Speed** | Only NMOS devices in pull-down (higher electron mobility means faster switching) |
| **Differential Outputs** | Generates both $F$ and $\bar{F}$ simultaneously, eliminating the need for an extra inverter |
| **Good Noise Immunity** | Differential operation improves noise rejection and common-mode noise suppression |
| **No Static Power** | Like CMOS, it is a static logic family with no direct $V_{DD}$-to-GND path in steady state |
| **Fast Switching** | Cross-coupled PMOS provides regenerative action (positive feedback) that improves transition speed |
| **Useful for noise-sensitive designs** | Differential signaling inherently rejects common-mode interference |

## Disadvantages of DCVSL

| Disadvantage | Explanation |
|--------------|-------------|
| **Higher Transistor Count** | Requires duplicated pull-down networks (PDN1 and PDN2) |
| **Larger Area** | Compared to simple CMOS gate due to dual PDN and cross-coupled PMOS |
| **Increased Design Complexity** | More complex layout; careful matching required for symmetry between the two sides |
| **Higher Input Capacitance** | More transistors at the input means larger input loading, which can slow down previous stages |
| **Dynamic Power** | Higher than single-ended CMOS because both output nodes switch (charging/discharging two nodes plus internal nodes) |
| **Cross-over Current** | During switching, short-circuit current flows, increasing power consumption |

---

## Comparison: PTL vs DCVSL vs CMOS

| Parameter | CMOS | PTL | DCVSL |
|-----------|------|-----|-------|
| **Transistor count** | 2N | Varies (can be < N) | > 2N (dual PDN + PMOS pair) |
| **Output swing** | Rail-to-rail | Degraded (NMOS: $V_{DD}-V_{Tn}$) | Rail-to-rail |
| **Static power** | Zero | Zero | Zero |
| **Speed** | Good | Good (for simple functions) | Very High |
| **Noise immunity** | Excellent | Moderate | Excellent (differential) |
| **Complementary outputs** | Needs inverter | Needs inverter | Built-in |
| **Best for** | General purpose | MUX, XOR, memory | High-speed differential |

---

## Common Mistakes

1. **Saying NMOS passes strong '1'**: NMOS passes strong '0' and weak '1' (degraded by $V_{Tn}$)
2. **Confusing DCVSL with dynamic logic**: DCVSL is a **static** logic family despite using differential operation
3. **Forgetting the threshold voltage drop formula**: $V_{OH,NMOS} = V_{DD} - V_{Tn}$; $V_{OL,PMOS} = |V_{Tp}|$
4. **Ignoring sneak paths**: When two pass transistors are simultaneously ON with opposite levels
5. **Thinking DCVSL has no power issues**: Cross-over current during switching is a real concern

---

## Self-Check Questions

**Q1**: An NMOS pass transistor with $V_{DD} = 3.3$ V and $V_{Tn} = 0.7$ V passes logic '1'. What is the output voltage?
> $V_{OH} = V_{DD} - V_{Tn} = 3.3 - 0.7 = 2.6$ V (weak '1')

**Q2**: Why does DCVSL not have static power consumption?
> The cross-coupled PMOS pair ensures that one PMOS is always OFF in steady state, preventing any direct $V_{DD}$-to-GND current path. This is similar to CMOS where the PUN and PDN are mutually exclusive.

**Q3**: What are the two key concepts that DCVSL combines?
> 1) Differential logic (complementary inputs and outputs) and 2) Positive feedback (cross-coupled PMOS providing regenerative action).

**Q4**: Why can NMOS pull down to 0 V but not pull up to $V_{DD}$?
> When pulling down, the NMOS gate is at $V_{DD}$ and source approaches 0 V, so $V_{GS}$ remains large ($V_{DD}$), keeping the transistor strongly ON. When pulling up, as the source rises toward $V_{DD}$, $V_{GS}$ decreases until it equals $V_{Tn}$, at which point the transistor turns OFF, unable to charge further.

**Q5**: What is a sneak path in PTL?
> A sneak path occurs when two pass transistors are both ON simultaneously, with one connected to $V_{DD}$ and the other to GND, creating an unintended short-circuit current path.

---

## Concept Links

- CMOS logic fundamentals (PUN/PDN): [02_cmos_logic.md](./02_cmos_logic.md)
- Pseudo-NMOS as another ratioed approach: [03_nmos_and_pseudo_nmos_logic.md](./03_nmos_and_pseudo_nmos_logic.md)
- Complementary Pass Transistor Logic (CPL) for adders: [11_low_voltage_logic_styles.md](./11_low_voltage_logic_styles.md#complementary-pass-transistor-logic-cpl)
- Dynamic logic as an alternative to static PTL: [05_dynamic_logic_and_domino.md](./05_dynamic_logic_and_domino.md)
