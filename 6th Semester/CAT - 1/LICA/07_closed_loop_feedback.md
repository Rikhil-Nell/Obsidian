# 07 - Closed-Loop & Feedback Types 🔁

## What is Closed-Loop Operation?

In **closed-loop** mode, a portion of the output is **fed back** to the input. This creates a feedback loop that controls the amplifier behavior.

> **Analogy:** It's like cruise control in a car. The system constantly compares your speed to the desired speed and adjusts accordingly.

---

## Why Use Feedback?

| Benefit | How It Works |
|---------|--------------|
| **Stable Gain** | Gain depends on resistors, not op-amp parameters |
| **Higher Bandwidth** | Trading gain for bandwidth |
| **Lower Distortion** | Feedback corrects non-linearities |
| **Predictable Behavior** | Less sensitive to temperature and component variations |

---

## Types of Feedback

### Negative vs Positive Feedback

| Type | Phase Relationship | Effect |
|------|-------------------|--------|
| **Negative** | Output fed 180° out-of-phase | Stabilizes, linear operation |
| **Positive** | Output fed in-phase | Oscillation, regeneration |

> **For amplifiers, we use NEGATIVE feedback!**

---

## The Four Basic Feedback Configurations

Feedback is classified by:
1. **What is sampled** at output: Voltage or Current
2. **How it's mixed** at input: Series or Shunt (Parallel)

```
┌─────────────────────────────────────────────────────────────┐
│                    FEEDBACK TYPES                           │
├──────────────────────────┬──────────────────────────────────┤
│     SERIES MIXING        │        SHUNT MIXING              │
│  (Series with input)     │    (Parallel with input)         │
├──────────────────────────┼──────────────────────────────────┤
│ VOLTAGE  │ Voltage-Series│   Voltage-Shunt                  │
│ SAMPLING │ (Series-Shunt)│   (Shunt-Shunt)                  │
├──────────┼───────────────┼──────────────────────────────────┤
│ CURRENT  │ Current-Series│   Current-Shunt                  │
│ SAMPLING │ (Series-Series)│  (Shunt-Series)                 │
└──────────┴───────────────┴──────────────────────────────────┘
```

---

## 1️⃣ Voltage-Series Feedback (Series-Shunt)

**Also called:** Non-Inverting Amplifier Configuration

### Circuit Concept
```
Vi ──►(+)
          ├──────────► Vo
      (-)─┤
          │
     ┌────┴────┐
     │   βVo   │ ◄── Feedback voltage
     └─────────┘
```

### Characteristics

| Property | Effect |
|----------|--------|
| Sample | Output **VOLTAGE** |
| Mix | In **SERIES** with input |
| Input Impedance | **INCREASES** (good for high-Z sources) |
| Output Impedance | **DECREASES** (can drive loads well) |

### Gain Formula

$$\boxed{A_f = \frac{V_o}{V_i} = \frac{A}{1 + A\beta}}$$

Where:
- $A$ = Open-loop gain
- $\beta$ = Feedback factor = $\frac{R_1}{R_1 + R_f}$
- $A\beta$ = Loop gain

For high loop gain ($A\beta >> 1$):
$$\boxed{A_f \approx \frac{1}{\beta} = 1 + \frac{R_f}{R_1}}$$

### Applications
- Voltage amplifiers
- Audio preamplifiers
- Instrumentation amplifiers

---

## 2️⃣ Voltage-Shunt Feedback (Shunt-Shunt)

**Also called:** Inverting Amplifier Configuration

### Circuit Concept
```
           Rf
    ┌────/\/\/────┐
    │             │
Vi ─┤─/\/\/─┬─────┴──► Vo
    │  R1   │
    │      (-)
    │       ├───
    └──►(+)─┘
        │
       GND
```

### Characteristics

| Property | Effect |
|----------|--------|
| Sample | Output **VOLTAGE** |
| Mix | In **SHUNT** (parallel) with input |
| Input Impedance | **DECREASES** (suits low-Z sources) |
| Output Impedance | **DECREASES** |

### Gain Formula

$$\boxed{A_f = \frac{V_o}{V_i} = -\frac{R_f}{R_1}}$$

(Negative sign indicates phase inversion)

### Applications
- Inverting voltage amplifiers
- Current-to-voltage converters

---

## 3️⃣ Current-Series Feedback (Series-Series)

### Characteristics

| Property | Effect |
|----------|--------|
| Sample | Output **CURRENT** |
| Mix | In **SERIES** with input |
| Input Impedance | **INCREASES** |
| Output Impedance | **INCREASES** (current source behavior) |

### Key Points
- Converts voltage input to current output
- Acts as voltage-controlled current source (transconductance)
- Excellent current stability when loop gain is high
- Used in emitter-degeneration BJT amplifiers

### Gain (Transconductance)

$$\boxed{G_m = \frac{I_o}{V_i} = \frac{g_m}{1 + g_m R_E}}$$

### Applications
- Voltage-controlled current sources
- Industrial instrumentation
- Analog signal conditioning

---

## 4️⃣ Current-Shunt Feedback (Shunt-Series)

### Characteristics

| Property | Effect |
|----------|--------|
| Sample | Output **CURRENT** |
| Mix | In **SHUNT** with input |
| Input Impedance | **DECREASES** |
| Output Impedance | **INCREASES** |

### Key Points
- Amplifies current directly
- Input is current, output is current
- Acts as current amplifier
- Gain becomes independent of transistor parameters

### Gain (Current Gain)

$$\boxed{A_i = \frac{I_o}{I_i}}$$

### Applications
- Current amplifiers
- Photodiode current amplification
- Current mirrors
- Low-impedance current sensing

---

## Summary: Effects of Feedback

| Feedback Type | Input Z | Output Z | Amplifier Type |
|---------------|---------|----------|----------------|
| Voltage-Series | ↑ High | ↓ Low | Voltage Amp |
| Voltage-Shunt | ↓ Low | ↓ Low | Transresistance |
| Current-Series | ↑ High | ↑ High | Transconductance |
| Current-Shunt | ↓ Low | ↑ High | Current Amp |

---

## Key Formulas Summary

### General Feedback Equation
$$\boxed{A_f = \frac{A}{1 + A\beta}}$$

### Closed-Loop Bandwidth
$$\boxed{BW_f = (1 + A\beta) \times BW_{OL}}$$

Bandwidth INCREASES with feedback!

### Input Impedance with Feedback (Series Mixing)
$$\boxed{Z_{in(f)} = Z_{in}(1 + A\beta)}$$

### Output Impedance with Feedback (Voltage Sampling)
$$\boxed{Z_{out(f)} = \frac{Z_{out}}{1 + A\beta}}$$

---

## Virtual Ground Concept

In **inverting configurations** with negative feedback:
- Non-inverting input is grounded
- Negative feedback forces inverting input to same voltage (0V)
- The inverting node is at "virtual ground"

$$\boxed{V_- = V_+ = 0 \text{ (Virtual Ground)}}$$

> **Important:** Virtual ground is a VOLTAGE reference, not a current sink!

```
                Rf
         ┌────/\/\/────┐
         │             │
 Vi ────/\/\/──┬───────┴──► Vo
         R1    │
              (●) ◄── Virtual Ground (≈0V)
               │
            (+)│(-) 
               ├─── Op-Amp
              GND
```

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Confusing series vs shunt | Series = add voltages; Shunt = add currents |
| Using wrong impedance effect | Series mixing ↑ Zin; Shunt mixing ↓ Zin |
| Forgetting virtual ground | Only valid with negative feedback AND ideal op-amp |
| Ignoring feedback factor β | β determines closed-loop gain |

---

*Next: [08_inverting_amplifier.md](08_inverting_amplifier.md) - Inverting Amplifier (Detailed Analysis) →*
