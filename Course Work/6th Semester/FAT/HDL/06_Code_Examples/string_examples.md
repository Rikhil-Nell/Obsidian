
``` verilog
// ============================================
// STRING EXAMPLES - SystemVerilog
// Extracted from course materials
// ============================================

// Example 1: Vowel Count Comparison
// ----------------------------------
// Count vowels in two strings and compare

module vowel_count_example;
    // Strings to analyze
    string str1 = "We are studying in VIT university";
    string str2 = "I am in SENSE department";
    int vowel_count_str1 = 0;
    int vowel_count_str2 = 0;
    int difference;
    
    // Function to check if a character is a vowel
    function int is_vowel(input byte ch);
        string vowels = "aeiouAEIOU";
        foreach (vowels[i]) begin
            if (vowels[i] == ch)
                return 1; // Return true if the character is a vowel
        end
        return 0; // Return false otherwise
    endfunction
    
    initial begin
        // Count vowels in str1
        foreach (str1[i]) begin
            if (is_vowel(str1[i]))
                vowel_count_str1++;
        end
        
        // Count vowels in str2
        foreach (str2[i]) begin
            if (is_vowel(str2[i]))
                vowel_count_str2++;
        end
        
        // Compare vowel counts
        if (vowel_count_str1 > vowel_count_str2) begin
            difference = vowel_count_str1 - vowel_count_str2;
            $display("str1 has more vowels (%0d) than str2 (%0d). Difference: %0d", 
                      vowel_count_str1, vowel_count_str2, difference);
        end else if (vowel_count_str2 > vowel_count_str1) begin
            difference = vowel_count_str2 - vowel_count_str1;
            $display("str2 has more vowels (%0d) than str1 (%0d). Difference: %0d", 
                      vowel_count_str2, vowel_count_str1, difference);
        end else begin
            $display("Both strings have the same number of vowels (%0d).", vowel_count_str1);
        end
    end
endmodule


// Example 2: Extract Vowels and Consonants
// -----------------------------------------
// Process string to separate vowels and consonants

module string_processor;
    string str1 = "We are appearing for CAT examination";
    string str2 = "";  // Will contain vowels
    string str3 = "";  // Will contain consonants
    int vowel_count = 0, consonant_count = 0, space_count = 0;
    byte c;
    
    function bit is_vowel(byte c);
        return (c == "a" || c == "e" || c == "i" || c == "o" || c == "u" ||
                c == "A" || c == "E" || c == "I" || c == "O" || c == "U");
    endfunction
    
    initial begin
        int i;
        for (i = 0; i < str1.len(); i++) begin
            c = str1[i];
            if (c == " ") begin
                space_count++;
            end else if (is_vowel(c)) begin
                str2 = {str2, string'(c)};
                vowel_count++;
            end else begin
                str3 = {str3, string'(c)};
                consonant_count++;
            end
        end
        
        $display("Original String: %s", str1);
        $display("Vowel String (str2): %s", str2);
        $display("Consonant String (str3): %s", str3);
        
        if (vowel_count > consonant_count) begin
            $display("str2 (vowels) has more characters than str3 (consonants) by %0d", 
                     vowel_count - consonant_count);
        end else if (consonant_count > vowel_count) begin
            $display("str3 (consonants) has more characters than str2 (vowels) by %0d", 
                     consonant_count - vowel_count);
        end else begin
            $display("str2 (vowels) and str3 (consonants) have the same number of characters");
        end
        
        $display("Total number of spaces: %0d", space_count);
    end
endmodule


// Example 3: Alternative Vowel Count Implementation
// --------------------------------------------------
// Using string.find method (alternative approach)

module vowel_count_example2;
    string str1 = "We are studying in VIT university";
    string str2 = "This is HDL verification";
    int vowel_count_str1 = 0;
    int vowel_count_str2 = 0;
    int difference;
    string vowels = "aeiouAEIOU";
    int i;
    
    initial begin
        // Count vowels in str1 using foreach
        foreach (str1[i]) begin
            foreach (vowels[j]) begin
                if (vowels[j] == str1[i]) begin
                    vowel_count_str1++;
                    break;
                end
            end
        end
        
        // Count vowels in str2
        foreach (str2[i]) begin
            foreach (vowels[j]) begin
                if (vowels[j] == str2[i]) begin
                    vowel_count_str2++;
                    break;
                end
            end
        end
        
        // Compare vowel counts
        if (vowel_count_str1 > vowel_count_str2) begin
            difference = vowel_count_str1 - vowel_count_str2;
            $display("str1 has more vowels (%0d) than str2 (%0d). Difference: %0d", 
                      vowel_count_str1, vowel_count_str2, difference);
        end else if (vowel_count_str2 > vowel_count_str1) begin
            difference = vowel_count_str2 - vowel_count_str1;
            $display("str2 has more vowels (%0d) than str1 (%0d). Difference: %0d", 
                      vowel_count_str2, vowel_count_str1, difference);
        end else begin
            $display("Both strings have the same number of vowels (%0d).", vowel_count_str1);
        end
    end
endmodule
``` 
