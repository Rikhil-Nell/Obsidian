# ADC Architectures

## Learning Objectives

After this section, you should be able to:

- Explain the purpose of an ADC.
- Compare flash, successive approximation, and single-slope ADCs.
- Identify speed, complexity, and resolution trade-offs.
- Answer block-diagram and operation-sequence exam questions.

## Ground-Up Explanation

An ADC converts an analog input voltage into a digital code. Since the analog input can take infinitely many values but the digital output has finite codes, the ADC must sample and quantize the input.

The architectures in the source material are:

- Flash ADC
- Successive approximation ADC
- Single-slope ADC

## Flash ADC

A flash ADC is the fastest common ADC architecture. It uses many comparators in parallel. Each comparator checks whether the input is above a reference threshold.

For an $n$-bit flash ADC:

$$
\boxed{\text{Number of comparators} = 2^n - 1}
$$

Advantages:

- Very fast conversion.
- All comparisons happen at the same time.
- Good for high-speed applications.

Disadvantages:

- Requires many comparators.
- Hardware grows rapidly with bit count.
- High power and area for larger resolution.

## Successive Approximation ADC

A successive approximation register ADC, or SAR ADC, finds the digital output one bit at a time. It uses a DAC internally and compares the DAC output with the input.

Basic sequence:

1. Set MSB to $1$.
2. DAC generates a trial analog voltage.
3. Comparator checks whether input is higher or lower.
4. Keep or clear the bit.
5. Move to the next bit.
6. Repeat until LSB is decided.

For an $n$-bit SAR ADC, conversion usually takes $n$ comparison steps.

Advantages:

- Good balance of speed and complexity.
- Much less hardware than flash ADC.
- Common in medium-speed, medium-resolution systems.

Disadvantages:

- Slower than flash ADC.
- Needs accurate DAC and comparator.

## Single-Slope ADC

A single-slope ADC compares the input voltage with a ramp voltage. A counter runs while the ramp rises. When the ramp equals the input, the comparator stops the count. The count value represents the input.

Basic blocks:

- Ramp generator
- Comparator
- Counter
- Clock
- Control logic

Advantages:

- Simple circuit.
- Low hardware complexity.
- Useful for slow measurements.

Disadvantages:

- Slow conversion.
- Accuracy depends on ramp linearity and clock stability.
- Not suitable for high-speed signals.

## Comparison Table

| ADC Type | Speed | Hardware Complexity | Best Use |
| --- | --- | --- | --- |
| Flash | Highest | Very high | Very high-speed conversion |
| SAR | Medium to high | Medium | General-purpose data acquisition |
| Single-slope | Low | Low | Slow measurement systems |

## Worked Example Pattern

Question: How many comparators are required for a 3-bit flash ADC?

Use:

$$
2^n - 1
$$

Substitute:

$$
2^3 - 1 = 7
$$

$$
\boxed{7\text{ comparators}}
$$

## Common Mistakes

- Saying flash ADC is hardware efficient. It is fast but hardware-heavy.
- Saying SAR decides all bits at once. It decides bits sequentially.
- Forgetting single-slope ADC depends on ramp linearity.
- Confusing DAC and ADC direction: DAC is digital-to-analog; ADC is analog-to-digital.

## Self-Check Questions

1. Which ADC is fastest?

<details>
<summary>Answer</summary>

Flash ADC.

</details>

2. How many comparisons does an $n$-bit SAR ADC generally require?

<details>
<summary>Answer</summary>

About $n$ comparison steps, one per bit.

</details>

3. Why is single-slope ADC slow?

<details>
<summary>Answer</summary>

It waits for a ramp to reach the input voltage, so conversion time depends on input level and ramp rate.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s25_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s26_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s27_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s28_img1.png]]

## Concept Links

- Previous topic: [DAC Architectures](./07_dac_architectures.md)
- Worked practice: [Worked Problems](./09_worked_problems.md)
- Formula reference: [ADC Architecture Formulas](./10_formula_sheet_ultimate.md#adc-architecture-formulas)
