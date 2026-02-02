# 06 - Open-Loop Op-Amp Configurations 🔓

## What is Open-Loop Operation?

In **open-loop** mode, **no feedback** is applied from output to input. The op-amp operates at its maximum (open-loop) gain.

> **Analogy:** It's like driving a car at full throttle with no brakes. Small input → HUGE output (often clipped to supply rails).

---

## The Three Open-Loop Configurations

```
┌─────────────────────────────────────────────────────────────┐
│                  OPEN-LOOP CONFIGURATIONS                   │
├───────────────────┬───────────────────┬────────────────────┤
│   Differential    │    Inverting      │   Non-Inverting    │
│   (V1 ≠ 0, V2≠0)  │    (V1 = 0)       │   (V2 = 0)         │
└───────────────────┴───────────────────┴────────────────────┘
```

---

## 1️⃣ Open-Loop Differential Amplifier

**Configuration:** Both V1 and V2 are finite (non-zero)

```
V1 ──►(-)
          ├──► Vout = AOL × (V2 - V1)
V2 ──►(+)
```

### Output Equation

$$\boxed{V_{out} = A_{OL} \times (V_+ - V_-) = A_{OL} \times V_{diff}}$$

Where:
- $A_{OL}$ = Open-loop gain (≈ 200,000 for IC 741)
- $V_{diff}$ = Differential input voltage

### Key Points
- Source resistances negligible compared to high input impedance
- Even tiny $V_{diff}$ causes output saturation
- Output swings to ±Vsat (near supply rails)

---

## 2️⃣ Open-Loop Inverting Amplifier

**Configuration:** V2 = 0 (non-inverting input grounded)

```
V1 ──►(-)
          ├──► Vout = -AOL × V1
GND ──►(+)
```

### Output Equation

$$\boxed{V_{out} = -A_{OL} \times V_1}$$

### Behavior
- Input at inverting terminal only
- Output is inverted (180° phase shift)
- Very small positive V1 → Output saturates negative
- Very small negative V1 → Output saturates positive

---

## 3️⃣ Open-Loop Non-Inverting Amplifier

**Configuration:** V1 = 0 (inverting input grounded)

```
GND ──►(-)
          ├──► Vout = +AOL × V2
V2 ──►(+)
```

### Output Equation

$$\boxed{V_{out} = +A_{OL} \times V_2}$$

### Behavior
- Input at non-inverting terminal only
- Output is in-phase (0° phase shift)
- Positive V2 → Output saturates positive
- Negative V2 → Output saturates negative

---

## Limitations of Open-Loop Operation

| Limitation | Description |
|------------|-------------|
| **Output Clipping** | Output clips at ±Vsat when it exceeds op-amp limits |
| **Noise Sensitivity** | Extremely high gain amplifies all noise |
| **Unstable Gain** | Gain varies with temperature and supply voltage |
| **Very Low Bandwidth** | Usable only at very low frequencies |
| **Not for Linear Amplification** | Due to above issues |

---

## Where Is Open-Loop Actually Used?

Since open-loop is impractical for linear amplification, it's used for:

| Application | Why Open-Loop Works |
|-------------|---------------------|
| **Comparators** | We WANT the output to swing rail-to-rail based on input comparison |
| **Square-wave Generators** | Rapid switching between ±Vsat |
| **Astable Multivibrators** | Oscillator circuits |
| **Schmitt Triggers** | Hysteresis-based switching |

---

## Comparator Example

```
            +Vsat 
              │ Output high when V2 > V1
V1 (ref)──►(-) │
              ├─┴─── Vout = ±Vsat
V2 (sig)──►(+) │
              │ Output low when V2 < V1
            -Vsat
```

The output is **always** at one of the two saturation levels:
- If $V_2 > V_1$: $V_{out} = +V_{sat}$
- If $V_2 < V_1$: $V_{out} = -V_{sat}$

---

## Summary Table

| Configuration | V1 | V2 | Output | Application |
|---------------|----|----|--------|-------------|
| Differential | ≠0 | ≠0 | $A_{OL}(V_2-V_1)$ | Comparator |
| Inverting | signal | 0 | $-A_{OL} \cdot V_1$ | Inverting comparator |
| Non-Inverting | 0 | signal | $+A_{OL} \cdot V_2$ | Non-inverting comparator |

---

## Key Formulas

$$\boxed{V_{out} = A_{OL} \times (V_+ - V_-)}$$

$$\boxed{V_{sat} \approx \pm(V_{CC} - 1.5V) \text{ to } \pm(V_{CC} - 2V)}$$

For IC 741 with ±15V supply: $V_{sat} \approx \pm13V$ to $\pm14V$

---

## Common Mistakes ⚠️

| Mistake | Correct Understanding |
|---------|----------------------|
| Expecting linear amplification | Output always clips in open-loop |
| Forgetting the sign | Inverting = negative gain, Non-inverting = positive |
| Using open-loop for audio | Use closed-loop for linear applications |
| Ignoring Vsat limits | Output cannot exceed supply voltage |

---

*Next: [07_closed_loop_feedback.md](./07_closed_loop_feedback.md) - Closed-Loop & Feedback Types →*
