---
Tags: "#CNotes"
---

In real life, the choice we are asked to make is more complicated than merely selecting between two alternatives. C provides a special control statement that allows us to handle such cases effectively; rather than using a series of if statements.

## Using **switch**

The control statement that allows us to make a decision from the number of choices is called a **switch**, or more correctly a **switch-case-default**, since these three keywords go together to make up the control statement. They most often appear as follows:

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

The integer expression following the keyword **switch** is any C expression that will yield an integer value. It could be an integer constant like 1, 2 or 3, or an expression that evaluates to an integer. 

The keyword **case** is followed by an integer or a character constant. Each constant in each case must be different from all the others. The “do this” lines in the above form of switch represent any valid C statement.





