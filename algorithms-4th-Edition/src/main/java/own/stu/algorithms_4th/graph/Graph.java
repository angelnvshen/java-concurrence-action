package own.stu.algorithms_4th.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {

  private int V; //顶点数量
  private int E; //边的数量

  private Bag<Integer>[] adj; //邻接表

  public Graph(int v) {
    V = v;
    adj = new Bag[V]; //创建邻接表
    for (int i = 0; i < V; i++) { // 将所有链表初始化为空
      adj[i] = new Bag<>();
    }
  }

  public Graph(In in) {
    this(in.readInt());
    int E = in.readInt();
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
}