package own.stu.algorithms_4th.graph.directed;

import own.stu.algorithms_4th.graph.undirected.DepthFirstOrder;

public class KosarajuSharirSCC {

  private boolean[] marked;   // 已访问过得顶点
  private int count;          // 强连通分量的个数
  private int[] id;           //强连通分量的标识符

  public KosarajuSharirSCC(DiGraph graph) {
    marked = new boolean[graph.V()];
    id = new int[graph.V()];

    DepthFirstOrder order = new DepthFirstOrder(graph.reverse());
    for (int s : order.reversePost()) {
      if (!marked[s]) {
        dfs(graph, s);
        count++;
      }
    }
  }

  private void dfs(DiGraph graph, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : graph.adj(v)) {
      if (!marked[w]) {
        dfs(graph, w);
      }
    }
  }

  public boolean stronglyConnected(int v, int w) {
    return id[v] == id[w];
  }

  public int count() {
    return count;
  }

  public int id(int v) {
    return id[v];
  }
}
