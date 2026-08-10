# 08 - Inter-Process Communication

## Learning Objectives

After this section you will understand:
- **Semaphores**: key-based mutual exclusion
- **Mailboxes**: FIFO-based message passing (bounded/unbounded, generic/parameterized)
- **Events**: trigger-based synchronization (`->`, `->>`, `@`, `wait`)
- When to use each mechanism

---

## Overview

> **Conceptual Clarity:** When you have multiple processes running in parallel (inside `fork-join`), they often need to coordinate. Imagine two chefs in a kitchen -- they need ways to avoid using the same stove at the same time (semaphore), pass orders between each other (mailbox), and signal "food is ready!" (event).

| Mechanism | Analogy | Use Case |
|---|---|---|
| **Semaphore** | Keys to a room | Mutual exclusion, resource sharing |
| **Mailbox** | Post office box | Passing data between processes |
| **Event** | Doorbell | Signaling that something happened |

---

## Semaphore

> **Conceptual Clarity:** A semaphore is a bucket of keys. Before a process can access a shared resource, it must grab a key from the bucket (`get`). When done, it puts the key back (`put`). If no keys are available, the process waits. This prevents two processes from accessing the same resource simultaneously.

### Semaphore Methods

| Method | Description |
|---|---|
| `new(N)` | Create semaphore with N keys |
| `get(N)` | Take N keys (blocks if not enough) |
| `put(N)` | Return N keys |
| `try_get(N)` | Try to take N keys (non-blocking, returns 0 if not enough) |

### Basic Example

```verilog
module tb;
    semaphore sem;

    initial begin
        sem = new(1);    // Create semaphore with 1 key

        fork
            process_A();
            process_B();
        join
    end

    task process_A();
        sem.get(1);                              // Take the key
        $display("[%0t] A: got key, working...", $time);
        #10;                                      // Simulate work
        $display("[%0t] A: done, returning key", $time);
        sem.put(1);                              // Return the key
    endtask

    task process_B();
        #2;                                       // Slight delay
        $display("[%0t] B: trying to get key...", $time);
        sem.get(1);                              // Blocks until A returns key
        $display("[%0t] B: got key, working...", $time);
        #5;
        sem.put(1);
    endtask
endmodule
```

Output:
```
[0] A: got key, working...
[2] B: trying to get key...
[10] A: done, returning key
[10] B: got key, working...
```

### Hotel Room Example

```verilog
module tb_top;
    semaphore key;

    initial begin
        key = new(1);    // One room key
        fork
            personA();
            personB();
            #25 personA();
        join_none
    end

    task getRoom(bit [1:0] id);
        $display("[%0t] Trying to get room for id[%0d]...", $time, id);
        key.get(1);
        $display("[%0t] Room key retrieved for id[%0d]", $time, id);
    endtask

    task putRoom(bit [1:0] id);
        $display("[%0t] Leaving room id[%0d]...", $time, id);
        key.put(1);
        $display("[%0t] Room key returned id[%0d]", $time, id);
    endtask

    task personA();
        getRoom(1);
        #20 putRoom(1);
    endtask

    task personB();
        #5 getRoom(2);
        #10 putRoom(2);
    endtask
endmodule
```

### `try_get` -- Non-Blocking

```verilog
module tb;
    semaphore sem;

    initial begin
        sem = new(4);    // 4 keys available

        // Take 3 keys
        sem.get(3);
        $display("Took 3 keys");

        // Try to take 3 more (only 1 left) -- non-blocking
        if (sem.try_get(3))
            $display("Got 3 more keys");
        else
            $display("Not enough keys, continuing...");    // This prints

        // Try to take 1 (1 left) -- succeeds
        if (sem.try_get(1))
            $display("Got 1 key");    // This prints
    end
endmodule
```

### Putting Back More Keys

You can `put` more keys than originally created, increasing the semaphore count.

```verilog
sem = new(1);    // Start with 1 key
sem.put(3);      // Now there are 4 keys
// This allows more concurrent access
```

---

## Mailbox

> **Conceptual Clarity:** A mailbox is a FIFO (First In, First Out) queue for passing data between processes. One process puts messages in, another takes them out. Like a post office box -- letters go in the slot and come out in order. The key advantage over a plain queue is that a mailbox has built-in **blocking** behavior: `get()` waits if the mailbox is empty, and `put()` waits if a bounded mailbox is full.

### Types of Mailboxes

| Type | Description |
|---|---|
| **Unbounded** | `new()` or `new(0)` -- unlimited size |
| **Bounded** | `new(N)` -- max N items, `put()` blocks when full |
| **Generic** | Accepts any data type |
| **Parameterized** | `mailbox #(type)` -- only accepts specified type |

### Mailbox Methods

| Method | Description | Blocking? |
|---|---|---|
| `new(N)` | Create mailbox (N=0 for unbounded) | -- |
| `put(item)` | Place item in mailbox | Yes (if bounded & full) |
| `get(var)` | Retrieve item from mailbox | Yes (if empty) |
| `try_put(item)` | Try to place item | No (returns 0 if full) |
| `try_get(var)` | Try to retrieve item | No (returns 0 if empty) |
| `peek(var)` | Copy item without removing | Yes (if empty) |
| `try_peek(var)` | Try to copy without removing | No (returns 0 if empty) |
| `num()` | Return number of items in mailbox | No |

### Basic Mailbox Example

```verilog
module tb;
    mailbox mbx;

    initial begin
        mbx = new();    // Unbounded mailbox

        fork
            producer();
            consumer();
        join
    end

    task producer();
        for (int i = 0; i < 5; i++) begin
            #5;
            mbx.put(i);
            $display("[%0t] PUT: %0d (count=%0d)", $time, i, mbx.num());
        end
    endtask

    task consumer();
        int data;
        for (int i = 0; i < 5; i++) begin
            mbx.get(data);    // Blocks until data available
            $display("[%0t] GOT: %0d (count=%0d)", $time, data, mbx.num());
        end
    endtask
endmodule
```

### Bounded Mailbox

```verilog
module tb;
    mailbox mbx;

    initial begin
        mbx = new(2);    // Bounded: max 2 items

        fork
            begin
                mbx.put("A");
                $display("[%0t] Put A", $time);
                mbx.put("B");
                $display("[%0t] Put B", $time);
                mbx.put("C");    // Blocks! Mailbox is full until consumer gets
                $display("[%0t] Put C", $time);
            end
            begin
                string s;
                #10;
                mbx.get(s);    // Unblocks the producer
                $display("[%0t] Got %s", $time, s);
            end
        join
    end
endmodule
```

### Parameterized Mailbox

```verilog
// Only accepts integers
mailbox #(int) int_mbx = new();
int_mbx.put(42);       // OK
// int_mbx.put("hi");  // COMPILATION ERROR: wrong type
```

### `try_get` and `try_peek`

```verilog
module tb;
    mailbox mbx = new();

    initial begin
        int data;

        // try_get on empty mailbox
        if (mbx.try_get(data))
            $display("Got: %0d", data);
        else
            $display("Mailbox empty!");    // This prints

        mbx.put(99);

        // try_peek: copies without removing
        if (mbx.try_peek(data))
            $display("Peeked: %0d", data);    // data = 99, still in mailbox

        $display("Count: %0d", mbx.num());    // Still 1
    end
endmodule
```

### Mailbox vs Queue

| Feature | Mailbox | Queue |
|---|---|---|
| Blocking `get()` | Yes (waits if empty) | No |
| Blocking `put()` | Yes (if bounded & full) | No |
| Thread safety | Built-in (uses semaphores internally) | Not safe |
| Random access | No (FIFO only) | Yes (any index) |
| Use case | Inter-process communication | Data storage |

> **Conceptual Clarity:** Use a **mailbox** when two parallel processes need to safely exchange data. Use a **queue** when you just need a flexible array with push/pop operations in a single process.

---

## Events

> **Conceptual Clarity:** An event is like a doorbell. One process "rings" it (triggers), and another process that is "listening" (waiting) gets unblocked. Events carry no data -- they just signal "something happened."

### Event Operators

| Operator/Construct | Description |
|---|---|
| `->` | Trigger event (blocking) |
| `->>` | Trigger event (non-blocking) |
| `@(event)` | Wait for event trigger |
| `wait(event.triggered)` | Wait, survives simultaneous trigger |
| `wait_order(e1, e2, e3)` | Wait for events in specific order |

### Basic Event: Trigger and Wait

```verilog
module tb;
    event done;

    initial begin
        fork
            // Process 1: waits for event
            begin
                $display("[%0t] Waiting for event...", $time);
                @(done);    // Blocks until 'done' is triggered
                $display("[%0t] Event received!", $time);
            end

            // Process 2: triggers event
            begin
                #10;
                $display("[%0t] Triggering event", $time);
                -> done;    // Trigger the event
            end
        join
    end
endmodule
```

Output:
```
[0] Waiting for event...
[10] Triggering event
[10] Event received!
```

### `@` vs `wait` -- The Race Condition Problem

> **Conceptual Clarity:** `@(event)` is edge-sensitive -- if the trigger happens at the EXACT same simulation time as the wait, it might miss it (race condition). `wait(event.triggered)` is level-sensitive -- it catches triggers even at the same time.

#### Problem with `@`: Trigger Before Wait

```verilog
module tb;
    event e;

    initial begin
        fork
            begin
                -> e;                      // Trigger FIRST
                $display("[%0t] Triggered", $time);
            end
            begin
                @(e);                      // Wait SECOND
                $display("[%0t] Caught", $time);    // NEVER prints!
            end
        join
    end
endmodule
```

The `@` operator missed the event because the trigger happened before (or at the same time as) the wait.

#### Solution with `wait`

```verilog
module tb;
    event e;

    initial begin
        fork
            begin
                -> e;
                $display("[%0t] Triggered", $time);
            end
            begin
                wait(e.triggered);         // Catches same-time trigger
                $display("[%0t] Caught", $time);    // PRINTS!
            end
        join
    end
endmodule
```

### `wait_order`

Blocks until all specified events trigger in the given left-to-right order. Out-of-order triggers cause a runtime error.

```verilog
module tb;
    event a, b, c;

    initial begin
        fork
            begin
                wait_order(a, b, c);    // Must trigger a, then b, then c
                $display("Events in correct order!");
            end
            begin
                #10 -> a;
                #20 -> b;
                #30 -> c;    // Correct order: a at 10, b at 20, c at 30
            end
        join
    end
endmodule
```

### Non-Blocking Trigger (`->>`)

Triggers the event without blocking the current process. The triggered event is scheduled in the **non-blocking assign region** of the current time step.

```verilog
->> e;    // Non-blocking trigger
```

---

## When to Use What

| Scenario | Use |
|---|---|
| Protect shared resource from concurrent access | Semaphore |
| Pass data between producer and consumer | Mailbox |
| Signal that a specific action happened (no data) | Event |
| Generator → Driver data flow | Mailbox |
| "Test complete" signal | Event |
| Memory access control | Semaphore |

---

## Common Mistakes

1. **`@` missing simultaneous events** - Use `wait(event.triggered)` for safety.
2. **Forgetting `new()` on semaphore/mailbox** - Built-in classes must be constructed.
3. **Bounded mailbox deadlock** - If producer fills the mailbox and consumer never runs, producer blocks forever.
4. **Putting back more keys than taken** - This increases concurrency beyond what was intended.

---

## Self-Check Questions

**Q1:** What happens if you call `sem.get(2)` but only 1 key is available?
> The process blocks until 2 keys are available.

**Q2:** What is the difference between `get()` and `try_get()` on a mailbox?
> `get()` blocks until data is available. `try_get()` returns immediately with 0 if empty.

**Q3:** What is the difference between `@(event)` and `wait(event.triggered)`?
> `@` is edge-sensitive and can miss simultaneous triggers. `wait` is level-sensitive and catches them.

**Q4:** What does `peek()` do on a mailbox?
> Copies the front item without removing it from the mailbox.

**Q5:** Can you `put()` more keys into a semaphore than it was created with?
> Yes. This increases the total number of available keys.

---

## Concept Links

- Previous: [07 - Randomization & Constraints](./07_Randomization_and_Constraints.md)
- Next: [09 - SystemVerilog Assertions](./09_Assertions_SVA.md)
- Related: [06 - TestBench Architecture](./06_Testbench_Architecture.md) (uses mailboxes/events)
- Formula Sheet: [12 - Formula Sheet](../05_Formula_Sheets/01_SV_Formula_Sheet.md#ipc)




