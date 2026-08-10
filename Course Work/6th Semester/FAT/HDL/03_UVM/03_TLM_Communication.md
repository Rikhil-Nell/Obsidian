# 03 - TLM Communication

## Learning Objectives

After this section you will understand:
- What TLM (Transaction-Level Modeling) communication is and why UVM uses it
- The difference between **ports** and **exports**
- Blocking `put()` and `get()` methods
- `uvm_tlm_fifo` for independent producer/consumer operation
- **Hierarchical connections** (port-to-port, export-to-export)
- **Analysis ports** -- the broadcast mechanism used by monitors

---

## Why TLM?

> **Conceptual Clarity:** TLM is like a standardized shipping protocol. Instead of each component knowing exactly how the other works internally, they just agree on a standard interface (put, get). This means you can swap any component for another as long as it has the same interface -- the shipping protocol stays the same even if the sender or receiver changes.

TLM provides:
- **Isolation** -- each component is independent of changes in others
- **Reusability** -- any component with the same interface can be swapped in
- **Abstraction** -- components communicate in terms of transactions, not signals

---

## Ports and Exports

UVM TLM uses two fundamental concepts:

| Concept | Symbol | Role |
|---|---|---|
| **Port** | Square (filled) | Defines the API (calls the method) |
| **Export** | Circle | Implements the method (receives the call) |

**Rule:** Data always flows from **producer** to **consumer**, but the direction of the method call depends on whether it's a `put` or `get`:

| Pattern | Who calls? | Who implements? | Data flows |
|---|---|---|---|
| **Put** | Producer calls `put()` | Consumer implements `put()` | Producer --> Consumer |
| **Get** | Consumer calls `get()` | Producer implements `get()` | Producer --> Consumer |

---

## Basic Transaction-Level Communication

### Put Pattern (Producer pushes)

```
 +-----------+          +-----------+
 | Producer  |------>---| Consumer  |
 |  [port]   |  put()   |  (export) |
 +-----------+          +-----------+
```

**Producer code:**
```verilog
class producer extends uvm_component;
    uvm_blocking_put_port #(my_trans) put_port;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        put_port = new("put_port", this);
    endfunction

    virtual task run_phase(uvm_phase phase);
        my_trans t;
        for (int i = 0; i < N; i++) begin
            // Generate t
            put_port.put(t);    // BLOCKS until consumer's put() completes
        end
    endtask
endclass
```

**Consumer code:**
```verilog
class consumer extends uvm_component;
    uvm_blocking_put_imp #(my_trans, consumer) put_export;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        put_export = new("put_export", this);
    endfunction

    task put(my_trans t);
        case (t.kind)
            BURST_READ:  /* do burst read */;
            BURST_WRITE: /* do burst write */;
        endcase
    endtask
endclass
```

**Key:** `uvm_blocking_put_port` means the producer's `put()` call **blocks** until the consumer's `put()` task completes.

### Get Pattern (Consumer pulls)

```
 +-----------+          +-----------+
 | Producer  |---<------| Consumer  |
 |  (export) |  get()   |  [port]   |
 +-----------+          +-----------+
```

Note: the port (square) is now on the **consumer** side, and the export (circle) is on the **producer** side.

**Consumer code:**
```verilog
class get_consumer extends uvm_component;
    uvm_blocking_get_port #(my_trans) get_port;

    function new(string name, uvm_component parent);
        super.new(name, parent);
        get_port = new("get_port", this);
    endfunction

    virtual task run_phase(uvm_phase phase);
        my_trans t;
        for (int i = 0; i < N; i++) begin
            get_port.get(t);    // BLOCKS until producer's get() completes
        end
    endtask
endclass
```

**Producer code:**
```verilog
class get_producer extends uvm_component;
    uvm_blocking_get_imp #(my_trans, get_producer) get_export;

    // ...

    task get(output my_trans t);
        my_trans tmp = new();
        // Assign values to tmp
        t = tmp;
    endtask
endclass
```

> **Conceptual Clarity:** In both `put` and `get`, there is a **single process** running. Control passes from the caller (port) to the implementer (export) and back. Data always flows from producer to consumer.

---

## TLM FIFO: Independent Producer and Consumer

In the basic put/get examples, the producer and consumer share a single thread -- one is active only when the other calls it. What if you want them to operate **independently**?

UVM provides `uvm_tlm_fifo` as a buffer between them.

```
 +-----------+     +----------+     +-----------+
 | Producer  |---->| TLM FIFO |---->| Consumer  |
 |  [port]   | put |          | get |  [port]   |
 +-----------+     +----------+     +-----------+
```

**Behavior:**
- Producer's `put()` returns immediately if FIFO has space; **blocks** if FIFO is full
- Consumer's `get()` returns immediately if FIFO has data; **blocks** if FIFO is empty
- Two consecutive `get()` calls return **different** transactions (consumed from FIFO)
- `peek()` returns a **copy** without removing from FIFO -- two consecutive `peek()` calls return the same transaction

---

## Hierarchical Connections

Real testbenches have deep component hierarchies. TLM connections must cross hierarchical boundaries.

```
+--------- Producer ----------+     +--------- Consumer ----------+
|                              |     |                              |
|  +------+   +------+  +---+ |     | +---+   +------+            |
|  | stim |-->| fifo |->|conv| |---->| |fifo|->| drv  |            |
|  +------+   +------+  +---+ |     | +---+   +------+            |
|                              |     |                              |
+------------------------------+     +------------------------------+
```

Three types of connections:

| Connection | Type | Direction | Purpose |
|---|---|---|---|
| **A, B, D** | Port-to-Export | Horizontal | Normal data flow |
| **C** | Port-to-Port | Downward | Import port from outer to inner component |
| **E** | Export-to-Export | Upward | Export from inner to outer component |

**Rule:** Every transaction-level connection must ultimately resolve to a port connected to an export. Port-to-port and export-to-export connections simply bring connectors to hierarchical boundaries.

---

## Analysis Ports and Exports

Analysis ports are the **broadcast mechanism** used primarily by monitors.

| TLM Type | Symbol | Connection | When to use |
|---|---|---|---|
| `put_port` / `put_export` | Square / Circle | 1-to-1 | Driver-sequencer, producer-consumer |
| `analysis_port` / `analysis_export` | Diamond | 1-to-many | Monitor broadcasting to scoreboards, coverage |

### Key Differences from Regular Ports

1. **Non-blocking** -- `write()` is a **function** (not a task), completes in zero time
2. **One-to-many** -- Can be connected to zero, one, or many exports
3. **No dependency** -- The monitor works regardless of how many (or zero) subscribers are connected
4. **Pointer sharing** -- All subscribers receive a pointer to the **same** transaction object

### Analysis Port Semantics

```verilog
// Monitor declares analysis port
uvm_analysis_port #(my_trans) ap;

// Monitor broadcasts
ap.write(t);    // calls write() on ALL connected exports, in order
```

When `write()` is called:
- The analysis port iterates through its list of connected exports
- Calls each export's `write()` function
- Since `write()` is a function (not a task), it completes in the **same delta cycle**
- If nothing is connected, `write()` simply returns

### Subscriber Pattern

```verilog
class my_subscriber extends uvm_subscriber #(my_trans);
    `uvm_component_utils(my_subscriber)

    function new(string name, uvm_component parent);
        super.new(name, parent);
    endfunction

    function void write(my_trans t);
        my_trans local_copy;
        local_copy = new();
        local_copy.copy(t);    // MUST make local copy

        // Process local_copy...
    endfunction
endclass
```

> **Conceptual Clarity:** Each subscriber gets a **pointer** to the same transaction. If one subscriber modifies it, all others see the modification. That is why each subscriber **must** make a local copy before processing.

### Analysis FIFO

For components that need blocking access to the analysis stream:

```verilog
uvm_tlm_analysis_fifo #(my_trans) analysis_fifo;
```

- Unbounded FIFO -- monitor's `write()` is guaranteed to succeed immediately
- The analysis component can `get()` from the FIFO at its own pace

---

## TLM Port Types Summary

| Port Type | Blocking? | Direction | Typical Use |
|---|---|---|---|
| `uvm_blocking_put_port` | Yes | Producer --> Consumer | Driver sending response |
| `uvm_blocking_get_port` | Yes | Consumer <-- Producer | Sequencer providing transactions |
| `uvm_analysis_port` | No (function) | 1-to-many broadcast | Monitor --> Scoreboard, Coverage |
| `uvm_tlm_fifo` | Buffered | Decoupled producer/consumer | Independent operation |

---

## Common Mistakes

1. **Connecting port to port directly** -- Port-to-port is only for hierarchical pass-through. Data flow requires port-to-export.
2. **Modifying the analysis transaction** -- Subscribers share a pointer. Always `copy()` before modifying.
3. **Forgetting to connect in `connect_phase`** -- TLM connections must be made in `connect_phase`, not `build_phase`.
4. **Using blocking put/get where analysis port is needed** -- Monitors should use `analysis_port.write()` (non-blocking, 1-to-many), not `put_port.put()` (blocking, 1-to-1).

---

## Self-Check Questions

**Q1:** What is the difference between a port and an export?
> A port defines the API and initiates the method call. An export implements the method. The port "calls" and the export "answers."

**Q2:** What happens if the producer calls `put()` and the FIFO is full?
> The `put()` call blocks until space becomes available in the FIFO.

**Q3:** How many exports can an analysis port be connected to?
> Zero or more. The monitor's behavior does not depend on how many (or zero) subscribers are connected.

**Q4:** Why must analysis port subscribers make a local copy of the transaction?
> Because all subscribers receive a pointer to the same transaction object. If one modifies it, all others see the modification, causing data corruption.

**Q5:** What is the difference between `get()` and `peek()`?
> `get()` removes the transaction from the FIFO and returns it. `peek()` returns a copy without removing it. Two consecutive `get()` calls return different transactions; two consecutive `peek()` calls return copies of the same one.

---

## Concept Links

- Previous: [02 - UVM Hierarchy & Components](./02_UVM_Hierarchy.md)
- Next: [04 - UVM Phases](./04_UVM_Phases.md)
- Formula Sheet: [06 - Formula Sheet](../05_Formula_Sheets/02_UVM_Formula_Sheet.md#tlm)
- Related (from CAT-2): [08 - Inter-Process Communication](../CAT-2/study_guide/08_interprocess_communication.md) (mailboxes are similar to TLM FIFOs)



