# 07 - Randomization & Constraints

## Learning Objectives

After this section you will understand:
- `rand` vs `randc` keywords
- `randomize()` method and `rand_mode()`
- Constraint blocks (internal, external, inherited)
- `inside` operator, weighted distribution (`dist`, `:=`, `:/`)
- Implication (`->`) and if-else constraints
- `foreach` constraints, inline constraints, functions in constraints
- `constraint_mode()`, static constraints, soft constraints, unique constraints
- Solve-before
- Random system methods: `$urandom`, `$random`, `$urandom_range`

---

## What is Randomization?

> **Conceptual Clarity:** Testing hardware with only hand-picked inputs is like testing a car only on smooth roads. Randomization generates thousands of unpredictable inputs automatically, catching corner-case bugs that manual testing would miss. SystemVerilog lets you randomize class properties with a single method call, and constraints let you control WHAT random values are valid.

---

## `rand` and `randc`

### `rand` -- Standard Random

Values are uniformly distributed over the valid range. The same value CAN appear consecutively.

```verilog
class packet;
    rand bit [3:0] addr;    // Random: 0-15, uniform distribution
    rand bit [7:0] data;
endclass
```

### `randc` -- Random Cyclic

Values are generated in a permutation -- every possible value appears once before any value repeats. Like shuffling a deck of cards.

```verilog
class packet;
    randc bit [1:0] mode;   // Cyclic: generates 0,1,2,3 in random order
                             // then repeats the full cycle
endclass
```

> **Conceptual Clarity:** If `addr` is `rand bit [1:0]`, calling `randomize()` four times might give: 2, 2, 0, 3 (repeats allowed). If `addr` is `randc bit [1:0]`, it guarantees something like: 3, 1, 0, 2 (no repeats until all values seen).

### Calling `randomize()`

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit [7:0] data;
endclass

module tb;
    initial begin
        packet pkt = new();

        repeat (5) begin
            if (!pkt.randomize())
                $fatal("Randomization failed!");
            $display("addr = %0h, data = %0h", pkt.addr, pkt.data);
        end
    end
endmodule
```

---

## Disable Randomization (`rand_mode`)

Disables randomization for specific variables. The disabled variable keeps its current value.

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit [7:0] data;
endclass

module tb;
    initial begin
        packet pkt = new();

        // Disable randomization for addr only
        pkt.addr.rand_mode(0);    // 0 = disabled
        pkt.addr = 4'hF;          // Set manually

        pkt.randomize();          // Only data gets randomized
        $display("addr = %0h", pkt.addr);   // Always F

        // Re-enable
        pkt.addr.rand_mode(1);    // 1 = enabled

        // Check status
        $display("addr rand_mode = %0d", pkt.addr.rand_mode());

        // Disable ALL randomization for the object
        pkt.rand_mode(0);         // All rand variables disabled
    end
endmodule
```

---

## Constraint Blocks

> **Conceptual Clarity:** Constraints are rules that limit what random values are valid. Without constraints, a `rand bit [7:0]` could be anything from 0-255. With a constraint `addr > 5`, it will only generate 6-255. Constraints are like a filter on the random number generator.

### Constraint Block Inside the Class

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit [7:0] data;

    constraint addr_range {
        addr > 3;
        addr < 12;
    }
    // addr will only be 4, 5, 6, 7, 8, 9, 10, or 11
endclass
```

### External Constraint Block

Define the constraint name inside the class, but write the body outside.

```verilog
class packet;
    rand bit [3:0] addr;

    constraint addr_range;    // Declaration only (like extern function)
endclass

// Definition outside the class
constraint packet::addr_range {
    addr > 3;
    addr < 12;
}
```

### Constraint Inheritance

Constraints are inherited by child classes and can be overridden by redefining with the same name.

```verilog
class parent;
    rand bit [3:0] addr;
    constraint addr_range { addr > 5; }
endclass

class child extends parent;
    constraint addr_range { addr < 3; }    // Override parent's constraint
endclass

module tb;
    initial begin
        child c = new();
        c.randomize();
        $display("addr = %0d", c.addr);    // Will be 0, 1, or 2
    end
endmodule
```

---

## `inside` Operator

Constrain a variable to be within a set of values or a range.

```verilog
class packet;
    rand bit [3:0] addr;

    // addr must be one of: 2, 4, 6, 8, or in range 10-15
    constraint addr_c {
        addr inside {2, 4, 6, 8, [10:15]};
    }
endclass
```

### Inverted inside (exclusion)

```verilog
class packet;
    rand bit [3:0] addr;

    // addr must NOT be 0, 1, 2, or 3
    constraint addr_c {
        !(addr inside {[0:3]});
    }
endclass
```

---

## Weighted Distribution (`dist`)

> **Conceptual Clarity:** Normal randomization gives equal probability to every valid value. `dist` lets you make some values appear MORE often than others. Like a loaded dice where 6 comes up 50% of the time.

### `:=` Operator (Weight per Value)

Each value (or each value in a range) gets the specified weight.

```verilog
class packet;
    rand bit [3:0] addr;

    constraint addr_dist {
        addr dist {
            0     := 1,     // weight 1
            [1:3] := 5,     // weight 5 for EACH of 1, 2, 3
            4     := 10     // weight 10
        };
    }
    // Total weights: 1 + 5 + 5 + 5 + 10 = 26
    // P(0) = 1/26, P(1) = P(2) = P(3) = 5/26, P(4) = 10/26
endclass
```

### `:/` Operator (Weight Divided Among Range)

The specified weight is **divided equally** among all values in the range.

```verilog
class packet;
    rand bit [3:0] addr;

    constraint addr_dist {
        addr dist {
            0     := 1,     // weight 1
            [1:3] :/ 6,     // weight 6 ÷ 3 = weight 2 for each of 1, 2, 3
            4     := 10     // weight 10
        };
    }
    // Total weights: 1 + 2 + 2 + 2 + 10 = 17
    // P(0) = 1/17, P(1) = P(2) = P(3) = 2/17, P(4) = 10/17
endclass
```

### Key Difference

| Operator | Range `[1:3] := 6` | Range `[1:3] :/ 6` |
|---|---|---|
| Weight for value 1 | 6 | 6 ÷ 3 = 2 |
| Weight for value 2 | 6 | 6 ÷ 3 = 2 |
| Weight for value 3 | 6 | 6 ÷ 3 = 2 |
| Total weight for range | 18 | 6 |

---

## Implication Operator (`->`)

The implication operator creates conditional constraints: "IF this is true, THEN that must hold."

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit [7:0] data;
    rand bit       mode;

    constraint mode_c {
        mode == 1 -> addr > 8;
        // IF mode is 1, THEN addr must be > 8
        // IF mode is 0, addr is unconstrained
    }
endclass
```

### if-else in Constraints

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit       mode;

    constraint mode_c {
        if (mode == 1)
            addr > 8;
        else
            addr < 4;
    }
endclass
```

---

## `foreach` Constraints

Constrain array elements using a loop inside the constraint block.

```verilog
class packet;
    rand bit [3:0] arr[5];

    constraint arr_c {
        foreach (arr[i]) {
            arr[i] > 3;
            arr[i] < 12;
        }
    }
endclass
```

---

## Disable Constraints (`constraint_mode`)

```verilog
class packet;
    rand bit [3:0] addr;

    constraint range_1 { addr > 5; }
    constraint range_2 { addr < 10; }
endclass

module tb;
    initial begin
        packet pkt = new();

        // Disable a specific constraint
        pkt.range_1.constraint_mode(0);    // Disable range_1
        pkt.randomize();                    // Only range_2 is active

        // Re-enable
        pkt.range_1.constraint_mode(1);
    end
endmodule
```

### Static Constraints

A static constraint mode change affects ALL objects of that class.

```verilog
class packet;
    rand bit [3:0] addr;
    static constraint addr_c { addr > 5; }
endclass

// Disabling addr_c affects ALL packet objects, not just one
```

---

## Inline Constraints

Add constraints at the point of randomization without defining them in the class.

```verilog
class packet;
    rand bit [3:0] addr;
    rand bit [7:0] data;
endclass

module tb;
    initial begin
        packet pkt = new();

        // Inline constraint using 'with'
        pkt.randomize() with {
            addr > 5;
            data < 100;
        };
    end
endmodule
```

### Inline + Class Constraints Together

```verilog
class packet;
    rand bit [3:0] addr;
    constraint addr_c { addr > 2; }    // Class constraint
endclass

module tb;
    initial begin
        packet pkt = new();
        pkt.randomize() with { addr < 10; };   // Inline constraint
        // BOTH constraints active: addr > 2 AND addr < 10
        // So addr will be 3-9
    end
endmodule
```

---

## Functions in Constraints

When a constraint is too complex for a single expression, use a function.

```verilog
class packet;
    rand bit [3:0] addr;

    // Helper function
    function bit is_valid(bit [3:0] val);
        return (val != 0 && val != 15);    // Not min or max
    endfunction

    constraint addr_c {
        is_valid(addr) == 1;    // Use function in constraint
    }
endclass
```

---

## Soft Constraints

A soft constraint can be **overridden** by inline constraints without causing a conflict error.

```verilog
class packet;
    rand bit [3:0] addr;
    constraint addr_c { soft addr > 5; }    // Soft: can be overridden
endclass

module tb;
    initial begin
        packet pkt = new();
        pkt.randomize() with { addr == 2; };    // Overrides soft constraint
        // No error! addr = 2 (soft constraint yields to inline)
    end
endmodule
```

> **Conceptual Clarity:** Without `soft`, having `addr > 5` in the class and `addr == 2` inline would cause a randomization failure (contradiction). With `soft`, the class constraint gracefully yields.

---

## Unique Constraints

Ensures array elements are all different.

```verilog
class packet;
    rand bit [3:0] arr[4];
    constraint unique_c { unique {arr}; }
    // All elements of arr will have distinct values
endclass
```

---

## Solve-Before

Controls the order in which the solver processes variables.

```verilog
class packet;
    rand bit       mode;
    rand bit [3:0] addr;

    constraint order_c {
        mode == 1 -> addr > 8;
        solve mode before addr;
        // First decide mode, THEN decide addr based on mode
    }
endclass
```

> **Conceptual Clarity:** Without `solve before`, the solver might pick addr=3 first, then be forced to set mode=0 (because mode=1 requires addr>8). With `solve mode before addr`, mode is decided independently first, giving more balanced distributions.

---

## Random System Methods

These are standalone methods (not class-based).

```verilog
module tb;
    initial begin
        int val;

        // $urandom -- returns unsigned 32-bit random number
        val = $urandom();
        $display("urandom: %0d", val);

        // $random -- returns signed 32-bit random number
        val = $random();
        $display("random: %0d", val);

        // $urandom_range(max, min) -- unsigned random in [min, max]
        val = $urandom_range(100, 50);    // 50 to 100
        $display("range: %0d", val);

        val = $urandom_range(10);          // 0 to 10 (min defaults to 0)
        $display("range: %0d", val);
    end
endmodule
```

---

## Common Mistakes

1. **Not checking `randomize()` return** - Always check: `if (!obj.randomize()) $fatal("Failed!");`
2. **Contradictory constraints** - `addr > 10` and `addr < 5` together = failure.
3. **Confusing `:=` and `:/`** - `:=` gives weight to EACH value; `:/` divides weight AMONG values.
4. **Forgetting `soft`** - Without `soft`, class constraints cannot be overridden by inline constraints.

---

## Self-Check Questions

**Q1:** What is the difference between `rand` and `randc`?
> `rand`: uniform random, repeats allowed. `randc`: cyclic, all values appear once before repeating.

**Q2:** `[1:5] := 10` vs `[1:5] :/ 10` -- what is the weight of value 3?
> `:=` gives weight 10 to each, so value 3 has weight 10. `:/` divides 10 by 5, so value 3 has weight 2.

**Q3:** What does `mode == 1 -> addr > 8` mean?
> IF mode equals 1, THEN addr must be greater than 8. If mode is 0, addr has no constraint from this rule.

**Q4:** How do you add a constraint only for one specific randomization call?
> Use inline constraint: `obj.randomize() with { addr < 5; };`

**Q5:** What does `solve mode before addr` do?
> Forces the solver to determine mode's value first, then solve addr considering mode's value.

---

## Concept Links

- Previous: [06 - TestBench Architecture](./06_testbench_architecture.md)
- Next: [08 - Inter-Process Communication](./08_interprocess_communication.md)
- Related: [05 - Classes & OOP](./05_classes_and_oop.md) (constraints live inside classes)
- Formula Sheet: [10 - Formula Sheet](./10_formula_sheet.md#randomization)
