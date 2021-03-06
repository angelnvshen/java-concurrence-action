package own.stu.algorithms_4th.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import own.stu.algorithms_4th.graph.directed.DirectedCycle;
import own.stu.algorithms_4th.graph.directed.KosarajuSharirSCC;
import own.stu.algorithms_4th.graph.minimun_spanning_tree.Edge;
import own.stu.algorithms_4th.graph.minimun_spanning_tree.LazyPrimMST;
import own.stu.algorithms_4th.graph.minimun_spanning_tree.PrimMST;
import own.stu.algorithms_4th.graph.shortest_path_tree.DijkstraSP;
import own.stu.algorithms_4th.graph.shortest_path_tree.DirectedEdge;
import own.stu.algorithms_4th.graph.shortest_path_tree.EdgeWeightedDigraph;
import own.stu.algorithms_4th.graph.undirected.AbstractSearch;
import own.stu.algorithms_4th.graph.undirected.BreadthFirstSearch;
import own.stu.algorithms_4th.graph.undirected.ConnectedComponent;
import own.stu.algorithms_4th.graph.undirected.Cycle;
import own.stu.algorithms_4th.graph.undirected.DeepFirstPaths;
import own.stu.algorithms_4th.graph.undirected.DeepFirstSearch;
import own.stu.algorithms_4th.graph.undirected.DepthFirstOrder;
import own.stu.algorithms_4th.graph.undirected.Graph;
import own.stu.algorithms_4th.graph.undirected.SymbolGraph;
import own.stu.algorithms_4th.graph.undirected.TwoColor;

public class GraphTest extends Init{

  @Test
  public void graphToString() {
    for (int s = 0; s < graph.V(); s++) {
      System.out.print(s + " -> ");
      for (int w : graph.adj(s)) {
        System.out.print(w + " ");
      }
      System.out.println();
    }

    System.out.println(graph.hasEdge(0, 2));
    System.out.println(graph.hasEdge(0, 3));
  }

  @Test
  public void dfs() {
    String file = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-graph.txt";

    Graph graph = new Graph(new In(file));
    int s = 0;

    DeepFirstSearch deepFirstSearch = new DeepFirstSearch(graph, s);

    for (int v = 0; v < graph.V(); v++) {
      if (deepFirstSearch.marked(v)) {
        System.out.print(v + " ");
      }
    }
    System.out.println();
    if (deepFirstSearch.count() != graph.V()) {
      System.out.print("NOT ");
    }
    System.out.println("connected");
  }

  @Test
  public void dfs_path() {
    String file = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-graph.txt";

    Graph graph = new Graph(new In(file));
    int s = 0;

    DeepFirstPaths dfp = new DeepFirstPaths(graph, s);

    int v = 5;
    printPath(dfp, v);
  }

  @Test
  public void bfs() {
    String file = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-graph-2.txt";

    Graph graph = new Graph(new In(file));

    BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);

    List<Integer> vlist = Lists.newArrayList(0, 1, 2, 3, 4, 5);

    for (int w : vlist) {
      printPath(bfs, w);

      System.out.println(bfs.distTo(w));
    }
  }

  private void printPath(AbstractSearch abstractSearch, int w) {
    if (abstractSearch.hasPathsTo(w)) {
      System.out.print("hasPathsTo : " + w + ", the path is : ");
      for (int t : abstractSearch.pathTo(w)) {
        System.out.print(t + " ");
      }
    } else {
      System.out.println("hasNoPath");
    }
    System.out.println();
  }

  @Test
  public void connectedComponent() {
    ConnectedComponent cc = new ConnectedComponent(graph);

    int m = cc.count();
    System.out.println("count : " + m);

    Bag<Integer>[] components = new Bag[m];
    for (int i = 0; i < m; i++) {
      components[i] = new Bag<>();
    }

    for (int v = 0; v < graph.V(); v++) {
      components[cc.id(v)].add(v);
    }

    for (int i = 0; i < m; i++) {
      System.out.print("component[" + i + "] : ");
      for (int v : components[i]) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void testCycle() {

    Cycle cycle = new Cycle(graph);
    System.out.println(cycle.isHasCycle());
  }

  @Test
  public void testTwoColor() {

    TwoColor twoColor = new TwoColor(graph);
    System.out.println(twoColor.isBipartite());
  }

  @Test
  public void testSymbolGraph() {
    String fileName = "C:\\Users\\CHANEL\\Desktop\\movies.txt";
    String delim = "/";

    SymbolGraph symbolGraph = new SymbolGraph(fileName, delim);

    Graph g = symbolGraph.G();

    List<String> list = Lists.newArrayList("Mercurio, Gus", "Blue Chips (1994)");
    list.stream().forEach(source -> {

      System.out.println(source + " : ");
      for (int w : g.adj(symbolGraph.index(source))) {
        System.out.println("    " + symbolGraph.name(w));
      }
    });
  }

  @Test
  public void testStdIn() {
    while (StdIn.hasNextLine()) {
      System.out.println("start:");
      System.out.println(StdIn.readLine());
    }
    System.out.println("end");
  }

  @Test
  public void degreeOfSeparation() {

    String fileName = "/Users/ScorpionKing/Desktop/routes.txt";
    SymbolGraph symbolGraph = new SymbolGraph(fileName, " ");

    String start = "MCO";

    boolean startExisted = symbolGraph.contains(start);
    if (!startExisted) {
      System.out.println(String.format("start point[%s] not Exist In DB", start));
    }

    int s = symbolGraph.index(start);

    Graph g = symbolGraph.G();
    BreadthFirstSearch bfs = new BreadthFirstSearch(g, s);

    List<String> destination = Lists.newArrayList("LAX", "LAS", "DFW", "SH");
    destination.stream().forEach(str -> {
      boolean destExisted = symbolGraph.contains(str);
      if (!destExisted) {
        System.out.println(String.format("dest point[%s] not Exist In DB", str));
        return;
      }

      int w = symbolGraph.index(str);
      boolean hasPaths = bfs.hasPathsTo(w);
      if (!hasPaths) {
        System.out.println(String.format("start point [%s] has no path to dest point [%s]", start, str));
      }
      System.out.println(start);
      for (int v : bfs.pathTo(w)) {
        System.out.println("  " + symbolGraph.name(v));
      }
    });
  }

  @Test
  public void test() {

    List<Integer> list = Lists.newArrayList(90, 1, 2, 3, 4, 5, 10, 32);
    System.out.println(list);
    Stack<Integer> reverse = new Stack<>();
//    for(int i = )
//    reverse.push();
  }

  // ----------------------------------

  @Test
  public void testDirectGraphPrint() {

    for (int i = 0; i < diGraph.V(); i++) {
      System.out.print(i + " : ");
      for (int v : diGraph.adj(i)) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void testDirectGraphHasCycle() {
    DirectedCycle finder = new DirectedCycle(diGraph);
    if (finder.hasCycle()) {
      StdOut.print("Directed cycle: ");
      for (int v : finder.cycle()) {
        StdOut.print(v + " ");
      }
      StdOut.println();
    } else {
      StdOut.println("No directed cycle");
    }
    StdOut.println();
  }

  @Test
  public void testDepthFirstOrder() {
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(diGraph.reverse());
    System.out.print("pre: ");
    for (int v : depthFirstOrder.pre()) {
      System.out.print(v + " ");
    }
    System.out.println();

    System.out.print("post: ");
    for (int v : depthFirstOrder.post()) {
      System.out.print(v + " ");
    }
    System.out.println();

    System.out.print("reversePost: ");
    for (int v : depthFirstOrder.reversePost()) {
      System.out.print(v + " ");
    }
    System.out.println();
  }

  @Test
  public void testDirectConnectComponent() {

    KosarajuSharirSCC scc = new KosarajuSharirSCC(diGraph);
    int count = scc.count();
    System.out.println(count);

    Bag<Integer>[] components = new Bag[count];
    for (int i = 0; i < count; i++) {
      components[i] = new Bag<>();
    }

    for (int v = 0; v < diGraph.V(); v++) {
      components[scc.id(v)].add(v);
    }

    for (int i = 0; i < count; i++) {
      System.out.print("component[" + i + "] : ");
      for (int v : components[i]) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }

  @Test
  public void testLazyPrimMST() {

    LazyPrimMST mst = new LazyPrimMST(edgWeightedGraph);
    for (Edge edge : mst.edges()) {
      System.out.println(edge);
    }
    System.out.println(mst.weight());
  }

  @Test
  public void testPrimMST() {

    PrimMST mst = new PrimMST(edgWeightedGraph);
    for (Edge edge : mst.edges()) {
      System.out.println(edge);
    }
    System.out.println(mst.weight());
  }

  @Test
  public void testDijkstraSP() {

    DijkstraSP sp = new DijkstraSP(edgeWeightedDigraph);

    printDijkstraSP(sp, edgeWeightedDigraph);

    DijkstraSP sp2 = new DijkstraSP(edgeWeightedDigraph, 0);
    printDijkstraSP(sp2, edgeWeightedDigraph);
  }

  private void printDijkstraSP(DijkstraSP sp, EdgeWeightedDigraph edgeWeightedDigraph) {
    for (int t = 0; t < edgeWeightedDigraph.V(); t++) {
      if (sp.hasPathTo(t)) {
        StdOut.printf("%d to %d (%.2f)  ", 0, t, sp.distTo(t));
        for (DirectedEdge e : sp.pathTo(t)) {
          StdOut.print(e + "   ");
        }
        StdOut.println();
      } else {
        StdOut.printf("%d to %d         no path\n", 0, t);
      }
    }
  }

  // cyclic inheritance 循环继承
  /*public static class A extends B{

  }
  public static class B extends C{

  }
  public static class C extends A{

  }*/


}
