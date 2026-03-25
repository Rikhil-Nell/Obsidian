# Priority Queue Using DLL

## What It Does
Implements a priority queue using a doubly linked list.

## Why This Works
List is maintained sorted by priority (smaller number = higher priority). So dequeue and peek are always from head.

## Input Format
- `q` operations
- commands among: `enqueue value priority`, `dequeue`, `peek`, `display`

## Output Format
- outputs for `dequeue`, `peek`, `display`

## Complexity
- Enqueue: O(n)
- Dequeue: O(1)
- Peek: O(1)

## Memory Tip
Keep the list always sorted so removal is easy.
