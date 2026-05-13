# Study Guide Implementation Plan

## Source materials

| Source | Location | Pages/slides |
|--------|----------|----------------|
| LPVLSI_MODULE-5.pptx | `Materials/` (extracted to `extracted_content/`) | 83 |

## Topics identified

1. Introduction to RAM (volatile, random access, power context)
2. SRAM architecture, timing, 6T cell, read/write mechanism
3. Precharge and equalization (nMOS vs pMOS, WL timing)
4. Low-power SRAM (active vs standby, DWL, pulse WL/column, ATD, voltage reduction, retention)
5. DRAM 1T1C, read/write, RAS/CAS timing, pros/cons
6. Self-refresh (entry/exit, RAS hold, vs deep power-down), SDRAM/DDR note
7. ROM architecture, 1 Kbit organization example
8. Low-power ROM (NAND vs NOR, hierarchical WL, selective precharge, difference encoding, nMOS precharge, voltage scaling)
9. Future trends (EPROM/EEPROM/Flash, MLC, roadmap)

## Proposed output structure

- `study_guide/00_roadmap.md`
- `study_guide/01_introduction_ram.md` through `09_future_memory_trends.md`
- `study_guide/10_worked_problems.md` (exam-style; source had no extracted examples)
- `study_guide/11_formula_sheet_ultimate.md`
- `study_guide/Images/` (place extracted figures here for wiki-links; names match `structured.md`)

## Verification

- Cross-check slide themes against each topic file
- Link integrity for relative `./` links
- Formula sheet aligned with module text (dynamic power scaling, threshold drop, etc.)

*Fast-track note: Phase 0 approval skipped per user request; guide generated directly from `extracted_content/structured.md` and related JSON.*
