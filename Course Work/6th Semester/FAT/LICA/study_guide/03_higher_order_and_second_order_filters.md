# Higher-Order and Second-Order Filters

## Learning Objectives

After this section, you should be able to:

- Explain why higher-order filters are used.
- Relate filter order to roll-off.
- Recognize the role of the Sallen-Key topology.
- Interpret the standard second-order low-pass transfer function.
- Understand damping factor, natural frequency, and quality factor.

## Ground-Up Explanation

A first-order filter gives a roll-off of only $20$ dB/decade. If an application needs sharper separation between passband and stopband, first-order filtering is not enough. Higher-order filters solve this by cascading multiple filter sections.

Every additional order usually adds another $20$ dB/decade of roll-off. Therefore:

| Order | Approximate Roll-Off |
| ---: | --- |
| 1 | $20$ dB/decade |
| 2 | $40$ dB/decade |
| 3 | $60$ dB/decade |
| 4 | $80$ dB/decade |

## Sallen-Key Idea

The Sallen-Key filter is a common second-order active filter topology. It uses an op-amp as a voltage follower or non-inverting amplifier with an RC feedback network.

The op-amp helps because:

- It reduces loading due to high input impedance.
- It provides low output impedance.
- It allows practical cascading of sections.
- It can introduce gain if a non-inverting amplifier is used.

## Standard Second-Order Low-Pass Form

A second-order low-pass filter can be written as:

$$
\boxed{H(s) = \frac{A\omega_0^2}{s^2 + \frac{\omega_0}{Q}s + \omega_0^2}}
$$

Variables:

- $H(s)$: transfer function in the Laplace domain
- $A$: passband gain
- $\omega_0$: natural frequency in rad/s
- $Q$: quality factor
- $s$: complex frequency variable

At $s = 0$:

$$
H(0) = A
$$

At $s \to \infty$:

$$
H(\infty) = 0
$$

This confirms the response is low-pass.

## Quality Factor and Damping

The quality factor $Q$ describes selectivity or peaking. A higher $Q$ gives a sharper response and may cause peaking near cutoff. A lower $Q$ gives a more damped, smoother response.

The damping factor is often inversely related to $Q$:

$$
\boxed{Q = \frac{1}{2\zeta}}
$$

Variables:

- $Q$: quality factor
- $\zeta$: damping ratio

## Derivation Connection

The general second-order denominator is:

$$
s^2 + 2\zeta\omega_0s + \omega_0^2
$$

Using $2\zeta = 1/Q$:

$$
s^2 + \frac{\omega_0}{Q}s + \omega_0^2
$$

So the common low-pass form becomes:

$$
H(s) = \frac{A\omega_0^2}{s^2 + \frac{\omega_0}{Q}s + \omega_0^2}
$$

## Higher-Order Construction

Higher-order filters are usually not designed as one giant circuit. Instead, they are built by cascading:

- First-order sections for odd leftover order.
- Second-order sections for paired poles.

For example, a fifth-order filter can be built as:

$$
\boxed{\text{5th order} = \text{two second-order sections} + \text{one first-order section}}
$$

## Worked Example Pattern

Question: What roll-off is expected for a fourth-order low-pass filter?

Solution:

Each order contributes about $20$ dB/decade.

$$
4 \times 20 = 80\text{ dB/decade}
$$

$$
\boxed{80\text{ dB/decade}}
$$

## Common Mistakes

- Assuming a higher-order filter always means better design. Higher order increases complexity and sensitivity.
- Forgetting that high $Q$ can cause response peaking.
- Treating all second-order filters as low-pass. The topology and transfer function determine LPF, HPF, BPF, or notch behavior.
- Ignoring loading when cascading passive sections. Active buffers help prevent this.

## Self-Check Questions

1. Why are second-order sections used in higher-order filters?

<details>
<summary>Answer</summary>

They are practical building blocks for pole pairs and can be cascaded to realize higher-order responses.

</details>

2. What does $H(0)=A$ and $H(\infty)=0$ indicate?

<details>
<summary>Answer</summary>

It indicates a low-pass response because DC passes with gain $A$ and very high frequencies are attenuated.

</details>

3. What happens when $Q$ is too high?

<details>
<summary>Answer</summary>

The response may become sharply peaked or oscillatory near the natural frequency.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s20_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s23_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s24_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s27_img1.png]]

## Concept Links

- Previous topic: [First-Order Active Filters](./02_first_order_active_filters.md)
- Next topic: [Filter Approximations and Transformations](./04_filter_approximations_and_transformations.md)
- Formula reference: [Second-Order Filter Formulas](./10_formula_sheet_ultimate.md#second-order-and-higher-order-filters)
