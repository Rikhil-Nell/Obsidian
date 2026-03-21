# 01 - Differential Amplifier

## Learning Objectives
- Understand the working of a differential amplifier using a single op-amp
- Analyze the two-op-amp differential amplifier configuration
- Calculate differential gain, common-mode gain, and CMRR
- Understand the role of resistor matching in CMRR

## Ground-Up Explanation

A **differential amplifier** amplifies the *difference* between two input signals while rejecting any signal common to both. Think of it like noise-cancelling headphones: the microphone picks up ambient noise (common-mode) and the speaker produces the inverse to cancel it, leaving only the desired audio (differential signal).

### Single Op-Amp Configuration

The basic differential amplifier uses one op-amp with four resistors. It is essentially a combination of an inverting and non-inverting amplifier applied simultaneously via the superposition principle.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s2_img1.png]]

**How it works:**
- Apply superposition: ground one input at a time
- When $V_{i2} = 0$: circuit acts as an **inverting amplifier** for $V_{i1}$, giving $V_{o1} = -\frac{R_2}{R_1}V_{i1}$
- When $V_{i1} = 0$: circuit acts as a **non-inverting amplifier** for $V_{i2}$, giving $V_{o2} = \frac{R_2}{R_1}V_{i2}$ (when $R_1 = R_3$ and $R_2 = R_4$)
- Combined output: $V_o = V_{o1} + V_{o2}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s2_img2.png]]

### Two Op-Amp Configuration

For higher gain and input resistance, a two-op-amp configuration is used.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s3_img1.png]]

- First stage (A1): Non-inverting amplifier producing $V_{o1}$
- Second stage (A2): Differential amplifier amplifying $V_{i2}$ and $V_{o1}$
- With $R_f = R_3$ and $R_1 = R_2$, output depends on $V_{id} = V_{i2} - V_{i1}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s4_img1.png]]

## Key Formulas

For equal resistors ($R_1 = R_2 = R_3 = R_f$):

$$\boxed{V_o = V_{i1} - V_{i2}}$$

For unequal resistors:

$$\boxed{V_o = \frac{R_2}{R_1}(V_{i2} - V_{i1})}$$

**Common-Mode Rejection Ratio:**

$$\boxed{CMRR = \frac{A_d}{A_{cm}}}$$

Where:
- $A_d$ = Differential gain = $\frac{R_2}{R_1}$
- $A_{cm}$ = Common-mode gain (ideally 0, practically small due to resistor mismatch)
- $V_{cm} = \frac{V_{i1} + V_{i2}}{2}$ (common-mode voltage)

## Analogies

- **Balance scale**: The diff amp works like a precision balance. It measures the *difference* in weight between two pans, ignoring the table it sits on (common-mode). If the table shakes (noise), both pans move equally and the reading stays the same.

## Common Mistakes

1. **Forgetting superposition**: You must analyze each input separately, then add results
2. **Resistor mismatch**: Even 1% mismatch degrades CMRR significantly
3. **Confusing differential and difference amplifier**: In this course, "differential amplifier" with a single op-amp IS the difference amplifier
4. **Sign convention**: The inverting input contributes a negative sign to the output

## Self-Check Questions

> [!question]- What happens to CMRR if resistors are perfectly matched?
> CMRR becomes infinite (ideal case). $A_{cm} = 0$, so $CMRR = \frac{A_d}{0} = \infty$.

> [!question]- Why is the two-op-amp configuration preferred over single op-amp?
> Higher input resistance, higher gain without degrading CMRR, and better performance for high-impedance sources.

> [!question]- If $R_1 = 10k\Omega$ and $R_2 = 100k\Omega$, what is the differential gain?
> $A_d = R_2/R_1 = 100k/10k = 10$

## Concept Links
- Prerequisite: Op-Amp Basics (inverting + non-inverting configurations)
- Next: [Instrumentation Amplifier](./02_instrumentation_amplifier.md) (builds on diff amp limitations)
- Related: [Comparator](./06_comparator_and_schmitt_trigger.md) (open-loop differential operation)
- Formulas: [Formula Sheet - Differential Amplifier](./15_formula_sheet_ultimate.md#differential-amplifier)
