# 14. Sort the Bitonic DLL

## Theory & MCQ Prep

**Bitonic DLL.** A doubly linked list whose values **first strictly increase then strictly decrease** (or reach the maximum and then decrease). Examples: `2 → 5 → 7 → 9 → 8 → 4 → 1`.

**Idea.** Use **two pointers** — `first` at head, `last` at tail. Compare them; the smaller value goes to the back of the result list, advance that pointer inward. This merges the two monotone halves in **O(n)**.

- **Approach class:** Two-pointer in-place merge (think *merge step* of merge sort, but on a single bitonic list — no D&C needed).
- **Time:** **O(n)**. **Space:** **O(1)** (re-uses existing nodes; no new allocations).
- **Difference from "sort a DLL":** general DLL sort is O(n log n). The **bitonic** structure lets us do it in O(n).
- **Stop condition:** `while (first != last)`. After the loop, attach the remaining single node.

### Example MCQs

**Q1.** A bitonic sequence:  
A) Is monotonic.  B) Is increasing then decreasing (or vice versa).  C) Is random.  D) Has all equal elements.  
**Answer: B.**

**Q2.** Time complexity to sort a bitonic doubly linked list:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(log n)  
**Answer: C.**

**Q3.** Auxiliary space used by the in-place bitonic-DLL sort (besides input):  
A) O(n)  B) O(log n)  C) O(1)  D) O(n²)  
**Answer: C.**

**Q4.** Why does the two-pointer technique work on a bitonic DLL?  
A) Both halves are individually sorted; one ascending, one descending.  
B) The list is randomised.  
C) Hashing is used.  
D) Sorting is unnecessary.  
**Answer: A.**

## Hand-write Java Code

```java
import java.util.Scanner;
class node {
    int data;
    node next;
    node prev;
    node(int n) {
        data = n;
        next = null;
        prev = null;
    }
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
```

## Shortcut Version

If you only need the sorted **values** (not the actual reattached DLL pointers), pull into an array, sort, print:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) x[i] = s.nextInt();
        Arrays.sort(x);
        for (int v : x) System.out.print(v + "-->");
        System.out.print("null");
    }
}
```
