>[!info] **Name**: Rikhil Nellimarla
>**Registration** Number: 23BEC7030
>**Course Name**: CMOS VLSI
>**Slot**: L49 + L50

# CMOS Inverter

-> **Objective**

To design, simulate, and physically verify a CMOS inverter using `gpdk180` technology in Cadence Virtuoso.

-> **Apparatus**

- Cadence Virtuoso (Schematic Editor, Layout XL, ADE L)
- `gpdk180` PDK library
- Assura (DRC/LVS)
- Quantus QRC / Assura extraction flow (for `av_extracted` view)

-> **Theory**

A CMOS inverter is the basic logic gate formed by one PMOS pull-up and one NMOS pull-down transistor.

- Logic equation: `Vout = A'`
- Transistor operation: when `A = 0`, PMOS ON and NMOS OFF so output goes HIGH; when `A = 1`, PMOS OFF and NMOS ON so output goes LOW.

Truth table:

| A | Vout |
|---|------|
| 0 | 1 |
| 1 | 0 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/INV/Inverter_Schmt.png]]

### Symbol
![[Actual Lab Work/INV/Inverter_Sym.png]]

### Simulation
![[Actual Lab Work/INV/Inverter_Sim.png]]

### Transient Analysis
![[Actual Lab Work/INV/INV_Wave.png]]

### Layout
![[Actual Lab Work/INV/Inverter_Layout.png]]

### DRC
![[Actual Lab Work/INV/Inverter_DRC.png]]

### LVS
![[Actual Lab Work/INV/Inverter_LVS.png]]

### AV Extracted
![[Actual Lab Work/INV/Inverter_Extr.png]]

-> **Steps to perform the experiment**

1. Open `Library Manager` -> `File` -> `New` -> `Library`, attach to `gpdk180`.
2. Create schematic: `File` -> `New` -> `Cell View` (`view = schematic`).
3. Place transistors: `Add` -> `Instance` (`pmos4` and `nmos4` from `gpdk180`), connect with `Add` -> `Wire`.
4. Add pins with `Add` -> `Pin`: `A`, `VDD`, `GND`, `VOUT`.
5. Run `Design` -> `Check and Save`.
6. Generate symbol: `Create` -> `Cellview` -> `From Cellview` and save symbol.
7. Create testbench cell (`schematic`) and place inverter symbol.
8. Add sources from `analogLib`: `vdc` for `VDD`, `vpulse` for input, and `gnd!` for ground.
9. Launch simulation: `Launch` -> `ADE L`.
10. Set transient analysis: `Analyses` -> `Choose` -> `tran`, stop time around `100n`, accuracy preset `moderate`.
11. Select outputs: `Outputs` -> `To Be Plotted` -> `Select on Schematic` (`A`, `VOUT`), then run with `Simulation` -> `Netlist and Run`.
12. Create layout: `File` -> `New` -> `Cell View` (`view = layout`), place devices, routes, contacts, and pins.
13. Import connectivity: `Connectivity` -> `Generate` -> `All From Source`, complete metal routing for all nets.
14. Run DRC: `Assura` -> `Run DRC` and fix all errors.
15. Run LVS: `Assura` -> `Run LVS` to verify layout netlist matches schematic netlist.
16. Run extraction (AV extracted): open extraction setup from Assura/Quantus flow, generate `av_extracted` (or `extracted`) view.
17. Re-simulate using extracted view by updating view list in ADE (`av_extracted` before `schematic`) to include parasitic effects.

-> **Result/Conclusion**

CMOS inverter was successfully designed in schematic and layout, passed DRC/LVS, and produced correct inverted transient response. Post-layout extracted simulation validated practical behavior including parasitic loading.

# CMOS NAND

-> **Objective**

To design and verify a 2-input CMOS NAND gate in `gpdk180` using schematic, simulation, layout, DRC, LVS, and extracted view.

-> **Apparatus**

- Cadence Virtuoso + ADE L
- `gpdk180` library
- Assura DRC/LVS
- Quantus/Assura extraction for `av_extracted`

-> **Theory**

2-input CMOS NAND uses PMOS in parallel (pull-up) and NMOS in series (pull-down).

- Equation: `Vout = (A.B)'`
- Pin set used: `A`, `B`, `VDD`, `GND`, `VOUT`

Truth table:

| A | B | Vout |
|---|---|------|
| 0 | 0 | 1 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/NAND/Nand_Schmt.png]]

### Symbol
![[Actual Lab Work/NAND/Nand_sym.png]]

### Simulation
![[Actual Lab Work/NAND/Nand_sim.png]]

### Transient Analysis
![[Actual Lab Work/NAND/Nand_Wave.png]]

### Layout
![[Actual Lab Work/NAND/Nand_layout.png]]

### DRC
![[Actual Lab Work/NAND/Nand_DRC.png]]

### LVS
![[Actual Lab Work/NAND/Nand_LVS.png]]

### AV Extracted
![[Actual Lab Work/NAND/Nand_Extr.png]]

-> **Steps to perform the experiment**

1. Create NAND schematic in Cadence using `pmos4` and `nmos4` from `gpdk180`.
2. Make PMOS network parallel and NMOS network series as per NAND logic.
3. Add pins `A`, `B`, `VDD`, `GND`, `VOUT`, then `Check and Save`.
4. Create symbol using `Create` -> `Cellview` -> `From Cellview`.
5. Build testbench cell and instantiate NAND symbol.
6. Connect `vdc` (`VDD`), two `vpulse` sources for inputs `A` and `B`, and `gnd!`.
7. Open `ADE L`, choose `tran` analysis (stop time near `100n`, moderate accuracy), select outputs, and run.
8. Observe waveform and verify NAND truth behavior.
9. Build layout, generate source connectivity, and complete routing.
10. Run Assura DRC, clear all design rule errors.
11. Run Assura LVS and confirm netlist match.
12. Generate `av_extracted` view through extraction flow and run post-layout transient simulation.

-> **Result/Conclusion**

NAND gate output matched expected logic and the design was physically verified through clean DRC/LVS. Extracted simulation confirmed functionality after including parasitic effects.

# CMOS NOR

-> **Objective**

To implement a 2-input CMOS NOR gate and validate both pre-layout and post-layout performance.

-> **Apparatus**

- Cadence Virtuoso with `gpdk180`
- ADE L for transient simulation
- Assura for DRC and LVS
- Quantus/Assura extraction for `av_extracted`

-> **Theory**

2-input CMOS NOR uses PMOS in series (pull-up) and NMOS in parallel (pull-down).

- Equation: `Vout = (A + B)'`
- Pins: `A`, `B`, `VDD`, `GND`, `VOUT`

Truth table:

| A | B | Vout |
|---|---|------|
| 0 | 0 | 1 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 0 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/NOR/Nor_Schmt.png]]

### Symbol
![[Actual Lab Work/NOR/Nor_Sym.png]]

### Simulation
![[Actual Lab Work/NOR/Nor_Sim.png]]

### Transient Analysis
![[Actual Lab Work/NOR/Nor_Wave.png]]

### Layout
![[Actual Lab Work/NOR/Nor_Layout.png]]

### DRC
![[Actual Lab Work/NOR/Nor_DRC.png]]

### LVS
![[Actual Lab Work/NOR/Nor_LVS.png]]

### AV Extracted
![[Actual Lab Work/NOR/Nor_extr.png]]

-> **Steps to perform the experiment**

1. Create NOR schematic with correct PMOS series and NMOS parallel arrangement.
2. Add standard pins (`A`, `B`, `VDD`, `GND`, `VOUT`) and check/save.
3. Create symbol from schematic and use it in a new testbench cell.
4. Drive inputs `A` and `B` using `vpulse`, power with `vdc` and `gnd!`.
5. In `ADE L`, select transient analysis (`100n`, moderate), select plotted outputs, and run.
6. Validate waveform against NOR truth table.
7. Draw full layout, then use `Connectivity` -> `Generate` -> `All From Source`.
8. Run DRC and correct spacing/width/contact errors if present.
9. Run LVS to confirm extraction netlist matches schematic intent.
10. Generate extracted view and verify post-layout simulation.

-> **Result/Conclusion**

The NOR gate showed expected logical behavior in transient simulation. Layout verification was successful with DRC and LVS passing, and extracted simulation retained correct function.

# CMOS AND

-> **Objective**

To realize a CMOS AND function and verify operation through simulation and full physical verification flow.

-> **Apparatus**

- Cadence Virtuoso + ADE L
- `gpdk180` technology
- Assura DRC/LVS
- Extraction flow for post-layout (`av_extracted`)

-> **Theory**

CMOS AND is commonly implemented as NAND followed by an inverter.

- Equation: `Vout = A.B`
- Pins: `A`, `B`, `VDD`, `GND`, `VOUT`

Truth table:

| A | B | Vout |
|---|---|------|
| 0 | 0 | 0 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/AND/AND_Schmt.png]]

### Symbol
![[Actual Lab Work/AND/AND_Sym.png]]

### Simulation
![[Actual Lab Work/AND/AND_Sim.png]]

### Transient Analysis
![[Actual Lab Work/AND/And_Wave.png]]

### Layout
![[Actual Lab Work/AND/AND_Layout.png]]

### DRC
![[Actual Lab Work/AND/AND_DRC.png]]

### LVS
![[Actual Lab Work/AND/AND_LVS.png]]

### AV Extracted
![[Actual Lab Work/AND/AND_Extr.png]]

-> **Steps to perform the experiment**

1. Draw NAND stage and inverter stage schematic, then cascade both.
2. Add and verify pins `A`, `B`, `VDD`, `GND`, `VOUT`.
3. Generate symbol and create simulation testbench.
4. Connect `vdc`, two `vpulse` inputs, and ground.
5. Use `ADE L` transient (`100n`, moderate) and select input/output nets.
6. Run simulation and confirm output is HIGH only when both inputs are HIGH.
7. Complete layout implementation and generate connectivity from source.
8. Execute DRC, fix errors, then run LVS until match status is clean.
9. Perform extraction and run post-layout transient response.

-> **Result/Conclusion**

AND gate implementation was successful with correct truth behavior in waveform plots and successful DRC/LVS verification. Extracted response confirmed practical correctness after parasitics.

# CMOS OR

-> **Objective**

To design and validate a CMOS OR gate through schematic, simulation, layout checks, and extraction.

-> **Apparatus**

- Cadence Virtuoso and ADE L
- `gpdk180` device library
- Assura DRC/LVS
- Quantus/Assura extracted view generation

-> **Theory**

CMOS OR is commonly implemented as NOR followed by an inverter.

- Equation: `Vout = A + B`
- Pins: `A`, `B`, `VDD`, `GND`, `VOUT`

Truth table:

| A | B | Vout |
|---|---|------|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 1 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/OR/OR_Schmt.png]]

### Symbol
![[Nor_Sym.png]]

### Simulation
![[Nor_Sim.png]]

### Transient Analysis
![[Actual Lab Work/OR/Or_Wave.png]]

### Layout
![[Or_Layout.png]]

### DRC
![[Actual Lab Work/OR/Or_DRC.png]]

### LVS
![[Actual Lab Work/OR/Or_LVS.png]]

### AV Extracted
![[Actual Lab Work/OR/OR_Extr.png]]

-> **Steps to perform the experiment**

1. Draw OR logic (NOR + inverter realization) in schematic editor.
2. Add all required pins and perform check/save.
3. Create symbol from schematic and instantiate in a simulation cell.
4. Drive with `vpulse` inputs and power rails (`vdc`, `gnd!`).
5. Run transient analysis in `ADE L` for about `100n`, with moderate preset.
6. Select outputs and verify OR truth behavior from waveform.
7. Perform layout, generate connectivity, and complete all routes and pins.
8. Run DRC and fix violations.
9. Run LVS and ensure extracted layout matches schematic.
10. Generate extracted view and validate post-layout behavior.

-> **Result/Conclusion**

OR gate design produced expected output levels across all input combinations and passed physical verification flow. Post-layout extracted waveform remained logically correct.

# CMOS XOR

-> **Objective**

To design and verify a CMOS XOR gate and analyze its behavior before and after parasitic extraction.

-> **Apparatus**

- Cadence Virtuoso schematic/layout tools
- `gpdk180` PDK
- ADE L transient simulator
- Assura DRC/LVS and extraction flow

-> **Theory**

XOR output is HIGH only when inputs are different.

- Equation: `Vout = A'B + AB'`
- Pins: `A`, `B`, `VDD`, `GND`, `VOUT`

Truth table:

| A | B | Vout |
|---|---|------|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

-> **Lab Pictures**

### Schematic
![[Actual Lab Work/XOR/Xor_Schmt1.png]]

### Symbol
![[Actual Lab Work/XOR/Xor_Sym1.png]]

### Simulation
![[Actual Lab Work/XOR/Xor_Sim1.png]]

### Transient Analysis
![[Actual Lab Work/XOR/Xor_Wave.png]]

### Layout
![[Actual Lab Work/XOR/Xor_Layout1.png]]

### DRC
![[Actual Lab Work/XOR/Xor_DRC1.png]]

### LVS
![[Actual Lab Work/XOR/Xor_LVS1.png]]

### AV Extracted
![[Actual Lab Work/XOR/Xor_Extr.png]]

-> **Steps to perform the experiment**

1. Draw XOR transistor-level schematic in Virtuoso using `gpdk180` devices.
2. Add pins `A`, `B`, `VDD`, `GND`, `VOUT` and verify connectivity.
3. Create symbol and use it inside a separate simulation testbench.
4. Apply `vpulse` inputs for `A` and `B`, power through `vdc`, reference with `gnd!`.
5. Open `ADE L`, set transient analysis (`tran`, around `100n`, moderate), and choose outputs.
6. Run and confirm XOR logic from waveform.
7. Create layout with proper diffusion sharing, metal routing, vias, and labeled pins.
8. Use `Connectivity` -> `Generate` -> `All From Source`, then complete and clean layout.
9. Run Assura DRC and resolve violations.
10. Run Assura LVS and ensure pass status.
11. Perform extraction to generate `av_extracted` view and run post-layout transient simulation.

-> **Result/Conclusion**

XOR gate operation was verified successfully in both schematic and extracted simulations, and layout passed DRC/LVS checks. The complete Cadence flow from schematic to post-layout analysis was achieved.
