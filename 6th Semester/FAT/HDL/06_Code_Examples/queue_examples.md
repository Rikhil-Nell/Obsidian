``` verilog
// ============================================
// QUEUE EXAMPLES - SystemVerilog
// Extracted from course materials
// ============================================

// Example 1: String Queue Operations
// -----------------------------------
module queue_operations_strings;
    string q1[$] = {"AB", "BC", "CA", "CB", "BA"};
    string q2[$];
    string q3[$];
    
    initial begin
        // Get first two and last three elements
        q2 = q1[0:1];           // First two: {"AB", "BC"}
        q3 = q1[$-2:$];         // Last three: {"CA", "CB", "BA"}
        
        // Add more elements to q2
        q2.push_back("CD");
        q2.push_back("DC");
        
        // Add more elements to q3
        q3.push_back("EF");
        q3.push_back("FE");
        
        // Display elements of q1 excluding the first element
        $display("Queue q1 (excluding first element):");
        foreach (q1[i]) begin
            if (i > 0) $write("%s ", q1[i]);
        end
        $display;
        
        // Display elements of q2 excluding the first element
        $display("Queue q2 (excluding first element):");
        foreach (q2[i]) begin
            if (i > 0) $write("%s ", q2[i]);
        end
        $display;
        
        // Display elements of q3 excluding the first element
        $display("Queue q3 (excluding first element):");
        foreach (q3[i]) begin
            if (i > 0) $write("%s ", q3[i]);
        end
        $display;
    end
endmodule


// Example 2: Integer Queue Operations
// ------------------------------------
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
        
        // Step 1: Create q3 with first two elements of q1 and last two elements of q2
        q3 = {q1[0:1], q2[$-1:$]};
        $display("\nAfter Step 1 - q3: %p", q3);
        
        // Step 2: Replace the second and third elements of q3 with 10 and 11
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


// Example 3: Queue with "with" Clause (from Dynamic Array)
// ---------------------------------------------------------
module dynamic_array_example1;
    int dyn_array[];              // Dynamic array
    int queue_20_80[$];           // Queue for elements >20 and <80
    int queue_div5[$];            // Queue for indices of elements divisible by 5
    int queue_odd_indices[$];     // Queue for indices of odd elements
    int queue_odd_values[$];      // Queue for odd element values
    
    initial begin
        // Allocate memory and initialize the dynamic array with first 15 multiples of 7
        dyn_array = new[15];
        foreach (dyn_array[i]) begin
            dyn_array[i] = (i + 1) * 7;
        end
        
        // Process the elements based on conditions using "with" clause
        queue_20_80 = dyn_array.find with (item > 20 && item < 80);
        queue_div5 = dyn_array.find_index with (item % 5 == 0);
        queue_odd_indices = dyn_array.find_index with (item % 2 != 0);
        queue_odd_values = dyn_array.find with (item % 2 != 0);
        
        // Display results
        $display("Queue containing elements >20 and <80: %p", queue_20_80);
        $display("Queue of indices where elements are divisible by 5: %p", queue_div5);
        $display("Queue of indices of odd numbers: %p", queue_odd_indices);
        $display("Queue of values of odd numbers: %p", queue_odd_values);
    end
endmodule
``` 
