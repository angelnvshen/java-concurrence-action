package own.stu.algorithms_4th.graph.directed;

import edu.princeton.cs.algs4.Stack;

/**
 * Single-source directed paths: given a digraph and source s, is there a directed path from s to v? If so, find such a
 * path. DepthFirstDirectedPaths.java uses depth-first search to solve this problem.
 */
public class DepthFirstDirectedPaths {

  private boolean[] marked;   // marked[v] = true iff v is reachable from s
  private int[] edgeTo;       // edgeTo[v] = last edge on path from s to v
  private final int s;        // source vertex

  public DepthFirstDirectedPaths(DiGraph digraph, int s) {
    marked = new boolean[digraph.V()];
    edgeTo = new int[digraph.V()];
    this.s = s;
    dfs(digraph, s);
  }

  private void dfs(DiGraph digraph, int v) {
    if (marked[v]) {
      return;
    }

    marked[v] = true;
    for (int w : digraph.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(digraph, w);
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<Integer> path = new Stack<Integer>();
    for (int x = v; x != s; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(s);
    return path;
  }
}
