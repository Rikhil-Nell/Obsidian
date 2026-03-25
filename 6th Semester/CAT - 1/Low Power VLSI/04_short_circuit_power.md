# Short-Circuit Power Dissipation

## Learning Objectives
After this section, you will understand:
- Mechanism of short-circuit current in CMOS
- Dependence on input rise/fall times
- Effect of load capacitance on short-circuit power
- Techniques to minimize short-circuit power

---

## What is Short-Circuit Power?

When input signals have **finite rise and fall times**, both PMOS and NMOS transistors can be ON simultaneously for a brief period, creating a direct current path from VDD to ground.

![[short_circuit_current.png]]

**Key Insight:** This occurs when input voltage is in the range $V_{Tn} < V_{in} < V_{DD} - |V_{Tp}|$

---

## Short-Circuit Window

During a rising input transition:

```
Vin   VTn         VDD-|VTp|    VDD
  │    │              │         │
  ├────┼──────────────┼─────────┤
  │    │   BOTH ON    │         │
  │    │  (short-ckt) │         │
  │    │              │         │
  0    ├──────────────┤         
       │  SC Window   │
```

| Transistor | Turn ON | Turn OFF |
|------------|---------|----------|
| NMOS | $V_{in} > V_{Tn}$ | - |
| PMOS | $V_{in} < V_{DD} - \|V_{Tp}\|$ | - |
| Both ON | $V_{Tn} < V_{in} < V_{DD} - |V_{Tp}|$ | - |

---

## Short-Circuit Current Analysis

For a symmetric inverter with:
- $k = k_n = k_p$
- $V_T = V_{Tn} = |V_{Tp}|$
- Rise time = Fall time = $\tau$

The time-averaged short-circuit current is:

$$I_{sc,avg} = \frac{k \cdot \tau}{12} (V_{DD} - 2V_T)^3$$

### Short-Circuit Power

$$\boxed{P_{sc} = I_{sc,avg} \cdot V_{DD} \propto k \cdot \tau \cdot (V_{DD} - 2V_T)^3}$$

| Factor | Effect on $P_{sc}$ |
|--------|-------------------|
| Input rise/fall time ($\tau$) | **Linear** - longer transitions → more power |
| Transconductance ($k$) | **Linear** - larger transistors → more power |
| $V_{DD} - 2V_T$ | **Cubic** - margin above threshold matters |

---

## Effect of Input Transition Time

Short-circuit current increases with slower input transitions:

| Input Speed | Short-Circuit Current | Reason |
|-------------|----------------------|--------|
| Fast transitions | Small | Brief overlap window |
| Slow transitions | Large | Extended overlap window |

![[short_circuit_waveform.png]]

**Design Guideline:** Keep input rise/fall times short to minimize short-circuit power.

---

## Effect of Load Capacitance

When load capacitance is **large**:
- Output voltage changes slowly
- During input transition, output is essentially constant
- PMOS (during rising input) or NMOS (during falling input) sees ~0V across drain-source
- Short-circuit current is minimized

When load capacitance is **small**:
- Output changes quickly with input
- Short-circuit current flows more easily
- Maximum short-circuit power

![[cmos_inverter_circuit.png]]

$$I_{sc} \downarrow \text{ as } C_L \uparrow$$

**Paradox:** Large load capacitance reduces short-circuit power but increases switching power!

---

## Voltage Dependence

Short-circuit power has a **cubic dependence** on $(V_{DD} - 2V_T)$:

$$P_{sc} \propto (V_{DD} - 2V_T)^3$$

This is stronger than the quadratic dependence of switching power on $V_{DD}$.

**Implication:** Voltage scaling reduces short-circuit power more effectively than switching power in some cases. However, as $V_{DD}$ approaches $2V_T$, short-circuit current approaches zero naturally.

---

## Minimizing Short-Circuit Power

### Design Strategies

1. **Minimize input transition times**
   - Use buffers to sharpen edges
   - Proper gate sizing for fan-out

2. **Match input and output transition times**
   - Avoid extremely fast inputs driving slow outputs

3. **Increase threshold voltages** (trade-off with speed)
   - Reduces the overlap window
   - Must balance with other requirements

4. **Reduce transistor transconductance**
   - Smaller $k$ means smaller $I_{sc}$
   - Trade-off with drive strength

### Circuit Techniques

- Use proper skewing of inverters
- Balance NMOS/PMOS sizes for symmetric transitions
- Avoid excessive output load without buffering

---

## Comparison: Switching vs Short-Circuit Power

| Aspect | Switching Power | Short-Circuit Power |
|--------|-----------------|---------------------|
| Cause | Capacitor charging | Direct VDD-GND path |
| $V_{DD}$ dependence | Quadratic ($V_{DD}^2$) | Cubic ($(V_{DD}-2V_T)^3$) |
| Rise/fall time effect | None | Linear |
| Load capacitance effect | Increases power | Decreases power |
| Typical contribution | 30-50% | ~10% |

---

## Common Mistakes

1. **Ignoring short-circuit power in buffer design** - Cascaded buffers can accumulate SC power
2. **Assuming zero rise/fall time** - Real signals always have finite slopes
3. **Not considering load capacitance effect** - SC power is maximized at low $C_L$
4. **Confusing with leakage** - SC is dynamic (occurs during transitions), leakage is static

---

## Self-Check Questions

<details>
<summary>1. Why does short-circuit current require finite input transition time?</summary>

With zero rise/fall time (ideal step input):
- Input instantly goes from 0 to VDD (or vice versa)
- PMOS and NMOS are never ON simultaneously
- No short-circuit current path exists

With finite transition time, there's a window where $V_{Tn} < V_{in} < V_{DD} - |V_{Tp}|$ and both transistors conduct.
</details>

<details>
<summary>2. How does reducing VDD affect short-circuit power?</summary>

Short-circuit power scales as $(V_{DD} - 2V_T)^3$. This is a cubic relationship, so:
- Reducing $V_{DD}$ has a stronger effect on SC power than on switching power
- When $V_{DD} < 2V_T$, there's no overlap window and SC power approaches zero
</details>

<details>
<summary>3. Why does larger load capacitance reduce short-circuit current?</summary>

With large $C_L$:
- Output changes very slowly during input transition
- When input is transitioning (both transistors ON), output is nearly constant
- The conducting transistor has ~0V drain-source voltage
- Current is limited since there's no voltage drop to drive it
</details>

---

## Concept Links

- **Previous:** [Switching Power](03_switching_power_dissipation.md)
- **Next:** [Leakage Power](05_leakage_power.md)
- **Related:** [Sources of Power Dissipation](02_sources_of_power_dissipation.md)
- **Formula Reference:** [Formula Sheet](16_formula_sheet_ultimate.md#short-circuit-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Switching Power](03_switching_power_dissipation.md) | Short-Circuit Power | [Leakage Power](05_leakage_power.md) |
