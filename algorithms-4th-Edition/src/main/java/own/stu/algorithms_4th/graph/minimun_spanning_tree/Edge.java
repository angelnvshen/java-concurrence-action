package own.stu.algorithms_4th.graph.minimun_spanning_tree;

public class Edge {

  private int v;
  private int w;
  private double weight;

  public Edge(int v, int w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public double weight() {
    return weight;
  }

  public int either() {
    return v;
  }

  public int other(int vertex) {
    if (vertex == w) {
      return v;
    }
    if (vertex == v) {
      return w;
    }

    throw new RuntimeException("Inconsistent edge");
  }

  public int compareTo(Edge that) {
    if (weight < that.weight()) {
      return -1;
    }

    if (weight == that.weight()) {
      return 0;
    }

    return 1;
  }

  @Override
  public String toString() {
    return String.format("%d-%d %.2f", v, w, weight);
  }
}
