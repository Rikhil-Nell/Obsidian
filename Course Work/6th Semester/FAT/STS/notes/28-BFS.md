# 28. Breadth-First Search (BFS)

## Theory & MCQ Prep

**BFS.** Visit a graph **level by level** from a source. Use a queue and a `visited[]` array to avoid re-visits.

**Algorithm**
1. Mark source visited; enqueue it.
2. While queue not empty: dequeue `u`, print/use it, enqueue every unvisited neighbour and mark it visited.

- **Approach class:** Graph traversal. **Time:** **O(V + E)**. **Space:** **O(V)** (queue + visited).
- **Tree special case:** trees are graphs without cycles; BFS = level-order traversal.
- **Shortest paths:** BFS in an **unweighted** graph gives the shortest path (in number of edges) from source. Not for weighted graphs.
- **Implementation choices:** adjacency list (used here, optimal for sparse graphs), adjacency matrix (BFS becomes O(V²)).

### Example MCQs

**Q1.** Time complexity of BFS using adjacency list:  
A) O(V²)  B) O(V + E)  C) O(E log V)  D) O(V·E)  
**Answer: B.**

**Q2.** Auxiliary data structure used by BFS:  
A) Stack  B) Queue  C) Priority Queue  D) Heap  
**Answer: B.**

**Q3.** BFS on an **unweighted** graph from source `s` returns:  
A) Shortest paths from `s` to all reachable vertices.  
B) Minimum spanning tree.  
C) Strongly connected components.  
D) Topological order.  
**Answer: A.**

**Q4.** Space complexity of BFS:  
A) O(1)  B) O(V)  C) O(E)  D) O(V·E)  
**Answer: B.**

**Q5.** With adjacency matrix instead of list, BFS becomes:  
A) O(V + E)  B) O(V²)  C) O(E²)  D) O(log V)  
**Answer: B.**

## Hand-write Java Code

```java
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
```

## Shortcut Version

Same idea using `ArrayDeque` and an int[] visited:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt(), E = s.nextInt();
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < V; i++) g.add(new ArrayList<>());
        for (int i = 0; i < E; i++) { int u = s.nextInt(), v = s.nextInt(); g.get(u).add(v); g.get(v).add(u); }
        int src = s.nextInt();
        boolean[] vis = new boolean[V];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(src); vis[src] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : g.get(u)) if (!vis[v]) { vis[v] = true; q.offer(v); }
        }
    }
}
```
