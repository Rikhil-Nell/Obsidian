# LICA Lab Record Automation — Agent Spec

This file is the instruction set for any AI agent (Codex, Claude, etc.) to fully automate generation of lab records from handwritten PDFs. Read this entirely before executing anything.

---

## 0. Identity & Output Contract

- **Student:** Rikhil Nellimarla | **Reg:** 23BEC7030 | **Course:** LICA | **Slot:** L51+L52
- **Output format:** Single `.md` file per experiment, with SVG circuit diagrams embedded inline as raw `<svg>...</svg>` blocks (not `![](path)` references).
- **Renderer target:** Obsidian / Typora / GitHub — all handle inline SVG.
- **Section order (mandatory):**
    1. Objective
    2. Apparatus
    3. Theory
    4. Circuit Diagram
    5. Observation Table / Waveforms
    6. Result / Conclusion

---

## 1. Input Handling

### 1a. Handwritten PDFs (like Butterworth.pdf)

The Butterworth PDF is a scanned handwritten document. pdfplumber will return empty text. Use the vision content (images) directly — read component values, table data, and circuit topology from the visual. Do NOT skip values because text extraction failed.

**Butterworth PDF — ground truth values extracted visually:**

#### LPF Circuit

- Input: 5 Vpp signal source
- Series resistor to non-inverting (+) input: **R = 318 Ω**
- Shunt capacitor from (+) node to ground: **C = 0.1 μF**
- Feedback resistor (inverting − input to ground, bottom of divider): **RA = 17.064 kΩ**
- Feedback resistor (output to inverting − input, top of divider): **Rf = 10 kΩ**
- Op-amp supply: ±Vc
- Gain formula: Av = 1 + Rf/RA
- Theoretical cutoff: fc = 1/(2πRC) ≈ 5 kHz

#### HPF Circuit

- Input: 5 Vpp signal source
- Series capacitor to non-inverting (+) input: **C = 0.1 μF**
- Shunt resistor from (+) node to ground: **R = 318 Ω**
- Same feedback network: **RA = 17.064 kΩ**, **Rf = 10 kΩ**
- Op-amp supply: ±Vc
- Same gain formula and cutoff frequency

#### LPF Observation Table

|Frequency|V₀ (V)|Aᵥ|dB|
|---|---|---|---|
|100 Hz|8.6|1.72|4.71|
|200 Hz|8.6|1.72|4.71|
|300 Hz|8.6|1.72|4.71|
|400 Hz|8.6|1.72|4.71|
|500 Hz|8.6|1.72|4.71|
|1 kHz|8.4|1.68|4.50|
|2 kHz|8.2|1.64|4.29|
|3 kHz|7.8|1.56|3.86|
|4 kHz|7.4|1.48|3.40|
|5 kHz|7.0|1.40|2.92|
|6 kHz|6.6|1.32|2.41|
|7 kHz|5.8|1.16|1.28|
|8 kHz|5.4|1.08|0.668|
|9 kHz|5.2|1.04|0.34|
|10 kHz|4.4|0.88|−1.10|

#### HPF Observation Table

|Frequency|V₀ (V)|Aᵥ|dB|
|---|---|---|---|
|100 Hz|0.16|0.032|−29.89|
|200 Hz|0.32|0.064|−23.87|
|300 Hz|0.40|0.080|−21.93|
|400 Hz|0.48|0.096|−20.35|
|500 Hz|0.56|0.112|−19.01|
|1 kHz|1.04|0.208|−13.63|
|2 kHz|2.00|0.400|−7.95|
|4 kHz|3.52|0.704|−3.04|
|6 kHz|4.62|0.924|−0.68|
|8 kHz|5.44|1.080|0.66|
|10 kHz|6.00|1.200|1.56|
|15 kHz|6.01|1.210|1.65|
|20 kHz|6.02|1.190|1.51|
|30 kHz|6.07|1.220|1.727|

### 1b. Typed/structured PDFs (like LAB_NOTES.pdf)

pdfplumber will return text. Parse sections by the `->` heading markers. Component values appear inline in the Apparatus section. Extract them exactly.

**LAB_NOTES PDF — ground truth values extracted:**

#### Integrator Circuit

- Input resistor (series, to inverting − input): **Rin = 15 kΩ**
- Feedback capacitor (inverting − input to output): **Cf = 470 μF** _(as labeled in diagram)_
- Feedback resistor in parallel with Cf: **Rf = 150 kΩ**
- Non-inverting (+) input: tied to ground
- Op-amp: IC 741
- Supply: ±V dual

#### Differentiator Circuit

- Input capacitor (series, to inverting − input): **Cin = 470 μF** _(as labeled)_
- Two feedback resistors in parallel: **R1 = 15 kΩ** (top), **R2 = 150 kΩ** (bottom)
- Non-inverting (+) input: tied to ground
- Op-amp: IC 741
- Supply: ±V dual

#### Integrator Observation Table

|Waveform|Input Vpp (V)|Freq (kHz)|Output Vpp (V)|Freq (kHz)|
|---|---|---|---|---|
|Sine|10.2|34|12.4|34|
|Square|10.2|34|10.4|34|
|Triangular|9.8|34|4.8|34|

#### Differentiator Observation Table

|Waveform|Input Vpp (V)|Freq (kHz)|Output Vpp (V)|Freq (kHz)|
|---|---|---|---|---|
|Sine|~5.0|34|~8.5|34|
|Square|5.12|34|9.40|34|
|Triangular|5.04|34|3.28|34|

---

## 2. Circuit Diagram Generation Rules

Use **schemdraw** (Python). Install: `pip install schemdraw matplotlib --break-system-packages`

### 2a. General Rules

- Always use `matplotlib.use('Agg')` before any schemdraw import to avoid display errors in headless environments.
- Save to SVG: `d.save('output.svg')`
- Embed the SVG file content inline in the markdown — read the file and paste the raw XML. Do NOT use image references.
- Label every component with both its symbol AND value (e.g., `'R\n318Ω'`).
- Use `loc='top'` for horizontal elements, `loc='right'` for vertical ones.

### 2b. Topology Patterns

#### Non-inverting op-amp with RC input filter (Butterworth LPF/HPF)

```
Vin ── [series_element] ── (+)─────── [op-amp] ── Vout
                           |                  |
                    [shunt_element]      [Rf feedback]
                           |                  |
                          GND           [RA to GND]
```

**schemdraw implementation pattern:**

```python
import schemdraw
import schemdraw.elements as elm
import matplotlib
matplotlib.use('Agg')

with schemdraw.Drawing(show=False) as d:
    # 1. Source
    src = d.add(elm.SourceSin().up().label('Vin\n5Vpp', loc='left'))
    d.add(elm.Line().right(1.5))

    # 2. Series element (R for LPF, C for HPF)
    series = d.add(elm.Resistor().right().label('R\n318Ω', loc='top'))
    # -- OR for HPF: --
    # series = d.add(elm.Capacitor().right().label('C\n0.1μF', loc='top'))

    # 3. Junction node — save position
    node_in1 = d.here
    d.add(elm.Dot())

    # 4. Shunt element down to ground (C for LPF, R for HPF)
    d.add(elm.Capacitor().down().label('C\n0.1μF', loc='right'))
    # -- OR for HPF: --
    # d.add(elm.Resistor().down().label('R\n318Ω', loc='right'))
    d.add(elm.Ground())

    # 5. Op-amp — anchor non-inverting input at saved junction
    d.here = node_in1
    op = d.add(elm.Opamp().anchor('in1').right())

    # 6. Power supply labels
    d.add(elm.Label().at(op.vs).label('+Vc', loc='right'))
    d.add(elm.Label().at(op.vd).label('−Vc', loc='right'))

    # 7. Output line
    d.add(elm.Line().at(op.out).right(2).label('Vout', loc='right'))
    out_pt = d.here

    # 8. Feedback: line left from out, then Rf up to inverting input level,
    #    then line to op.in2, then RA down to ground
    d.add(elm.Line().at(out_pt).up(1.5))
    fb_top = d.here
    d.add(elm.Resistor().left().tox(op.in2).label('Rf\n10kΩ', loc='top'))
    fb_mid = d.here
    d.add(elm.Line().toy(op.in2))
    d.add(elm.Line().at(op.in2).tox(fb_mid))

    # RA from fb_mid down to ground
    d.add(elm.Resistor().at(fb_mid).down().label('RA\n17.064kΩ', loc='right'))
    d.add(elm.Ground())

    # Close source return
    d.add(elm.Line().at(src.start).right().tox(op.vs))
    d.add(elm.Line().toy(op.vs))

    d.save('lpf_circuit.svg')
```

> **Note:** The feedback network for this non-inverting Butterworth amplifier is: inverting (−) input connects to the midpoint of a voltage divider made of Rf (output side) and RA (ground side). This is what gives Av = 1 + Rf/RA. Do NOT route Rf as the feedback from output back to inverting input directly — that would be an inverting configuration. The correct topology has RA going to ground from the (−) pin, and Rf connecting (−) pin to output.

#### Inverting op-amp integrator (LAB_NOTES)

```
Vin ── [Rin] ── (−) ─── [op-amp] ── Vout
                 |    [Cf ∥ Rf]    |
                 └────────────────-┘
                (+) ── GND
```

```python
with schemdraw.Drawing(show=False) as d:
    src = d.add(elm.SourceSin().up().label('Vin', loc='left'))
    d.add(elm.Line().right(1.5))
    d.add(elm.Resistor().right().label('Rin\n15kΩ', loc='top'))

    node_inv = d.here
    d.add(elm.Dot())

    op = d.add(elm.Opamp().anchor('in2').right())

    # Non-inverting to ground
    d.add(elm.Line().at(op.in1).down(0.5))
    d.add(elm.Ground())

    # Power
    d.add(elm.Label().at(op.vs).label('+V', loc='right'))
    d.add(elm.Label().at(op.vd).label('−V', loc='right'))

    # Output
    d.add(elm.Line().at(op.out).right(2).label('Vout', loc='right'))
    out_pt = d.here

    # Feedback path: Cf and Rf in parallel, from out back to inv node
    d.add(elm.Line().at(out_pt).up(2.5))
    fb_top_right = d.here
    # Cf on top rail
    d.add(elm.Capacitor().left().tox(node_inv).label('Cf\n470μF', loc='top'))
    fb_top_left = d.here
    d.add(elm.Line().toy(node_inv))
    # Rf below Cf
    d.add(elm.Line().at(out_pt).up(1.5))
    fb_mid_right = d.here
    d.add(elm.Resistor().left().tox(node_inv).label('Rf\n150kΩ', loc='bottom'))

    # Source return
    d.add(elm.Line().at(src.start).right().tox(op.in1))
    d.add(elm.Line().toy(op.in1))

    d.save('integrator_circuit.svg')
```

#### Inverting op-amp differentiator (LAB_NOTES)

Same as integrator but swap input and feedback:

- Input: Cin (capacitor) in series instead of Rin
- Feedback: R1 and R2 in parallel instead of Cf ∥ Rf
- Component values: Cin = 470 μF, R1 = 15 kΩ, R2 = 150 kΩ

---

## 3. Text Content Rules

### Objective

One sentence. Should include: circuit type, what is being designed, what is being studied. Pull verbatim from the PDF if it exists (LAB_NOTES has it). For handwritten PDFs, derive it from the "Objective:" line at the top.

### Apparatus

Render as a markdown table with columns: Component | Specification. Pull ALL component values mentioned anywhere in the PDF — cross-reference the circuit diagram labels and the written apparatus list. Do not leave values as "—" if they appear in the diagram.

### Theory

Must include:

- What the circuit does (functional description)
- The key formula (cutoff frequency, gain, time constant — whichever applies)
- Substituted numerical calculation using the actual component values from THIS experiment
- Expected behavior per input waveform type if applicable
- For filters: state roll-off rate (−20 dB/decade for 1st order)

### Circuit Diagram

- One SVG per distinct circuit topology in the experiment
- Label: component symbol + value (both)
- Include Vin label on source, Vout label on output, ±Vc/±V on supply pins
- Caption the SVG with circuit name (e.g., "### Low Pass Filter (LPF)")

### Observation Table

Transcribe exactly as written. Preserve all columns. Use `~` for approximate values. Use `−` (Unicode minus) not `-` for negative dB values.

### Result / Conclusion

- State whether the experiment succeeded
- Reference the theoretical expectation and compare to observed values
- Mention the cutoff frequency / crossover point where visible in the data
- Note any deviations and attribute them (component tolerance, non-ideal op-amp)
- Keep it 3–5 sentences, no bullet points

---

## 4. File Naming Convention

```
{experiment_slug}_lab_record.md
```

Examples:

- `butterworth_lpf_hpf_lab_record.md`
- `integrator_differentiator_lab_record.md`

SVGs (if saved separately): `{experiment_slug}_{circuit_name}.svg`

---

## 5. Execution Checklist

Before writing any output, verify:

- [ ] All component values from the PDF are captured (none left as placeholders)
- [ ] Circuit topology matches the handwritten/rendered diagram exactly
- [ ] Observation table rows match the PDF exactly (no hallucinated rows)
- [ ] Formulas use the actual values from this experiment, not generic symbols
- [ ] SVG renders without errors (test with `python3 script.py` before embedding)
- [ ] All 6 sections are present in correct order
- [ ] Student details header is present

---

## 6. Known Gotchas

|Issue|Fix|
|---|---|
|Butterworth PDF text extraction returns empty|It's a scanned image — use vision/document context, not pdfplumber|
|schemdraw `.tox()` / `.toy()` with no prior position|Always set `d.here` explicitly before using positional anchors|
|Feedback network looks wrong|Butterworth uses non-inverting config: RA to GND from (−), Rf from (−) to out|
|Integrator vs differentiator swap|Integrator: R input + C feedback. Differentiator: C input + R feedback|
|Negative dB values showing as `-`|Use Unicode `−` (U+2212) or just `-` is fine in markdown tables|
|SVG not rendering inline in GitHub|GitHub strips inline SVG — use `<img src="circuit.svg">` for GitHub, raw SVG for Obsidian/Typora|
|matplotlib display error in headless|Always call `matplotlib.use('Agg')` before importing schemdraw|

---

## 7. Future Experiments Template

When a new handwritten PDF arrives:

1. Run vision extraction → identify experiment name from "Objective:" line
2. Extract all component values from circuit diagram labels
3. Extract observation table rows
4. Match to topology patterns in Section 2b (or define new pattern)
5. Generate schemdraw script → test → embed SVG
6. Fill all 6 sections using rules in Section 3
7. Run checklist in Section 5