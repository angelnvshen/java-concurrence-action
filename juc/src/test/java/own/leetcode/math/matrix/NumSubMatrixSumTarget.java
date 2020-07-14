package own.leetcode.math.matrix;

import java.util.HashMap;
import java.util.Map;

public class NumSubMatrixSumTarget {

    public static void main(String[] args) {
        NumSubMatrixSumTarget target = new NumSubMatrixSumTarget();
        System.out.println(target.numSubMatrixSumTarget(new int[][]{
                {0, 1, 0}, {1, 1, 1}, {0, 1, 0}
        }, 0));
    }

    public int numSubMatrixSumTarget(int[][] matrix, int target) {
        /* 前缀和的应用*/
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int[][] pre = new int[n + 1][m + 1];
        // i == 0 || j == 0 => pre[i][j] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + matrix[i - 1][j - 1]
                        - pre[i - 1][j - 1]; // 减去[i-1][j-1]是因为计算了两边
            }
        }

        print(matrix);
        System.out.println();
        print(pre);

        int count = 0;

        for (int i = 0; i < n; i++) { // 上边界
            for (int j = i; j < n; j++) { // 下边界
                Map<Integer, Integer> countMap = new HashMap<>();
                countMap.put(0, 1);
                for (int k = 1; k <= m; k++) {
                    // 上下边界确定后的 子矩阵的前缀和
                    int diff = pre[j + 1][k] - pre[i][k];

                    if (countMap.get(diff - target) != null) {
                        count += countMap.get(diff - target);
                    }

                    countMap.put(diff, countMap.getOrDefault(diff, 0) + 1);
                    if (i == 0) {
                        System.out.println(countMap);
                    }
                }
            }
        }

        return count;
    }


    private void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}