package own.leetcode.other;

public class DPSolution {

    /**
     * You are given coins of different denominations and a total amount of money amount.
     * Write a function to compute the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins, return -1
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * <p>
     * dp[i] = min(dp[i-coins[1]], dp[i-coins[2]], dp[i-coins[5]])+1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        /*int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != MAX_VALUE) {
                    if (dp[i] > dp[i - coins[j]]) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount] == MAX_VALUE ? -1 : dp[amount];*/

        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]]) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 问总共有多少条不同的路径？
     * <p>
     * 示例 1:
     * <p>
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * <p>
     * 说明：m 和 n 的值均不超过 100。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: 2
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n_len = obstacleGrid.length;
        int m_len = obstacleGrid[0].length;
        // 初始化dp数组 为原数组大小 行和列分别 + 1，用以放置第一行和第一列作为边界值（dp[i=0][j] = 0, dp[i][j=0] = 0)
        int dp[][] = new int[n_len + 1][m_len + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 初始化dp[1][1] 为1，表示为第一步，当且仅当原数组第一个数值obstacleGrid[0][0]不是障碍物。
                if (i == 1 && j == 1 && obstacleGrid[i - 1][j - 1] == 0) {
                    dp[i][j] = 1;
                } else if (i != 0 && j != 0) {
                    if (obstacleGrid[i - 1][j - 1] == 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[n_len][m_len];
    }

    /**
     * 一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。
     * <p>
     * 请注意：
     * <p>
     * 石子的数量 ≥ 2 且 < 1100；
     * 每一个石子的位置序号都是一个非负整数，且其 < 231；
     * 第一个石子的位置永远是0。
     * <p>
     * [0,1,3,5,6,8,12,17]
     * <p>
     * 总共有8个石子。
     * <p>
     * ===== 解题 ======
     * dp[0] = true
     * dp[i] 表示青蛙能否跳到石头i上。
     * dp[i] = or{j in (0, i-1) }(dp[j] & a[j] + j > i )
     * <p>
     * 时间复杂度 o(power(n,2))
     */
    public static boolean canCross(int[] stones) {

        stones[0] = 1;
        boolean dp[] = new boolean[stones.length];
        dp[0] = true;
        for (int i = 1; i < stones.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && stones[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[stones.length - 1];
    }

    /**
     * 房屋染色
     * 中文English
     * 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
     * <p>
     * 费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用。
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: [[14,2,11],[11,14,5],[14,3,10]]
     * 输出: 10
     * <p>
     * r,g,b表示三种颜色 分别是三个列的index
     * dp[i][r] = costs[i][r] + min(dp[i-1][g], dp[i-1][b]);
     *
     * @param costs
     * @return
     */
    public static int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        int dp[][] = new int[n + 1][3];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {// 注意 costs[i-1][j] 中 i-1 因为dp数组比cost多初始化一列，来代表空值
                    dp[i][j] = costs[i - 1][j] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                }
                if (j == 1) {
                    dp[i][j] = costs[i - 1][j] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                }
                if (j == 2) {
                    dp[i][j] = costs[i - 1][j] + Math.min(dp[i - 1][0], dp[i - 1][1]);
                }
            }
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    /**
     * 这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。
     * <p>
     * 费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。
     * <p>
     * 跟上一题的类似：房屋有3变成k
     *
     * @param costs
     * @return
     */
    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;

        int dp[][] = new int[n + 1][k];
        // init ,这一步可以不做，默认int数组 初始化都是0
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }

        // dp[i][k] 第i栋房子的对最小花费和次小花费
        int min1, min2;
        int id1 = 0, id2 = 0;

        for (int i = 1; i < dp.length; i++) {
            min1 = min2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    id2 = id1;
                    min1 = dp[i - 1][j];
                    id1 = j;
                } else if (dp[i - 1][j] < min2) {
                    min2 = dp[i - 1][j];
                    id2 = j;

                }

            }
            if(min2 == Integer.MAX_VALUE){
                min2 = 0;
            }
            for (int j = 0; j < k; j++) {
                if (j != id1) {
                    dp[i][j] = min1 + costs[i - 1][j];
                } else {
                    dp[i][j] = min2 + costs[i - 1][j];
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            result = Math.min(dp[n][i], result);
        }

        return result;
    }

    /**
     * 描述
     * 中文
     * English
     * 有一个消息包含A-Z通过以下规则编码
     * <p>
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     * <p>
     * 样例 1:
     * <p>
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以被解码为 AB (1 2) 或 L (12).
     * <p>
     * i表示前i个字符的解法总数
     * dp[i] = dp[i-1](s[i-1]是一个字母）+ dp[i-2](s[i-2]s[i-1]两个数组组成一个字母）
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 0) {
            return 0;
        }

        int dp[] = new int[n + 1];
        dp[0] = 1; // 初始化 空串解法为1
        for (int i = 1; i < dp.length; i++) {
            if (chars[i - 1] >= '1' && chars[i - 1] <= '9') {
                dp[i] += dp[i - 1];
            }
            if (i > 1) { // 边界 i = 1时，注意
                int j = 10 * (chars[i - 2] - '0') + (chars[i - 1] - '0');
                if (j >= 10 && j <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     * <p>
     * 示例:
     * <p>
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     * <p>
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     * <p>
     * <p>
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {

        return -1;
    }

    /**
     * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,5,4,7]
     * 输出: 3
     * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
     * <p>
     * <p>
     * dp[i] 表示以i为结尾的最长连续递增序列的长度
     * dp[i] = max(1, dp[i-1] + 1 {a[i] > a[i-1]}
     * <p>
     * result = max(dp[i] i in(0, n-1) )
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int result = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // case 0:
            dp[i] = 1;

            // case 1:
            if (i > 0 && nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

    /**
     * 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
     * <p>
     * <p>
     * 样例
     * 输入:  [[1,3,1],[1,5,1],[4,2,1]]
     * 输出: 7
     * <p>
     * 样例解释：
     * 路线为： 1 -> 3 -> 1 -> 1 -> 1。
     * <p>
     * dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
     * <p>
     * TODO 滚动数组
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        int[][] pi = new int[n][m]; // 记录路径：0 表示从上边而来，1表示从左边来
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = grid[i - 1][j - 1];
                } else {
                    dp[i][j] = grid[i - 1][j - 1] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                    if (dp[i - 1][j] > dp[i][j - 1]) { // 左边的大于上边的
                        pi[i - 1][j - 1] = 1;
                    }
                }
            }
        }

        // 此种方式只能是一条路径，如果是多条路径可以考虑 链表形式
        // 打印路径和最大的那个路径
        int path[] = new int[m + n - 1];
        int x = n - 1;
        int y = m - 1;
        for (int p = 0; p < path.length; p++) {
            path[p] = grid[x][y];
            if (pi[x][y] == 1) {
                x--;
            } else {
                y--;
            }
        }
        for (int i = path.length - 1; i >= 0; i--) {
            System.out.print(path[i] + ", ");
        }

        return dp[n][m];
    }

    /**
     * 炸弹袭击
     * 中文English
     * 给定一个二维矩阵, 每一个格子可能是一堵墙 W,或者 一个敌人 E 或者空 0 (数字 '0'), 返回你可以用一个炸弹杀死的最大敌人数.
     * 炸弹会杀死所有在同一行和同一列没有墙阻隔的敌人。 由于墙比较坚固，所以墙不会被摧毁.
     * <p>
     * 样例
     * 样例1
     * <p>
     * 输入:
     * grid =[
     * "0E00",
     * "E0WE",
     * "0E00"
     * ]
     * 输出: 3
     * 解释:
     * 把炸弹放在 (1,1) 能杀3个敌人
     * <p>
     * up[i][j] -> if(grid[i][j] = 'W') up[i][j] = 0, if(grid[i][j] = 'E')  up[i][j] += 1; up[i][j] += up[i-1][j]
     *
     * @param grid
     */
    public static int maxKilledEnemies(char[][] grid) {
        // write your code here

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] left = new int[n][m];
        int[][] right = new int[n][m];

        // up
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    up[i][j] = 0;
                    continue;
                }
                up[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
            }
        }

        // down
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    down[i][j] = 0;
                    continue;
                }
                down[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (i < n - 1) {
                    down[i][j] += down[i + 1][j];
                }
            }
        }

        // left
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'W') {
                    left[i][j] = 0;
                    continue;
                }
                left[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (j > 0) {
                    left[i][j] += left[i][j - 1];
                }
            }
        }

        // right
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') {
                    right[i][j] = 0;
                    continue;
                }
                right[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (j < m - 1) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    int tem = up[i][j] + down[i][j] + left[i][j] + right[i][j];
                    result = Math.max(result, tem);
                }
            }
        }

        return result;
    }

    /**
     * 给出一个 非负 整数 num，对所有满足 0 ≤ i ≤ num 条件的数字 i 均需要计算其二进制表示中数字 1 的个数并以数组的形式返回。
     * <p>
     * 样例
     * 样例1
     * <p>
     * 输入： 5
     * 输出： [0,1,1,2,1,2]
     * 解释：
     * 0~5的二进制表示分别是：
     * 000
     * 001
     * 010
     * 011
     * 100
     * 101
     * 每个数字中1的个数为： 0,1,1,2,1,2
     * <p>
     * dp[i] 表示 数字 i中的二进制格式中1的个数
     * dp[i] = dp[i >> 1] + i & 1;
     *
     * @param num
     * @return
     */
    public static int[] countBits(int num) {
        // write your code here
        int dp[] = new int[num + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        // Arrays.stream(dp).boxed().forEach(System.out::println);
        return dp;
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 22; i++) {
            System.out.println(i + ", " + coinChange(new int[]{1, 2, 5}, i));
        }
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1}, 0));
        System.out.println(coinChange(new int[]{186, 419, 83, 408}, 6249));*/

        // ===============================
        /*System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(7, 3));*/

        // ===============================
        /*System.out.println(canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));*/

        // ===============================
        /*int[][] nums = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] nums = new int[][]{{0}};
        System.out.println(uniquePathsWithObstacles(nums));*/

        // ===============================
        /*int[][] nums = new int[][]{{1, 2, 3}, {1, 4, 6}};
        int[][] nums = new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}};
        int[][] nums = new int[][]{{5}};
        System.out.println(minCost(nums));
        System.out.println(minCostII(nums));*/

        // ===============================
        /*System.out.println(numDecodings("12"));
        System.out.println(numDecodings("100"));*/

        // ===============================
        /*int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));*/

        // ===============================
        /*int[] nums = new int[]{2, 2, 2, 2, 2};
        int[] nums = new int[]{1, 3, 5, 4, 7};
        int[] nums = new int[]{1};
        System.out.println(findLengthOfLCIS(nums));*/

        // ===============================
        /*int[][] nums = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] nums = new int[][]{{1, 2}, {1, 1}};
        System.out.println(minPathSum(nums));*/

        // ===============================
        /*char[][] nums = new char[][]{{'0', 'E', '0', '0'}, {'E', '0', 'W', 'E'}, {'0', 'E', '0', '0'}};
        char[][] nums = new char[][]{{'E'}};

        *//*char[][] nums = null;
        String[] str = new String[]{"W00000EEEEEEEE000000WWW0WWW00W0W0WEEEE0000EW00W"};
        int c_lent = str.length;
        nums = new char[c_lent][];
        for (int i = 0; i < str.length; i++) {
            String s = str[i];
            nums[i] = s.toCharArray();
        }*//*

        System.out.println(maxKilledEnemies(nums));*/

        // ===============================
        // countBits(5);

    }
}