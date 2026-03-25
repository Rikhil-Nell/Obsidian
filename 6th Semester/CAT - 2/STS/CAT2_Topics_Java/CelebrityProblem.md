# Celebrity Problem

## What It Does
Finds a person known by everyone but who knows no one.

## Why This Works
Elimination pass keeps one candidate:
- if candidate knows `i`, candidate cannot be celebrity
Then verify candidate row and column conditions.

## Input Format
- `n`
- `n x n` matrix where `matrix[a][b] = 1` means `a` knows `b`

## Output Format
- celebrity index, or `-1`

## Complexity
- Time: O(n)
- Space: O(1)

## Memory Tip
Two-phase pattern: eliminate first, verify second.
