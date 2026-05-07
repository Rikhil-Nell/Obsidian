import java.util.*;
public class Main {
    public static void dfsTraversal(List<List<Integer>> graph, int V) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfsUtil(graph, i, visited);
        }
        System.out.println();
    }
    public static void dfsUtil(List<List<Integer>> graph, int current, boolean[] visited) {
        visited[current] = true;
        System.out.print(current + " ");
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) dfsUtil(graph, neighbor, visited);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < v; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfsTraversal(graph, v);
    }
}
