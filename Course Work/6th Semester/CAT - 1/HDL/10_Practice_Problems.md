# 🎯 Practice Problems

> **Assignment Questions for HDL Verification**

These questions are from your course assignment. Practice these to prepare for your exam!

---

## Question Categories

| Category | Questions |
|----------|-----------|
| Verification Concepts | Q1 |
| Dynamic Arrays | Q2, Q6 |
| Strings | Q3, Q7 |
| Enums | Q4, Q8 |
| Queues | Q5, Q10 |
| Arrays & Structs | Q5b |
| Associative Arrays | Q9 |

---

## Q1: Verification Concepts

**(a)** Briefly discuss the role of **driver** while configuring a layered testbench to verify any DUT. Clearly discuss about input and output flow for "Driver" component.

**(b)** Which features of SystemVerilog make it preferable over Verilog HDL when verification of a DUT is the main objective? Briefly discuss them.

> 💡 **Hint:** Review [[01_Introduction]] for verification concepts

---

## Q2: 2D Array to Dynamic Array

Write a SV program to create a 2D array with the following rows:
- `{14, 10, 15, 3}`
- `{12, 30, 16, 9}`
- `{7, 9, 15, 10}`

Extract elements from this 2D array that are **divisible by 3 but not divisible by 2** and store them in a dynamic array. Display the contents of both arrays.

> 💡 **Hint:** Review [[07_Arrays]] for dynamic array usage

**Expected filtered elements:** 15, 3, 15, 9, 9, 15

---

## Q3: String Processing - Vowels and Consonants

Write a SV program to process the string:
```
str1 = "We are appearing for CAT examination"
```

Create two new strings:
- `str2`: containing all the **vowels**
- `str3`: containing all the **consonants** (ignoring spaces)

Compare the sizes and display:
- Which string has more characters
- The difference in size
- Total number of spaces in str1

> 💡 **Hint:** Review [[05_Strings]] for string methods

---

## Q4: Enum with Fruits

Write a SV program to assign numbers `{3, 6, 4, 5, 2}` to five fruits `{Apple, Orange, Guava, Grapes, Mango}` using an enumerated data type.

Requirements:
1. Use `typedef` to give the enumerated data type a new name: `fruits`
2. Create a variable and assign `Grapes` as its current value
3. Display values of **first and last** members of enumeration
4. Display the **name** of the current value (i.e., "Grapes")

> 💡 **Hint:** Review [[06_Enums]] for enum methods like `.first()`, `.last()`, `.name()`

---

## Q5: Queue Operations

**(a)** Write a SV program to create a queue `q1` of string elements:
```
{"AB", "BC", "CA", "CB", "BA"}
```

Requirements:
1. Declare two additional queues `q2` and `q3`
2. Store first two elements of q1 into q2
3. Store last three elements of q1 into q3
4. Add `{"CD", "DC"}` to q2
5. Add `{"EF", "FE"}` to q3
6. Display all three queues, **excluding the first element of each**

**(b)** With appropriate examples, discuss the difference between packed and unpacked arrays. How is the size of a dynamic array increased during runtime?

> 💡 **Hint:** Review [[08_Queues]] for slicing and [[07_Arrays]] for packed/unpacked

---

## Q6: Dynamic Array with "with" Clause

Write a program to create a dynamic array containing the **first 15 multiples of 7** as its elements. Using the "with" clause and built-in functions, perform:

| Task | Description |
|------|-------------|
| (a) | Store elements >20 and <80 into a queue and display |
| (b) | Store and display indices of elements divisible by 5 |
| (c) | Store indices AND values of all odd numbers into two separate queues |

> 💡 **Hint:** Use `.find with (condition)` and `.find_index with (condition)`

**Array elements:** 7, 14, 21, 28, 35, 42, 49, 56, 63, 70, 77, 84, 91, 98, 105

---

## Q7: Vowel Count Comparison

Given two strings:
```
str1 = "We are students of VIT university"
str2 = "This is HDL verification"
```

Write a program to:
1. Count number of vowels in both strings
2. Check whether str1 or str2 has more vowels
3. Display the difference between their vowel counts

> 💡 **Hint:** Review [[05_Strings]] for string iteration

---

## Q8: Colors Enum with Methods

Write a SV program to declare an enum named "Colors" with:
```
red=0, green, blue=4, yellow, white=10, black
```

Requirements:
1. Use a **for loop** with `first` and `next` methods to display name and value of each color
2. Check if a specific color (e.g., "yellow") exists in the enum
3. Display message indicating whether it exists or not

> 💡 **Hint:** Use `.first`, `.next`, `.num`, and `.name` methods

---

## Q9: Associative Array for Student Scores

Design a SV program that keeps track of quiz scores for students.

Requirements:
1. Use an **associative array** where:
   - Key = student's name (string)
   - Value = quiz score (int)
2. Traverse the array using a loop
3. Determine and display names of students with **highest and lowest scores**

> 💡 **Hint:** Associative arrays use `foreach(arr[key])` syntax

Example data:
```systemverilog
int scores[string];
scores["Alice"] = 95;
scores["Bob"] = 73;
scores["Charlie"] = 88;
```

---

## Q10: Advanced Queue Operations

Given queues:
```
qa = {3, 6, 9, 12}
qb = {2, 4, 6, 8}
```

Perform these operations:
1. Create `qc` using first 3 elements of qa and last element of qb
2. Insert 50 at the beginning of qc
3. Delete the 3rd element of qc
4. Create `qd` by merging qa and qb in **reverse, alternating elements**
5. Display all queues after each step

> 💡 **Hint:** Use `push_front`, `delete(index)`, and queue slicing

---

## Exam Tips

> [!TIP]
> **Common patterns to memorize:**
> - `foreach (arr[i])` for iteration
> - `arr.find with (item > 5)` for filtering
> - `queue[$]` for last element
> - `queue[0:2]` for slicing
> - `.first()`, `.last()`, `.next()` for enums

> [!WARNING]
> **Watch out for:**
> - Packed vs unpacked array syntax
> - `=` vs `<=` in always blocks
> - Queue `$` meaning unbounded

---

## 📖 Reference Materials

For comprehensive examples, refer to these original files:
- `Assignment-=1.pdf` - Complete assignment with marking scheme
- `System Verilog-2-310.pdf` - Detailed syntax reference

---

← **Previous:** [[02_Code_Coverage]] | **Back to:** [[00_Study_Guide]] →
