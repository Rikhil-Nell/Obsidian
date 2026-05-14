``` verilog
// ============================================
// ARRAY EXAMPLES - SystemVerilog
// Extracted from course materials
// ============================================

// Example 1: 2D Array Filter to Dynamic Array
// --------------------------------------------
// Write a SV program to create a 2D array with the following rows: 
// {14, 10, 15, 3}, {12, 30, 16, 9}, and {7, 9, 15, 10}.
// Extract elements divisible by 3 but not divisible by 2 
// and store them in a dynamic array.

module array_filter;
    // Define a 2D array with given rows
    int arr[3][4] = '{'{14, 10, 15, 3}, '{12, 30, 16, 9}, '{7, 9, 15, 10}};
    
    // Declare a dynamic array to store filtered elements
    int filtered[];
    int i, j;
    
    initial begin
        // Display original 2D Array
        $display("Original 2D Array:");
        for (i = 0; i < 3; i++) begin
            for (j = 0; j < 4; j++) begin
                $write("%0d ", arr[i][j]);
            end
            $display;
        end
        
        // Extract elements meeting the condition:
        // divisible by 3 but NOT divisible by 2
        foreach (arr[i, j]) begin
            if (arr[i][j] % 3 == 0 && arr[i][j] % 2 != 0) begin
                filtered = new[filtered.size+1](filtered);
                filtered[filtered.size-1] = arr[i][j];  // Append to dynamic array
            end
        end
        
        // Display filtered elements
        $display("Filtered Elements (Divisible by 3 but not by 2):");
        foreach (filtered[i]) begin
            $write("%0d ", filtered[i]);
        end
        $display;
    end
endmodule


// Example 2: Dynamic Array with Queue Operations
// -----------------------------------------------
// Create dynamic array with first 15 multiples of 7
// and perform various filtering operations

module dynamic_array_example1;
    int dyn_array[];              // Dynamic array
    int queue_20_80[$];           // Queue for elements >20 and <80
    int queue_div5[$];            // Queue for indices of elements divisible by 5
    int queue_odd_indices[$];     // Queue for indices of odd elements
    int queue_odd_values[$];      // Queue for odd element values
    
    initial begin
        // Allocate memory and initialize with first 15 multiples of 7
        dyn_array = new[15];
        foreach (dyn_array[i]) begin
            dyn_array[i] = (i + 1) * 7;
        end
        
        // Display the array
        $display("Dynamic Array (first 15 multiples of 7):");
        foreach (dyn_array[i]) begin
            $write("%0d ", dyn_array[i]);
        end
        $display;
        
        // (a) Store elements >20 and <80 into a queue
        queue_20_80 = dyn_array.find with (item > 20 && item < 80);
        $display("\nQueue containing elements >20 and <80: %p", queue_20_80);
        
        // (b) Store indices of elements divisible by 5
        queue_div5 = dyn_array.find_index with (item % 5 == 0);
        $display("Queue of indices where elements are divisible by 5: %p", queue_div5);
        
        // (c) Store indices and values of odd numbers
        queue_odd_indices = dyn_array.find_index with (item % 2 != 0);
        queue_odd_values = dyn_array.find with (item % 2 != 0);
        $display("Queue of indices of odd numbers: %p", queue_odd_indices);
        $display("Queue of values of odd numbers: %p", queue_odd_values);
    end
endmodule


// Example 3: Packed vs Unpacked Arrays
// -------------------------------------

// Packed Array Example
module packed_array_example;
    // Packed array: dimensions BEFORE the name
    logic [3:0][7:0] packed_word;  // 4 bytes = 32 bits total, contiguous
    
    initial begin
        packed_word = 32'hDEADBEEF;
        
        $display("Packed word as single value: %h", packed_word);
        $display("Byte 0: %h", packed_word[0]);
        $display("Byte 1: %h", packed_word[1]);
        $display("Byte 2: %h", packed_word[2]);
        $display("Byte 3: %h", packed_word[3]);
    end
endmodule

// Unpacked Array Example
module unpacked_array_example;
    // Unpacked array: dimensions AFTER the name
    logic [7:0] memory [0:3];  // 4 separate 8-bit values
    
    initial begin
        memory[0] = 8'hAA;
        memory[1] = 8'hBB;
        memory[2] = 8'hCC;
        memory[3] = 8'hDD;
        
        $display("Memory contents:");
        for (int i = 0; i < 4; i++) begin
            $display("memory[%0d] = %h", i, memory[i]);
        end
    end
endmodule
```