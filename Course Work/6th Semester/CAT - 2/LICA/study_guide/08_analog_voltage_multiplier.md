# 08 - Analog Voltage Multiplier

## Learning Objectives
- Understand the log-antilog method of analog multiplication
- Derive how two voltages are multiplied using logarithmic identities
- Analyze applications: voltage squarer, frequency doubler, phase angle detector
- Understand voltage division using log and difference amplifiers

## Ground-Up Explanation

An analog voltage multiplier produces an output proportional to the **product** of two input voltages: $V_o \propto V_x \times V_y$.

### Log-Antilog Method

The key insight uses the **logarithmic identity**:

$$\ln(A \times B) = \ln(A) + \ln(B)$$

Since we can build log amplifiers (topic 05), summing amplifiers, and antilog amplifiers, we chain them:

1. **Log amplifier 1**: Converts $V_x$ to $\ln(V_x)$
2. **Log amplifier 2**: Converts $V_y$ to $\ln(V_y)$
3. **Summing amplifier**: Adds them: $\ln(V_x) + \ln(V_y) = \ln(V_x \cdot V_y)$
4. **Antilog amplifier**: Converts back: $e^{\ln(V_x \cdot V_y)} = V_x \cdot V_y$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s38_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s38_img2.png]]

**For voltage division**: Send the log amplifier outputs to a **difference amplifier** instead of a summing amplifier:

$$\ln(V_x) - \ln(V_y) = \ln(V_x / V_y) \xrightarrow{\text{antilog}} V_x / V_y$$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s38_img3.png]]

---

## Applications

### 1. Voltage Squarer

Supply **both inputs with the same signal** ($V_x = V_y = V_i$):

$$V_o = V_i \times V_i = V_i^2$$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s39_img1.png]]

### 2. Frequency Doubler

When a sinusoidal signal $V_i = V_p \sin(\omega t)$ is squared:

$$V_o = V_p^2 \sin^2(\omega t) = \frac{V_p^2}{2}(1 - \cos(2\omega t))$$

The output contains a **DC component** and a component at **twice the input frequency** ($2\omega$). The frequency is doubled.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s39_img3.png]]

### 3. Phase Angle Detector

When two sinusoidal signals with **different phase angles** are multiplied:

$$V_x = V_{xp}\sin(\omega t + \phi_x), \quad V_y = V_{yp}\sin(\omega t + \phi_y)$$

The product contains:
- A **DC component** proportional to $\cos(\phi_x - \phi_y)$
- A high-frequency component at $2\omega$ (filtered out)

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s40_img1.png]]

From the DC component, the phase difference can be calculated using a phase angle meter. A scaling factor of 10 is typically used in the multiplier configuration.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s40_img2.png]]

## Key Formulas

**Analog multiplication (log-antilog):**

$$\boxed{V_o = K \cdot V_x \cdot V_y}$$

Where $K$ is a scaling constant (typically $1/10$).

**Voltage squarer:**

$$\boxed{V_o = K \cdot V_i^2}$$

**Frequency doubler identity:**

$$\boxed{\sin^2(\omega t) = \frac{1}{2}(1 - \cos(2\omega t))}$$

**Phase detection:**

$$\boxed{V_{DC} \propto \cos(\phi_x - \phi_y)}$$

## Common Mistakes

1. **Forgetting the scaling factor**: Real multipliers include a scale factor $K$ (often $1/10$) to keep the output in a usable range
2. **Confusing multiplication with summing**: A summing amplifier adds voltages; a multiplier computes the product
3. **Phase detection requires filtering**: The raw multiplier output contains both DC and $2\omega$ components. The LPF extracts only the DC term

## Self-Check Questions

> [!question]- How can an analog multiplier perform division?
> Use a difference amplifier after the log stages instead of a summing amplifier: $\ln(V_x) - \ln(V_y) = \ln(V_x/V_y)$, then antilog gives $V_x/V_y$.

> [!question]- What frequency appears at the output of a frequency doubler if the input is 1 kHz?
> 2 kHz (double the input frequency), plus a DC offset.

> [!question]- Why does squaring a sine wave double the frequency?
> Because $\sin^2(\omega t) = \frac{1}{2}(1 - \cos(2\omega t))$. The $\cos(2\omega t)$ term has twice the original frequency.

## Concept Links
- Prerequisite: [Log & Antilog Amplifier](./05_log_and_antilog_amplifier.md) (building blocks)
- Related: [Phase Locked Loop](./13_phase_locked_loop.md) (PLL phase detector uses similar multiplication)
- Formulas: [Formula Sheet - Analog Multiplier](./15_formula_sheet_ultimate.md#analog-voltage-multiplier)
