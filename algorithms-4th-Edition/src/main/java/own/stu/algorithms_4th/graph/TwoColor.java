package own.stu.algorithms_4th.graph;

/**
 * 是否是二分图
 */
public class TwoColor {

  private boolean[] marked;
  private boolean[] color;
  private boolean isTwoColorable = true;

  public TwoColor(Graph graph) {
    marked = new boolean[graph.V()];
    color = new boolean[graph.V()];
  }

  public void dfs(Graph graph, int s) {
    marked[s] = true;
    for (int w : graph.adj(s)) {
      if (!marked[w]) {
        color[w] = !color[s];
        dfs(graph, w);
      } else if (marked[w] == marked[s]) {
        isTwoColorable = false;
      }
    }
  }

  public boolean isBipartite() {
    return isTwoColorable;
  }
}
