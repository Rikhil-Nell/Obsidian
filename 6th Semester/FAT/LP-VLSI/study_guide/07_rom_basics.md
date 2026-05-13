# 07 ROM Basics and Organization

## Learning objectives

- Describe **ROM core** as **matrix** of cells with **row** and **column** decoders selecting **one of $2^n$ words** from **$n$-bit** address.
- Explain **precharge before read**, **sense amplifiers**, **column decoder before sensing** (some designs), **OE** and **CS** for expansion.
- Reproduce the **1 Kbit** example: **256 words × 4 bits**, **8-bit address**, split **5 row + 3 column**, **32 word lines**, **32×32** array, **four 8-to-1** column muxes → **D0–D3**.

## Ground-up explanation

### Functional blocks

1. **Cell array** – fixed pattern (mask ROM) or field-programmed depending on technology (not all details in this deck).
2. **Row decoder** – asserts **one WL** for given row address bits.
3. **Column decoder / mux** – picks the **bit columns** that form the output word.
4. **Conditioning / precharge** – bit lines precharged for fast, reliable read.
5. **Sense amplifiers** – small differential or single-ended swing to logic levels.
6. **Output buffers** – enabled by **OE**; **CS** helps **multi-chip** systems widen word width or depth.

### 1 Kbit ROM worked structure (from slides)

- **Total bits:** $1024$
- **Organization:** $256$ **words** × **4 bits/word**
- **Address width:** $\log_2(256) = 8$ bits

**Address partition:**

- **5** bits → **row** → $2^5 = 32$ **word lines**
- **3** bits → **column** side → supports selecting among 8 columns per bit lane context in the narrative

**Array:** conceptual **32 × 32** matrix of cells.

**Column side:** **four** separate **8-to-1** multiplexers (one per output bit position) select the proper column out of eight groups, yielding **4-bit** output **D0–D3**.

**Output path:** selected nibble passes through **buffers** enabled by **CS** (and timing with **OE** as applicable).

## Analogies

- **Row/column decode** is like picking a shelf (row) then a book position on that shelf (column set).

## Key formulas

$$\boxed{1024\ \text{bits} = 256 \times 4}$$

$$\boxed{8\text{-bit address} = 5\text{-bit row} + 3\text{-bit column (in this example)}}$$

$$\boxed{2^5 = 32\ \text{word lines}}$$

## Figures

- `![[LPVLSI_MODULE-5_s66_img1.png]]` – basic ROM architecture.
- `![[LPVLSI_MODULE-5_s68_img1.png]]` – 1024-bit ROM architecture.

## Common mistakes

- Forgetting **four** 8-to-1 muxes align with **4 output bits**, not one mux for all.

## Self-check

Compute number of row bits if a 4096-bit ROM is 512 words × 8 bits.

<details>
<summary>Answer</summary>

512 words → **9** address bits total ($2^9=512$). Row/column split not fully specified without more structure; if you keep similar style, you must follow problem statement. Here: **9 bits total**, **8 bits** for word width path uses different partition—**only the slide’s 256×4 case is exam-given**.

</details>

## Concept links

- [Low-power ROM](./08_low_power_rom_techniques.md)
- [Formula sheet](./11_formula_sheet_ultimate.md)
