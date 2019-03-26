package own.stu.algorithms_4th.graph.minimun_spanning_tree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgWeightedGraph {

  private int V;            // 顶点数量
  private int E;            // 边数量
  private Bag<Edge>[] adj;  // 邻接表

  public EdgWeightedGraph(int v) {
    V = v;
    E = 0;

    adj = new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<>();
    }
  }

  public EdgWeightedGraph(In in) {
    this(in.readInt());
    if (V < 0) {
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    }
    int E = in.readInt();
    if (E < 0) {
      throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
    }

    for (int i = 0; i < E; i++) {
      // 添加一条边
      int v = in.readInt();
      int w = in.readInt();
      double weight = in.readDouble();
      Edge edge = new Edge(v, w, weight);
      add(edge);
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void add(Edge edge) {
    int v = edge.either();
    int w = edge.other(v);

    adj[v].add(edge);
    adj[w].add(edge);
    E++;
  }

  public Iterable<Edge> adj(int w) {
    return adj[w];
  }

  public Iterable<Edge> edges() {
    Bag<Edge> edges = new Bag<>();
    for (int v = 0; v < V; v++) {
      for (Edge edge : adj(v)) {
        if (edge.other(v) > v) {
          edges.add(edge);
        }
      }
    }
    return edges;
  }
}
