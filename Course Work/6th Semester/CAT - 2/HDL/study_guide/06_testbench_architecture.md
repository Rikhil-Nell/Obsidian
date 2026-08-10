# 06 - TestBench Architecture

## Learning Objectives

After this section you will understand:
- The layered testbench architecture in SystemVerilog
- The role of each component: Transaction, Generator, Driver, Interface, Environment
- How mailboxes and events connect these components
- How to write a basic adder testbench step by step

---

## What is a TestBench?

> **Conceptual Clarity:** A testbench is a program that tests your hardware design. Think of it like a quality control process in a factory: you feed inputs to the device, observe the outputs, and check if they match expectations. In SystemVerilog, testbenches use OOP (classes) to create modular, reusable testing components.

The design being tested is called the **DUT** (Design Under Test).

---

## TestBench Architecture

![[testbench_architecture.png]]

The layered testbench has these components:

| Component | Role |
|---|---|
| **Transaction** | Data packet containing stimulus fields |
| **Generator** | Creates random transactions |
| **Driver** | Converts transactions into pin-level signals for DUT |
| **Interface** | Groups DUT signals together |
| **Environment** | Connects all components and runs the test |
| **Test** | Top-level, configures and starts the environment |

> **Conceptual Clarity:** Think of it like a restaurant. The **Transaction** is the order slip. The **Generator** is the customer writing orders. The **Driver** is the chef who reads the order and cooks (converts order to actual food). The **Interface** is the kitchen window where orders pass through. The **Environment** is the restaurant manager who coordinates everything.

---

## Step-by-Step: Building an Adder TestBench

### Step 1: Transaction Class

The transaction class defines the fields needed to test the DUT.

```verilog
class transaction;
    // Stimulus fields -- randomized to generate different test cases
    rand bit [3:0] a;
    rand bit [3:0] b;

    // Response field -- not randomized (comes from DUT)
    bit [4:0] sum;

    // Display method for debugging
    function void display(string tag = "");
        $display("[%s] a = %0d, b = %0d, sum = %0d", tag, a, b, sum);
    endfunction
endclass
```

> **Conceptual Clarity:** `rand` means these fields will get random values when you call `randomize()`. This is how SystemVerilog automates test generation -- instead of manually writing `a=1, b=2`, `a=3, b=7`, etc., the system generates thousands of random combinations.

---

### Step 2: Generator Class

The generator creates randomized transactions and sends them to the driver via a mailbox.

```verilog
class generator;
    transaction trans;          // Transaction handle
    mailbox gen2drv;            // Mailbox to send transactions to driver
    event ended;                // Event to signal completion
    int repeat_count;           // How many transactions to generate

    // Constructor: receive mailbox handle from environment
    function new(mailbox gen2drv);
        this.gen2drv = gen2drv;
    endfunction

    // Main task: generate and send transactions
    task main();
        repeat (repeat_count) begin
            trans = new();
            if (!trans.randomize())
                $fatal("Randomization failed!");
            trans.display("GEN");
            gen2drv.put(trans);        // Send transaction to driver
        end
        -> ended;                       // Signal that generation is done
    endtask
endclass
```

---

### Step 3: Interface

The interface groups all DUT signals in one place, making connections cleaner.

```verilog
interface adder_if;
    logic [3:0] a;
    logic [3:0] b;
    logic [4:0] sum;
    logic       clk;
    logic       reset;
endinterface
```

> **Conceptual Clarity:** Without an interface, you would have to pass dozens of individual wires between the testbench and DUT. An interface bundles them all together -- like using a single USB cable instead of connecting 12 separate wires.

---

### Step 4: Driver Class

The driver receives transactions from the generator and drives the DUT pins.

```verilog
class driver;
    virtual adder_if vif;       // Virtual interface to DUT signals
    mailbox gen2drv;            // Mailbox to receive transactions

    function new(mailbox gen2drv, virtual adder_if vif);
        this.gen2drv = gen2drv;
        this.vif = vif;
    endfunction

    // Reset the DUT
    task reset();
        vif.a     <= 0;
        vif.b     <= 0;
        @(posedge vif.clk);
        vif.reset <= 1;
        @(posedge vif.clk);
        vif.reset <= 0;
    endtask

    // Main driving task
    task main();
        transaction trans;
        forever begin
            gen2drv.get(trans);          // Get transaction from generator (blocking)
            @(posedge vif.clk);
            vif.a <= trans.a;            // Drive DUT inputs
            vif.b <= trans.b;
            @(posedge vif.clk);
            trans.display("DRV");
        end
    endtask
endclass
```

---

### Step 5: Environment Class

The environment creates all components and connects them.

```verilog
class environment;
    generator gen;
    driver    drv;
    mailbox   gen2drv;
    virtual adder_if vif;
    event gen_ended;

    function new(virtual adder_if vif);
        this.vif = vif;
        gen2drv = new();                    // Create mailbox
        gen = new(gen2drv);                 // Pass mailbox to generator
        drv = new(gen2drv, vif);            // Pass mailbox + interface to driver
    endfunction

    task run();
        drv.reset();                        // Reset DUT first

        fork
            gen.main();                     // Start generator
            drv.main();                     // Start driver
        join_any

        wait(gen.ended.triggered);          // Wait for generator to finish
        #50;                                // Extra time for pipeline to flush
    endtask
endclass
```

---

### Step 6: Top-Level Test

```verilog
module tb_top;
    adder_if aif();

    // Instantiate DUT
    adder DUT (
        .a(aif.a),
        .b(aif.b),
        .sum(aif.sum),
        .clk(aif.clk),
        .reset(aif.reset)
    );

    // Clock generation
    initial begin
        aif.clk = 0;
        forever #5 aif.clk = ~aif.clk;
    end

    // Run test
    initial begin
        environment env = new(aif);
        env.gen.repeat_count = 10;         // Generate 10 transactions
        env.run();
        $finish;
    end
endmodule
```

---

## Data Flow Summary

```
Generator  --[mailbox]--> Driver --[interface]--> DUT
   |                        |
   | creates                | drives
   v                        v
Transaction          DUT input pins (a, b)
```

---

## Common Mistakes

1. **Forgetting `virtual` on interface handles** - Class members holding interface references must use `virtual adder_if`, not just `adder_if`.
2. **Not calling `new()` on mailbox** - The mailbox must be created with `new()` before use.
3. **Missing `fork-join_any`** - Generator and driver must run in parallel; using sequential code will deadlock.
4. **Not randomizing** - Forgetting to call `trans.randomize()` gives default (zero) values.

---

## Self-Check Questions

**Q1:** Why do we use a mailbox between generator and driver?
> The mailbox provides safe, synchronized data transfer between two parallel processes. Without it, both processes could try to access the same data simultaneously.

**Q2:** What does `virtual` mean in `virtual adder_if vif`?
> It means the interface handle inside a class is a reference to an actual interface instance. Without `virtual`, you cannot store interface references inside classes.

**Q3:** Why use `fork-join_any` in the environment, not `fork-join`?
> The driver runs `forever` (never finishes), so `fork-join` would wait forever. `fork-join_any` lets us proceed once the generator finishes.

---

## Concept Links

- Previous: [05 - Classes & OOP](./05_classes_and_oop.md)
- Next: [07 - Randomization & Constraints](./07_randomization_and_constraints.md)
- Related: [08 - IPC](./08_interprocess_communication.md) (mailbox, events used here)
