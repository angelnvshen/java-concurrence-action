package own.leetcode.dp.backpack;

public class BackPack {
    public static void main(String[] args) {
        BackPack backPack = new BackPack();
        System.out.println(backPack.backPack(10, new int[]{3, 4, 8, 5}));
    }

    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // write your code here
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }

        int n = A.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        /*
        dp[i][j] 表示前i个物品是否能装满重量j的大小
        对于第i个物品
            如果不选择 dp[i][j] = dp[i - 1][j]
            如果选择 dp[i][j] = dp[i - 1][j - A[i - 1]]
        */
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - A[i - 1]];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        int ans = 0;
        for (int i = m; i >= 1; i--) {
            if (dp[n][i] == true) {
                return i;
            }
        }

        return ans;
    }
}