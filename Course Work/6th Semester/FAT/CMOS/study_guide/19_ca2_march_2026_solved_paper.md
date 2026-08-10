# CA2 March 2026 Solved Paper

> Paper: ECE3005 CMOS VLSI Design, Continuous Assessment Test - 2, March 2026.  
> Use this as an exam-style worked solution sheet: formula first, intuition second, calculation step-by-step.

## Quick Coverage Map

| Question | Main topic | Already covered in notes |
| --- | --- | --- |
| Q1 | System-level dynamic switching power | [[09_power_dissipation]], [[17_worked_problems#problem-8--system-on-chip-dynamic-power]], [[18_formula_sheet#power]] |
| Q2 | Static CMOS inverter properties and delay minimisation | [[07_cmos_inverter_vtc_and_noise_margins]], [[08_cmos_inverter_dynamic_behavior]], [[11_static_cmos_logic]], [[18_formula_sheet#delay-and-rc]] |
| Q3 | Logical effort path delay and sizing | [[10_logical_effort]], [[17_worked_problems#problem-11--logical-effort-multistage-path-sizing]], [[18_formula_sheet#logical-effort]] |
| Q4(a) | Logical effort of complex CMOS gate | [[10_logical_effort]], [[11_static_cmos_logic]] |
| Q4(b) | NAND vs NOR speed comparison | [[10_logical_effort]], [[11_static_cmos_logic]], [[18_formula_sheet#logical-effort]] |
| Q5 | 3-input NOR rise/fall delay | [[10_logical_effort]], [[11_static_cmos_logic]], [[08_cmos_inverter_dynamic_behavior]] |

---

## Q1. Switching Power of the 2 V, 65 nm SoC

### Question Data

Given:

- Supply voltage: $V_{DD}=2\,\text{V}$
- Technology: 65 nm, drawn channel length $=50\,\text{nm}$, $\lambda=25\,\text{nm}=0.025\,\mu\text{m}$
- Total transistors: $1\times10^9$
- Logic transistors: $50\times10^6$
- Memory transistors: $950\times10^6$
- Logic average width: $12\lambda=12(0.025)=0.3\,\mu\text{m}$
- Memory average width: $4\lambda=4(0.025)=0.1\,\mu\text{m}$
- Memory divided into 10 banks, only one bank active
- Logic activity factor: $\alpha_{logic}=0.2$
- Memory activity factor in active bank: $\alpha_{mem}=0.02$
- Gate capacitance: $1\,\text{fF}/\mu\text{m}$
- Diffusion capacitance: $0.9\,\text{fF}/\mu\text{m}$
- Total capacitance per width:

$$
C' = 1+0.9 = 1.9\,\text{fF}/\mu\text{m}
$$

- Clock frequency: $f=1\,\text{GHz}=10^9\,\text{Hz}$

### Formula

Dynamic switching power:

$$
\boxed{P_{dyn}=\alpha C_L V_{DD}^2 f}
$$

For multiple blocks:

$$
\boxed{P_{total}=\left(\sum_i \alpha_i C_i\right)V_{DD}^2f}
$$

Capacitance from transistor width:

$$
\boxed{C=N\cdot W\cdot C'}
$$

where:

- $N$ = number of transistors,
- $W$ = average transistor width in $\mu\text{m}$,
- $C'$ = capacitance per unit width.

### Intuition

This is not a device-physics problem. It is a bookkeeping problem:

1. Convert transistor widths from $\lambda$ to $\mu\text{m}$.
2. Find the physical capacitance contributed by logic and memory.
3. Multiply each capacitance by its activity factor.
4. Multiply the final active capacitance by $V_{DD}^2f$.

Memory has far more transistors, but most memory is idle because only one bank is selected. That is why the bank factor matters.

### Step 1: Logic Capacitance

$$
C_{logic}=N_{logic}\cdot W_{logic}\cdot C'
$$

$$
C_{logic}=(50\times10^6)(0.3)(1.9\,\text{fF})
$$

$$
C_{logic}=28.5\times10^6\,\text{fF}
$$

Since $10^6\,\text{fF}=1\,\text{nF}$:

$$
\boxed{C_{logic}=28.5\,\text{nF}}
$$

Active logic capacitance:

$$
\alpha_{logic}C_{logic}=0.2(28.5)=5.7\,\text{nF}
$$

### Step 2: Memory Capacitance

Total memory capacitance:

$$
C_{mem,total}=(950\times10^6)(0.1)(1.9\,\text{fF})
$$

$$
C_{mem,total}=180.5\times10^6\,\text{fF}
$$

$$
\boxed{C_{mem,total}=180.5\,\text{nF}}
$$

Only one of ten banks is active:

$$
C_{mem,active\,bank}=\frac{180.5}{10}=18.05\,\text{nF}
$$

Active switching capacitance of the selected memory bank:

$$
\alpha_{mem}C_{mem,active\,bank}=0.02(18.05)=0.361\,\text{nF}
$$

### Step 3: Total Active Capacitance

$$
\sum \alpha C = 5.7 + 0.361
$$

$$
\boxed{\sum \alpha C = 6.061\,\text{nF}}
$$

### Step 4: Switching Power

$$
P=(6.061\times10^{-9})(2)^2(10^9)
$$

$$
P=6.061\times4
$$

$$
\boxed{P_{dyn}\approx24.24\,\text{W}}
$$

### Final Answer

$$
\boxed{P_{switching}\approx24.2\,\text{W}}
$$

If the memory bank factor were ignored, memory active capacitance would be $0.02(180.5)=3.61\,\text{nF}$ and the answer would become:

$$
P=(5.7+3.61)\times4\approx37.2\,\text{W}
$$

But the phrase "only the necessary bank is activated" means the correct exam answer should include the divide-by-10 bank factor.

### Q3 Exam Traps

- Do not forget $V_{DD}^2$. Here $V_{DD}=2\,\text{V}$, so power is multiplied by 4.
- Convert $\lambda$ correctly: $25\,\text{nm}=0.025\,\mu\text{m}$.
- Use both gate and diffusion capacitance: $1+0.9=1.9\,\text{fF}/\mu\text{m}$.
- Apply memory banking before activity factor.

---

## Q2. Static CMOS Inverter Properties and Delay Minimisation

### What Is a Static CMOS Inverter?

A static CMOS inverter uses:

- one pMOS pull-up transistor connected to $V_{DD}$,
- one nMOS pull-down transistor connected to ground,
- both gates tied together as the input,
- both drains tied together as the output.

For $V_{in}=0$:

- nMOS is OFF,
- pMOS is ON,
- output is pulled to $V_{DD}$.

For $V_{in}=V_{DD}$:

- nMOS is ON,
- pMOS is OFF,
- output is pulled to 0.

So the Boolean function is:

$$
\boxed{V_{out}=\overline{V_{in}}}
$$

### Important Properties of a Static CMOS Inverter

#### 1. Rail-to-Rail Output

Static CMOS gives:

$$
\boxed{V_{OH}=V_{DD}}
$$

$$
\boxed{V_{OL}=0}
$$

This is because pMOS passes a strong 1 and nMOS passes a strong 0.

#### 2. Very Low Static Power

In steady state, one transistor is OFF:

- input 0: nMOS OFF,
- input 1: pMOS OFF.

So ideally there is no direct DC path from $V_{DD}$ to ground.

Ideal static power:

$$
\boxed{P_{static}\approx0}
$$

Real static power:

$$
\boxed{P_{static}=V_{DD}I_{leak}}
$$

Leakage exists due to subthreshold current, junction leakage, gate leakage, DIBL, etc.

#### 3. High Input Impedance

The input terminal is connected to MOS gates, which are insulated by oxide. Therefore DC input current is almost zero:

$$
\boxed{I_{in}\approx0}
$$

This allows one gate to drive many other gates, limited mainly by capacitance rather than DC current.

#### 4. Regenerative Switching

The voltage transfer characteristic has a steep transition region. Small changes around the switching threshold produce large output changes.

Switching threshold:

$$
\boxed{V_M:\;V_{in}=V_{out}}
$$

For a symmetric inverter:

$$
\boxed{V_M\approx\frac{V_{DD}}{2}}
$$

This gives good noise immunity.

#### 5. Good Noise Margins

Noise margins:

$$
\boxed{NM_L=V_{IL}-V_{OL}}
$$

$$
\boxed{NM_H=V_{OH}-V_{IH}}
$$

For static CMOS:

$$
V_{OH}=V_{DD},\qquad V_{OL}=0
$$

So:

$$
NM_L=V_{IL}
$$

$$
NM_H=V_{DD}-V_{IH}
$$

#### 6. Dynamic Power Dominates During Switching

Every time the output load capacitance charges and discharges:

$$
\boxed{P_{dyn}=\alpha C_LV_{DD}^2f}
$$

This is the main active power term.

#### 7. Finite Propagation Delay

The output capacitance must be charged or discharged through transistor resistance.

Falling delay:

$$
\boxed{t_{pHL}=0.69R_nC_L}
$$

Rising delay:

$$
\boxed{t_{pLH}=0.69R_pC_L}
$$

Average delay:

$$
\boxed{t_p=\frac{t_{pHL}+t_{pLH}}{2}}
$$

### How to Minimise Propagation Delay

The key formula is:

$$
\boxed{t_p\approx0.69R_{eq}C_L}
$$

So delay can be reduced by reducing $R_{eq}$ or reducing $C_L$.

#### Step 1: Reduce Load Capacitance

Load capacitance includes:

$$
\boxed{C_L=C_{gate,fanout}+C_{diffusion}+C_{wire}+C_{overlap}}
$$

Ways to reduce it:

- reduce fanout,
- reduce wire length,
- use compact layout,
- reduce diffusion area,
- avoid unnecessary large transistor sizes.

This is usually the cleanest delay improvement because it also reduces power.

#### Step 2: Increase Driver Width Carefully

MOS resistance roughly follows:

$$
\boxed{R_{eq}\propto\frac{1}{W}}
$$

So widening transistors reduces resistance.

But width also increases capacitance:

$$
\boxed{C_{gate}\propto W,\qquad C_{diffusion}\propto W}
$$

Therefore, sizing helps only until self-loading dominates. Oversizing wastes area and power.

#### Step 3: Balance nMOS and pMOS Strength

Because electron mobility is higher than hole mobility:

$$
\mu_n>\mu_p
$$

pMOS must be wider to match nMOS resistance.

For equal rise and fall delay:

$$
\boxed{\frac{W_p}{W_n}\approx\frac{\mu_n}{\mu_p}}
$$

Often:

$$
\boxed{\frac{W_p}{W_n}\approx2\text{ to }3}
$$

For minimum average delay, not necessarily perfect symmetry:

$$
\boxed{\beta_{opt}=\sqrt{r}}
$$

where:

$$
r=\frac{R_{p,unit}}{R_{n,unit}}
$$

#### Step 4: Use Tapered Buffers for Large Loads

If a tiny inverter drives a huge capacitance, delay is large. Use an inverter chain with gradually increasing size.

Total electrical effort:

$$
\boxed{F=\frac{C_L}{C_{in,1}}}
$$

Optimum stage effort:

$$
\boxed{f=F^{1/N}}
$$

Practical rule:

$$
\boxed{f\approx4}
$$

This is the fanout-of-4 rule.

#### Step 5: Reduce Fan-In in Logic Gates

Large fan-in gates have series stacks. Series transistors increase resistance.

For an $n$-input NAND pull-down:

$$
t_{pHL}\propto n^2
$$

Large gates should be broken into smaller gates using a tree structure.

#### Step 6: Reorder Inputs in Stacks

If one input arrives late, place it closest to the output node. This reduces the capacitance that the late input must discharge.

This is important for NAND/NOR stacks and appears in [[11_static_cmos_logic]].

### Exam-Style Summary

Static CMOS inverter is popular because it has:

- full swing output,
- high noise margins,
- almost zero static power,
- high input impedance,
- simple layout,
- robust operation.

Delay is minimised by:

- reducing $C_L$,
- reducing $R_{eq}$ by sizing,
- balancing nMOS/pMOS strengths,
- using tapered buffers,
- reducing fan-in,
- using logical effort for stage sizing.

---

## Q3. Logical Effort: Path from A to B

Corrected figure interpretation:

- input $A$ enters one 2-input NOR gate whose input capacitance is labelled 9,
- the output of this first gate drives three identical 2-input NOR gates labelled $x$,
- only one of those $x$ gates is on the path to $B$,
- that on-path $x$ gate drives two identical 2-input NOR gates labelled $y$,
- only one of those $y$ gates is on the path to $B$,
- the output $B$ drives a capacitive load of 55.

So the A-to-B path is:

$$
A\rightarrow NOR2(9)\rightarrow NOR2(x)\rightarrow NOR2(y)\rightarrow B
$$

There are two branching points:

1. first gate output drives three $x$ gates, so $b_1=3$,
2. on-path $x$ output drives two $y$ gates, so $b_2=2$.

### Formula List

Logical effort delay of one gate:

$$
\boxed{d=gh+p}
$$

Path logical effort:

$$
\boxed{G=\prod g_i}
$$

Path electrical effort:

$$
\boxed{H=\frac{C_{out}}{C_{in}}}
$$

Branching effort:

$$
\boxed{b=\frac{C_{onpath}+C_{offpath}}{C_{onpath}}}
$$

Path effort:

$$
\boxed{F=GBH}
$$

Best stage effort:

$$
\boxed{\hat f=F^{1/N}}
$$

Minimum path delay:

$$
\boxed{D_{min}=N\hat f+P}
$$

where:

$$
\boxed{P=\sum p_i}
$$

Sizing equation:

$$
\boxed{C_{in,i}=\frac{g_iC_{out,i}}{\hat f}}
$$

If branching exists after a stage:

$$
\boxed{C_{in,i}=\frac{g_i b_i C_{onpath}}{\hat f}}
$$

### Logical Effort Values

Every gate on the A-to-B path is a 2-input NOR.

$$
\boxed{g_{NOR2}=\frac{2(2)+1}{3}=\frac{5}{3}}
$$

$$
\boxed{p_{NOR2}=2}
$$

### Part (a): Minimum Delay from A to B

#### Step 1: Number of Stages

Path:

$$
A\rightarrow NOR2\rightarrow NOR2\rightarrow NOR2\rightarrow B
$$

So:

$$
\boxed{N=3}
$$

#### Step 2: Path Logical Effort

$$
G=g_{NOR2}\cdot g_{NOR2}\cdot g_{NOR2}
$$

$$
G=\left(\frac{5}{3}\right)^3
$$

$$
\boxed{G=\frac{125}{27}\approx4.63}
$$

#### Step 3: Path Electrical Effort

Input capacitance at $A$:

$$
C_{in}=9
$$

Final output load:

$$
C_L=55
$$

Therefore:

$$
H=\frac{55}{9}
$$

$$
\boxed{H=6.11}
$$

#### Step 4: Branching Effort

At the output of the first NOR gate, the signal drives three identical $x$ gates:

- one $x$ gate is on the A-to-B path,
- two $x$ gates are off-path.

Thus:

$$
b_1=\frac{x+x+x}{x}=3
$$

At the output of the on-path $x$ gate, the signal drives two identical $y$ gates:

- one $y$ gate is on the A-to-B path,
- one $y$ gate is off-path.

Thus:

$$
b_2=\frac{y+y}{y}=2
$$

Total branching effort:

$$
B=b_1b_2=3\cdot2
$$

$$
\boxed{B=6}
$$

#### Step 5: Path Effort

$$
F=GBH
$$

$$
F=\frac{125}{27}\cdot6\cdot\frac{55}{9}
$$

$$
F\approx169.75
$$

$$
\boxed{F\approx170}
$$

#### Step 6: Best Stage Effort

$$
\hat f=F^{1/N}
$$

$$
\hat f=(169.75)^{1/3}
$$

$$
\boxed{\hat f\approx5.53}
$$

This is larger than the usual FO4 target. That means the path is carrying a fairly heavy branched load for only three stages, but since the figure fixes the number of gates, this is the minimum delay for that path topology.

#### Step 7: Parasitic Delay

$$
P=p_{NOR2}+p_{NOR2}+p_{NOR2}
$$

$$
P=2+2+2
$$

$$
\boxed{P=6}
$$

#### Step 8: Minimum Delay

$$
D_{min}=N\hat f+P
$$

$$
D_{min}=3(5.53)+6
$$

$$
D_{min}=16.59+6
$$

$$
\boxed{D_{min}\approx22.6\tau}
$$

### Part (b): Choose Transistor Sizes

We size backward from the load.

#### Step 1: Size the Final 2-Input NOR, $y$

Final load:

$$
C_L=55
$$

For the final NOR2:

$$
C_{in,NOR2}=\frac{g_{NOR2}C_L}{\hat f}
$$

$$
y=\frac{(5/3)(55)}{5.53}
$$

$$
y\approx16.6
$$

So:

$$
\boxed{y\approx16.6}
$$

Use a practical rounded value:

$$
\boxed{y\approx17}
$$

#### Step 2: Size the Middle 2-Input NOR, $x$

The output of the on-path $x$ gate drives two identical $y$ gates:

$$
C_{total}=2y=2(16.6)=33.2
$$

For the $x$ NOR2:

$$
x=\frac{g_{NOR2}C_{total}}{\hat f}
$$

$$
x=\frac{(5/3)(33.2)}{5.53}
$$

$$
x\approx10.0
$$

So:

$$
\boxed{x\approx10}
$$

Use a practical rounded value:

$$
\boxed{x\approx10}
$$

#### Step 3: Verify Input Capacitance

The first NOR gate labelled 9 drives three identical $x$ gates.

Total load after the first gate:

$$
3x=3(10)=30
$$

Stage effort of the first NOR:

$$
h_1=\frac{30}{9}=3.33
$$

Because the first gate is NOR2:

$$
f_1=g_{NOR2}h_1=\frac{5}{3}(3.33)\approx5.55
$$

This matches $\hat f\approx5.53$, so the sizing is consistent.

### Final Answers

$$
\boxed{D_{min}\approx22.6\tau}
$$

$$
\boxed{x\approx10,\qquad y\approx16.6\text{ or }17}
$$

### Exam Traps

- Do not forget both branching efforts: $3$ at the $x$ fanout and $2$ at the $y$ fanout.
- NOR gates have larger logical effort than NAND gates.
- Size backward from the load, not forward from the input.
- Include parasitic delay $P$, not just effort delay $N\hat f$.

---

## Q4(a). Logical Effort of $U=XYZ+W$

### First Understand the Logic

The function is:

$$
\boxed{U=XYZ+W}
$$

Static CMOS naturally implements inverted logic efficiently. So first consider:

$$
\overline{U}=\overline{XYZ+W}
$$

Using De Morgan's theorem:

$$
\overline{U}=\overline{XYZ}\cdot\overline{W}
$$

$$
\boxed{\overline{U}=(\overline X+\overline Y+\overline Z)\overline W}
$$

The pull-down network for $\overline{U}$ conducts when:

$$
XYZ+W=1
$$

So the nMOS PDN is:

- one branch with $X,Y,Z$ in series,
- in parallel with one nMOS controlled by $W$.

The pMOS PUN is the dual:

- pMOS $W$ in series with
- pMOS $X,Y,Z$ in parallel.

This complex CMOS gate directly produces $\overline U$. To get $U$, add an inverter.

### Sizing for Equal Drive

Use the standard reference inverter:

- nMOS width = 1,
- pMOS width = 2,
- input capacitance = $1+2=3$ units.

#### nMOS Sizing

The $XYZ$ branch has 3 nMOS in series. To match unit inverter pull-down resistance, each nMOS should be 3 times wider:

$$
W_{nX}=W_{nY}=W_{nZ}=3
$$

The $W$ nMOS branch has only one transistor, so:

$$
W_{nW}=1
$$

#### pMOS Sizing

The pull-up path has two pMOS in series:

- pMOS $W$,
- one of pMOS $X,Y,Z$.

A reference pMOS has width 2. For two pMOS in series, each should be doubled:

$$
W_p=2\times2=4
$$

So:

$$
W_{pW}=W_{pX}=W_{pY}=W_{pZ}=4
$$

### Logical Effort of Inputs

Logical effort of an input:

$$
\boxed{g=\frac{C_{in,\text{gate input}}}{C_{in,\text{reference inverter}}}}
$$

Reference inverter input capacitance:

$$
\boxed{C_{inv}=3}
$$

#### Inputs X, Y, Z

Each of $X,Y,Z$ drives:

- one nMOS of width 3,
- one pMOS of width 4.

So:

$$
C_X=C_Y=C_Z=3+4=7
$$

Therefore:

$$
\boxed{g_X=g_Y=g_Z=\frac{7}{3}}
$$

#### Input W

Input $W$ drives:

- one nMOS of width 1,
- one pMOS of width 4.

So:

$$
C_W=1+4=5
$$

Therefore:

$$
\boxed{g_W=\frac{5}{3}}
$$

### Final Logical Effort Result

For the complex gate producing $\overline U$:

$$
\boxed{g_X=g_Y=g_Z=\frac{7}{3}}
$$

$$
\boxed{g_W=\frac{5}{3}}
$$

The $W$ input is faster because it controls a single nMOS pull-down path, while $X,Y,Z$ are part of a 3-stack.

If the required output is non-inverted $U$, then add an inverter after this complex gate. That inverter contributes:

$$
g_{inv}=1,\qquad p_{inv}=1
$$

### Exam Intuition

Inputs in long series stacks usually have larger logical effort because their transistors must be widened to maintain drive strength. Parallel single-device branches usually have lower logical effort.

---

## Q4(b). Compare Speed of 2-Input NAND and 2-Input NOR

### NAND vs NOR Formula

Logical effort delay:

$$
\boxed{d=gh+p}
$$

where:

- $g$ = logical effort,
- $h$ = electrical effort,
- $p$ = parasitic delay.

### 2-Input NAND

For NAND2:

$$
\boxed{g_{NAND2}=\frac{4}{3}}
$$

$$
\boxed{p_{NAND2}=2}
$$

So:

$$
\boxed{d_{NAND2}=\frac{4}{3}h+2}
$$

### 2-Input NOR

For NOR2:

$$
\boxed{g_{NOR2}=\frac{5}{3}}
$$

$$
\boxed{p_{NOR2}=2}
$$

So:

$$
\boxed{d_{NOR2}=\frac{5}{3}h+2}
$$

### Comparison for Equal Electrical Effort

Given equal $h$:

$$
d_{NOR2}-d_{NAND2}
=\left(\frac{5}{3}h+2\right)-\left(\frac{4}{3}h+2\right)
$$

$$
\boxed{d_{NOR2}-d_{NAND2}=\frac{h}{3}}
$$

Since $h>0$:

$$
\boxed{d_{NOR2}>d_{NAND2}}
$$

Therefore:

$$
\boxed{\text{2-input NAND is faster than 2-input NOR}}
$$

### NAND vs NOR Intuition

In a NAND gate:

- nMOS transistors are in series,
- pMOS transistors are in parallel.

In a NOR gate:

- nMOS transistors are in parallel,
- pMOS transistors are in series.

pMOS devices are slower because:

$$
\mu_p<\mu_n
$$

So putting pMOS in series is especially costly. To compensate, pMOS devices must be made very wide, increasing input capacitance. That is why NOR has larger logical effort.

### Exam One-Liner

For the same load, NAND2 is faster because:

$$
\boxed{g_{NAND2}=\frac{4}{3}<g_{NOR2}=\frac{5}{3}}
$$

---

## Q5. Estimate $t_{pdf}$ and $t_{pdr}$ for a 3-Input NOR Loaded by $h$ Identical NOR Gates

Here:

- $t_{pdf}$ = falling propagation delay,
- $t_{pdr}$ = rising propagation delay.

Some books write these as:

$$
t_{pdf}=t_{pHL}
$$

$$
t_{pdr}=t_{pLH}
$$

### 3-Input NOR Structure

For a 3-input NOR:

$$
Y=\overline{A+B+C}
$$

PDN:

- 3 nMOS transistors in parallel.

PUN:

- 3 pMOS transistors in series.

### Key Intuition

The falling transition is easy:

- output falls when any nMOS turns ON,
- discharge path has only one nMOS resistance.

The rising transition is hard:

- output rises only when all inputs are 0,
- charge path passes through 3 series pMOS transistors.

So an unsized 3-input NOR has much worse rising delay than falling delay.

In logical effort, we normally size the pMOS stack so the worst-case pull-up resistance matches the inverter reference.

### Logical Effort Values for 3-Input NOR

For NOR-$n$:

$$
\boxed{g_{NORn}=\frac{2n+1}{3}}
$$

For $n=3$:

$$
g_{NOR3}=\frac{2(3)+1}{3}
$$

$$
\boxed{g_{NOR3}=\frac{7}{3}}
$$

Parasitic delay:

$$
\boxed{p_{NOR3}=3}
$$

### Electrical Effort

The output is loaded with $h$ identical NOR gates.

If $h$ is already given as fanout/electrical effort:

$$
\boxed{h=\frac{C_L}{C_{in}}}
$$

For $h$ identical same-size gates, this is simply:

$$
\boxed{h=\text{number of identical gates}}
$$

### Delay Formula

Logical effort delay:

$$
d=gh+p
$$

So:

$$
d_{NOR3}=\frac{7}{3}h+3
$$

In time units:

$$
\boxed{t_p=\left(\frac{7}{3}h+3\right)\tau}
$$

### Falling Delay

For the sized logical-effort model:

$$
\boxed{t_{pdf}=t_{pHL}\approx\left(\frac{7}{3}h+3\right)\tau}
$$

### Rising Delay

For the sized logical-effort model:

$$
\boxed{t_{pdr}=t_{pLH}\approx\left(\frac{7}{3}h+3\right)\tau}
$$

This equality assumes the NOR3 has been sized so the 3-series pMOS pull-up has approximately the same effective resistance as the reference inverter.

### If the Gate Is Not Sized

If minimum-size devices are used, then:

$$
t_{pdf}\approx0.69R_nC_L
$$

but:

$$
t_{pdr}\approx0.69(3R_p)C_L+\text{internal-node Elmore terms}
$$

Since:

$$
R_p>R_n
$$

and there are 3 pMOS devices in series:

$$
\boxed{t_{pdr}\gg t_{pdf}}
$$

### Final Exam Answer

For the standard logical-effort estimate:

$$
\boxed{t_{pdf}\approx\left(\frac{7}{3}h+3\right)\tau}
$$

$$
\boxed{t_{pdr}\approx\left(\frac{7}{3}h+3\right)\tau}
$$

For intuition, always mention:

$$
\boxed{\text{NOR rising delay is naturally worse because pMOS devices are in series.}}
$$

---

## Last-Minute Formula Bank for This Paper

### Power

$$
\boxed{P_{dyn}=\alpha C_LV_{DD}^2f}
$$

$$
\boxed{C=NWC'}
$$

$$
\boxed{P_{static}=V_{DD}I_{leak}}
$$

### Inverter Delay

$$
\boxed{t_{pHL}=0.69R_nC_L}
$$

$$
\boxed{t_{pLH}=0.69R_pC_L}
$$

$$
\boxed{t_p=\frac{t_{pHL}+t_{pLH}}{2}}
$$

$$
\boxed{R_{eq}\propto\frac{1}{W}}
$$

### Noise Margins

$$
\boxed{NM_L=V_{IL}-V_{OL}}
$$

$$
\boxed{NM_H=V_{OH}-V_{IH}}
$$

For static CMOS:

$$
\boxed{V_{OH}=V_{DD},\qquad V_{OL}=0}
$$

### Logical Effort

$$
\boxed{d=gh+p}
$$

$$
\boxed{G=\prod g_i}
$$

$$
\boxed{H=\frac{C_{out}}{C_{in}}}
$$

$$
\boxed{B=\prod b_i}
$$

$$
\boxed{F=GBH}
$$

$$
\boxed{\hat f=F^{1/N}}
$$

$$
\boxed{D_{min}=N\hat f+P}
$$

$$
\boxed{C_{in,i}=\frac{g_iC_{out,i}}{\hat f}}
$$

### Standard Gate Values

Inverter:

$$
\boxed{g=1,\qquad p=1}
$$

NAND-$n$:

$$
\boxed{g_{NANDn}=\frac{n+2}{3},\qquad p=n}
$$

NOR-$n$:

$$
\boxed{g_{NORn}=\frac{2n+1}{3},\qquad p=n}
$$

Specific values:

$$
\boxed{g_{NAND2}=\frac{4}{3},\quad p_{NAND2}=2}
$$

$$
\boxed{g_{NOR2}=\frac{5}{3},\quad p_{NOR2}=2}
$$

$$
\boxed{g_{NOR3}=\frac{7}{3},\quad p_{NOR3}=3}
$$

$$
\boxed{g_{NOR4}=3,\quad p_{NOR4}=4}
$$

### Static CMOS Construction

For CMOS logic:

- PDN uses nMOS.
- PUN uses pMOS.
- Series in PDN corresponds to AND.
- Parallel in PDN corresponds to OR.
- PUN is the dual of PDN.

De Morgan:

$$
\boxed{\overline{AB}=\overline A+\overline B}
$$

$$
\boxed{\overline{A+B}=\overline A\,\overline B}
$$

### What to Revise Before This Exam

Most important:

1. [[09_power_dissipation]] — Q1 is almost directly from this.
2. [[10_logical_effort]] — Q3, Q4, and Q5 depend on this.
3. [[11_static_cmos_logic]] — static CMOS construction, fan-in, input ordering, NAND vs NOR.
4. [[08_cmos_inverter_dynamic_behavior]] — delay formulas and delay minimisation.
5. [[18_formula_sheet]] — memorize the logical effort and power formulas.
