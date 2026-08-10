# IC Fabrication Processes

> Concept: how raw silicon turns into nMOS, pMOS, and CMOS chips, and why fabrication choices show up later in circuit equations (capacitances, latch-up, well biasing, sheet resistance).

## Why Start Here

Every later topic — threshold voltage, current equations, parasitic capacitance, scaling, even logic styles — depends on what is actually built on the wafer. The professor's slides treat fabrication as the *foundation*, so questions about "which mask defines what", "why latch-up happens", and "what is the difference between p-well and twin-tub" are all fair targets.

A MOS transistor is **not** placed on a chip; it is *grown into* the silicon by repeating these basic operations:

1. Grow or deposit a layer (oxide, polysilicon, metal).
2. Cover it with photoresist.
3. Use a mask + UV light to harden a pattern.
4. Etch the unprotected regions.
5. Optionally diffuse or implant dopants.

So "designing a transistor" really means choosing where dopants go, how thick the oxide is, and where wires later connect.

## Generations of ICs (Context)

The slides explicitly define four IC eras: **SSI**, **MSI**, **LSI**, and **VLSI**. The push to VLSI is what created the need for CMOS over pure nMOS — leakage, power density, and noise immunity get worse as you cram more transistors in.

**Moore's law** (1965) — transistor count per chip doubles roughly every 18–24 months. Used in exam answers to motivate scaling and CMOS dominance.

---

## nMOS Fabrication (Step-by-Step from Slides)

This is the canonical 9-step nMOS process from the slides. Memorise the *order*; it is a common short-answer question.



1. **Wafer preparation.** A high-purity single-crystal silicon wafer is sliced thin. p-type impurities (commonly boron) are introduced as the crystal is grown so the body is uniformly doped p-type.
2. **Initial oxidation.** A thin $SiO_2$ layer is grown over the entire wafer. This protects the surface, blocks dopants in unwanted areas, and acts as the dielectric base.
3. **Photoresist coat.** A liquid photoresist is spun onto the wafer to a uniform thickness.
4. **UV exposure through mask 1.** UV light hits photoresist through openings in the mask. Areas that get exposed *polymerize* (harden); the protected (mask-shadowed) regions stay soluble. This pattern marks where source/drain diffusion and channel will eventually be created.


5. **Etch.** The unhardened resist plus the underlying $SiO_2$ are etched away, exposing the bare silicon in mask-defined windows.
6. **Thin gate oxide growth.** Remaining resist is stripped, and a *thin* $SiO_2$ layer is grown over the whole wafer. This is the gate oxide that later sets $C_{ox}$ and threshold voltage.
7. **Polysilicon deposition.** Polysilicon is deposited on top of the thin oxide. Wherever poly later crosses thin oxide over silicon, that crossing becomes a transistor gate.

8. **Source/drain diffusion.** Photoresist + a new mask define where n-type impurities (typically phosphorus or arsenic) are diffused or implanted. The polysilicon gate itself acts as a *self-aligned* mask, so the n+ source and drain stop exactly at the gate edges. This is the key reason the gate aligns to the channel without alignment error.
9. **Thick oxide + contact cuts + metal.** A thick oxide is grown, then patterned to expose contact regions. Aluminium is deposited everywhere and patterned to form interconnect.


The whole process revolves around three conducting layers separated by oxide:

- **diffusion** in the substrate (source, drain, well taps),
- **polysilicon** on top of the gate oxide (gates and short interconnect),
- **metal** insulated by oxide (signal and power routing).

## Why nMOS Alone Is Not Enough

A pure nMOS gate uses an *active load* (a depletion or enhancement nMOS pull-up). That load:

- always conducts when output is low → continuous static current → high static power,
- limits output high voltage to $V_{DD}-V_T$ → degraded noise margin,
- requires *ratioed* sizing (driver/load ratio matters).

CMOS replaces the load with a complementary pMOS network. That eliminates static current in steady state and gives full-swing rail-to-rail outputs. This is the answer to the common question: *"Why CMOS over nMOS?"*

---

## CMOS Fabrication

CMOS needs both n- and p-type transistors on the same wafer. Since one device type has to sit in a "wrong" substrate, that device is built in a **well** doped opposite to the substrate.

Four common CMOS approaches:

| Process | Substrate | Wells used | Comment |
|---|---|---|---|
| p-well | n-type | p-well for nMOS | Classic, used for lambda-based rules. |
| n-well | p-type | n-well for pMOS | Most common in modern flows. |
| Twin-tub | high-resistivity n-type | both p-well and n-well | Best device matching, more masks. |
| SOI (silicon-on-insulator) | thin Si on oxide | none in conventional sense | Lowest junction capacitance, no latch-up. |

### p-Well Process — 8 Mask Sequence (from slides)


| Mask | Defines |
|---|---|
| 1 | Deep p-well diffusion regions. |
| 2 | Thinox regions (where thick field oxide is removed and thin gate oxide is grown). |
| 3 | Polysilicon pattern (gates + local interconnect). |
| 4 | p-plus areas — combined ("anded") with mask 2 to define where p-diffusion (pMOS source/drain in n-substrate) goes. |
| 5 | n-plus areas — usually the negative of mask 4; defines n-diffusion (nMOS source/drain inside p-well). |
| 6 | Contact cuts through oxide. |
| 7 | Metal interconnect pattern. |
| 8 | Passivation (overglass) opening for bond pads. |

### n-Well Process

In n-well, the substrate is p-type (so nMOS sits directly in the substrate), and an n-well is created for the pMOS. Otherwise the mask order and operations mirror the p-well flow.

### Twin-Tub Process

Here you start with high-resistivity n-type silicon and create *both* p-well and n-well regions independently. Each device type now sits in an optimally doped well.

Advantages from the slides:

- Independent control of nMOS and pMOS doping → preserves performance of *both* device types.
- Better doping control, relaxed manufacturing tolerances.
- Helps with **latch-up** because well/substrate resistances can be tuned.

Cost: more masks and more fabrication steps.

### Body Terminal Connections (Always-True Rules)

- nMOS body → most-negative supply (usually $GND$ / $V_{SS}$).
- pMOS body → most-positive supply (usually $V_{DD}$).

Reason: this keeps the source-body and drain-body junctions reverse-biased so they only contribute small leakage and depletion capacitance, never forward conduction.

---

## Latch-up

CMOS chips contain two parasitic bipolar transistors formed by the n-well/p-substrate/n+/p+ stack: an n-p-n and a p-n-p that together form a parasitic **thyristor (SCR)** between $V_{DD}$ and $V_{SS}$.

If a transient noise pulse turns one of them on, the pair regenerates and creates a *low-resistance short* from supply to ground. The chip locks in that state until power is cycled, often destroying the part by overheating.

### Why It Happens

Each parasitic BJT has a base-resistance ($R_S$ for substrate, $R_P$ for well). If a current spike develops a voltage drop across that resistance equal to roughly one diode drop, the BJT turns on. Once both BJTs are on, the loop has gain $> 1$ and latches.

### How to Prevent It (exam-favourite list)

1. **Increase substrate doping** → lowers $R_S$ → larger spike needed to trigger.
2. **Lower $R_P$** by tight contacts to $V_{SS}$ from p-substrate / well.
3. **Guard rings** — n+ rings tied to $V_{DD}$ around pMOS, p+ rings tied to $V_{SS}$ around nMOS, to collect stray carriers before they trigger the BJTs.
4. **Trench isolation** — physically isolate p- and n- devices with deep oxide trenches.
5. **SOI** — eliminates the parasitic vertical bipolar entirely.
6. **On-chip latch-up protection circuitry** that shuts power if anomalous current is detected.

---

## Integrated Resistors and Capacitors

VLSI rarely uses discrete resistors and capacitors. Instead, designers use *layer geometry*:

### Sheet Resistance

Any thin conducting layer (diffusion, polysilicon, metal) has a *sheet resistance* $R_s$ in $\Omega/\square$ (ohms per square). The total resistance between two ends of a strip is

$$
\boxed{R = R_s \cdot \frac{L}{W}}
$$

where $L$ and $W$ are length and width in the same units. The "square" idea: a square has $L=W$, so its resistance equals $R_s$ regardless of size.

Typical relative sheet resistances in VLSI: metal (very low) < silicide < n+ diffusion < polysilicon < p-well. Wires therefore use metal; gates use polysilicon.

### Integrated Capacitors

Two main flavours:

- **Oxide capacitors** between metal/poly and substrate, or poly-over-poly. Linear, voltage-independent (other than parasitic effects). $C = \epsilon_{ox}A/t_{ox}$.
- **Junction capacitors** from reverse-biased pn-junctions. *Voltage-dependent* — capacitance decreases with reverse bias, since depletion width increases.

These choices later show up directly in delay (RC), power ($CV^2f$), and noise coupling.

### CMOS Nanotechnology — Quick Awareness

Modern technologies (sub-100 nm) replace some elements of the classic flow:

- **High-k dielectric** in place of $SiO_2$ to maintain $C_{ox}$ while preventing gate tunnelling.
- **Metal gates** (FUSI, TiN) instead of polysilicon.
- **Strained silicon** under the channel for higher mobility.
- **FinFETs / GAA-FETs** with 3-D channels for better short-channel control.

All of these are responses to limits we will study in [[06_scaling_and_short_channel_effects]].

---

## Common Exam Mistakes

- Confusing **p-well** and **n-well** processes. Track which is the *substrate* and which device sits in the *well*.
- Listing the mask sequence in the wrong order, especially mixing mask 4 (p-plus) and mask 5 (n-plus).
- Forgetting that the polysilicon gate is its own self-aligning mask for source/drain.
- Saying CMOS has zero static power. It is approximately zero — leakage exists and dominates in scaled processes.
- Calling latch-up just "a short circuit". The regenerative SCR action is what makes it latch and destructive.

## Self-Check Questions

1. Why is mask 5 usually the negative of mask 4?
   <details><summary>Answer</summary>Mask 4 defines all p+ regions (pMOS source/drain). The complementary regions are exactly where n+ diffusion is needed (nMOS source/drain), so the same physical pattern can be reused inverted, saving a mask design effort.</details>

2. In a p-well process, which transistor sits inside the well?
   <details><summary>Answer</summary>The nMOS, because it needs a p-type body. The substrate is n-type, so a p-well must be built to host the nMOS.</details>

3. What two parasitic BJTs cause latch-up?
   <details><summary>Answer</summary>A vertical n-p-n (n+ source/drain — p-substrate or p-well — n-well) and a lateral p-n-p (p+ — n-well — p-substrate). Together they form a positive-feedback SCR.</details>

4. Why does increasing substrate doping help latch-up?
   <details><summary>Answer</summary>It reduces the parasitic substrate resistance $R_S$, so spurious currents create smaller voltage drops, making it harder to forward-bias the parasitic BJT base-emitter junction.</details>

5. Why must nMOS body be tied to the most-negative supply?
   <details><summary>Answer</summary>To keep the body junction reverse-biased under all source/drain voltages. If body went above source, the body diode would forward-conduct and destroy the operating point.</details>

## Concept Links

- Next: [[02_mos_capacitor_and_operating_modes]]
- Related: [[06_scaling_and_short_channel_effects]] (limits set by fabrication), [[05_mosfet_capacitances_and_resistances]] (sheet resistance, junction capacitance)
- Formulas: [[18_formula_sheet#fabrication-and-passive-elements]]
