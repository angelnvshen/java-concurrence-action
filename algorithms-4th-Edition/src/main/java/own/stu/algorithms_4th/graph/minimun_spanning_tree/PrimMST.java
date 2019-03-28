package own.stu.algorithms_4th.graph.minimun_spanning_tree;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {

  private boolean[] marked;       // 如果v在树中 则为true

  private IndexMinPQ<Double> pq;  // 有效的横切边

  private Edge[] edgeTo;          //距离树最近的边

  private double[] distTo;        //distTo[w] = edgeTo[w].weight()

  public PrimMST(EdgWeightedGraph graph) {
    marked = new boolean[graph.V()];
    edgeTo = new Edge[graph.V()];
    distTo = new double[graph.V()];
    for (int v = 0; v < graph.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    pq = new IndexMinPQ<>(graph.V());

    distTo[0] = 0.0;
    pq.insert(0, 0.0);
    while (!pq.isEmpty()) {
      visit(graph, pq.delMin());
    }
  }

  private void visit(EdgWeightedGraph graph, int v) {
    marked[v] = true;
    for (Edge edge : graph.adj(v)) {
      int w = edge.other(v);
      if (marked[w]) {
        continue;
      }

      if (edge.weight() < distTo[w]) {
        // 连接 v和树的最佳边Edge 变成 edge
        edgeTo[w] = edge;
        distTo[w] = edge.weight();
        if (pq.contains(w)) {
          pq.changeKey(w, edge.weight());
        } else {
          pq.insert(w, edge.weight());
        }
      }
    }
  }

  public Iterable<Edge> edges() {

    Queue<Edge> mst = new Queue<Edge>();
    for (Edge edge : edgeTo) {
      mst.enqueue(edge);
    }
    return mst;
  }

  public double weight() {
    double weight = 0;
    for (double w : distTo) {
      weight += w;
    }
    return weight;
  }
}
