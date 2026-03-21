# 03 - Precision Rectifier

## Learning Objectives
- Understand why ordinary diode rectifiers fail for small signals
- Explain how op-amp feedback eliminates the diode voltage drop
- Analyze half-wave, inverse half-wave, full-wave, and absolute value circuits
- Determine output waveforms for given inputs

## Ground-Up Explanation

### The Problem with Ordinary Rectifiers

Standard silicon diodes have a **cut-in voltage of ~0.7V**. If your signal is smaller than 0.7V (common in sensor applications), the diode won't even turn on, and no rectification occurs. Even for larger signals, the 0.7V drop introduces significant error.

**Analogy**: Imagine a toll booth (diode) that charges 0.7 rupees. If you're carrying only 0.5 rupees (small signal), you can't pass through at all. A precision rectifier is like having a friend with infinite money (op-amp) who pays the toll for you, so even a 1-paisa signal can pass through perfectly.

### How Precision Rectifier Works (Super Diode)

The op-amp has a very high open-loop gain ($\sim 10^5$). With the diode in the feedback path:
- Even if $V_{in}$ is just $7 \mu V$, the op-amp amplifies it to $10^5 \times 7\mu V = 0.7V$ -- just enough to turn on the diode
- The diode drop is effectively divided by the open-loop gain, becoming negligible ($0.7V / 10^5 = 7 \mu V$)
- Result: the circuit behaves as if the diode has **zero cut-in voltage**

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s12_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s12_img2.png]]

### Precision Half-Wave Rectifier Operation

**For $V_i > 0$:**
- Op-amp output goes positive
- Diode D conducts (forward biased)
- Feedback loop closes through the diode
- Output: $V_o = V_i$ (positive half appears at output)

**For $V_i < 0$:**
- Op-amp output goes negative
- Diode D is reverse biased (OFF)
- No feedback path, output = 0V
- Op-amp output saturates at $-V_{sat}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s13_img1.png]]

### Precision Inverse Half-Wave Rectifier

By reversing the diode direction, the output passes only the **negative** half-cycle:

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s14_img1.png]]

### Precision Full-Wave Rectifier

Uses two diodes and a summing amplifier:

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s15_img1.png]]

**When $V_i > 0$:** D1 OFF, D2 ON. $V_o' = -V_i$. The summing amplifier combines $V_o'$ and $V_i$:
$$V_o = -2V_o' - V_i = -2(-V_i) - V_i = V_i$$

**When $V_i < 0$:** D1 ON, D2 OFF. $V_o' = 0$. Only $V_i$ feeds the summing amplifier:
$$V_o = -V_i$$

Result: Output is always positive = $|V_i|$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s15_img2.png]]

### Absolute Value Circuit

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s16_img1.png]]

Converts negative values to positive. The full-wave rectifier output = $|V_i|$, hence called the **absolute value rectifier**.

## Key Formulas

**Effective diode drop in precision rectifier:**

$$\boxed{V_{D,eff} = \frac{V_D}{A_{OL}} \approx 0}$$

**Half-wave output:**

$$\boxed{V_o = \begin{cases} V_i & \text{if } V_i > 0 \\ 0 & \text{if } V_i < 0 \end{cases}}$$

**Full-wave output:**

$$\boxed{V_o = |V_i|}$$

## Common Mistakes

1. **Forgetting op-amp saturation**: When diode is OFF, the op-amp output goes to $-V_{sat}$, not 0V. The *circuit* output is 0V, but the op-amp itself saturates.
2. **Confusing half-wave and inverse half-wave**: Reversing the diode direction inverts which half-cycle passes through.
3. **Full-wave analysis**: You must analyze both half-cycles separately and apply superposition for the summing amplifier stage.

## Self-Check Questions

> [!question]- Why is a precision rectifier also called a "super diode"?
> Because it behaves as a diode with zero cut-in voltage, effectively being a "perfect" or "super" version of a regular diode.

> [!question]- What is the minimum input signal that a precision rectifier can handle?
> Theoretically, the minimum signal is $V_D / A_{OL} \approx 7\mu V$ for a typical op-amp. Practically, it's limited by op-amp offset voltage and noise.

> [!question]- How many op-amps are needed for a precision full-wave rectifier?
> Two: one for the precision half-wave stage (with diodes) and one for the summing amplifier stage.

## Concept Links
- Prerequisite: Op-Amp inverting/non-inverting configurations
- Next: [Clippers & Clampers](./04_clippers_and_clampers.md) (also wave-shaping circuits)
- Related: [Comparator](./06_comparator_and_schmitt_trigger.md) (also uses diodes and thresholds)
- Formulas: [Formula Sheet - Precision Rectifier](./15_formula_sheet_ultimate.md#precision-rectifier)
