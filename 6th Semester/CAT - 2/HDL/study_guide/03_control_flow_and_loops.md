# 03 - Control Flow & Loops

## Learning Objectives

After this section you will understand:
- All loop types in SystemVerilog (forever, repeat, while, for, do-while, foreach)
- `break` and `continue` statements
- `unique` and `priority` variants of if-else and case
- Fork-join parallelism (join, join_any, join_none)
- `wait fork` and `disable fork`

---

## SystemVerilog Loops

> **Conceptual Clarity:** Loops in SystemVerilog work the same way as in C/C++. The key difference is that SystemVerilog adds `forever` and `repeat` which are hardware-specific -- `forever` runs an infinite loop (used for clock generation), and `repeat` runs a fixed number of iterations.

### forever

Runs indefinitely. **Must include a time delay** or your simulation will hang.

```verilog
initial begin
    forever begin
        #5 clk = ~clk;    // Toggle clock every 5 time units
    end
end
```

### repeat

Runs the block a fixed number of times.

```verilog
initial begin
    repeat (10) begin
        @(posedge clk);
        $display("Cycle");
    end
    // Prints "Cycle" exactly 10 times
end
```

### while

Runs as long as the condition is true. Condition checked **before** each iteration.

```verilog
int count = 0;
initial begin
    while (count < 5) begin
        $display("count = %0d", count);
        count++;
    end
    // Output: count = 0, 1, 2, 3, 4
end
```

### do-while

Same as while, but condition checked **after** each iteration (guarantees at least one execution).

```verilog
int count = 0;
initial begin
    do begin
        $display("count = %0d", count);
        count++;
    end while (count < 5);
    // Output: count = 0, 1, 2, 3, 4
end
```

### for

Standard C-style for loop.

```verilog
initial begin
    for (int i = 0; i < 10; i++) begin
        $display("i = %0d", i);
    end
end
```

> **Conceptual Clarity:** Note that `int i` is declared **inside** the for loop. This is SystemVerilog -- Verilog required declaring loop variables outside the loop.

### foreach

Iterates over all elements of an array automatically.

```verilog
int arr[5] = '{10, 20, 30, 40, 50};
initial begin
    foreach (arr[i]) begin
        $display("arr[%0d] = %0d", i, arr[i]);
    end
end
```

### break and continue

```verilog
initial begin
    for (int i = 0; i < 10; i++) begin
        if (i == 3) continue;    // Skip iteration when i=3
        if (i == 7) break;       // Exit loop when i=7
        $display("i = %0d", i);
    end
    // Output: 0, 1, 2, 4, 5, 6
end
```

---

## unique and priority if-else

> **Conceptual Clarity:** In regular `if-else`, the simulator just picks the first true condition silently. `unique` and `priority` add **runtime checks** that warn you if your conditions overlap or if none match. This helps catch design bugs that would otherwise go unnoticed.

### unique-if

`unique-if` evaluates conditions in **any order** and:
- Reports an error when **no condition matches** (unless there is an explicit else)
- Reports an error when **more than one condition** is true

```verilog
// No else block -- error if no match
module tb;
    int x = 5;
    initial begin
        unique if (x == 1) $display("one");
        else if (x == 2)   $display("two");
        // WARNING: No condition matched and no else clause!
    end
endmodule
```

```verilog
// Multiple matches -- error
module tb;
    int x = 4;
    initial begin
        unique if (x == 4)  $display("match 1");
        else if (x < 10)    $display("match 2");
        else                 $display("no match");
        // WARNING: More than one condition matched!
        // Output still prints "match 1" (first match)
    end
endmodule
```

### priority-if

`priority-if` evaluates conditions in **sequential order** and:
- Reports an error if **no condition matches** and there is no else clause
- Does **NOT** report an error for multiple matches (takes first match)

```verilog
module tb;
    int x = 4;
    initial begin
        priority if (x == 4) $display("match 1");
        else if (x < 10)     $display("match 2");
        else                  $display("no match");
        // Output: "match 1" -- no error even though both conditions are true
    end
endmodule
```

### Comparison Table

| Feature | `unique-if` | `priority-if` |
|---|---|---|
| Evaluation order | Any order | Sequential |
| No match (no else) | Error | Error |
| Multiple matches | Error | OK (takes first) |

---

## unique and priority case

The same concepts apply to `case` statements:

### unique case

```verilog
module tb;
    int sel = 3;
    initial begin
        unique case (sel)
            0: $display("zero");
            1: $display("one");
            2: $display("two");
            // WARNING: No items match for sel=3!
        endcase
    end
endmodule
```

```verilog
// Multiple matches
module tb;
    int sel = 0;
    initial begin
        unique case (1'b1)
            (sel < 5):  $display("less than 5");
            (sel < 10): $display("less than 10");
        endcase
        // WARNING: More than one case item matches!
    end
endmodule
```

### priority case

```verilog
module tb;
    int sel = 0;
    initial begin
        priority case (1'b1)
            (sel < 5):  $display("less than 5");
            (sel < 10): $display("less than 10");
        endcase
        // Output: "less than 5" -- no error for multiple matches
    end
endmodule
```

---

## Fork-Join (Parallel Execution)

> **Conceptual Clarity:** In real hardware, things happen in parallel -- a clock keeps ticking while data flows through gates. `fork-join` lets you model this parallelism in testbenches. All processes inside a fork start at the **same simulation time**. The three variants differ in WHEN the parent process resumes.

![[fork_join_diagram.png]]

### fork-join

**Waits for ALL** processes to finish before continuing.

```verilog
initial begin
    $display("[%0t] Before fork", $time);
    fork
        begin
            #5 $display("[%0t] Process 1 done", $time);    // finishes at 5ns
        end
        begin
            #20 $display("[%0t] Process 2 done", $time);   // finishes at 20ns
        end
    join
    $display("[%0t] After fork", $time);    // Prints at 20ns
end
```

Output:
```
[0] Before fork
[5] Process 1 done
[20] Process 2 done
[20] After fork          <-- waits for BOTH
```

### fork-join_any

**Waits for ANY ONE** process to finish before continuing. Other processes keep running in background.

```verilog
initial begin
    $display("[%0t] Before fork", $time);
    fork
        begin
            #5 $display("[%0t] Process 1 done", $time);
        end
        begin
            #20 $display("[%0t] Process 2 done", $time);
        end
    join_any
    $display("[%0t] After fork", $time);    // Prints at 5ns
end
```

Output:
```
[0] Before fork
[5] Process 1 done
[5] After fork           <-- unblocked when Process 1 finishes
[20] Process 2 done      <-- Process 2 still runs in background
```

### fork-join_none

**Does NOT wait** at all. Fork block is non-blocking. Parent continues immediately.

```verilog
initial begin
    $display("[%0t] Before fork", $time);
    fork
        begin
            #5 $display("[%0t] Process 1 done", $time);
        end
        begin
            #20 $display("[%0t] Process 2 done", $time);
        end
    join_none
    $display("[%0t] After fork", $time);    // Prints at 0ns!
end
```

Output:
```
[0] Before fork
[0] After fork            <-- does NOT wait at all
[5] Process 1 done
[20] Process 2 done
```

### Comparison Table

| Variant | Parent unblocked when... |
|---|---|
| `fork-join` | ALL child processes complete |
| `fork-join_any` | ANY ONE child process completes |
| `fork-join_none` | Immediately (non-blocking) |

### wait fork

Blocks the current process until **all** previously spawned fork processes complete.

```verilog
initial begin
    fork
        #10 $display("A");
        #20 $display("B");
    join_none

    $display("Doing work...");

    wait fork;    // Block until all forked processes finish
    $display("All forked processes done");
end
```

### disable fork

Terminates all active child processes spawned by the current process.

```verilog
initial begin
    fork
        #10 $display("Process 1");
        #50 $display("Process 2");    // This gets killed
    join_any

    disable fork;    // Kill remaining child processes
    $display("Remaining processes killed");
end
```

---

## Common Mistakes

1. **`forever` without delay** - Your simulation will hang in an infinite loop with no time advancing.
2. **Confusing `unique` and `priority`** - `unique` flags multiple matches; `priority` does not.
3. **`fork-join_any` does not kill other processes** - They keep running in the background. Use `disable fork` if you need to stop them.
4. **`fork-join_none` timing** - Spawned processes do not execute until the parent hits a blocking statement or finishes.

---

## Self-Check Questions

**Q1:** What is the difference between `while` and `do-while`?
> `while` checks the condition BEFORE the first iteration (may execute 0 times). `do-while` checks AFTER (always executes at least once).

**Q2:** If you have `unique-if` with two true conditions, what happens?
> A runtime error/warning is generated for multiple matches. The first matching branch still executes.

**Q3:** `fork-join_any` with processes taking 5ns and 20ns -- when does the parent continue?
> At 5ns (when the first process finishes).

**Q4:** How do you kill all remaining forked processes?
> `disable fork;`

**Q5:** What does `wait fork;` do?
> Blocks until ALL previously spawned fork processes complete.

---

## Concept Links

- Previous: [02 - User-Defined Types & Arrays](./02_user_defined_types_and_arrays.md)
- Next: [04 - Tasks & Functions](./04_tasks_and_functions.md)
- Formula Sheet: [10 - Formula Sheet](./10_formula_sheet.md#control-flow)
