package own.stu.algorithms_4th.graph.undirected;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Graph {

  private int V; //顶点数量
  private int E; //边的数量

  private Bag<Integer>[] adj; //邻接表

  public Graph(int v) {
    V = v;
    if (V < 0) {
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    }
    adj = new Bag[V]; //创建邻接表
    for (int i = 0; i < V; i++) { // 将所有链表初始化为空
      adj[i] = new Bag<>();
    }
  }

  public Graph(Graph graph) {
    this(graph.V());
    this.E = graph.E();
    for (int v = 0; v < graph.V(); v++) {
      // reverse so that adjacency list is in same order as original
      Stack<Integer> reverse = new Stack<>();
      for (int w : graph.adj(v)) {
        reverse.push(w);
      }
      for (int w : reverse) {
        adj[v].add(w);
      }
    }
  }

  public Graph(In in) {
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
      addEdge(v, w);
    }
  }

  public void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v);
    E++;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<Integer> adj(int v) {
    return adj[v];
  }

  public boolean hasEdge(int v, int w) {
    Bag<Integer> edges = adj[v];
    if (edges == null) {
      return false;
    }

    for (int edge : adj[v]) {
      if (edge == w) {
        return true;
      }
    }
    return false;
  }
}