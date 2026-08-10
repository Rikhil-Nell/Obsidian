# 02 - UVM Hierarchy & Components

## Learning Objectives

After this section you will understand:
- The complete UVM testbench hierarchy
- The role of each component: Testbench, Test, Environment, Agent, Sequencer, Driver, Monitor, Scoreboard
- Active vs Passive agents
- Sequence items (transactions) and how sequences generate them
- The sequencer-driver handshake (`start_item`/`finish_item`, `get_next_item`/`item_done`)

---

## UVM Hierarchy Overview

> **Conceptual Clarity:** The UVM hierarchy is like a company org chart. The **Testbench** is the company. The **Test** is the CEO who decides what to do. The **Environment** is a department. The **Agent** is a team within that department. Inside each team, you have the **Sequencer** (task manager), **Driver** (the person who does the work), and **Monitor** (the quality inspector).

```
UVM Testbench (top-level module)
 |
 +-- DUT (Design Under Test)
 |
 +-- UVM Test (dynamically instantiated)
      |
      +-- UVM Environment
           |
           +-- UVM Agent (one per DUT interface)
           |    |
           |    +-- UVM Sequencer (controls stimulus flow)
           |    +-- UVM Driver (drives DUT pins)
           |    +-- UVM Monitor (observes DUT pins)
           |
           +-- UVM Scoreboard (checks correctness)
```

---

## UVM Testbench

The top-level **module** (not a class) that:
1. Instantiates the DUT
2. Instantiates the UVM Test class
3. Configures connections between them

**Key point:** The UVM Test is **dynamically instantiated at run-time**. This means:
- The testbench is compiled **once**
- Different tests can be selected at run-time (e.g., via command-line `+UVM_TESTNAME=my_test`)

---

## UVM Test

The **top-level UVM component** (class, not module). The test:
1. Instantiates the top-level environment
2. Configures the environment (via factory overrides or config database)
3. Applies stimulus by invoking sequences

### Test Inheritance Pattern

```verilog
// Base test -- instantiates environment, sets defaults
class base_test extends uvm_test;
    `uvm_component_utils(base_test)
    my_env env;

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        env = my_env::type_id::create("env", this);
    endfunction
endclass

// Specific test -- extends base, runs specific sequences
class random_test extends base_test;
    `uvm_component_utils(random_test)
    // ... configures different sequences or overrides
endclass
```

> **Conceptual Clarity:** The base test is like a template. Each specific test extends it and only changes what is different (which sequences to run, which overrides to apply). This avoids duplicating environment setup code.

---

## UVM Environment

A **container** component that groups related verification components:
- UVM Agents (one per DUT interface)
- UVM Scoreboards
- Other UVM Environments (for complex SoCs)

```
Top-Level Environment
 |
 +-- PCIe Agent      (for PCIe interface)
 +-- USB Agent       (for USB interface)
 +-- AXI Agent       (for AXI interface)
 +-- Scoreboard      (checks all outputs)
```

The environment defines the **reusable component topology**. Multiple tests can instantiate the same environment and configure it differently.

---

## UVM Agent

The agent is the key structural unit. It groups together the components that deal with **one specific DUT interface**.

### Agent Contents

| Component | Role |
|---|---|
| **Sequencer** | Controls stimulus flow (arbiter) |
| **Driver** | Converts transactions to pin-level signals |
| **Monitor** | Converts pin-level signals back to transactions |

### Active vs Passive Mode

| Mode | Sequencer | Driver | Monitor | Use Case |
|---|---|---|---|---|
| **Active** | Enabled | Enabled | Enabled | Generating and monitoring stimulus |
| **Passive** | Disabled | Disabled | Enabled | Only observing (e.g., on output port) |

> **Conceptual Clarity:** Think of an active agent as a full team (manager + worker + inspector). A passive agent is just the inspector watching what happens. You can dynamically switch between modes.

The agent can also optionally include:
- Coverage collectors
- Protocol checkers
- TLM models

---

## UVM Sequence Item (Transaction)

The **fundamental building block** -- the smallest data transfer in UVM.

> **Conceptual Clarity:** If the testbench is a post office, a sequence item is a single letter. It contains all the data fields (address, write data, read/write flag) that define one transaction on the DUT interface.

### Key Properties

- Extended from `uvm_sequence_item`
- Request data is `rand` (randomizable)
- Response data is NOT `rand`
- Registered with factory via `` `uvm_object_utils ``
- Has built-in methods: `print()`, `copy()`, `compare()`

### Example

```verilog
class lpi_seq_item extends uvm_sequence_item;
    `uvm_object_utils(lpi_seq_item)

    // Request data -- randomizable
    rand bit slp_req0;
    rand bit slp_req1;
    rand bit wakeup_req0;
    rand bit wakeup_req1;

    // Constraints on data members
    constraint slp_wakeup_reqs {
        ((slp_req0 || slp_req1) && (wakeup_req0 || wakeup_req1)) != 1;
    };

    // Constructor
    function new(string name = "lpi_seq_item");
        super.new(name);
    endfunction
endclass
```

### Bus Transaction Example

```verilog
class bus_seq_item extends uvm_sequence_item;
    `uvm_object_utils(bus_seq_item)

    // Request fields (rand)
    rand logic [31:0] addr;
    rand logic [31:0] write_data;
    rand bit          read_not_write;
    rand int          delay;

    // Response fields (NOT rand)
    bit          error;
    logic [31:0] read_data;

    function new(string name = "bus_seq_item");
        super.new(name);
    endfunction
endclass
```

---

## UVM Sequence

An **ordered collection of transactions**. Sequences shape and constrain transactions, then send them to the sequencer.

> **Conceptual Clarity:** If a sequence item is a single letter, a sequence is a batch of letters prepared for mailing. The sequence decides how many letters to send, what kind, and in what order.

### Key Points

- Extended from `uvm_sequence #(sequence_item_type)`
- Registered via `` `uvm_object_utils `` (it is an object, not a component)
- Main logic goes in the `body()` task
- Uses `start_item()` and `finish_item()` to handshake with the sequencer/driver
- Sequences can be hierarchical: a parent sequence can invoke child sequences

### Example

```verilog
class lpi_basic_seq extends uvm_sequence #(lpi_seq_item);
    `uvm_object_utils(lpi_basic_seq)

    rand int num_of_trans;

    function new(string name = "lpi_basic_seq");
        super.new(name);
    endfunction

    task body();
        lpi_seq_item seq_item;
        seq_item = lpi_seq_item::type_id::create("seq_item");

        for (int i = 0; i < num_of_trans; i++) begin
            `uvm_info(get_type_name(),
                $sformatf("in seq for count = %0d", i), UVM_LOW)

            start_item(seq_item);                    // blocks until driver ready

            if (!seq_item.randomize())
                `uvm_error("body", "Randomization failed for seq_item")

            `uvm_info(get_type_name(),
                $sformatf("req0=%0d req1=%0d slp0=%0d slp1=%0d",
                    seq_item.wakeup_req0, seq_item.wakeup_req1,
                    seq_item.slp_req0, seq_item.slp_req1), UVM_LOW)

            finish_item(seq_item);                   // blocks until driver done
        end
    endtask
endclass
```

### `start_item` / `finish_item` Flow

```
Sequence                    Sequencer                   Driver
   |                            |                         |
   |-- start_item(item) ------>|                         |
   |   (blocks until driver    |                         |
   |    requests next item)    |                         |
   |                           |<-- get_next_item() -----|
   |<---- returns -------------|                         |
   |                           |                         |
   | (randomize item here)     |                         |
   |                           |                         |
   |-- finish_item(item) ---->|--- sends item ---------->|
   |   (blocks until driver   |                         |
   |    calls item_done)      |                         |
   |                          |<--- item_done() --------|
   |<---- returns ------------|                         |
```

---

## UVM Sequencer

The sequencer is a **simple arbiter** that controls the flow of request/response sequence items between sequences and the driver.

- Extended from `uvm_sequencer #(sequence_item_type)`
- Has a `seq_item_export` TLM port
- Multiple sequences can be bound to the same sequencer (arbitrated)
- Connected to the driver via TLM

```verilog
// Connection in the agent's connect_phase:
driver.seq_item_port.connect(sequencer.seq_item_export);
```

---

## UVM Driver

The driver is where **TLM meets the pin level**. It:
1. Receives transactions from the sequencer
2. Converts transactions into pin-level signal activity
3. Drives the DUT interface

- Extended from `uvm_driver #(sequence_item_type)`
- Has a `seq_item_port` TLM port (connects to sequencer)
- Has access to the DUT interface (via virtual interface)
- Registered via `` `uvm_component_utils ``

### Driver Methods

| Method | Blocking? | Description |
|---|---|---|
| `get_next_item()` | Yes | Blocks until sequencer has a transaction |
| `try_next_item()` | No | Returns null if no transaction available |
| `item_done()` | No | Completes the driver-sequencer handshake |
| `put()` | No | Places a response sequence item in the sequencer |

### Driver Task Pattern

```verilog
task run_phase(uvm_phase phase);
    forever begin
        seq_item_port.get_next_item(req);    // get transaction from sequencer

        // Drive DUT signals from req fields
        vif.addr       <= req.addr;
        vif.write_data <= req.write_data;
        vif.rw         <= req.read_not_write;
        @(posedge vif.clk);                  // wait for clock

        seq_item_port.item_done();           // signal completion to sequencer
    end
endtask
```

> **Conceptual Clarity:** The driver's only job is to translate transactions into wires. It should NOT check outputs -- that's the monitor's and scoreboard's job. Keeping them separate preserves modularity.

---

## UVM Monitor

The monitor is the **reverse of the driver**:
1. Observes DUT signal/pin-level activity
2. Converts signals back into transactions
3. Broadcasts transactions through its **analysis port**

- Extended from `uvm_monitor`
- Has an `analysis_port` for broadcasting
- Should NOT control DUT signals (unidirectional)
- Can optionally perform coverage collection, checking, logging

### Monitor Pattern

```verilog
class my_monitor extends uvm_monitor;
    `uvm_component_utils(my_monitor)

    virtual my_if vif;
    uvm_analysis_port #(my_trans) ap;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        ap = new("ap", this);
    endfunction

    task run_phase(uvm_phase phase);
        my_trans t;
        forever begin
            @(posedge vif.clk);
            t = my_trans::type_id::create("t");
            t.addr = vif.addr;
            t.data = vif.data;
            // ...
            ap.write(t);    // broadcast to all connected subscribers
        end
    endtask
endclass
```

---

## UVM Scoreboard

The scoreboard **checks DUT correctness** by comparing actual outputs with expected outputs.

- Receives transactions from the monitor via analysis ports
- Can use a reference model (C/C++ via DPI, SystemC TLM2.0, or SV model) for expected outputs
- Not to be confused with SVA "checker"

### Scoreboard Pattern

```verilog
class my_scoreboard extends uvm_scoreboard;
    `uvm_component_utils(my_scoreboard)

    uvm_analysis_imp #(my_trans, my_scoreboard) ap;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        ap = new("ap", this);
    endfunction

    function void write(my_trans t);
        // Compare t with expected output
        // Log PASS/FAIL
    endfunction
endclass
```

---

## Common Mistakes

1. **Embedding monitor logic in the driver** -- Violates modularity. You can't turn the monitor ON/OFF independently if it's in the driver.
2. **Having the monitor control DUT signals** -- Monitor is unidirectional (observe only). Driver is bidirectional.
3. **Confusing sequence and sequencer** -- A sequence generates transactions. A sequencer routes them to the driver.
4. **Forgetting to connect TLM ports** -- `driver.seq_item_port.connect(sequencer.seq_item_export)` must be in `connect_phase`.
5. **Using `new()` instead of `type_id::create()`** -- Bypasses the factory.

---

## Self-Check Questions

**Q1:** List the components inside a UVM Agent.
> Sequencer, Driver, and Monitor.

**Q2:** What is the difference between an active and passive agent?
> Active agent: sequencer + driver + monitor are all enabled (generates and monitors stimulus). Passive agent: only the monitor is enabled (observes only).

**Q3:** What is the relationship between a sequence item and a sequence?
> A sequence item (transaction) is a single data transfer. A sequence is an ordered collection of sequence items that shapes and constrains them before sending them to the sequencer.

**Q4:** What does `start_item()` do? What does `finish_item()` do?
> `start_item()` blocks until the driver is ready to accept a new transaction. `finish_item()` sends the transaction to the driver and blocks until the driver signals `item_done()`.

**Q5:** Why should the monitor NOT control DUT signals?
> Modularity. The monitor's job is observation only (unidirectional). If it controls signals, you lose the ability to independently enable/disable monitoring and driving, which defeats the purpose of the agent architecture.

---

## Concept Links

- Previous: [01 - UVM Introduction & Factory](./01_UVM_Introduction.md)
- Next: [03 - TLM Communication](./03_TLM_Communication.md)
- Formula Sheet: [06 - Formula Sheet](../05_Formula_Sheets/02_UVM_Formula_Sheet.md#uvm-hierarchy)
- Related (from CAT-2): [06 - TestBench Architecture](../CAT-2/study_guide/06_testbench_architecture.md) (agent, monitor, driver pattern from labs)



