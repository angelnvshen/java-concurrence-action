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

    public static void main(String[] args) {
        // ========================================
        /*System.out.println(numSquares(12));
        System.out.println(numSquares(13));*/

        // ========================================
        System.out.println(minCut("a"));
        System.out.println(minCut("aab"));
        // ========================================
        // ========================================
        // ========================================
        // ========================================
        // ========================================
        // ========================================
    }
}