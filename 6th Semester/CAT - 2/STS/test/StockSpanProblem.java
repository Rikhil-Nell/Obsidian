package test;

import java.util.*;

public class StockSpanProblem {
    static ArrayList<Integer> calculateSpan(int [] arr){
        int n = arr.length;
        ArrayList<Integer> span = new ArrayList<>();

        for(int i = 0; i < n; i++){

            int count = 1;
            
            for(int j = i - 1; j >= 0 && arr[i] >= arr[j]; j--){
                count++;
            }
            span.add(count);
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
