# 6️⃣ Arrays in SystemVerilog

> **Learning Goal:** Master packed, unpacked, and dynamic arrays

---

## Array Types Overview

| Type | Size | Memory | Use Case |
|------|------|--------|----------|
| **Packed** | Fixed | Contiguous bits | Hardware signals |
| **Unpacked** | Fixed | Separate elements | Memories, lookup tables |
| **Dynamic** | Variable | Changes at runtime | Unknown size at compile |

---

## Packed Arrays

**Definition:** All bits stored contiguously as a single vector.

```systemverilog
// Packed array: [SIZE] comes BEFORE the name
logic [7:0] packed_byte;           // 8-bit vector
logic [3:0][7:0] packed_word;      // 4 bytes = 32 bits total
```

### Characteristics
- ✅ Can be used as a whole or sliced
- ✅ Dimensions are [high:low] format
- ✅ Stored as contiguous bits in memory
- ✅ Good for hardware that operates on bit vectors

```systemverilog
logic [31:0] data;        // 32-bit packed vector
data[7:0] = 8'hFF;        // Access byte 0
data[31:24] = 8'h00;      // Access byte 3
```

---

## Unpacked Arrays

**Definition:** Elements stored separately, like traditional arrays.

```systemverilog
// Unpacked array: [SIZE] comes AFTER the name
logic data [0:7];                  // 8 elements of 1-bit each
logic [7:0] memory [0:255];        // 256 bytes (memory array)
int scores [10];                   // 10 integers
```

### Characteristics
- ✅ Each element is independent
- ✅ Can have multi-dimensional arrays
- ✅ Elements accessed individually
- ✅ Good for memories and lookup tables

```systemverilog
logic [7:0] mem [0:1023];   // 1KB memory
mem[0] = 8'hAB;             // Access location 0
mem[100] = 8'hCD;           // Access location 100
```

---

## Packed vs Unpacked Comparison

```systemverilog
// PACKED: bits together
logic [3:0][7:0] packed_data;    // 32 contiguous bits
//         ^^^^^ [3:0] means 4 groups
//              ^^^^^ [7:0] means 8 bits each

// UNPACKED: elements separate  
logic [7:0] unpacked_data [4];   // 4 separate 8-bit values
//                        ^^^ [4] means 4 elements
```

| Feature | Packed | Unpacked |
|---------|--------|----------|
| Memory layout | Contiguous | Separate |
| Bit slicing | ✅ Allowed | ❌ Not directly |
| Dimension syntax | `[a:b] name` | `name [size]` |
| Typical use | Bit vectors, fields | Memories, tables |

---

## Dynamic Arrays

**Definition:** Size determined and changeable at **runtime**.

```systemverilog
int dyn_array [];           // Empty dynamic array declaration

initial begin
    dyn_array = new[10];    // Allocate 10 elements
    dyn_array = new[20];    // Resize to 20 elements
    dyn_array = new[5](dyn_array);  // Resize keeping old data
end
```

### Key Methods

| Method | Description |
|--------|-------------|
| `new[N]` | Allocate N elements |
| `new[N](old)` | Resize and copy from old |
| `.size()` | Get current size |
| `.delete()` | Delete all elements |

---

## Dynamic Array Example

Filter elements from a 2D array:

```systemverilog
module array_filter;
    // Define a 2D array with given rows
    int arr[3][4] = '{'{14, 10, 15, 3}, '{12, 30, 16, 9}, '{7, 9, 15, 10}};
    
    // Declare a dynamic array to store filtered elements
    int filtered[];
    int i, j;
    
    initial begin
        // Display original 2D Array
        $display("Original 2D Array:");
        for (i = 0; i < 3; i++) begin
            for (j = 0; j < 4; j++) begin
                $write("%0d ", arr[i][j]);
            end
            $display;
        end
        
        // Extract elements divisible by 3 but not by 2
        foreach (arr[i, j]) begin
            if (arr[i][j] % 3 == 0 && arr[i][j] % 2 != 0) begin
                filtered = new[filtered.size+1](filtered);
                filtered = filtered, arr[i][j];  // Append to dynamic array
            end
        end
        
        // Display filtered elements
        $display("Filtered Elements (Divisible by 3 but not by 2):");
        foreach (filtered[i]) begin
            $write("%0d ", filtered[i]);
        end
        $display;
    end
endmodule
```

---

## Multi-Dimensional Arrays

```systemverilog
// 2D unpacked array
int matrix [3][4];         // 3 rows, 4 columns

// 2D packed array  
logic [3:0][7:0] packed_2d; // 4x8 = 32 bits packed

// Mixed: packed elements in unpacked array
logic [31:0] register_file [0:31];  // 32 registers, each 32 bits
```

---

## Array Initialization

```systemverilog
// Unpacked array initialization with '{...}
int arr[4] = '{1, 2, 3, 4};
int matrix[2][3] = '{'{1, 2, 3}, '{4, 5, 6}};

// Packed array initialization
logic [7:0] byte_val = 8'hAB;
logic [3:0][7:0] word = 32'hDEADBEEF;

// Default values
int zeros[10] = '{default: 0};    // All zeros
```

---

## foreach Loop

Iterate through arrays easily:

```systemverilog
int arr[5] = '{1, 2, 3, 4, 5};

// Single dimension
foreach (arr[i]) begin
    $display("arr[%0d] = %0d", i, arr[i]);
end

// Multi-dimension
int matrix[2][3];
foreach (matrix[i, j]) begin
    matrix[i][j] = i * 3 + j;
end
```

---

## Key Takeaways

- [ ] **Packed**: `[size] name` - contiguous bits for hardware
- [ ] **Unpacked**: `name [size]` - separate elements for memory
- [ ] **Dynamic**: `name []` with `new[N]` - runtime sizing
- [ ] Use `'{...}` for array initialization
- [ ] Use `foreach` for easy iteration

---

**Next:** [[08_Queues]] →
