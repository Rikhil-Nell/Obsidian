# 09 Future Trends: EPROM, EEPROM, Flash, MLC

## Learning objectives

- Compare **EPROM**, **EEPROM**, **Flash** for density, flexibility, cost, typical applications.
- Describe **Flash+** integrating Flash + EEPROM on one die and why (code vs parametric data).
- Explain market shift: **DRAM historically dominant** vs **Flash focus** (cost/bit, cell size, process).
- Summarize **MLC / multi-level** flash: multiple **charge levels** per cell → **2+ bits/cell** vs classical single-bit DRAM/EPROM/EEPROM style storage.

## Ground-up explanation

### EPROM / EEPROM / Flash positioning (slides)

- **UV-EPROM:** high density, **field alterable**, but **higher cost** and **less flexibility** in some usage dimensions.
- **EEPROM:** preferred when **density < ~256 K** and **flexibility** is key.
- **Flash:** **high density**, **low cost**, **strong system flexibility** → **dominant** in many memory markets.

### Technology scaling snippet

Rough statement in slides: process feature size **~12% reduction per year** over long periods (qualitative roadmap talking point, not a precise exam calculation unless instructor says so).

### Flash+ concept

**Single chip** combines **Flash** and **EEPROM**:

- **Flash** good for **microcontroller code** with **dynamic updates**.
- **EEPROM** good for **parametric** data needing **byte-level** changes.
- Together: **flexibility** + **efficient handling** of both program storage and small-parameter storage.

### DRAM vs Flash economics

Earlier **DRAM** led volume memory markets; slides argue **Flash** now primary focus due to **lowest cost/bit**, **smaller cell**, **simpler manufacturing** in comparative narrative.

### Wireless drivers

Phones, cameras, handhelds → demand for **flash** capacity and technology push.

### Multi-level cells (MLC)

Traditional DRAM / EPROM / EEPROM / early flash: **one bit per cell** (binary charge amount interpretation).

**Modern flash:** **multiple distinct charge levels** on a cell → **multiple bits per cell** (2+), **higher density**, **lower cost per bit**, more **sensing complexity** (not fully expanded in deck).

## Analogies

- **MLC** is like parking sensors that must distinguish not just “occupied vs empty” but **which of several distance bands**—more information, finer measurement.

## Key formulas

No fixed boxed equations in trend slides; qualitative scaling:

$$\boxed{\text{Cost per bit} \downarrow \quad \text{as density} \uparrow}$$

## Common mistakes

- Saying **self-refresh** applies to **Flash** (this deck treats refresh as **DRAM** context; flash retention is different physics).
- Confusing **EEPROM byte erase/program** flexibility with **Flash** block erase typical trade (course may add nuance beyond these slides).

## Self-check

1. Why include EEPROM alongside Flash on a **Flash+** die?

<details>
<summary>Answer</summary>

**Byte-level** or flexible small-parameter updates suit **EEPROM**, while **bulk code storage** suits **Flash**; combined chip serves both needs.

</details>

## Concept links

- [DRAM](./05_dram_basics.md)
- [Self-refresh](./06_self_refresh_and_related.md)
- [ROM power](./08_low_power_rom_techniques.md)
