# 16. Merge Sort for Doubly Linked List

## Theory & MCQ Prep

**Problem.** Sort a doubly linked list in non-decreasing order using merge sort.

**Idea.** Classic merge sort:
1. **Split** the list into two halves using slow/fast pointers (the "tortoise and hare" trick).
2. Recursively `sort` each half.
3. **Merge** two sorted halves while fixing both `next` and `prev` pointers.

- **Approach class:** **Divide & Conquer**, **stable**.
- **Time:** **O(n log n)** — the standard merge-sort recurrence T(n) = 2T(n/2) + O(n).
- **Space:** **O(log n)** recursion stack — no array copy needed because lists merge in-place via pointer rewiring.
- **Why merge sort over quick sort for linked lists?** Linked lists don't support random access → quicksort's pivot selection becomes O(n). Merge sort's split + merge are natural for linked lists.
- **Watch the prev pointers** during merge: every time you wire `a.next = b`, you must also set `b.prev = a` (and clear leading `prev = null`).

### Example MCQs

**Q1.** Time complexity of merge sort on a linked list:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(log n)  
**Answer: B.**

**Q2.** Why is merge sort preferred over quick sort for linked lists?  
A) Better cache behaviour  B) No random access penalty  C) Less memory  D) Both B and C  
**Answer: D.**

**Q3.** Auxiliary space for linked-list merge sort (excluding the input nodes):  
A) O(n)  B) O(log n)  C) O(1)  D) O(n²)  
**Answer: B** (recursion stack).

**Q4.** The split function uses which pointer technique?  
A) Hashing  B) Two-pointer slow/fast  C) Recursion  D) Index counting  
**Answer: B.**

**Q5.** Merge sort is:  
A) Always stable  B) Unstable  C) Only stable on arrays  D) Only stable on doubly linked lists  
**Answer: A.**

## Hand-write Java Code

```java
import java.util.Scanner;
class Main {
    static node head = null;
    static class node {
        int data;
        node next;
        node prev;
        node(int n) { data = n; next = null; prev = null; }
    }
    static void insert(int n) {
        node newnode = new node(n);
        if (head == null) head = newnode;
        else {
            node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newnode;
            newnode.prev = cur;
        }
    }
    static node sort(node head) {
        if (head == null || head.next == null) return head;
        node head1 = split(head);
        node first = sort(head);
        node second = sort(head1);
        return merge(first, second);
    }
    static node split(node first) {
        node fast = first;
        node slow = first;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        node temp = slow.next;
        slow.next = null;
        return temp;
    }
    static node merge(node first, node second) {
        if (first == null) return second;
        if (second == null) return first;
        if (first.data <= second.data) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
    static void display() {
        node cur = head;
        while (cur != null) {
            System.out.print(cur.data + "-->");
            cur = cur.next;
        }
        System.out.print("null");
    }
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        for (int i = 0; i < n; i++) insert(sw.nextInt());
        head = sort(head);
        display();
    }
}
```

## Shortcut Version

Pull values into a list, sort with `Collections.sort`, rebuild — produces the same final order:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> v = new ArrayList<>();
        for (int i = 0; i < n; i++) v.add(s.nextInt());
        Collections.sort(v);
        for (int x : v) System.out.print(x + "-->");
        System.out.print("null");
    }
}
```
