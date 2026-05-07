import java.util.*;
class edge {
    int v;
    int w;
    edge(int v, int w) { this.v = v; this.w = w; }
}
class Main {
    static void dials(ArrayList<ArrayList<edge>> al, int v, int s, int max) {
        int dis[] = new int[v];
        for (int i = 0; i < v; i++) dis[i] = Integer.MAX_VALUE;
        dis[s] = 0;
        List<Queue<Integer>> l = new ArrayList<>();
        for (int i = 0; i <= v * max; i++) l.add(new LinkedList<>());
        l.get(0).add(s);
        int ind = 0;
        while (ind < l.size()) {
            while (!l.get(ind).isEmpty()) {
                int u = l.get(ind).poll();
                if (dis[u] < ind) continue;
                for (edge n : al.get(u)) {
                    int ver = n.v;
                    int w = n.w;
                    if (dis[u] + w < dis[ver]) {
                        dis[ver] = dis[u] + w;
                        l.get(dis[ver]).add(ver);
                    }
                }
            }
            ind++;
        }
        for (int i = 0; i < v; i++) System.out.print(dis[i] + " ");
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<ArrayList<edge>> al = new ArrayList<>();
        for (int i = 0; i < v; i++) al.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int ver = sc.nextInt();
            int w = sc.nextInt();
            al.get(u).add(new edge(ver, w));
            al.get(ver).add(new edge(u, w));
        }
        int max = sc.nextInt();
        dials(al, v, 0, max);
    }
}
