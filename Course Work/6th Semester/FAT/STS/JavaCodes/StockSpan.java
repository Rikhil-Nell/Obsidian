import java.util.*;
public class Main {
    static void span(int p[], int n, int s[]) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        s[0] = 1;
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && p[st.peek()] <= p[i]) st.pop();
            s[i] = (st.isEmpty() ? (i + 1) : (i - st.peek()));
            st.push(i);
        }
    }
    public static void main(String[] args) {
        Scanner sw = new Scanner(System.in);
        int n = sw.nextInt();
        int p[] = new int[n];
        for (int i = 0; i < n; i++) p[i] = sw.nextInt();
        int s[] = new int[n];
        span(p, n, s);
        for (int i = 0; i < n; i++) System.out.print(s[i] + " ");
    }
}
