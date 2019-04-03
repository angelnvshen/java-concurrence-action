package own.stu.algorithms_4th.graph;

import com.alibaba.fastjson.JSON;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import own.stu.algorithms_4th.graph.directed.BreadthFirstDirectedPaths;
import own.stu.algorithms_4th.graph.directed.DepthFirstDirectedPaths;
import own.stu.algorithms_4th.graph.directed.DiGraph;
import own.stu.algorithms_4th.graph.directed.SymbolDigraph;
import own.stu.algorithms_4th.graph.undirected.Graph;
import own.stu.algorithms_4th.graph.undirected.SymbolGraph;

public class DiGraphTest extends Init {

  @Test
  public void testBreadthFirstDirectedPaths_multi() {
    ArrayList<Integer> sources = Lists.newArrayList(3, 0);
    String source = JSON.toJSONString(sources);
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(diGraph, sources);

    for (int v = 0; v < diGraph.V(); v++) {
      if (bfs.hasPathTo(v)) {
        StdOut.printf("%s to %d (%d):  ", source, v, bfs.distTo(v));
        for (int x : bfs.pathTo(v)) {
          if (sources.contains(x)) {
            StdOut.print(x);
          } else {
            StdOut.print("->" + x);
          }
        }
        StdOut.println();
      } else {
        StdOut.printf("%s to %d (-):  not connected\n", source, v);
      }

    }
  }

  @Test
  public void testBreadthFirstDirectedPaths() {
    int s = 0;
    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(diGraph, s);

    for (int v = 0; v < diGraph.V(); v++) {
      if (bfs.hasPathTo(v)) {
        StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
        for (int x : bfs.pathTo(v)) {
          if (x == s) {
            StdOut.print(x);
          } else {
            StdOut.print("->" + x);
          }
        }
        StdOut.println();
      } else {
        StdOut.printf("%d to %d (-):  not connected\n", s, v);
      }

    }
  }

  @Test
  public void testDepthFirstDirectedPaths() {
    int s = 0;
    DepthFirstDirectedPaths depthFirstDirectedPaths = new DepthFirstDirectedPaths(diGraph, s);
    for (int v = 0; v < diGraph.V(); v++) {
      if (depthFirstDirectedPaths.hasPathTo(v)) {
        StdOut.printf("%d to %d:  ", s, v);
        for (int x : depthFirstDirectedPaths.pathTo(v)) {
          if (x == s) {
            StdOut.print(x);
          } else {
            StdOut.print("-" + x);
          }
        }
        StdOut.println();
      } else {
        StdOut.printf("%d to %d:  not connected\n", s, v);
      }
    }
  }

  @Test
  public void testSymbolGraph() {
    String fileName = "/Users/ScorpionKing/Desktop/routes.txt";
    SymbolGraph symbolGraph = new SymbolGraph(fileName, " ");
    Graph graph = symbolGraph.G();
    List<String> list = Lists.newArrayList("HOU", "MCO", "LAS", "PHX");

    list.stream().forEach(source -> {

      if (symbolGraph.contains(source)) {
        System.out.println(source + " : ");
        for (int w : graph.adj(symbolGraph.index(source))) {
          System.out.println("    " + symbolGraph.name(w));
        }
      } else {
        StdOut.println("input not contain '" + source + "'");
      }
    });
  }

  @Test
  public void testSymbolDiGraph() {
    String fileName = "/Users/ScorpionKing/Desktop/routes.txt";
    SymbolDigraph symbolGraph = new SymbolDigraph(fileName, " ");
    DiGraph graph = symbolGraph.digraph();
    List<String> list = Lists.newArrayList("HOU", "MCO", "LAS", "PHX");

    list.stream().forEach(source -> {

      if (symbolGraph.contains(source)) {
        System.out.println(source + " : ");
        for (int w : graph.adj(symbolGraph.indexOf(source))) {
          System.out.println("    " + symbolGraph.nameOf(w));
        }
      } else {
        StdOut.println("input not contain '" + source + "'");
      }
    });
  }
}
