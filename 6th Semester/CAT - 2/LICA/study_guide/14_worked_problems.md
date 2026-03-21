# 14 - Worked Problems & Practice Questions

Since the lecture slides contain numericals primarily as images (circuit diagrams with handwritten solutions), this file provides:
1. **Slide-image numericals** -- direct references to solved problems from lecture slides
2. **Custom practice problems** -- new problems with step-by-step solutions covering all exam topics

---

## Part A: Solved Problems from Lecture Slides

The following images from the lecture slides contain worked numericals. Study these carefully as they represent the type and difficulty level expected in the exam.

### Differential / Instrumentation Amplifier Numericals

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s10_img1.png]]

### Logarithmic Amplifier Derivation Steps

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s24_img3.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s24_img4.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s24_img5.png]]

### Analog Multiplier Application Examples

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s39_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-02-17_Reference-Material-I_s39_img4.png]]

### Square Wave Generator Frequency Derivation

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s18_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s18_img3.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s18_img4.png]]

### Triangular Wave Generator Calculation

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s21_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s21_img3.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-03_Reference-Material-I_s22_img1.png]]

### 555 Timer Timing Derivations

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s7_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s12_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s12_img2.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s12_img3.png]]

### 555 Astable Design Equations

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s13_img1.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-11_Reference-Material-I_s13_img2.png]]

### PLL Phase Detector Calculations

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s4_img3.png]]

![[WINSEM2025-26_ECE3001_ETH_AP2025264001037_2026-03-18_Reference-Material-I_s4_img4.png]]

---

## Part B: Custom Practice Problems with Solutions

### Problem 1: Instrumentation Amplifier Gain

**Q:** An instrumentation amplifier uses $R_1 = 20k\Omega$, $R = 2k\Omega$ (gain setting resistor), $R_2 = R_3 = 10k\Omega$. If $V_1 = 5.002V$ and $V_2 = 4.998V$, find the output voltage.

**Solution:**

$$V_d = V_1 - V_2 = 5.002 - 4.998 = 0.004V = 4mV$$

Stage 1 gain: $A_1 = 1 + \frac{2R_1}{R} = 1 + \frac{2 \times 20k}{2k} = 1 + 20 = 21$

Stage 2 gain: $A_2 = \frac{R_3}{R_2} = \frac{10k}{10k} = 1$

$$\boxed{V_o = A_1 \times A_2 \times V_d = 21 \times 1 \times 4mV = 84mV}$$

---

### Problem 2: Schmitt Trigger Thresholds

**Q:** Design a Schmitt trigger with $V_{UT} = +2V$ and $V_{LT} = -2V$ using an op-amp with $V_{sat} = \pm 13V$ and $V_{ref} = 0V$.

**Solution:**

Since $V_{ref} = 0$, the thresholds are symmetric:

$$V_{UT} = +\beta V_{sat} \quad \Rightarrow \quad 2 = \beta \times 13 \quad \Rightarrow \quad \beta = \frac{2}{13}$$

$$\beta = \frac{R_1}{R_1 + R_2} = \frac{2}{13}$$

Choose $R_1 = 2k\Omega$, then: $\frac{2k}{2k + R_2} = \frac{2}{13}$

$$2k + R_2 = 13k \quad \Rightarrow \quad \boxed{R_2 = 11k\Omega}$$

Hysteresis: $V_H = V_{UT} - V_{LT} = 2 - (-2) = 4V$ ✓

---

### Problem 3: RC Phase Shift Oscillator Design

**Q:** Design an RC phase shift oscillator to produce a 1 kHz sine wave. Given $C = 10nF$, find $R$ and $R_f$ (if $R_1 = 10k\Omega$).

**Solution:**

$$f_0 = \frac{1}{2\pi RC\sqrt{6}}$$

$$1000 = \frac{1}{2\pi \times R \times 10 \times 10^{-9} \times \sqrt{6}}$$

$$R = \frac{1}{2\pi \times 10^3 \times 10^{-8} \times 2.449} = \frac{1}{1.539 \times 10^{-4}}$$

$$\boxed{R \approx 6.5k\Omega} \quad \text{(use standard 6.8kΩ)}$$

Gain condition: $|A| = R_f/R_1 = 29$

$$\boxed{R_f = 29 \times R_1 = 29 \times 10k = 290k\Omega}$$

---

### Problem 4: Wien Bridge Oscillator Design

**Q:** Design a Wien bridge oscillator for $f_0 = 5kHz$ with $C = 0.01\mu F$. Find $R$, and the resistor ratio for the amplifier.

**Solution:**

$$f_0 = \frac{1}{2\pi RC}$$

$$R = \frac{1}{2\pi \times 5000 \times 0.01 \times 10^{-6}} = \frac{1}{3.14 \times 10^{-4}}$$

$$\boxed{R \approx 3.18k\Omega} \quad \text{(use standard 3.3kΩ)}$$

Gain condition: $A = 3$, so $1 + \frac{R_f}{R_1} = 3$, giving $\boxed{R_f = 2R_1}$

Choose $R_1 = 10k\Omega$, then $R_f = 20k\Omega$.

---

### Problem 5: 555 Monostable Pulse Width

**Q:** A 555 timer in monostable mode must produce a output pulse of exactly 5 ms. If $C = 1\mu F$, find $R$.

**Solution:**

$$T = 1.1RC$$

$$5 \times 10^{-3} = 1.1 \times R \times 1 \times 10^{-6}$$

$$\boxed{R = \frac{5 \times 10^{-3}}{1.1 \times 10^{-6}} \approx 4.55k\Omega}$$

Use standard $4.7k\Omega$ resistor. Actual pulse: $T = 1.1 \times 4.7k \times 1\mu = 5.17ms$.

---

### Problem 6: 555 Astable Design

**Q:** Design a 555 astable to produce a 2 kHz square wave with 60% duty cycle. Given $C = 0.01\mu F$.

**Solution:**

Duty cycle: $D = \frac{R_A + R_B}{R_A + 2R_B} = 0.6$

$$0.6(R_A + 2R_B) = R_A + R_B$$
$$0.6R_A + 1.2R_B = R_A + R_B$$
$$0.2R_B = 0.4R_A$$
$$\boxed{R_B = 2R_A}$$

Frequency: $f = \frac{1.44}{(R_A + 2R_B)C} = \frac{1.44}{(R_A + 4R_A) \times 0.01\mu} = \frac{1.44}{5R_A \times 10^{-8}}$

$$2000 = \frac{1.44}{5R_A \times 10^{-8}} \Rightarrow R_A = \frac{1.44}{2000 \times 5 \times 10^{-8}} = \frac{1.44}{10^{-4}} = 14,400\Omega$$

$$\boxed{R_A \approx 14.4k\Omega, \quad R_B = 2 \times 14.4k = 28.8k\Omega}$$

Use standard: $R_A = 15k\Omega$, $R_B = 27k\Omega$.

---

### Problem 7: Precision Rectifier

**Q:** A precision half-wave rectifier receives $V_i = 200mV \sin(\omega t)$. If the op-amp has an open-loop gain of $2 \times 10^5$, what is the effective diode drop?

**Solution:**

$$V_{D,eff} = \frac{V_D}{A_{OL}} = \frac{0.7}{2 \times 10^5} = 3.5\mu V$$

This is negligible. The output will be:
$$\boxed{V_o = 200mV \text{ (positive half cycle)}, \quad V_o = 0 \text{ (negative half cycle)}}$$

Even though the input (200 mV) is much smaller than the normal diode drop (700 mV), the precision rectifier handles it perfectly.

---

### Problem 8: Log Amplifier Output

**Q:** A basic log amplifier has $R = 10k\Omega$ and a transistor with $I_S = 10^{-13}A$. Find the output voltage at $T = 300K$ for $V_i = 1V$.

**Solution:**

$$V_o = -\frac{kT}{q}\ln\left(\frac{V_i}{I_S \cdot R}\right)$$

$$V_T = \frac{kT}{q} = 25.87mV \approx 26mV$$

$$V_o = -26 \times 10^{-3} \times \ln\left(\frac{1}{10^{-13} \times 10^4}\right) = -26 \times 10^{-3} \times \ln(10^9)$$

$$V_o = -26 \times 10^{-3} \times 9 \times 2.303 = -26 \times 10^{-3} \times 20.72$$

$$\boxed{V_o \approx -0.539V}$$

---

### Problem 9: Clipper Output

**Q:** An op-amp clipper has $R_f = 50k\Omega$, $R_1 = 10k\Omega$, $V_Z = 6.2V$, and signal diode $V_D = 0.7V$. For $V_i = 3V\sin(\omega t)$, find the output.

**Solution:**

Gain in linear region: $A_v = -R_f/R_1 = -50k/10k = -5$

Peak output (without clipping): $V_{o,peak} = -5 \times 3 = -15V$

Clipping level: $V_{clip} = \pm(V_Z + V_D) = \pm(6.2 + 0.7) = \pm 6.9V$

Since $|V_{o,peak}| = 15V > 6.9V$, clipping occurs.

$$\boxed{V_o = \text{Inverted sine wave, clipped at } \pm 6.9V}$$

---

### Problem 10: PLL Phase Detector Comparison

**Q:** Compare the performance of XOR and SR flip-flop phase detectors for a PLL where the input signal has a non-50% duty cycle.

**Solution:**

| Aspect | XOR Detector | SR Flip-Flop Detector |
|--------|------------|---------------------|
| Works with non-50% duty cycle? | **No** -- gives incorrect error voltage | **Yes** -- only depends on edge timing |
| Linear range | 0 to 180 degrees | 0 to 360 degrees |
| Better choice? | Not suitable for this case | **Recommended** |

The **SR flip-flop detector** is the correct choice because it compares edge timings (rising/falling edges) rather than signal overlap, making it independent of duty cycle.

$$\boxed{\text{SR flip-flop phase detector should be used.}}$$

---

## Part C: Quick Practice Questions (Try These!)

### Module 3
1. Calculate the CMRR of a differential amplifier with $A_d = 100$ and $A_{cm} = 0.01$.
2. Design an instrumentation amplifier with a gain of 101 using $R_1 = 50k\Omega$.
3. Draw the output waveform of a positive clamper for $V_i = 5V\sin(\omega t)$.
4. If a log amplifier has output $-0.3V$ at $V_i = 100mV$, what is the output at $V_i = 1V$?
5. Calculate the hysteresis of a Schmitt trigger with $R_1 = 1k\Omega$, $R_2 = 10k\Omega$, $V_{sat} = 14V$.

### Module 4
6. Find the Wien bridge oscillator frequency with $R = 15.9k\Omega$ and $C = 0.01\mu F$.
7. A square wave generator has $R_F = 20k\Omega$, $C = 0.05\mu F$, $\beta = 0.5$. Find the frequency.
8. Design a 555 monostable for a 10 ms pulse using $C = 4.7\mu F$.
9. For a 555 astable with $R_A = 6.8k\Omega$, $R_B = 3.9k\Omega$, $C = 0.1\mu F$, find $f$ and $D$.
10. What is the hysteresis of a 555 Schmitt trigger with $V_{CC} = 9V$?

### Answers (verify your work!)

1. $CMRR = 100/0.01 = 10,000$ or $80dB$
2. $101 = 1 + 2(50k)/R \Rightarrow R = 1k\Omega$
3. Output shifted up by 5V: ranges from 0V to +10V
4. $\Delta V_o = -V_T \ln(1000/100) = -26mV \times 2.303 = -59.9mV$; $V_o = -0.3 + (-0.06) \approx -0.36V$
5. $V_H = 2 \times 14 \times 1k/(1k+10k) = 28/11 \approx 2.55V$
6. $f = 1/(2\pi \times 15.9k \times 0.01\mu) = 1 kHz$
7. $T = 2 \times 20k \times 0.05\mu \times \ln(3) = 2.198ms$; $f \approx 455Hz$
8. $R = T/(1.1C) = 10ms/(1.1 \times 4.7\mu) = 1.94k\Omega$ (use $2k\Omega$)
9. $f = 1.44/((6.8k+2\times3.9k)\times 0.1\mu) = 1.44/1.46\times10^{-3} = 986Hz$; $D = (6.8+3.9)/(6.8+7.8) = 73.3\%$
10. $V_H = V_{CC}/3 = 9/3 = 3V$
