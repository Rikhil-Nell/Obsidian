import java.util.*;
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int suf = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            suf += a[i];
        }
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            pre += a[i];
            if (pre == suf) {
                System.out.println(pre + " " + i);
                max = Math.max(max, suf);
            }
            suf -= a[i];
        }
        System.out.println(max);
    }
}
