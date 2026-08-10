# 4️⃣ Strings in SystemVerilog

> **Learning Goal:** Master string declaration, operations, and common methods

---

## What is a String?

The `string` data type stores and manipulates **textual data**.

### Key Features
- **Dynamic array of characters** - size can change at runtime
- **Null-terminated** (`\0`) internally
- Rich set of **built-in methods**

---

## Declaration and Initialization

```systemverilog
// Declaration only
string s1;

// Declaration with initialization
string s1 = "Hello, SystemVerilog!";

// Empty string
string empty = "";
```

---

## String Operations

### 1. Concatenation

Use `{ }` to join strings:

```systemverilog
string first = "Hello";
string second = "World";
string result;

result = {first, " ", second};  // "Hello World"
```

### 2. Comparison

Use standard operators:

```systemverilog
string a = "apple";
string b = "banana";

if (a == b)    // Equal
if (a != b)    // Not equal
if (a < b)     // Lexicographic comparison
if (a > b)     // Lexicographic comparison
```

### 3. Accessing Characters

Use index (like array):

```systemverilog
string s = "Hello";
byte ch = s[0];      // 'H' (returns byte/ASCII value)
```

### 4. Getting Length

```systemverilog
string s = "Hello";
int length = s.len();  // Returns 5
```

---

## String Methods

| Method | Description | Example |
|--------|-------------|---------|
| `.len()` | Returns length | `s.len()` → 5 |
| `.getc(i)` | Get char at index i | `s.getc(0)` → 'H' |
| `.putc(i, c)` | Put char c at index i | `s.putc(0, 'h')` |
| `.toupper()` | Convert to uppercase | `"hello".toupper()` → "HELLO" |
| `.tolower()` | Convert to lowercase | `"HELLO".tolower()` → "hello" |
| `.substr(i, j)` | Substring from i to j | `"Hello".substr(1,3)` → "ell" |
| `.atoi()` | String to integer | `"123".atoi()` → 123 |
| `.itoa(n)` | Integer to string | Assigns int as string |

---

## Practical Example: Count Vowels

```systemverilog
module vowel_count_example;
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
                return 1;  // It's a vowel
        end
        return 0;  // Not a vowel
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
        
        // Compare and display
        if (vowel_count_str1 > vowel_count_str2) begin
            difference = vowel_count_str1 - vowel_count_str2;
            $display("str1 has more vowels (%0d) than str2 (%0d). Difference: %0d", 
                      vowel_count_str1, vowel_count_str2, difference);
        end else if (vowel_count_str2 > vowel_count_str1) begin
            difference = vowel_count_str2 - vowel_count_str1;
            $display("str2 has more vowels (%0d) than str1 (%0d). Difference: %0d", 
                      vowel_count_str2, vowel_count_str1, difference);
        end else begin
            $display("Both strings have same number of vowels (%0d)", vowel_count_str1);
        end
    end
endmodule
```

---

## Dynamic String Resizing

Strings automatically resize as you add data:

```systemverilog
initial begin
    string s = "Hello";
    $display("Original: %s, Length: %0d", s, s.len());
    
    s = {s, " World"};  // Concatenate
    $display("After concat: %s, Length: %0d", s, s.len());
    
    s = "Hi";  // Shrink
    $display("After shrink: %s, Length: %0d", s, s.len());
end
```

**Output:**
```
Original: Hello, Length: 5
After concat: Hello World, Length: 11
After shrink: Hi, Length: 2
```

---

## Integer to String Conversion

```systemverilog
initial begin
    int num = 12345;
    string s;
    
    s.itoa(num);  // Convert int to string
    $display("String: %s", s);
end
```

**Output:** `String: 12345`

---

## String Processing Example

Extract vowels and consonants from a string:

```systemverilog
module string_processor;
    string str1 = "We are appearing for CAT examination";
    string str2 = "";  // vowels
    string str3 = "";  // consonants
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
        
        if (vowel_count > consonant_count)
            $display("str2 (vowels) has more characters by %0d", 
                     vowel_count - consonant_count);
        else if (consonant_count > vowel_count)
            $display("str3 (consonants) has more characters by %0d", 
                     consonant_count - vowel_count);
        
        $display("Total spaces: %0d", space_count);
    end
endmodule
```

---

## Key Takeaways

- [ ] Strings are **dynamic** - size changes automatically
- [ ] Use `{ }` for **concatenation**
- [ ] Use `foreach` to **iterate** through characters
- [ ] `.len()` returns length, `.getc(i)` gets character
- [ ] Characters are `byte` (ASCII values)

---

**Next:** [Enums](./05_Enums.md) →

