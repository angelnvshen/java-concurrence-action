package own.stu.algorithms_4th.graph.shortest_path_tree;

public class AcyclicSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;

  public AcyclicSP(EdgeWeightedDigraph digraph) {
    edgeTo = new DirectedEdge[digraph.V()];
    distTo = new double[digraph.V()];
    for (int i = 0; i < digraph.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }

//    Topological topological = new Topological()
  }

  private void relax(DirectedEdge edge) {
    int v = edge.from();
    int w = edge.to();

    if (distTo[w] > distTo[v] + edge.weight()) {
      distTo[w] = distTo[v] + edge.weight();
      edgeTo[w] = edge;
    }
  }

}
