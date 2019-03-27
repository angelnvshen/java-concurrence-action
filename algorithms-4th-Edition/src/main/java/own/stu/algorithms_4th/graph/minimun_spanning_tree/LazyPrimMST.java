package own.stu.algorithms_4th.graph.minimun_spanning_tree;

import edu.princeton.cs.algs4.MinPQ;
import own.stu.algorithms_4th.fundamentals.balanceSearchTree.redBlackTree.Queue;

public class LazyPrimMST {

  private double weight;       // total weight of MST
  private boolean[] marked; // 最小生成树的顶点
  private Queue<Edge> mst;  // 最小生成树的边
  private MinPQ<Edge> pq;   // 横切边（包括失效的边）

  public LazyPrimMST(EdgWeightedGraph graph) {

    marked = new boolean[graph.V()];
    mst = new Queue<>();
    pq = new MinPQ<>();

    visit(graph, 0); // 假设graph是连通的
    while (!pq.isEmpty()) {
      Edge edge = pq.delMin(); // 从pq中获取权重最小的边
      int v = edge.either();
      int w = edge.other(v);

      if (marked[v] && marked[w]) {
        continue;
      }
      mst.enqueue(edge);
      weight += edge.weight();

      if (!marked[v]) {
        visit(graph, v);
      }

      if (!marked[w]) {
        visit(graph, w);
      }
    }
  }

  private void visit(EdgWeightedGraph graph, int v) {
    // 标记顶点v,并将所有连接v和从未被标记的顶点的边加入到pq
    marked[v] = true;
    for (Edge edge : graph.adj(v)) {
      if (!marked[edge.other(v)]) {
        pq.insert(edge);
      }
    }
  }

  public Iterable<Edge> edges() {
    return mst;
  }

  public double weight() {
    return weight;
  }
}