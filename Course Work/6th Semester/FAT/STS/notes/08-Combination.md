# 8. Combination

## Theory & MCQ Prep

**Problem.** Given an array of `n` distinct elements and a number `d`, print every combination of `d` elements (order within combination does not matter; combinations themselves are produced in increasing-index order).

**Idea.** **Backtracking** with a "choose / move forward" recursion: at each level pick `a[j]` for `j` ≥ start, then recurse with `start = j + 1` and `ind + 1`. When `ind == d` print the buffer.

- **Approach class:** **Backtracking** (combinatorial enumeration).
- **Number of combinations** = C(n, d) = n! / (d!·(n-d)!).
- **Time:** O(C(n, d) · d) (each combination takes O(d) to print).
- **Space:** O(d) for the buffer + O(d) recursion depth.
- **vs Permutations:** combinations don't care about order, so we always advance `start = j + 1` to avoid revisits — that's the structural difference.

### Example MCQs

**Q1.** C(5, 2) =  
A) 5  B) 10  C) 20  D) 25  
**Answer: B.**

**Q2.** What is the maximum recursion depth when generating combinations of size `d` from `n` items?  
A) n  B) d  C) n·d  D) C(n,d)  
**Answer: B.**

**Q3.** Which line ensures we don't reuse already-picked elements / produce duplicates?  
A) `for (int j = 0; j < n; j++)`  B) `com(a, c, j+1, e, ind+1, d)`  C) `if (ind == d) print`  D) `c[ind] = a[j]`  
**Answer: B** — moving `start` to `j+1`.

**Q4.** Generating combinations is best classified as:  
A) Greedy  B) Backtracking  C) Divide & Conquer  D) DP  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    static void com(int a[], int c[], int s, int e, int ind, int d) {
        if (ind == d) {
            for (int j = 0; j < d; j++) System.out.print(c[j] + " ");
            System.out.println();
            return;
        }
        for (int j = s; j <= e; j++) {
            c[ind] = a[j];
            com(a, c, j + 1, e, ind + 1, d);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int d = sc.nextInt();
        int c[] = new int[d];
        com(a, c, 0, n - 1, 0, d);
    }
}
```

## Shortcut Version

Bitmask enumeration over all `2^n` subsets, keep only size-`d` ones (compact, no recursion):

```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = s.nextInt();
        int d = s.nextInt();
        for (int m = 0; m < (1 << n); m++) {
            if (Integer.bitCount(m) != d) continue;
            for (int i = 0; i < n; i++)
                if ((m & (1 << i)) != 0) System.out.print(a[i] + " ");
            System.out.println();
        }
    }
}
```
