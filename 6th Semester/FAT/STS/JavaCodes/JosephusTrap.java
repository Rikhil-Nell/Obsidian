import java.util.*;
class Main {
    static int josh(int n, int k) {
        if (n == 1) return 0;
        return (josh(n - 1, k) + k) % n;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(josh(n, k));
        System.out.println(josh(n, k) + 1);
    }
}
