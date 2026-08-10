## 1. Loop Detection
```java
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList list = new LinkedList();
 
        for (int i = 0; i < n; i++)
            list.insertAtEnd(sc.nextInt());
 
        list.display();
       // list.createLoop(3);
        if (list.loopDetection())
            System.out.println("Loop detected");
        else
            System.out.println("No loop detected");
    }
 
    static class LinkedList 
    {
        Node head = null, tail = null;
 
        void insertAtEnd(int data) 
        {
            Node newNode = new Node(data);
            if (head == null) 
            {
                head = newNode;
                tail = newNode;
            } else 
            {
                tail.next = newNode;
                tail = newNode;
            }
        }
 
        void display() 
        {
            Node temp = head;
            while (temp != null) 
            {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
 
        boolean loopDetection() 
        {
            Node slow = head;
            Node fast = head;
 
            while (fast != null && fast.next != null) 
            {
                slow = slow.next;
                fast = fast.next.next;
 
                if (slow == fast)
                    return true;
            }
            return false;
        }
 
        void createLoop(int pos) 
        {
            if (pos == -1) return;
 
            Node temp = head;
            for (int i = 1; temp != null && i < pos; i++)
                temp = temp.next;
 
            tail.next = temp;
        }
    }
 
    static class Node 
    {
        int data;
        Node next = null;
        Node(int d) 
        { 
            data = d;
        }
    }
}
```

## 2. Sort Bitonic DLL merge
```java
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList list = new LinkedList();
        for(int i=0;i<n;i++)
           list.insertAtEnd(sc.nextInt());
        list.display();
        list.bitonicDll();
        list.display();
    }
 
    static class LinkedList 
    {
        Node head = null, tail = null;
        void bitonicDll()
        {
            Node bitoHead=null,bitoTail=null;
            while(head!=tail)
            {
                if(head.data<=tail.data)
                {
                    if(bitoHead==null)
                    {
                        bitoHead=bitoTail=head;
                        head=head.next;
                    }
                    else 
                    {
                        Node future=head.next;
                        bitoTail.next=head;
                        head.prev=bitoTail;
                        head.next=null;
                        bitoTail=bitoTail.next;
                        future.prev=null;
                        head=future;
                    }
                }
                else 
                {
                    if(bitoTail==null)
                    {
                        bitoHead=bitoTail=tail;
                        tail=tail.prev;
                    }
                    else 
                    {
                        Node future=tail.prev;
                        bitoTail.next=tail;
                        tail.prev=bitoTail;
                        tail.next=null;
                        bitoTail=bitoTail.next;
                        future.next=null;
                        tail=future;
                    }
                }
            }
            bitoTail.next=head;
            head.prev=bitoTail;
            head.next=null;
            bitoTail=bitoTail.next;
            head=bitoHead;
        }
        void insertAtEnd(int data) 
        {
            Node newNode = new Node(data);
            if (head == null) 
            {
                head = newNode;
                tail = newNode;
            } else 
            {
                tail.next = newNode;
                newNode.prev=tail;
                tail = newNode;
            }
        }
 
        void display() 
        {
            Node temp = head;
            while (temp != null) 
            {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
 
     
    
    }
 
    static class Node 
    {
        int data;
        Node next = null;
        Node prev=null;
        Node(int d) 
        { 
            data = d;
        }
    }
}
```

## 3. Segregate Even & odd Linkedlist
```java
import java.util.*;
 
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList list = new LinkedList();
 
        for (int i = 0; i < n; i++)
        {
            list.insertAtEnd(sc.nextInt());
        }
 
        list.display();
        list.segregate();
        list.display();
    }
 
    static class LinkedList
    {
        Node head = null, tail = null;
 
        void insertAtEnd(int data)
        {
            Node newNode = new Node(data);
 
            if (head == null)
            {
                head = newNode;
                tail = newNode;
            }
            else
            {
                tail.next = newNode;
                tail = newNode;
            }
        }
 
        void segregate()
        {
            Node oddHead = null, oddTail = null;
            Node evenHead = null, evenTail = null;
 
            Node temp = head;
 
            while (temp != null)
            {
                if (temp.data % 2 == 0)
                {
                    if (evenHead == null)
                    {
                        evenHead = evenTail = temp;
                    }
                    else
                    {
                        evenTail.next = temp;
                        evenTail = evenTail.next;
                    }
                }
                else
                {
                    if (oddHead == null)
                    {
                        oddHead = oddTail = temp;
                    }
                    else
                    {
                        oddTail.next = temp;
                        oddTail = oddTail.next;
                    }
                }
 
                temp = temp.next;
            }
 
            if (evenHead == null)
            {
                head = oddHead;
                tail = oddTail;
                oddTail.next = null;
                return;
            }
 
            if (oddHead == null)
            {
                head = evenHead;
                tail = evenTail;
                evenTail.next = null;
                return;
            }
 
            evenTail.next = oddHead;
            oddTail.next = null;
 
            head = evenHead;
            tail = oddTail;
        }
 
        void display()
        {
            Node temp = head;
 
            while (temp != null)
            {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
 
    static class Node
    {
        int data;
        Node next = null;
 
        Node(int d)
        {
            data = d;
        }
    }
}
```


## 4. Merge sort using DLL
```java
 
import java.util.*;
 
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList list = new LinkedList();
 
        for (int i = 0; i < n; i++)
        {
            list.insertAtEnd(sc.nextInt());
        }
 
        System.out.println("Original List:");
        list.display(list.head);
        System.out.println();
 
        list.head = list.mergeSort(list.head);
 
        System.out.println("Sorted List:");
        list.display(list.head);
    }
 
    static class LinkedList
    {
        Node head = null, tail = null;
 
        void insertAtEnd(int data)
        {
            Node newNode = new Node(data);
 
            if (head == null)
            {
                head = newNode;
                tail = newNode;
            }
            else
            {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
 
        Node mergeSort(Node h)
        {
            if (h == null || h.next == null)
            {
                return h;
            }
 
            Node middle = getMiddle(h);
            Node nextOfMiddle = middle.next;
 
            middle.next = null;
            if (nextOfMiddle != null)
            {
                nextOfMiddle.prev = null;
            }
 
            Node left = mergeSort(h);
            Node right = mergeSort(nextOfMiddle);
 
            return merge(left, right);
        }
 
        Node getMiddle(Node head)
        {
            if (head == null)
            {
                return head;
            }
 
            Node slow = head;
            Node fast = head;
 
            while (fast.next != null && fast.next.next != null)
            {
                slow = slow.next;
                fast = fast.next.next;
            }
 
            return slow;
        }
 
        Node merge(Node first, Node second)
        {
            if (first == null)
                return second;
            if (second == null)
                return first;
 
            Node head = null, tail = null;
 
            while (first != null && second != null)
            {
                Node temp;
 
                if (first.data <= second.data)
                {
                    temp = first;
                    first = first.next;
                }
                else
                {
                    temp = second;
                    second = second.next;
                }
 
                temp.prev = tail;
 
                if (head == null)
                {
                    head = temp;
                    tail = temp;
                }
                else
                {
                    tail.next = temp;
                    tail = temp;
                }
            }
 
            Node rest = (first != null) ? first : second;
 
            if (rest != null)
            {
                tail.next = rest;
                rest.prev = tail;
            }
 
            return head;
        }
 
        void display(Node head)
        {
            Node temp = head;
 
            while (temp != null)
            {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
    }
 
    static class Node
    {
        int data;
        Node next = null, prev = null;
 
        Node(int d)
        {
            data = d;
        }
    }
}
```


## 5. Minimum Stack
```java
import java.util.*;
import java.util.Scanner;
public class MinimumStack{
    static Stack<Integer> st=new Stack<>();
    static Stack<Integer> mst=new Stack<>();
    static void push(int n){
        if(st.isEmpty()){
            st.push(n);
            mst.push(n);
        }
        else{
            st.push(n);
            if(n<=mst.peek()) mst.push(n);
        }
    }
    static void pop(){
        int ele=st.pop();
        if(ele==mst.peek()) mst.pop();
    }
    static void getmin(){
        if(mst.isEmpty()){
            System.out.println("Stack is Empty");
        }
        else{
            System.out.println(mst.peek());
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            push(sc.nextInt());
            getmin();
        }
    }

}
```

## 6. Celebrity Problem
```java
import java.util.Scanner;
import java.util.Stack;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // System.out.println("Enter the number of people at the party:");
        int n = sc.nextInt(); // Number of people
        int matrix[][] = new int[n][n]; // Matrix representation of the party
        
    //    System.out.println("Enter the matrix (1 if A knows B, otherwise 0):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
 
        int res = findCelebrity(matrix, n);
        if (res == -1) {
            System.out.println("There is no celebrity in the party.");
        } else {
            System.out.println("Person " + res + " is the celebrity in the party.");
        }
 
        sc.close();
    }
 
    // Function to find the celebrity
    public static int findCelebrity(int[][] M, int N) {
        Stack<Integer> stack = new Stack<>();
 
        // Step 1: Push all people into the stack
        for (int i = 0; i < N; i++) {
            stack.push(i);
        }
 
        // Step 2: Eliminate non-celebrities
        while (stack.size() > 1) {
            int A = stack.pop();
            int B = stack.pop();
 
            if (M[A][B] == 1) {
                // A knows B, so A is not a celebrity
                stack.push(B);
            } else {
                // A does not know B, so B is not a celebrity
                stack.push(A);
            }
        }
 
        // Step 3: Verify the remaining candidate
        if (stack.isEmpty()) {
            return -1; // No celebrity
        }
 
        int candidate = stack.pop();
        for (int i = 0; i < N; i++) {
            if (i != candidate && (M[candidate][i] == 1 || M[i][candidate] == 0)) {
                return -1; // Not a celebrity
            }
        }
 
        return candidate;
    }
}
```

## 7. Tower of Hanoi
```java
import java.util.*;

public class Main {
    static Stack<Integer> sr = new Stack<>(), ax = new Stack<>(), ds = new Stack<>();

    static void change(Stack<Integer> s1, Stack<Integer> s2, char a, char b) {
        if (s1.isEmpty() && s2.isEmpty()) return;

        if (s1.isEmpty() || (!s2.isEmpty() && s2.peek() < s1.peek())) {
            int val = s2.pop();
            s1.push(val);
            System.out.println("Move " + val + " from " + b + " to " + a);
        } else {
            int val = s1.pop();
            s2.push(val);
            System.out.println("Move " + val + " from " + a + " to " + b);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = n; i > 0; i--) sr.push(i);

        char s = 'S', a = 'A', d = 'D';
        if (n % 2 == 0) { char tmp = a; a = d; d = tmp; }

        int moves = (int) Math.pow(2, n) - 1;
        for (int i = 1; i <= moves; i++) {
            if (i % 3 == 1)      change(sr, ds, s, d);
            else if (i % 3 == 2) change(sr, ax, s, a);
            else                change(ax, ds, a, d);
        }
    }
}
```

## 8. Stock Span
```java
import java.util.*;
public class Main
{
public static void main(String[] args) {
int n=8;
int[] price={100,80,60,70,60,75,85,120};
int[] output=stockSpan(price,n);
for(int i=0;i<n;i++)
  System.out.print(output[i]+" ");
}
static int[] stockSpan(int[] price,int n)
{
    int[] res=new int[n];
    Stack<Integer> st=new Stack<>();
    res[0]=1;
    st.push(0);
    for(int curr=1;curr<n;curr++)
    {
        while(!st.isEmpty() && price[st.peek()]<price[curr])
          st.pop();
        res[curr]=st.isEmpty() ? (curr+1):curr-st.peek();  
    }
    return res;
    
}
}
```

## 9. Priority Queue using DLL
```java
import java.util.*;
public class Main {
    
    public static void main(String[] args) {
        PriorityQueueDLL pq = new PriorityQueueDLL();
        pq.push(10, 3);
        pq.push(5, 1);
        pq.push(20, 2);
        pq.push(15, 4);
        pq.print();
        System.out.println(pq.pop());
        pq.print();
    }
    static class Node {
        int data, priority;
        Node prev, next;
        Node(int d, int p) {
            data = d;
            priority = p;
        }
    }
    static class PriorityQueueDLL {
        Node head;
        void push(int data, int priority) {
            Node newNode = new Node(data, priority);
            if (head == null) {
                head = newNode;
                return;
            }
            Node cur = head;
            while (cur != null && cur.priority <= priority) {
                cur = cur.next;
            }
            if (cur == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (cur == null) {
                Node tail = head;
                while (tail.next != null) tail = tail.next;
                tail.next = newNode;
                newNode.prev = tail;
            } else {
                Node p = cur.prev;
                p.next = newNode;
                newNode.prev = p;
                newNode.next = cur;
                cur.prev = newNode;
            }
        }
        int pop() {
            if (head == null) return -1;
            int d = head.data;
            head = head.next;
            if (head != null) head.prev = null;
            return d;
        }
        void print() {
            Node t = head;
            while (t != null) {
                System.out.print("(" + t.data + "," + t.priority + ") ");
                t = t.next;
            }
            System.out.println();
        }
    }
    
}
```

## 10. Sort without using Extra spaces
```java
import java.util.*;
public class Main
{
public static void main(String[] args) {
Queue<Integer> q=new LinkedList<>();
q.offer(4);
q.offer(3);
q.offer(1);
q.offer(2);
q.offer(5);
System.out.println("Before sort: "+q);
sortQueue(q);
System.out.println("After sort: "+q);
}
static void sortQueue(Queue<Integer> q)
{
    int n=q.size();
    for(int i=0;i<n;i++)
    {
        int minIndex=-1;
        int minVal=9999;
        // check min ele 
        for(int j=0;j<n;j++)
        {
            int curr=q.poll();
            if(curr<minVal && j<n-i)
            {
                minVal=curr;
                minIndex=j;
            }
            q.offer(curr);
        }
        for(int j=0;j<n;j++)
        {
            int curr=q.poll();
            if(j!=minIndex)
              q.offer(curr);
        }
        q.offer(minVal);
    }
}
}
```

## 11. Stack Permutations
```java
import java.util.*;

class Main {
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int i = 0; i < n; i++) q1.add(sw.nextInt());
        for (int i = 0; i < n; i++) q2.add(sw.nextInt());

        Stack<Integer> st = new Stack<>();

        while (!q1.isEmpty()) 
        {
            st.push( q1.poll());
                while (!st.isEmpty() && st.peek() == q2.peek()) 
                {
                    st.pop();
                    q2.poll();
                }
        
        }

        if(q1.isEmpty() && st.isEmpty()) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
}
```