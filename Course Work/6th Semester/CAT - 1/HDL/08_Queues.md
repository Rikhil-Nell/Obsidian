# 7️⃣ Queues in SystemVerilog

> **Learning Goal:** Master queue declaration, operations, and built-in methods

---

## What is a Queue?

A **queue** is a variable-size, ordered collection with automatic memory management.

```systemverilog
int q[$];  // Empty queue declaration ($ means unbounded)
```

Think of it as a **flexible array** that can grow and shrink dynamically.

---

## Declaration and Initialization

```systemverilog
// Empty queue
int empty_q[$];

// Queue with initial values
int q[$] = {1, 2, 3, 4, 5};

// Queue of strings
string names[$] = {"Alice", "Bob", "Charlie"};

// Bounded queue (max 10 elements)
int bounded_q[$:10];
```

---

## Queue Methods

### Adding Elements

| Method | Description | Example |
|--------|-------------|---------|
| `push_back(val)` | Add to end | `q.push_back(10);` |
| `push_front(val)` | Add to front | `q.push_front(0);` |
| `insert(idx, val)` | Insert at index | `q.insert(2, 99);` |

### Removing Elements

| Method | Description | Returns |
|--------|-------------|---------|
| `pop_back()` | Remove from end | Removed element |
| `pop_front()` | Remove from front | Removed element |
| `delete(idx)` | Delete at index | void |
| `delete()` | Delete all | void |

### Querying

| Method | Description | Returns |
|--------|-------------|---------|
| `size()` | Number of elements | int |
| `[$]` | Last element index | int |

---

## Queue Slicing

Access subsets of a queue using slice notation:

```systemverilog
int q[$] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

q[0:2]      // First 3 elements: {0, 1, 2}
q[3:$]      // Element 3 to end: {3, 4, 5, 6, 7, 8, 9}
q[$-2:$]    // Last 3 elements: {7, 8, 9}
q[2:5]      // Elements 2-5: {2, 3, 4, 5}
```

---

## Queue Operations Example

```systemverilog
module queue_operations;
    // Declare the original queues
    int q1[$] = '{3, 5, 7, 9};
    int q2[$] = '{2, 4, 6, 8};
    int q3[$];  // Queue for step (1) and (2)
    int q4[$];  // Queue for step (3)
    
    initial begin
        // Display original queues
        $display("Original q1: %p", q1);
        $display("Original q2: %p", q2);
        
        // Step 1: Create q3 with first two elements of q1 and last two of q2
        q3 = {q1[0:1], q2[$-1:$]};
        $display("\nAfter Step 1 - q3: %p", q3);
        
        // Step 2: Replace second and third elements of q3 with 10 and 11
        if ($size(q3) >= 3) begin
            q3[1] = 10;
            q3[2] = 11;
        end
        $display("After Step 2 - q3: %p", q3);
        
        // Step 3: Create q4 by concatenating all elements of q1 and q2
        q4 = {q1, q2};
        $display("After Step 3 - q4: %p", q4);
        
        // Final Display of all queues
        $display("\nFinal q1: %p", q1);
        $display("Final q2: %p", q2);
        $display("Final q3: %p", q3);
        $display("Final q4: %p", q4);
    end
endmodule
```

---

## String Queue Example

```systemverilog
module queue_operations;
    string q1[$] = {"AB", "BC", "CA", "CB", "BA"};
    string q2[$];
    string q3[$];
    
    initial begin
        // Get first two and last three elements
        q2 = q1[0:1];           // {"AB", "BC"}
        q3 = q1[$-2:$];         // {"CA", "CB", "BA"}
        
        // Add more elements
        q2.push_back("CD");
        q2.push_back("DC");
        
        q3.push_back("EF");
        q3.push_back("FE");
        
        // Display excluding first element
        $display("Queue q1 (excluding first element):");
        foreach (q1[i]) begin
            if (i > 0) $write("%s ", q1[i]);
        end
        $display;
        
        $display("Queue q2 (excluding first element):");
        foreach (q2[i]) begin
            if (i > 0) $write("%s ", q2[i]);
        end
        $display;
        
        $display("Queue q3 (excluding first element):");
        foreach (q3[i]) begin
            if (i > 0) $write("%s ", q3[i]);
        end
        $display;
    end
endmodule
```

---

## Queue with "with" Clause

Use `with` for filtering and finding:

```systemverilog
module dynamic_array_example;
    int dyn_array[];
    int queue_20_80[$];
    int queue_div5[$];
    int queue_odd_indices[$];
    int queue_odd_values[$];
    
    initial begin
        // Initialize with first 15 multiples of 7
        dyn_array = new[15];
        foreach (dyn_array[i]) begin
            dyn_array[i] = (i + 1) * 7;
        end
        
        // Find elements > 20 and < 80
        queue_20_80 = dyn_array.find with (item > 20 && item < 80);
        
        // Find indices where element is divisible by 5
        queue_div5 = dyn_array.find_index with (item % 5 == 0);
        
        // Find indices of odd numbers
        queue_odd_indices = dyn_array.find_index with (item % 2 != 0);
        
        // Find odd values
        queue_odd_values = dyn_array.find with (item % 2 != 0);
        
        // Display results
        $display("Queue containing elements >20 and <80: %p", queue_20_80);
        $display("Queue of indices where elements are divisible by 5: %p", queue_div5);
        $display("Queue of indices of odd numbers: %p", queue_odd_indices);
        $display("Queue of values of odd numbers: %p", queue_odd_values);
    end
endmodule
```

---

## Array/Queue Built-in Methods with "with"

| Method | Description | Example |
|--------|-------------|---------|
| `find` | Return elements matching condition | `q.find with (item > 5)` |
| `find_index` | Return indices matching condition | `q.find_index with (item > 5)` |
| `find_first` | Return first match | `q.find_first with (item > 5)` |
| `find_last` | Return last match | `q.find_last with (item > 5)` |
| `min` | Return minimum element | `q.min` |
| `max` | Return maximum element | `q.max` |
| `unique` | Return unique elements | `q.unique` |
| `sum` | Sum of elements | `q.sum` |
| `sort` | Sort in place | `q.sort` |
| `rsort` | Reverse sort | `q.rsort` |
| `shuffle` | Randomize order | `q.shuffle` |

---

## Key Takeaways

- [ ] `$` in declaration means unbounded: `int q[$]`
- [ ] `push_back/front` and `pop_back/front` for queue ops
- [ ] Slicing: `q[0:2]`, `q[$-2:$]`, `q[3:$]`
- [ ] Use `find` with `with` clause for filtering
- [ ] Queues auto-manage memory (no `new` needed)

---

**Next:** [[09_Structs]] →
