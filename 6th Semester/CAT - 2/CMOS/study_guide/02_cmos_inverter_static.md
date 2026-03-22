# CMOS Inverter - Static Characteristics

## Learning Objectives

After this section you will understand:
- How a CMOS inverter works as a switch (the most fundamental digital gate)
- Why CMOS is superior to NMOS-only or PMOS-only logic
- The Voltage Transfer Characteristic (VTC) and its 5 operating regions
- How to find the switching threshold VM
- What noise margins are and how to calculate them

---

## What is a CMOS Inverter?

The CMOS inverter is the **most fundamental building block** in all of digital VLSI design. If you understand how an inverter works, you can understand NAND, NOR, XOR, adders, multipliers, and entire processors -- they are all built from combinations of the same principle.

**Analogy:** The CMOS inverter is like the alphabet of digital circuits. Just as every word is built from letters, every digital circuit is built from inverters (and their extensions).

### Circuit Structure

A CMOS inverter consists of exactly **two transistors**:
- **PMOS** (top): Connected between $V_{DD}$ (power supply) and the output
- **NMOS** (bottom): Connected between the output and GND (ground)
- Both gates are tied together as the **input**

![[cmos_inverter_circuit.png]]

### How It Works: The Switch Model

The simplest way to understand the inverter is the **switch model**:

![[cmos_inverter_switch_model.png]]

| Input ($V_{in}$) | NMOS | PMOS | Output ($V_{out}$) |
|-------------------|------|------|---------------------|
| LOW (0V) | OFF (open switch) | ON (closed switch) | **HIGH** ($V_{DD}$) |
| HIGH ($V_{DD}$) | ON (closed switch) | OFF (open switch) | **LOW** (GND) |

The NMOS turns ON when gate voltage is HIGH. The PMOS turns ON when gate voltage is LOW. They are **complementary** -- exactly one is always ON, the other OFF.

**Analogy:** Think of a seesaw. When one side goes up, the other must go down. The NMOS and PMOS work like that -- they never both fully conduct at the same time (except briefly during switching).

---

## Why CMOS is Special (Key Properties)

| Property | Description | Why It Matters |
|----------|-------------|----------------|
| **Rail-to-rail swing** | Output reaches exactly $V_{DD}$ or exactly 0V | Maximum noise margins |
| **Ratioless logic** | Output levels do NOT depend on device sizes | Can use minimum-size transistors |
| **High input impedance** | Gate draws zero DC current | Can drive many gates (high fan-out) |
| **Low output impedance** | Always a path from output to a supply rail | Robust against noise |
| **No static power** | No direct path from $V_{DD}$ to GND in steady state | Ultra-low standby power |

---

## Voltage Transfer Characteristic (VTC)

The VTC is a plot of $V_{out}$ vs $V_{in}$ -- it tells you what the output voltage is for every possible input voltage.

### Graphical Construction

The VTC is found by superimposing the I-V curves of the NMOS and PMOS. Since they share the same current and the same output/drain node, we find their intersection points.

**Step 1:** Transform PMOS curves to the same coordinate system using:
$$V_{DS,p} = V_{out} - V_{DD}, \quad V_{GS,p} = V_{in} - V_{DD}$$

![[cmos_vtc_pmos_transform.png]]

**Step 2:** Mirror the PMOS I-V curves:

![[cmos_pmos_load_lines.png]]

**Step 3:** Find intersection points (where $I_{D,n} = I_{D,p}$):

![[cmos_vtc_operating_points.png]]

### The 5 Operating Regions of the VTC

The VTC can be divided into **5 distinct regions** (A through E), each with different transistor operating conditions:

![[vtc_five_regions.png]]

![[vtc_five_regions_table.png]]

| Region | $V_{in}$ Range | NMOS | PMOS | $V_{out}$ |
|--------|---------------|------|------|-----------|
| **A** | $0$ to $V_{T,n}$ | Cutoff | Linear | $V_{DD}$ (constant HIGH) |
| **B** | $V_{T,n}$ to $V_{IL}$ | Saturation | Linear | HIGH, starting to drop |
| **C** | $V_{IL}$ to $V_{IH}$ | Saturation | Saturation | Rapid transition (gain < -1) |
| **D** | $V_{IH}$ to $V_{DD}-|V_{T,p}|$ | Linear | Saturation | LOW, settling |
| **E** | $V_{DD}-|V_{T,p}|$ to $V_{DD}$ | Linear | Cutoff | $0$ (constant LOW) |

**Key insight:** The steep transition in region C is what makes the inverter a good digital gate -- a small change in input causes a large change in output.

---

## Switching Threshold ($V_M$)

### Definition

The **switching threshold** $V_M$ is the input voltage at which $V_{in} = V_{out}$. Graphically, it is where the VTC crosses the $V_{in} = V_{out}$ (45-degree) line.

![[switching_threshold_vtc.png]]

At $V_M$, **both transistors are in saturation** (both are simultaneously ON). This is the only point where the inverter has a direct path from $V_{DD}$ to GND, causing a brief current spike.

### Derivation

At $V_M$: both in saturation, and $I_{D,n} = I_{D,p}$. With velocity saturation assumed ($V_{DSAT} < V_M - V_T$):

![[switching_threshold_equation.png]]

Solving for $V_M$:

$$\boxed{V_M = \frac{V_{DD} - |V_{T,p}| + V_{T,n}\sqrt{r}}{1 + \sqrt{r}}}$$

where $r = \frac{k_p \cdot V_{DSAT,p}}{k_n \cdot V_{DSAT,n}}$

### Simplified Form (for large $V_{DD}$)

![[vm_simplified_equation.png]]

$$\boxed{V_M \approx \frac{V_{DD}}{1 + \sqrt{r}}}$$

### Controlling $V_M$ Through Sizing

To set $V_M$ to a desired value, we can adjust the PMOS/NMOS size ratio:

![[vm_sizing_ratio_equation.png]]

**Key insight:** Making the PMOS wider shifts $V_M$ toward $V_{DD}$. Making the NMOS wider shifts $V_M$ toward GND.

---

## Noise Margins

### What Are Noise Margins?

**Analogy:** Noise margin is like the "safety buffer" on a bridge's weight limit. If a bridge can hold 10 tons, and you set the weight limit at 8 tons, you have a 2-ton noise margin. Similarly, noise margins tell you how much unwanted voltage "noise" a circuit can tolerate before misinterpreting a signal.

![[noise_margin_concept.png]]

### Definitions

When one inverter's output connects to another's input via a wire, noise can corrupt the signal. Noise margins define how much corruption is tolerable:

![[noise_margin_diagram.png]]

$$\boxed{NM_L = V_{IL} - V_{OL}}$$
$$\boxed{NM_H = V_{OH} - V_{IH}}$$

Where:
- $V_{IL}$ = maximum input voltage recognized as LOW
- $V_{IH}$ = minimum input voltage recognized as HIGH
- $V_{OL}$ = output voltage when driving LOW (ideally 0V for CMOS)
- $V_{OH}$ = output voltage when driving HIGH (ideally $V_{DD}$ for CMOS)

**For ideal CMOS:** $V_{OL} = 0$ and $V_{OH} = V_{DD}$, so:
- $NM_L = V_{IL}$
- $NM_H = V_{DD} - V_{IH}$

### Key Points About Noise Margins

1. **Higher noise margins = more robust circuit** (can tolerate more noise)
2. For a **symmetric inverter** ($V_M = V_{DD}/2$), the noise margins are equal: $NM_L = NM_H$
3. The region between $V_{IL}$ and $V_{IH}$ is the **uncertain/transition region** -- inputs in this range produce unpredictable outputs
4. CMOS inherently has **large noise margins** because the output swings rail-to-rail ($0$ to $V_{DD}$)

---

## Supply Voltage Scaling

Reducing $V_{DD}$ has major implications:

![[supply_voltage_scaling_vtc.png]]

| Effect | What Happens |
|--------|-------------|
| VTC shape | Becomes less sharp (softer transition) |
| Noise margins | **Decrease** (less room for error) |
| Speed | **Decreases** (more delay) |
| Power | **Decreases dramatically** (power scales as $V_{DD}^2$) |
| Minimum $V_{DD}$ | Must satisfy $V_{DD} > V_{T,n} + |V_{T,p}|$ |

**Critical rule:** Below $V_{DD} = V_{T,n} + |V_{T,p}|$, neither transistor can fully turn on, and the inverter stops working as a logic gate.

---

## Common Mistakes

1. **Confusing $V_M$ with $V_{th}$**: $V_M$ is the switching threshold (where $V_{in} = V_{out}$). $V_{th}$ is used interchangeably with $V_M$ in some textbooks but NOT the same as the transistor threshold voltage $V_T$
2. **Forgetting that CMOS has NO static power**: If someone asks "what is the static power of a CMOS inverter?" the answer is **approximately zero** (ignoring leakage)
3. **Not recognizing the 5 regions**: Know which transistor is in what mode for each region -- this is a common exam question
4. **Assuming symmetric VTC**: The VTC is only symmetric if the PMOS is sized wider to compensate for lower hole mobility. Default minimum-size transistors give an asymmetric VTC

---

## Self-Check Questions

**Q1:** In which region of the VTC are both transistors in saturation?

> **A:** Region C (the transition region between $V_{IL}$ and $V_{IH}$). This is also where the gain is below -1.

**Q2:** If you increase the width of the PMOS transistor (while keeping NMOS the same), does $V_M$ increase or decrease?

> **A:** $V_M$ **increases** (shifts toward $V_{DD}$). A wider PMOS pulls the output HIGH more strongly, so more input voltage is needed to overcome it.

**Q3:** For a CMOS inverter with $V_{DD} = 3.3V$, $V_{IL} = 1.2V$, $V_{IH} = 1.8V$, calculate the noise margins.

> **A:** $NM_L = V_{IL} - V_{OL} = 1.2 - 0 = 1.2V$. $NM_H = V_{OH} - V_{IH} = 3.3 - 1.8 = 1.5V$.

**Q4:** What is the minimum supply voltage for a CMOS inverter with $V_{T,n} = 0.4V$ and $|V_{T,p}| = 0.5V$?

> **A:** $V_{DD,min} = V_{T,n} + |V_{T,p}| = 0.4 + 0.5 = 0.9V$.

---

## Concept Links

- The VTC regions use LEVEL 1 current equations from [SPICE Modeling](./01_spice_modeling.md)
- VIL and VIH are derived analytically in [CMOS Inverter Design](./03_cmos_inverter_design.md)
- Supply voltage scaling connects directly to [Power Dissipation](./04_power_dissipation.md)
- The transient behavior of the inverter is analyzed in [Dynamic Characteristics](./05_dynamic_characteristics.md)
- All VTC formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#cmos-inverter-vtc)
