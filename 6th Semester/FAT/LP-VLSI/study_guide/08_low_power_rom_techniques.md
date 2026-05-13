# 08 Low-Power ROM Techniques

## Learning objectives

- Identify **dominant capacitances**: predecoder lines, **word-lines**, **bit-lines**.
- Compare **NAND** vs **NOR** ROM array organization (series vs parallel pull-downs); know **NOR faster**, **NAND** alternative.
- Explain **why bit-line precharge dominates** dynamic power in cited 2K×18 @ 3.3 V, 0.6 µm, 10 MHz example.
- Describe **hierarchical divided word-line**: global/local WL, **activate only selected subblock**.
- Explain **selective precharge** vs full precharge every cycle.
- Describe **difference encoding** (store deltas, reconstruct with adder; offset variants).
- Explain **nMOS precharge** for **reduced voltage swing** $V_{\text{DD}}-V_T$ to GND and side effects.
- State **voltage scaling**: dynamic power **quadratic** in $V_{\text{DD}}$, **delay** worsens.

## Ground-up explanation

### ROM power picture

ROM contains **memory bank (core)** of transistors in **matrix**. Two organizations:

- **NAND array** – pull-down devices in **series** (slower access in comparison narrative).
- **NOR array** – pull-downs in **parallel** → **faster access**, more common per slides.

**Decoder** selects **one WL** at a time. **Column mux + drivers** route selected column data to bus. **Control** generates **precharge** and **read** timing.

### Where power goes

Large **capacitive** nets: **predecoder**, **WL**, **BL**. Example ROM: **2K × 18**, 0.6 µm, **3.3 V**, **10 MHz** – **largest share** from **bit-line precharge** in core.

**Why BL precharge hurts:** BLs connect to **many** drain nodes; each access can **switch multiple BLs** (e.g. **12:1 mux** example → more BL activity than minimally necessary → **at least ~4 extra BLs** toggling in the slide story).

### Architecture-level low power

**(i) Divided hierarchical word-line**

- Partition core into **subblocks**.
- **Global vs local WL** on different metal layers.
- **Only selected subblock** active during read → less switching.

**Layout note:** still need **minimum mux ratio** (e.g. **4:1**), so **some** multi-BL switching remains.

**(ii) Selective precharge**

- Classic: **all** BLs precharged high each cycle → many lines discharge even if unused.
- **Selective:** precharge **only** BLs needed for current read → big **dynamic power** cut; **low overhead** (reuse control logic).

**(iii) Difference encoding**

- For **sequential** access patterns (e.g. **digital filters**), adjacent ROM words often **similar**.
- Store **differences** between consecutive values instead of full words → **smaller core**.
- Need **adder** to reconstruct → **hardware complexity** trade-off.
- Variant: differences vs **fixed offsets**.

### Circuit-level low power

**(i) nMOS precharge (swing reduction)**

- BL high level limited to about **$V_{\text{DD}} - V_T$**; swing between that and **0**.
- **Lowers** $CV^2f$ contribution vs full-rail swing.
- **Downsides:** worse **noise margin**, **body effect** raises $V_T$, need careful **output driver** design.

**(ii) Voltage scaling**

$$\boxed{P_{\text{dyn}} \propto C V_{\text{DD}}^2 f}$$

Lowering $V_{\text{DD}}$ gives **roughly quadratic** dynamic power reduction; **short-circuit power** also drops with supply (slides mention modeling). **Cost:** **delay increases**, **speed drops**.

## Analogies

- **Selective precharge** is like lighting only the hallway lamps you walk past, not every corridor in the building.
- **Difference encoding** is like storing the delta between frames in video instead of full frames every time.

## Figures

- `![[LPVLSI_MODULE-5_s71_img1.png]]`
- `![[LPVLSI_MODULE-5_s77_img1.png]]`

## Common mistakes

- Claiming **hierarchical WL** eliminates **all** unused bit-line toggles (slides: **minimum mux ratio** still forces some multi-line activity).
- Saying voltage scaling is “free” power reduction with **no** performance penalty.

## Self-check

1. NOR vs NAND ROM: which favors speed in this module?

<details>
<summary>Answer</summary>

**NOR** ROM described as **higher access speed** vs NAND.

</details>

## Concept links

- [ROM basics](./07_rom_basics.md)
- [Precharge concepts](./03_precharge_and_equalization.md)
- [Formula sheet](./11_formula_sheet_ultimate.md)
