# 02 - User-Defined Types & Arrays

## Learning Objectives

After this section you will understand:
- How to create custom types with `enum`, `struct`, `union`, and `typedef`
- The difference between packed and unpacked arrays
- How dynamic arrays, associative arrays, and queues work
- Array manipulation methods (search, sort, reduce)

---

## User-Defined Data Types

SystemVerilog allows creating custom data types using five mechanisms:
1. Class (covered in [05 - Classes](./05_Classes_and_OOP.md))
2. Enumerations
3. Structures
4. Unions
5. Typedef

---

## Enumerations

> **Conceptual Clarity:** An enumeration (enum) is like giving names to numbers. Instead of remembering that state 0 means "idle," state 1 means "ready," and state 2 means "busy," you just use the names directly. This makes code much more readable and less error-prone.

### Basic Declaration

```verilog
enum {IDLE, READY, BUSY} states;
// IDLE = 0, READY = 1, BUSY = 2 (auto-assigned)
```

### Typed Enum with Explicit Values

```verilog
// Using 4-state type to allow X values
enum integer {IDLE, XX='x, S1='b01, S2='b10} state, next;
```

### Enum with Typedef (Recommended)

```verilog
typedef enum {red, blue, green} colour;
colour c;  // c can only be red, blue, or green
```

### Enum Methods

| Method | Description |
|---|---|
| `first()` | Returns the first member value |
| `last()` | Returns the last member value |
| `next(N)` | Returns the Nth next value (default N=1) |
| `prev(N)` | Returns the Nth previous value (default N=1) |
| `name()` | Returns the string name of the current value |

### Enum Methods Example

```verilog
module enum_method;
    typedef enum {red, blue, green} colour;
    colour c;
    initial begin
        c = c.first();
        $display(" %s ", c.name);    // Output: red

        c = c.next();
        $display(" %s ", c.name);    // Output: blue

        c = c.last();
        $display(" %s ", c.name);    // Output: green

        c = c.prev();
        $display(" %s ", c.name);    // Output: blue
    end
endmodule
```

### Enum Casting

```verilog
module enum_method;
    typedef enum {red, blue, green} colour;
    colour c, d;
    int i;
    initial begin
        $display("%s", c.name());          // Output: red (default = first)
        d = c;
        $display("%s", d.name());          // Output: red
        d = colour'(c + 1);               // Cast required for arithmetic
        $display("%s", d.name());          // Output: blue
        i = d;                             // Automatic cast to int
        $display("%0d", i);                // Output: 1
        c = colour'(i);                    // Cast back from int
        $display("%s", c.name());          // Output: blue
    end
endmodule
```

> **Conceptual Clarity:** You cannot directly do `c = c + 1` on an enum because adding 1 to an enum produces an integer, not an enum. You must cast it back: `c = colour'(c + 1)`.

---

## Structures

> **Conceptual Clarity:** An array forces all elements to be the same type. A structure lets you bundle different types together -- like a form with fields for name (string), age (int), and active (bit).

### Declaration

```verilog
struct {
    int       a;
    byte      b;
    bit [7:0] c;
} my_data_struct;
```

### Accessing Members

```verilog
my_data_struct.a = 123;
$display(" a value is %d ", my_data_struct.a);    // Output: 123
```

### Structure Literals (Assignment)

```verilog
// Positional assignment
my_data_struct = '{1234, 8'b10, 8'h20};

// Named assignment with default
my_data_struct = '{a:1234, default:8'h20};
// a = 1234, b and c both get 8'h20
```

---

## Unions

> **Conceptual Clarity:** While a struct gives each member its own storage space, a union makes all members **share the same memory location**. Only one member holds a valid value at any time. Think of it as one parking spot that can hold a car OR a motorcycle -- but not both at the same time.

```verilog
union {
    int       a;
    byte      b;
    bit [7:0] c;
} my_data;

// Writing to 'a' overwrites 'b' and 'c' because they share memory
my_data.a = 32'hDEADBEEF;
// my_data.b now reads the lowest byte of that value
```

---

## Typedef

> **Conceptual Clarity:** `typedef` is like creating an alias or nickname for a data type. Instead of writing `enum {NO, YES}` everywhere, you define it once as `boolean` and use that name everywhere.

### Advantages
- Shorter names reduce typing errors
- Improves readability
- Changing a type in one place propagates everywhere
- Increases reusability

```verilog
typedef enum {NO, YES} boolean;
boolean flag;

typedef union { int i; shortreal f; } num;    // Named union type

typedef struct {
    bit isfloat;
    union { int i; shortreal f; } n;          // Anonymous union inside
} tagged_st;                                   // Named structure type
```

---

## Fixed Arrays (Packed vs Unpacked)

> **Conceptual Clarity:** Think of a **packed** array as a single continuous block of bits that you can treat as one number. Think of an **unpacked** array as a collection of individual boxes (like an array of variables). Packed dimensions are declared BEFORE the variable name, unpacked dimensions are declared AFTER.

![[packed_unpacked_arrays.png]]

### Declaration Syntax

```verilog
// ---- PACKED ARRAYS (dimensions BEFORE name) ----
reg [0:10] vari;                    // 11-bit packed array
wire [31:0][1:0] vari;              // 2D packed array

// ---- UNPACKED ARRAYS (dimensions AFTER name) ----
wire status [31:0];                 // 1D unpacked, 32 elements
wire status [32];                   // Same thing, different syntax
integer matrix [7:0][0:31][15:0];   // 3D unpacked array
integer matrix [8][32][16];         // Same thing

// ---- MIXED ----
reg [31:0] registers [0:255];       // 256 registers, each 32-bit packed
// [31:0] is packed, [0:255] is unpacked
```

### Size Equivalence

```verilog
int Array[8][32];       // Same as:
int Array[0:7][0:31];   // Both create 8x32 unpacked array
```

### Accessing Elements

```verilog
bit [3:4][5:6] Array [0:2];

Array[2]          // Accesses 4 elements: [2][3][5], [2][3][6], [2][4][5], [2][4][6]
Array[1][3]       // Accesses 2 elements: [1][3][5] and [1][3][6]
Array[0][3][6]    // Accesses 1 element
```

---

## Dynamic Arrays

> **Conceptual Clarity:** A fixed array has its size decided at compile time and cannot change. A dynamic array starts with NO size -- you allocate memory for it at runtime using `new()`. This is useful when you don't know in advance how many elements you need.

### Declaration and Usage

```verilog
int dyn_arr [];                   // Empty square brackets = dynamic array

initial begin
    dyn_arr = new[4];             // Allocate 4 elements
    foreach (dyn_arr[i])
        dyn_arr[i] = i * 2;      // Initialize: 0, 2, 4, 6

    $display("Size: %0d", dyn_arr.size());   // Output: 4
end
```

### Dynamic Array Methods

![[dynamic_array_methods.png]]

| Method | Description |
|---|---|
| `new[N]` | Allocate N elements |
| `new[N](old)` | Allocate N elements, copy old values |
| `size()` | Returns current number of elements |
| `delete()` | Removes all elements, size becomes 0 |

### Adding New Items Without Losing Old Data

```verilog
int dyn [];
initial begin
    dyn = new[4];                  // Size = 4
    foreach (dyn[i]) dyn[i] = i;   // {0, 1, 2, 3}

    // Resize to 8, preserving original 4 values
    dyn = new[8](dyn);             // {0, 1, 2, 3, 0, 0, 0, 0}
    $display("New size: %0d", dyn.size());   // Output: 8
end
```

---

## Array Manipulation Methods

> **Conceptual Clarity:** SystemVerilog has built-in methods to search through, sort, and reduce arrays -- similar to Python's `filter()`, `sorted()`, and `sum()`. These methods use a `with` clause to specify the condition or expression.

### Array Locator Methods (Mandatory `with` clause)

These return a queue of matching elements or indices.

| Method | Description |
|---|---|
| `find() with (expr)` | Returns all elements matching expr |
| `find_index() with (expr)` | Returns indices of matching elements |
| `find_first() with (expr)` | Returns first matching element |
| `find_first_index() with (expr)` | Returns index of first match |
| `find_last() with (expr)` | Returns last matching element |
| `find_last_index() with (expr)` | Returns index of last match |

```verilog
int arr[] = '{2, 7, 3, 9, 1, 8};
int result[$];

result = arr.find with (item > 5);              // result = {7, 9, 8}
result = arr.find_index with (item > 5);         // result = {1, 3, 5}
result = arr.find_first with (item > 5);         // result = {7}
result = arr.find_first_index with (item > 5);   // result = {1}
```

### Array Ordering Methods (Optional `with` clause)

| Method | Description |
|---|---|
| `sort()` | Sort in ascending order |
| `rsort()` | Sort in descending order |
| `reverse()` | Reverse the order |
| `shuffle()` | Randomize the order |

```verilog
int arr[] = '{3, 1, 4, 1, 5};
arr.sort();      // {1, 1, 3, 4, 5}
arr.rsort();     // {5, 4, 3, 1, 1}
arr.reverse();   // {1, 1, 3, 4, 5}
arr.shuffle();   // random order
```

### Array Reduction Methods

| Method | Description |
|---|---|
| `sum()` | Sum of all elements |
| `product()` | Product of all elements |
| `and()` | Bitwise AND of all elements |
| `or()` | Bitwise OR of all elements |
| `xor()` | Bitwise XOR of all elements |

```verilog
int arr[] = '{1, 2, 3, 4};
int s;
s = arr.sum();        // s = 10
s = arr.product();    // s = 24
```

---

## Queues

> **Conceptual Clarity:** A queue is like a dynamic array that can efficiently grow and shrink from both ends (like a line at a store -- people join at the back and leave from the front). It is declared with `[$]` instead of `[]`.

### Declaration and Syntax

```verilog
int q [$];                            // Unbounded queue (any size)
int q_bounded [$:99];                 // Bounded queue (max 100 elements)
```

### Queue Operations

```verilog
int q[$] = {1, 2, 3, 4, 5};

q.push_front(0);      // q = {0, 1, 2, 3, 4, 5}
q.push_back(6);       // q = {0, 1, 2, 3, 4, 5, 6}

int a = q.pop_front(); // a = 0, q = {1, 2, 3, 4, 5, 6}
int b = q.pop_back();  // b = 6, q = {1, 2, 3, 4, 5}

q.insert(2, 99);       // Insert 99 at index 2: q = {1, 2, 99, 3, 4, 5}
q.delete(2);           // Delete index 2: q = {1, 2, 3, 4, 5}

$display("Size: %0d", q.size());   // Output: 5
```

### Queue Slicing

```verilog
int q[$] = {0, 1, 2, 3, 4, 5};

int sub[$] = q[1:3];    // sub = {1, 2, 3}
int sub2[$] = q[2:$];   // sub2 = {2, 3, 4, 5}
```

---

## Common Mistakes

1. **Forgetting `typedef` with enums** - Without typedef, you cannot reuse the enum as a type for multiple variables.
2. **Packed vs unpacked confusion** - Remember: dimensions BEFORE name = packed; AFTER name = unpacked.
3. **Dynamic array without `new()`** - Accessing a dynamic array before calling `new()` is a runtime error.
4. **Queue `push_front` vs `push_back`** - Confusing these reverses your expected order.

---

## Self-Check Questions

**Q1:** What does `enum {A, B, C} x;` default A, B, C to?
> A=0, B=1, C=2 (auto-incrementing from 0)

**Q2:** What is the difference between a struct and a union?
> A struct gives each member its own memory. A union makes all members share the same memory -- only one is valid at a time.

**Q3:** In `reg [31:0] mem [0:255]`, which part is packed and which is unpacked?
> `[31:0]` (before name) is packed. `[0:255]` (after name) is unpacked.

**Q4:** How do you resize a dynamic array while keeping old values?
> `dyn = new[new_size](dyn);`

**Q5:** What method returns all elements greater than 5 from an array?
> `arr.find with (item > 5);`

---

## Concept Links

- Previous: [01 - Introduction & Data Types](./01_Data_Types_Deep_Dive.md)
- Next: [03 - Control Flow & Loops](./03_Control_Flow_and_Loops.md)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#arrays)




