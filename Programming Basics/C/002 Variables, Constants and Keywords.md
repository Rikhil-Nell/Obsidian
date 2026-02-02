---
Tags: "#CNotes"
---
A variable is a container which stores a "value". In this case C stores constants as a value.

## There are certain rules to assign a variable in C being:

- First character must be an alphabet or underscore
- The variable must not include any commas or blanks
- No special symbols are allowed apart from underscore
- Variable names are case sensitive

Creating meaningful variables are a good practice as it helps review the code better down the lane and you do not need to second guess where the given variable is being used.


## Constants

It is an entity whose value cannot be changed. There are primarily 3 types of constants in C:
- Integer Constant (1, 2, 3)
- Real Constant (-323.1, 2.5, 7.0)
- Character Constant ('a', '$', '@') {Must be enclosed with single inverted commas}

## Keywords

These are reserved words, whose meaning is already known to the compiler. There are 32 keywords available in C. It is discouraged to make variable with these words in them.

## Basic structure of a C program

All C programs have to follow a basic structure, a C program starts with a main function and executes instructions present inside it. Each instruction us terminated with a semicolon(;).

There are some rules which are applicable to all the C programs:
- Every program's execution starts from the main( ) function.
- All the statements are terminated with a semicolon.
- Instructions are case-sensitive.
- Instructions are executed in the same order in which they are written.

## Comments

Comments are used to clarify something about the program in plain language. It is a way for us to add notes to our program. There are two types of comments in C.

- Single line comment: // This is a comment
- Multi line comment: /* This a comment*/

Comments in a C program are not executed and are ignored.

## Compilation and Execution

>[!summary] First.c (written in VScode) ---> C program compiler (compiled by GCC) ---> first.exe

A compiler is a computer program which converts C program into machine language so that it can be easily understood by the computer.

A C program is written in plain text. This pain text is a combination of Instructions in a particular sequence. The compiler performs some basic checks and finally converts the program into an executable file.

## Library funcitons

C language has a lot of valuable library functions which is used to carry out certain tasks for instance, **"printf"** functions is used to print values on the screen.

```C
printf ("This is %d /n", i)
```

here, **%d** is for integers, **%f** is for real values and **%c** is for characters

## Types of variables

- Integer variables: int a = 3;
- Real variables: float a = 7.7
- Character variables: char a = B

## Receiving input from the user

In order to take input from the user and assign it to a variable, we use **"scanf"** function.

Syntax for using **"scanf"** function:

```C
scanf("%d &i")
```

**'&'** is the "address of" operator and it means that the supplied value should be copied to the address which is indicated by variable 'i'.

