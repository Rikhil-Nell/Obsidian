import java.util.*;

public class SegregateEvenOddLinkedList {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node segregate(Node head) {
        Node evenHead = null;
        Node evenTail = null;
        Node oddHead = null;
        Node oddTail = null;

        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = null;

            if (current.value % 2 == 0) {
                if (evenHead == null) {
                    evenHead = current;
                    evenTail = current;
                } else {
                    evenTail.next = current;
                    evenTail = current;
                }
            } else {
                if (oddHead == null) {
                    oddHead = current;
                    oddTail = current;
                } else {
                    oddTail.next = current;
                    oddTail = current;
                }
            }
            current = nextNode;
        }

        if (evenHead == null) {
            return oddHead;
        }
        evenTail.next = oddHead;
        return evenHead;
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

    static void printList(Node head) {
        List<Integer> output = new ArrayList<>();
        Node current = head;
        while (current != null) {
            output.add(current.value);
            current = current.next;
        }
        for (int i = 0; i < output.size(); i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(output.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        Node head = buildList(values);
        Node result = segregate(head);
        printList(result);

        scanner.close();
    }
}
