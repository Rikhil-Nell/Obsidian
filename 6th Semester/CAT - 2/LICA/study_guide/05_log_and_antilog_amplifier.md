# 05 - Logarithmic and Antilogarithmic Amplifier

## Learning Objectives
- Understand why log/antilog amplifiers are needed (dynamic range compression)
- Derive the output of a basic log amplifier using a transistor
- Explain the temperature compensation problem and its solution
- Derive the compensated log amplifier (matched transistors)
- Understand the antilog amplifier and its compensated version
- Know applications: analog multiplication, division, exponentiation

## Ground-Up Explanation

### Why Log Amplifiers?

Resistive feedback gives **linear** input-to-output relationships. But many real-world applications need to handle signals with a **huge dynamic range** (e.g., 10 mV to 10 V -- a 1000:1 ratio). A log amplifier compresses this range:

$$V_o \propto \ln(V_i)$$

**Analogy**: The Richter scale for earthquakes. A magnitude 6 earthquake is 10x stronger than magnitude 5. The log scale (log amplifier) compresses a massive range of energies into manageable numbers (1-10).

### Basic Log Amplifier

Replace the feedback resistor of an inverting amplifier with a **BJT transistor** (transdiode configuration):
- Base: grounded
- Collector: connected to virtual ground (inverting input)
- Emitter: connected to output

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s21_img1.png]]

The transistor collector current follows the exponential relation:

$$I_C = I_S \cdot e^{V_{BE}/V_T}$$

Where $V_T = kT/q$ is the thermal voltage (~26 mV at room temperature).

Since $I_C = V_i / R$ (from the input resistor) and $V_o = -V_{BE}$:

$$V_o = -V_T \ln\left(\frac{V_i}{I_S \cdot R}\right)$$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s21_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s21_img3.png]]

### The Temperature Problem

$I_S$ (reverse saturation current) changes with transistor design and **temperature**, which changes the output calibration. Also, $V_T = kT/q$ itself is temperature-dependent.

### Compensated Log Amplifier (Matched Transistors)

Solution: Use **two matched transistors** Q1 and Q2 fabricated on the same silicon wafer:
- $I_{S1} = I_{S2}$ (ensures close matching and thermal tracking)
- A difference amplifier subtracts the two VBE voltages, canceling $I_S$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s23_img1.png]]

The four-op-amp compensated log amplifier:

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s24_img1.png]]

- Op-amp A1: $V_{BE1}$ depends on $V_1/R_1$
- Op-amp A2: $V_{BE2}$ depends on $V_{ref}/R_2$
- A3 (difference amplifier): subtracts $V_{BE1} - V_{BE2}$, $I_S$ cancels out
- A4 (gain stage): final output

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s24_img2.png]]

**Temperature compensation**: A positive-temperature-coefficient resistor ($R_{TC}$) reduces gain when temperature rises, compensating the $kT/q$ factor.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s25_img1.png]]

---

## Antilog Amplifier

The antilog amplifier **reverses** the logarithm:

$$V_o \propto e^{V_i} \quad \text{or} \quad V_o \propto 10^{V_i}$$

The transistor is placed at the **input** side (emitter to input signal, collector to inverting terminal).

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s26_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s26_img2.png]]

### Compensated Antilog Amplifier

Same temperature issues exist. Solution: matched transistors with difference amplifier.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s27_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s28_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s29_img1.png]]

## Key Formulas

**Basic log amplifier:**

$$\boxed{V_o = -\frac{kT}{q} \ln\left(\frac{V_i}{I_S \cdot R}\right)}$$

**Compensated log amplifier (IS cancels):**

$$\boxed{V_o = -\frac{kT}{q} \ln\left(\frac{V_1 \cdot R_2}{V_{ref} \cdot R_1}\right)}$$

**Basic antilog amplifier:**

$$\boxed{V_o = -R_f \cdot I_S \cdot e^{V_i / V_T}}$$

**Thermal voltage:**

$$\boxed{V_T = \frac{kT}{q} \approx 26\text{ mV at 300K}}$$

Where:
- $k = 1.38 \times 10^{-23}$ J/K (Boltzmann constant)
- $T$ = Temperature in Kelvin
- $q = 1.6 \times 10^{-19}$ C (electron charge)
- $I_S$ = Reverse saturation current of transistor

## Common Mistakes

1. **Sign of output**: The log amplifier output is **negative** (for positive input) because $V_o = -V_{BE}$
2. **Only positive inputs**: Basic log amplifier works only for $V_i > 0$ (transistor needs forward bias)
3. **Not accounting for temperature**: Uncompensated circuits drift significantly with temperature
4. **Confusing log and antilog transistor placement**: Log = transistor in feedback; Antilog = transistor at input

## Self-Check Questions

> [!question]- Why are matched transistors used in compensated log amplifiers?
> To ensure $I_{S1} = I_{S2}$, so that when VBE voltages are subtracted, the $I_S$ terms cancel, eliminating temperature-dependent calibration errors.

> [!question]- What is the thermal voltage at 27 degrees C (300K)?
> $V_T = kT/q = (1.38 \times 10^{-23} \times 300) / (1.6 \times 10^{-19}) \approx 25.87$ mV $ \approx 26$ mV

> [!question]- How does a log-antilog pair enable analog multiplication?
> $\ln(A) + \ln(B) = \ln(AB)$. Take log of both signals, sum them (using a summing amplifier), then take antilog. Result is A x B.

## Concept Links
- Related: [Analog Voltage Multiplier](./08_analog_voltage_multiplier.md) (uses log + antilog for multiplication)
- Related: [Instrumentation Amplifier](./02_instrumentation_amplifier.md) (both handle small signals)
- Formulas: [Formula Sheet - Log/Antilog](./15_formula_sheet_ultimate.md#logarithmic-and-antilog-amplifier)
