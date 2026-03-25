import java.util.*;

public class SortBitonicDLL {
    static class Node {
        int value;
        Node prev;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node sortBitonic(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node current = head;
        while (current.next != null && current.value <= current.next.value) {
            current = current.next;
        }

        if (current.next == null) {
            return head;
        }

        Node second = current.next;
        current.next = null;
        second.prev = null;

        second = reverse(second);
        return mergeSorted(head, second);
    }

    static Node reverse(Node head) {
        Node current = head;
        Node newHead = null;

        while (current != null) {
            Node nextNode = current.next;
            current.next = newHead;
            if (newHead != null) {
                newHead.prev = current;
            }
            current.prev = null;
            newHead = current;
            current = nextNode;
        }

        return newHead;
    }

    static Node mergeSorted(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        Node dummy = new Node(-1);
        Node tail = dummy;

        while (first != null && second != null) {
            if (first.value <= second.value) {
                tail.next = first;
                first.prev = tail;
                first = first.next;
            } else {
                tail.next = second;
                second.prev = tail;
                second = second.next;
            }
            tail = tail.next;
        }

        if (first != null) {
            tail.next = first;
            first.prev = tail;
        } else if (second != null) {
            tail.next = second;
            second.prev = tail;
        }

        Node result = dummy.next;
        result.prev = null;
        return result;
    }

    static Node buildDLL(int[] values) {
        if (values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            Node node = new Node(values[i]);
            current.next = node;
            node.prev = current;
            current = node;
        }
        return head;
    }

    static void printList(Node head) {
        Node current = head;
        List<Integer> output = new ArrayList<>();
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

        Node head = buildDLL(values);
        Node sorted = sortBitonic(head);
        printList(sorted);

        scanner.close();
    }
}
