# Recommended Study Order

A 6-tier path from easiest to hardest. Each tier shares a *pattern* — once you've internalised the pattern, the whole tier collapses into "fill in the variation." Aim to be able to **hand-write the entire file from memory** before moving on.

> **Suggested cadence:** Tier 1–2 in one sitting, Tier 3 the next day, Tier 4 over two days, Tier 5–6 across the final two days. Re-do Tier 1 every morning as a warm-up — it's free marks.

Numbers in `[brackets]` are the topic index from `00-Index.md`.

---

## Tier 1 — Single-pass array scans (easiest, do first)

**Pattern.** `Scanner` → fill array → one for-loop with a running variable. **No recursion, no extra data structure.** Memorise the Scanner boilerplate here once and you'll reuse it everywhere.

| # | Topic | Why it's first |
|---|-------|----------------|
| 1 | **Leaders in Array** [2] | 5 lines of body. Right-to-left scan + running max. |
| 2 | **Max Equilibrium Sum** [1] | Prefix/suffix in one loop. Same shape as Leaders, just two running totals. |
| 3 | **Majority Element (Moore's Voting)** [3] | Counter + verification pass. The "pair-cancellation" idea is the only new concept. |
| 4 | **Selection Sort** [5] | Nested loop, swap. The simplest sort to write under pressure. |

After this tier you should be able to: declare an int array, read `n` then `n` values, run a loop, swap elements, and use `Math.max`/`Math.min`. Everything else builds on this.

---

## Tier 2 — Plain recursion + greedy (no backtracking yet)

**Pattern.** A function that calls itself with smaller parameters and returns. **No `mark / recurse / unmark`** stuff yet. Internalise "base case + recursive case + how the answers combine."

| # | Topic | Why now |
|---|-------|---------|
| 5 | **Josephus Trap** [9] | The whole algorithm is **one line**: `(josh(n-1, k) + k) % n`. Easiest recursion in the syllabus. |
| 6 | **Maneuvering** [7] | Two-line recursion: base case (boundary) + sum of two recursive calls. Mirrors Josephus structurally. |
| 7 | **Activity Selection** [12] | Greedy is just a single-pass scan with a "last finish time" tracker. Tier-1 style with one extra rule. |
| 8 | **Loop Detection (Floyd)** [13] | Two-pointer scan on a linked list. The hard part is the linked-list scaffolding (insert, createloop) — the algorithm itself is 8 lines. |

After Tier 2 you've covered **8 topics** and you can confidently write recursive base cases, recursive returns, and two-pointer linked-list code.

---

## Tier 3 — Stack & queue idioms

**Pattern.** Push / pop with one invariant. Most of these are ~15 lines.

| # | Topic | Why now |
|---|-------|---------|
| 9  | **Stack Permutations** [23] | Pure simulation: push current, pop while top matches output. Cleanest stack problem in the syllabus. |
| 10 | **Stock Span** [20] | Monotonic stack template. The most reusable idea in the exam — also appears in next-greater-element problems. |
| 11 | **Minimum Stack** [17] | Two stacks; aux stack tracks current minimum. The push/pop rules are 4 lines each. |
| 12 | **Celebrity Problem** [18] | Stack elimination + verification. Same "push then pop pairs" rhythm as Stack Permutations. |
| 13 | **Sort Queue Without Extra Space** [22] | Two helper functions (`findMin`, `insertToRear`) wrapped in a loop — selection sort on a queue. Lengthier but mechanical. |
| 14 | **Iterative Tower of Hanoi** [19] | 3 stacks + a `change(s1, s2)` helper + the `i % 3` pattern + the parity swap when n is even. Most code in this tier — hardest of the lot, but still pure stack work. |

---

## Tier 4 — Backtracking patterns (templated recursion)

**Pattern.** `for each choice → mark → recurse → unmark`. Memorise the *template* once; each problem is a small variation.

| # | Topic | Why now |
|---|-------|---------|
| 15 | **Combination** [8] | The cleanest "choose / move start forward" template. 7 lines of recursion. |
| 16 | **Sorted Unique Permutation** [6] | Swap-based recursion + `TreeSet` for sort+dedupe. Adds the "swap → recurse → swap-back" idiom. |
| 17 | **Maze Solving** [10] | Adds the "mark cell, try directions, unmark on failure" idiom. 2D version of Combination. |
| 18 | **N-Queens (MCQs only!)** [11] | You don't need to write the code — just know: **backtracking, O(N!), 92 solutions for N=8, no solution for N=2 or N=3, diagonal check `r-c` / `r+c`**. Pure MCQ memorisation. |

After Tier 4 you've covered **18 topics** — all the array, recursion, stack, and backtracking work is done.

---

## Tier 5 — Linked-list pointer surgery (where bugs live)

**Pattern.** You're rewiring `next`/`prev` pointers. The algorithms are short but **easy to mis-type and waste minutes debugging without a debugger**. Practice on paper.

Within this tier go in this order:

| # | Topic | Why this order |
|---|-------|----------------|
| 19 | **Segregate Even & Odd Nodes** [15] | Single-LL, 4-pointer book-keeping (`es, ee, os, oe`). Easiest pointer rewire. |
| 20 | **Quick Sort** [4] | Not a linked-list problem, but pivot/partition logic is conceptually similar to "split". Safe to slot here as a refresher before harder split-based work. |
| 21 | **Priority Queue using DLL** [21] | Sorted insert into a DLL with 4 cases (empty, before front, between, at rear). Each case is 3 lines but you must handle all four. |
| 22 | **Sort Bitonic DLL** [14] | Two pointers (head/tail), pull whichever is smaller, append at result tail. Easy to lose `prev` pointers — rewrite from scratch twice before exam. |
| 23 | **Merge Sort for DLL** [16] | The boss of this tier. Three functions: `split` (slow/fast), `sort` (recursive), `merge` (recursive with `prev` rewires). Practice the merge function until you can write it blindfolded. |

---

## Tier 6 — Trees & Graphs

**Pattern.** Build a node class, build a queue of `Qnode`s carrying extra metadata (level, horizontal distance), then BFS or recurse. Most of the code here is *boilerplate* (Node class, BST insert via `create`) which you should master **once** because it's reused across all five tree topics.

### Sub-tier 6a — Trees (master the BST `create()` boilerplate first)

| # | Topic | Why this order |
|---|-------|----------------|
| 24 | **Views of Tree (Right view)** [25] | Single recursion with a level counter and `if (al.size() == level) add`. The simplest tree problem and the template for left view. |
| 25 | **Boundary Traversal** [27] | Three small helpers (`leftB`, `leaves`, `rightB`) called in order. Each helper is ~6 lines. |
| 26 | **Recover BST** [24] | In-order traversal with a `prev` pointer + the "1 vs 2 descents" rule. Conceptually rich, mechanically short. |
| 27 | **Vertical Order Traversal** [26] | Introduces the `Qnode(node, ver, level)` + `TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>>` pattern. Once you nail this... |
| 28 | **Top & Bottom Views (in Views of Tree)** [25] | ...Top/Bottom views are *the same code* as Vertical Order with `firstEntry().getValue().getFirst()` and `lastEntry().getValue().getLast()` instead of the full nested loop. Free win. |

### Sub-tier 6b — Graphs (templates that scale)

| # | Topic | Why this order |
|---|-------|----------------|
| 29 | **BFS** [28] | Build adjacency list, queue, visited[]. 12-line traversal. **The boilerplate for every graph problem.** |
| 30 | **DFS** [29] | Same adjacency-list boilerplate as BFS, just recursive. Pair-learn with BFS — they share 80% of the code. |
| 31 | **Bellman-Ford** [31] | An `Edge(src, dest, weight)` class + double for-loop relaxation. Conceptually heavier than BFS/DFS but mechanically only ~10 extra lines. |
| 32 | **Dial's Algorithm** [30] | The *hardest* code in the syllabus: weighted-edge adjacency list + bucket array of queues + skip-stale check. **Save for last.** If you're truly out of time, study its **shortcut version** (Dijkstra with `PriorityQueue`) which is shorter and most autograders accept. |

---

## Cheatsheet: which tier is each topic in?

| Topic # | Topic | Tier |
|---------|-------|------|
| 1  | Max Equilibrium Sum | 1 |
| 2  | Leaders in Array | 1 |
| 3  | Majority Element | 1 |
| 4  | Quick Sort | 5 |
| 5  | Selection Sort | 1 |
| 6  | Sorted Unique Permutation | 4 |
| 7  | Maneuvering | 2 |
| 8  | Combination | 4 |
| 9  | Josephus Trap | 2 |
| 10 | Maze Solving | 4 |
| 11 | N-Queens (MCQ only) | 4 |
| 12 | Activity Selection | 2 |
| 13 | Loop Detection | 2 |
| 14 | Sort Bitonic DLL | 5 |
| 15 | Segregate Even/Odd LL | 5 |
| 16 | Merge Sort DLL | 5 |
| 17 | Minimum Stack | 3 |
| 18 | Celebrity Problem | 3 |
| 19 | Iterative Tower of Hanoi | 3 |
| 20 | Stock Span | 3 |
| 21 | Priority Queue using DLL | 5 |
| 22 | Sort Without Extra Space | 3 |
| 23 | Stack Permutations | 3 |
| 24 | Recover BST | 6a |
| 25 | Views of Tree | 6a |
| 26 | Vertical Order | 6a |
| 27 | Boundary Traversal | 6a |
| 28 | BFS | 6b |
| 29 | DFS | 6b |
| 30 | Dial's Algorithm | 6b |
| 31 | Bellman-Ford | 6b |

---

## Strategy notes for the locked browser exam

1. **Decide your two coding answers in advance.** Pick the two algorithms you can hand-write *fastest with zero bugs* — likely from Tier 1–3 plus one Tier 4 backtracker. Don't gamble on Merge Sort DLL or Dial's unless you've drilled them.
2. **Write the Scanner skeleton first**, even before reading the problem fully. `import java.util.*; class Main { public static void main(String args[]) { Scanner sc = new Scanner(System.in); ... } }`. That's 4 lines of buffer that you don't need to think about.
3. **If you forget the algorithm, fall back to the Shortcut Version** in your notes. `Arrays.sort`, `Collections.sort`, `PriorityQueue`, `TreeSet`, `Integer.bitCount` cover surprising amounts of ground when you can't recall the canonical solution.
4. **For MCQs**, the high-yield categories are: time/space complexity, classification (greedy/DP/backtracking), iteration counts (e.g. Josephus survivor for n=10, k=2 → 5; N-Queens 92 solutions; Catalan numbers for stack permutations), and "which algorithm handles negative weights" (Bellman-Ford, not Dijkstra/Dial's). Skim the **Time/Space Quick Reference** in `00-Index.md` the morning of the exam.
5. **N-Queens is MCQ-only** per syllabus — do not waste hand-writing time on its code.
