# 04 - Tasks & Functions

## Learning Objectives

After this section you will understand:
- The difference between tasks and functions
- Static vs automatic tasks/functions
- Argument passing: by value, by reference, by name
- Default argument values and directions

---

## Tasks vs Functions -- What is the Difference?

> **Conceptual Clarity:** Both tasks and functions are reusable blocks of code (like functions in C). The critical difference: **tasks can consume simulation time** (they can have delays like `#10`, `@posedge clk`, `wait`), while **functions execute instantly** (zero simulation time, no delays allowed). Think of a function as a calculator (instant result) and a task as a recipe (takes time to complete).

| Feature | Task | Function |
|---|---|---|
| Time-consuming statements (`#`, `@`, `wait`) | Allowed | **NOT** allowed |
| Can call tasks | Yes | **No** |
| Can call functions | Yes | Yes |
| Return value | No explicit return (use output args) | Returns exactly one value |
| `void` return | N/A | Allowed (void function) |

---

## Task Declaration

```verilog
// Basic task
task display_info;
    $display("Hello from task");
endtask

// Task with time delay (functions CANNOT do this)
task wait_cycles(int n);
    repeat (n) @(posedge clk);
endtask

// Task with input/output arguments
task add(input int a, input int b, output int result);
    result = a + b;
endtask
```

### Calling a Task

```verilog
initial begin
    int sum;
    display_info();          // Call simple task
    wait_cycles(5);          // Wait 5 clock cycles
    add(10, 20, sum);        // sum = 30
    $display("Sum = %0d", sum);
end
```

---

## Function Declaration

```verilog
// Function with return value
function int multiply(int a, int b);
    return a * b;
endfunction

// Function with return via function name (older style)
function int square(int x);
    square = x * x;         // Assign to function name
endfunction

// Void function (returns nothing)
function void print_value(int v);
    $display("Value = %0d", v);
endfunction
```

### Calling a Function

```verilog
initial begin
    int result;
    result = multiply(3, 4);       // result = 12
    $display("%0d", result);

    result = square(5);            // result = 25

    print_value(42);               // Prints: Value = 42

    void'(multiply(2, 3));         // Discard return value explicitly
end
```

---

## Static vs Automatic

> **Conceptual Clarity:** Imagine two photocopying machines. A **static** machine has one sheet of paper that everyone writes on -- if two people use it at the same time, they overwrite each other's work. An **automatic** machine gives each person their own fresh sheet -- no interference. In code, static tasks/functions share memory across all calls; automatic ones get their own private copy each time.

| Type | Behavior |
|---|---|
| `static` (default) | All calls share the same storage. Dangerous with concurrent calls. |
| `automatic` | Each call gets its own storage (like local variables in C). Safe with `fork`. |

```verilog
// Static task (default) -- shared storage
task static_task;
    int count = 0;
    count++;
    $display("Count = %0d", count);
endtask
// Calling twice: Count = 1, Count = 2 (retains value!)

// Automatic task -- private storage per call
task automatic auto_task;
    int count = 0;
    count++;
    $display("Count = %0d", count);
endtask
// Calling twice: Count = 1, Count = 1 (fresh each time)
```

### Mixing Static and Automatic

SystemVerilog allows:
- Declaring an `automatic` variable inside a `static` task
- Declaring a `static` variable inside an `automatic` task

```verilog
task static_task;
    automatic int local_var = 0;    // This variable is automatic (private per call)
    local_var++;
    $display("local_var = %0d", local_var);
endtask
```

---

## Argument Passing Mechanisms

### Default Argument Direction and Type

- Default direction: `input` (if not specified)
- Default type: `logic` (if not specified)

```verilog
// These are equivalent:
task my_task(a, b);           // Both are input logic (default)
task my_task(input logic a, input logic b);
```

### Pass by Value (Default)

The argument is **copied** into the task/function. Changes inside do NOT affect the original.

```verilog
function void modify(int x);
    x = 100;    // Only modifies local copy
endfunction

initial begin
    int a = 5;
    modify(a);
    $display("a = %0d", a);    // Output: a = 5 (unchanged!)
end
```

### Pass by Reference (`ref`)

A **reference** to the original variable is passed. Changes inside DO affect the original.

```verilog
function void modify(ref int x);
    x = 100;    // Modifies the ORIGINAL variable
endfunction

initial begin
    int a = 5;
    modify(a);
    $display("a = %0d", a);    // Output: a = 100 (changed!)
end
```

> **Conceptual Clarity:** Pass by value is like giving someone a photocopy of a document -- they can scribble on it without affecting your original. Pass by reference is like giving them your actual document -- any changes they make are permanent.

### Pass by Reference Example

```verilog
task automatic compute(ref int x, ref int y, ref int z);
    z = x + y;
endtask

initial begin
    int a = 10, b = 15, c;
    compute(a, b, c);
    $display("Value of z = %0d", c);    // Output: Value of z = 25
end
```

### Pass by Name

Arguments can be passed in any order by specifying the parameter name.

```verilog
task display(input string name, input int age);
    $display("%s is %0d years old", name, age);
endtask

initial begin
    // Normal positional call
    display("Alice", 25);

    // Pass by name (any order)
    display(.age(30), .name("Bob"));
end
```

### Default Argument Values

```verilog
function int add(int a, int b = 10);
    return a + b;
endfunction

initial begin
    $display("%0d", add(5, 20));    // Output: 25
    $display("%0d", add(5));        // Output: 15 (b defaults to 10)
end
```

---

## Key Rules Summary

| Rule | Details |
|---|---|
| Tasks can have delays | `#10`, `@(posedge clk)`, `wait()` |
| Functions cannot have delays | Zero simulation time only |
| Functions return one value | Or void for no return |
| Tasks use output arguments | For returning values |
| `ref` only in automatic | Pass by reference requires automatic context |
| Multiple statements | No `begin-end` needed in SV (unlike Verilog) |

---

## Common Mistakes

1. **Putting `#delay` in a function** - Functions cannot consume time. Use a task instead.
2. **Calling a task from a function** - Not allowed. Functions can only call other functions.
3. **Using `ref` in a static task** - `ref` arguments require an `automatic` task/function.
4. **Forgetting `void'()` when discarding return** - The compiler may warn if you ignore a function's return value.

---

## Self-Check Questions

**Q1:** Can a function contain `@(posedge clk)`?
> No. Functions cannot contain any time-consuming statements.

**Q2:** What is the default argument direction if not specified?
> `input`

**Q3:** What is the difference between pass by value and pass by reference?
> By value: a copy is made, original is unaffected. By reference (`ref`): the original variable is directly modified.

**Q4:** What happens if you call a static task concurrently from two `fork` threads?
> Both calls share the same storage, so variables can get overwritten unpredictably. Use `automatic` instead.

**Q5:** Can you pass arguments by name in SystemVerilog?
> Yes. Use `.param_name(value)` syntax to pass in any order.

---

## Concept Links

- Previous: [03 - Control Flow & Loops](./03_control_flow_and_loops.md)
- Next: [05 - Classes & OOP](./05_classes_and_oop.md)
- Formula Sheet: [10 - Formula Sheet](./10_formula_sheet.md#tasks-and-functions)
