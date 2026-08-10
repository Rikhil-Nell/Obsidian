# 11 - Low-Voltage Low-Power Logic Styles

---

## Learning Objectives

After studying this section, you will be able to:

- Explain technology and supply voltage scaling trends
- Describe why CMOS and BiCMOS remain the dominant technologies
- Classify logic styles into static and dynamic categories
- Explain the impact of series transistor connections on resistance and delay
- Describe the Full Static CMOS XOR/XNOR gate implementation
- Explain Complementary Pass Transistor Logic (CPL) and the $V_{DD} - V_{th}$ problem
- Describe Double Pass Transistor Logic (DPL) and how it solves CPL's limitation
- Explain Dual-Rail Domino and Single-Rail Domino XOR/NOR implementations
- Compare all five logic styles for adder design

---

## Technology and Supply Voltage Trends

### Scaling Roadmap

Technology scaling is the continuous reduction of transistor dimensions to improve performance, density, and cost per transistor:

| Year (approx.) | Technology Node |
|----------------|----------------|
| 2001 | 95 nm |
| 2003 | 65 nm |
| 2007 | 45 nm |
| 2010 | 32 nm |
| 2013 | 22 nm |
| 2016 | 13-14 nm |
| Present | 5 nm, 3 nm |

![[technology_supply_voltage_trends.png]]

### CMOS and BiCMOS Dominance

Most studies on process technologies for low-voltage and low-power applications indicate that **scaled CMOS and BiCMOS technologies will continue to be the dominant solutions**, driven by:
- High performance at low power
- Mature fabrication infrastructure
- Cost-effectiveness at high volume

### Supply Voltage Trends

As technology scales:
- $V_{DD}$ reduces (from 5V → 3.3V → 1.8V → 1.2V → 0.8V → ...)
- $V_{th}$ also reduces but **not as aggressively** as $V_{DD}$
- The ratio $V_{DD}/V_{th}$ decreases, reducing overdrive and speed
- Low-voltage operation becomes critical for portable, battery-powered devices

---

## Why Logic Style Choice Matters for Adders

Low-power applications have become a major concern for VLSI designers. Designing **high-speed adders with low power consumption** is extremely important because adders are essential components of:
- Arithmetic Logic Unit (ALU)
- Floating-point unit
- Address generation during cache and memory access

Since these operations occur frequently, the **efficiency of the adder directly affects the overall performance and power consumption** of the processor.

The challenge can be approached at multiple design levels:
- Architectural level
- **Circuit level (logic style choice)** ← This section focuses here
- Layout level
- Device level
- Process technology level

---

## Static vs Dynamic Logic Styles

| Property | Static Logic | Dynamic Logic |
|----------|-------------|---------------|
| Output determination | Continuously by inputs | Only during evaluation phase |
| Clock required? | No | Yes (precharge + evaluate) |
| Extra switching activity | No precharge-related switching | Precharge causes additional switching |
| Power | Generally lower | Can be higher due to clock overhead |
| Speed | Good | Very high (for certain functions) |

Static logic avoids the additional switching activity associated with clock-controlled precharge, reducing extra power dissipation. Dynamic logic offers speed advantages but with power overhead from clock distribution.

---

## Series Transistor Resistance Problem

When NMOS or PMOS transistors are connected in **series**:
- Their effective resistance **increases**
- This reduces the current driving capability
- To maintain reasonable conducting current, **transistor width must be increased**

![[series_nmos_resistance.png]]
![[series_pmos_resistance.png]]

### Why This Matters

The delay time is determined by **RC delay**:
- $R$ = resistance (inversely proportional to width $W$)
- $C$ = capacitance (load)
- Since $R \propto 1/W$, increasing $W$ reduces $R$ and therefore reduces delay

**For series connections**: If $k$ transistors are in series, the effective resistance is approximately $k \times R_{single}$. To match the drive of a single transistor, each series transistor must have its width increased by factor $k$.

---

## Five Logic Styles for XOR/XNOR Implementation

### 1. Full Static CMOS

The standard complementary CMOS implementation of XOR uses both NMOS pull-down and PMOS pull-up networks.

![[xor_full_static_cmos.png]]

| Property | Value |
|----------|-------|
| **Transistor count** | 12 transistors (for 2-input XOR) |
| **Pull-up** | PMOS network (series + parallel) |
| **Pull-down** | NMOS network (series + parallel) |
| **Output swing** | Full rail-to-rail ($0$ to $V_{DD}$) |
| **Static power** | Zero |
| **Speed** | Moderate (limited by series PMOS) |

**Issue**: Series PMOS transistors have high resistance (due to lower hole mobility), requiring larger transistor widths to maintain drive strength. This increases area and capacitance.

### 2. Complementary Pass Transistor Logic (CPL)

CPL uses pass transistors with only NMOS devices in the pass network, achieving a compact design.

![[xor_cpl_implementation.png]]

| Property | Value |
|----------|-------|
| **Transistor count** | ~6 transistors (half of full CMOS) |
| **Key advantage** | No series transistor sizing needed |
| **Key problem** | Output HIGH is only $V_{DD} - V_{th}$ (threshold voltage drop) |

#### The $V_{DD} - V_{th}$ Problem in CPL

When the NMOS pass transistor passes logic '1':
- Output voltage reaches only $V_{DD} - V_{th}$ (as explained in [04_pass_transistor_logic_and_dcvsl.md](./04_pass_transistor_logic_and_dcvsl.md#nmos-pass-transistor-passes-strong-0-but-weak-1))
- This degraded HIGH voltage means the **PMOS in downstream inverters is not fully turned off**
- Large **short-circuit current** flows through the inverter
- Power consumption increases significantly

#### The pMOS Feedback Fix

To solve this, an **additional pMOS transistor** is connected as feedback across the inverter output:
- This pulls the output node X up to full $V_{DD}$
- Restores the full logic level
- Reduces short-circuit current

![[xor_cpl_pmos_feedback.png]]

### 3. Double Pass Transistor Logic (DPL)

DPL is an improvement over CPL that uses **both NMOS and PMOS** transistors in the pass network.

![[xor_dpl_implementation.png]]

| Property | Value |
|----------|-------|
| **Transistor count** | More than CPL, fewer than full CMOS |
| **Key advantage** | Full voltage swing (no $V_{th}$ drop) |
| **Why it works** | NMOS passes strong '0', PMOS passes strong '1' — together, full swing |

**DPL overcomes CPL's $V_{th}$ loss** because:
- NMOS effectively pulls down to full GND
- PMOS effectively pulls up to full $V_{DD}$
- Both logic levels are transmitted without voltage degradation
- Signal integrity and circuit performance are improved

### 4. Dual-Rail Domino Logic

Dual-rail Domino uses **two complementary dynamic logic blocks** to generate both XOR and XNOR outputs simultaneously.

![[xor_dual_rail_domino.png]]

| Property | Value |
|----------|-------|
| **Inputs required** | Dual-rail (both $A$ and $\bar{A}$, $B$ and $\bar{B}$) |
| **Clock required** | Yes (precharge + evaluate) |
| **Outputs** | Complementary (XOR and XNOR) |
| **Speed** | Very high |
| **Power** | Higher (clock overhead + precharge) |

**Key point**: For XOR/XNOR gates, **dual-rail inputs are required** because the function is symmetric in both true and complement inputs.

### 5. Single-Rail Domino Logic

For simpler functions like NOR, single-rail Domino can be used:

![[nor_single_rail_domino.png]]

| Property | Value |
|----------|-------|
| **Inputs required** | Single-rail (only $A$ and $B$) |
| **Functions** | NAND, NOR, AND, OR (not XOR) |
| **Advantage** | Reduced circuit complexity vs dual-rail |
| **Power** | Lower than dual-rail (fewer transistors, simpler routing) |

**Key point**: Other functions (NAND, NOR) can be implemented with single-rail inputs, reducing complexity and power.

---

## Comprehensive Comparison of Logic Styles

| Parameter | Full Static CMOS | CPL | DPL | Dual-Rail Domino | Single-Rail Domino |
|-----------|-----------------|-----|-----|------------------|-------------------|
| **Type** | Static | Static | Static | Dynamic | Dynamic |
| **Clock?** | No | No | No | Yes | Yes |
| **Transistors** | Highest (12) | Lowest (~6) | Moderate | Moderate | Low |
| **Voltage swing** | Full | Degraded ($V_{DD}-V_{th}$) | Full | Full | Full |
| **Static power** | Zero | Higher (short-circuit) | Zero | Zero | Zero |
| **Speed** | Moderate | Fast | Fast | Very fast | Fast |
| **Suitable for XOR?** | Yes | Yes (with fix) | Yes | Yes (dual-rail needed) | No (NOR, NAND only) |
| **Area** | Large | Small | Medium | Medium | Small |
| **Series sizing needed?** | Yes | No | No | No | No |

---

## Common Mistakes

1. **Forgetting CPL's voltage drop**: CPL without pMOS feedback has $V_{OH} = V_{DD} - V_{th}$
2. **Thinking DPL = CPL**: DPL uses BOTH nMOS and pMOS pass transistors (CPL uses only nMOS)
3. **Assuming single-rail Domino works for XOR**: XOR requires dual-rail inputs in Domino
4. **Ignoring series resistance**: Series transistors increase R, requiring width scaling
5. **Confusing static vs dynamic advantages**: Static avoids clock overhead; Dynamic is faster

---

## Self-Check Questions

**Q1**: Why does the CPL XOR gate have a voltage drop problem?
> The nMOS pass transistor can only charge the output to $V_{DD} - V_{th}$ (weak '1'). This prevents full turn-off of downstream PMOS, causing short-circuit current.

**Q2**: How does DPL solve CPL's problem?
> DPL uses both nMOS and pMOS pass transistors. nMOS passes strong '0' while pMOS passes strong '1', together providing full rail-to-rail voltage swing.

**Q3**: Why do XOR/XNOR gates require dual-rail inputs in Domino logic?
> XOR is a non-trivial symmetric function that needs both true and complement inputs to be evaluated. Other functions like NOR only need single-rail inputs.

**Q4**: What happens when transistors are connected in series?
> Effective resistance increases ($R_{series} \approx k \times R_{single}$), reducing current drive. Transistor width must be increased by factor $k$ to compensate.

**Q5**: Which logic style has the lowest transistor count for XOR?
> CPL (~6 transistors), compared to 12 for full static CMOS.

---

## Concept Links

- Pass transistor logic fundamentals: [04_pass_transistor_logic_and_dcvsl.md](./04_pass_transistor_logic_and_dcvsl.md)
- Dynamic logic and Domino fundamentals: [05_dynamic_logic_and_domino.md](./05_dynamic_logic_and_domino.md)
- Adder architectures that use these XOR gates: [09_standard_adder_cells_and_rca.md](./09_standard_adder_cells_and_rca.md)
- Deep submicrometer trends: [08_deep_submicrometer_design_issues.md](./08_deep_submicrometer_design_issues.md)
- Technology scaling and power: [14_formula_sheet_ultimate.md](./14_formula_sheet_ultimate.md)
