package own.stu.algorithms_4th.graph.directed;

import own.stu.algorithms_4th.graph.undirected.DepthFirstOrder;

public class Topological {

  private Iterable<Integer> order;

  public Topological(DiGraph graph) {
    DirectedCycle cycle = new DirectedCycle(graph);
    if (!cycle.hasCycle()) {

      DepthFirstOrder depthFirstOrder = new DepthFirstOrder(graph);
      order = depthFirstOrder.reversePost();
    }
  }

  public Iterable<Integer> order() {
    return order;
  }

  public boolean isDAG() {
    return order != null;
  }
}