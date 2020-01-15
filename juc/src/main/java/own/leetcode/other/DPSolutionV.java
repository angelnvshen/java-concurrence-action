package own.leetcode.other;

public class DPSolutionV {

    // ================================ hard 题 动态规划 ========================

    /**
     * 89. K数之和
     * <p>
     * 给定 n 个不同的正整数，整数 k（k <= n）以及一个目标数字 target。
     * 在这 n 个数里面找出 k 个数，使得这 k 个数的和等于目标数字，求问有多少种方案？
     * <p>
     * 样例
     * <p>
     * 输入:
     * List = [1,2,3,4]
     * k = 2
     * target = 5
     * 输出: 2
     * 说明: 1 + 4 = 2 + 3 = 5
     * <p>
     * 前i个数中找出j个数使得，和为s
     * 对于最后一个数，如果选择之后，则 dp[i][j][s] = dp[i-1][j-1][s-A[i-1]]，表示再继续再前i-1个数中找个j-1个数使得和为s 减去最后一个数的结果。
     * 如果不选择，dp[i][j][s] = dp[i-1][j][s]，表示从i-1个数中找个j个数使得，和为s
     * dp[i][j][s] 表示方案数
     * <p>
     * init
     * dp[0][0][0] = 1
     * dp[0][j][s] = 0;
     *
     * @param A
     * @param k
     * @param target
     * @return
     */
    public static int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int dp[][][] = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int s = 0; s <= target; s++) {

                    if (i == 0 && j == 0 && s == 0) {
                        dp[i][j][s] = 1;
                        continue;
                    }

                    if (i == 0) {
                        dp[i][j][s] = 0;
                        continue;
                    }

                    // 不选择第i个元素
                    dp[i][j][s] = dp[i - 1][j][s];
                    // 选择第i个元
                    if (j > 0 && s >= A[i - 1]) {
                        dp[i][j][s] += dp[i - 1][j - 1][s - A[i - 1]];
                    }
                }
            }
        }

        return dp[n][k][target];
    }

    public static void main(String[] args) {
        // ========================================
//        int[] A = new int[]{1, 2, 3, 4};
//        System.out.println(kSum(A, 2, 5));

        int[] A = new int[]{1, 2, 3, 4, 5};
        System.out.println(kSum(A, 3, 6));
        // ========================================
        // ========================================
        // ========================================
        // ----------------------------------------
    }

}
