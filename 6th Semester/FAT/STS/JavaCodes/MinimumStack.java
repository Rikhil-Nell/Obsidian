import java.util.*;
class Main {
    static Stack<Integer> st = new Stack<>();
    static Stack<Integer> mst = new Stack<>();
    static void push(int n) {
        if (st.isEmpty()) {
            st.push(n);
            mst.push(n);
        } else {
            st.push(n);
            if (n <= mst.peek()) mst.push(n);
        }
    }
    static void pop() {
        int ele = st.pop();
        if (ele == mst.peek()) mst.pop();
    }
    static void getmin() {
        if (mst.isEmpty()) System.out.print("Stack is Empty");
        else System.out.print(mst.peek());
    }
    public static void main(String ar[]) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        for (int i = 0; i < n; i++) push(sw.nextInt());
        getmin();
    }
}
