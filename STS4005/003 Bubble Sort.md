
# Code
```java
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps, array is sorted
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        int[] data = {-2, 45, 0, 11, -9};
        bubbleSort(data);
        System.out.println(java.util.Arrays.toString(data));  // [-9, -2, 0, 11, 45]
    }
}
```

# Time and Space Complexities

Time Complexity: **O(n^2)**
Space Complexity: **O(n)**
