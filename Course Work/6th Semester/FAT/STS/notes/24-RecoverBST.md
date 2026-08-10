# 24. Recover the BST

## Theory & MCQ Prep

**Problem.** A valid BST has had **exactly two** of its nodes' values swapped. Recover the BST by swapping them back, **without changing the structure**.

**Idea.** In-order traversal of a valid BST yields strictly **increasing** values. If two values are swapped, the in-order sequence has either **one** or **two** descents (depending on whether the swapped nodes are adjacent in in-order or not):
- **Adjacent swap** → one descent. The two violators are the descent's two endpoints.
- **Non-adjacent swap** → two descents. The first violator is the *first* of the first descent; the second is the *second* of the second descent.

**Pattern (linear in-order with `prev` pointer):**
```
if (prev != null && root.data < prev.data):
    if (first == null) first = prev
    last = root
```
After traversal, swap `first.data` and `last.data`.

- **Approach class:** In-order traversal + invariant tracking.
- **Time:** **O(n)**. **Space:** **O(h)** for recursion (h = height; up to O(n) on skewed BST, O(log n) balanced).
- **Morris traversal variant:** O(1) space, O(n) time.

### Example MCQs

**Q1.** In-order traversal of a valid BST is:  
A) Decreasing  B) Sorted increasing  C) Random  D) Level-by-level  
**Answer: B.**

**Q2.** Time complexity of recovering a BST with two swapped nodes:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(log n)  
**Answer: C.**

**Q3.** When the two swapped nodes are NOT adjacent in in-order, the in-order traversal exhibits:  
A) Zero descents  B) One descent  C) Two descents  D) Three descents  
**Answer: C.**

**Q4.** Auxiliary space using normal recursion (not Morris):  
A) O(n)  B) O(h)  C) O(1)  D) O(n²)  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class node {
    int data;
    node left, right;
    node(int d) { data = d; left = right = null; }
}
class BT {
    node root = null;
    node first = null;
    node last = null;
    node prev = null;
    Scanner sc = new Scanner(System.in);
    node insert() {
        int d = sc.nextInt();
        if (d == -1) return null;
        node tnode = new node(d);
        Queue<node> q = new LinkedList<>();
        q.add(tnode);
        root = tnode;
        while (!q.isEmpty()) {
            node curr = q.poll();
            int l = sc.nextInt();
            if (l != -1) {
                node lnode = new node(l);
                curr.left = lnode;
                q.add(lnode);
            }
            int r = sc.nextInt();
            if (r != -1) {
                node rnode = new node(r);
                curr.right = rnode;
                q.add(rnode);
            }
        }
        return root;
    }
    private void inorder(node root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.data < prev.data) {
            if (first == null) { first = prev; last = root; }
            else last = root;
        }
        prev = root;
        inorder(root.right);
    }
    public void Inorder(node root) {
        if (root == null) return;
        Inorder(root.left);
        System.out.print(root.data + " ");
        Inorder(root.right);
    }
    void recoverTree(node root) {
        prev = new node(Integer.MIN_VALUE);
        inorder(root);
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }
    }
}
class Main {
    public static void main(String ar[]) {
        BT b = new BT();
        b.root = b.insert();
        b.Inorder(b.root);
        b.recoverTree(b.root);
        System.out.println();
        b.Inorder(b.root);
    }
}
```

## Shortcut Version

Collect in-order values, sort a copy, fix the two indices that differ:

```java
import java.util.*;
class N { int d; N l, r; N(int x){d=x;} }
class Main {
    static List<N> a = new ArrayList<>();
    static void in(N x) { if (x == null) return; in(x.l); a.add(x); in(x.r); }
    public static void main(String[] z) {
        Scanner s = new Scanner(System.in);
        Queue<N> q = new LinkedList<>();
        int r0 = s.nextInt();
        N root = new N(r0); q.add(root);
        while (!q.isEmpty()) {
            N c = q.poll();
            int L = s.nextInt(); if (L != -1) { c.l = new N(L); q.add(c.l); }
            int R = s.nextInt(); if (R != -1) { c.r = new N(R); q.add(c.r); }
        }
        in(root);
        int[] vals = a.stream().mapToInt(n -> n.d).toArray();
        int[] sorted = vals.clone();
        Arrays.sort(sorted);
        int p = -1, q2 = -1;
        for (int i = 0; i < vals.length; i++) if (vals[i] != sorted[i]) { if (p < 0) p = i; q2 = i; }
        if (p >= 0) { int t = a.get(p).d; a.get(p).d = a.get(q2).d; a.get(q2).d = t; }
        a.forEach(n -> System.out.print(n.d + " "));
    }
}
```
