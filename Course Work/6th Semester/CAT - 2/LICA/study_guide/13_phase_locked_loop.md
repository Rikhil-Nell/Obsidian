# 13 - Phase Locked Loop (PLL)

## Learning Objectives
- Understand the block diagram and function of each PLL component
- Explain the three operating modes: free-running, capture, and locked
- Analyze different phase detector types (analog, XOR, SR flip-flop)
- Understand the role of the low-pass filter in PLL stability
- Know PLL applications

## Ground-Up Explanation

A **Phase Locked Loop (PLL)** is a feedback system that forces an internal oscillator (VCO) to match the frequency and phase of an external input signal.

**Analogy**: Two musicians trying to play in sync. One musician (VCO) listens to the other (input signal) and adjusts their tempo (frequency) until they're perfectly synchronized (phase-locked). The "ear" is the phase detector, and the "brain" deciding how to adjust is the low-pass filter.

### Block Diagram

A PLL consists of three main components:

```
Input (fi) --> [Phase Detector] --> [Low-Pass Filter] --> [VCO] --> Output (fo)
                     ^                                       |
                     |_______________________________________|
                              Feedback loop
```

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s2_img1.png]]

**1. Phase Detector (PD):**
- Compares input frequency $f_i$ with VCO feedback frequency $f_o$
- Produces an output voltage proportional to the **phase difference**
- This is the "error voltage" $V_e$

**2. Low-Pass Filter (LPF):**
- Removes high-frequency noise and ripple from the phase detector output
- Produces a smooth DC control voltage
- Also determines the **dynamic response and stability** of the PLL

**3. Voltage-Controlled Oscillator (VCO):**
- Output frequency is proportional to the input control voltage
- Free-running frequency $f_0$ (when no input signal)
- Frequency changes as control voltage changes

### Three Operating Modes

| Mode | Description |
|------|-------------|
| **Free-running** | No input signal applied. VCO oscillates at its natural frequency $f_0$ |
| **Capture** | Input signal applied. VCO frequency begins to change toward $f_i$ |
| **Locked** | VCO frequency = input frequency. Phase difference is constant. PLL tracks input |

Once locked, the PLL can **track small changes** in input frequency by continuously adjusting the VCO.

---

## Phase Detectors

### 1. Analog Phase Detector (Multiplier Type)

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s4_img1.png]]

An analog switch controlled by the VCO output multiplies the two signals:

Let input = $V_i \sin(\omega_i t)$ and VCO output = $V_o \sin(\omega_o t)$

The phase detector multiplies them. After filtering:
- When frequencies match ($\omega_i = \omega_o$): output $\propto \cos(\phi)$ where $\phi$ is the phase difference

$$\boxed{V_{error} \propto \cos(\phi_i - \phi_o)}$$

**PLL locks at 90-degree phase difference** (where $\cos(90°) = 0$, the equilibrium point).

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s5_img1.png]]

**Limitations:**
- Output depends on input signal amplitude
- Non-linear response ($\cos$ function) is hard to control

### 2. Digital Phase Detector -- XOR Type

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s6_img1.png]]

- Requires **square wave** inputs
- XOR gate output goes HIGH when signals are different
- The greater the phase difference, the wider the pulse
- Error voltage is proportional to phase difference
- Maximum error at 180 degrees, minimum at 0 degrees

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s7_img1.png]]

**Limitation:** Works well only if both signals are perfect square waves with **50% duty cycle**.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s7_img2.png]]

### 3. Digital Phase Detector -- SR Flip-Flop Type

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s8_img1.png]]

Uses an RS latch (NOR gates):
- Input frequency edge --> **SET** (Q goes HIGH)
- VCO frequency edge --> **RESET** (Q goes LOW)
- Output stays HIGH during the time difference between edges
- Average value: $V_{DC} \propto$ phase difference

$$\boxed{V_{DC} \propto \frac{\phi}{2\pi}}$$

**Advantages over XOR detector:**
- Linear over full **360 degrees** (vs 180 for XOR)
- Better capture range
- Better lock stability
- Better tracking performance

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s8_img2.png]]

---

## Low-Pass Filter

The LPF serves two purposes:
1. **Filters** high-frequency ripple from phase detector output
2. **Controls** PLL dynamics (bandwidth, settling time, stability)

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s9_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s9_img2.png]]

---

## Key Formulas

**Lock range**: Range of input frequencies the PLL can track once locked:

$$\boxed{f_L = \pm \Delta f_{lock}}$$

**Capture range**: Range of frequencies over which the PLL can acquire lock (always $\leq$ lock range):

$$\boxed{f_C = \pm \Delta f_{capture} \leq f_L}$$

**Analog PD error voltage:**

$$\boxed{V_e = K_d \cos(\phi_i - \phi_o)}$$

**VCO frequency:**

$$\boxed{f_{VCO} = f_0 + K_v \cdot V_{control}}$$

Where:
- $K_d$ = phase detector gain (V/rad)
- $K_v$ = VCO gain (Hz/V)
- $f_0$ = free-running frequency

## PLL Applications

- FM stereo decoders
- Motor speed control
- Tracking filters
- Frequency synthesizers
- FM demodulators
- FSK decoders
- Local oscillator in TV/FM tuners

## Comparison of Phase Detectors

| Feature | Analog (Multiplier) | XOR (Digital) | SR Flip-Flop |
|---------|-------------------|---------------|--------------|
| Input type | Sinusoidal | Square wave | Square wave |
| Linear range | Non-linear ($\cos$) | 0-180 degrees | 0-360 degrees |
| Lock point | 90 degrees | 90 degrees | Near 0 degrees |
| Amplitude sensitivity | Yes | No | No |
| Duty cycle requirement | N/A | 50% required | Not required |
| Capture range | Moderate | Moderate | Best |

## Common Mistakes

1. **Confusing lock range and capture range**: Capture range is always less than or equal to lock range. The PLL must "capture" first, then it can "track" over the lock range.
2. **Phase detector output is NOT frequency difference**: It's proportional to the *phase* difference. Frequency difference only matters during acquisition.
3. **VCO is NOT excluded from your exam**: The user excluded VCO as a standalone topic, but VCO *within PLL context* is included.

## Self-Check Questions

> [!question]- What are the three operating modes of a PLL?
> 1. Free-running (no input, VCO at natural frequency)
> 2. Capture (VCO adjusting toward input frequency)
> 3. Locked (VCO frequency = input frequency, tracks changes)

> [!question]- Which phase detector type has the best capture range and why?
> SR flip-flop phase detector -- it's linear over the full 360 degrees and doesn't require 50% duty cycle inputs, giving it superior performance.

> [!question]- If the capture range is 100 Hz, can the lock range be 50 Hz?
> No. Lock range $\geq$ capture range. If the PLL is already locked, it can track over a wider range than it can initially acquire.

## Concept Links
- Related: [Analog Voltage Multiplier](./08_analog_voltage_multiplier.md) (analog PD is a multiplier)
- Related: [Comparator](./06_comparator_and_schmitt_trigger.md) (phase detector comparison)
- Related: [555 Timer FSK](./12_555_applications.md) (PLL used in FSK demodulation)
- Formulas: [Formula Sheet - PLL](./15_formula_sheet_ultimate.md#phase-locked-loop)
