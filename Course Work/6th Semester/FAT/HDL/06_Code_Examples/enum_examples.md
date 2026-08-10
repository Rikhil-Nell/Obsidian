``` verilog
// ============================================
// ENUM EXAMPLES - SystemVerilog
// Extracted from course materials
// ============================================

// Example 1: Fruit Enum with Custom Values
// -----------------------------------------
module enum_fruits;
    typedef enum int {
        Apple  = 3,
        Orange = 6,
        Guava  = 4,
        Grapes = 5,
        Mango  = 2
    } fruits;
    
    fruits current_fruit = Grapes;
    
    initial begin
        // Display the values of the first and last members
        $display("First fruit value (Apple): %0d", current_fruit.first());
        $display("Last fruit value (Mango): %0d", current_fruit.last());
        
        // Display the name of the current value
        case (current_fruit)
            Apple:  $display("Current fruit: Apple");
            Orange: $display("Current fruit: Orange");
            Guava:  $display("Current fruit: Guava");
            Grapes: $display("Current fruit: Grapes");
            Mango:  $display("Current fruit: Mango");
        endcase
    end
endmodule


// Example 2: Colors Enum with Iteration
// --------------------------------------
module enum_datatype;
    enum {red = 0, green, blue = 4, yellow, white = 10, black} colors;
    int count = 0;
    int value_to_check = 4;
    string color_to_check = "yellow";
    string color_found = "No color found";
    bit found = 0;
    
    initial begin
        // Display all members of the Colors enum
        $display("Displaying all members of the Colors enum:");
        colors = colors.first;
        
        for (int i = 0; i < colors.num; i++) begin
            $display("Color: %0s \t Value: %0d", colors.name, colors);
            colors = colors.next;
            count++;
        end
        
        // Task 3: Display the total number of colors
        $display("\nTotal number of colors in the enum: %0d %0d", count, colors.num);
        
        // Task 4: Retrieve the name of the color corresponding to a specific value (e.g., 4)
        colors = colors.first;
        for (int i = 0; i < colors.num; i++) begin
            if (colors == value_to_check) begin
                color_found = colors.name;
                break;
            end
            colors = colors.next;
        end
        $display("\nThe color corresponding to the value %0d is: %s", value_to_check, color_found);
        
        // Task 5: Check if a specific color (e.g., yellow) exists in the enum
        colors = colors.first;
        for (int i = 0; i < colors.num; i++) begin
            if (colors.name == color_to_check) begin
                found = 1;
                break;
            end
            colors = colors.next;
        end
        
        if (found)
            $display("\nThe color %s exists in the Colors enum.", color_to_check);
        else
            $display("\nThe color %s does not exist in the Colors enum.", color_to_check);
    end
endmodule


// Example 3: Simple Fruit Enum with typedef
// ------------------------------------------
module fruit_enum;
    // Define enum with specific values using typedef
    typedef enum int {
        Apple  = 3,
        Orange = 6,
        Guava  = 4,
        Grapes = 5,
        Mango  = 2
    } fruits_t;
    
    // Declare and initialize a variable with Grapes
    fruits_t current_fruit = Grapes;
    
    initial begin
        // Display first and last enum values
        $display("First enum: %s = %0d", fruits_t'(0), Apple);
        $display("Last enum: %s = %0d", fruits_t'(4), Mango);
        
        // Display current value
        $display("Current value: %s = %0d", current_fruit.name(), current_fruit);
    end
endmodule
```
