package own.stu.algorithms_4th.graph.shortest_path_tree;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class DijkstraSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private IndexMinPQ<Double> pq;

  public DijkstraSP(EdgeWeightedDigraph graph) {
    edgeTo = new DirectedEdge[graph.V()];
    distTo = new double[graph.V()];
    for (int i = 0; i < graph.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }
    distTo[0] = 0;

    pq = new IndexMinPQ<>(graph.V());
    pq.insert(0, distTo[0]);
    while (!pq.isEmpty()) {
      relax(graph, pq.delMin());
    }
  }

  public DijkstraSP(EdgeWeightedDigraph graph, int s) {
    edgeTo = new DirectedEdge[graph.V()];
    distTo = new double[graph.V()];
    for (int i = 0; i < graph.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }
    distTo[s] = 0;
    pq = new IndexMinPQ<>(graph.V());
    pq.insert(s, distTo[s]);

    while (!pq.isEmpty()) {
      int v = pq.delMin();
      for (DirectedEdge edge : graph.adj(v)) {
        relax(edge);
      }
    }
  }

  private void relax(EdgeWeightedDigraph graph, int v) {
    for (DirectedEdge edge : graph.adj(v)) {
      int w = edge.to();
      if (distTo[w] >= distTo[v] + edge.weight()) {
        distTo[w] = distTo[v] + edge.weight();
        edgeTo[w] = edge;
        if (pq.contains(w)) {
          pq.decreaseKey(w, distTo[w]);
        } else {
          pq.insert(w, distTo[w]);
        }
      }
    }
  }

  private void relax(DirectedEdge e) {
    int v = e.from(), w = e.to();
    if (distTo[w] > distTo[v] + e.weight()) {
      distTo[w] = distTo[v] + e.weight();
      edgeTo[w] = e;
      if (pq.contains(w)) {
        pq.decreaseKey(w, distTo[w]);
      } else {
        pq.insert(w, distTo[w]);
      }
    }
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<DirectedEdge> path = new Stack<>();
    for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
      path.push(edge);
    }

    return path;
  }
}