# Switched Capacitance Minimization

## Learning Objectives
After this section, you will understand:
- What switched capacitance means
- System-level capacitance reduction techniques
- Circuit-level approaches (pass-transistor logic)
- Mask-level (physical design) measures

---

## What is Switched Capacitance?

**Switched capacitance** is the effective capacitance that is actively charged and discharged during operation:

$$C_{switched} = \sum_i \alpha_i \cdot C_i$$

where:
- $\alpha_i$ = switching activity of node $i$
- $C_i$ = physical capacitance at node $i$

Since dynamic power:
$$P_{dyn} = f \cdot C_{switched} \cdot V_{DD}^2$$

**Reducing switched capacitance directly reduces dynamic power!**

---

## Sources of Capacitance

```
Total Node Capacitance
├── Gate Capacitance
│   └── Input capacitance of driven gates
├── Diffusion Capacitance  
│   └── Drain/source junction capacitances
├── Interconnect Capacitance
│   └── Metal lines (dominant in sub-micron!)
└── Coupling Capacitance
    └── To adjacent wires and substrate
```

---

## Three-Level Approach

Switched capacitance can be reduced at different design hierarchies:

| Level | Focus | Examples |
|-------|-------|----------|
| **System** | Architecture, bus structure | Bus partitioning |
| **Circuit** | Logic style | Pass-transistor logic |
| **Mask/Physical** | Layout, sizing | Minimum transistors |

---

## System-Level Measures

### The Global Bus Problem

When many modules share a single global bus:

![[bus_partitioning.png]]

Problems:
- Large capacitance from all connected drivers/receivers
- Long interconnect length
- Every bus access charges this large capacitance
- High dynamic power consumption

### Solution: Bus Partitioning

Replace one global bus with multiple local buses:

| Approach | Capacitance per Access | Power |
|----------|----------------------|-------|
| Global bus | High | High |
| Local buses | Low | Low |

**Trade-off:** More routing area, more complex interconnect.

### Other System-Level Techniques

1. **Memory partitioning** - Enable only needed banks
2. **Functional unit isolation** - Power down unused modules
3. **Data encoding** - Reduce transitions on buses
4. **Locality-aware placement** - Keep communicating modules close

---

## Circuit-Level Measures

### Impact of Logic Style

Different logic styles have different transistor counts for the same function:

| Logic Style | Transistors | Characteristics |
|-------------|-------------|-----------------|
| Static CMOS | High | Full swing, robust |
| Pass-transistor | Low | Compact, but needs restoration |
| Dynamic | Medium | Fast, but complex clocking |

### Pass-Transistor (Transmission Gate) Logic

Pass-transistor logic can implement some functions with **fewer transistors**:

**Example: XOR Gate**

Standard CMOS (12 transistors):
```
Complex implementation with NAND/NOR gates
```

Pass-transistor (4 transistors + inverter):
```
       A ─────┬─────────────┐
              │             │
         ┌────┴────┐   ┌────┴────┐
    B ───┤  NMOS   ├───┤  PMOS   ├─── Out
         │ (A as  │   │ (Ā as  │
         │ control)│   │ control)│
         └────┬────┘   └────┬────┘
              │             │
       B̄ ─────┴─────────────┘
```

**Fewer transistors → Less capacitance → Lower power!**

### Advantages of Pass-Transistor Logic

| Advantage | Description |
|-----------|-------------|
| Fewer transistors | For XOR, MUX, arithmetic circuits |
| Less capacitance | Fewer nodes to charge |
| Good for data paths | Multiplexers, adders |

### Disadvantages of Pass-Transistor Logic

| Disadvantage | Description |
|--------------|-------------|
| Threshold drop | nMOS cannot pass strong '1' |
| Signal degradation | Voltage levels may degrade |
| Need restoration | Level restorer or buffer required |
| Dual-rail logic | Often needs complementary signals |
| Output buffering | Must add inverters for driving |

### Signal Degradation Problem

When nMOS passes logic '1':
- Output reaches only $V_{DD} - V_{Tn}$
- Not full swing → static current in following inverter
- Requires swing restoration circuitry

### Practical Considerations

The advantages of pass-transistor logic may be offset by:
- Swing restoration circuits
- Output buffers for driving capability
- Generation of complementary signals

**Recommendation:** Evaluate total silicon area, delay, and power before choosing logic style.

---

## Mask-Level Measures

### Principle

At the physical design level, reduce parasitic capacitances:

- Gate capacitance: $C_g = C_{ox} \cdot W \cdot L$
- Diffusion capacitance: $C_d \propto W \cdot L_d$

**Smaller transistors → Less capacitance!**

### Minimum-Size Transistors

Using minimum-size transistors where feasible:
- Minimizes drain/source junction capacitances
- Reduces gate capacitance
- Lowers switched capacitance

Performance vs. Power Trade-Off
- Designing logic gates with minimum-size transistors affects the dynamic performance of the circuit.

### Trade-off: Size vs Performance

| Sizing | Capacitance | Drive Strength | Delay |
|--------|-------------|----------------|-------|
| Minimum | Lowest | Weak | Higher (at large loads) |
| Larger | Higher | Strong | Lower (at large loads) |

**Guideline:** 
- Use minimum size for internal gates
- Upsize only gates driving large loads or critical paths

### Impact of Load Type

| Load Type | Sizing Strategy |
|-----------|-----------------|
| Intrinsic (small) | Minimum size OK |
| Extrinsic (large) | Must upsize for adequate drive |
| Long interconnect | Upsize for wire delay |
| High fan-out | Upsize for multiple loads |

### Standard Cell Library Considerations

Most standard cell libraries use:
- Larger transistor sizes for general-purpose use
- Support wide range of loads and speeds

**Result:** Standard-cell designs may have higher switched capacitance than custom-sized implementations.

| Design Style | Capacitance Overhead |
|--------------|---------------------|
| Full custom | Optimal |
| Standard cell | 10-50% overhead |
| Gate array | Higher overhead |

---

## Summary: Capacitance Reduction Techniques

| Level | Technique | Impact |
|-------|-----------|--------|
| System | Bus partitioning | Reduce interconnect cap |
| System | Memory partitioning | Enable only needed banks |
| Circuit | Pass-transistor logic | Fewer transistors |
| Circuit | Logic optimization | Reduce gate count |
| Mask | Minimum sizing | Reduce transistor cap |
| Mask | Short interconnects | Reduce wire cap |

---

## Common Mistakes

1. **Ignoring wire capacitance** - Dominates in sub-micron
2. **Always using minimum size** - Critical paths need upsizing
3. **Assuming pass-transistor is always better** - Overhead can exceed savings
4. **Not considering switching activity** - High-activity nodes need more attention

---

## Self-Check Questions

<details>
<summary>1. Why is interconnect capacitance becoming more significant?</summary>

As transistors scale:
- Gate capacitance decreases (smaller transistors)
- But wire lengths relative to transistor size increase
- Wire capacitance doesn't scale as well as transistors
- In deep sub-micron, wire capacitance can be 50-80% of total

Metal lines become the dominant capacitor!
</details>

<details>
<summary>2. When is pass-transistor logic beneficial?</summary>

Pass-transistor logic is beneficial when:
- Implementing functions like XOR, MUX that naturally suit it
- Transistor count reduction exceeds restoration overhead
- Cascading is limited (signal degradation compounds)
- Dual-rail signals are already available

It may NOT be beneficial for:
- High fan-out outputs (need strong buffers)
- Long logic chains (signal degradation)
- When complementary signals must be generated
</details>

<details>
<summary>3. How does bus partitioning reduce power?</summary>

Original global bus:
- All modules connect to one bus
- Every access charges large total capacitance

Partitioned local buses:
- Each access only charges local bus capacitance
- Often 4-10× smaller capacitance per access
- Power per access reduced proportionally

Trade-off: Inter-partition communication may be higher.
</details>

---

## Concept Links

- **Previous:** [Pipelining](13_pipelining.md)
- **Next:** [Worked Problems](15_worked_problems.md)
- **Related:**
  - [Switching Power](03_switching_power_dissipation.md) - Power from capacitance
  - [Sources of Power](02_sources_of_power_dissipation.md)
- **Formula Reference:** [Formula Sheet](16_formula_sheet_ultimate.md#switching-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Pipelining](13_pipelining.md) | Switched Capacitance | [Worked Problems](15_worked_problems.md) |
