# 02 SRAM Architecture and the 6T Memory Cell

## Learning objectives

- Draw or describe the **SRAM chip blocks**: address buffers, row/column decoders, **memory array**, **sense amplifiers**, I/O buffers, **precharge** circuits.
- Explain **word line (WL)**, **bit line (BL)** and **complementary bit line** (often written $\overline{\text{BL}}$ or BL bar).
- Describe **read** and **write** for the **6T cell** and the role of **cross-coupled inverters** and **access NMOS** devices (MN3, MN4 in slides).
- State control signals **WE**, **CS**, **OE** and what access time means.

## Ground-up explanation

### Chip-level picture

An **SRAM** integrates:

1. **Address buffers** – condition external address signals.
2. **Row decoder** – selects one **word line (WL)**.
3. **Column decoder** – selects the correct **bit-line pair** path to the array column.
4. **Memory array** – grid of cells; each cell sits at the intersection of a WL and a **differential bit-line pair** (BL / $\overline{\text{BL}}$).
5. **Sense amplifiers** – turn a **small differential voltage** on BL/$\overline{\text{BL}}$ into full logic levels quickly.
6. **I/O buffers** – interface to the outside world.
7. **Precharge circuits** – set BL/$\overline{\text{BL}}$ to a known level before read (see [03](./03_precharge_and_equalization.md)).

**Selected cell** = intersection of activated WL and the selected bit-line column.

### Read operation (conceptual)

1. Bit lines are **precharged** (often to the same voltage, commonly $V_{\text{DD}}$ in the slide narrative).
2. **WL = 1** turns on access transistors; the cell **disturbs** the balanced precharge slightly.
3. Example from slides: if internal node **A = 1**, **B = 0**, then **BL** tends to discharge slightly while **$\overline{\text{BL}}$** stays higher → small **$\Delta V$**.
4. **Sense amplifier** resolves **BL vs $\overline{\text{BL}}$** to a full logic output (e.g. BL > $\overline{\text{BL}}$ → read as logic 1).

### Write operation

Force strong levels on BL / $\overline{\text{BL}}$ (example for writing **1**: BL high, $\overline{\text{BL}}$ low). Assert **WL**. Access devices conduct; the internal latch nodes are **overwritten**. **Positive feedback** of the cross-coupled inverters snaps the cell to the new stable state.

### 6T cell structure

- **Two cross-coupled inverters** – bistable storage at nodes A and B (one bit and its complement).
- **Two access NMOS** – connect the cell to BL / $\overline{\text{BL}}$ when WL is high.
- **WL** – shared along a row; enables access for all cells on that row (column muxing selects the column of interest at chip level).

### Access time

**Access time** = delay from **address valid** to **data valid at output**, dominated by **row decode / WL delay** and **bit-line sensing**.

### Control signals

| Signal | Role (from slides) |
|--------|-------------------|
| **WE** (Write Enable) | Chooses **read vs write** path behavior |
| **CS** (Chip Select) | Selects this chip among multiple chips |
| **OE** (Output Enable) | Enables output buffer during **read** |

Small voltage swings on bit lines + sensitive sense amps → **faster** sensing (trade: careful noise and timing design).

## Analogies

- **Cross-coupled inverters** are like a ball in a two-well valley: small push (write forces on BL) makes it roll to the other stable minimum.
- **Sense amplifier** is like reading a barely tilted balance scale with a magnifying glass.

## Key figures (Obsidian)

Place extracted figures in `study_guide/Images/` or your vault attachments folder so these resolve:

- `![[LPVLSI_MODULE-5_s11_img1.png]]` – typical SRAM architecture (slide 11).
- `![[LPVLSI_MODULE-5_s14_img1.png]]` – read/write timing (slide 14).
- `![[LPVLSI_MODULE-5_s15_img1.png]]` – 6T CMOS cell (slide 15).

## Advantages and disadvantages (exam bullets)

**Advantages:** high speed; **no refresh**; lower dynamic power than DRAM-style refresh overhead; good noise margin in 6T; **simple control** (no refresh controller); **cache** (L1/L2/L3) application.

**Disadvantages:** **low density** (6T vs 1T1C); **high cost per bit**; **higher static/leakage** concern; volatile; **harder scaling** vs 1T1C; not for huge main memory capacity.

## Common mistakes

- Saying SRAM has **one** bit line like DRAM (SRAM differential pair is standard here).
- Ignoring that **read** is **non-destructive** in SRAM (contrast DRAM in [05](./05_dram_basics.md)).
- Forgetting **OE** only matters on **read** output path.

## Self-check

1. Why are BL and $\overline{\text{BL}}$ precharged to the same level before read?

<details>
<summary>Answer</summary>

So the sense amplifier starts from a known, balanced condition; the cell then creates a **small intentional imbalance** that indicates stored data.

</details>

2. What provides positive feedback during write?

<details>
<summary>Answer</summary>

The **cross-coupled inverter pair** (latch) regenerates full rail levels once BL/$\overline{\text{BL}}$ overwrite the internal nodes.

</details>

## Concept links

- [Precharge and equalization](./03_precharge_and_equalization.md)
- [Low-power SRAM](./04_low_power_sram.md)
- [Formula sheet](./11_formula_sheet_ultimate.md)
