# Glitching Power Dissipation

## Learning Objectives
After this section, you will understand:
- What causes glitches in digital circuits
- How glitches contribute to power dissipation
- Techniques to minimize glitching power
- Why balanced path delays matter

---

## What is Glitching Power?

Glitching power is dissipated due to **spurious signal transitions** that occur before the circuit settles to its final logic value. These intermediate transitions are called **glitches** or **hazards**.

**Key Insight:** Glitches occur due to **unequal propagation delays** through different signal paths.

---

## Glitch Generation Mechanism

Consider a multi-level logic circuit where inputs arrive at different times:

```
Input A ────►┌─────┐
             │     │     ┌─────┐
Input B ────►│ Gate├────►│     │
             │  1  │     │ Gate├────► Output
Input C ────►└─────┘     │  3  │      (glitches!)
                   ┌────►│     │
Input D ────►┌─────┤     └─────┘
             │ Gate│
             │  2  │
             └─────┘
```

If Gate 1 output arrives before Gate 2 output:
- Gate 3 receives inputs at different times
- Output makes false transitions before settling
- Each transition dissipates power

---

## Cascaded Circuit Example

![[glitching_example.png]]

In a chain of gates:
- First gate output changes → second gate responds → creates glitch
- Glitch propagates and amplifies through the chain
- Power is wasted on transitions that don't represent final values

---

## Glitching Power Characteristics

| Characteristic | Description |
|----------------|-------------|
| Type | Dynamic power (switching) |
| Cause | Path delay mismatch |
| Signal | May be full or partial swing |
| Dependence | Logic depth, fan-in, topology |

Glitching power contributes to the overall dynamic power:

$$P_{dynamic} = P_{switching} + P_{glitching} + P_{short-circuit}$$

---

## Conditions for Glitching

Glitches occur when:
1. **Multiple inputs change** at different times
2. **Path delays are unbalanced** - signals arrive at different times
3. **Logic has reconvergent paths** - same signal affects output through multiple routes

When all inputs change **simultaneously**, no glitching occurs!

---

## Example: Parity Network

### Imbalanced Tree (High Glitching)

```
A ──┬──►XOR──┐
B ──┘        │
             ├──►XOR──┐
C ──┬──►XOR──┘        │
D ──┘                 ├──►XOR──► Out
                      │            ↑
E ──┬──►XOR──┐        │         Glitches!
F ──┘        │        │
             ├──►XOR──┘
G ──┬──►XOR──┘
H ──┘
```

**Problem:** Wide disparity in signal arrival times causes many glitches.

### Balanced Tree (Low Glitching)

```
        Level 1          Level 2        Level 3
A ──┬──►XOR──┐
B ──┘        ├──►XOR──┐
C ──┬──►XOR──┘        ├──►XOR──► Out
D ──┘                 │            ↑
                      │         No glitches
E ──┬──►XOR──┐        │
F ──┘        ├──►XOR──┘
G ──┬──►XOR──┘
H ──┘
```

**Solution:** All paths have equal delay → signals arrive together → no glitches.

**Bonus:** Balanced tree also has smaller overall propagation delay!

---

## Techniques to Minimize Glitching

### 1. Path Balancing
- Equalize delays through all paths to each gate
- Use balanced tree structures for multi-input functions
- Add delay elements to short paths (trade-off with speed)

### 2. Buffer Insertion
- Insert buffers on highly loaded nodes
- Helps balance delays
- Also improves signal integrity

### 3. Logic Restructuring
- Convert cascaded implementations to balanced trees
- Reduce logic depth where possible
- Consider alternate logic decomposition

### 4. Input Reordering
- In multi-level logic, order inputs by arrival time
- Place late-arriving signals near output

### 5. Avoid Cascaded Structures

**Instead of:**
```
A ─►AND─►AND─►AND─►AND─► Out
     ↑    ↑    ↑    ↑
     B    C    D    E
```

**Use:**
```
         ┌──►AND──┐
A,B ────►│       ├──►AND──► Out
C,D ────►└──►AND──┘
             ↑
             E (treated separately)
```

---

## Dynamic Logic Advantage

**Key Observation:** Glitching is **not significant** in dynamic CMOS logic circuits because:
- Each node can undergo **at most one transition** per clock cycle
- Precharge/evaluate phases prevent multiple transitions

This is one advantage of dynamic logic for low-power design.

---

## Common Mistakes

1. **Ignoring internal nodes** - Glitches on internal nodes waste power even if output doesn't glitch
2. **Assuming all transitions are useful** - Many transitions are just glitches
3. **Not considering logic depth** - Deeper logic = more glitch opportunities
4. **Optimizing only for speed** - May create delay imbalances

---

## Self-Check Questions

<details>
<summary>1. Why doesn't glitching occur in dynamic logic?</summary>

In dynamic logic:
- Precharge phase sets all outputs to known state
- Evaluate phase allows only one transition (or none)
- Clock controls when evaluation happens
- Output can change at most once per clock cycle

This eliminates the possibility of multiple transitions (glitches) per cycle.
</details>

<details>
<summary>2. How does a balanced tree structure reduce glitches?</summary>

In a balanced tree:
- All paths from inputs to output have equal propagation delay
- All input signals "appear" at each gate level simultaneously
- When all inputs change together, no glitches occur
- Additionally, overall delay is reduced (logarithmic vs linear)
</details>

<details>
<summary>3. Can adding delays reduce power?</summary>

Yes, paradoxically! Adding delays to short paths can balance the arrival times at subsequent gates, eliminating glitches. The power saved from prevented glitches may exceed the small power cost of the added delays.
</details>

---

## Concept Links

- **Previous:** [Leakage Power](./05_leakage_power.md)
- **Next:** [Short Channel Effects](./07_short_channel_effects.md)
- **Related:**
  - [Switching Power](./03_switching_power_dissipation.md) - Glitching is a form of switching
  - [Sources of Power](./02_sources_of_power_dissipation.md) - Classification
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#dynamic-power)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Leakage Power](./05_leakage_power.md) | Glitching Power | [Short Channel Effects](./07_short_channel_effects.md) |
