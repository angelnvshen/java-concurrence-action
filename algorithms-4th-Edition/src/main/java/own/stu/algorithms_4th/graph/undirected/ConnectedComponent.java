package own.stu.algorithms_4th.graph.undirected;

public class ConnectedComponent implements CC {

  private boolean marked[];

  private int[] id;

  private int count;

  public ConnectedComponent(Graph graph) {
    marked = new boolean[graph.V()];

    id = new int[graph.V()];

    for (int s = 0; s < graph.V(); s++) {
      if (!marked[s]) {
        dfs(graph, s);
        count++;
      }
    }
  }

  private void dfs(Graph graph, int v) {
    marked[v] = true;
    id[v] = count;

    for (int w : graph.adj(v)) {
      if (!marked[w]) {
        dfs(graph, w);
      }
    }
  }

  @Override
  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }

  @Override
  public int count() {
    return count;
  }

  @Override
  public int id(int v) {
    return id[v];
  }
}
