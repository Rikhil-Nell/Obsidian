# Iterative Tower of Hanoi

## What It Does
Prints disk moves to solve Tower of Hanoi iteratively.

## Why This Works
Number of moves is `2^n - 1`. Moves follow repeating rod-pair pattern. For even `n`, destination and auxiliary roles swap.

## Input Format
- `n` disks

## Output Format
- first line: total moves
- next lines: `fromRod toRod`

## Complexity
- Time: O(2^n)
- Space: O(n)

## Memory Tip
Remember odd/even rule and 3-move cycle.
