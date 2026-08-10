# Filter Approximations and Transformations

## Learning Objectives

After this section, you should be able to:

- Explain why prototype low-pass filters are used.
- Distinguish Butterworth, Chebyshev, and elliptic approximations.
- Relate passband ripple and stopband attenuation to filter order.
- Understand why frequency transformations save design effort.

## Ground-Up Explanation

Designing every filter from zero is inefficient. Instead, filter design often starts with a normalized low-pass prototype. Once a good low-pass response is designed, frequency transformation converts it into a required low-pass, high-pass, band-pass, or band-reject response.

The approximation family determines the shape of the magnitude response. The three major families in the extracted slides are Butterworth, Chebyshev, and elliptic.

## Frequency Transformation

Frequency transformation modifies the prototype transfer function $H_p(s)$ into a new transfer function $H(s)$ for the desired filter.

The idea is:

$$
\boxed{H_p(s)\ \longrightarrow\ H(s)}
$$

For exams, remember the purpose more than the full algebra unless the transformation equations are given:

- Start with normalized low-pass design.
- Decide required cutoff or band edges.
- Transform the frequency variable.
- Realize the final circuit from the transformed transfer function.

## Butterworth Filters

Butterworth filters are maximally flat in the passband. This means the response has no ripple and stays smooth near low frequencies.

The normalized magnitude response is commonly written as:

$$
\boxed{|H(j\omega)| = \frac{A}{\sqrt{1 + \left(\frac{\omega}{\omega_c}\right)^{2N}}}}
$$

Variables:

- $A$: passband gain
- $\omega$: angular frequency in rad/s
- $\omega_c$: cutoff angular frequency in rad/s
- $N$: filter order

Key points:

- Smoothest passband.
- No ripple.
- Slower transition than Chebyshev or elliptic for the same order.
- Higher order may be needed for tight stopband specs.

## Chebyshev Filters

Chebyshev filters accept controlled ripple in the passband to get a sharper transition from passband to stopband.

A common magnitude response form is:

$$
\boxed{|H(j\omega)| = \frac{A}{\sqrt{1 + \epsilon^2 C_N^2\left(\frac{\omega}{\omega_c}\right)}}}
$$

Variables:

- $\epsilon$: ripple factor
- $C_N$: Chebyshev polynomial of order $N$
- $N$: filter order
- $\omega_c$: cutoff angular frequency

Inside the passband, $C_N$ oscillates between $-1$ and $+1$. Therefore $C_N^2$ lies between $0$ and $1$, causing equiripple behavior.

The response oscillates between:

$$
A
$$

and:

$$
\frac{A}{\sqrt{1+\epsilon^2}}
$$

In the stopband, $C_N$ grows quickly, so attenuation increases sharply.

## Elliptic Filters

Elliptic filters, also called Cauer filters, have ripple in both passband and stopband. Their biggest advantage is that they produce the sharpest transition for a given order.

Key points:

- Equiripple passband.
- Equiripple stopband.
- Transmission zeros improve stopband attenuation.
- Usually minimum order for a given set of specifications.
- More mathematically complex than Butterworth and Chebyshev.

## Comparison Table

| Approximation | Passband | Stopband | Transition Sharpness | Best Use |
| --- | --- | --- | --- | --- |
| Butterworth | Maximally flat | Monotonic | Slowest | When smooth passband is important |
| Chebyshev | Ripple | Monotonic | Sharper | When sharper cutoff is needed with acceptable passband ripple |
| Elliptic | Ripple | Ripple | Sharpest | When minimum order is required |

## Design Trade-Offs

Filter design is always a trade-off:

- More flatness usually means slower cutoff.
- Faster cutoff usually means ripple or more complexity.
- Lower order means simpler hardware.
- Higher order means sharper filtering but greater sensitivity.

## Worked Example Pattern

Question: Which filter approximation should be chosen when the passband must be maximally flat?

Solution:

Butterworth is maximally flat in the passband and has no ripple.

$$
\boxed{\text{Butterworth filter}}
$$

Question: Which filter gives the sharpest transition for a given order?

Solution:

Elliptic filters use ripple in both passband and stopband and include transmission zeros.

$$
\boxed{\text{Elliptic filter}}
$$

## Common Mistakes

- Saying Chebyshev is flat. It is not flat; it has controlled ripple.
- Saying elliptic is always preferred. It is sharper, but more complex and has ripple in both bands.
- Forgetting that Butterworth may need a higher order to meet the same stopband attenuation.
- Confusing filter order $N$ with number of op-amps. Implementation depends on section realization.

## Self-Check Questions

1. Why does Chebyshev roll off faster than Butterworth?

<details>
<summary>Answer</summary>

It allows controlled passband ripple, which permits a sharper transition to the stopband for the same order.

</details>

2. What is the main advantage of elliptic filters?

<details>
<summary>Answer</summary>

They usually achieve the required specifications with the minimum order.

</details>

3. What is the main drawback of Butterworth filters?

<details>
<summary>Answer</summary>

For a given order, the transition is less sharp than Chebyshev or elliptic filters.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s28_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s29_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s33_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s38_img1.png]]

## Concept Links

- Previous topic: [Higher-Order and Second-Order Filters](./03_higher_order_and_second_order_filters.md)
- Next topic: [All-Pass, State-Variable, and Switched-Capacitor Filters](./05_all_pass_state_variable_and_switched_capacitor_filters.md)
- Formula reference: [Filter Approximation Formulas](./10_formula_sheet_ultimate.md#filter-approximations)
