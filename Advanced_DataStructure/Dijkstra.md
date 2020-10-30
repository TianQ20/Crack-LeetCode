# Dijkstra Algorithm

if you are not familiar with Dijkstra Algorithm, please check this [helpful tutorail](https://www.youtube.com/watch?v=XB4MIexjvY0&ab_channel=AbdulBari)

Notes can be found [here](https://github.com/TianQ20/Crack-LeetCode/blob/main/docs/Dijkstra.pdf)

## Template

```java
credit to Algorithm 4th edition

Public class Dijkstra {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public Dijkstra(EdgeWeightedDiagraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin())
        }
    }

    private void relax(EdgeWeightedDiagraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
}
```

## Example

[1102. Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/)

```java
class Solution {
    public int maximumMinimumPath(int[][] A) {
        int n = A.length, m = A[0].length;
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        // getting the maximum min --> using max heap
        // ensure that we always pick up next node in queue with maximum smallest value
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        pq.add(new int[] {A[0][0], 0, 0});
        int max = A[0][0];
        A[0][0] = -1; // mark as visited
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            max = Math.min(max, top[0]);
            int i = top[1], j = top[2];
            if (i == n - 1 && j == m - 1) { // reach end
                break;
            }
            for (int d = 0; d < 4; d++) {
                int newi = i + dirs[d], newj = j + dirs[d + 1];
                if (newi >= 0 && newi < n && newj >= 0 && newj < m && A[newi][newj] >= 0) {
                    pq.add(new int[] {A[newi][newj], newi, newj});
                    A[newi][newj] = -1; // mark as visited
                }
            }
        }
        return max;
    }
}
```

credit to [leetcode post](https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/324923/Clear-Code-Dijkstra-Algorithm-(C%2B%2BJavaPythonGoPHP))

[1631. Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/)

```java
class Solution {
    public int minimumEffortPath(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] dist = new int[m][n];
        int[] dirs = new int[] {0, 1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[] {0, 0, 0}); // distance, row, col
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], r = top[1], c = top[2];
            if (r == m - 1 && c == n - 1) {
                return d;
            }
            for (int i = 0; i < 4; i++) {
                int newr = r + dirs[i], newc = c + dirs[i + 1];
                if (newr >= 0 && newr < m && newc >= 0 && newc < n) {
                    int newd = Math.max(d, Math.abs(A[newr][newc] - A[r][c]));
                    if (dist[newr][newc] > newd) {
                        dist[newr][newc] = newd;
                        pq.add(new int[] {dist[newr][newc], newr, newc});
                    }
                }
            }
        }
        return -1;
    }
}
```

credit to [leetcode post](https://leetcode.com/problems/path-with-minimum-effort/discuss/909017/JavaPython-Dijikstra-Clean-and-Concise-O(MNlogMN))
