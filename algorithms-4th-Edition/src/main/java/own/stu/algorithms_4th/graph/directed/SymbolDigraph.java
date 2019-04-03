package own.stu.algorithms_4th.graph.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * The {@code SymbolDigraph} class represents a digraph, where the vertex names are arbitrary strings. By providing
 * mappings between string vertex names and integers, it serves as a wrapper around the {@link DiGraph} data type, which
 * assumes the vertex names are integers between 0 and <em>V</em> - 1. It also supports initializing a symbol digraph
 * from a file.
 */
public class SymbolDigraph {

  private ST<String, Integer> st;  // string -> index
  private String[] keys;           // index  -> string
  private DiGraph graph;           // the underlying digraph

  public SymbolDigraph(String filename, String delimiter) {
    // First pass builds the index by reading strings to associate
    // distinct strings with an index
    In in = new In(filename);
    st = new ST<>();
    while (in.hasNextLine()) {
      for (String str : in.readLine().split(delimiter)) {
        if (!st.contains(str)) {
          st.put(str, st.size());
        }
      }
    }

    // inverted index to get string keys in an array
    keys = new String[st.size()];
    for (String str : st.keys()) {
      keys[st.get(str)] = str;
    }

    // second pass builds the digraph by connecting first vertex on each
    // line to all others
    in = new In(filename);
    graph = new DiGraph(st.size());
    while (in.hasNextLine()) {
      String[] a = in.readLine().split(delimiter);
      int v = st.get(a[0]);
      for (int w = 1; w < a.length; w++) {
        graph.addEdge(v, w);
      }
    }
  }

  public boolean contains(String s) {
    return st.contains(s);
  }

  public int indexOf(String s) {
    return st.get(s);
  }

  public DiGraph digraph() {
    return graph;
  }

  public String nameOf(int v) {
    return keys[v];
  }
}