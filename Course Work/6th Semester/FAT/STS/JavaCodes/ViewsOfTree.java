import java.util.*;
class Node {
    int data;
    Node left, right;
    Node(int d) { data = d; left = right = null; }
}
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
    void leftView(Node root, ArrayList<Integer> al, int level) {
        if (root == null) return;
        if (al.size() == level) al.add(root.data);
        leftView(root.left, al, level + 1);
        leftView(root.right, al, level + 1);
    }
    void rightView(Node root, ArrayList<Integer> al, int level) {
        if (root == null) return;
        if (al.size() == level) al.add(root.data);
        rightView(root.right, al, level + 1);
        rightView(root.left, al, level + 1);
    }
    void topView(Node root) {
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
    void bottomView(Node root) {
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
        ArrayList<Integer> lv = new ArrayList<>();
        t.leftView(t.root, lv, 0);
        System.out.println("Left:  " + lv);
        ArrayList<Integer> rv = new ArrayList<>();
        t.rightView(t.root, rv, 0);
        System.out.println("Right: " + rv);
        System.out.print("Top:    ");
        t.topView(t.root);
        System.out.print("Bottom: ");
        t.bottomView(t.root);
    }
}
