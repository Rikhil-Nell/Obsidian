# 05 DRAM Basics (1T1C Cell)

## Learning objectives

- Describe the **1T1C** DRAM cell: access **nMOS**, **storage capacitor** $C_S$, **bit-line capacitance** $C_{\text{BL}}$ (includes parasitic $C_p$).
- Contrast **single-ended** bit line (DRAM) with **differential pair** (SRAM).
- Explain **precharge to half-$V_{\text{DD}}$** before read, WL high to turn on access device.
- Distinguish read **“1”** vs **“0”** small-signal behavior and **charge sharing** language.
- Outline **write 1** and **write 0** at bit-line and capacitor level.
- Narrate **RAS/CAS** style timing for **write** and **read** including **OE** and **R/W**.
- List DRAM **advantages** and **disadvantages** including **destructive read** and **refresh**.

## Ground-up explanation

### Cell structure

One **access nMOS** + one **storage capacitor** $C_S$. **WL** runs perpendicular to **BL** (single bit line per column in this treatment).

- **$C_S$** stores **charge** representing the bit.
- **$C_{\text{BL}}$** is bit-line parasitic capacitance (slides use $C_{\text{BL}}$ / $C_p$ naming in places).

Leakage → **periodic refresh** mandatory.

### Read precondition

**$C_{\text{BL}}$ precharged to $\frac{V_{\text{DD}}}{2}$** (logic “half-$V_{\text{DD}}$” region). Then **WL high** turns on access transistor.

### Read “1”

When cell stores ideal **1**, connecting $C_S$ to BL tends to **raise** BL voltage slightly (small positive deviation from precharged mid-rail). Real cells: charge may be **less than ideal** due to leakage.

### Read “0”

**Charge sharing** between $C_{\text{BL}}$ and $C_S$: as BL discharges slightly and $C_S$ charges, **BL voltage dips slightly** → interpreted as stored **0**.

### Write

Data presented on I/O, routed by **column decoder** to selected **BL**. For selected cell, BL forced **high** or **low**.

**Write 1:** BL charged toward **1** ($V_{\text{DD}}$ or $V_{\text{DD}}-V_T$ depending on write circuitry). WL on → **$C_S$ charges** toward logic 1.

**Write 0:** BL pulled to **0**; WL on → **$C_S$ discharges** if it held 1, or remains 0.

### Timing (representative numbers from slides)

**Write** sequence (conceptual phases):

1. Row address + **RAS** low → row selected  
2. Column address + **CAS** low → column selected  
3. **R/W low** → write  
4. Data valid; capacitor **charged = 1**, **discharged = 0**  
5. RAS/CAS return high; **~75 ns** total write time (example in deck)

**Read** sequence:

1. RAS low → **row** into sense amplifiers  
2. CAS low → **column**  
3. **OE low** enables output (data bus was **Hi-Z**)  
4. Sense amp **detects small charge**, **amplifies and restores** (refresh during read)  
5. Signals high; **~65 ns** example

Key signals:

| Signal | Read | Write |
|--------|------|-------|
| RAS | Row strobe (active low) | Row strobe |
| CAS | Column strobe | Column strobe |
| R/W | **HIGH** read | **LOW** write |
| OE | Enable output buffer | (context-dependent / inactive for output) |

### Advantages / disadvantages (exam)

**Pros:** **high density**, **low cost per bit**, **large capacity** for main memory, **simple 1T1C cell**.

**Cons:** **refresh** required, **slower** than SRAM, **refresh adds power**, **destructive read** (must rewrite), **lower noise margin**, **complex periphery** (sense amps, refresh control).

## Analogies

- **$C_S$** is a leaky bucket; **refresh** is periodically refilling before it drops below a readable level.
- **Destructive read** is like measuring ink by almost wiping it—you must rewrite after sensing.

## Key formulas

Half-$V_{\text{DD}}$ precharge (starting line for read):

$$\boxed{V_{\text{BL,pre}} = \frac{V_{\text{DD}}}{2}}$$

Write high level through nMOS access (when relevant):

$$\boxed{V_{\text{stored}} \le V_{\text{DD}} - V_T \quad \text{(simplified nMOS pass perspective)}}$$

## Figures

- `![[LPVLSI_MODULE-5_s40_img1.png]]` – 1T cell schematic.

## Common mistakes

- Calling DRAM read **non-destructive** (wrong for this course: **restore** required).
- Forgetting **OE** for read output enable path.
- Mixing **SRAM differential** read with **DRAM single-ended** small swing.

## Self-check

1. Why is refresh mandatory?

<details>
<summary>Answer</summary>

**Leakage** removes stored charge from $C_S$; without periodic restoration, voltage crosses uncertain region.

</details>

2. What does “destructive read” imply for the controller?

<details>
<summary>Answer</summary>

After a read, the **original charge** must be **written back** (sense amp restore / rewrite) so data remains valid.

</details>

## Concept links

- [Self-refresh](./06_self_refresh_and_related.md)
- [SRAM comparison](./02_sram_architecture_and_cell.md)
- [Formula sheet](./11_formula_sheet_ultimate.md)
