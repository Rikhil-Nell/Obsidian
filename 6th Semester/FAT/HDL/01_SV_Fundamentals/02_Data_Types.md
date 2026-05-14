# 2️⃣ Data Types in SystemVerilog

> **Learning Goal:** Master the differences between wire, reg, logic, int, and integer

---

## The Big Three: wire, reg, logic

### 1. Wire

**Definition:** Represents a **physical connection** between hardware elements.

| Property | Description |
|----------|-------------|
| Storage | ❌ Cannot store a value |
| Driver | Requires continuous driver (`assign`) or module/gate output |
| Usage | Combinational connections, driving outputs |

```systemverilog
wire a, b, c;
assign c = a & b;  // 'c' is continuously driven by AND of 'a' and 'b'
```

---

### 2. Reg

**Definition:** A **data storage element** that holds a value.

| Property | Description |
|----------|-------------|
| Storage | ✅ Can store a value |
| Driver | Used in procedural blocks (`always`, `initial`) |
| Usage | Sequential logic |

```systemverilog
reg d;
always @(posedge clk) begin
    d = a & b;  // 'd' updates on the clock edge
end
```

> [!WARNING]
> Despite the name "reg", it doesn't always synthesize to a register/flip-flop! It's just a variable that can hold a value.

---

### 3. Logic (SystemVerilog)

**Definition:** A **unified data type** that can replace both `reg` and `wire`.

| Property | Description |
|----------|-------------|
| Storage | ✅ Can store a value |
| Driver | Can be used in BOTH procedural AND continuous assignments |
| Usage | Recommended for most designs |

```systemverilog
logic a, b, c;
assign c = a & b;        // Continuous assignment ✅

always @(posedge clk) begin
    c <= a | b;          // Procedural assignment ✅
end
```

---

## Comparison Table

| Feature | wire | reg | logic |
|---------|------|-----|-------|
| Stores value | ❌ | ✅ | ✅ |
| Continuous assign | ✅ | ❌ | ✅ |
| Procedural assign | ❌ | ✅ | ✅ |
| Recommended | Legacy | Legacy | ✅ **Use this** |

> [!TIP]
> **Best Practice:** Use `logic` for everything in SystemVerilog. It avoids the ambiguity between `wire` and `reg`.

---

## Integer Types: int vs integer

### int (SystemVerilog)

- **32-bit signed** (guaranteed)
- Can be used in **packed arrays** and structures
- SystemVerilog-specific

```systemverilog
int count = 0;
int arr[10];  // Array of ints
```

### integer (Verilog Legacy)

- **Signed** by default
- Size may **vary** (usually 32-bit, could be 64-bit)
- **Cannot** be used in packed arrays
- Legacy type from Verilog

```systemverilog
integer i;
for (i = 0; i < 10; i++) begin
    // loop body
end
```

---

## int vs integer Comparison

| Feature | int | integer |
|---------|-----|---------|
| Origin | SystemVerilog | Verilog (legacy) |
| Bit width | Always 32-bit | Usually 32, may vary |
| Signed | Yes | Yes |
| Packed arrays | ✅ Allowed | ❌ Not allowed |
| Recommended | ✅ **Use this** | Legacy code only |

---

## Four-State vs Two-State Types

### Four-State Types (X, Z, 0, 1)
```systemverilog
logic   // 4-state
reg     // 4-state
wire    // 4-state
integer // 4-state
```

### Two-State Types (0, 1 only)
```systemverilog
bit     // single bit, 2-state
int     // 32-bit, 2-state
byte    // 8-bit, 2-state
shortint // 16-bit, 2-state
longint // 64-bit, 2-state
```

> [!NOTE]
> Two-state types are **faster in simulation** but can't represent unknown (X) or high-impedance (Z) states.

---

## Quick Reference

```systemverilog
// Use logic for signals (replaces wire/reg)
logic clk, reset;
logic [7:0] data;

// Use int for counters and indices
int i, j, count;

// Use bit for two-state simulation variables
bit done;

// Use byte for 8-bit data
byte char_data;
```

---

## Key Takeaways

- [ ] `wire` = connection, no storage, needs continuous driver
- [ ] `reg` = storage, procedural blocks only
- [ ] `logic` = best of both, **use this in SystemVerilog**
- [ ] `int` = guaranteed 32-bit, use for counters
- [ ] `integer` = legacy, avoid in new code

---

**Next:** [Assignments](./03_Assignments.md) →

