# Parallelism for Low Power Design

## Learning Objectives
After this section, you will understand:
- How parallelism enables voltage scaling
- The power-area trade-off in parallel architectures
- Quantitative analysis with 16-bit adder example
- When parallelism is beneficial

---

## Core Concept

**Key Insight:** Parallel processing trades **area** for **power reduction** while maintaining throughput.

Traditional view:
> Parallelism improves performance at cost of power

Low-power view:
> Parallelism enables voltage scaling while maintaining performance!

---

## How Parallelism Enables Power Reduction

### The Power-Throughput Trade-off

From the power equation:
$$P = \alpha f C_L V_{DD}^2$$

And the frequency relationship:
$$f_{max} \propto \frac{V_{DD} - V_T}{V_{DD}} \cdot V_{DD} \approx V_{DD} \text{ (when } V_T \ll V_{DD} \text{)}$$

### The Opportunity

If we can **halve the frequency** requirement:
- Use **two parallel units** operating at $f/2$ each
- Reduce $V_{DD}$ proportionally
- Power reduces by MORE than half (quadratic voltage effect!)

---

## 16-Bit Adder Example

### Reference Design (Single Adder)

| Parameter | Value |
|-----------|-------|
| Supply voltage | $V_{ref}$ |
| Frequency | $f_{ref}$ = 100 MHz |
| Effective capacitance | $C_{ref}$ |
| Power | $P_{ref} = C_{ref} \cdot V_{ref}^2 \cdot f_{ref}$ |

### Parallel Architecture (Two Adders)

![[parallel_adder_architecture.png]]

```
            ┌─────────────┐
A[15:0] ───►│   Adder 1   │
B[15:0] ───►│  (16-bit)   ├───┐
            └─────────────┘   │
                              ├──►MUX──► Result
            ┌─────────────┐   │
A[15:0] ───►│   Adder 2   ├───┘
B[15:0] ───►│  (16-bit)   │
            └─────────────┘
```

**Operation:**
- Even cycles: Adder 1 computes, output from Adder 2
- Odd cycles: Adder 2 computes, output from Adder 1
- Each adder runs at $f_{ref}/2$ while system maintains $f_{ref}$ throughput

---

## Power Analysis of Parallel Architecture

### Voltage Scaling

Since each adder runs at half frequency:
$$f_{new} = \frac{f_{ref}}{2}$$

The voltage can be reduced (assuming linear delay-voltage relationship):
$$V_{new} \approx 0.5 \cdot V_{ref}$$

### Capacitance Increase

Two adders + MUX overhead:
$$C_{total} \approx 2 \cdot C_{ref}$$

### Power Calculation

$$P_{parallel} = C_{total} \cdot V_{new}^2 \cdot f_{new}$$
$$P_{parallel} = 2 C_{ref} \cdot (0.5 V_{ref})^2 \cdot \frac{f_{ref}}{2}$$
$$P_{parallel} = 2 C_{ref} \cdot 0.25 V_{ref}^2 \cdot 0.5 f_{ref}$$
$$\boxed{P_{parallel} = 0.25 \cdot P_{ref}}$$

**Result:** 75% power reduction!

---

## Impact Summary

| Metric | Reference | Parallel | Change |
|--------|-----------|----------|--------|
| Throughput | $f_{ref}$ | $f_{ref}$ | Same |
| Area | 1× | 2× | +100% |
| Voltage | $V_{ref}$ | $0.5 V_{ref}$ | -50% |
| Frequency (per unit) | $f_{ref}$ | $f_{ref}/2$ | -50% |
| **Power** | $P_{ref}$ | $0.25 P_{ref}$ | **-75%** |

![[parallelism_impact_table.png]]

---

## General Analysis

For N parallel units:

| Parameter | Value |
|-----------|-------|
| Voltage | $V_{DD}/N$ |
| Frequency per unit | $f/N$ |
| Total capacitance | $N \cdot C_{ref}$ |
| Power | $N \cdot C_{ref} \cdot (V_{DD}/N)^2 \cdot (f/N) = P_{ref}/N^2$ |

$$\boxed{P_{parallel} = \frac{P_{ref}}{N^2}}$$

**Quadratic power reduction with linear area increase!**

| N (units) | Area | Power | Power Reduction |
|-----------|------|-------|-----------------|
| 1 | 1× | 1.0 | Reference |
| 2 | 2× | 0.25 | 75% |
| 4 | 4× | 0.0625 | 93.75% |
| 8 | 8× | 0.0156 | 98.4% |

---

## Practical Considerations

### Limits to Scaling

1. **$V_{DD}$ cannot be arbitrarily low**
   - Must maintain $V_{DD} > V_T$ for proper operation
   - Noise margins degrade at low voltage

2. **Diminishing returns**
   - Overhead (MUX, routing) grows
   - Leakage becomes significant at low voltages

3. **Area constraints**
   - Chip area is expensive
   - Practical limit is often 2-4× replication

### When Parallelism is Effective

✅ Good candidates:
- Data-flow dominated applications
- Regular, repetitive computations
- Throughput matters more than latency

❌ Poor candidates:
- Control-dominated logic
- Irregular computation patterns
- Area-constrained designs

---

## Comparison with Pipelining

| Aspect | Parallelism | Pipelining |
|--------|-------------|------------|
| Approach | Multiple copies | Break into stages |
| Area overhead | 2×+ | Lower (~10-20%) |
| Latency | Same | Increased (pipeline depth) |
| Throughput | Maintained | Maintained |
| Voltage reduction | Yes | Yes |
| Complexity | Moderate | Lower |

See [Pipelining](./13_pipelining.md) for comparison.

---

## Common Mistakes

1. **Forgetting MUX overhead** - Adds to total capacitance
2. **Ignoring routing** - Long wires between parallel units add delay/power
3. **Linear power assumption** - Actual relationship is quadratic
4. **Neglecting control logic** - Selection logic also consumes power

---

## Self-Check Questions

<details>
<summary>1. Why does parallelism enable voltage reduction?</summary>

Each parallel unit operates at lower frequency ($f/N$ for N units). Since:
$$f_{max} \propto V_{DD}$$

Lower frequency requirement allows lower voltage while maintaining speed. The entire system still maintains full throughput.
</details>

<details>
<summary>2. Why is power reduction quadratic while area is linear?</summary>

Power depends on $V_{DD}^2 \cdot f$:
- With N units, voltage scales as $1/N$
- Frequency per unit scales as $1/N$
- Power per unit scales as $1/N^3$
- N units give total power $= N \cdot (1/N^3) \cdot P_{ref} = P_{ref}/N^2$

Area doubles but power quarters with N=2 because voltage appears squared in power equation.
</details>

<details>
<summary>3. What limits practical parallelism for low power?</summary>

- Minimum operating voltage ($V_{DD} > V_T$)
- Area overhead and cost
- Routing complexity and wire delays
- Control logic overhead
- Diminishing returns at high N
- Leakage power at very low voltages
</details>

---

## Concept Links

- **Previous:** [MTCMOS Circuits](./11_mtcmos_circuits.md)
- **Next:** [Pipelining](./13_pipelining.md)
- **Related:**
  - [Switching Power](./03_switching_power_dissipation.md) - The power being reduced
  - [Parallel + Pipelining](./13_pipelining.md#combined-approach)
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#switching-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [MTCMOS](./11_mtcmos_circuits.md) | Parallelism | [Pipelining](./13_pipelining.md) |
