# DAC Architectures

## Learning Objectives

After this section, you should be able to:

- Convert a binary input into its fractional digital value.
- Calculate ideal DAC output voltage.
- Explain weighted-resistor DAC operation.
- Explain why weighted-resistor DACs become impractical at high resolution.
- Explain the advantage of R-2R ladder DACs.

## Ground-Up Explanation

A DAC converts an $n$-bit binary number into a proportional analog voltage or current. The digital bits do not all contribute equally. The most significant bit contributes the largest amount, and each next bit contributes half of the previous bit.

For a binary fraction:

$$
D = b_1 2^{-1} + b_2 2^{-2} + \cdots + b_n 2^{-n}
$$

The analog output is proportional to this fraction:

$$
\boxed{V_o = K V_{FS} D}
$$

Variables:

- $D$: binary fractional value
- $b_1$: most significant bit
- $b_n$: least significant bit
- $K$: scaling factor, often $1$
- $V_{FS}$: full-scale voltage
- $V_o$: analog output voltage

## Binary Fraction Example

For input $101_2$:

$$
D = 1(2^{-1}) + 0(2^{-2}) + 1(2^{-3})
$$

$$
D = \frac{1}{2} + 0 + \frac{1}{8} = 0.625
$$

If $V_{FS} = 8$ V and $K = 1$:

$$
V_o = 1(8)(0.625) = 5\text{ V}
$$

$$
\boxed{V_o = 5\text{ V}}
$$

## Weighted-Resistor DAC

A binary weighted-resistor DAC uses different resistor values for different bits. Each bit controls a switch:

- If bit is $1$, it connects to reference voltage.
- If bit is $0$, it connects to ground.

The op-amp works as a summing amplifier. Currents from the bit branches add at the inverting input, and the feedback resistor converts total current into output voltage.

### Why Resistors Are Weighted

The MSB must contribute the largest current, so it uses the smallest resistor. Each lower bit contributes half the previous current, so its resistance doubles.

Example resistor pattern:

| Bit | Weight | Resistor |
| --- | ---: | --- |
| MSB | $1/2$ | $R$ |
| Next | $1/4$ | $2R$ |
| Next | $1/8$ | $4R$ |
| LSB | $1/16$ | $8R$ |

## Weighted DAC Output Pattern

For an inverting weighted DAC, a common ideal form is:

$$
\boxed{V_o = -R_f V_R\left(\frac{b_1}{R} + \frac{b_2}{2R} + \frac{b_3}{4R} + \cdots + \frac{b_n}{2^{n-1}R}\right)}
$$

If $R_f = R$:

$$
\boxed{V_o = -V_R\left(b_1 + \frac{b_2}{2} + \frac{b_3}{4} + \cdots + \frac{b_n}{2^{n-1}}\right)}
$$

The sign is negative because the op-amp is used in inverting summing mode.

## Drawbacks of Weighted-Resistor DAC

Weighted-resistor DACs are simple conceptually but poor for high resolution:

- Need a wide range of resistor values.
- For many bits, resistor ratios become very large.
- Large resistors are difficult to fabricate accurately.
- Op-amp bias currents create errors with large resistors.
- Usually practical only for low resolution.

## R-2R Ladder DAC

The R-2R ladder DAC solves the resistor-spread problem by using only two resistor values: $R$ and $2R$.

Advantages:

- Easier fabrication.
- Better resistor matching.
- Scales to higher bit counts more practically.
- Input resistance remains predictable.
- Only two precision resistor values are needed.

## R-2R Intuition

At each stage, the ladder divides current into binary-weighted portions. Instead of physically using $R$, $2R$, $4R$, $8R$, and so on, the ladder network creates the weighting using repeated $R$ and $2R$ sections.

## Worked Example

For a full-scale voltage of $16$ V, find the analog output for binary data $1101_2$. Assume $K = 1$.

Compute the binary fraction:

$$
D = 1(2^{-1}) + 1(2^{-2}) + 0(2^{-3}) + 1(2^{-4})
$$

$$
D = \frac{1}{2} + \frac{1}{4} + 0 + \frac{1}{16}
$$

$$
D = 0.8125
$$

Output:

$$
V_o = K V_{FS} D = 1(16)(0.8125)
$$

$$
\boxed{V_o = 13\text{ V}}
$$

## Common Mistakes

- Treating binary input as decimal directly without using bit weights.
- Forgetting that a weighted-resistor DAC may produce an inverted output.
- Saying R-2R uses many different resistor values. It uses only $R$ and $2R$.
- Ignoring resistor tolerance when explaining DAC accuracy.

## Self-Check Questions

1. Why is the MSB connected through the smallest resistor in a weighted DAC?

<details>
<summary>Answer</summary>

The MSB must produce the largest current contribution, and current is inversely proportional to resistance.

</details>

2. Why is R-2R more practical than weighted-resistor DAC?

<details>
<summary>Answer</summary>

It uses only two resistor values, making fabrication and matching easier.

</details>

3. What is the binary fraction for $101_2$?

<details>
<summary>Answer</summary>

$$
101_2 = 1(2^{-1}) + 0(2^{-2}) + 1(2^{-3}) = 0.625
$$

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s11_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s14_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s16_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s18_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s23_img1.png]]

## Concept Links

- Previous topic: [DAC and ADC Fundamentals and Specifications](./06_dac_adc_fundamentals_and_specifications.md)
- Next topic: [ADC Architectures](./08_adc_architectures.md)
- Formula reference: [DAC Formulas](./10_formula_sheet_ultimate.md#dac-architecture-formulas)
