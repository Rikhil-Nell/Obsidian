# Loop Detection

## What It Does

Detects whether a singly linked list has a loop using Floyd's tortoise-hare method. Also prints the starting node value of the loop when it exists.

## Why This Works

A slow pointer moves one step and a fast pointer moves two steps. If a loop exists, they must meet inside the loop. To get loop start, move one pointer to head and move both one step at a time.

## Input Format

- `n` (number of nodes)
- `n` integers (list values)
- `position` (0-based index where tail connects, `-1` means no loop)

## Output Format

- `true` or `false` (loop exists)
- start node value of loop, or `-1` if no loop

## Complexity

- Time: O(n)
- Space: O(1)

## Memory Tip

Think in two parts:

1. Detect collision.
2. Reset one pointer to head, walk both equally to loop entry.
