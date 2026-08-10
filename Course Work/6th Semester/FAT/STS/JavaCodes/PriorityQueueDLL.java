import java.util.*;
class node {
    int data;
    int pr;
    node next;
    node prev;
    node(int n, int pri) { data = n; pr = pri; next = null; prev = null; }
}
class Main {
    static node front = null;
    static node rear = null;
    static void insert(int n, int prio) {
        node newnode = new node(n, prio);
        if (front == null) {
            front = newnode;
            rear = newnode;
        } else if (prio < front.pr) {
            newnode.next = front;
            front.prev = newnode;
            front = newnode;
        } else {
            node temp = front;
            while (temp.next != null && temp.next.pr <= prio) temp = temp.next;
            if (temp.next == null) {
                temp.next = newnode;
                newnode.prev = temp;
                rear = newnode;
            } else {
                newnode.next = temp.next;
                newnode.prev = temp;
                temp.next.prev = newnode;
                temp.next = newnode;
            }
        }
    }
    static void display() {
        node cur = front;
        while (cur != null) {
            System.out.println(cur.data + " " + cur.pr);
            cur = cur.next;
        }
    }
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        for (int i = 0; i < n; i++) {
            int c = sw.nextInt();
            int d = sw.nextInt();
            insert(c, d);
        }
        display();
    }
}
