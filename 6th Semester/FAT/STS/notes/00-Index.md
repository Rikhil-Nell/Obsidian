# STS4005 - DSA Exam Prep Index

Start here:

- [study-order.md](study-order.md) - easiest-to-hardest study path, grouped by pattern and difficulty.
- `../JavaCodes/` - runnable Java practice files. Each file uses `class Main`, so copy/rename one file as `Main.java` when compiling individually.

Each topic note has:

- **Theory & MCQ Prep** - concepts, classification, time/space complexity, gotchas, and MCQs.
- **Hand-write Java Code** - the version to memorise for the locked browser.
- **Shortcut Version** - fast built-in API trick when it can pass automated test cases.

## Topic Notes

| # | Topic | Category | File |
|---|-------|----------|------|
| 1 | Max Equilibrium Sum | Array / Prefix-Suffix | [01-MaxEquilibriumSum.md](01-MaxEquilibriumSum.md) |
| 2 | Leaders in Array | Array / Right-scan | [02-LeadersInArray.md](02-LeadersInArray.md) |
| 3 | Majority Element (Moore's Voting) | Array / Voting | [03-MajorityElement.md](03-MajorityElement.md) |
| 4 | Quick Sort | Divide & Conquer | [04-QuickSort.md](04-QuickSort.md) |
| 5 | Selection Sort | Brute Force | [05-SelectionSort.md](05-SelectionSort.md) |
| 6 | Sorted Unique Permutation | Backtracking | [06-SortedUniquePermutation.md](06-SortedUniquePermutation.md) |
| 7 | Maneuvering Problem | Recursion / DP | [07-Maneuvering.md](07-Maneuvering.md) |
| 8 | Combination | Backtracking | [08-Combination.md](08-Combination.md) |
| 9 | Josephus Trap | Recursion | [09-JosephusTrap.md](09-JosephusTrap.md) |
| 10 | Maze Solving (Rat in a Maze) | Backtracking | [10-MazeSolving.md](10-MazeSolving.md) |
| 11 | N-Queens (MCQ only) | Backtracking | [11-NQueens.md](11-NQueens.md) |
| 12 | Activity Selection Problem | Greedy | [12-ActivitySelection.md](12-ActivitySelection.md) |
| 13 | Loop Detection in LL | Linked List / Floyd | [13-LoopDetection.md](13-LoopDetection.md) |
| 14 | Sort Bitonic DLL | Linked List | [14-SortBitonicDLL.md](14-SortBitonicDLL.md) |
| 15 | Segregate Even/Odd Nodes in LL | Linked List | [15-SegregateEvenOdd.md](15-SegregateEvenOdd.md) |
| 16 | Merge Sort for DLL | Linked List / D&C | [16-MergeSortDLL.md](16-MergeSortDLL.md) |
| 17 | Minimum Stack | Stack | [17-MinimumStack.md](17-MinimumStack.md) |
| 18 | Celebrity Problem | Stack | [18-CelebrityProblem.md](18-CelebrityProblem.md) |
| 19 | Iterative Tower of Hanoi | Stack | [19-IterativeTowerOfHanoi.md](19-IterativeTowerOfHanoi.md) |
| 20 | Stock Span Problem | Stack / Monotonic | [20-StockSpan.md](20-StockSpan.md) |
| 21 | Priority Queue using DLL | Linked List / Queue | [21-PriorityQueueDLL.md](21-PriorityQueueDLL.md) |
| 22 | Sort Queue Without Extra Space | Queue | [22-SortWithoutExtraSpace.md](22-SortWithoutExtraSpace.md) |
| 23 | Stack Permutations | Stack | [23-StackPermutations.md](23-StackPermutations.md) |
| 24 | Recover BST | Tree | [24-RecoverBST.md](24-RecoverBST.md) |
| 25 | Views of Tree (Left/Right/Top/Bottom) | Tree / BFS | [25-ViewsOfTree.md](25-ViewsOfTree.md) |
| 26 | Vertical Order Traversal | Tree / BFS | [26-VerticalOrder.md](26-VerticalOrder.md) |
| 27 | Boundary Traversal | Tree | [27-BoundaryTraversal.md](27-BoundaryTraversal.md) |
| 28 | BFS | Graph | [28-BFS.md](28-BFS.md) |
| 29 | DFS | Graph | [29-DFS.md](29-DFS.md) |
| 30 | Dial's Algorithm | Graph / Shortest Path | [30-DialsAlgorithm.md](30-DialsAlgorithm.md) |
| 31 | Bellman-Ford Algorithm | Graph / Shortest Path | [31-BellmanFord.md](31-BellmanFord.md) |

## Quick Classification Cheat-Sheet

- **Greedy:** Activity Selection.
- **Backtracking:** N-Queens, Maze Solving, Sorted Unique Permutation, Combination.
- **Divide & Conquer:** Quick Sort, Merge Sort for DLL.
- **Brute Force:** Selection Sort.
- **DP-style recurrence:** Maneuvering with memoisation, Bellman-Ford edge relaxation.
- **Recursion:** Josephus, Maneuvering, Tower of Hanoi.
- **Two-pointer / scan:** Max Equilibrium Sum, Leaders, Majority Element, Loop Detection.
- **Monotonic Stack:** Stock Span.
- **Stack elimination:** Celebrity Problem, Stack Permutations.
- **Bucket shortest path:** Dial's Algorithm.
- **Negative-weight shortest path:** Bellman-Ford.
- **BFS/DFS based:** Views of Tree, Vertical Order, BFS, DFS.

## Study Priority

If time is short, use this order:

1. Tier 1 from [study-order.md](study-order.md): Leaders, Max Equilibrium, Majority, Selection Sort.
2. Tier 2: Josephus, Maneuvering, Activity Selection, Loop Detection.
3. Tier 3: Stack Permutations, Stock Span, Minimum Stack, Celebrity.
4. Tier 4: Combination, Sorted Unique Permutation, Maze Solving, N-Queens MCQs.
5. Tier 5: Linked-list pointer topics and Quick Sort.
6. Tier 6: Trees, then BFS/DFS, then Bellman-Ford, and finally Dial's Algorithm.

## Time/Space Quick Reference

| Algorithm | Time | Space |
|-----------|------|-------|
| Max Equilibrium Sum | O(n) | O(1) |
| Leaders in Array | O(n) | O(1) |
| Majority Element | O(n) | O(1) |
| Quick Sort | Avg O(n log n), worst O(n^2) | O(log n) |
| Selection Sort | O(n^2) | O(1) |
| Sorted Unique Permutation | O(n! * n log(n!)) | O(n!) |
| Maneuvering recursion | O(2^(m+n)) | O(m+n) |
| Maneuvering DP | O(m*n) | O(m*n) |
| Combination | O(C(n,r) * r) | O(r) |
| Josephus | O(n) | O(n) recursive / O(1) iterative |
| Maze Solving | O(2^(r*c)) | O(r*c) |
| N-Queens | O(N!) | O(N^2) |
| Activity Selection | O(n log n) with sorting, O(n) if pre-sorted | O(1) |
| Loop Detection | O(n) | O(1) |
| Sort Bitonic DLL | O(n) | O(1) |
| Segregate Even/Odd LL | O(n) | O(1) |
| Merge Sort DLL | O(n log n) | O(log n) |
| Minimum Stack | O(1) per operation | O(n) |
| Celebrity Problem | O(n) | O(n) stack / O(1) two-pointer |
| Tower of Hanoi | O(2^n) | O(n) |
| Stock Span | O(n) | O(n) |
| Priority Queue using DLL | Insert O(n), delete O(1) | O(n) |
| Sort Queue Without Extra Space | O(n^2) | O(1) |
| Stack Permutations | O(n) | O(n) |
| Recover BST | O(n) | O(h) |
| Views of Tree | O(n) for left/right, O(n log n) for top/bottom with TreeMap | O(n) |
| Vertical Order Traversal | O(n log n) | O(n) |
| Boundary Traversal | O(n) | O(n) |
| BFS | O(V+E) | O(V) |
| DFS | O(V+E) | O(V) |
| Dial's Algorithm | O(W*V + E) | O(W*V) |
| Bellman-Ford | O(V*E) | O(V) |
