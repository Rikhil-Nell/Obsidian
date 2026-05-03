# MOS Capacitor and Operating Modes

> Concept: before the MOSFET conducts as a transistor, it behaves as a *capacitor*. Understanding accumulation, depletion, and inversion at the silicon surface is what makes threshold voltage and the I-V equations make sense.

## Why This Note Exists Separately

In the slides, half of Module 1 is spent on "MOS structure", "energy band diagram", "Fermi potential", "flat-band voltage", and "depletion thickness *before* anyone says the word transistor". The exam often asks for the **work-function difference**, **flat-band voltage**, or **depletion charge** as standalone problems (Problem 1 in the slides is exactly this). These calculations live entirely in the MOS-capacitor world.

So treat the MOSFET as a layered structure first:

```
Gate (metal or polysilicon)
─────────────  Vg
   SiO2 (oxide, dielectric)
─────────────
   Silicon substrate (p-type for nMOS)
─────────────  Body
```

The gate and the substrate are two plates; the oxide is the dielectric. With *no source and drain*, this stack is exactly a capacitor. The trick is that one plate (silicon) is a semiconductor, so its charge distribution depends strongly on the applied voltage.

---

## Mass Action Law and Carrier Concentrations

For doped silicon at equilibrium:

$$
\boxed{\,n \cdot p = n_i^2\,}
$$

- $n$ = electron concentration (cm$^{-3}$),
- $p$ = hole concentration (cm$^{-3}$),
- $n_i$ = intrinsic carrier concentration (≈ $1.45\times10^{10}$ cm$^{-3}$ for silicon at room temperature).

For a p-type substrate doped with acceptor concentration $N_A$:

$$
p_{p0}\approx N_A, \qquad n_{p0}\approx \frac{n_i^2}{N_A}
$$

For an n-type region doped with donor concentration $N_D$:

$$
n_{n0}\approx N_D, \qquad p_{n0}\approx \frac{n_i^2}{N_D}
$$

These appear in junction-built-in potential calculations, flat-band voltage, and threshold voltage derivations.

## Fermi Potential

The Fermi potential measures how far the Fermi level $E_F$ is from the intrinsic level $E_i$ inside silicon. By definition,

$$
\phi_F = \frac{E_i - E_F}{q}
$$

- For p-type silicon (Fermi level below intrinsic): $\phi_F < 0$, computed as
$$
\boxed{\,\phi_F = -\frac{kT}{q}\ln\!\left(\frac{N_A}{n_i}\right)\,}
$$
- For n-type silicon (Fermi level above intrinsic): $\phi_F > 0$,
$$
\phi_F = +\frac{kT}{q}\ln\!\left(\frac{N_D}{n_i}\right)
$$

At room temperature, $kT/q \approx 0.026$ V.

This sign convention is exactly what the slides use, and it changes sign when you cross to PMOS — common source of mistakes.

## Work Function and Built-in Voltage

The **work function** $q\phi_s$ of a material is the energy required to lift an electron from its Fermi level out into vacuum. For a doped semiconductor:

$$
q\phi_s = q\chi + (E_c - E_F)
$$

where $q\chi$ is the **electron affinity** (≈ 4.15 eV for silicon — fixed, independent of doping).

The *work-function difference* between the gate and the silicon channel surface is what creates a built-in potential across the MOS stack even with $V_G=0$:

$$
\boxed{\,\Phi_{GC} = \phi_{gate} - \phi_{Si\,(\text{channel side})}\,}
$$

If you applied an external voltage exactly equal to $\Phi_{GC}$, the energy bands at the silicon surface would not bend at all — that voltage is the **flat-band voltage** $V_{FB}$:

$$
V_{FB} = \Phi_{GC} - \frac{Q_{ox}}{C_{ox}}
$$

with $Q_{ox}$ being the equivalent fixed oxide-interface charge per unit area.

> **Worked example (Slide Problem 1, paraphrased).** A MOS structure has a p-type substrate with given $\phi_F$ and an aluminium gate ($q\phi_{Al}=4.1$ eV). Compute the built-in potential.
> 1. Compute $q\phi_s = q\chi + (E_c-E_i) - q\phi_F$ for silicon. Use $\chi=4.15$ eV.
> 2. $\Phi_{GC}=q\phi_{Al}-q\phi_s$.
> 3. $V_{FB}=\Phi_{GC}-Q_{ox}/C_{ox}$.

---

## Energy Band Diagram of MOS

When metal, oxide, and silicon are brought into physical contact, *Fermi levels must align* at equilibrium. Because $\phi_{gate}\ne\phi_{Si}$, bringing them together forces a redistribution of charge:

- a small voltage drop appears across the oxide,
- the silicon's energy bands *bend* near the surface to absorb the rest.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p25_img1.png]]
![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p27_img1.png]]

This bending is the source of all MOS behaviour. By changing $V_G$ you change the amount and direction of band bending. The three resulting regimes are accumulation, depletion, and inversion.

---

## The Three Operating Modes (under External Bias)

Take an nMOS-style stack: p-type substrate with body grounded, polarity of $V_G$ varies.

### 1. Accumulation ($V_G < V_{FB}$, negative side)

A negative gate voltage attracts holes (the majority carriers in p-type) toward the surface. Hole density at the surface exceeds the bulk equilibrium hole density.

- Surface is more *p* than the bulk.
- Energy bands bend *upward* near the surface.
- Oxide field points toward the gate.

There is no inversion layer, so no transistor channel.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p30_img1.png]]

### 2. Depletion (small positive $V_G$)

A small positive gate voltage repels holes from the surface. They leave behind ionised acceptor atoms (negative fixed charge). A region near the surface is *depleted* of mobile carriers.

- Bands bend *downward* near the surface.
- The surface potential $\phi_s$ becomes positive.
- Depletion-region charge $Q_B$ is supported entirely by ionised acceptors.

The depletion width $x_d$ depends on $\phi_s$. From the Poisson equation under the depletion approximation:

$$
\boxed{\,x_d = \sqrt{\frac{2\,\epsilon_{Si}\,\phi_s}{q\,N_A}}\,}
$$

The depletion-region *charge density per unit area* is

$$
\boxed{\,Q_B = -\sqrt{2\,q\,N_A\,\epsilon_{Si}\,\phi_s}\,}
$$

(negative for nMOS because acceptors carry negative ionised charge once holes leave).

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p31_img1.png]]

### 3. Inversion (larger positive $V_G$)

Increase $V_G$ further. The downward band bending at the surface eventually pulls the *intrinsic* level $E_i$ below the Fermi level $E_F$. At that point the surface looks **n-type** even though the bulk is p-type. Electrons (minority carriers in bulk) are attracted from the substrate to form an **inversion layer** right under the oxide.

The conventional definition: surface is **inverted** when surface electron density equals the bulk hole density, i.e. $\phi_s = -\phi_F$ (using the slide sign convention, $\phi_F<0$ for p-type, so this is a positive surface potential of magnitude $|\phi_F|$).

The condition for **strong inversion** used to define threshold voltage: $\phi_s = -2\phi_F$.

At strong inversion, depletion width hits its maximum:

$$
\boxed{\,x_{dm} = \sqrt{\frac{2\,\epsilon_{Si}\,(2|\phi_F|)}{q\,N_A}}\,}
$$

Once strong inversion is established, $x_d$ does not grow further with $V_G$ — extra gate charge is balanced by additional inversion-layer electrons rather than by widening depletion.

![[WINSEM2025-26_ECE3005_ETH_AP2025264001051_2025-12-09_Reference-Material-I_p34_img1.png]]

### Mode Cheat Table

| Mode | Surface | Bands bend | $V_G$ (nMOS) | Inversion layer? |
|---|---|---|---|---|
| Accumulation | extra holes | up | very negative | no |
| Flat-band | undisturbed | none | $V_{FB}$ | no |
| Depletion | empty of mobile carriers | down | small positive | no |
| Weak inversion | few electrons | down more | near $V_T$ | onset |
| Strong inversion | dense electron layer | $\phi_s=-2\phi_F$ | $\ge V_T$ | yes |

---

## Oxide Capacitance per Unit Area

The oxide acts as the dielectric of a parallel-plate capacitor:

$$
\boxed{\,C_{ox} = \frac{\epsilon_{ox}}{t_{ox}}\,}
$$

- $\epsilon_{ox} = \epsilon_{r,ox}\,\epsilon_0$ where $\epsilon_{r,ox}\approx 3.9$ for $SiO_2$.
- $\epsilon_0 = 8.854\times10^{-14}$ F/cm = $8.854\times10^{-12}$ F/m.

This $C_{ox}$ is the most important number in the MOS world. It appears in:

- threshold voltage (offsetting depletion charge),
- the transconductance parameter $k'=\mu C_{ox}$,
- oxide-related parasitic capacitances,
- power equations through the gate capacitance.

> **Quick numerical feel.** $t_{ox}=8\,\text{nm}$ → $C_{ox}=3.9\times8.854\times10^{-12}/(8\times10^{-9})\approx 4.3\times10^{-3}$ F/m$^2$ $\approx 4.3\,\mu\text{F/cm}^2$.

---

## Putting It Together: Voltage Distribution

The gate voltage relative to the body $V_G$ is shared among:

$$
V_G = V_{FB} + \phi_s + \frac{|Q_B|}{C_{ox}}
$$

- $V_{FB}$ undoes the built-in band bending;
- $\phi_s$ is the surface potential needed to enter the desired mode;
- $|Q_B|/C_{ox}$ supports the depletion charge.

Set $\phi_s = 2|\phi_F|$ (strong inversion) and you get the threshold voltage formula derived in [[03_threshold_voltage_and_body_effect]].

---

## Common Exam Mistakes

- Confusing *flat-band voltage* (no band bending) with *threshold voltage* (strong inversion). They are different phenomena and very different values.
- Sign of $\phi_F$. For p-type substrate, $\phi_F$ is negative; the slides write the inversion condition as $\phi_s=-\phi_F$ for weak inversion and $\phi_s=-2\phi_F$ for strong inversion.
- Treating depletion width $x_d$ as constant. It grows with $\phi_s$ until inversion clamps it at $x_{dm}$.
- Forgetting that *electron affinity* $\chi$ is fixed for silicon; only doping changes the work function.
- Mixing units: $C_{ox}$ in F/m$^2$ vs F/cm$^2$ vs F/$\mu$m$^2$ — convert carefully.

## Self-Check Questions

1. Why does the gate-substrate stack always have some band bending even at $V_G=0$?
   <details><summary>Answer</summary>The gate and substrate generally have different work functions. To align Fermi levels at equilibrium, charge must redistribute and the silicon bands bend at the surface.</details>

2. What stops the depletion region from growing forever as $V_G$ increases?
   <details><summary>Answer</summary>Once strong inversion forms, every additional gate charge is balanced by new electrons in the inversion layer rather than by widening the depletion region. So $x_d$ saturates at $x_{dm}$.</details>

3. Why does $C_{ox}$ depend only on $t_{ox}$?
   <details><summary>Answer</summary>It is the parallel-plate capacitance per unit area of the oxide alone. The dielectric constant of $SiO_2$ is fixed, so the only knob is thickness.</details>

4. Why is the inversion-layer electron concentration controlled by the gate voltage but not (much) by drain voltage at the source side?
   <details><summary>Answer</summary>The local channel charge $Q_I(y)\propto C_{ox}(V_{GS}-V_T-V_C(y))$. At $y=0$ (source end), $V_C=0$, so the gate-to-source overdrive sets the charge regardless of drain voltage. This is what makes saturation behave like a current source.</details>

## Concept Links

- Previous: [[01_fabrication_processes]]
- Next: [[03_threshold_voltage_and_body_effect]]
- Related: [[04_mosfet_iv_characteristics]] (uses inversion-layer charge), [[05_mosfet_capacitances_and_resistances]] (uses $C_{ox}$ and depletion)
- Formulas: [[18_formula_sheet#mos-capacitor]]
