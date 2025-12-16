Checklist:- [[SEM-5 FAT/STS/Checklist]]

| CAT-1                            | CAT-2                                           | FAT                                      |
| -------------------------------- | ----------------------------------------------- | ---------------------------------------- |
| [[#Java Introduction, Features]] | [[#Introduction to Algorithms]]                 | [[#Max product subarray]]                |
| [[#Structure of Java Program]]   | [[#Time & Space complexity]]                    | [[#Maximum sum of hour glass in matrix]] |
| [[#Basic I/O]]                   | [[#Simple Sieve]]                               | [[#Weighted substring]]                  |
| [[#Data Types & Operators]]      | [[#Segmented Sieve Algorithm]]                  | [[#Move hyphen to beginning]]            |
| [[#Decision making]]             | [[#Euler's phi Algorithm]]                      | [[#Manacher's Algorithm]]                |
| [[#Control Structures]]          | [[#Strobogrammatic Number]]                     |                                          |
| [[#Class & Object]]              | [[#Remainder Theorem]]                          |                                          |
| [[#Inheritance]]                 | [[#Binary Palindrome]]                          |                                          |
| [[#Encapsulation, Abstraction]]  | [[#Booth's Algorithm]]                          |                                          |
| [[#Arrays - Fundamentals]]       | [[#Euclid's Algorithm]]                         |                                          |
| [[#Strings - Fundamentals]]      | [[#Karatsuba Algorithm]]                        |                                          |
|                                  | [[#Longest Sequence of 1 after flipping a bit]] |                                          |
|                                  | [[#Swap two nibbles in a byte]]                 |                                          |
|                                  | [[#Block Swap Algorithm]]                       |                                          |

# Java Introduction, Features 


## 1. How Java Works: The "WORA" Architecture

The most critical concept in Java is "Write Once, Run Anywhere" (WORA). Unlike C++ (which compiles to OS-specific machine code) or Python (which is strictly interpreted), Java uses a hybrid approach.

### The Workflow:

1. Source Code (.java): You write human-readable code.

2. Compilation (javac): The Java Compiler acts as a translator. It does not create machine code. It creates Bytecode (.class).

   * Nuance: Bytecode is a set of instructions for a "virtual" machine, not a physical CPU. This is why the .class file can move from Windows to Mac to Linux without changing.

3. Execution (JVM): The Java Virtual Machine reads the bytecode.

4. Interpretation + JIT:

   * Old Java was purely interpreted (slow).

   * Modern Java uses a JIT (Just-In-Time) Compiler. The JVM monitors which parts of code are run frequently ("hot spots") and compiles them into native machine code on the fly for high speed.

---

## 2. The "Holy Trinity": JDK vs JRE vs JVM

This is a guaranteed exam/interview question. You must understand the hierarchy.

| Component | Full Form                  | Role                                                                                            | Analogy                 |
|-----------|----------------------------|-------------------------------------------------------------------------------------------------|-------------------------|
| JVM       | Java Virtual Machine       | The engine that actually runs the code. It is platform-dependent (there is a different JVM for Windows vs. Mac). | The Engine              |
| JRE       | Java Runtime Environment   | JVM + Libraries (rt.jar). It lets you run Java programs but not develop them.                   | The Car (Engine + Body) |
| JDK       | Java Development Kit       | JRE + Dev Tools (Compiler javac, Debugger). Required to write code.                             | The Car Factory         |

---

## 3. Java vs. Python: The Technical Contrast

Since your lecturer emphasized this, here is the technical breakdown beyond just "Python is easier."

| Feature     | Java                                                                 | Python                                                                 |
|-------------|----------------------------------------------------------------------|------------------------------------------------------------------------|
| Typing      | Statically Typed: You must declare types (int x = 10;). Catches errors at compile time. | Dynamically Typed: Types are inferred (x = 10). Errors often catch at runtime. |
| Performance | Faster: Because of JIT compilation and static typing.               | Slower: Purely interpreted (mostly) and dynamic nature adds overhead.  |
| Syntax      | Verbose: Requires boilerplate (classes, public static void main).   | Concise: Uses indentation, no braces, minimal boilerplate.             |
| Memory      | Explicit Management (via GC): Uses a sophisticated Garbage Collector. | Reference Counting + GC: slightly different mechanic, generally higher overhead per object. |

---
## 4. Common Misunderstandings & "Nuances"

These are the trick questions usually asked in STS exams.

* **Misunderstanding 1:** "Java has no pointers."
  * **Reality:** Java has no explicit pointers (you can't do pointer arithmetic like ptr++). However, every object interaction is done via a reference (which is effectively a pointer).

* **Misunderstanding 2:** "Java is Pass-by-Reference."
  * **Reality:** NO. Java is strictly Pass-by-Value.
  * When you pass an object to a function, you are passing a copy of the reference (the address), not the actual object. If you reassign the reference inside the function, the original object outside remains unchanged.

* **Misunderstanding 3:** "Java is 100% Object Oriented."
  * **Reality:** No. Java has Primitive Types (int, double, boolean) for performance reasons. These are not objects. (Pure OOP languages like Smalltalk or Ruby treat everything as an object).

---

## Practice MCQs (Java Intro)

Here are the types of questions likely to appear based on the topics above.

**Q1.** Which of the following best describes the role of the JIT compiler?  
A) It converts Java source code into bytecode.  
B) It converts bytecode into native machine code strictly before execution starts.  
C) It converts bytecode into native machine code on-the-fly during execution for performance.  
D) It acts as a debugger for the JVM.  
>**Answer:** C  
**Reasoning:** javac does A. The JIT works during execution (Just-In-Time) to optimize performance.

**Q2.** Which statement regarding JDK, JRE, and JVM is true?  
A) JRE contains JDK + JVM.  
B) JVM contains JRE + tools.  
C) JDK contains JRE + tools.  
D) JVM runs independent of the Operating System.  
>**Answer:** C  
**Reasoning:** JDK is the superset. It contains the JRE (to run code) and tools (to compile/debug). Note on D: The JVM is actually OS-dependent; the bytecode is OS-independent.

**Q3.** A student claims Java is slower than C++ because it is interpreted. Why is this technically partially incorrect in modern Java?  
A) Java converts to Assembly language before running.  
B) The JIT compiler compiles "hot" bytecode to native machine code, approaching C++ speeds.  
C) Java uses pointers to speed up memory access.  
D) Java ignores memory management to run faster.  
>**Answer:** B  
**Reasoning:** While Java has overhead, the JIT compiler optimizes frequently used code paths to near-native speed.

**Q4.** What is the result of the compilation process in Java?  
A) .exe file  
B) .obj file  
C) .class file (Bytecode)  
D) Binary code  
>**Answer:** C  
**Reasoning:** Java compiles to Bytecode (.class), which the JVM then understands.

**Q5.** Java is described as "Platform Independent." What specifically provides this feature?  
A) The Source Code  
B) The Bytecode  
C) The JDK  
D) The Operating System  
>**Answer:** B  
**Reasoning:** The source code is just text. The Bytecode is the universal language that can travel to any computer. The JVM on that computer then translates it.

# Structure of Java Program  

The structure of a Java program consists of the following sections:

* **Documentation Section:**
    * Consists of comment lines (e.g., `//` or `/* */`).
    * Comments are optional but beneficial for the programmer to explain code logic.
    * Commonly used in corporate programs for maintainability.

* **Package Statement:**
    * A package is a group of classes.
    * Declared using the keyword `package`.
    * It is optional, but if used, it allows declaring many classes within one element.

* **Import Statements:**
    * Used to import built-in and user-defined packages into the Java source file.
    * Declared using the keyword `import`.
    * The `*` character is used to import *all* classes belonging to a specific package.

* **Interface Statement:**
    * Similar to classes but includes a group of method declarations (without implementation).
    * Declared using the keyword `interface`.
    * Used specifically when programmers want to implement **multiple inheritance**, which classes alone cannot do.

* **Class Definition:**
    * The most important segment; a Java program is a collection of classes.
    * A class declaration consists of **Modifiers**, **Class Name**, **Keywords**, and the **Class Body** within curly brackets `{}`.

* **Main Method Class:**
    * This class defines the `main` method.
    * The `main` method is the starting point of the program.
    * While a program may have many classes, only one class defines the main method.

---

### MCQ Solutions

**Question 1:** Java source code is compiled into __________  
* A) Source code  
* B) Byte code  
* C) .obj  
* D) .exe  
> **Answer: B) Byte code**

**Question 2:** Which tool is used to compile Java code?  
* A) java  
* B) javadoc  
* C) jar  
* D) javac  
> **Answer: D) javac**

**Question 3:** Which of the following tools is used to execute Java code?  
* A) java  
* B) javadoc  
* C) jar  
* D) javac  
> **Answer: A) java**

**Question 4:** What is the use of an interpreter?  
* A) They convert bytecode to machine language code  
* B) They read high-level code and execute them  
* C) They are intermediates between JIT and JVM  
* D) It is a synonym for JIT  
> **Answer: A) They convert bytecode to machine language code**  
**(Note: In Java, the interpreter reads the compiled bytecode produced by `javac` and translates it to machine code for execution.)*


# Basic I/O  

This section focuses on how to handle Input and Output operations in Java, primarily using the `Scanner` class and Command Line Arguments.

* **Scanner Class:**
    * **Purpose:** It is the easiest way to read input in a Java program, though not the most efficient. It is used to obtain input of primitive types (int, double, etc.) and strings.
    * **Package:** You must import it using `import java.util.Scanner;`.
    * **Initialization:** To use it, you must create an object of the class, typically passing `System.in` (standard input stream) to the constructor:
        `Scanner sc = new Scanner(System.in);`
    * **Key Methods:**
        * `nextInt()`: Reads an integer.
        * `nextDouble()`: Reads a double.
        * `next()`: Reads the next token/word as a String.
        * `nextLine()`: Reads the entire line as a String.
        * `next().charAt(0)`: Used to read a single character (since there is no direct `nextChar` method).

* **Command Line Arguments:**
    * **Definition:** These are arguments passed to the program at the time of running it.
    * **Storage:** They are captured as Strings into the `String args[]` parameter of the `main` method.
    * **Usage:** There is no restriction on the number of arguments. They are accessed using array indices (e.g., `args[0]`, `args[1]`).

* **BufferedReader (Alternative):**
    * The slides briefly show an alternative method using `BufferedReader` and `InputStreamReader`, which handles input streams directly. This requires more boilerplate code (parsing integers explicitly) compared to Scanner.

---

### MCQ Solution

**Question 1:** Which of these packages contain classes and interfaces used for input & output operations of a program?  
* A) java.lang  
* B) java.io  
* C) None of these  
* D) [Blank]

> **Answer: B) java.io**  
> *Reasoning:* While the `Scanner` class belongs to `java.util`, the standard package dedicated to Input/Output streams (like `BufferedReader`, `InputStreamReader`, and file handling) is `java.io`. The `java.lang` package contains fundamental classes (like `String`, `System`), not IO streams.


# Data Types & Operators  

### 1. Data Types in Java (Added Section)

Since the PPT didn't cover this, here is a quick breakdown of how Java handles data. Java is **strongly typed**, meaning every variable must have a declared type.

**A. Primitive Data Types (The Basics)**  
There are 8 primitive types built into Java. They hold simple values.

  * **Integer types:**
      * `byte` (1 byte): Very small numbers (-128 to 127).
      * `short` (2 bytes): Small numbers.
      * `int` (4 bytes): The standard default for numbers.
      * `long` (8 bytes): Huge numbers (use an 'L' suffix, e.g., `100000L`).
  * **Floating-point types:**
      * `float` (4 bytes): Decimals with 6–7 digits of precision (use an 'f' suffix, e.g., `3.14f`).
      * `double` (8 bytes): Decimals with 15–16 digits of precision (default for decimals).
  * **Characters & Booleans:**
      * `char` (2 bytes): Single character (e.g., 'A') stored in Unicode.
      * `boolean`: Stores only `true` or `false`.

**B. Non-Primitive Data Types (Reference Types)**  
These refer to objects.

  * **String:** A sequence of characters (e.g., "Hello").
  * **Arrays:** A collection of similar variables.
  * **Classes/Interfaces:** User-defined types.

-----

### 2. Operators (Summary of PPT)

Operators are symbols used to perform operations on operands (variables and values).

  * **Unary Operators:**

      * Require only one operand.
      * **Types:** Increment (`++`), Decrement (`--`), Negation (`!`), Bitwise Complement (`~`), and Unary Plus/Minus (`+`, `-`).
      * **Prefix vs Postfix:**
          * `++x` (Prefix): Increments the value *then* uses it.
          * `x++` (Postfix): Uses the value *then* increments it.

  * **Arithmetic Operators:**

      * Used for basic math: `+`, `-`, `*`, `/` (quotient), `%` (remainder).

  * **Relational Operators:**

      * Used to compare two values; returns a boolean (`true`/`false`).
      * Operators: `\==`, `!=`, `>`, `<`, `>=`, `<=`.

  * **Bitwise Operators:**

      * Perform operations on individual bits of integer types.
      * Operators: `&`, `|`, `^`, `~`, `<<`, `>>`, `>>>`.

  * **Logical Operators:**

      * Operate on boolean expressions and support **short-circuiting**.
      * Operators: `&&`, `||`, `!`.

  * **Ternary Operator:**

      * Shorthand for `if-else`.
      * Syntax: `(Condition) ? (Value if True) : (Value if False)`.

  * **Assignment Operators:**

      * Assign values from right to left.  
      * Examples: `\=`, `+=`, `-=`, `*=`, `&=`, `<<=`.

-----

### 3. Solutions to "Predict the Output" Questions

Here are the answers to the coding questions found at the end of your PPT.

#### **Question 1 (Slide 407)**

```java
public class A {
    public static void main(String[] args) {
        int $_ = 5;
    }
}
```

- **Answer:** **1. Nothing**
    
- **Explanation:** The variable name `$_` is valid because Java identifiers can start with `$` or `_`. The code compiles and runs successfully, but since there is no print statement, it outputs nothing.
    

#### **Question 2 (Slide 424)**

```java
System.out.println(10 + 20 + "Face");
System.out.println("Face" + 10 + 20);
```

- **Answer:** `30Face` and `Face1020` (Option 3 in Slide 440)
    
- **Explanation:**
    
    - **Line 1:** `10 + 20` is evaluated first → `30`. Then `30 + "Face"` → `"30Face"`.
        
    - **Line 2:** `"Face" + 10` → `"Face10"`. Then `"Face10" + 20` → `"Face1020"`.
        

#### **Question 3 (Slide 443)**

```java
String s1 = "FACE";
String s2 = "FACE";
System.out.println("s1 == s2 is:" + s1 == s2);
```

- **Answer:** `false` (Option 2 in Slide 464)
    
- **Explanation:**
    
    - `+` has higher precedence than `\==`.
        
    - `"s1 == s2 is:" + s1` creates `"s1 == s2 is:FACE"`.
        
    - Then the program checks: `"s1 == s2 is:FACE" == "FACE"` → **false**.
      

# Decision making  

### Summary: Conditional Statements

This section covers decision-making statements in Java, which allow a program to execute different blocks of code based on specific conditions.

  * **Decision Making Overview:**

      * These statements determine whether a specific segment of code will be executed or skipped.
      * The types of decision-making statements include `if`, `if-else`, `nested if`, `if-else-if ladder`, and `switch-case`.

  * **Simple `if` Statement:**

      * ```
        * Used for one-way decision making.
        ```
      * **Syntax:** `if (condition) { // code }`
      * If the condition is true, the code block executes; otherwise, it is skipped.

  * **`if-else` Statement:**

      * ```
        * Used for two-way decision making.
        ```
      * If the condition is `true`, the `if` block executes. If the condition is `false`, the `else` block executes.

  * **Nested `if`:**

      * An `if-else` statement can be placed inside another `if` or `else` block.
      * This is used when multiple conditions need to be checked in layers (e.g., checking if a number is equal to 10, and *then* checking if it is less than 15).

  * **`if-else-if` Ladder:**

      * Used for multi-way branching.
      * It checks conditions sequentially from top to bottom. As soon as one condition is met, that specific block is executed, and the rest of the ladder is ignored.

  * **`switch` Statement:**

      * ```
        * A multi-way branch statement often used as a cleaner alternative to long `if-else-if` ladders when comparing a single variable against specific values.
        ```
      * **Supported Types:** `byte`, `short`, `int`, `char`, `String`, and enumerations.
      * **Break:** The `break` statement is optional but crucial. If omitted, execution "falls through" to the next case automatically.

-----

### Solutions to "Predict the Output"

Here are the answers to the code challenges presented at the end of the slides.

**Question 1 (Slide 367)**

```java
int x = 10;
if (x) { ... }
```

  * **Answer:** **2. Compile time error**
  * **Reasoning:** In Java, an integer (`int x`) cannot be used as a boolean condition. Unlike C++, Java requires a strict `true` or `false` in the `if` parenthesis (e.g., `if(x != 0)`).

**Question 2 (Slide 403)**

```java
int x = 10;
if (x)
    System.out.println("HELLO");
    System.out.println("WELCOME");
else
    ...
```

  * **Answer:** **4. Compile time error** (Slide 431)
  * **Reasoning:** There are two errors here.
    1. `if(x)` is invalid (int cannot be boolean).  
    2. The `else` is "dangling." Because there are no curly braces `{}` after the `if`, only the first line (`HELLO`) is considered part of the `if`. The second line (`WELCOME`) is treated as a normal statement, which separates the `else` from the `if`, causing a syntax error.

**Question 3 (Slide 434)**

```java
if (true)
    ;
```

  * **Answer:** **1. No Output** (Slide 449)
  * **Reasoning:** The code checks `true` and then executes a semicolon `;` (an empty statement). Nothing happens, and no error occurs.

**Question 4 (Slide 452)**

```java
int x = 10;
switch (x + 1 + 1) { // Evaluates to 12
    case 10: ...
    case 10 + 1 + 1: ... // Evaluates to 12
}
```

  * **Answer:** **2. BYE** (Slide 481)
  * **Reasoning:** The switch expression evaluates to `12`. Java allows constant expressions in cases. `case 10 + 1 + 1` evaluates to `12`. The match is found, and "BYE" is printed.

**Question 5 (Slide 484)**

```java
if (true)
    break;
```

  * **Answer:** **2. Error** (Slide 499)
  * **Reasoning:** The `break` keyword can only be used inside **loops** (`for`, `while`, `do-while`) or **switch** statements. Using `break` inside a standalone `if` statement results in a "break outside switch or loop" compile-time error.


# Control Structures  


### Summary: Control Structures (Looping & Jumping)

This section covers **Looping Statements**, which allow executing a block of code multiple times, and **Jump Statements**, which alter the normal flow of control.

#### 1. Looping Statements

Loops are used to execute a statement or group of statements multiple times.

  * **For Loop:**

      * ```
        * Best for: When the number of iterations is fixed.
        ```
      * **Syntax:** `for (initialization; condition; increment/decrement) { ... }`

  * **While Loop:**

      * ```
        * Best for: When the number of iterations is not fixed.
        ```
      * **Syntax:** Checks the condition *before* executing the block. If the condition is false initially, the loop never runs.

  * **Do-While Loop:**

      * ```
        * Best for: When the loop must execute at least once, regardless of the condition.
        ```
      * **Syntax:** The condition is checked at the end of the loop.

  * **Enhanced For Loop (For-Each):**

      * Designed specifically to iterate through elements of a **collection** or **array** efficiently.
      * **Syntax:** `for (Type var : array) { ... }`

  * **Infinite Loop:**

      * A common mistake where the loop condition never becomes `false`, causing it to run forever (e.g., `while(true)` or `while(x==5)` where `x` never changes).

#### 2. Jump Statements

These statements transfer control to another part of the program.

  * **Break:**
      * Immediately terminates the loop or switch statement. Control resumes at the next statement after the loop.

  * **Continue:**
      * Skips the remaining code in the *current* iteration and jumps immediately to the next iteration.

  * **Return:**
      * Exits from a method, optionally returning a value.

-----

### Solutions to "Predict the Output"

Here are the answers to the questions found at the end of your slides.

**Question 1 (Slide 447)**

```java
int j = 0;
do
    for (int i = 0; i++ < 1 ; )
        System.out.println(i);
while (j++ < 2);
```

  * **Answer:** **1. 111**  
  * **Reasoning:**  
      * The outer `do-while` runs 3 times (`j = 0,1,2`).  
      * The inner loop prints `1` each time.  
      * Total output: `1` repeated three times → `111`.

**Question 2 (Slide 471)**

```java
P: for (int i = 2; i < 7; i++) {
    if (i == 3) continue;
    if (i == 5) break P;
    s = s + i;
}
```

  * **Answer:** **3. 24**  
  * **Reasoning:**  
      * `i=2` → appended → "2"  
      * `i=3` → skipped  
      * `i=4` → appended → "24"  
      * `i=5` → breaks the labelled loop  

**Question 3 (Slide 505)**

```java
for (int i = 0; i < 10; i++)
    int x = 10;
```

  * **Answer:** **2. Compile time error**  
  * **Reasoning:** A variable declaration cannot be the *only* loop statement unless surrounded by `{ }`.

**Question 4 (Slide 524)**

```java
for (System.out.println("HI"); i < 1; i++)
    System.out.println("HELLO");
```

  * **Answer:** **1. HI HELLO**  
  * **Reasoning:**  
      * Initialization prints `"HI"` once.  
      * Condition true → prints `"HELLO"` once.

**Question 5 (Slide 544)**

```java
for (int i = 0;; i++)
    System.out.println("HELLO");
```

  * **Answer:** **3. HELLO(Infinitely)**  
  * **Reasoning:** Empty condition defaults to `true`, creating an infinite loop.


# Class & Object  

	
# Inheritance  


# Encapsualtion, Abstraction  


# Arrays - Fundamentals  


# Strings - Fundamnetals  


# Introduction to Algorithms  


# Time & Space complexity  


# Simple Sieve  


# Segmented Sieve Algorithm  


# Euler's phi Algorithm  


# Strobogrammatic Number  


# Remainder Theorem  


# Binary Palindrome  


# Booth's Algorithm  


# Euclid's Algorithm  


# Karatsuba Algorithm  


# Longest Sequence of 1 after flipping a bit  


# Swap two nibbles in a byte  


# Block Swap Algorithm  


# Max product subarray  


# Maximum sum of hour glass in matrix  


# Weighted substring  


# Move hyphen to beginning  


# Manacher's Algorithm