# Stack Permutations

## What It Does
Checks if one sequence can be produced from another using only stack push/pop behavior.

## Why This Works
Push input elements one by one. After each push, pop while stack top matches next required output element.

## Input Format
- `n`
- `n` integers for input sequence
- `n` integers for desired output sequence

## Output Format
- `true` or `false`

## Complexity
- Time: O(n)
- Space: O(n)

## Memory Tip
Always greedily pop whenever top matches output.
