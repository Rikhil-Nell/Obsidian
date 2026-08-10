# Formula Sheet (Last-Minute Reference)

> Pure formula recall sheet. Each section is anchored so other notes can deep-link to it. Constants at the bottom.

## fabrication

- $\rho=\dfrac{R\cdot t\cdot W}{L}$ — resistivity from sheet measurement.
- Latch-up holding voltage: $V_{hold}\approx V_{DD}$ when $\beta_{npn}\beta_{pnp}>1$.
- Gate-oxide capacitance per area: $C_{ox}=\dfrac{\varepsilon_{ox}}{t_{ox}}$.

## mos-capacitor

- Mass-action law: $n\,p=n_i^2$.
- Bulk Fermi potential (p-type): $\phi_F=-V_T\ln(N_A/n_i)$, n-type: $+V_T\ln(N_D/n_i)$.
- Built-in potential of p-n junction: $\phi_0=V_T\ln(N_A N_D/n_i^2)$.
- Surface potential at strong inversion onset: $\phi_S=2|\phi_F|$.
- Depletion width at strong inversion: $x_{d,\max}=\sqrt{\dfrac{2\varepsilon_{Si}(2|\phi_F|)}{qN_A}}$.
- Bulk depletion charge: $Q_{B0}=\sqrt{2qN_A\varepsilon_{Si}(2|\phi_F|)}$.
- With $V_{SB}\neq 0$: $Q_B=\sqrt{2qN_A\varepsilon_{Si}(2|\phi_F|+V_{SB})}$.

## threshold-and-body-effect

- $V_{T0}=\Phi_{MS}-\dfrac{Q_{ox}}{C_{ox}}+2|\phi_F|+\dfrac{Q_{B0}}{C_{ox}}$ (nMOS, p-substrate).
- Body effect: $V_T=V_{T0}+\gamma\!\left(\sqrt{2|\phi_F|+V_{SB}}-\sqrt{2|\phi_F|}\right)$.
- Body-effect coefficient: $\gamma=\dfrac{\sqrt{2qN_A\varepsilon_{Si}}}{C_{ox}}$.
- Threshold-implant shift: $\Delta V_T=\pm\dfrac{qN_I}{C_{ox}}$ (sign depends on implant type vs substrate).

## mos-iv

- Linear: $I_D=k'\dfrac{W}{L}\!\left[(V_{GS}-V_T)V_{DS}-\dfrac{V_{DS}^2}{2}\right]\,(1+\lambda V_{DS})$.
- Saturation: $I_D=\dfrac{1}{2}k'\dfrac{W}{L}(V_{GS}-V_T)^2(1+\lambda V_{DS})$.
- $V_{DSAT}=V_{GS}-V_T$ (long channel).
- $k'=\mu C_{ox}$, $\beta=k'(W/L)$, transconductance $g_m=\partial I_D/\partial V_{GS}$.
  - Saturation: $g_m=\beta(V_{GS}-V_T)=\sqrt{2\beta I_D}$.

### short-channel (velocity saturated)

- $I_{D,sat}\approx W\, v_{sat}\, C_{ox}\,(V_{GS}-V_T-V_{DSAT}/2)$.
- $V_{DSAT}\approx \dfrac{(V_{GS}-V_T)\, \xi_c L}{(V_{GS}-V_T)+\xi_c L}$, with critical field $\xi_c=2v_{sat}/\mu$.

## capacitances

- Overlap (per side): $C_{ov}=W\,L_D\,C_{ox}$.
- Gate-channel partition (linear): $C_{gs}=C_{gd}=\frac{1}{2}WLC_{ox}$.
- Gate-channel partition (saturation): $C_{gs}=\frac{2}{3}WLC_{ox}$, $C_{gd}=0$.
- Junction (zero-bias): $C_{j0}=\sqrt{\dfrac{q\varepsilon_{Si}}{2}\dfrac{N_AN_D}{(N_A+N_D)\phi_0}}$.
- Junction (reverse biased): $C_j=\dfrac{C_{j0}}{(1-V/\phi_0)^m}$ ($m=\frac{1}{2}$ abrupt, $\frac{1}{3}$ linearly graded).
- Large-signal equivalent: $C_{eq}=K_{eq}\,C_{j0}$ with $K_{eq}=\dfrac{-\phi_0^m}{(V_2-V_1)(1-m)}\!\left[(\phi_0-V_2)^{1-m}-(\phi_0-V_1)^{1-m}\right]$.

## scaling

- Full (constant-field) scaling, factor $S>1$:
  - lengths/widths/oxide thickness ÷ $S$
  - voltages ÷ $S$
  - doping × $S$
  - power × $1/S^2$
  - delay × $1/S$
  - power-delay product × $1/S^3$
- Constant-voltage scaling: keep $V_{DD}$ fixed; current density × $S$, power density × $S^3$ (reliability disaster).
- DIBL: $\Delta V_T \approx -\eta\,V_{DS}$.
- Subthreshold swing: $S=\dfrac{kT}{q}\ln 10\,(1+C_{dep}/C_{ox})\ge 60$ mV/dec.

## cmos-inverter-dc

- Switching threshold (long channel):
  $$
  V_M=\dfrac{V_{Tn}+\sqrt{1/k_R}\,(V_{DD}-|V_{Tp}|)}{1+\sqrt{1/k_R}}\quad\text{with } k_R=\dfrac{k_n}{k_p}
  $$
- Symmetric inverter: $V_M=V_{DD}/2$ when $k_R=1$ and $V_{Tn}=|V_{Tp}|$.
- Symmetry size ratio: $(W/L)_p/(W/L)_n=\mu_n/\mu_p$.
- $V_{IL}$ (slope $-1$ in region B): $V_{IL}=\dfrac{2V_{out}+|V_{Tp}|-V_{DD}+k_R V_{Tn}}{1+k_R}$.
- $V_{IH}$ (slope $-1$ in region D): $V_{IH}=\dfrac{V_{DD}+|V_{Tp}|+k_R(2V_{out}+V_{Tn})}{1+k_R}$.
- $NM_L=V_{IL}-V_{OL}$, $NM_H=V_{OH}-V_{IH}$.
- $V_{OH}=V_{DD}$, $V_{OL}=0$ (static CMOS, ignoring leakage).

## delay-and-rc

- First-order: $t_p=0.69\,R_{eq}C_L$.
- Per transition: $t_{pHL}=0.69\,R_n C_L$, $t_{pLH}=0.69\,R_p C_L$.
- Average delay: $t_p=\frac{1}{2}(t_{pHL}+t_{pLH})$.
- Effective resistance: $R_{eq}=\dfrac{R_\square}{W/L}$ (per slide convention).
- Optimum $\beta=W_p/W_n$ for symmetric delay: $\beta_{opt}=\sqrt{R_p/R_n}$ at unit width $\approx\sqrt{\mu_n/\mu_p}$.
- Inverter chain optimum stage effort: $\hat f=e\approx 2.7$ for delay-only, FO4 ($\hat f=4$) for power+delay.
- Optimum number of stages for total fanout $F$: $N=\ln F/\ln \hat f$.

### elmore-delay

$$
t_{Elmore}=\sum_i C_i\,R_{i,\text{src-to-}i}
$$

For a chain of $n$ identical RC sections: $t_{Elmore}=\sum_{i=1}^n iRC=\dfrac{n(n+1)}{2}RC$.

## power

- Dynamic switching: $P_{dyn}=\alpha\,C_L V_{DD}^2 f$.
- Energy per transition: $E_{trans}=\frac{1}{2}C_LV_{DD}^2$ (energy to load); per cycle $E_{cycle}=C_LV_{DD}^2$.
- Activity factor at AND output: $\alpha=2p_{out}(1-p_{out})$, with $p_{out}=p_A p_B$.
- Activity factor at OR output: $\alpha=2p_{out}(1-p_{out})$, with $p_{out}=1-(1-p_A)(1-p_B)$.
- Activity factor at XOR output: $\alpha=2p_{out}(1-p_{out})$, with $p_{out}=p_A+p_B-2p_Ap_B$.
- Glitch power: ≈ ($p_{glitch}\cdot$ depth) extra dynamic.
- Short-circuit power: $P_{sc}\approx \dfrac{\beta}{12}(V_{DD}-2V_T)^3 t_r f$.
- Sub-threshold leakage: $I_{sub}=I_{D0}\dfrac{W}{L}e^{(V_{GS}-V_T)/(n V_{th})}\,(1-e^{-V_{DS}/V_{th}})$.
  - Roughly: $I_{sub}\propto e^{-V_T/(nkT/q)}$.
- DIBL leakage exponential: $I_{sub}\propto e^{(V_{DS}\eta)/(n V_{th})}$.
- Gate leakage scales with area and exponentially with $1/t_{ox}$.

### Figures of merit

- Power-delay product: $PDP=P\cdot t_p=\alpha C V_{DD}^2$ (energy per operation).
- Energy-delay product: $EDP=PDP\cdot t_p$ minimised when $V_{DD}\approx \frac{3}{2}V_T$ (at fixed throughput).

## logical-effort

- Gate delay: $d=g\cdot h+p$ (in $\tau$ units).
  - $g$ = logical effort, $h=C_{out}/C_{in}$ = electrical effort, $p$ = parasitic delay.
- Path effort: $F=GBH$ where $G=\prod g_i$, $B=\prod b_i$ (branching), $H=C_{load}/C_{in,1}$.
- Optimum stage effort: $\hat f=F^{1/N}$.
- Total path delay: $D=\sum (g_ih_i+p_i)=N F^{1/N}+P$ where $P=\sum p_i$.
- Optimum number of stages: $\hat N=\ln F/\ln \hat f$ ≈ $\ln F$ when $\hat f\approx e$.
- Standard logical efforts:
  - Inverter: $g=1$, $p=1$.
  - NAND-$n$: $g=(n+2)/3$, $p=n$.
  - NOR-$n$: $g=(2n+1)/3$, $p=n$.
  - Mux ($n$ way): $g=2$, $p=2n$.
- Sizing rule: $C_{in,i}=g_i C_{out,i}/\hat f$ (back-walk from load).

## logic-gates

- Static CMOS DeMorgan: $\overline{A\cdot B}=\bar A+\bar B$, $\overline{A+B}=\bar A\cdot\bar B$ — pull-down NAND uses series nMOS, pull-up uses parallel pMOS, etc.
- For an $n$-input NAND: nMOS each width $nW$ for unit-inverter resistance.
- Pseudo-nMOS pull-up: $V_{OL}=V_{DD}\dfrac{R_p}{R_p+R_n}$.
- Sizing for symmetric pseudo-nMOS noise margin: $k_n/k_p\gg 1$.

## dynamic-logic

- Domino stage: $d_{stage}=g_d\cdot h+p_d+t_{HL,inv}$ (precharge + evaluation + buffer).
- Charge sharing: $V_X=V_{DD}\dfrac{C_L}{C_L+C_{int}}$ when internal node $C_{int}$ at GND shares with precharged $C_L$.
- Footed dynamic gate eliminates $V_{out}=V_{DD}$ leak path; unfooted is faster but sensitive to noise.

## sequential-timing

- Setup constraint (single-cycle): $T_{clk}\ge t_{c-q}+t_{logic,\max}+t_{su}$.
- Hold constraint: $t_{c-q}+t_{logic,\min}\ge t_h$.
- Min cycle with skew $\delta$: $T_{clk}\ge t_{c-q}+t_{logic,\max}+t_{su}+\delta$ (for late capture).
- Pipelining: $N$-stage pipeline ideally improves throughput by $\approx N$, latency = $N\cdot T_{clk}$.

### registers

- Master-slave: 2 latches, $\overline\phi$/$\phi$ sets master then slave on opposite edges.
- Setup ≈ propagation through input mux, hold ≈ propagation through transmission-gate plus inverter.
- Race condition: clock skew may cause master to be transparent while slave is still transparent → both write same edge.

## memory

- 6T SRAM cell ratio constraints:
  - Read stability (cell ratio): $\dfrac{(W/L)_{driver}}{(W/L)_{access}}>1$.
  - Writeability (pull-up ratio): $\dfrac{(W/L)_{access}}{(W/L)_{loadP}}>1$.
- DRAM stored voltage: high = $V_{DD}-V_{Tn}$; refresh period $t_R\le C_s\,V_{margin}/I_{leak}$.
- DRAM read voltage shift: $\Delta V_{BL}=\dfrac{C_s(V_s-V_{BL,pre})}{C_s+C_{BL}}$.
- Decoder: $N$ address bits → $2^N$ word lines (one-hot).

## testability

- Stuck-at fault count: ≈ $2n$ for $n$ lines.
- Scan-chain length = number of registers; test vector load time = chain length × clock cycles.
- LFSR sequence length: $2^k-1$ (excluding all-zeros) for primitive-polynomial $k$-bit LFSR.
- Fault coverage = (faults detected) / (all stuck-at faults).
- BIST signature: typically a MISR; same input pattern → same signature each run.

## constants

| Symbol | Value | Units |
|---|---|---|
| $q$ | $1.602\times 10^{-19}$ | C |
| $k_B$ | $1.381\times 10^{-23}$ | J/K |
| $V_T=kT/q$ at $300\,K$ | $0.0259$ | V |
| $\varepsilon_0$ | $8.854\times 10^{-14}$ | F/cm |
| $\varepsilon_{Si}$ | $11.7\,\varepsilon_0$ | F/cm |
| $\varepsilon_{ox}$ | $3.9\,\varepsilon_0$ | F/cm |
| $n_i$ (Si, 300 K) | $1.45\times 10^{10}$ | $\text{cm}^{-3}$ |
| $E_g$ (Si) | $1.12$ | eV |
| Electron affinity Si | $4.15$ | eV |
| Aluminium work function | $4.1$ | eV |
| $\mu_n$ (long ch.) | $\approx 500$ | $\text{cm}^2/\text{V·s}$ |
| $\mu_p$ (long ch.) | $\approx 200$ | $\text{cm}^2/\text{V·s}$ |
| $v_{sat}$ (electrons) | $\approx 10^7$ | cm/s |

## conversions

- $1\,\mu\text{m}^2=10^{-8}\,\text{cm}^2$.
- $1\,\text{fF}=10^{-15}\,\text{F}$.
- $1\,\text{Å}=10^{-8}\,\text{cm}=10^{-10}\,\text{m}=0.1\,\text{nm}$.
- $1\,\text{ps}=10^{-12}\,\text{s}$.
- $kT$ at $300\,\text{K}$ ≈ $0.0259$ eV.

## Concept Links

- Theory note for each formula linked above
- Worked problems applying these formulas: [[17_worked_problems]]
- Walkthrough/verification: [[walkthrough]]
- Previous: [[17_worked_problems]]
