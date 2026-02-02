### ✅ **1. Variable Definitions and Access**

**Context/Question:** Example Script on variables

```bash
#!/bin/bash
# Defining variables
name="krishna"
country="INDIA"

# Accessing variables
echo "Name: $name"
echo "Country: $country"

# Defining a read-only variable
pi=3.14159
readonly pi
echo "Value of pi: $pi"

# Attempting to change the value of a read-only variable
# pi=3.14  # This will cause an error

# Unsetting a variable
temp="Temporary value"
echo "Before unsetting: $temp"
unset temp
echo "After unsetting: $temp"
```

---

### ✅ **2. Variable Types Demonstration**

**Context/Question:** Example Script on variables

```bash
#!/bin/bash

# Global Variables
echo "Global Variables"
echo "HOME: $HOME"
echo "PATH: $PATH"
echo "SHELL: $SHELL"
echo "USERNAME: $USER"
echo "PWD: $PWD"
echo ""
# Local Variables
echo "Local Variables"
local_var="I am a local variable"
echo "local_var: $local_var"
echo ""

# Special Variables
echo "Special Variables"
echo "Script name: $0"
echo "First argument: $1"
echo "Second argument: $2"
echo "All arguments: $@"
echo "Number of arguments: $#"
echo ""
```

---

### ✅ **3. Input Handling**

**Context/Question:** Example Script on variables

```bash
#!/bin/bash

echo "Enter name : "
read name1 name2 name3
echo "Names : $name1, $name2, $name3"

read -p "username : " user_var
read -sp "password : " pass_var
echo "username : $user_var"
echo "password : $pass_var": 

echo "Enter name1 : "
read
echo "Name : $REPLY"
```

---

### ✅ **4. Arrays - Index Assignment**

**Context/Question:** Example Script on arrays

```bash
#!/bin/bash

# Method 1: Individual assignments to indices
fruits[0]=apple
fruits[1]=banana
fruits[2]=orange

echo "First fruit: ${fruits[0]}"
echo "Second fruit: ${fruits[1]}"
echo "Third fruit: ${fruits[2]}"
echo "All fruits: ${fruits[@]}"
echo "All fruits: ${fruits[*]}"
echo "Fruits from index 1: ${fruits[@]:1}"
echo "Fruits from index 0: ${fruits[@]:0:2}"
```

---

### ✅ **5. Arrays - Parentheses Assignment**

**Context/Question:** Example Script on arrays

```bash
#!/bin/bash

# Method 2: Using parentheses
fruits=(apple banana orange)

echo "First fruit: ${fruits[0]}"
echo "Second fruit: ${fruits[1]}"
echo "Third fruit: ${fruits[2]}"
echo "All fruits: ${fruits[@]}"
echo "All fruits: ${fruits[*]}"
echo "Fruits from index 1: ${fruits[@]:1}"
echo "Fruits from index 0: ${fruits[@]:0:2}"
```

---

### ✅ **6. Arrays - Explicit Indices**

**Context/Question:** Example Script on arrays

```bash
#!/bin/bash

# Method 3: Using explicit indices
fruits=([1]=apple [3]=banana [0]=orange)

echo "First fruit: ${fruits[0]}"
echo "Second fruit: ${fruits[1]}"
echo "Third fruit: ${fruits[2]}"
echo "All fruits: ${fruits[@]}"
echo "All fruits: ${fruits[*]}"
echo "Fruits from index 1: ${fruits[@]:1}"
echo "Fruits from index 0: ${fruits[@]:0:2}"
```

---

### ✅ **7. Arithmetic Operations**

**Context/Question:** Performing Arithmetic in Shell

```bash
#!/bin/bash
var1=10
var2=20
let result=var1+var2
echo "Result: $result"
```

```bash
#!/bin/bash
var1=10
var2=20
result=$(expr $var1 + $var2)
echo "Result: $result"
```

```bash
#!/bin/bash
var1=10
var2=20
result=$((var1 + var2))
echo "Result: $result"
```

```bash
#!/bin/bash
var1=10.5
var2=2.3
result=$(echo "$var1 + $var2" | bc)
echo "Result: $result"
```

---

### ✅ **8. Relational and Logical Operators**

```bash
#!/bin/bash
# Arithmetic operations
a=10
b=3
add=$((a + b))
sub=$((a - b))
mul=$((a * b))
div=$((a / b))
mod=$((a % b))
exp=$((a ** b))

echo "Arithmetic Operations:"
echo "Addition (a + b) = $add"
echo "Subtraction (a - b) = $sub"
echo "Multiplication (a * b) = $mul"
echo "Division (a / b) = $div"
echo "Modulus (a % b) = $mod"
echo "Exponentiation (a ** b) = $exp"
```

```bash
#!/bin/bash
# Relational operations
a=10 
b=3
rel_eq=$((a == b))
rel_ne=$((a != b))
rel_gt=$((a > b))
rel_lt=$((a < b))
rel_ge=$((a >= b))
rel_le=$((a <= b))

echo "Relational Operations (0 for false, 1 for true):"
echo "a == b: $rel_eq"
echo "a != b: $rel_ne"
echo "a > b: $rel_gt"
echo "a < b: $rel_lt"
echo "a >= b: $rel_ge"
echo "a <= b: $rel_le"
```

```bash
#!/bin/bash
# Boolean operations
a=10
b=3
bool_and=$((a && b))
bool_or=$((a || b))
bool_not=$((!a))

echo "Boolean Operations:"
echo "AND: $bool_and"
echo "OR: $bool_or"
echo "NOT: $bool_not"
```

```bash
#!/bin/bash
# Bitwise operations
a=10
b=3
bit_and=$((a & b))
bit_or=$((a | b))
bit_not=$((~a))

echo "Bitwise Operations"
echo "bit_and: $bit_and"
echo "bit_or: $bit_or"
echo "bit_not: $bit_not"
```

---

### ✅ **9. Conditional Statements**

**Context/Question:** Example Script on if / if-else / elif

```bash
#!/bin/bash
a=5

if [ $a -gt 0 ]; then
echo "a is greater than 0"
fi
```

```bash
#!/bin/bash
a=5

if [ $a -gt 0 ]; then
echo "a is greater than 0"
else
echo "a is less than or equal to 0"
fi
```

```bash
#!/bin/bash
a=5

if [ $a -gt 10 ]; then
echo "a is greater than 10"
elif [ $a -gt 0 ]; then
echo "a is greater than 0 but less than or equal to 10"
else
echo "a is less than or equal to 0"
fi
```

---

### ✅ **10. Count Lines with Word**

**Context/Question:** Count lines in a file containing a specific word (whole word)

```bash
#!/bin/bash
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 filename word"
  exit 1
fi

filename=$1
word=$2

if [ ! -f "$filename" ]; then
  echo "Error: File '$filename' not found."
  exit 1
fi

line_count=$(grep -w "$word" "$filename" | wc -l)
echo "The word '$word' appears as a whole word in $line_count lines in the file '$filename'."
```

---

### ✅ **11. Find and Replace Whole Word**

**Context/Question:** Replace a whole word in a file and save output separately

```bash
#!/bin/bash
if [ "$#" -ne 4 ]; then
  echo "Usage: $0 input_file output_file search_word replace_word"
  exit 1
fi

input_file=$1
output_file=$2
search_word=$3
replace_word=$4

if [ ! -f "$input_file" ]; then
  echo "Error: Input file '$input_file' does not exist."
  exit 1
fi

sed -r "s/\b$search_word\b/$replace_word/g" "$input_file" > "$output_file"

if [ $? -eq 0 ]; then
  echo "Successfully replaced all occurrences of '$search_word' with '$replace_word' in '$input_file'."
  echo "Modified content saved to '$output_file'."
else
  echo "Error: Failed to replace words."
  exit 1
fi
```

---

### ✅ **12. `case` Statement – Menu Selection**

**Context/Question:** Simple Menu-Based Case Statement

```bash
#!/bin/bash

echo "Choose an option: "
echo "1) Start"
echo "2) Stop"
echo "3) Restart"

read choice

case $choice in
1)
  echo "Starting the service..."
  ;;
2)
  echo "Stopping the service..."
  ;;
3)
  echo "Restarting the service..."
  ;;
*)
  echo "Invalid option. Please choose 1, 2, or 3."
  ;;
esac
```

---

### ✅ **13. `case` Statement – File Type Checker**

**Context/Question:** Detect file type based on extension

```bash
#!/bin/bash

echo "Enter a filename: "
read filename

case "$filename" in
*.txt)
  echo "This is a text file."
  ;;
*.jpg | *.png)
  echo "This is an image file."
  ;;
*.sh)
  echo "This is a shell script."
  ;;
*)
  echo "Unknown file type."
  ;;
esac
```

---

### ✅ **14. `case` Statement – Character Type Checker**

**Context/Question:** Determine if character is lowercase, digit, etc.

```bash
#!/bin/bash

read char

case $char in
[a-z])
  echo "You entered a lowercase letter."
  ;;
[A-Z])
  echo "You entered an uppercase letter."
  ;;
[0-9])
  echo "You entered a digit."
  ;;
?)
  echo "You entered a special character."
  ;;
*)
  echo "You entered more than one character."
  ;;
esac
```

---

### ✅ **15. Loop – `while` Loop with Counter**

**Context/Question:** Basic while loop

```bash
#!/bin/bash

counter=1

while [ $counter -le 5 ]
do
  echo "Counter: $counter"
  counter=$((counter + 1))
done
```

---

### ✅ **16. Loop – `until` Loop with Counter**

**Context/Question:** Basic until loop

```bash
#!/bin/bash

counter=1

until [ $counter -gt 5 ]
do
  echo "Counter: $counter"
  counter=$((counter + 1))
done
```

---

### ✅ **17. Loop – `while` with Break and Continue**

**Context/Question:** Demonstrating `break` and `continue`

```bash
#!/bin/bash

counter=1

while [ $counter -le 10 ]
do
  if [ $counter -eq 5 ]; then
    echo "Skipping number 5"
    counter=$((counter + 1))
    continue
  fi

  if [ $counter -eq 8 ]; then
    echo "Breaking at number 8"
    break
  fi

  echo "Counter: $counter"
  counter=$((counter + 1))
done
```

---

### ✅ **18. Loop – `until` with Break and Continue**

**Context/Question:** Same as above, but using `until`

```bash
#!/bin/bash

counter=1

until [ $counter -gt 10 ]
do
  if [ $counter -eq 5 ]; then
    echo "Skipping number 5"
    counter=$((counter + 1))
    continue
  fi

  if [ $counter -eq 8 ]; then
    echo "Breaking at number 8"
    break
  fi

  echo "Counter: $counter"
  counter=$((counter + 1))
done
```

---

### ✅ **19. Functions – Multiplication Function**

**Context/Question:** Simple multiplication function

```bash
#!/bin/bash

multiply() {
  local result=$(( $1 * $2 ))
  echo $result
}

# Call the function and capture its output
result=$(multiply 4 5)
echo "Product: $result"
```

---

### ✅ **20. Functions – Calculator Menu Using Functions**

**Context/Question:** Modular menu-driven calculator

```bash
#!/bin/bash

show_menu() {
  echo "1. Add"
  echo "2. Subtract"
  echo "3. Multiply"
  echo "4. Divide"
  echo "5. Exit"
}

add() {
  echo "Enter two numbers: "
  read a b
  echo "Result: $(( a + b ))"
}

subtract() {
  echo "Enter two numbers: "
  read a b
  echo "Result: $(( a - b ))"
}

multiply() {
  echo "Enter two numbers: "
  read a b
  echo "Result: $(( a * b ))"
}

divide() {
  echo "Enter two numbers: "
  read a b
  if [ $b -ne 0 ]; then
    echo "Result: $(( a / b ))"
  else
    echo "Division by zero is not allowed."
  fi
}

while true
do
  show_menu
  read -p "Choose an option: " choice
  case $choice in
    1) add ;;
    2) subtract ;;
    3) multiply ;;
    4) divide ;;
    5) echo "Exiting..."; break ;;
    *) echo "Invalid option. Please try again." ;;
  esac
done
```

---

### ✅ **21. Pattern – Right-Angled Triangle**

**Context/Question:** Print increasing stars in triangle shape

```bash
#!/bin/bash

n=5

for ((i=1; i<=n; i++))
do
  for ((j=1; j<=i; j++))
  do
    echo -n "* "
  done
  echo
done
```

---

### ✅ **22. Pattern – Inverted Right-Angled Triangle**

**Context/Question:** Print decreasing stars

```bash
#!/bin/bash

n=5

for ((i=n; i>=1; i--))
do
  for ((j=1; j<=i; j++))
  do
    echo -n "* "
  done
  echo
done
```

---

### ✅ **23. Pattern – Center-Aligned Pyramid**

**Context/Question:** Print pyramid shape with stars

```bash
#!/bin/bash

n=5

for ((i=1; i<=n; i++))
do
  for ((j=i; j<n; j++))
  do
    echo -n "  "
  done
  for ((j=1; j<=(2*i-1); j++))
  do
    echo -n "* "
  done
  echo
done
```

---

### ✅ **24. Pattern – Diamond Shape**

**Context/Question:** Combine two pyramids

```bash
#!/bin/bash
n=5

# Upper part
for ((i=1; i<=n; i++))
do
  for ((j=i; j<n; j++))
  do
    echo -n "  "
  done
  for ((j=1; j<=(2*i-1); j++))
  do
    echo -n "* "
  done
  echo
done

# Lower part
for ((i=n-1; i>=1; i--))
do
  for ((j=n; j>i; j--))
  do
    echo -n "  "
  done
  for ((j=1; j<=(2*i-1); j++))
  do
    echo -n "* "
  done
  echo
done
```

---

### ✅ **25. Pattern – Number Pyramid**

**Context/Question:** Pyramid of numbers, increasing then decreasing

```bash
#!/bin/bash

n=5

for ((i=1; i<=n; i++))
do
  for ((j=i; j<n; j++))
  do
    echo -n "  "
  done
  for ((j=1; j<=i; j++))
  do
    echo -n "$j "
  done
  for ((j=i-1; j>=1; j--))
  do
    echo -n "$j "
  done
  echo
done
```

---

### ✅ **26. Pattern – Fibonacci Triangle**

**Context/Question:** Display Fibonacci numbers row-wise

```bash
#!/bin/bash

n=5
a=0
b=1

for ((i=1; i<=n; i++))
do
  for ((j=1; j<=i; j++))
  do
    echo -n "$a "
    fn=$((a + b))
    a=$b
    b=$fn
  done
  echo
done
```

---

### ✅ **27. Prime Number Checker**

**Context/Question:** Check if number is prime

```bash
#!/bin/bash

is_prime() {
  num=$1
  if [ $num -le 1 ]; then
    echo "Not a Prime Number"
    return
  fi

  for ((i=2; i*i<=num; i++))
  do
    if [ $((num % i)) -eq 0 ]; then
      echo "Not a Prime Number"
      return
    fi
  done

  echo "Prime Number"
}

read -p "Enter a number: " n
is_prime $n
```

---

### ✅ **28. Fibonacci Series Using Recursion**

**Context/Question:** Recursive Fibonacci implementation

```bash
#!/bin/bash

fibonacci() {
  if [ $1 -le 0 ]; then
    echo 0
  elif [ $1 -eq 1 ]; then
    echo 1
  else
    echo $(( $(fibonacci $(( $1 - 1 ))) + $(fibonacci $(( $1 - 2 ))) ))
  fi
}

read -p "Enter the number of terms: " n

for ((i=0; i<n; i++))
do
  echo -n "$(fibonacci $i) "
done
echo
```

---
