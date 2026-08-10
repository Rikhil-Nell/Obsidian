# 10 Worked Problems (Exam-Style)

The extraction reported **no** formal end-of-chapter problems in `problems.json`. Below are **original practice items** aligned to slide concepts and linked to your topic notes.

---

## Problem 1 – SRAM read polarity

**Prompt:** Before read, BL and $\overline{\text{BL}}$ are precharged high. A selected 6T cell holds **A = 1**, **B = 0**. After WL rises, which line tends to fall slightly, and what logic does **BL > $\overline{\text{BL}}$** map to after sensing?

**Concepts used:** [SRAM 6T read](./02_sram_architecture_and_cell.md), [Precharge](./03_precharge_and_equalization.md)

**Formulas used:** None beyond qualitative conduction paths.

**Solution:**

1. Access devices connect cell to BL / $\overline{\text{BL}}$.
2. With **A high**, the network that can discharge **BL** through NMOS paths (as per slide story) pulls **BL** slightly down while $\overline{\text{BL}}$ stays relatively higher.
3. Sense mapping given: **BL > $\overline{\text{BL}}$** → **logic 1**.

$$\boxed{\text{BL dips slightly; BL} > \overline{\text{BL}} \Rightarrow \text{read } 1}$$

**Verification:** Consistent with stored **1** at node A in the slide narrative.

---

## Problem 2 – nMOS vs pMOS precharge margin

**Prompt:** Explain in two sentences why **pMOS precharge to $V_{\text{DD}}$** can improve read noise margin compared to **nMOS pull-up** precharge toward a high level.

**Concepts used:** [Precharge](./03_precharge_and_equalization.md)

**Formulas used:** [Threshold drop](./11_formula_sheet_ultimate.md#threshold-and-swing)

**Solution:**

1. nMOS passing a high level incurs approximately **one threshold drop**, so BL may not reach full $V_{\text{DD}}$, shrinking sensing margin.
2. pMOS precharge pulls bit lines **high toward rail** without that nMOS weak-high limitation → **larger reliable high level** before WL evaluation.

$$\boxed{\text{pMOS precharge yields stronger high level vs typical nMOS high-pass weakness}}$$

---

## Problem 3 – DRAM destructive read

**Prompt:** Why must a **read** be followed by **restore** in DRAM?

**Concepts used:** [DRAM basics](./05_dram_basics.md)

**Formulas used:** [Charge sharing intuition](./11_formula_sheet_ultimate.md)

**Solution:**

1. Connecting $C_S$ to precharged $C_{\text{BL}}$ **redistributes charge**.
2. The read operation disturbs the stored level to a small-signal interpretation; sense amplifiers **re-write** full level to the cell (refresh row context).

$$\boxed{\text{Read is destructive to stored level; restore rewrites full charge}}$$

---

## Problem 4 – Self-refresh RAS hold

**Prompt:** Per module slides, what **minimum order** of **RAS low duration** indicates deep internal self-refresh, and what is **lost** in **deep power-down** instead?

**Concepts used:** [Self-refresh](./06_self_refresh_and_related.md)

**Solution:**

1. Slides cite **RAS held LOW > 100 µs** as long self-refresh indication in the timing figure commentary.
2. **Deep power-down** disables refresh to save power → **data not retained**.

$$\boxed{t_{\text{RAS}} > 100\ \mu\text{s} \text{ (slide rule-of-thumb)};\ \text{DPD} \Rightarrow \text{data lost}}$$

---

## Problem 5 – 1 Kbit ROM addressing math

**Prompt:** A 1 Kbit ROM is **256 × 4**. How many address bits? How many word lines if **5** bits are used for row decode?

**Concepts used:** [ROM organization](./07_rom_basics.md)

**Formulas used:** $\log_2$ word count.

**Solution:**

1. Words = 256 → address bits $= \log_2(256) = 8$.
2. Row bits = 5 → $2^5 = 32$ word lines.

$$\boxed{8\text{ address bits};\ 32\ \text{word lines}}$$

**Verification:** $2^5 \cdot (\text{columns per row structure}) = 32 \times 32$ array story matches **1024** bits total with 4-bit wide read grouping as in slides.

---

## Problem 6 – ROM dynamic power scaling

**Prompt:** If supply drops from **3.3 V** to **1.8 V** (same frequency, same activity factor, capacitance unchanged), approximate the **dynamic power** ratio $P_2/P_1$ using the quadratic rule.

**Concepts used:** [Low-power ROM](./08_low_power_rom_techniques.md)

**Formulas used:** [Dynamic power](./11_formula_sheet_ultimate.md#dynamic-power-scaling)

**Solution:**

$$\frac{P_2}{P_1} \approx \left(\frac{1.8}{3.3}\right)^2 \approx 0.545^2 \approx 0.30$$

$$\boxed{P_2 \approx 0.30\, P_1 \quad (\text{about } 70\%\ \text{reduction})}$$

**Unit tracking:** voltages in volts cancel in ratio; result dimensionless.

---

## Problem 7 – SDRAM vs DDR data rate (qualitative)

**Prompt:** DDR1 achieves higher **data rate** than SDRAM at the **same clock frequency**—how?

**Concepts used:** [Self-refresh / SDRAM note](./06_self_refresh_and_related.md)

**Solution:** DDR samples output on **both** clock edges; SDRAM transfers on **one** edge per cycle (SDR).

$$\boxed{\text{DDR effective data rate } \approx 2\times \text{ SDRAM for same } f_{\text{clk}}}$$
