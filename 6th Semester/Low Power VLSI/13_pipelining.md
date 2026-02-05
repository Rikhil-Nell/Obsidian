# Pipelining for Low Power Design

## Learning Objectives
After this section, you will understand:
- How pipelining reduces critical path delay
- Voltage scaling through pipelining
- Comparison with parallelism
- Combined parallel-pipelined approach

---

## Core Concept

**Key Insight:** Pipelining breaks a long computation into shorter stages, allowing voltage scaling while maintaining throughput.

Unlike parallelism (which replicates hardware), pipelining reuses the same hardware with time-multiplexing.

---

## How Pipelining Enables Power Reduction

### The Critical Path Problem

In a combinational circuit:
$$t_{delay} = t_{critical\_path}$$

To run at frequency $f$:
$$f \leq \frac{1}{t_{delay}}$$

### The Pipelining Solution

Break the long path into N stages:
$$t_{stage} \approx \frac{t_{delay}}{N}$$

Each stage can now run at the same external frequency but with:
- Lower critical path per stage
- Opportunity to reduce voltage!

![[power_consumption_trend.png]]

---

## 16-Bit Adder Example

### Reference Design (Non-Pipelined)

| Parameter | Value |
|-----------|-------|
| Critical path | $t_{16bit}$ |
| Frequency | $f_{ref}$ = 100 MHz |
| Voltage | $V_{ref}$ |
| Capacitance | $C_{ref}$ |

### Two-Stage Pipelined Design

```
                    Pipeline Register
                          │
        ┌────────────┐    │    ┌────────────┐
A[7:0]──┤  8-bit    ├────┼────┤  8-bit    ├──► Sum[15:0]
B[7:0]──┤  Adder 1  │    │    │  Adder 2  │
        └────────────┘    │    └────────────┘
          (Low 8)         │      (High 8)
                          │
                     Carry out
```

![[pipelined_adder.png]]

**Key Change:** Each stage has ~half the critical path delay.

---

## Voltage Scaling Analysis

### Critical Path Reduction

$$t_{stage} \approx \frac{t_{16bit}}{2}$$

### Voltage Reduction

Since $f_{max} \propto V_{DD}$ (to first order):

If critical path is halved, we can halve voltage while maintaining same frequency:
$$V_{new} \approx 0.5 \cdot V_{ref}$$

### Capacitance Overhead

Pipeline registers add some capacitance:
$$C_{total} \approx C_{ref} + C_{reg} \approx 1.1 \cdot C_{ref}$$

(Assuming ~10% overhead for registers)

---

## Power Analysis

$$P_{pipelined} = C_{total} \cdot V_{new}^2 \cdot f_{ref}$$
$$P_{pipelined} = 1.1 \cdot C_{ref} \cdot (0.5 V_{ref})^2 \cdot f_{ref}$$
$$P_{pipelined} = 1.1 \cdot 0.25 \cdot P_{ref}$$
$$\boxed{P_{pipelined} \approx 0.275 \cdot P_{ref}}$$

**Result:** ~72% power reduction with only ~10% area overhead!

---

## Pipelining vs Parallelism Comparison

| Metric | Parallelism | Pipelining |
|--------|-------------|------------|
| **Power reduction** | 75% | 72% |
| **Area overhead** | 100%+ | 10-20% |
| **Latency** | Same | Increased by (N-1) cycles |
| **Throughput** | Same | Same |
| **Complexity** | MUX + control | Pipeline registers |
| **Data independence** | Required | Not required |

**Key Insight:** Pipelining achieves similar power savings with much less area!

![[pipelining_impact_table.png]]

---

## Trade-offs in Pipelining

### Advantages

| Advantage | Description |
|-----------|-------------|
| Low area overhead | Only adds pipeline registers |
| Similar power savings | As parallelism |
| Natural fit | For data-flow architectures |

### Disadvantages

| Disadvantage | Description |
|--------------|-------------|
| Increased latency | N-stage pipeline has (N-1) cycle latency |
| Pipeline hazards | Data/control dependencies complicate design |
| Register overhead | Each stage needs output registers |
| Clock skew | Must balance across stages |

---

## Combined Parallel-Pipelined Approach

### Combining Both Techniques

For maximum power reduction, use BOTH parallelism AND pipelining:

```
        ┌───────────────────────────────┐
        │  Pipelined Adder 1            │
A ────►─┤  (2-stage)                    ├──┐
B ────►─│                               │  │
        └───────────────────────────────┘  │
                                           ├──►MUX──► Result
        ┌───────────────────────────────┐  │
        │  Pipelined Adder 2            ├──┘
A ────►─┤  (2-stage)                    │
B ────►─│                               │
        └───────────────────────────────┘
```

### Analysis for 2×2 Configuration

Two parallel units, each with 2-stage pipeline:

| Parameter | Value |
|-----------|-------|
| Voltage | $V_{ref}/4$ (from both techniques) |
| Frequency per unit | $f_{ref}/2$ |
| Total capacitance | $\approx 2.2 \cdot C_{ref}$ |

$$P_{combined} = 2.2 \cdot C_{ref} \cdot (V_{ref}/4)^2 \cdot (f_{ref}/2)$$
$$P_{combined} = 2.2 \cdot \frac{1}{16} \cdot \frac{1}{2} \cdot P_{ref}$$
$$\boxed{P_{combined} \approx 0.07 \cdot P_{ref}}$$

**Result:** ~93% power reduction! (~11% of reference power)

---

## Summary Table

| Architecture | Area | Power | Reduction |
|--------------|------|-------|-----------|
| Reference | 1× | 1.0 | - |
| Parallel (2×) | 2× | 0.25 | 75% |
| Pipelined (2-stage) | 1.1× | 0.275 | 72% |
| Combined (2×2) | 2.2× | 0.07 | 93% |

![[power_comparison_table.png]]

---

## Practical Considerations

### Pipeline Depth Limits

1. **Register overhead** - Too many stages add capacitance
2. **Clock distribution** - More stages = harder clock tree
3. **Diminishing returns** - Voltage can't go below $V_T$

### Optimal Pipeline Depth

Typically 2-4 stages provide best power-performance trade-off.

### Data Dependencies

- Pipelining requires careful handling of data hazards
- May need forwarding or stalling logic
- Adds control complexity

---

## Common Mistakes

1. **Ignoring pipeline register power** - Registers consume power too
2. **Forgetting latency penalty** - Not acceptable for all applications
3. **Over-pipelining** - Too many stages have diminishing returns
4. **Unequal stage delays** - Slowest stage limits frequency

---

## Self-Check Questions

<details>
<summary>1. Why does pipelining enable voltage reduction?</summary>

Pipelining reduces critical path per stage:
- Each stage is shorter → faster
- If we don't need the extra speed, reduce voltage
- Lower voltage maintains same stage delay as original full path
- System throughput is maintained (same clock frequency)
</details>

<details>
<summary>2. Why is pipelining more area-efficient than parallelism?</summary>

- Parallelism: Duplicate entire logic block (2× or more)
- Pipelining: Only add pipeline registers between stages

Pipeline registers are much smaller than duplicating compute units. For a 16-bit adder, registers might be 10-20% overhead vs 100%+ for parallel adder.
</details>

<details>
<summary>3. When would you choose parallelism over pipelining?</summary>

Choose parallelism when:
- Latency is critical (pipelining adds latency)
- Data dependencies prevent pipelining
- Application has natural parallel structure
- Already at optimal pipeline depth

Choose pipelining when:
- Area is constrained
- Latency can be tolerated
- Natural sequential data flow exists
</details>

---

## Concept Links

- **Previous:** [Parallelism](./12_parallelism.md)
- **Next:** [Switched Capacitance](./14_switched_capacitance.md)
- **Related:**
  - [Switching Power](./03_switching_power_dissipation.md) - The power being reduced
  - [Parallelism](./12_parallelism.md) - Alternative approach
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#switching-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Parallelism](./12_parallelism.md) | Pipelining | [Switched Capacitance](./14_switched_capacitance.md) |
