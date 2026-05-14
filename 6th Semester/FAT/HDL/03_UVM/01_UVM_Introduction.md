# 01 - UVM Introduction & Factory

## Learning Objectives

After this section you will understand:
- What UVM is and why it was created
- The difference between pure SystemVerilog testbenches and UVM-based testbenches
- The UVM **factory** pattern and why it matters
- `uvm_object_utils` vs `uvm_component_utils` macros
- Factory overriding for test customization

---

## What is UVM?

> **Conceptual Clarity:** UVM (Universal Verification Methodology) is a **standardized class library** built on top of SystemVerilog. Think of it as a "framework" -- like Django is to Python or React is to JavaScript. You don't write testbenches from scratch anymore; you use UVM's pre-built classes (driver, monitor, agent, etc.) and extend them for your specific design.

UVM is:
- A **transaction-level methodology (TLM)** designed for testbench development
- A **class library** that makes it easy to write configurable and reusable verification code
- Built on OOP concepts (inheritance, polymorphism) but you don't need to be an OOP expert

### Why UVM Over Pure SystemVerilog?

| Aspect | Pure SystemVerilog | UVM |
|---|---|---|
| **Coding standard** | Ad hoc, varies per engineer | Standardized methodology |
| **Reusability** | Low -- testbenches are custom | High -- components carry forward to next project |
| **What changes per project** | Everything | Only driver, scoreboard, sequences |
| **Maintainability** | Hard to understand others' code | Consistent structure across teams |
| **Factory/Override** | Not available | Built-in test customization |

### History

UVM was born from three competing methodologies:

```
OVM (Mentor) + URM (Cadence) + VMM (Synopsys)
                    |
                    v
        Accelera picked OVM as base
                    |
                    v
              UVM was born
```

All vendors now support UVM. It is the industry standard for large SoC verification.

---

## Polymorphism: The Bedrock of UVM

> **Conceptual Clarity:** Polymorphism means "many forms." In UVM, you use a parent class handle to hold a child class object and call methods on it. The correct child method runs because of the `virtual` keyword. This is how UVM achieves flexibility -- you write generic testbench code that works with any specific implementation.

$$\text{Polymorphism} = \text{Inheritance} + \text{Virtual Methods}$$

### Example

```verilog
class vehicle;
    virtual function void vehicle_type();
        $display("vehicle");
    endfunction
    virtual task color();
        $display("It has color");
    endtask
endclass

class four_wheeler extends vehicle;
    function void vehicle_type();
        $display("It's a four wheeler");
    endfunction
    task color();
        $display("It has different colors");
    endtask
endclass

class BENZ extends four_wheeler;
    function void vehicle_type();
        $display("It's a BENZ");
    endfunction
    task color();
        $display("It is Black");
    endtask
endclass
```

```verilog
program polymorphism;
    initial begin
        vehicle vehcl;
        four_wheeler four_whlr;
        BENZ benz;

        four_whlr = new();
        benz = new();

        vehcl = four_whlr;           // parent handle holds child object
        vehcl.vehicle_type();        // prints: "It's a four wheeler"

        vehcl = benz;                // parent handle holds grandchild
        vehcl.vehicle_type();        // prints: "It's a BENZ"

        four_whlr = benz;            // child handle holds grandchild
        four_whlr.color();           // prints: "It is Black"
    end
endprogram
```

**Output:**
```
It's a four wheeler
It's a BENZ
It is Black
```

---

## The UVM Factory

> **Conceptual Clarity:** The factory is like a "smart constructor." Instead of calling `new()` directly, you register your class with the factory and use `type_id::create()` to make objects. This lets you swap one class for another at runtime without changing any code -- essential for running different tests.

### Why Factory Matters

The factory enables:
- **Object overriding** -- swap a base class for a derived class globally
- **Reusability** -- testbench code stays the same, only overrides change
- **Test customization** -- each test can use different sequences/transactions
- **Polymorphism** -- the factory always returns the correct derived type

### Factory Registration Macros

Every UVM class must be registered with the factory using one of two macros:

| Macro | Used For | Has Phases? | Has Hierarchy? |
|---|---|---|---|
| `` `uvm_object_utils(class_name) `` | sequence, sequence_item, transaction, config | No | No |
| `` `uvm_component_utils(class_name) `` | driver, monitor, agent, env, test, scoreboard | Yes | Yes |

### Which Classes Use Which Macro?

**Objects** (`` `uvm_object_utils ``):
- `sequence` -- test stimulus patterns
- `sequence_item` -- individual transactions
- `transaction` -- data packets
- `config object` -- configuration data

These are **temporary** -- created, used, and deleted dynamically.

**Components** (`` `uvm_component_utils ``):
- `driver` -- drives DUT signals
- `monitor` -- observes DUT signals
- `agent` -- groups driver + monitor + sequencer
- `env` (environment) -- groups agents + scoreboard
- `test` -- top-level test configuration
- `scoreboard` -- checks DUT output

These are **permanent hierarchical blocks** that follow the phase schedule.

> **Conceptual Clarity:** Think of components as **workers in a factory** -- they show up every day, follow the same schedule (phases), and have a fixed position (hierarchy). Objects are like **parcels** moving through the factory -- they are created, processed, and discarded.

### Factory Override

```verilog
// Original: base_seq is used everywhere
class base_seq extends uvm_sequence #(my_item);
    `uvm_object_utils(base_seq)
    // ...
endclass

// New: error_seq extends base_seq with error injection
class error_seq extends base_seq;
    `uvm_object_utils(error_seq)
    // ... adds error scenarios
endclass

// Override: wherever base_seq is created, error_seq is used instead
set_type_override_by_type(base_seq::get_type(), error_seq::get_type());
```

Now **every** `base_seq::type_id::create()` call automatically creates an `error_seq` instead. Zero code changes in the testbench.

---

## Why Phases Are Needed

A UVM testbench has many components (test, env, agent, driver, monitor, scoreboard). If they all start working randomly, chaos happens. UVM divides simulation into **ordered steps called phases**.

| Phase | Purpose |
|---|---|
| **Build** | Create all components |
| **Connect** | Connect TLM ports/exports between components |
| **Run** | Actual simulation (drive signals, generate packets, monitor DUT) |
| **Check** | Final checking after simulation |
| **Report** | Print pass/fail summaries |

> **Conceptual Clarity:** Phases are like shifts in a factory:
> 1. **Build** the factory floor
> 2. **Connect** the machines
> 3. **Start production** (run)
> 4. **Inspect** products (check)
> 5. **Generate report** (report)

Only **components** have phases (because they are permanent). Objects (sequences, transactions) do not -- they are temporary data that flows through the components.

---

## Common Mistakes

1. **Using `uvm_object_utils` for a driver** -- Drivers need phases and hierarchy, so they must use `uvm_component_utils`.
2. **Using `new()` instead of `type_id::create()`** -- Bypasses the factory, loses override capability.
3. **Forgetting `super.new()` in constructor** -- UVM components must chain to the parent constructor.
4. **Confusing objects and components** -- Objects are data (temporary). Components are structural (permanent).

---

## Self-Check Questions

**Q1:** What is the difference between `uvm_object_utils` and `uvm_component_utils`?
> `uvm_object_utils` is for temporary data objects (sequences, transactions) that have no hierarchy or phases. `uvm_component_utils` is for permanent testbench components (driver, monitor, etc.) that participate in the phase system and form the testbench hierarchy.

**Q2:** Why do we use the factory (`type_id::create()`) instead of `new()`?
> The factory allows runtime overriding -- you can swap one class for another globally without changing any testbench code. This enables test customization and reusability.

**Q3:** What does polymorphism enable in UVM?
> It allows a parent class handle to hold a child class object and call the correct child method (via `virtual`). This means the testbench can use generic code that works with any specific sequence/driver implementation.

**Q4:** Is a `sequence` an object or a component?
> An object. Sequences are temporary data patterns that are created, used to generate transactions, and then destroyed. They use `uvm_object_utils`.

---

## Concept Links

- Next: [02 - UVM Hierarchy & Components](./02_UVM_Hierarchy.md)
- Formula Sheet: [06 - Formula Sheet](../05_Formula_Sheets/02_UVM_Formula_Sheet.md#uvm-factory)
- Related (from CAT-2): [05 - Classes & OOP](../CAT-2/study_guide/05_classes_and_oop.md) (polymorphism, virtual, inheritance)


