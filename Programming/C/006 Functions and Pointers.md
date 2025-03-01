---
tags:
  - CNotes
---

## What is a Function?

A function is a self-contained block of statements that perform a coherent task of some kind. Following would be an apt example:

``` C
message();
main()
{
	message();
	printf("\nCry, and you stop the monotomy!");
}
message()
{
	printf("\nSmile, and the world smiles with you...");
}
```

Its output would be as follows: *Smile, and the world smiles with you... Cry , and you stop the monotony!*

When the **main function** calls the **message function** it hands the control over to the **message function** and goes to sleep while it runs and then comes back to life when it has completed executing, you can also declare the **function** before the **main function**, but you cannot initialize it without declaring it in the same file or in a header file that is included.

You are also not restricted to calling only one **function**, you can make as many functions as you like but you ought to declare and initialize it it properly to avoid difficulty during code review or debugging.

These are the conclusions that be drawn about functions in C:

- Any C program contains at least one function.
- If a program contains only one function, it must be **main()**.
- If a C program contains more than one function, then one (and only one) of these functions must be **main()**, because program execution always begins with **main()**.
- There is no limit on the number of functions that might be present in a C program.
- Each function in a program is called in the sequence specified by the function calls in the **main()**.
- After each function has done its thing, control returns to **main()**. When **main()** runs out of functions to call, the program ends.


>[!info] Except for the fact that program execution always begins with **main()**, all functions in C enjoy a state of perfect equality, No precedence, No priorities.

### Summary

- C program is a collection of one or more **functions**.
- A function gets called when the **function** name is followed by a semicolon. For example: 
``` C
main()
{
	argentina();
}
```
- A **function** is defined when function name is followed by a fair of braces in which one or more statements may be present. For example:
``` C
argentina()
{
	statement 1;
	statement 2;
	statement 3;
}
```
- Any function can be called form any other function. Even **main()** can be called from other functions. For example:
``` C
main()
{
	message();
}

message()
{
printf("\n Can't imagine a life without C");
main();
}
```
- A function can be called any number of times. For example:
``` C
main()
{
	message();
	message();
}

message()
{
	printf("\n Jewel Theif!!");
}
```
- The order in which the functions are defined in a program and the order in which they called need not necessarily be same. For example:
``` C
main()
{
	message1();
	message2();
}

message2()
{
	printf("\nBut the butter was bitter");
}

message1()
{
	printf("\nMary bought some butter");
}
```
Even though this is permitted, it is advisable to define the functions in the same order in which they are called, making the code easier to understand.
- A **function** can also call itself. This process is called **'recursion'**.
- A function can be called from other function, but a function cannot be defined in another function, Thus, the following would not work:
``` C
main()
{
	printf("\n I am in main");
	argentina()
	{
		printf("\n I am in argentina");
	}
}
```
- There are basically two types of functions:
	1. Library functions EX. **printf()**, **scanf()**, etc.
	2. User defined functions EX. **argentina()**, **brazil()**, etc.

As the name suggests, library functions are a premade set of commonly used **functions** grouped and stored in a library that is sent to us by default with the compiler, the procedure to call either types of functions is exactly the same.

## Why use Funcitons?

There are mainly two reasons why people, prefer to use functions rather than cramming all their logic in the **main()** function:

1. writing functions avoids the tedious job of rewriting parts of code which you are gonna use again and again in the said project, EX: you would prefer to stored the formula for finding the area of a square in a **function** over having to rewrite the code for it every time you want to use it.
2. having to read through one huge piece of code is a debugging nightmare, and separating the code in smaller modular functions, enhances the readability of the code

## Passing Value between Functions

Since the functions we have used so far have been really simple and restrictive, we can use **'arguments'** as a mechanism to convey information to our **functions** to do something more complicated.

``` C
#include<stdio.h>

  

int main()
{
	int a,b,c,sum;
	
	printf("Enter any three numbers ");
	scanf("%d %d %d", &a, &b, &c);
	
	sum = calsum(a,b,c);
	printf("\nSum = %d", sum);
	
	return 0;
}


calsum (x, y, z)
int x,y,z;

{ int d;
	
	d = x + y + z;
	return d;
	
}
```

Notable things about the above program:
- The values of **a, b, c** are passed onto the function **calsum()** from **main()** by making a call to the said function and mentioning a, b, c in the parentheses. In the **calsum()** function these values get collected in three variables **x, y, z**.
- the variables **a, b, and c** are called **'actual arguments'**, whereas the variables **x, y and z** are called **'formal arguments'**. Any number of arguments can be passed to a function being called as long as the ==type, order and number== of the actual and formal arguments are same. We could have used the variables **a, b and c** again instead of **x, y and z** as the compiler would treat them as different variables since they are in different functions.
- There are two methods of declaring the **formal arguments**. The one that we have used in our program is know as Kernighan and Ritchie method.
``` C
calsum(x,y,z)
int x,y,z;
```
There is another more commonly used method called ANSI method:
```C
calsum(int x, int y, int z)
```

- In the earlier programs the moment the closing brace of the called function was encountered the control returned to the calling function. No separate **return** statement was necessary to send back the control, this is fine when the function being called is unimportant but going forward **return** statement is essential for the following purposes:
  1. On executing the **return** statement it immediately transfers the control back to the calling program.
  2. It returns the value present in the parentheses after **return**, to the calling wprogram.
- There is no restriction on the number of **return** statements that may be present in a function. Also the **return** statement need not always be present at the end of called function. 
``` C
fun()
{
	char ch;
	printf("\nEnter any alphabet ");
	scanf("%c", &ch);
	
	if(ch>=65 && ch<=90)
		return(ch);
	else
		return(ch + 32);
}
```
In this function different **return** statements will be executed depending on whether **ch** is capital or not.
- Whenever the control returns form a function some value is definitely returned. If it is a meaningful value then it should be accepted by calling a program by equating the called function to some variable. For example,
``` C 
sum = calsum(a, b, c);
```
All the following are valid **return** statements.
``` C
return(a);
return(23);
return(12.34);
return;
```
 In the last statement a garbage value is returned to the calling function since we are not returning any specific value. Note that in this case the parentheses after **return** are dropped.
- If we want that a called function should not return any value, in that case, we must mention so by using the keyword **void** as shown below.
```C
void display()
{
	printf("\n Heads I win....");
	printf("\n Tales you lose");
}
```

- A function can return only one value at a time. This, the following statements are invalid. 
```C
return(a,b);
return(x,12);
```
There is a way to get around this limitation in the following way:

- If the value of a **formal** argument is changed in the called function, the corresponding change does not take place in the calling function. For Example
``` C
main()
{
	int a =  30;
	fun (a);
	printf("\n%d", a);
}

fun(int b)
{
	b =  60;
	printf("\n%d", b);
}
```
The output of the above program would be: *60 30*

Thus, even though the value of **b** is changed in **fun( ),** the value of **a** in **main( )** remains unchanged. This means that when values are passed to a called function the values present in actual arguments are not physically moved to the formal arguments; just a photocopy of values in actual argument is made into formal arguments.

>[!abstract] It is required that we send the a variable stored in calling function to called function, if we want it to work with the said variable, why? because the compiler assigns variables to only one function, meaning the scope of utility of the variables is limited only to the function it is initialized and defined in. __In general we can say that the scope of a variable is local to the function in which it is defined__

## Calling Convention

Calling convention indicates the order in which arguments are passed to a function when a function call is encountered. 

**C language passes arguments from right to left.**

For Example: 
```C
int a = 1
printf("%d %d %d", a, ++a, a++);
```

The output most would expect would be: *1 2 3*
But the actual output is: *3 3 1*

1 is passed through the expression **a**++ and then a is incremented to 2. Then result of ++**a** is passed. That is, **a** is incremented to 3 and then passed. Finally, latest value of **a**, i.e. 3, is passed. Thus in right to left order 1, 3, 3 get passed. Once **printf( )** collects them it prints them in the order in which we have asked it to get them printed (and not the order in which they were passed). Thus 3 3 1 gets printed

## One Dicey Issue

Normally when calling a function we must write a their prototype before making the call. This helps the compilier in checking whether the values being passed and returned are as per the prototype declaration. But since we don't define library functions and mererly call them we may not know the prototypes of library functions. Hence when the library of functions is provided a set of ".h" files is also provided. These files contain the prototypes of library functions.

# Advanced Features of Functions

## Function Declarartion and Prototypes

Any C function by default returns an **int** value. Whenever a call is made to a function, the compiler assumes that a function should return a valye of the type **int**.
If we desire that a function should return a value other that **int**, then it is necessary to explicitly mention so in the calling function as well as in the called function. For example:

```c
main()
{
	float square(float);
	float a,b;
	
	printf("\nEnter any number ");
	scanf("%f",&a);
	
	b = square(a);
	printf("\nSquare of %f is %f",a,b);
}

float square(float x)
{
	float y;
	y = x * x;
	return(y);
}
```

The output of the following would be: 
*Enter any number 1.5*
*Square of 1.5 is 2.250000*

>[!note] Note that the function **square()** must be decleared in the **main()** as `float sqaure(float)`

This statement is often called the prototype declaration of the **square()** function. What it means is square funciton receives a float and returns a float. We have done prototype declaration in **main()** because we called in from **main()**. If we are required to call this funciton in any other functions along with main function then we can do the prototype declaration outside all the functions.

In some programming situations we want the called function to not return any value. This can be achieved using **void** keyword in the following manner.

```c
main()
{
	void gospel();
	gospel;
}

void gospel()
{
	print("jibberish");
}
```

Here, the function has been defined to return void; mean it would return nothing.

## Call by Value and Call by Reference

Calling a function and passing the values of the variables to the function is called "calls by value".
Calling a function and passing the reference of the variable to the function is called "calls by reference".

## Introduction to Pointers

Many other languages have pointers but are used sparingly, The clever use of pointers is what makes C an excellent language.

Consider the declaration, 
```c
int i = 3 ;
```

This declaration tells the C compiler to: 
1. Reserve space in memory to hold the integer value.
2. Associate the name i with this memory location.
3. Store the value 3 at this location. 

We may represent i’s location in memory by the following memory map
![[Obsidian_DKT3Tjmtib.png]]

From above we can say that, the address of **i** is a number.

We can print the address using the following code:
```c
main()
{
	int i = 3;
	printf("\nAddress of i = %u", &i);
	printf("\nValue of i = %d", i);
}
```

Here, **'&'** operator before **i** is used to return the address of **i**, as it has not sign associated with it, we use **%u** which is a format specifier for printing an unsigned integer. 
The other pointer operator available in C is **‘\*’**, called ‘value at address’ operator. It gives the value stored at a particular address. The ‘value at address’ operator is also called ‘indirection’ operator.

``` C
main()
{
	int i = 3;
	
	printf("\nAddress of i = %u", &i);
	printf("\nValue of i = %d", i);
	printf("\nValue of i = %d",*(&i));
}
```

printing the value of **\*(&i)** is the same as printing the value of **i**.

>[!warning] But remember that **j** is not an ordinary variable like any other integer variable. It is a variable that contains the address of other variable (**i** in this case). Since **j** is a variable the compiler must provide it space in the memory. Once again, the following memory map would illustrate the contents of **i** and **j**.

![[Obsidian_XlgAVPe0FW.png]]

As you can see, **i’s** value is 3 and **j’s** value is i’s address.

But wait, we can’t use **j** in a program without declaring it. And since j is a variable that contains the address of **i**, it is declared as,

```c
int \*j ;
```

This declaration tells the compiler that **j** will be used to store the address of an integer value. In other words **j** points to an integer. 

Let us go by the meaning of **\***. It stands for ‘value at address’. Thus, **int \*j** would mean, the value at the address contained in **j** is an int.

``` C
main( ) {
	int i = 3 ;
	int *j = &i ;
	
	printf ( "\nAddress of i = %u", &i ) ; 
	printf ( "\nAddress of i = %u", j ) ;
	printf ( "\nAddress of j = %u", &j ) ; 
	printf ( "\nValue of j = %u", j ) ;
	printf ( "\nValue of i = %d", i ) ; 
	printf ( "\nValue of i = %d", *( &i ) ) ;
	printf ( "\nValue of i = %d", *j ) ;
	 }
```
The above program gives the following output:
*Address of i = 65524
Address of i = 65524
Address of j = 65522
Value of j = 65524
Value of i = 3
Value of i = 3
Value of i = 3*




