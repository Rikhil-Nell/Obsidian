# Filter Fundamentals

## Learning Objectives

After this section, you should be able to:

- Explain what a filter does in a signal-processing system.
- Distinguish analog, digital, passive, and active filters.
- Compare active and passive networks in exam-ready points.
- Identify low-pass, high-pass, band-pass, and band-reject responses.
- Connect frequency response to the transfer function $H(j\omega)$.

## Ground-Up Explanation

A filter is a circuit that allows desired frequency components to pass and attenuates unwanted frequency components. In simple terms, it behaves like a gatekeeper for frequency.

If a signal contains useful audio plus high-frequency noise, a low-pass filter can keep the useful low-frequency content and reduce the high-frequency noise. If a signal contains a low-frequency hum, a high-pass filter can reduce the hum and preserve higher-frequency content.

The behavior of a filter is described by its frequency response. The frequency response tells how much gain or attenuation the filter gives at each frequency. In circuit analysis this is written as a transfer function:

$$
H(j\omega) = \frac{V_o(j\omega)}{V_i(j\omega)}
$$

Here, $V_i$ is input voltage, $V_o$ is output voltage, and $\omega = 2\pi f$ is angular frequency in rad/s.

## Analogies

- A low-pass filter is like a low-height gate: small children pass, tall adults are blocked. Low frequencies pass, high frequencies are attenuated.
- A high-pass filter is like a height restriction in reverse: only tall people pass. High frequencies pass, low frequencies are attenuated.
- A band-pass filter is like a nightclub age limit: only a middle range is accepted.
- A band-reject filter is like blocking one noisy radio station while keeping frequencies below and above it.

## Active vs Passive Filters

| Feature | Passive Filter | Active Filter |
| --- | --- | --- |
| Components | R, L, C | Op-amp, R, C |
| Gain | Usually cannot exceed unity | Can be greater than unity |
| Loading effect | More significant | Minimal due to high input and low output impedance |
| Inductors | Often required | Usually avoided |
| Low-frequency design | Bulky and costly due to inductors | Practical using RC and op-amps |
| Power supply | No external supply needed | Requires DC supply |
| High-frequency use | Often better | Limited by op-amp gain-bandwidth product and slew rate |

### Advantages of Active Filters

Active filters are preferred in many LIC applications because they:

- Avoid practical inductors, which are bulky, costly, and lossy at low frequencies.
- Can provide passband gain greater than unity.
- Minimize loading effects when cascaded.
- Are tunable through gain and frequency-setting components.
- Do not suffer insertion loss like passive filters.

### Limitations of Active Filters

Active filters are not ideal in every situation:

- High-frequency response is limited by op-amp gain-bandwidth product.
- Slew rate limits large-signal high-frequency behavior.
- They need DC power supplies.
- Temperature and process variations affect active devices.
- High-frequency active filter design can become costly.

## Basic Filter Types

| Filter Type | Passband | Stopband | Common Symbol |
| --- | --- | --- | --- |
| Low-pass filter | $0$ to $f_H$ | Above $f_H$ | LPF |
| High-pass filter | Above $f_L$ | $0$ to $f_L$ | HPF |
| Band-pass filter | $f_L$ to $f_H$ | Below $f_L$, above $f_H$ | BPF |
| Band-reject filter | Below $f_H$, above $f_L$ | $f_H$ to $f_L$ | BRF or notch |

## Key Formulas

### Frequency and Angular Frequency

$$
\boxed{\omega = 2\pi f}
$$

Variables:

- $\omega$: angular frequency in rad/s
- $f$: frequency in Hz

### Transfer Function

$$
\boxed{H(j\omega) = \frac{V_o(j\omega)}{V_i(j\omega)}}
$$

Variables:

- $H(j\omega)$: frequency-domain transfer function
- $V_o$: output voltage
- $V_i$: input voltage

### Decibel Gain

$$
\boxed{A_{dB} = 20\log_{10}|A_v|}
$$

Variables:

- $A_{dB}$: voltage gain in dB
- $A_v$: voltage gain ratio

## Derivation: Why Roll-Off Is Measured in dB/Decade

For a first-order RC section, the magnitude eventually contains a factor proportional to $1/f$ in the stopband. When frequency increases by a factor of 10:

$$
20\log_{10}\left(\frac{1}{10}\right) = -20\text{ dB}
$$

So a first-order section has a roll-off of about $20$ dB/decade, or $6$ dB/octave.

## Worked Example Pattern

Question: A filter passes low-frequency signals and attenuates high-frequency noise. What type is it?

Solution:

1. Identify the passed region: low frequencies.
2. Identify the rejected region: high frequencies.
3. Therefore, it is a low-pass filter.

$$
\boxed{\text{Low-pass filter}}
$$

## Common Mistakes

- Do not say active filters are always better. They are limited at high frequencies by practical op-amp behavior.
- Do not confuse $f_H$ and $f_L$. $f_H$ is commonly the high cutoff of an LPF or upper edge of a BPF; $f_L$ is commonly the low cutoff of an HPF or lower edge of a BPF.
- Do not forget that passive filters can work without power supplies, while active filters need DC supply.
- Do not describe stopband as complete zero output in practical circuits. It means strong attenuation.

## Self-Check Questions

1. Why are inductors avoided in active filter design?

<details>
<summary>Answer</summary>

Inductors are bulky, costly, lossy, sensitive to magnetic coupling, and impractical at low frequencies. Active filters use op-amps with R and C networks instead.

</details>

2. What is the major limitation of active filters at high frequency?

<details>
<summary>Answer</summary>

The op-amp gain-bandwidth product and slew rate limit high-frequency performance.

</details>

3. What does $H(j\omega)$ represent?

<details>
<summary>Answer</summary>

It is the frequency-domain transfer function, equal to output phasor divided by input phasor.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s2_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s4_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s8_img1.png]]

## Concept Links

- Next topic: [First-Order Active Filters](./02_first_order_active_filters.md)
- Formula reference: [General Filter Formulas](./10_formula_sheet_ultimate.md#filter-fundamentals)
