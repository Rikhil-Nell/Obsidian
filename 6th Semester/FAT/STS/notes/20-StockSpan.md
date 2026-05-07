# 20. Stock Span Problem

## Theory & MCQ Prep

**Problem.** Given daily stock prices `p[0..n-1]`, the *span* on day `i` is the number of consecutive days **before and including day `i`** for which the price was ≤ `p[i]`. Compute span for every day.

**Monotonic stack approach (O(n)):** Stack stores **indices** of days with prices in **decreasing** order from bottom to top. For each `i`:
1. Pop indices whose price ≤ `p[i]` (they are dominated by today's price).
2. If stack empty → span = `i + 1`. Else → span = `i − stack.peek()`.
3. Push `i`.

- **Approach class:** **Monotonic stack** (decreasing).
- **Time:** **O(n)** total — every index pushed and popped at most once. Brute force is O(n²).
- **Space:** **O(n)**.
- **Use cases:** generalises to "previous greater element" — same template.

### Example MCQs

**Q1.** Optimal time complexity of the Stock Span problem:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(log n)  
**Answer: C.**

**Q2.** The auxiliary stack stores:  
A) Prices  B) Spans  C) Indices of days  D) Sums  
**Answer: C.**

**Q3.** For `p = {100, 80, 60, 70, 60, 75, 85}`, the spans are:  
A) `1 1 1 2 1 4 6`  B) `1 1 1 2 1 4 5`  C) `1 1 1 2 2 4 5`  D) `1 1 1 1 1 1 1`  
**Answer: A.**

**Q4.** Stock Span is essentially a problem of finding:  
A) Next greater element  B) Previous greater element  C) Next smaller element  D) Previous smaller element  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
public class Main {
    static void span(int p[], int n, int s[]) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        s[0] = 1;
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && p[st.peek()] <= p[i]) st.pop();
            s[i] = (st.isEmpty() ? (i + 1) : (i - st.peek()));
            st.push(i);
        }
    }
    public static void main(String[] args) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        int p[] = new int[n];
        for (int i = 0; i < n; i++) p[i] = sw.nextInt();
        int s[] = new int[n];
        span(p, n, s);
        for (int i = 0; i < n; i++) System.out.print(s[i] + " ");
    }
}
```

## Shortcut Version

Same monotonic stack via `ArrayDeque`, fewer lines:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] p = new int[n], r = new int[n];
        for (int i = 0; i < n; i++) p[i] = s.nextInt();
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && p[st.peek()] <= p[i]) st.pop();
            r[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }
        for (int v : r) System.out.print(v + " ");
    }
}
```
