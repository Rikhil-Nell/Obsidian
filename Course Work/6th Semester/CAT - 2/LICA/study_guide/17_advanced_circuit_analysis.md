# 17 - Advanced Circuit Analysis & Derivations

This document is designed to bridge the gap between theoretical knowledge and complex, exam-style "curveball" numerical questions. It focuses on formal mathematical derivations, transfer functions, and general node-voltage approaches to non-standard op-amp circuits.

## 1. The Practical Integrator: Mathematical Deep Dive

An **ideal integrator** has a feedback capacitor $C$ and an input resistor $R_1$. Its gain at DC ($f=0$) is infinite, which means any tiny input offset voltage will eventually saturate the op-amp. 
To fix this, a **practical integrator** adds a feedback resistor $R_f$ in parallel with $C$.

### 1.1 Transfer Function Derivation
Let's find the transfer function $A_v(s) = \frac{V_o(s)}{V_i(s)}$ using Laplace transforms ($s = j\omega$).

1. The impedance of the input arm is $Z_1 = R_1$.
2. The impedance of the feedback arm is $R_f$ in parallel with $C$:
   $$Z_f = R_f \parallel \left(\frac{1}{sC}\right) = \frac{R_f \cdot \frac{1}{sC}}{R_f + \frac{1}{sC}} = \frac{R_f}{1 + s R_f C}$$
3. For an inverting amplifier, the gain is $A_v(s) = -\frac{Z_f}{Z_1}$:
   $$A_v(s) = -\frac{\frac{R_f}{1 + s R_f C}}{R_1} = -\frac{R_f/R_1}{1 + s R_f C}$$

Substituting $s = j\omega$:
$$A_v(j\omega) = \frac{-R_f/R_1}{1 + j\omega R_f C}$$

### 1.2 Important Frequencies
The magnitude of the gain is:
$$|A_v| = \frac{R_f/R_1}{\sqrt{1 + (\omega R_f C)^2}} = \frac{R_f/R_1}{\sqrt{1 + (f/f_a)^2}}$$

From this, we define two critical frequencies:

**1. Lower Frequency Limit of Integration (Break Frequency, $f_a$):**
This is the frequency where the real and imaginary parts of the denominator are equal ($\omega R_f C = 1$). 
At $f_a$, the gain drops by 3dB from its maximum DC value of $R_f / R_1$.
$$\boxed{f_a = \frac{1}{2\pi R_f C}}$$
**Crucial Rule:** The circuit acts as a *true* integrator only for signal frequencies **$f \gg f_a$** (typically $f \ge 10f_a$). If $f < f_a$, it acts like a simple inverting amplifier.

**2. 0 dB Frequency ($f_b$):**
This is the frequency where the gain magnitude drops to 1 ($0$ dB). Assuming $f \gg f_a$, the 1 in the denominator is negligible, so:
$|A_v| \approx \frac{R_f/R_1}{\omega R_f C} = \frac{1}{\omega R_1 C} = 1 \implies \omega R_1 C = 1$
$$\boxed{f_b = \frac{1}{2\pi R_1 C}}$$

---

## 2. The Practical Differentiator

An ideal differentiator ($C$ at input, $R_f$ in feedback) is inherently unstable because its gain increases infinitely with frequency, amplifying high-frequency noise. A **practical differentiator** adds a resistor $R_1$ in series with the input capacitor $C_1$, and sometimes a capacitor $C_f$ in parallel with $R_f$.

### 2.1 Transfer Function (with just $R_1$ added)
1. Input impedance: $Z_1 = R_1 + \frac{1}{sC_1}$
2. Feedback impedance: $Z_f = R_f$
3. Gain:
   $$A_v(s) = -\frac{R_f}{R_1 + \frac{1}{sC_1}} = -\frac{s R_f C_1}{1 + s R_1 C_1}$$

Substituting $s = j\omega$:
$$A_v(j\omega) = \frac{-j\omega R_f C_1}{1 + j\omega R_1 C_1}$$

### 2.2 Important Frequencies
**1. Upper Frequency Limit of Differentiation ($f_a$):**
This is the break frequency where the component $R_1$ begins to dominate the capacitor $C_1$.
$$\boxed{f_a = \frac{1}{2\pi R_1 C_1}}$$
**Crucial Rule:** The circuit acts as a true differentiator only for signal frequencies **$f \ll f_a$** (typically $f \le 0.1f_a$). For $f > f_a$, it acts as a high-pass filter turning into a simple inverting amplifier with gain $-R_f/R_1$.

**2. 0 dB Frequency ($f_b$):**
The frequency at which the gain of the ideal differentiation portion is 1.
$$\boxed{f_b = \frac{1}{2\pi R_f C_1}}$$

---

## 3. Node-Voltage Analysis for Custom Op-Amp Circuits

Exams often present "curveball" circuits that aren't standard textbook topologies (e.g., biased clipers with potentiometers, un-labeled multi-stage amps). 
**Always fall back on Node-Voltage Analysis and Ideal Op-Amp Rules.**

### 3.1 The Golden Rules
1. **Virtual Short:** Assuming negative feedback exists and the op-amp is not saturated, the voltage difference between the input terminals is zero: $v^+ = v^-$.
2. **Infinite Input Impedance:** No current flows into or out of the $v^+$ or $v^-$ terminals: $i^+ = i^- = 0$.

### 3.2 Step-by-Step Approach for "Weird" Circuits
**Step 1:** Establish what $v^+$ is. If it's connected to ground, $v^+ = 0$. If it's connected to a voltage divider or directly to an input signal, calculate it.
**Step 2:** Apply the Virtual Short rule. Set $v^- = v^+$.
**Step 3:** Apply Kirchhoff's Current Law (KCL) at the $v^-$ node.
Sum all currents leaving the $v^-$ node and set them to 0. (Remember, current into the op-amp is 0).

**Example Application: The Custom Base Clipper**
Let's analyze the circuit from Exam Question 2 mathematically.
- $v^+$ is connected to $v_{in}$. So $v^+ = v_{in}$.
- Diode $D$ is between op-amp output ($V_{amp}$) and the circuit output ($v_o$). 
- Feedback connects $v_o$ directly to $v^-$.
- Resistor $R_2$ connects $v_o$ to $-V_{ref}$.

**Case A: Op-amp output goes positive ($v_{in} > -V_{ref}$)**
- Diode $D$ is forward biased ($ON$). It acts as a short (ideally).
- The loop is closed. Virtual short applies: $v^- = v^+ \implies v^- = v_{in}$.
- Since $v_o$ is physically wired to $v^-$, we instantly know: **$v_o = v_{in}$**.
- Does this violate KCL? Let's check the $v_o$ node: The op-amp output easily provides the current $\frac{v_{in} - (-V_{ref})}{R_2}$ requested by $R_2$. The circuit is stable.

**Case B: Op-amp output goes negative ($v_{in} < -V_{ref}$)**
- The op-amp output goes negative, so Diode $D$ becomes reverse biased ($OFF$).
- The diode acts as an open circuit. The op-amp output is physically disconnected from $v_o$.
- The feedback loop is now **broken**. Virtual short NO LONGER LIES.
- Look at the isolated $v_o$ node. It is connected ONLY to the inverting terminal $v^-$ and, through $R_2$, to $-V_{ref}$.
- Since $i^- = 0$, no current can flow through $R_2$.
- By Ohm's law, voltage drop across $R_2 = I \cdot R_2 = 0 \cdot 10k = 0V$.
- Therefore, $v_o = -V_{ref}$.

---

## 4. Log-Antilog Multiplier: Full Mathematical Expansion

To derive $V_o = K \cdot V_1 V_2$, we must rely on the precise Shockley Diode equation applied to a BJT (where $I_c \approx I_E$).

### 4.1 The Log Amplifier Formula
For a log amp with an input resistor $R$ and a BJT in the feedback loop (base grounded, collector to $v^-$, emitter to $v_{out}$):
1. Input current: $I_{in} = \frac{V_{in}}{R}$.
2. Because $i^- = 0$, all this current must flow through the BJT collector: $I_c = I_{in} = \frac{V_{in}}{R}$.
3. The BJT collector current equation is: $I_c = I_s e^{V_{be} / V_T}$
4. Here, the base is at Ground (0V), and the emitter is at $V_{out}$. So $V_{be} = 0 - V_{out} = -V_{out}$.
5. Therefore: $\frac{V_{in}}{R} = I_s e^{-V_{out} / V_T}$
6. Rearranging for $V_{out}$:
   $e^{-V_{out} / V_T} = \frac{V_{in}}{I_s R}$
   $\frac{-V_{out}}{V_T} = \ln\left(\frac{V_{in}}{I_s R}\right)$
   $$\boxed{V_{log} = -V_T \ln\left(\frac{V_{in}}{I_s R}\right)}$$

### 4.2 The Antilog Amplifier Formula
For an antilog amp, the diode/BJT is at the input, and the resistor $R_f$ is in the feedback loop.
1. Input voltage $V_{in}$ is applied to the emitter. Base is grounded. Therefore, $V_{be} = 0 - V_{in} = -V_{in}$.
2. Current through the BJT: $I_c = I_s e^{-V_{in} / V_T}$.
3. This exact current must flow through the feedback resistor $R_f$ (since $i^- = 0$).
4. The output voltage is the voltage drop across $R_f$ (since $v^- = 0V$):
   $V_{out} = 0 - I_c R_f = -I_c R_f$
5. Substituting $I_c$:
   $$\boxed{V_{antilog} = -R_f I_s e^{-V_{in} / V_T}}$$

*(By plugging the output of a summing amplifier that sums two log signals into this $V_{in}$ term, you recover the multiplied linear voltages, as demonstrated in `16_exam_solutions.md`.)*
