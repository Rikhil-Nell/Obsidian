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
