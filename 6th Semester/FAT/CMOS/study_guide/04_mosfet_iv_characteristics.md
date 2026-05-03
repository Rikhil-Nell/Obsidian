# MOSFET I-V Characteristics

> Concept: derive the linear and saturation drain-current equations from the gradual channel approximation, understand pinch-off, channel-length modulation, velocity saturation, and the SPICE Levels 1/2/3 vocabulary used in the slides.

## The Big Picture

There are **three operating regions** for any MOS transistor:

1. **Cutoff** — $V_{GS}<V_T$ → no inversion channel → only sub-threshold leakage.
2. **Linear / Triode** — $V_{GS}\ge V_T$ and $V_{DS}<V_{GS}-V_T$ → channel exists end-to-end → behaves like a voltage-controlled resistor.
3. **Saturation** — $V_{GS}\ge V_T$ and $V_{DS}\ge V_{GS}-V_T$ → channel pinches off near drain → current depends mostly on $V_{GS}$.

Saturation is the regime in which most amplifying and switching action happens. The boundary $V_{DS}=V_{GS}-V_T$ is called the **saturation edge** or **VDSAT**.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p46_img1.png]]
![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p47_img1.png]]

## Gradual Channel Approximation (GCA) — Why It Works

Assumptions used in the slide derivation:

- The y-axis runs from source ($y=0$) to drain ($y=L$) along the channel.
- The threshold voltage is constant along the channel.
- The vertical (gate-to-channel) field $E_x$ is much larger than the lateral $E_y$.
- The entire channel is inverted (i.e. the device is in linear or about to saturate).
- Mobility $\mu_n$ is constant.

These let us treat each thin slice of channel as a tiny resistor and integrate.

### Channel Charge as a Function of Position

At position $y$, the local channel-to-source voltage is $V_C(y)$. Local inversion charge per unit area:

$$
\boxed{\,Q_I(y) = -C_{ox}\!\left[V_{GS}-V_T-V_C(y)\right]\,}
$$

This says the local channel charge depends on how far the gate-to-channel voltage *at that point* exceeds threshold. Near the source ($V_C=0$) the channel is dense; near the drain ($V_C=V_{DS}$) it is thinner.

### Local Resistance and Drain Current

Differential resistance of an infinitesimal slice $dy$:

$$
dR = \frac{dy}{\mu_n\,|Q_I(y)|\,W}
$$

Drain current (constant along channel for DC):

$$
I_D\,dy = \mu_n\,W\,|Q_I(y)|\,dV_C
$$

Integrate from $y=0$, $V_C=0$ to $y=L$, $V_C=V_{DS}$:

$$
\int_0^L I_D\,dy = \mu_n\,W\,C_{ox}\!\int_0^{V_{DS}}\!(V_{GS}-V_T-V_C)\,dV_C
$$

leading to the **linear (triode) region** equation:

$$
\boxed{\,I_D = \mu_n C_{ox}\frac{W}{L}\!\left[(V_{GS}-V_T)V_{DS}-\frac{V_{DS}^2}{2}\right]\,}
$$

Define the trans-conductance parameters:

$$
k_n' = \mu_n C_{ox}\quad\text{(process)},\qquad k_n = k_n'\frac{W}{L}\quad\text{(device)}
$$

So shortcut form: $I_D=k_n[(V_{GS}-V_T)V_{DS}-V_{DS}^2/2]$.

### The Saturation Equation

Set $V_{DS}=V_{GS}-V_T$ (the saturation edge) in the linear formula:

$$
\boxed{\,I_{D,\text{sat}}=\frac{1}{2}\mu_n C_{ox}\frac{W}{L}(V_{GS}-V_T)^2\,}
$$

Once $V_{DS}>V_{GS}-V_T$, the local channel charge at $y=L$ goes (nearly) to zero — pinch-off — and increasing $V_{DS}$ drops across the depletion region near the drain, not across the channel. The current saturates.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p54_img1.png]]

---

## Channel Length Modulation (CLM)

In real saturation, increasing $V_{DS}$ slightly *shortens* the effective channel because the pinch-off point moves toward the source. The effective length becomes $L'=L-\Delta L$. Replacing $L$ in the saturation formula:

$$
I_D = \frac{1}{2}k_n'\frac{W}{L-\Delta L}(V_{GS}-V_T)^2
$$

Empirically modelled as

$$
\boxed{\,I_{D,\text{sat}} = \frac{1}{2}\mu_n C_{ox}\frac{W}{L}(V_{GS}-V_T)^2\,(1+\lambda V_{DS})\,}
$$

with **channel-length-modulation coefficient** $\lambda$ (V$^{-1}$). Large $\lambda$ ⇒ less ideal current source ⇒ noisier delay model. Long-channel devices have small $\lambda$; short-channel devices have larger $\lambda$.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p59_img1.png]]

---

## Velocity Saturation (Short-Channel Reality)

The square-law assumes drift velocity $v_d=\mu E$ (linear in field). In short channels, the lateral field $E_y$ becomes huge and carriers hit a **saturation velocity** $v_{sat}$ (≈$10^7$ cm/s for electrons in Si). Effective mobility drops and the saturation current loses its quadratic dependence:

$$
v_d \approx \frac{\mu_n E_y}{1+E_y/E_C}
$$

with critical field $E_C$. The result: in deep-submicron CMOS,

$$
I_{D,\text{sat}} \propto (V_{GS}-V_T)^\alpha \quad\text{with}\quad 1<\alpha<2
$$

instead of a pure square law. This is the basis for the **alpha-power law** $\alpha\approx1.3$ in nano-CMOS textbooks.

---

## Drain Current as a Function of Three Voltages

Including the body terminal:

$$
I_D = f(V_{GS},V_{DS},V_{BS})
$$

Body effect modifies $V_T$ (see [[03_threshold_voltage_and_body_effect]]) but otherwise the same regions apply. Always check region first, then plug into the right formula.

---

## Worked Example — Slide Problem 3

Process: $L=0.4\,\mu\text{m}$, $t_{ox}=8$ nm, $\mu_n=450$ cm$^2$/V·s, $V_T=0.7$ V. Device sized $W/L=8\,\mu\text{m}/0.8\,\mu\text{m}$.

### (a) Compute $C_{ox}$ and $k_n'$

$$
C_{ox}=\frac{3.9\cdot8.854\times10^{-12}}{8\times10^{-9}}\approx 4.32\times10^{-3}\,\text{F/m}^2
$$

$$
k_n' = \mu_n C_{ox} = 450\times10^{-4}\,\text{m}^2/\text{V·s}\times 4.32\times10^{-3}\,\text{F/m}^2
\approx 194\,\mu\text{A/V}^2
$$

### (b) $V_{GS}, V_{DS}$ for saturation, $I_D=100\,\mu$A

Use $I_D=\tfrac{1}{2}k_n'(W/L)(V_{GS}-V_T)^2$. With $W/L=10$:

$$
100\,\mu\text{A}=\tfrac{1}{2}(194\,\mu\text{A/V}^2)(10)(V_{GS}-V_T)^2
$$

$$
(V_{GS}-V_T)^2 = \frac{100}{970}\approx 0.103\Rightarrow V_{GS}-V_T\approx 0.32\,\text{V}
$$

So $V_{GS}\approx 1.02$ V. To stay in saturation, $V_{DS}\ge V_{GS}-V_T=0.32$ V.

### (c) $V_{GS}$ for $R_{on}=1\,\text{k}\Omega$, $V_{DS}=1$ V

For very small $V_{DS}$, the resistance approximation:

$$
R_{on}\approx\frac{1}{k_n'(W/L)(V_{GS}-V_T)}
$$

$$
1000=\frac{1}{(194\times10^{-6})(10)(V_{GS}-V_T)}
\Rightarrow V_{GS}-V_T\approx 0.515\,\text{V}
$$

So $V_{GS}\approx 1.21$ V.

This pattern (find $C_{ox}$, then $k_n'$, then plug into the right region equation) is the core of nearly every numerical question in the unit.

---

## SPICE MOSFET Levels (Slide Module 2)

Used as conceptual vocabulary. The slides explicitly mention three:

| Level | Nature | What it captures |
|---|---|---|
| LEVEL 1 (MOS1) | Square-law GCA | Basic linear/saturation, no short-channel effects. Good for hand analysis. |
| LEVEL 2 (MOS2) | Detailed analytical | Bulk-charge with channel-voltage dependence, sub-threshold current, scattering velocity saturation, charge-controlled capacitances. |
| LEVEL 3 (MOS3) | Semi-empirical | Mostly empirical fits; balances accuracy and runtime. Bulk-charge factor $F_B$, parameters $F_S$ and $\mu_S$ for short channel, $F_N$ for narrow channel. |

For exams, you are expected to recognise that:
- LEVEL 1 = the equations on this page,
- LEVEL 2/3 = "accurate but include short-channel + sub-threshold + capacitance corrections".

---

## I-V Plotting Tips

- Output characteristic ($I_D$ vs $V_{DS}$ at fixed $V_{GS}$): parabolic in linear region, flattens in saturation, tilts upward slightly because of CLM.
- Transfer characteristic ($I_D$ vs $V_{GS}$ in saturation): zero up to $V_T$, then $\propto(V_{GS}-V_T)^2$ (long channel) or $\propto(V_{GS}-V_T)^\alpha$ with $\alpha\approx 1.3$ (short channel).

---

## Common Exam Mistakes

- Skipping the region check. Always confirm $V_{DS}$ vs $V_{GS}-V_T$ before plugging into a formula.
- Using the wrong mobility for pMOS ($\mu_p$, not $\mu_n$).
- Forgetting the $1/2$ in the saturation formula.
- Treating $\lambda$ as dimensionless (it is in V$^{-1}$).
- Mixing units when computing $k_n'$. Track $\text{cm}^2/\text{V·s}$ vs $\mu\text{m}^2/\text{V·s}$ vs $\text{m}^2/\text{V·s}$.

## Self-Check Questions

1. Why is the saturation current independent of $V_{DS}$ to first order?
   <details><summary>Answer</summary>Past pinch-off, the channel charge at the drain end is essentially zero. Extra $V_{DS}$ drops over a depleted region near the drain, not across the channel, so it doesn't change the carriers being injected from the source.</details>

2. What changes when you double $W$ at fixed $L$?
   <details><summary>Answer</summary>$k_n$ doubles, so both linear and saturation currents double. Input gate capacitance (and hence load on driving stage) also doubles.</details>

3. Why do short-channel devices show $I_D\propto V_{GS}-V_T$ rather than $(V_{GS}-V_T)^2$?
   <details><summary>Answer</summary>Velocity saturation. Once carriers reach $v_{sat}$, increasing overdrive raises the *charge* but not the velocity, giving $I_D\propto Q_I\propto (V_{GS}-V_T)$.</details>

4. Why does a transistor look like a voltage-controlled resistor for very small $V_{DS}$?
   <details><summary>Answer</summary>The $V_{DS}^2/2$ term becomes negligible, leaving $I_D\approx k_n(V_{GS}-V_T)V_{DS}$, which is Ohmic with $R_{on}=1/[k_n(V_{GS}-V_T)]$.</details>

## Concept Links

- Previous: [[03_threshold_voltage_and_body_effect]]
- Next: [[05_mosfet_capacitances_and_resistances]]
- Related: [[06_scaling_and_short_channel_effects]] (velocity saturation, DIBL), [[08_cmos_inverter_dynamic_behavior]] (uses $R_{on}$)
- Formulas: [[18_formula_sheet#mos-transistor-iv]]
