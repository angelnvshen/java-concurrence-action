package own.leetcode.search.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class IsBipartite {

    public static void main(String[] args) {
        IsBipartite bipartite = new IsBipartite();
        System.out.println(bipartite.isBipartite(new int[][]{
                /*{1, 3},
                {0, 2},
                {1, 3},
                {0, 2},*/

                /*{1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2},*/

                {}, {3}, {}, {1}, {}
        }));
    }

    public boolean isBipartite(int[][] graph) {
        /*宽搜，确定一条边的两个顶点分别在两个不同的集合中*/
        if (graph == null || graph.length == 0) {
            return false;
        }

        int n = graph.length;
        this.setA = new boolean[n];
        this.setB = new boolean[n];

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (!bfs(graph, i, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean[] setA;
    private boolean[] setB;

    private boolean bfs(int[][] graph, int pIndex, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(pIndex);
        visited[pIndex] = true;
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            int inWitch = inWitch(setA, setB, idx);
            if (inWitch == BOTH) return false;
            if (inWitch == NOT_ANY) {
                setA[idx] = true;
                inWitch = IN_A;
            }
            int[] neighbers = graph[idx];
            for (int nei : neighbers) {
                if (visited[nei]) {
                    int neiInWitch = inWitch(setA, setB, nei);
                    if (neiInWitch == BOTH) return false;
                    if (neiInWitch == inWitch) return false;
                    continue;
                }
                queue.add(nei);
                visited[nei] = true;
                if (inWitch == IN_A) {
                    setB[nei] = true;
                } else {
                    setA[nei] = true;
                }
            }
        }
        return true;
    }

    private static int BOTH = 2;
    private static int IN_A = 1;
    private static int IN_B = 0;
    private static int NOT_ANY = -1;

    private int inWitch(boolean[] setA, boolean[] setB, int p) {
        boolean inA = setA[p];
        boolean inB = setB[p];
        if (inA && inB) {
            return BOTH;
        } else if (inA) {
            return IN_A;
        } else if (inB) {
            return IN_B;
        } else {
            return NOT_ANY;
        }
    }
}