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
