import java.util.*;
class node {
    int data;
    node next;
    node(int d) { data = d; next = null; }
}
class LL {
    node head = null;
    void insert(int d) {
        node newn = new node(d);
        if (head == null) head = newn;
        else {
            node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newn;
        }
    }
    void createloop(int s) {
        node start = head;
        node end = head;
        while (end.next != null) end = end.next;
        while (start.data != s) {
            start = start.next;
            if (start.next == null && start.data != s) return;
        }
        end.next = start;
    }
    boolean loopdetect() {
        node slow = head;
        node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LL l = new LL();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) l.insert(sc.nextInt());
        int s = sc.nextInt();
        l.createloop(s);
        System.out.println((l.loopdetect()) ? "Yes" : "No");
    }
}
