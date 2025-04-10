
### **1.**

**Question:** Add five 8-bit numbers stored from memory location `3001H`, store result in `4000H`.

**Concept:** Memory access, loop, addition.

**Unique Opcode Used:** `ADD M`, `INX H`, `MOV`, `LXI`

**Answer:**

```assembly
LXI H, 3001H     ; HL points to start of numbers
MVI C, 05H       ; Counter for 5 numbers
MVI D, 00H       ; D will store lower byte sum
MVI E, 00H       ; E will store carry

NEXT: MOV A, M   ; Load current byte to A
ADD D           ; Add previous sum
MOV D, A        ; Store result in D
JNC NO_CARRY    ; If no carry, skip
INR E           ; Else increment carry

NO_CARRY: INX H ; Next memory location
DCR C           ; Decrease counter
JNZ NEXT        ; Loop if not zero

LXI H, 4000H    ; Store result
MOV M, D        ; Store lower byte
INX H
MOV M, E        ; Store carry
HLT

```

----------

### **2.**

**Question:** Add `49H` and `56H` from `2500H`, `2600H`, store in `2100H`.

**Concept:** Direct addressing, arithmetic.

**Unique Opcode Used:** `LDA`, `ADD`, `STA`

**Answer:**

```assembly
LDA 2500H      ; Load 49H into A
MOV B, A       ; Save A to B
LDA 2600H      ; Load 56H into A
ADD B          ; Add B to A
STA 2100H      ; Store result
HLT

```

----------

### **3.**

**Question:** Find 2's complement of `4CH` in `2500H`, store in `2600H`.

**Concept:** Bitwise NOT, ADD 1.

**Unique Opcode Used:** `CMA`, `ADI`, `STA`

**Answer:**

```assembly
LDA 2500H      ; Load 4CH
CMA            ; 1's complement
ADI 01H        ; Add 1
STA 2600H      ; Store result
HLT

```

----------

### **4.**

**Question:** Subtract `78H` (from `2500H`) from `B2H` (in A), store in `2500H`.

**Concept:** Register-memory subtraction.

**Unique Opcode Used:** `LDA`, `SUB`, `STA`

**Answer:**

```assembly
MVI A, B2H     ; Load B2H in A
LDA 2500H      ; Load 78H
SUB A          ; Subtract A - M (78H)
STA 2500H      ; Store result
HLT

```

----------

### **5.**

**Question:** Mask off 4 LSBs of `7EH` in `2500H`, store in `2600H`.

**Concept:** Bit masking using AND.

**Unique Opcode Used:** `ANI`, `STA`

**Answer:**

```assembly
LDA 2500H      ; Load 7EH
ANI F0H        ; Mask lower 4 bits
STA 2600H      ; Store result
HLT

```

----------

### **6.**

**Question:** Find the largest of numbers in `2500H` and `2600H`, store in `2100H`.

**Concept:** Comparison, conditional jump.

**Unique Opcode Used:** `CMP`, `JNC`, `MOV`

**Answer:**

```assembly
LDA 2500H      ; Load first number
MOV B, A       ; Store in B
LDA 2600H      ; Load second number
CMP B          ; Compare A - B
JNC STORE_A    ; If A >= B, store A
MOV A, B       ; Else, store B

STORE_A: STA 2100H
HLT

```

----------

### **7.**

**Question:** Find the smallest of numbers in `2500H` and `2600H`, store in `2100H`.

**Concept:** Comparison, conditional jump.

**Unique Opcode Used:** `CMP`, `JC`, `MOV`

**Answer:**

```assembly
LDA 2500H      ; Load first number
MOV B, A       ; Save to B
LDA 2600H      ; Load second number
CMP B          ; Compare A - B
JC STORE_A     ; If A < B, store A
MOV A, B       ; Else, store B

STORE_A: STA 2100H
HLT

```

----------

### **8.**

**Question:** Add two 8-bit numbers using 8085.

**Concept:** Simple 8-bit register addition.

**Unique Opcode Used:** `ADD`, `MOV`

**Answer:**

```assembly
MVI A, 34H     ; Load first number
MVI B, 21H     ; Load second number
ADD B          ; A = A + B
STA 2100H      ; Store result
HLT

```

----------

### **9.**

**Question:** Multiply two 8-bit numbers using 8085.

**Concept:** Repeated addition.

**Unique Opcode Used:** `DCR`, `JNZ`, `ADD`

**Answer:**

```assembly
MVI A, 00H     ; Clear accumulator for result
MVI B, 05H     ; First number
MVI C, 03H     ; Second number

LOOP: ADD B    ; A = A + B
DCR C
JNZ LOOP

STA 2100H      ; Store result
HLT

```

----------

### **10.**

**Question:** Subtract two 8-bit numbers using 8085.

**Concept:** Basic subtraction.

**Unique Opcode Used:** `SUB`

**Answer:**

```assembly
MVI A, 60H     ; First number
MVI B, 40H     ; Second number
SUB B          ; A = A - B
STA 2100H      ; Store result
HLT

```

----------

### **11.**

**Question:** Perform division of two 8-bit numbers using 8085.

**Concept:** Repeated subtraction.

**Unique Opcode Used:** `CMP`, `SUB`, `INR`

**Answer:**

```assembly
MVI D, 00H      ; Quotient
MVI A, 20H      ; Dividend
MVI B, 04H      ; Divisor

DIV_LOOP: CMP B ; Compare A and B
JC DONE         ; If A < B, done
SUB B           ; A = A - B
INR D           ; D = D + 1 (increment quotient)
JMP DIV_LOOP

DONE: STA 2101H ; Remainder in A
MOV A, D
STA 2100H       ; Quotient in 2100H
HLT

```

----------

### **12.**

**Question:** Write a block transfer program in 8085.

**Concept:** Memory copy using loop.

**Unique Opcode Used:** `MOV M, A`, `LXI`, `INX`, `DCR`, `JNZ`

**Answer:**

```assembly
LXI H, 3000H    ; Source start
LXI D, 4000H    ; Destination start
MVI C, 05H      ; Number of bytes

COPY_LOOP: MOV A, M  ; Load from source
MOV E, L
MOV D, H
STAX D          ; Store to destination
INX H           ; Next source
INX D           ; Next destination
DCR C
JNZ COPY_LOOP
HLT

```

----------

### **13.**

**Question:** Write a block exchange program in 8085.

**Concept:** Swapping memory contents between two blocks.

**Unique Opcode Used:** `MOV`, `STAX`, `XCHG`

**Answer:**

```assembly
LXI H, 3000H     ; Block 1
LXI D, 4000H     ; Block 2
MVI C, 05H       ; Count

EX_LOOP: MOV A, M    ; Load from H
LDAX D              ; Load from D
MOV B, A            ; Save from H
MOV A, M            ; Load again from H
MOV M, B            ; Swap to H
MOV M, A            ; Save other to D
INX H
INX D
DCR C
JNZ EX_LOOP
HLT

```

----------

### **14.**

**Question:** Find largest in array starting at `2001H`, result in `2400H`.

**Concept:** Array traversal, comparison.

**Unique Opcode Used:** `CMP`, `JNC`, `MOV`, `INX`

**Answer:**

```assembly
LXI H, 2000H    ; HL points to count
MOV C, M        ; Store count
INX H
MOV A, M        ; First value as max
DCR C           ; Decrement count

FIND_LARGEST:
INX H
CMP M
JNC SKIP
MOV A, M        ; New max

SKIP: DCR C
JNZ FIND_LARGEST
STA 2400H       ; Store max
HLT

```

----------

### **15.**

**Question:** Find smallest in array starting at `2001H`, result in `2400H`.

**Concept:** Same as largest, with reverse condition.

**Unique Opcode Used:** `JC`, `CMP`, `MOV`

**Answer:**

```assembly
LXI H, 2000H    ; HL points to count
MOV C, M
INX H
MOV A, M
DCR C

FIND_SMALLEST:
INX H
CMP M
JC SKIP
MOV A, M

SKIP: DCR C
JNZ FIND_SMALLEST
STA 2400H
HLT

```

----------

### **16.**

**Question:** Add & subtract R0 and R1, store in R3 (sum), R4 (difference). Use ARM.

**Concept:** Register arithmetic.

**Unique Opcode Used:** `ADD`, `SUB`

**Answer:**

```armasm
ADD R3, R0, R1    ; R3 = R0 + R1
SUB R4, R0, R1    ; R4 = R0 - R1

```

----------

### **17.**

**Question:** Multiply R0 (123) and R1 (2CH), store result in `0x10000000`. Use ARM.

**Concept:** Register-to-memory move, multiplication.

**Unique Opcode Used:** `MUL`, `STR`, `LDR`

**Answer:**

```armasm
MOV R0, #123
MOV R1, #0x2C
MUL R2, R0, R1         ; R2 = R0 * R1
LDR R3, =0x10000000
STR R2, [R3]           ; Store result in memory

```

----------

### **18.**

**Question:** Arrange array in ascending order using 8085.

**Concept:** Bubble sort logic.

**Unique Opcode Used:** `CMP`, `JNC`, `MOV`, `XCHG`

**Answer:**

```assembly
; Assuming array starts at 2001H and first byte is count

LXI H, 2000H
MOV C, M
DCR C

OUTER: LXI H, 2001H
MOV D, C

INNER: MOV A, M
INX H
CMP M
JNC SKIP
MOV B, M
MOV M, A
DCX H
MOV M, B
INX H

SKIP: DCR D
JNZ INNER
DCR C
JNZ OUTER
HLT

```

----------

### **19.**

**Question:** Arrange array in descending order using 8085.

**Concept:** Bubble sort logic with reversed comparison.

**Unique Opcode Used:** `CMP`, `JC`, `MOV`

**Answer:**

```assembly
; Assuming array starts at 2001H and first byte is count

LXI H, 2000H
MOV C, M
DCR C

OUTER: LXI H, 2001H
MOV D, C

INNER: MOV A, M
INX H
CMP M
JC SKIP
MOV B, M
MOV M, A
DCX H
MOV M, B
INX H

SKIP: DCR D
JNZ INNER
DCR C
JNZ OUTER
HLT

```

----------

### **20.**

**Question:** Find even numbers from data array using 8085.

**Concept:** Masking, loop, parity.

**Unique Opcode Used:** `ANI`, `JZ`

**Answer:**

```assembly
LXI H, 2000H    ; HL = count
MOV C, M
INX H
LXI D, 3000H    ; Destination
FIND_EVEN:
MOV A, M
ANI 01H         ; Check LSB
JNZ SKIP        ; If LSB = 1, it's odd
MOV A, M
STAX D
INX D

SKIP: INX H
DCR C
JNZ FIND_EVEN
HLT

```

----------

### **21.**

**Question:** Find odd numbers from a data array using 8085.

**Concept:** Bit masking, parity check.

**Unique Opcode Used:** `ANI`, `JNZ`

**Answer:**

```assembly
LXI H, 2000H    ; HL points to count
MOV C, M
INX H
LXI D, 3000H    ; Destination for odd numbers

FIND_ODD:
MOV A, M
ANI 01H         ; Check LSB
JZ SKIP         ; If zero, it's even
MOV A, M
STAX D          ; Store odd number
INX D

SKIP: INX H
DCR C
JNZ FIND_ODD
HLT

```

----------

### **22.**

**Question:** Add and subtract contents of `0x10000100` & `0x10000200`, store in `0x10000300` & `0x10000400` respectively. (ARM)

**Concept:** Register-level math + memory access.

**Unique Opcode Used:** `LDR`, `STR`, `ADD`, `SUB`

**Answer:**

```armasm
LDR R0, =0x10000100
LDR R1, [R0]             ; R1 = [0x10000100]
LDR R2, =0x10000200
LDR R3, [R2]             ; R3 = [0x10000200]

ADD R4, R1, R3
SUB R5, R1, R3

LDR R6, =0x10000300
STR R4, [R6]             ; Store sum

LDR R7, =0x10000400
STR R5, [R7]             ; Store difference

```

----------

### **23.**

**Question:** Divide `72H` by `34H`, store quotient & remainder in `0x10000100` & `0x10000200`. (ARM)

**Concept:** Division instruction (or manual if not supported), memory I/O.

**Unique Opcode Used:** `MOV`, `UDIV`, `MLS`, `STR`

**Answer:**

```armasm
MOV R0, #0x72
MOV R1, #0x34

UDIV R2, R0, R1          ; R2 = Quotient
MLS R3, R2, R1, R0       ; R3 = R0 - R2 * R1 → Remainder

LDR R4, =0x10000100
STR R2, [R4]             ; Store quotient

LDR R5, =0x10000200
STR R3, [R5]             ; Store remainder

```

----------

### **24.**

**Question:** Set 19th and 9th bits, reset 22nd and 7th bits of `R2` without disturbing others. Store in `0x10000200`. (ARM)

**Concept:** Bit manipulation using `ORR`, `BIC`.

**Unique Opcode Used:** `ORR`, `BIC`, `STR`

**Answer:**

```armasm
; Set 19th and 9th bits
ORR R2, R2, #(1 << 19)
ORR R2, R2, #(1 << 9)

; Reset 22nd and 7th bits
BIC R2, R2, #(1 << 22)
BIC R2, R2, #(1 << 7)

LDR R1, =0x10000200
STR R2, [R1]

```

----------

### **25.**

**Question:** Multiply `R2` content by 35 using shift instructions. Store result in `0x10000100`. (ARM)

**Concept:** Shifting and adding (35 = 32 + 2 + 1).

**Unique Opcode Used:** `LSL`, `ADD`

**Answer:**

```armasm
MOV R1, R2, LSL #5     ; R1 = R2 * 32
ADD R1, R1, R2, LSL #1 ; R1 += R2 * 2
ADD R1, R1, R2         ; R1 += R2 * 1 → R1 = R2 * 35

LDR R3, =0x10000100
STR R1, [R3]

```

----------

### **26.**

**Question:** Divide `R2` content by 4 using shift instructions, store result in `0x10000100`. (ARM)

**Concept:** Logical right shift (divide by 2ⁿ).

**Unique Opcode Used:** `LSR`, `STR`

**Answer:**

```armasm
MOV R1, R2, LSR #2     ; Divide R2 by 4

LDR R3, =0x10000100
STR R1, [R3]

```

----------
