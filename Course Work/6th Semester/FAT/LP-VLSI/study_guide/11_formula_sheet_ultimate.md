# 11 Formula Sheet (Module 5: Low-Voltage Low-Power Memories)

## Physical constants (generic CMOS course values)

Use **your instructor’s** sheet if different.

| Constant | Symbol | Typical value |
|----------|--------|----------------|
| Elementary charge | $q$ | $1.602 \times 10^{-19}\ \text{C}$ |
| Thermal voltage @300 K | $\phi_t = kT/q$ | $\approx 25.9\ \text{mV}$ |

## Unit conversion reference

| Quantity | Conversion |
|----------|------------|
| Time | $1\ \mu\text{s} = 10^{-6}\ \text{s}$ |
| Frequency | $f = 1/T$ |
| Power | $1\ \text{mW} = 10^{-3}\ \text{W}$ |

## Dynamic power scaling {#dynamic-power-scaling}

Switching (activity-aware form):

$$P_{\text{dyn}} = \alpha\, C\, V_{\text{DD}}^2\, f$$

| Symbol | Meaning | Units |
|--------|---------|-------|
| $\alpha$ | Activity factor (0–1) | dimensionless |
| $C$ | Switched capacitance | F |
| $V_{\text{DD}}$ | Supply voltage | V |
| $f$ | Clock / toggle rate | Hz |

$$\boxed{P_{\text{dyn}} \propto V_{\text{DD}}^2}$$

**ROM / bit-line intuition:** precharging many **high-capacitance** BLs each cycle → large effective $C$.

## Threshold and swing {#threshold-and-swing}

nMOS device passing a **weak high** (first-order):

$$\boxed{V_{\text{high,max}} \approx V_{\text{DD}} - V_{Tn}}$$

Reduced swing read-high precharge top level:

$$\boxed{\Delta V_{\text{swing}} = V_{\text{high}} - 0}$$

Smaller swing → lower $CV^2f$ but **noise margin** trade-off (ROM nMOS precharge section).

## DRAM read precondition

Half-supply precharge (from slides):

$$\boxed{V_{\text{BL,pre}} = \frac{V_{\text{DD}}}{2}}$$

## Addressing arithmetic

$$N_{\text{words}} = 2^{n_{\text{addr}}}$$

**1 Kbit example:** $256 = 2^8$ words → **8** address bits; **5** row bits → $2^5 = 32$ WLs.

## Timing numbers quoted in deck (memorize as given)

| Item | Value |
|------|-------|
| Example DRAM write cycle | $\approx 75\ \text{ns}$ |
| Example DRAM read cycle | $\approx 65\ \text{ns}$ |
| Self-refresh RAS low hold (figure commentary) | $> 100\ \mu\text{s}$ |
| All-row refresh window ballpark | $5\text{–}10\ \text{ms}$ (datasheet final) |

## Duty-ratio pulse power intuition

$$\boxed{P \propto \frac{t_{\text{on}}}{T_{\text{cycle}}}}$$

Applies to **pulsed WL** / pulsed sense enable arguments.

## Sign and level conventions (module)

| Signal / condition | Meaning in this deck |
|--------------------|----------------------|
| **WL high** | Access transistors ON (SRAM/DRAM) |
| **WL low** | Array isolated during BL precharge (SRAM) |
| **RAS / CAS low** | Strobe active (DRAM timing figures) |
| **R/W high** | Read |
| **R/W low** | Write |
| **OE low** | Output buffer enabled on read path example |

## Quick decision table: SRAM vs DRAM (exam)

| Property | SRAM | DRAM |
|----------|------|------|
| Cell | 6T latch | 1T1C |
| Refresh | No | Yes |
| Read | Non-destructive | Destructive (restore) |
| Bit lines | Differential pair common | Single BL emphasized |
| Density | Lower | Higher |

## Pre-submission checklist

- [ ] Stated whether answer is **SRAM** or **DRAM** when both plausible  
- [ ] Mentioned **refresh** if charge storage  
- [ ] Included **sense amplifier** role if small $\Delta V$ read  
- [ ] RAS/CAS/OE/R/W levels consistent for read vs write  
- [ ] Units on power/time calculations  

## Concept index

- SRAM control: [02](./02_sram_architecture_and_cell.md)  
- Precharge: [03](./03_precharge_and_equalization.md)  
- Low-power SRAM: [04](./04_low_power_sram.md)  
- DRAM: [05](./05_dram_basics.md)  
- Self-refresh: [06](./06_self_refresh_and_related.md)  
- ROM: [07](./07_rom_basics.md), [08](./08_low_power_rom_techniques.md)  
