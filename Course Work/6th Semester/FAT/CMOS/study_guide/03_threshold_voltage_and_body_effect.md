# Threshold Voltage and Body Effect

> Concept: derive $V_{T0}$ from physical contributions, see why it shifts with body bias, channel length, channel width, and dopant implants. This is a standard exam derivation/numerical question (Slide Problem 2 and Problem 4).

## Definition

The **threshold voltage** $V_T$ is the gate-to-source voltage at which a *strong inversion layer* forms under the oxide and the device just barely starts to conduct as a transistor. Below $V_T$ the only current is sub-threshold leakage; above $V_T$ the channel charge supports the I-V equations.

The slides distinguish:

- $V_{T0}$ — threshold voltage at zero source-to-body bias ($V_{SB}=0$).
- $V_T(V_{SB})$ — actual threshold when $V_{SB}\ne 0$ (body effect).

## Four Physical Contributions to $V_{T0}$

The slides break threshold into four pieces (this is examiner-favourite):

1. **Work-function difference $\Phi_{GC}$** between gate and channel — provides the built-in band bending offset.
2. **Surface-potential change** — the gate has to push surface potential to $-2\phi_F$ for strong inversion.
3. **Depletion-charge offset** — the gate must support the depletion charge $Q_{B0}$ in the substrate.
4. **Fixed oxide-interface charge offset** — defects and trapped charges $Q_{ox}$ at the Si/$SiO_2$ interface need to be cancelled.

Combine them in the right signs:

$$
\boxed{\,V_{T0} = \Phi_{GC} - 2\phi_F - \frac{Q_{B0}}{C_{ox}} - \frac{Q_{ox}}{C_{ox}}\,}
$$

where for an nMOS on p-type substrate ($\phi_F<0$):

- $\Phi_{GC} = \phi_{gate}-\phi_{Si}$ (work-function difference, can be positive or negative depending on gate material and doping),
- $-2\phi_F$ is positive (for p-type, $\phi_F$ is negative, so $-2\phi_F$ is a positive surface-potential target),
- $Q_{B0}$ is the depletion charge per unit area at $V_{SB}=0$, computed from
$$
\boxed{\,Q_{B0} = -\sqrt{2\,q\,N_A\,\epsilon_{Si}\,(2|\phi_F|)}\,}
$$
- $Q_{ox} = q\,N_{ox}$ where $N_{ox}$ is the fixed oxide-interface charge density per area.

### Sign Conventions (very common slip-ups)

- nMOS: $\phi_F<0$, $Q_{B0}<0$, $\gamma>0$, $V_{SB}\ge 0$, $V_{T0}>0$.
- pMOS: $\phi_F>0$, $Q_{B0}>0$, $\gamma<0$, $V_{SB}\le 0$, $V_{T0}<0$.

A pMOS threshold voltage is usually written with absolute value (|V_{TP}|) when comparing magnitudes.

---

## Body Effect (Substrate-Bias Effect)

The threshold voltage is *only* well-defined when $V_{SB}=0$. If the source is at a higher potential than the body (say, an upper transistor in a stacked NAND), the source-to-body voltage $V_{SB}>0$ widens the depletion region under the channel. That extra depletion charge needs more gate voltage to invert. So $V_T$ rises.

Generalised form from the slides:

$$
\boxed{\,V_T(V_{SB}) = V_{T0} + \gamma\!\left(\sqrt{2|\phi_F| + V_{SB}} - \sqrt{2|\phi_F|}\right)\,}
$$

with **body-effect coefficient**

$$
\boxed{\,\gamma = \frac{\sqrt{2\,q\,N_A\,\epsilon_{Si}}}{C_{ox}}\,}
$$

Units: $\gamma$ has units of $\sqrt{V}$ (typical values 0.3 – 0.6 V$^{1/2}$ for older processes, smaller in modern processes).

### Why It Matters in Logic

Stacked nMOS chains (NAND gates, dynamic gates) raise the source of upper transistors above ground. Their $V_{SB}>0$ raises their $V_T$, slows the gate, and reduces overdrive. Designers either widen those transistors or use **progressive sizing** (see [[11_static_cmos_logic]]).

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p42_img1.png]]

---

## Threshold Adjustment by Implantation

The slides note that $V_{T0}$ can be tuned post-design with channel implants:

- **For nMOS, more positive $V_T$**: implant *p-type* (acceptor) atoms in the channel — increases $|Q_{B0}|$.
- **For nMOS, less positive $V_T$**: implant *n-type* (donor) atoms — partially compensates the substrate.

The formula change is:

$$
\Delta V_T = \pm \frac{q\,N_I}{C_{ox}}
$$

where $N_I$ is the implanted dose per unit area, sign chosen by dopant type.

---

## Worked Example — Slide Problem 2 (n-channel polysilicon-gate MOSFET)

Given:
- $N_A = 10^{16}\,\text{cm}^{-3}$ (substrate),
- $N_D = 2\times10^{20}\,\text{cm}^{-3}$ (poly gate),
- $t_{ox}=500\,\AA = 50\,\text{nm}$,
- $\phi_F=0.55$ V (poly gate Fermi potential, given),
- $N_{ox}=4\times10^{10}\,\text{cm}^{-2}$,
- $V_{SB}=0$.

### Step 1 — Fermi potentials

For p-type substrate at room temperature:
$$
\phi_F^{sub}=-0.026\ln\frac{10^{16}}{1.45\times10^{10}}\approx -0.35\,\text{V}
$$

For n+ poly gate:
$$
\phi_F^{gate}=+0.026\ln\frac{2\times10^{20}}{1.45\times10^{10}}\approx +0.55\,\text{V}
$$

(matches the given +0.55 V).

### Step 2 — Work-function difference

$$
\Phi_{GC}=\phi_F^{gate}-\phi_F^{sub}=0.55-(-0.35)=0.90\,\text{V}
$$

(For metal gates use electron-affinity-based formula instead.)

### Step 3 — Oxide capacitance

$$
C_{ox}=\frac{3.9\times8.854\times10^{-14}}{50\times10^{-7}}\approx 6.9\times10^{-8}\,\text{F/cm}^2
$$

### Step 4 — Depletion charge $Q_{B0}$

$$
Q_{B0}=-\sqrt{2\,q\,N_A\,\epsilon_{Si}\,(2|\phi_F^{sub}|)}
$$

With $\epsilon_{Si}=11.7\,\epsilon_0$, $q=1.6\times10^{-19}$ C, this evaluates to roughly $-4.8\times10^{-8}$ C/cm$^2$. So $-Q_{B0}/C_{ox}\approx +0.69$ V (the slides report $-0.69$ V because of sign placement in their formula).

### Step 5 — Oxide charge term

$$
Q_{ox}/C_{ox}=q\,N_{ox}/C_{ox}\approx 0.09\,\text{V}
$$

### Step 6 — Combine

$$
V_{T0} = 0.90 - (-2\times0.35) - 0.69 - 0.09 \approx 0.40\,\text{V}
$$

(Matches the slide answer $0.90-(-0.70)-(-0.69)-0.09=0.40$ V.)

---

## Threshold Reduction Due to Short Channel ($\Delta V_{T0}$)

When the channel length $L$ becomes comparable to the source/drain junction depth $x_j$, *part* of the bulk depletion charge under the gate is actually supplied by the source/drain pn-junctions, not by the gate. The gate then needs *less* voltage to invert what is left, so $V_{T0}$ **drops**.

Modelling the depletion region as a trapezoid (slides' geometry):

$$
\Delta V_{T0} = -\frac{Q_{B0}}{C_{ox}}\cdot\frac{x_j}{L}\!\left(\sqrt{1+\frac{2\,x_{dD}}{x_j}}+\sqrt{1+\frac{2\,x_{dS}}{x_j}}-2\right)
$$

In short, $\Delta V_{T0}\propto x_j/L$ — short channels lose more threshold voltage. Combined with **DIBL** (drain reduces the source barrier), this is one half of why short-channel devices leak so much.

This is exam-tested in Slide Problem 4: plot $V_{T0}$ vs $L$, find $V_{T0}$ at $L=0.7\,\mu\text{m}$ with given parameters.

---

## Threshold Increase Due to Narrow Channel

Symmetrically: when channel width $W$ is small (comparable to $x_{dm}$), the gate has to support an *extra* fringe depletion charge spreading sideways under the field oxide. So $V_T$ **rises** as $W$ shrinks. Modelled as

$$
\Delta V_{T0}^{narrow} = \frac{|Q_{B0}|}{C_{ox}}\cdot K\cdot\frac{x_{dm}}{W}
$$

with $K$ an empirical shape coefficient.

---

## Common Exam Mistakes

- Forgetting one of the four terms in $V_{T0}$.
- Sign error on $\phi_F$ or $Q_{B0}$ for nMOS vs pMOS.
- Using $V_{T0}$ directly for stacked transistors instead of body-effect-corrected $V_T$.
- Treating $\gamma$ as dimensionless. Units are $V^{1/2}$.
- Mixing centimetre and metre units when computing $C_{ox}$.

## Self-Check Questions

1. Why does $V_T$ go *up* with $V_{SB}$ for nMOS but *down* (more negative) with $V_{BS}$ for pMOS?
   <details><summary>Answer</summary>For nMOS, raising $V_{SB}$ widens the depletion region, requiring more gate voltage to reach strong inversion → larger positive $V_T$. The same widening for pMOS makes the magnitude $|V_T|$ larger, i.e. more negative.</details>

2. Why are stacked transistors slower in logic gates?
   <details><summary>Answer</summary>The upper transistors have $V_{SB}>0$, so their $V_T$ rises, reducing overdrive $(V_{GS}-V_T)$ and increasing on-resistance. Body effect therefore slows the chain.</details>

3. Why does an n-channel implant *decrease* $V_T$?
   <details><summary>Answer</summary>The donor implant partly compensates the p-type substrate, reducing the depletion charge that must be inverted, so less gate voltage is needed.</details>

4. What dominates $V_T$ in nanoscale processes — $\Phi_{GC}$, $-2\phi_F$, or $Q_B/C_{ox}$?
   <details><summary>Answer</summary>The depletion charge term scales weakly while $V_{T0}$ stays around a few hundred mV; in deep-submicron processes the work-function term and channel implants are tuned heavily to set $V_{T0}$ low without making leakage explode.</details>

## Concept Links

- Previous: [[02_mos_capacitor_and_operating_modes]]
- Next: [[04_mosfet_iv_characteristics]]
- Related: [[06_scaling_and_short_channel_effects]] ($\Delta V_T$ vs $L$), [[11_static_cmos_logic]] (stacked-FET body effect)
- Formulas: [[18_formula_sheet#threshold-voltage-and-body-effect]]
