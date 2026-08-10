# 2. Leaders in Array

## Theory & MCQ Prep

**Problem.** An element is a *leader* if it is greater than (or ≥, depending on variant) all elements to its **right**. The rightmost element is always a leader.

**Idea.** Scan from **right to left**, track running max. Whenever `a[i] > max`, it's a leader and we update `max`.

- **Approach class:** Single right-to-left scan; **not** D&C, **not** greedy.
- **Time:** O(n). **Space:** O(1) extra.
- **Output order:** If you print during right-scan you get leaders in **reverse** of their array order. To print left-to-right, push to a list and reverse, or use a stack.
- **Gotcha:** Strict `>` vs `≥` — the convention used in class is **strict greater than** (so duplicates do not count as leaders).

### Example MCQs

**Q1.** Leaders in `{16, 17, 4, 3, 5, 2}` (strict `>`) are:  
A) 16, 17, 5, 2  B) 17, 5, 2  C) 17, 4, 5, 2  D) 17, 5  
**Answer: B.**

**Q2.** What is the time complexity of the optimal leaders-in-array solution?  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(1)  
**Answer: C.**

**Q3.** The right-to-left scan technique used here is most similar to:  
A) Stock Span (monotonic stack) B) Bubble sort C) Two-pointer D) Sliding window  
**Answer: A** — both use the "greater-than-everything-after" idea with a single pass.

## Hand-write Java Code

```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] > max) {
                System.out.print(a[i] + " ");
                max = a[i];
            }
        }
    }
}
```

## Shortcut Version

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) x[i] = s.nextInt();
        Deque<Integer> ans = new ArrayDeque<>();
        int mx = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) if (x[i] > mx) { ans.push(x[i]); mx = x[i]; }
        ans.forEach(v -> System.out.print(v + " "));
    }
}
```
