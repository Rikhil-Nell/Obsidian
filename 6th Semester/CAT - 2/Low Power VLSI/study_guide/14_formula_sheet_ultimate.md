# 14 - Ultimate Formula Sheet

---

## Clock Distribution Power

$$\boxed{P_{clock} = C_L \cdot V_{DD}^2 \cdot f}$$

$$C_L = C_d + N \cdot C_g + \alpha \cdot C_w \cdot D$$

| Symbol | Meaning |
|--------|---------|
| $C_L$ | Total clock load capacitance |
| $C_d$ | Clock driver capacitance |
| $N$ | Number of clock terminals |
| $C_g$ | Input capacitance per terminal |
| $\alpha$ | Routing estimation factor |
| $C_w$ | Wire capacitance per unit length |
| $D$ | Chip dimension |

$$t_{skew} = t_{CLK@FF2} - t_{CLK@FF1}$$

---

## Dynamic Power

$$\boxed{P_{dynamic} = \alpha \cdot C_L \cdot V_{DD}^2 \cdot f}$$

| Symbol | Meaning |
|--------|---------|
| $\alpha$ | Switching activity factor (0 to 1) |
| $C_L$ | Load capacitance |
| $V_{DD}$ | Supply voltage |
| $f$ | Clock frequency |

---

## Static Power

$$\boxed{P_{static} = V_{DD} \cdot I_{leakage}}$$

---

## Timing Constraints

### Setup Time

$$\boxed{t_{clk-to-q} + t_{comb} + t_{su} \leq T_{clk} + t_{skew}}$$

### Hold Time

$$\boxed{t_{clk-to-q} + t_{comb} \geq t_h + t_{skew}}$$

| Key Difference | Setup | Hold |
|----------------|-------|------|
| Depends on frequency? | Yes ($T_{clk}$) | No |
| Worsened by | Negative skew | Positive skew |

---

## Transistor Counts

| Logic Style | Transistor Count |
|-------------|------------------|
| Full CMOS (N-input) | $2N$ |
| Pseudo-NMOS (N-input) | $N + 1$ |
| Dynamic Logic (N-input) | $N + 2$ |
| Domino Logic (N-input) | $N + 3$ (includes inverter) |

---

## Pass Transistor Logic

$$\boxed{V_{OH,NMOS} = V_{DD} - V_{Tn}} \quad \text{(weak '1')}$$

$$\boxed{V_{OL,PMOS} = |V_{Tp}|} \quad \text{(weak '0')}$$

| Transistor | Strong | Weak |
|-----------|--------|------|
| NMOS | '0' (GND) | '1' ($V_{DD} - V_{Tn}$) |
| PMOS | '1' ($V_{DD}$) | '0' ($|V_{Tp}|$) |

---

## Leakage Currents

### Subthreshold Leakage

$$\boxed{I_{sub} = I_0 \cdot e^{\frac{V_{GS} - V_{th}}{n \cdot V_T}} \cdot \left(1 - e^{-V_{DS}/V_T}\right)}$$

### Subthreshold Swing

$$\boxed{S = n \cdot \frac{kT}{q} \cdot \ln 10 \approx n \times 60 \text{ mV/decade (at 300K)}}$$

Ideal minimum: $S = 60$ mV/decade (when $n = 1$)

### Thermal Voltage

$$V_T = \frac{kT}{q} \approx 26 \text{ mV at 300K}$$

### DIBL Effect on Threshold

$$\boxed{V_{th,eff} = V_{th0} - \eta \cdot V_{DS}}$$

### Punchthrough Voltage

$$\boxed{V_{PT} = \frac{q \cdot N_B \cdot (L - W_j)^2}{2 \cdot \epsilon_s}}$$

### Hot Carrier Barrier Heights

| Carrier | Barrier Height (Si-SiO₂) |
|---------|--------------------------|
| Electrons | 3.1 eV |
| Holes | 4.5 eV |

---

## Adder Equations

### Half Adder

$$Sum = A \oplus B$$
$$C_{out} = A \cdot B$$

### Full Adder

$$\boxed{Sum = A \oplus B \oplus C_{in}}$$
$$\boxed{C_{out} = (A \oplus B) \cdot C_{in} + A \cdot B}$$

### CLA: Propagate and Generate

$$\boxed{P_i = A_i \oplus B_i}$$
$$\boxed{G_i = A_i \cdot B_i}$$
$$\boxed{S_i = P_i \oplus C_i}$$
$$\boxed{C_{i+1} = G_i + P_i \cdot C_i}$$

### Expanded Carries (4-bit CLA)

$$C_1 = G_0 + P_0 C_0$$
$$C_2 = G_1 + P_1 G_0 + P_1 P_0 C_0$$
$$C_3 = G_2 + P_2 G_1 + P_2 P_1 G_0 + P_2 P_1 P_0 C_0$$
$$C_4 = G_3 + P_3 G_2 + P_3 P_2 G_1 + P_3 P_2 P_1 G_0 + P_3 P_2 P_1 P_0 C_0$$

### CSA Delay (k operands)

$$\boxed{T = (k - 2) \cdot T_{CSA} + T_{CPA}}$$

### Adder Delay Comparison

| Architecture | Delay |
|-------------|-------|
| RCA | $O(n)$ |
| CLA | $O(\log n)$ |
| CSL | $O(\sqrt{n})$ |
| CSA (k operands) | $(k-2) \cdot T_{FA} + T_{CPA}$ |

---

## Multiplier Equations

### Unsigned Multiplication Product

$$\boxed{P = X \times Y = \sum_{i=0}^{n-1} \sum_{j=0}^{n-1} x_i \cdot y_j \cdot 2^{i+j}}$$

Product bit width: $2n$ bits for $n \times n$ multiplication

### Braun Multiplier Complexity

| Component | Count |
|-----------|-------|
| AND gates | $n^2$ |
| CSA Full Adders | $(n-1)^2$ |
| Final RCA FAs | $n - 1$ |

### Booth Encoding Table

| $Q_i$ | $Q_{i-1}$ | Action |
|--------|-----------|--------|
| 0 | 0 | No operation |
| 0 | 1 | A ← A + M |
| 1 | 0 | A ← A - M |
| 1 | 1 | No operation |

### Wallace Tree Reduction Levels

$$\boxed{\text{Levels} \approx \lceil \log_{1.5} n \rceil = O(\log n)}$$

### Multiplier Delay Comparison

| Architecture | Delay Order |
|-------------|-------------|
| Braun (array) | $O(n)$ |
| Baugh-Wooley | $O(n)$ |
| Booth | $O(n)$ (sequential iterations) |
| Wallace Tree | $O(\log n)$ + CPA |

---

## Deep Submicrometer Quick Reference

| DSM Challenge | Mitigation Technique |
|---------------|---------------------|
| Short-channel effects | Halo doping, retrograde wells |
| Gate oxide tunneling | High-k dielectrics (HfO₂) |
| Leakage current | Multi-threshold CMOS, power gating |
| Mobility degradation | Strain engineering |
| Loss of gate control | Multi-gate devices (FinFETs) |
| Junction leakage | SOI technology |

---

## Logic Style Comparison (XOR Gates)

| Logic Style | Type | Transistors | Voltage Swing | CPL Fix |
|-------------|------|-------------|---------------|---------|
| Full Static CMOS | Static | 12 | Full | N/A |
| CPL | Static | ~6 | $V_{DD} - V_{th}$ | pMOS feedback |
| DPL | Static | ~8 | Full | Both nMOS+pMOS |
| Dual-Rail Domino | Dynamic | ~10 | Full | N/A |
| Single-Rail Domino | Dynamic | ~6 | Full | N/A (NOR only) |

---

## Key Constants

| Constant | Value |
|----------|-------|
| $kT/q$ at 300K | 26 mV |
| $\ln 10$ | 2.303 |
| Silicon $E_g$ | 1.12 eV |
| Electron barrier (Si-SiO₂) | 3.1 eV |
| Hole barrier (Si-SiO₂) | 4.5 eV |
| Electron mobility (Si) | ~1350 cm²/V·s |
| Hole mobility (Si) | ~480 cm²/V·s |
| $\epsilon_{Si}$ | $1.04 \times 10^{-12}$ F/cm |
| $q$ | $1.6 \times 10^{-19}$ C |
