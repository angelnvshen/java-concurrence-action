package own.stu.algorithms_4th.graph;

import own.stu.algorithms_4th.fundamentals.balanceSearchTree.redBlackTree.Queue;

public class BreadthFirstSearch extends AbstractSearch{

  public BreadthFirstSearch(Graph graph, int s) {

    marked = new boolean[graph.V()];
    edgeTo = new int[graph.V()];
    start = s;
    dfs(graph, s);
  }

  public void dfs(Graph graph, int s) {

    Queue<Integer> queue = new Queue();
    queue.enqueue(s);
    marked[s] = true;

    while (!queue.isEmpty()) {
      int v = queue.dequeue();

      for (int w : graph.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          marked[w] = true;
          queue.enqueue(w);
        }
      }
    }
  }
}