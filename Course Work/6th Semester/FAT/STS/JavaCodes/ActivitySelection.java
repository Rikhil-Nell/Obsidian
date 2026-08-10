import java.util.*;
class Main {
    public static void printMaxActivities(int s[], int f[], int n) {
        int i = 0;
        System.out.print(i + " ");
        for (int j = 1; j < n; j++) {
            if (s[j] >= f[i]) {
                System.out.print(j + " ");
                i = j;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s[] = new int[n];
        int f[] = new int[n];
        for (int i = 0; i < n; i++) s[i] = sc.nextInt();
        for (int i = 0; i < n; i++) f[i] = sc.nextInt();
        printMaxActivities(s, f, n);
    }
}
