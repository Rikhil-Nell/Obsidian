# 02 - Op-Amp Basics & IC 741 🔧

## What is an Op-Amp?

An **Operational Amplifier (Op-Amp)** is a high-gain, direct-coupled amplifier that can amplify both AC and DC signals.

> **Analogy:** Think of an op-amp like a **super-powered megaphone**. You whisper into it (small input signal), and it shouts loudly (large output signal). The "operational" part comes from being able to perform mathematical operations like addition, subtraction, integration, and differentiation on signals.

---

## Op-Amp Symbol

```
         +VCC (Positive Supply)
            │
            │
    ───────┬┴┬───────
           │ │
  V1 ─────►│-│         
           │ │◄─────── Vout = A(V2 - V1)
  V2 ─────►│+│
           │ │
    ───────┴┬┴───────
            │
            │
         -VEE (Negative Supply)
```

| Terminal | Name | Function |
|----------|------|----------|
| **+** | Non-Inverting Input | Signal here appears in-phase at output |
| **-** | Inverting Input | Signal here appears inverted at output |
| **Vout** | Output | Amplified output = A(V+ - V-) |
| **+VCC** | Positive Supply | Typically +15V |
| **-VEE** | Negative Supply | Typically -15V |

---

## IC 741 - The Classic Op-Amp

The **IC 741** is the most widely used general-purpose operational amplifier.

### Pin Configuration (8-pin DIP)

```
        ┌─────────────┐
  O.N. ─┤ 1         8 ├─ NC
   V-  ─┤ 2   741   7 ├─ +VCC
   V+  ─┤ 3         6 ├─ Vout
 -VEE  ─┤ 4         5 ├─ O.N.
        └─────────────┘
```

| Pin | Name | Description |
|:---:|------|-------------|
| 1 | Offset Null | Used to nullify input offset voltage |
| 2 | Inverting Input (V-) | Input for inverting signal |
| 3 | Non-Inverting Input (V+) | Input for non-inverting signal |
| 4 | V- (Negative Supply) | Connect to -VEE (typically -15V) |
| 5 | Offset Null | Used with Pin 1 for offset adjustment |
| 6 | Output | Output of the op-amp |
| 7 | V+ (Positive Supply) | Connect to +VCC (typically +15V) |
| 8 | NC | No Connection |

> **Memory Trick:** "**2-3-6**" = Input(-), Input(+), Output

---

## IC 741 Key Specifications

| Parameter | Symbol | IC 741 Value | Unit |
|-----------|--------|--------------|------|
| **Supply Voltage** | VCC/VEE | ±5V to ±18V | V |
| **Open-Loop Gain** | AOL | 2 × 10⁵ (200,000) | - |
| **Input Impedance** | Zin | 2 MΩ | Ω |
| **Output Impedance** | Zout | 75 Ω | Ω |
| **Input Offset Voltage** | VOS | 2 mV (typ), 6 mV (max) | mV |
| **Input Bias Current** | IB | 80 nA (typ), 500 nA (max) | nA |
| **Input Offset Current** | IOS | 20 nA | nA |
| **Gain-Bandwidth Product** | GBP | 1 MHz | Hz |
| **Slew Rate** | SR | 0.5 V/μs | V/μs |
| **CMRR** | ρ | 90 dB | dB |
| **Output Voltage Swing** | Vo(max) | ±13V (with ±15V supply) | V |

> **CRITICAL SPEC TO MEMORIZE:**
> $$\boxed{SR = 0.5 \text{ V/μs}, \quad GBP = 1 \text{ MHz}, \quad Z_{in} = 2 \text{ MΩ}, \quad Z_{out} = 75 \text{ Ω}}$$

---

## Understanding Each Parameter

### 1. Supply Voltage
- **Dual Supply:** ±5V to ±18V (most common: ±15V)
- **Single Supply:** 10V to 36V
- Allows handling of positive AND negative signals

### 2. Open-Loop Voltage Gain (AOL)
- The amplification when NO feedback is applied
- IC741: **200,000** (very high!)
- $V_{out} = A_{OL} \times (V_+ - V_-)$

### 3. Input Offset Voltage (VOS)
- Small voltage difference at inputs to make output = 0
- Caused by internal transistor mismatches
- **Needs compensation** for precision applications

### 4. Input Bias Current (IB)
- Average DC current at input terminals: $I_B = \frac{I_{B1} + I_{B2}}{2}$
- Due to internal transistor base currents
- IC741: 80 nA typical

### 5. Gain-Bandwidth Product (GBP)
- $\text{Gain} \times \text{Bandwidth} = \text{constant}$
- IC741: 1 MHz
- **Trade-off:** Higher gain = Lower bandwidth

### 6. Slew Rate (SR)
- Maximum rate of output voltage change
- SR = 0.5 V/μs means output can only change by 0.5V per microsecond
- **Limits high-frequency performance**

### 7. Input Impedance (Zin)
- Resistance seen at input terminals
- High Zin = Minimal loading on source (good!)
- IC741: **2 MΩ**

### 8. Output Impedance (Zout)
- Resistance seen at output
- Low Zout = Can drive loads effectively
- IC741: **75 Ω**

### 9. Output Voltage Swing
- Maximum range of output voltage
- With ±15V supply: approximately ±13V
- Cannot reach rail voltages due to internal drops

---

## Ideal vs Practical Op-Amp

| Parameter | Ideal | IC 741 Practical |
|-----------|-------|------------------|
| Open-Loop Gain (AOL) | **∞** | 2 × 10⁵ |
| Input Impedance (Zin) | **∞** | 2 MΩ |
| Output Impedance (Zout) | **0** | 75 Ω |
| Bandwidth | **∞** | 1 MHz |
| Input Offset Voltage | **0** | 2 mV |
| Input Bias Current | **0** | 80 nA |
| Input Offset Current | **0** | 20 nA |
| CMRR | **∞** | 90 dB |
| Slew Rate | **∞** | 0.5 V/μs |

> **Key Insight:** The ideal op-amp is a theoretical model used for analysis. Real op-amps (like 741) have finite values that affect circuit performance.

---

## Transfer Characteristics (Voltage Transfer Curve)

The **transfer characteristic** shows the relationship between input voltage and output voltage.

### Open-Loop Transfer Characteristic

```
    Vout
      │
+Vsat ├────────────────────────┐
      │                        │
      │                        │
      │             ╱──────────┘
    0 ├────────────╳──────────────► Vdiff
      │           ╱
      │ ─────────╱
      │
-Vsat ├────────────────────────
      │
```

| Region | Condition | Output |
|--------|-----------|--------|
| **Positive Saturation** | $V_{diff} > +V_{sat}/A_{OL}$ | $+V_{sat}$ (≈ +13V) |
| **Linear Region** | $-V_{sat}/A_{OL} < V_{diff} < +V_{sat}/A_{OL}$ | $V_o = A_{OL} \times V_{diff}$ |
| **Negative Saturation** | $V_{diff} < -V_{sat}/A_{OL}$ | $-V_{sat}$ (≈ -13V) |

> **Key Point:** Linear region is extremely narrow (~130 μV for 741) due to very high gain!

### Linear Region Width

$$\boxed{\Delta V_{diff(linear)} = \frac{2 V_{sat}}{A_{OL}} = \frac{2 \times 13V}{200000} \approx 130 \text{ μV}}$$

### Closed-Loop Transfer Characteristic

With negative feedback, the linear region is greatly extended:

```
    Vout
      │
+Vsat ├──────────────────────┐
      │                      │
      │                 ╱────┘
      │            ╱────
      │       ╱────
    0 ├──────╳────────────────► Vin
      │ ╱────
      │╱────
      ├────────────────────
-Vsat 
      │
```

**For Inverting Amplifier:** $V_o = -\frac{R_f}{R_1} \times V_{in}$ (slope = gain)

**For Non-Inverting Amplifier:** $V_o = (1 + \frac{R_f}{R_1}) \times V_{in}$

### Key Differences: Open vs Closed Loop

| Property | Open Loop | Closed Loop |
|----------|-----------|-------------|
| **Linear Range** | ~130 μV | Several volts |
| **Gain** | 200,000 | Determined by Rf/R1 |
| **Predictability** | Very sensitive | Stable & predictable |
| **Practical Use** | Comparators only | Amplifiers |

---

## Op-Amp Internal Block Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                                                             │
│  V1 ──►┌────────────┐   ┌──────────────┐   ┌─────────────┐  │
│        │Differential│   │ Intermediate │   │   Output    │──►Vout
│  V2 ──►│  Amplifier │──►│    Stages    │──►│   Stage     │  │
│        │  (Input)   │   │   (Gain)     │   │ (Push-Pull) │  │
│        └────────────┘   └──────────────┘   └─────────────┘  │
│              │                  │                │          │
│              └──────────────────┴────────────────┘          │
│                         Level Shifter                       │
└─────────────────────────────────────────────────────────────┘
```

### Stages:
1. **Input Stage (Differential Amplifier)**
   - High input impedance
   - Amplifies difference between V1 and V2
   - Rejects common-mode signals

2. **Intermediate Stage**
   - Provides additional voltage gain
   - Level shifting to restore DC levels

3. **Output Stage (Push-Pull)**
   - Low output impedance
   - High current drive capability
   - Class AB operation to reduce crossover distortion

---

## IC 741 Internal Components

| Component | Purpose |
|-----------|---------|
| **Transistors (BJTs)** | Amplification and switching (differential pairs, current mirrors) |
| **Resistors** | Current control, gain setting, biasing |
| **Capacitors** | Frequency compensation, stability |
| **Diodes** | Overvoltage protection, linearity |
| **Current Mirrors** | Constant current biasing |

---

## Key Formulas

### Output Voltage (Open-Loop)
$$\boxed{V_{out} = A_{OL} \times (V_+ - V_-) = A_{OL} \times V_{diff}}$$

### Input Bias Current
$$\boxed{I_B = \frac{I_{B1} + I_{B2}}{2}}$$

### Gain-Bandwidth Product
$$\boxed{f_{unity} = A_{OL} \times f_{corner} = \text{constant} = 1 \text{ MHz (for 741)}}$$

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Using single supply without level shifting | For signals around 0V, need dual supply OR virtual ground circuit |
| Ignoring output swing limits | Output can't reach ±VCC, typically limited to ±(VCC - 2V) |
| Expecting rail-to-rail | IC 741 is NOT rail-to-rail; modern op-amps like LM358 are better |
| Forgetting offset null pins | Pins 1 & 5 can be used with potentiometer to zero out offset |

---

## Practice Problem

**Q:** An IC 741 is powered with ±15V supply. What is the maximum output voltage swing?

**A:** Output swing ≈ ±13V (about 2V less than supply rails)

The output cannot reach the supply voltages due to internal transistor saturation voltages.

---

*Next: [03_dc_characteristics.md](03_dc_characteristics.md) - DC Characteristics →*
