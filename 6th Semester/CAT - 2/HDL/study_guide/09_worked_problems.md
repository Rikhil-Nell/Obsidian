# 09 - Worked Problems

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

## Concept Links

- Master the topics behind these problems:
    - [02 Arrays](./02_user_defined_types_and_arrays.md)
    - [03 Control Flow (Fork-Join)](./03_control_flow_and_loops.md)
    - [05 Classes & OOP](./05_classes_and_oop.md)
    - [06 TestBench Architecture](./06_testbench_architecture.md)
    - [07 Constraints](./07_randomization_and_constraints.md)
