# 5️⃣ Enumerations (enum) in SystemVerilog

> **Learning Goal:** Master enum declaration, typedef, and built-in methods

---

## What is an Enum?

An **enumeration** defines a set of named constants with integer values.

```systemverilog
enum {RED, GREEN, BLUE} color;
```

This creates a variable `color` that can only be RED (0), GREEN (1), or BLUE (2).

---

## Basic Syntax

### Simple Declaration

```systemverilog
enum {APPLE, ORANGE, BANANA} fruit;

// APPLE = 0, ORANGE = 1, BANANA = 2 (auto-numbered)
```

### With Custom Values

```systemverilog
enum {
    RED = 0,
    GREEN,        // 1 (auto-increment)
    BLUE = 4,
    YELLOW,       // 5 (continues from BLUE)
    WHITE = 10,
    BLACK         // 11
} colors;
```

---

## Using typedef

Create a **reusable type** with `typedef`:

```systemverilog
typedef enum int {
    Apple = 3,
    Orange = 6,
    Guava = 4,
    Grapes = 5,
    Mango = 2
} fruits_t;

// Now create variables of this type
fruits_t current_fruit = Grapes;
fruits_t my_fruit;
```

> [!TIP]
> Always use `typedef` for enums you'll reuse. It makes code cleaner!

---

## Enum Methods

| Method | Description | Returns |
|--------|-------------|---------|
| `.first()` | First member | Enum value |
| `.last()` | Last member | Enum value |
| `.next()` | Next member | Enum value |
| `.prev()` | Previous member | Enum value |
| `.num()` | Number of members | Integer |
| `.name()` | Name as string | String |

---

## Example: Iterating Through Enum

```systemverilog
module enum_datatype;
    enum {red = 0, green, blue = 4, yellow, white = 10, black} colors;
    int count = 0;
    int value_to_check = 4;
    string color_to_check = "yellow";
    string color_found = "No color found";
    
    initial begin
        // Display all members of Colors enum
        $display("Displaying all members of the Colors enum:");
        colors = colors.first;
        
        for (int i = 0; i < colors.num; i++) begin
            $display("Color: %0s \t Value: %0d", colors.name, colors);
            colors = colors.next;
            count++;
        end
        
        // Display total number of colors
        $display("\nTotal number of colors in the enum: %0d %0d", count, colors.num);
        
        // Retrieve color by value (e.g., 4)
        colors = colors.first;
        for (int i = 0; i < colors.num; i++) begin
            if (colors == value_to_check) begin
                color_found = colors.name;
                break;
            end
            colors = colors.next;
        end
        $display("\nThe color corresponding to value %0d is: %s", 
                 value_to_check, color_found);
        
        // Check if specific color exists
        colors = colors.first;
        for (int i = 0; i < colors.num; i++) begin
            if (colors.name == color_to_check) begin
                found = 1;
                break;
            end
            colors = colors.next;
        end
        
        if (found)
            $display("\nThe color %s exists in the Colors enum.", color_to_check);
        else
            $display("\nThe color %s does not exist in the Colors enum.", color_to_check);
    end
endmodule
```

---

## Example: Fruits Enum with typedef

```systemverilog
module fruit_enum;
    // Define enum with specific values using typedef
    typedef enum int {
        Apple = 3,
        Orange = 6,
        Guava = 4,
        Grapes = 5,
        Mango = 2
    } fruits_t;
    
    // Declare and initialize a variable
    fruits_t current_fruit = Grapes;
    
    initial begin
        // Display first and last enum values
        $display("First enum: %s = %0d", fruits_t'(0), Apple);
        $display("Last enum: %s = %0d", fruits_t'(4), Mango);
        
        // Display current value
        $display("Current value: %s = %0d", current_fruit.name(), current_fruit);
    end
endmodule
```

---

## Enum with Arrays

Creating a module that assigns values to fruits:

```systemverilog
module enum_fruits;
    typedef enum int {
        Apple = 3,
        Orange = 6,
        Guava = 4,
        Grapes = 5,
        Mango = 2
    } fruits;
    
    fruits current_fruit = Grapes;
    
    initial begin
        // Display first and last members
        $display("First fruit value (Apple): %0d", current_fruit.first());
        $display("Last fruit value (Mango): %0d", current_fruit.last());
        
        // Display the name of current value
        case (current_fruit)
            Apple:  $display("Current fruit: Apple");
            Orange: $display("Current fruit: Orange");
            Guava:  $display("Current fruit: Guava");
            Grapes: $display("Current fruit: Grapes");
            Mango:  $display("Current fruit: Mango");
        endcase
    end
endmodule
```

---

## Important Notes

> [!WARNING]
> **Enum values must be unique!** You cannot have two members with the same value.

> [!NOTE]
> The **order** in `.first()`, `.last()`, `.next()` follows **declaration order**, not numerical order!

---

## Practical Use Cases

### State Machines

```systemverilog
typedef enum logic [1:0] {
    IDLE  = 2'b00,
    READ  = 2'b01,
    WRITE = 2'b10,
    DONE  = 2'b11
} state_t;

state_t current_state, next_state;
```

### Opcodes

```systemverilog
typedef enum logic [3:0] {
    ADD = 4'h0,
    SUB = 4'h1,
    MUL = 4'h2,
    DIV = 4'h3
} opcode_t;
```

---

## Key Takeaways

- [ ] Enums create named integer constants
- [ ] Use `typedef` for reusable enum types
- [ ] `.first()`, `.last()`, `.next()`, `.prev()` for navigation
- [ ] `.name()` returns the member name as a string
- [ ] `.num()` returns total number of members

---

**Next:** [[07_Arrays]] →
