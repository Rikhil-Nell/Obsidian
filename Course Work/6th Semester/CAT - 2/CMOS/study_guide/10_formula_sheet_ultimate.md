# Ultimate Formula Sheet - Low Power VLSI Design (CMOS)

Quick reference for ALL formulas, constants, and key relationships. Organized by topic with anchor links.

---

## SPICE Model Equations

### LEVEL 1 (Square-Law)

| Region | Condition | Current $I_D$ |
|--------|-----------|----------------|
| Cutoff | $V_{GS} < V_T$ | $0$ |
| Linear | $V_{GS} > V_T$, $V_{DS} < V_{GS} - V_T$ | $k_n[(V_{GS}-V_T)V_{DS} - V_{DS}^2/2]$ |
| Saturation | $V_{GS} > V_T$, $V_{DS} \geq V_{GS} - V_T$ | $\frac{k_n}{2}(V_{GS}-V_T)^2(1+\lambda V_{DS})$ |

$$k_n = \mu_n C_{ox} (W/L), \quad k_p = \mu_p C_{ox} (W/L)$$

---

## CMOS Inverter VTC

### Voltage Relationships

$$V_{GS,n} = V_{in}, \quad V_{DS,n} = V_{out}$$
$$V_{GS,p} = V_{in} - V_{DD}, \quad V_{DS,p} = V_{out} - V_{DD}$$

### Output Levels

$$V_{OH} = V_{DD}, \quad V_{OL} = 0$$

### 5 VTC Regions

| Region | NMOS | PMOS | $V_{out}$ |
|--------|------|------|-----------|
| A | Cutoff | Linear | $V_{DD}$ |
| B | Sat | Linear | Dropping |
| C | Sat | Sat | Transition |
| D | Linear | Sat | Rising to 0 |
| E | Linear | Cutoff | $0$ |

### Switching Threshold

$$\boxed{V_M = \frac{V_{DD} - |V_{T,p}| + V_{T,n}\sqrt{r}}{1 + \sqrt{r}}}$$

where $r = k_p V_{DSAT,p} / (k_n V_{DSAT,n})$

Simplified (large $V_{DD}$): $V_M \approx V_{DD}/(1 + \sqrt{r})$

---

## Noise Margins

$$\boxed{NM_L = V_{IL} - V_{OL} = V_{IL}}$$
$$\boxed{NM_H = V_{OH} - V_{IH} = V_{DD} - V_{IH}}$$

---

## VIL, VIH Derivations

### $V_{IL}$ (NMOS sat, PMOS linear)

$$V_{IL} = \frac{2V_{out} + V_{T,n} - V_{DD} + k_R(V_{DD} + V_{T,p})}{1 + k_R}$$

### $V_{IH}$ (NMOS linear, PMOS sat)

$$V_{IH} = \frac{2V_{out} + V_{T,n} + (V_{DD} + V_{T,p})/k_R}{1 + 1/k_R}$$

### Inverter Threshold $V_{th}$

$$\boxed{V_{th} = \frac{V_{T,n} + \sqrt{k_R}(V_{DD} + V_{T,p})}{1 + \sqrt{k_R}}}$$

### Design Equation: $k_R$ from Desired $V_{th}$

$$\boxed{k_R = \left(\frac{V_{th} - V_{T,n}}{V_{DD} + V_{T,p} - V_{th}}\right)^2}$$

### Symmetric Inverter

If $V_{T,n} = |V_{T,p}|$: $k_R = 1 \Rightarrow (W/L)_p/(W/L)_n = \mu_n/\mu_p \approx 2.5$

---

## Power Dissipation

### Dynamic (Switching) Power

$$\boxed{P_{dynamic} = C_L \cdot V_{DD}^2 \cdot f_{sw} = \alpha \cdot C_L \cdot V_{DD}^2 \cdot f_{clock}}$$

- $\alpha$ = activity factor (0.1 -- 0.3 typical)
- Energy per cycle: $E = C_L V_{DD}^2$

### Short-Circuit Power

$$P_{sc} = I_{sc} \cdot V_{DD}$$

### Static (Leakage) Power

$$P_{static} = I_{leak} \cdot V_{DD}$$

### Total Power

$$\boxed{P_{total} = P_{dynamic} + P_{sc} + P_{static}}$$

### Minimum $V_{DD}$

$$V_{DD,min} = V_{T,n} + |V_{T,p}|$$

---

## Propagation Delay

### Load Capacitance

$$\boxed{C_L = C_{gd,12} + C_{db,n} + C_{db,p} + C_w + C_g(\text{fanout})}$$

Gate-drain (Miller): $C_{gd} = 2 C_{GD0} W$

Junction: $C_{db} = K_{eq} \cdot C_{j0}$

### First-Order RC Delay

$$\boxed{t_{pHL} = 0.69 \cdot R_{eq,n} \cdot C_L}$$
$$\boxed{t_{pLH} = 0.69 \cdot R_{eq,p} \cdot C_L}$$
$$\boxed{t_p = \frac{t_{pHL} + t_{pLH}}{2} = 0.69 \cdot C_L \cdot \frac{R_{eq,n} + R_{eq,p}}{2}}$$

### Rise/Fall Time

$$t_r: V_{10\%} \text{ to } V_{90\%}, \quad t_f: V_{90\%} \text{ to } V_{10\%}$$

### Equivalent Resistance

$$R_{eq,n} \approx \frac{1}{k_n(V_{DD} - V_{T,n} - V_{DSAT,n}/2)}$$

---

## NMOS/PMOS Sizing

### Optimal Beta for Minimum Delay

$$\boxed{\beta_{opt} = \sqrt{r}} \quad (r = R_{eq,p}/R_{eq,n} \approx 2)$$
$$\beta_{opt} \approx 1.4$$

### Sizing with Factor $S$

$$R_{eq} = R_{ref}/S, \quad C_{int} = S \cdot C_{i,ref}$$
$$t_p = t_{p0}(1 + C_{ext}/(S \cdot C_{i,ref}))$$

---

## Inverter Chain Optimization

### Per-Stage Delay

$$t_{p,j} = t_{p0}(1 + f_j/\gamma)$$

where $f_j = C_{g,j+1}/C_{g,j}$ = stage fanout, $\gamma = C_{int}/C_g$

### Optimal Sizing (Geometric Mean)

$$\boxed{C_{g,j} = \sqrt{C_{g,j-1} \cdot C_{g,j+1}}}$$

### Equal Fanout Per Stage

$$\boxed{f = \sqrt[N]{F} = (C_L/C_{g1})^{1/N}}$$

### Minimum Total Delay

$$\boxed{t_p = N \cdot t_{p0}(1 + \sqrt[N]{F}/\gamma)}$$

### Optimal Fanout

$$\boxed{f_{opt} \approx 4 \quad (\text{fanout-of-4 rule})}$$

### Optimal Number of Stages

$$N_{opt} = \log_{f_{opt}}(F) = \frac{\ln(F)}{\ln(4)}$$

---

## Timing Concepts

| Term | Symbol | Definition |
|------|--------|------------|
| Propagation delay | $t_{pd}$ | Max time, input 50% to output 50% |
| Contamination delay | $t_{cd}$ | Min time, input 50% to output 50% |
| Slack | -- | Required time - Arrival time |
| Critical path | -- | Path with smallest slack |
| Setup time | $t_{setup}$ | Data stable before clock edge |
| Hold time | $t_{hold}$ | Data stable after clock edge |

---

## RC Transistor Model

### Unit Transistor (Width $= 1$)

| Parameter | NMOS | PMOS (width 2) |
|-----------|------|-----------------|
| Resistance | $R$ | $R$ (2x wider to compensate) |
| Gate cap | $C$ | $2C$ |
| Diffusion cap | $C$ | $2C$ |
| Total input cap | -- | -- |

**Unit inverter input capacitance:** $3C$ (= $C + 2C$)

### Width $k$ Transistor

$$R_{channel} = R/k, \quad C_{gate} = kC, \quad C_{diff} = kC$$

### Diffusion Capacitance

$$C_s = A_S \times C_{jbs} + P_S \times C_{jbsw}$$

---

## Elmore Delay

$$\boxed{t_{pd} = \sum_{i} C_i \cdot R_{i \rightarrow s}}$$

$R_{i \rightarrow s}$ = resistance on shared path from source to both node $i$ and leaf

### Key Results

| Configuration | Delay |
|---------------|-------|
| Inverter driving $m$ gates | $(3 + 3m)RC$ |
| Width-$w$ driving $m$ gates | $(3 + 3m/w)RC = (3 + 3h)RC$ |
| FO4 inverter | $15RC$ |
| 3-NAND falling | $(12 + 5h)RC$ |
| 3-NAND rising | $(15 + 5h)RC$ |

---

## Logical Effort

### Linear Delay Model

$$\boxed{d = g \cdot h + p}$$

- $g$ = logical effort, $h$ = electrical effort ($C_{out}/C_{in}$), $p$ = parasitic delay

### Logical Effort Values

| Gate | $g$ | $p$ |
|------|-----|-----|
| INV | $1$ | $1$ |
| NAND2 | $4/3$ | $2$ |
| NAND3 | $5/3$ | $3$ |
| NAND4 | $2$ | $4$ |
| NOR2 | $5/3$ | $2$ |
| NOR3 | $7/3$ | $3$ |
| NOR4 | $3$ | $4$ |

### General Formulas

$$g_{NAND,n} = \frac{n+2}{3}, \quad g_{NOR,n} = \frac{2n+1}{3}, \quad p_n \approx n$$

### Multi-Stage Path

$$G = \prod g_i, \quad H = C_{out}/C_{in}, \quad B = \prod b_i$$
$$\boxed{F = G \cdot H \cdot B}$$
$$\hat{f} = F^{1/N}$$
$$\boxed{D_{min} = N \cdot F^{1/N} + P}$$

### Optimal Stage Effort

$$\boxed{\hat{f}_{opt} \approx 3.6}$$

### Gate Sizing (Backward)

$$C_{in,i} = \frac{C_{out,i} \cdot g_i}{\hat{f}}$$

### Branching Effort

$$b = \frac{C_{on-path} + C_{off-path}}{C_{on-path}}$$

---

## Physical Constants & Typical Values

| Parameter | Symbol | Typical Value |
|-----------|--------|---------------|
| Electron mobility (Si) | $\mu_n$ | $500-700 \text{ cm}^2/\text{V}\cdot\text{s}$ |
| Hole mobility (Si) | $\mu_p$ | $200-300 \text{ cm}^2/\text{V}\cdot\text{s}$ |
| Mobility ratio | $\mu_n/\mu_p$ | $\approx 2 - 2.5$ |
| Thermal voltage (300K) | $V_T$ | $26 \text{ mV}$ |
| Gate oxide capacitance | $C_{ox}$ | Technology-dependent |
| PMOS/NMOS resistance ratio | $r$ | $\approx 2$ |
| Optimal beta | $\beta_{opt}$ | $\sqrt{r} \approx 1.4$ |
| Optimal fanout | $f_{opt}$ | $\approx 4$ |
| FO4 delay (65nm) | -- | $15 \text{ ps}$ |
| Activity factor | $\alpha$ | $0.1 - 0.3$ |

---

## Pre-Exam Checklist

- [ ] Can I sketch a CMOS inverter and explain how it works?
- [ ] Can I draw the VTC and label all 5 regions with transistor states?
- [ ] Can I calculate $V_M$ given device parameters?
- [ ] Can I compute noise margins from $V_{IL}$ and $V_{IH}$?
- [ ] Can I design an inverter for a desired $V_{th}$ using $k_R$?
- [ ] Can I calculate dynamic power $P = CV_{DD}^2 f$?
- [ ] Can I identify all components of load capacitance?
- [ ] Can I compute propagation delay using $t_p = 0.69 RC$?
- [ ] Can I find $\beta_{opt}$ for minimum delay?
- [ ] Can I size an inverter chain using the fanout-of-4 rule?
- [ ] Can I compute Elmore delay for an RC tree?
- [ ] Can I find the logical effort of NAND and NOR gates?
- [ ] Can I use $d = gh + p$ for single-stage delay?
- [ ] Can I compute minimum delay for a multi-stage path?
- [ ] Can I work backward to find gate sizes from path effort?
