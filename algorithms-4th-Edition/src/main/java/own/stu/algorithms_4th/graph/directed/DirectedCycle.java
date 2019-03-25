package own.stu.algorithms_4th.graph.directed;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {

  private boolean[] marked;         // marked[v] = has vertex v been marked?

  private int[] edgeTo;             // edgeTo[v] = previous vertex on path to v

  private boolean[] onStack;        // onStack[v] = is vertex on the stack?

  private Stack<Integer> cycle;     // directed cycle (or null if no such cycle)

  public DirectedCycle(DiGraph graph) {
    marked = new boolean[graph.V()];
    edgeTo = new int[graph.V()];
    onStack = new boolean[graph.V()];

    for (int v = 0; v < graph.V(); v++) {
      if (!marked[v] && cycle == null) {
        dfs(graph, v);
      }
    }
  }

  private void dfs(DiGraph graph, int v) {

    onStack[v] = true;
    marked[v] = true;
    for (int w : graph.adj(v)) {
      if (hasCycle()) {
        return;
      }

      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(graph, w);
      } else if (onStack[w]) {
        cycle = new Stack<>();
        for (int x = v; x != w; x = edgeTo[x]) {
          cycle.push(x);
        }
        cycle.push(w);
        cycle.push(v);
        assert check();
      }
    }
    onStack[v] = false;
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<Integer> cycle() {
    return cycle;
  }

  private boolean check() {

    if (hasCycle()) {
      // verify cycle
      int first = -1, last = -1;
      for (int v : cycle()) {
        if (first == -1) {
          first = v;
        }
        last = v;
      }
      if (first != last) {
        System.err.printf("cycle begins with %d and ends with %d\n", first, last);
        return false;
      }
    }

    return true;
  }
}
