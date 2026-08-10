# 27. Boundary Traversal

## Theory & MCQ Prep

**Problem.** Print the boundary of a binary tree in **anti-clockwise** order, starting from the root:

1. **Root**.
2. **Left boundary** (top-down), excluding leaves.
3. **All leaves**, left to right.
4. **Right boundary** (bottom-up), excluding leaves.

No node should appear twice (e.g. the root, if a leaf, is printed only once).

- **Approach class:** Three carefully ordered traversals stitched together.
- **Time:** **O(n)**. **Space:** **O(n)** for output + recursion.
- **Common gotchas:**
  - When traversing left boundary, prefer left child; if no left child, take right child (the "boundary" follows the visible silhouette).
  - Skip leaves while collecting left/right boundaries — they're added separately by the leaves pass to avoid duplication.
  - Reverse the right boundary collection before printing.

### Example MCQs

**Q1.** Boundary traversal of a binary tree visits the boundary in:  
A) Clockwise  B) Anti-clockwise  C) Random  D) Level by level  
**Answer: B.**

**Q2.** Time complexity of boundary traversal:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(h)  
**Answer: C.**

**Q3.** Why are leaves omitted from the left/right boundary collection?  
A) To avoid duplication with the leaves pass.  B) For O(1) space.  C) Leaves are unreachable.  D) To save time.  
**Answer: A.**

**Q4.** For a BT with only one node, the boundary traversal prints:  
A) Empty  B) Root only  C) Root twice  D) Error  
**Answer: B.**

**Q5.** For the BT:
```
            1
           / \
          2   3
         / \   \
        4   5   6
```
Boundary traversal (anti-clockwise) is:  
A) 1 2 4 5 6 3  B) 1 2 4 5 3 6  C) 1 2 4 5 6 3 (same as A)  D) 1 4 5 6 3 2  
**Answer: A** (root → left bdry: 2 → leaves: 4,5,6 → right bdry reverse: 3).

## Hand-write Java Code

```java
import java.util.*;
class Node {
    int data;
    Node left, right;
    Node(int d) { data = d; left = right = null; }
}
class BST {
    Node root = null;
    Node create(Node root, int d) {
        if (root == null) return new Node(d);
        else if (d <= root.data) root.left = create(root.left, d);
        else root.right = create(root.right, d);
        return root;
    }
    boolean isLeaf(Node n) { return n.left == null && n.right == null; }
    void leftB(Node root, ArrayList<Integer> bl) {
        if (root.left == null) return;
        Node curr = root.left;
        while (curr != null) {
            if (!isLeaf(curr)) bl.add(curr.data);
            curr = (curr.left != null) ? curr.left : curr.right;
        }
    }
    void leaves(Node root, ArrayList<Integer> bl) {
        if (isLeaf(root)) { bl.add(root.data); return; }
        if (root.left != null) leaves(root.left, bl);
        if (root.right != null) leaves(root.right, bl);
    }
    void rightB(Node root, ArrayList<Integer> bl) {
        ArrayList<Integer> temp = new ArrayList<>();
        if (root.right == null) return;
        Node curr = root.right;
        while (curr != null) {
            if (!isLeaf(curr)) temp.add(curr.data);
            curr = (curr.right != null) ? curr.right : curr.left;
        }
        for (int i = temp.size() - 1; i >= 0; i--) bl.add(temp.get(i));
    }
    void Boundary(ArrayList<Integer> bl, Node root) {
        if (root == null) return;
        bl.add(root.data);
        leftB(root, bl);
        leaves(root, bl);
        rightB(root, bl);
    }
}
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BST t = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) t.root = t.create(t.root, sc.nextInt());
        ArrayList<Integer> bl = new ArrayList<>();
        t.Boundary(bl, t.root);
        for (int v : bl) System.out.print(v + " ");
    }
}
```

## Shortcut Version

Same algorithm but inline-helper compact:

```java
import java.util.*;
class N { int d; N l, r; N(int x){d=x;} }
class Main {
    static List<Integer> ans = new ArrayList<>();
    static boolean leaf(N n) { return n.l == null && n.r == null; }
    static void left(N x) {
        if (x == null || leaf(x)) return;
        ans.add(x.d);
        left(x.l != null ? x.l : x.r);
    }
    static void leaves(N x) {
        if (x == null) return;
        if (leaf(x)) ans.add(x.d);
        leaves(x.l); leaves(x.r);
    }
    static void right(N x) {
        if (x == null || leaf(x)) return;
        right(x.r != null ? x.r : x.l);
        ans.add(x.d);
    }
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        N root = null;
        for (int i = 0; i < n; i++) root = ins(root, s.nextInt());
        if (root != null) {
            ans.add(root.d);
            left(root.l);
            if (!leaf(root)) leaves(root);
            right(root.r);
        }
        ans.forEach(v -> System.out.print(v + " "));
    }
    static N ins(N r, int d) {
        if (r == null) return new N(d);
        if (d <= r.d) r.l = ins(r.l, d); else r.r = ins(r.r, d);
        return r;
    }
}
```
