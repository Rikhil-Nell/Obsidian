# Sort Bitonic DLL

## What It Does

Sorts a bitonic doubly linked list (first increasing, then decreasing) into a fully increasing order.

## Why This Works

A bitonic list can be split at the turning point:

- first half is already increasing
- second half is decreasing, so reverse it

Then merge two sorted DLLs.

## Input Format

- `n`
- `n` integers in bitonic order

## Output Format

- sorted DLL values in increasing order

## Complexity

- Time: O(n)
- Space: O(1) extra (not counting recursion/printing helpers)

## Memory Tip

Formula in head: split -> reverse second -> merge.
