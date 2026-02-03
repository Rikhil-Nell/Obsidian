# 11 - Internal Circuit of Op-Amp 🔬

## Overview

An op-amp is a **multistage, direct-coupled, high-gain amplifier**. Understanding its internal structure helps explain its DC and AC characteristics.

---

## Op-Amp Block Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│  V+ ──►┌─────────────┐   ┌──────────────┐   ┌──────────────┐    │
│        │ Differential│   │   Level      │   │   Output     │──►Vo
│  V- ──►│  Amplifier  ├──►│   Shifter    ├──►│   Stage      │    │
│        │(Input Stage)│   │ (DC adjust)  │   │ (Push-Pull)  │    │
│        └─────────────┘   └──────────────┘   └──────────────┘    │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
         Stage 1              Stage 2           Stage 3
      High Impedance       DC Level Control    High Current Drive
```

---

## Stage 1: Differential Amplifier (Input Stage)

### Purpose
- Provides **high input impedance**
- Amplifies **difference** between V+ and V-
- Rejects **common-mode** signals

### Circuit
```
           RC1            RC2
            ┌──┐          ┌──┐
  +VCC ─────┴──┴──┬───┬───┴──┴───── +VCC
                  │   │
               VO1│   │VO2
                  │   │
              ┌───┴───┴───┐
        V1 ──►│ Q1     Q2 │◄── V2
              └─────┬─────┘
                    │
                   RE
                    │
                  ─VEE
```

### Key Characteristics

| Property | Effect |
|----------|--------|
| **Two identical transistors** | Q1 and Q2 matched for balance |
| **Common emitter resistor RE** | Provides bias and tail current |
| **Collector resistors RC1, RC2** | Load resistors (often replaced by current mirrors) |

### Modes of Operation

1. **Differential Mode:** V1 ≠ V2 → Signals are amplified
2. **Common Mode:** V1 = V2 → Signals cancel (ideally no output)

---

## Single-Ended Differential Input

When one input is grounded:

```
V signal ──►(Gate of Q1)
                │
         Emitter sees V/2
                │
    VO1 = inverted signal
    VO2 = non-inverted signal
```

---

## Cascaded Differential Stages

For higher gain, multiple differential stages are cascaded:

```
┌───────────────┐     ┌───────────────┐
│ Differential  │────►│ Differential  │────► To Level Shifter
│   Stage 1     │     │   Stage 2     │
└───────────────┘     └───────────────┘
   High Zin              More Gain
```

**Benefits:**
- Higher overall gain
- Better CMRR
- Improved linearity

---

## Stage 2: Level Shifter

### The Problem

Direct coupling between stages causes **DC level buildup**:
- Each stage adds to the quiescent DC voltage
- Output may be biased far from zero
- Limits output swing and causes distortion

### The Solution

Level translator circuits restore the DC level to near zero volts.

### Basic Level Shifter Circuit

```
           +VCC
             │
             │
          ┌──┴──┐
     Vi ──┤  Q  ├── Emitter Follower
          └──┬──┘
             │
      R1 ────┤
             │
      R2 ────┼──► Vo (shifted down)
             │
           ─VEE
```

### Level Shift Amount

$$\boxed{V_{shift} = V_{BE} + I_E \times R}$$

### Improved Level Shifters

- **Diode-based biasing:** Better temperature stability
- **Current mirror biasing:** Precise current control
- **VBE multiplier:** Adjustable DC level

---

## Stage 3: Output Stage

### Purpose
- **High current drive** capability
- **Low output impedance**
- Can drive external loads (speakers, motors, etc.)

### Push-Pull Configuration

```
           +VCC
             │
          ┌──┴──┐
          │  Q1 │ NPN (sources current)
     Vi ──┤     ├──┬──► Vo
          │  Q2 │  │
          └──┬──┘  │
             │    RL (Load)
           ─VEE    │
                  GND
```

### How It Works

| Input Vi | Q1 (NPN) | Q2 (PNP) | Result |
|----------|----------|----------|--------|
| Positive | ON | OFF | Sources current to load |
| Negative | OFF | ON | Sinks current from load |
| Zero | Both OFF | Both OFF | Crossover region |

### Crossover Distortion

**Problem:** When Vi is near zero, neither transistor is fully ON, causing a "dead zone."

```
Output:
     /
    / ← Dead zone (crossover distortion)
   /
──/──
 /
```

### Solution: Class AB Biasing

Add bias voltage between bases so a small quiescent current flows:

$$V_{bias} \approx 2V_{BE} \approx 1.2V \text{ to } 1.4V$$

---

## IC 741 Output Stage

```
                +VCC
                  │
               ┌──┴──┐
        Bias ──┤     ├── Q1 (output NPN)
       (VBE    │     │
    Multiplier)│     │
               │     ├──────► Vo
               │     │
         25Ω ──┤     ├── Q2 (output PNP)
               └──┬──┘
                  │
                -VEE
```

Key features:
- **VBE multiplier** provides adjustable bias
- **25Ω resistors** stabilize quiescent base current
- **Class AB operation** eliminates crossover distortion

---

## Complete Internal Block Diagram (Detailed)

```
     ┌───────────────────────────────────────────────────────┐
     │                      Op-Amp IC                        │
     │                                                       │
 V+ ─┼─►┌─────────┐   ┌────────┐   ┌──────┐   ┌─────────┐    │
     │  │  Diff   │   │ Diff   │   │Level │   │ Class AB│──► │──► Vo
 V- ─┼─►│  Amp    ├──►│ Amp    ├──►│Shift ├──►│ Output  │    │
     │  │ Stage 1 │   │ Stage 2│   │      │   │  Stage  │    │
     │  └─────────┘   └────────┘   └──────┘   └─────────┘    │
     │       ↑             ↑           ↑           ↑         │
     │   Current       Current    Constant    VBE            │
     │   Mirrors       Mirrors    Current     Multiplier     │
     │                           Source                      │
     └───────────────────────────────────────────────────────┘
```

---

## Summary: Function of Each Stage

| Stage | Function | Key Property |
|-------|----------|--------------|
| **Differential Amplifier** | Amplify difference, reject common mode | High Zin, High CMRR |
| **Intermediate Stages** | Additional gain | Voltage amplification |
| **Level Shifter** | Restore DC to ~0V | Prevents saturation |
| **Output Stage** | Drive loads, source/sink current | Low Zout, High current |

---

## Key Components Inside Op-Amp

| Component | Purpose |
|-----------|---------|
| **BJTs (many)** | Amplification, current mirrors, active loads |
| **Resistors** | Biasing, gain setting |
| **Capacitors** | Frequency compensation |
| **Diodes** | Protection, level shifting |
| **Current Mirrors** | Constant current biasing |

---

## Important Equations

### Differential Gain (Single Stage)
$$A_d = g_m \times R_C$$

### Level Shift
$$V_{shift} = V_{BE} + I_E \times R$$

### Output Stage Current
$$I_{out} = \frac{V_o}{R_L}$$

---

## Common Exam Questions

1. **Draw and explain the block diagram of an op-amp**
2. **Explain the function of the differential amplifier**
3. **Why is a level shifter needed?**
4. **Explain crossover distortion and how to eliminate it**

---

*Next: [12_formula_sheet_ultimate.md](./12_formula_sheet_ultimate.md) - The Ultimate Formula Sheet! →*
