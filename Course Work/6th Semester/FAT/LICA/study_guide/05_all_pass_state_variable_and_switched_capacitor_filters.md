# All-Pass, State-Variable, and Switched-Capacitor Filters

## Learning Objectives

After this section, you should be able to:

- Explain the purpose of all-pass filters.
- Describe state-variable filters as universal filters.
- Identify low-pass, high-pass, band-pass, and notch outputs in a state-variable filter.
- Explain how switched-capacitor filters replace resistors in IC design.
- Derive the switched-capacitor equivalent resistance idea.

## All-Pass Filters

An all-pass filter allows all frequency components to pass with constant magnitude, but changes phase. It is used when amplitude should remain unchanged while timing or phase must be corrected.

Uses:

- Phase equalization
- Delay equalization
- Signal timing correction
- Communication systems

The key pole-zero idea is that zeros are mirror images of poles, giving constant magnitude response.

## All-Pass Analogy

An all-pass filter is like a road that lets every vehicle through but changes the arrival time of each lane. Nothing is blocked by size, but timing is adjusted.

## State-Variable Filters

A state-variable filter is a versatile second-order active filter. It uses:

- A summing amplifier
- Two integrators
- A feedback network
- Sometimes a final summer

It is also called a universal filter because the same circuit can provide multiple outputs.

| Output | Where It Comes From |
| --- | --- |
| High-pass | Summing amplifier output |
| Band-pass | First integrator output |
| Low-pass | Second integrator output |
| Notch | Sum of high-pass and low-pass outputs |

## Why State-Variable Filters Are Useful

State-variable filters are important because they allow:

- Independent adjustment of natural frequency and quality factor.
- Multiple filter responses from one circuit.
- Electronic tuning over a wide frequency range.
- Better practical control than many single-output second-order sections.

The timing elements $R$ and $C$ set the natural frequency. The feedback network controls damping and $Q$.

## Switched-Capacitor Filters

Switched-capacitor filters are discrete-time filters that replace resistors using capacitors, switches, and a clock. This is very useful in IC fabrication because accurate resistors are hard to make on-chip, while capacitor ratios can be controlled more accurately.

The key principle is that a capacitor moved between two nodes transfers charge every clock cycle. Repeated charge transfer creates an average current, just like a resistor would.

## Derivation: Equivalent Resistance of a Switched Capacitor

Assume a capacitor $C$ transfers charge between voltages $V_1$ and $V_2$ once every clock period $T_{ck}$.

Charge transferred:

$$
\Delta Q = C(V_1 - V_2)
$$

Average current:

$$
i = \frac{\Delta Q}{T_{ck}} = \frac{C(V_1 - V_2)}{T_{ck}}
$$

Since $f_{ck} = 1/T_{ck}$:

$$
i = C f_{ck}(V_1 - V_2)
$$

For a resistor:

$$
i = \frac{V_1 - V_2}{R}
$$

Equating both:

$$
\frac{V_1 - V_2}{R_{eq}} = C f_{ck}(V_1 - V_2)
$$

Therefore:

$$
\boxed{R_{eq} = \frac{1}{C f_{ck}}}
$$

Variables:

- $R_{eq}$: equivalent resistance in ohms
- $C$: switched capacitance in farads
- $f_{ck}$: clock frequency in Hz

## Features of Switched-Capacitor Filters

- No physical resistors are needed.
- Unity-gain frequency can depend on capacitance ratios.
- Frequency can be programmed using clock frequency.
- Good for IC implementation.
- Operates as a discrete-time circuit.

## Practical Limitations

- Clock frequency cannot be unlimited because MOS switches and op-amps have finite speed.
- The lower clock limit is affected by leakage currents and op-amp input bias currents.
- Practical switched-capacitor filters are sampled systems, so sampling effects matter.
- Clock feedthrough and switching noise can affect performance.

## Worked Example

Find $R_{eq}$ for $C = 10\text{ pF}$ and $f_{ck} = 100\text{ kHz}$.

Convert:

$$
C = 10 \times 10^{-12}F,\quad f_{ck} = 100 \times 10^3Hz
$$

Use:

$$
R_{eq} = \frac{1}{C f_{ck}}
$$

Substitute:

$$
R_{eq} = \frac{1}{(10 \times 10^{-12})(100 \times 10^3)}
$$

$$
R_{eq} = 1 \times 10^6\ \Omega
$$

$$
\boxed{R_{eq} = 1\ M\Omega}
$$

## Common Mistakes

- Saying switched-capacitor filters are continuous-time filters. They are discrete-time operated.
- Forgetting that $R_{eq}$ decreases when clock frequency increases.
- Confusing state-variable filters with only low-pass filters. They provide multiple outputs.
- Thinking all-pass filters change amplitude. Ideally, they change phase only.

## Self-Check Questions

1. Why are switched-capacitor filters attractive in IC design?

<details>
<summary>Answer</summary>

They replace inaccurate or large on-chip resistors with capacitors and switches, and capacitor ratios are easier to control in IC fabrication.

</details>

2. Which state-variable output is taken after the second integrator?

<details>
<summary>Answer</summary>

The low-pass output.

</details>

3. What happens to $R_{eq}$ if $f_{ck}$ doubles?

<details>
<summary>Answer</summary>

$R_{eq}$ becomes half, because $R_{eq} = 1/(C f_{ck})$.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s39_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s40_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s41_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s45_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s47_img1.png]]

## Concept Links

- Previous topic: [Filter Approximations and Transformations](./04_filter_approximations_and_transformations.md)
- Next topic: [DAC and ADC Fundamentals and Specifications](./06_dac_adc_fundamentals_and_specifications.md)
- Formula reference: [Switched-Capacitor Formulas](./10_formula_sheet_ultimate.md#switched-capacitor-filters)
