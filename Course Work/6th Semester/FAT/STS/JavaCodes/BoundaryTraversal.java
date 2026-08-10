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
