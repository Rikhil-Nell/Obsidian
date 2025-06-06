A variable can store data and can slot in as a substiute to the said data as long as its data type is defined.

There are two steps to creating a variable in C++ being: 
- Declaration of the variable
- Assigning a value to the variable

>Splitting the process in two like this enables an oppourtunity for user input.

# There are many datatypes you can give to a variable in C++

![Data Types in C++](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20191113115600/DatatypesInC.png)

## **Data Types in C++ are Mainly Divided into 3 Types:** 

**1. Primitive Data Types**: These data types are built-in or predefined data types and can be used directly by the user to declare variables. example: int, char, float, bool, etc. Primitive data types available in C++ are: 

-   Integer
-   Character
-   Boolean
-   Floating Point
-   Double Floating Point
-   Valueless or Void
-   Wide Character

**2. Derived Data Types:** [Derived data types](https://www.geeksforgeeks.org/derived-data-types-in-c/) that are derived from the primitive or built-in datatypes are referred to as Derived Data Types. These can be of four types namely: 

-   Function
-   Array
-   Pointer
-   Reference

**3. Abstract or User-Defined Data Types**: [Abstract or User-Defined data types](https://www.geeksforgeeks.org/user-defined-derived-data-types-in-c/) are defined by the user itself. Like, defining a class in C++ or a structure. C++ provides the following user-defined datatypes:  

-   Class
-   Structure
-   Union
-   Enumeration
-   Typedef defined Datatype

## **Primitive Data Types**

-   **Integer**: The keyword used for integer data types is **int**. Integers typically require 4 bytes of memory space and range from -2147483648 to 2147483647.  
-   **Character**: Character data type is used for storing characters. The keyword used for the character data type is **char**. Characters typically require 1 byte of memory space and range from -128 to 127 or 0 to 255.  
-   **Boolean**: Boolean data type is used for storing Boolean or logical values. A Boolean variable can store either _true_ or _false_. The keyword used for the Boolean data type is **bool**. 
-   **Floating Point**: Floating Point data type is used for storing single-precision floating-point values or decimal values. The keyword used for the floating-point data type is **float**. Float variables typically require 4 bytes of memory space. 
-   **Double Floating Point**: Double Floating Point data type is used for storing double-precision floating-point values or decimal values. The keyword used for the double floating-point data type is **double**. Double variables typically require 8 bytes of memory space. 
-   **void**: Void means without any value. void data type represents a valueless entity. A void data type is used for those function which does not return a value. 
-   **Wide Character**: [Wide character](https://www.geeksforgeeks.org/wide-char-and-library-functions-in-c/) data type is also a character data type but this data type has a size greater than the normal 8-bit data type. Represented by **wchar_t**. It is generally 2 or 4 bytes long.
-   **sizeof() operator:** [sizeof() operator](https://www.geeksforgeeks.org/sizeof-operator-c) is used to find the number of bytes occupied by a variable/data type in computer memory.

