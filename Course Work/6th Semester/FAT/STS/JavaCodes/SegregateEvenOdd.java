import java.util.*;
class node {
    int data;
    node next;
    node(int d) { data = d; next = null; }
}
class LL {
    node head = null;
    void append(int d) {
        node newn = new node(d);
        if (head == null) head = newn;
        else {
            node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newn;
        }
    }
    void segregate() {
        node es = null, ee = null, os = null, oe = null;
        node curr = head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                if (es == null) es = ee = curr;
                else { ee.next = curr; ee = curr; }
            } else {
                if (os == null) os = oe = curr;
                else { oe.next = curr; oe = curr; }
            }
            curr = curr.next;
        }
        if (es == null) head = os;
        else if (os == null) head = es;
        else {
            head = es;
            ee.next = os;
            oe.next = null;
        }
    }
    void display() {
        node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        LL l = new LL();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) l.append(sc.nextInt());
        l.segregate();
        l.display();
    }
}
