# 01 - Introduction & Data Types

## Learning Objectives

After this section you will understand:
- What SystemVerilog is and why it replaced Verilog for verification
- The difference between 2-state and 4-state data types
- When to use `logic`, `bit`, `int`, `byte`, etc.
- How signed/unsigned casting works
- Literals and string operations

---

## What is SystemVerilog?

> **Conceptual Clarity:** Think of Verilog as a language that was originally built just to *describe* hardware (like drawing a blueprint). Over time, engineers also needed to *test* their hardware designs. SystemVerilog is an upgraded version of Verilog that combines both abilities -- designing hardware AND writing test programs to verify it works correctly -- into a single language.

- SystemVerilog is a **combined hardware description language (HDL) and hardware verification language (HVL)**
- It is an extensive set of enhancements to IEEE 1364 Verilog-2001 standards
- It has features inherited from Verilog HDL, VHDL, C, and C++

### History

| Year | Milestone |
|---|---|
| 1983 | Verilog began as a proprietary language |
| 1992 | Verilog opened to the public |
| 1995 | Verilog became IEEE standard 1364 (updated 2001, 2005) |
| 2005 | SystemVerilog published as IEEE 1800 |
| 2009 | SystemVerilog officially superseded Verilog |
| 2012 | Updated to IEEE 1800-2012 standard |

### SystemVerilog - 5 Major Parts

| Abbreviation | Full Name | Purpose |
|---|---|---|
| SVD | SystemVerilog for Design | Features supporting RTL design |
| SVTB | SystemVerilog for Test Benches | Test bench specific features |
| SVA | SystemVerilog Assertions | Temporal and concurrent assertions |
| SVDPI | SV Direct Programming Interface | C/C++ integration |
| SVAPI | SV Application Programming Interface | Coverage/Assertion integration |

![[sv_features_overview.png]]

### Verilog vs SystemVerilog

| Feature | Verilog | SystemVerilog |
|---|---|---|
| Usage | Design entry, module-level verification | Full design flow |
| Data types | 4-state only (`reg`, `wire`) | 2-state + 4-state, `logic` type |
| Verification | Basic (`$random`, fork/join) | Constrained random, OOP, assertions, coverage |
| Inter-language | PLI (complex) | DPI (simple, direct value passing) |

> **Conceptual Clarity:** In Verilog you had to worry about whether to use `wire` or `reg` -- pick wrong and you get errors. SystemVerilog introduces `logic` which works in place of both, removing this headache entirely.

### Direct Programming Interface (DPI)

DPI allows SystemVerilog to call C functions and vice versa:

```verilog
// Import: SV calls C functions
import "DPI-C" function int c_add(int a, int b);

// Export: C calls SV functions
export "DPI-C" function sv_multiply;
```

---

## Integer and Logic Data Types

> **Conceptual Clarity:** Digital circuits deal with signals that can be 0 or 1. But in simulation, signals can also be *unknown* (`x`) or *floating/disconnected* (`z`). A **2-state** type only stores 0 and 1 (faster simulation, used in testbenches). A **4-state** type stores 0, 1, x, and z (used when modeling actual hardware behavior).

![[sv_data_types_overview.png]]

### 2-State Data Types (0, 1 only)

| Type | Width | Signed? | Usage |
|---|---|---|---|
| `bit` | User-defined | Unsigned | General 2-state vector |
| `byte` | 8 bits | Signed | Small values, characters |
| `shortint` | 16 bits | Signed | Medium integers |
| `int` | 32 bits | Signed | General integer |
| `longint` | 64 bits | Signed | Large integers |

### 4-State Data Types (0, 1, x, z)

| Type | Width | Signed? | Usage |
|---|---|---|---|
| `logic` | User-defined | Unsigned | **Replaces both `reg` and `wire`** |
| `reg` | User-defined | Unsigned | Legacy Verilog compatibility |
| `integer` | 32 bits | Signed | General 4-state integer |
| `time` | 64 bits | Unsigned | Simulation time values |

### Code Examples

```verilog
// 2-state types
bit            flag;          // 1-bit, value is 0 or 1
bit [7:0]      data;          // 8-bit unsigned
byte           b;             // 8-bit signed (-128 to 127)
int            count;         // 32-bit signed
shortint       s;             // 16-bit signed
longint        l;             // 64-bit signed

// 4-state types
logic          clk;           // 1-bit, can be 0, 1, x, z
logic [15:0]   addr;          // 16-bit address
reg [7:0]      old_style;     // same as logic, legacy
integer        i;             // 32-bit signed, 4-state
time           sim_time;      // 64-bit unsigned
```

> **Conceptual Clarity:** Use `logic` for RTL design (where you need x/z for debugging). Use `bit`/`int` in testbenches where you only care about 0 and 1 and want faster simulation.

---

## Signed and Unsigned

- `byte`, `shortint`, `int`, `integer`, `longint` default to **signed**
- `bit`, `reg`, `logic` default to **unsigned**

```verilog
int unsigned ui;              // Force int to unsigned
int signed si;                // Explicitly signed (default anyway)
byte unsigned ubyte;          // Force byte to unsigned

logic [1:0] L;                // 2-bit unsigned vector

// Signed/unsigned casting
if (signed'(ubyte) < 150)     // Cast unsigned byte to signed for comparison
    $display("Less than 150");
```

> **Conceptual Clarity:** Signed means the type can represent negative numbers (using two's complement). An 8-bit signed byte holds -128 to +127. An 8-bit unsigned byte holds 0 to 255. The `'` operator after a type name performs a cast.

---

## Void Data Type

- Represents nonexistent data
- Used as return type of functions that return nothing

```verilog
function void print_msg(string msg);
    $display("%s", msg);
endfunction

// Call without expecting a return value
void'(some_function_call());
```

---

## Literals

### Integer and Logic Literals

```verilog
// Verilog way: must specify width explicitly
reg [31:0] a = 32'hffffffff;

// SystemVerilog way: unsized literals fill all bits
reg [31:0] b = '1;         // All bits set to 1 (= 32'hFFFFFFFF)
reg [31:0] c = '0;         // All bits set to 0
reg [31:0] d = 'x;         // All bits unknown
reg [31:0] e = 'z;         // All bits high-impedance
```

> **Conceptual Clarity:** The `'1` literal is a shortcut that means "set every single bit to 1, no matter how wide the variable is." This is much more convenient than counting bits manually.

### Time Literals

```verilog
// Time values with units (no space between number and unit)
#0.1ns;     // 0.1 nanoseconds
#40ps;      // 40 picoseconds
#1us;       // 1 microsecond
// Units: fs, ps, ns, us, ms, s
```

### Array Literals

```verilog
int n[1:2][1:3] = '{'{0,1,2}, '{3{4}}};
// n[1] = {0,1,2}, n[2] = {4,4,4}

int m[1:2][1:6] = '{2{'{3{4, 5}}}};
// Same as '{'{4,5,4,5,4,5}, '{4,5,4,5,4,5}}
```

---

## Strings

> **Conceptual Clarity:** In Verilog, strings were just packed arrays of 8-bit ASCII characters -- clunky and easy to mess up (strings could get truncated). SystemVerilog adds a proper `string` type that automatically resizes, similar to strings in Python or Java.

### Declaration

```verilog
string myName = "TEST BENCH";    // No fixed size needed
string empty  = "";              // Empty string
```

### String Methods

| Method | Description | Example |
|---|---|---|
| `str.len()` | Returns length | `"hello".len()` returns 5 |
| `str.putc(i, c)` | Replace char at index i | `str.putc(0, "H")` |
| `str.getc(i)` | Get ASCII of char at i | `str.getc(0)` returns 72 for "H" |
| `str.toupper()` | Convert to uppercase | `"hello".toupper()` = `"HELLO"` |
| `str.tolower()` | Convert to lowercase | `"HELLO".tolower()` = `"hello"` |
| `str.compare(s)` | Case-sensitive compare | Returns 0 if equal |
| `str.icompare(s)` | Case-insensitive compare | Returns 0 if equal |
| `str.substr(i,j)` | Substring from i to j | `"hello".substr(1,3)` = `"ell"` |
| `str.atoi()` | String to integer | `"123".atoi()` = 123 |
| `str.atoreal()` | String to real | `"3.14".atoreal()` = 3.14 |
| `str.itoa(i)` | Integer to string | stores decimal repr |
| `str.hextoa(i)` | Int to hex string | stores hex repr |
| `str.bintoa(i)` | Int to binary string | stores binary repr |
| `str.realtoa(r)` | Real to string | stores real repr |

### String Methods Example

```verilog
module str;
    string A;
    string B;
    initial begin
        A = "TEST ";
        B = "Bench";
        $display(" %d ", A.len());           // Output: 5
        $display(" %s ", A.getc(5));         // Output: (space char)
        $display(" %s ", A.tolower);         // Output: test
        $display(" %s ", B.toupper);         // Output: BENCH
        $display(" %d ", B.compare(A));      // Output: -18
        $display(" %d ", A.compare("test")); // Output: -32
        $display(" %s ", A.substr(2,3));     // Output: ST
        A = "111";
        $display(" %d ", A.atoi());          // Output: 111
    end
endmodule
```

### String Operators

| Operator | Syntax | Description |
|---|---|---|
| Equality | `Str1 == Str2` | True if identical |
| Inequality | `Str1 != Str2` | True if different |
| Comparison | `Str1 < Str2` | Lexicographic compare |
| Concatenation | `{Str1, Str2}` | Join strings |
| Replication | `{3{Str1}}` | Repeat string 3 times |
| Indexing | `Str[i]` | Access character at index i |

### String Operators Examples

```verilog
program main;
    initial begin
        string str1, str2, str3;
        str1 = "TEST BENCH";
        str2 = "TEST BENCH";
        str3 = "test bench";

        // Equality
        if (str1 == str2)
            $display("str1 and str2 are equal");       // This prints
        if (str1 == str3)
            $display("str1 and str3 are equal");       // Does NOT print (case sensitive)
    end
endprogram
```

```verilog
program main;
    initial begin
        string Str1, Str2, Str3;
        Str1 = "c";
        Str2 = "d";
        Str3 = "e";

        if (Str1 < Str2)  $display("Str1 < Str2");    // Prints
        if (Str1 <= Str2) $display("Str1 <= Str2");    // Prints
        if (Str3 > Str2)  $display("Str3 > Str2");     // Prints
        if (Str3 >= Str2) $display("Str3 >= Str2");     // Prints
    end
endprogram
```

```verilog
program main;
    initial begin
        string Str1, Str2, Str3, Str4, Str5;
        Str1 = "WWW.";
        Str2 = "VITAP";
        Str3 = "";
        Str4 = ".AC";
        Str5 = ".IN";
        $display(" %s ", {Str1, Str2, Str3, Str4, Str5});
        // Output: WWW.VITAP.AC.IN
    end
endprogram
```

```verilog
program main;
    initial begin
        string Str1;
        Str1 = "WWW.TESTBENCH.IN";
        for (int i = 0; i < 16; i++)
            $write("%s ", Str1[i]);
        // Output: W W W . T E S T B E N C H . I N
    end
endprogram
```

---

## Common Mistakes

1. **Using `reg` in new SV code** - Use `logic` instead. `reg` is legacy.
2. **Forgetting signed/unsigned defaults** - `int` is signed, `bit` is unsigned. Mixing them in comparisons causes bugs.
3. **Not using `'1` and `'0`** - Hardcoding widths like `32'hFFFFFFFF` is error-prone when widths change.
4. **String truncation in Verilog style** - Always use `string` type, never packed byte arrays for text manipulation.

---

## Self-Check Questions

**Q1:** What is the difference between `bit` and `logic`?
> `bit` is 2-state (0, 1 only). `logic` is 4-state (0, 1, x, z). Use `bit` in testbenches, `logic` in RTL.

**Q2:** What does `reg [31:0] a = '1;` do?
> Sets all 32 bits of `a` to 1 (i.e., `a = 32'hFFFFFFFF`).

**Q3:** Is `int` signed or unsigned by default?
> Signed. Use `int unsigned` to make it unsigned.

**Q4:** What does `str.compare(s)` return when strings are equal?
> Returns 0.

**Q5:** What is the difference between `{Str1, Str2}` and `{3{Str1}}`?
> The first concatenates two strings. The second replicates Str1 three times.

---

## Concept Links

- Next: [02 - User-Defined Types & Arrays](./02_User_Defined_Types_and_Arrays.md)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#data-types)


