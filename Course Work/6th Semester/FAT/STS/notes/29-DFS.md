# 29. Depth-First Search (DFS)

## Theory & MCQ Prep

**DFS.** Go as deep as possible from a vertex before backtracking. Naturally implemented with **recursion** (call stack) or an explicit stack.

**Algorithm (recursive)**
1. Mark current vertex visited; print/use it.
2. For each neighbour, if unvisited, recurse.

- **Approach class:** Graph traversal. **Time:** **O(V + E)**. **Space:** **O(V)** (recursion + visited).
- **Properties / uses:** topological sort (on DAGs), cycle detection, connected components, strongly connected components (Tarjan / Kosaraju), bridges & articulation points, path discovery.
- **Iterative DFS:** push source; while stack non-empty pop, mark visited, push unvisited neighbours.
- **Disconnected graph:** outer loop calls DFS for every unvisited vertex.

### Example MCQs

**Q1.** Auxiliary data structure used by iterative DFS:  
A) Queue  B) Stack  C) Priority Queue  D) Hash table  
**Answer: B.**

**Q2.** Time complexity of DFS on adjacency list:  
A) O(V + E)  B) O(V²)  C) O(E²)  D) O(V·E)  
**Answer: A.**

**Q3.** DFS in a directed graph can be used to:  
A) Detect cycles  B) Compute topological sort on DAG  C) Find SCCs  D) All of the above  
**Answer: D.**

**Q4.** For a graph with multiple connected components, the standard DFS driver is:  
A) Run DFS from vertex 0 only.  
B) Run DFS from each unvisited vertex.  
C) Run BFS first.  
D) Sort vertices.  
**Answer: B.**

**Q5.** DFS recursion stack space worst case:  
A) O(1)  B) O(log V)  C) O(V)  D) O(V²)  
**Answer: C** (a path graph forces the stack to depth V).

## Hand-write Java Code

```java
import java.util.*;
public class Solution {
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
```

## Shortcut Version

Iterative with an explicit stack:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt(), E = s.nextInt();
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < V; i++) g.add(new ArrayList<>());
        for (int i = 0; i < E; i++) { int u = s.nextInt(), v = s.nextInt(); g.get(u).add(v); g.get(v).add(u); }
        boolean[] vis = new boolean[V];
        Deque<Integer> st = new ArrayDeque<>();
        for (int src = 0; src < V; src++) {
            if (vis[src]) continue;
            st.push(src);
            while (!st.isEmpty()) {
                int u = st.pop();
                if (vis[u]) continue;
                vis[u] = true;
                System.out.print(u + " ");
                for (int v : g.get(u)) if (!vis[v]) st.push(v);
            }
        }
    }
}
```
