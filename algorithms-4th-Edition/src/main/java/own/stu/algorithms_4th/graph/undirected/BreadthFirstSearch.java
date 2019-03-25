package own.stu.algorithms_4th.graph.undirected;

import own.stu.algorithms_4th.fundamentals.balanceSearchTree.redBlackTree.Queue;

public class BreadthFirstSearch extends AbstractSearch {

  private static final int INFINITY = Integer.MAX_VALUE;

  protected int[] distTo;

  public BreadthFirstSearch(Graph graph, int s) {

    marked = new boolean[graph.V()];
    edgeTo = new int[graph.V()];
    distTo = new int[graph.V()];
    start = s;
    dfs(graph, s);
  }

  public int distTo(int v) {
    return distTo[v];
  }

  public void dfs(Graph graph, int s) {

    Queue<Integer> queue = new Queue();
    for (int v = 0; v < graph.V(); v++) {
      distTo[v] = INFINITY;
    }
    distTo[s] = 0;
    queue.enqueue(s);
    marked[s] = true;

    while (!queue.isEmpty()) {
      int v = queue.dequeue();

      for (int w : graph.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
          queue.enqueue(w);
        }
      }
    }
  }
}