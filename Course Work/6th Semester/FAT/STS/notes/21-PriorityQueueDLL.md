# 21. Priority Queue using Doubly Linked List

## Theory & MCQ Prep

**Problem.** Build a priority queue where each node stores `(data, priority)`. Lower priority number = higher priority (like a min-priority queue). Provide insert (sorted by priority) and front-deletion.

**Idea.** Maintain a DLL kept **sorted in non-decreasing priority** at all times. New element is *inserted at the correct position* (linear scan). Front of the DLL is always the highest priority — front deletion is O(1).

- **Approach class:** Sorted linked list / classic priority queue implementation.
- **Time:** insert **O(n)** (linear scan to find position); deleteFront / peek **O(1)**.
- **Space:** O(n).
- **Vs. binary heap:** heap has O(log n) insert/extract but no order traversal. DLL keeps the queue sortable and traversable in O(n), at the cost of O(n) inserts.
- **Cases to handle:** empty list, prio < front prio (insert at head), traverse until next prio > prio (insert before that), reaches tail (append at rear).

### Example MCQs

**Q1.** Insertion time complexity in a priority queue implemented as a sorted DLL:  
A) O(1)  B) O(log n)  C) O(n)  D) O(n²)  
**Answer: C.**

**Q2.** Time to extract the highest-priority element from this DLL-based PQ:  
A) O(1)  B) O(log n)  C) O(n)  D) O(n log n)  
**Answer: A.**

**Q3.** A binary heap-based priority queue has insertion complexity:  
A) O(1)  B) O(log n)  C) O(n)  D) O(n log n)  
**Answer: B.**

**Q4.** When inserting `(value, prio)` and the new prio is less than the front's prio, what happens?  
A) Insert at rear  B) Insert at head and shift the front pointer  C) Skip insertion  D) Throw exception  
**Answer: B.**

## Hand-write Java Code

```java
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
```

## Shortcut Version

Use Java's built-in `PriorityQueue` of int[2] {value, priority}:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        for (int i = 0; i < n; i++) pq.offer(new int[]{s.nextInt(), s.nextInt()});
        while (!pq.isEmpty()) { int[] t = pq.poll(); System.out.println(t[0] + " " + t[1]); }
    }
}
```
