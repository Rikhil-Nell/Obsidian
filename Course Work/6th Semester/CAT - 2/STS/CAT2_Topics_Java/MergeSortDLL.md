# Merge Sort for DLL

## What It Does

Sorts a doubly linked list using merge sort.

## Why This Works

Merge sort fits linked lists well:

- find middle by slow-fast pointers
- recursively sort left and right parts
- merge sorted halves while fixing both `next` and `prev`

## Input Format

- `n`
- `n` integers

## Output Format

- sorted DLL values

## Complexity

- Time: O(n log n)
- Space: O(log n) recursion stack

## Memory Tip

Critical part to remember: while merging DLL, update `prev` pointers too.
