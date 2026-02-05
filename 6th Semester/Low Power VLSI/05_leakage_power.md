# Leakage Power Dissipation

## Learning Objectives
After this section, you will understand:
- All six leakage mechanisms in MOSFETs (I1-I6)
- Subthreshold leakage and its exponential dependence
- Why leakage is critical in modern technologies
- Temperature effects on leakage

---

## Why Leakage Power is Important

Leakage power is consumed even when circuits are **idle** (no switching activity). With technology scaling:

| Technology Node | Leakage Contribution |
|-----------------|---------------------|
| > 90 nm | Negligible |
| 90 nm - 65 nm | Significant |
| < 45 nm | 20-70% of total power |

**Critical Trend:** Leakage increases faster than dynamic power with each technology generation!

![[leakage_components.png]]

---

## The Six Leakage Mechanisms

![[leakage_six_mechanisms.png]]

```
MOSFET Leakage Currents
├── I1: pn Junction Reverse-Bias Current
├── I2: Subthreshold Leakage          ← DOMINANT
├── I3: Gate Oxide Tunneling          ← DOMINANT  
├── I4: Hot-Carrier Injection
├── I5: Gate-Induced Drain Leakage (GIDL)
└── I6: Channel Punchthrough
```

---

## I1: pn Junction Reverse-Bias Current

When drain/source-to-well junctions are reverse biased, minority carrier diffusion/drift causes leakage.

$$I_{diode} = I_S \left( e^{V_{bias}/V_T} - 1 \right) + I_{gen}$$

| Parameter | Typical Value |
|-----------|---------------|
| Reverse saturation current density $J_S$ | 1-5 pA/μm² |
| Temperature dependence | Strong (doubles per ~10°C) |

**Key Insight:** Junction leakage occurs even in standby when no switching takes place.

---

## I2: Subthreshold Leakage (DOMINANT)

This is carrier diffusion from source to drain when $V_{GS} < V_T$ (weak inversion).

### Subthreshold Current Equation

$$\boxed{I_{sub} = I_0 \cdot e^{\frac{V_{GS} - V_T}{m V_t}} \left(1 - e^{-V_{DS}/V_t}\right)}$$

where:
- $V_t = kT/q$ = thermal voltage (~26 mV at 300K)
- $m$ = subthreshold swing coefficient (1.0-1.5)
- $I_0$ = technology-dependent prefactor

### Subthreshold Slope

The **subthreshold slope** indicates how effectively the transistor can be turned off:

$$S_t = m \cdot V_t \cdot \ln(10) = 2.3 \cdot m \cdot V_t \approx 60-100 \text{ mV/decade}$$

**Meaning:** For every 60-100 mV reduction in $V_{GS}$ below $V_T$, drain current decreases by 10×.

| Typical $S_t$ Range | Quality |
|---------------------|---------|
| 60-70 mV/decade | Excellent (near ideal) |
| 70-100 mV/decade | Typical bulk CMOS |
| > 100 mV/decade | Poor subthreshold behavior |

### Typical Subthreshold Current Values

| Device Type | $I_{sub}$ per μm width |
|-------------|------------------------|
| Standard $V_T$ | 1-10 nA/μm |
| Low $V_T$ | 100 nA/μm |

---

## I3: Gate Oxide Tunneling (DOMINANT)

With thin gate oxides (< 2-3 nm), electrons tunnel directly through the oxide.

### Mechanism

![[gate_tunneling.png]]

**Direct Tunneling:**
- Electrons from substrate → gate (positive gate bias)
- Electrons from n+ polysilicon → substrate (negative gate bias)

### Tunneling Current Characteristics

$$I_{tunnel} \propto e^{-t_{ox}/\lambda}$$

where $\lambda$ depends on barrier height and electron energy.

| Gate Oxide Thickness | Tunneling Current |
|---------------------|-------------------|
| > 3 nm | Negligible |
| 2-3 nm | Measurable |
| < 2 nm | Significant |

**Modern Problem:** As $t_{ox}$ scales below 2 nm, gate leakage becomes comparable to subthreshold leakage.

---

## I4: Hot-Carrier Injection

High-energy ("hot") electrons near the drain can overcome the Si-SiO₂ barrier and enter the oxide.

### Mechanism
- High electric field accelerates carriers
- Carriers gain energy > 3.1 eV (electrons) or 4.5 eV (holes)
- Carriers overcome interface barrier and enter oxide
- Causes oxide charging → $V_T$ shift → reliability degradation

**Key Insight:** This is more of a reliability issue than a power issue, but it does contribute to gate leakage.

---

## I5: Gate-Induced Drain Leakage (GIDL)

When gate is at low/negative voltage relative to drain, band-to-band tunneling occurs in the drain overlap region.

### Mechanism

![[gidl_current.png]]

1. High $V_{DG}$ creates deep depletion under drain overlap
2. Band bending enables band-to-band tunneling
3. Generated carriers swept to substrate → drain-body current

### Enhancement Factors
- Thinner oxide → higher field → more GIDL
- Higher $V_{DD}$ → stronger tunneling
- Short channels → greater overlap effects

---

## I6: Channel Punchthrough

When drain and source depletion regions **merge** through the channel bulk, current flows independent of gate voltage.

### Conditions for Punchthrough
1. Short channel length
2. Low doping concentration
3. High drain-source voltage

See [Short Channel Effects](./07_short_channel_effects.md#punchthrough) for detailed analysis.

---

## Factors Affecting Subthreshold Leakage

### 1. DIBL (Drain-Induced Barrier Lowering)
Drain voltage reduces the source-channel barrier, increasing subthreshold current.

### 2. Body Effect
Reverse body bias increases $V_T$, reducing leakage.

### 3. Narrow Width Effect
Changes in $V_T$ due to gate width modulation affect leakage.

### 4. Channel Length and $V_T$ Roll-off
Shorter channels have lower $V_T$, leading to exponentially higher leakage.

### 5. Temperature Effect

Temperature strongly affects subthreshold leakage:

| Parameter | Temperature Effect |
|-----------|-------------------|
| $S_t$ (slope) | Increases linearly with T |
| $V_T$ | Decreases ~0.8-2 mV/°C |
| $I_{off}$ | Increases ~2× per 10°C |

**Example:** For 0.3 μm technology:
- $S_t$ varies from 58.2 to 81.9 mV/decade (-50°C to +25°C)
- $I_{off}$ increases from 0.45 pA to 100 pA (factor of 356×)

---

## Total Leakage Power

$$\boxed{P_{leak} = V_{DD} \cdot I_{leak}}$$

where:
$$I_{leak} = I_{sub} + I_{gate} + I_{junction} + I_{GIDL} + ...$$

For a chip with many transistors:

$$I_{total} = \sum_{\text{all transistors}} I_{leak,i}$$

---

## Worked Example

**Problem 6 from course:** A 65 nm process chip has:
- 50 million logic transistors (12λ width)
- 950 million memory transistors (4λ width)
- λ = 25 nm
- Low-Vt leakage: 100 nA/μm
- High-Vt leakage: 10 nA/μm
- Gate leakage: 5 nA/μm
- 5% of logic uses low-Vt, rest uses high-Vt
- VDD = 1V

**Solution:**

Low-Vt device width:
$$W_{low} = 50 \times 10^6 \times 0.05 \times 12\lambda \times 0.025 \text{ μm/λ} = 0.75 \times 10^6 \text{ μm}$$

High-Vt device width:
$$W_{high} = [50 \times 10^6 \times 0.95 \times 12 + 950 \times 10^6 \times 4] \times 0.025 = 109.25 \times 10^6 \text{ μm}$$

Subthreshold current:
$$I_{sub} = \frac{0.75 \times 10^6 \times 100 + 109.25 \times 10^6 \times 10}{2} = 584 \text{ mA}$$

Gate current:
$$I_{gate} = \frac{(0.75 + 109.25) \times 10^6 \times 5}{2} = 275 \text{ mA}$$

Static power:
$$\boxed{P_{static} = (584 + 275) \times 1 = 859 \text{ mW}}$$

---

## Common Mistakes

1. **Ignoring temperature effects** - Leakage doubles per ~10°C
2. **Using high-Vt everywhere** - Impacts critical path timing
3. **Forgetting gate leakage in thin oxides** - Significant below 2 nm
4. **Not accounting for standby** - Leakage accumulates even when idle

---

## Self-Check Questions

<details>
<summary>1. Which leakage mechanisms are dominant in modern processes?</summary>

I2 (subthreshold leakage) and I3 (gate oxide tunneling) are the dominant mechanisms:
- Subthreshold leakage increases exponentially with decreasing Vt
- Gate tunneling increases exponentially with decreasing oxide thickness
Both are consequences of aggressive technology scaling.
</details>

<details>
<summary>2. Why does subthreshold current have exponential dependence on VGS?</summary>

In weak inversion (VGS < VT), carriers diffuse from source to drain rather than drifting in a channel. This diffusion current is exponentially dependent on the surface potential, which is controlled by VGS. The relationship follows Boltzmann statistics.
</details>

<details>
<summary>3. How can leakage power be reduced?</summary>

- Use higher Vt devices in non-critical paths
- Apply reverse body bias (VTCMOS)
- Use sleep transistors (MTCMOS)
- Lower temperature
- Increase gate oxide thickness (trade-off with performance)
</details>

---

## Concept Links

- **Previous:** [Short-Circuit Power](./04_short_circuit_power.md)
- **Next:** [Glitching Power](./06_glitching_power.md)
- **Related:**
  - [Short Channel Effects](./07_short_channel_effects.md) - DIBL, punchthrough
  - [VTCMOS](./10_vtcmos_circuits.md) - Leakage reduction technique
  - [MTCMOS](./11_mtcmos_circuits.md) - Sleep transistors
- **Formula Reference:** [Formula Sheet](./16_formula_sheet_ultimate.md#leakage-currents)

---

## Navigation

| Previous | Current | Next |
|----------|---------|------|
| [Short-Circuit Power](./04_short_circuit_power.md) | Leakage Power | [Glitching Power](./06_glitching_power.md) |
