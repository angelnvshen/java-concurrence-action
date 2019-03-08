package own.stu.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import own.stu.algorithms_4th.graph.AbstractSearch;
import own.stu.algorithms_4th.graph.BreadthFirstSearch;
import own.stu.algorithms_4th.graph.ConnectedComponent;
import own.stu.algorithms_4th.graph.Cycle;
import own.stu.algorithms_4th.graph.DeepFirstPaths;
import own.stu.algorithms_4th.graph.DeepFirstSearch;
import own.stu.algorithms_4th.graph.Graph;
import own.stu.algorithms_4th.graph.SymbolGraph;
import own.stu.algorithms_4th.graph.TwoColor;

public class GraphTest {

  @Test
  public void graphToString() {
    for (int s = 0; s < graph.V(); s++) {
      System.out.print(s + " -> ");
      for (int w : graph.adj(s)) {
        System.out.print(w + " ");
      }
      System.out.println();
    }
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

  private Graph graph;

  @Before
  public void before() {

    String file = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-graph.txt";
    graph = new Graph(new In(file));
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
    String fileName = "";
    String delim = "";

    SymbolGraph symbolGraph = new SymbolGraph(fileName, delim);

    Graph g = symbolGraph.G();

    while (StdIn.hasNextLine()){
      String source = StdIn.readLine();
      for(int w : g.adj(symbolGraph.index(source))){
        System.out.println(" " + symbolGraph.name(w));
      }
    }
  }
}
