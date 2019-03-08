package own.stu.algorithms_4th.graph;

/**
 * 连通分量
 */
public interface CC {

  boolean connected(int v, int w); // 顶点v ,w 是否连通

  int count(); // 连通分量个数

  int id(int v); //顶点v 所在的连通分量 （0 - count()-1 )
}
