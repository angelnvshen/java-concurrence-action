package own.stu.algorithms_4th.graph.undirected;

import java.util.Stack;

public abstract class AbstractSearch {

  protected boolean[] marked;

  protected int[] edgeTo;

  protected int start;

  public Iterable<Integer> pathTo(int v) {

    if (!hasPathsTo(v)) {
      return null;
    }

    Stack<Integer> stack = new Stack<>();
    for (int x = v; x != start; x = edgeTo[x]) {
      stack.push(x);
    }
    stack.push(start);

    return stack;
  }

  public boolean hasPathsTo(int v) {
    return marked[v];
  }

}
