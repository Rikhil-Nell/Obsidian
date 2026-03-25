import java.util.*;

public class CelebrityProblem {
    static int findCelebrity(int[][] m) {
        int n = m.length;
        int candidate = 0;

        // Step 1: Find candidate
        for (int i = 1; i < n; i++) {
            if (m[candidate][i] == 1) {
                candidate = i;
            }
        }

        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (m[candidate][i] == 1 || m[i][candidate] == 0) {
                    return -1;
                }
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(findCelebrity(matrix));
        scanner.close();
    }
}
