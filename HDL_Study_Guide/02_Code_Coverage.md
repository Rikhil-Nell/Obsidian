# 9️⃣ Code and Functional Coverage

> **Learning Goal:** Understand how to measure verification completeness

---

## What is Code Coverage?

Code coverage measures **how efficiently your verification tests exercise the DUT (Design Under Test)**.

- Provides **quantitative measurement** of testing space
- Describes the **degree to which source code has been tested**
- Also called **structural coverage**

> [!TIP]
> Code coverage is automatically collected by simulation tools - no additional coding required!

---

## Questions Code Coverage Answers

| Question | Coverage Type |
|----------|---------------|
| Have all branches in `case`/`if` been entered? | Branch Coverage |
| Have all conditions been simulated? | Conditional Coverage |
| Have all variables been toggled? | Toggle Coverage |
| Have all RTL statements been executed? | Statement Coverage |
| Have all FSM states been entered? | FSM Coverage |
| Have all paths been exercised? | Path Coverage |

---

## Types of Code Coverage

### 1. Statement / Line Coverage
Checks if **every line of code** has been executed at least once.

```systemverilog
always @(posedge a) begin
    c = b && a;        // Line 1 - executed?
    if (c && f)
        b = e;         // Line 2 - executed?
    else
        e = b;         // Line 3 - executed?
end
```

### 2. Branch Coverage
Checks if **every branch** (if/else, case) has been taken.

```systemverilog
if (c && f)
    b = e;      // Branch 1: condition TRUE
else
    e = b;      // Branch 2: condition FALSE
```

### 3. Conditional Coverage
Checks if **every Boolean sub-expression** has been TRUE and FALSE.

```systemverilog
if (c && f)  // Need: c=T/F, f=T/F, and combinations
```

### 4. Toggle Coverage
Checks if every **signal has transitioned** 0→1 and 1→0.

```systemverilog
reg signal;
// Need: signal to go 0→1 and 1→0 during simulation
```

### 5. FSM Coverage
Checks if:
- All **states** have been entered
- All **legal transitions** have been exercised

```
     ┌─────┐         ┌─────┐
     │IDLE │────────▶│BUSY │
     └──┬──┘         └──┬──┘
        │      ▲        │
        │      │        │
        ▼      │        ▼
     ┌─────┐   │     ┌─────┐
     │DONE │───┘     │ERROR│
     └─────┘         └─────┘
```

### 6. Path Coverage
Checks if **all possible paths** through a block have been exercised.

---

## Coverage Example

```systemverilog
module dut();
    reg a, b, c, d, e, f;
    
    initial begin
        #5 a = 0;
        #5 a = 1;
    end
    
    always @(posedge a) begin
        c = b && a;
        if (c && f)
            b = e;
        else
            e = b;
        
        case(c)
            1: f = 1;
            0: f = 0;
            default: f = 0;
        endcase
    end
endmodule
```

**Coverage Questions:**
- ✅ Is `a` toggled? (0→1) → **Yes**
- ❓ Is `if` true branch covered? → Depends on `c && f`
- ❓ Are all case branches covered? → Need c=1, c=0

---

## When to Use Code Coverage

| Verification Level | Code Coverage Useful? |
|-------------------|----------------------|
| **Unit Level** | ✅ Yes - verify every feature |
| **Sub-system Level** | ⚠️ Limited use |
| **System Level** | ❌ Not as useful - focus is on interactions |

> [!NOTE]
> Code coverage is a **criterion for finishing unit-level testing** since it needs to verify every feature.

---

## Functional Coverage vs Code Coverage

| Aspect | Code Coverage | Functional Coverage |
|--------|---------------|---------------------|
| What it measures | Code execution | Feature/scenario coverage |
| Who defines it | Tool (automatic) | Verification engineer |
| When useful | Unit testing | All levels |
| Coding required | None | Yes (covergroups) |

---

## Using Coverage Reports

1. **Run simulation** with coverage enabled
2. **Examine coverage report**
3. **Find low values** (uncovered areas)
4. **Understand why** code wasn't tested
5. **Write more tests** or direct randomness to cover gaps

> [!WARNING]
> Low coverage areas may be **hiding bugs**! Always investigate uncovered code.

---

## Key Takeaways

- [ ] Code coverage measures how well tests exercise the DUT
- [ ] Six types: Statement, Branch, Conditional, Toggle, FSM, Path
- [ ] Automatically collected by tools (no extra coding)
- [ ] Most useful at unit/block level verification
- [ ] Low coverage = potential bugs hiding

---

## 📖 For Deeper Study

> Refer to `Module_2_code coverage.pdf` for detailed examples and functional coverage specifics.

---

← **Previous:** [[09_Structs]] | **Next:** [[10_Practice_Problems]] →
