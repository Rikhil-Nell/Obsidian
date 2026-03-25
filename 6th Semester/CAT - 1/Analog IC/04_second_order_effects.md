# 🔧 Second-Order Effects

> **Chapter 2.3**: Body effect, channel-length modulation, and subthreshold conduction

---

## 🎯 Learning Objectives

After this module, you will be able to:
- Calculate threshold voltage with body effect
- Apply channel-length modulation to current equations
- Understand subthreshold behavior
- Know when to include each effect in calculations

---

## 1️⃣ Body Effect (Back-Gate Effect)

### What is Body Effect?

When the source-bulk voltage (VSB) is non-zero, the threshold voltage **changes**.

### The Water Tank Analogy 🚰

Imagine the bulk as a water level control:
- VSB = 0: Normal water level, normal VTH
- VSB > 0: Higher pressure from below pushes water harder, needs more gate voltage to overcome → VTH increases

### Formula

$$\boxed{V_{TH} = V_{TH0} + \gamma\left(\sqrt{2\Phi_F + V_{SB}} - \sqrt{2\Phi_F}\right)}$$

| Variable | Description | Typical Value |
|----------|-------------|---------------|
| VTH0 | Threshold at VSB = 0 | 0.3 - 0.7 V |
| γ (gamma) | Body-effect coefficient | 0.3 - 0.4 V^(1/2) |
| 2ΦF | Twice Fermi potential | 0.6 - 0.9 V |
| VSB | Source-bulk voltage | Variable |

### Body Effect Coefficient

$$\boxed{\gamma = \frac{\sqrt{2q\varepsilon_{si}N_{sub}}}{C_{ox}}}$$

### Key Insight

- **VSB > 0** → VTH **increases** (more voltage needed to turn on)
- **VSB < 0** (bulk higher than source) → VTH **decreases**

---

### Worked Example: VTH with Body Effect

**Problem**: Given VTH0 = 0.55V, γ = 0.4 V^(1/2), 2ΦF = 0.7V, VSB = 2V. Find VTH.

**Solution**:
$$V_{TH} = 0.55 + 0.4\left(\sqrt{0.7 + 2} - \sqrt{0.7}\right)$$
$$V_{TH} = 0.55 + 0.4\left(\sqrt{2.7} - \sqrt{0.7}\right)$$
$$V_{TH} = 0.55 + 0.4(1.643 - 0.837)$$
$$V_{TH} = 0.55 + 0.4(0.806) = 0.55 + 0.32$$

**Answer**: VTH = 0.87 V

---

### Calculating γ from First Principles

**Problem**: Calculate γ given Na = 8×10¹⁴ cm⁻³, tox = 120 Å

**Step 1**: Calculate Cox
$$C_{ox} = \frac{3.9 \times 8.854 \times 10^{-14}}{120 \times 10^{-8}} = 2.88 \times 10^{-7} \text{ F/cm}^2$$

**Step 2**: Calculate γ
$$\gamma = \frac{\sqrt{2 \times 1.6 \times 10^{-19} \times 1.04 \times 10^{-12} \times 8 \times 10^{14}}}{2.88 \times 10^{-7}}$$
$$\gamma = \frac{\sqrt{2.66 \times 10^{-16}}}{2.88 \times 10^{-7}} = \frac{5.16 \times 10^{-9}}{2.88 \times 10^{-7}}$$

**Answer**: γ ≈ 0.018 V^(1/2) (or as specified in problem)

---

## 2️⃣ Channel-Length Modulation (CLM)

### What is CLM?

In saturation, as VDS increases, the pinch-off point moves toward the source, **effectively shortening the channel**.

### Visual: Pinch-off Point Movement

```
VDS = VDS1:                    VDS = VDS2 > VDS1:
      Gate                          Gate
  n+═══════╲n+                  n+══════╲   n+
       L'   ↑                      L'   ↑
             pinch-off                   pinch-off
             point                       moves left!
```

### The Consequence

- Shorter effective L → Higher ID
- ID is not perfectly constant in saturation
- Finite output resistance!

### Modified Saturation Equation

$$\boxed{I_D = \frac{1}{2}\mu_n C_{ox} \frac{W}{L} (V_{GS} - V_{TH})^2 (1 + \lambda V_{DS})}$$

| Variable | Description | Typical Value |
|----------|-------------|---------------|
| λ (lambda) | CLM coefficient | 0.05 - 0.2 V⁻¹ |

### Key Relationships

- **λ ∝ 1/L**: Longer channels have smaller λ (more ideal current source)
- **λ** is larger for short-channel devices

### Output Resistance

$$\boxed{r_O = \frac{1}{\lambda I_D} \approx \frac{1 + \lambda V_{DS}}{\lambda I_D}}$$

For small λVDS:
$$r_O \approx \frac{1}{\lambda I_D}$$

---

### CLM Impact on gm

With CLM, the gm expressions become:

$$g_m = \mu_n C_{ox} \frac{W}{L}(V_{GS} - V_{TH})(1 + \lambda V_{DS})$$

$$g_m = \sqrt{2\mu_n C_{ox} \frac{W}{L} I_D (1 + \lambda V_{DS})}$$

---

## 3️⃣ Subthreshold Conduction

### What is Subthreshold?

For VGS < VTH, the transistor is not completely OFF - a small "weak inversion" current flows.

### The Leaky Faucet Analogy 🚰

Even when the valve (gate) is "closed," some water (current) drips through!

### Subthreshold Current

$$I_D = I_0 \exp\left(\frac{V_{GS}}{\xi V_T}\right)$$

where:
- I0 ∝ W/L
- ξ ≈ 1.5 (subthreshold slope factor)
- VT = kT/q ≈ 26 mV at room temperature

### Key Characteristic

- **~80 mV** decrease in VGS → ID decreases by **one decade** (10×)

### Weak Inversion gm

$$g_m = \frac{I_D}{\xi V_T}$$

> **Note**: This is similar to bipolar transistors, but with factor ξ making it slightly worse.

---

## 4️⃣ Transition: Strong to Weak Inversion

When does square-law stop and exponential start?

### Transition Overdrive

$$\boxed{(V_{GS} - V_{TH})_{transition} = 2\xi V_T \approx 80 \text{ mV}}$$

For overdrives below ~80 mV, subthreshold behavior dominates.

---

## 5️⃣ Voltage Limitations

### Gate Oxide Breakdown
- Very high VGS can destroy the thin gate oxide
- Irreversible damage!
- Modern devices: ~1.2V limit

### Punchthrough
- Very high VDS can cause drain depletion region to touch source depletion region
- Direct current path, bypassing channel control
- More severe in short-channel devices

---

## 6️⃣ When to Include Each Effect?

| Effect | Include When... | Ignore When... |
|--------|-----------------|----------------|
| Body Effect | VSB ≠ 0 | VSB = 0, bulk tied to source |
| CLM | λ is given, or need rO | λ = 0 stated, or RD ≪ rO |
| Subthreshold | VGS ≈ VTH or below | VGS - VTH > 100 mV |

---

## 🔢 Comprehensive Example

**Problem**: Calculate ID for an NMOS with body effect and CLM:
- W/L = 10, kn = 110 μA/V²
- VTH0 = 0.7V, γ = 0.08 V^(1/2), 2|ΦF| = 0.58V
- VGS = 2V, VDS = 2V, VSB = 1V
- λ = 0.1 V⁻¹

**Solution**:

**Step 1**: Calculate VTH with body effect
$$V_{TH} = 0.7 + 0.08\left(\sqrt{0.58 + 1} - \sqrt{0.58}\right)$$
$$V_{TH} = 0.7 + 0.08(1.257 - 0.762) = 0.7 + 0.04 = 0.74V$$

**Step 2**: Check region
- VGS - VTH = 2 - 0.74 = 1.26V
- VDS = 2V > 1.26V → **Saturation** ✓

**Step 3**: Calculate ID with CLM
$$I_D = \frac{1}{2} \times 110 \times 10 \times (1.26)^2 \times (1 + 0.1 \times 2)$$
$$I_D = 550 \times 1.588 \times 1.2$$
$$I_D = 1047.8 \text{ μA} \approx 1.05 \text{ mA}$$

**Step 4**: Calculate rO
$$r_O = \frac{1}{\lambda I_D} = \frac{1}{0.1 \times 1.05 \times 10^{-3}} = 9.5 \text{ kΩ}$$

---

## 🔗 Concept Links

- **Previous**: [Transconductance](03_transconductance.md)
- **Next**: [Device Models](05_device_models.md)
- **Formulas**: [Formula Sheet - Second-Order](08_formula_sheet_ultimate.md#threshold-voltage-with-body-effect)
- **Problems**: [Q2, Q3 in Worked Problems](07_worked_problems.md)

---

## ✅ Self-Check Questions

1. What happens to VTH when VSB increases?
2. Why does λ decrease for longer channel devices?
3. What is the subthreshold slope (mV/decade)?
4. Why is body effect sometimes called "back-gate effect"?
5. If rO is important in a circuit, should you use long or short channel devices?

<details>
<summary>Click for Answers</summary>

1. VTH increases (more gate voltage needed)
2. The relative change in channel length (ΔL/L) is smaller for larger L
3. ~80 mV/decade
4. Because the bulk acts like a second gate that can modulate VTH
5. Long channel (larger L → smaller λ → larger rO)

</details>
