# Minimum Stack

## What It Does

Supports stack operations plus fast minimum query.

## Why This Works

Use two stacks:

- normal value stack
- minimum stack storing current minima

On push, add to min stack when new value is smaller or equal. On pop, remove from min stack if popped value equals current min.

## Input Format

- `q` operations
- commands among: `push x`, `pop`, `top`, `getMin`

## Output Format

- output for `pop`, `top`, and `getMin`

## Complexity

- Time: O(1) per operation
- Space: O(n)

## Memory Tip

Second stack tracks historical minimums.
