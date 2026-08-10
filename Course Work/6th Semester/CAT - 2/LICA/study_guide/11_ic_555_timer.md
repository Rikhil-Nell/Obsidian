# 11 - IC 555 Timer

## Learning Objectives
- Understand the internal architecture of the 555 timer (pin diagram, comparators, flip-flop)
- Explain the role of each pin
- Derive the pulse width for monostable multivibrator ($T = 1.1RC$)
- Derive the frequency and duty cycle for astable multivibrator
- Draw timing diagrams for both modes

## Ground-Up Explanation

The **IC 555 Timer** is one of the most versatile and widely used integrated circuits. It can be configured as a monostable (one-shot) or astable (free-running) multivibrator.

### Internal Architecture

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s30_img1.png]]

**Internal components:**
- **Voltage divider**: Three equal resistors (5k each) create reference voltages at $\frac{2}{3}V_{CC}$ (upper) and $\frac{1}{3}V_{CC}$ (lower)
- **Upper Comparator (UC)**: Compares pin 6 (threshold) with $\frac{2}{3}V_{CC}$
- **Lower Comparator (LC)**: Compares pin 2 (trigger) with $\frac{1}{3}V_{CC}$
- **SR Flip-Flop**: Stores the output state
- **Discharge transistor Q1**: Internal NPN transistor connected to pin 7

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s2_img1.png]]

### Pin Description

| Pin | Name | Function |
|-----|------|----------|
| 1 | GND | Ground reference |
| 2 | Trigger | When voltage < $\frac{1}{3}V_{CC}$, output goes HIGH (sets FF) |
| 3 | Output | Switches between HIGH ($\approx V_{CC} - 0.5V$) and LOW ($\approx 0.1V$) |
| 4 | Reset | Forces output LOW when < 0.4V (active-low) |
| 5 | Control | External modulation of threshold; bypass with 0.1$\mu$F to ground |
| 6 | Threshold | When voltage > $\frac{2}{3}V_{CC}$, output goes LOW (resets FF) |
| 7 | Discharge | Internal transistor conducts when output LOW; discharges capacitor |
| 8 | $V_{CC}$ | Positive supply (4.5V to 16V) |

---

## Monostable Multivibrator (One-Shot)

Has **one stable state** and **one quasi-stable state**. The output is normally LOW and produces a single HIGH pulse when triggered.

### Circuit

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s3_img1.png]]

- Resistor R connected between pin 8 ($V_{CC}$) and pin 7 (discharge) / pin 6 (threshold)
- Capacitor C connected from pin 7/6 to ground
- Pin 2 (trigger) receives the trigger pulse

### Operation (Step-by-Step)

**Stable state (before trigger):**
- Pin 2 held above $\frac{1}{3}V_{CC}$ -- LC output = 0
- Capacitor voltage = 0 -- UC output = 0
- FF: S=0, R=0 -- Q retains 0, $\bar{Q}$=1
- Q1 is ON, capacitor clamped to ground
- Output (pin 3) = LOW

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s4_img1.png]]

**Trigger applied (pin 2 drops below $\frac{1}{3}V_{CC}$):**
- LC output goes HIGH (S=1)
- FF: S=1, R=0 -- Q=1, $\bar{Q}$=0
- Q1 turns OFF -- capacitor unclamped
- Output goes HIGH
- Capacitor begins charging through R toward $V_{CC}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s5_img1.png]]

**Capacitor charges past $\frac{2}{3}V_{CC}$:**
- UC output goes HIGH (R=1)
- FF: S=0, R=1 -- Q=0, $\bar{Q}$=1
- Q1 turns ON -- capacitor discharges to ground
- Output goes LOW -- returns to stable state
- Waits for next trigger

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s6_img1.png]]

### Pulse Width Derivation

Capacitor charges from 0 toward $V_{CC}$:

$$V_C(t) = V_{CC}(1 - e^{-t/RC})$$

Output returns LOW when $V_C = \frac{2}{3}V_{CC}$:

$$\frac{2}{3}V_{CC} = V_{CC}(1 - e^{-T/RC})$$

$$e^{-T/RC} = \frac{1}{3}$$

$$\boxed{T = RC \ln 3 \approx 1.1RC}$$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s7_img1.png]]

---

## Astable Multivibrator (Free-Running)

Has **no stable state** -- continuously oscillates between HIGH and LOW.

### Circuit

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s8_img1.png]]

- $R_A$: Connected between $V_{CC}$ and pin 7 (discharge)
- $R_B$: Connected between pin 7 and pin 6/pin 2 (threshold/trigger tied together)
- Capacitor C: Connected from pin 6/2 to ground
- Pins 2 and 6 connected together -- monitors capacitor voltage

### Operation

**Charging phase (output HIGH):**
- Capacitor charges through $R_A + R_B$ toward $V_{CC}$
- $V_C(t) = V_{CC}(1 - e^{-t/(R_A + R_B)C})$
- Q1 is OFF
- Continues until $V_C$ reaches $\frac{2}{3}V_{CC}$

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s9_img1.png]]

**Discharging phase (output LOW):**
- UC triggers, FF resets, Q1 turns ON
- Capacitor discharges through $R_B$ only (to ground via Q1)
- $V_C(t) = \frac{2}{3}V_{CC} \cdot e^{-t/(R_BC)}$
- Continues until $V_C$ drops to $\frac{1}{3}V_{CC}$
- LC triggers, FF sets, Q1 turns OFF, charging starts again

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s10_img1.png]]

### Timing Formulas

**Charge time (output HIGH):**

$$\boxed{T_H = 0.693(R_A + R_B)C}$$

**Discharge time (output LOW):**

$$\boxed{T_L = 0.693 \cdot R_B \cdot C}$$

**Total period:**

$$\boxed{T = T_H + T_L = 0.693(R_A + 2R_B)C}$$

**Frequency:**

$$\boxed{f = \frac{1.44}{(R_A + 2R_B)C}}$$

**Duty cycle:**

$$\boxed{D = \frac{T_H}{T} = \frac{R_A + R_B}{R_A + 2R_B} \times 100\%}$$

Note: Duty cycle is always > 50% because charging goes through $R_A + R_B$ but discharging only through $R_B$.

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s11_img1.png]]

## Common Mistakes

1. **Monostable trigger width**: The trigger pulse must be **shorter** than the output pulse. If the trigger stays LOW too long, the circuit won't retrigger properly.
2. **Astable duty cycle > 50%**: Because charge path ($R_A + R_B$) is always longer than discharge path ($R_B$), the HIGH time is always longer than LOW time.
3. **Pin 2 and 6 connection in astable**: Both must be tied together. Students forget this and wonder why it doesn't oscillate.
4. **Pin 4 (Reset)**: Must be connected to $V_{CC}$ (disabled) for normal operation. Floating pin 4 can cause erratic behavior.

## Self-Check Questions

> [!question]- If $R = 100k\Omega$ and $C = 10\mu F$, what is the monostable pulse width?
> $T = 1.1 \times 100 \times 10^3 \times 10 \times 10^{-6} = 1.1$ seconds

> [!question]- For astable with $R_A = 7k\Omega$, $R_B = 3k\Omega$, $C = 0.1\mu F$, find frequency and duty cycle.
> $f = 1.44 / ((7k + 2\times3k) \times 0.1\mu) = 1.44 / (13k \times 0.1\mu) = 1.44 / 1.3\times10^{-3} = 1108$ Hz
> $D = (7k + 3k)/(7k + 6k) = 10/13 = 76.9\%$

> [!question]- What are the two threshold voltages inside the 555 timer?
> Upper threshold = $\frac{2}{3}V_{CC}$ (pin 6/UC), Lower threshold = $\frac{1}{3}V_{CC}$ (pin 2/LC)

## Concept Links
- Prerequisite: [Comparator & Schmitt Trigger](./06_comparator_and_schmitt_trigger.md) (internal comparators)
- Prerequisite: [Waveform Generators](./10_waveform_generators.md) (op-amp-based square wave)
- Next: [555 Applications](./12_555_applications.md) (ramp, PWM, FSK, freq divider)
- Formulas: [Formula Sheet - 555 Timer](./15_formula_sheet_ultimate.md#ic-555-timer)
