package own.stu.algorithms_4th.graph.undirected;

/**
 * 是否是无环图
 */
public class Cycle {

  private boolean[] marked; // marked[v] = is there an s-v path?

  private boolean hasCycle;

  public Cycle(Graph graph) {
    marked = new boolean[graph.V()];

    for (int s = 0; s < graph.V(); s++) {
      if (!marked[s]) {
        dfs(graph, s);
      }
    }
  }

  private void dfs(Graph graph, int s) {
    marked[s] = true;

    for (int w : graph.adj(s)) {
      if (!marked[w]) {
        dfs(graph, w);
      } else if (w == s) {
        hasCycle = true;
      }
    }
  }

  public boolean isHasCycle() {
    return hasCycle;
  }
}
