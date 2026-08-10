# 7. Maneuvering Problem

## Theory & MCQ Prep

**Problem.** A robot stands at the **top-left** of an `r × c` grid and must reach the **bottom-right**. It can only move **right** or **down**. Count the number of unique paths.

**Closed-form.** From `(0,0)` to `(r-1, c-1)` the robot makes `(r-1)` downs and `(c-1)` rights in any order → `C(r+c-2, r-1)` paths.

**Recursive form** (used in class):
```
man(r, c, R, C) = 1            if r == R-1 OR c == C-1
                = man(r+1, c, R, C) + man(r, c+1, R, C)   otherwise
```

- **Approach class:** **Recursion** with overlapping subproblems → can be memoised → **DP**.
- **Plain recursion:** O(2^(R+C)) time. **Memoised DP:** O(R·C) time, O(R·C) space.
- For the trivial 2×2 grid `man(0,0,2,2) = 1` (you can only go RD or DR — but the recursion above returns 1 because either coordinate already equals the boundary at start). For 3×3 → 6, for 4×4 → 20, for `R×C` → `C(R+C-2, R-1)`.

### Example MCQs

**Q1.** Number of unique paths in a 3×3 grid (top-left to bottom-right, only right/down):  
A) 4  B) 6  C) 8  D) 9  
**Answer: B** (C(4,2) = 6).

**Q2.** Plain-recursive Maneuvering has time complexity:  
A) O(R·C)  B) O(R+C)  C) O(2^(R+C))  D) O(log(R·C))  
**Answer: C.**

**Q3.** With memoisation, time and space become:  
A) O(R·C), O(R·C)  B) O(R+C), O(1)  C) O(2^(R·C)), O(R·C)  D) O(R·C log RC), O(R·C)  
**Answer: A.**

**Q4.** Maneuvering exhibits which key DP property?  
A) Greedy choice  B) Optimal substructure + overlapping subproblems  C) Backtracking pruning  D) None  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
public class Main {
    public static int man(int r, int c, int row, int col) {
        if (r == row - 1 || c == col - 1) return 1;
        return man(r + 1, c, row, col) + man(r, c + 1, row, col);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        System.out.println(man(0, 0, r, c));
    }
}
```

## Shortcut Version

DP table (or single Pascal-row formula) — both pass automated tests in O(R·C):

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int r = s.nextInt(), c = s.nextInt();
        long[][] dp = new long[r][c];
        for (int i = 0; i < r; i++) dp[i][0] = 1;
        for (int j = 0; j < c; j++) dp[0][j] = 1;
        for (int i = 1; i < r; i++)
            for (int j = 1; j < c; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        System.out.println(dp[r - 1][c - 1]);
    }
}
```
