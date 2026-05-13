# 06 Self-Refresh, Low-Power Modes, SDRAM/DDR

## Learning objectives

- Explain **why** DRAM needs refresh and what **self-refresh** buys for **standby/sleep**.
- Describe **internal oscillator**, **refresh counter**, **sequential row refresh**, **sense-and-restore**.
- Contrast **self-refresh** vs **deep power-down** (data retention vs loss).
- State **entry** command sketch: **CAS low**, **WE low** as special command, then **RAS held low** long duration.
- Quantify **RAS hold** slide rule: **> 100 µs** in self-refresh diagram narrative.
- Explain **exit**: **RAS high** returns to normal.
- One paragraph: **SDRAM** vs **DDR1** from slides.

## Ground-up explanation

### Refresh motivation

DRAM stores **charge** on a capacitor. **Leakage** reduces stored voltage; below a margin, data is **invalid** → **refresh** restores charge.

### Self-refresh concept

In **self-refresh**, the **DRAM chip** performs refresh **without external clocked commands** from the system using **on-chip** low-power control.

**Typical internal flow (slides):**

1. **Enter** self-refresh mode (control pattern; e.g. clock disable context in DDR family mentioned loosely).
2. **Halt** external clock / heavy controller activity.
3. **Internal low-power oscillator** creates timing.
4. **Refresh counter** walks **row addresses** sequentially.
5. **Row decoder** asserts one WL at a time.
6. **Sense amplifiers** read weak levels, **amplify**, **restore** full level (this is the refresh action).
7. **Cycle** until **all rows** refreshed within required **window** (slides: **5–10 ms** typical order, **device-specific**).

### Why it matters for low power

- Enables **standby / sleep**: memory controller can power down; DRAM **retains** data.
- **Low-frequency internal clock** vs fast system clock → **less switching**.
- **Temperature-aware** refresh in advanced parts trades power vs retention.

### Impact on retention

Ensures **inactive rows** still get restored (normal traffic might not touch them).

### Waveform-level story (slides)

**Key idea:** **RAS held LOW** for a **long** time (**> 100 µs** in figure commentary). Other signals **inactive / don’t care** (hatched). **No data transfer**.

**Entering:**

- Sequence cited: **CAS goes LOW**, **WE goes LOW** → interpreted as **“enter self-refresh”** command (DRAM command-set specific).
- Then **RAS goes low and stays low** → chip in **self-refresh state**.

**Internal:** oscillator, counter, automatic row refresh; external address/data/OE “don’t care”.

**Exiting:**

- **RAS returns HIGH** → leave self-refresh, normal operation resumes.

### Self-refresh vs deep power-down

Slides: **self-refresh** refreshes **all banks internally** → **data kept**. **Deep power-down** disables refresh to **minimize power** → **stored data lost**.

### SDRAM and DDR (slide bullets)

- **SDRAM:** synchronous with **system clock**; **one word per clock** (SDR); **burst** and **pipelining**.
- **DDR1:** data transferred on **both clock edges** → **double data rate** at same clock frequency; **lower voltage** / better efficiency narrative vs older SDRAM.

## Analogies

- **Self-refresh** is like a night watchman inside the warehouse who periodically checks every aisle without head office calling each time.
- **Deep power-down** is locking the warehouse and turning off security cameras to save money—nobody is watching, so inventory state is not guaranteed.

## Key formulas / numbers

$$\boxed{t_{\text{RAS,hold,self-refresh}} > 100\ \mu\text{s} \quad \text{(slide figure guidance)}}$$

$$\boxed{T_{\text{refresh,all rows}} \sim 5\text{–}10\ \text{ms} \quad \text{(typical ballpark; follow datasheet)}}$$

## Figures

- `![[LPVLSI_MODULE-5_s52_img1.jpg]]`
- `![[LPVLSI_MODULE-5_s62_img1.png]]` – self-refresh vs deep power-down contrast.

## Common mistakes

- Thinking **self-refresh** needs continuous **CAS strobing** like normal reads.
- Confusing **>100 µs RAS low** diagram with **normal** RAS pulse width in a standard access cycle.

## Self-check

1. In one sentence, why can the external clock stop during self-refresh?

<details>
<summary>Answer</summary>

An **on-chip oscillator and control** generate refresh timing **internally**, so the external interface does not need to toggle each refresh.

</details>

## Concept links

- [DRAM basics](./05_dram_basics.md)
- [Future trends](./09_future_trends.md)
