# CAT 2 Theory Notes (MCQ Prep)

## 1) Loop Detection (Linked List)
- Core idea: Floyd cycle detection (slow, fast pointers).
- If `slow == fast`, loop exists.
- Loop start finding trick: move one pointer to head and then step both by one.
- Common MCQ trap: checking only `fast != null` but forgetting `fast.next != null`.
- Complexity: O(n) time, O(1) space.
- [ ] 
## 2) Sort Bitonic DLL
- Bitonic means first increasing, then decreasing.
- Split at turning point, reverse decreasing part, merge two sorted lists.
- Why DLL matters: must maintain both `next` and `prev` links.
- Complexity: O(n).
- [ ] 
## 3) Segregate Even and Odd Nodes
- Stable segregation keeps original order among even nodes and among odd nodes.
- Use two temporary chains and connect them.
- MCQ check: if no even nodes, output is unchanged odd list.
- Complexity: O(n), O(1).
- [ ] 
## 4) Merge Sort for DLL
- Divide list by middle node (slow-fast pointers).
- Recursively sort both halves.
- Merge while repairing `prev` pointers.
- Better than quicksort for linked lists due to no random access.
- Complexity: O(n log n).
- [ ] 
## 5) Minimum Stack
- Supports `push`, `pop`, `top`, `getMin` in O(1).
- Use second stack to track minimum history.
- If pushed value <= current min, push in min stack too.
- On pop, remove from min stack when popped value equals current min.
- [x] 
## 6) Celebrity Problem 
- Celebrity: known by everyone, knows no one.
- Two-pass method:
  - elimination pass to find candidate
  - verification pass to confirm candidate
- Matrix interpretation: `M[a][b] = 1` means `a` knows `b`.
- Complexity: O(n).
  - [x] 
## 7) Iterative Tower of Hanoi
- Total moves: `2^n - 1`.
- Recursive relation: `T(n) = 2T(n-1) + 1`.
- Iterative pattern cycles among rod pairs.
- For even `n`, swap destination and auxiliary labels.
- Complexity: exponential O(2^n).
- [ ] 
## 8) Stock Span Problem
- Span on day `i`: count of consecutive days ending at `i` with price <= current.
- Monotonic stack of indices gives nearest previous greater element.
- If stack empty, span is `i + 1`.
- Complexity: O(n) amortized.
- [x] 
## 9) Priority Queue Using DLL
- Priority queue behavior:
  - insertion based on priority
  - deletion of highest-priority element
- If list remains sorted by priority, dequeue is O(1), enqueue O(n).
- If same priority, insertion strategy may decide FIFO behavior.
- [ ] 

## 10) Sort Without Extra Space (Stack)
- Uses recursion as implicit stack memory, no explicit extra data structure.
- Steps:
  - pop one item
  - recursively sort rest
  - insert popped item into sorted position
- Complexity usually O(n^2).
- [x] 

## 11) Stack Permutations
- Validate if output sequence can come from input sequence using one stack.
- Push from input; pop whenever top matches next needed output.
- If all output consumed successfully, permutation is valid.
- Complexity: O(n).
- [x] 

## Quick MCQ Comparison Table
| Topic                    | Typical DS Pattern      | Time Complexity |
| ------------------------ | ----------------------- | --------------- |
| Loop Detection           | Two pointers            | O(n)            |
| Bitonic DLL Sort         | Split + reverse + merge | O(n)            |
| Even/Odd Segregation     | Two list tails          | O(n)            |
| Merge Sort DLL           | Divide and conquer      | O(n log n)      |
| Minimum Stack            | Auxiliary stack         | O(1) per op     |
| Celebrity                | Elimination + verify    | O(n)            |
| Iterative Hanoi          | Move pattern simulation | O(2^n)          |
| Stock Span               | Monotonic stack         | O(n)            |
| Priority Queue DLL       | Sorted insertion        | O(n) insert     |
| Sort Stack (No Extra DS) | Recursion insertion     | O(n^2)          |
| Stack Permutations       | Simulation stack        | O(n)            |

## Last Minute Exam Strategy
- If question says "nearest greater/smaller" -> think monotonic stack.
- If question says "cycle in linked list" -> think Floyd.
- If question says "fast min query with stack" -> think extra min stack.
- If question says "possible output from stack" -> think simulation.
- If question says "person known by all" -> celebrity elimination + verify.

![[Pasted image 20260325031630.png]]

