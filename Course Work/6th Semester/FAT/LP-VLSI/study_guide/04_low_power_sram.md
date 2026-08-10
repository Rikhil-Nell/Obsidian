# 04 Low-Power SRAM Technologies

## Learning objectives

- Split SRAM power into **active** vs **standby (data retention)** components.
- Explain **partial activation** of word lines, **pulse WL**, **pulse column/sense**, and **ATD**.
- Describe **DWL (Divided Word Line)** and the **two-level metal** requirement.
- Summarize **operating voltage reduction** ideas (low-voltage sense amp, level shifting) and **charging capacitance reduction** (divided data lines, I/O division, pre-decoding).
- Contrast SRAM vs DRAM for **on-chip voltage converters** for retention (slides: SRAM less reliant).

## Ground-up explanation

### Power categories

$$\boxed{P_{\text{total}} \approx P_{\text{active}} + P_{\text{standby}}}$$

- **Active power:** decoders, memory array switching, sense amplifiers, I/O and other peripherals during access.
- **Standby:** dominated by **effective data retention current** of **unselected** cells when sense path is largely off; other static paths called negligible in the slide summary.

### Circuit techniques (high level)

1. **Partial activation / multi-divided WL** – only energize the portion of the row that must be active; cuts **DC** that would otherwise flow in wider activation patterns.
2. **Pulse word line** – shorten **duty cycle** of WL active time to the **minimum** needed for read/write → power scales roughly with **active time / cycle time** (duty ratio argument in slides).
3. **Pulse column / sense circuitry** – same idea on the sense side; reduces **$I_{\text{DCP}}$** style contribution.
4. **ATD (Address Transition Detection)** – generates **internal pulses** when address changes → enables **self-timed** pulse control for WL and column/sense blocks.

### DWL (Divided Word Line)

- **Hierarchical decoding:** main word line + **sub-word lines**.
- Typically **two metal levels**: one for **main WL**, one for **data line** direction routing (as stated).
- **Fanout per main WL:** commonly **4** sub-word lines (up to **8**) – **area vs decoder area** compromise.

### Operating voltage reduction

- Low $V_{\text{DD}}$: if data-line signal near $V_{\text{DD}}$ must be amplified, **PMOS loads without $V_T$ drop** may be preferable; may need **level shifting** on data-line voltages.

### Charging capacitance reduction

Techniques originally for **speed** also save **dynamic power** (especially relevant to DRAMs in text, but listed for SRAM context):

- **Data-line division**
- **I/O line division**
- **Pre-decoding** between address buffer and final decoder (common commercial SRAM practice)

### Data retention power reduction

Slides: **on-chip voltage converters** common in DRAM; in SRAM, **wider voltage margin** and different cell principle mean **cell improvements alone** often suffice to reduce retention current (fewer wide-spread converter approaches).

## Analogies

- **Pulsing WL** is like only holding a refrigerator door open the seconds you need, not the whole minute you are in the kitchen.
- **DWL** is like a building with floor lights: only the floor you use lights up.

## Key formulas

Duty-ratio intuition for pulsed WL:

$$\boxed{P_{\text{WL-related}} \propto \frac{t_{\text{WL-on}}}{T_{\text{cycle}}}}$$

Dynamic power scaling (bit-line capacitance switching):

$$\boxed{P_{\text{dyn}} \propto C V^2 f}$$

See [Formula sheet](./11_formula_sheet_ultimate.md).

## Figures

- `![[LPVLSI_MODULE-5_s34_img1.png]]` – DWL / hierarchical concept.
- `![[LPVLSI_MODULE-5_s35_img1.png]]` – pulsed WL idea.

## Common mistakes

- Attributing **refresh** power to SRAM (SRAM has **no refresh**).
- Forgetting **ATD** ties pulses to **address transitions**, not only external clock edges.

## Self-check

1. Name two blocks that dominate **active** SRAM power according to the slides.

<details>
<summary>Answer</summary>

Row/column decoders, memory array, sense amplifiers, I/O buffers (any subset consistent with “sum of these” wording).

</details>

## Concept links

- [SRAM basics](./02_sram_architecture_and_cell.md)
- [Precharge](./03_precharge_and_equalization.md) (bit-line $CV^2f$)
