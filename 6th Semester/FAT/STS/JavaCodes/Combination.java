import java.util.*;
class Main {
    static void com(int a[], int c[], int s, int e, int ind, int d) {
        if (ind == d) {
            for (int j = 0; j < d; j++) System.out.print(c[j] + " ");
            System.out.println();
            return;
        }
        for (int j = s; j <= e; j++) {
            c[ind] = a[j];
            com(a, c, j + 1, e, ind + 1, d);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int d = sc.nextInt();
        int c[] = new int[d];
        com(a, c, 0, n - 1, 0, d);
    }
}
