---
Tags: "#CNotes"
---
A C program is a set of instructions similar to a recipe book.

There are primarily 3 types of instructions:
- Type declaration Instructions
- Arithmetic Instructions 
- Control Instructions

## Type Declaration Instructions:

**'int a;'
'float b;'**

### Other variations:

int i  = 10; int j = i; int a = 2;
int p = a + j - i;

float b =  a + 3; 
float a = 1.1;

>The above code will throw an error as we are trying to use a variable before declaring it

## Arithmetic Instructions:

- Arithmetic instructions involve operands and operators
- Operands are the values which are being operated upon 
- Operators are the arithmetic symbols and functions being used

>There is no operator to perform exponentiation in C language.
>You must '#include <math.h>;' and then use the function **"pow(x,y)"**, which yields x^y.

### Type Conversion

int (Operator) int = int

int (Operator) float = float

float (Operator) float = float

### Operator Precedence in C

In C programming simple mathematical rules like BODMAS, do not apply.
Instead it follow the following operator priority:

| Priority | Operator                          |
| -------- | --------------------------------- |
| 1st      | Multiplication, Division, Modular |
| 2nd      | Addition, Subtraction             |
| 3rd      | Is equal to                       |

Operators of higher priority are evaluated first in the absence of parenthesis.

**Operator Associativity:** When operators of equal priority are present in an expression, the tie is taken care of by associativity.

	x * y / z => (x * y) / z
	x / y * z => (x / y) * z

Multiplication and Division follow left to right associativity.

**Operator Precedence for when the given operators are used together with** [[003 Conditional Instructions#Relational Operators]] **and** [[003 Conditional Instructions#Logical Operators]] **are used together:** [[003 Conditional Instructions#Operators Precedence (advanced)]] 

Unique operators useful while using loops: [[b#More Operators]]

## Control Instructions:

Determines the flow of control in a program. There are 4 types of control instructions in C:
- Sequence Control Instruction
- Decision Control Instruction
- Loop Control Instruction
- Case Control Instruction


