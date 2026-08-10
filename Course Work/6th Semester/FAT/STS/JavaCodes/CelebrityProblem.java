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
