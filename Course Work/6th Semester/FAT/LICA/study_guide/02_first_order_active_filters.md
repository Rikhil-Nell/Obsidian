# First-Order Active Filters

## Learning Objectives

After this section, you should be able to:

- Design first-order low-pass and high-pass active filters.
- Explain cutoff frequency, passband, stopband, and roll-off.
- Distinguish unity-gain and variable-gain versions.
- Build band-pass and band-reject filters from LPF and HPF blocks.
- Solve direct $R$, $C$, and cutoff-frequency problems.

## Ground-Up Explanation

A first-order active filter contains one energy-storage element, usually one capacitor, along with resistors and an op-amp. The word "first-order" means the transfer function has one dominant power of $s$ in the denominator. This gives a slope of about $20$ dB/decade outside the passband.

In active filter design, the op-amp provides buffering and sometimes gain. The RC network sets the cutoff frequency.

## Cutoff Frequency

The cutoff frequency is the frequency at which the output magnitude becomes $0.707$ times the passband value. In decibels this is the $-3$ dB point.

For a first-order RC filter:

$$
\boxed{f_c = \frac{1}{2\pi RC}}
$$

Depending on the filter type, this may be written as:

$$
\boxed{f_H = \frac{1}{2\pi RC}}
$$

for low-pass filters, or:

$$
\boxed{f_L = \frac{1}{2\pi RC}}
$$

for high-pass filters.

Variables:

- $f_c$: cutoff frequency in Hz
- $f_H$: high cutoff frequency in Hz
- $f_L$: low cutoff frequency in Hz
- $R$: resistance in ohms
- $C$: capacitance in farads

## First-Order Low-Pass Filter

A low-pass filter passes low frequencies and attenuates high frequencies.

For $f < f_H$, gain is approximately equal to passband gain $A$. For $f = f_H$, gain falls to $0.707A$. For $f > f_H$, gain decreases at about $20$ dB/decade.

### Design Steps

1. Choose the high cutoff frequency $f_H$.
2. Select a practical capacitor value, usually less than $1\ \mu F$.
3. Calculate $R$ using $R = 1/(2\pi f_H C)$.
4. Choose $R_i$ and $R_f$ for the required passband gain.

For a non-inverting op-amp gain:

$$
\boxed{A = 1 + \frac{R_f}{R_i}}
$$

## First-Order High-Pass Filter

A high-pass filter attenuates low frequencies and passes high frequencies. It is useful when low-frequency drift, hum, or DC components must be removed.

For $f < f_L$, the signal is attenuated. For $f > f_L$, the gain approaches passband gain $A$.

The cutoff is:

$$
\boxed{f_L = \frac{1}{2\pi RC}}
$$

## Band-Pass Filter From First-Order Sections

A first-order band-pass filter can be made by cascading:

1. A high-pass section with cutoff $f_L$.
2. A low-pass section with cutoff $f_H$.

For a valid passband:

$$
\boxed{f_H > f_L}
$$

For a clear passband, a useful design guideline is:

$$
\boxed{f_H \ge 10f_L}
$$

The passband exists between $f_L$ and $f_H$.

## Band-Reject Filter From First-Order Sections

A band-reject filter passes frequencies below and above a rejected middle band. It can be formed by connecting an LPF and HPF in parallel and adding their outputs using a summing amplifier.

For a wideband band-reject filter:

$$
\boxed{f_L > f_H}
$$

The rejected band lies between $f_H$ and $f_L$.

## Analogies

- LPF: A tea strainer that lets fine liquid through but blocks larger particles.
- HPF: A speed bump that stops slow movement but lets fast changes through.
- BPF: A window that only opens for one range of frequencies.
- BRF: A "do not enter" zone for one band of frequencies.

## Worked Examples

### Example 1: Find Resistance for LPF

Design a first-order LPF with $f_H = 1\text{ kHz}$ and $C = 0.01\ \mu F$.

Convert:

$$
C = 0.01\ \mu F = 0.01 \times 10^{-6}F = 10^{-8}F
$$

Use:

$$
R = \frac{1}{2\pi f_H C}
$$

Substitute:

$$
R = \frac{1}{2\pi(1000)(10^{-8})}
$$

$$
R \approx 15915\ \Omega
$$

$$
\boxed{R \approx 15.9\ k\Omega}
$$

### Example 2: Identify Band-Pass Validity

Given $f_L = 200\text{ Hz}$ and $f_H = 2\text{ kHz}$, check whether a first-order BPF has a clear passband.

$$
10f_L = 10(200) = 2000\text{ Hz}
$$

Since $f_H = 2000\text{ Hz}$, the condition $f_H \ge 10f_L$ is satisfied.

$$
\boxed{\text{Clear passband from }200\text{ Hz to }2\text{ kHz}}
$$

## Common Mistakes

- Using microfarads directly without converting to farads.
- Interchanging LPF and HPF cutoff notation.
- Forgetting that first-order roll-off is only $20$ dB/decade.
- For BPF, using $f_H < f_L$ by mistake.
- For band-reject, forgetting that the LPF and HPF outputs are summed in parallel.

## Self-Check Questions

1. What is the gain at cutoff relative to passband gain?

<details>
<summary>Answer</summary>

It is $0.707$ times the passband gain, or $-3$ dB.

</details>

2. How do you make a first-order band-pass filter?

<details>
<summary>Answer</summary>

Cascade a first-order HPF and a first-order LPF, with $f_H > f_L$.

</details>

3. What is the roll-off of a first-order filter?

<details>
<summary>Answer</summary>

Approximately $20$ dB/decade or $6$ dB/octave.

</details>

## Source Images

Representative extracted slide images:

- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s11_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s13_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s14_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s17_img1.png]]
- ![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-04-07_Reference-Material-I_s19_img1.png]]

## Concept Links

- Previous topic: [Filter Fundamentals](./01_filter_fundamentals.md)
- Next topic: [Higher-Order and Second-Order Filters](./03_higher_order_and_second_order_filters.md)
- Formula reference: [First-Order Filter Formulas](./10_formula_sheet_ultimate.md#first-order-active-filters)
