# 16 - Detailed Exam Solutions

To properly bridge the gap between theoretical concepts and the specific analytical questions on your exam, here are step-by-step mathematical answers to the 5 exam questions provided.

---

## Question 1: Practical Integrator
**Given:** An op-amp practical integrator circuit with $R_1 = 10k\Omega$, $R_f = 100k\Omega$, and $C = 10nF$.
**Find:**
1. Lower frequency limit of integration.
2. Output response for:
   (a) $1V$ peak sine-wave at $2.5kHz$.
   (b) Constant amplitude of $1V$ from 0 to 0.4 ms full square-wave.

**Solution:**

### 1. Lower Frequency Limit of Integration ($f_a$)
The lower frequency limit of integration (also known as the break frequency) is the frequency above which the circuit acts as a true integrator, overcoming the low-frequency attenuation caused by $R_f$.
$$f_a = \frac{1}{2\pi R_f C}$$
$$f_a = \frac{1}{2 \pi (100 \times 10^3) (10 \times 10^{-9})} = \frac{1}{2 \pi \times 10^{-3}} = \frac{1000}{2\pi} \approx 159.15 \text{ Hz}$$
*(For signal frequencies $f \gg 159.15 \text{ Hz}$, the circuit integrates accurately).*

### 2. Output Responses
First, compute the primary integrator time constant ($R_1 C$):
$R_1 C = (10 \times 10^3)(10 \times 10^{-9}) = 10^{-4} \text{ seconds}$.

**(a) $1V$ peak sine-wave at $2.5kHz$:**
Since $2.5 \text{ kHz} \gg f_a$ (159.15 Hz), the circuit acts as an ideal integrator.
Input signal: $V_{in}(t) = 1 \sin(2\pi \times 2500 t) = \sin(5000\pi t)$
The output voltage for an integrator is given by:
$$V_{out}(t) = -\frac{1}{R_1 C} \int V_{in}(t) dt$$
$$V_{out}(t) = -\frac{1}{10^{-4}} \int \sin(5000\pi t) dt = -10000 \left[ \frac{-\cos(5000\pi t)}{5000\pi} \right]$$
$$V_{out}(t) = \frac{10000}{5000\pi} \cos(5000\pi t) = \frac{2}{\pi} \cos(5000\pi t)$$
$$V_{out}(t) \approx 0.636 \cos(5000\pi t) \text{ V}$$
**Result:** The output is a cosine wave (shifted by $90^\circ$ relative to the input) with a peak voltage of **0.636 V**.

**(b) Constant amplitude of $1V$ from 0 to 0.4 ms full square-wave:**
A full square wave with a duration of 0.4 ms implies a half-period $T/2 = 0.2 \text{ ms}$. 
Integrating a square wave yields a **triangular wave**.
When $V_{in}$ is held constant at $+1V$ during a half-cycle ($0.2 \text{ ms}$):
$$\Delta V_{out} = -\frac{1}{R_1 C} \int_0^{T/2} V_{in} dt = -10000 \int_0^{0.0002} 1 dt$$
$$\Delta V_{out} = -10000 \times 0.0002 = -2 \text{ V}$$
Because the square wave is alternating $\pm 1V$, the output is a symmetrical triangle wave. During the positive half-cycle, it ramps down by 2V, and during the negative half-cycle, it ramps up by 2V.
**Result:** The output is a **triangular wave** with a peak-to-peak amplitude of **$2\text{V}$** (ranging from $+1\text{V}$ to $-1\text{V}$).

---

## Question 2: Precision Clipper (Positive Base Clipper)
**Given:** Fig 1 shows an op-amp with input $v_1$ at the non-inverting (+) terminal. The output drives the anode of diode $D$. The cathode of $D$ is $v_o$, which feeds back directly to the inverting (-) terminal. A resistor $R_2 = 10k\Omega$ connects $v_o$ to the wiper of potentiometer $R_p$. The potentiometer connects to a negative supply, creating a reference voltage $V_{ref}$ (which is negative).

**Analysis of Circuit Operation:**
This circuit is a **Precision Low-Level Clipper** (base clipper) featuring a reference voltage.

1. **Condition 1: $v_1 > V_{ref}$**
   The non-inverting input is higher than the reference voltage. The op-amp output swings positive, forward-biasing diode $D$.
   The feedback loop is active. According to the virtual short principle, the op-amp forces $v^- = v^+ = v_1$.
   Since $v_o$ is connected directly to $v^-$, the output exactly follows the input: $v_o = v_1$. The circuit acts as a non-inverting buffer follower.
   
2. **Condition 2: $v_1 < V_{ref}$**
   The op-amp tries to pull $v^-$ down to match $v_1$. It drives its output negative to do so.
   However, a negative voltage at the op-amp output reverse-biases diode $D$, acting as an open switch.
   The feedback loop is broken. No current flows into the high-impedance inverting terminal, meaning no current flows through $R_2$.
   Because there is no voltage drop across $R_2$, $v_o$ simply equals the voltage at the potentiometer wiper.
   $v_o = V_{ref}$ 

**Output Voltage Conclusion:**
$$\boxed{ v_o = \begin{cases} v_1 & \text{for } v_1 \geq V_{ref} \\ V_{ref} & \text{for } v_1 < V_{ref} \end{cases} }$$
**Result:** The output follows the input waveform exactly, but any signal value below $V_{ref}$ is clipped off cleanly at the potentiometer's negative reference voltage.

---

## Question 3: Analog Voltage Multiplier Derivation
**Given:** Realize the operation of a multiplication circuit and find the output voltage when two input voltages are not identical ($V_x \neq V_y$).

**Circuit Realization (Block Diagram Concept):**
An analog multiplier utilizes the fundamental logarithmic identity: $\ln(V_x \cdot V_y) = \ln(V_x) + \ln(V_y)$.
The circuit requires 3 distinct op-amp stages:
1. Two **Log Amplifiers** to convert the linear inputs $V_x$ and $V_y$ into logarithmic voltages.
2. A **Summing Amplifier** to add the two log outputs together.
3. An **Antilog Amplifier** to convert the log sum back to a linear scale.

**Mathematical Derivation:**
Let's assume the log amplifiers utilize identical transistors in the feedback loop, with reverse saturation current $I_s$ and input resistors $R$.

1. **Log Amplifier Outputs:**
   $$V_{Lx} = -V_T \ln\left(\frac{V_x}{I_s R}\right)$$
   $$V_{Ly} = -V_T \ln\left(\frac{V_y}{I_s R}\right)$$
   *(where $V_T = kT/q \approx 26mV$)*

2. **Summing Amplifier Output:**
   These two signals are fed into an inverting Summing Amplifier designed for unity gain ($R_f = R_{in}$):
   $$V_{sum} = -(V_{Lx} + V_{Ly}) = V_T \left[ \ln\left(\frac{V_x}{I_s R}\right) + \ln\left(\frac{V_y}{I_s R}\right) \right]$$
   Using log addition identities:
   $$V_{sum} = V_T \ln\left(\frac{V_x V_y}{I_s^2 R^2}\right)$$

3. **Antilog Amplifier Output:**
   This logarithmic sum is fed into an Antilog Amplifier with a reference feedback resistor $R_{ref}$:
   $$V_o = -I_s R_{ref} \cdot e^{V_{sum} / V_T}$$
   Substituting $V_{sum}$:
   $$V_o = -I_s R_{ref} \cdot e^{\ln\left(\frac{V_x V_y}{I_s^2 R^2}\right)}$$
   The exponential and logarithm operations cancel each other out:
   $$V_o = -I_s R_{ref} \cdot \left(\frac{V_x V_y}{I_s^2 R^2}\right)$$
   $$V_o = -\left(\frac{R_{ref}}{I_s R^2}\right) \cdot (V_x \cdot V_y)$$

**Result:** The final output voltage is **$V_o = -K \cdot (V_x \cdot V_y)$**, where $K = \frac{R_{ref}}{I_s R^2}$ is a fixed hardware scale factor. The two independent voltages are successfully multiplied.

---

## Question 4: RC Op-amp based Oscillator Design
**Given:** Target frequency $f_0 = 100 \text{ Hz}$, Capacitor $C = 0.1\mu F$. Design and explain.

**Circuit Selection:** 
For a low-frequency $100\text{ Hz}$ target, an **RC Phase Shift Oscillator** is appropriate.
The circuit consists of:
1. An op-amp in an **inverting configuration**, which provides an initial $180^\circ$ phase shift.
2. A feedback network consisting of three cascaded RC high-pass sections. Each section contributes $60^\circ$ of phase shift at the resonant frequency, providing an additional $180^\circ$ phase shift.
3. Total loop phase shift is $360^\circ$ (which equals $0^\circ$), satisfying the Barkhausen criterion for positive feedback.

**Calculations:**
The frequency of oscillation for a 3-stage RC Phase Shift Oscillator is:
$$f_0 = \frac{1}{2\pi R C \sqrt{6}}$$
Solving for R:
$$100 = \frac{1}{2\pi \cdot R \cdot (0.1 \times 10^{-6}) \cdot \sqrt{6}}$$
$$R = \frac{1}{2\pi \times 100 \times 10^{-7} \times \sqrt{6}}$$
$$R = \frac{1}{6.28 \times 10^{-5} \times 2.449} = \frac{1}{1.538 \times 10^{-4}}$$
$$R \approx 6500 \Omega = 6.5 \text{ k}\Omega$$
*(A standard $6.8 \text{ k}\Omega$ resistor or a $10 \text{ k}\Omega$ potentiometer tuned to $6.5 \text{ k}\Omega$ should be chosen for the three RC stages).*

**Amplifier Gain Design:**
To sustain oscillations, the Barkhausen criterion dictates the loop gain magnitude $|A\beta| \geq 1$. For this specific 3-stage network, the required amplifier gain is $-29$.
$$|A| = \frac{R_f}{R_1} \geq 29$$
Select a standard input resistor $R_1 = 10 \text{ k}\Omega$.
$$R_f \geq 29 \times 10 \text{ k}\Omega = 290 \text{ k}\Omega$$
*(A $330 \text{ k}\Omega$ standard resistor or a variable resistor should be used in the feedback loop to precisely tune start-up conditions).*

**Design Summary:** Use three RC sections with $C = 0.1\mu F$ and $R = 6.5 \text{ k}\Omega$, driven by an inverting op-amp with $R_1 = 10 \text{ k}\Omega$ and $R_f \ge 290 \text{ k}\Omega$.

---

## Question 5: Basic PLL and Frequency Generation Application
**Given:** Explain basic PLL operation and its main application to generate frequency proportional to input voltage.

**1. Basic PLL Operation:**
A Phase Locked Loop (PLL) is an automatic control feedback system used for frequency and phase synchronization. It consists of three interconnected blocks:
- **Phase Detector (PD):** Compares the incoming signal phase ($f_{in}$) against the feedback signal from the VCO ($f_{out}$). It outputs an "error voltage" that is proportional to the phase difference between the two signals.
- **Low Pass Filter (LPF):** Takes the raw, noisy error voltage from the phase detector and smooths it out. It removes high-frequency AC components, yielding a stable DC control voltage ($V_c$). It also controls the dynamic response (capture range) of the PLL.
- **Voltage Controlled Oscillator (VCO):** A local oscillator whose output frequency is linearly controlled by the DC voltage $V_c$ generated by the LPF.

**How it locks:** Initially, the VCO oscillates at its free-running frequency. When an input is applied, the PD generates an error voltage. The LPF smooths it, which shifts the VCO frequency toward the input frequency. Once the frequencies match ($f_{out} = f_{in}$), the PLL achieves "lock" and will continuously track fluctuations in the input signal.

**2. Main Application: Generating Frequency Proportional to Input Voltage**
The core of the PLL is the **Voltage Controlled Oscillator (VCO)**. When used in a synthesizer or modulator context, the VCO maps a voltage level to an exact frequency output.
- **Frequency Modulator (Voltage-to-Frequency Conversion):** By applying an external modulating signal directly into the VCO's control voltage pin (temporarily bypassing or supplementing the LPF output), the VCO generates an output waveform whose frequency shifts perfectly in proportion to the input voltage amplitude ($f_{out} = f_0 + K_v V_{in}$). 
- **Application in FM:** This property is the foundation of **FM Transmitters** and **Telemetry**, where audio or data (input voltage levels) are broadcast over radio by directly shifting the carrier frequency proportionally to the audio signal amplitude. In reverse, the PLL as a whole serves as an FM Demodulator, tracking the fluctuating frequency to produce an exact voltage copy of the original signal.
