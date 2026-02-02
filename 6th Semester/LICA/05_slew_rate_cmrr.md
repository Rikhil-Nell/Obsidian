# 05 - Slew Rate & CMRR ⚡ (EXAM CRITICAL)

## Part A: Slew Rate

### What is Slew Rate?

**Definition:** The maximum rate at which the op-amp output can change in response to a rapidly changing input.

$$\boxed{SR = \frac{dV_{out}}{dt}\bigg|_{max} \quad \text{(Units: V/μs)}}$$

> **Analogy:** Think of slew rate as the "top speed" of your output. No matter how fast you press the gas pedal (input), the car (output) can only accelerate so fast.

### IC 741 Slew Rate
$$\boxed{SR = 0.5 \text{ V/μs}}$$

This means: Output can change by at most **0.5V in 1 microsecond**.

---

### Why Does Slew Rate Matter?

For **large signals**, even if the op-amp has enough bandwidth, the output may not follow a fast input because it's slew-rate limited.

```
Input (fast)      Output (slew-limited)
    ┌──           ╱
    │           ╱
────┘         ╱
            ╱
          ╱
        ╱─────────
```

The output "ramps" instead of instantly switching!

---

### Slew Rate Calculation from Time

$$\boxed{SR = \frac{\Delta V}{\Delta t}}$$

$$\boxed{\Delta t = \frac{\Delta V}{SR}}$$

---

### Worked Example 1: Time to Change

**Problem:** An IC 741 (SR = 0.5 V/μs) is used as a unity gain inverting amplifier with an input change of 10V. Find the time for output to change by 10V.

**Solution:**

$$\Delta t = \frac{\Delta V}{SR} = \frac{10 \text{ V}}{0.5 \text{ V/μs}} = \boxed{20 \text{ μs}}$$

---

### Slew Rate for Sinusoidal Signals

For a sinewave: $v_o = V_m \sin(\omega t)$

The maximum rate of change occurs at zero crossing:

$$\frac{dv_o}{dt}\bigg|_{max} = V_m \omega = 2\pi f V_m$$

**For undistorted output:**

$$\boxed{SR \geq 2\pi f V_m}$$

Or solving for maximum frequency:

$$\boxed{f_{max} = \frac{SR}{2\pi V_m}}$$

---

### Full Power Bandwidth

The maximum frequency at which the op-amp can produce **full amplitude undistorted output**:

$$\boxed{f_{FP} = \frac{SR}{2\pi V_{m(max)}}}$$

---

### Worked Example 2: Maximum Undistorted Frequency

**Problem:** SR = 0.5 V/μs. Find maximum undistorted sine-wave frequency for:
(a) 12V peak
(b) 2V peak

**Solution:**

(a) For $V_m = 12V$:
$$f_{max} = \frac{SR}{2\pi V_m} = \frac{0.5 \times 10^6}{2\pi \times 12} = \frac{0.5 \times 10^6}{75.4} = \boxed{6.63 \text{ kHz}}$$

(b) For $V_m = 2V$:
$$f_{max} = \frac{0.5 \times 10^6}{2\pi \times 2} = \frac{0.5 \times 10^6}{12.57} = \boxed{39.8 \text{ kHz}}$$

> **Key Insight:** Smaller amplitude = Higher possible frequency!

---

### Worked Example 3: Square Wave Rise Time

**Problem:** SR = 0.5 V/μs. Can we amplify a 500 mV peak-to-peak square wave with rise time ≤ 4 μs to 5V peak-to-peak?

**Solution:**

Required output change = 5V in 4 μs

Required slew rate = $\frac{5 \text{ V}}{4 \text{ μs}} = 1.25 \text{ V/μs}$

Op-amp SR = 0.5 V/μs

Since 1.25 > 0.5 V/μs, **NOT POSSIBLE!** ❌

The output will have slower rise time than desired.

---

### Worked Example 4: Maximum Input Amplitude

**Problem:** IC 741 inverting amplifier with gain = 100. Flat response up to 10 kHz. Find max peak-to-peak input without distortion.

**Solution:**

At 10 kHz, using $f_{max} = \frac{SR}{2\pi V_m}$:

$$V_m = \frac{SR}{2\pi f} = \frac{0.5 \times 10^6}{2\pi \times 10^4} = \frac{0.5 \times 10^6}{62832} = 7.96 \text{ V (peak)}$$

Max output amplitude = 7.96V peak

Max input = $\frac{7.96}{100} \approx 80$ mV peak

$$\text{Peak-to-peak input} = 2 \times 80 = \boxed{160 \text{ mV}_{pp}}$$

---

## Part B: Common Mode Rejection Ratio (CMRR)

### What is CMRR?

**CMRR** measures how well the op-amp rejects signals that are common to both inputs.

$$\boxed{CMRR = \frac{A_{dm}}{A_{cm}} = \frac{\text{Differential-mode gain}}{\text{Common-mode gain}}}$$

$$\boxed{CMRR_{dB} = 20\log_{10}(CMRR)}$$

### IC 741 CMRR
$$\boxed{CMRR = 90 \text{ dB}}$$

Converting: $90 = 20\log(CMRR)$ → $CMRR = 10^{4.5} = 31623$

---

### Common Mode vs Differential Mode

The op-amp is a **differential amplifier** - it responds to the DIFFERENCE between its inputs.

| Mode | Definition | Formula | Ideal Output |
|------|------------|---------|--------------|
| **Differential Mode** | Difference between inputs | $V_{diff} = V_+ - V_-$ | $A_{dm} \times V_{diff}$ |
| **Common Mode** | Average of inputs | $V_{cm} = \frac{V_+ + V_-}{2}$ | 0 (ideally) |

### Decomposing Input Signals

Any two input signals can be decomposed into differential and common-mode components:

$$\boxed{V_+ = V_{cm} + \frac{V_{diff}}{2}}$$
$$\boxed{V_- = V_{cm} - \frac{V_{diff}}{2}}$$

### Visual Representation

```
Differential Mode:              Common Mode:
(Signals are opposite)          (Signals are same)

V+ ───┐                         V+ ───┐
      │ Different               V+ = V- │ Same
V- ───┘                         V- ───┘
      │                               │
      ▼                               ▼
  Amplified                       Rejected
  (Desired)                      (Unwanted noise)
```

### Why Common Mode Rejection Matters

**Common-mode signals are typically NOISE:**
- 50/60 Hz power line interference
- Ground loop currents
- Electromagnetic interference (EMI)

**Differential signals are typically the SIGNAL:**
- Sensor outputs
- Audio signals
- Data signals

### Real Op-Amp Output

$$\boxed{V_o = A_{dm} \cdot V_{diff} + A_{cm} \cdot V_{cm}}$$

Where:
- $A_{dm}$ = Differential-mode gain (very high, ~10⁵)
- $A_{cm}$ = Common-mode gain (very low, ideally 0)

---

### Output Voltage with CMRR

$$\boxed{V_o = A_{dm} \left( V_{diff} + \frac{V_{cm}}{CMRR} \right)}$$

Or equivalently:

$$\boxed{V_o = A_{dm} \cdot V_{diff} + A_{cm} \cdot V_{cm}}$$

Where:
- $V_{diff} = V_2 - V_1$ (differential signal)
- $V_{cm} = \frac{V_1 + V_2}{2}$ (common-mode signal)

---

### Worked Example: CMRR Comparison

**Problem:** A differential amplifier has:
- Case (i): CMRR = 1000
- Case (ii): CMRR = 10000

Compare outputs for two input sets:
- Set 1: V1 = +100 μV, V2 = -100 μV
- Set 2: V1 = 1100 μV, V2 = 900 μV

**Solution:**

For both sets: $V_{diff} = V_2 - V_1 = -200$ μV

**Set 1:**
$$V_{cm} = \frac{100 + (-100)}{2} = 0 \text{ μV}$$

**Set 2:**
$$V_{cm} = \frac{1100 + 900}{2} = 1000 \text{ μV}$$

Using $V_o = A_{dm}(V_{diff} + V_{cm}/CMRR)$, assuming $A_{dm} = 1$:

| Case | Set 1 Output | Set 2 Output | Difference |
|------|--------------|--------------|------------|
| CMRR = 1000 | -200 μV | -200 + 1000/1000 = -199 μV | 0.5% |
| CMRR = 10000 | -200 μV | -200 + 1000/10000 = -199.9 μV | 0.05% |

**Higher CMRR = Better rejection of common-mode signals!**

---

## Quick Reference: Slew Rate Formulas

| Formula | Application |
|---------|-------------|
| $SR = \frac{\Delta V}{\Delta t}$ | Basic definition |
| $\Delta t = \frac{\Delta V}{SR}$ | Finding time for voltage change |
| $f_{max} = \frac{SR}{2\pi V_m}$ | Maximum frequency for sinewave |
| $V_m = \frac{SR}{2\pi f}$ | Maximum amplitude at frequency f |
| $f_{FP} = \frac{SR}{2\pi V_{m(max)}}$ | Full power bandwidth |

---

## Quick Reference: CMRR Formulas

| Formula | Application |
|---------|-------------|
| $CMRR = \frac{A_{dm}}{A_{cm}}$ | Definition (ratio) |
| $CMRR_{dB} = 20\log_{10}(CMRR)$ | Converting to dB |
| $V_o = A_{dm}(V_{diff} + \frac{V_{cm}}{CMRR})$ | Output with CMRR effect |
| $V_{diff} = V_2 - V_1$ | Differential signal |
| $V_{cm} = \frac{V_1 + V_2}{2}$ | Common-mode signal |

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Forgetting units | SR is V/μs, not V/s! (multiply by 10⁶) |
| Using peak instead of peak-to-peak | Formulas use $V_m$ (peak), not $V_{pp}$ |
| Confusing SR with bandwidth | SR limits large signals; BW limits small signals |
| CMRR linear vs dB | 90 dB ≠ 90! It's 10^(90/20) ≈ 31623 |
| Ignoring sign in Vdiff | $V_{diff} = V_+ - V_-$ (order matters!) |

---

## Important Constants to Memorize (IC 741)

| Parameter | Value |
|-----------|-------|
| Slew Rate | **0.5 V/μs** |
| CMRR | **90 dB** |
| GBP | **1 MHz** |

---

*Next: [06_open_loop_configs.md](./06_open_loop_configs.md) - Open-Loop Configurations →*
