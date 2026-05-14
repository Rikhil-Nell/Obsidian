# 06 - UVM Formula & Syntax Sheet

## Quick Reference Guide

This sheet contains the essential UVM syntax, macros, and patterns you need for the exam.

---

## 1. UVM Factory

### Registration Macros

| Macro | For | Has Phases? | Has Hierarchy? |
|---|---|---|---|
| `` `uvm_object_utils(class) `` | sequence, seq_item, transaction, config | No | No |
| `` `uvm_component_utils(class) `` | driver, monitor, agent, env, test, scoreboard | Yes | Yes |

### Object Creation

```verilog
// CORRECT: factory creation
my_class obj = my_class::type_id::create("obj_name", this);  // component
my_class obj = my_class::type_id::create("obj_name");         // object

// WRONG: bypasses factory
my_class obj = new("obj_name");  // loses override capability
```

### Factory Override

```verilog
// Type override (global: all instances)
set_type_override_by_type(base::get_type(), derived::get_type());

// Instance override (specific instance only)
set_inst_override_by_type("path.to.instance", base::get_type(), derived::get_type());
```

---

## 2. UVM Hierarchy

### Component Base Classes

| Base Class | Purpose |
|---|---|
| `uvm_test` | Top-level test |
| `uvm_env` | Container for agents and scoreboards |
| `uvm_agent` | Groups sequencer + driver + monitor |
| `uvm_driver #(REQ)` | Drives DUT signals |
| `uvm_monitor` | Observes DUT signals |
| `uvm_scoreboard` | Checks DUT output |
| `uvm_sequencer #(REQ)` | Routes transactions |
| `uvm_subscriber #(T)` | Analysis port subscriber |

### Object Base Classes

| Base Class | Purpose |
|---|---|
| `uvm_sequence_item` | Single transaction |
| `uvm_sequence #(REQ)` | Collection of transactions |
| `uvm_object` | Generic UVM object |

### Constructor Patterns

```verilog
// Component constructor (2 args)
function new(string name, uvm_component parent);
    super.new(name, parent);
endfunction

// Object constructor (1 arg)
function new(string name = "default_name");
    super.new(name);
endfunction
```

---

## 3. UVM Phases

### Phase Summary

| Phase | Type | Order | What goes here |
|---|---|---|---|
| `build_phase` | function | Top-down | `type_id::create()` |
| `connect_phase` | function | Bottom-up | `port.connect(export)` |
| `end_of_elaboration` | function | Bottom-up | Final adjustments |
| `start_of_simulation` | function | Bottom-up | Display banners |
| `run_phase` | **task** | Parallel | Stimulus, driving, monitoring |
| `extract` | function | Bottom-up | Get scoreboard data |
| `check` | function | Bottom-up | Verify correctness |
| `report` | function | Bottom-up | Print results |
| `final` | function | Bottom-up | Cleanup |

### Phase Templates

```verilog
function void build_phase(uvm_phase phase);
    super.build_phase(phase);
    // create components
endfunction

function void connect_phase(uvm_phase phase);
    super.connect_phase(phase);
    // connect TLM ports
endfunction

task run_phase(uvm_phase phase);
    phase.raise_objection(this);
    // simulation activity
    phase.drop_objection(this);
endtask
```

---

## 4. TLM Communication

### Port Types

| Type | Symbol | Blocking? | Cardinality |
|---|---|---|---|
| `uvm_blocking_put_port #(T)` | Square | Yes | 1:1 |
| `uvm_blocking_put_imp #(T, IMP)` | Circle | Yes | 1:1 |
| `uvm_blocking_get_port #(T)` | Square | Yes | 1:1 |
| `uvm_blocking_get_imp #(T, IMP)` | Circle | Yes | 1:1 |
| `uvm_analysis_port #(T)` | Diamond | No (function) | 1:N |
| `uvm_analysis_imp #(T, IMP)` | -- | No | 1:1 |

### Connection Syntax

```verilog
// Driver <-> Sequencer
driver.seq_item_port.connect(sequencer.seq_item_export);

// Monitor -> Scoreboard (via analysis port)
monitor.ap.connect(scoreboard.ap);
```

### Hierarchical Connection Types

| Connection | Type | Direction | Purpose |
|---|---|---|---|
| Port-to-Export | Normal | Horizontal | Data transfer |
| Port-to-Port | Passthrough | Outer-to-inner | Import port downward |
| Export-to-Export | Passthrough | Inner-to-outer | Export port upward |

---

## 5. Sequence-Driver Handshake

### Sequence Side

```verilog
task body();
    my_item item = my_item::type_id::create("item");
    start_item(item);        // blocks until driver calls get_next_item
    item.randomize();
    finish_item(item);       // blocks until driver calls item_done
endtask
```

### Driver Side

```verilog
task run_phase(uvm_phase phase);
    forever begin
        seq_item_port.get_next_item(req);   // blocks until sequence has item
        // drive DUT
        seq_item_port.item_done();          // unblocks finish_item
    end
endtask
```

### Driver Methods

| Method | Blocking? | Purpose |
|---|---|---|
| `get_next_item()` | Yes | Get next transaction from sequencer |
| `try_next_item()` | No | Non-blocking get (returns null if none) |
| `item_done()` | No | Complete handshake |
| `put()` | No | Send response to sequencer |

---

## 6. Config Database

```verilog
// SET (top-level module)
uvm_config_db #(TYPE)::set(CONTEXT, INST_PATH, FIELD_NAME, VALUE);

// GET (component)
if (!uvm_config_db #(TYPE)::get(this, "", FIELD_NAME, local_var))
    `uvm_fatal("ID", "Config get failed")
```

### Common Pattern: Virtual Interface

```verilog
// In top module:
uvm_config_db #(virtual my_if)::set(null, "*", "vif", vif_instance);

// In component:
uvm_config_db #(virtual my_if)::get(this, "", "vif", vif);
```

---

## 7. Reporting Macros

| Macro | Severity | Continues? |
|---|---|---|
| `` `uvm_info(ID, MSG, VERBOSITY) `` | Info | Yes |
| `` `uvm_warning(ID, MSG) `` | Warning | Yes |
| `` `uvm_error(ID, MSG) `` | Error | Yes |
| `` `uvm_fatal(ID, MSG) `` | Fatal | **No** (stops simulation) |

### Verbosity Levels

`UVM_NONE` < `UVM_LOW` < `UVM_MEDIUM` < `UVM_HIGH` < `UVM_FULL` < `UVM_DEBUG`

---

## 8. Agent Active/Passive

```verilog
// In agent's build_phase:
if (get_is_active() == UVM_ACTIVE) begin
    drv = my_driver::type_id::create("drv", this);
    sqr = uvm_sequencer #(my_item)::type_id::create("sqr", this);
end
mon = my_monitor::type_id::create("mon", this);  // always created
```

| Mode | `get_is_active()` | Sequencer | Driver | Monitor |
|---|---|---|---|---|
| Active | `UVM_ACTIVE` | Created | Created | Created |
| Passive | `UVM_PASSIVE` | -- | -- | Created |

---

## 9. Top-Level Module Pattern

```verilog
module tb_top;
    my_if vif();

    my_dut dut (.a(vif.a), .b(vif.b), ...);

    initial begin
        uvm_config_db #(virtual my_if)::set(null, "*", "vif", vif);
        run_test("my_test");    // starts UVM phase engine
    end
endmodule
```

---

## 10. Quick Decision Table

| Question | Answer |
|---|---|
| Object or Component? | Does it have phases + hierarchy? Component. Otherwise Object. |
| `uvm_object_utils` or `uvm_component_utils`? | Object --> `uvm_object_utils`. Component --> `uvm_component_utils`. |
| `new()` or `type_id::create()`? | Always `type_id::create()` for factory support. |
| Port or Analysis Port? | 1-to-1: regular port. 1-to-many broadcast: analysis port. |
| `build_phase` or `connect_phase`? | Creating: build. Connecting: connect. |
| `run_phase` or function phase? | Needs simulation time? run_phase (task). Otherwise function phase. |
