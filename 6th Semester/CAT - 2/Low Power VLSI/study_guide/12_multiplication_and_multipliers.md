# 12 - Multiplication and Multiplier Architectures

---

## Learning Objectives

After studying this section, you will be able to:

- Explain unsigned binary multiplication and partial product generation
- Describe right-shift and left-shift multiplication algorithms
- Classify multiplier types (serial, parallel, serial-parallel)
- Explain the Braun Multiplier structure, timing, advantages/disadvantages
- Explain the Baugh-Wooley Multiplier for signed 2's complement multiplication
- Describe the 5 building blocks and product formula transformation
- Explain the Booth Multiplier algorithm with registers, encoding, and flowchart
- Walk through a Booth multiplication example step-by-step
- Explain the Wallace Tree Multiplier and 4:2 compressor concept
- Compare all four multiplier architectures

---

## Overview of Multiplication

### Fundamental Concept

Multiplication can be considered as a **series of repeated additions**:

| Term | Definition |
|------|-----------|
| **Multiplicand** | The number being added ($X$) |
| **Multiplier** | The number of times it is added ($Y$) |
| **Product** | The result of multiplication ($P$) |

The two main steps in any multiplication algorithm:
1. **Generating partial products** (AND of multiplicand bits with multiplier bits)
2. **Accumulating/adding partial products** (using adder structures)

To speed up multiplication, **both steps must be optimized**.

### Unsigned Multiplication

For two n-bit unsigned numbers $X$ (multiplicand) and $Y$ (multiplier):

$$X = \sum_{i=0}^{n-1} x_i \cdot 2^i \quad \text{and} \quad Y = \sum_{j=0}^{n-1} y_j \cdot 2^j$$

The product $P = X \times Y$ produces a **2n-bit** result:

$$\boxed{P = X \times Y = \sum_{i=0}^{n-1} \sum_{j=0}^{n-1} x_i \cdot y_j \cdot 2^{i+j}}$$

Each partial product term $P_{ij} = x_i \cdot y_j$ is called a **summand** and is generated using a simple AND gate.

![[unsigned_multiplication_formula.png]]
![[multiplication_partial_products.png]]
![[general_multiplication_algorithm.png]]

### Shift/Add Multiplication Algorithms

Sequential (1-bit-at-a-time) multiplication works by:
1. Initialize cumulative partial product to 0
2. Successively add shifted versions of the multiplicand
3. Each term is shifted by one bit relative to the previous

Two versions exist:

| Version | Direction | Processing Order |
|---------|-----------|-----------------|
| **Right-shift** | Shift partial product right | Top to bottom (MSB → LSB) |
| **Left-shift** | Shift partial product left | Bottom to top (LSB → MSB) |

![[right_shift_multiplication.png]]
![[left_shift_multiplication.png]]

### Hardware Implementation

The sequential multiplier uses:
- **Registers**: to store multiplicand, multiplier, and partial product
- **Adder**: to add partial products
- **Shift register**: to shift the partial product

![[shift_add_hardware_1.png]]
![[shift_add_hardware_2.png]]
![[multiplication_flowchart_1.png]]

---

## Multiplier Classification

| Type | Description | Speed | Area |
|------|-------------|-------|------|
| **Serial** | Both operands entered serially; successive addition | Slowest | Smallest |
| **Parallel** | Both operands applied in parallel; all partial products computed simultaneously | Fastest | Largest |
| **Serial-Parallel** | One operand serial, one parallel; trade-off | Medium | Medium |

![[multiplier_classification.png]]

**Serial multipliers**: Simple structure, minimum chip area, but poor speed performance.

**Parallel multipliers**: High-speed but large chip area. Examples include:
- **Array multipliers**: Braun, Baugh-Wooley (regular structure, easier layout)
- **Tree multipliers**: Wallace Tree (faster due to reduced addition stages)

**Serial-Parallel multipliers**: Good trade-off between speed and area. One operand is serial, the other stored in parallel.

---

## 1. Braun Multiplier

### Structure

The Braun multiplier is the most basic **parallel array multiplier** for unsigned numbers. It consists of:

- An **array of AND gates** for partial product generation
- **Carry Save Adders** (rows of FAs) for partial product accumulation
- A final **Ripple Carry Adder** at the last row for the final result

![[braun_multiplier_structure.png]]

### Operation

For n×n multiplication:
1. Each $x_i \cdot y_j$ is computed using an AND gate
2. The partial products are arranged in shifted rows
3. Each row of FAs adds one row of partial products to the accumulated sum (carry-save fashion)
4. The carry bits are "saved" for the next row rather than propagated
5. The final row uses a RCA to produce the final product

### Timing Analysis

The worst-case multiplication time:

![[braun_worst_case_formula.png]]

The critical path passes through:
- The diagonal of the carry-save array
- Plus the final RCA row

![[braun_timing_analysis.png]]

### Complexity

$$\boxed{\text{Hardware complexity} = O(n^2)}$$

For an n×n multiplier:
- $n^2$ AND gates for partial products
- $(n-1) \times (n-1)$ Full Adders in the carry-save array
- $(n-1)$ Full Adders in the final RCA

### Glitching Problem

The Braun multiplier is susceptible to **glitching** at the final RCA stage:
- The carry propagates sequentially through the final RCA
- This causes temporary, incorrect output values before stabilization
- Glitches increase dynamic power consumption

### Advantages

| Advantage | Explanation |
|-----------|-------------|
| Simple, regular array structure | Easy to design and implement in VLSI |
| Suitable for unsigned multiplication | Standard approach for unsigned operands |
| Good performance for small sizes | Speed, power, and area efficient for < 16 bits |
| Easy layout | Repetitive structure simplifies physical design |

### Disadvantages

| Disadvantage | Explanation |
|--------------|-------------|
| $O(n^2)$ hardware complexity | Area grows quadratically with operand size |
| Not efficient for large operands | Area and power become prohibitive for > 16 bits |
| Uses RCA at final stage | Slow carry propagation limits speed |
| Glitching problems | Extra power consumption due to spurious transitions |
| Unsigned only | Cannot directly handle signed multiplication |

---

## 2. Baugh-Wooley Multiplier

### Purpose

The Baugh-Wooley multiplier extends the Braun multiplier to handle **signed numbers in 2's complement representation**. It rearranges partial products to avoid explicitly handling negative terms.

### 2's Complement Representation

For a signed n-bit number $X$ in 2's complement:

$$X = -x_{n-1} \cdot 2^{n-1} + \sum_{i=0}^{n-2} x_i \cdot 2^i$$

The MSB ($x_{n-1}$) carries a **negative weight**.

### The Problem with Signed Multiplication

When multiplying two signed numbers $X \times Y$:
- The partial products involving the sign bits ($x_{n-1}$ and $y_{n-1}$) are **negative**
- This would normally require **subtraction circuits** in the array

### Baugh-Wooley Solution

The key insight: **transform the negative partial product terms into positive terms** so that only **adders** are needed (no subtractors).

![[baugh_wooley_structure.png]]
![[baugh_wooley_partial_products.png]]

### Product Formula

The product of signed X and Y is:

![[baugh_wooley_product_formula.png]]

The last two terms involve subtraction. Baugh-Wooley transforms these using:
- $\bar{x_i} = 1 - x_i$ (complement relationship)
- Converting subtractions into additions with a constant correction

![[baugh_wooley_transformation.png]]
![[baugh_wooley_final_product.png]]

### Five Building Blocks

The Baugh-Wooley array uses **five types of building blocks**:

![[baugh_wooley_building_blocks.png]]

1. **Type 1**: Standard FA with AND-generated partial products
2. **Type 2**: FA with complemented partial products (for sign-bit rows)
3. **Type 3**: FA with additional correction terms
4. **Type 4**: Half Adder for boundary cells
5. **Type 5**: Constant correction cell

![[baugh_wooley_array.png]]

### Performance Considerations

| Factor | Impact |
|--------|--------|
| Number of bits | Area and power increase with operand size |
| Multiplier structure | Array structure affects layout efficiency |
| Layout strategy | Regularity improves power and wiring |

Regular and local structures (like array multipliers):
- Improve layout efficiency
- Reduce interconnect complexity
- Minimize wiring overhead
- Reduce switching activity → lower power

![[bw_basic_concept.png]]
![[bw_handling_signed.png]]
![[bw_partial_products.png]]
![[bw_column_addition.png]]

### Advantages

| Advantage | Explanation |
|-----------|-------------|
| Handles signed multiplication | 2's complement operands directly supported |
| Regular array structure | Suitable for VLSI implementation |
| No explicit sign handling | Hardware simplified by partial product transformation |
| Compatible with carry-save | Uses CSA-based accumulation |
| Better than separate sign-magnitude | Unified architecture for signed multiplication |

### Disadvantages

| Disadvantage | Explanation |
|--------------|-------------|
| $O(n^2)$ complexity | Same quadratic growth as Braun |
| More adders than unsigned | Sign correction terms require additional cells |
| Not as fast as Wallace Tree | Linear reduction depth limits speed |
| Higher power and area for large sizes | Scaled with operand size |
| Increased circuit complexity | More building block types vs Braun |

---

## 3. Booth Multiplier

### Purpose

The Booth multiplier reduces the number of partial products by **encoding** the multiplier bits, achieving speed improvement for signed multiplication with less hardware than full array multipliers.

### Booth Encoding Algorithm

The Booth algorithm examines **2 bits at a time** (the current multiplier bit and the previous bit) to determine the operation:

![[booth_encoding_table.png]]

| $Q_i$ | $Q_{i-1}$ | Action |
|--------|-----------|--------|
| 0 | 0 | No operation (shift only) |
| 0 | 1 | Add multiplicand to accumulator |
| 1 | 0 | Subtract multiplicand from accumulator |
| 1 | 1 | No operation (shift only) |

### Registers

![[booth_registers.png]]

| Register | Purpose |
|----------|---------|
| **A** (Accumulator) | Stores the running sum/product |
| **Q** (Multiplier) | Holds the multiplier bits |
| **Q₋₁** (Extra bit) | Holds the previous LSB of Q (initialized to 0) |
| **M** (Multiplicand) | Stores the multiplicand |
| **Counter** | Tracks the number of iterations (n iterations for n-bit multiplier) |

### Algorithm Steps

![[booth_flowchart.png]]

**Initialization**:
- A ← 0 (all zeros)
- Q ← Multiplier
- Q₋₁ ← 0
- Counter ← n (number of bits)

![[booth_initialization.png]]

**For each iteration**:

1. **Decision Step**: Examine Q₀ and Q₋₁:
   - If Q₀Q₋₁ = 10: A ← A - M (subtract multiplicand)
   - If Q₀Q₋₁ = 01: A ← A + M (add multiplicand)
   - If Q₀Q₋₁ = 00 or 11: No arithmetic operation

![[booth_decision_step.png]]

2. **Shift Operation**: Arithmetic right shift [A, Q, Q₋₁] by 1 bit

![[booth_shift_operation.png]]

3. **Counter Update**: Counter ← Counter - 1

![[booth_counter_update.png]]

4. **Repeat** until Counter = 0

**Final Output**: Product = [A, Q] (concatenation)

![[booth_final_output.png]]

### Worked Example

![[booth_example.png]]

### Key Insight: Why Booth Works

The algorithm exploits the property that **consecutive 1's in the multiplier** can be replaced by a subtraction at the start of the run and an addition at the end. For example:
- `0111110` (binary 62) can be treated as `1000000 - 0000010` (64 - 2)
- Instead of 5 additions, only 1 subtraction and 1 addition are needed

### Advantages

| Advantage | Explanation |
|-----------|-------------|
| Handles signed multiplication | Works directly with 2's complement |
| Reduces partial products | Considers 2 bits at a time, halving operations for consecutive 1's |
| Handles consecutive 1's efficiently | Bit-pair recoding eliminates redundant additions |
| Saves hardware | Less than array multipliers (Braun, Baugh-Wooley) |
| Suitable for high-speed multiplication | Used in modern processors and DSPs |

### Disadvantages

| Disadvantage | Explanation |
|--------------|-------------|
| Complex control logic | Decision-making circuitry adds design complexity |
| Extra components needed | Q₋₁ bit, decision logic, arithmetic shift |
| Performance degrades for alternating bits | Pattern 101010... causes operation every cycle (worst case) |
| Arithmetic shift complexity | Right shift must preserve sign bit |
| Slightly higher power | Control overhead increases power consumption |

---

## 4. Wallace Tree Multiplier

### Purpose

The Wallace Tree improves multiplication speed by **reducing partial products in parallel** using a tree structure of compressors, achieving **logarithmic reduction depth**.

### Core Concept

Instead of accumulating partial products row by row (like Braun/Baugh-Wooley), the Wallace Tree:
1. Generates all $n^2$ partial products simultaneously
2. Uses a **tree of CSAs (3:2 counters) and 4:2 compressors** to reduce the partial products in parallel
3. Each level reduces the number of rows by approximately 1.5×
4. When only 2 rows remain, a final CPA produces the result

### 4:2 Compressors

![[wallace_tree_4x2_compressor.png]]

A 4:2 compressor takes **4 input bits** of the same weight (plus a carry-in) and produces **2 output bits** (sum and carry) plus carry-out:

The sum output:
![[wallace_sum_formula.png]]
![[wallace_sum_rearranged.png]]

### Speed Advantage

$$\boxed{\text{Number of reduction levels} = O(\log_{1.5} n) = O(\log n)}$$

Compared to array multipliers where the depth is $O(n)$, the Wallace Tree achieves **logarithmic reduction**, making it significantly faster for large operand sizes.

### Structure

![[wallace_tree_multiplier_diagram.png]]

The tree uses a combination of:
- **Full Adders** (3:2 counters): reduce 3 inputs to 2
- **Half Adders**: reduce 2 inputs to 2 (with carry to next column)
- **4:2 Compressors**: reduce 4 inputs to 2 (more regular structure)

### Advantages

![[wallace_advantages.png]]

| Advantage | Explanation |
|-----------|-------------|
| Very high speed | Logarithmic reduction depth ($O(\log n)$) |
| Efficient for large operands | Speed advantage grows with operand size |
| Parallel reduction | All column reductions happen simultaneously |
| CSA-based | No intermediate carry propagation |
| 4:2 compressors | More regular structure than pure 3:2 counters |
| Carry propagation only at final stage | CPA delay is the only sequential delay |

### Disadvantages

![[wallace_disadvantages.png]]

| Disadvantage | Explanation |
|--------------|-------------|
| Irregular layout | Tree structure is less regular than array multipliers |
| Complex wiring | Inter-level connections are difficult to route |
| Larger area | More hardware than sequential multipliers |
| Higher power | More parallel computation = more switching |
| Difficult to design | Layout and timing verification is challenging |
| Higher routing complexity | Wire lengths vary significantly across the tree |

---

## Comprehensive Multiplier Comparison

| Parameter | Braun | Baugh-Wooley | Booth | Wallace Tree |
|-----------|-------|--------------|-------|--------------|
| **Number type** | Unsigned only | Signed (2's comp) | Signed (2's comp) | Any (depends on PP generation) |
| **Structure** | Regular array | Regular array | Sequential | Irregular tree |
| **Complexity** | $O(n^2)$ | $O(n^2)$ | $O(n)$ (sequential) | $O(n^2)$ (gates), $O(\log n)$ (depth) |
| **Speed** | Moderate | Moderate | Moderate (sequential) | **Fastest** |
| **Area** | Large | Large | Small | **Largest** |
| **Power** | Moderate | Moderate | Low-Moderate | High |
| **Layout regularity** | Excellent | Excellent | N/A (sequential) | Poor (irregular) |
| **Final stage** | RCA | RCA | Accumulator | CPA (CLA/RCA) |
| **Best for** | Small unsigned | Signed multiplication | Area-constrained | High-speed, large operands |

---

## Common Mistakes

1. **Assuming Braun handles signed numbers**: Braun is unsigned only; use Baugh-Wooley for signed
2. **Forgetting Booth's worst case**: Alternating bits (101010) cause operations every cycle, negating the efficiency gain
3. **Confusing CSA in Braun with standalone CSA**: In Braun, CSA is used for partial product accumulation, not multi-operand addition
4. **Thinking Wallace Tree is always better**: For small operands (< 8 bits), the overhead of the tree structure isn't justified
5. **Q₋₁ initialization in Booth**: Q₋₁ must be initialized to 0, not to any other value
6. **Confusing 4:2 compressor with 4-bit adder**: A 4:2 compressor handles 4 bits of the SAME weight, not 4-bit numbers

---

## Self-Check Questions

**Q1**: How many AND gates are needed for partial product generation in an 8×8 Braun multiplier?
> $n^2 = 8^2 = 64$ AND gates

**Q2**: Why does the Baugh-Wooley multiplier transform negative partial products?
> To avoid using subtractor cells. By converting negative terms into positive terms with correction constants, the entire array uses only adders, maintaining regularity.

**Q3**: In the Booth algorithm, what happens when Q₀Q₋₁ = 10?
> Subtract: A ← A - M (subtract the multiplicand from the accumulator). This corresponds to the start of a run of 1's.

**Q4**: What makes the Wallace Tree faster than the Braun multiplier?
> The Wallace Tree reduces partial products in parallel with logarithmic depth ($O(\log n)$), while Braun has linear depth ($O(n)$). The tree compresses multiple rows simultaneously using 3:2 and 4:2 counters.

**Q5**: For a 6×6 multiplier, how many bits is the product?
> $2n = 12$ bits

---

## Concept Links

- RCA used in Braun's final stage: [09_standard_adder_cells_and_rca.md](./09_standard_adder_cells_and_rca.md)
- CSA concept used in carry-save arrays: [10_cla_csl_hybrid_csa.md](./10_cla_csl_hybrid_csa.md#carry-save-adder-csa)
- Low-voltage logic styles for multiplier cells: [11_low_voltage_logic_styles.md](./11_low_voltage_logic_styles.md)
- Worked problems with multiplier examples: [13_worked_problems.md](./13_worked_problems.md)
- All multiplier formulas: [14_formula_sheet_ultimate.md](./14_formula_sheet_ultimate.md#multiplier-equations)
