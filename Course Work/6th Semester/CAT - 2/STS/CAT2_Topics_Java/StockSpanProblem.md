# Stock Span Problem

## What It Does
For each day, calculates how many consecutive previous days have price less than or equal to today's price.

## Why This Works
Maintain stack of indices with strictly greater prices. For each day, pop smaller/equal prices; nearest greater index gives span boundary.

## Input Format
- `n`
- `n` stock prices

## Output Format
- `n` span values

## Complexity
- Time: O(n)
- Space: O(n)

## Memory Tip
Monotonic decreasing stack of prices by index.
