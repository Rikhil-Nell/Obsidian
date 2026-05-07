import java.util.*;
public class Main {
    public static void bfsTraversal(List<List<Integer>> graph, int sr, int V) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sr);
        visited[sr] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");
            for (int nv : graph.get(curr)) {
                if (!visited[nv]) {
                    queue.add(nv);
                    visited[nv] = true;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int sr = sc.nextInt();
        bfsTraversal(graph, sr, V);
    }
}
