# 12 - Ultimate Formula Sheet 📋

## 🔴 CRITICAL: Must-Know Formulas

### IC 741 Specifications (MEMORIZE!)

| Parameter | Symbol | Value |
|-----------|--------|-------|
| Slew Rate | SR | **0.5 V/μs** |
| Gain-Bandwidth Product | GBP | **1 MHz** |
| Open-Loop Gain | $A_{OL}$ | **2 × 10⁵** |
| Input Impedance | $Z_{in}$ | **2 MΩ** |
| Output Impedance | $Z_{out}$ | **75 Ω** |
| CMRR | ρ | **90 dB** |
| Input Offset Voltage | $V_{OS}$ | 2 mV (typ), 6 mV (max) |
| Input Bias Current | $I_B$ | 80 nA (typ) |
| Output Swing | $V_o$ | ±13V (with ±15V supply) |

---

## 📊 DC Characteristics

### Input Bias & Offset Currents

$$\boxed{I_B = \frac{I_{B1} + I_{B2}}{2}}$$

$$\boxed{I_{OS} = |I_{B1} - I_{B2}|}$$

### Compensation Resistor

$$\boxed{R_{comp} = R_1 \parallel R_f = \frac{R_1 \times R_f}{R_1 + R_f}}$$

### Output Offset Voltages

**Due to VOS only:**
$$\boxed{V_{oo} = V_{OS}\left(1 + \frac{R_f}{R_1}\right)}$$

**Due to IOS only:**
$$\boxed{V_{oo} = I_{OS} \times R_f}$$

**Total (with compensation):**
$$\boxed{V_{OT} = V_{OS}\left(1 + \frac{R_f}{R_1}\right) \pm I_{OS} \cdot R_f}$$

### Thermal Drift

$$\boxed{\Delta V_{OS} = \frac{\Delta V_{OS}}{\Delta T} \times \Delta T}$$

$$\boxed{\Delta I_{OS} = \frac{\Delta I_{OS}}{\Delta T} \times \Delta T}$$

---

## 📈 AC Characteristics & Frequency

### Open-Loop Gain vs Frequency

$$\boxed{A(j\omega) = \frac{A_0}{1 + j\frac{f}{f_c}}}$$

$$\boxed{|A| = \frac{A_0}{\sqrt{1 + (f/f_c)^2}}}$$

### Gain-Bandwidth Product

$$\boxed{GBP = A_0 \times f_c = A_{CL} \times f_{CL} = \text{constant}}$$

### Closed-Loop Bandwidth

$$\boxed{f_{CL} = \frac{GBP}{A_{CL}} = \frac{f_{unity}}{|A_v|}}$$

### Phase Shift

$$\boxed{\phi = -\tan^{-1}\left(\frac{f}{f_c}\right)}$$

---

## ⚡ Slew Rate (EXAM CRITICAL!)

### Basic Definition

$$\boxed{SR = \frac{\Delta V}{\Delta t} \quad \text{or} \quad \Delta t = \frac{\Delta V}{SR}}$$

### For Sinusoidal Signals

$$\boxed{f_{max} = \frac{SR}{2\pi V_m}}$$

$$\boxed{V_m = \frac{SR}{2\pi f}}$$

### Full Power Bandwidth

$$\boxed{f_{FP} = \frac{SR}{2\pi V_{m(max)}}}$$

---

## 📊 CMRR

### Definition

$$\boxed{CMRR = \frac{A_{dm}}{A_{cm}}}$$

$$\boxed{CMRR_{dB} = 20\log_{10}(CMRR)}$$

### Output with CMRR

$$\boxed{V_o = A_{dm}\left(V_{diff} + \frac{V_{cm}}{CMRR}\right)}$$

Where:
- $V_{diff} = V_+ - V_-$
- $V_{cm} = \frac{V_+ + V_-}{2}$

---

## 🔁 Feedback Formulas

### General Feedback Gain

$$\boxed{A_f = \frac{A}{1 + A\beta}}$$

### Closed-Loop Bandwidth with Feedback

$$\boxed{BW_f = BW_{OL} \times (1 + A\beta)}$$

### Impedance with Feedback

**Series mixing → Input Z increases:**
$$\boxed{Z_{in(f)} = Z_{in}(1 + A\beta)}$$

**Voltage sampling → Output Z decreases:**
$$\boxed{Z_{out(f)} = \frac{Z_{out}}{1 + A\beta}}$$

---

## 📉 Inverting Amplifier (CRITICAL!)

$$\boxed{A_v = -\frac{R_f}{R_1}}$$

$$\boxed{Z_{in} = R_1}$$

$$\boxed{Z_{out} \approx 0}$$

**Bandwidth:**
$$\boxed{BW = \frac{GBP}{|A_v|}}$$

---

## 📈 Non-Inverting Amplifier (CRITICAL!)

$$\boxed{A_v = 1 + \frac{R_f}{R_1}}$$

$$\boxed{\beta = \frac{R_1}{R_1 + R_f}}$$

**Voltage Follower (Buffer):**
$$\boxed{A_v = 1}$$

---

## ➕ Summing Amplifier

$$\boxed{V_o = -R_f\left(\frac{V_1}{R_1} + \frac{V_2}{R_2} + \frac{V_3}{R_3} + ...\right)}$$

**Equal resistors ($R_1 = R_2 = R_3 = R$):**
$$\boxed{V_o = -\frac{R_f}{R}(V_1 + V_2 + V_3)}$$

---

## ➖ Subtractor (Difference Amplifier)

**Unity gain (equal resistors):**
$$\boxed{V_o = V_2 - V_1}$$

**General:**
$$\boxed{V_o = \frac{R_f}{R_1}(V_2 - V_1)}$$

---

## ∫ Integrator

$$\boxed{V_o = -\frac{1}{R_1 C_f}\int V_i \, dt}$$

**Transfer function:**
$$\boxed{H(j\omega) = -\frac{1}{j\omega R_1 C_f}}$$

**For constant input:**
$$\boxed{V_o = -\frac{V_i \times t}{R_1 C_f}}$$

---

## d/dt Differentiator

$$\boxed{V_o = -R_f C_1 \frac{dV_i}{dt}}$$

**Transfer function:**
$$\boxed{H(j\omega) = -j\omega R_f C_1}$$

---

## 📐 Quick Reference Tables

### Gain Comparison

| Configuration | Gain Formula | Min Gain | Phase |
|--------------|--------------|----------|-------|
| Inverting | $-R_f/R_1$ | Any | 180° |
| Non-Inverting | $1 + R_f/R_1$ | 1 | 0° |
| Voltage Follower | 1 | 1 | 0° |

### Slew Rate Quick Calculator

| $V_m$ (peak) | $f_{max}$ (SR=0.5 V/μs) |
|--------------|-------------------------|
| 1 V | 79.6 kHz |
| 2 V | 39.8 kHz |
| 5 V | 15.9 kHz |
| 10 V | 7.96 kHz |
| 12 V | 6.63 kHz |

### Common Resistor Ratios

| $R_f : R_1$ | Inverting Gain | Non-Inv Gain |
|-------------|----------------|--------------|
| 1:1 | -1 | 2 |
| 10:1 | -10 | 11 |
| 100:1 | -100 | 101 |
| 9:1 | -9 | 10 |

---

## 🔢 Constants & Conversions

### Useful Constants

| Constant | Value |
|----------|-------|
| $2\pi$ | 6.283 |
| $1/2\pi$ | 0.159 |
| ln(10) | 2.303 |

### Unit Conversions

| From | To | Multiply by |
|------|-----|-------------|
| V/μs | V/s | 10⁶ |
| MHz | Hz | 10⁶ |
| kΩ | Ω | 10³ |
| mV | V | 10⁻³ |
| nA | A | 10⁻⁹ |
| μs | s | 10⁻⁶ |

### dB Conversions

| dB | Linear Ratio |
|-----|--------------|
| 20 dB | 10 |
| 40 dB | 100 |
| 60 dB | 1000 |
| 90 dB | 31623 |

$$\boxed{dB = 20\log_{10}(\text{ratio})}$$

$$\boxed{\text{ratio} = 10^{dB/20}}$$

---

## ⚠️ Sign Conventions

| Circuit | Sign | Meaning |
|---------|------|---------|
| Inverting Amp | Negative (-) | Output inverted |
| Non-Inverting Amp | Positive (+) | Output in-phase |
| Integrator | Negative (-) | Inverts while integrating |
| Differentiator | Negative (-) | Inverts while differentiating |

---

## ✅ Pre-Exam Checklist

Before the exam, verify you can:

- [ ] Calculate inverting amp gain from Rf and R1
- [ ] Calculate non-inverting amp gain (remember the +1!)
- [ ] Convert slew rate problems in under 2 minutes
- [ ] Apply fmax = SR/(2πVm) correctly
- [ ] Calculate CMRR from Adm and Acm
- [ ] Derive integrator output equation
- [ ] Draw op-amp block diagram
- [ ] Calculate total output offset voltage
- [ ] Determine closed-loop bandwidth from GBP
- [ ] Apply compensation resistor formula

---

## 🚨 Common Exam Traps

1. **Forgetting +1 in non-inverting gain**
2. **Using peak-to-peak instead of peak in slew rate**
3. **Wrong sign in inverting amplifier**
4. **Units: V/μs vs V/s (factor of 10⁶!)**
5. **CMRR in dB vs linear**
6. **Zin of inverting amp = R1, NOT 2MΩ**

---

## 📝 Formula Summary Card (Tear-out)

```
INVERTING:          Av = -Rf/R1           Zin = R1
NON-INVERTING:      Av = 1 + Rf/R1        Zin = Very High
SLEW RATE:          fmax = SR/(2πVm)      Δt = ΔV/SR
CMRR:               CMRR = Adm/Acm        CMRRdB = 20log(CMRR)
GBP:                BW = GBP/|Av|         GBP = 1MHz (741)
BIAS:               IB = (IB1+IB2)/2      IOS = |IB1-IB2|
COMPENSATION:       Rcomp = R1||Rf
INTEGRATOR:         Vo = -(1/RC)∫Vi dt
DIFFERENTIATOR:     Vo = -RC(dVi/dt)
SUMMER:             Vo = -Rf(V1/R1 + V2/R2 + ...)
SUBTRACTOR:         Vo = V2 - V1 (unity gain)
```

---

*Good luck with your LICA exam! 🍀*
