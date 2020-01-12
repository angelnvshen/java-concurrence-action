package own.leetcode.other;

public class DPSolutionIII {


    /**
     * 完美平方: 给一个正整数 n, 请问最少多少个完全平方数(比如1, 4, 9...)的和等于n。
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: 12
     * 输出: 3
     * 解释: 4 + 4 + 4
     * <p>
     * 最后一个 完全平方数 j^2，n-j^2 也一定会被划分成最少的平方数之和。
     * dp[i] 表示i最少被划分为几个平方数之和
     * <p>
     * dp[i] = min(dp[i-j^2] { j^2 in 1 ... i } + 1)
     *
     * @param n
     * @return
     */
    public static int numSquares(int n) {

        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }


    /**
     * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
     * <p>
     * 最少需要分割几次?
     * <p>
     * 样例
     * <p>
     * 输入: "a"
     * 输出: 0
     * 解释: "a" 本身就是回文串, 无需分割
     * <p>
     * 可以划分的最少子串数 - 1 就是最少分割的次数
     * <p>
     * S[j..n-1] 是最后一段回文串，则 需要知道 S[0..j-1]最少可以划分多少个回文串
     * 固求S[0..n-1]最少可以划分多少个回文串，可以转为子问题 S[0..j-1]
     * <p>
     * dp[i] 表示S的前i个字符S[0..i-1]最少可以划分多少个回文串
     * dp[i] = min(dp[j](j..i 表示一个回文串) + 1) {j in 0.. i-1}
     * <p>
     * init dp[0] = 0
     *
     * @param s
     * @return
     */
    public static int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] palin = isPalin(chars);

        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (palin[j][i - 1]) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }

        return dp[n] - 1;
    }

    // s[i][j]表示 s[i..j-1]是否是回文串
    private static boolean[][] isPalin(char[] s) {
        int n = s.length;
        boolean pa[][] = new boolean[n][n];

        int i, j;
        for (int c = 0; c < n; c++) { // c表示生成回文串的中间值，如果s长度是奇数，则c表示字母，一共n个字母
            i = j = c;
            while (i >= 0 && j < n && s[i] == s[j]) {
                pa[i][j] = true;
                i--;
                j++;
            }
        }

        for (int c = 0; c < n - 1; c++) { // c表示生成回文串的中间值，如果s长度是偶数，则c表示一条线，一共n-1条线
            i = c;
            j = c + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                pa[i][j] = true;
                i--;
                j++;
            }
        }

        return pa;
    }

    /**
     * 给定 n 本书, 第 i 本书的页数为 pages[i]. 现在有 K 个人来复印这些书籍, 而每个人只能复印编号连续的一段的书,
     * 比如一个人可以复印 pages[0], pages[1], pages[2], 但是不可以只复印 pages[0], pages[2], pages[3] 而不复印 pages[1].
     * <p>
     * 所有人复印的速度是一样的, 复印一页需要花费一分钟, 并且所有人同时开始复印. 怎样分配这 K 个人的任务, 使得这 n 本书能够被尽快复印完?
     * <p>
     * 返回完成复印任务最少需要的分钟数.
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: pages = [3, 2, 4], K = 2
     * 输出: 5
     * 解释: 第一个人复印前两本书, 耗时 5 分钟. 第二个人复印第三本书, 耗时 4 分钟.
     * <p>
     * 最优策略中最后一个抄写员k，抄写了 i..n-1 本书。 需要的时间为 pages[i] + ... + pages[n-1],
     * 再需要知道前k-1个人最少需要的时间抄写前i（0 ... i-1 ）本书
     * dp[K][i]表示前k个抄写员抄写前i本书最少需要的时间
     * <p>
     * dp[K][i] = min( max(dp[K-1][j] {前k-1个抄写员抄写前j本书最少的时间}, sum(page[j ... i-1]){第 k个抄写员抄写 j... i-1 本书最少的时间} )) {j in 0...i }
     * <p>
     * inti
     * dp[0][0] = 0； 0本书耗时0，dp[0][i] = Integer.max_value 表示 0个抄写员 耗时正无穷
     *
     * @param pages
     * @param K
     * @return
     */
    public static int copyBooks(int[] pages, int K) {

        if (pages == null || pages.length == 0) {
            return 0;
        }
        int n = pages.length;
        if (K > n) {
            K = n;
        }

        int dp[][] = new int[K + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int k = 1; k <= K; k++) {
            dp[k][0] = 0;
            for (int i = 1; i <= n; i++) {
                dp[k][i] = Integer.MAX_VALUE;

                int sum = 0;// pages[i ... k-1]
                for (int j = i; j >= 0; j--) {

                    dp[k][i] = Math.min(dp[k][i], Math.max(sum, dp[k - 1][j]));

                    if (j >= 1) {
                        sum += pages[j - 1];
                    }
                }
            }
        }

        return dp[K][n];
    }

    // ================================ 以上为： 划分型动态规划 ========================

    /**
     * 有 n 个硬币排成一条线。两个参赛者轮流从右边依次拿走 1 或 2 个硬币，直到没有硬币为止。拿到最后一枚硬币的人获胜。
     * <p>
     * 请判定 先手玩家 必胜还是必败?
     * <p>
     * 若必胜, 返回 true, 否则返回 false.
     * <p>
     * 样例
     * <p>
     * 输入: 1
     * 输出: true
     * <p>
     * dp[i] 是面对i个硬币，是否先手必胜（true/false)
     * 拿走1枚硬币后，是否是必胜 ： dp[i-1] = true | false
     * 拿走2枚硬币后，是否是必胜 ： dp[i-2] = true | false
     * <p>
     * dp[i] ---
     * dp[i-1] = true , dp[i-2] = true ，必败
     * dp[i-1] = false , dp[i-2] = true ，拿走1枚硬币，此时必胜
     * dp[i-1] = true , dp[i-2] = false ，拿走2枚硬币，此时必胜
     * dp[i-1] = false , dp[i-2] = false ，拿走1枚火2枚硬币，此时必胜
     * <p>
     * dp[i] = (dp[i-1] == false or dp[i-2] == false)
     * <p>
     * init
     * dp[0] = false,面对0个硬币，先手必败
     * dp[1] = dp[2] = true;
     *
     * @param n
     * @return
     */
    public static boolean firstWillWin(int n) {

        if (n == 0) {
            return false;
        }

        boolean dp[] = new boolean[n + 1];
        dp[0] = false;
        dp[1] = true;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = (!dp[i - 1]) || (!dp[i - 2]);
        }
        return dp[n];
    }

    // ================================ 以上为： 博弈型动态规划 (通常从第一步开始分析，因为随着硬币越来越少，局面越来越简单)========================

    /**
     * 背包问题
     * 中文English
     * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
     * <p>
     * 样例: 样例 1:
     * 输入:  [3,4,8,5], backpack size=10
     * 输出:  9
     * <p>
     * 样例 2:
     * 输入:  [2,3,5,7], backpack size=12
     * 输出:  12
     * <p>
     * dp[i][m] 前i个物品能否拼出重量m
     * dp[i][m] = dp[i-1][m] || dp[i-1][m-A[i]]
     * dp[0]0] = true; dp[0][1...m-1] = false
     *
     * @param m
     * @param A
     * @return
     */
    public static int backPack(int m, int[] A) {
        if (m == 0 || A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        boolean dp[][] = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j]; // 前i-1个物品就能拼出m
                if (j >= A[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - A[i - 1]]; // 前i-1个物品能拼出重量 m-A[i]
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[n][i] == true) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 563. 背包问题 V
     * 中文English
     * 给出 n 个物品, 以及一个数组, nums[i] 代表第i个物品的大小, 保证大小均为正数, 正整数 target 表示背包的大小, 找到能填满背包的方案数。
     * 每一个物品只能使用一次
     * <p>
     * 样例
     * 给出候选物品集合 [1,2,3,3,7] 以及 target 7
     * <p>
     * 结果的集合为:
     * [7]
     * [1,3,3]
     * 返回 2
     * <p>
     * dp[i][w]前i个物品能拼出重量的方式数
     * dp[i][w] = dp[i-1][w] 表示前i-1个物品能拼出w的方式数量 + dp[i-1][w-nums[i]] 表示前i-1个物品能拼出w-num[i]重量的方式数目
     *
     * @param nums
     * @param target
     * @return
     */
    public static int backPackV(int[] nums, int target) {
        if (target == 0 || nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        /*
        // 空间复杂度 n * target
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= target; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];*/

        // 只是用一维数组，因为 dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]],是由上一层的数字相加而来，所以可以简化到一层来操作，但是要注意从后往前操作。
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            dp[i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 564. 组合总和 IV
     * 中文English
     * 给出一个都是正整数的数组 nums，其中没有重复的数。从中找出所有的和为 target 的组合个数。
     * <p>
     * 样例
     * 样例1
     * <p>
     * 输入: nums = [1, 2, 4] 和 target = 4
     * 输出: 6
     * 解释:
     * 可能的所有组合有：
     * [1, 1, 1, 1]
     * [1, 1, 2]
     * [1, 2, 1]
     * [2, 1, 1]
     * [2, 2]
     * [4]
     * <p>
     * 有点类似零钱的问题
     * dp[i] = sum(dp[i-num[j]]) {j in 0... num.length-1} 表示 前target的目标组合个数
     * <p>
     * init
     * dp[0] = 1 有一种方式拼成 重量 0 （什么都不选）
     *
     * @param nums
     * @param target
     * @return
     */
    public static int backPackVI(int[] nums, int target) {
        if (target == 0 || nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int dp[] = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 0;
            for (int j = 0; j < n; j++) {

                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }

        return dp[target];
    }

    /**
     * 125. 背包问题 II
     * 中文English
     * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.
     * <p>
     * 问最多能装入背包的总价值是多大?
     * <p>
     * 样例
     * 输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
     * 输出: 9
     * 解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9
     * <p>
     * dp[i][w] 前i个物品能够拼成重量w 的最大价值
     * dp[i][w] = max(dp[i-1][w] 前 i-1个物品就能够拼成w，i个物品就不用当到背包就可以完成拼成w的方式,
     * dp[i-1][w-A[i]] + V[i]表示前i-1个物品能够拼成重量 w-a[i] 再加上第i个物品的重量就是w的重量，所以价值就是前边所写
     *
     * @param m 背包大小
     * @param A 物品大小
     * @param V 物品价值
     * @return
     */
    public static int backPackII(int m, int[] A, int[] V) {
        if (m == 0 || A == null || A.length == 0 || V == null || V.length == 0 || A.length != V.length) {
            return 0;
        }

        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];

        // init
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[0][i] = -1;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i <= m; i++) {
            if (dp[n][i] != -1) {
                result = Math.max(result, dp[n][i]);
            }
        }
        return result;
    }

    /**
     * TODO
     * @param m
     * @param A
     * @param V
     * @return
     */
    public static int backPackIII(int m, int[] A, int[] V) {
        return 0;
    }
    // ================================ 以上为： 背包型动态规划（数组的大小和总承重有关)========================

    // ================================ 以上为： 区间型动态规划 ========================

    public static void main(String[] args) {
        // ========================================
        /*System.out.println(numSquares(12));
        System.out.println(numSquares(13));*/

        // ========================================
        /*System.out.println(minCut("a"));
        System.out.println(minCut("aab"));*/

        // ========================================
        /*int pages[] = new int[]{3, 2, 4};
        System.out.println(copyBooks(pages, 2));*/

        // ========================================
        /*System.out.println(firstWillWin(1));
        System.out.println(firstWillWin(4));
        System.out.println(firstWillWin(5));
        System.out.println(firstWillWin(6));*/

        // ========================================
        /*int weights[] = new int[]{3, 4, 8, 5};
        System.out.println(backPack(9, weights));
        weights = new int[]{2, 3, 5, 7};
        System.out.println(backPack(12, weights));*/
        // ----------------------------------------
        /*int weights[] = new int[]{1, 2, 3, 3, 7};
        System.out.println(backPackV(weights, 7));
        int weights[] = new int[]{1, 2, 4};
        int weights[] = new int[]{1, 2};
        System.out.println(backPackVI(weights, 4));*/
        // ----------------------------------------
        /*int weights[] = new int[]{2, 3, 5, 7};
        int values[] = new int[]{1, 5, 2, 4};
        System.out.println(backPackII(10, weights, values));

        int weights[] = new int[]{2, 3, 8};
        int values[] = new int[]{2, 5, 8};
        System.out.println(backPackII(10, weights, values));*/
        // ========================================
        // ========================================
        // ========================================
    }
}