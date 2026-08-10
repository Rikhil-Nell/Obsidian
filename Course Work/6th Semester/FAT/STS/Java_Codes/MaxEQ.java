import java.util.*;

public class MaxEQ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];

        int total = 0;

        for(int x : a) total += x;
        
        int left = 0;

        for(int i = 0; i < n; i++){
            total -= a[i];
            if(left == total) System.out.println("Eq at " + total);
            left += a[i];
        }
        sc.close();
    }
}
