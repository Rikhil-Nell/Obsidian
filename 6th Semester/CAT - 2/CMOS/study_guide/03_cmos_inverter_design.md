# CMOS Inverter - Design & Voltage Calculations

## Learning Objectives

After this section you will understand:
- How to derive $V_{IL}$ and $V_{IH}$ analytically using KCL
- How to compute the inverter threshold voltage $V_{th}$
- How to design a CMOS inverter for a desired switching threshold using $k_R$
- What makes a "symmetric" inverter and how to achieve it

---

## Voltage Relationships

Before any derivation, establish the voltage relationships. For a CMOS inverter with NMOS (bottom) and PMOS (top):

![[cmos_inverter_voltage_config.png]]

$$\boxed{V_{GS,n} = V_{in}, \quad V_{DS,n} = V_{out}}$$
$$\boxed{V_{GS,p} = V_{in} - V_{DD}, \quad V_{DS,p} = V_{out} - V_{DD}}$$

These relationships are **critical** -- they convert device voltages ($V_{GS}$, $V_{DS}$) into circuit voltages ($V_{in}$, $V_{out}$). Every derivation below uses them.

---

## Output Voltages $V_{OL}$ and $V_{OH}$

### When Input is LOW ($V_{in} \leq V_{T,n}$)
- NMOS is cutoff, PMOS is in linear region
- Since $I_D = 0$ and PMOS provides a path to $V_{DD}$:
$$\boxed{V_{OH} = V_{DD}}$$

### When Input is HIGH ($V_{in} \geq V_{DD} - |V_{T,p}|$)
- PMOS is cutoff, NMOS is in linear region
- Since $I_D = 0$ and NMOS provides a path to GND:
$$\boxed{V_{OL} = 0}$$

**Key takeaway:** CMOS gives true rail-to-rail output swing: $V_{OL} = 0$ and $V_{OH} = V_{DD}$.

---

## Deriving $V_{IL}$ (Maximum Input LOW)

**Definition:** $V_{IL}$ is the input voltage where the VTC slope equals $-1$ (i.e., $dV_{out}/dV_{in} = -1$), while the output is still mostly HIGH.

At $V_{in} = V_{IL}$:
- **NMOS** is in **saturation** ($V_{DS,n} > V_{GS,n} - V_{T,n}$)
- **PMOS** is in **linear** region

### Step 1: Write KCL ($I_{D,n} = I_{D,p}$)

$$\frac{k_n}{2}(V_{in} - V_{T,n})^2 = k_p\left[(V_{in} - V_{DD} - V_{T,p})(V_{out} - V_{DD}) - \frac{(V_{out} - V_{DD})^2}{2}\right]$$

### Step 2: Differentiate with respect to $V_{in}$ and set $dV_{out}/dV_{in} = -1$

After differentiation and substitution of $V_{in} = V_{IL}$:

$$\boxed{V_{IL} = \frac{2V_{out} + V_{T,n} - V_{DD} + V_{DD}\cdot k_R + V_{T,p}\cdot k_R}{1 + k_R}}$$

where $k_R = k_p/k_n$ is the **transconductance ratio**.

The corresponding $V_{out}$ at $V_{in} = V_{IL}$ is found from the KCL equation.

---

## Deriving $V_{IH}$ (Minimum Input HIGH)

**Definition:** $V_{IH}$ is the input voltage where the VTC slope equals $-1$ (i.e., $dV_{out}/dV_{in} = -1$), while the output is transitioning to LOW.

At $V_{in} = V_{IH}$:
- **NMOS** is in **linear** region
- **PMOS** is in **saturation**

### Step 1: Write KCL ($I_{D,n} = I_{D,p}$)

$$k_n\left[(V_{in} - V_{T,n})V_{out} - \frac{V_{out}^2}{2}\right] = \frac{k_p}{2}(V_{in} - V_{DD} - V_{T,p})^2$$

### Step 2: Differentiate and set $dV_{out}/dV_{in} = -1$

$$\boxed{V_{IH} = \frac{2V_{out} + V_{T,n} + V_{DD} + V_{T,p}/k_R}{1 + 1/k_R}}$$

---

## Inverter Threshold Voltage $V_{th}$

### Definition

The inverter threshold $V_{th}$ is the voltage where $V_{in} = V_{out}$. At this point, **both transistors are in saturation**. This is the same as the switching threshold $V_M$ from the previous file.

### Full Derivation

At $V_{in} = V_{out} = V_{th}$, both in saturation:

$$\frac{k_n}{2}(V_{th} - V_{T,n})^2 = \frac{k_p}{2}(V_{th} - V_{DD} - V_{T,p})^2$$

Taking square roots and solving:

$$\boxed{V_{th} = \frac{V_{T,n} + \sqrt{k_R}(V_{DD} + V_{T,p})}{1 + \sqrt{k_R}}}$$

where $k_R = k_p / k_n$.

**Note:** $V_{T,p}$ is negative for PMOS, so $V_{DD} + V_{T,p} = V_{DD} - |V_{T,p}|$.

---

## Designing CMOS Inverters

### The Design Problem

Given a desired threshold voltage $V_{th}$, find the required $k_R$:

![[design_equations_kr.png]]

Rearranging the $V_{th}$ equation:

$$\boxed{k_R = \left(\frac{V_{th} - V_{T,n}}{V_{DD} + V_{T,p} - V_{th}}\right)^2}$$

### The Ideal Symmetric Inverter

For a **symmetric** inverter, we want $V_{th} = V_{DD}/2$ (the VTC is perfectly centered). The ideal switching threshold is:

$$V_{th,ideal} = \frac{V_{DD}}{2}$$

If we also set $V_{T,n} = |V_{T,p}| = V_{T0}$ (symmetric threshold voltages), then:

$$\boxed{k_R = 1 \quad \Rightarrow \quad k_p = k_n}$$

Since $k = \mu C_{ox}(W/L)$ and $\mu_p \approx \mu_n / 2.5$:

$$\boxed{\frac{(W/L)_p}{(W/L)_n} = \frac{\mu_n}{\mu_p} \approx 2.5}$$

**Key insight:** To get a symmetric inverter, the PMOS must be about **2.5 times wider** than the NMOS.

### Effect of $k_R$ on VTC

![[vtc_kr_shift.png]]

![[vtc_kr_curves.png]]

- **Increasing $k_R$** (stronger PMOS): shifts $V_{th}$ toward **lower** values
- **Decreasing $k_R$** (stronger NMOS): shifts $V_{th}$ toward **higher** values

---

## Worked Example: Noise Margin Calculation

### Problem

![[problem1_parameters.png]]

Consider a CMOS inverter with:
- $V_{DD} = 2.5V$
- $V_{T,n} = 0.4V$, $V_{T,p} = -0.4V$
- $k_n = 115 \mu A/V^2$, $k_p = 57.5 \mu A/V^2$
- $k_R = k_p/k_n = 0.5$

Calculate the noise margins.

### Solution

**Step 1:** Calculate $V_{IL}$ using the derived formula.

From the equations, substituting $k_R = 0.5$:
- Solve for $V_{out}$ at the $dV_{out}/dV_{in} = -1$ point
- Then compute $V_{IL}$

After calculation: $V_{IL} \approx 1.03V$

**Step 2:** Calculate $V_{IH}$:

After calculation: $V_{IH} \approx 1.45V$

**Step 3:** Calculate noise margins:

$$NM_L = V_{IL} - V_{OL} = 1.03 - 0 = 1.03V$$
$$NM_H = V_{OH} - V_{IH} = 2.5 - 1.45 = 1.05V$$

**Note:** Since $k_R \neq 1$ (not symmetric), $NM_L \neq NM_H$, but they are close.

> The full step-by-step solution with equation images is in [Worked Problems](./09_worked_problems.md#problem-1-noise-margins)

---

## Common Mistakes

1. **Forgetting which transistor is in which mode for VIL vs VIH:**
   - At $V_{IL}$: NMOS saturated, PMOS linear
   - At $V_{IH}$: NMOS linear, PMOS saturated
   - **Memory trick:** The transistor that just turned ON is in saturation (low current); the one that was already ON is in linear (high current)

2. **Sign errors with PMOS voltages:** Always use $V_{GS,p} = V_{in} - V_{DD}$ (this is negative when input is low) and $V_{T,p}$ is negative

3. **Confusing $k_R$ with $\beta$:** $k_R = k_p/k_n$ while $\beta = (W/L)_p / (W/L)_n$. They are related by $k_R = (\mu_p/\mu_n) \cdot \beta$

4. **Not checking the condition $V_{DD} > V_{T,n} + |V_{T,p}|$:** If this is violated, the inverter cannot function properly

---

## Self-Check Questions

**Q1:** For a symmetric CMOS inverter with $V_{DD} = 1.8V$ and $V_{T,n} = |V_{T,p}| = 0.3V$, what is $V_{th}$?

> **A:** $V_{th} = V_{DD}/2 = 0.9V$

**Q2:** If $k_R = 2.5$ (PMOS is relatively stronger), will $V_{th}$ be above or below $V_{DD}/2$?

> **A:** Increasing $k_R$ shifts $V_{th}$ toward **lower** values, so $V_{th} < V_{DD}/2$. This is counterintuitive -- a stronger PMOS makes the transition happen at a lower input voltage because the PMOS "wins" the tug-of-war longer.

**Q3:** At the point $V_{in} = V_{IL}$, is the NMOS saturated or linear?

> **A:** **Saturated**. At $V_{IL}$, the NMOS has just turned on and carries relatively little current, while the PMOS (which has been ON) is in linear mode carrying the same small current.

---

## Concept Links

- The definitions of VTC regions come from [CMOS Inverter Static](./02_cmos_inverter_static.md)
- The $k_n$, $k_p$ parameters are the LEVEL 1 SPICE model from [SPICE Modeling](./01_spice_modeling.md)
- The noise margin calculation feeds into understanding the impact of [Power Dissipation](./04_power_dissipation.md) on $V_{DD}$ choices
- All derivation formulas are in [Formula Sheet](./10_formula_sheet_ultimate.md#vil-vih-derivations)
