# 11. N-Queens  *(MCQ-only topic)*

## Theory & MCQ Prep

**Problem.** Place `N` queens on an `N×N` chessboard so that **no two attack each other** (no shared row, column, or diagonal). Output a valid placement (binary matrix).

**Algorithm — classical backtracking:**
1. Pick column 0 (or row 0) and try every row position for the queen.
2. After placing, recurse to the next column.
3. If no row in a column is safe, **backtrack** to the previous column and shift its queen.
4. Repeat until all `N` queens are placed (success) or all options exhausted (no solution).

**Safety check** for placing a queen at `(r, c)`:
- No queen exists in the same column (`board[i][c]`, i < r).
- No queen on the upper-left diagonal (`board[r-i][c-i]`).
- No queen on the upper-right diagonal (`board[r-i][c+i]`).

**Key facts**
- **Approach class:** **Backtracking** (with pruning).
- **Time complexity (upper bound):** O(N!). With pruning, many fewer leaves are reached. The number of **distinct solutions** for small N: N=1→1, N=2→0, N=3→0, N=4→2, N=5→10, N=6→4, N=7→40, N=8→**92**.
- **Space:** O(N²) for the board, O(N) recursion depth.
- **No solution exists** for N = 2 and N = 3.
- **Naïve brute force** (try all C(N², N) placements) is far worse than backtracking.

### Example MCQs

**Q1.** N-Queens is an instance of:  
A) Greedy  B) DP  C) Backtracking  D) Branch & Bound only  
**Answer: C.** (Branch & Bound is also valid; in coursework the canonical answer is **Backtracking**.)

**Q2.** Total distinct solutions for the 8-Queens problem:  
A) 8  B) 64  C) 92  D) 256  
**Answer: C.**

**Q3.** N values for which **no solution** exists:  
A) N = 1  B) N = 2 and N = 3  C) N = 4  D) Only N = 0  
**Answer: B.**

**Q4.** Worst-case time complexity of the standard backtracking N-Queens is:  
A) O(2^N)  B) O(N²)  C) O(N!)  D) O(N^N)  
**Answer: C** (loose upper bound; tighter is O(N!) but typically pruned much further).

**Q5.** While placing queens column by column, when do we *backtrack*?  
A) After every successful placement.  
B) When current column has no row where the queen is safe.  
C) Only at column 0.  
D) Never — it's a greedy algorithm.  
**Answer: B.**

**Q6.** For N = 4 the number of distinct solutions is:  
A) 1  B) 2  C) 4  D) 8  
**Answer: B.**

**Q7.** The diagonal check `board[r-i][c-i]` and `board[r-i][c+i]` exploits the fact that:  
A) Diagonals share `r-c` or `r+c`.  
B) Rows are equal.  
C) Columns are equal.  
D) Distance is squared.  
**Answer: A.** (Top-left↔bottom-right diagonal: `r - c` constant. Top-right↔bottom-left: `r + c` constant.)

**Q8.** For an `N×N` board, how many cells can a single queen attack (including its own)?  
A) `4N - 4`  B) `4N - 3`  C) `2N`  D) `N²`  
**Answer: B** (one row + one column + two diagonals minus over-counts).

## (No coding required — MCQ only per syllabus.) 

For your reference, here's the canonical backtracking template (you do **not** need to memorise this for coding, only for understanding the MCQs):

```java
import java.util.*;
public class Main {
    static int N;
    static int[][] board;
    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 1) return false;
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) if (board[i][j] == 1) return false;
        return true;
    }
    static boolean solve(int row) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solve(row + 1)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N][N];
        if (solve(0)) {
            for (int[] r : board) {
                for (int v : r) System.out.print(v + " ");
                System.out.println();
            }
        } else System.out.println("No solution");
    }
}
```
