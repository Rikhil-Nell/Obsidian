# 12. Activity Selection Problem

## Theory & MCQ Prep

**Problem.** Given `n` activities with start times `s[]` and finish times `f[]`, pick the **maximum number** of activities that can be performed by a single person (no overlapping).

**Greedy strategy.** Sort activities by **finish time**. Pick the first one. Then repeatedly pick the next activity whose `start ≥ finish_of_last_picked`.

**Why greedy works:** picking the activity that finishes earliest leaves the maximum room for future activities (an "exchange argument" proof).

- **Approach class:** **Greedy** (canonical example).
- **Time:** O(n log n) (sort) + O(n) (scan) = **O(n log n)**.  
  If activities are *already sorted by finish time* (as in many course versions, including the class code below), it is **O(n)**.
- **Space:** O(1).
- **Variants:** weighted activity selection cannot be solved greedily — needs DP.

### Example MCQs

**Q1.** Activity Selection Problem is an example of:  
A) Greedy  B) DP  C) Backtracking  D) Brute force  
**Answer: A.**

**Q2.** The greedy choice in this problem is to pick:  
A) Shortest activity  B) Activity that starts earliest  C) Activity that finishes earliest  D) Activity with most overlaps  
**Answer: C.**

**Q3.** If activities are pre-sorted by finish time, the time complexity is:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(1)  
**Answer: C.**

**Q4.** For activities with `s = {1, 3, 0, 5, 8, 5}` and `f = {2, 4, 6, 7, 9, 9}` (already sorted by finish), the maximum number of activities is:  
A) 3  B) 4  C) 5  D) 6  
**Answer: B** (pick indices 0, 1, 3, 4).

**Q5.** Picking the activity with the **earliest start time** instead of earliest finish:  
A) Always gives the optimum.  B) Can give a sub-optimum.  C) Same time complexity.  D) Both B and C.  
**Answer: D.**

## Hand-write Java Code

Assumes activities are already sorted by finish time.

```java
import java.util.*;
class Main {
    public static void printMaxActivities(int s[], int f[], int n) {
        int i = 0;
        System.out.print(i + " ");
        for (int j = 1; j < n; j++) {
            if (s[j] >= f[i]) {
                System.out.print(j + " ");
                i = j;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s[] = new int[n];
        int f[] = new int[n];
        for (int i = 0; i < n; i++) s[i] = sc.nextInt();
        for (int i = 0; i < n; i++) f[i] = sc.nextInt();
        printMaxActivities(s, f, n);
    }
}
```

## Shortcut Version

If activities aren't pre-sorted, sort by finish time first:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] act = new int[n][2];
        for (int i = 0; i < n; i++) act[i][0] = s.nextInt();
        for (int i = 0; i < n; i++) act[i][1] = s.nextInt();
        Arrays.sort(act, (x, y) -> x[1] - y[1]);
        int last = -1, count = 0;
        for (int[] r : act) if (r[0] >= last) { count++; last = r[1]; }
        System.out.println(count);
    }
}
```
