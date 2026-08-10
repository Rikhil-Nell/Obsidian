# 04 - Clippers and Clampers

## Learning Objectives
- Understand the difference between clippers and clampers
- Analyze op-amp clipper circuits with Zener diodes
- Analyze positive and negative clamper circuits
- Determine output waveforms for given input and component values

## Ground-Up Explanation

Both clippers and clampers are **wave-shaping circuits** -- they modify the amplitude of a signal without changing its frequency.

### Clippers (Amplitude Limiters)

**Analogy**: A clipper is like a speed limiter on a vehicle. No matter how hard you press the accelerator, the speed (voltage) gets clipped at a maximum value.

An op-amp clipper uses **Zener diodes** in the feedback path of an inverting amplifier.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s17_img1.png]]

**Operation:**
- When $|V_o| < (V_Z + V_D)$: Both diodes are OFF. Circuit acts as a normal inverting amplifier: $A_v = -R_f/R_1$
- When $|V_o| \geq (V_Z + V_D)$: Zener breaks down, signal diode forward biases. Output is **clipped** at $\pm(V_Z + V_D)$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s18_img1.png]]

Using two back-to-back Zener diodes clips in **both** positive and negative directions. Between the clipping thresholds, the circuit operates as a standard linear amplifier.

### Clampers (DC Level Shifters)

**Analogy**: A clamper is like an elevator. It shifts the entire waveform up or down by a fixed amount, without changing its shape, just like an elevator moves you between floors without changing your posture.

A clamper shifts the entire input waveform up or down by adding a **fixed DC level**. Also called a **DC restorer** or **DC inserter**.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s19_img1.png]]

### Positive Clamper Operation

1. **Negative half-cycle**: Diode conducts, capacitor charges to negative peak value $V_{np}$ (to maintain virtual ground)
2. **Positive half-cycle**: Diode OFF, capacitor holds its charge, output is shifted upward

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s20_img1.png]]

### Clamper with Reference Voltage

Adding a reference voltage $V_{ref}$ at the non-inverting input shifts the clamp level:

$$V_o = V_i + V_{np} + V_{ref}$$

For **negative clamping**: Reverse the diode polarity and reference voltage. The capacitor charges during the positive half-cycle, shifting the waveform downward.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s20_img2.png]]

## Key Formulas

**Clipper threshold:**

$$\boxed{V_{clip} = \pm(V_Z + V_D)}$$

**Clipper gain (within linear region):**

$$\boxed{A_v = -\frac{R_f}{R_1} \quad \text{for } |V_o| < V_{clip}}$$

**Positive clamper output:**

$$\boxed{V_o = V_i + V_{np}}$$

**Clamper with reference:**

$$\boxed{V_o = V_i + V_{np} + V_{ref}}$$

## Common Mistakes

1. **Confusing clippers and clampers**: Clippers *limit* the amplitude; clampers *shift* the DC level
2. **Zener diode direction**: The Zener must be reverse-biased for breakdown. The signal diode is in series to ensure proper conduction direction
3. **Forgetting $V_D$**: The clipping level is $V_Z + V_D$ (0.7V for signal diode), not just $V_Z$
4. **Clamper time constant**: The RC time constant must be much larger than the signal period for proper clamping

## Self-Check Questions

> [!question]- If $V_Z = 5V$ and $V_D = 0.7V$, what are the clipping levels?
> $\pm(5 + 0.7) = \pm 5.7V$

> [!question]- What is the key difference between a clipper and a clamper?
> A clipper removes parts of the waveform beyond a threshold (changes shape). A clamper shifts the entire waveform up or down without changing its shape.

> [!question]- In a positive clamper, during which half-cycle does the diode conduct?
> During the negative half-cycle. The diode conducts to charge the capacitor.

## Concept Links
- Prerequisite: [Precision Rectifier](./03_precision_rectifier.md) (uses similar diode-op-amp feedback)
- Related: [Comparator](./06_comparator_and_schmitt_trigger.md) (threshold-based switching)
- Formulas: [Formula Sheet - Clippers & Clampers](./15_formula_sheet_ultimate.md#clippers-and-clampers)
