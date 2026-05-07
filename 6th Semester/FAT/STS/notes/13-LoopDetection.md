# 13. Loop Detection in a Linked List

## Theory & MCQ Prep

**Problem.** Detect whether a singly linked list contains a cycle.

**Floyd's Tortoise and Hare (cycle detection).** Use two pointers `slow` (1 step) and `fast` (2 steps). If they ever meet → cycle exists. If `fast` reaches `null` → no cycle.

- **Approach class:** Two-pointer / **Floyd's algorithm**.
- **Time:** **O(n)**. **Space:** **O(1)**.
- **Why it works:** if there's a cycle of length L, fast catches up to slow within L iterations after both enter the cycle.
- **Alternatives:** hash-set of visited nodes (O(n) time, O(n) space); Brent's algorithm (slightly fewer pointer chases).
- **Bonus formulas (after detection):**
  - Distance from head to cycle entry = distance from meeting-point to cycle entry (going forward) → reset `slow` to head, advance both at same speed; they meet at entry.
  - Cycle length = number of steps for `fast` to come back to meeting point keeping `slow` fixed.

### Example MCQs

**Q1.** Floyd's cycle detection algorithm runs in:  
A) O(n²) time, O(1) space  B) O(n) time, O(n) space  C) O(n) time, O(1) space  D) O(log n) time  
**Answer: C.**

**Q2.** Two pointers slow and fast move 1 and 2 steps. If they meet, it implies:  
A) The list ends.  B) The list has a cycle.  C) The list is sorted.  D) The list is doubly linked.  
**Answer: B.**

**Q3.** If the list has no cycle, what terminates Floyd's loop?  
A) `slow == fast`  B) `fast == null` or `fast.next == null`  C) Counter exceeds n  D) Exception thrown  
**Answer: B.**

**Q4.** Hash-set based cycle detection has:  
A) O(1) space  B) O(n) space  C) O(log n) space  D) O(n²) space  
**Answer: B.**

## Hand-write Java Code

```java
import java.util.*;
class node {
    int data;
    node next;
    node(int d) {
        data = d;
        next = null;
    }
}
class LL {
    node head = null;
    void insert(int d) {
        node newn = new node(d);
        if (head == null) head = newn;
        else {
            node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newn;
        }
    }
    void createloop(int s) {
        node start = head;
        node end = head;
        while (end.next != null) end = end.next;
        while (start.data != s) {
            start = start.next;
            if (start.next == null && start.data != s) return;
        }
        end.next = start;
    }
    boolean loopdetect() {
        node slow = head;
        node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LL l = new LL();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) l.insert(sc.nextInt());
        int s = sc.nextInt();
        l.createloop(s);
        System.out.println((l.loopdetect()) ? "Yes" : "No");
    }
}
```

## Shortcut Version

`HashSet` based — slightly more memory but a one-glance loop:

```java
import java.util.*;
class Node { int d; Node n; Node(int x){d=x;} }
public class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Node head = null, tail = null;
        for (int i = 0; i < n; i++) {
            Node x = new Node(s.nextInt());
            if (head == null) head = tail = x; else { tail.n = x; tail = x; }
        }
        int v = s.nextInt();
        Node t = head, target = null;
        while (t != null) { if (t.d == v) target = t; if (t.n == null) { t.n = target; break; } t = t.n; }
        Set<Node> seen = new HashSet<>();
        Node c = head;
        while (c != null) { if (!seen.add(c)) { System.out.println("Yes"); return; } c = c.n; }
        System.out.println("No");
    }
}
```
