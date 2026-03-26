# 13 - Worked Problems

---

## Problem Set A: Clocking and Power

### A1. Clock Distribution Power Calculation

**Problem**: A processor chip has the following clock distribution parameters:
- Clock driver capacitance: $C_d = 30$ pF
- Number of clock terminals: $N = 8000$
- Input capacitance per terminal: $C_g = 15$ fF
- Estimation factor: $\alpha = 1.2$
- Wire capacitance per mm: $C_w = 0.5$ pF/mm
- Chip dimension: $D = 10$ mm
- Supply voltage: $V_{DD} = 1.2$ V
- Clock frequency: $f = 2$ GHz

Calculate: (a) Total clock load capacitance (b) Dynamic power consumed by the clock

**Solution**:

(a) Total capacitance:
$$C_L = C_d + N \cdot C_g + \alpha \cdot C_w \cdot D$$
$$C_L = 30 + 8000 \times 0.015 + 1.2 \times 0.5 \times 10$$
$$C_L = 30 + 120 + 6 = 156 \text{ pF}$$

(b) Clock power:
$$P_{clock} = C_L \cdot V_{DD}^2 \cdot f$$
$$P_{clock} = 156 \times 10^{-12} \times (1.2)^2 \times 2 \times 10^9$$
$$\boxed{P_{clock} = 156 \times 10^{-12} \times 1.44 \times 2 \times 10^9 = 0.449 \text{ W}}$$

---

### A2. Setup Time Violation Check

**Problem**: In a synchronous system:
- Clock period: $T_{clk} = 5$ ns
- Clock-to-Q delay: $t_{clk-to-q} = 0.3$ ns
- Combinational delay: $t_{comb} = 4.0$ ns
- Setup time: $t_{su} = 0.5$ ns
- Clock skew: $t_{skew} = +0.2$ ns (positive)

Is there a setup violation?

**Solution**:

Setup time constraint: $t_{clk-to-q} + t_{comb} + t_{su} \leq T_{clk} + t_{skew}$

$$0.3 + 4.0 + 0.5 \leq 5.0 + 0.2$$
$$4.8 \leq 5.2$$

$$\boxed{\text{No setup violation — constraint is satisfied with 0.4 ns slack}}$$

---

### A3. Hold Time Violation Check

**Problem**: Using the same system from A2, check for hold violation if:
- Hold time: $t_h = 0.2$ ns
- Minimum combinational delay: $t_{comb,min} = 0.1$ ns

**Solution**:

Hold time constraint: $t_{clk-to-q} + t_{comb,min} \geq t_h + t_{skew}$

$$0.3 + 0.1 \geq 0.2 + 0.2$$
$$0.4 \geq 0.4$$

$$\boxed{\text{Marginal — exactly at the boundary. Any additional positive skew would cause a hold violation.}}$$

---

## Problem Set B: Transistor Counts and Logic Styles

### B1. Transistor Count Comparison

**Problem**: Calculate the transistor count for a 5-input NOR gate using: (a) Full CMOS, (b) Pseudo-NMOS, (c) Dynamic logic

**Solution**:

(a) Full CMOS: $2N = 2 \times 5 = \boxed{10 \text{ transistors}}$
- PDN: 5 NMOS in parallel
- PUN: 5 PMOS in series

(b) Pseudo-NMOS: $N + 1 = 5 + 1 = \boxed{6 \text{ transistors}}$
- PDN: 5 NMOS in parallel
- Load: 1 always-ON PMOS

(c) Dynamic logic: $N + 2 = 5 + 2 = \boxed{7 \text{ transistors}}$
- PDN: 5 NMOS in parallel
- Precharge PMOS: 1
- Evaluation NMOS: 1

---

### B2. Pass Transistor Voltage Level

**Problem**: An NMOS pass transistor has $V_{DD} = 1.8$ V and $V_{Tn} = 0.5$ V. If it passes logic '1', what is the output voltage? If $V_{DD}$ is then reduced to 1.0 V, what happens?

**Solution**:

At $V_{DD} = 1.8$ V:
$$V_{OH} = V_{DD} - V_{Tn} = 1.8 - 0.5 = \boxed{1.3 \text{ V (weak '1')}}$$

At $V_{DD} = 1.0$ V:
$$V_{OH} = V_{DD} - V_{Tn} = 1.0 - 0.5 = \boxed{0.5 \text{ V}}$$

**Analysis**: At low $V_{DD}$, the degradation becomes severe (50% of $V_{DD}$), making CPL unreliable at low voltages. This motivates DPL which provides full swing.

---

## Problem Set C: Leakage

### C1. Subthreshold Swing Calculation

**Problem**: At room temperature ($T = 300$ K), calculate the subthreshold swing for:
(a) Ideal case ($n = 1$)
(b) $n = 1.3$

**Solution**:

$$S = n \cdot \frac{kT}{q} \cdot \ln 10$$

At $T = 300$ K: $kT/q = 26$ mV, and $\ln 10 = 2.303$

(a) Ideal: $S = 1.0 \times 26 \times 2.303 = \boxed{59.9 \approx 60 \text{ mV/decade}}$

(b) $n = 1.3$: $S = 1.3 \times 26 \times 2.303 = \boxed{77.8 \text{ mV/decade}}$

---

### C2. Punchthrough Voltage

**Problem**: Calculate the punchthrough voltage for an NMOS transistor with:
- $N_B = 5 \times 10^{17}$ cm$^{-3}$
- $L = 0.05$ µm = $50$ nm
- $W_j = 0.015$ µm
- $\epsilon_s = 1.04 \times 10^{-12}$ F/cm

**Solution**:

$$V_{PT} = \frac{q \cdot N_B \cdot (L - W_j)^2}{2 \cdot \epsilon_s}$$

$$V_{PT} = \frac{1.6 \times 10^{-19} \times 5 \times 10^{17} \times (0.035 \times 10^{-4})^2}{2 \times 1.04 \times 10^{-12}}$$

$$= \frac{8 \times 10^{-2} \times 1.225 \times 10^{-11}}{2.08 \times 10^{-12}}$$

$$\boxed{V_{PT} = \frac{9.8 \times 10^{-13}}{2.08 \times 10^{-12}} = 0.471 \text{ V}}$$

At 50 nm channel length, punchthrough occurs at less than 0.5 V — confirming why nanometer devices are extremely susceptible.

---

## Problem Set D: Adders

### D1. CLA Carry Expansion

**Problem**: Given a 4-bit CLA with inputs $A = 1011$ and $B = 0110$ and $C_0 = 1$, compute all carries and sum bits using P/G signals.

**Solution**:

**Step 1**: Compute P and G:

| Bit $i$ | $A_i$ | $B_i$ | $P_i = A_i \oplus B_i$ | $G_i = A_i \cdot B_i$ |
|---------|-------|-------|------------------------|----------------------|
| 0 | 1 | 0 | 1 | 0 |
| 1 | 1 | 1 | 0 | 1 |
| 2 | 0 | 1 | 1 | 0 |
| 3 | 1 | 0 | 1 | 0 |

**Step 2**: Compute carries:

$C_1 = G_0 + P_0 \cdot C_0 = 0 + 1 \cdot 1 = 1$

$C_2 = G_1 + P_1 \cdot C_1 = 1 + 0 \cdot 1 = 1$

$C_3 = G_2 + P_2 \cdot C_2 = 0 + 1 \cdot 1 = 1$

$C_4 = G_3 + P_3 \cdot C_3 = 0 + 1 \cdot 1 = 1$

**Step 3**: Compute sums:

$S_0 = P_0 \oplus C_0 = 1 \oplus 1 = 0$
$S_1 = P_1 \oplus C_1 = 0 \oplus 1 = 1$
$S_2 = P_2 \oplus C_2 = 1 \oplus 1 = 0$
$S_3 = P_3 \oplus C_3 = 1 \oplus 1 = 0$

$$\boxed{A + B + C_0 = 1011 + 0110 + 1 = \mathbf{10010} \quad (S = 0010, C_4 = 1)}$$

**Verification**: $11 + 6 + 1 = 18 = 10010_2$ ✓

---

### D2. CSA Multi-Operand Delay

**Problem**: Calculate the total delay for adding 8 operands using CSA levels followed by a 16-bit CLA as the final CPA.

**Solution**:

$$T = (k - 2) \cdot T_{CSA} + T_{CPA}$$
$$T = (8 - 2) \cdot T_{CSA} + T_{CLA}$$
$$\boxed{T = 6 \cdot T_{FA} + O(\log_2 16) = 6 \cdot T_{FA} + 4 \cdot T_{gate}}$$

If $T_{FA} \approx 2 \cdot T_{gate}$:
$$T \approx 12 \cdot T_{gate} + 4 \cdot T_{gate} = 16 \cdot T_{gate}$$

Compared to adding 8 operands sequentially with 7 separate 16-bit CLA additions: $7 \times 4 \cdot T_{gate} = 28 \cdot T_{gate}$

**Speedup**: $28/16 = 1.75\times$ faster

---

## Problem Set E: Multipliers

### E1. Booth Multiplication

**Problem**: Multiply $M = +7$ (multiplicand) by $Q = -3$ (multiplier) using Booth's algorithm with 4-bit representation.

**Solution**:

$M = +7 = 0111$, $-M = 1001$ (2's complement)
$Q = -3 = 1101$ (2's complement)

Initial: A = 0000, Q = 1101, Q₋₁ = 0, Counter = 4

| Step | A | Q | Q₋₁ | Q₀Q₋₁ | Action |
|------|---|---|-----|--------|--------|
| Init | 0000 | 1101 | 0 | 10 | A = A - M = 0000 + 1001 = 1001 |
| After op | 1001 | 1101 | 0 | - | Arithmetic right shift |
| Shift 1 | 1100 | 1110 | 1 | 01 | A = A + M = 1100 + 0111 = 0011 |
| After op | 0011 | 1110 | 1 | - | Arithmetic right shift |
| Shift 2 | 0001 | 1111 | 0 | 10 | A = A - M = 0001 + 1001 = 1010 |
| After op | 1010 | 1111 | 0 | - | Arithmetic right shift |
| Shift 3 | 1101 | 0111 | 1 | 11 | No operation |
| Shift 4 | 1110 | 1011 | 1 | - | Counter = 0, done |

Product = [A, Q] = 1110 1011

Converting: 11101011₂ = $-21$ (in 8-bit 2's complement)

$$\boxed{7 \times (-3) = -21} \quad \checkmark$$

---

### E2. Braun Multiplier Size

**Problem**: For a 6×6 Braun multiplier, calculate:
(a) Number of AND gates
(b) Number of Full Adders in the carry-save array
(c) Number of Full Adders in the final RCA
(d) Total number of product bits

**Solution**:

(a) AND gates: $n^2 = 6^2 = \boxed{36}$

(b) CSA array FAs: $(n-1) \times (n-1) = 5 \times 5 = \boxed{25}$

(c) Final RCA FAs: $n - 1 = 6 - 1 = \boxed{5}$

(d) Product bits: $2n = 2 \times 6 = \boxed{12 \text{ bits}}$

**Total FAs**: $25 + 5 = 30$ Full Adders

---

### E3. Wallace Tree Reduction Levels

**Problem**: How many reduction levels does a Wallace Tree multiplier need for an 8×8 multiplication?

**Solution**:

An 8×8 multiplier generates 8 rows of partial products.

Each CSA level reduces the row count by approximately $\frac{2}{3}$:

| Level | Input Rows | Output Rows |
|-------|------------|-------------|
| 1 | 8 | 6 (8 groups of 3 → 6 groups of 2, remaining rows pass through) |
| 2 | 6 | 4 |
| 3 | 4 | 3 |
| 4 | 3 | 2 |

$$\boxed{4 \text{ reduction levels} + \text{final CPA}}$$

The formula: $\lceil \log_{1.5} 8 \rceil = \lceil 5.13 \rceil \approx 4$ levels

---

## Problem Set F: Comparison Questions (Exam Format)

### F1. Compare Any Two Adder Architectures

**Question**: Compare RCA and CLA adders with respect to (a) delay, (b) hardware complexity, (c) power consumption, (d) area, and (e) applications.

| Parameter | RCA | CLA |
|-----------|-----|-----|
| **Delay** | $O(n)$ — linear with bit width | $O(\log n)$ — logarithmic |
| **Hardware** | Minimal — just n FAs | Complex — P/G logic + carry computation |
| **Power** | Low — fewer gates | Higher — more gates, more switching |
| **Area** | Smallest adder architecture | Larger due to additional logic |
| **Applications** | Small adders (4-8 bits), low-power | High-speed processors, ALUs |

---

### F2. Compare Any Two Multiplier Architectures

**Question**: Compare Braun and Wallace Tree multipliers.

| Parameter | Braun | Wallace Tree |
|-----------|-------|--------------|
| **Number handling** | Unsigned only | Any (depends on PP generation) |
| **Structure** | Regular array | Irregular tree |
| **Delay** | $O(n)$ — linear | $O(\log n)$ — logarithmic |
| **Area** | $O(n^2)$ | $O(n^2)$ but with more overhead |
| **Layout** | Excellent regularity | Poor (complex wiring) |
| **Speed** | Moderate | Very high |
| **Power** | Moderate | Higher |
| **Best for** | Small unsigned multiplication | Large, high-speed multiplication |

---

## Exam Strategy Tips

1. **Always show working**: Even for simple calculations, show the formula first
2. **Draw truth tables when comparing**: Side-by-side tables score well
3. **Include 4-5 comparison points**: Speed, area, power, complexity, applications
4. **State the formula before substituting**: Examiners give marks for correct formulae
5. **For Booth problems**: Always show the register contents at each step in a clear table
6. **For CLA problems**: Always compute P and G first, then expand carries
