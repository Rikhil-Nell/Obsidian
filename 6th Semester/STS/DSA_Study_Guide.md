# DSA Exam Study Guide 🎯

Quick reference for your coding exam - theory, complexities, and MCQ trivia.

---

## Quick Complexity Cheat Sheet

| Algorithm | Time | Space | Type |
|-----------|------|-------|------|
| Max Equilibrium Sum | O(n) | O(1) | Prefix Sum |
| Leaders in Array | O(n) | O(1) | Single Pass |
| Majority Element | O(n) | O(1) | Boyer-Moore |
| Quick Sort | O(n log n) avg, O(n²) worst | O(log n) | Divide & Conquer |
| Selection Sort | O(n²) | O(1) | Brute Force |
| Permutations | O(n! × n) | O(n) | Backtracking |
| Maneuvering (Grid Paths) | O(2^(m+n)) | O(m+n) | Recursion |
| Combinations nCr | O(2^n) | O(n) | Recursion |
| Josephus Problem | O(n) | O(n) | Recursion |
| Maze Solving | O(2^(n²)) | O(n²) | Backtracking |
| Activity Selection | O(n log n) | O(1) | Greedy |
| N-Queens | O(n!) | O(n) | Backtracking |

---

## 1. Max Equilibrium Sum

**Problem**: Find index where `leftSum == rightSum`, maximize `leftSum + arr[i]`

**Type**: Prefix Sum / Single Pass

**Key Insight**: 
- Calculate total sum first
- Iterate: `totalSum -= arr[i]` makes it rightSum
- Check if `leftSum == totalSum`, then update result
- Add `arr[i]` to leftSum after checking

**Formula**: `equilibriumSum = leftSum + arr[i]` when `leftSum == rightSum`

**Complexity**: O(n) time, O(1) space

---

## 2. Leaders in Array

**Problem**: Element is leader if all elements to its RIGHT are smaller

**Type**: Single Pass (Right to Left)

**Key Insight**:
- Rightmost element is ALWAYS a leader
- Traverse from right, track `maxSoFar`
- If `arr[i] > maxSoFar` → it's a leader

**Complexity**: O(n) time, O(1) space

**MCQ Trivia**: 
- Last element = always leader
- If array is sorted ascending → only last is leader
- If array is sorted descending → all are leaders

---

## 3. Majority Element (Boyer-Moore Voting)

**Problem**: Find element appearing more than `n/2` times

**Type**: Boyer-Moore Voting Algorithm

**Two Phases**:
1. **Find Candidate**: count++/-- method
2. **Verify**: actually count occurrences

**Key Insight**:
- If count becomes 0, change candidate to current
- Increment/decrement based on match

**Code Pattern**:
```
if (count == 0) candidate = num;
count += (num == candidate) ? 1 : -1;
```

**Complexity**: O(n) time, O(1) space

**MCQ Trivia**:
- Verification is OPTIONAL only if problem GUARANTEES majority exists
- Verification is MANDATORY if you need to return -1 when no majority
- Works because majority elements "survive" the voting

---

## 4. Quick Sort ⚡

**Type**: DIVIDE AND CONQUER

**Algorithm**:
1. Pick pivot (usually last element)
2. Partition: elements < pivot go left, > pivot go right
3. Recursively sort left and right halves

**Key Formula (Partition)**:
```
i = lo - 1
for j = lo to hi-1:
    if a[j] < pivot: swap a[++i] with a[j]
swap a[i+1] with a[hi]  // put pivot in place
return i+1
```

**Complexity**:
- **Average**: O(n log n)
- **Worst** (sorted array): O(n²)
- **Space**: O(log n) for recursion stack

**MCQ Trivia**:
- NOT stable (equal elements may change order)
- IN-PLACE sorting
- Worst case when array already sorted OR all elements same
- Best pivot = median (but hard to find)

---

## 5. Selection Sort

**Type**: BRUTE FORCE

**Algorithm**:
1. Find minimum in unsorted portion
2. Swap with first unsorted element
3. Repeat

**Key Pattern**:
```
for i = 0 to n-2:
    min = i
    for j = i+1 to n-1:
        if a[j] < a[min]: min = j
    swap a[i] with a[min]
```

**Complexity**: O(n²) ALWAYS (no best case improvement)

**MCQ Trivia**:
- NOT stable
- IN-PLACE
- Makes minimum swaps: O(n)
- Good when writes are expensive

---

## 6. Permutations (Backtracking)

**Type**: BACKTRACKING

### Basic Permutation (swap method in your Permutations.java)
```
swap(fi, i)
permutation(fi + 1)
swap(fi, i)  // backtrack
```

### Sorted Unique Permutation (handles duplicates)
**Key**: Sort array first, then skip duplicates:
```
if (i > 0 && arr[i] == arr[i-1] && !used[i-1]) continue;
```

**Complexity**: O(n! × n) time, O(n) space

**MCQ Trivia**:
- n distinct elements → n! permutations
- With duplicates: n! / (k1! × k2! × ...) where ki = count of each duplicate

---

## 7. Combinations (nCr)

**Type**: RECURSION (Pascal's Triangle)

**Formula**: `C(n,r) = C(n-1, r-1) + C(n-1, r)`

**Base Cases**: 
- `r == 0` → return 1 (empty set)
- `r == n` → return 1 (pick all)

**Intuition**: For each element, either INCLUDE it (n-1, r-1) or EXCLUDE it (n-1, r)

**Complexity**: O(2^n) exponential without memoization

**MCQ Trivia**:
- C(n,r) = C(n, n-r) (symmetry)
- Sum of row in Pascal's triangle = 2^n
- C(n,0) + C(n,1) + ... + C(n,n) = 2^n

---

## 8. Maneuvering (Grid Paths)

**Type**: RECURSION (can be DP)

**Problem**: Count paths from (0,0) to (m-1, n-1), only RIGHT and DOWN

**Formula**: `countPaths(m,n) = countPaths(m-1,n) + countPaths(m,n-1)`

**Base Case**: `m == 1 OR n == 1` → return 1

**Mathematical Answer**: C(m+n-2, m-1) = C(m+n-2, n-1)

**Complexity**: O(2^(m+n)) recursive, O(m×n) with DP

---

## 9. Maze Solving (with obstacles)

**Type**: BACKTRACKING

**Difference from Maneuvering**: Has blocked cells (0s)

**Key Addition**: `isSafe()` check
```
return (row >= 0 && row < n && col >= 0 && col < n && maze[row][col] == 1);
```

**Backtracking Pattern**:
```
maze[row][col] = 0;  // mark visited
solve(down); solve(right);
maze[row][col] = 1;  // backtrack
```

**Complexity**: O(2^(n²)) worst case

**MCQ Trivia**:
- Mark cell as visited BEFORE recursing
- Restore cell AFTER recursing (backtrack)

---

## 10. Josephus Problem

**Type**: RECURSION

**Problem**: n people in circle, every k-th person eliminated. Find survivor.

**Formula**: `J(n,k) = (J(n-1,k) + k) % n`

**Base Case**: `J(1,k) = 0` (0-indexed)

**For 1-indexed result**: Add 1 to final answer

**Complexity**: O(n) time, O(n) space (recursion stack)

**Historical Trivia**: Named after Flavius Josephus, Jewish historian

---

## 11. Activity Selection

**Type**: GREEDY

**Problem**: Select maximum non-overlapping activities

**Greedy Choice**: Sort by END time, pick earliest ending first

**Algorithm**:
1. Sort activities by end time
2. Select first activity
3. For each activity: if start >= lastEnd, select it

**Why sort by END time?** Leaves maximum room for other activities

**Complexity**: O(n log n) for sorting

**MCQ Trivia**:
- Sorting by START time does NOT work
- This is optimal (proven by exchange argument)
- Example of "greedy stays ahead" proof

---

## 12. N-Queens (MCQ Only)

**Type**: BACKTRACKING

**Problem**: Place n queens on n×n board, no two attack each other

**Attack conditions**:
- Same row ❌
- Same column ❌
- Same diagonal ❌

**Key Checks for each cell (r, c)**:
1. No queen in same column
2. No queen in upper-left diagonal
3. No queen in upper-right diagonal

**Complexity**: O(n!) time, O(n) space

**MCQ Trivia**:
- For n=1: 1 solution
- For n=2, n=3: 0 solutions
- For n=4: 2 solutions
- For n=8: 92 solutions
- Total solutions grow roughly as n!/e

---

## Algorithm Classification Summary

### Divide and Conquer
- Quick Sort
- Merge Sort (not in your list)

### Backtracking
- Permutations
- Maze Solving
- N-Queens

### Greedy
- Activity Selection

### Dynamic Programming (can optimize)
- Maneuvering/Grid Paths
- Combinations

### Single Pass / Prefix Techniques
- Max Equilibrium Sum
- Leaders in Array
- Majority Element

---

## Memory Tricks 🧠

1. **Quick Sort**: "Pick pivot, partition, recurse"
2. **Josephus**: "(J + k) mod n" - just remember +k and mod
3. **Majority**: "0 means switch, +1/-1 voting"
4. **Combinations**: "Pick or skip: (n-1,r-1) + (n-1,r)"
5. **Activity Selection**: "Sort by END, not start!"
6. **Permutations duplicate skip**: "Same as prev AND prev unused = skip"

---

Good luck with your exam! 🍀

---

## Input Methods for Exams 📥

### Method 1: Scanner (Most Common)
```java
import java.util.*;
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();           // single int
int[] arr = new int[n];
for(int i=0; i<n; i++) arr[i] = sc.nextInt();  // array
String s = sc.next();           // single word
String line = sc.nextLine();    // full line
```

### Method 2: BufferedReader (Faster)
```java
import java.io.*;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int n = Integer.parseInt(br.readLine());
String[] parts = br.readLine().split(" ");
int[] arr = new int[parts.length];
for(int i=0; i<parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
```

### Method 3: Hardcoded (Quick Testing)
```java
int[] arr = {5, 2, 9, 1, 7};
int n = arr.length;
```

---

## All Code Snippets 📝

### 1. Max Equilibrium Sum
```java
public static int maxEquilibrium(int[] arr) {
    int totalSum = 0;
    for (int x : arr) totalSum += x;

    int leftSum = 0, res = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
        totalSum -= arr[i];
        if (leftSum == totalSum) res = Math.max(res, leftSum + arr[i]);
        leftSum += arr[i];
    }
    return res;
}
```

---

### 2. Leaders in Array
```java
public static void findLeaders(int[] arr) {
    int n = arr.length, maxSoFar = arr[n-1];
    System.out.print(maxSoFar + " ");
    for (int i = n-2; i >= 0; i--) {
        if (arr[i] > maxSoFar) {
            maxSoFar = arr[i];
            System.out.print(maxSoFar + " ");
        }
    }
}
```

---

### 3. Majority Element
```java
public static int findMajority(int[] arr) {
    int candidate = 0, count = 0;
    for (int num : arr) {
        if (count == 0) candidate = num;
        count += (num == candidate) ? 1 : -1;
    }
    count = 0;
    for (int num : arr) if (num == candidate) count++;
    return (count > arr.length / 2) ? candidate : -1;
}
```

---

### 4. Quick Sort
```java
static void quickSort(int[] a, int lo, int hi) {
    if (lo >= hi) return;
    int p = partition(a, lo, hi);
    quickSort(a, lo, p - 1);
    quickSort(a, p + 1, hi);
}

static int partition(int[] a, int lo, int hi) {
    int pivot = a[hi], i = lo - 1;
    for (int j = lo; j < hi; j++) {
        if (a[j] < pivot) {
            i++;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }
    }
    int t = a[i+1]; a[i+1] = a[hi]; a[hi] = t;
    return i + 1;
}
```

---

### 5. Selection Sort
```java
static void selectionSort(int[] a) {
    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++)
            if (a[j] < a[min]) min = j;
        int t = a[i]; a[i] = a[min]; a[min] = t;
    }
}
```

---

### 6a. Permutations (Basic - Swap Method)
```java
public static void permutation(char[] ch, int fi) {
    if (fi == ch.length) {
        System.out.println(new String(ch));
        return;
    }
    for (int i = fi; i < ch.length; i++) {
        char t = ch[fi]; ch[fi] = ch[i]; ch[i] = t;  // swap
        permutation(ch, fi + 1);
        t = ch[fi]; ch[fi] = ch[i]; ch[i] = t;       // backtrack
    }
}
```

### 6b. Sorted Unique Permutations (Handles Duplicates)
```java
static void permute(char[] a, boolean[] used, String cur) {
    if (cur.length() == a.length) {
        System.out.println(cur);
        return;
    }
    for (int i = 0; i < a.length; i++) {
        if (used[i]) continue;
        if (i > 0 && a[i] == a[i-1] && !used[i-1]) continue;
        used[i] = true;
        permute(a, used, cur + a[i]);
        used[i] = false;
    }
}
// Call: Arrays.sort(arr); permute(arr, new boolean[arr.length], "");
```

---

### 7. Combinations (nCr)
```java
static int nCr(int n, int r) {
    if (r == 0 || r == n) return 1;
    return nCr(n - 1, r - 1) + nCr(n - 1, r);
}
```

---

### 8. Maneuvering (Grid Paths - No Obstacles)
```java
// Count paths
static int countPaths(int m, int n) {
    if (m == 1 || n == 1) return 1;
    return countPaths(m - 1, n) + countPaths(m, n - 1);
}

// Print paths
static void printPaths(int r, int c, int m, int n, String path) {
    if (r == m-1 && c == n-1) { System.out.println(path); return; }
    if (r < m-1) printPaths(r+1, c, m, n, path + "D");
    if (c < n-1) printPaths(r, c+1, m, n, path + "R");
}
```

---

### 9. Maze Solving (With Obstacles)
```java
static int[][] m;
static int n;

static void solve(int r, int c, String p) {
    if (r == n-1 && c == n-1) { System.out.println(p); return; }
    m[r][c] = 0; // mark visited
    if (r+1 < n && m[r+1][c] == 1) solve(r+1, c, p+"D");
    if (c+1 < n && m[r][c+1] == 1) solve(r, c+1, p+"R");
    m[r][c] = 1; // backtrack
}
```

---

### 10. Josephus Problem
```java
static int josephus(int n, int k) {
    if (n == 1) return 0;
    return (josephus(n - 1, k) + k) % n;
}
// For 1-indexed: josephus(n, k) + 1
```

---

### 11. Activity Selection
```java
// Version 1: Pre-sorted by finish time (faculty style)
static void select(int[] s, int[] f) {
    System.out.print(0 + " ");
    int last = 0;
    for (int j = 1; j < s.length; j++) {
        if (s[j] >= f[last]) {
            System.out.print(j + " ");
            last = j;
        }
    }
}

// Version 2: Unsorted input (need to sort)
static void selectUnsorted(int[][] a) {
    Arrays.sort(a, (x, y) -> x[1] - y[1]); // sort by end
    int end = a[0][1];
    System.out.print(0 + " ");
    for (int i = 1; i < a.length; i++) {
        if (a[i][0] >= end) {
            System.out.print(i + " ");
            end = a[i][1];
        }
    }
}
```

---
