package test;

import java.util.*;

public class StackPermutations {
    
    static boolean isStackPermutation(int[] input, int[] output) {
        Stack<Integer> stack = new Stack<>();
        int outputIndex = 0;

        for (int value : input) {
            stack.push(value);

            while (!stack.isEmpty() && outputIndex < output.length && stack.peek() == output[outputIndex]) {
                stack.pop();
                outputIndex++;
            }
        }

        return outputIndex == output.length;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] input = new int[n];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            output[i] = scanner.nextInt();
        }

        System.out.println(isStackPermutation(input, output));
        scanner.close();
    }
}
