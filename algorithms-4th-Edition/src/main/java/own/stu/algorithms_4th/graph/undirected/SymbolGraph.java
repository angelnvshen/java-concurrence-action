package own.stu.algorithms_4th.graph.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图
 */
public class SymbolGraph {

  private ST<String, Integer> st; //符号表 > 索引

  private String[] keys;//索引 > 符号名

  private Graph G; //图

  public SymbolGraph(String fileName, String delim) {

    st = new ST();

    In in = new In(fileName);

    while (in.hasNextLine()){
      String[] a = in.readLine().split(delim);
      for(int i = 0;i<a.length;i++){ // 为每个不同的字符串关联一个索引
        if(!st.contains(a[i])){
          st.put(a[i], st.size());
        }
      }
    }

    keys = new String[st.size()]; // 用来获取顶点名的反向索引，是一个数组
    for(String name : st.keys()){
      keys[st.get(name)] = name;
    }

    G = new Graph(st.size());

    in = new In(fileName);

    while (in.hasNextLine()){
      String[] a = in.readLine().split(delim);
      int v = st.get(a[0]);
      for(int i = 1; i<a.length;i++){
        G.addEdge(v, st.get(a[i]));
      }
    }
  }

  public boolean contains(String key) {
    return st.contains(key);
  }

  public int index(String key) {
    return st.get(key);
  }

  public String name(int v) {
    return keys[v];
  }

  public Graph G() {
    return G;
  }
}
