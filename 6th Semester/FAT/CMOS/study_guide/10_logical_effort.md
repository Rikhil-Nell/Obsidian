# Logical Effort

> Concept: a fast, accurate way to size and stage gates without simulation. Logical effort uses three numbers per gate (logical effort $g$, electrical effort $h$, parasitic delay $p$) to predict delay and find optimum sizing for *paths*.

## Why a Method Like This Exists

Designers face three constant questions:

1. *What is the best logic topology* for a function?
2. *How many gate stages* should I use?
3. *How wide should each transistor be*?

Logical effort answers all three with simple algebra. The method comes from Sutherland, Sproull, and Harris and is standard exam material here.

It rests on a normalised **linear delay model** — a generalisation of the inverter chain analysis from [[08_cmos_inverter_dynamic_behavior]] to arbitrary gates.

---

## The Linear Delay Model

Define the delay of any gate as

$$
\boxed{\,d = g\cdot h + p\,}
$$

where everything is measured in units of an unloaded inverter delay $\tau$:

- $g$ — **logical effort** of the gate. Captures topology overhead vs an inverter.
- $h$ — **electrical effort**, also called fanout. Ratio of output capacitance to input capacitance: $h=C_{out}/C_{in}$.
- $p$ — **parasitic delay** of the gate. Captures self-loading from drain diffusion.

Real time delay is

$$
t_{delay} = d\cdot\tau,\quad \tau\approx \text{FO1 inverter delay}
$$

Read this as: "Delay = drive-strength penalty × load + intrinsic delay."

---

## Computing Logical Effort

### Definition

The logical effort of an input of a gate is the **ratio of input capacitance** to the input capacitance of an inverter that would produce the same drive current.

For unit-inverter convention, an inverter has nMOS of width 1 and pMOS of width 2 (to compensate $\mu_n/\mu_p\approx 2$). Total input capacitance = 3 units.

$$
g_{inv}=\frac{C_{in,\text{inv}}}{C_{in,\text{inv}}}=1
$$

### NAND-2

To match the resistance of an inverter when both inputs are high, each nMOS in the series stack must be width 2 (so two of them in series ≈ one of width 1). Each pMOS is parallel and width 2 (same as inverter pMOS). Total per input: 2 (nMOS) + 2 (pMOS) = 4.

$$
g_{NAND2}=\frac{4}{3}
$$

For an $n$-input NAND:

$$
\boxed{\,g_{NAND_n}=\frac{n+2}{3}\,}
$$

### NOR-2

pMOS in series, nMOS in parallel. Each pMOS in series stack must be width 4 to match (twice the parallel inverter pMOS = 4). Each nMOS is unit. Per input: 1 + 4 = 5.

$$
g_{NOR2}=\frac{5}{3}
$$

For $n$-input NOR:

$$
\boxed{\,g_{NOR_n}=\frac{2n+1}{3}\,}
$$

### Common Gate Reference Table

| Gate | $g$ | $p$ |
|---|---:|---:|
| Inverter | 1 | 1 |
| NAND-2 | 4/3 | 2 |
| NAND-3 | 5/3 | 3 |
| NAND-$n$ | $(n+2)/3$ | $n$ |
| NOR-2 | 5/3 | 2 |
| NOR-3 | 7/3 | 3 |
| NOR-$n$ | $(2n+1)/3$ | $n$ |
| XOR-2 | 4 | 4 |
| MUX-2 | 2 | 4 |

The trend: NOR has higher $g$ than NAND because the pMOS series stack hurts more (lower mobility). Designers prefer NAND chains when speed matters.

### Parasitic Delay $p$

$p$ models the drain diffusion + Miller capacitance of the gate's *own* output, expressed in units of inverter parasitic delay. Roughly $p\approx n$ for an $n$-input NAND or NOR.

---

## Single-Gate Delay Examples

### Inverter Driving Identical Inverter (FO1)

$g=1$, $h=1$, $p=1$:

$$
d=1\cdot 1+1=2
$$

So a fanout-of-1 inverter delay is $2\tau$, equal to roughly two inverter "$\tau$" units.

### Ring Oscillator

$N$ inverters in a loop. Each stage has $g=h=p=1$ ⇒ $d=2$ per stage. Signal must traverse twice (once for each polarity), so period $T=2N\cdot d\cdot\tau=4N\tau$.

$$
\boxed{\,f_{osc}=\frac{1}{4N\tau}\,}
$$

(slide Problem from Logical Effort section.)

### NAND-2 with $h=4$

$g=4/3$, $h=4$, $p=2$:

$$
d=\tfrac{4}{3}\cdot 4 + 2 = 5.33+2=7.33
$$

So a NAND-2 driving 4× its own input capacitance has a delay of $\sim 7.3\tau$, vs an inverter's $\sim 5\tau$ at the same fanout. The $g=4/3$ penalty makes NAND2 about 1.5× the inverter's *useful* effort delay.

---

## Multi-Stage Logic Networks

### Path Logical Effort

For a path of $N$ stages with logical efforts $g_1, g_2, \dots, g_N$:

$$
\boxed{\,G = \prod_{i=1}^N g_i\,}
$$

### Path Electrical Effort

$$
\boxed{\,H = \frac{C_{out,N}}{C_{in,1}}\,}
$$

i.e., total load capacitance at the path output divided by input capacitance of the first stage.

### Branching Effort

If a stage's output drives multiple paths, only part of the capacitance lies on the path of interest. **Branching effort** at stage $i$:

$$
b_i = \frac{C_{onpath}+C_{offpath}}{C_{onpath}}
$$

Path branching effort:

$$
\boxed{\,B = \prod_i b_i\,}
$$

### Path Effort

$$
\boxed{\,F = G\cdot B\cdot H\,}
$$

### Best Stage Effort and Minimum Delay

For minimum delay, **every stage should bear the same effort**:

$$
\boxed{\,\hat f = F^{1/N}\,}
$$

Total path delay:

$$
\boxed{\,D = \sum_i p_i + N\cdot\hat f = P + N\,F^{1/N}\,}
$$

where $P=\sum_i p_i$ is the sum of parasitic delays.

### Optimal Number of Stages

Differentiate $D$ w.r.t. $N$ and solve. For a process with parasitic delay $p_{inv}\approx 1$, the closed-form result is

$$
\hat f \approx 4
$$

Same answer as inverter chains in [[08_cmos_inverter_dynamic_behavior]]. So the **fanout-of-4** rule is the universal logical-effort sizing target.

---

## Sizing Path Stages from $\hat f$

Once $\hat f$ is known, work *backwards* from the load:

$$
C_{in,i} = \frac{g_i\,C_{out,i}}{\hat f}
$$

Starting from $C_{in,N+1}=C_{load}$ and going from stage $N$ backwards to stage 1.

### Worked Example — Path with NAND2, NAND3, INV driving $C_L$

Suppose path: input → NAND2 → NAND3 → INV → $C_L=64\,C_{g,1}$.

1. Compute $G = (4/3)(5/3)(1) = 20/9 \approx 2.22$.
2. $H=64$, no branching ($B=1$). $F=2.22\cdot 64 = 142$.
3. With $N=3$, $\hat f=142^{1/3}\approx 5.22$.
4. $P=2+3+1=6$.
5. $D = 6 + 3\cdot 5.22 = 21.7$ inverter delays.

To find sizes:
- Stage 3 (inverter): $C_{in,3}=g_3 C_{L}/\hat f = 1\cdot 64/5.22\approx 12.3\,C_{g,1}$.
- Stage 2 (NAND3): $C_{in,2}=g_2 C_{in,3}/\hat f = (5/3)\cdot 12.3/5.22\approx 3.93\,C_{g,1}$.
- Stage 1 (NAND2): $C_{in,1}=g_1 C_{in,2}/\hat f = (4/3)\cdot 3.93/5.22\approx 1\,C_{g,1}$. ✓ (matches start)

If you want **fewer stages**, $\hat f$ rises, parasitic-per-stage drops, but each stage carries more effort. There's a window where 3 or 4 stages are roughly equally good — the exam often asks you to pick that.

---

## Asymmetric Logic Gates

If a gate's inputs have different *required* speeds (for instance, a critical input vs a less-critical one), you can deliberately size to give different logical efforts to different inputs. Sizing nMOS and pMOS stacks unequally lets you trade $g$ on one input for $g$ on another, while keeping the gate's overall drive constant.

This is how skewed gates (HI-skew, LO-skew) are designed — useful for domino chains in [[13_dynamic_and_domino_logic]].

---

## Branching-Effort Worked Example

Imagine a gate whose output drives both a path of interest with capacitance $4C$ and a side path with $12C$.

$$
b = \frac{4C+12C}{4C} = 4
$$

So the *path effort* multiplies by 4, and you need bigger stages to drive both branches at the same effort.

---

## Common Exam Mistakes

- Forgetting that $g$ for NOR-$n$ grows linearly while NAND-$n$ grows much more slowly. So NOR-heavy paths are slow.
- Mixing branching effort with electrical effort. $H$ is end-to-end load/input ratio; $B$ accounts for off-path branches at intermediate nodes.
- Using $h$ for the entire path. $h$ is per-stage; $H$ is the path version.
- Sizing forwards. Always size **backwards** from the load.
- Quoting $\hat f=e\approx 2.7$ as the answer when the question is about real CMOS — use $\sim 4$.

## Self-Check Questions

1. Why is NAND preferred over NOR for fast logic?
   <details><summary>Answer</summary>NAND has lower logical effort: $(n+2)/3$ vs $(2n+1)/3$ for NOR. The pMOS in NOR sits in series and must be made very wide to match an inverter, blowing up input capacitance.</details>

2. Why is the path effort $F=G\cdot B\cdot H$ split into three?
   <details><summary>Answer</summary>$G$ captures topology cost, $B$ captures off-path side-loading, $H$ captures end-to-end load. Each stage's effort is $g\cdot h$, but $h$ at intermediate stages depends on branching, so $B$ separates that out cleanly.</details>

3. What does it mean if your computed $\hat f$ exceeds 6 or 7?
   <details><summary>Answer</summary>The path is too short for the load — add more stages. Conversely, $\hat f<2$ means the path has too many stages and is paying parasitic delays you can save.</details>

4. Why is $p$ proportional to $n$ for NAND-$n$?
   <details><summary>Answer</summary>The $n$ series nMOS transistors plus the parallel pMOS produce $\sim n$ unit-drains worth of diffusion at the output, so parasitic delay scales linearly with input count.</details>

## Concept Links

- Previous: [[09_power_dissipation]]
- Next: [[11_static_cmos_logic]]
- Related: [[08_cmos_inverter_dynamic_behavior]] (RC origins of the linear model)
- Formulas: [[18_formula_sheet#logical-effort]]
