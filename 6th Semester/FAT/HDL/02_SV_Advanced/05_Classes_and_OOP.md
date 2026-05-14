# 05 - Classes & OOP

## Learning Objectives

After this section you will understand:
- Class declaration, object creation, and handles
- Constructors (`new`), the `this` keyword, and class assignment
- Inheritance, polymorphism, and method overriding
- `super` keyword and scope resolution operator `::`
- Static and dynamic casting (`$cast`)
- Data hiding (`local`, `protected`)
- Abstract classes and virtual methods
- `typedef class` for forward declarations

---

## What is a Class?

> **Conceptual Clarity:** A class is a blueprint. Imagine you design a blueprint for a house -- it describes what rooms the house has (properties) and what you can do in it (methods). The actual house you build from that blueprint is called an **object**. You can build many houses from the same blueprint. In SystemVerilog, classes are used to model complex testbench components like drivers, monitors, and scoreboards.

- A class contains **properties** (data/variables) and **methods** (tasks/functions)
- Objects are created dynamically at runtime
- Objects are accessed through **handles** (like pointers in C)

---

## Class Declaration

```verilog
class sv_class;
    // Properties
    bit [7:0] addr;
    bit [7:0] data;
    bit       wr;

    // Method
    function void display();
        $display("addr = %0h, data = %0h, wr = %0b", addr, data, wr);
    endfunction
endclass
```

### Object Creation and Handle

```verilog
module tb;
    initial begin
        sv_class class_1;          // Declare a handle (like a pointer)
        // class_1 is currently NULL -- no object exists yet

        class_1 = new();           // Create object, assign to handle
        // Now class_1 points to an actual object in memory

        // Access properties and methods using dot notation
        class_1.addr = 8'hAB;
        class_1.data = 8'hCD;
        class_1.wr   = 1;
        class_1.display();         // Output: addr = ab, data = cd, wr = 1
    end
endmodule
```

> **Conceptual Clarity:** A handle is NOT the object itself -- it is an address that tells you WHERE the object is in memory. Declaring `sv_class c;` creates the handle but NOT the object. You must call `new()` to actually create the object.

---

## The `this` Keyword

`this` refers to the current object instance. Use it when a method parameter has the same name as a class property.

```verilog
class packet;
    int addr;
    int data;

    function new(int addr, int data);
        this.addr = addr;    // this.addr = class property, addr = argument
        this.data = data;
    endfunction
endclass
```

> **Conceptual Clarity:** Without `this`, when you write `addr = addr;` inside the constructor, the compiler doesn't know if you mean the class property or the function argument. `this.addr` explicitly says "the property of THIS object."

---

## Constructors (`new`)

The `new` method is the class **constructor** -- it runs when you create an object.

```verilog
class packet;
    int addr;
    int data;

    // Constructor -- called automatically on new()
    function new();
        addr = 0;
        data = 0;
        $display("Packet created");
    endfunction
endclass
```

### Rules for Constructors

- Defined as `function new()` (no return type, not even `void`)
- Every class has a built-in `new` even if you don't define one
- Specifying any return type gives a compilation error
- Can accept arguments for runtime customization

### Constructor with Arguments

```verilog
class packet;
    int addr;
    int data;

    function new(int a = 0, int d = 0);
        addr = a;
        data = d;
    endfunction
endclass

module tb;
    initial begin
        packet p1 = new();          // addr=0, data=0 (defaults)
        packet p2 = new(8'hFF, 8'h42);  // addr=FF, data=42
    end
endmodule
```

---

## Class Assignment (Handle Copying)

> **Conceptual Clarity:** When you assign one class handle to another, you are NOT copying the object. You are making both handles point to the SAME object. Like two TV remotes controlling the same TV -- pressing a button on either remote affects the same TV.

```verilog
class packet;
    int addr;
    function new(int a);
        addr = a;
    endfunction
endclass

module tb;
    initial begin
        packet pkt_1 = new(10);    // Create object, addr = 10
        packet pkt_2;

        pkt_2 = pkt_1;             // pkt_2 now points to SAME object as pkt_1
        // NO new object created! Both handles -> same object

        pkt_2.addr = 99;           // Modify through pkt_2
        $display("pkt_1.addr = %0d", pkt_1.addr);
        // Output: pkt_1.addr = 99  <-- pkt_1 also sees the change!
    end
endmodule
```

---

## Inheritance

> **Conceptual Clarity:** Inheritance means creating a new class based on an existing one. The new class (child) automatically gets all properties and methods of the original (parent), plus can add its own or override the parent's. Like how a sports car inherits all features of a basic car (engine, wheels, steering) and adds turbo boost.

```verilog
// Parent class (base class)
class parent_class;
    int addr;

    function void display();
        $display("addr = %0d", addr);
    endfunction
endclass

// Child class (extended class)
class child_class extends parent_class;
    int data;    // New property

    function void display_all();
        $display("addr = %0d, data = %0d", addr, data);
        // 'addr' is inherited from parent -- accessible directly
    endfunction
endclass
```

```verilog
module tb;
    initial begin
        child_class c = new();
        c.addr = 10;       // Inherited from parent
        c.data = 20;       // Defined in child
        c.display();       // Inherited method: addr = 10
        c.display_all();   // Child method: addr = 10, data = 20
    end
endmodule
```

### Inheritance Terminology

| Term | Synonyms | Description |
|---|---|---|
| Parent class | Base class, superclass | The original class being extended |
| Child class | Extended class, derived class, subclass | The new class that inherits |
| Multilevel inheritance | -- | Child extends a class that is itself a child |

![[inheritance_diagram.png]]

---

## Method Overriding

A child class can **redefine** a method from the parent class with the same name.

```verilog
class parent;
    function void display();
        $display("Parent display");
    endfunction
endclass

class child extends parent;
    function void display();          // Same name = override
        $display("Child display");
    endfunction
endclass

module tb;
    initial begin
        child c = new();
        c.display();    // Output: "Child display" (overridden)
    end
endmodule
```

---

## The `super` Keyword

`super` accesses the **parent class** members from within a child class. Only goes one level up.

```verilog
class parent;
    int x = 10;
    function void display();
        $display("Parent: x = %0d", x);
    endfunction
endclass

class child extends parent;
    int x = 20;    // Overrides parent's x

    function void display();
        super.display();              // Call parent's display
        $display("Child: x = %0d", x);
    endfunction

    function void show_both();
        $display("Parent x = %0d", super.x);   // Access parent's x
        $display("Child x = %0d", this.x);      // Access child's x
    endfunction
endclass
```

---

## Polymorphism

> **Conceptual Clarity:** Polymorphism means "many forms." The same method call can behave differently depending on which object type the handle actually points to. A base class handle can point to any of its child class objects, and the correct child's method gets called. This only works when the method is declared `virtual`.

```verilog
class base_class;
    virtual function void display();
        $display("Base class");
    endfunction
endclass

class child_A extends base_class;
    function void display();
        $display("Child A");
    endfunction
endclass

class child_B extends base_class;
    function void display();
        $display("Child B");
    endfunction
endclass

class child_C extends base_class;
    function void display();
        $display("Child C");
    endfunction
endclass
```

```verilog
module tb;
    initial begin
        base_class handle;

        child_A a = new();
        child_B b = new();
        child_C c = new();

        handle = a;
        handle.display();    // Output: "Child A"

        handle = b;
        handle.display();    // Output: "Child B"

        handle = c;
        handle.display();    // Output: "Child C"

        // Same handle, same method call, different behavior = polymorphism
    end
endmodule
```

> **Conceptual Clarity:** The key is the `virtual` keyword on the base class method. Without it, `handle.display()` would always call the BASE class version, even if handle points to a child object.

---

## Casting

### Static Casting

- Converts one data type to another **compatible** type
- Checked at **compile time** (no runtime overhead)
- NOT applicable to OOP (class handles)
- Uses the `'` operator

```verilog
int i;
real r = 3.14;
i = int'(r);           // Cast real to int: i = 3

typedef enum {RED, GREEN, BLUE} color_t;
color_t c;
c = color_t'(1);       // Cast int to enum: c = GREEN
```

### Dynamic Casting (`$cast`)

- Used for class hierarchy casting (OOP)
- Checked at **runtime** -- reports error if incompatible
- Essential when assigning a parent handle to a child handle

```verilog
// Assigning child -> parent: ALWAYS allowed
parent_class p;
child_class c = new();
p = c;                 // OK -- parent handle can point to child object

// Assigning parent -> child: NOT directly allowed
child_class c2;
c2 = p;                // COMPILATION ERROR! Types are incompatible

// Use $cast instead
$cast(c2, p);          // OK at runtime IF p actually points to a child_class
```

> **Conceptual Clarity:** Why is parent-to-child assignment dangerous? Because a parent handle might point to a pure parent object that doesn't have the child's extra properties. If you then try to access `c2.child_property`, it would crash. `$cast` checks at runtime whether the actual object is compatible.

### Full $cast Example

```verilog
class parent;
    int addr = 10;
endclass

class child extends parent;
    int data = 20;
endclass

module tb;
    initial begin
        parent p;
        child  c1 = new();
        child  c2;

        p = c1;               // OK: parent <- child (always safe)

        // p now actually points to a child object
        // We can safely cast it back
        if ($cast(c2, p))
            $display("Cast succeeded: data = %0d", c2.data);
        else
            $display("Cast failed");
        // Output: Cast succeeded: data = 20
    end
endmodule
```

---

## Data Hiding and Encapsulation

> **Conceptual Clarity:** Encapsulation means hiding a class's internal data and only letting the outside world interact through methods. Like a vending machine -- you don't reach inside to grab a drink, you press a button (method) and the machine gives you one. This prevents accidental corruption of data.

### `local` Access

Members declared `local` are **only accessible within the class itself**. Not even child classes can access them.

```verilog
class secret;
    local integer x;          // Only accessible inside 'secret'

    function void set_x(int val);
        x = val;              // Allowed: inside the class
    endfunction

    function int get_x();
        return x;             // Allowed: inside the class
    endfunction
endclass

module tb;
    initial begin
        secret s = new();
        s.set_x(42);          // OK: using public method
        // s.x = 10;          // COMPILATION ERROR: x is local
        $display("x = %0d", s.get_x());
    end
endmodule
```

### `protected` Access

Members declared `protected` are accessible **within the class AND its child classes**, but not from outside.

```verilog
class parent;
    protected integer x;

    function void set_x(int val);
        x = val;
    endfunction
endclass

class child extends parent;
    function void modify_x();
        x = x + 1;            // OK: child can access protected member
    endfunction
endclass

module tb;
    initial begin
        child c = new();
        c.set_x(10);
        c.modify_x();
        // c.x = 5;           // COMPILATION ERROR: x is protected
    end
endmodule
```

### Comparison

| Modifier | Inside class | In child class | Outside class |
|---|---|---|---|
| (default) | Yes | Yes | Yes |
| `protected` | Yes | Yes | **No** |
| `local` | Yes | **No** | **No** |

---

## Abstract Classes (`virtual class`)

> **Conceptual Clarity:** An abstract class is a blueprint that is intentionally **incomplete**. It defines WHAT methods should exist, but not necessarily HOW they work. You cannot build a house directly from an abstract blueprint -- you must first create a concrete blueprint that fills in the details. In SV, abstract classes are declared with `virtual class`.

```verilog
virtual class shape;
    pure virtual function real area();       // No implementation -- child MUST define this
    pure virtual function void display();
endclass

class circle extends shape;
    real radius;

    function new(real r);
        radius = r;
    endfunction

    function real area();
        return 3.14159 * radius * radius;
    endfunction

    function void display();
        $display("Circle: radius=%0.2f, area=%0.2f", radius, area());
    endfunction
endclass
```

```verilog
module tb;
    initial begin
        // shape s = new();     // COMPILATION ERROR: cannot instantiate abstract class
        circle c = new(5.0);
        c.display();            // Output: Circle: radius=5.00, area=78.54
    end
endmodule
```

---

## Virtual Methods

> **Conceptual Clarity:** A `virtual` method is one where the version that gets called depends on the **actual object type**, not the handle type. Without `virtual`, the handle type decides which method runs. This is what enables polymorphism.

### Without `virtual` (Wrong behavior)

```verilog
class base;
    function void display();           // NOT virtual
        $display("Base display");
    endfunction
endclass

class extended extends base;
    function void display();
        $display("Extended display");
    endfunction
endclass

module tb;
    initial begin
        base b;
        extended e = new();
        b = e;                         // base handle -> extended object
        b.display();                   // Output: "Base display" <-- WRONG!
        // Calls base version because method is not virtual
    end
endmodule
```

### With `virtual` (Correct behavior)

```verilog
class base;
    virtual function void display();   // VIRTUAL
        $display("Base display");
    endfunction
endclass

class extended extends base;
    function void display();
        $display("Extended display");
    endfunction
endclass

module tb;
    initial begin
        base b;
        extended e = new();
        b = e;                         // base handle -> extended object
        b.display();                   // Output: "Extended display" <-- CORRECT!
        // Calls extended version because method is virtual
    end
endmodule
```

---

## Scope Resolution Operator `::`

Used to access static members of a class from outside, or to access parent class members from within a derived class.

```verilog
class packet;
    static int count = 0;

    function new();
        count++;
    endfunction

    static function int get_count();
        return count;
    endfunction
endclass

module tb;
    initial begin
        packet p1 = new();
        packet p2 = new();
        $display("Total packets: %0d", packet::get_count());
        // Output: Total packets: 2
        // Access static method using ClassName::method_name
    end
endmodule
```

---

## `typedef class` (Forward Declaration)

When two classes reference each other, one must be declared before the other. Use `typedef class` to provide a forward declaration.

```verilog
typedef class B;    // Forward declaration -- "trust me, class B will exist"

class A;
    B b_handle;     // Now this compiles even though B is defined below
endclass

class B;
    A a_handle;
endclass
```

---

## Common Mistakes

1. **Forgetting `new()`** - Declaring a handle does NOT create an object. Accessing a null handle causes a runtime error.
2. **Handle assignment is not object copy** - `p2 = p1` makes both handles point to the same object, NOT a copy.
3. **Forgetting `virtual` for polymorphism** - Without `virtual`, the base class method always gets called regardless of the actual object type.
4. **Direct parent-to-child assignment** - Must use `$cast()`. Direct assignment is a compilation error.
5. **Accessing `local` members from child** - `local` is stricter than `protected`. Use `protected` if children need access.

---

## Self-Check Questions

**Q1:** What does `packet p;` do vs `packet p = new();`?
> `packet p;` declares a handle (null, no object). `packet p = new();` declares a handle AND creates an object.

**Q2:** After `p2 = p1;`, how many objects exist?
> One. Both handles point to the same object.

**Q3:** What is the difference between `virtual` and non-virtual methods?
> Virtual: actual object's method is called (polymorphism). Non-virtual: handle type's method is called.

**Q4:** When must you use `$cast`?
> When assigning a parent class handle to a child class handle. Direct assignment gives a compilation error.

**Q5:** What is the difference between `local` and `protected`?
> `local`: only accessible inside the class. `protected`: accessible inside the class AND its children.

**Q6:** Can you instantiate an abstract class?
> No. Attempting `new()` on a `virtual class` gives a compilation error.

---

## Concept Links

- Previous: [04 - Tasks & Functions](./04_Tasks_and_Functions.md)
- Next: [06 - TestBench Architecture](./06_Testbench_Architecture.md)
- Related: [07 - Randomization](./07_Randomization_and_Constraints.md) (uses classes extensively)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#classes)




