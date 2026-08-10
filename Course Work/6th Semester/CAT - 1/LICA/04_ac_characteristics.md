# 04 - AC Characteristics & Frequency Response 📈

## Overview

AC characteristics describe how the op-amp behaves with **time-varying (AC) signals**. The key challenge: **op-amp gain is NOT constant** – it changes with frequency!

> **Analogy:** Imagine a speaker that sounds great at low volumes but gets distorted at high volumes. Similarly, op-amps work great at low frequencies but lose gain at high frequencies.

---

## Small-Signal vs Large-Signal AC

| Type | Amplitude | Key Concerns |
|------|-----------|--------------|
| **Small-Signal** (≤ 1V peak) | Low | Noise, Bandwidth, Gain-Bandwidth Product |
| **Large-Signal** (> 1V peak) | High | **Slew Rate** becomes critical |

---

## Frequency Response

### Why Does Gain Decrease at High Frequencies?

Internal capacitors in the op-amp create reactive paths:
1. **Junction capacitors** in BJTs/MOSFETs
2. **Parasitic capacitance** between conducting paths
3. **Compensation capacitors** (intentionally added)

### High-Frequency Model

```
         ┌─────────────────┐
         │                 │
Vi ─────►│  Rin      C     │──► Vo
         │   ╱╲    ─┴─     │
         │  ╱  ╲    │      │
         │ A×Vi  ═══╪══    │
         │         ─┬─     │
         └─────────────────┘
```

The capacitor C causes gain to roll off at high frequencies.

---

## Open-Loop Gain vs Frequency

### Transfer Function

$$\boxed{A(j\omega) = \frac{A_0}{1 + j\frac{\omega}{\omega_c}} = \frac{A_0}{1 + j\frac{f}{f_c}}}$$

Where:
- $A_0$ = DC open-loop gain (for 741: 2 × 10⁵)
- $\omega_c = 2\pi f_c$ = Corner (break) frequency
- $f_c$ = First corner frequency (where gain drops by 3dB)

### Magnitude and Phase

$$|A(j\omega)| = \frac{A_0}{\sqrt{1 + \left(\frac{f}{f_c}\right)^2}}$$

$$\phi = -\tan^{-1}\left(\frac{f}{f_c}\right)$$

### Bode Plot

```
Gain (dB)
    │
A₀  ├────────────────┐
    │                 ╲
    │                  ╲  -20 dB/decade
    │                   ╲
    │                    ╲
0dB ├─────────────────────╲─────────────
    │                      ╲
    └──────────┬───────────┬────────────►
               fc         funity       f (Hz)
```

Key points:
- Gain is flat until corner frequency ($f_c$)
- Falls at **-20 dB/decade** (or -6 dB/octave)
- Reaches 0 dB (unity gain) at $f_{unity}$

---

## Gain-Bandwidth Product (GBP)

$$\boxed{GBP = A_0 \times f_c = A_{CL} \times f_{CL} = \text{constant}}$$

For IC 741: **GBP = 1 MHz**

### What This Means
- If you want higher gain → bandwidth decreases
- If you want higher bandwidth → gain must decrease

### Closed-Loop Bandwidth

$$\boxed{f_{CL} = \frac{GBP}{A_{CL}} = \frac{f_{unity}}{A_{CL}}}$$

Where $A_{CL}$ = closed-loop gain

---

### Worked Example: GBP Calculation

**Problem:** An op-amp has unity gain-bandwidth of 1.5 MHz. For a signal of frequency 2 kHz, what is the open-loop DC voltage gain?

**Solution:**

At low frequencies, $A_0 = \frac{GBP}{f}$... but this isn't quite right.

The unity-gain bandwidth IS the gain-bandwidth product:
$$GBP = 1.5 \text{ MHz}$$

At DC (f → 0), gain is maximum. We need the corner frequency:
$$f_c = \frac{GBP}{A_0}$$

If we assume typical $A_0 = 2 \times 10^5$:
$$f_c = \frac{1.5 \times 10^6}{2 \times 10^5} = 7.5 \text{ Hz}$$

At 2 kHz (>> 7.5 Hz), gain has rolled off:
$$A(2\text{kHz}) = \frac{GBP}{2\text{kHz}} = \frac{1.5 \times 10^6}{2 \times 10^3} = \boxed{750}$$

---

## Closed-Loop Frequency Response

Negative feedback stabilizes gain over a wider bandwidth:

```
Gain (dB)
    │
A₀  ├────┐
    │    │ Open-loop
    │    ╲
ACL ├────────────────┐
    │    Closed-loop  ╲
    │                  ╲
0dB ├───────────────────╲───────────
    │                    ╲
    └────────┬───────────┬──────────►
            fCL         funity     f
```

Benefits of negative feedback:
- Stable, predictable gain
- Extended bandwidth
- Reduced distortion

---

## Frequency Compensation Methods

### Why Compensate?
- Multiple internal poles cause phase shifts
- At certain frequencies, negative feedback becomes positive → oscillation!
- Compensation ensures **stability**

### 1️⃣ Dominant Pole Compensation

**Concept:** Add a new very low-frequency pole that dominates all others.

$$\boxed{f_d << f_1 < f_2 < f_3}$$

**Implementation:** Add external RC network

$$f_d = \frac{1}{2\pi R_c C_c}$$

**Effect:**
- Gain starts rolling off earlier
- Only 90° max phase shift at unity gain crossing
- Reduced bandwidth but better stability

```
Gain (dB)
    │
    ├───────┐ Uncompensated
    │        ╲
    ├─────┐   ╲
    │      ╲   ╲  Compensated
    │       ╲   ╲
    └───────┬────────────────────►
           fd              f
```

### 2️⃣ Pole-Zero Compensation

**Concept:** Add both a pole AND a zero to maintain higher bandwidth.

$$\boxed{f_0 < f_{zero} \approx f_1}$$

**How it works:**
- Zero cancels effect of first pole
- Pole at lower frequency provides rolloff
- Better bandwidth than dominant pole method

**Transfer function:**
$$A'(s) = A(s) \times \frac{(1 + s/\omega_z)}{(1 + s/\omega_p)}$$

### 3️⃣ Miller Effect Compensation

**Concept:** Use a capacitor between high-gain stages.

**Benefits:**
- Phase lead at high frequencies
- Partially cancels phase lag from poles
- Improves stability without sacrificing all bandwidth

---

## Multiple Corner Frequencies

Real op-amps have multiple stages, each contributing a pole:

$$A(s) = \frac{A_0}{(1 + \frac{s}{\omega_1})(1 + \frac{s}{\omega_2})(1 + \frac{s}{\omega_3})...}$$

Each pole:
- Adds -20 dB/decade to rolloff
- Adds -90° to phase shift

**Danger Zone:** If total phase shift reaches 180° while gain > 1 → oscillation!

---

## Summary of AC Formulas

| Parameter | Formula | Notes |
|-----------|---------|-------|
| Gain vs Frequency | $A(j\omega) = \frac{A_0}{1 + j\omega/\omega_c}$ | First-order response |
| Magnitude | $\|A\| = \frac{A_0}{\sqrt{1 + (f/f_c)^2}}$ | |
| Phase | $\phi = -\tan^{-1}(f/f_c)$ | Negative = lag |
| Corner Frequency | $f_c = \frac{1}{2\pi RC}$ | 3dB point |
| GBP | $A_0 \times f_c = A_{CL} \times f_{CL}$ | Constant |
| Closed-Loop BW | $f_{CL} = GBP/A_{CL}$ | |
| Rolloff Rate | -20 dB/decade | Per pole |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Thinking gain is constant | Gain decreases with frequency! |
| Ignoring GBP trade-off | Higher gain = Lower bandwidth |
| Forgetting phase shift | Each pole adds up to 90° lag |
| Not compensating | Uncompensated op-amp may oscillate |
| Confusing open/closed loop BW | Closed-loop BW is much higher |

---

*Next: [05_slew_rate_cmrr.md](05_slew_rate_cmrr.md) - Slew Rate & CMRR (CRITICAL for exams!) →*
