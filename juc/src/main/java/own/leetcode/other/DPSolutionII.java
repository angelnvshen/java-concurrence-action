package own.leetcode.other;

public class DPSolutionII {

    /**
     * 打劫房屋
     * 中文English
     * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
     * <p>
     * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: [3, 8, 4]
     * 输出: 8
     * 解释: 仅仅打劫第二个房子.
     * <p>
     * dp[i] 表示盗取第i栋房子的收益，如果选择盗取i dp[i] = dp[i-2] + nums[i] ,如果选择不盗取i,则 dp[i] = dp[i-1];
     * 固有：dp[i] = max(dp[i-2] + nums[i] , dp[i-1]);
     *
     * @param A
     * @return
     */
    public static long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        if (n == 1) {
            return A[0];
        }

        /*long[] dp = new long[n];
        dp[0] = A[0];
        dp[1] = A[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + A[i], dp[i - 1]);
        }

        return dp[n - 1];*/
        long old = A[0];
        long now = A[1];

        for (int i = 2; i < n; i++) {
            long t = Math.max(old + A[i], now); // 参考 dp[i] = Math.max(dp[i - 2] + A[i], dp[i - 1]);
            old = now;
            now = t;
        }
        return now;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意你不能在买入股票前卖出股票。
     * <p>
     * 示例:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * <p>
     * <p>
     * 记录历史最低值，如果当天的价格大于前一天价格，可以卖出，求出最高利润
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int result = 0;
        int minBuy = prices[0];

        for (int i = 1; i < prices.length; i++) {
            minBuy = Math.min(prices[i], minBuy);
            if (prices[i] > prices[i - 1]) {
                result = Math.max(result, prices[i] - minBuy);
            }
        }

        return result;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * <p>
     * 利用贪心思想，只要当天价格高于前一天价格，就可以卖出
     *
     * @param prices
     * @return
     */
    public static int maxProfitII(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * <p>
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,3,5,0,0,3,1,4]
     * 输出: 6
     * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * <p>
     * 最多可以完成两次交易
     * 1        |      2  |            3                |    4    |    5
     * 第一次买入之前 | 持有股票 | 第一次卖出之后，第二次买入之前 | 持有股票 | 第二次卖出之后
     * dp[i] 表示 前N天结束后，的最大获利 max(dp[i][j] { j in (1, 3, 5 ... })
     * <p>
     * dp[i][5] = max(dp[i-1][5], dp[i-1][4] + prices[i] - prices[i-1]),
     * 前n天的在阶段5最大获利：前n-1天也处于阶段5，或者前n-1天处于阶段4，第n天买出股票价格 - 第n-1天股票价格 + 前 n-1天最大获利
     * <p>
     * dp[i][4] = max(dp[i-1][3], dp[i-1][4] + prices[i] - prices[i-1]),
     * 前n天的在阶段4最大获利：前n-1天也处于阶段4,则第n天买出股票价格 - 第n-1天股票价格 + 前 n-1天阶段4最大获利,
     * 或者前n-1天处于阶段3，表示第n天才买入股票，最大获利为dp[i-1][3]
     *
     * @param prices
     * @return
     */
    public static int maxProfitIII(int[] prices) {

        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }

        int n = prices.length;
        int m = 5;

        int[][] dp = new int[n + 1][m + 1];
        // init
        dp[0][1] = 0;
        for (int j = 2; j < m; j++) {
            dp[0][j] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            // phase 1, 3, 5
            for (int j = 1; j <= m; j += 2) {

                dp[i][j] = dp[i - 1][j];
                if (j > 1 && i >= 2 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // phase 2, 4
            for (int j = 2; j <= m; j += 2) {
                dp[i][j] = dp[i - 1][j - 1];
                if (i >= 2 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i - 1][j] + prices[i - 1] - prices[i - 2], dp[i][j]);
                }
            }
        }
        for (int i = 1; i <= m; i += 2) {
            result = Math.max(result, dp[n][i]);
        }

        return result;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * <p>
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    public static int maxProfitIV(int k, int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }

        if (k >= prices.length / 2) {
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    result += prices[i] - prices[i - 1];
                }
            }
            return result;
        }

        int n = prices.length;
        int m = 2 * k + 1;

        int[][] dp = new int[n + 1][m + 1];
        // init
        dp[0][1] = 0;
        for (int j = 2; j < m; j++) {
            dp[0][j] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            // phase 1, 3, 5 .. 2k+1
            for (int j = 1; j <= m; j += 2) {

                dp[i][j] = dp[i - 1][j];
                if (j > 1 && i >= 2 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            // phase 2, 4 .. 2k
            for (int j = 2; j <= m; j += 2) {
                dp[i][j] = dp[i - 1][j - 1];
                if (i >= 2 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i - 1][j] + prices[i - 1] - prices[i - 2], dp[i][j]);
                }
            }
        }
        for (int i = 1; i <= m; i += 2) {
            result = Math.max(result, dp[n][i]);
        }

        return result;
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * <p>
     * 示例:
     * <p>
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * <p>
     * dp[i]表示以i为结尾的最长子序列长度
     * dp[i] = max(1, dp[j] + 1 {i > j} )
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int[] pi = new int[n]; // 记录每个最小值的位置
        int p = 0; // 最长子序列最后一个元素的位置

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            pi[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] == dp[j + 1]) {
                        pi[i] = j;
                    }
                }

            }
            // result
            result = Math.max(result, dp[i]);
            if (result == dp[i]) {
                p = i;
            }
        }

        int[] seq = new int[result]; // 最长子序列 各个元素数组，长度为result
        for (int i = seq.length - 1; i >= 0; i--) {
            seq[i] = nums[p];
            p = pi[p];
        }
        for (int i = 0; i < seq.length; i++)
            System.out.println(seq[i]);
        return result;
    }

    public static int maxEnvelopes(int[][] envelopes) {
        int result = 0;

        return result;
    }

    public static void main(String[] args) {

        // =====================================
        /*int houses[] = new int[]{3, 8, 4};
        int houses[] = new int[]{5, 2, 1, 3};
        assert houseRobber(houses) == 8;
        int houses[] = new int[]{2};
        assert houseRobber(houses) == 2;*/

        // =====================================
        /*int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        assert maxProfit(prices) == 5;
        prices = new int[]{7, 6, 4, 3, 1};
        assert maxProfit(prices) == 0;
        prices = new int[]{1, 2};
        assert maxProfit(prices) == 1;*/

        // =====================================
        /*int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        assert maxProfitII(prices) == 7;
        prices = new int[]{1, 2, 3, 4, 5};
        assert maxProfitII(prices) == 4;
        prices = new int[]{1};
        assert maxProfitII(prices) == 0;*/
        /*int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfitIII(prices));*/
        /*int[] prices = new int[]{2, 4, 1};
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(maxProfitIV(2, prices));*/
        int[] prices = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        // assert lengthOfLIS(prices) == 4;
        System.out.println(lengthOfLIS(prices));

    }
}
