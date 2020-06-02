package own.leetcode.dp.coordinator;

public class MinPathSum {
    public static void main(String[] args) {
        MinPathSum sum = new MinPathSum();
        System.out.println(sum.minPathSum(new int[][]{{0}}));
    }

    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length; //row
        int n = grid[0].length; // col
        
        /*
        init:
        dp[1][1] = grid[1][1];
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - ]) + grid[i][j];
        */
        int old;
        int now = 0;
        int[][] dp = new int[2][n];
        for (int i = 0; i < m; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[now][j] = grid[i][j];
                    continue;
                }

                int t = Integer.MAX_VALUE;
                if (i >= 1) {
                    t = Math.min(t, dp[old][j]);
                }
                if (j >= 1) {
                    t = Math.min(t, dp[now][j - 1]);
                }
                dp[now][j] = t + grid[i][j];
            }
        }
        return dp[now][n - 1];
    }
}