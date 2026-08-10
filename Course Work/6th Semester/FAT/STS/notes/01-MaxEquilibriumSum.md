# 1. Max Equilibrium Sum

## Theory & MCQ Prep

**Problem.** Given an array `a[0..n-1]`, find an index `i` such that the sum of elements before `i` equals the sum of elements after `i` (the element `a[i]` itself is included on **both** sides — i.e. `prefix[i] == suffix[i]` where each includes `a[i]`). Return the maximum such common sum.

**Idea.** Compute total sum once. Walk left → right keeping a running prefix. After adding `a[i]` to prefix, the suffix-from-i (still including `a[i]`) is the *current remaining* — that's the value we compare. Once `prefix == suffix`, `i` is an equilibrium index.

- **Approach class:** Single-pass + running prefix/suffix. **Not** D&C, **not** greedy.
- **Time:** O(n). **Space:** O(1) extra (besides the array).
- **Common gotcha:** prefix and suffix definitions — whether they include `a[i]` or not. Slide convention used here: both include `a[i]`, so `pre += a[i]; check; suf -= a[i]`.
- **Edge cases:** all zeros (every index works → max stays 0), single element (it is itself the equilibrium).

### Example MCQs

**Q1.** For `arr = {-7, 1, 5, 2, -4, 3, 0}`, what is a max-equilibrium index?  
A) 2 B) 3 C) 4 D) 6  
**Answer: B (index 3, value 2 — prefix and suffix both equal 1).**

**Q2.** Time complexity of the standard max equilibrium sum algorithm using prefix/suffix sums is:  
A) O(n²) B) O(n log n) C) O(n) D) O(1)  
**Answer: C.**

**Q3.** Which statement is true?  
A) Equilibrium index must always be at the middle of the array.  
B) An array may have multiple equilibrium indices.  
C) An array of negative numbers cannot have an equilibrium index.  
D) The first and last index are never equilibrium indices.  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int suf = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            suf += a[i];
        }
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pre += a[i];
            if (pre == suf) {
                System.out.println(pre + " " + i);
                max = Math.max(max, suf);
            }
            suf -= a[i];
        }
        System.out.println(max);
    }
}
```

## Shortcut Version

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), x[] = new int[n], total = 0, max = Integer.MIN_VALUE, pre = 0;
        for (int i = 0; i < n; i++) { x[i] = s.nextInt(); total += x[i]; }
        for (int i = 0; i < n; i++) {
            pre += x[i];
            if (pre == total) max = Math.max(max, pre);
            total -= x[i];
        }
        System.out.println(max);
    }
}
```
