# 11 - Worked Problems

## Learning Objectives

This file contains worked examples from the course material. Reviewing these will solidify your understanding of how different SystemVerilog constructs behave when executed.

---

## Complete Problem Setup: TestBench Component Connection

**Problem Description:**
How do you connect a Generator, Driver, and Environment using Mailboxes and Events? Explain with a code structure.

> **Conceptual Clarity:** This is the most important architectural pattern in SystemVerilog verification. The Generator creates random data (transactions), puts them in a Mailbox, and the Driver takes them out to drive the pins.

**Code Solution:**

```verilog
// 1. The data packet
class transaction;
    rand bit [3:0] a, b;
endclass

// 2. The Generator
class generator;
    transaction trans;
    mailbox gen2drv;          // Mailbox handle
    event ended;              // Event to signal completion

    function new(mailbox gen2drv);
        this.gen2drv = gen2drv;
    endfunction

    task main();
        repeat(10) begin
            trans = new();
            if(!trans.randomize()) $fatal("Randomization failed");
            gen2drv.put(trans);   // Send to driver
        end
        -> ended;                 // Signal completion
    endtask
endclass

// 3. The Driver
class driver;
    mailbox gen2drv;          // Mailbox handle (same mailbox)

    function new(mailbox gen2drv);
        this.gen2drv = gen2drv;
    endfunction

    task main();
        transaction trans;
        forever begin
            gen2drv.get(trans);   // Get from generator
            // (Drive pins here)
            $display("Driving: a=%0d, b=%0d", trans.a, trans.b);
        end
    endtask
endclass

// 4. The Environment (ties them together)
class environment;
    generator gen;
    driver drv;
    mailbox gen2drv;

    function new();
        gen2drv = new();                 // 1. Create mailbox
        gen = new(gen2drv);              // 2. Pass to Generator
        drv = new(gen2drv);              // 3. Pass to Driver
    endfunction

    task run();
        fork
            gen.main();                  // Run both in parallel
            drv.main();
        join_any
        wait(gen.ended.triggered);       // Wait until generator is done
    endtask
endclass
```

---

## Array Methods Execution

**Problem Description:**
Given the array `int arr[] = '{2, 7, 3, 9, 1, 8};`, show the output of `find`, `find_index`, and `find_first` with the condition `(item > 5)`.

**Solution:**

```verilog
module tb;
    int arr[] = '{2, 7, 3, 9, 1, 8};
    int res_q[$];

    initial begin
        // 1. find(): Returns the actual elements that match
        res_q = arr.find with (item > 5);
        // Result: {7, 9, 8}

        // 2. find_index(): Returns the **indices** of elements that match
        res_q = arr.find_index with (item > 5);
        // Result: {1, 3, 5}  (arr[1]=7, arr[3]=9, arr[5]=8)

        // 3. find_first(): Returns a queue with only the FIRST matching element
        res_q = arr.find_first with (item > 5);
        // Result: {7}
    end
endmodule
```

---

## Dynamic Casting (`$cast`)

**Problem Description:**
Explain what happens in the following code. Will the `$cast` succeed or fail, and why?

```verilog
class parent;
    int x = 10;
endclass

class child extends parent;
    int y = 20;
endclass

module tb;
    initial begin
        parent p;
        child c1, c2;

        c1 = new();
        p = c1;            // Line A

        if ($cast(c2, p))  // Line B
            $display("Success: y = %0d", c2.y);
        else
            $display("Failed");
    end
endmodule
```

**Solution:**
The `$cast` will **succeed**, and the output will be `Success: y = 20`.

**Explanation:**
1. At **Line A**, the parent handle `p` is assigned to point to `c1` (which is a `child` object). This is always allowed (implicit casting up the hierarchy).
2. At **Line B**, we try to cast `p` back into a `child` handle (`c2`). The `$cast` performs a runtime check: "Does `p` currently point to an object that is at least a `child`?" Since `p` is pointing to the object created by `c1 = new()` (which is a `child` object), the check passes.
3. If `p` had been attached to `new()` (a pure parent object), the `$cast` would have failed.

---

## Solving Constraints with Implication

**Problem Description:**
Evaluate the output probability for the following constraint. What values can `addr` take when `mode=1`, and when `mode=0`?

```verilog
class packet;
    rand bit mode;           // 1-bit: 0 or 1
    rand bit [3:0] addr;     // 4-bit: 0 to 15

    constraint order_c {
        mode == 1 -> addr > 12;
    }
endclass
```

**Solution:**

There are two cases. A 1-bit `mode` acts like a coin flip (50% chance of 0, 50% chance of 1).

**Case 1: When `mode = 1`**
- The implication condition (`mode == 1`) is TRUE.
- Therefore, the constraint `addr > 12` MUST be satisfied.
- The valid values for `addr` are `13, 14, 15`.
- Probability: If `mode` is 1, `addr` is drawn uniformly from {13, 14, 15}.

**Case 2: When `mode = 0`**
- The implication condition (`mode == 1`) is FALSE.
- Therefore, the right side (`addr > 12`) is IGNORED.
- The valid values for `addr` are the full 4-bit range: `0, 1, 2, ... 15`.
- Probability: If `mode` is 0, `addr` is drawn uniformly from {0..15}.

---

## Fork-Join Timing Traces

**Problem Description:**
Determine the final simulation time and output order for the following block.

```verilog
initial begin
    $display("[%0t] Start", $time);
    fork
        #5  $display("[%0t] P1", $time);
        #15 $display("[%0t] P2", $time);
        #10 $display("[%0t] P3", $time);
    join_any
    $display("[%0t] End", $time);
end
```

**Solution:**
`join_any` unblocks the parent process as soon as **the very first** forked process finishes.

1. At time `0`: "Start" prints. All three forked processes begin waiting.
2. At time `5`: Delay `#5` finishes. "P1" prints. Since one process finished, `join_any` unblocks.
3. At time `5`: "End" prints immediately after `join_any` exits.
4. At time `10`: Delay `#10` finishes in the background. "P3" prints.
5. At time `15`: Delay `#15` finishes in the background. "P2" prints.

**Output Trace:**
```text
[0] Start
[5] P1
[5] End
[10] P3
[15] P2
```
Final simulation time to hit the bottom of the file would be `0` outside the fork (since `join_any` happens inside a single initial block, the block technically finishes at time 5 if there's nothing else after it, but the background processes continue until 15).

---

## SVA: Tracing Assertion Output (Quiz Q3 Style)

**Problem Description:**
Determine the output (PASS/FAIL at each clock edge) for the following code.

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

    ap : assert property (p)
        $display("PASS at %0t", $time);
    else
        $display("FAIL at %0t", $time);
endmodule
```

**Solution:**

Clock period is 10ns (posedge at 5, 15, 25, 35, 45, 55...).

The property says: "If `a` is high at posedge clk, then `b` must be high 1 or 2 cycles later."

| Time | posedge | a (sampled) | b (sampled) | Antecedent? | Check | Result |
|---|---|---|---|---|---|---|
| 5ns | 1st | 1 | 0 | YES | Need b=1 at 15ns or 25ns | b@15=0, b@25=1 --> **PASS** |
| 15ns | 2nd | 0 | 0 | NO | Vacuous pass | **PASS** |
| 25ns | 3rd | 0 | 1 | NO | Vacuous pass | **PASS** |
| 35ns | 4th | 1 | 0 | YES | Need b=1 at 45ns or 55ns | b@45=1 --> **PASS** |
| 45ns | 5th | 1 | 1 | YES | Need b=1 at 55ns or 65ns | Depends on values |

> **Key insight:** When `a` is 0, the implication `a |->` vacuously passes. The assertion only actively checks when `a` is sampled as 1.

---

## SVA: Sequence with Delay (Quiz Q4 Style)

**Problem Description:**
Determine the output for the following code.

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

    ap : assert property (p)
        $display("PASS at %0t", $time);
    else
        $display("FAIL at %0t", $time);
endmodule
```

**Solution:**

The property says: "If `a` is high, then 1 cycle later the sequence `s` must start, where `s` = `b` is high, then `c` is high next cycle."

So the full timeline when `a` matches: cycle+1 must have `b` high, cycle+2 must have `c` high.

| Time | a | b | c | Antecedent? | Check b@+1, c@+2 | Result |
|---|---|---|---|---|---|---|
| 5ns | 1 | 0 | 0 | YES | b@15=0 --> **FAIL** | FAIL at evaluation time |
| 15ns | 0 | 0 | 0 | NO | Vacuous | PASS |
| 25ns | 0 | 1 | 0 | NO | Vacuous | PASS |
| 55ns | 1 | 0 | 0 | YES | b@65=0 --> **FAIL** | FAIL |

---

## Functional Coverage: Computing Bin Hits (Quiz Q1 Style)

**Problem Description:**
Given the following covergroup and sampled values, compute the final coverage percentage.

```verilog
bit [5:0] data;

covergroup cg;
    coverpoint data {
        bins low[]  = {[0:3]};       // 4 bins: {0}, {1}, {2}, {3}
        bins mid    = {[10:20]};     // 1 bin:  covers 10-20
        bins high[] = {50, [60:61]}; // 3 bins: {50}, {60}, {61}
    }
endgroup

cg cg_inst = new();
// Sampled values: 2, 10, 15, 50, 61, 5, 3
```

**Solution:**

**Total bins = 4 + 1 + 3 = 8**

| Value | Bin hit |
|---|---|
| 2 | `low[2]` |
| 10 | `mid` (10 is in [10:20]) |
| 15 | `mid` (already hit, no new bin) |
| 50 | `high[50]` |
| 61 | `high[61]` |
| 5 | No matching bin |
| 3 | `low[3]` |

**Unique bins hit: 5 out of 8**

$$\boxed{\text{Coverage} = \frac{5}{8} \times 100 = 62.50\%}$$

---

## Functional Coverage: Cross with iff (Quiz Q2 Style)

**Problem Description:**
How many cross bins exist and how many are hit?

```verilog
bit [1:0] x, y;
bit valid;

covergroup cg;
    cp_x : coverpoint x {
        bins x0 = {0};
        bins x1 = {1};
        bins x2 = {[2:3]} iff (valid);    // only when valid=1
    }
    cp_y : coverpoint y {
        bins y0 = {0};
        bins y1 = {[1:3]};
    }
    cross cp_x, cp_y;
endgroup

// Samples: (x=0, y=0, valid=0), (x=1, y=2, valid=1), (x=3, y=1, valid=1), (x=2, y=0, valid=0)
```

**Solution:**

**cp_x:** 3 bins (x0, x1, x2)
**cp_y:** 2 bins (y0, y1)
**Cross bins:** 3 x 2 = **6 cross bins**

| Sample | x | y | valid | cp_x hit | cp_y hit | Cross hit |
|---|---|---|---|---|---|---|
| 1 | 0 | 0 | 0 | x0 | y0 | (x0, y0) |
| 2 | 1 | 2 | 1 | x1 | y1 | (x1, y1) |
| 3 | 3 | 1 | 1 | x2 (valid=1) | y1 | (x2, y1) |
| 4 | 2 | 0 | 0 | x2 **SKIPPED** (valid=0) | y0 | No cross (x2 skipped) |

**Cross bins hit: 3 out of 6 = 50%**

---

## Concept Links

- Master the topics behind these problems:
    - [02 Arrays](../02_SV_Advanced/02_User_Defined_Types_and_Arrays.md)
    - [03 Control Flow (Fork-Join)](../02_SV_Advanced/03_Control_Flow_and_Loops.md)
    - [05 Classes & OOP](../02_SV_Advanced/05_Classes_and_OOP.md)
    - [06 TestBench Architecture](../02_SV_Advanced/06_Testbench_Architecture.md)
    - [07 Constraints](../02_SV_Advanced/07_Randomization_and_Constraints.md)
    - [09 SVA](../02_SV_Advanced/09_Assertions_SVA.md)
    - [10 Functional Coverage](../02_SV_Advanced/10_Functional_Coverage.md)







