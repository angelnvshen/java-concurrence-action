package own.leetcode.binaryTreeAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图 注意 图的lable都是不重复的。
 */
public class Graph {

    public static void dfs_print(GraphNode graphNode, int[] visit){

        visit[graphNode.label] = 1;
        System.out.print(graphNode.label + "-> ");

        for(int i=0;i<graphNode.neighbours.size();i++){
            if(visit[graphNode.neighbours.get(i).label] == 0){
                dfs_print(graphNode.neighbours.get(i), visit);
            }
        }
    }

    public static void bfs_print(GraphNode graphNode, int[] visit){
        Queue<GraphNode> queue = new LinkedList();
        queue.add(graphNode);
        visit[graphNode.label] = 1;
        while (!queue.isEmpty()){
            GraphNode node = queue.remove();
            System.out.print(node.label + "-> ");
            for(int i = 0;i<node.neighbours.size();i++){
                if(visit[node.neighbours.get(i).label] == 0){
                    queue.add(node.neighbours.get(i));
                    visit[node.neighbours.get(i).label] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphNode g0 = new GraphNode(0);
        GraphNode g1 = new GraphNode(1);
        GraphNode g2 = new GraphNode(2);
        GraphNode g3 = new GraphNode(3);
        GraphNode g4 = new GraphNode(4);

        g0.neighbours.add(g4);
        g0.neighbours.add(g2);
        g2.neighbours.add(g3);
        g3.neighbours.add(g4);
        g4.neighbours.add(g3);
        g1.neighbours.add(g2);

        List<GraphNode> graphNodes = new ArrayList<>();
        graphNodes.add(g0);
        graphNodes.add(g1);
        graphNodes.add(g2);
        graphNodes.add(g3);
        graphNodes.add(g4);

        int[] visit = new int[graphNodes.size()];
        for(int i = 0;i<graphNodes.size();i++){
            if(visit[graphNodes.get(i).label]==0){
//                dfs_print(graphNodes.get(i), visit);
                bfs_print(graphNodes.get(i),visit);
            }
        }
    }


    // 矩阵表示 多用于稠密图
    // 邻接表 表示（多应用与稀疏图，内存节省）
    static class GraphNode{
        int label;
        List<GraphNode> neighbours;

        public GraphNode(int label) {
            this.label = label;
            neighbours = new ArrayList<>();
        }
    }
}
