# MOSFET Capacitances and Resistances

> Concept: catalog every parasitic capacitance and resistance that you have to add up to estimate AC behaviour, propagation delay, and dynamic power. Slide Problems 5 and 6 are pure capacitance arithmetic.

## Why This Note Exists

The DC equations from [[04_mosfet_iv_characteristics]] tell you the *current at steady state*. They say nothing about *speed*. To switch a node, the driver has to charge and discharge a *capacitance* through some *resistance*. So every later delay equation, $RC$ model, Elmore delay, and dynamic power expression depends on you knowing exactly which parasitics to add.

Two big categories from the slides:

1. **Oxide-related capacitances** — gate-to-source, gate-to-drain, gate-to-body, gate overlap.
2. **Junction capacitances** — source-to-body, drain-to-body, plus their *sidewall* contribution.

Plus parasitic series resistance from contacts and diffusion.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p89_img1.png]]

---

## Geometry Reminder

The slides use:
- $L_M$ — drawn (mask) channel length on the layout.
- $L_D$ — gate-to-source-or-drain *overlap* (each side equal in symmetric devices).
- $L = L_M - 2L_D$ — actual electrical channel length.
- $W$ — channel width.
- $x_j$ — junction depth of source/drain into substrate.

So when you compute oxide capacitance, watch for whether you are using $L$ or $L_M$.

---

## Oxide-Related Capacitances

### 1. Overlap Capacitances $C_{GS,ov}$, $C_{GD,ov}$

The gate physically extends a bit over the source and drain diffusions due to lateral diffusion. Those overlapping regions are parallel-plate capacitors:

$$
\boxed{\,C_{GS}^{ov} = C_{GD}^{ov} = C_{ox}\cdot W\cdot L_D\,}
$$

These are **bias-independent** — they exist regardless of operating region.

### 2. Bias-Dependent Gate-Channel Capacitances

For the channel-region capacitance (i.e. through the active part of the gate, ignoring overlaps), the distribution between $C_{GS}$, $C_{GD}$, $C_{GB}$ depends on the operating mode:

| Mode | $C_{GS}$ | $C_{GD}$ | $C_{GB}$ |
|---|---|---|---|
| Cut-off | 0 | 0 | $C_{ox}\,W\,L$ (gate sees only the body) |
| Linear | $\tfrac{1}{2}C_{ox}WL$ | $\tfrac{1}{2}C_{ox}WL$ | 0 (channel shields body) |
| Saturation | $\tfrac{2}{3}C_{ox}WL$ | 0 | 0 (drain end pinched off) |

Why the differences:

- In **cutoff** there is no inversion layer. The gate looks down through the oxide directly at the depleted/quasi-neutral substrate, giving $C_{GB}\approx C_{ox}WL$.
- In **linear** the channel exists end-to-end and shields the body. The distributed gate-channel capacitance can be modelled as split equally between source and drain.
- In **saturation** the drain end is pinched off — there is no continuous channel near the drain — so $C_{GD}$ from the channel side vanishes. Detailed integration gives the famous $\tfrac{2}{3}C_{ox}WL$ on the source side.

Add the overlap pieces to the active-channel pieces:

$$
C_{GS,total} = C_{GS}^{ov}+C_{GS}^{channel},\quad C_{GD,total}=C_{GD}^{ov}+C_{GD}^{channel}
$$

For most digital hand-analysis, people simplify everything as a single **gate capacitance** $C_g\approx C_{ox}WL$ and lump it onto the input.

---

## Junction Capacitances

The source and drain n+ diffusions form pn-junctions with the surrounding p-substrate or p-well. Reverse-biased pn-junctions store charge in their depletion regions, giving voltage-dependent capacitance.

### Per-Unit-Area Junction Capacitance

Starting from depletion width:

$$
x_d = \sqrt{\frac{2\epsilon_{Si}}{q}\!\left(\frac{N_A+N_D}{N_A N_D}\right)(\phi_0 - V)}
$$

with built-in potential

$$
\phi_0 = \frac{kT}{q}\ln\!\left(\frac{N_A N_D}{n_i^2}\right)
$$

The junction capacitance per unit area is

$$
C_j(V) = \frac{\epsilon_{Si}}{x_d} = \frac{C_{j0}}{(1-V/\phi_0)^m}
$$

where $V$ is the *junction voltage* (negative for reverse bias) and $m$ is the **grading coefficient**:

- $m=1/2$ for an abrupt junction,
- $m=1/3$ for linearly graded.

Zero-bias capacitance per area:

$$
\boxed{\,C_{j0}=\sqrt{\frac{q\,\epsilon_{Si}}{2\,\phi_0}\cdot\frac{N_A N_D}{N_A+N_D}}\,}
$$

### Equivalent Large-Signal Capacitance

In a switching circuit, the junction voltage isn't constant — it sweeps from $V_1$ to $V_2$. The slides define an **equivalent large-signal junction capacitance**:

$$
C_{eq} = \frac{Q(V_2)-Q(V_1)}{V_2-V_1}
$$

Working it out for an abrupt junction:

$$
C_{eq} = C_{j0}\cdot K_{eq}
$$

with

$$
\boxed{\,K_{eq} = -\frac{2\sqrt{\phi_0}}{V_2-V_1}\!\left(\sqrt{\phi_0-V_2}-\sqrt{\phi_0-V_1}\right)\,}
$$

This is the formula most exam problems use to convert a swing into an effective constant capacitance for delay calculation.

### Bottom-Wall vs Sidewall

The S/D diffusion forms **five** planar pn-junctions: one bottom and four sidewalls. The bottom faces the substrate; three of the sidewalls face the p+ channel-stop; one faces the channel.

Total junction capacitance for source or drain:

$$
\boxed{\,C_{SB\,or\,DB} = C_{j0}\cdot K_{eq}\cdot A + C_{j0,sw}\cdot K_{eq,sw}\cdot P\,}
$$

- $A = W\cdot Y$ — bottom area of the diffusion,
- $P = 2(W+Y)-W = W+2Y$ — sidewall perimeter (the side facing the channel is *not* a junction with the substrate, so it is excluded),
- $C_{j0,sw}$ — sidewall capacitance per unit length, computed using the higher channel-stop doping $N_{A,sw}$.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p94_img1.png]]

---

## Worked Example — Slide Problem 5

Abrupt pn-junction with $N_D=10^{19}\,\text{cm}^{-3}$, $N_A=10^{16}\,\text{cm}^{-3}$, $A=20\,\mu\text{m}\times20\,\mu\text{m}=400\,\mu\text{m}^2$, $V_{bias}=-5$ V (reverse).

### Step 1 — Built-in potential

$$
\phi_0 = 0.026\ln\!\frac{10^{19}\cdot10^{16}}{(1.45\times10^{10})^2}\approx 0.026\cdot\ln(4.76\times10^{14})\approx 0.88\,\text{V}
$$

### Step 2 — $C_{j0}$ per unit area

$$
C_{j0}=\sqrt{\frac{(1.6\times10^{-19})(11.7\times8.854\times10^{-14})}{2\times 0.88}\cdot\frac{10^{19}\cdot10^{16}}{10^{19}+10^{16}}}
$$

This evaluates (in F/cm$^2$) to about $C_{j0}\approx 3\times10^{-8}$ F/cm$^2 = 3\times10^{-4}$ F/m$^2$.

### Step 3 — Voltage equivalence factor for swing $0\to-5$ V

$$
K_{eq}=-\frac{2\sqrt{0.88}}{-5-0}\!\left(\sqrt{0.88-(-5)}-\sqrt{0.88-0}\right)
=-\frac{1.876}{-5}\!\left(\sqrt{5.88}-\sqrt{0.88}\right)
$$

$$
=0.375\cdot(2.42-0.94)=0.375\cdot1.49\approx 0.56
$$

### Step 4 — Equivalent capacitance

$$
C_{eq}=C_{j0}\cdot K_{eq}\cdot A \approx 3\times10^{-4}\cdot 0.56\cdot (400\times10^{-12})\,\text{m}^2
\approx 6.7\times10^{-14}\,\text{F}=67\,\text{fF}
$$

(Match the slide's numerical method even if the exact prefactor differs by a factor of 2 from another book — different sign conventions exist.)

---

## Source/Drain Series Resistance

Modern small-geometry devices have *non-trivial* series resistance from:
- the n+ diffusion sheet resistance ($R_{sheet}$),
- the contact between metal and silicon ($R_C$).

$$
R_{S,D}=R_C + R_{sheet}\cdot\frac{L_{contact-to-channel}}{W}
$$

This series resistance reduces effective $V_{GS}$ at the channel and therefore reduces the achievable current. In SPICE, you model it with a resistor in series with the intrinsic source/drain.

For digital hand analysis we usually fold this into an **effective on-resistance** $R_n$ or $R_p$ per unit width.

---

## Effective Switching Resistance for Delay

For an inverter or any digital gate, define the *equivalent resistance* during a transition as

$$
R_{eq}\approx\frac{V_{DD}}{2}\cdot\!\left[\frac{1}{I_{D,sat}(V_{DD})}+\frac{1}{I_{D,sat}(V_{DD}/2)}\right]^{-1}
$$

(or more often quoted as a tabulated value per minimum-size transistor, ≈ 13 kΩ for nMOS and ≈ 31 kΩ for pMOS at 2.5 V in the slide example). $R_p>R_n$ because hole mobility is lower — that is why pMOS is widened.

This $R_{eq}$ is what shows up in $t_p\approx 0.69 R_{eq}C_L$.

---

## Common Exam Mistakes

- Forgetting that overlap capacitance is *bias-independent* and should always be added.
- Counting the side facing the channel as a junction sidewall (it is not — it is the channel boundary).
- Using zero-bias $C_{j0}$ directly in delay calculations. Convert with $K_{eq}$ for the actual swing.
- Mixing units: junction area in $\mu\text{m}^2$ and $C_{j0}$ in $F/\text{cm}^2$ → factor-of-$10^{-8}$ mistakes.
- Treating gate-source capacitance as $\tfrac{2}{3}C_{ox}WL$ in linear region (the $\tfrac{2}{3}$ form belongs to *saturation*; in linear it is $\tfrac{1}{2}$ each side).

## Self-Check Questions

1. Why does $C_{GD}$ collapse to (almost) zero in saturation?
   <details><summary>Answer</summary>The drain end of the channel is pinched off — the inversion charge at $y=L$ is essentially zero — so no signal coupling exists from gate to drain through the channel. Only the *overlap* component remains.</details>

2. Why is sidewall doping $N_{A,sw}$ higher than substrate doping?
   <details><summary>Answer</summary>The channel-stop p+ implant under the field oxide is intentionally heavily doped to prevent parasitic channels between adjacent devices. So sidewall $C_{j0,sw}$ uses this higher density.</details>

3. Why is $K_{eq}<1$ for a reverse-bias swing?
   <details><summary>Answer</summary>The capacitance decreases as reverse bias grows, so the *average* capacitance over the swing is less than $C_{j0}$. $K_{eq}$ is the ratio of the average to the zero-bias value.</details>

4. Why do designers often use $C_{in}\approx C_{ox}WL_M$ as an estimate of input gate capacitance?
   <details><summary>Answer</summary>It treats the entire gate area as one parallel-plate capacitor, which is conservative and easy. For precise simulation you split into bias-dependent $C_{GS}$, $C_{GD}$, plus overlaps.</details>

## Concept Links

- Previous: [[04_mosfet_iv_characteristics]]
- Next: [[06_scaling_and_short_channel_effects]]
- Related: [[08_cmos_inverter_dynamic_behavior]] (uses $R_{eq}$, $C_L$), [[09_power_dissipation]] (uses $C_L$ in $\alpha CV^2 f$)
- Formulas: [[18_formula_sheet#capacitances-and-resistances]]
