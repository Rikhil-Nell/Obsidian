---
Tags: "#CNotes"
---
In C language, we must be able to execute instructions on a condition(s) being met.

**Decision Making Instructions in C :**
- If-else Statements
- Switch Statements

## If-else Statements

The syntax for if-else statements in C looks like 

```C
if(Condition to be checked){statements-if condition true;}
else {statements-if condition false;}
```

### Relational Operators

Relations operators are used to evaluate conditions inside the **if** statements
Some examples are:
- equals to ('= =')
- greater/lesser than or equal ('>=')
- greater/lesser than ('>,<')
- not equals to ('!=')

>**Important Note:** "=" is used for assignment whereas "= =" is used for equality check

The condition can be any valid expression. In C a non-zero value is considered to be true

### Logical Operators

**"&&",  "||", "!"** are the three logical operators in C. These are read as **"AND", "OR" and "NOT"**.
They are used to provide logic to our C programs.

#### Usage Of Logical Operators

- **"&&"** --> AND --> is true when both the conditions are true
	"1 and 0" is evaluated as false
	"0 and 0" is evaluated as false
	"1 and 1" is evaluated as true
- **"||"**  --> OR --> is true when at least one of the conditions is true 
	"1 or 0" is evaluated as true
	"0 or 0" is evaluated as false
	"1 or 1" is evaluated as true
- **"!"** --> NOT --> returns true if given false and returns false if given true
	"3 == 3" is evaluated as false
	"3 > 30" is evaluated as true

As the number of conditions increase, the level for indentation increases. This reduces the readability. Logical operators come to the rescue in such cases.

#### Else If Clause

Instead of using multiple **"if"** statements, we can also use **"else if"** along with **"if"** this forming a **"if - else if - else"** ladder.

```C
if {(//statements);}
else if {(//statements);}
else {(//statements);}
```

Using **"if - else if - else"** ladder reduces indents. The last **"else"** is optional. Also there can be any number of **"else if"** statements. The last **"else"** is only executed if all conditions fail.

### Operators Precedence (advanced)

| Priority |     Operator     |
| -------- |:----------------:|
| 1st      |      **!**       |
| 2nd      |   **\*, /, %**   |
| 3rd      |     **+,-**      |
| 4th      | **<, >, <=, >=** |
| 5th      |   **\==, !=**    |
| 6th      |      **&&**      |
| 7th      |     **\|\|**     |
| 8th      |      **=**       |

### Conditional Operators

A short hand **"If-else"** can be written using the conditional or ternary operator.

	Condtion? expression-if-true : expression-if-false

Here **"?"** and **":"** are ternary operators

## Switch Case Control Instruction

Switch case is used when we have to make a choice between number of alternatives for a given variable.

```C
Switch (integer-expression)
{
	case C1:
	code:	
	break;
	//
	case C2:
	code:	
	break;
	//
	case C3:
	code:
	break;
}
```

The value of integer-expression is matched against C1, C2, C3... if it matches any of these cases that case along with all the other subsequent **"cases"** and **"default"** statements are executed to prevent this **"break"** is to be used at the end of each case.

>[!seealso] [[005 Case Control Structure]] refer to these notes for more info

1. We can use switch-case statements even by writing cases in any order of our choice (not necessarily ascending)
2. **"char"** values are allowed as they can be easily evaluate to an integer
3. A switch can occur within another but in practice this is rarely done
