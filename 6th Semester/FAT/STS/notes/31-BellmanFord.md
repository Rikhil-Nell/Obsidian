# 31. Bellman-Ford Algorithm

## Theory & MCQ Prep

**Idea.** Single-source shortest paths in a (possibly directed, possibly negative-weighted) graph. **Relax every edge `V − 1` times**; after that, all shortest paths are correct provided there's no negative cycle reachable from the source.

**Algorithm**
1. `dist[s] = 0`, others = ∞.
2. Repeat `V − 1` times: for every edge `(u, v, w)`, if `dist[u] + w < dist[v]`, set `dist[v] = dist[u] + w`.
3. **Negative-cycle detection (extra pass):** if any edge can still relax, a negative cycle is reachable.

- **Approach class:** Edge relaxation / **DP** over edges.
- **Time:** **O(V·E)**. **Space:** **O(V)**.
- **Handles negative weights** (Dijkstra and Dial's do not).
- **Detects negative cycles** (an extra Vth pass).
- **Why V−1 passes?** A simple shortest path uses at most `V − 1` edges; pass `k` finalises distances reachable via at most `k` edges.

### Example MCQs

**Q1.** Time complexity of Bellman-Ford:  
A) O(V + E)  B) O(V log V + E)  C) O(V · E)  D) O(V²·E)  
**Answer: C.**

**Q2.** Bellman-Ford is preferred over Dijkstra when:  
A) Graph has negative edge weights.  B) Graph is dense.  C) Graph is unweighted.  D) Graph is a DAG.  
**Answer: A.**

**Q3.** After how many passes can all simple shortest paths be guaranteed?  
A) V  B) V − 1  C) E  D) log V  
**Answer: B.**

**Q4.** Detecting a negative cycle requires:  
A) An (V−1)th pass that relaxes any edge.  
B) An extra Vth pass; if any edge still relaxes, a negative cycle exists.  
C) Sorting edges.  
D) Cannot be detected.  
**Answer: B.**

**Q5.** For `n = 5` vertices the inner edge-relaxation loop runs (in the main routine):  
A) 4 times  B) 5 times  C) 25 times  D) E times  
**Answer: A** (V − 1 = 4 outer iterations, each going through all E edges).

## Hand-write Java Code

```java
import java.util.*;
class Main {
    static class Edge {
        int src, dest, weight;
        Edge(int src, int dest, int weight) { this.src = src; this.dest = dest; this.weight = weight; }
    }
    public static void bellmanFord(int n, int m, List<Edge> edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }
        for (int i = 0; i < n; i++)
            System.out.print((dist[i] == Integer.MAX_VALUE ? "-1" : dist[i]) + " ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }
        bellmanFord(n, m, edges, 0);
    }
}
```

## Shortcut Version

Same algorithm, edges as int[3], cleaner control flow:

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), m = s.nextInt();
        int[][] e = new int[m][3];
        for (int i = 0; i < m; i++) { e[i][0] = s.nextInt(); e[i][1] = s.nextInt(); e[i][2] = s.nextInt(); }
        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        for (int i = 0; i < n - 1; i++)
            for (int[] x : e)
                if (d[x[0]] != Long.MAX_VALUE && d[x[0]] + x[2] < d[x[1]]) d[x[1]] = d[x[0]] + x[2];
        for (long v : d) System.out.print((v == Long.MAX_VALUE ? -1 : v) + " ");
    }
}
```
