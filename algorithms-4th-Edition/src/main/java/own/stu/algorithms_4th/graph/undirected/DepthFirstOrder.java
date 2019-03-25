package own.stu.algorithms_4th.graph.undirected;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import own.stu.algorithms_4th.graph.directed.DiGraph;

/**
 * 有向图基于深度优先搜索的顶点排序
 */
public class DepthFirstOrder {

  private boolean[] marked;

  private Queue<Integer> pre;
  private Queue<Integer> post;
  private Stack<Integer> reversePost;


  public DepthFirstOrder(DiGraph graph) {
    pre = new Queue<>();
    post = new Queue<>();
    reversePost = new Stack<>();

    marked = new boolean[graph.V()];
    for (int v = 0; v < graph.V(); v++) {
      if (!marked[v]) {
        dfs(graph, v);
      }
    }
  }

  public void dfs(DiGraph graph, int v) {

    pre.enqueue(v);
    marked[v] = true;
    for (int w : graph.adj(v)) {
      if (!marked[w]) {
        dfs(graph, w);
      }
    }
    post.enqueue(v);
    reversePost.push(v);
  }

  public Iterable<Integer> pre() {
    return pre;
  }

  public Iterable<Integer> post() {
    return post;
  }

  public Iterable<Integer> reversePost() {
    return reversePost;
  }
}