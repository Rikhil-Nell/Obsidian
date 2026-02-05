# Formula Sheet - Low Power VLSI Design

## Quick Reference Card

| Concept | Formula |
|---------|---------|
| **Switching Power** | $P_{sw} = \alpha f C_L V_{DD}^2$ |
| **Total Power** | $P = P_{sw} + P_{sc} + P_{leak}$ |
| **Subthreshold Current** | $I_{sub} = I_0 \cdot e^{(V_{GS}-V_T)/mV_t}$ |
| **PDP** | $PDP = P \cdot t_p$ |
| **EDP** | $EDP = PDP \cdot t_p = P \cdot t_p^2$ |

---

## Physical Constants

| Constant | Symbol | Value |
|----------|--------|-------|
| Elementary charge | $q$ | $1.6 \times 10^{-19}$ C |
| Boltzmann constant | $k$ | $1.38 \times 10^{-23}$ J/K |
| Thermal voltage (300K) | $V_t = kT/q$ | 26 mV |
| Permittivity of silicon | $\epsilon_{Si}$ | $1.04 \times 10^{-12}$ F/cm |
| Permittivity of SiO₂ | $\epsilon_{ox}$ | $3.45 \times 10^{-13}$ F/cm |
| Intrinsic carrier conc. (300K) | $n_i$ | $1.5 \times 10^{10}$ cm⁻³ |

---

## Power Equations

### Total Power Dissipation

$$\boxed{P_{total} = P_{switching} + P_{short-circuit} + P_{leakage}}$$

$$P = \alpha f C_L V_{DD}^2 + V_{DD} I_{peak}(P_{01} + P_{10}) + V_{DD} I_{leak}$$

---

### Switching Power

$$\boxed{P_{sw} = \alpha f C_L V_{DD}^2}$$

| Parameter | Description | Units |
|-----------|-------------|-------|
| $\alpha$ | Switching activity factor | dimensionless |
| $f$ | Clock frequency | Hz |
| $C_L$ | Load capacitance | F |
| $V_{DD}$ | Supply voltage | V |

#### Energy per Switching Event

$$E_{cycle} = C_L V_{DD}^2$$

#### Energy stored/dissipated per transition

$$E_{stored} = E_{dissipated} = \frac{1}{2} C_L V_{DD}^2$$

---

### Switching Activity

$$\boxed{\alpha = P_{0 \to 1} = P_0 \cdot P_1}$$

| Gate | $P_1$ | $P_0$ | $\alpha$ |
|------|-------|-------|----------|
| Inverter | 1/2 | 1/2 | 1/4 |
| AND2 | 1/4 | 3/4 | 3/16 |
| OR2 | 3/4 | 1/4 | 3/16 |
| NAND2 | 3/4 | 1/4 | 3/16 |
| NOR2 | 1/4 | 3/4 | 3/16 |
| XOR2 | 1/2 | 1/2 | 1/4 |
| AND3 | 1/8 | 7/8 | 7/64 |

---

### Short-Circuit Power

$$P_{sc} \propto k \cdot \tau \cdot (V_{DD} - 2V_T)^3$$

| Factor | Effect |
|--------|--------|
| Rise/fall time ($\tau$) | Linear |
| Transconductance ($k$) | Linear |
| Voltage margin | Cubic |

---

## Leakage Currents

### Total Leakage Power

$$\boxed{P_{leak} = V_{DD} \cdot I_{leak}}$$

### Subthreshold Leakage (Dominant)

$$\boxed{I_{sub} = I_0 \cdot e^{\frac{V_{GS} - V_T}{m V_t}} \left(1 - e^{-V_{DS}/V_t}\right)}$$

Simplified (when $V_{DS} >> V_t$):

$$I_{sub} \approx I_0 \cdot e^{(V_{GS} - V_T)/(mV_t)}$$

### Subthreshold Slope

$$\boxed{S_t = m \cdot V_t \cdot \ln(10) = 2.3 \cdot m \cdot V_t}$$

**Ideal:** $S_t \approx 60$ mV/decade at 300K

**Typical:** $S_t \approx 70-100$ mV/decade

### Junction Leakage

$$I_{junction} = I_S \left( e^{V_{bias}/V_t} - 1 \right)$$

### Leakage Temperature Dependence

- $V_T$ decreases ~0.8-2 mV/°C
- $I_{off}$ doubles every ~10°C
- $S_t$ increases linearly with T

---

## Device Equations

### Threshold Voltage (with Body Effect)

$$\boxed{V_T = V_{T0} + \gamma \left(\sqrt{|2\phi_F + V_{SB}|} - \sqrt{|2\phi_F|}\right)}$$

| Parameter | Description |
|-----------|-------------|
| $V_{T0}$ | Threshold at zero body bias |
| $\gamma$ | Body effect coefficient |
| $\phi_F$ | Fermi potential |
| $V_{SB}$ | Source-to-body voltage |

### Body Effect Coefficient

$$\gamma = \frac{\sqrt{2q \epsilon_{Si} N_A}}{C_{ox}}$$

### Fermi Potential

$$\phi_F = V_t \ln\left(\frac{N_A}{n_i}\right)$$

---

### Drain Current (Long Channel, Saturation)

$$I_{D,sat} = \frac{\mu C_{ox} W}{2L} (V_{GS} - V_T)^2$$

### Drain Current (Short Channel, Velocity Saturated)

$$I_{D,sat} = W C_{ox} v_{sat} (V_{GS} - V_T)$$

---

## PDP and EDP

### Power-Delay Product

$$\boxed{PDP = P_{avg} \cdot t_p = \frac{\alpha C_L V_{DD}^2}{2}}$$

**Units:** Joules (Energy per switching event)

### Energy-Delay Product

$$\boxed{EDP = PDP \cdot t_p = P_{avg} \cdot t_p^2}$$

**Units:** Joule-seconds

### Optimal Voltage for EDP

$$V_{DD,opt} \approx 3 V_T$$

---

## Delay Equations

### Propagation Delay (First Order)

$$t_p \propto \frac{C_L V_{DD}}{(V_{DD} - V_T)^{\alpha}}$$

where $\alpha \approx 1-2$ depending on velocity saturation.

### Maximum Frequency

$$f_{max} = \frac{1}{2 t_p}$$

---

## Architectural Formulas

### Parallelism (N units)

| Parameter | Scaling |
|-----------|---------|
| Voltage | $V_{DD}/N$ |
| Frequency per unit | $f/N$ |
| Total capacitance | $N \cdot C_{ref}$ |
| **Power** | $P_{ref}/N^2$ |

### Pipelining (N stages)

| Parameter | Scaling |
|-----------|---------|
| Voltage | $V_{DD}/N$ |
| Frequency | Same |
| Latency | $(N-1)$ cycles |
| **Power** | $\approx P_{ref}/N^2$ (with overhead) |

### Combined Parallel + Pipelined

For M parallel units with N pipeline stages:
$$P_{combined} \approx \frac{P_{ref}}{(M \cdot N)^2} \times \text{overhead}$$

---

## Short Channel Effects Summary

| Effect | Impact on $I_{off}$ | Impact on $V_T$ |
|--------|---------------------|-----------------|
| DIBL | Increases | Decreases |
| Punchthrough | Increases | Loss of control |
| $V_T$ Roll-off | Increases | Decreases |
| Hot carriers | Gate current | Increases (aging) |

---

## Technology Scaling Trends

If dimensions scale by factor $S$:

| Parameter | Scaling |
|-----------|---------|
| $V_{DD}$ | $1/S$ |
| $C$ | $1/S$ |
| $f$ | $S$ |
| $P_{dyn}$ | $1/S^2$ |
| $P_{leak}$ | Increases! |

---

## Unit Conversions

| Prefix | Symbol | Factor |
|--------|--------|--------|
| pico | p | $10^{-12}$ |
| femto | f | $10^{-15}$ |
| atto | a | $10^{-18}$ |
| nano | n | $10^{-9}$ |
| micro | μ | $10^{-6}$ |
| milli | m | $10^{-3}$ |
| kilo | k | $10^{3}$ |
| mega | M | $10^{6}$ |
| giga | G | $10^{9}$ |

### Common Units in Low Power VLSI

| Quantity | Typical Unit | Range |
|----------|--------------|-------|
| Capacitance | fF, pF | 1 fF - 100 pF |
| Current (switching) | mA, μA | μA - A |
| Current (leakage) | nA, pA | pA/μm - nA/μm |
| Power | mW, μW | nW - W |
| Voltage | V | 0.5V - 5V |
| Frequency | MHz, GHz | kHz - GHz |

---

## Sign Conventions

| Quantity | Positive Convention |
|----------|---------------------|
| $V_{GS}$ | Gate more positive than source |
| $V_{DS}$ | Drain more positive than source |
| $V_{SB}$ | Source more positive than body (reverse bias) |
| $I_D$ | Current into drain (for nMOS) |

---

## Common Approximations

| Approximation | When Valid |
|---------------|------------|
| $I_{sub} \propto e^{-V_T/S_t}$ | Subthreshold region |
| $f \propto V_{DD}$ | Velocity-saturated devices |
| $P_{sc} << P_{sw}$ | Fast transitions |
| $t_p \propto 1/(V_{DD}-V_T)$ | Long channel |

---

## Exam Tips

1. **Always check units** - Convert to SI before calculations
2. **Watch for leakage vs switching** - Different equations apply
3. **Remember quadratic voltage** - $V^2$ in power is crucial
4. **Temperature effects** - Leakage doubles per ~10°C
5. **Activity factor** - Don't forget $\alpha$ in switching power

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Worked Problems](./15_worked_problems.md) | Formula Sheet | [Roadmap](./00_roadmap.md) |
