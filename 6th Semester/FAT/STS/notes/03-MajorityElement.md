# 3. Majority Element (Moore's Voting)

## Theory & MCQ Prep

**Problem.** A *majority element* appears **strictly more than** ⌊n/2⌋ times in an array. Find it (or report none).

**Moore's Voting Algorithm — two phases:**
1. **Candidate selection:** maintain `count` and `candidate`. Walk through; if `count == 0` set candidate = current; else if equal increment, else decrement.
2. **Verification:** count occurrences of the candidate; if > n/2 it's the majority, else there is none.

- **Approach class:** Voting / cancellation. Single-pass + verification → **O(n)** time, **O(1)** space.
- Why it works: every "decrement" is one majority vote cancelling one non-majority vote. If a true majority exists, it survives.
- Verification phase is **mandatory** — without it you can return a wrong answer when no majority exists (e.g. `{1,2,3}` → algorithm leaves candidate = 3, but 3 isn't a majority).
- **Comparison:** Sorting + middle element → O(n log n). Hashmap counting → O(n) time, O(n) space. Moore's is the optimal one.

### Example MCQs

**Q1.** What does Moore's Voting Algorithm do in its **first** pass?  
A) Counts every element using a hashmap.  
B) Sorts the array.  
C) Maintains a candidate and a counter, cancelling pairs of unequal elements.  
D) Performs binary search.  
**Answer: C.**

**Q2.** Auxiliary space complexity of Moore's voting algorithm:  
A) O(n)  B) O(log n)  C) O(1)  D) O(n²)  
**Answer: C.**

**Q3.** For `{3, 3, 4, 2, 4, 4, 2, 4, 4}`, the majority element is:  
A) 3  B) 2  C) 4  D) None  
**Answer: C** (4 appears 5 times, n=9, ⌊9/2⌋=4, 5>4).

**Q4.** Why is the second (verification) pass needed?  
A) To sort the array. B) Because Moore's first pass may return a non-existent majority. C) For O(1) space. D) It isn't needed.  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int c = 0;
        int ele = 0;
        for (int i = 0; i < n; i++) {
            if (c == 0) {
                ele = a[i];
                c = 1;
            } else if (a[i] == ele) {
                c++;
            } else {
                c--;
            }
        }
        int nc = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == ele) nc++;
        }
        if (nc > a.length / 2) {
            System.out.println(ele);
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
        Arrays.sort(x);
        int cand = x[n / 2];
        int cnt = 0;
        for (int v : x) if (v == cand) cnt++;
        if (cnt > n / 2) System.out.println(cand);
    }
}
```
