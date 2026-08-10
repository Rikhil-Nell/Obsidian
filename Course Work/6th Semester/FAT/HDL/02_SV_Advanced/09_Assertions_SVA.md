# 09 - SystemVerilog Assertions (SVA)

## Learning Objectives

After this section you will understand:
- The difference between **Immediate** and **Concurrent** assertions
- How to build SVA checkers using **Boolean expressions**, **Sequences**, **Properties**, and **Assert**
- Overlapped (`|->`) vs Non-overlapped (`|=>`) implication
- **Timing windows**: fixed delay, ranges, overlapping, and indefinite
- **Repetition operators**: consecutive `[*N]`, go-to `[->N]`
- **SVA methods**: `$rose`, `$fell`, `$stable`, `$past`
- **Built-in system functions**: `$onehot`, `$onehot0`, `$isunknown`, `$countones`
- `disable iff` for reset handling
- How to write real-world assertions (counter case study)

---

## Why Assertions?

> **Conceptual Clarity:** Assertions are like guardrails on a highway. Your design (the car) is supposed to follow certain rules. Instead of waiting until you crash (wrong output at the end of simulation), assertions catch violations immediately when they happen. This makes debugging dramatically faster because the error message points to the exact rule that was broken and the exact time it broke.

Assertions are used to:
- Check the occurrence of a specific condition or sequence of events
- Provide functional coverage
- Reduce debug time by catching errors at the source

There are two kinds:

| Kind | When Evaluated | Use Case |
|---|---|---|
| **Immediate** | Current simulation time (like `if-else`) | Combinational checks, procedural blocks |
| **Concurrent** | At clock edges, over multiple cycles | Protocol checks, temporal sequences |

---

## Immediate Assertions

Immediate assertions check a condition **right now**, at the current simulation time. They behave like an `if-else` statement with assertion control.

### Syntax

```verilog
[label:] assert (expression)
    [pass_statement]
else
    [fail_statement];
```

### Action Block

- The **pass statement** executes if the expression is true
- The **fail statement** (after `else`) executes if the expression is false
- Both are optional
- If an assertion fails and **no else clause** is specified, the tool calls `$error` by default

### Severity Levels

| System Task | Severity | Description |
|---|---|---|
| `$fatal` | Fatal | Terminates simulation immediately |
| `$error` | Error | Reports error, simulation continues (DEFAULT) |
| `$warning` | Warning | Can be suppressed tool-specifically |
| `$info` | Info | No specific severity, informational |

### Example

```verilog
// Inside an always or initial block
always_comb begin
    a_sum: assert (sum == (a ^ b ^ cin))
        else $error("SUM mismatch: a=%b b=%b cin=%b, got sum=%b", a, b, cin, sum);

    a_cout: assert (cout == ((a & b) | (b & cin) | (a & cin)))
        else $error("COUT mismatch: a=%b b=%b cin=%b, got cout=%b", a, b, cin, cout);
end
```

> **Conceptual Clarity:** This is exactly how your lab code (week-8) places assertions inside the interface's `always_comb` block. Every time the inputs change, the assertion fires immediately and checks if the outputs are correct.

### Immediate Assertion in Interface (Lab Pattern)

```verilog
interface full_adder_if;
    logic a, b, cin;
    logic sum, cout;

    always_comb begin
        assert (sum == (a ^ b ^ cin))
            else $error("ASSERT FAIL [SUM] a=%b b=%b cin=%b | got sum=%b", a, b, cin, sum);
        assert (cout == ((a & b) | (b & cin) | (a & cin)))
            else $error("ASSERT FAIL [COUT] a=%b b=%b cin=%b | got cout=%b", a, b, cin, cout);
    end
endinterface
```

---

## Concurrent Assertions

Concurrent assertions check sequences of events **spread over multiple clock cycles**.

Key differences from immediate assertions:

| Feature | Immediate | Concurrent |
|---|---|---|
| Evaluation | Current time | At clock edges |
| Spans multiple cycles | No | Yes |
| Keyword | None (just `assert`) | Uses `property` |
| Placement | Procedural blocks only | Module, interface, program, or procedural |
| Values used | Current values | **Sampled** values (from preponed region) |

The keyword that differentiates the two is **`property`**.

---

## Building Blocks of SVA

SVA checkers are built from four layers:

```
Boolean Expressions  -->  Sequences  -->  Properties  -->  Assert
      (signals)         (over time)    (complex logic)  (activate)
```

### 1. Boolean Expressions

Simple logical conditions evaluated at a single clock edge:
```verilog
(a && b)
(read && write)    // should never be true together
(!reset)
```

### 2. Sequence

Boolean expressions that evaluate **over a period of time** involving one or more clock cycles.

```verilog
sequence seq_1;
    @(posedge clk) a;    // signal "a" must be high at posedge clk
endsequence
```

> **Conceptual Clarity:** A sequence is like a pattern you expect to see on a waveform. "First I see signal A go high, then 2 clocks later signal B goes high" -- that is a sequence.

### 3. Property

Sequences combined logically or temporally to create complex behaviors.

```verilog
property prop_1;
    @(posedge clk) a |=> b;    // if a is high, b must be high next cycle
endproperty
```

### 4. Assert

The property must be **asserted** to take effect during simulation.

```verilog
assert property (prop_1)
    $display("PASS at %0t", $time);
else
    $display("FAIL at %0t", $time);
```

### Complete Example

```verilog
// "The Read and Write signals should never be asserted together"
sequence s_rw;
    @(posedge clk) (read && write);
endsequence

property p_no_rw;
    @(posedge clk) not (read && write);
endproperty

a_no_rw: assert property (p_no_rw)
    else $error("Read and Write both active at %0t", $time);
```

---

## Implication Operators

Implication operators let you say "**IF** this condition is true, **THEN** check that condition." If the antecedent (left side) is false, the assertion **vacuously passes** (does not fail).

### Overlapped Implication `|->`

The consequent is checked in the **same clock cycle** as the antecedent.

```verilog
property p;
    @(posedge clk) a |-> b;    // if a is high, b must ALSO be high (same cycle)
endproperty
```

### Non-overlapped Implication `|=>`

The consequent is checked in the **next clock cycle** after the antecedent.

```verilog
property p;
    @(posedge clk) a |=> b;    // if a is high NOW, b must be high NEXT cycle
endproperty
```

> **Conceptual Clarity:** Think of it as two different promises:
> - `|->` (overlapped): "If I raise my hand, you must ALREADY be standing" (same moment)
> - `|=>` (non-overlapped): "If I raise my hand, you must stand up NEXT" (next cycle)

### With Fixed Delay

```verilog
// if a is high, b must be high after 2 clock cycles
property p;
    @(posedge clk) a |=> ##2 b;
endproperty
```

Equivalently with `|->`:
```verilog
property p;
    @(posedge clk) a |-> ##3 b;    // ##1 (for |=>) + ##2 = ##3 total from |->
endproperty
```

### With Sequence as Antecedent

```verilog
sequence seq_1;
    a ##1 b;    // a high, then b high next cycle
endsequence

// if seq_1 matches, then d must be low 2 cycles after
property p;
    @(posedge clk) seq_1 |=> ##2 !d;
endproperty
```

---

## Timing Windows

### Fixed Delay `##N`

```verilog
a |-> ##2 b;    // b must be high exactly 2 cycles after a
```

### Range `##[min:max]`

```verilog
// if a is high, b must be high within 1 to 4 cycles
property p;
    @(posedge clk) a |-> ##[1:4] b;
endproperty
```

### Overlapping Window `##[0:N]`

```verilog
// b must be high in the SAME cycle or within 4 cycles
property p;
    @(posedge clk) a |-> ##[0:4] b;
endproperty
```

### Indefinite Window `##[1:$]`

```verilog
// b will eventually be high, starting from next cycle, no upper bound
property p;
    @(posedge clk) a |-> ##[1:$] b;
endproperty
```

The `$` sign means no upper bound -- the checker keeps checking until end of simulation. This is called the **eventuality operator**.

---

## Repetition Operators

### Consecutive Repetition `[*N]`

The signal/sequence must match for N **consecutive** clock cycles.

```verilog
// if a is high, b must be high for 3 consecutive cycles
property p;
    @(posedge clk) a |-> b [*3];
endproperty
```

This means: at the cycle a is high, b must be high, AND the next cycle, AND the cycle after that (3 consecutive cycles total).

### Go-to Repetition `[->N]`

The signal must match N times, but **not necessarily on consecutive** clock cycles.

```verilog
// b must be high 3 times (not necessarily consecutive),
// then c must be high the cycle after b is high for the 3rd time
property p;
    @(posedge clk) a |-> b [->3] ##1 c;
endproperty
```

> **Conceptual Clarity:** 
> - `b [*3]` = "b must be high for 3 cycles in a row" (like running 3 laps without stopping)
> - `b [->3]` = "b must be high 3 times total, gaps allowed" (like running 3 laps, resting between them is OK)

---

## SVA Methods

### `$rose(signal)`

Returns true if the **least significant bit** changed from 0 to 1 (rising edge).

```verilog
sequence seq_rose;
    @(posedge clk) $rose(a);    // a transitioned 0->1
endsequence
```

### `$fell(signal)`

Returns true if the least significant bit changed from 1 to 0 (falling edge).

```verilog
sequence seq_fell;
    @(posedge clk) $fell(a);    // a transitioned 1->0
endsequence
```

### `$stable(signal)`

Returns true if the signal **did not change** from the previous clock cycle.

```verilog
sequence seq_stable;
    @(posedge clk) $stable(a);    // a has the same value as last cycle
endsequence
```

### `$past(signal, N)`

Returns the value of the signal from **N clock cycles ago** (default N=1).

```verilog
// if b is high now, then 2 cycles ago a must have been high
property p;
    @(posedge clk) b |-> ($past(a, 2) == 1);
endproperty
```

### `$past` with Gating Signal

```verilog
// only check if gating signal c is valid
property p;
    @(posedge clk) b |-> ($past(a, 2, , c) == 1);
endproperty
```

The gating signal `c` must be true on the clock edge before checking the consequent condition.

---

## Built-in System Functions

| Function | Description |
|---|---|
| `$onehot(expr)` | Exactly ONE bit is high |
| `$onehot0(expr)` | At most ONE bit is high (zero or one) |
| `$isunknown(expr)` | Any bit is X or Z |
| `$countones(expr)` | Number of bits that are 1 |

### Example

```verilog
// state must always be one-hot encoded
a_1: assert property (@(posedge clk) $onehot(state))
    else $error("State not one-hot: %b", state);

// state must be zero-one-hot
a_2: assert property (@(posedge clk) $onehot0(state))
    else $error("State not zero-one-hot: %b", state);

// bus must never have unknown values
a_3: assert property (@(posedge clk) !$isunknown(bus))
    else $error("Unknown bits on bus: %b", bus);

// bus must have more than one bit high
a_4: assert property (@(posedge clk) $countones(bus) > 1)
    else $error("Bus has <= 1 bit high: %b", bus);
```

---

## `disable iff`

In certain conditions (like during reset), you do not want assertions to fire. `disable iff` stops the checker when the condition is true.

```verilog
property p;
    @(posedge clk) disable iff (reset)
        a |-> b [*3] ##1 c;
endproperty

a_check: assert property (p)
    else $error("Sequence failed at %0t", $time);
```

> **Conceptual Clarity:** `disable iff` is like a "pause button" for the assertion. While reset is high, the assertion pretends it does not exist. Once reset goes low, it starts checking again.

---

## `ended` Keyword

When concatenating sequences, the **ending point** of a sequence can be used as a synchronization point.

```verilog
sequence seq_1;
    a ##1 b;
endsequence

sequence seq_2;
    c ##1 d;
endsequence

// seq_1 ends, then 2 cycles later seq_2 must match
property p;
    @(posedge clk) seq_1.ended |-> ##2 seq_2;
endproperty
```

---

## Case Study: Counter Assertions

This is a real-world example from the course (slides 302-309). A simple UP/DOWN counter is presented as the DUT, with assertions deployed directly at the source.

### Counter DUT

```verilog
module counter (
    input        clk, rst_, ld_cnt_, updn_cnt, count_enb,
    input  [7:0] data_in,
    output logic [7:0] data_out
);
    always @(posedge clk or negedge rst_) begin
        if (!rst_)
            data_out <= 0;
        else begin
            if (!ld_cnt_)                          // LOAD DATA
                data_out <= data_in;
            else if (!count_enb)                   // HOLD DATA
                data_out <= data_out;
            else begin                             // COUNT DATA
                case (updn_cnt)
                    1'b1: data_out <= data_out + 1; // Count UP
                    1'b0: data_out <= data_out - 1; // Count DOWN
                endcase
            end
        end
    end
endmodule
```

### Counter Behavior

| Condition | Behavior |
|---|---|
| `rst_` == 0 (active low) | `data_out = 8'b0` |
| `ld_cnt_` == 0 | `data_out = data_in` (load) |
| `count_enb` == 0 | `data_out` holds (no change) |
| `count_enb` == 1, `updn_cnt` == 1 | `data_out = data_out + 1` |
| `count_enb` == 1, `updn_cnt` == 0 | `data_out = data_out - 1` |

### CHECK #1: Reset Assertion

Check that when `rst_` is asserted (==0), `data_out == 8'b0`.

```verilog
`ifdef check1
property counter_reset;
    @(clk) disable iff (rst_) !rst_ |=> (data_out == 8'b0);
endproperty

counter_reset_check: assert property (counter_reset)
    else $display($stime,,, "\t\tCOUNTER RESET CHECK FAIL:: rst_=%b data_out=%0d \n",
                  rst_, data_out);
`endif
```

### CHECK #2: Hold Assertion

Check that if `ld_cnt_` is deasserted (==1) and `count_enb` is not enabled (==0), then `data_out` holds its previous value. Disable this property if `rst` is low.

```verilog
`ifdef check2
property counter_hold;
    @(posedge clk) disable iff (!rst_) (ld_cnt_ & !count_enb) |=> data_out == $past(data_out);
endproperty

counter_hold_check: assert property (counter_hold)
    else $display($stime,,, "\t\tCOUNTER HOLD CHECK FAIL:: counter HOLD \n");
`endif
```

### CHECK #3: Count Assertion (using `$past`)

Check that if `ld_cnt_` is deasserted and `count_enb` is enabled, the count goes UP when `updn_cnt==1` and DOWN when `updn_cnt==0`. Disable if `rst` is low.

```verilog
`ifdef check3
property counter_count;
    @(posedge clk) disable iff (!rst_) (ld_cnt_ & count_enb) |->
        if (updn_cnt) ##1 (data_out - 8'h01) == $past(data_out)
        else          ##1 (data_out + 8'h01) == $past(data_out);
endproperty

counter_count_check: assert property (counter_count)
    else $display($stime,,, "\t\tCOUNTER COUNT CHECK FAIL:: UPDOWN COUNT using $past \n");
`endif
```

### CHECK #3 Alternate: Using Local Variables

```verilog
`ifdef check3
property counter_count_local;
    logic [7:0] local_data;
    @(posedge clk) disable iff (!rst_) (ld_cnt_ & count_enb, local_data = data_out)
    |->
        if (updn_cnt) ##1 (data_out == (local_data + 8'h01))
        else          ##1 (data_out == (local_data - 8'h01));
endproperty

counter_count_check: assert property (counter_count_local)
    else $display($stime,,, "\t\tCOUNTER COUNT CHECK FAIL \n");
`endif
```

> **Conceptual Clarity:** The local variable `local_data` captures `data_out` at the moment the antecedent matches. This gives you a "snapshot" of the value to compare against one cycle later. This is an alternative to using `$past`.

---

## Assertion Inside a Case Statement

A concurrent assertion can be placed inside procedural code:

```verilog
always_ff @(posedge clk) begin
    case (state)
        s0: begin
            state <= s1;
            assert property ($past(state) == s4);
        end
        // ...
    endcase
end
```

The assertion is evaluated at every rising edge of the clock but only fires when the `s0` branch of the case statement is executed.

---

## Common Mistakes

1. **Confusing `|->` and `|=>`** -- `|->` checks the same cycle, `|=>` checks the next cycle. Off-by-one errors are the most common SVA bug.
2. **Forgetting `disable iff`** -- During reset, all bets are off. Without `disable iff (reset)`, assertions will fire false failures during reset sequences.
3. **Vacuous pass** -- If the antecedent (left side of `|->`) is never true, the assertion always passes. This can hide bugs. Use coverage to ensure antecedents are exercised.
4. **Using `|=>` when you mean `|-> ##1`** -- They are equivalent, but mixing them causes confusion.
5. **Not asserting the property** -- Writing `property p; ... endproperty` without `assert property (p)` means it is never checked.

---

## Self-Check Questions

**Q1:** What is the difference between `|->` and `|=>`?
> `|->` is overlapped: the consequent is checked in the same cycle as the antecedent match. `|=>` is non-overlapped: the consequent is checked in the next clock cycle. `a |=> b` is equivalent to `a |-> ##1 b`.

**Q2:** What happens if the antecedent of an implication is false?
> The assertion vacuously passes. It does not fail. This is by design -- implication means "IF this, THEN that." If "this" never happens, the rule is not violated.

**Q3:** Given `property p; @(posedge clk) a |-> ##[1:2] b; endproperty`, when must `b` be high?
> If `a` is high at a positive clock edge, then `b` must be high either 1 or 2 clock cycles later (at least one of those two cycles).

**Q4:** What does `disable iff (reset)` do?
> It deactivates the assertion whenever `reset` is true. The assertion is not evaluated and cannot produce pass or fail results during reset.

**Q5:** What is the difference between `b [*3]` and `b [->3]`?
> `b [*3]` requires b to be high for 3 **consecutive** clock cycles. `b [->3]` requires b to be high on 3 clock cycles total, but they do not need to be consecutive.

**Q6:** In the counter example, why does CHECK #2 use `$past(data_out)` in the consequent?
> Because we want to check that the output HELD its value (did not change). `data_out == $past(data_out)` means "the current value equals what it was one cycle ago."

---

## Concept Links

- Previous: [08 - Inter-Process Communication](./08_Interprocess_Communication.md)
- Next: [10 - Functional Coverage](./10_Functional_Coverage.md)
- Worked Problems: [11 - Worked Problems](../04_Practice_Problems/04_Worked_Problems.md)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#sva)




