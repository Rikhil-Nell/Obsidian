# Worked Problems

## Coverage Note

The extraction found two entries in `problems.json`, both from Unit 6 page 10, but their problem text was empty and only specification definitions were captured. The structured slide text also contains explicit numerical problems on DAC accuracy and DAC output. Those are solved here.

## Problem 1: DAC Accuracy Error

Source topic: [DAC Specifications](./06_dac_adc_fundamentals_and_specifications.md#dac-specifications)

Formula reference: [DAC Resolution](./10_formula_sheet_ultimate.md#dac-and-adc-specifications)

### Problem

If $V_{FS}$ of a 3-bit DAC is $8$ V and the output voltage for input $101_2$ is $5.9$ V, find the error.

### Concepts Used

- Resolution
- Ideal DAC output
- Absolute error
- Error in LSB

### Solution

For a 3-bit DAC:

$$
2^n - 1 = 2^3 - 1 = 7
$$

Step size:

$$
\text{Step size} = \frac{V_{FS}}{2^n - 1}
$$

$$
\text{Step size} = \frac{8}{7} = 1.14\text{ V}
$$

Binary input:

$$
101_2 = 5_{10}
$$

Ideal output:

$$
V_{ideal} = 5(1.14) = 5.7\text{ V}
$$

Actual output:

$$
V_{actual} = 5.9\text{ V}
$$

Absolute error:

$$
V_{error} = V_{actual} - V_{ideal}
$$

$$
V_{error} = 5.9 - 5.7 = 0.2\text{ V}
$$

Error in LSB:

$$
\text{Error in LSB} = \frac{0.2}{1.14} = 0.175
$$

$$
\boxed{V_{error} = 0.2\text{ V} = 0.175\text{ LSB}}
$$

### Verification

The calculated ideal output $5.7$ V is close to the actual $5.9$ V, so the small positive error is reasonable.

## Problem 2: DAC Output for 3-Bit Code

Source topic: [DAC Architectures](./07_dac_architectures.md#binary-fraction-example)

Formula reference: [DAC Binary Fraction](./10_formula_sheet_ultimate.md#dac-architecture-formulas)

### Problem

For a full-scale output voltage of $8$ V, find the analog output voltage of binary data $101_2$. Assume scaling factor $K = 1$.

### Solution

Binary fraction:

$$
D = 1(2^{-1}) + 0(2^{-2}) + 1(2^{-3})
$$

$$
D = \frac{1}{2} + 0 + \frac{1}{8}
$$

$$
D = 0.625
$$

DAC output:

$$
V_o = K V_{FS}D
$$

$$
V_o = 1(8)(0.625)
$$

$$
\boxed{V_o = 5\text{ V}}
$$

### Verification

The maximum 3-bit code is $111_2$, which corresponds to $0.875$ in this binary fractional weighting. The code $101_2$ should be below full scale, and $5$ V is below $8$ V.

## Problem 3: DAC Output for 4-Bit Code

Source topic: [DAC Architectures](./07_dac_architectures.md#worked-example)

Formula reference: [DAC Binary Fraction](./10_formula_sheet_ultimate.md#dac-architecture-formulas)

### Problem

For a full-scale output voltage of $16$ V, find the analog output voltage of binary data $1101_2$. Assume scaling factor $K = 1$.

### Solution

Binary fraction:

$$
D = 1(2^{-1}) + 1(2^{-2}) + 0(2^{-3}) + 1(2^{-4})
$$

$$
D = \frac{1}{2} + \frac{1}{4} + 0 + \frac{1}{16}
$$

$$
D = 0.8125
$$

Output voltage:

$$
V_o = K V_{FS}D
$$

$$
V_o = 1(16)(0.8125)
$$

$$
\boxed{V_o = 13\text{ V}}
$$

### Verification

The input $1101_2$ is large but not the maximum 4-bit code, so output should be close to but below $16$ V. $13$ V is reasonable.

## Problem 4: First-Order Filter Resistance

Source topic: [First-Order Active Filters](./02_first_order_active_filters.md#worked-examples)

Formula reference: [First-Order Filter Formulas](./10_formula_sheet_ultimate.md#first-order-active-filters)

### Problem

Design a first-order low-pass filter with cutoff frequency $f_H = 1$ kHz using $C = 0.01\ \mu F$. Find $R$.

### Solution

Convert capacitance:

$$
C = 0.01\ \mu F = 10^{-8}F
$$

Formula:

$$
f_H = \frac{1}{2\pi RC}
$$

Rearrange:

$$
R = \frac{1}{2\pi f_H C}
$$

Substitute:

$$
R = \frac{1}{2\pi(1000)(10^{-8})}
$$

$$
R = 15915\ \Omega
$$

$$
\boxed{R \approx 15.9\ k\Omega}
$$

### Verification

Using $R = 15.9\ k\Omega$ and $C = 10^{-8}F$ gives a cutoff close to $1$ kHz.

## Problem 5: Switched-Capacitor Equivalent Resistance

Source topic: [Switched-Capacitor Filters](./05_all_pass_state_variable_and_switched_capacitor_filters.md#worked-example)

Formula reference: [Switched-Capacitor Formulas](./10_formula_sheet_ultimate.md#switched-capacitor-filters)

### Problem

Find the equivalent resistance of a switched capacitor with $C = 10$ pF and $f_{ck} = 100$ kHz.

### Solution

Convert:

$$
C = 10 \times 10^{-12}F
$$

$$
f_{ck} = 100 \times 10^3Hz
$$

Formula:

$$
R_{eq} = \frac{1}{C f_{ck}}
$$

Substitute:

$$
R_{eq} = \frac{1}{(10 \times 10^{-12})(100 \times 10^3)}
$$

$$
R_{eq} = 10^6\ \Omega
$$

$$
\boxed{R_{eq} = 1\ M\Omega}
$$

### Verification

A tiny capacitor switched at a moderate clock frequency producing a large equivalent resistance is expected in SC filter design.

## Problem 6: Flash ADC Comparator Count

Source topic: [ADC Architectures](./08_adc_architectures.md#worked-example-pattern)

Formula reference: [ADC Architecture Formulas](./10_formula_sheet_ultimate.md#adc-architecture-formulas)

### Problem

How many comparators are required for a 3-bit flash ADC?

### Solution

Formula:

$$
N_c = 2^n - 1
$$

Substitute:

$$
N_c = 2^3 - 1 = 7
$$

$$
\boxed{7\text{ comparators}}
$$

### Verification

Flash ADC hardware grows exponentially with bit count, so $7$ comparators for only $3$ bits is consistent.

## Source Images for Problem Slides

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s5_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s13_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s13_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s47_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-08_Reference-Material-I_s25_img1.png]]
