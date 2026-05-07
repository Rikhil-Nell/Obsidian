# 15. Segregate Even & Odd Nodes in a Linked List

## Theory & MCQ Prep

**Problem.** Rearrange a singly linked list so that all **even-valued** nodes appear before all **odd-valued** nodes, **preserving** the relative order within each group.

**Idea.** Single pass. Maintain four pointers: `evenStart, evenEnd, oddStart, oddEnd`. Append the current node to the corresponding sub-list. After the pass, link `evenEnd → oddStart` and set `oddEnd.next = null`.

- **Approach class:** Two-list partition / pointer manipulation. **No new nodes allocated.**
- **Time:** **O(n)**. **Space:** **O(1)** auxiliary.
- **Stable:** preserves original order within evens and within odds.
- **Edge cases:** all-evens or all-odds → return that single list (handle null start pointers carefully).

### Example MCQs

**Q1.** Time complexity of the optimal in-place segregate algorithm:  
A) O(n²)  B) O(n log n)  C) O(n)  D) O(1)  
**Answer: C.**

**Q2.** The technique is best described as:  
A) Quicksort partition  B) Two-list partition with stable order  C) Hashing  D) Recursion  
**Answer: B.**

**Q3.** What is `oddEnd.next` set to at the end?  
A) head  B) null  C) evenStart  D) Itself  
**Answer: B** (otherwise it would form a cycle through the old next pointer).

**Q4.** If the list contains only odd numbers, the head after segregation should point to:  
A) null  B) The first odd node  C) The last odd node  D) Same as before  
**Answer: B (and D is also true).**

## Hand-write Java Code

```java
import java.util.*;
class node {
    int data;
    node next;
    node(int d) { data = d; next = null; }
}
class LL {
    node head = null;
    void append(int d) {
        node newn = new node(d);
        if (head == null) head = newn;
        else {
            node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newn;
        }
    }
    void segregate() {
        node es = null, ee = null, os = null, oe = null;
        node curr = head;
        while (curr != null) {
            if (curr.data % 2 == 0) {
                if (es == null) es = ee = curr;
                else { ee.next = curr; ee = curr; }
            } else {
                if (os == null) os = oe = curr;
                else { oe.next = curr; oe = curr; }
            }
            curr = curr.next;
        }
        if (es == null) head = os;
        else if (os == null) head = es;
        else {
            head = es;
            ee.next = os;
            oe.next = null;
        }
    }
    void display() {
        node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}
class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        LL l = new LL();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) l.append(sc.nextInt());
        l.segregate();
        l.display();
    }
}
```

## Shortcut Version

If only the **printed values** matter, dump to two ArrayLists then concatenate:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Integer> ev = new ArrayList<>(), od = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int v = s.nextInt();
            (v % 2 == 0 ? ev : od).add(v);
        }
        ev.addAll(od);
        ev.forEach(v -> System.out.print(v + " "));
    }
}
```
