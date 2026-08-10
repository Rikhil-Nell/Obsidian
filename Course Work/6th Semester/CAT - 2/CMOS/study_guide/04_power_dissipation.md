# Power Dissipation in CMOS

## Learning Objectives

After this section you will understand:
- Why power consumption is the #1 challenge in modern VLSI design
- The three components of CMOS power: dynamic, short-circuit, and static (leakage)
- How to calculate dynamic switching power ($P = CV_{DD}^2 f$)
- How technology scaling affects power
- Trade-offs between speed and power

---

## Why Does Power Matter?

![[power_battery_gap.png]]

The graph above tells the entire story of modern VLSI design: **power consumption grows much faster than battery technology can keep up**. This gap is why "Low Power VLSI Design" is an entire course, not just a footnote.

### Historical Context: Moore's Law and Power

![[moore_law_transistor_count.png]]

**Moore's Law** states that transistor count doubles approximately every 2 years. More transistors = more switching = more power.

![[microprocessor_power_trend.png]]

Microprocessor power has grown from 0.2W (Intel 4004, 1971) to over 100W (modern processors). At one point, processors were on track to reach the power density of a nuclear reactor!

![[itrs_power_technology.png]]

**Key insight:** The ITRS (International Technology Roadmap for Semiconductors) recognized that power must be capped around 198W for practical thermal management. This forced the industry to stop increasing clock frequencies and instead move to multi-core designs.

---

## Components of CMOS Power Dissipation

Total power in CMOS:

$$\boxed{P_{total} = P_{dynamic} + P_{short-circuit} + P_{static}}$$

### 1. Dynamic (Switching) Power -- The Dominant Component

![[switching_power_formula.png]]

$$\boxed{P_{switching} = C_L \cdot V_{DD}^2 \cdot f_{sw}}$$

Where:
- $C_L$ = load capacitance being charged/discharged (F)  
- $V_{DD}$ = supply voltage (V)
- $f_{sw}$ = switching frequency (Hz) = $\alpha \cdot f_{clock}$
- $\alpha$ = activity factor (fraction of clock cycles where switching occurs, typically 0.1 to 0.3)

**Analogy:** Think of filling and emptying a bucket of water repeatedly. The energy used depends on the bucket size ($C_L$), the height you lift water ($V_{DD}$), and how often you do it ($f_{sw}$).

#### Where Does This Formula Come From?

Each time the output transitions from LOW to HIGH:
- The PMOS charges $C_L$ from 0 to $V_{DD}$
- Energy drawn from supply: $E_{supply} = C_L \cdot V_{DD}^2$
- Energy stored in capacitor: $E_{cap} = \frac{1}{2}C_L V_{DD}^2$
- Energy dissipated in PMOS resistance: $E_{diss} = \frac{1}{2}C_L V_{DD}^2$

Each HIGH-to-LOW transition:
- The NMOS discharges $C_L$ from $V_{DD}$ to 0
- The stored energy $\frac{1}{2}C_L V_{DD}^2$ is dissipated in NMOS

**Total energy per complete cycle** (one 0->1->0):
$$E_{cycle} = C_L \cdot V_{DD}^2$$

**Power** = Energy per cycle $\times$ switching frequency:
$$P_{switching} = C_L \cdot V_{DD}^2 \cdot f_{sw}$$

#### Key Insight: $V_{DD}$ Dominates

Since power scales as $V_{DD}^2$, reducing supply voltage is the **most effective** way to reduce power:
- Halving $V_{DD}$ reduces dynamic power by **4x**
- But this also increases delay (speed penalty)

### 2. Short-Circuit Power

During switching, there is a brief moment when **both NMOS and PMOS are simultaneously ON**, creating a direct path from $V_{DD}$ to GND (a "short circuit").

$$P_{sc} = I_{sc} \cdot V_{DD}$$

This power is typically **10-15%** of dynamic power in well-designed circuits. It can be minimized by:
- Ensuring input rise/fall times are not much larger than output rise/fall times
- Keeping transistor sizes balanced

### 3. Static (Leakage) Power

Even when a CMOS gate is not switching, tiny currents flow:

$$P_{static} = I_{leakage} \cdot V_{DD}$$

Sources of leakage:
- **Subthreshold current**: Current when $V_{GS} < V_T$ (transistor is "off" but not perfectly)
- **Gate leakage**: Current through the ultra-thin gate oxide (tunneling)
- **Junction leakage**: Reverse-biased PN junction currents

**Critical in modern technology:** At 65nm and below, leakage power can equal or exceed dynamic power! This is why advanced nodes use:
- High-k gate dielectrics
- FinFET structures
- Power gating (turning off unused blocks)

---

## CMOS Logic Structure

![[cmos_pullup_pulldown.png]]

Every CMOS gate consists of:
- **Pull-Up Network (PUN)**: Made of PMOS transistors, connects output to $V_{DD}$
- **Pull-Down Network (PDN)**: Made of NMOS transistors, connects output to GND

The PUN and PDN are **complementary**: for any input combination, exactly one network is ON and the other is OFF. This is why there is no static current path.

---

## Power-Delay Trade-offs

| Strategy | Power Effect | Delay Effect | Notes |
|----------|-------------|--------------|-------|
| Reduce $V_{DD}$ | $\downarrow\downarrow$ (quadratic) | $\uparrow$ (slower) | Most effective power reduction |
| Reduce $C_L$ | $\downarrow$ (linear) | $\downarrow$ (faster) | Win-win: minimize parasitics |
| Reduce $f_{clock}$ | $\downarrow$ (linear) | N/A | Lower throughput |
| Reduce $\alpha$ (activity) | $\downarrow$ (linear) | N/A | Clock gating, operand isolation |
| Reduce $V_T$ | $\uparrow$ (more leakage) | $\downarrow$ (faster) | Leakage vs speed trade-off |
| Multi-$V_T$ design | $\downarrow$ leakage | Varies | Use high-$V_T$ on non-critical paths |

---

## Common Mistakes

1. **Saying CMOS has zero power dissipation**: CMOS has very low STATIC power, but significant DYNAMIC power. When gates switch, they absolutely consume power
2. **Forgetting the activity factor**: $f_{sw} \neq f_{clock}$. Multiply by $\alpha$ (typically 0.1-0.3)
3. **Confusing energy and power**: Energy per cycle is $CV_{DD}^2$. Power is energy $\times$ frequency
4. **Ignoring leakage in modern processes**: Below 90nm, leakage is NOT negligible

---

## Self-Check Questions

**Q1:** A CMOS inverter has $C_L = 50 fF$, $V_{DD} = 1.2V$, and operates at $f_{clock} = 1 GHz$ with activity factor $\alpha = 0.2$. What is the dynamic power?

> **A:** $P = C_L V_{DD}^2 f_{sw} = 50 \times 10^{-15} \times 1.44 \times 0.2 \times 10^9 = 14.4 \mu W$

**Q2:** If you reduce $V_{DD}$ from 1.8V to 1.2V, by what factor does dynamic power decrease?

> **A:** Factor = $(1.8/1.2)^2 = 2.25$. Power decreases by 2.25x.

**Q3:** Why can't we just reduce $V_{DD}$ to very low values (say 0.3V)?

> **A:** Three reasons: (1) $V_{DD}$ must exceed $V_{T,n} + |V_{T,p}|$ for the gate to function, (2) Speed decreases dramatically at low $V_{DD}$, (3) The noise margin shrinks, making the circuit unreliable.

**Q4:** What are the three components of CMOS power dissipation?

> **A:** Dynamic (switching) power ($CV_{DD}^2 f$), short-circuit power, and static (leakage) power.

---

## Concept Links

- Supply voltage scaling effects on VTC are in [CMOS Inverter Static](./02_cmos_inverter_static.md#supply-voltage-scaling)
- The capacitance $C_L$ is broken down in detail in [Dynamic Characteristics](./05_dynamic_characteristics.md)
- Delay vs. $V_{DD}$ trade-off is quantified in [Dynamic Characteristics](./05_dynamic_characteristics.md#propagation-delay-first-order-analysis)
- The complete power formula is in [Formula Sheet](./10_formula_sheet_ultimate.md#power-dissipation)
