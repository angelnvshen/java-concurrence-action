package own.stu.algorithms_4th.graph.directed;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * The {@code BreadthDirectedFirstPaths} class represents a data type for finding shortest paths (number of edges) from
 * a source vertex <em>s</em> (or set of source vertices) to every other vertex in the digraph.
 */
public class BreadthFirstDirectedPaths {

  private static final int INFINITY = Integer.MAX_VALUE;

  private boolean[] marked;   // marked[v] = is there an s->v path?
  private int[] edgeTo;       // edgeTo[v] = last edge on shortest s->v path
  private int[] distTo;    // distTo[v] = length of shortest s->v path

  public BreadthFirstDirectedPaths(DiGraph G, int s) {
    marked = new boolean[G.V()];
    distTo = new int[G.V()];
    edgeTo = new int[G.V()];
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = INFINITY;
    }
    bfs(G, s);
  }

  public BreadthFirstDirectedPaths(DiGraph G, Iterable<Integer> sources) {
    marked = new boolean[G.V()];
    distTo = new int[G.V()];
    edgeTo = new int[G.V()];
    for (int v = 0; v < G.V(); v++)
      distTo[v] = INFINITY;
    bfs(G, sources);
  }

  private void bfs(DiGraph G, int s) {

    Queue<Integer> queue = new Queue<>();
    queue.enqueue(s);
    marked[s] = true;
    distTo[s] = 0;
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          distTo[w] = distTo[v] + 1;
          edgeTo[w] = v;
          marked[w] = true;
          queue.enqueue(w);
        }
      }
    }
  }

  private void bfs(DiGraph G, Iterable<Integer> sources) {
    Queue<Integer> queue = new Queue<>();
    for (int v : sources) {
      marked[v] = true;
      distTo[v] = 0;
      queue.enqueue(v);
    }

    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          distTo[w] = distTo[v] + 1;
          marked[w] = true;
          queue.enqueue(w);
        }
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public int distTo(int v) {
    return distTo[v];
  }

  public Iterable<Integer> pathTo(int v) {

    if (!hasPathTo(v)) return null;
    Stack<Integer> path = new Stack<Integer>();
    int x;
    for (x = v; distTo[x] != 0; x = edgeTo[x])
      path.push(x);
    path.push(x);
    return path;
  }
}
