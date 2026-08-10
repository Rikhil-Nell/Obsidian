# 18. Celebrity Problem

## Theory & MCQ Prep

**Problem.** In a party of `n` people, a *celebrity* is someone who:
1. Is **known by everyone** else, AND
2. **Knows nobody** else.

You are given an `n×n` matrix `M` where `M[i][j] = 1` means person `i` knows person `j`. Find the celebrity's index, or report none.

**Stack-based approach (O(n)):**
1. Push all `n` indices onto a stack.
2. While stack has > 1 element: pop two (`x`, `y`). If `M[x][y] == 1`, x knows y → x cannot be celeb, push `y`. Else push `x`.
3. The last remaining is a **candidate** — verify it: row all 0s except `M[c][c]`, column all 1s except `M[c][c]`.

**Two-pointer variant (also O(n)):** `i = 0, j = n-1`. If `M[i][j] == 1`, `i` is not celeb → `i++`. Else `j--`. Verify the survivor.

- **Approach class:** Elimination by transitivity. Optimal time **O(n)**, brute force is O(n²).
- **Space:** O(n) for the stack version, O(1) for the two-pointer version.
- **Verification step is mandatory** — without it the algorithm wrongly accepts non-celebrities.

### Example MCQs

**Q1.** Optimal time complexity of finding a celebrity in `n` people:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(1)  
**Answer: C.**

**Q2.** When we pop `x` and `y` and `M[x][y] == 1`, we eliminate:  
A) y  B) x  C) Both  D) Neither  
**Answer: B** (x knows y → x cannot be the celeb who knows nobody).

**Q3.** After elimination, why is verification necessary?  
A) Elimination only finds a candidate; transitivity does not guarantee the celeb properties hold for the candidate.  
B) To handle the case `n = 1`.  
C) To save space.  
D) It isn't needed.  
**Answer: A.**

**Q4.** A celebrity, if exists, is **unique**. The proof is:  
A) Two celebs would each know the other → contradicts "knows nobody".  
B) By induction on `n`.  
C) Pigeonhole.  
D) Both A and B are accepted.  
**Answer: A.**

## Hand-write Java Code

```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = sc.nextInt();
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) st.push(i);
        while (st.size() > 1) {
            int x = st.pop();
            int y = st.pop();
            if (mat[x][y] == 1) st.push(y);
            else st.push(x);
        }
        int c = st.pop();
        for (int i = 0; i < n; i++) {
            if (i != c && (mat[c][i] == 1 || mat[i][c] == 0)) {
                System.out.println("No celebrity found.");
                return;
            }
        }
        System.out.println(c);
    }
}
```

## Shortcut Version

Two-pointer, O(1) extra space:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) m[i][j] = s.nextInt();
        int i = 0, j = n - 1;
        while (i < j) { if (m[i][j] == 1) i++; else j--; }
        int c = i;
        for (int k = 0; k < n; k++)
            if (k != c && (m[c][k] == 1 || m[k][c] == 0)) { System.out.println("No celebrity found."); return; }
        System.out.println(c);
    }
}
```
