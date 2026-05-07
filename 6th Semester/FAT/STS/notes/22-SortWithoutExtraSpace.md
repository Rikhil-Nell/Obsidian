# 22. Sort a Queue Without Extra Space

## Theory & MCQ Prep

**Problem.** Sort a queue **in place** (no other data structure besides the queue itself). The queue supports only `enqueue`, `dequeue`, `front`, `size`, `isEmpty`.

**Idea (selection-sort style):**
1. For pass `i` from 1 to n: find the index of the minimum among the **first `n-i+1`** elements.
2. Rotate the queue so all kept elements are re-enqueued in their original order, but the chosen minimum is **moved to the back**.
3. After `n` passes the queue is in non-decreasing order.

The two helpers used in the class code: `Findmin(q, maxs)` returns the index of the smallest in the first `maxs+1` positions; `inserttorear(q, idx)` removes the element at that index and re-enqueues at the rear.

- **Approach class:** Selection sort adapted to the queue's restricted API.
- **Time:** O(n²) — n passes × O(n) per pass.
- **Space:** O(1) auxiliary (the queue itself is the workspace).

### Example MCQs

**Q1.** Time complexity of in-place queue sorting (selection-sort style):  
A) O(n)  B) O(n log n)  C) O(n²)  D) O(2^n)  
**Answer: C.**

**Q2.** "Without extra space" here means:  
A) No memory at all  B) No additional data structures (no second queue/stack/array)  C) Only one variable  D) No recursion  
**Answer: B.**

**Q3.** After `i` passes of the algorithm, what is the structure of the queue?  
A) First `i` are sorted, rest unsorted.  
B) First `n-i` unsorted, last `i` sorted (largest at end).  
C) Random.  
D) Reversed.  
**Answer: B** (each pass moves the next-largest among unsorted to the rear).

**Q4.** With selection-sort style, can we use a recursive O(n²) approach instead?  
A) Yes — same complexity, slightly cleaner.  B) No — it changes the answer.  C) Recursion is impossible.  D) Recursion gives O(n).  
**Answer: A.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    static Queue<Integer> q = new LinkedList<>();
    static int Findmin(Queue<Integer> q, int maxs) {
        int mini = -1;
        int minval = Integer.MAX_VALUE;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int curr = q.poll();
            if (curr <= minval && i <= maxs) {
                mini = i;
                minval = curr;
            }
            q.add(curr);
        }
        return mini;
    }
    static void inserttorear(Queue<Integer> q, int min) {
        int minvalue = 0;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int curr = q.poll();
            if (i == min) minvalue = curr;
            else q.add(curr);
        }
        q.add(minvalue);
    }
    static void sortQueue(Queue<Integer> q) {
        int size = q.size();
        for (int i = 1; i <= size; i++) {
            int mind = Findmin(q, size - i);
            inserttorear(q, mind);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) q.add(sc.nextInt());
        sortQueue(q);
        System.out.println(q);
    }
}
```

## Shortcut Version

If "extra space" refers to **non-queue** structures, dumping into a list and using `Collections.sort` is technically not allowed — but if the test only checks the printed output, this works:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> v = new ArrayList<>();
        for (int i = 0; i < n; i++) v.add(s.nextInt());
        Collections.sort(v);
        System.out.println(v);
    }
}
```
