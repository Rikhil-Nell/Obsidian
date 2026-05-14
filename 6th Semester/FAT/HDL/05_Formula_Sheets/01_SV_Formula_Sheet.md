# 12 - Formula & Syntax Sheet

## Quick Reference Guide

This sheet compiles all the key SystemVerilog syntax, keywords, and method signatures covered in this study guide. Use it as a quick lookup table when writing code or reviewing for exams.

---

## 1. Data Types

| Category | Types | Default Signedness | Description |
|---|---|---|---|
| **2-State** | `bit`, `logic` (in synthesis) | Unsigned | 0, 1 only |
| | `byte` | Signed | 8-bit |
| | `shortint` | Signed | 16-bit |
| | `int` | Signed | 32-bit |
| | `longint` | Signed | 64-bit |
| **4-State** | `logic`, `reg` | Unsigned | 0, 1, X, Z |
| | `integer` | Signed | 32-bit |
| | `time` | Unsigned | 64-bit |

**Literals:**
- Fill with 1s: `'1` (e.g., `reg [31:0] a = '1;`)
- Fill with 0s: `'0`
- Fill with Zs: `'z`
- Time: `#10ns;`

---

## 2. Strings

| Objective | Syntax |
|---|---|
| Declaration | `string s = "Hello";` |
| Length | `s.len()` |
| Character access | `s.getc(index)` or `s[index]` |
| Convert Case | `s.toupper()`, `s.tolower()` |
| Compare | `s1 == s2`, `s1.compare(s2)`, `s1.icompare(s2)` |
| Int to String | `s.itoa(integer)`, `s.hextoa(integer)` |
| String to Int | `s.atoi()`, `s.atoreal()` |

---

## 3. Arrays and Queues

| Objective | Syntax |
|---|---|
| **Packed** Array | `logic [31:0] name;` (dimensions BEFORE name) |
| **Unpacked** Array | `logic name [31:0];` (dimensions AFTER name) |
| **Dynamic** Array | `int arr[];` -> `arr = new[5];` |
| Resize Dynamic | `arr = new[10](arr);` (keeps old elements) |
| **Queue** | `int q[$];` |
| Queue Push | `q.push_back(val);`, `q.push_front(val);` |
| Queue Pop | `val = q.pop_back();`, `val = q.pop_front();` |

**Array Methods:**
- Location: `find`, `find_index`, `find_first`, `find_last` (Requires `with (expr)`)
- Ordering: `sort`, `rsort`, `reverse`, `shuffle`
- Reduction: `sum`, `product`, `and`, `or`, `xor`

---

## 4. Control Flow

| Construct | Syntax / Description |
|---|---|
| `forever` | `forever begin ... end` (Must have delay) |
| `repeat` | `repeat (count) begin ... end` |
| `do-while` | `do begin ... end while (cond);` (Executes at least once) |
| `foreach` | `foreach (arr[i]) begin ... end` (Auto-iterates array) |
| `unique if` | `unique if (cond1) ... else if (cond2) ...` (Error if 0 or >1 matches) |
| `priority if` | `priority if (cond1) ... else if (cond2) ...` (Error if 0 matches. Takes first match if >1.) |
| `fork-join` | Waits for ALL processes to finish |
| `fork-join_any` | Waits for ANY ONE process to finish |
| `fork-join_none` | Does NOT wait (non-blocking) |
| `wait fork` | Blocks until all spawned forks complete |
| `disable fork` | Kills all active child processes |

---

## 5. Tasks and Functions

| Feature | Task | Function |
|---|---|---|
| Delays (`#`, `@`, `wait`) | Allowed | **NOT** Allowed |
| Return Value | Via output args | Return single value (or `void`) |
| Calls Tasks | Yes | **No** |
| Calls Functions | Yes | Yes |

**Pass by Reference:**
```verilog
task automatic compute(ref int x);
```
*(Modifies original variable; requires `automatic`)*

---

## 6. Classes (OOP)

| Keyword/Concept | Syntax / Description |
|---|---|
| Constructor | `function new(); ... endfunction` |
| Create object | `handle = new();` |
| `this` | Refers to current object properties: `this.var = var;` |
| Handle copy | `h2 = h1;` (Points to SAME object, does not copy) |
| Inheritance | `class child extends parent;` |
| Overriding | Redefine parent method in child |
| `super` | Access parent method: `super.display();` |
| Polymorphism | Requires `virtual` on base class methods |
| Dynamic Cast | `$cast(child_handle, parent_handle)` |
| `local` | Accessible ONLY inside defining class |
| `protected` | Accessible inside defining class AND child classes |
| Abstract Class | `virtual class name;` (Cannot be instantiated) |

---

## 7. Randomization and Constraints

| Concept | Syntax / Description |
|---|---|
| `rand` | Standard uniform random variable |
| `randc` | Cyclic random (no repeats until full cycle) |
| Randomize call | `if(!obj.randomize()) $error("Fail");` |
| Disable variable | `obj.var.rand_mode(0);` |
| `inside` | `constraint c { var inside {[0:10], 20, 30}; }` |
| `dist` (`:=`) | `var dist { 1:=10, 2:=5 };` (weight 10 for '1', 5 for '2') |
| `dist` (`:/`) | `var dist { [1:5]:/10 };` (weight 10 *divided among* 1-5) |
| Implication | `cond1 -> cond2;` (If cond1 true, cond2 enforced) |
| Disable constraint | `obj.constraint_name.constraint_mode(0);` |
| Inline | `obj.randomize() with { var > 5; };` |
| Soft | `constraint c { soft var > 5; }` (Can be overridden inline) |
| Solve Before | `solve A before B;` (A decided first) |
| System Methods | `$urandom`, `$random`, `$urandom_range(max, min)` |

---

## 8. Inter-Process Communication (IPC)

| Mechanism | Methods | Use Case |
|---|---|---|
| **Semaphore** | `new(keys)`, `get(keys)`, `put(keys)`, `try_get(keys)` | Mutual exclusion, resource sharing |
| **Mailbox** (FIFO)| `new(bound)`, `put(val)`, `get(var)`, `try_put`, `try_get`, `peek`, `num` | Passing data between processes (e.g. Gen -> Drv) |
| **Events** | `->e`, `->>e`, `@(e)`, `wait(e.triggered)` | Signaling synchronization |

**Event Wait Comparison:**
- `@(e)`: Edge-sensitive (can miss simultaneous triggers)
- `wait(e.triggered)`: Level-sensitive (safer for simultaneous triggers)

---

## 9. SystemVerilog Assertions (SVA)

### Assertion Types

| Type | Syntax | Evaluation |
|---|---|---|
| **Immediate** | `assert (expr) else $error(...);` | Current time |
| **Concurrent** | `assert property (@(posedge clk) ...)` | At clock edges |

### Implication Operators

| Operator | Name | Meaning |
|---|---|---|
| `\|->` | Overlapped | Check consequent SAME cycle |
| `\|=>` | Non-overlapped | Check consequent NEXT cycle |

`a \|=> b` is equivalent to `a \|-> ##1 b`

### Timing Constructs

| Syntax | Meaning |
|---|---|
| `##N` | Exactly N cycle delay |
| `##[min:max]` | Delay within range |
| `##[0:N]` | Same cycle or up to N cycles |
| `##[1:$]` | Eventually (no upper bound) |

### Repetition Operators

| Syntax | Meaning |
|---|---|
| `[*N]` | Consecutive repetition (N cycles in a row) |
| `[->N]` | Go-to repetition (N times, gaps allowed) |
| `[=N]` | Non-consecutive repetition (N times, not necessarily ending on last match) |

### SVA Methods

| Method | Returns true when |
|---|---|
| `$rose(sig)` | LSB changed 0 --> 1 |
| `$fell(sig)` | LSB changed 1 --> 0 |
| `$stable(sig)` | Value did NOT change |
| `$past(sig, N)` | Value of sig N cycles ago |

### Built-in System Functions

| Function | Description |
|---|---|
| `$onehot(expr)` | Exactly one bit is 1 |
| `$onehot0(expr)` | At most one bit is 1 |
| `$isunknown(expr)` | Any bit is X or Z |
| `$countones(expr)` | Count of bits that are 1 |

### Keywords

| Keyword | Usage |
|---|---|
| `disable iff (cond)` | Pause assertion when cond is true |
| `seq.ended` | Synchronize on sequence endpoint |
| `sequence ... endsequence` | Define temporal sequence |
| `property ... endproperty` | Define temporal property |

### Severity Levels (Immediate Assertions)

| Task | Effect |
|---|---|
| `$fatal` | Terminate simulation |
| `$error` | Report error (DEFAULT) |
| `$warning` | Report warning |
| `$info` | Informational |

---

## 10. Functional Coverage

### Covergroup Syntax

```verilog
covergroup cg_name [@(event)];
    coverpoint variable {
        bins name   = { values };       // single bin
        bins name[] = { values };       // array bins (one per value)
        bins name   = { [lo:hi] };      // range bin
        bins name   = { val } iff (en); // conditional bin
    }
    cross cp1, cp2;                     // cross coverage
    option.per_instance = 1;            // per-instance tracking
endgroup
```

### Key Methods

| Method | Description |
|---|---|
| `cg_inst = new()` | Construct covergroup instance |
| `cg_inst.sample()` | Manually sample coverage |
| `cg_inst.get_coverage()` | Returns coverage % (0.0 - 100.0) |

### Bin Notation

| Syntax | Result |
|---|---|
| `bins a = {1, 2, 3};` | 1 bin (hit by any of 1, 2, or 3) |
| `bins a[] = {1, 2, 3};` | 3 bins (one per value) |
| `bins a = {[0:7]};` | 1 bin (covers range 0-7) |
| `bins a[] = {[0:7]};` | 8 bins (one per value in range) |
| `bins a = {val} iff (en);` | Conditional: only hit when en=1 |

### Coverage Calculation

$$\text{Coverage \%} = \frac{\text{Bins hit}}{\text{Total bins}} \times 100$$

**Cross bins count** = product of bins in each coverpoint

