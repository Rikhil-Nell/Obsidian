import java.util.Scanner;
class node {
    int data;
    node next;
    node prev;
    node(int n) { data = n; next = null; prev = null; }
}
class DLL {
    node head = null;
    void insert(int n) {
        node newnode = new node(n);
        if (head == null) head = newnode;
        else {
            node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newnode;
            newnode.prev = cur;
        }
    }
    void bit() {
        node first = head;
        node last = head;
        node resl = null;
        node reslend = null;
        while (last.next != null) last = last.next;
        while (first != last) {
            if (first.data <= last.data) {
                if (resl == null) {
                    resl = reslend = first;
                    first = first.next;
                } else {
                    node cur = first.next;
                    reslend.next = first;
                    first.prev = reslend;
                    cur.prev = null;
                    first = cur;
                    reslend = reslend.next;
                }
            } else {
                if (resl == null) {
                    resl = reslend = last;
                    last = last.prev;
                } else {
                    node cur = last.prev;
                    reslend.next = last;
                    last.prev = reslend;
                    cur.next = null;
                    last = cur;
                    reslend = reslend.next;
                }
            }
        }
        reslend.next = first;
        first.prev = reslend;
        head = resl;
    }
    void display() {
        node cur = head;
        while (cur != null) {
            System.out.print(cur.data + "-->");
            cur = cur.next;
        }
        System.out.print("null");
    }
}
class Main {
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        DLL dl = new DLL();
        int n = sw.nextInt();
        for (int i = 0; i < n; i++) dl.insert(sw.nextInt());
        dl.bit();
        dl.display();
    }
}
