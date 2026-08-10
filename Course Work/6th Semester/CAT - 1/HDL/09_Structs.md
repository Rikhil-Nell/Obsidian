# 8️⃣ Structures in SystemVerilog

> **Learning Goal:** Master packed and unpacked struct declarations

---

## What is a Struct?

A **struct** groups related variables into a single unit.

```systemverilog
struct {
    logic [7:0] red;
    logic [7:0] green;
    logic [7:0] blue;
} pixel;
```

---

## Basic Struct Declaration

```systemverilog
// Declare and use immediately
struct {
    int x;
    int y;
    string name;
} point;

// Access members with dot notation
point.x = 10;
point.y = 20;
point.name = "Origin";
```

---

## typedef for Reusable Structs

Create a **named type** you can reuse:

```systemverilog
typedef struct {
    logic [7:0] opcode;
    logic [15:0] address;
    logic [31:0] data;
} instruction_t;

// Now create variables
instruction_t instr1, instr2;
instruction_t instructions[100];  // Array of structs

instr1.opcode = 8'hAB;
instr1.address = 16'h1234;
instr1.data = 32'hDEADBEEF;
```

---

## Packed vs Unpacked Structs

### Packed Struct

All members stored as **contiguous bits**:

```systemverilog
typedef struct packed {
    logic [7:0] header;   // Bits 31:24
    logic [7:0] length;   // Bits 23:16
    logic [15:0] payload; // Bits 15:0
} packet_t;              // Total: 32 bits, contiguous

packet_t pkt;
pkt = 32'hABCD1234;      // Can assign as single value!
```

### Unpacked Struct

Members stored **separately**:

```systemverilog
typedef struct {
    int count;
    string name;
    real temperature;
} sensor_t;              // Members stored independently

sensor_t sensor;
sensor.count = 42;
sensor.name = "Temp1";
sensor.temperature = 25.5;
```

---

## Packed vs Unpacked Comparison

| Feature | Packed | Unpacked |
|---------|--------|----------|
| Memory | Contiguous bits | Separate storage |
| Keyword | `struct packed` | `struct` |
| Assign as vector | ✅ Yes | ❌ No |
| Bit slicing | ✅ Yes | ❌ No |
| String/real members | ❌ No (only fixed-size) | ✅ Yes |
| Use case | Hardware registers | Testbench data |

---

## Packed Struct with Fixed-Size Array

```systemverilog
typedef struct packed {
    logic [3:0] count;
    logic [7:0] data [4];  // Fixed array inside struct
} data_packet_t;

data_packet_t pkt;
pkt.count = 4'd3;
pkt.data[0] = 8'hAA;
pkt.data[1] = 8'hBB;
```

---

## Unpacked Struct with Arrays (Testbench Style)

```systemverilog
typedef struct {
    string test_name;
    int expected_result;
    int input_values[10];
    bit passed;
} test_case_t;

test_case_t tests[100];  // Array of test cases

tests[0].test_name = "Addition Test";
tests[0].expected_result = 42;
tests[0].input_values = '{10, 32, 0, 0, 0, 0, 0, 0, 0, 0};
tests[0].passed = 1;
```

---

## Struct Initialization

```systemverilog
// Named member assignment
instruction_t instr = '{
    opcode: 8'h55,
    address: 16'h1000,
    data: 32'h0
};

// Positional assignment
instruction_t instr2 = '{8'hAA, 16'h2000, 32'hFFFF};

// Default values
typedef struct {
    int a;
    int b;
    int c;
} triple_t;

triple_t t = '{default: 0};  // All members = 0
```

---

## Struct with Enum

Combining structs and enums:

```systemverilog
typedef enum logic [1:0] {
    IDLE  = 2'b00,
    READ  = 2'b01,
    WRITE = 2'b10,
    DONE  = 2'b11
} state_t;

typedef struct packed {
    state_t current_state;
    logic [7:0] data;
    logic valid;
} transaction_t;

transaction_t txn;
txn.current_state = WRITE;
txn.data = 8'hAB;
txn.valid = 1'b1;
```

---

## Nested Structs

Structs can contain other structs:

```systemverilog
typedef struct {
    int x;
    int y;
} point_t;

typedef struct {
    point_t top_left;
    point_t bottom_right;
    string color;
} rectangle_t;

rectangle_t rect;
rect.top_left.x = 0;
rect.top_left.y = 100;
rect.bottom_right.x = 200;
rect.bottom_right.y = 0;
rect.color = "blue";
```

---

## Practical Example: Register Definition

```systemverilog
typedef struct packed {
    logic [7:0]  reserved;    // Bits 31:24
    logic        enable;      // Bit 23
    logic [2:0]  mode;        // Bits 22:20
    logic [3:0]  channel;     // Bits 19:16
    logic [15:0] threshold;   // Bits 15:0
} config_reg_t;              // Total: 32 bits

config_reg_t cfg;

// Can access as fields
cfg.enable = 1'b1;
cfg.mode = 3'b101;
cfg.threshold = 16'd1000;

// Or as single 32-bit value
logic [31:0] reg_value = cfg;
```

---

## Key Takeaways

- [ ] Structs group related variables together
- [ ] Use `typedef` for reusable struct types
- [ ] **Packed**: contiguous bits, for hardware
- [ ] **Unpacked**: separate storage, for testbenches
- [ ] Access members with `.` (dot notation)
- [ ] Can nest structs and combine with enums

---

← **Previous:** [[08_Queues]] | **Next:** [[02_Code_Coverage]] →
