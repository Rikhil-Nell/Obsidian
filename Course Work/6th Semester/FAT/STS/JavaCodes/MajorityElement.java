import java.util.*;
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int c = 0;
        int ele = 0;
        for (int i = 0; i < n; i++) {
            if (c == 0) {
                ele = a[i];
                c = 1;
            } else if (a[i] == ele) {
                c++;
            } else {
                c--;
            }
        }
        int nc = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == ele) nc++;
        }
        if (nc > a.length / 2) System.out.println(ele);
    }
}
