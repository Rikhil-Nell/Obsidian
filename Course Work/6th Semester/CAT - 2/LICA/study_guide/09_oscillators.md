# 09 - Oscillators (RC Phase Shift & Wien Bridge)

## Learning Objectives
- Understand the Barkhausen criterion for oscillation
- Derive the frequency and gain conditions for RC phase shift oscillator
- Derive the frequency and gain conditions for Wien bridge oscillator
- Compare the two oscillator types

## Ground-Up Explanation

### What is an Oscillator?

An oscillator is a circuit that generates a **periodic output waveform without any external input signal**. It is essentially a feedback amplifier where part of the output is fed back to the input.

**Analogy**: A child on a swing. You push once (initial disturbance), and if the swing's natural resonance matches the push timing, the oscillation sustains itself. The "feedback" is gravity pulling the child back through the equilibrium point.

### Barkhausen Criterion

For sustained oscillations, the loop gain must satisfy:

$$\boxed{|A \cdot \beta| = 1 \quad \text{and} \quad \angle(A \cdot \beta) = 0^\circ \text{ or } 360^\circ}$$

Where $A$ = amplifier gain, $\beta$ = feedback fraction.

- If $|A\beta| < 1$: oscillations die out
- If $|A\beta| > 1$: oscillations grow (until limited by saturation)
- If $|A\beta| = 1$: sustained oscillations at constant amplitude

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s8_img1.png]]

---

## RC Phase Shift Oscillator

LC oscillators require large inductors at low frequencies. **RC networks** provide a simpler alternative.

### Circuit Description
- Op-amp in **inverting** configuration (provides 180-degree phase shift)
- Three RC sections in cascade (each provides ~60 degrees, total = 180 degrees)
- Total phase shift: 180 (amplifier) + 180 (RC network) = **360 degrees**

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s9_img1.png]]

### Derivation

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s10_img3.png]]

Each RC section shifts the phase. Through analysis of three cascaded RC sections:

**Frequency of oscillation:**

$$\boxed{f_0 = \frac{1}{2\pi RC\sqrt{6}}}$$

**Required gain:**

$$\boxed{|A| = 29}$$

This means $R_f / R_1 = 29$, so the feedback resistor must be 29 times the input resistor.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s11_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s11_img2.png]]

---

## Wien Bridge Oscillator

### Circuit Description
- Op-amp in **non-inverting** configuration (zero phase shift from amplifier)
- Feedback network: Wien bridge (series RC + parallel RC)
- The feedback network provides **zero phase shift at resonance** -- matching the non-inverting amplifier

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s12_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s12_img2.png]]

### Derivation

At resonance (balanced bridge condition):

**Frequency of oscillation:**

$$\boxed{f_0 = \frac{1}{2\pi RC}}$$

**Required gain:**

$$\boxed{A = 1 + \frac{R_f}{R_1} = 3 \quad \Rightarrow \quad R_f = 2R_1}$$

The feedback fraction at resonance is $\beta = 1/3$, so the amplifier gain must be 3 to satisfy $A\beta = 1$.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s13_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s13_img2.png]]

---

## Comparison: RC Phase Shift vs Wien Bridge

| Parameter | RC Phase Shift | Wien Bridge |
|-----------|---------------|-------------|
| Configuration | Inverting | Non-inverting |
| Phase shift needed | 180 from RC network | 0 (bridge balanced) |
| Gain required | 29 (high) | 3 (low) |
| Frequency formula | $\frac{1}{2\pi RC\sqrt{6}}$ | $\frac{1}{2\pi RC}$ |
| Component matching | Not critical | Requires closely matched RC |
| Frequency tuning | Difficult (3 RC sections) | Easy (single RC pair) |
| Frequency stability | Low | High |
| Distortion | Higher | Lower |
| Amplitude stabilization | Not needed (basic) | May need (e.g., lamp bulb) |
| Best for | Simple low-freq circuits | Audio frequency generation |

## Common Mistakes

1. **Mixing up gain values**: RC phase shift needs gain = 29; Wien bridge needs gain = 3
2. **Forgetting $\sqrt{6}$ in RC osc frequency**: It's $1/(2\pi RC\sqrt{6})$, not $1/(2\pi RC)$
3. **Wien bridge = non-inverting**: Students often assume it's inverting like the RC oscillator
4. **Barkhausen is necessary but not sufficient**: Meeting the criterion doesn't guarantee oscillation at startup; $|A\beta|$ is usually set slightly > 1 initially

## Self-Check Questions

> [!question]- If $R = 10k\Omega$ and $C = 0.01\mu F$, find $f_0$ for both oscillators.
> RC Phase Shift: $f_0 = 1/(2\pi \times 10^4 \times 10^{-8} \times \sqrt{6}) = 1/(6.28 \times 10^{-4} \times 2.449) = 1/0.001538 \approx 650$ Hz
> Wien Bridge: $f_0 = 1/(2\pi \times 10^4 \times 10^{-8}) = 1/(6.28 \times 10^{-4}) \approx 1592$ Hz

> [!question]- What is the Barkhausen criterion?
> For sustained oscillation: loop gain magnitude = 1 ($|A\beta| = 1$) and loop phase = 0 degrees or 360 degrees.

> [!question]- If the gain of an RC phase shift oscillator is set to 25 instead of 29, what happens?
> $|A\beta| < 1$, so oscillations will not be sustained. Any initial oscillation will decay and die out.

## Concept Links
- Next: [Waveform Generators](./10_waveform_generators.md) (square, triangle, sawtooth)
- Related: [Schmitt Trigger](./06_comparator_and_schmitt_trigger.md) (used in square wave generators)
- Formulas: [Formula Sheet - Oscillators](./15_formula_sheet_ultimate.md#oscillators)
