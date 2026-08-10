# 5. Selection Sort

## Theory & MCQ Prep

**Idea.** For each position `i`, find the **minimum** element in `a[i..n-1]` and swap it into `a[i]`. After pass `i`, the first `i+1` elements are sorted.

**Classification:** **Brute Force**, comparison-based, **in-place**, **not stable** (the swap can move equal elements past each other).

**Time complexity** — always **O(n²)**, regardless of input (best = avg = worst). Number of comparisons = n(n-1)/2.  
**Number of swaps** ≤ n-1 (this is its only redeeming feature: minimal writes — useful when writes are expensive).  
**Space:** O(1) auxiliary.

### Example MCQs

**Q1.** The number of comparisons made by selection sort on n elements is:  
A) n  B) n−1  C) n(n−1)/2  D) n²−1  
**Answer: C.**

**Q2.** Selection sort is:  
A) Stable always B) Stable only on linked lists C) Unstable D) Stable when descending  
**Answer: C** (the swap can break stability on equal keys).

**Q3.** Which sort minimises **swaps** (≤ n−1) at the cost of O(n²) comparisons?  
A) Bubble Sort B) Insertion Sort C) Selection Sort D) Quick Sort  
**Answer: C.**

**Q4.** Best-case time complexity of selection sort is:  
A) O(1) B) O(n) C) O(n log n) D) O(n²)  
**Answer: D.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    public static void main(String ars[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) min = j;
            }
            if (min != i) {
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
        System.out.println(Arrays.toString(a));
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
        Arrays.sort(x);
        System.out.println(Arrays.toString(x));
    }
}
```
