# Segregate Even and Odd Linked List

## What It Does

Rearranges a linked list so all even-valued nodes come first, followed by all odd-valued nodes, while preserving original relative order inside each group.

## Why This Works

Create two chains while traversing once:

- even list
- odd list

At the end, connect even tail to odd head.

## Input Format

- `n`
- `n` integers

## Output Format

- list after segregation

## Complexity

- Time: O(n)
- Space: O(1)

## Memory Tip

Keep four pointers: `evenHead`, `evenTail`, `oddHead`, `oddTail`.
