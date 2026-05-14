# 05 - Building a UVM Testbench (Complete Example)

## Learning Objectives

After this section you will understand:
- How to assemble a complete UVM testbench from individual components
- The relationship between the lab code (CAT-2 week-8) and UVM components
- How each component maps to UVM base classes
- The complete flow from sequence item to DUT stimulation and output checking

---

## Mapping Lab Code to UVM

> **Conceptual Clarity:** Your week-8 lab code already implements the UVM architecture pattern -- just without the UVM base classes. UVM formalizes what you already know.

| Lab Code (Pure SV) | UVM Equivalent | UVM Base Class |
|---|---|---|
| `transaction` class | Sequence Item | `uvm_sequence_item` |
| `generator` class | Sequence | `uvm_sequence` |
| `driver` class | Driver | `uvm_driver` |
| `monitor` class | Monitor | `uvm_monitor` |
| `scoreboard` class | Scoreboard | `uvm_scoreboard` |
| `agent` class | Agent | `uvm_agent` |
| `environment` class | Environment | `uvm_env` |
| `base_test` class | Test | `uvm_test` |
| `mailbox #(transaction)` | TLM port/export | `seq_item_port` / `analysis_port` |
| `testbench` module | Testbench module | Same (still a module) |

---

## Complete UVM Testbench: Full Adder

This example parallels your lab code but uses UVM base classes.

### Step 1: Interface (Unchanged)

```verilog
interface full_adder_if;
    logic a, b, cin;
    logic sum, cout;
endinterface
```

### Step 2: Sequence Item (was `transaction`)

```verilog
class fa_seq_item extends uvm_sequence_item;
    `uvm_object_utils(fa_seq_item)

    // Request fields
    rand bit a, b, cin;

    // Response fields
    bit sum, cout;

    function new(string name = "fa_seq_item");
        super.new(name);
    endfunction

    function void display(string msg = "");
        `uvm_info("SEQ_ITEM", $sformatf("%s A=%b B=%b Cin=%b | Sum=%b Cout=%b",
            msg, a, b, cin, sum, cout), UVM_LOW)
    endfunction
endclass
```

**Differences from lab:**
- Extends `uvm_sequence_item` (not plain class)
- Registered with `` `uvm_object_utils ``
- Uses `uvm_info` instead of `$display`
- Constructor calls `super.new(name)`

### Step 3: Sequence (was `generator`)

```verilog
class fa_random_seq extends uvm_sequence #(fa_seq_item);
    `uvm_object_utils(fa_random_seq)

    rand int num_trans;

    constraint c_num { num_trans inside {[5:20]}; }

    function new(string name = "fa_random_seq");
        super.new(name);
    endfunction

    task body();
        fa_seq_item item;

        for (int i = 0; i < num_trans; i++) begin
            item = fa_seq_item::type_id::create("item");
            start_item(item);
            if (!item.randomize())
                `uvm_error("SEQ", "Randomization failed")
            finish_item(item);
        end
    endtask
endclass

// Directed test sequence -- exhaustive 8 combinations
class fa_directed_seq extends uvm_sequence #(fa_seq_item);
    `uvm_object_utils(fa_directed_seq)

    function new(string name = "fa_directed_seq");
        super.new(name);
    endfunction

    task body();
        fa_seq_item item;

        for (int i = 0; i < 8; i++) begin
            item = fa_seq_item::type_id::create("item");
            start_item(item);
            item.a   = i[2];
            item.b   = i[1];
            item.cin = i[0];
            finish_item(item);
        end
    endtask
endclass
```

**Differences from lab:**
- Uses `start_item` / `finish_item` instead of `mailbox.put()`
- Factory creation instead of `new()`
- `body()` task replaces `run()` task

### Step 4: Driver

```verilog
class fa_driver extends uvm_driver #(fa_seq_item);
    `uvm_component_utils(fa_driver)

    virtual full_adder_if vif;

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        if (!uvm_config_db #(virtual full_adder_if)::get(this, "", "vif", vif))
            `uvm_fatal("DRV", "Could not get virtual interface")
    endfunction

    task run_phase(uvm_phase phase);
        fa_seq_item item;

        forever begin
            seq_item_port.get_next_item(item);    // get from sequencer

            // Drive DUT
            vif.a   = item.a;
            vif.b   = item.b;
            vif.cin = item.cin;
            #10;

            seq_item_port.item_done();            // handshake complete
        end
    endtask
endclass
```

**Key differences from lab:**
- Uses `seq_item_port.get_next_item()` instead of `mailbox.get()`
- Gets virtual interface from `uvm_config_db` instead of constructor argument
- Constructor takes `(string name, uvm_component parent)` -- component pattern
- `run_phase` replaces `run` task

### Step 5: Monitor (with Coverage)

```verilog
class fa_monitor extends uvm_monitor;
    `uvm_component_utils(fa_monitor)

    virtual full_adder_if vif;
    uvm_analysis_port #(fa_seq_item) ap;

    // Functional coverage
    covergroup fa_cg;
        option.per_instance = 1;
        A_cp   : coverpoint vif.a;
        B_cp   : coverpoint vif.b;
        CIN_cp : coverpoint vif.cin;
        ABC_cross : cross A_cp, B_cp, CIN_cp;
    endgroup

    function new(string name, uvm_component parent);
        super.new(name, parent);
        ap = new("ap", this);
        fa_cg = new();
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        if (!uvm_config_db #(virtual full_adder_if)::get(this, "", "vif", vif))
            `uvm_fatal("MON", "Could not get virtual interface")
    endfunction

    task run_phase(uvm_phase phase);
        fa_seq_item item;

        forever begin
            #9;    // sample before next stimulus
            item = fa_seq_item::type_id::create("item");
            item.a    = vif.a;
            item.b    = vif.b;
            item.cin  = vif.cin;
            item.sum  = vif.sum;
            item.cout = vif.cout;

            fa_cg.sample();
            ap.write(item);    // broadcast via analysis port

            #1;
        end
    endtask
endclass
```

**Key difference from lab:** Uses `analysis_port.write()` instead of `mailbox.put()` -- this enables broadcast to multiple subscribers (scoreboard, coverage, logger, etc.).

### Step 6: Scoreboard

```verilog
class fa_scoreboard extends uvm_scoreboard;
    `uvm_component_utils(fa_scoreboard)

    uvm_analysis_imp #(fa_seq_item, fa_scoreboard) ap;

    int pass_count, fail_count;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        ap = new("ap", this);
    endfunction

    function void write(fa_seq_item item);
        bit expected_sum, expected_cout;

        expected_sum  = item.a ^ item.b ^ item.cin;
        expected_cout = (item.a & item.b) | (item.b & item.cin) | (item.a & item.cin);

        if (item.sum !== expected_sum || item.cout !== expected_cout) begin
            `uvm_error("SCB", $sformatf("FAIL: A=%b B=%b Cin=%b | Expected sum=%b cout=%b, Got sum=%b cout=%b",
                item.a, item.b, item.cin, expected_sum, expected_cout, item.sum, item.cout))
            fail_count++;
        end
        else begin
            `uvm_info("SCB", "PASS", UVM_HIGH)
            pass_count++;
        end
    endfunction

    function void report_phase(uvm_phase phase);
        `uvm_info("SCB", $sformatf("Passed: %0d, Failed: %0d", pass_count, fail_count), UVM_LOW)
    endfunction
endclass
```

### Step 7: Agent

```verilog
class fa_agent extends uvm_agent;
    `uvm_component_utils(fa_agent)

    fa_driver    drv;
    fa_monitor   mon;
    uvm_sequencer #(fa_seq_item) sqr;

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);

        mon = fa_monitor::type_id::create("mon", this);

        if (get_is_active() == UVM_ACTIVE) begin
            drv = fa_driver::type_id::create("drv", this);
            sqr = uvm_sequencer #(fa_seq_item)::type_id::create("sqr", this);
        end
    endfunction

    function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);
        if (get_is_active() == UVM_ACTIVE)
            drv.seq_item_port.connect(sqr.seq_item_export);
    endfunction
endclass
```

**Key:** `get_is_active()` replaces the manual `is_active` bit from your lab code.

### Step 8: Environment

```verilog
class fa_env extends uvm_env;
    `uvm_component_utils(fa_env)

    fa_agent      agt;
    fa_scoreboard scb;

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        agt = fa_agent::type_id::create("agt", this);
        scb = fa_scoreboard::type_id::create("scb", this);
    endfunction

    function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);
        agt.mon.ap.connect(scb.ap);    // monitor broadcasts to scoreboard
    endfunction
endclass
```

### Step 9: Test

```verilog
class fa_base_test extends uvm_test;
    `uvm_component_utils(fa_base_test)

    fa_env env;

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        env = fa_env::type_id::create("env", this);
    endfunction

    task run_phase(uvm_phase phase);
        fa_directed_seq seq;
        phase.raise_objection(this);

        seq = fa_directed_seq::type_id::create("seq");
        seq.start(env.agt.sqr);    // start sequence on the agent's sequencer

        phase.drop_objection(this);
    endtask
endclass
```

### Step 10: Top-Level Testbench Module

```verilog
module tb_top;
    full_adder_if vif();

    full_adder dut (
        .a(vif.a), .b(vif.b), .cin(vif.cin),
        .sum(vif.sum), .cout(vif.cout)
    );

    initial begin
        uvm_config_db #(virtual full_adder_if)::set(null, "*", "vif", vif);
        run_test("fa_base_test");
    end
endmodule
```

**Key UVM-specific additions:**
- `uvm_config_db::set()` shares the virtual interface with all components
- `run_test("fa_base_test")` dynamically creates the test and starts the phase engine

---

## Data Flow Summary

```
Test                    Environment
 |                         |
 |-- creates env --------->|
                           |
              Agent                    Scoreboard
               |                          |
   Sequencer  Driver  Monitor            |
      |         |        |               |
      |<-seq----|        |               |
      |         |        |               |
      |    DUT signals   |               |
      |         |------->|               |
      |         |   (observes)           |
      |         |        |--write()----->|
      |         |        |  (analysis    |
      |         |        |   port)       |
```

1. **Test** creates environment and starts a sequence on the agent's sequencer
2. **Sequence** generates sequence items (`start_item`/`finish_item`)
3. **Sequencer** routes items to the driver
4. **Driver** converts transactions to pin-level signals on the DUT
5. **Monitor** observes DUT outputs, converts back to transactions
6. **Monitor** broadcasts via `analysis_port.write()`
7. **Scoreboard** receives transactions and checks correctness

---

## Lab Code vs UVM: Side-by-Side

| Feature | Lab Code (Pure SV) | UVM |
|---|---|---|
| Component creation | `new()` | `type_id::create()` |
| Communication | `mailbox` | TLM ports/exports |
| Interface passing | Constructor argument | `uvm_config_db` |
| Execution control | `fork`/`join` in top | Phase engine (`build`/`connect`/`run`) |
| Test selection | Compile-time (`directed_test t;`) | Run-time (`+UVM_TESTNAME=...`) |
| Broadcast | Not available | `analysis_port` (1-to-many) |
| Override/Swap | Not available | Factory override |
| Active/Passive | Manual `is_active` bit | Built-in `get_is_active()` |
| Logging | `$display` | `uvm_info`/`uvm_error`/`uvm_fatal` |

---

## UVM Reporting Macros

| Macro | Severity | Typical Use |
|---|---|---|
| `` `uvm_info(ID, MSG, VERBOSITY) `` | Informational | Progress messages, debug |
| `` `uvm_warning(ID, MSG) `` | Warning | Non-fatal issues |
| `` `uvm_error(ID, MSG) `` | Error | Failures, mismatches |
| `` `uvm_fatal(ID, MSG) `` | Fatal | Unrecoverable errors, stops simulation |

Verbosity levels: `UVM_NONE`, `UVM_LOW`, `UVM_MEDIUM`, `UVM_HIGH`, `UVM_FULL`, `UVM_DEBUG`

---

## `uvm_config_db`: Sharing Configuration

The config database replaces constructor arguments for passing virtual interfaces and configuration objects.

```verilog
// SET (in top-level module):
uvm_config_db #(virtual my_if)::set(null, "*", "vif", vif_instance);

// GET (in component):
if (!uvm_config_db #(virtual my_if)::get(this, "", "vif", vif))
    `uvm_fatal("CFG", "Could not get virtual interface")
```

| Parameter | Meaning |
|---|---|
| `null` (set) | Setting from top-level (no parent component) |
| `"*"` (set) | Make available to all components in hierarchy |
| `"vif"` | Key name to look up |
| `vif` | The actual value |
| `this` (get) | The component requesting the value |

---

## Self-Check Questions

**Q1:** What replaces the `mailbox` from lab code in UVM?
> TLM ports/exports. The driver uses `seq_item_port` to get transactions from the sequencer. The monitor uses `analysis_port` to broadcast to the scoreboard.

**Q2:** How does the test select which sequence to run?
> The test creates a sequence object and calls `seq.start(sequencer)` in `run_phase`.

**Q3:** Why use `uvm_config_db` instead of passing the virtual interface through the constructor?
> `uvm_config_db` decouples the configuration from the component hierarchy. Any component can look up any configuration value without requiring it as a constructor argument. This makes components more reusable.

**Q4:** What does `run_test("test_name")` do?
> It dynamically creates the specified test class via the factory, builds the entire component hierarchy (build_phase), connects everything (connect_phase), runs the simulation (run_phase), and performs cleanup.

---

## Concept Links

- Previous: [04 - UVM Phases](./04_UVM_Phases.md)
- Next: [06 - Formula Sheet](../05_Formula_Sheets/02_UVM_Formula_Sheet.md)
- Related (from CAT-2): [06 - TestBench Architecture](../CAT-2/study_guide/06_testbench_architecture.md) (the non-UVM version)
- Lab Record: The week-8 code is the pure-SV version of this UVM testbench


