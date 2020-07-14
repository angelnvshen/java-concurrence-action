package own.leetcode.sort;

import java.util.PriorityQueue;

public class KClosest {

    public static void main(String[] args) {
        KClosest c = new KClosest();
        /*System.out.println(c.kClosest(new int[][]{
                {1, 3},
                {-2, 2},
        }, 1));*/

        System.out.println(c.kClosest(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4},
        }, 2));
    }

    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 || K <= 0) {
            return points;
        }

        int n = points.length;
        int[][] distance = new int[n][2];
        for (int i = 0; i < n; i++) {
            distance[i][0] = points[i][0] * points[i][0]
                    + points[i][1] * points[i][1];
            distance[i][1] = i;
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> b[0] - a[0]); // 大堆
        for (int i = 0; i < n; i++) {
            heap.add(distance[i]);
            if (heap.size() > K) {
                heap.poll();
            }
        }
        int[][] ans = new int[heap.size()][2];
        int idx = 0;
        while (!heap.isEmpty()) {
            int[] d = heap.poll();
            ans[idx++] = points[d[1]];
        }
        return ans;
    }
}