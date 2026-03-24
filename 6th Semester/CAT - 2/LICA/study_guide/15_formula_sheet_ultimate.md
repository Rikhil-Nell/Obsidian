# 15 - Formula Sheet (Ultimate Quick Reference)

> [!tip] Exam Strategy
> Print this page for last-minute revision. Boxed formulas are the most frequently tested.

---

## Differential Amplifier

$$\boxed{V_o = \frac{R_2}{R_1}(V_2 - V_1)}$$

$$CMRR = \frac{A_d}{A_{cm}} = \frac{R_2/R_1}{A_{cm}}$$

---

## Instrumentation Amplifier

$$\boxed{V_o = \left(1 + \frac{2R_1}{R}\right)\frac{R_3}{R_2}(V_1 - V_2)}$$

For unity Stage 2 ($R_2 = R_3$):

$$V_o = \left(1 + \frac{2R_1}{R}\right)(V_1 - V_2)$$

Gain adjusted by changing only $R$.

---

## Precision Rectifier

$$V_{D,eff} = \frac{V_D}{A_{OL}} \approx 0$$

Half-wave: $V_o = V_i$ (for $V_i > 0$), $V_o = 0$ (for $V_i < 0$)

Full-wave: $V_o = |V_i|$

---

## Clippers and Clampers

**Clipper:**

$$\boxed{V_{clip} = \pm(V_Z + V_D)}, \quad A_v = -\frac{R_f}{R_1} \text{ (linear region)}$$

**Positive Clamper:**

$$V_o = V_i + V_{np} \quad \text{(or } + V_{ref}\text{)}$$

---

## Logarithmic and Antilog Amplifier

$$\boxed{V_o^{log} = -\frac{kT}{q}\ln\left(\frac{V_i}{I_S R}\right)}$$

$$\boxed{V_o^{antilog} = -R_f \cdot I_S \cdot e^{V_i/V_T}}$$

**Thermal voltage:** $V_T = \frac{kT}{q} \approx 26\text{ mV at 300 K}$

Constants: $k = 1.38 \times 10^{-23}$ J/K, $q = 1.6 \times 10^{-19}$ C

---

## Comparator and Schmitt Trigger

**Comparator** (open-loop):

$$V_o = \begin{cases} +V_{sat} & V_i > V_{ref} \\ -V_{sat} & V_i < V_{ref} \end{cases}$$

**Schmitt Trigger** (positive feedback, $\beta = \frac{R_1}{R_1 + R_2}$):

$$\boxed{V_{UT} = V_{ref}\frac{R_2}{R_1+R_2} + V_{sat}\frac{R_1}{R_1+R_2}}$$

$$\boxed{V_{LT} = V_{ref}\frac{R_2}{R_1+R_2} - V_{sat}\frac{R_1}{R_1+R_2}}$$

$$\boxed{V_H = V_{UT} - V_{LT} = \frac{2R_1 V_{sat}}{R_1 + R_2}}$$

For $V_{ref} = 0$: $V_{UT} = +\beta V_{sat}$, $V_{LT} = -\beta V_{sat}$

---

## Sample and Hold Circuit

$$\tau_{charge} = (R_0 + r_{ds})C$$

$$\boxed{f_{sample} \geq 2f_{input}} \quad \text{(Nyquist)}$$

---

## Analog Voltage Multiplier

$$V_o = K \cdot V_x \cdot V_y \quad (K \text{ is scaling factor, typically } 1/10)$$

Frequency doubler: $\sin^2(\omega t) = \frac{1}{2}(1 - \cos 2\omega t)$

Phase detection: $V_{DC} \propto \cos(\phi_x - \phi_y)$

---

## Oscillators

**Barkhausen Criterion:**

$$\boxed{|A\beta| = 1, \quad \angle(A\beta) = 0^\circ \text{ or } 360^\circ}$$

| Parameter          | RC Phase Shift                            | Wien Bridge                       |        |                 |     |
| ------------------ | ----------------------------------------- | --------------------------------- | ------ | --------------- | --- |
| Frequency          | $\boxed{f_0 = \frac{1}{2\pi RC\sqrt{6}}}$ | $\boxed{f_0 = \frac{1}{2\pi RC}}$ |        |                 |     |
| Min. Gain          | $\boxed{                                  | A                                 | = 29}$ | $\boxed{A = 3}$ |     |
| Config             | Inverting                                 | Non-inverting                     |        |                 |     |
| Phase from network | 180 degrees                               | 0 degrees                         |        |                 |     |
| $R_f/R_1$ for gain | 29                                        | 2                                 |        |                 |     |

---

## Waveform Generators

**Square Wave Generator** (op-amp astable, $\beta = R_1/(R_1 + R_2)$):

$$\boxed{T = 2R_F C \ln\left(\frac{1+\beta}{1-\beta}\right)}$$

**Triangular Wave Generator:**

$$\boxed{f = \frac{R_2}{4R_1 R_3 C_1}}, \quad V_{pp} = 2V_{sat}\frac{R_3}{R_2}$$

---

## IC 555 Timer

**Pin Summary:** 1-GND, 2-Trigger, 3-Output, 4-Reset, 5-Control, 6-Threshold, 7-Discharge, 8-VCC

**Internal thresholds:** Upper = $\frac{2}{3}V_{CC}$, Lower = $\frac{1}{3}V_{CC}$

### Monostable

$$\boxed{T = 1.1RC = RC\ln 3}$$

### Astable

$$\boxed{T_H = 0.693(R_A + R_B)C}$$

$$\boxed{T_L = 0.693 \cdot R_B \cdot C}$$

$$\boxed{T = 0.693(R_A + 2R_B)C}$$

$$\boxed{f = \frac{1.44}{(R_A + 2R_B)C}}$$

$$\boxed{D = \frac{R_A + R_B}{R_A + 2R_B} \times 100\%}$$

**Note:** Duty cycle always > 50% (charging through $R_A + R_B$, discharging through $R_B$ only).

---

## 555 Applications

**555 Schmitt Trigger hysteresis:**

$$\boxed{V_H = \frac{V_{CC}}{3}}$$

**Ramp generator (constant current):**

$$V_C = \frac{i \cdot t}{C}, \quad i = \frac{V_{CC} - V_{BE}}{R_E}$$

**FSK:** $f_1 = \frac{1.44}{(R_A + 2R_B)C}$, $f_2 = \frac{1.44}{(R_A\|R_C + 2R_B)C}$

**Frequency divider:** $f_{out} = f_{in}/n$ (set $T_{mono}$ > input period)

---

## Phase Locked Loop

**Block diagram:** Input → Phase Detector → LPF → VCO → (feedback to PD)

**Lock range ≥ Capture range** (always)

**Analog PD:** $V_e = K_d \cos(\phi_i - \phi_o)$ (lock at 90 degrees)

**VCO:** $f_{VCO} = f_0 + K_v \cdot V_{control}$

| Phase Detector | Linear Range | Best Feature |
|---------------|-------------|--------------|
| Analog | Non-linear | Works with sine waves |
| XOR | 0-180 degrees | Simple digital |
| SR Flip-Flop | **0-360 degrees** | Best capture, no duty cycle req. |

---

## Physical Constants

| Constant | Symbol | Value |
|----------|--------|-------|
| Boltzmann | $k$ | $1.38 \times 10^{-23}$ J/K |
| Electron charge | $q$ | $1.6 \times 10^{-19}$ C |
| Thermal voltage (300 K) | $V_T$ | $\approx 26$ mV |

---

## Key Mathematical Identities

$$\ln(AB) = \ln A + \ln B$$
$$\ln(A/B) = \ln A - \ln B$$
$$\sin^2(\theta) = \frac{1}{2}(1 - \cos 2\theta)$$
$$\ln 3 \approx 1.099$$
$$\sqrt{6} \approx 2.449$$
