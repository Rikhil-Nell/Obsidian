# 4. Quick Sort

## Theory & MCQ Prep

**Idea.** Pick a *pivot*, **partition** the array so that elements ≤ pivot lie left and elements > pivot lie right of it, then **recursively** quicksort the two halves.

**Classification:** **Divide & Conquer**, in-place, **not stable**, comparison-based.

**Time complexity**
- Best / Average: **O(n log n)** (balanced splits).
- Worst: **O(n²)** (already sorted with first/last as pivot, or all equal).
- Space: **O(log n)** auxiliary (recursion stack), in-place otherwise.

**Pivot strategies:** first element (used in class), last, middle, median-of-three, randomized. Randomized pivot expected O(n log n).

**Partition variants:** Lomuto (single index); Hoare (two indices, faster). The class code uses a Hoare-flavoured "start/end" walk where pivot = `a[lb]` and end finally swaps with `lb`.

### Example MCQs

**Q1.** Worst-case time complexity of Quick Sort with first-element pivot on a sorted input is:  
A) O(n log n) B) O(n) C) O(n²) D) O(log n)  
**Answer: C.**

**Q2.** Quick sort is best categorised as:  
A) Greedy B) Dynamic Programming C) Divide & Conquer D) Backtracking  
**Answer: C.**

**Q3.** Which is true about Quick Sort?  
A) Stable B) Always O(n log n) C) In-place D) Needs O(n) auxiliary space  
**Answer: C.**

**Q4.** After one partition step on `{6, 3, 9, 5, 2, 8, 7}` with pivot 6 using the lb-based scheme, the pivot's final index is:  
A) 0 B) 2 C) 3 D) 6  
**Answer: C** (3 elements ≤ 6 → pivot ends up at index 3).

## Hand-write Java Code

```java
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
```

## Shortcut Version

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Integer[] x = new Integer[n];
        for (int i = 0; i < n; i++) x[i] = s.nextInt();
        Arrays.sort(x);
        for (int v : x) System.out.print(v + " ");
    }
}
```
