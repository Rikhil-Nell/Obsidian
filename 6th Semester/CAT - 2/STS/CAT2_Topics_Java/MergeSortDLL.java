import java.util.*;

public class MergeSortDLL {
    static class Node {
        int value;
        Node prev;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = split(head);
        Node left = mergeSort(head);
        Node right = mergeSort(middle);
        return merge(left, right);
    }

    static Node split(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;
        if (second != null) {
            second.prev = null;
        }
        return second;
    }

    static Node merge(Node first, Node second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        if (first.value <= second.value) {
            first.next = merge(first.next, second);
            if (first.next != null) {
                first.next.prev = first;
            }
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            if (second.next != null) {
                second.next.prev = second;
            }
            second.prev = null;
            return second;
        }
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
        Node sorted = mergeSort(head);
        printList(sorted);

        scanner.close();
    }
}
