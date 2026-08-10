import java.util.*;
class Main {
    static Queue<Integer> q = new LinkedList<>();
    static int Findmin(Queue<Integer> q, int maxs) {
        int mini = -1;
        int minval = Integer.MAX_VALUE;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int curr = q.poll();
            if (curr <= minval && i <= maxs) {
                mini = i;
                minval = curr;
            }
            q.add(curr);
        }
        return mini;
    }
    static void inserttorear(Queue<Integer> q, int min) {
        int minvalue = 0;
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int curr = q.poll();
            if (i == min) minvalue = curr;
            else q.add(curr);
        }
        q.add(minvalue);
    }
    static void sortQueue(Queue<Integer> q) {
        int size = q.size();
        for (int i = 1; i <= size; i++) {
            int mind = Findmin(q, size - i);
            inserttorear(q, mind);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) q.add(sc.nextInt());
        sortQueue(q);
        System.out.println(q);
    }
}
