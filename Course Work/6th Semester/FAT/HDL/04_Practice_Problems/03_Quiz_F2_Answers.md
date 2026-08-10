

> [!info] Quiz F2 Details:-
> **Name**: Rikhil Nellimarla
> **Registration** Number: 23BEC7030
> **Course Name**: HDL Verification
> **Slot**: F1 + TF1

---

## Question 1: Functional Coverage – Covergroup with Bins

**Question:** Write expected output for the following functional coverage code.

### Given Code

```verilog
module tb;

  bit [5:0] data;
  covergroup cg;
    coverpoint data {
      bins a[] = {0, 2, 4, 6};
      bins b[] = {[10:12], [15:16]};
      bins c   = {[20:25]};
      bins d[] = {30, [40:41], 50};
    }
  endgroup

  cg cg_inst = new();

  initial begin
    int values[8] = '{2, 11, 15, 22, 30, 41, 18, 50};
    foreach(values[i]) begin
      #5 data = values[i];
      cg_inst.sample();
      $display("Sampled data = %0d", data);
    end
    $display("Coverage = %0.2f%%", cg_inst.get_coverage());
  end

endmodule
```

### Answer

#### Bin Definitions Analysis

| Bin | Type | Values Covered |
|-----|------|----------------|
| `a[0]` | Auto-indexed | 0 |
| `a[1]` | Auto-indexed | 2 |
| `a[2]` | Auto-indexed | 4 |
| `a[3]` | Auto-indexed | 6 |
| `b[0]` | Auto-indexed | 10, 11, 12 |
| `b[1]` | Auto-indexed | 15, 16 |
| `c` | Single bin | 20, 21, 22, 23, 24, 25 |
| `d[0]` | Auto-indexed | 30 |
| `d[1]` | Auto-indexed | 40, 41 |
| `d[2]` | Auto-indexed | 50 |

**Total bins = 10**

#### Sample-by-Sample Evaluation

| Sample | Value | Bin Hit |
|--------|-------|---------|
| 1 | 2 | `a[1]` ✅ |
| 2 | 11 | `b[0]` ✅ |
| 3 | 15 | `b[1]` ✅ |
| 4 | 22 | `c` ✅ |
| 5 | 30 | `d[0]` ✅ |
| 6 | 41 | `d[1]` ✅ |
| 7 | 18 | No bin hit ❌ |
| 8 | 50 | `d[2]` ✅ |

**Bins hit: 7 out of 10**

#### Expected Output

```
Sampled data = 2
Sampled data = 11
Sampled data = 15
Sampled data = 22
Sampled data = 30
Sampled data = 41
Sampled data = 18
Sampled data = 50
Coverage = 70.00%
```

**Explanation:** 7 out of 10 bins were hit. Bins `a[0]` (value 0), `a[2]` (value 4), and `a[3]` (value 6) were never sampled, so coverage = 7/10 = 70.00%. Value 18 does not fall into any defined bin.

---

## Question 2: Functional Coverage with Cross Coverage and `iff`

**Question:** Write the output for the following code with cross coverage.

### Given Code

```verilog
module tb2;
  bit [3:0] a, b;
  bit en;
  covergroup cg;
    cp_a : coverpoint a {
      bins a1[] = {1, 2};
      bins a2   = {[5:6]} iff(en);
    }
    cp_b : coverpoint b {
      bins b1  = {3};
      bins b2[] = {[7:8]};
    }
    cross cp_a, cp_b;
  endgroup

  cg c = new();

  int avals[4] = '{1, 5, 6, 2};
  int bvals[4] = '{3, 7, 8, 7};
  bit evals[4] = '{0, 0, 1, 1};

  initial begin
    foreach(avals[i]) begin
      a  = avals[i];
      b  = bvals[i];
      en = evals[i];
      c.sample();
      $display("a=%0d b=%0d en=%0b", a, b, en);
    end

    $display("Coverage = %0.2f%%", c.get_coverage());
  end
endmodule
```

### Answer

#### Bin Definitions

**cp_a bins:**
- `a1[0]` = 1
- `a1[1]` = 2
- `a2` = {5, 6} but only sampled when `en == 1`

**cp_b bins:**
- `b1` = 3
- `b2[0]` = 7
- `b2[1]` = 8

**Cross bins:** cp_a × cp_b = 3 bins × 3 bins = **9 cross bins**

#### Sample-by-Sample Evaluation

| Iter | a | b | en | cp_a bin hit | cp_b bin hit | Cross hit |
|------|---|---|----|-------------|-------------|-----------|
| 0 | 1 | 3 | 0 | `a1[0]` ✅ | `b1` ✅ | `<a1[0], b1>` ✅ |
| 1 | 5 | 7 | 0 | None (a2 needs en=1) | `b2[0]` ✅ | None |
| 2 | 6 | 8 | 1 | `a2` ✅ | `b2[1]` ✅ | `<a2, b2[1]>` ✅ |
| 3 | 2 | 7 | 1 | `a1[1]` ✅ | `b2[0]` ✅ | `<a1[1], b2[0]>` ✅ |

**cp_a coverage:** 3/3 bins hit = 100%
**cp_b coverage:** 3/3 bins hit = 100%
**Cross coverage:** 3/9 cross bins hit = 33.33%

**Overall coverage** = average of all coverpoints = (100 + 100 + 33.33) / 3 = **77.78%**

#### Expected Output

```
a=1 b=3 en=0
a=5 b=7 en=0
a=6 b=8 en=1
a=2 b=7 en=1
Coverage = 77.78%
```

---

## Question 3: SVA Property with Consecutive Delay `##[1:2]`

**Question:** Write output for the following assertion code.

### Given Code

```verilog
module tb3;

  bit clk, a, b;
  always #5 clk = ~clk;

  initial begin
    clk = 0;
    a = 1; b = 0;
    #10 a = 0;
    #10 b = 1;
    #10 a = 1; b = 0;
    #10 b = 1;
    #20 $finish;
  end

  property p;
    @(posedge clk)
    a |-> ##[1:2] b;
  endproperty

  ap : assert property(p)
    $display("PASS at %0t", $time);
  else
    $display("FAIL at %0t", $time);

endmodule
```

### Answer

#### Signal Timeline

The clock has a period of 10 time units (posedge at t=5, 15, 25, 35, 45, 55...).

| Time | Event | a | b |
|------|-------|---|---|
| 0 | Initial | 1 | 0 |
| 10 | a=0 | 0 | 0 |
| 20 | b=1 | 0 | 1 |
| 30 | a=1, b=0 | 1 | 0 |
| 40 | b=1 | 1 | 1 |

**Signal values at posedge clk:**

| Posedge | Time | a | b |
|---------|------|---|---|
| 1 | 5 | 1 | 0 |
| 2 | 15 | 0 | 0 |
| 3 | 25 | 0 | 1 |
| 4 | 35 | 1 | 0 |
| 5 | 45 | 1 | 1 |
| 6 | 55 | 1 | 1 |

#### Property: `a |-> ##[1:2] b`

This means: when `a` is high, `b` must be high within the next 1 or 2 clock cycles.

| Posedge | Time | a | Evaluation | Result |
|---------|------|---|-----------|--------|
| 1 (t=5) | 5 | 1 | Check b at t=15 (b=0) and t=25 (b=1) → b=1 at ##2 | **PASS** (reported at t=25) |
| 2 (t=15) | 15 | 0 | Antecedent false → vacuous pass | **PASS** |
| 3 (t=25) | 25 | 0 | Antecedent false → vacuous pass | **PASS** |
| 4 (t=35) | 35 | 1 | Check b at t=45 (b=1) → b=1 at ##1 | **PASS** (reported at t=45) |
| 5 (t=45) | 45 | 1 | Check b at t=55 (b=1) → b=1 at ##1 | **PASS** (reported at t=55) |

#### Expected Output

```
PASS at 15
PASS at 25
PASS at 25
PASS at 35
PASS at 45
PASS at 55
```

**Explanation:**
- At t=5: `a=1`, triggers check. `b=0` at ##1 (t=15), `b=1` at ##2 (t=25) → **PASS at 25**
- At t=15: `a=0`, antecedent false → vacuous **PASS at 15**
- At t=25: `a=0`, antecedent false → vacuous **PASS at 25**
- At t=35: `a=1`, `b=1` at ##1 (t=45) → **PASS at 45**
- At t=45: `a=1`, `b=1` at ##1 (t=55) → **PASS at 55**

---

## Question 4: SVA with Sequence and Multi-Signal Property

**Question:** Write output for the following assertion code with sequence.

### Given Code

```verilog
module tb4;

  bit clk, a, b, c;
  always #5 clk = ~clk;

  initial begin
    clk = 0;  a = 1; b = 0; c = 0;
    #10 a = 0;
    #10 b = 1;
    #10 b = 0;
    #10 c = 1;
    #10 a = 1; c = 0;
    #10 a = 0;
    #10 b = 1;
    #10 c = 0;
    #20 $finish;
  end

  sequence s;
    b ##1 c;
  endsequence

  property p;
    @(posedge clk)
    a |-> ##1 s;
  endproperty

  ap : assert property(p)
    $display("PASS at %0t", $time);
  else
    $display("FAIL at %0t", $time);

endmodule
```

### Answer

#### Signal Timeline

| Time | Event | a | b | c |
|------|-------|---|---|---|
| 0 | Initial | 1 | 0 | 0 |
| 10 | a=0 | 0 | 0 | 0 |
| 20 | b=1 | 0 | 1 | 0 |
| 30 | b=0 | 0 | 0 | 0 |
| 40 | c=1 | 0 | 0 | 1 |
| 50 | a=1,c=0 | 1 | 0 | 0 |
| 60 | a=0 | 0 | 0 | 0 |
| 70 | b=1 | 0 | 1 | 0 |
| 80 | c=0 | 0 | 1 | 0 |

**Signal values at posedge clk (t = 5, 15, 25, 35, 45, 55, 65, 75, 85):**

| Posedge | Time | a | b | c |
|---------|------|---|---|---|
| 1 | 5 | 1 | 0 | 0 |
| 2 | 15 | 0 | 0 | 0 |
| 3 | 25 | 0 | 1 | 0 |
| 4 | 35 | 0 | 0 | 0 |
| 5 | 45 | 0 | 0 | 1 |
| 6 | 55 | 1 | 0 | 0 |
| 7 | 65 | 0 | 0 | 0 |
| 8 | 75 | 0 | 1 | 0 |
| 9 | 85 | 0 | 1 | 0 |

#### Property: `a |-> ##1 s` where `s = b ##1 c`

Expanded: when `a` is high, at the **next** clock cycle `b` must be high, and at the clock cycle **after that** `c` must be high.

Full check: `a` → (##1 `b`) → (##2 `c`)

| Posedge | Time | a | Check | Result |
|---------|------|---|-------|--------|
| 1 (t=5) | 5 | 1 | ##1: b at t=15 = 0 → **FAIL** | **FAIL at 15** |
| 2 (t=15) | 15 | 0 | Vacuous pass | **PASS at 15** |
| 3 (t=25) | 25 | 0 | Vacuous pass | **PASS at 25** |
| 4 (t=35) | 35 | 0 | Vacuous pass | **PASS at 35** |
| 5 (t=45) | 45 | 0 | Vacuous pass | **PASS at 45** |
| 6 (t=55) | 55 | 1 | ##1: b at t=65 = 0 → **FAIL** | **FAIL at 65** |
| 7 (t=65) | 65 | 0 | Vacuous pass | **PASS at 65** |
| 8 (t=75) | 75 | 0 | Vacuous pass | **PASS at 75** |

#### Expected Output

```
FAIL at 15
PASS at 15
PASS at 25
PASS at 35
PASS at 45
FAIL at 65
PASS at 65
PASS at 75
```

**Explanation:**
- **t=5:** `a=1`, check ##1 b → b=0 at t=15 → sequence `s` fails immediately → **FAIL at 15**
- **t=15 to t=45:** `a=0`, antecedent false → vacuous **PASS**
- **t=55:** `a=1`, check ##1 b → b=0 at t=65 → sequence `s` fails immediately → **FAIL at 65**
- **t=65 to t=75:** `a=0`, antecedent false → vacuous **PASS**

---
