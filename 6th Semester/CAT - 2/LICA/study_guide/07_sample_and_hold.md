# 07 - Sample and Hold Circuit

## Learning Objectives
- Understand the purpose and working of a sample and hold (S&H) circuit
- Identify the role of each component (MOSFET switch, capacitor, op-amp buffers)
- Explain sampling and hold modes
- Know the Nyquist criterion for proper sampling
- List applications

## Ground-Up Explanation

A **Sample and Hold (S&H)** circuit captures an analog input voltage at a specific instant and **holds** that value constant for a period of time. This is essential for analog-to-digital conversion, where the ADC needs a stable voltage during its conversion process.

**Analogy**: Imagine photographing a moving car. The camera (S&H circuit) "samples" the car's position at the instant the shutter clicks and "holds" that image frozen, even though the actual car keeps moving.

### Circuit Architecture

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s36_img3.png]]

The circuit has three main components:
1. **Input buffer** (Op-amp 1): Voltage follower that provides high input impedance and isolates the source
2. **MOSFET analog switch**: Controlled by voltage $V_C$ -- acts as a gate
3. **Hold capacitor + Output buffer** (Op-amp 2): Stores the sampled voltage and provides it to the load

### Operating Modes

**Sample Mode** ($V_C$ = positive, MOSFET ON):
- MOSFET conducts, connecting input to capacitor
- Capacitor charges to the input voltage with time constant $(R_0 + r_{ds})C$
- The entire input waveform appears at the output through Op-amp 2

**Hold Mode** ($V_C$ = negative, MOSFET OFF):
- MOSFET turns OFF, disconnecting input from capacitor
- Since $R_{in}$ of Op-amp 2 is very high, the capacitor retains its charge
- Output stays at the voltage the capacitor was charged to at the moment of switch-off

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s37_img1.png]]

### Nyquist Criterion

For proper sampling, the control voltage frequency must be **at least twice** the input signal frequency:

$$f_{sample} \geq 2 \times f_{signal}$$

This is the **Nyquist sampling theorem**. Violating it causes **aliasing** -- the reconstructed signal will be distorted.

## Key Formulas

**Charging time constant:**

$$\boxed{\tau = (R_0 + r_{ds}) \cdot C}$$

**Nyquist criterion:**

$$\boxed{f_{sample} \geq 2 \cdot f_{input}}$$

**Hold voltage:**

$$\boxed{V_{hold} = V_{in}(t_{sample})}$$

Where $t_{sample}$ is the instant when MOSFET switches from ON to OFF.

## Applications

- Analog-to-Digital Converters (ADC)
- Pulse Amplitude Measurement
- Digital Oscilloscopes
- Data Acquisition Systems
- Signal Reconstruction

## Common Mistakes

1. **Forgetting the buffers**: Both input and output buffers are essential. Without the output buffer, load current would discharge the capacitor.
2. **Confusing $V_C$ with $V_i$**: $V_C$ is the control/clock signal (digital); $V_i$ is the analog signal being sampled.
3. **Capacitor value tradeoff**: Too small $\to$ voltage droops quickly during hold. Too large $\to$ slow charging, can't track fast signals.

## Self-Check Questions

> [!question]- What happens if the sampling frequency is less than twice the input frequency?
> Aliasing occurs -- the sampled signal will not accurately represent the original signal. High-frequency components will appear as lower frequencies.

> [!question]- Why is a MOSFET used as the switch instead of a BJT?
> MOSFETs have very high OFF-resistance (virtually infinite) which prevents charge leakage from the capacitor during hold mode. They also have no offset voltage when ON.

> [!question]- What determines how long the hold voltage remains accurate?
> The leakage current of the capacitor, the input bias current of Op-amp 2, and the drain-source leakage of the MOSFET. Together these cause a slow "droop" in the held voltage.

## Concept Links
- Related: [Analog Voltage Multiplier](./08_analog_voltage_multiplier.md) (both are signal processing circuits)
- Related: ADC (sample and hold is the front-end of every ADC)
- Formulas: [Formula Sheet - Sample & Hold](./15_formula_sheet_ultimate.md#sample-and-hold-circuit)
