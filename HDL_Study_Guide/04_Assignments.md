# 3️⃣ Assignments in SystemVerilog

> **Learning Goal:** Master blocking vs non-blocking, procedural vs continuous assignments

---

## Two Categories of Assignments

| Type | Keyword/Syntax | Used For |
|------|----------------|----------|
| **Continuous** | `assign` | Combinational logic |
| **Procedural** | Inside `always`/`initial` | Sequential logic |

---

## Continuous Assignment

**Definition:** Describes combinational logic where outputs are **continuously driven** based on inputs.

```systemverilog
wire y;
assign y = a & b;  // 'y' continuously updates as 'a' and 'b' change
```

### Characteristics
- ✅ Used with `assign` statements
- ✅ Target must be a `wire` (or `logic`)
- ✅ Right-hand side is continuously evaluated
- ✅ For combinational logic and module connections

---

## Procedural Assignment

**Definition:** Used inside procedural blocks (`always`, `initial`) to describe sequential behavior.

```systemverilog
reg q;
always @(posedge clk) begin
    q <= d;  // Non-blocking assignment for sequential logic
end
```

### Two Types

| Type | Symbol | Execution |
|------|--------|-----------|
| Blocking | `=` | Immediate, in order |
| Non-blocking | `<=` | Scheduled, end of time step |

---

## Blocking Assignment (=)

**Execution:** Executes **immediately** in the order written.

```systemverilog
always @(*) begin
    y = a & b;       // Step 1: y gets a & b
    z = y | c;       // Step 2: z uses UPDATED value of y
end
```

### Use Case
- ✅ **Combinational logic** inside procedural blocks
- ✅ When you need values to update immediately

### How It Works
```
Time 0: y = a & b    → y updated immediately
Time 0: z = y | c    → z uses new value of y
```

---

## Non-Blocking Assignment (<=)

**Execution:** Schedules the value to update at the **end of the time step** (delta cycle).

```systemverilog
always @(posedge clk) begin
    q1 <= d;     // Step 1: Schedule q1 = d
    q2 <= q1;    // Step 2: Schedule q2 = OLD value of q1
end
```

### Use Case
- ✅ **Sequential logic** (flip-flops, registers)
- ✅ Modeling hardware that updates simultaneously

### How It Works
```
Clock edge arrives:
  - Read d, q1 values (old values!)
  - Schedule: q1 will become d
  - Schedule: q2 will become old q1
End of time step:
  - All updates happen simultaneously
```

> [!IMPORTANT]
> With non-blocking, **all reads happen before any writes**. This models real flip-flop behavior!

---

## Comparison: = vs <=

| Aspect | Blocking (=) | Non-Blocking (<=) |
|--------|--------------|-------------------|
| Execution | Immediate, sequential | Scheduled, end of timestep |
| Use for | Combinational logic | Sequential logic |
| Order matters? | YES | NO (all read old values) |
| Models | Wires, gates | Flip-flops, registers |

---

## Common Mistake ⚠️

**Wrong:** Using blocking in sequential logic

```systemverilog
// ❌ BAD - causes race conditions!
always @(posedge clk) begin
    q1 = d;      // q1 updates immediately
    q2 = q1;     // q2 gets NEW q1, not old!
end
```

**Correct:** Using non-blocking in sequential logic

```systemverilog
// ✅ GOOD - models proper flip-flop chain
always @(posedge clk) begin
    q1 <= d;     // Schedule q1 = d
    q2 <= q1;    // Schedule q2 = OLD q1
end
```

---

## The initial Block

The `initial` block is a **procedural block** that:
- Executes **once** at the start of simulation
- Statements run **sequentially**
- Used for testbench initialization

```systemverilog
initial begin
    a = 0;           // Time 0
    #5 a = 1;        // Time 5
    #10 a = 0;       // Time 15
end
```

### Why is it "Procedural"?
1. **Sequential execution** - statements run in order
2. **Defined start/end** - begins at time 0, ends when done
3. **Supports control flow** - loops, conditionals, delays

> [!NOTE]
> Without delays (`#`), all statements execute in **zero simulation time** but still in order.

---

## $stop vs $finish

| Task | Effect |
|------|--------|
| `$stop` | Pauses simulation (can resume) |
| `$finish` | Terminates simulation completely |

```systemverilog
initial begin
    #100 $stop;    // Pause at time 100 (can continue)
    #200 $finish;  // End simulation at time 200
end
```

---

## Practical Examples

### Example 1: Combinational MUX (use wire + assign)

```systemverilog
module mux(output wire y, input wire a, b, sel);
    assign y = sel ? a : b;  // Continuous assignment
endmodule
```

### Example 2: D Flip-Flop (use logic + <=)

```systemverilog
module d_flip_flop(output logic q, input logic d, clk);
    always @(posedge clk) begin
        q <= d;  // Non-blocking for sequential
    end
endmodule
```

### Example 3: Shift Register

```systemverilog
module shift_register(output reg [3:0] q, input wire d, clk);
    always @(posedge clk) begin
        q[0] <= d;       // Input to first stage
        q[1] <= q[0];    // Shift through stages
        q[2] <= q[1];
        q[3] <= q[2];
    end
endmodule
```

---

## Key Takeaways

- [ ] `assign` = continuous, for combinational logic
- [ ] `=` (blocking) = immediate, for combinational inside always
- [ ] `<=` (non-blocking) = scheduled, for sequential logic
- [ ] `initial` = procedural block, runs once at start
- [ ] **Golden Rule:** Use `<=` in `always @(posedge clk)`

---

**Next:** [[05_Strings]] →
