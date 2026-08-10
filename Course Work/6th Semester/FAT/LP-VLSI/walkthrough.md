# Walkthrough: Verification Checklist (Module 5 Study Guide)

Use this after you copy any extracted figures into `study_guide/Images/` (or your vault attachment folder).

## Page / slide coverage

| Slide range (approx.) | Topic file |
|-------------------------|------------|
| 1–7 | [01](./01_introduction_ram.md) |
| 8–22 | [02](./02_sram_architecture_and_cell.md) |
| 23–30 | [03](./03_precharge_and_equalization.md) |
| 31–37 | [04](./04_low_power_sram.md) |
| 38–50 | [05](./05_dram_basics.md) |
| 51–62 | [06](./06_self_refresh_and_related.md) |
| 63 | [06](./06_self_refresh_and_related.md) (SDRAM/DDR) |
| 64–68 | [07](./07_rom_basics.md) |
| 69–77 | [08](./08_low_power_rom_techniques.md) |
| 78–83 | [09](./09_future_trends.md) |

## Problem coverage

- [x] Source `problems.json` empty → **synthetic** items in [10_worked_problems.md](./10_worked_problems.md) cover major types (read polarity, precharge, DRAM restore, self-refresh timing, ROM addressing, power scaling, DDR vs SDRAM).

## Formula coverage

- [x] Dynamic power, threshold drop, half-$V_{\text{DD}}$ precharge, addressing math, timing constants → [11_formula_sheet_ultimate.md](./11_formula_sheet_ultimate.md)

## Link integrity (manual)

In Obsidian, click links between:

- [00 roadmap](./00_roadmap.md) → each `0x_*.md`
- Topic “Concept links” sections → neighbors and formula sheet

Relative paths assume all files stay in the same `study_guide/` folder.

## Consistency checks

- [ ] **BL / $\overline{\text{BL}}$** notation uniform (Obsidian math supported).
- [ ] **Destructive read** only assigned to **DRAM** in this module set.
- [ ] **Self-refresh** diagrams: **>100 µs RAS low** called out where relevant.

## Figures

- [ ] Ensure wiki-linked PNG/JPG names match your extracted asset filenames (`LPVLSI_MODULE-5_s*.png`).

## Known gaps

- Read/write **timing diagram** slides (12–13) are mostly figure-only in extraction; rely on `![[LPVLSI_MODULE-5_s14_img1.png]]` and lecture notes for axis labels.
