# 26. Vertical Order Traversal

## Theory & MCQ Prep

**Problem.** Group every node by its **horizontal distance (HD)** from the root and print groups left-to-right. Within a group, order nodes by their level (top-to-bottom); ties broken by BFS order.

**Idea.**
1. BFS from root with each node carrying `(HD, level)`.
2. Maintain a `TreeMap<HD, TreeMap<level, LinkedList<value>>>`. (TreeMap keeps HDs and levels in sorted order automatically.)
3. After BFS, iterate the outer TreeMap: for each HD, iterate inner TreeMap and print all values.

- **Approach class:** **BFS with horizontal-distance tracking** + sorted map.
- **Time:** **O(n log n)** (TreeMap inserts).
- **Space:** **O(n)** for the queue and map.
- **DFS variant** also works but BFS gives the natural top-to-bottom ordering at each HD without extra sorting.
- **Difference from top/bottom view:** vertical order prints **all** nodes in each HD; views print only the first/last.

### Example MCQs

**Q1.** Time complexity of vertical order traversal using TreeMap is:  
A) O(n)  B) O(n log n)  C) O(n²)  D) O(log n)  
**Answer: B.**

**Q2.** Horizontal distance of root is taken as:  
A) 1  B) 0  C) Number of levels  D) −∞  
**Answer: B.**

**Q3.** Why is BFS preferred over DFS for this traversal?  
A) DFS is impossible.  B) BFS naturally orders nodes top-to-bottom within an HD.  C) DFS uses more memory.  D) BFS is always faster.  
**Answer: B.**

**Q4.** For the BT:
```
        1
       / \
      2   3
     / \   \
    4   5   6
```
Vertical order is:  
A) 4, 2, 1 5, 3, 6  B) 4 2 1 5 3 6  C) 1 2 3 4 5 6  D) 4 2 5 1 3 6  
**Answer: B** (groups: HD=−2:{4}, HD=−1:{2}, HD=0:{1,5}, HD=1:{3}, HD=2:{6}).

## Hand-write Java Code

```java
import java.util.*;
class Node { int data; Node left, right; Node(int d){data=d;} }
class Qnode {
    Node node;
    int v, l;
    Qnode(Node node, int v, int l) { this.node = node; this.v = v; this.l = l; }
}
class BST {
    Node root = null;
    Node create(Node root, int d) {
        if (root == null) return new Node(d);
        else if (d <= root.data) root.left = create(root.left, d);
        else root.right = create(root.right, d);
        return root;
    }
    public void verticalTraversal(Node root) {
        TreeMap<Integer, TreeMap<Integer, LinkedList<Integer>>> map = new TreeMap<>();
        Queue<Qnode> qdata = new LinkedList<>();
        qdata.offer(new Qnode(root, 0, 0));
        while (!qdata.isEmpty()) {
            Qnode curr = qdata.poll();
            int ver = curr.v;
            int level = curr.l;
            map.putIfAbsent(ver, new TreeMap<>());
            map.get(ver).putIfAbsent(level, new LinkedList<>());
            map.get(ver).get(level).add(curr.node.data);
            if (curr.node.left != null) qdata.offer(new Qnode(curr.node.left, ver - 1, level + 1));
            if (curr.node.right != null) qdata.offer(new Qnode(curr.node.right, ver + 1, level + 1));
        }
        for (TreeMap<Integer, LinkedList<Integer>> levels : map.values())
            for (LinkedList<Integer> nodes : levels.values())
                for (int num : nodes) System.out.print(num + " ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST tree = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) tree.root = tree.create(tree.root, sc.nextInt());
        tree.verticalTraversal(tree.root);
    }
}
```

## Shortcut Version

Use a `HashMap<HD, ArrayList<int[]>>` and sort by HD at the end — same idea, slightly less Java boilerplate:

```java
import java.util.*;
class N { int d; N l, r; N(int x){d=x;} }
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        N root = null;
        for (int i = 0; i < n; i++) root = ins(root, s.nextInt());
        Map<Integer, List<int[]>> map = new TreeMap<>();
        Queue<Object[]> q = new LinkedList<>();
        q.add(new Object[]{root, 0, 0});
        while (!q.isEmpty()) {
            Object[] x = q.poll();
            N c = (N) x[0]; int hd = (int) x[1], lv = (int) x[2];
            map.computeIfAbsent(hd, k -> new ArrayList<>()).add(new int[]{lv, c.d});
            if (c.l != null) q.add(new Object[]{c.l, hd - 1, lv + 1});
            if (c.r != null) q.add(new Object[]{c.r, hd + 1, lv + 1});
        }
        for (List<int[]> v : map.values()) {
            v.sort((x, y) -> x[0] - y[0]);
            for (int[] p : v) System.out.print(p[1] + " ");
        }
    }
    static N ins(N r, int d) {
        if (r == null) return new N(d);
        if (d <= r.d) r.l = ins(r.l, d); else r.r = ins(r.r, d);
        return r;
    }
}
```
