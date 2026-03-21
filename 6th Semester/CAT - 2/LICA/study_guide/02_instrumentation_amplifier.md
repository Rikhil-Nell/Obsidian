# 02 - Instrumentation Amplifier

## Learning Objectives
- Understand why a basic difference amplifier is insufficient for transducer signals
- Derive the output voltage of a 3-op-amp instrumentation amplifier
- Explain the role of each stage (input buffer + difference amplifier)
- Know the merits and applications

## Ground-Up Explanation

Industrial transducers (temperature sensors, strain gauges, pressure sensors) produce extremely small signals -- typically in the $\mu V$ to $mV$ range. These tiny signals often ride on large common-mode noise voltages. A basic difference amplifier fails here because:

1. CMRR depends entirely on resistor matching
2. Gain adjustment requires changing multiple resistors
3. High gain requires large $R_2/R_1$, increasing noise and mismatch error

**Analogy**: Imagine trying to hear a whisper (differential signal) in a noisy crowd (common-mode noise). A basic difference amplifier is like cupping one ear -- it helps a little. An instrumentation amplifier is like professional noise-cancelling headphones with adjustable volume -- it rejects the crowd noise perfectly and amplifies the whisper with a single knob.

### Limitations of the Basic Difference Amplifier

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s6_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s6_img2.png]]

### Three-Op-Amp Instrumentation Amplifier

The solution is a **three-op-amp instrumentation amplifier** consisting of:
- **Stage 1** (A1 + A2): Differential input buffer stage -- provides high input impedance and adjustable gain via a single resistor $R$
- **Stage 2** (A3): Standard difference amplifier -- subtracts the two buffered outputs

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s8_img1.png]]

### How Stage 1 Works

- Both op-amps A1 and A2 are configured as non-inverting amplifiers
- They share a common gain-setting resistor $R$ between their outputs
- The resistors $R_1$ on each side set the gain
- Since both inputs see high-impedance non-inverting terminals, the input impedance is very high

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s8_img2.png]]

### Derivation

For the input buffer stage:
- Output of A1: $V_{o1} = V_1 + \frac{R_1}{R}(V_1 - V_2) = V_1(1 + \frac{R_1}{R}) - V_2\frac{R_1}{R}$
- Output of A2: $V_{o2} = V_2 + \frac{R_1}{R}(V_2 - V_1) = V_2(1 + \frac{R_1}{R}) - V_1\frac{R_1}{R}$
- Difference: $V_{o1} - V_{o2} = (1 + \frac{2R_1}{R})(V_1 - V_2)$

Stage 2 (A3) is a unity-gain difference amplifier (when $R_2 = R_3$):

$$V_o = V_{o1} - V_{o2} = (1 + \frac{2R_1}{R})(V_1 - V_2)$$

If Stage 2 has gain $\frac{R_3}{R_2}$:

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s8_img3.png]]

## Key Formulas

$$\boxed{V_o = \left(1 + \frac{2R_1}{R}\right)\frac{R_3}{R_2}(V_1 - V_2)}$$

For unity-gain Stage 2 ($R_2 = R_3$):

$$\boxed{V_o = \left(1 + \frac{2R_1}{R}\right)(V_1 - V_2)}$$

Where:
- $R$ = Gain-setting resistor (only resistor that needs to be changed for gain adjustment)
- $R_1$ = Fixed resistors in buffer stage
- $R_2, R_3$ = Resistors in difference amplifier stage (factory-trimmed)

## Merits of Instrumentation Amplifier

1. **Gain depends on only one resistor** $R$ -- easy to adjust
2. **Resistor matching** in Stage 2 is fixed and factory-trimmed
3. **High gain achievable** without degrading CMRR
4. **Very high input impedance** (non-inverting inputs of A1 and A2)
5. **Excellent CMRR** even at high gains

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s9_img1.png]]

## Worked Example

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s10_img1.png]]

## Common Mistakes

1. **Confusing which resistor sets the gain**: Only $R$ (the resistor between the two input op-amps) sets the gain. $R_1$ is fixed.
2. **Forgetting the "+1" in gain formula**: The gain is $(1 + 2R_1/R)$, not just $2R_1/R$
3. **Assuming all three op-amps have equal gain**: Stage 1 provides variable gain; Stage 2 provides unity (or fixed) gain
4. **Missing applications**: Instrumentation amplifiers are used with Wheatstone bridges, thermocouples, strain gauges

## Self-Check Questions

> [!question]- If $R_1 = 25k\Omega$ and $R = 1k\Omega$ with unity Stage 2, what is the total gain?
> $A = 1 + 2(25k)/1k = 1 + 50 = 51$

> [!question]- Why can't we just increase the gain of a single-op-amp difference amplifier instead?
> Increasing gain requires large $R_2/R_1$ ratio, which degrades CMRR due to increased sensitivity to resistor mismatch, and increases noise.

> [!question]- What happens to the gain if $R$ is made very small?
> Gain becomes very large: $A = 1 + 2R_1/R \to \infty$ as $R \to 0$. Practically, op-amp bandwidth limits the usable gain.

## Concept Links
- Prerequisite: [Differential Amplifier](./01_differential_amplifier.md)
- Related: [Log/Antilog Amplifier](./05_log_and_antilog_amplifier.md) (both handle small signals)
- Formulas: [Formula Sheet - Instrumentation Amplifier](./15_formula_sheet_ultimate.md#instrumentation-amplifier)
