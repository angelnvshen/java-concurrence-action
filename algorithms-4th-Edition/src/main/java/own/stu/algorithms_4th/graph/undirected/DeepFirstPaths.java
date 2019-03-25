package own.stu.algorithms_4th.graph.undirected;

public class DeepFirstPaths extends AbstractSearch {

  int count;

  public DeepFirstPaths(Graph graph, int s) {
    marked = new boolean[graph.V()];
    edgeTo = new int[graph.V()];
    this.start = s;
    dfs(graph, s);
  }

  public void dfs(Graph graph, int v) {
    marked[v] = true;
    count++;
    for (int w : graph.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(graph, w);
      }
    }
  }
}
