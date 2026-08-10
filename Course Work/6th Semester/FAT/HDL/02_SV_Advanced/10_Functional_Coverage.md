# 10 - Functional Coverage

## Learning Objectives

After this section you will understand:
- The difference between **code coverage** and **functional coverage**
- How to declare `covergroup`, `coverpoint`, and `bins`
- Explicit bins, array bins, range bins, and `iff` guards
- `cross` coverage
- Sampling strategies (`sample()`, automatic triggers)
- `get_coverage()` method
- How to embed covergroups inside classes (monitor pattern from labs)
- How to compute coverage percentage by hand (exam skill)

---

## Code Coverage vs Functional Coverage

> **Conceptual Clarity:** Code coverage asks "Did my testbench exercise every line of RTL code?" Functional coverage asks "Did my testbench exercise every **interesting scenario** I care about?" You can have 100% code coverage and still miss critical bugs if you never tested a specific input combination.

| Aspect | Code Coverage | Functional Coverage |
|---|---|---|
| **Who defines it?** | Automatic (tool-generated) | Manual (engineer writes covergroups) |
| **What it measures** | Lines, branches, expressions, FSM states | User-defined scenarios, input combos |
| **100% means** | Every code path was executed | Every specified scenario was hit |
| **Can miss bugs?** | Yes (exercised code != correct behavior) | Less likely (explicitly tracks scenarios) |

Types of code coverage:
- **Statement coverage**: Was each line executed?
- **Branch/path coverage**: Was each if/else branch taken?
- **Expression coverage**: Were all sub-expressions evaluated to both true and false?
- **FSM coverage**: Were all states and transitions visited?

Functional coverage complements code coverage by tracking **what you intended to test**.

---

## Covergroup Basics

A `covergroup` is a user-defined construct that specifies what values/scenarios to track.

### Syntax

```verilog
covergroup cg_name;
    coverpoint variable_name {
        bins bin_name = { values };
    }
endgroup
```

### Creating and Sampling

```verilog
// Declare
covergroup cg;
    coverpoint data;
endgroup

// Create instance
cg cg_inst = new();

// Sample (collect coverage)
cg_inst.sample();

// Check coverage
$display("Coverage = %0.2f%%", cg_inst.get_coverage());
```

> **Conceptual Clarity:** A covergroup is like a checklist. Each coverpoint is an item on the checklist. Each bin is a specific checkbox. Every time you call `sample()`, the tool looks at the current values and checks off any matching boxes. `get_coverage()` tells you what percentage of boxes have been checked.

---

## Coverpoints

A `coverpoint` specifies which variable to track and how to partition its value space.

### Automatic Bins

If you do not specify bins, the tool creates them automatically:

```verilog
covergroup cg;
    coverpoint data;    // auto-bins: one bin per possible value
endgroup
```

For a `bit [5:0]` variable (0-63), this creates 64 automatic bins.

### Explicit Bins

You define exactly which values to track:

```verilog
covergroup cg;
    coverpoint data {
        bins a[] = {0, 2, 4, 6};               // 4 separate bins, one per value
        bins b[] = {[10:12], [15:16]};          // array bins for ranges
        bins c   = {[20:25]};                   // single bin covering range 20-25
        bins d[] = {30, [40:41], 50};           // 3 bins: {30}, {40,41}, {50}
    }
endgroup
```

### Bin Types

| Syntax | Meaning | Example |
|---|---|---|
| `bins a = {1, 2, 3};` | Single bin covering values 1, 2, 3 | Hit if data is 1, 2, or 3 |
| `bins a[] = {1, 2, 3};` | Array bins: one bin PER value | 3 separate bins |
| `bins a = {[0:7]};` | Single bin covering range 0-7 | Hit if data is anywhere in 0-7 |
| `bins a[] = {[0:7]};` | Array bins: one bin per value in range | 8 separate bins |
| `bins a[] = {[0:3], [8:9]};` | Array bins for multiple ranges | 6 bins total |

---

## Worked Example: Quiz Q1 (Compute Coverage)

This is the exact pattern from Quiz_F2 Question 1:

```verilog
module tb;
    bit [5:0] data;

    covergroup cg;
        coverpoint data {
            bins a[] = {0, 2, 4, 6};               // 4 bins: {0}, {2}, {4}, {6}
            bins b[] = {[10:12], [15:16]};          // 5 bins: {10}, {11}, {12}, {15}, {16}
            bins c   = {[20:25]};                   // 1 bin:  {20,21,22,23,24,25}
            bins d[] = {30, [40:41], 50};           // 3 bins: {30}, {40,41}, {50}
        }
    endgroup

    cg cg_inst = new();

    initial begin
        int values[8] = '{2, 11, 15, 22, 30, 41, 18, 50};

        foreach (values[i]) begin
            #5 data = values[i];
            cg_inst.sample();
            $display("Sampled data = %0d", data);
        end

        $display("Coverage = %0.2f%%", cg_inst.get_coverage());
    end
endmodule
```

### Step-by-Step Coverage Calculation

**Total bins = 4 + 5 + 1 + 3 = 13 bins**

| Sample | Value | Which bin? | Hit? |
|---|---|---|---|
| 1 | 2 | `a[]` bin for value 2 | NEW hit |
| 2 | 11 | `b[]` bin for value 11 | NEW hit |
| 3 | 15 | `b[]` bin for value 15 | NEW hit |
| 4 | 22 | `c` bin (range 20-25) | NEW hit |
| 5 | 30 | `d[]` bin for value 30 | NEW hit |
| 6 | 41 | `d[]` bin for value {40,41} | NEW hit |
| 7 | 18 | No matching bin | No hit |
| 8 | 50 | `d[]` bin for value 50 | NEW hit |

**Bins hit: 7 out of 13**

$$\text{Coverage} = \frac{7}{13} \times 100 = 53.85\%$$

**Output:**
```
Sampled data = 2
Sampled data = 11
Sampled data = 15
Sampled data = 22
Sampled data = 30
Sampled data = 41
Sampled data = 18
Sampled data = 50
Coverage = 53.85%
```

---

## `iff` Guard on Bins

Bins can be conditionally active using `iff`:

```verilog
covergroup cg;
    cp_a : coverpoint a {
        bins a1[] = {1, 2};
        bins a2   = {[5:6]} iff (en);    // only sampled when en == 1
    }
endgroup
```

The bin `a2` only records a hit when `en` is high at the time of sampling. If `en` is low, the sample is ignored for that bin.

---

## Cross Coverage

Cross coverage tracks **combinations** of two or more coverpoints.

```verilog
covergroup cg;
    cp_a : coverpoint a;
    cp_b : coverpoint b;

    cross cp_a, cp_b;    // tracks all combinations of (a, b)
endgroup
```

For 1-bit signals `a` and `b`, the cross creates 4 bins:
- `(a=0, b=0)`, `(a=0, b=1)`, `(a=1, b=0)`, `(a=1, b=1)`

### Lab Pattern: Full Adder Input Coverage

```verilog
covergroup input_coverage;
    option.per_instance = 1;

    cp_a   : coverpoint tb_if.a;
    cp_b   : coverpoint tb_if.b;
    cp_cin : coverpoint tb_if.cin;

    cross_abc : cross cp_a, cp_b, cp_cin;    // all 8 input combos
endgroup
```

For three 1-bit signals, the cross creates $2 \times 2 \times 2 = 8$ cross bins. 100% cross coverage means all 8 input combinations have been exercised.

---

## Worked Example: Quiz Q2 (Cross Coverage with `iff`)

This matches Quiz_F2 Question 2:

```verilog
module tb2;
    bit [3:0] a, b;
    bit en;

    covergroup cg;
        cp_a : coverpoint a {
            bins a1[] = {1, 2};                   // 2 bins: {1}, {2}
            bins a2   = {[5:6]} iff (en);         // 1 bin: {5,6} only when en=1
        }
        cp_b : coverpoint b {
            bins b1   = {3};                      // 1 bin: {3}
            bins b2[] = {[7:8]};                  // 2 bins: {7}, {8}
        }
        cross cp_a, cp_b;
    endgroup

    cg c = new();

    int avals[4] = '{1, 5, 6, 2};
    int bvals[4] = '{3, 7, 8, 7};
    bit evals[4] = '{0, 0, 1, 1};

    initial begin
        foreach (avals[i]) begin
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

### Analysis

**cp_a bins:** `a1[1]`, `a1[2]`, `a2` (3 bins total)
**cp_b bins:** `b1`, `b2[7]`, `b2[8]` (3 bins total)
**Cross bins:** 3 x 3 = 9 cross bins

| Sample | a | b | en | cp_a bin hit | cp_b bin hit | Cross bin hit |
|---|---|---|---|---|---|---|
| 1 | 1 | 3 | 0 | a1[1] | b1 | (a1[1], b1) |
| 2 | 5 | 7 | 0 | a2 **SKIPPED** (en=0) | b2[7] | No cross (a2 skipped) |
| 3 | 6 | 8 | 1 | a2 (en=1, hits!) | b2[8] | (a2, b2[8]) |
| 4 | 2 | 7 | 1 | a1[2] | b2[7] | (a1[2], b2[7]) |

**cp_a hits:** a1[1], a2, a1[2] = 3/3 = 100%
**cp_b hits:** b1, b2[7], b2[8] = 3/3 = 100%
**Cross hits:** 3 out of 9 bins hit

> The overall `get_coverage()` combines coverpoint and cross coverage. The exact calculation depends on the tool's weighting, but typically each coverpoint and cross have equal weight.

---

## Covergroup Options

| Option | Description | Example |
|---|---|---|
| `option.per_instance = 1` | Track coverage per instance (not merged) | Used in classes |
| `option.at_least = N` | Bin needs N hits to be "covered" | `option.at_least = 5;` |
| `option.auto_bin_max = N` | Max number of auto-generated bins | Limits auto-bin count |

---

## Sampling Strategies

### Manual Sampling

Call `sample()` explicitly when you want to record coverage:

```verilog
cg_inst.sample();
```

### Automatic Sampling (Clock-Based)

```verilog
covergroup cg @(posedge clk);    // sample at every posedge clk
    coverpoint data;
endgroup
```

### Sampling in a Monitor (Lab Pattern)

```verilog
class monitor;
    virtual full_adder_if tb_if;
    mailbox #(transaction) mon2scb;

    covergroup fa_cg;
        option.per_instance = 1;
        A_cp   : coverpoint tb_if.a;
        B_cp   : coverpoint tb_if.b;
        CIN_cp : coverpoint tb_if.cin;
        ABC_cross : cross A_cp, B_cp, CIN_cp;
    endgroup

    function new(virtual full_adder_if tb_if, mailbox #(transaction) mon2scb);
        this.tb_if   = tb_if;
        this.mon2scb = mon2scb;
        fa_cg = new();    // IMPORTANT: must construct covergroup
    endfunction

    task run();
        transaction t;
        forever begin
            t = new();
            #9;
            t.a    = tb_if.a;
            t.b    = tb_if.b;
            t.cin  = tb_if.cin;
            t.sum  = tb_if.sum;
            t.cout = tb_if.cout;

            fa_cg.sample();    // sample coverage
            mon2scb.put(t);
            #1;
        end
    endtask
endclass
```

> **Conceptual Clarity:** The monitor samples at `#9` (just before the next stimulus at `#10`) to give combinational logic maximum settling time. This ensures the sampled values are stable.

---

## Common Mistakes

1. **Forgetting `new()` on covergroup** -- Covergroups must be constructed with `new()` before `sample()` is called. Without it, you get a null-reference error.
2. **Confusing `bins a[] = {1,2,3}` with `bins a = {1,2,3}`** -- The `[]` creates 3 separate bins (one per value). Without `[]`, it is a single bin that is hit by any of the three values.
3. **Ignoring `iff` guards** -- If a bin has `iff(en)` and `en` is 0, the sample is completely ignored for that bin. This affects coverage percentages.
4. **Not understanding cross bin count** -- Cross of N coverpoints with $B_1, B_2, ..., B_N$ bins each creates $B_1 \times B_2 \times ... \times B_N$ cross bins.
5. **Sampling at wrong time** -- Sampling before the DUT outputs have settled gives wrong coverage data.

---

## Self-Check Questions

**Q1:** What is the difference between `bins a[] = {1, 2, 3}` and `bins a = {1, 2, 3}`?
> `bins a[] = {1, 2, 3}` creates 3 separate bins (one for value 1, one for 2, one for 3). `bins a = {1, 2, 3}` creates 1 bin that is hit by any of the three values.

**Q2:** If a coverpoint has 4 bins and 3 are hit, what is the coverpoint coverage?
> 3/4 = 75%

**Q3:** What does `iff(en)` do on a bin?
> The bin only records a hit when `en` is true at the time of sampling. If `en` is false, the sample is ignored for that bin.

**Q4:** How many cross bins does `cross cp_a, cp_b` create if `cp_a` has 3 bins and `cp_b` has 4 bins?
> 3 x 4 = 12 cross bins.

**Q5:** Why do we use `option.per_instance = 1` inside a class?
> Without it, coverage from all instances of the class is merged together. With `per_instance = 1`, each object tracks its own coverage independently.

**Q6:** What is the difference between `sample()` and `@(posedge clk)` sampling?
> `sample()` is manual: you call it exactly when you want to record. `@(posedge clk)` is automatic: coverage is sampled at every rising clock edge whether you want it or not.

---

## Concept Links

- Previous: [09 - SystemVerilog Assertions](./09_Assertions_SVA.md)
- Next: [11 - Worked Problems](../04_Practice_Problems/04_Worked_Problems.md)
- Related: [06 - TestBench Architecture](./06_Testbench_Architecture.md) (monitor is where coverage lives)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#functional-coverage)




