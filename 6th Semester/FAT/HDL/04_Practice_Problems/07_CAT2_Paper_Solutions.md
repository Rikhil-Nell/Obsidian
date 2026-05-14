# CAT-2 Paper Solutions (Winter Semester 2025-26)

## Question 1: `join_any` vs `join` Timeline Analysis
**Modify the given SystemVerilog program by replacing join_any with join in the outer fork-join block. Analyze the simulation output and explain how the execution behaviour changes when using join instead of join_any, particularly in terms of task completion and synchronization. (10 M)**

### Original vs Modified Behavior
The original code uses a nested `fork`. 
- **Inner Fork** has three threads (Worker A step 1 at 40ns, step 2 at 60ns, step 3 at 15+70=85ns) bound by an inner `join_any`. Thus, the inner fork finishes at **t=40** when step 1 completes, but the other threads continue running in the background.
- **Outer Fork** has three threads:
  1. The Thread holding the inner fork (finishes at **t=40**).
  2. Worker B (finishes at **t=45** = 20+25).
  3. Worker C (finishes at **t=60** = 10+50).

If the **outer block** uses `join_any`, the Controller unblocks at **t=40** when Thread 1 finishes.
If modified to **`join`**, the Controller will wait for the **longest thread to finish**, which is Thread 3 at **t=60**.

### Modified Simulation Output (Using `join`)
```
[0] Controller: Parallel execution started
[10] Worker_C started
[15] Worker_A step3 started
[20] Worker_B started
[40] Worker_A step1 done
[40] Worker_A: One internal task completed
[45] Worker_B finished
[60] Worker_A step2 done
[60] Worker_C finished
[60] Controller: First worker finished execution  <-- Note: Executes at 60 because of join
[85] Worker_A step3 finished
```

### Explanation of Execution Behavior
When changing from `join_any` to `join`, the synchronization semantics change fundamentally. `join` dictates a strict barrier: **all spawned threads must completely finish** before the parent process can resume execution. In our timeline, Worker C takes the longest absolute time (60 units). Therefore, the `$display` for the Controller finishing execution gets pushed from `t=40` (the time of the fastest thread completion, Thread 1) to `t=60`.
*Note: Even with `join`, the background threads spawned by the inner `join_any` (Worker A step 2 and step 3) are "orphaned" processes to the outer fork, so they continue running independently and finish at 60 and 85 respectively without blocking the main outer `join`.*

---

## Question 2: Semaphore Execution Timeline
**Determine the sequence of displayed outputs with simulation time and explain how the semaphore affects the order of resource acquisition and release. (10 M)**

### Code Analysis
The semaphore is initialized with **2 keys** (`sem = new(2);`). All workers request `1` key.
- **Worker 1 (A):** Starts at `t=5`, holds for 15.
- **Worker 2 (B):** Starts at `t=10`, holds for 20.
- **Worker 3 (C):** Starts at `t=12`, holds for 10.
- **Worker 4 (D):** Starts at `t=15`, holds for 12.

### Step-by-Step Timeline Trace
- `t=0`:  Simulation starts. `fork...join_none` spawns all tasks in parallel.
- `t=5`:  Worker 1 requests a key. 2 keys available. **Acquires.** (1 key left).
- `t=10`: Worker 2 requests a key. 1 key available. **Acquires.** (0 keys left).
- `t=12`: Worker 3 requests a key. 0 keys available. **Worker 3 Blocks.**
- `t=15`: Worker 4 requests a key. 0 keys available. **Worker 4 Blocks.**
- `t=20`: Worker 1 finishes (5+15) and **Releases.** (1 key left).
  - Worker 3 has been waiting the longest (FIFO queue), so it **Acquires.** (0 keys left).
- `t=30`: Worker 2 finishes (10+20) and **Releases.** (1 key left).
  - Worker 4 has been waiting, so it **Acquires.** (0 keys left).
- `t=30`: Worker 3 finishes (20+10) and **Releases.** (1 key left).
- `t=42`: Worker 4 finishes (30+12) and **Releases.** (2 keys left).
- `t=60`: Main initial block finishes.

### Sequence of Displayed Outputs
```
[5] Worker 1 requesting resource
[5] Worker 1 acquired resource
[10] Worker 2 requesting resource
[10] Worker 2 acquired resource
[12] Worker 3 requesting resource
[15] Worker 4 requesting resource
[20] Worker 1 releasing resource
[20] Worker 3 acquired resource
[30] Worker 2 releasing resource
[30] Worker 3 releasing resource
[30] Worker 4 acquired resource
[42] Worker 4 releasing resource
simulation got over at [60]
```

### Explanation of Semaphore Effect
The semaphore acts as a traffic controller, restricting parallel execution. Even though all 4 tasks are launched almost simultaneously, the `new(2)` constraint ensures a maximum of 2 tasks are ever in the "acquired" critical section at the same time. The semaphore enforces a FIFO blocking queue, ensuring fair access for Worker 3 and Worker 4 once resources are returned.

---

## Question 3: Vehicle Class
**Define a class vehicle with three data members. Define a parameterized constructor. Define a method display. Perform the same task using a default constructor and another method assign_data. (10 M)**

```verilog
class vehicle;
  string model;
  string color;
  int mileage;
  
  // Parameterized constructor with default arguments
  function new(string m="Default", string c="None", int mi=0);
    model = m;
    color = c;
    mileage = mi;
  endfunction
  
  // Method to display data
  function void display();
    $display("Model: %s, Color: %s, Mileage: %0d", model, color, mileage);
  endfunction
  
  // Method to assign data post-construction
  function void assign_data(string m, string c, int mi);
    model = m;
    color = c;
    mileage = mi;
  endfunction
endclass

module tb;
  initial begin
    vehicle v1, v2;
    vehicle v3, v4;
    
    $display("--- Using Parameterized Constructor ---");
    v1 = new("Swift", "White", 22);
    v2 = new("Creta", "Black", 18);
    v1.display();
    v2.display();
    
    $display("\n--- Using Default Constructor and assign_data() ---");
    // Passing no arguments falls back to the default values in new()
    v3 = new(); 
    v4 = new();
    
    v3.assign_data("Swift", "White", 22);
    v4.assign_data("Creta", "Black", 18);
    
    v3.display();
    v4.display();
  end
endmodule
```

---

## Question 4(a): Function vs Task
**Write a program to find whether the subtraction result of two integers is odd or not by defining a function and a task. (5 M)**

```verilog
module odd_even_subtraction;
  
  // Function (Returns a value, executes in zero time)
  function bit is_odd_func(int a, int b);
    return ((a - b) % 2) != 0;
  endfunction
  
  // Task (Uses output argument)
  task is_odd_task(input int a, input int b, output bit res);
    res = ((a - b) % 2) != 0;
  endtask
  
  initial begin
    int num1 = 15;
    int num2 = 6;
    bit result;
    
    // Using Function
    result = is_odd_func(num1, num2);
    $display("[Function] %0d - %0d is %s", num1, num2, result ? "ODD" : "EVEN");
    
    // Using Task
    is_odd_task(num1, num2, result);
    $display("[Task]     %0d - %0d is %s", num1, num2, result ? "ODD" : "EVEN");
  end
endmodule
```

---

## Question 4(b): `priority if` vs `unique if`
**Predict the output. Discuss what change in simulator behaviour or result may occur if priority if is replaced with unique if. (5 M)**

### Predicted Output
Given `sel = 3'b110;`:
- `sel[2]` is 1 (True)
- `sel[1]` is 1 (True)
- `sel[0]` is 0 (False)

`priority if` evaluates conditions in sequential order until it finds the first true condition. Since `sel[2]` is true, it executes Block A and immediately exits the `if` block.

```
Block A executed
value of sel = 110
```

### Effect of Changing to `unique if`
A `unique if` explicitly asserts to the simulator that **one and only one** condition in the chain will evaluate to true. 
If replaced, the output **result remains exactly the same** ("Block A executed"), because it still evaluates sequentially and picks the first match.
However, the **simulator behavior changes**: It will evaluate the overlap, detect that *both* `sel[2]` and `sel[1]` are true, and throw a **Run-Time Warning/Error** (Unique-If Violation) to alert the engineer of overlapping conditions.

---

## Question 5: Mailbox Blocking Behaviour
**Predict the displayed simulation output with timestamps and explain the result based on mailbox blocking behaviour and task timing. (10 M)**

### Code Analysis
- Mailbox Capacity: `mb = new(2);` (Can hold a maximum of 2 elements).
- **Generator**: Runs every 10ns, attempting to `put` an element.
- **Processor**: Runs every 25ns, attempting to `get` an element.

### Timeline Trace
- `t=10`: Gen attempts and puts `1`. (MB holds [1]).
- `t=20`: Gen attempts and puts `2`. (MB holds [1, 2] - **FULL**).
- `t=25`: Proc gets `1`. (MB holds [2]).
- `t=30`: Gen attempts and puts `3`. (MB holds [2, 3] - **FULL**).
- `t=40`: Gen attempts to put `4`. **Mailbox is full! Generator Blocks.**
- `t=50`: Proc gets `2`. (MB holds [3]).
  - Generator immediately **Unblocks** and puts `4`. (MB holds [3, 4] - **FULL**).
- `t=60`: Gen attempts to put `5`. **Mailbox is full! Generator Blocks.**
- `t=75`: Proc gets `3`. (MB holds [4]).
  - Generator immediately **Unblocks** and puts `5`. (MB holds [4, 5] - **FULL**). Generator loop finishes.
- `t=100`: Proc gets `4`. (MB holds [5]).
- `t=125`: Proc gets `5`. (MB empty). Processor loop finishes.

### Displayed Output
```
[10] Generator: Attempting to send 1
[10] Generator: Sent 1
[20] Generator: Attempting to send 2
[20] Generator: Sent 2
[25] Processor: Received 1
[30] Generator: Attempting to send 3
[30] Generator: Sent 3
[40] Generator: Attempting to send 4
[50] Processor: Received 2
[50] Generator: Sent 4
[60] Generator: Attempting to send 5
[75] Processor: Received 3
[75] Generator: Sent 5
[100] Processor: Received 4
[125] Processor: Received 5
```

### Explanation of Result
Because the Generator generates data much faster (every 10ns) than the Processor can consume it (every 25ns), and the mailbox has a strict bound of 2, the mailbox quickly fills up. At `t=40` and `t=60`, the `mb.put(i)` call **blocks** the Generator thread completely. It cannot proceed until `t=50` and `t=75` when the Processor makes a `mb.get()` call, freeing up a slot in the mailbox. This perfectly demonstrates how bounded mailboxes provide automatic flow control and synchronization between mismatched-speed parallel threads.
