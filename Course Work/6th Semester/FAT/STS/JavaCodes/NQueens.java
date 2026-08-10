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
