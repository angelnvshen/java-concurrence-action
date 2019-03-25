package own.stu.algorithms_4th.graph.directed;

import own.stu.algorithms_4th.graph.undirected.Graph;

public class DirectedDFS {

  boolean[] marked;

  public DirectedDFS(Graph graph, int s) {
    marked = new boolean[graph.V()];
    dfs(graph, s);
  }


  public DirectedDFS(Graph graph, Iterable<Integer> sources) {
    marked = new boolean[graph.V()];
    for (int s : sources) {
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
      }
    }
  }

  public boolean marked(int w) {
    return marked[w];
  }
}
