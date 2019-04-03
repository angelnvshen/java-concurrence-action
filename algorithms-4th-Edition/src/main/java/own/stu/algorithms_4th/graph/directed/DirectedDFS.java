package own.stu.algorithms_4th.graph.directed;

import own.stu.algorithms_4th.graph.undirected.Graph;

/**
 * Single-source reachability and Multiple-source reachability:
 *  Given a digraph and source s, is there a directed path from s to v?
 *  If so, find such a path. DirectedDFS.java uses depth-first search to solve this problem
 */
public class DirectedDFS {

  boolean[] marked;   // marked[v] = true iff v is reachable from source(s)

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
