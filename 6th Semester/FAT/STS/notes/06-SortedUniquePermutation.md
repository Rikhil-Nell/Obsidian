# 6. Sorted Unique Permutation

## Theory & MCQ Prep

**Problem.** Given a string (possibly with duplicates), print all **unique** permutations in **sorted (lexicographic)** order.

**Idea.** Generate all n! permutations by classical **backtracking** (swap-based recursion); push every full permutation into a `TreeSet<String>` which deduplicates and keeps lexicographic order automatically.

- **Approach class:** **Backtracking** (recursive permutation generation).
- **Number of recursive leaves** = n!. With duplicates, distinct count = n! / (Π fᵢ!) where fᵢ are duplicate-letter frequencies.
- **Time:** generating all permutations is O(n!·n); inserting into TreeSet is O(n!·n log n!) overall.
- **Space:** O(unique permutations · n) for storage; O(n) recursion depth.
- **Alternative without extra space:** sort the array, then use the *next-permutation* algorithm in a loop (O(n!·n)) — naturally produces sorted unique permutations.

### Example MCQs

**Q1.** Distinct permutations of `"AABC"` are:  
A) 24 B) 12 C) 16 D) 6  
**Answer: B** (4!/2! = 12).

**Q2.** Which data structure gives **sorted, deduplicated** permutations with one line of code?  
A) HashSet B) ArrayList C) TreeSet D) PriorityQueue (min-heap)  
**Answer: C.**

**Q3.** Sorted unique permutation generation is best classified as:  
A) Greedy B) Divide & Conquer C) Backtracking D) Dynamic Programming  
**Answer: C.**

**Q4.** For `"AB"`, the printed output is:  
A) AB BA B) BA AB C) AB D) BA  
**Answer: A** (sorted order: AB, then BA).

## Hand-write Java Code

```java
import java.util.*;
public class Main {
    static TreeSet<String> set = new TreeSet<>();
    public static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void permutations(char[] a, int fi) {
        if (fi == a.length - 1) {
            set.add(new String(a));
            return;
        }
        for (int i = fi; i < a.length; i++) {
            swap(a, fi, i);
            permutations(a, fi + 1);
            swap(a, fi, i);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] a = s.toCharArray();
        permutations(a, 0);
        for (String perm : set) System.out.println(perm);
    }
}
```

## Shortcut Version

```java
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] a = sc.next().toCharArray();
        Arrays.sort(a);
        TreeSet<String> set = new TreeSet<>();
        do {
            set.add(new String(a));
        } while (nextPermutation(a));
        set.forEach(System.out::println);
    }
    static boolean nextPermutation(char[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0) return false;
        int j = a.length - 1;
        while (a[j] <= a[i]) j--;
        char t = a[i]; a[i] = a[j]; a[j] = t;
        for (int l = i + 1, r = a.length - 1; l < r; l++, r--) {
            t = a[l]; a[l] = a[r]; a[r] = t;
        }
        return true;
    }
}
```
