package own.stu.algorithms_4th.graph.directed;

import own.stu.algorithms_4th.graph.undirected.DepthFirstOrder;

/**
 * The {@code Topological} class represents a data type for determining a topological order of a directed acyclic graph
 * (DAG). Recall, a digraph has a topological order if and only if it is a DAG.
 */
public class Topological {

  private Iterable<Integer> order;  // topological order

  private int[] rank;               // rank[v] = rank of vertex v in order

  public Topological(DiGraph graph) {
    DirectedCycle cycle = new DirectedCycle(graph);
    if (!cycle.hasCycle()) {

      DepthFirstOrder depthFirstOrder = new DepthFirstOrder(graph);
      order = depthFirstOrder.reversePost();
      rank = new int[graph.V()];
      int i = 0;
      for (int v : order) {
        rank[v] = i++;
      }
    }
  }

  public Iterable<Integer> order() {
    return order;
  }

  public boolean isDAG() {
    return order != null;
  }

  public int rank(int v) {
    if (hasOrder()) {
      return rank[v];
    } else {
      return -1;
    }
  }

  public boolean hasOrder() {
    return order != null;
  }

}