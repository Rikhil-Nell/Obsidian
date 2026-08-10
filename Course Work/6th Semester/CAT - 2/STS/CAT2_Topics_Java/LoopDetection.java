import java.util.*;

public class LoopDetection {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static boolean hasLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static Node loopStart(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node pointer = head;
                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }
                return pointer;
            }
        }
        return null;
    }

    static Node buildList(int[] values) {
        if (values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    static void connectTailToPosition(Node head, int position) {
        if (head == null || position < 0) {
            return;
        }

        Node loopNode = null;
        Node tail = head;
        int index = 0;

        while (tail.next != null) {
            if (index == position) {
                loopNode = tail;
            }
            tail = tail.next;
            index++;
        }

        if (index == position) {
            loopNode = tail;
        }

        if (loopNode != null) {
            tail.next = loopNode;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        int position = scanner.nextInt();

        Node head = buildList(values);
        connectTailToPosition(head, position);

        boolean loopFound = hasLoop(head);
        System.out.println(loopFound);

        Node start = loopStart(head);
        if (start == null) {
            System.out.println(-1);
        } else {
            System.out.println(start.value);
        }

        scanner.close();
    }
}
