# Sort Without Extra Space

## What It Does
Sorts a stack using recursion only (no extra data structure like another stack or array).

## Why This Works
Recursive idea:
- pop top
- sort remaining stack
- insert popped value at proper position recursively

## Input Format
- `n`
- `n` integers pushed into stack

## Output Format
- sorted values by repeatedly popping stack top

## Complexity
- Time: O(n^2)
- Space: O(n) recursion stack

## Memory Tip
Two recursive methods: `sortStack` and `insertInSortedOrder`.
