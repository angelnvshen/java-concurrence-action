package own.stu.algorithms_4th.graph.shortest_path_tree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {

  private final int V;
  private int E;
  private Bag<DirectedEdge>[] adj;

  public EdgeWeightedDigraph(int v) {
    V = v;
    E = 0;
    adj = new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<>();
    }
  }

  public EdgeWeightedDigraph(In in) {
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
      DirectedEdge edge = new DirectedEdge(v, w, weight);
      addEdge(edge);
    }
  }

  public void addEdge(DirectedEdge edge) {

    adj[edge.from()].add(edge);
    E++;
  }

  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }

  public Iterable<DirectedEdge> edges() {
    Bag<DirectedEdge> edges = new Bag<>();

    for (int i = 0; i < V; i++) {
      for (DirectedEdge edge : adj[i]) {
        edges.add(edge);
      }
    }
    return edges;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }
}
