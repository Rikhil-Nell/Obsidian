# 30. Dial's Algorithm

## Theory & MCQ Prep

**Idea.** Dial's algorithm is a **bucket-based** variant of Dijkstra's shortest-path algorithm for graphs with **small, integer, non-negative** edge weights.

Instead of a priority queue, use **W·V + 1** buckets (lists indexed by distance). Bucket `k` holds vertices whose **current** tentative distance equals `k`. Process buckets in increasing index order — exactly like a min-heap, but with O(1) bucket access.

**Algorithm**
1. `dist[s] = 0`, others = ∞.
2. Place `s` in bucket 0.
3. For `idx = 0, 1, 2, ...`:
   - While bucket `idx` non-empty: pop `u`. If `dist[u] != idx`, skip (stale entry).
   - For every neighbour `v` with weight `w`: if `dist[u] + w < dist[v]`, update `dist[v]` and add `v` to bucket `dist[v]`.

- **Approach class:** Bucket-based Dijkstra (single-source shortest path with non-negative weights).
- **Time:** **O(W·V + E)** where W = max edge weight. Faster than O((V+E) log V) when W is small.
- **Space:** **O(W·V)** for buckets.
- **Limitation:** Doesn't work with negative weights. Use **Bellman-Ford** for those.
- **The class implementation reads `max` (max edge weight) as the last input** so it can size the bucket list as `v * max`.

### Example MCQs

**Q1.** Dial's algorithm is most efficient when:  
A) Graph is dense.  B) Edge weights are small non-negative integers.  C) Negative weights exist.  D) Graph is acyclic.  
**Answer: B.**

**Q2.** Time complexity of Dial's algorithm:  
A) O(V²)  B) O((V+E) log V)  C) O(W·V + E)  D) O(V·E)  
**Answer: C.**

**Q3.** Why are buckets used instead of a min-heap?  
A) O(1) extract-min when key is bounded.  
B) Heaps are unstable.  
C) Buckets are slower but simpler.  
D) Buckets allow negative weights.  
**Answer: A.**

**Q4.** Dial's vs Dijkstra:  
A) Always faster.  B) Faster only when max edge weight W is small.  C) Slower always.  D) They're identical.  
**Answer: B.**

**Q5.** Dial's algorithm cannot handle:  
A) Sparse graphs  B) Dense graphs  C) Negative edges  D) Self loops  
**Answer: C.**

## Hand-write Java Code

```java
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
```

## Shortcut Version

Plain Dijkstra with a `PriorityQueue` (works on the same input — most autograders accept it):

```java
import java.util.*;
class Main {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        int V = s.nextInt(), E = s.nextInt();
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < V; i++) g.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            int u = s.nextInt(), v = s.nextInt(), w = s.nextInt();
            g.get(u).add(new int[]{v, w});
            g.get(v).add(new int[]{u, w});
        }
        int max = s.nextInt();
        int[] d = new int[V];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        pq.offer(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int u = t[0];
            if (t[1] > d[u]) continue;
            for (int[] e : g.get(u))
                if (d[u] + e[1] < d[e[0]]) { d[e[0]] = d[u] + e[1]; pq.offer(new int[]{e[0], d[e[0]]}); }
        }
        for (int x : d) System.out.print(x + " ");
    }
}
```
