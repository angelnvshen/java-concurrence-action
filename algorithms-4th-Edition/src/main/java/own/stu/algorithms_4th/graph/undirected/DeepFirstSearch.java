package own.stu.algorithms_4th.graph.undirected;

public class DeepFirstSearch {

  boolean[] marked;

  int count; // number of vertices connected to s

  public DeepFirstSearch(Graph graph, int v) {
    marked = new boolean[graph.V()];
    dfs(graph, v);
  }

  public void dfs(Graph graph, int v) {
    marked[v] = true;
    count++;
    for (int w : graph.adj(v)) {
      if (!marked[w]) {
        dfs(graph, w);
      }
    }
  }

  public boolean marked(int w) {
    return marked[w];
  }

  public int count() {
    return count;
  }
}
