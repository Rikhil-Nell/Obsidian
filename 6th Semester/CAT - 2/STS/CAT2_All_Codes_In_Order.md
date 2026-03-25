# CAT2 All Java Codes (In Order)

## LoopDetection

```java
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
```

## SortBitonicDLL

```java
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
```

## SegregateEvenOddLinkedList

```java
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
```

## MergeSortDLL

```java
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
```

## MinimumStack

```java
import java.util.*;

public class MinimumStack {
    static class MinStack {
        Stack<Integer> valueStack = new Stack<>();
        Stack<Integer> minimumStack = new Stack<>();

        void push(int value) {
            valueStack.push(value);
            if (minimumStack.isEmpty() || value <= minimumStack.peek()) {
                minimumStack.push(value);
            }
        }

        int pop() {
            if (valueStack.isEmpty()) {
                return -1;
            }
            int removed = valueStack.pop();
            if (removed == minimumStack.peek()) {
                minimumStack.pop();
            }
            return removed;
        }

        int top() {
            if (valueStack.isEmpty()) {
                return -1;
            }
            return valueStack.peek();
        }

        int getMin() {
            if (minimumStack.isEmpty()) {
                return -1;
            }
            return minimumStack.peek();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinStack minStack = new MinStack();

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            String command = scanner.next();

            if (command.equals("push")) {
                int value = scanner.nextInt();
                minStack.push(value);
            } else if (command.equals("pop")) {
                System.out.println(minStack.pop());
            } else if (command.equals("top")) {
                System.out.println(minStack.top());
            } else if (command.equals("getMin")) {
                System.out.println(minStack.getMin());
            }
        }

        scanner.close();
    }
}
```

## CelebrityProblem

```java
import java.util.*;

public class CelebrityProblem {
    static int findCelebrity(int[][] m) {
        int n = m.length;
        int candidate = 0;

        // Step 1: Find candidate
        for (int i = 1; i < n; i++) {
            if (m[candidate][i] == 1) {
                candidate = i;
            }
        }

        // Step 2: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                if (m[candidate][i] == 1 || m[i][candidate] == 0) {
                    return -1;
                }
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(findCelebrity(matrix));
        scanner.close();
    }
}
```

## IterativeTowerOfHanoi

```java
import java.util.*;

public class IterativeTowerOfHanoi {
    static void moveDisk(Stack<Integer> from, Stack<Integer> to, char fromRod, char toRod) {
        int fromTop = from.isEmpty() ? Integer.MIN_VALUE : from.pop();
        int toTop = to.isEmpty() ? Integer.MIN_VALUE : to.pop();

        if (fromTop == Integer.MIN_VALUE) {
            from.push(toTop);
            System.out.println(toRod + " " + fromRod);
        } else if (toTop == Integer.MIN_VALUE) {
            to.push(fromTop);
            System.out.println(fromRod + " " + toRod);
        } else if (fromTop > toTop) {
            from.push(fromTop);
            from.push(toTop);
            System.out.println(toRod + " " + fromRod);
        } else {
            to.push(toTop);
            to.push(fromTop);
            System.out.println(fromRod + " " + toRod);
        }
    }

    static void solve(int n, char source, char auxiliary, char destination) {
        Stack<Integer> sourceRod = new Stack<>();
        Stack<Integer> auxiliaryRod = new Stack<>();
        Stack<Integer> destinationRod = new Stack<>();

        for (int i = n; i >= 1; i--) {
            sourceRod.push(i);
        }

        if (n % 2 == 0) {
            char temp = destination;
            destination = auxiliary;
            auxiliary = temp;
        }

        int totalMoves = (1 << n) - 1;
        System.out.println(totalMoves);

        for (int move = 1; move <= totalMoves; move++) {
            if (move % 3 == 1) {
                moveDisk(sourceRod, destinationRod, source, destination);
            } else if (move % 3 == 2) {
                moveDisk(sourceRod, auxiliaryRod, source, auxiliary);
            } else {
                moveDisk(auxiliaryRod, destinationRod, auxiliary, destination);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        solve(n, 'A', 'B', 'C');

        scanner.close();
    }
}
```

## StockSpanProblem

```java
import java.util.ArrayList;
import java.util.Scanner;
class StockSpan {
    static ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> span = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            span.add(1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[i] >= arr[j]; j--) {
                span.set(i, span.get(i) + 1);
            }
        }
        return span;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt(); 
        }
        ArrayList<Integer> span = calculateSpan(arr);
        for (int x : span) {
            System.out.print(x + " ");
        }
        sc.close();
    }
}
```

## PriorityQueueUsingDLL

```java
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
```

## SortWithoutExtraSpace

```java
import java.util.*;

public class SortWithoutExtraSpace {
    static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int top = stack.pop();
        sortStack(stack);
        insertInSortedOrder(stack, top);
    }

    static void insertInSortedOrder(Stack<Integer> stack, int value) {
        if (stack.isEmpty() || stack.peek() <= value) {
            stack.push(value);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, value);
        stack.push(top);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            stack.push(scanner.nextInt());
        }

        sortStack(stack);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) {
                System.out.print(" ");
            }
        }
        System.out.println();

        scanner.close();
    }
}
```

## StackPermutations

```java
import java.util.*;

public class StackPermutations {
    static boolean isStackPermutation(int[] input, int[] output) {
        Stack<Integer> stack = new Stack<>();
        int outputIndex = 0;

        for (int value : input) {
            stack.push(value);

            while (!stack.isEmpty() && outputIndex < output.length && stack.peek() == output[outputIndex]) {
                stack.pop();
                outputIndex++;
            }
        }

        return outputIndex == output.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] input = new int[n];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            output[i] = scanner.nextInt();
        }

        System.out.println(isStackPermutation(input, output));
        scanner.close();
    }
}
```
