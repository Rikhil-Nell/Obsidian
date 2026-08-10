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
