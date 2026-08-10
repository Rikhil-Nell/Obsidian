import java.util.*;

public class PriorityQueueUsingDLL {
    static class Node {
        int value;
        int priority;
        Node prev;
        Node next;

        Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
    }

    static class PriorityQueueDLL {
        Node head;
        Node tail;

        void enqueue(int value, int priority) {
            Node node = new Node(value, priority);

            if (head == null) {
                head = node;
                tail = node;
                return;
            }

            if (priority < head.priority) {
                node.next = head;
                head.prev = node;
                head = node;
                return;
            }

            Node current = head;
            while (current.next != null && current.next.priority <= priority) {
                current = current.next;
            }

            node.next = current.next;
            node.prev = current;

            if (current.next != null) {
                current.next.prev = node;
            } else {
                tail = node;
            }

            current.next = node;
        }

        int dequeue() {
            if (head == null) {
                return -1;
            }
            int value = head.value;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            return value;
        }

        int peek() {
            if (head == null) {
                return -1;
            }
            return head.value;
        }

        void display() {
            if (head == null) {
                System.out.println("empty");
                return;
            }
            Node current = head;
            boolean first = true;
            while (current != null) {
                if (!first) {
                    System.out.print(" ");
                }
                System.out.print("(" + current.value + "," + current.priority + ")");
                first = false;
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueueDLL queue = new PriorityQueueDLL();

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            String command = scanner.next();

            if (command.equals("enqueue")) {
                int value = scanner.nextInt();
                int priority = scanner.nextInt();
                queue.enqueue(value, priority);
            } else if (command.equals("dequeue")) {
                System.out.println(queue.dequeue());
            } else if (command.equals("peek")) {
                System.out.println(queue.peek());
            } else if (command.equals("display")) {
                queue.display();
            }
        }

        scanner.close();
    }
}
