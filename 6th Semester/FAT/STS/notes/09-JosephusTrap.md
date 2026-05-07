# 9. Josephus Trap

## Theory & MCQ Prep

**Problem.** `n` people stand in a circle numbered 0..n-1 (or 1..n). Starting from person 0, every `k`-th person is eliminated until one remains. Return the surviving person.

**Recurrence (0-indexed):**
```
J(1)   = 0
J(n,k) = (J(n-1, k) + k) % n
```
Reason: after the first kill the circle has n-1 people, but renumbered. The shift maps the answer in the smaller problem back to the original numbering.

- **Approach class:** **Recursion** with no overlapping subproblems → linear recursion (could be flipped to O(n) iterative).
- **Time:** O(n). **Space:** O(n) recursion stack (or O(1) iterative).
- **Index conversion:** the recurrence yields **0-based** result; add 1 for 1-based.
- **Brute force using a circular linked list / queue** is O(n·k).
- **Closed-form for k = 2:** if n = 2^m + L with L < 2^m, survivor (1-indexed) = 2L + 1.

### Example MCQs

**Q1.** Time complexity of the recurrence `J(n,k) = (J(n-1,k) + k) % n` is:  
A) O(log n)  B) O(n)  C) O(n²)  D) O(2^n)  
**Answer: B.**

**Q2.** For n = 5, k = 3 the 1-indexed Josephus survivor is:  
A) 1  B) 3  C) 4  D) 5  
**Answer: C** (`J(5,3) = 3` zero-based → 4 one-based).

**Q3.** The recurrence relies on:  
A) Greedy choice  B) Renumbering after each kill  C) Sorting  D) Hashing  
**Answer: B.**

**Q4.** For k = 2 and n = 10, the 1-indexed survivor is:  
A) 1  B) 5  C) 7  D) 10  
**Answer: B** (n = 8 + 2 → 2·2 + 1 = 5).

## Hand-write Java Code

```java
import java.util.*;
class Main {
    static int josh(int n, int k) {
        if (n == 1) return 0;
        return (josh(n - 1, k) + k) % n;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(josh(n, k));
        System.out.println(josh(n, k) + 1);
    }
}
```

## Shortcut Version

Iterative one-liner-style — no stack, O(1) extra space:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), k = s.nextInt(), j = 0;
        for (int i = 2; i <= n; i++) j = (j + k) % i;
        System.out.println(j);
        System.out.println(j + 1);
    }
}
```
