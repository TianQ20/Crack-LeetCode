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

## Similar Questions

| Question | Tags |
| -------- | -------- |
| [1102. Path With Maximum Minimum Value](https://github.com/TianQ20/Crack-LeetCode/blob/main/Questions/Graph/1102.%20Path%20With%20Maximum%20Minimum%20Value.md) | BFS |
| [1631. Path With Minimum Effort](https://github.com/TianQ20/Crack-LeetCode/blob/main/Questions/Graph/1631.%20Path%20With%20Minimum%20Effort.md) | BFS |
