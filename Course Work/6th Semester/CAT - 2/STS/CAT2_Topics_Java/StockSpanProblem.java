import java.util.ArrayList;
import java.util.Scanner;
class StockSpan {
    static ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> span = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            span.add(1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[i] >= arr[j]; j--) {
                span.set(i, span.get(i) + 1);
            }
        }
        return span;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); 
        }
        ArrayList<Integer> span = calculateSpan(arr);
        for (int x : span) {
            System.out.print(x + " ");
        }
        sc.close();
    }
}
