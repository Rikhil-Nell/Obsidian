# Formula Sheet Ultimate

## Physical Constants and Core Relations

| Quantity | Formula | Units |
| --- | --- | --- |
| Angular frequency | $\omega = 2\pi f$ | rad/s |
| Frequency | $f = \omega/(2\pi)$ | Hz |
| Capacitive reactance | $X_C = 1/(2\pi f C)$ | ohms |
| Decibel voltage gain | $A_{dB} = 20\log_{10}|A_v|$ | dB |

## Unit Conversion Reference

| Prefix | Value |
| --- | ---: |
| kilo, k | $10^3$ |
| mega, M | $10^6$ |
| milli, m | $10^{-3}$ |
| micro, $\mu$ | $10^{-6}$ |
| nano, n | $10^{-9}$ |
| pico, p | $10^{-12}$ |

## Filter Fundamentals

### Transfer Function

$$
\boxed{H(j\omega) = \frac{V_o(j\omega)}{V_i(j\omega)}}
$$

Variables:

- $V_o$: output voltage
- $V_i$: input voltage
- $\omega$: angular frequency

### Roll-Off Rule

$$
\boxed{\text{Roll-off} \approx 20N\text{ dB/decade}}
$$

Variables:

- $N$: filter order

## First-Order Active Filters

### Cutoff Frequency

$$
\boxed{f_c = \frac{1}{2\pi RC}}
$$

For low-pass:

$$
\boxed{f_H = \frac{1}{2\pi RC}}
$$

For high-pass:

$$
\boxed{f_L = \frac{1}{2\pi RC}}
$$

### Resistance From Cutoff

$$
\boxed{R = \frac{1}{2\pi f_c C}}
$$

### Capacitance From Cutoff

$$
\boxed{C = \frac{1}{2\pi f_c R}}
$$

### Non-Inverting Passband Gain

$$
\boxed{A = 1 + \frac{R_f}{R_i}}
$$

### Inverting Gain Magnitude

$$
\boxed{|A| = \frac{R_f}{R_i}}
$$

### Band-Pass Condition

$$
\boxed{f_H > f_L}
$$

For a clearer passband:

$$
\boxed{f_H \ge 10f_L}
$$

### Band-Reject Wideband Condition

$$
\boxed{f_L > f_H}
$$

## Second-Order and Higher-Order Filters

### Standard Second-Order Low-Pass Transfer Function

$$
\boxed{H(s) = \frac{A\omega_0^2}{s^2 + \frac{\omega_0}{Q}s + \omega_0^2}}
$$

Variables:

- $A$: passband gain
- $\omega_0$: natural angular frequency
- $Q$: quality factor

### Quality Factor and Damping Ratio

$$
\boxed{Q = \frac{1}{2\zeta}}
$$

Variables:

- $Q$: quality factor
- $\zeta$: damping ratio

### Cascading Order

$$
\boxed{\text{Total order} = \text{sum of section orders}}
$$

## Filter Approximations

### Butterworth Magnitude Response

$$
\boxed{|H(j\omega)| = \frac{A}{\sqrt{1 + \left(\frac{\omega}{\omega_c}\right)^{2N}}}}
$$

### Chebyshev Magnitude Response

$$
\boxed{|H(j\omega)| = \frac{A}{\sqrt{1 + \epsilon^2 C_N^2\left(\frac{\omega}{\omega_c}\right)}}}
$$

### Chebyshev Ripple Limits

Maximum passband value:

$$
\boxed{|H|_{max} = A}
$$

Minimum passband value:

$$
\boxed{|H|_{min} = \frac{A}{\sqrt{1+\epsilon^2}}}
$$

## Switched-Capacitor Filters

### Charge Transfer

$$
\boxed{\Delta Q = C(V_1 - V_2)}
$$

### Average Current

$$
\boxed{i = C f_{ck}(V_1 - V_2)}
$$

### Equivalent Resistance

$$
\boxed{R_{eq} = \frac{1}{C f_{ck}}}
$$

Variables:

- $C$: switched capacitance
- $f_{ck}$: clock frequency
- $R_{eq}$: equivalent resistance

## DAC and ADC Specifications

### DAC Resolution

$$
\boxed{\text{Resolution} = \frac{V_{FS}}{2^n - 1}}
$$

Variables:

- $V_{FS}$: full-scale voltage
- $n$: number of bits

### Ideal DAC Output From Code

For integer code $D_{int}$:

$$
\boxed{V_o = D_{int}\left(\frac{V_{FS}}{2^n - 1}\right)}
$$

### Error

$$
\boxed{V_{error} = V_{actual} - V_{ideal}}
$$

### Error in LSB

$$
\boxed{\text{Error in LSB} = \frac{V_{error}}{\text{LSB size}}}
$$

### Gain Error

$$
\boxed{\text{Gain error} = \text{Full-scale error} - \text{Offset error}}
$$

## DAC Architecture Formulas

### Binary Fraction

$$
\boxed{D = b_1 2^{-1} + b_2 2^{-2} + \cdots + b_n 2^{-n}}
$$

### DAC Output From Binary Fraction

$$
\boxed{V_o = K V_{FS}D}
$$

### Weighted-Resistor DAC Output

$$
\boxed{V_o = -R_f V_R\left(\frac{b_1}{R} + \frac{b_2}{2R} + \frac{b_3}{4R} + \cdots + \frac{b_n}{2^{n-1}R}\right)}
$$

If $R_f = R$:

$$
\boxed{V_o = -V_R\left(b_1 + \frac{b_2}{2} + \frac{b_3}{4} + \cdots + \frac{b_n}{2^{n-1}}\right)}
$$

## ADC Architecture Formulas

### Flash ADC Comparator Count

$$
\boxed{N_c = 2^n - 1}
$$

Variables:

- $N_c$: number of comparators
- $n$: number of output bits

### SAR ADC Conversion Steps

$$
\boxed{\text{Approximate comparison steps} = n}
$$

## Quick Reference Tables

### Filter Approximation Choice

| Need | Choose |
| --- | --- |
| Smooth passband, no ripple | Butterworth |
| Faster cutoff, passband ripple allowed | Chebyshev |
| Sharpest cutoff, ripple in both bands allowed | Elliptic |

### Converter Architecture Choice

| Need | Choose |
| --- | --- |
| Fastest ADC | Flash |
| Balanced speed and complexity | SAR |
| Simple slow conversion | Single-slope |
| Simple low-resolution DAC | Weighted-resistor |
| Practical higher-resolution DAC | R-2R ladder |

## Sign Conventions Summary

- Inverting op-amp DAC outputs often carry a negative sign.
- Magnitude response formulas usually use absolute value, so sign is ignored for gain.
- Use $f_H$ for high cutoff and $f_L$ for low cutoff.
- Use $\omega = 2\pi f$ when converting from Hz to rad/s.

## Pre-Submission Checklist

- Convert all capacitances to farads before substituting.
- Convert kHz, MHz, pF, nF, and microfarads carefully.
- Box the final answer.
- Include units in every numerical answer.
- For DAC problems, state whether using binary fraction or integer-code method.
- For ADC problems, mention speed and hardware trade-off if asked conceptually.
