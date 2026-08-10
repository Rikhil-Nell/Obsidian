# 03 Precharge and Equalization Circuits

## Learning objectives

- State **why** bit lines are precharged before read.
- Explain **equalization** ($\Phi_{\text{eq}}$) and why BL and $\overline{\text{BL}}$ are shorted to one potential.
- Compare **nMOS precharge** vs **pMOS precharge** (threshold drop vs full swing to $V_{\text{DD}}$).
- Describe **WL timing** relative to precharge (WL low to isolate cell during precharge).

## Ground-up explanation

### Purpose of precharge

Before a **read**, BL and $\overline{\text{BL}}$ must start from a **known** voltage. That lets the **selected cell** create a **predictable small differential** after WL turns on, which the **sense amplifier** can detect quickly and reliably.

### Equalization

**Equalization** connects BL and $\overline{\text{BL}}$ (via a device controlled by e.g. $\Phi_{\text{eq}}$) so any **residual voltage difference** from a previous cycle is **removed**. Both lines are forced to the **same potential** before evaluation.

### nMOS precharge (Figure a in slides)

- nMOS devices pull bit lines toward high, but an nMOS passing a strong **1** suffers **threshold voltage drop** → bit lines may not reach full $V_{\text{DD}}$.
- **Consequence:** reduced **noise margin** and incomplete high level vs ideal full-rail precharge.

### pMOS precharge (Figure b)

- pMOS devices pull BL / $\overline{\text{BL}}$ **up toward $V_{\text{DD}}$** without the same “weak 1” problem as nMOS pull-up through pass device semantics in this context.
- **Advantage:** **full voltage swing** on bit lines, **better read stability** / margin for sensing.

### Word line during precharge

**WL is kept low** during precharge so the **memory array is isolated** from bit lines while they charge and equalize. After precharge completes, **WL goes high** to connect the selected row; cell data develops **differential** on BL / $\overline{\text{BL}}$.

### Power and trade-offs

Precharge toggles **large bit-line capacitance** → **dynamic power**, but **improves speed and accuracy** of read.

| Style | Pros | Cons |
|-------|------|------|
| nMOS | Simpler, possibly smaller area | $V_T$ drop, worse margin |
| pMOS | Full high level, better sensing | Slightly more area / complexity |

### Alternate narrative in slides ($V_{\text{DD}}/2$)

Some slides also describe schemes where nMOS precharge targets **mid-rail** (e.g. $V_{\text{DD}}/2$) for **differential sensing**; same theme: **equalizer** removes offset, **$\Phi_n$ / $\Phi_p$** style signals enable precharge networks.

## Analogies

- **Equalization** is like zeroing two scales to the same mark before weighing a tiny difference.
- **nMOS weak high** is like inflating a balloon through a narrow valve: you may not reach full pressure.

## Key formulas

Threshold drop theme (not always a single boxed equation in slides):

$$\boxed{V_{\text{BL,max}} \approx V_{\text{DD}} - V_T \quad \text{(nMOS passing high, simplified view)}}$$

Full swing precharge high level with pMOS pull-up toward $V_{\text{DD}}$:

$$\boxed{V_{\text{BL}} \rightarrow V_{\text{DD}} \quad \text{(ideal pMOS precharge to rail)}}$$

See [Formula sheet](./11_formula_sheet_ultimate.md#threshold-and-swing).

## Figures

- `![[LPVLSI_MODULE-5_s23_img1.png]]`
- `![[LPVLSI_MODULE-5_s27_img1.png]]`

## Common mistakes

- Asserting **WL high** during precharge for the selected row in a hand-drawn timing sketch (wrong for **array** precharge phase as taught: **keep cell off** until BL settled).
- Claiming nMOS and pMOS precharge are identical for noise margin.

## Self-check

1. One sentence: why equalize BL and $\overline{\text{BL}}$?

<details>
<summary>Answer</summary>

To **cancel residual differential** from the prior operation so sensing starts from a **clean common-mode** point.

</details>

## Concept links

- [SRAM cell](./02_sram_architecture_and_cell.md)
- [Low-power SRAM](./04_low_power_sram.md) (bit-line switching power)
