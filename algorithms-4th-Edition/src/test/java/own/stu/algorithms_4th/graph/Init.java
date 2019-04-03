package own.stu.algorithms_4th.graph;

import edu.princeton.cs.algs4.In;
import org.junit.Before;
import own.stu.algorithms_4th.graph.directed.DiGraph;
import own.stu.algorithms_4th.graph.minimun_spanning_tree.EdgWeightedGraph;
import own.stu.algorithms_4th.graph.shortest_path_tree.EdgeWeightedDigraph;
import own.stu.algorithms_4th.graph.undirected.Graph;

public class Init {

  protected Graph graph;
  protected DiGraph diGraph;
  protected EdgWeightedGraph edgWeightedGraph;
  protected EdgeWeightedDigraph edgeWeightedDigraph;

  @Before
  public void before() {

    String file = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-graph-3.txt";
    graph = new Graph(new In(file));
    String file_digraph = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-tinyDAG-3.txt";
    diGraph = new DiGraph(new In(file_digraph));

    String file_weightGraph = "/Users/ScorpionKing/IdeaProject_cl/core/algorithms-4th-Edition/src/main/resources/tmp/data-tinyEWG.txt";
    String file_weightGraph_10000 = "/Users/ScorpionKing/Desktop/10000EWG.txt";
    String file_weightGraph_large = "/Users/ScorpionKing/Desktop/largeEWG.txt";
    edgWeightedGraph = new EdgWeightedGraph(new In(file_weightGraph));

    edgeWeightedDigraph = new EdgeWeightedDigraph(new In(file_weightGraph));
  }

}
