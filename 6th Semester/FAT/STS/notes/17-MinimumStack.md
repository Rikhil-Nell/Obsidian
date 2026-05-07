# 17. Minimum Stack

## Theory & MCQ Prep

**Problem.** Design a stack that supports `push`, `pop`, and `getMin` — all in **O(1)**.

**Idea (auxiliary stack).** Maintain a second stack `mst` of *current minimums*. Push to `mst` only when the incoming value ≤ `mst.peek()` (or stack is empty). On pop, also pop `mst` if the popped value equals `mst.peek()`.

- **Approach class:** Augmented data structure (stack with helper stack).
- **All operations:** **O(1)** time. **Space:** **O(n)** (worst case `mst` mirrors `st`).
- **Subtle equality:** push to `mst` only when **`<=`** (not strict `<`) — otherwise after popping a duplicate minimum, the next `getMin()` is wrong.
- **O(1) space variant:** store *encoded* values in a single stack; when pushing a new minimum, push `2*x - currentMin` and update min. Trickier to memorise; the auxiliary-stack approach is the standard answer.

### Example MCQs

**Q1.** Time complexity of `getMin()` in the optimal Min Stack:  
A) O(1)  B) O(n)  C) O(log n)  D) O(n²)  
**Answer: A.**

**Q2.** Auxiliary space used by the two-stack Min Stack on `n` elements:  
A) O(1)  B) O(log n)  C) O(n)  D) O(n²)  
**Answer: C.**

**Q3.** When pushing value `x`, we push to the auxiliary `mst` if and only if:  
A) `mst.isEmpty() || x <= mst.peek()`  
B) `x > mst.peek()`  
C) `x == 0`  
D) Always.  
**Answer: A.**

**Q4.** Why use `<=` (not `<`) for the auxiliary push?  
A) It's faster.  B) To handle duplicate minimums correctly during pop.  C) Required by the JVM.  D) No reason.  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    static Stack<Integer> st = new Stack<>();
    static Stack<Integer> mst = new Stack<>();
    static void push(int n) {
        if (st.isEmpty()) {
            st.push(n);
            mst.push(n);
        } else {
            st.push(n);
            if (n <= mst.peek()) mst.push(n);
        }
    }
    static void pop() {
        int ele = st.pop();
        if (ele == mst.peek()) mst.pop();
    }
    static void getmin() {
        if (mst.isEmpty()) System.out.print("Stack is Empty");
        else System.out.print(mst.peek());
    }
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        for (int i = 0; i < n; i++) push(sw.nextInt());
        getmin();
    }
}
```

## Shortcut Version

Same idea but everything via `ArrayDeque` (faster than `Stack`):

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        Deque<Integer> st = new ArrayDeque<>(), mn = new ArrayDeque<>();
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            int x = s.nextInt();
            st.push(x);
            if (mn.isEmpty() || x <= mn.peek()) mn.push(x);
        }
        System.out.print(mn.peek());
    }
}
```
