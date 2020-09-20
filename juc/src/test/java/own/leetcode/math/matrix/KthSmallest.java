package own.leetcode.math.matrix;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallest {

    public static void main(String[] args) {
        KthSmallest smallest = new KthSmallest();
        System.out.println(smallest.kthSmallest(new int[][]{
                {1, 1, 2},
                {1, 1, 3},
                {1, 1, 9},
        }, 4));
    }

    /**
     * @param matrix: a matrix of integers
     * @param k:      An integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        // write your code here
        /*
        堆
        最大堆：维护size = k,但是需要 n * m * logk时间复杂度
        最小堆：放入k个不重复的元素 
         */
        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] hash = new boolean[n][m];
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, new PairComparator());
        minHeap.add(new Pair(0, 0, matrix[0][0]));

        for (int i = 0; i < k - 1; i++) {
            Pair cur = minHeap.poll();
            for (int j = 0; j < 2; j++) {
                int next_x = cur.x + dx[j];
                int next_y = cur.y + dy[j];
                if (next_y < m && next_x < n && !hash[next_x][next_y]) {
//                if (next_y < m && next_x < n ){ // && !hash[next_x][next_y]) {
                    hash[next_x][next_y] = true;
                    Pair next_Pair = new Pair(next_x, next_y, matrix[next_x][next_y]);
                    minHeap.add(next_Pair);
                    System.out.println(next_x + ", " + next_y + " | " + minHeap.peek().val);
                }
            }
        }
        return minHeap.peek().val;
    }

    class Pair {
        public int x, y, val;

        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    class PairComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.val - b.val;
        }
    }
}