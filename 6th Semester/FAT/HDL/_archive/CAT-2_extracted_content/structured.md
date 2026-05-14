# Extracted Course Content

**Source Files**: VHDL.pdf

**Total Pages/Slides**: 309

**Extraction Time**: 2026-03-27T20:25:26.979682

---

## Table of Contents

- **Main**
  - • It has features inherited from Verilog  HDL,VHDL,C,C++
  - – Updated with more features in 2012 (IEE 1800 2012 standard)
  - • For better Coverage/Assertion integration
  - Why System Verilog
  - entire design flow
  - more efficient
  - entire design flow
- **DPI**
  - – C calls SV functions
  - learning 2 languages (Specman E/Vera and PSL)
  - Data Types
  - 4-state, 64 bit unsigned
  - if (signed'(ubyte)< 150) // ubyte is unsigned
  - Void
  - '0 is equivalent to making an assignment of 0
  - int n[1:2][1:6] = '{2{'{3{4, 5}}}}; // same as '{'{4,5,4,5,4,5},'{4,5,4,5,4,5}
  - string myName = "TEST BENCH";
  - 14. str.realtoa(r) stores the ASCII real representation of r into str (inverse of atoreal)
  - String Methods
  - Syntax: Str[index]
  - Str1 and str3 are not equal
  - Str1 and str3 are not equal
  - Str3 >= Str2
- **WWW.VITAP.AC.IN**
  - String Operators
  - W W W . T E S T B E N C H . I N
  - 5. Typedef.
  - enum {IDLE,READY,BUZY} states;
  - a member of the enumeration, the name() method returns the empty string.
  - blue
  - enum integer {IDLE, XX='x, S1='b01, S2='b10} state, next;
  - $display(" a value is %d ",my_data_struct.a);
  - my_data_struct = `{a:1234,default:8'h20};
  - "my_data_struct".
  - } tagged_st; // named structure
  - reg [31:0] registers2 [256]; // register is packed 32 bit wide
- **ARRAYS:**
  - ‘ADDER’ TestBench Without Monitor, Agent and Scoreboard
  - 1. Declaring the fields.
  - 3. Adding display() method to display Transaction properties.
  - •Sending the randomized class to driver
  - 2. ‘Randomize’ the transaction class,
  - and driver).
  - 4. Adding a variable to control the number of packets to be created,
  - completion of the Generation process.
  - This is a simple interface without modport and clocking block.
  - Driver Class
  - Accessing "Array[0][3][6]" will access one element.
  - required.
  - Dynamic Array Methods
  - 0
  - contents into the new one after creation.
  - • If an argument is not provided, item is the name used by default.
  - Mandatory 'with' clause
  - Optional 'with' clause
  - Array Ordering Methods
  - Array Reduction Methods
  - A queue is distinguished by it's specification of the size using $ operator.
  - SystemVerilog Loops
  - simulation time.
  - Used to repeat statements in a block a certain number of times.
  - It'll repeat  the block as long as the condition is true.
  - For
  - while loop
  - do while loop
  - SystemVerilog 'break’and 'continue'
  - report an erorr when there is more than 1 match found in the if else conditions
  - No else block for unique-if
  - Multiple matches in unique-if
  - None of the conditions are true or if there's no else clause to the final if construct
  - Exit after first match in priority-if
  - unique : No items match for given expression
  - unique : More than one case item matches
  - priority case
  - of the statements based on when child threads finish.
  - fork-join will be unblocked at 20ns.
  - fork-join_any will be unblocked at 5ns
  - completion of the Process inside the fork-join_none.
  - wait fork; causes the process to block until the completion of all processes started from fork blocks.
  - • default arguments type is logic if no type has been specified
  - default arguments type is logic if no type has been specified.
  - function with return value with the return keyword
  - Void function
  - subroutine
  - To indicate argument pass by reference, the argument declaration is preceded by keyword ref.
  - Value of z = 25
  - argument.
  - Class Declaration
  - Accessing class properties and methods
  - this is a pre-defined class handle referring to the object from which it is used, calling this.variable means object.v
  - On calling the new method it allocates the memory and returns the address to the class handle.
  - pass arguments to the constructor, which allows run-time customization of an object.
  - pkt_2
  - •If the class is derived from a derived class, then it is referred to as Multilevel inheritance
  - •The child class is also known as an extended class, derived class, subclass
  - parent class.
  - or different forms of the same method.
  - with display method overridden in it.
  - method, this is called polymorphism.
  - class members.
  - With super keyword, it is allowed to access the class members of parent class which is only one level up
  - •Dynamic casting
  - concatenation or replication braces
  - type for the assignment.
  - casting.
  - assigning child class handle to parent class handle
  - assigning parent class handle to child class handle
  - assigning parent class handle to child class handle
  - Use of $cast or casting
  - local integer x;
  - Accessing local variable outside the class ( Not allowed )
  - Accessing local variable within the class ( Allowed )
  - protected integer x;
  - Accessing a protected variable outside the class ( Not allowed )
  - Accessing a protected variable in the extended class ( allowed )
  - a method declaration.
  - virtual class leads to a compilation error.
  - and creating it.
  - Task declared with a virtual keyword before the task keyword is referred to as virtual task
  - assigned to the base class handle.
  - the base class method.
  - method.
  - derived classes
  - the class by using class resolution operator ::
  - is used to provide a forward declaration of the class.
  - Random System Methods $urandom(), $random and $urandom_range();
  - •object handle's
  - In order to randomize the object variables, the user needs to call randomize() method.
  - the rand_mode method returns 1 if randomization is enabled else returns 0
  - randomization disable for all class variable
  - block defined outside the class is called as extern constraint block
  - Constraint block inside the class
  - Constraint block outside the class
  - Constraint blocks can be overridden by writing constraint block with the same name as in parent class.
  - SystemVerilog inside operator, random variables will get values specified within the inside block.
  - constraint inside example
  - inverted inside example
  - •the sum of weights need not be a 100
  - in the range. where n is the number of values in the range.
  - randomization with dist operator
  - difference between := and :/ dist operator
  - considered.
  - constraints in the optional else constraint/constraint-block must be satisfied.
  - The foreach loop iterates over the elements of an array, so constraints with the foreach loop are called Iterative constraints.
  - Any mode change of static constraint will afect in all the objects of same class type.
  - constraint is used to constrain the variable addr.
  - Constraint inside the class and inline constraint
  - variables.
  - The $urandom_range() function returns an unsigned integer within a specified range.
  - •Events
  - this leads to an unexpected result. A semaphore can be used to overcome this situation.
  - new( );
  - endmodule
  - Putting back more keys
  - other process puts the key.
  - keys are not available simulation will proceed(non-blocking).
  - Parameterized Mailbox that can accept items of only a specific data type
  - • num() : Retrieve the number of messages in the mailbox
  - share data for which a certain level of determinism is required.
  - method returns a negative integer.
  - method returns a negative integer.
  - Non-blocking events are triggered using the ->> operator.
  - Whereas wait(); construct will detect the event triggering.
  - run-time error is generated.
  - the event waiting with @ operator
  - will not be executed.
  - •Concurrent Assertions
  - • The action_block specifies what actions are taken upon success or failure of the assertion
  - •If an assertion fails and no else clause is specified, the tool shall, by default call $error.
  - The Keyword differentiates the immediate assertion from the concurrent assertion is "property."
  - Below diagram shows the steps involved in the creation of an SVA checker,
  - Request is asserted."
  - SVA provides a keyword to represent these complex sequential behaviors called "property".
  - It has to be asserted to take effect during a simulation. SVA provides a keyword called "assert" to check the property.
  - clock. If the signal "a" is not high on any positive clock edge, the assertion will fail.
  - clock cycles.
  - checking the seq_2 (“d” should be low, 2 clock cycles after seq_1 is true).
  - in the same clock cycle or within 4 clock cycles.
  - eventually starting from the next clock cycle.
  - number of clocks specified.
  - for 3 clock cycles followed by “c” should be high after ”b” is high for the third time.
  - occur, the assertion will fai
  - the “b” is high, then 2 cycles before that, a was high.
  - that, a was high only if the gating signal "c' is valid on any given positive edge of the clock.
  - counts the number of bits that are high in a vector.
  - Assert statement a_4 checks that the number of ones in the vector "bus" is greater than one.
  - this entire sequence, if reset is detected high at any point, the checker will stop.
  - them. the end point of the sequences does the synchronization.
  - executed.

---


# Source: VHDL.pdf


## Page 1: • It has features inherited from Verilog  HDL,VHDL,C,C++

2

# What is System Verilog


## • SystemVerilog is a combined hardware  description language and


# hardware verification  language


# • SystemVerilog is an extensive set of  enhancements to IEEE


# 1364 Verilog-2001  standards


# • It has features inherited from Verilog  HDL,VHDL,C,C++


## Page 2: – Updated with more features in 2012 (IEE 1800 2012 standard)

3
Verification with System Verilog

# History and evolution of SV


# • Verilog (IEEE standard 1364)


# – Began in 1983 as a proprietary language


# – Opened to the public in 1992


# – Became an IEEE standard in 1995 (updated in 2001 and 2005)


# – Between 1983 and 2005 design sizes increased dramatically!


# • System Verilog (IEEE standard 1800)


# – Originally intended to be the 2005 update to Verilog


# – Contains hundreds of enhancements and extensions to Verilog


# – Published in 2005 as a separate document


# – Officially superseded Verilog in 2009


# – Updated with more features in 2012 (IEE 1800 2012 standard)


## Page 3: • For better Coverage/Assertion integration

4

# System Verilog – User View


# • Has 5 major parts


# – SVD – System Verilog for Design


# • Features supporting Design


# – SVTB – System Verilog for Test benches


# • Test bench specific Features


# – SVA – System Verilog Assertions


# • Features for temporal and concurrent assertions


# – SVDPI – SV Direct Programming Interface


# • For better C/C++ integration


# – SVAPI – SV Application Programming Interface


# • For better Coverage/Assertion integration


## Page 4: Why System Verilog


# Why System Verilog

5

### Figures on this page:

- `VHDL_p4_img1.png`

## Page 5: entire design flow

6

# Verilog Comparison


# • Used for Design Entry


# • Module Level Verification


## Verilog


## System Verilog


# • Module Level Design


# • Constrained Random


# Verification


# • Assertions/Coverage


# • One single language for


# entire design flow


## Page 6: more efficient

7
Verification with System Verilog

# Verilog Comparison – Data types


# • Strict about usage of wire


# & reg data type


# • Variable types are 4 state –


# 0,1,X,Z


## Verilog


## System Verilog


# •


# Logic data type can be used so


# no need to worry about reg &


# wire


# •


# 2 state data type added – 0, 1


# state


# •


# 2 state variable can be used in


# test benches,where X,Z are not


# required


# •


# 2 state variable in RTL model


# may enable simulators to be


# more efficient


## Page 7: entire design flow

8
Verification with System Verilog

# Verilog Comparison


# • Used for Design Entry


# • Module Level Verification


## Verilog


## System Verilog


# • Module Level Design


# • Constrained Random


# Verification


# • Assertions/Coverage


# • One single language for


# entire design flow


## Page 8: DPI

9
Verification with System Verilog

# Verification Capabilities


# • File I/o


# • Random number generation


# • Fork/join


# • Initial block


# • Task & functions


# • PLI


## Verilog


## System Verilog


# •


# All Verilog features


# •


# Constrained random number


# generation


# •


# Classes and OOP features


# •


# Fork/join_any,fork/join_none


# •


# Final block


# •


# Task & function enhancements


# •


# DPI


## Page 9: – C calls SV functions


# Direct Programming Interface


# • DPI is an interface between System Verilog and C that  allows


# inter-language function calls


# • Simple to used as compared to PLI’s


# • Values can be passed directly


# • Import Functions


# – SV calls C functions


# • Export Functions


# – C calls SV functions


## Page 10: learning 2 languages (Specman E/Vera and PSL)


# Why not VHDL ?


# • VHDL Lacks


# – Constrained Random Generation


# – Functional Coverage


# – Assertions


# • Specman E/Vera


# – Used with VHDL and Verilog for Constrained Random


# generation and Functional Coverage


# • PSL (Property Specification Language)


# – Used for Assertions


# • Learning 1 language (System Verilog) is better than


# learning 2 languages (Specman E/Vera and PSL)


## Page 11: Data Types


# Data Types


### Figures on this page:

- `VHDL_p11_img1.png`

## Page 12: 4-state, 64 bit unsigned


# Integer Data Type


# shortint


# 2-state (1, 0), 16 bit signed


# int


# 2-state, 32 bit signed


# longint


# 2-state, 64 bit signed


# byte


# 2-state, 8 bit signed


# bit


# 2-state, user-defined vector size


# logic


# 4-state (1,0,x,z) user-def


# reg


# 4-state user-defined size


# integer


# 4-state, 32 bit signed


# time


# 4-state, 64 bit unsigned


## Page 13: if (signed'(ubyte)< 150) // ubyte is unsigned


# Signed/Unsigned


# • byte, shortint, int, integer and longint defaults to signed


# – Use unsigned to represent unsigned integer value


## Example: int unsigned ui;


# • bit, reg and logic defaults to unsigned


# • To create vectors, use the following syntax:


## logic [1:0] L; // Creates 2 bit logic vector


# To use these types as unsigned, user has to explicitly declare it as unsigned.


## int unsigned ui;


## int signed si


## byte unsigned ubyte;


# User can cast using signed and unsigned casting.


## if (signed'(ubyte)< 150) // ubyte is unsigned


## Page 14: Void


# • The void data type represents nonexistent data.


# • This type can be specified as the return type of functions to indicate no return value.


## void = function_call();


# Void


## Page 15: '0 is equivalent to making an assignment of 0


# LITERALS


## Integer And Logic Literals


# In verilog , to assign a value to all the bits of vector, user has to specify them explicitly.


## reg[31:0] a = 32'hffffffff;


# Systemverilog Adds the ability to specify unsized literal single bit values with a preceding (').'0, '1, 'X, 'x, 'Z, 'z


# // sets all bits to this value.


## reg[31:0] a = '1;


# 'x is equivalent to Verilog-2001 'bx


# 'z is equivalent to Verilog-2001 'bz


# '1 is equivalent to making an assignment of all 1's


# '0 is equivalent to making an assignment of 0


## Page 16: int n[1:2][1:6] = '{2{'{3{4, 5}}}}; // same as '{'{4,5,4,5,4,5},'{4,5,4,5,4,5}


# LITERALS


## Time Literals


# Time is written in integer or fixed-point format, followed without a space by a time unit (fs ps ns us ms s step).


# EXAMPLE


# 0.1ns


# 40ps


## Array Literals


# Array literals are syntactically similar to C initializers, but with the replicate operator ( {{}} ) allowed.


# EXAMPLE


## int n[1:2][1:3] = '{'{0,1,2},'{3{4}}};


## int n[1:2][1:6] = '{2{'{3{4, 5}}}}; // same as '{'{4,5,4,5,4,5},'{4,5,4,5,4,5}


## Page 17: string myName = "TEST BENCH";


# STRINGS


# •


# In Verilog, string literals are packed arrays of a width that is a multiple of 8 bits which hold ASCII values.


# •


# In Verilog, if a string is larger than the destination string variable, the string is truncated to the left, and the leftmost


# characters will be lost.


# •


# SystemVerilog adds new keyword "string" which is used to declare string data types unlike verilog.


# •


# String data types can be of arbitrary length and no truncation occurs.


## string myName = "TEST BENCH";


## Page 18: 14. str.realtoa(r) stores the ASCII real representation of r into str (inverse of atoreal)


# String Methods


# •


# 1. str.len() returns the length of the string, i.e., the number of characters in the string.


# •


# 2. str.putc(i, c) replaces the ith character in str with the given integral value.


# •


# 3. str.getc(i) returns the ASCII code of the ith character in str.


# •


# 4. str.toupper() returns a string with characters in str converted to uppercase.


# •


# 5. str.tolower() returns a string with characters in str converted to lowercase.


# •


# 6. str.compare(s) compares str and s, and return value. This comparison is case sensitive.


# •


# 7. str.icompare(s) compares str and s, and return value .This comparison is case insensitive.


# •


# 8. str.substr(i, j) returns a new string that is a substring formed by index i through j of str.


# •


# 9. str.atoi() returns the integer corresponding to the ASCII decimal representation in str.


# •


# 10. str.atoreal() returns the real number corresponding to the ASCII decimal representation in str.


# •


# 11. str.itoa(i) stores the ASCII decimal representation of i into str (inverse of atoi).


# •


# 12. str.hextoa(i) stores the ASCII hexadecimal representation of i into str (inverse of atohex).


# •


# 13. str.bintoa(i) stores the ASCII binary representation of i into str (inverse of atobin).


# •


# 14. str.realtoa(r) stores the ASCII real representation of r into str (inverse of atoreal)


## Page 19



### Figures on this page:

- `VHDL_p19_img1.png`
- `VHDL_p19_img2.png`

## Page 20: String Methods


## module str;


## string A;


## string B;


## initial


## begin


# A = "TEST ";


# B = "Bench";


# $display(" %d ",A.len() );


# $display(" %s ",A.getc(5) );


# $display(" %s ",A.tolower);


# $display(" %s ",B.toupper);


# $display(" %d ",B.compare(A) );


# $display(" %d ",A.compare("test") );


# $display(" %s ",A.substr(2,3) ); A = "111";


# $display(" %d ",A.atoi() );


## end


## endmodule


# RESULTS :


## 5


## test


## BENCH


## -18


## -32


## ST


## 111


# String Methods


## Page 21: Syntax: Str[index]


# String Operators


# SystemVerilog provides a set of operators that can be used to manipulate combinations of string variables


# and string literals.


## Equality


# Syntax : Str1 == Str2


## Inequality.


# Syntax: Str1 != Str2


## Comparison.


# Syntax:


# Str1 < Str2


# Str1 <= Str2


# Str1 > Str2


# Str1 >= Str2


## Concatenation.


# Syntax: {Str1,Str2,...,Strn}


## Replication.


# Syntax : {multiplier{Str}}


## Indexing.


# Syntax: Str[index]


## Page 22: Str1 and str3 are not equal


# String Operators


## program main;


## initial


## begin


## string str1,str2,str3;


# str1 = "TEST BENCH";


# str2 = "TEST BENCH";


# str3 = "test bench";


## if(str1 == str2)


# $display(" Str1 and str2 are equal");


## else


# $display(" Str1 and str2 are not equal");


## if(str1 == str3)


# $display(" Str1 and str3 are equal");


## else


# $display(" Str1 and str3 are not equal");


## end


## endprogram


# RESULT


## Str1 and str2 are equal


## Str1 and str3 are not equal


## Page 23: Str1 and str3 are not equal


# String Operators


## program main;


## initial


## begin


## string str1,str2,str3;


# str1 = "TEST BENCH";


# str2 = "TEST BENCH";


# str3 = "test bench";


## if(str1 != str2)


# $display(" Str1 and str2 are not equal");


## else


# $display(" Str1 and str2 are equal");


## if(str1 != str3)


# $display(" Str1 and str3 are not equal");


## else


# $display(" Str1 and str3 are equal");


## end


## endprogram


# RESULT


## Str1 and str2 are equal


## Str1 and str3 are not equal


## Page 24: Str3 >= Str2


# String Operators


## program main;


## initial


## begin


## string Str1,Str2,Str3;


# Str1 = "c";


# Str2 = "d";


# Str3 = "e";


## if(Str1 < Str2)


# $display(" Str1 < Str2 ");


## if(Str1 <= Str2)


# $display(" Str1 <= Str2 ");


## if(Str3 > Str2)


# $display(" Str3 > Str2");


## if(Str3 >= Str2)


# $display(" Str3 >= Str2");


## end


## endprogram


# RESULT


## Str1 < Str2


## Str1 <= Str2


## Str3 > Str2


## Str3 >= Str2


## Page 25: WWW.VITAP.AC.IN


# String Operators


## program main;


## initial


## begin


## string Str1,Str2,Str3,Str4,Str5;


# Str1 = "WWW.";


# Str2 = “VITAP";


# Str3 = "";


# Str4 = “.AC";


# Str5 = ".IN";


# $display(" %s ",{Str1,Str2,Str3,Str4,Str5});


## end


## endprogram


# RESULT


## WWW.VITAP.AC.IN


## Page 26: String Operators


## program main;


## initial


## begin


## string Str1,Str2;


# Str1 = "W";


# Str2 = ".VITAP.AC.IN";


# $display(" %s ",{{3{Str1}},Str2});


## end


## endprogram


# RESULT


## WWW.VITAP.AC.IN


# String Operators


## Page 27: W W W . T E S T B E N C H . I N


# String Operators


## program main;


## initial


## begin


## string Str1;


# Str1 = "WWW.TESTBENCH.IN";


## for(int i =0 ;i < 16 ; i++)


# $write("%s ",Str1[i]);


## end


## endprogram


# RESULT


## W W W . T E S T B E N C H . I N


## Page 28: 5. Typedef.


# USERDEFINED DATATYPES


# Systemverilog allows the user to define datatypes.


# There are different ways to define user defined datatypes.


# 1. Class.


# 2. Enumarations.


# 3. Struct.


# 4. Union.


# 5. Typedef.


## Page 29: enum {IDLE,READY,BUZY} states;


# ENUMARATIONS


# •


# Need for variables that have a limited set of possible values that can be usally referred to by name.


# •


# There's a specific facility, called an enumeration in SystemVerilog .


# •


# Enumerated data types assign a symbolic name to each legal value taken by the data type.


## enum {IDLE,READY,BUZY} states;


## Page 30: a member of the enumeration, the name() method returns the empty string.


# ENUMARATIONS


# •


# SystemVerilog includes a set of specialized methods to enable iterating over the values of enumerated.


# The first() method returns the value of the first member of the enumeration.


# The last() method returns the value of the last member of the enumeration.


# The next() method returns the Nth next enumeration value (default is the next one) starting from the current


# value of the given variable.


# The prev() method returns the Nth previous enumeration value (default is the previous one) starting from the


# current value of the given variable.


# The name() method returns the string representation of the given enumeration value. If the given value is not


# a member of the enumeration, the name() method returns the empty string.


## Page 31: blue


# ENUMARATIONS


## module enum_method;


## typedef enum {red,blue,green} colour;


# colour c;


## initial


## begin


# c = c.first();


# $display(" %s ",c.name);


# c = c.next();


# $display(" %s ",c.name);


# c = c.last();


# $display(" %s ",c.name);


# c = c.prev();


# $display(" %s ",c.name);


## end


## endmodule


# RESULTS :


## red


## blue


## green


## blue


## Page 32: enum integer {IDLE, XX='x, S1='b01, S2='b10} state, next;


# ENUMARATIONS


## module enum_method;


## typedef enum {red,blue,green} colour;


# colour c,d;


## int i;


## initial


## begin


# $display("%s",c.name());


# d = c;


# $display("%s",d.name());


# d = colour'(c + 1); // use casting


# $display("%s",d.name());


# i = d; // automatic casting


# $display("%0d",i);


# c = colour'(i);


# $display("%s",c.name());


## end


## endmodule


# RESULT


## red


## red


## blue


## 1


## blue


# TIP: If you want to use X or Z as enum values, then define


# it using 4-state data type explicitly.


## enum integer {IDLE, XX='x, S1='b01, S2='b10} state, next;


## Page 33: $display(" a value is %d ",my_data_struct.a);


# STRUCTURES AND UNIOUNS


## Structure:


# •


# The disadvantage of arrays is that all the elements stored in then are to be of the same data type.


# •


# If we need to use a collection of different data types, it is not possible using an array. When we require using a


# collection of different data items of different data types we can use a structure.


# •


# Structure is a method of packing data of different types.


## struct {


## int a;


## byte b;


## bit [7:0] c;


# } my_data_struct;


# The structured variables can be accessed using the variable name "my_data_struct".


# my_data_struct.a = 123;


# $display(" a value is %d ",my_data_struct.a);


## Page 34: my_data_struct = `{a:1234,default:8'h20};


# STRUCTURES AND UNIOUNS


# •


# A structure literal must have a type, which may be either explicitly indicated with a prefix or implicitly indicated by


# an assignment-like context.


## Assignments To Struct Members:


# Structure literals can also use member name and value, or data type and default value.


# my_data_struct = `{1234,8'b10,8'h20};


## my_data_struct = `{a:1234,default:8'h20};


## Page 35



### Figures on this page:

- `VHDL_p35_img1.png`
- `VHDL_p35_img2.png`

## Page 36: "my_data_struct".


# Union


# •


# Unions like structure contain members whose individual data types may differ from one another.


# •


# The members that compose a union all share the same storage area.


# •


# A union allows us to treat the same space in memory as a number of different variables.


## union {


## int a;


## byte b;


## bit [7:0] c;


# } my_data;


# "my_data_union"


# "my_data_struct".


### Figures on this page:

- `VHDL_p36_img1.png`
- `VHDL_p36_img2.png`

## Page 37: } tagged_st; // named structure


# Advantages Of Using Typedef :


# •


# Shorter names are easier to type and reduce typing errors.


# •


# Improves readability by shortening complex declarations.


# •


# Improves understanding by clarifying the meaning of data.


# •


# Changing a data type in one place is easier than changing all of its uses throughout the code.


# •


# Allows defining new data types using structs, unions and Enumerations also.


# •


# Increases reusability.


# •


# Useful is type casting.


## typedef enum {NO, YES} boolean;


## typedef union { int i; shortreal f; } num; // named union


# type


## typedef struct {


## bit isfloat;


## union { int i; shortreal f; } n; // anonymous type


# } tagged_st; // named structure


## Page 38: reg [31:0] registers2 [256]; // register is packed 32 bit wide


# ARRAYS:


# •


# Arrays hold a fixed number of equally-sized data elements.


# •


# Individual elements are accessed by index using a consecutive range of integers.


## Fixed Arrays:


# "Packed array" to refer to the dimensions declared before the object name.


# "unpacked array" refers to the dimensions declared after the object name.


## int Array[8][32]; is the same as: int Array[0:7][0:31];


# // Packed Arrays


## reg [0:10] vari; // packed array of 4-bits


## wire [31:0] [1:0] vari; // 2-dimensional packed array


# // Unpacked Arrays


## wire status [31:0]; // 1 dimensional unpacked array


## wire status [32]; // 1 dimensional unpacked array


## integer matrix[7:0][0:31][15:0]; // 3-dimensional unpacked array of integers


## integer matrix[8][32][16]; // 3-dimensional unpacked array of integers


## reg [31:0] registers1 [0:255]; // unpacked array of 256 registers; each


## reg [31:0] registers2 [256]; // register is packed 32 bit wide


## Page 39: ARRAYS:


# ARRAYS:


### Figures on this page:

- `VHDL_p39_img1.png`

## Page 40



### Figures on this page:

- `VHDL_p40_img1.png`

## Page 41



### Figures on this page:

- `VHDL_p41_img1.png`
- `VHDL_p41_img2.png`
- `VHDL_p41_img3.png`

## Page 42



### Figures on this page:

- `VHDL_p42_img1.png`
- `VHDL_p42_img2.png`
- `VHDL_p42_img3.png`
- `VHDL_p42_img4.png`

## Page 43



### Figures on this page:

- `VHDL_p43_img1.png`
- `VHDL_p43_img2.png`

## Page 44: ‘ADDER’ TestBench Without Monitor, Agent and Scoreboard


## TestBench Architecture


## ‘ADDER’ TestBench Without Monitor, Agent and Scoreboard


### Figures on this page:

- `VHDL_p44_img1.png`

## Page 45: 1. Declaring the fields.


## Transaction Class


# •Fields required to generate the stimulus are declared in the transaction class.


# •Transaction class can also be used as a placeholder for the activity monitored by the monitor on DUT


# signals.


# •So, the first step is to declare the ‘Fields‘ in the transaction class.


# •Below are the steps to write the transaction class.


## 1. Declaring the fields.


### Figures on this page:

- `VHDL_p45_img1.png`

## Page 46: 3. Adding display() method to display Transaction properties.


## 2. To generate the random stimulus, declare the fields as ‘rand‘.


## 3. Adding display() method to display Transaction properties.


### Figures on this page:

- `VHDL_p46_img1.png`
- `VHDL_p46_img2.png`

## Page 47: •Sending the randomized class to driver


## Generator Class


# Generator class is responsible for,


# •Generating the stimulus by randomizing the transaction class


# •Sending the randomized class to driver


### Figures on this page:

- `VHDL_p47_img1.png`

## Page 48: 2. ‘Randomize’ the transaction class,


## 1. Declare the transaction class handle,


## 2. ‘Randomize’ the transaction class,


### Figures on this page:

- `VHDL_p48_img1.png`
- `VHDL_p48_img2.png`

## Page 49: and driver).


## 3. Adding Mailbox and event,


# Mailbox is used to send the randomized transaction to Driver.


# Event to indicate the end of packet generation.


# This involves,


# •Declaring the Mailbox and Event


# •Getting the Mailbox handle from the env class ( because the same mailbox will be shared across generator


# and driver).


### Figures on this page:

- `VHDL_p49_img1.png`

## Page 50: 4. Adding a variable to control the number of packets to be created,


## 4. Adding a variable to control the number of packets to be created,


### Figures on this page:

- `VHDL_p50_img1.png`

## Page 51: completion of the Generation process.


## 5. Adding an event to indicate the completion of the generation process, the event will be triggered on the


# completion of the Generation process.


### Figures on this page:

- `VHDL_p51_img1.png`

## Page 52



### Figures on this page:

- `VHDL_p52_img1.png`

## Page 53: This is a simple interface without modport and clocking block.


## Interface


# Interface will group the signals.


# This is a simple interface without modport and clocking block.


### Figures on this page:

- `VHDL_p53_img1.png`
- `VHDL_p53_img2.png`

## Page 54: Driver Class


## Driver Class


### Figures on this page:

- `VHDL_p54_img1.png`
- `VHDL_p54_img2.png`

## Page 55



### Figures on this page:

- `VHDL_p55_img1.png`

## Page 56



### Figures on this page:

- `VHDL_p56_img1.png`

## Page 57



### Figures on this page:

- `VHDL_p57_img1.png`

## Page 58



### Figures on this page:

- `VHDL_p58_img1.png`

## Page 59



### Figures on this page:

- `VHDL_p59_img1.png`
- `VHDL_p59_img2.png`
- `VHDL_p59_img3.png`

## Page 60



### Figures on this page:

- `VHDL_p60_img1.png`

## Page 61



### Figures on this page:

- `VHDL_p61_img1.png`

## Page 62



### Figures on this page:

- `VHDL_p62_img1.png`

## Page 63



### Figures on this page:

- `VHDL_p63_img1.png`

## Page 64



### Figures on this page:

- `VHDL_p64_img1.png`

## Page 65



### Figures on this page:

- `VHDL_p65_img1.png`
- `VHDL_p65_img2.png`

## Page 66: Accessing "Array[0][3][6]" will access one element.


# Accessing Individual Elements Of Multidimensional


# Arrays:


## bit [3:4][5:6]Array [0:2];


# Accessing "Array[2]" will access 4 elements


# Array[2][3][5],Array[2][3][6],Array[2][4][5] and Array[2][4][6].


# Accessing "Array[1][3]" will access 2 elements Array[1][3][5] and Array[1][3][6].


# Accessing "Array[0][3][6]" will access one element.


### Figures on this page:

- `VHDL_p66_img1.png`

## Page 67: required.


# DYNAMIC ARRAYS:


# A dynamic array dimensions are specified by the empty square brackets [ ] .


# The  new() function is used to allocate a size for the array and initialize its elements if


# required.


### Figures on this page:

- `VHDL_p67_img1.png`

## Page 68



### Figures on this page:

- `VHDL_p68_img1.png`
- `VHDL_p68_img2.png`

## Page 69: Dynamic Array Methods


## Dynamic Array Methods


### Figures on this page:

- `VHDL_p69_img1.png`

## Page 70: 0


# DYNAMIC ARRAYS:


# RESULT


## 4


## 8


## 0


### Figures on this page:

- `VHDL_p70_img1.png`

## Page 71



### Figures on this page:

- `VHDL_p71_img1.png`
- `VHDL_p71_img2.png`

## Page 72: contents into the new one after creation.


## How to add new items to a dynamic array ?


# •


# Many times we may need to add new elements to an  existing dyanmic array without losing its original


# contents.


# •


# Since the new() operator is used to allocate a particular size for the array, we also have to copy the old array


# contents into the new one after creation.


### Figures on this page:

- `VHDL_p72_img1.png`

## Page 73



### Figures on this page:

- `VHDL_p73_img1.png`
- `VHDL_p73_img2.png`

## Page 74: • If an argument is not provided, item is the name used by default.


## System Verilog Array Manipulation


# • There are many built-in methods in SystemVerilog to help  in array searching and ordering.


# • Array manipulation methods simply iterate through the array elements and each element is


# used to evaluate the expression specified by the with clause


# • The iterator argument specifies a local variable that can be used within the with expression


# to refer to the current element in the iteration.


# • If an argument is not provided, item is the name used by default.


## Page 75: Mandatory 'with' clause


## Mandatory 'with' clause


### Figures on this page:

- `VHDL_p75_img1.png`

## Page 76



### Figures on this page:

- `VHDL_p76_img1.png`
- `VHDL_p76_img2.png`

## Page 77: Optional 'with' clause


## Optional 'with' clause


### Figures on this page:

- `VHDL_p77_img1.png`
- `VHDL_p77_img2.png`

## Page 78



### Figures on this page:

- `VHDL_p78_img1.png`
- `VHDL_p78_img2.png`

## Page 79: Array Ordering Methods


## Array Ordering Methods


### Figures on this page:

- `VHDL_p79_img1.png`

## Page 80



### Figures on this page:

- `VHDL_p80_img1.png`
- `VHDL_p80_img2.png`

## Page 81: Array Reduction Methods


## Array Reduction Methods


### Figures on this page:

- `VHDL_p81_img1.png`

## Page 82



### Figures on this page:

- `VHDL_p82_img1.png`
- `VHDL_p82_img2.png`

## Page 83: A queue is distinguished by it's specification of the size using $ operator.


## SystemVerilog Queue


# • A SystemVerilog queue is a First In First Out scheme which can have a variable size to


# store elements of the same data type


# • It is similar to a one-dimensional unpacked array that grows and shrinks automatically.


# • They can also be manipulated by indexing, concatenation and slicing operators. Queues


# can be passed to tasks/functions as ref or non-ref arguments.


## Syntax and Usage


## A queue is distinguished by it's specification of the size using $ operator.


## Page 84



### Figures on this page:

- `VHDL_p84_img1.png`

## Page 85



### Figures on this page:

- `VHDL_p85_img1.png`

## Page 86



### Figures on this page:

- `VHDL_p86_img1.png`

## Page 87



### Figures on this page:

- `VHDL_p87_img1.png`

## Page 88: SystemVerilog Loops


## SystemVerilog Loops


### Figures on this page:

- `VHDL_p88_img1.png`
- `VHDL_p88_img2.png`

## Page 89: simulation time.


## forever


# This is an infinite loop, just like while (1) .


# Note that your simulation will hang unless you include a time delay inside the forever block to advance


# simulation time.


### Figures on this page:

- `VHDL_p89_img1.png`
- `VHDL_p89_img2.png`

## Page 90: Used to repeat statements in a block a certain number of times.


## repeat


# Used to repeat statements in a block a certain number of times.


### Figures on this page:

- `VHDL_p90_img1.png`
- `VHDL_p90_img2.png`

## Page 91: It'll repeat  the block as long as the condition is true.


## while


# It'll repeat  the block as long as the condition is true.


### Figures on this page:

- `VHDL_p91_img1.png`

## Page 92: For


## For


### Figures on this page:

- `VHDL_p92_img1.png`
- `VHDL_p92_img2.png`

## Page 93: while loop


## while loop


### Figures on this page:

- `VHDL_p93_img1.png`
- `VHDL_p93_img2.png`

## Page 94: do while loop


## do while loop


### Figures on this page:

- `VHDL_p94_img1.png`
- `VHDL_p94_img2.png`

## Page 95: SystemVerilog 'break’and 'continue'


# /


# SystemVerilog 'break’and 'continue'


### Figures on this page:

- `VHDL_p95_img1.png`
- `VHDL_p95_img2.png`

## Page 96



### Figures on this page:

- `VHDL_p96_img1.png`
- `VHDL_p96_img2.png`

## Page 97: report an erorr when there is more than 1 match found in the if else conditions


## SystemVerilog 'unique’ and 'priority' if-else


## unique-if, unique0-if


# unique-if evaluates conditions in any order and does the following :


# •


# report an error when none of the if conditions match unless there is an explicit else .


# •


# report an erorr when there is more than 1 match found in the if else conditions


### Figures on this page:

- `VHDL_p97_img1.png`

## Page 98: No else block for unique-if


## No else block for unique-if


### Figures on this page:

- `VHDL_p98_img1.png`
- `VHDL_p98_img2.png`

## Page 99: Multiple matches in unique-if


## Multiple matches in unique-if


### Figures on this page:

- `VHDL_p99_img1.png`
- `VHDL_p99_img2.png`

## Page 100: None of the conditions are true or if there's no else clause to the final if construct


## priority-if


# priority-if evaluates all conditions in sequential order and a violation is reported when:


# None of the conditions are true or if there's no else clause to the final if construct


## Page 101



### Figures on this page:

- `VHDL_p101_img1.png`
- `VHDL_p101_img2.png`

## Page 102: Exit after first match in priority-if


## Exit after first match in priority-if


### Figures on this page:

- `VHDL_p102_img1.png`
- `VHDL_p102_img2.png`

## Page 103: unique : No items match for given expression


## unique : No items match for given expression


### Figures on this page:

- `VHDL_p103_img1.png`
- `VHDL_p103_img2.png`

## Page 104: unique : More than one case item matches


## unique : More than one case item matches


### Figures on this page:

- `VHDL_p104_img1.png`
- `VHDL_p104_img2.png`

## Page 105: priority case


## priority case


### Figures on this page:

- `VHDL_p105_img1.png`
- `VHDL_p105_img2.png`

## Page 106: of the statements based on when child threads finish.


## SystemVerilog fork join


# • SystemVerilog provides support for parallel or concurrent threads through fork join


# construct.


# • Multiple procedural blocks can be spawned off at the same time using fork and join .


# • There are variations to fork join that allow the main thread to continue executing rest


# of the statements based on when child threads finish.


## Page 107



### Figures on this page:

- `VHDL_p107_img1.png`

## Page 108



### Figures on this page:

- `VHDL_p108_img1.png`

## Page 109: fork-join will be unblocked at 20ns.


## SystemVerilog Fork Join


## fork join


# Fork-Join will start all the processes inside it parallel and


# wait for the completion of all the processes.


# •


# fork block will be blocked until the completion of


# process-1 and Process-2.


# •


# Both process-1 and Process-2 will start at the same


# time,


# •


# Process-1 will finish at 5ns and Process-2 will finish at


# 20ns.


# •


# fork-join will be unblocked at 20ns.


### Figures on this page:

- `VHDL_p109_img1.png`

## Page 110



### Figures on this page:

- `VHDL_p110_img1.png`
- `VHDL_p110_img2.png`

## Page 111: fork-join_any will be unblocked at 5ns


## SystemVerilog fork join_any


# Fork-Join_any will be unblocked after the completion of any of the Processes.


# fork block will be blocked until the completion of any of


# the Process Process-1 or Process-2.


# fork-join_any will be unblocked at 5ns


### Figures on this page:

- `VHDL_p111_img1.png`

## Page 112



### Figures on this page:

- `VHDL_p112_img1.png`
- `VHDL_p112_img2.png`

## Page 113: completion of the Process inside the fork-join_none.


## SystemVerilog fork join_none


## fork join_none


# •


# As in the case of Fork-Join and Fork-Join_any fork block is blocking, but in case of Fork-Join_none fork


# block will be non-blocking.


# •


# Processes inside the fork-join_none block will be started at the same time, fork block will not wait for the


# completion of the Process inside the fork-join_none.


### Figures on this page:

- `VHDL_p113_img1.png`

## Page 114



### Figures on this page:

- `VHDL_p114_img1.png`
- `VHDL_p114_img2.png`

## Page 115: wait fork; causes the process to block until the completion of all processes started from fork blocks.


## wait fork


# wait fork; causes the process to block until the completion of all processes started from fork blocks.


## Page 116



### Figures on this page:

- `VHDL_p116_img1.png`
- `VHDL_p116_img2.png`

## Page 117



### Figures on this page:

- `VHDL_p117_img1.png`
- `VHDL_p117_img2.png`

## Page 118



### Figures on this page:

- `VHDL_p118_img1.png`

## Page 119



### Figures on this page:

- `VHDL_p119_img1.png`

## Page 120



### Figures on this page:

- `VHDL_p120_img1.png`
- `VHDL_p120_img2.png`

## Page 121



### Figures on this page:

- `VHDL_p121_img1.png`

## Page 122



## Page 123



### Figures on this page:

- `VHDL_p123_img1.png`

## Page 124: • default arguments type is logic if no type has been specified


## SystemVerilog Tasks


# •


# Tasks and Functions provide a means of splitting code into small parts.


# •


# A Task can contain a declaration of parameters, input arguments, output arguments, in-out arguments,


# registers, events, and zero or more behavioral statements.


# SystemVerilog task can be,


# • Static: Static tasks share the same storage space for all task calls.


# • Automatic: Automatic tasks allocate unique, stacked storage for each task call


# SystemVerilog allows,


# • to declare an automatic variable in a static task


# • to declare a static variable in an automatic task


# • more capabilities for declaring task ports


# • multiple statements within task without requiring a begin...end or fork...join block


# • returning from the task before reaching the end of the task


# • passing values by reference, value, names, and position


# • default argument values


# • the default direction of argument is input if no direction has been specified


# • default arguments type is logic if no type has been specified


## Page 125



### Figures on this page:

- `VHDL_p125_img1.png`
- `VHDL_p125_img2.png`

## Page 126



### Figures on this page:

- `VHDL_p126_img1.png`
- `VHDL_p126_img2.png`

## Page 127: default arguments type is logic if no type has been specified.


## SystemVerilog Functions


# A Function can contain declarations of range, returned type, parameters, input arguments, registers, and


# events.


# •


# A function without a range or return type declaration returns a one-bit value


# •


# Any expression can be used as a function call argument


# •


# Functions cannot contain any time-controlled statements, and they cannot enable tasks


# •


# Functions can return only one value


# SystemVerilog allows,


# •


# to declare an automatic variable in static functions


# •


# to declare the static variable in automatic functions


# •


# more capabilities for declaring function ports


# •


# multiple statements within a function without requiring a begin...end or fork...join block


# •


# returning from the function before reaching the end of the function


# •


# Passing values by reference, value, names, and position


# •


# default argument values


# •


# function output and inout ports


# •


# the default direction of argument is input if no direction has been specified.


# •


# default arguments type is logic if no type has been specified.


## Page 128



### Figures on this page:

- `VHDL_p128_img1.png`
- `VHDL_p128_img2.png`

## Page 129



### Figures on this page:

- `VHDL_p129_img1.png`
- `VHDL_p129_img2.png`

## Page 130: function with return value with the return keyword


## function with return value with the return keyword


### Figures on this page:

- `VHDL_p130_img1.png`
- `VHDL_p130_img2.png`

## Page 131: Void function


## Void function


### Figures on this page:

- `VHDL_p131_img1.png`
- `VHDL_p131_img2.png`

## Page 132



### Figures on this page:

- `VHDL_p132_img1.png`
- `VHDL_p132_img2.png`

## Page 133: subroutine


## argument pass by value


# In argument pass by value,


# •


# the argument passing mechanism works by copying each argument into the subroutine area.


# •


# if any changes to arguments within the subroutine, those changes will not be visible outside the


# subroutine


### Figures on this page:

- `VHDL_p133_img1.png`
- `VHDL_p133_img2.png`

## Page 134: To indicate argument pass by reference, the argument declaration is preceded by keyword ref.


## argument pass by reference


# •


# In pass by reference, a reference to the original argument is passed to the subroutine.


# •


# As the argument within a subroutine is pointing to an original argument, any changes to the argument within


# subroutine will be visible outside.


# •


## To indicate argument pass by reference, the argument declaration is preceded by keyword ref.


### Figures on this page:

- `VHDL_p134_img1.png`
- `VHDL_p134_img2.png`

## Page 135: Value of z = 25


# Value of z = 25


### Figures on this page:

- `VHDL_p135_img1.png`

## Page 136: argument.


## argument pass by name


# In argument pass by name, arguments can be passed in any order by specifying the name of the subroutine


# argument.


### Figures on this page:

- `VHDL_p136_img1.png`
- `VHDL_p136_img2.png`

## Page 137: Class Declaration


## SystemVerilog Class


# • A class is a user-defined data type that includes data (class properties), functions and tasks that operate on


# data.


# • functions and tasks are called as methods, both are members of the class.


# • classes allow objects to be dynamically created, deleted, assigned and accessed via object handles.


## Class Declaration


### Figures on this page:

- `VHDL_p137_img1.png`

## Page 138: Accessing class properties and methods


## Class declaration/Class Instance


# sv_class class_1;


## Object Creation


## Accessing class properties and methods


### Figures on this page:

- `VHDL_p138_img1.png`
- `VHDL_p138_img2.png`
- `VHDL_p138_img3.png`

## Page 139



### Figures on this page:

- `VHDL_p139_img1.png`
- `VHDL_p139_img2.png`

## Page 140: this is a pre-defined class handle referring to the object from which it is used, calling this.variable means object.v


## SystemVerilog this keyword


# •


# this keyword is used to refer to class properties.


# •


# this keyword is used to unambiguously refer to class properties or methods of the current instance.


# •


# this is a pre-defined class handle referring to the object from which it is used, calling this.variable means object.v


### Figures on this page:

- `VHDL_p140_img1.png`

## Page 141



### Figures on this page:

- `VHDL_p141_img1.png`
- `VHDL_p141_img2.png`
- `VHDL_p141_img3.png`

## Page 142



### Figures on this page:

- `VHDL_p142_img1.png`
- `VHDL_p142_img2.png`
- `VHDL_p142_img3.png`

## Page 143: On calling the new method it allocates the memory and returns the address to the class handle.


## SystemVerilog Class Constructors


# •


# The new function is called as class constructor.


# •


# On calling the new method it allocates the memory and returns the address to the class handle.


### Figures on this page:

- `VHDL_p143_img1.png`

## Page 144: pass arguments to the constructor, which allows run-time customization of an object.


# •The new operation is defined as a function with no return type


# •every class has a built-in new method, calling the constructor of class without the


# explicit definition of the new method will invoke the default built-in new method


# •specifying return type to the constructor shall give a compilation error (even specifying


# void shall give a compilation error)


# •The constructor can be used for initializing the class properties. In case of any


# initialization required, those can be placed in the constructor and It is also possible to


# pass arguments to the constructor, which allows run-time customization of an object.


## Page 145



### Figures on this page:

- `VHDL_p145_img1.png`

## Page 146



### Figures on this page:

- `VHDL_p146_img1.png`
- `VHDL_p146_img2.png`
- `VHDL_p146_img3.png`

## Page 147: pkt_2


## System Verilog Class Assignment


# Object will be created only after doing new to an class handle,


# •an object is created only for pkt_1, pkt_2 is just a handle to the packet


# •pkt_1 is assigned to the pkt_2. so only one object has been created, pkt_1 and pkt_2 are two handles both are


# pointing to the same object


## •As both the handles are pointing to the same object any changes made with respect to pkt_1 will reflect on


# pkt_2


### Figures on this page:

- `VHDL_p147_img1.png`

## Page 148



### Figures on this page:

- `VHDL_p148_img1.png`

## Page 149



### Figures on this page:

- `VHDL_p149_img1.png`
- `VHDL_p149_img2.png`

## Page 150



### Figures on this page:

- `VHDL_p150_img1.png`

## Page 151: •If the class is derived from a derived class, then it is referred to as Multilevel inheritance


## SystemVerilog Inheritance


# •


# Inheritance is an OOP concept that allows the user to create classes that are built upon existing classes.


# •


# The new class will be with new properties and methods along with having access to all the properties and methods of


# the original class.


# •


# Inheritance is about inheriting base class members to the extended class.


# •New classes can be created based on existing classes, this is referred to as class inheritance


# •A derived class by default inherits the properties and methods of its parent class


# •An inherited class is called a subclass of its parent class


# •A derived class may add new properties and methods, or modify the inherited properties and methods


# •Inheritance allows re-usability. i.e. derived class by default includes the properties and methods, which


# is ready to use


# •If the class is derived from a derived class, then it is referred to as Multilevel inheritance


## Page 152



### Figures on this page:

- `VHDL_p152_img1.png`

## Page 153: •The child class is also known as an extended class, derived class, subclass


## Inheritance Terminology


## Parent Class


# •It's an existing class;


# •The class whose features are inherited


# •The parent class is also known as a base class, superclass


## Child Class


# •It's an extended class;


# •The class that inherits the other class is known as subclass


# •The child class is also known as an extended class, derived class, subclass


## Page 154: parent class.


# Though the addr is not declared in child_class, it is accessible. because it is inherited from the


# parent class.


### Figures on this page:

- `VHDL_p154_img1.png`
- `VHDL_p154_img2.png`

## Page 155: or different forms of the same method.


## Polymorphism in SystemVerilog


# •


# Polymorphism means many forms.


# •


# Polymorphism in SystemVerilog provides an ability to an object to take on many forms.


# •


# Method handle of super-class can be made to refer to the subclass method, this allows polymorphism


# or different forms of the same method.


### Figures on this page:

- `VHDL_p155_img1.png`

## Page 156: with display method overridden in it.


# Writing three extended classes of base_class,


# with display method overridden in it.


### Figures on this page:

- `VHDL_p156_img1.png`
- `VHDL_p156_img2.png`
- `VHDL_p156_img3.png`

## Page 157: method, this is called polymorphism.


# Though all the methods are called using


# base_class handle, different methods are getting


# called. this shows the many forms of the same


# method, this is called polymorphism.


### Figures on this page:

- `VHDL_p157_img1.png`
- `VHDL_p157_img2.png`
- `VHDL_p157_img3.png`
- `VHDL_p157_img4.png`

## Page 158



### Figures on this page:

- `VHDL_p158_img1.png`
- `VHDL_p158_img2.png`
- `VHDL_p158_img3.png`

## Page 159: class members.


## SystemVerilog Overriding class members


# •


# Base class or parent class properties and methods can be overridden in the child class or extended class.


# •


# Defining the class properties and methods with the same name as parent class in the child class will override the


# class members.


### Figures on this page:

- `VHDL_p159_img1.png`
- `VHDL_p159_img2.png`

## Page 160: With super keyword, it is allowed to access the class members of parent class which is only one level up


## SystemVerilog Super keyword


## The super keyword is used in a derived class to refer to the members of the parent class.


# •


# When class members are overridden in the derived class, It is necessary to use the super keyword to


# access members of a parent class


# •


# With super keyword, it is allowed to access the class members of parent class which is only one level up


### Figures on this page:

- `VHDL_p160_img1.png`

## Page 161



### Figures on this page:

- `VHDL_p161_img1.png`
- `VHDL_p161_img2.png`

## Page 162



### Figures on this page:

- `VHDL_p162_img1.png`
- `VHDL_p162_img2.png`

## Page 163: •Dynamic casting


# • SystemVerilog casting means the conversion of one data type to another datatype.


# •


# During value or variable assignment to a variable, it is required to assign value or variable of the same


# data type.


# • Some situations need assignment of different data type, in such situations, it is necessary to convert data


# type and assign. Otherwise, the assignment of different data type results in a compilation error.


## SystemVerilog Casting


# •Static casting


# •Dynamic casting


## Page 164: concatenation or replication braces


## Static casting


# •SystemVerilog static casting is not applicable to OOP


# •Static casting converts one data type to another compatible data types (example string to


# int)


# •As the name says 'Static', the conversion data type is fixed.


# •Static casting will be checked during compilation, so there won't be run-time checking and


# error reporting


# •Casting is applicable to value, variable or to an expression


# •A data type can be changed by using a cast ( ' ) operation


# •The vale/variable/expression to be cast must be enclosed in parentheses or within


# concatenation or replication braces


## Page 165



### Figures on this page:

- `VHDL_p165_img1.png`
- `VHDL_p165_img2.png`

## Page 166: type for the assignment.


## Dynamic casting


# • Dynamic casting is used to, safely cast a super-class pointer (reference) into a subclass pointer (reference)


# in a class hierarchy


# •Dynamic casting will be checked during run time, an attempt to cast an object to an incompatible object will


# result in a run-time error


# •Dynamic casting is done using the $cast(destination, source) method


# •With  $cast compatibility of the assignment will not be checked during compile time, it will be checked


# during run-time


# parent_class = child_class; //allowed


# child_class


# = parent_class; //not-allowed


# parent_class = child_class ;


# child_class


# = parent_class; //allowed because parent_class is pointing to child_class.


# Though parent_class is pointing to the child_class, we will get a compilation error saying its not compatible


# type for the assignment.


## Page 167: casting.


## $cast(child_class,parent_class);


# • In the above parent class assignment with child class example. type of parent class is changing dynamically i.e


# on declaration it is of parent class type, on child class assignment it is of child class type.


# • Parent class handle during $cast execution is considered for the assignment, so it referred to as dynamic


# casting.


## Page 168: assigning child class handle to parent class handle


## assigning child class handle to parent class handle


### Figures on this page:

- `VHDL_p168_img1.png`
- `VHDL_p168_img2.png`

## Page 169: assigning parent class handle to child class handle


## assigning parent class handle to child class handle


### Figures on this page:

- `VHDL_p169_img1.png`
- `VHDL_p169_img2.png`

## Page 170: assigning parent class handle to child class handle


## assigning parent class handle to child class handle


### Figures on this page:

- `VHDL_p170_img1.png`
- `VHDL_p170_img2.png`

## Page 171: Use of $cast or casting


## Use of $cast or casting


### Figures on this page:

- `VHDL_p171_img1.png`
- `VHDL_p171_img2.png`
- `VHDL_p171_img3.png`

## Page 172: local integer x;


## Systemverilog Data Hiding and Encapsulation


# The technique of hiding the data within the class and making it available only through the methods, is


# known as encapsulation.


## Access Control


# •local


# •protected


## local class members


## External access to the class members can be avoided by declaring members as local.


# Any violation could result in a compilation error.


## Syntax:


# local integer x;


## Page 173: Accessing local variable outside the class ( Not allowed )


## Accessing local variable outside the class ( Not allowed )


### Figures on this page:

- `VHDL_p173_img1.png`
- `VHDL_p173_img2.png`

## Page 174: Accessing local variable within the class ( Allowed )


## Accessing local variable within the class ( Allowed )


### Figures on this page:

- `VHDL_p174_img1.png`
- `VHDL_p174_img2.png`

## Page 175: protected integer x;


## Protected class members


# •


# In some use cases, it is required to access the class members only by the derived class’s,


# •


# this can be done by prefixing the class members with the protected keyword.


# •


# Any violation could result in a compilation error.


## Syntax:


# protected integer x;


## Page 176: Accessing a protected variable outside the class ( Not allowed )


## Accessing a protected variable outside the class ( Not allowed )


### Figures on this page:

- `VHDL_p176_img1.png`
- `VHDL_p176_img2.png`
- `VHDL_p176_img3.png`

## Page 177: Accessing a protected variable in the extended class ( allowed )


## Accessing a protected variable in the extended class ( allowed )


### Figures on this page:

- `VHDL_p177_img1.png`
- `VHDL_p177_img2.png`
- `VHDL_p177_img3.png`

## Page 178: a method declaration.


## Abstract Class


# •


# SystemVerilog class declared with the keyword virtual is referred to as an abstract class.


# •


# An abstract class sets out the prototype for the sub-classes.


# •


# An abstract class cannot be instantiated, it can only be derived.


# •


# An abstract class can contain methods for which there are only a prototype and no implementation, just


# a method declaration.


### Figures on this page:

- `VHDL_p178_img1.png`
- `VHDL_p178_img2.png`

## Page 179: virtual class leads to a compilation error.


## Instantiating virtual class


# An abstract class can only be derived, creating an object of a


# virtual class leads to a compilation error.


### Figures on this page:

- `VHDL_p179_img1.png`
- `VHDL_p179_img2.png`

## Page 180: and creating it.


## Deriving virtual class


# An abstract class is derived and written extend the class


# and creating it.


### Figures on this page:

- `VHDL_p180_img1.png`
- `VHDL_p180_img2.png`

## Page 181: Task declared with a virtual keyword before the task keyword is referred to as virtual task


## Virtual Methods in SystemVerilog


# SystemVerilog Methods declared with the keyword virtual are referred to as virtual methods.


## Virtual Methods,


# Virtual Functions


# •Virtual Tasks


## Virtual Functions


# A function declared with a virtual keyword before the function keyword is referred to as virtual Function


## Virtual Task


# Task declared with a virtual keyword before the task keyword is referred to as virtual task


## Page 182: assigned to the base class handle.


## About Virtual Method


# In a virtual method,


# If the base_class handle is referring to the extended class, then the extended class method handle will get


# assigned to the base class handle.


### Figures on this page:

- `VHDL_p182_img1.png`

## Page 183: the base class method.


# the method inside the base class is declared without


# a virtual keyword, on calling method of the base


# class which is pointing to the extended class will call


# the base class method.


### Figures on this page:

- `VHDL_p183_img1.png`
- `VHDL_p183_img2.png`

## Page 184: method.


# the method inside the base class is declared with a virtual


# keyword, on calling method of the base class which is


# pointing to an extended class will call the extended class


# method.


### Figures on this page:

- `VHDL_p184_img1.png`
- `VHDL_p184_img2.png`

## Page 185: derived classes


## Scope Resolution Operator ::


## •The class scope operator :: is used to specify an identifier defined within the scope of a class.


# •Classes and other scopes can have the same identifiers


# •The scope resolution operator uniquely identifies a member of a particular class


# Class Resolution operator allows access to static members (class properties and methods) from


# outside the class, as well as access to public or protected elements of super classes from within the


# derived classes


## Page 186: the class by using class resolution operator ::


# A static member of the class is accessed outside


# the class by using class resolution operator ::


### Figures on this page:

- `VHDL_p186_img1.png`
- `VHDL_p186_img2.png`

## Page 187: is used to provide a forward declaration of the class.


## SystemVerilog typedef class


# A typedef is used to provide a forward declaration of the class.


# In some cases, the class needs to be instantiated before the class declaration. In these kinds of situations, the typedef


# is used to provide a forward declaration of the class.


### Figures on this page:

- `VHDL_p187_img1.png`

## Page 188



### Figures on this page:

- `VHDL_p188_img1.png`
- `VHDL_p188_img2.png`

## Page 189



### Figures on this page:

- `VHDL_p189_img1.png`
- `VHDL_p189_img2.png`

## Page 190: Random System Methods $urandom(), $random and $urandom_range();


## SystemVerilog Randomization and SystemVerilog Constraint


## •Randomization


## •Disable Randomization


## •Randomization methods


## •Constraints


# •


## Constraint Block, External Constraint Blocks


## and Constraint Inheritance


# •


## Inside Operator


# •


## Weighted Distribution


# •


## Implication Operator and if-else


# •


## Iterative in Constraint Blocks (foreach


## constraints)


# •


## Disable Constraint


# •


## Static Constraints


# •


## In line Constraints


# •


## Functions in Constraints


# •


## Soft Constraints


# •


## Unique Constraints


# •


## Bidirectional Constraints


# •


## Solve-Before


## Random System Methods $urandom(), $random and $urandom_range();


## Page 191: •object handle's


## randomization in SystemVerilog


# • Randomization is the process of making something random;


# • SystemVerilog randomization is the process of generating random values to a variable.


# • Verilog has a $random method for generating the random integer values.


# • This is good for randomizing the variables alone, but it is hard to use in case of class object


# randomization.


# •


# for easy randomization of class properties, SystemVerilog provides rand keyword and randomize()


# method.


# •Following types can be declared as rand and randc,


# singular variables of any integral type


# •arrays


# •arrays size


# •object handle's


## Page 192: In order to randomize the object variables, the user needs to call randomize() method.


## rand keyword


# •


# Variables declared with the rand keyword are standard random variables.


# •


# Their values are uniformly distributed over their range.


# addr is a 4-bit unsigned integer with a range of 0 to 15. on randomization this variable shall be assigned any


# value in the range 0 to 15 with equal probability.


## randc keyword


# •


# randc is random-cyclic.


# •


# For the variables declared with the randc keyword, on randomization variable values don't repeat a random


# value until every possible value has been assigned.


# In order to randomize the object variables, the user needs to call randomize() method.


### Figures on this page:

- `VHDL_p192_img1.png`
- `VHDL_p192_img2.png`
- `VHDL_p192_img3.png`

## Page 193



### Figures on this page:

- `VHDL_p193_img1.png`
- `VHDL_p193_img2.png`

## Page 194: the rand_mode method returns 1 if randomization is enabled else returns 0


## Disable randomization


# The rand_mode() method is used to disable the randomization of a variable declared with the rand/randc


# keyword.


# •


# rand_mode(1) means randomization enabled


# •


# rand_mode(0) means randomization disabled


# •


# The default value of rand_mode is 1, i.e enabled


# •


# Once the randomization is disabled, it is required to make rand_mode(1) enable back the randomization


# •


# rand_mode can be called as SystemVerilog method, the randomization enables/disable status of a variable can


# be obtained by calling vairble.rand_mode().


# •


# the rand_mode method returns 1 if randomization is enabled else returns 0


### Figures on this page:

- `VHDL_p194_img1.png`

## Page 195



### Figures on this page:

- `VHDL_p195_img1.png`
- `VHDL_p195_img2.png`

## Page 196



### Figures on this page:

- `VHDL_p196_img1.png`
- `VHDL_p196_img2.png`

## Page 197: randomization disable for all class variable


## randomization disable for all class variable


### Figures on this page:

- `VHDL_p197_img1.png`
- `VHDL_p197_img2.png`

## Page 198: block defined outside the class is called as extern constraint block


## Constrained randomization


# •


# In some situations it is required to control the values getting assigned on randomization, this can be achieved by writing


# constraints


# •


# By writing constraints to a random variable, the user can get specific value on randomization.


# •


# constraints to a random variable shall be written in constraint blocks.


## Constraint blocks


# •


# Constraint blocks are class members like tasks, functions, and variables


# •


# Constraint blocks will have a unique name within a class


# •


# Constraint blocks consist of conditions or expressions to limit or control the values for a random


# variable


# •


# Constraint blocks are enclosed within curly braces { }


# •


# Constraint blocks can be defined inside the class or outside the class like extern methods, constraint


# block defined outside the class is called as extern constraint block


### Figures on this page:

- `VHDL_p198_img1.png`

## Page 199: Constraint block inside the class


## Constraint block inside the class


### Figures on this page:

- `VHDL_p199_img1.png`
- `VHDL_p199_img2.png`

## Page 200: Constraint block outside the class


## Constraint block outside the class


### Figures on this page:

- `VHDL_p200_img1.png`
- `VHDL_p200_img2.png`

## Page 201: Constraint blocks can be overridden by writing constraint block with the same name as in parent class.


## Constraint Inheritance


# •


# Like class members, constraints also will get inherited from parent class to child class.


# •


# Constraint blocks can be overridden by writing constraint block with the same name as in parent class.


## Page 202



### Figures on this page:

- `VHDL_p202_img1.png`
- `VHDL_p202_img2.png`

## Page 203: SystemVerilog inside operator, random variables will get values specified within the inside block.


## Constraint inside SystemVerilog


# •


# During randomization, it might require to randomize the variable within a range of values or with inset of


# values or other than a range of values.


# •


# this can be achieved by using constraint inside operator.


# SystemVerilog inside operator, random variables will get values specified within the inside block.


### Figures on this page:

- `VHDL_p203_img1.png`

## Page 204



### Figures on this page:

- `VHDL_p204_img1.png`

## Page 205: constraint inside example


## constraint inside example


### Figures on this page:

- `VHDL_p205_img1.png`
- `VHDL_p205_img2.png`

## Page 206: inverted inside example


## inverted inside example


### Figures on this page:

- `VHDL_p206_img1.png`
- `VHDL_p206_img2.png`

## Page 207: •the sum of weights need not be a 100


## dist Constraint in SystemVerilog


# •


# Constraint provides control on randomization, from which the user can control the values on


# randomization.


# •


# it would be good if it's possible to control the occurrence or repetition of the same value on


# randomization.


# •


# its possible, with dist operator, some values can be allocated more often to a random variable.


# •


## this is called a weighted distribution.


# •


# dist is an operator, it takes a list of values and weights, separated by := or :/ operator.


## weighted distribution


# As the name says, in weighted distribution weight will be specified to the values inside the constraint block.


# Value with the more weight will get allocated more often to a random variable.


# Value - desired value to a random variable


# weight - indicates how often the value needs to be considered on randomization


# •The values and weights can be constants or variables,


# •value can be single or a range


# •the default weight of an unspecified value is := 1


# •the sum of weights need not be a 100


### Figures on this page:

- `VHDL_p207_img1.png`

## Page 208: in the range. where n is the number of values in the range.


# The := operator assigns the specified weight to the item, or if the item is a range, specified weight to every


# value in the range.


# The :/ operator assigns the specified weight to the item, or if the item is a range, specified weight/n to every value


# in the range. where n is the number of values in the range.


### Figures on this page:

- `VHDL_p208_img1.png`
- `VHDL_p208_img2.png`

## Page 209: randomization with dist operator


## weighted distribution constraint examples


## randomization with dist operator


### Figures on this page:

- `VHDL_p209_img1.png`
- `VHDL_p209_img2.png`

## Page 210: difference between := and :/ dist operator


## difference between := and :/ dist operator


### Figures on this page:

- `VHDL_p210_img1.png`
- `VHDL_p210_img2.png`
- `VHDL_p210_img3.png`

## Page 211: considered.


## SystemVerilog implication if else Constraints


# • The implication operator can be used to declaring conditional relations between two variables.


# • implication operator is denoted by the symbol ->.


# • The implication operator is placed between the expression and constraint.


# If the expression on the LHS of implication operator (->) is true, then the only constraint on the RHS will be


# considered.


### Figures on this page:

- `VHDL_p211_img1.png`

## Page 212



### Figures on this page:

- `VHDL_p212_img1.png`
- `VHDL_p212_img2.png`

## Page 213: constraints in the optional else constraint/constraint-block must be satisfied.


## if else constraints


# •


# if else block allows conditional executions of constraints.


# •


# If the expression is true, all the constraints in the first constraint/constraint-block must be satisfied, otherwise all the


# constraints in the optional else constraint/constraint-block must be satisfied.


## Page 214



### Figures on this page:

- `VHDL_p214_img1.png`
- `VHDL_p214_img2.png`

## Page 215: The foreach loop iterates over the elements of an array, so constraints with the foreach loop are called Iterative constraints.


## SystemVerilog foreach loop Constraint Blocks


# •


# SystemVerilog supports using the foreach loop inside a constraint block.


# •


# using the foreach loop within the constraint block will make easy to constrain an array.


# •


# The foreach loop iterates over the elements of an array, so constraints with the foreach loop are called Iterative constraints.


### Figures on this page:

- `VHDL_p215_img1.png`

## Page 216



### Figures on this page:

- `VHDL_p216_img1.png`
- `VHDL_p216_img2.png`

## Page 217: Any mode change of static constraint will afect in all the objects of same class type.


## Constraint modes and Static Constraints


## Constraint Modes


# The constraint_mode() method can be used to disable any particular constraint block.


# By default constraint_mode value for all the constraint blocks will be 1.


# constraint_mode() can be used as follow,


# addr_range.constraint_mode(0); //disable


# addr_range constraint


# packet.addr_range.constraint_mode(0);


## Static Constraints


# A constraint block can be defined as static, by including static keyword in its definition.


# static constraint addr_range { addr > 5; }


# Any mode change of static constraint will afect in all the objects of same class type.


## Page 218



### Figures on this page:

- `VHDL_p218_img1.png`
- `VHDL_p218_img2.png`

## Page 219: constraint is used to constrain the variable addr.


## Inline constraint Syntax


## SystemVerilog Inline Constraints


# Class doesn't have constraints defined in it. the inline


# constraint is used to constrain the variable addr.


### Figures on this page:

- `VHDL_p219_img1.png`
- `VHDL_p219_img2.png`
- `VHDL_p219_img3.png`

## Page 220: Constraint inside the class and inline constraint


## Constraint inside the class and inline constraint


### Figures on this page:

- `VHDL_p220_img1.png`
- `VHDL_p220_img2.png`

## Page 221: variables.


## Functions in Constraints


# • In some cases constraint can't be expressed in a single line,


# • in such cases function call can be used to constrain a random variable.


# • Calling the function inside the constraint is referred to as function in constraints.


# •The function will be written outside the constraint block


# •Constraint logic shall be written inside the function as function definition and function call shall be


# placed inside the constraint block


# •Functions shall be called before constraints are solved, and their return values shall be treated as state


# variables.


### Figures on this page:

- `VHDL_p221_img1.png`

## Page 222



### Figures on this page:

- `VHDL_p222_img1.png`
- `VHDL_p222_img2.png`

## Page 223: The $urandom_range() function returns an unsigned integer within a specified range.


## SystemVerilog Random System Methods


## $urandom( )


# The system function $urandom provides a mechanism for generating pseudorandom numbers.


# The function returns a new 32-bit random number each time it is called.


# The number shall be unsigned.


# The seed is an optional argument that determines the sequence of random numbers generated.


# The seed can be an integral expression.


## $random( )


# $random() is same as $urandom() but it generates signed numbers.


## $urandom_range( )


# The $urandom_range() function returns an unsigned integer within a specified range.


### Figures on this page:

- `VHDL_p223_img1.png`

## Page 224



### Figures on this page:

- `VHDL_p224_img1.png`
- `VHDL_p224_img2.png`

## Page 225: •Events


## Synchronization Communication Mechanisms


## •Semaphore


## •Mailbox


## •Events


## Page 226: this leads to an unexpected result. A semaphore can be used to overcome this situation.


## Semaphore


# • Semaphore is a SystemVerilog built-in class, used for access control to shared resources, and for basic


# synchronization.


# • A semaphore is like a bucket with the number of keys.


# • processes using semaphores must first procure a key from the bucket before they can continue to execute


# • All other processes must wait until a sufficient number of keys are returned to the bucket.


# Imagine a situation where two processes try to access a shared memory area.


# where one process tries to write and the other process is trying to read the same memory location.


# this leads to an unexpected result. A semaphore can be used to overcome this situation.


### Figures on this page:

- `VHDL_p226_img1.png`

## Page 227: new( );


## Semaphore methods


# Semaphore is a built-in class that provides the following methods,


# •


# new(); Create a semaphore with a specified number of keys


# •


# get();


# Obtain one or more keys from the bucket


# •


# put();


# Return one or more keys into the bucket


# •


# try_get(); Try to obtain one or more keys without blocking


## new( );


### Figures on this page:

- `VHDL_p227_img1.png`

## Page 228



### Figures on this page:

- `VHDL_p228_img1.png`
- `VHDL_p228_img2.png`

## Page 229



### Figures on this page:

- `VHDL_p229_img1.png`

## Page 230



### Figures on this page:

- `VHDL_p230_img1.png`
- `VHDL_p230_img2.png`

## Page 231: endmodule


# module tb_top;


# semaphore key;


# initial begin


# key = new (1);


# forkpersonA ();


# personB ();


# #25 personA ();


# join_none


# end


# task getRoom (bit [1:0] id);


# $display ("[%0t] Trying to get a room for id[%0d] ...", $time, id);


# key.get (1);


# $ display ("[%0t] Room Key retrieved for id[%0d]", $time, id);


# endtask


# task putRoom (bit [1:0] id);


# $display ("[%0t] Leaving room id[%0d] ...", $time, id);


# key.put (1);


# $display ("[%0t] Room Key put back id[%0d]", $time, id);


# endtask


# task personA ();


# getRoom (1);


# #20 putRoom (1);


# endtask


# task personB ();


# #5  getRoom (2);


# #10 putRoom (2);


# endtask


# endmodule


### Figures on this page:

- `VHDL_p231_img1.png`

## Page 232



### Figures on this page:

- `VHDL_p232_img1.png`

## Page 233



### Figures on this page:

- `VHDL_p233_img1.png`
- `VHDL_p233_img2.png`

## Page 234



### Figures on this page:

- `VHDL_p234_img1.png`
- `VHDL_p234_img2.png`

## Page 235: Putting back more keys


## Putting back more keys


### Figures on this page:

- `VHDL_p235_img1.png`
- `VHDL_p235_img2.png`

## Page 236: other process puts the key.


# At the same time, two processes will get access to the


# method and the other process will be blocked until the one


# other process puts the key.


### Figures on this page:

- `VHDL_p236_img1.png`
- `VHDL_p236_img2.png`

## Page 237



### Figures on this page:

- `VHDL_p237_img1.png`
- `VHDL_p237_img2.png`

## Page 238



### Figures on this page:

- `VHDL_p238_img1.png`
- `VHDL_p238_img2.png`

## Page 239: keys are not available simulation will proceed(non-blocking).


# Creating semaphore with '4' key, try_get() will check for the keys if the


# keys are not available simulation will proceed(non-blocking).


### Figures on this page:

- `VHDL_p239_img1.png`
- `VHDL_p239_img2.png`

## Page 240: Parameterized Mailbox that can accept items of only a specific data type


# SystemVerilog Mailbox


# A SystemVerilog mailbox is a way to allow different processes to exchange data between each other.


# It is similar to a real postbox where letters can be put into the box and a person can retrieve those letters


# later on.


# SystemVerilog mailboxes are created as having either a bounded or unbounded queue size.


# A bounded mailbox can only store a limited amount of data, and if a process attempts to store more


# messages into a full mailbox, it will be suspended until there's enough room in the mailbox.


# An unbounded mailbox has unlimited size.


# There are two types:


# •


# Generic Mailbox that can accept items of any data type


# •


# Parameterized Mailbox that can accept items of only a specific data type


### Figures on this page:

- `VHDL_p240_img1.png`

## Page 241



### Figures on this page:

- `VHDL_p241_img1.png`
- `VHDL_p241_img2.png`

## Page 242: • num() : Retrieve the number of messages in the mailbox


# • new() : Create a mailbox


# • put() : Place a message in a mailbox


# • try_put() : Try to place a message in a mailbox without blocking


# • get() : Retrieve a message from a mailbox


# • try_get() : Try to retrieve a message from a mailbox without blocking


# • peek() : Copy a message from a mailbox without removing


# • try_peek() : Try to copy a message from a mailbox without blocking & removing


# • num() : Retrieve the number of messages in the mailbox


## Page 243



### Figures on this page:

- `VHDL_p243_img1.png`

## Page 244: share data for which a certain level of determinism is required.


## SystemVerilog Mailbox vs Queue


# Although a SystemVerilog mailbox essentially behaves like a queue, it is quite different from the


# queue data type.


# A simple queue can only push and pop items from either the front or the back. However, a mailbox is a


# builtin class that uses semaphores (/systemverilog/systemverilog-semaphore) to have atomic control


# the push and pop from the queue.


# Moreover, you cannot access a given index within the mailbox queue, but only retrieve items in FIFO


# order.


# you cannot access a given index within the mailbox queue, but only retrieve items in FIFO order.


## Where is a mailbox used ?


# A SystemVerilog mailbox is typically used when there are multiple threads running in parallel and want to


# share data for which a certain level of determinism is required.


## Page 245



### Figures on this page:

- `VHDL_p245_img1.png`

## Page 246



### Figures on this page:

- `VHDL_p246_img1.png`

## Page 247



### Figures on this page:

- `VHDL_p247_img1.png`
- `VHDL_p247_img2.png`

## Page 248



### Figures on this page:

- `VHDL_p248_img1.png`

## Page 249: method returns a negative integer.


# • The try_get() method attempts to retrieve a message from a mailbox without blocking.If the mailbox is


# empty, then the method returns 0.


# • If the type of the message variable and the type of the message in the mailbox are different, the method


# returns a negative integer.


# • The try_peek() method attempts to copy a message from a mailbox without blocking & without removing


# from mailbox queue.If the mailbox is empty, then the method returns 0. If there is a type mismatch the


# method returns a negative integer.


## Page 250



### Figures on this page:

- `VHDL_p250_img1.png`

## Page 251



### Figures on this page:

- `VHDL_p251_img1.png`
- `VHDL_p251_img2.png`

## Page 252: method returns a negative integer.


# • The try_get() method attempts to retrieve a message from a mailbox without blocking.If the mailbox is


# empty, then the method returns 0.


# • If the type of the message variable and the type of the message in the mailbox are different, the method


# returns a negative integer.


# • The try_peek() method attempts to copy a message from a mailbox without blocking & without removing


# from mailbox queue.If the mailbox is empty, then the method returns 0. If there is a type mismatch the


# method returns a negative integer.


## Page 253



### Figures on this page:

- `VHDL_p253_img1.png`
- `VHDL_p253_img2.png`

## Page 254



### Figures on this page:

- `VHDL_p254_img1.png`
- `VHDL_p254_img2.png`
- `VHDL_p254_img3.png`

## Page 255



### Figures on this page:

- `VHDL_p255_img1.png`
- `VHDL_p255_img2.png`

## Page 256



### Figures on this page:

- `VHDL_p256_img1.png`
- `VHDL_p256_img2.png`

## Page 257



### Figures on this page:

- `VHDL_p257_img1.png`

## Page 258: Non-blocking events are triggered using the ->> operator.


## SystemVerilog Events


# •


# Events are static objects useful for synchronization between the process.


# •


# Events operations are of two staged processes in which one process will trigger the event, and the other processes


# will wait for an event to be triggered.


# •Events are triggered using -> operator or ->> operator


# •wait for an event to be triggered using @ operator or wait() construct


## -> operator


# Named events are triggered via the -> operator.


# Triggering an event unblocks all processes currently waiting on that event.


## ->> operator


# Non-blocking events are triggered using the ->> operator.


## Page 259: Whereas wait(); construct will detect the event triggering.


## @ operator


# •


# wait for an event to be triggered is via the event control operator, @.


# •The @ operator blocks the calling process until the given event is triggered.


# •For a trigger to unblock a process waiting on an event, the waiting process must execute the @ statement


# before the triggering process executes the trigger operator, ->


# Note: If the trigger executes first, then the waiting process remains blocked.


## wait operator


# •


# If the event triggering and waiting for event trigger with @ operator happens at the same time, @


# operator may miss detecting the event trigger.


# •


# Whereas wait(); construct will detect the event triggering.


### Figures on this page:

- `VHDL_p259_img1.png`
- `VHDL_p259_img2.png`

## Page 260: run-time error is generated.


## wait_order();


# The wait_order construct is blocking the process until all of the specified events are triggered in


# the given order (left to right).


# event trigger with out of order will not unblock the process.


# Blocks the process until events a, b, and c trigger in the order a –> b –> c. If the events trigger out of order, a


# run-time error is generated.


### Figures on this page:

- `VHDL_p260_img1.png`
- `VHDL_p260_img2.png`
- `VHDL_p260_img3.png`

## Page 261: the event waiting with @ operator


## the event waiting with @ operator


### Figures on this page:

- `VHDL_p261_img1.png`
- `VHDL_p261_img2.png`

## Page 262: will not be executed.


## trigger first and then waiting for a trigger


# •


# event triggering happens first and then the


# waiting for trigger happens.


# •


# As the waiting happens later, it will be blocking,


# so the statements after the wait for the trigger


# will not be executed.


### Figures on this page:

- `VHDL_p262_img1.png`
- `VHDL_p262_img2.png`

## Page 263



### Figures on this page:

- `VHDL_p263_img1.png`
- `VHDL_p263_img2.png`

## Page 264



### Figures on this page:

- `VHDL_p264_img1.png`
- `VHDL_p264_img2.png`

## Page 265



### Figures on this page:

- `VHDL_p265_img1.png`

## Page 266: •Concurrent Assertions


## SystemVerilog Assertions


# • Assertions are primarily used to validate the behavior of a design.


# •


# An assertion is a check embedded in design or bound to a design unit during the simulation.


# • Warnings or errors are generated on the failure of a specific condition or sequence of events.


# Assertions are used to,


# • Check the occurrence of a specific condition or sequence of events.


# • Provide functional coverage.


# There are two kinds of assertions:


# •Immediate Assertions


# •Concurrent Assertions


## Page 267: • The action_block specifies what actions are taken upon success or failure of the assertion


## Immediate Assertions:


# • Immediate assertions check for a condition at the current simulation time.


# • An immediate assertion is the same as an if..else statement with assertion control.


# •


# Immediate assertions have to be placed in a procedural block definition.


# • The optional statement label (identifier and colon) creates a named block around the assertion


# statement


# • The action block is executed immediately after the evaluation of the assert expression


# • The action_block specifies what actions are taken upon success or failure of the assertion


### Figures on this page:

- `VHDL_p267_img1.png`

## Page 268: •If an assertion fails and no else clause is specified, the tool shall, by default call $error.


## action_block;


## •The pass statement is executed if the expression evaluates to true


## •The statement associated with else is called a fail statement and is executed if the expression evaluates to


# false


## •Both pass and fail statements are optional


# •Since the assertion is a statement that something must be true, the failure of an assertion shall have a severity


# associated with it.


## •By default, the severity of an assertion failure is an error.


# •Other severity levels can be specified by including one of the following severity system tasks in the fail


# statement:


# •


# $fatal is a run-time fatal.


# •


# $error is a run-time error.


# •


# $warning is a run-time warning, which can be suppressed in a tool-specific manner.


# •


# $info indicates that the assertion failure carries no specific severity.


# •If an assertion fails and no else clause is specified, the tool shall, by default call $error.


### Figures on this page:

- `VHDL_p268_img1.png`

## Page 269



### Figures on this page:

- `VHDL_p269_img1.png`

## Page 270



### Figures on this page:

- `VHDL_p270_img1.png`
- `VHDL_p270_img2.png`

## Page 271



### Figures on this page:

- `VHDL_p271_img1.png`
- `VHDL_p271_img2.png`

## Page 272: The Keyword differentiates the immediate assertion from the concurrent assertion is "property."


## Concurrent Assertions:


# Concurrent assertions check the sequence of events spread over multiple clock cycles.


# •


# The concurrent assertion is evaluated only at the occurrence of a clock tick


# •


# The test expression is evaluated at clock edges based on the sampled values of the variables involved


# •


# It can be placed in a procedural block, a module, an interface or a program definition


# The Keyword differentiates the immediate assertion from the concurrent assertion is "property."


### Figures on this page:

- `VHDL_p272_img1.png`

## Page 273: Below diagram shows the steps involved in the creation of an SVA checker,


## Building blocks of SVA


# Below diagram shows the steps involved in the creation of an SVA checker,


### Figures on this page:

- `VHDL_p273_img1.png`

## Page 274: Request is asserted."


# "The Read and Write signals should never be asserted together."


# "A Request should be followed by an Acknowledge occurring no more than two clocks after the


# Request is asserted."


### Figures on this page:

- `VHDL_p274_img1.png`
- `VHDL_p274_img2.png`
- `VHDL_p274_img3.png`

## Page 275: SVA provides a keyword to represent these complex sequential behaviors called "property".


## Boolean expressions


# The functionality is represented by the combination of multiple logical events. These events could be simple Boolean


# expressions.


## Sequence


# Boolean expression events that evaluate over a period of time involving single/multiple clock cycles. SVA provides a keyword


# to represent these events called "sequence."


## Property


# •


# A number of sequences can be combined logically or sequentially to create more complex sequences.


# •


# SVA provides a keyword to represent these complex sequential behaviors called "property".


### Figures on this page:

- `VHDL_p275_img1.png`
- `VHDL_p275_img2.png`

## Page 276: It has to be asserted to take effect during a simulation. SVA provides a keyword called "assert" to check the property.


## Assert


# •


# The property is the one that is verified during a simulation.


# •


# It has to be asserted to take effect during a simulation. SVA provides a keyword called "assert" to check the property.


### Figures on this page:

- `VHDL_p276_img1.png`

## Page 277: clock. If the signal "a" is not high on any positive clock edge, the assertion will fail.


## SVA Sequence example


# sequence seq_1 checks that the signal "a" is high on every positive edge of the


# clock. If the signal "a" is not high on any positive clock edge, the assertion will fail.


### Figures on this page:

- `VHDL_p277_img1.png`
- `VHDL_p277_img2.png`

## Page 278: clock cycles.


## Non-overlapped implication


# The non-overlapped implication is denoted by the symbol |=>


# If there is a match on the antecedent, then the consequent expression is evaluated in the next clock cycle.


# if signal "a" is high on a given positive clock edge, then signal "b" should be high on the next clock edge.


## The implication with a fixed delay on the consequent


# property checks that, if signal "a" is high on a given positive clock edge, then signal "b" should be high after 2


# clock cycles.


### Figures on this page:

- `VHDL_p278_img1.png`
- `VHDL_p278_img2.png`

## Page 279



### Figures on this page:

- `VHDL_p279_img1.png`
- `VHDL_p279_img2.png`

## Page 280



### Figures on this page:

- `VHDL_p280_img1.png`
- `VHDL_p280_img2.png`

## Page 281: checking the seq_2 (“d” should be low, 2 clock cycles after seq_1 is true).


## The implication with a sequence as an antecedent


# Property checks that, if the sequence seq_1 is true on a given positive edge of the clock, then start


# checking the seq_2 (“d” should be low, 2 clock cycles after seq_1 is true).


### Figures on this page:

- `VHDL_p281_img1.png`

## Page 282



### Figures on this page:

- `VHDL_p282_img1.png`
- `VHDL_p282_img2.png`

## Page 283: in the same clock cycle or within 4 clock cycles.


## Timing windows in SVA Checkers


# property checks that, if signal "a" is high on a given positive clock edge, then within 1 to 4 clock cycles,


# the signal "b" should be high.


## Overlapping timing window


# property checks that, if signal "a" is high on a given positive clock edge, then signal "b" should be high


# in the same clock cycle or within 4 clock cycles.


### Figures on this page:

- `VHDL_p283_img1.png`
- `VHDL_p283_img2.png`

## Page 284



### Figures on this page:

- `VHDL_p284_img1.png`

## Page 285



### Figures on this page:

- `VHDL_p285_img1.png`

## Page 286: eventually starting from the next clock cycle.


## Indefinite timing window


# •


# The upper limit of the timing window specified in the right-hand side can be defined with a "$" sign which


# implies that there is no upper bound for timing.


# •


# This is called the "eventuality" operator.


# •


# The checker will keep checking for a match until the end of the simulation.


# property checks that, if signal "a" is high on a given positive clock edge, then signal "b" will be high


# eventually starting from the next clock cycle.


### Figures on this page:

- `VHDL_p286_img1.png`

## Page 287



### Figures on this page:

- `VHDL_p287_img1.png`

## Page 288: number of clocks specified.


## SystemVerilog Repetition operators


# property checks that, if the signal “a” is high on given posedge of the clock, the signal “b” should be high for 3


# consecutive clock cycles.


# The Consecutive repetition operator is used to specify that a signal or a sequence will match continuously for the


# number of clocks specified.


### Figures on this page:

- `VHDL_p288_img1.png`
- `VHDL_p288_img2.png`
- `VHDL_p288_img3.png`

## Page 289



### Figures on this page:

- `VHDL_p289_img1.png`

## Page 290



### Figures on this page:

- `VHDL_p290_img1.png`

## Page 291: for 3 clock cycles followed by “c” should be high after ”b” is high for the third time.


## go to repetition


# The go-to repetition operator is used to specify that a signal will match the number of times specified not


# necessarily on continuous clock cycles.


# property checks that, if the signal “a” is high on given posedge of the clock, the signal “b” should be high


# for 3 clock cycles followed by “c” should be high after ”b” is high for the third time.


### Figures on this page:

- `VHDL_p291_img1.png`
- `VHDL_p291_img2.png`

## Page 292



### Figures on this page:

- `VHDL_p292_img1.png`

## Page 293: occur, the assertion will fai


## SVA Methods


## $rose


# returns true if the least significant bit of the expression changed to 1. Otherwise, it returns false.


# Sequence seq_rose checks that the signal "a" transitions to a value of 1 on every positive edge of the clock.


# If the transition does not occur, the assertion will fail.


# Sequence seq_fell checks that the signal "a" transitions to a value of 0 on every positive edge of the clock.


# If the transition does not occur, the assertion will fail.


# Sequence seq_fell checks that the signal "a" transitions to a value


# of 0 on every positive edge of the clock. If the transition does not


# occur, the assertion will fai


### Figures on this page:

- `VHDL_p293_img1.png`
- `VHDL_p293_img2.png`
- `VHDL_p293_img3.png`
- `VHDL_p293_img4.png`

## Page 294: the “b” is high, then 2 cycles before that, a was high.


# returns true if the value of the expression did not change. Otherwise, it returns false.


# Sequence seq_stable checks that the signal "a" is stable on every positive edge of the clock.


# If there is any transition occurs, the assertion will fail.


# provides the value of the signal from the previous clock cycle.


# Property checks that, in the given positive clock edge, if


# the “b” is high, then 2 cycles before that, a was high.


### Figures on this page:

- `VHDL_p294_img1.png`
- `VHDL_p294_img2.png`
- `VHDL_p294_img3.png`
- `VHDL_p294_img4.png`

## Page 295



### Figures on this page:

- `VHDL_p295_img1.png`

## Page 296: that, a was high only if the gating signal "c' is valid on any given positive edge of the clock.


# The $past construct can be used with a gating signal. on a given clock edge, the gating signal has to be true


# even before checking for the consequent condition.


# Property checks that, in the given positive clock edge, if the “b” is high, then 2 cycles before


# that, a was high only if the gating signal "c' is valid on any given positive edge of the clock.


### Figures on this page:

- `VHDL_p296_img1.png`
- `VHDL_p296_img2.png`
- `VHDL_p296_img3.png`

## Page 297: counts the number of bits that are high in a vector.


## Built-in system functions


# •$onehot(expression)


# •


# checks that only one bit of the expression can be high on any given clock edge.


# •$onehot0(expression)


# •


# checks only one bit of the expression can be high or none of the bits can be high on any


# given clock edge.


# •$isunknown(expression)


# •


# checks if any bit of the expression is X or Z.


# •$countones(expression)


# •


# counts the number of bits that are high in a vector.


## Page 298: Assert statement a_4 checks that the number of ones in the vector "bus" is greater than one.


# Assert statement a_1 checks that the bit vector "state" is one-hot.


# Assert statement a_2 checks that the bit vector "state" is zero one-hot.


# Assert statement a_3 checks if any bit of the vector "bus" is X or Z.


# Assert statement a_4 checks that the number of ones in the vector "bus" is greater than one.


### Figures on this page:

- `VHDL_p298_img1.png`

## Page 299: this entire sequence, if reset is detected high at any point, the checker will stop.


## disable iff


# In certain design conditions, we don't want to proceed with the check if some condition is true.


# this can be achieved by using disable iff.


# property checks that, if the signal “a” is high on given posedge of the clock, the signal “b” should


# be high for 3 clock cycles followed by “c” should be high after ”b” is high for the third time. During


# this entire sequence, if reset is detected high at any point, the checker will stop.


### Figures on this page:

- `VHDL_p299_img1.png`

## Page 300: them. the end point of the sequences does the synchronization.


## ended


# while concatenating the sequences, the ending point of the sequence can be used as a synchronization point.


# This is expressed by attaching the keyword "ended" to a sequence name.


# property checks that, sequence seq_1 and SEQ_2 match with a delay of 2 clock cycles in between


# them. the end point of the sequences does the synchronization.


### Figures on this page:

- `VHDL_p300_img1.png`

## Page 301: executed.


# when a gets the value 1 that b takes the value 1 one cycle later and that c takes the


# value 1 one additional cycle later or that reset got the value 0:


# Here, the concurrent assertion is placed inside a case statement. The check of the assertion is performed


## every rising edge of the clock and is started only if the branch s0 of the case statement is


# executed.


### Figures on this page:

- `VHDL_p301_img1.png`
- `VHDL_p301_img2.png`

## Page 302



### Figures on this page:

- `VHDL_p302_img1.png`
- `VHDL_p302_img2.png`

## Page 303



### Figures on this page:

- `VHDL_p303_img1.png`
- `VHDL_p303_img2.png`

## Page 304



### Figures on this page:

- `VHDL_p304_img1.png`

## Page 305



### Figures on this page:

- `VHDL_p305_img1.png`
- `VHDL_p305_img2.png`

## Page 306



### Figures on this page:

- `VHDL_p306_img1.png`

## Page 307



### Figures on this page:

- `VHDL_p307_img1.png`

## Page 308



### Figures on this page:

- `VHDL_p308_img1.png`

## Page 309



### Figures on this page:

- `VHDL_p309_img1.png`
