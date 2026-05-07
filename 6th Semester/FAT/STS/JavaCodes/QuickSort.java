import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        quicksort(arr, 0, n - 1);
        for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
    }
    public static void quicksort(int arr[], int lb, int ub) {
        if (lb < ub) {
            int loc = partition(arr, lb, ub);
            quicksort(arr, lb, loc - 1);
            quicksort(arr, loc + 1, ub);
        }
    }
    public static int partition(int arr[], int lb, int ub) {
        int pivot = arr[lb];
        int start = lb;
        int end = ub;
        while (start < end) {
            while (start < end && arr[start] <= pivot) start++;
            while (arr[end] > pivot) end--;
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        int temp = arr[lb];
        arr[lb] = arr[end];
        arr[end] = temp;
        return end;
    }
}
