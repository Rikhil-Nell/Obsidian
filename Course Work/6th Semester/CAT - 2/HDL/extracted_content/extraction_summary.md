# Extraction Summary

## Overview

| Metric | Count |
|--------|-------|
| Source Files | 1 |
| Total Pages/Slides | 309 |
| Equations Detected | 124 |
| Problems/Examples | 5 |
| Figures Extracted | 414 |
| Tables Extracted | 0 |

## Source Files

- VHDL.pdf

## Section Hierarchy

### Main
- • It has features inherited from Verilog  HDL,VHDL,C,C++
- – Updated with more features in 2012 (IEE 1800 2012 standard)
- • For better Coverage/Assertion integration
- Why System Verilog
- entire design flow
- more efficient
- entire design flow

### DPI
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

### WWW.VITAP.AC.IN
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

### ARRAYS:
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


## Problems/Examples Found

- **Example 0.1** (Page 16, VHDL.pdf)
- **Example** (Page 185, VHDL.pdf)
- **Example** (Page 185, VHDL.pdf)
- **Example** (Page 185, VHDL.pdf)
- **Example** (Page 186, VHDL.pdf)

---

*Extracted at: 2026-03-27T20:25:26.979682*
