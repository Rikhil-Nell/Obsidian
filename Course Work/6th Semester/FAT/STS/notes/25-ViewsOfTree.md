# 25. Views of a Tree (Left / Right / Top / Bottom)

## Theory & MCQ Prep

A *view* is what an external observer sees from a particular direction.

| View    | What is visible                                              | Standard technique                                  |
|---------|--------------------------------------------------------------|------------------------------------------------------|
| Left    | First node at every depth (when traversing left-first)       | Recursive: visit left then right; print on entering a new level |
| Right   | First node at every depth (when traversing right-first)      | Recursive: visit right then left; print on entering a new level |
| Top     | First node at each **horizontal distance (HD)**              | BFS with HD; for each HD remember the first encounter (smallest level) |
| Bottom  | Last node at each **horizontal distance (HD)**               | BFS with HD; for each HD overwrite to keep the last (largest level) |

**Horizontal distance.** Root has HD = 0, left child HD = parent − 1, right child HD = parent + 1.

- **Left/Right view (recursive):** O(n) time, O(h) recursion.
- **Top/Bottom view (BFS + TreeMap):** O(n log n) time, O(n) space.
- **Common gotcha for Top View:** if you process left-first BFS naïvely, the *deeper* node at the same HD might overwrite the shallower one — always compare levels (the class code stores `(HD, level, list)`).

### Example MCQs

**Q1.** The right view of a BT is the:  
A) Last node at every level  B) First node at every level  C) Rightmost node at every level (=last in level order)  D) None  
**Answer: C** (equivalently, the first node visited at each level when traversing right-then-left).

**Q2.** Top view problem can be solved using:  
A) DFS only  B) BFS with horizontal distance and TreeMap  C) Inorder traversal  D) Hashing without map  
**Answer: B.**

**Q3.** Time complexity of top view using BFS + TreeMap:  
A) O(n)  B) O(n log n)  C) O(n²)  D) O(log n)  
**Answer: B** (TreeMap operations are O(log n) per node).

**Q4.** Number of nodes in the bottom view of a perfect BT of height h:  
A) 2^h  B) 2^(h+1) − 1  C) h+1  D) 2h+1  
**Answer: A** (the bottom view = the leaves of a perfect BT, count = 2^h, and `2h+1` is the count of distinct horizontal distances which also equals 2^h for h ≥ 1 — the cleanest answer is **A**).

**Q5.** For the BT:
```
        1
       / \
      2   3
       \   \
        4   5
```
Right view is:  
A) 1 3 5  B) 1 3 4 5  C) 1 2 3  D) 1 3  
**Answer: A.**

## Hand-write Java Code

(Right View on a BST built by `create()`. Mirror the recursion direction for Left view.)

```java
import java.util.*;
class node {
    int data;
    node left, right;
    node(int d) { data = d; left = right = null; }
}
class BST {
    node root = null;
    node create(node root, int d) {
        if (root == null) return new node(d);
        else if (d <= root.data) root.left = create(root.left, d);
        else root.right = create(root.right, d);
        return root;
    }
    void Rightview(node root, ArrayList<Integer> al, int l) {
        if (root == null) return;
        if (al.size() == l) al.add(root.data);
        if (root.right != null) Rightview(root.right, al, l + 1);
        if (root.left != null) Rightview(root.left, al, l + 1);
    }
}
class Main {
    public static void main(String ars[]) {
        Scanner sc = new Scanner(System.in);
        BST t = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) t.root = t.create(t.root, sc.nextInt());
        ArrayList<Integer> al = new ArrayList<>();
        t.Rightview(t.root, al, 0);
        System.out.println(al);
    }
}
```

**Top + Bottom View** (BFS with HD + TreeMap), as in the class code:

```java
import java.util.*;
class Node { int data; Node left, right; Node(int d){data=d;} }
class Qnode { Node node; int v, l; Qnode(Node n, int v, int l){this.node=n;this.v=v;this.l=l;} }
class BST {
    Node root = null;
    Node create(Node root, int d) {
        if (root == null) return new Node(d);
        else if (d <= root.data) root.left = create(root.left, d);
        else root.right = create(root.right, d);
        return root;
    }
    public void topView(Node root) {
        TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> map = new TreeMap<>();
        Queue<Qnode> qd = new LinkedList<>();
        qd.offer(new Qnode(root, 0, 0));
        while (!qd.isEmpty()) {
            Qnode c = qd.poll();
            map.putIfAbsent(c.v, new TreeMap<>());
            map.get(c.v).putIfAbsent(c.l, new LinkedList<>());
            map.get(c.v).get(c.l).add(c.node.data);
            if (c.node.left != null) qd.offer(new Qnode(c.node.left, c.v - 1, c.l + 1));
            if (c.node.right != null) qd.offer(new Qnode(c.node.right, c.v + 1, c.l + 1));
        }
        for (TreeMap<Integer, LinkedList<Integer>> levels : map.values())
            System.out.print(levels.firstEntry().getValue().getFirst() + " ");
        System.out.println();
    }
    public void bottomView(Node root) {
        TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> map = new TreeMap<>();
        Queue<Qnode> qd = new LinkedList<>();
        qd.offer(new Qnode(root, 0, 0));
        while (!qd.isEmpty()) {
            Qnode c = qd.poll();
            map.putIfAbsent(c.v, new TreeMap<>());
            map.get(c.v).putIfAbsent(c.l, new LinkedList<>());
            map.get(c.v).get(c.l).add(c.node.data);
            if (c.node.left != null) qd.offer(new Qnode(c.node.left, c.v - 1, c.l + 1));
            if (c.node.right != null) qd.offer(new Qnode(c.node.right, c.v + 1, c.l + 1));
        }
        for (TreeMap<Integer, LinkedList<Integer>> levels : map.values())
            System.out.print(levels.lastEntry().getValue().getLast() + " ");
        System.out.println();
    }
}
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST t = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) t.root = t.create(t.root, sc.nextInt());
        t.topView(t.root);
        t.bottomView(t.root);
    }
}
```

## Shortcut Version

**Right view via BFS** (one node per level — the last in level-order):

```java
import java.util.*;
class N { int d; N l, r; N(int x){d=x;} }
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        N root = null;
        for (int i = 0; i < n; i++) root = ins(root, s.nextInt());
        Queue<N> q = new LinkedList<>(); q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size(); int last = 0;
            for (int i = 0; i < sz; i++) {
                N c = q.poll(); last = c.d;
                if (c.l != null) q.add(c.l);
                if (c.r != null) q.add(c.r);
            }
            System.out.print(last + " ");
        }
    }
    static N ins(N r, int d) {
        if (r == null) return new N(d);
        if (d <= r.d) r.l = ins(r.l, d); else r.r = ins(r.r, d);
        return r;
    }
}
```
