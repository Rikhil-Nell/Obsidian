# 19. Iterative Tower of Hanoi

## Theory & MCQ Prep

**Problem.** Move `n` disks from source `S` to destination `D` using auxiliary `A`, larger disk never on top of smaller. Standard recursive solution: T(n) = 2T(n-1) + 1 → **2^n − 1** moves.

**Iterative algorithm using 3 stacks.** For each move number `i = 1..2^n−1`, exactly one of three pair-swap operations is legal at that step. The pattern repeats every 3 moves:
- `i % 3 == 1` → legal move between `S` and `D`
- `i % 3 == 2` → legal move between `S` and `A`
- `i % 3 == 0` → legal move between `A` and `D`

A "legal move between two pegs" is: pop the top from each, the smaller one goes back on top of the larger. (If a peg is empty, treat top as `−∞`.)

**Parity trick.** If `n` is even, swap the *names* of `A` and `D` so the same modulo pattern works.

- **Approach class:** Classical iterative simulation; equivalent in moves to recursion.
- **Total moves:** **2^n − 1** (minimum). **Time:** O(2^n). **Space:** O(n) (stacks store disks).
- **MCQ favourite:** "minimum moves to solve Tower of Hanoi for n = 10" → 1023.

### Example MCQs

**Q1.** Minimum number of moves to solve Tower of Hanoi with 6 disks:  
A) 31  B) 63  C) 64  D) 127  
**Answer: B** (2^6 − 1).

**Q2.** Recurrence relation for Tower of Hanoi (n disks):  
A) T(n) = T(n-1) + 1  B) T(n) = 2T(n-1) + 1  C) T(n) = 2T(n/2) + n  D) T(n) = T(n-1) + n  
**Answer: B.**

**Q3.** In the iterative 3-stack algorithm with `n` even, we swap the labels of:  
A) Source and Auxiliary  B) Auxiliary and Destination  C) Source and Destination  D) Nothing  
**Answer: B** (so that the parity of the smallest disk's first move is correct).

**Q4.** Time complexity of Tower of Hanoi:  
A) O(n)  B) O(n log n)  C) O(n²)  D) O(2^n)  
**Answer: D.**

## Hand-write Java Code

```java
import java.util.Scanner;
import java.util.Stack;
class Main {
    static Stack<Integer> sr = new Stack<>();
    static Stack<Integer> ax = new Stack<>();
    static Stack<Integer> ds = new Stack<>();
    static void change(Stack<Integer> s1, Stack<Integer> s2, char a, char b) {
        int v1, v2;
        if (s1.isEmpty()) v1 = Integer.MIN_VALUE; else v1 = s1.pop();
        if (s2.isEmpty()) v2 = Integer.MIN_VALUE; else v2 = s2.pop();
        if (v1 == Integer.MIN_VALUE) {
            s1.push(v2);
            System.out.println("The value " + v2 + " is moved from " + b + " to " + a);
        } else if (v2 == Integer.MIN_VALUE) {
            s2.push(v1);
            System.out.println("The value " + v1 + " is moved from " + a + " to " + b);
        } else if (v1 < v2) {
            s2.push(v2);
            s2.push(v1);
            System.out.println("The value " + v1 + " is moved from " + a + " to " + b);
        } else {
            s1.push(v1);
            s1.push(v2);
            System.out.println("The value " + v2 + " is moved from " + b + " to " + a);
        }
    }
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        for (int i = n; i > 0; i--) sr.push(i);
        char s = 'S', a = 'A', d = 'D';
        if (n % 2 == 0) { char temp = a; a = d; d = temp; }
        int moves = (int) (Math.pow(2, n) - 1);
        for (int i = 1; i <= moves; i++) {
            if (i % 3 == 1) change(sr, ds, s, d);
            if (i % 3 == 2) change(sr, ax, s, a);
            if (i % 3 == 0) change(ax, ds, a, d);
        }
    }
}
```

## Shortcut Version

If the test only asks for **number of moves**:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println((1L << n) - 1);
    }
}
```

If it asks for the move sequence, the recursive variant is the most memorable:

```java
import java.util.*;
class Main {
    static void hanoi(int n, char s, char a, char d) {
        if (n == 0) return;
        hanoi(n - 1, s, d, a);
        System.out.println("The value " + n + " is moved from " + s + " to " + d);
        hanoi(n - 1, a, s, d);
    }
    public static void main(String[] x) {
        Scanner sc = new Scanner(System.in);
        hanoi(sc.nextInt(), 'S', 'A', 'D');
    }
}
```
