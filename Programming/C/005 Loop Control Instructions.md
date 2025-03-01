---
Tags: "#CNotes"
---
## What Are Loops?

Sometimes we want our programs to execute a  few set of instructions over and over again for example:  printing 1 to 100, first 100 even numbers etc.

Hence loops make it easy for a programmer to tell the computer that a given set of instructions must be executed repeatedly.

## Types Of Loops

Primarily there are 3 types of in C language:
- While Loop
- For Loop
- Do-While Loop

## **while** Loop

```C
while (condition is true){
//code}
```

The block keeps repeating as long as the condition remains true

The image below shows the operation of the **while** loop:
![[Acrobat_jO9RRFGTH7.png]]


> If the condition never becomes false, the while loop keeps getting executed. Such a loop is known as an infinite loop

>Loop counter need not be **int** it can be **float** as well

### More Operators

There are a variety of operators frequently used with **while** such as:
- Increment Operators: it increments the value of **i** by 1, every time the statement **i++** gets executed
- Decrement Operators: similar to increment operators it decrements the value of **i** by 1, every time the statement **i--** gets executed 
- Both Increment and Decrement Operators are divided into to two: Pre and Post
- 	main (){
		int  = 1;
			while ( i++ < 10){
			printf("%d\n",i);
			}}
- In the above operator **++** acts as a post incrementation operator since the incrementation of **i** happens after the comparison between **i** and 10.
- 	main (){
		int  = 1;
			while ( ++i < 10){
			printf("%d\n",i);
			}}
- In the above operator **++** acts as a pre incrementation operator since the incrementation of **i** happens before the comparison between **i** and 10.
- Operators like **+= ; -= ;\*= ; /= ; %=**  are called **compound assignment operators**, For example: j = j + 10 can also be written as j += 10 or j = j / 10 can also be written as j /= 10

## **for** Loop

The **for** is the most widely used loop by programmers, The **for** loop allows us to specify three things about a loop in a single line
1. Setting the loop counter to an initial value
2. Testing the loop counter to determine whether its value has reached the number of repetitions desired
3. Increasing the value of loop counter each segment within the loop has been executed.

General form of **for** statement 

```C
for (intitalize counter ;  test counter ; increment counter)
{
	do this;
	and this;
	and this;
}
```

The image below shows the operation of the **for** loop:
![[Obsidian_rF80Dipxhy.png]]

There are 3 steps in **for** loop compared to **while** loop: 
- Initialization
- Testing 
- Incrementation.
![[Obsidian_v4dYQWDh12.png]]

>If there is only one statement in the body of a **for** loop the pair of curly braces can be dropped.

## Nesting Of Loops

The way **if** statements can be nested, similarly **while** and **for** loops can also be nested.

Syntax for **for** nested loop 

```C
for ( initialization; condition; increment )
{
	for ( initialization; condition; increment )
	{
		 // statement of inside loop
	}
	// statement of outer loop
}
```

Syntax for **while** loop

```C
while(condition)
{
	while(condition)
	{
		// statement of inside loop
	}
		// statement of outer loop
}
```

>**Note:** There is no rule that a loop must be nested inside its own type. In fact, there can be any type of loop nested inside any type and to any level.

### Break Inside Nested Loops

Whenever we use a break statement inside the nested loops it breaks the innermost loop and program control is inside the outer loop.

### Continue Inside Nested loops

Whenever we use a continue statement inside the nested loops it skips the iteration of the innermost loop only. The outer loop remains unaffected.

## Multiple Initializations in the **for** Loop

The initialization expression of the for loop can contain more than one statement separated by a comma. For example,

```C
for ( i = 1, j = 2 ; j <= 10 ; j++ )
```

Multiple statements can also be used in the incrementation expression of for loop; i.e., you can increment (or decrement) two or more variables at the same time. 

*However, only one expression is allowed in the test expression. This expression may contain several conditions linked together using logical operators.*

Use of multiple statements in the initialization expression also demonstrates why semicolons are used to separate the three expressions in the for loop. If commas had been used, they could not also have been used to separate multiple statements in the initialization expression, without confusing the compiler

## The *Odd* Loop

In real life programming, where one may come across a situation when it is not known beforehand how many times the statements in the loop are to be executed. This situation can be tackled with **do-while** loop. 

**do-while** loop are extensively used when the loop is executed based on the count variable assigned by the user.

for example:

```C
/* Execution of a loop an unknown number of times \*/
main( )
{
	char another ;
	int num ;
	do
	{
		printf ( "Enter a number " ) ;
		scanf ( "%d", &num ) ;
		printf ( "square of %d is %d", num, num * num ) ;
		printf ( "\nWant to enter another number y/n " ) ;
		scanf ( " %c", &another ) ;
	} 
	while ( another == 'y' ) ;
}
```

While it is possible to replicate the above functionality with **for** and **while** loops too but **do-while** is more efficient. Their script would be along the lines of:

For **for** loop:

```C
/* odd loop using a for loop\*/
main( )
{
	char another = 'y' ;
	int num ;
	for ( ; another == 'y' ; )
	{
		printf ( "Enter a number " ) ;
		scanf ( "%d", &num ) ;
		printf ( "square of %d is %d", num, num * num ) ;
		printf ( "\nWant to enter another number y/n " ) ;
		scanf ( " %c", &another ) ;
	}
}
```

For **while** loop:

```C
/* odd loop using a while loop \*/
main( )
{
	char another = 'y' ;
	int num ;
	while ( another == 'y' )
	{
		printf ( "Enter a number " ) ;
		scanf ( "%d", &num ) ;
		printf ( "square of %d is %d", num, num * num ) ;
		printf ( "\nWant to enter another number y/n " ) ;
		scanf ( " %c", &another ) ;
	}
}
```

## *break* statement

We often come across situations where we want to jump out of a loop instantly, without waiting to get back to the conditional test. The keyword **break** allows us to do this.

When **break** is encountered inside any loop, control automatically passes to the first statement after the loop. A **break** is usually associated with an **if** statement.

The keyword **break**, breaks the control only from the while in which it is placed.

## *continue* statement

In some programming situations we want to take the control to the beginning of the loop, bypassing the statements inside the loop, which have not yet been executed. The keyword **continue** allows us to do this. 

When **continue** is encountered inside any loop, control automatically passes to the beginning of the loop. A **continue** is usually associated with an **if** statement.

## **do-while** Loop

General form of **do-while** statement 

```C
do
{
	this ;
	and this ;
	and this ;
	and this ;
} 
while ( this condition is true ) 
```

The image below shows the operation of the **do-while** loop:
![[Obsidian_0dBYQgHGGy.png]]

There is a minor difference between the working of **while** and **do-while** loops. 

This difference is the place where the condition is tested. The **while** tests the condition before executing any of the statements within the while loop. As against this, the **do-while** tests the condition after having executed the statements within the loop.

>**break** and **continue** are used with **do-while** just as they would be in a **while** or a **for** loop. A **break** takes you out of the **do-while** bypassing the conditional test. A **continue** sends you straight to the test at the end of the loop.

## Summary 


1. The three type of loops available in C are for, while, and do-while.
2. A break statement takes the execution control out of the loop.
3. A continue statement skips the execution of the statements after it and takes the control to the beginning of the loop.
4. A do-while loop is used to ensure that the statements within the loop are executed at least once.
5. The ++ operator increments the operand by 1, whereas, the -- operator decrements it by 1.
6. The operators +=, -=, \*=, /=, %= are compound assignment operators. They modify the value of the operand to the left of them

