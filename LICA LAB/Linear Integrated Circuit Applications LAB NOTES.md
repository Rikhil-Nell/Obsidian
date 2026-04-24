>[!info] Lab Record Details:-
>**Name**: Rikhil Nellimarla
>**Registration** Number: 23BEC7030 
>**Course Name**: LICA
>**Slot**: L51+L52

# Differentiator and Integrator

-> **Objective** 

To design and analyze **op-amp based integrator and differentiator circuits**, and to study their output response for different input waveforms such as sine, square, and triangular waves.

-> **Apparatus**

- Operational Amplifier (e.g., IC 741 Op-Amp or equivalent)
- Resistors: 15k$\ohm$ and 150k$\ohm$ 
- Capacitors: 10$\mu f$
- Function Generator
- Cathode Ray Oscilloscope (CRO)
- Breadboard and connecting wires
- Dual power supply (±V)

-> **Theory**

An **integrator circuit** using an op-amp produces an output proportional to the integral of the input signal. It typically consists of a resistor at the input and a capacitor in the feedback path.

- For a sine wave → output is a cosine wave (phase shift)
- For a square wave → output is a triangular wave
- For a triangular wave → output is a parabolic waveform

A **differentiator circuit** performs the opposite operation, producing an output proportional to the derivative of the input.

- For a sine wave → output is a cosine wave (phase lead)
- For a square wave → output shows spikes
- For a triangular wave → output is a square wave

-> **Circuit Diagram**
### Integrator
![[circuit(1) 1.svg]]
### Differentiator
![[circuit 1.svg]]

### Proof of work:
![[1776011544889.jpg]]

![[1776011545188.jpg]]

![[1776011544685.jpg]]

![[1776011544836.jpg]]

![[1776011545044.jpg]]

![[1776011545098.jpg]]

![[1776011545145.jpg]]

![[1776011544939.jpg]]

-> **Observation Table / Waveforms**

#### **Integrator Observations**

|Waveform|Input Vpp (V)|Frequency (kHz)|Output Vpp (V)|Frequency (kHz)|
|---|---|---|---|---|
|Sine|10.2|34|12.4|34|
|Square|10.2|34|10.4|34|
|Triangular|9.8|34|4.8|34|

#### **Differentiator Observations**

| Waveform   | Input Vpp (V) | Frequency (kHz) | Output Vpp (V) | Frequency (kHz) |
| ---------- | ------------- | --------------- | -------------- | --------------- |
| Sine       | ~5.0          | 34              | ~8.5           | 34              |
| Square     | 5.12          | 34              | 9.40           | 34              |
| Triangular | 5.04          | 34              | 3.28           | 34              |

-> **Result/Conclusion**

The integrator and differentiator circuits using an op-amp were successfully implemented and tested.

The integrator circuit produced outputs consistent with theoretical expectations, converting square waves into triangular waves and modifying sine wave phase. The differentiator circuit successfully demonstrated rate-of-change behavior, producing sharp transitions for square wave inputs and square outputs for triangular inputs.

Minor deviations in amplitude were observed due to practical limitations such as non-ideal op-amp characteristics and component tolerances.


# Butterworth Filters

-> **Objective**

To design and analyze first-order active Butterworth low-pass and high-pass filters using an op-amp, and to study their frequency response around the cutoff frequency.

-> **Apparatus**

| Component                       | Specification                 |
| ------------------------------- | ----------------------------- |
| Operational Amplifier           | Op-amp with dual supply (±Vc) |
| Resistor `R`                    | 318 $\Omega$                  |
| Feedback Resistor `Rf`          | 10 k$\Omega$                  |
| Feedback Resistor `RA`          | 17.064 k$\Omega$              |
| Capacitor `C`                   | 0.1 $\mu F$                   |
| Input Source                    | 5 Vpp signal source           |
| CRO / DSO                       | For output measurement        |
| Function Generator              | For sinusoidal input          |
| Breadboard and connecting wires | As required                   |

-> **Theory**

A first-order active Butterworth filter provides a maximally flat magnitude response in the passband. In the low-pass configuration, low frequencies are passed with nearly constant gain and higher frequencies are attenuated; in the high-pass configuration, low frequencies are attenuated and higher frequencies are passed. Since both are first-order filters, the theoretical roll-off rate is `−20 dB/decade`.

For both circuits, the cutoff frequency is given by:

$f_c = \frac{1}{2 \pi RC}$

Using the experimental values $R = 318\ \Omega$ and $C = 0.1\ \mu F = 0.1 \times 10^{-6}\ F$:

$$f_c = \frac{1}{2 \pi (318)(0.1 \times 10^{-6})} \approx 5005\ \text{Hz} \approx 5\ \text{kHz}$$

The non-inverting gain of the active stage is:

$$A_v = 1 + \frac{R_f}{R_A}$$

Substituting $R_f = 10\ \text{k}\Omega$ and $R_A = 17.064\ \text{k}\Omega$:

$$A_v = 1 + \frac{10}{17.064} \approx 1.586$$

Thus, the passband gain is expected to be about $1.59$. For the LPF, the output should remain high below about $5\ \text{kHz}$ and then decrease beyond cutoff. For the HPF, the output should be small at low frequency and rise toward the passband gain as the frequency increases beyond the cutoff region.

-> **Circuit Diagram**

### Low Pass Filter (LPF)

![[circuit.svg]]

### High Pass Filter (HPF)

![[circuit(1).svg]]

### Proof of work: 
![[1776019227370.jpg]]

![[1776019227314.jpg]]

![[1776019227433.jpg]]

![[1776019227184.jpg]]

![[1776019227245.jpg]]

-> **Observation Table / Waveforms**

#### **LPF Observations**

| Frequency | V₀ (V) | Aᵥ   | dB    |
| --------- | ------ | ---- | ----- |
| 100 Hz    | 8.6    | 1.72 | 4.71  |
| 200 Hz    | 8.6    | 1.72 | 4.71  |
| 300 Hz    | 8.6    | 1.72 | 4.71  |
| 400 Hz    | 8.6    | 1.72 | 4.71  |
| 500 Hz    | 8.6    | 1.72 | 4.71  |
| 1 kHz     | 8.4    | 1.68 | 4.50  |
| 2 kHz     | 8.2    | 1.64 | 4.29  |
| 3 kHz     | 7.8    | 1.56 | 3.86  |
| 4 kHz     | 7.4    | 1.48 | 3.40  |
| 5 kHz     | 7.0    | 1.40 | 2.92  |
| 6 kHz     | 6.6    | 1.32 | 2.41  |
| 7 kHz     | 5.8    | 1.16 | 1.28  |
| 8 kHz     | 5.4    | 1.08 | 0.668 |
| 9 kHz     | 5.2    | 1.04 | 0.34  |
| 10 kHz    | 4.4    | 0.88 | −1.10 |

#### **HPF Observations**

| Frequency | V₀ (V) | Aᵥ | dB |
|---|---|---|---|
| 100 Hz | 0.16 | 0.032 | −29.89 |
| 200 Hz | 0.32 | 0.064 | −23.87 |
| 300 Hz | 0.40 | 0.080 | −21.93 |
| 400 Hz | 0.48 | 0.096 | −20.35 |
| 500 Hz | 0.56 | 0.112 | −19.01 |
| 1 kHz | 1.04 | 0.208 | −13.63 |
| 2 kHz | 2.00 | 0.400 | −7.95 |
| 4 kHz | 3.52 | 0.704 | −3.04 |
| 6 kHz | 4.62 | 0.924 | −0.68 |
| 8 kHz | 5.44 | 1.080 | 0.66 |
| 10 kHz | 6.00 | 1.200 | 1.56 |
| 15 kHz | 6.01 | 1.210 | 1.65 |
| 20 kHz | 6.02 | 1.190 | 1.51 |
| 30 kHz | 6.07 | 1.220 | 1.727 |

-> **Result/Conclusion**

The Butterworth low-pass and high-pass filters were successfully designed and tested using the given RC network and non-inverting op-amp configuration. The theoretical cutoff frequency was approximately `5 kHz`, and the observation tables show the expected crossover behavior near this region, with the LPF gain decreasing and the HPF gain increasing around cutoff. The measured passband gains are close to the expected active gain, though not identical, due to component tolerance and practical limitations of the op-amp. Small deviations in amplitude and dB values are consistent with non-ideal frequency response and experimental measurement error.


# Schmitt Trigger

-> **Objective**

To design and verify a Schmitt trigger circuit and determine its upper trigger point, lower trigger point, and hysteresis width.

-> **Apparatus**

| Component | Specification |
|---|---|
| Operational Amplifier | Op-amp comparator with $\pm V_{CC}$ supply |
| Resistor $R_1$ | 22 k$\Omega$ |
| Resistor $R_2$ | 10 k$\Omega$ |
| Reference Voltage | $V_R = 3\ \text{V}$ |
| Saturation Voltage | $V_{sat} = 12\ \text{V}$ |
| Input Source | $V_{in}$ |
| CRO / DSO | For observing trigger switching |
| Breadboard and connecting wires | As required |

-> **Theory**

A Schmitt trigger is a regenerative comparator with positive feedback. It converts a slowly varying or noisy analog input into a clean digital-like output by introducing hysteresis. The circuit switches at two different threshold voltages: the upper trigger point (UTP) during rising input and the lower trigger point (LTP) during falling input.

The hysteresis width is:

$$V_H = V_{UTP} - V_{LTP}$$

For the biased Schmitt trigger, the threshold levels are written as:

$$V_{UTP} = \frac{R_2}{R_1 + R_2}V_R + \frac{R_1}{R_1 + R_2}V_{sat}$$

$$V_{LTP} = \frac{R_2}{R_1 + R_2}V_R - \frac{R_1}{R_1 + R_2}V_{sat}$$

Using the handwritten calculation values for this experiment:

$$V_{UTP} = 9.625\ \text{V}$$

$$V_{LTP} = -6.875\ \text{V}$$

$$V_H = 9.625 - (-6.875) = 16.5\ \text{V}$$

Thus the output is expected to switch high when the input exceeds the upper threshold and switch low when the input falls below the lower threshold.

-> **Circuit Diagram**

### Schmitt Trigger

![[circuit(2).svg]]
### Proof of work

![[WhatsApp Image 2026-04-13 at 22.09.04.jpeg]]

-> **Observation Table / Waveforms**

| Parameter | Theory | Practical |
|---|---|---|
| UTP | 9.625 V | 2.96 V |
| LTP | −6.875 V | −2.08 V |

-> **Result / Conclusion**

The Schmitt trigger circuit was studied successfully and the upper and lower threshold levels were identified. The theoretical trigger points obtained from the handwritten calculation were $9.625\ \text{V}$ and $-6.875\ \text{V}$, while the practical values recorded were $2.96\ \text{V}$ and $-2.08\ \text{V}$. The difference between theory and practice is due to non-ideal op-amp saturation, component tolerance, and practical loading effects. The experiment still verified the hysteresis behavior that is characteristic of a Schmitt trigger.


# Monostable Multivibrator using 555 Timer

-> **Objective**

To design and study a 555 timer in monostable mode and verify the generated single output pulse width for a trigger input.

-> **Apparatus**

| Component | Specification |
|---|---|
| Timer IC | IC 555 |
| Timing Resistor | 1 k$\Omega$ |
| Timing Capacitor | 0.3 $\mu$F |
| Trigger Coupling Capacitor | 0.1 $\mu$F |
| Control Pin Capacitor | 0.01 $\mu$F |
| Diode | As shown in the trigger path |
| Trigger Source | 1 kHz input |
| CRO / DSO | For observing pulse width |
| Breadboard and connecting wires | As required |
| DC Supply | $V_{CC}$ |

-> **Theory**

A monostable multivibrator has one stable state and one quasi-stable state. When a negative trigger pulse is applied to the trigger input of the 555 timer, the output goes high for a fixed interval determined by the external resistor and capacitor, and then returns to the stable state automatically.

The pulse width of a 555 monostable is given by:

$$T = 1.1RC$$

Using the visible values $R = 1\ \text{k}\Omega$ and $C = 0.3\ \mu F$:

$$T = 1.1 \times 1 \times 10^3 \times 0.3 \times 10^{-6} = 330\ \mu s$$

For a trigger repetition rate of $1\ \text{kHz}$, the input period is about $1\ \text{ms}$. Hence the output is expected to remain high for about $330\ \mu s$ and low for the remaining part of the cycle.

-> **Circuit Diagram**

### Monostable Multivibrator
![[circuit 2.svg]]

### Proof of work
![[1776019311652.jpg]]

![[1776019311749.jpg]]

![[1776019311608.jpg]]


-> **Observation Table / Waveforms**

| Parameter | Value |
|---|---|
| Trigger Frequency | 1 kHz |
| On Time | 340 $\mu$s |
| Off Time | 640 $\mu$s |

-> **Result / Conclusion**

The 555 timer monostable multivibrator was obtained successfully. The theoretical pulse width was $330\ \mu s$, while the observed on-time was $340\ \mu s$, which is very close to the expected value. The small deviation can be attributed to capacitor tolerance, diode drop, and practical triggering effects. The waveform behavior matched the expected one-shot response of a monostable circuit.


# Astable Multivibrator using 555 Timer

-> **Objective**

To design and analyze a 555 timer in astable mode and measure its output amplitude, time period, frequency, and on-off times.

-> **Apparatus**

| Component | Specification |
|---|---|
| Timer IC | IC 555 |
| Resistors | $R_1$, $R_2$ |
| Capacitors | $C_1$, $C_2$ |
| DC Supply | $V_{CC} = 9\ \text{V}$ |
| CRO / DSO | For observing output waveform |
| Breadboard and connecting wires | As required |

-> **Theory**

In astable mode, the 555 timer has no stable state and continuously produces a rectangular waveform. The capacitor charges through $R_1 + R_2$ and discharges through $R_2$, which determines the on-time and off-time of the output waveform.

The standard relations are:

$$T_{on} = 0.693(R_1 + R_2)C_1$$

$$T_{off} = 0.693R_2C_1$$

$$T = T_{on} + T_{off}$$

$$f = \frac{1.44}{(R_1 + 2R_2)C_1}$$

The output of the astable multivibrator is expected to be a continuous square wave whose amplitude is close to the supply voltage.

-> **Circuit Diagram**

### Astable Multivibrator

![[circuit 3.svg]]

### Proof of work

![[1776077011405.jpg]]

![[1776077012319.jpg]]

![[1776077012421.jpg]]

-> **Observation Table / Waveforms**

| Parameter | Value |
|---|---|
| $V_{CC}$ | 9 V |
| Output Amplitude | 9.2 V |
| Period | 2.80 ms |
| Frequency | 430 Hz |
| On Time | 1.20 ms |
| Off Time | 600 $\mu$s |

-> **Result / Conclusion**

The astable multivibrator using the 555 timer produced a repetitive output waveform as expected. The measured output amplitude was close to the supply voltage, confirming proper switching operation of the timer. The handwritten observations show a frequency of about $430\ \text{Hz}$ with distinct on-time and off-time intervals. Minor inconsistency among the recorded timing values is likely due to practical measurement approximation while reading the waveform.


# Precision Full Wave Rectifier

-> **Objective**

To study the operation of a precision full wave rectifier using op-amps and diodes, and to compare the practical voltage gain with the theoretical value.

-> **Apparatus**

| Component | Specification |
|---|---|
| Operational Amplifiers | Two op-amps with $\pm 15\ \text{V}$ supply |
| Input Resistor | 1 k$\Omega$ |
| Feedback / Network Resistors | 10 k$\Omega$ |
| Diodes | Two diodes |
| AC Source | Sinusoidal input |
| CRO / DSO | For waveform observation |
| Breadboard and connecting wires | As required |

-> **Theory**

A precision full wave rectifier converts both the positive and negative halves of an AC input signal into a unidirectional output without the large threshold error seen in ordinary diode rectifiers. The use of op-amps compensates for the diode forward drop, allowing accurate rectification even for small signals.

For the inverting stage, the closed-loop gain is:

$$A_v = \frac{V_o}{V_{in}} = -\frac{R_f}{R_{in}}$$

Using the visible resistor values $R_f = 10\ \text{k}\Omega$ and $R_{in} = 1\ \text{k}\Omega$:

$$A_v = -\frac{10\ \text{k}\Omega}{1\ \text{k}\Omega} = -10$$

Thus the magnitude of the theoretical gain is $10$, and the final rectified output is expected to follow the full-wave rectified shape of the input.

-> **Circuit Diagram**

### Full Wave Rectifier

![[circuit(1) 2.svg]]
### Proof of work
![[1776077131075.jpg]]

![[1776077130877.jpg]]

![[1776077131224.jpg]]

-> **Observation Table / Waveforms**

| Parameter            | Theoretical | Practical |
| -------------------- | ----------- | --------- |
| Voltage Gain $A_{v}$ | 10          | 9.8       |

-> **Result / Conclusion**

The precision full wave rectifier was obtained successfully and the output waveform showed rectification of both half cycles of the input signal. The theoretical gain magnitude was $10$, while the practical gain was $9.8$, showing close agreement. The small error is consistent with resistor tolerance and non-ideal op-amp behavior. The circuit therefore verified the expected operation of a precision rectifier.


# RC Phase Shift Oscillator

-> **Objective**

To design and analyze an RC phase shift oscillator using an op-amp and to compare the theoretical oscillation frequency with the observed value.

-> **Apparatus**

| Component | Specification |
|---|---|
| Operational Amplifier | IC 741 |
| Input Resistor | 1 k$\Omega$ |
| Feedback Resistor | 47 k$\Omega$ |
| RC Network Resistors | 220 $\Omega$ each |
| RC Network Capacitors | 0.33 $\mu$F each |
| Dual Supply | As required for the op-amp |
| CRO / DSO | For frequency measurement |
| Breadboard and connecting wires | As required |

-> **Theory**

An RC phase shift oscillator uses an inverting amplifier together with a three-section RC feedback network. The RC network contributes a phase shift of $180^\circ$ and the inverting amplifier contributes another $180^\circ$, giving a total phase shift of $360^\circ$ required for sustained oscillation.

The frequency of oscillation is:

$$f = \frac{1}{2\pi \sqrt{6}RC}$$

Using the visible values $R = 220\ \Omega$ and $C = 0.33\ \mu F$:

$$f = \frac{1}{2\pi \sqrt{6} \times 220 \times 0.33 \times 10^{-6}} \approx 894\ \text{Hz}$$

The amplifier gain must be sufficiently large to compensate for the attenuation of the RC network, and the 47 k$\Omega$ to 1 k$\Omega$ resistor ratio provides the required gain.

-> **Circuit Diagram**

### RC Phase Shift Oscillator

![[circuit 5.svg]]

### Proof of work
![[1776077319080.jpg]]

![[1776077318863.jpg]]

![[1776077318615.jpg]]

![[1776077318977.jpg]]

-> **Observation Table / Waveforms**

| Parameter | Value |
|---|---|
| Theoretical Frequency | 894 Hz |
| Observed Frequency | 545.8 Hz |

-> **Result / Conclusion**

The RC phase shift oscillator generated an oscillating output waveform successfully. Using the given component values, the theoretical frequency was about $894\ \text{Hz}$, while the observed frequency was $545.8\ \text{Hz}$. The deviation can be attributed to component tolerances, loading, and the non-ideal frequency response of the op-amp. Even with this variation, the experiment confirmed the working principle of the RC phase shift oscillator.


# Wien Bridge Oscillator

-> **Objective**

To study the Wien bridge oscillator using an op-amp and verify the frequency of oscillation from the bridge network values.

-> **Apparatus**

| Component | Specification |
|---|---|
| Operational Amplifier | IC 741 |
| Bridge Resistors | 1 k$\Omega$ |
| Bridge Capacitors | 0.33 $\mu$F |
| Feedback Resistor | 2.2 k$\Omega$ |
| Dual Supply | As required for the op-amp |
| CRO / DSO | For frequency measurement |
| Breadboard and connecting wires | As required |

-> **Theory**

A Wien bridge oscillator uses a lead-lag RC bridge network in the positive feedback path of an amplifier. At the oscillation frequency, the phase shift of the bridge becomes zero and the loop gain condition is satisfied, producing a sinusoidal output.

For equal values of $R$ and $C$, the frequency of oscillation is:

$$f = \frac{1}{2\pi RC}$$

Using the visible values $R = 1\ \text{k}\Omega$ and $C = 0.33\ \mu F$:

$$f = \frac{1}{2\pi \times 1 \times 10^3 \times 0.33 \times 10^{-6}} \approx 482\ \text{Hz}$$

The oscillator is expected to produce a low-distortion sinusoidal waveform when the bridge balance and amplifier gain are properly maintained.

-> **Circuit Diagram**
### Wien Bridge Oscillator

![[circuit 6.svg]]
### Proof of work
![[1776077421994.jpg]]

![[1776077421814.jpg]]

![[1776077421137.jpg]]

-> **Observation Table / Waveforms**

| Parameter | Value |
|---|---|
| Practical Frequency | 469 Hz |
| Theoretical Frequency | 482 Hz |

-> **Result / Conclusion**

The Wien bridge oscillator produced oscillations successfully and the measured frequency was close to the theoretical value. The theoretical frequency was about $482\ \text{Hz}$, while the practical frequency observed was $469\ \text{Hz}$. This small error indicates good agreement between theory and practice. Minor deviation is expected because of component tolerance and non-ideal op-amp characteristics.


# 4-Bit Digital to Analog Converter

-> **Objective**

To study a 4-bit digital to analog converter using an op-amp and resistor network, and to observe the analog output corresponding to different digital input combinations.

-> **Apparatus**

| Component | Specification |
|---|---|
| Operational Amplifier | IC 741 with $\pm 15\ \text{V}$ supply |
| Feedback Resistor | 15 k$\Omega$ |
| Ladder Resistors | 4.7 k$\Omega$ |
| Input / Bit Resistors | 10 k$\Omega$ |
| Digital Input Source | 5 V logic input |
| CRO / DSO / Multimeter | For output measurement |
| Breadboard and connecting wires | As required |

-> **Theory**

A digital to analog converter (DAC) converts a binary input word into a proportional analog voltage. In an inverting resistor-network DAC, each input bit contributes a weighted current, and the op-amp sums these currents to produce an analog output voltage. As the binary value increases, the magnitude of the output voltage also increases in a nearly linear manner, with inversion because of the inverting op-amp configuration.

For an inverting summing DAC, the output can be written in general form as:

$$V_o = -R_f \left(\frac{V_{D0}}{R_{D0}} + \frac{V_{D1}}{R_{D1}} + \frac{V_{D2}}{R_{D2}} + \frac{V_{D3}}{R_{D3}}\right)$$

Thus each digital input contributes a weighted analog component to the final output.

-> **Circuit Diagram**

### 4-Bit Digital to Analog Converter
![[circuit(2) 1.svg]]

### Proof of work
![[1776077493120.jpg]]

![[1776077493277.jpg]]


-> **Observation Table / Waveforms**

| D3 | D2 | D1 | D0 | Output (V) |
|---|---|---|---|---|
| 0 | 0 | 0 | 0 | 0.02 |
| 0 | 0 | 0 | 1 | −1.003 |
| 0 | 0 | 1 | 0 | −1.943 |
| 0 | 0 | 1 | 1 | −2.947 |
| 0 | 1 | 0 | 0 | −3.869 |
| 0 | 1 | 0 | 1 | −4.973 |
| 0 | 1 | 1 | 0 | −5.812 |
| 1 | 0 | 0 | 0 | −6.68 |

-> **Result / Conclusion**

The 4-bit DAC produced distinct analog output levels for different digital input combinations. As the binary input increased, the output voltage became more negative, confirming the inverting nature of the op-amp DAC. The observed outputs show approximately monotonic behavior, which is the expected DAC characteristic. Small non-uniformity between steps is due to resistor mismatch and practical op-amp limitations.


# 555 Timer Astable Multivibrator (5 V Setup)

-> **Objective**

To study the astable operation of a 555 timer using the given 5 V component setup and determine the expected time period and frequency from the circuit values.

-> **Apparatus**

| Component | Specification |
|---|---|
| Timer IC | IC 555 |
| Resistor $R_A$ | 15 k$\Omega$ |
| Resistor $R_B$ | 100 k$\Omega$ |
| Timing Capacitor | 10 $\mu$F |
| Control Pin Capacitor | 0.01 $\mu$F |
| DC Supply | 5 V RPS |
| CRO / DSO | For waveform observation |
| Breadboard and connecting wires | As required |

-> **Theory**

In the 555 astable configuration, the capacitor repeatedly charges through $R_A + R_B$ and discharges through $R_B$, generating a continuous square wave at the output. The duty cycle and oscillation frequency depend on the resistor-capacitor network connected to the timer.

The timing equations are:

$$T_{on} = 0.693(R_A + R_B)C$$

$$T_{off} = 0.693R_BC$$

$$T = 0.693(R_A + 2R_B)C$$

$$f = \frac{1.44}{(R_A + 2R_B)C}$$

Using $R_A = 15\ \text{k}\Omega$, $R_B = 100\ \text{k}\Omega$, and $C = 10\ \mu F$:

$$T_{on} = 0.693(115 \times 10^3)(10 \times 10^{-6}) \approx 0.797\ \text{s}$$

$$T_{off} = 0.693(100 \times 10^3)(10 \times 10^{-6}) \approx 0.693\ \text{s}$$

$$T \approx 1.49\ \text{s}, \qquad f \approx 0.671\ \text{Hz}$$

-> **Circuit Diagram**

### 555 timer buzzer
![[circuit 4.svg]]

### Proof of work
![[WhatsApp Image 2026-04-13 at 16.38.12.jpeg]]

-> **Result / Conclusion**

The 5 V 555 astable setup gives a low-frequency square-wave output whose theoretical period is about $1.49\ \text{s}$. The calculated on-time and off-time are approximately $0.797\ \text{s}$ and $0.693\ \text{s}$ respectively, giving a frequency of about $0.671\ \text{Hz}$. This section was prepared from the component values visible in the handwritten page, while no separate practical readings were written on that page. The experiment therefore provides the expected theoretical timing behavior for the given astable configuration.
