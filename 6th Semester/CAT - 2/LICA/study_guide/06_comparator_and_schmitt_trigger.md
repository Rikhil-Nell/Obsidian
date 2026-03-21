# 06 - Op-Amp Comparator and Schmitt Trigger

## Learning Objectives
- Understand open-loop comparator operation (inverting and non-inverting)
- Calculate reference voltage configurations
- Explain why noise causes false triggering in simple comparators
- Derive upper and lower threshold voltages (UTP, LTP) for Schmitt trigger
- Calculate hysteresis width
- Know applications of both circuits

## Ground-Up Explanation

### Op-Amp Comparator

A comparator compares an input voltage $V_i$ with a reference voltage $V_{ref}$ and produces a **binary** output: either $+V_{sat}$ or $-V_{sat}$. It operates in **open-loop** (no feedback).

**Analogy**: A comparator is like a thermostat. It checks if the room temperature (input) is above or below the set point (reference). If above, it turns OFF the heater ($-V_{sat}$); if below, it turns ON ($+V_{sat}$).

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s30_img1.png]]

**Inverting Comparator:**
- $V_i$ applied to inverting input (-), $V_{ref}$ to non-inverting input (+)
- When $V_i > V_{ref}$: $V_o = -V_{sat}$
- When $V_i < V_{ref}$: $V_o = +V_{sat}$

**Non-Inverting Comparator:**
- $V_i$ applied to non-inverting input (+), $V_{ref}$ to inverting input (-)
- When $V_i > V_{ref}$: $V_o = +V_{sat}$
- When $V_i < V_{ref}$: $V_o = -V_{sat}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s31_img1.png]]

**Practical notes:**
- There is a ~2 mV transition zone where output is uncertain (due to op-amp offset voltage)
- Reference voltage can be set using a voltage divider from $V_{CC}$ and $-V_{EE}$
- Output can be limited using Zener diodes

**Applications:** Zero-crossing detector, window detector, pulse-time modulator, phase detector, timing marker generator.

---

### Schmitt Trigger (Regenerative Comparator)

**The Problem**: In a simple comparator, input noise near the threshold causes **false triggering** -- the output rapidly oscillates between $+V_{sat}$ and $-V_{sat}$.

**The Solution**: Add **positive feedback** from output to the non-inverting input. This creates **two different switching thresholds** (hysteresis), so the circuit "remembers" its previous state.

**Analogy**: Consider a light switch with a dead zone. You need to push it past a certain point to turn it ON, and push it back past a *different* (lower) point to turn it OFF. This prevents the switch from flickering when your hand trembles near the threshold.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s33_img1.png]]

### How It Works

The positive feedback via $R_1$ and $R_2$ creates a voltage at the non-inverting input:

$$V^+ = V_{ref} \cdot \frac{R_2}{R_1 + R_2} + V_o \cdot \frac{R_1}{R_1 + R_2}$$

Let $\beta = \frac{R_1}{R_1 + R_2}$ (feedback fraction).

**When $V_o = +V_{sat}$:**

$$V_{UT} = V_{ref}\frac{R_2}{R_1 + R_2} + V_{sat}\frac{R_1}{R_1 + R_2}$$

The output flips to $-V_{sat}$ only when $V_i$ exceeds this **Upper Threshold (UTP)**.

**When $V_o = -V_{sat}$:**

$$V_{LT} = V_{ref}\frac{R_2}{R_1 + R_2} - V_{sat}\frac{R_1}{R_1 + R_2}$$

The output flips to $+V_{sat}$ only when $V_i$ drops below this **Lower Threshold (LTP)**.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s33_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s34_img1.png]]

### Hysteresis Width

$$V_H = V_{UT} - V_{LT} = 2V_{sat} \cdot \frac{R_1}{R_1 + R_2} = 2\beta V_{sat}$$

**Key observation**: $V_H$ is **independent of $V_{ref}$**. If $V_{ref} = 0$, the thresholds become symmetrical around zero, producing a clean square wave from a sine input.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s34_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s34_img3.png]]

### Merits of Schmitt Trigger
- Produces **two threshold switching** at $V_{LT}$ and $V_{UT}$
- Switches only when input crosses a threshold -- **immune to noise**
- Produces **clean square waves**
- Applications: analog-to-digital conversion, clock signal conditioning, threshold detection, noise margin improvement

## Key Formulas

**Comparator:**

$$\boxed{V_o = \begin{cases} +V_{sat} & \text{if } V_i > V_{ref} \text{ (non-inverting)} \\ -V_{sat} & \text{if } V_i < V_{ref} \end{cases}}$$

**Schmitt trigger thresholds:**

$$\boxed{V_{UT} = V_{ref}\frac{R_2}{R_1 + R_2} + V_{sat}\frac{R_1}{R_1 + R_2}}$$

$$\boxed{V_{LT} = V_{ref}\frac{R_2}{R_1 + R_2} - V_{sat}\frac{R_1}{R_1 + R_2}}$$

**Hysteresis width:**

$$\boxed{V_H = V_{UT} - V_{LT} = \frac{2R_1 \cdot V_{sat}}{R_1 + R_2}}$$

**For $V_{ref} = 0$ (symmetrical):**

$$\boxed{V_{UT} = +\beta V_{sat}, \quad V_{LT} = -\beta V_{sat}}$$

Where $\beta = R_1 / (R_1 + R_2)$.

## Common Mistakes

1. **Confusing feedback types**: Comparator = open-loop (no feedback); Schmitt = positive feedback (NOT negative)
2. **Hysteresis is independent of Vref**: Changing $V_{ref}$ shifts both thresholds equally but doesn't change $V_H$
3. **Direction of comparison**: For an inverting comparator, output goes NEGATIVE when input exceeds reference
4. **Forgetting that Schmitt trigger is also a comparator**: It's a comparator WITH hysteresis

## Self-Check Questions

> [!question]- If $R_1 = 10k\Omega$, $R_2 = 100k\Omega$, $V_{sat} = 12V$, and $V_{ref} = 0$, find $V_{UT}$, $V_{LT}$, and $V_H$.
> $\beta = 10k/(10k+100k) = 1/11 \approx 0.091$
> $V_{UT} = +\beta V_{sat} = 12/11 \approx 1.09V$
> $V_{LT} = -\beta V_{sat} = -12/11 \approx -1.09V$
> $V_H = 2 \times 12/11 \approx 2.18V$

> [!question]- Why does a simple comparator suffer from false triggering?
> Because the high open-loop gain amplifies even tiny noise signals near the threshold, causing the output to rapidly toggle between $+V_{sat}$ and $-V_{sat}$.

> [!question]- What type of feedback does a Schmitt trigger use?
> Positive feedback (output fed back to the non-inverting input). This is what creates the hysteresis.

## Concept Links
- Prerequisite: Op-Amp open-loop gain concept
- Used by: [Square Wave Generator](./10_waveform_generators.md) (uses Schmitt trigger principle)
- Used by: [555 Timer Schmitt Trigger](./12_555_applications.md) (uses 555 as Schmitt trigger)
- Related: [Precision Rectifier](./03_precision_rectifier.md) (threshold-based switching)
- Formulas: [Formula Sheet - Comparator & Schmitt](./15_formula_sheet_ultimate.md#comparator-and-schmitt-trigger)
