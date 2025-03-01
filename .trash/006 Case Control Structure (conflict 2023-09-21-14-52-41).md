---
tags:
   "#CNotes"
---

In real life, the choice we are asked to make is more complicated than merely selecting between two alternatives. C provides a special control statement that allows us to handle such cases effectively; rather than using a series of if statements.

## Decisions using **switch**

The control statement that allows us to make a decision from the number of choices is called a **switch**, or more correctly a **switch-case-default**, since these three keywords go together to make up the control statement. They most often appear as follows:

```C
switch ( integer expression )
	{
	case constant 1 :
		do this ;
	case constant 2 :
		do this ;d
	case constant 3 :
		do this ;
	default :
		do this ;
	}

```

The integer expression following the keyword **switch** is any C expression that will yield an integer value. It could be an integer constant like 1, 2 or 3, or an expression that evaluates to an integer. 

The keyword **case** is followed by an integer or a character constant. Each constant in each case must be different from all the others. The “do this” lines in the above form of switch represent any valid C statement.

### How **switch-case** works?

Given below is the general form of **switch-case**:

```C
switch(integer expression)
{
	case constant 1:
		do this;
	case constant 2:
		do this;
	case constant 3:
		do this;
}
```

When executed, the integer expression following keyword **switch** in any C expression that will yield an integer value. It could be an integer constant like 1,2 or 3 or an expression that evaluates to an integer. 

This is then mapped to the integer constant following the keyword **case.**

>[!info] Each constant in each **case** must be different from all the others

and the code in the above mentioned "do this" lines gets executed.
Incase the integer doesn't get mapped to any of the case constants then the code under the keyword **default** is executed instead.

> [!important]  If you do not use a **break** statement after every case, the code under the the subsequent **cases** to the **case** that we mapped to also get executed.

>[!important] There is no need to use a break statement after the **default**, since the control comes out of the switch anyways.

A good example of **switch case** would be the following:

```C
main()
{
	int i = 2;
	switch (i)
	{
		case 1:
			printf("I am in case 1 \n");
			
		case 2:
			printf("I am in case 2 \n");
			
		case 3:
			printf("I am in case 3 \n");
			
		default:
			printf("I am in default \n")
	}
}
```

The output of the program would be: *I am in case 2*

The operation of **switch** is shown below in the form of a flowchart for better understanding.

![[Pasted image 20230914102452.png]]

## The Tips and Traps

- It is not necessary to arranged the **cases** in the descending order of their assingned integer constants, you can choose to arrange them in no particular order

- You are also allowed to use **char** values in **case** and **switch** as show in the following program
```C
main()
{
	char c ="x";
	switch (c)
	{
		case "v":
			printf("I am in case v \n")
			break;
		case "a":
			printf("I am in case a \n")
			break;
		case "x":
			printf("I am in case x \n")
			break;
		default:
			printf("I am in default \n")}
}
```

 The output of this program would be: *I am in case x*.
 >[!info] This is possible because the char values that we entered are turned into their respective ASCII values and hence can be evaluated/mapped.
 
-  At times we may want to execute a common set of statments for multiple **cases**. How this can be doen is shown in the following example:

```C
main()
{
char ch;

printf("Enter any of the aplhabet a,b, or c");
scanf("%c", &ch);

switch(ch)
	{
	case 'a':
	case 'A':
		printf("a as in ashar \n");
		break;
		
	case 'b':
	case 'B':
		printf("b as in brain \n");
		break;
		
	case 'c':
	case 'C':
		printf("c as in cookie \n");
		break;
		
	default:
		printf("wish you knew what are alphabets");
	}
}
```

Here we are using the feature/flaw of the keyword **case** of executing all the code pertaining to its own **case** and all the other **cases** below it unless a **break** statement is used to our advantage.

- Even if there are multiple statements to be executed in each **case** there is no need to enclose them within a pair of braces (unlike **if**, and **else**).

- Every statement in a switch must belong to some case or the other, If a statement doesn't belong to any case the compiler won't report an error. However, the statement would never get executed. For example, in the following program the printf() never goes to work.

```C
main()
{
	int i,j;
	
	printf("Enter value of i");
	scanf("%d",&i);
	
	switch(i)
	{
		printf("Hello")
		case 1:
			j = 10;
			break;
			
		case 2;
			j = 20;
			break;
	}
}
```

- If we have no **default** case, then the program simply falls through the entire **switch** and continues with the next instruction (if any,) that follows the closing brace of **switch**.

- While **switch** is better in a lot of ways when compared to **if**, a certain disadvantge holds it back being:
	- We cannot have a case in a **switch** which looks like this: *case i <= 20:*
	- All that we can have after the case is an **int** constant or a **char** constant or an expression that evaluates to one of these constants. Even a **float** is not allowed.
The advantage of **switch** over **if** is that it leads to a more structured program that level of indentation is manageable, more so if there are multiple statements within each **case** of a **switch**.

- We can check the value of any expression in a **switch**. Thus the following **switch** statements are legal.
	- switch (i+j+k)
	- switch (23 + 45 % 4 * k)
	- switch(a < 4 && b > 7)
>[!info] Expression can also be used in cases provided they do not contain any variables. Thus **case** 3 + 7 is correct, however, **case** a + b is incorrect.

- The **break** statement when used in a **switch** takes the control outside the **switch**. However, use of **continue** will no take the control to the beginning of **switch** as one is likely to believe.

- It is possible for a **switch** to occur inside another, but this is a rare practice. Such statements would be called nested **switch** statements.

- The **switch** statement is very useful while writing menu driven programs.

## **switch** Versus **if-else** Ladder

Below is a list of things that you simply cannot do with a **switch**:
1. A float expression cannot be tested using a **switch**
2. Cases can never have variable expressions (for example it is wrong to say **case** a +3:)
3. Multiple cases cannot use same expressions. Thus the following switch is illegal.
```C
switch(a)
{
	case 3:
	...
	case 1 + 2:
	...
}
```

The above may lead you to believe that these are obvious disadvantages with a switch, especially since there weren’t any such limitations with if-else. Then why use a switch at all? For speed—switch works faster than an equivalent if-else ladder. How come? This is because the compiler generates a jump table for a switch during compilation. As a result, during execution it simply refers the jump table to decide which case should be executed, rather than actually checking which case is satisfied. As against this, if-elses are slower because they are evaluated at execution time. A switch with 10 cases would work faster than an equivalent if-else ladder. 

Also, a switch with 2 cases would work slower than if-else ladder. Why? If the 10th case is satisfied then jump table would be referred and statements for the 10th case would be executed. As against this, in an if-else ladder 10 conditions would be evaluated at execution time, which makes it slow. Note that a lookup in the jump table is faster than evaluation of a condition, especially if the condition is complex.

If on the other hand the conditions in the if-else were simple and less in number then if-else would work out faster than the lookup mechanism of a switch. Hence a switch with two cases would work slower than an equivalent if-else. Thus, you as a programmer should take a decision which of the two should be used when.

## The **goto** Keyword

Using **goto** is a sin, and anyone who tries to perform such heresy will be crucified by the pure knights of the **if-else**, **for-while** and **switch-case** regiments. But for the the sake of understanding and idenitfying this heresey we shall study it, following is how this impious magic would be used:

```C
main()
{
	int goals;
	
	printf("Enter the number of goals scored against India");
	scanf("%d",%goals);
	
	if (goals <= 5)
		goto sos;
	else
	{
		printf("About Time soccer players learnt C \n");
		printf("and said goodbye! adieu! to soccer");
		exit(); /*terminates program execution*/
		
	}
	sos:
		printf("To err is human!");
}
```

The output for the following inputs would be: 
- Input = 3 ==> Output: To err is human!
- Input = 7 ==> Output: About time soccer players learnt C and said goodbye! adieu! to soccer
### A few remarks about the program to things clearer. 

- If the condition is satisfied the **goto** statement transfers control to the label ‘sos’, causing **printf( )** following sos to be executed. 

- The label can be on a separate line or on the same line as the statement following it, as in, 
  *sos : printf ( "To err is human!" ) ;*

- Any number of **gotos** can take the control to the same label.
 
- The **exit( )** function is a standard library function which terminates the execution of the program. It is necessary to use this function since we don't want the statement 
  *printf ( "To err is human!" )* 
  to get executed after execution of the else block.

- The only programming situation in favour of using goto is when we want to take the control out of the loop that is contained in several other loops. The following program illustrates this. 

```C
main()
{
	int i,j,k
	
	for(i=1;i<=3;i++)
	{
		for(j=1;j<=3;j==)
		{
			for(k=1;k<=3;k++)
			{
				if(i==3 && j==3 && k==3)
					goto out;
					
				else
					printf("%d %d %d\n",i,j,k);
			}
		}
	}
	out: 
		printf("Out of the loop at last!")
}
```

## Summary

1. When we need to choose one among number of alternatives, a **switch** statement is used.
2. The **switch** keyword is follwed by an integer or an expression that evaluates to an integer.
3. The **case** keyword is folled by an integer or a character constant.
4. The control falls through all the cases unless the **break** statement is given.
5. The usage of the **goto** keyword is heresy and should be avoided as it usually violates the pure knights' codex.
