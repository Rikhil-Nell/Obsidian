# 23. Stack Permutations

## Theory & MCQ Prep

**Problem.** Given two arrays `x[]` (input order) and `y[]` (claimed output order), determine whether `y` can be produced from `x` using a single stack with the operations *push from input* and *pop to output* in some interleaving.

**Idea.** Walk through `x` left to right; push each element. After every push (and again after popping a match), if the stack's top equals `y[j]`, pop and advance `j`. At the end, the answer is **yes iff the stack is empty** (equivalently, j reached n).

- **Approach class:** Stack simulation / greedy popping.
- **Time:** **O(n)**. Each element pushed once, popped at most once.
- **Space:** **O(n)** for the stack.
- **Theory tidbit:** the number of valid stack permutations of `n` distinct items is the **Catalan number** Cₙ = (2n)!/((n+1)!·n!). For n = 3 → 5; for n = 4 → 14.
- **Forbidden pattern:** A permutation is *not* a stack permutation iff it contains the pattern **231** (Knuth). e.g. `2 3 1` from input `1 2 3` is impossible.

### Example MCQs

**Q1.** Number of stack permutations possible from input `1 2 3 4`:  
A) 14  B) 16  C) 24  D) 7  
**Answer: A** (Catalan C₄ = 14).

**Q2.** From input `1 2 3` is the output `3 1 2` valid?  
A) Yes  B) No  
**Answer: B** (it contains the 231 pattern relative to input ordering).

**Q3.** Time complexity of the simulation algorithm:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(2^n)  
**Answer: C.**

**Q4.** The simulation returns "Yes" iff:  
A) The stack is non-empty at the end.  
B) The stack is empty at the end.  
C) `j == n` and stack non-empty.  
D) The output array is sorted.  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
public class Main {
    public static boolean check(int x[], int y[], int n) {
        Stack<Integer> s = new Stack<Integer>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            s.push(x[i]);
            while (!s.isEmpty() && y[j] == s.peek()) {
                s.pop();
                j++;
            }
        }
        return s.isEmpty();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int j = 0; j < n; j++) b[j] = sc.nextInt();
        if (check(a, b, n)) System.out.println("Yes");
        else System.out.println("No");
    }
}
```

## Shortcut Version

`ArrayDeque` instead of `Stack` (faster), terser:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] x = new int[n], y = new int[n];
        for (int i = 0; i < n; i++) x[i] = s.nextInt();
        for (int i = 0; i < n; i++) y[i] = s.nextInt();
        Deque<Integer> st = new ArrayDeque<>();
        int j = 0;
        for (int v : x) {
            st.push(v);
            while (!st.isEmpty() && st.peek() == y[j]) { st.pop(); j++; }
        }
        System.out.println(st.isEmpty() ? "Yes" : "No");
    }
}
```
