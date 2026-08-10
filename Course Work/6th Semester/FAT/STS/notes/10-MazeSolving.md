# 10. Maze Solving (Rat in a Maze)

## Theory & MCQ Prep

**Problem.** Given an `r × c` binary matrix where `1` = open cell and `0` = blocked, the rat starts at `(0,0)` and must reach `(r-1, c-1)`, moving only **right** or **down**. Print the path matrix (1s along a valid path) or "No".

**Idea.** Classic **backtracking**:
1. If current cell is destination and is open → mark and return true.
2. Else if cell is in-bounds and open → mark, try right, try down. If neither works, **unmark** and return false.

- **Approach class:** **Backtracking** (DFS over the implicit grid graph).
- **Time:** O(2^(r·c)) worst case (each cell may branch in 2 directions).
- **Space:** O(r·c) for the path matrix and recursion depth.
- **All-4-directions variant** would also need a `visited` matrix to avoid cycles. The 2-direction (R/D only) version doesn't need explicit visited — it can never revisit.

### Example MCQs

**Q1.** Maze solving (rat in a maze) is an example of:  
A) Greedy  B) DP  C) Backtracking  D) Divide & Conquer  
**Answer: C.**

**Q2.** With movement only Right/Down on an n×n maze, the worst-case time complexity of recursive backtracking is:  
A) O(n²)  B) O(2^n)  C) O(2^(n²))  D) O(n!)  
**Answer: C.**

**Q3.** When a cell leads to no valid path, the algorithm performs which step?  
A) Marks it visited and continues.  B) Returns true.  C) Resets `path[x][y] = 0` (un-marks) and returns false.  D) Throws exception.  
**Answer: C.**

**Q4.** Why is no separate `visited` array required in the right/down-only variant?  
A) Because path[][] also serves as visited.  B) Because right/down movements can never form a cycle.  C) Because the maze is binary.  D) It is required.  
**Answer: B** (also A is technically true, but the deeper reason is monotone movement).

## Hand-write Java Code

```java
import java.util.*;
public class Main {
    static boolean findpath(int m[][], int x, int y, int r, int c, int path[][]) {
        if (x == r - 1 && y == c - 1 && m[x][y] == 1) {
            path[x][y] = 1;
            return true;
        }
        if (x >= 0 && x < r && y >= 0 && y < c && m[x][y] == 1) {
            path[x][y] = 1;
            if (findpath(m, x, y + 1, r, c, path)) return true;
            if (findpath(m, x + 1, y, r, c, path)) return true;
            path[x][y] = 0;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int m[][] = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = sc.nextInt();
        int path[][] = new int[r][c];
        if (findpath(m, 0, 0, r, c, path)) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) System.out.print(path[i][j] + " ");
                System.out.println();
            }
        } else {
            System.out.println("No");
        }
    }
}
```

## Shortcut Version

Same backtracking compressed; this is already near-minimal. A "DP reachability" trick works if you only need yes/no:

```java
import java.util.*;
public class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int r = s.nextInt(), c = s.nextInt();
        int[][] m = new int[r][c];
        for (int i = 0; i < r; i++) for (int j = 0; j < c; j++) m[i][j] = s.nextInt();
        boolean[][] d = new boolean[r][c];
        d[0][0] = m[0][0] == 1;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (m[i][j] == 1 && (i + j) > 0)
                    d[i][j] = (i > 0 && d[i - 1][j]) || (j > 0 && d[i][j - 1]);
        System.out.println(d[r - 1][c - 1] ? "Yes" : "No");
    }
}
```
