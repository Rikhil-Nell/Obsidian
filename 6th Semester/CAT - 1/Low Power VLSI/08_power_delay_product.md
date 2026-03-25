# Power-Delay Product and Energy-Delay Product

## Learning Objectives
After this section, you will understand:
- Power-Delay Product (PDP) as an energy metric
- Energy-Delay Product (EDP) as a combined quality metric
- Voltage dependence of PDP and EDP
- How to optimize the power-performance trade-off

---

## Power-Delay Product (PDP)

### Definition

The **Power-Delay Product** is the product of average power consumption and propagation delay:

$$\boxed{PDP = P_{avg} \times t_p}$$

### Physical Meaning

PDP represents the **energy consumed per switching event**:

$$PDP = P_{avg} \times t_p = \frac{\text{Energy}}{\text{time}} \times \text{time} = \text{Energy}$$

**Units:** Watt-seconds (Ws) = Joules (J)

---

## PDP Derivation

Assuming a gate is switched at maximum frequency $f_{max}$:

$$f_{max} = \frac{1}{2t_p}$$

(Factor of 2 accounts for high-to-low and low-to-high transitions)

For switching power:
$$P = \alpha \cdot f \cdot C_L \cdot V_{DD}^2$$

At maximum frequency:
$$P_{max} = \alpha \cdot \frac{1}{2t_p} \cdot C_L \cdot V_{DD}^2$$

Therefore:
$$\boxed{PDP = P_{max} \times t_p = \frac{\alpha \cdot C_L \cdot V_{DD}^2}{2}}$$

---

## PDP Interpretation

| Aspect | Interpretation |
|--------|----------------|
| Lower PDP | More energy efficient |
| Higher PDP | More energy consumed per operation |
| Goal | Minimize PDP for energy efficiency |

### Why PDP Matters

For battery-operated devices:
- Total energy = PDP × number of operations
- Lower PDP = longer battery life for same number of operations

---

## Energy-Delay Product (EDP)

### Limitation of PDP

PDP measures energy but ignores performance. A circuit can have:
- Low energy but slow (poor performance)
- High energy but fast (poor efficiency)

**EDP combines both energy and performance!**

### Definition

$$\boxed{EDP = PDP \times t_p = P_{avg} \times t_p^2}$$

$$EDP = \frac{\alpha \cdot C_L \cdot V_{DD}^2}{2} \times t_p$$

**Units:** Joule-seconds (J·s)

---

## EDP as a Quality Metric

EDP captures the fundamental trade-off:
- **Higher supply voltage:** Lower delay, higher energy
- **Lower supply voltage:** Higher delay, lower energy

The optimal operating point minimizes EDP.

![[edp_voltage_dependence.png]]

---

## Voltage Dependence Analysis

### Delay vs Voltage

For a CMOS inverter:
$$t_p \propto \frac{C_L \cdot V_{DD}}{(V_{DD} - V_T)^{\alpha}}$$

where $\alpha \approx 1$ to 2 depending on velocity saturation.

As $V_{DD} \rightarrow V_T$: delay increases dramatically!

### PDP vs Voltage

$$PDP \propto V_{DD}^2$$

Reducing voltage reduces PDP (good for energy).

### EDP vs Voltage

$$EDP \propto V_{DD}^2 \times t_p$$

There's an **optimal voltage** that minimizes EDP!

---

## Finding Optimal $V_{DD}$ for EDP

The EDP voltage dependence:

$$EDP = \frac{\alpha C_L V_{DD}^2}{2} \times \frac{C_L V_{DD}}{(V_{DD} - V_T)^2}$$

$$EDP \propto \frac{V_{DD}^3}{(V_{DD} - V_T)^2}$$

Taking derivative and setting to zero gives optimal $V_{DD} \approx 3V_T$.

| Voltage Range | EDP Behavior |
|---------------|--------------|
| $V_{DD} < 3V_T$ | Delay dominates, EDP high |
| $V_{DD} = 3V_T$ | Optimal EDP |
| $V_{DD} > 3V_T$ | Energy dominates, EDP high |

---

## Comparison: PDP vs EDP

| Metric | Formula | Units | Measures | Use Case |
|--------|---------|-------|----------|----------|
| PDP | $P \times t_p$ | Joules | Energy only | Energy efficiency |
| EDP | $P \times t_p^2$ | J·s | Energy × Delay | Power-performance |

### When to Use Which?

- **PDP:** When you care only about energy (battery life)
- **EDP:** When balancing power and performance

---

## Worked Example

**Problem:** A CMOS inverter has:
- $C_L$ = 10 fF
- $V_{DD}$ = 1.0 V
- $t_p$ = 50 ps
- $\alpha$ = 0.5

Calculate PDP and EDP.

**Solution:**

**PDP:**
$$PDP = \frac{\alpha \cdot C_L \cdot V_{DD}^2}{2}$$
$$PDP = \frac{0.5 \times 10 \times 10^{-15} \times 1.0^2}{2}$$
$$\boxed{PDP = 2.5 \text{ fJ}}$$

**EDP:**
$$EDP = PDP \times t_p$$
$$EDP = 2.5 \times 10^{-15} \times 50 \times 10^{-12}$$
$$\boxed{EDP = 1.25 \times 10^{-25} \text{ J·s}}$$

---

## Common Mistakes

1. **Confusing power and energy** - PDP is energy, not power
2. **Optimizing only PDP** - May sacrifice too much performance
3. **Ignoring leakage in PDP** - Static power also contributes
4. **Assuming linear voltage scaling** - Delay behavior is nonlinear

---

## Self-Check Questions

<details>
<summary>1. Why is PDP considered an energy metric even though it includes power?</summary>

$PDP = P \times t_p = \frac{\text{Energy}}{\text{Time}} \times \text{Time} = \text{Energy}$

The time cancels out, leaving energy. PDP represents the energy consumed per switching operation.
</details>

<details>
<summary>2. Why can't we just minimize voltage to get lowest EDP?</summary>

As voltage decreases:
- Energy decreases ($\propto V_{DD}^2$)
- But delay increases ($\propto V_{DD}/(V_{DD}-V_T)$)

At very low voltages (near $V_T$), delay increases dramatically, overwhelming energy savings. EDP has a minimum around $V_{DD} \approx 3V_T$.
</details>

<details>
<summary>3. For a given energy budget, should you optimize for PDP or EDP?</summary>

For a given energy budget alone, PDP is sufficient. But if you also need to meet timing constraints, EDP is more appropriate as it penalizes both high energy AND high delay.
</details>

---

## Concept Links

- **Previous:** [Short Channel Effects](07_short_channel_effects.md)
- **Next:** [Second Order Effects](09_second_order_effects.md)
- **Related:**
  - [Switching Power](03_switching_power_dissipation.md) - Power component of PDP
  - [Parallelism](12_parallelism.md) - Voltage scaling trade-offs
- **Formula Reference:** [Formula Sheet](16_formula_sheet_ultimate.md#pdp-edp)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Short Channel Effects](07_short_channel_effects.md) | PDP and EDP | [Second Order Effects](09_second_order_effects.md) |
