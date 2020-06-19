package own.leetcode.dp;

public class LongestPalindromeSubseq {

    public static void main(String[] args) {
        LongestPalindromeSubseq subseq = new LongestPalindromeSubseq();
        System.out.println(subseq.longestPalindromeSubseq("bbbab"));
    }
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        /*
        划分型
        dp[i][j]表示字符i...j的最长回文子序列的长度
        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 1 | s(i) == s(j))
        */

        int n = s.length();
        char[] cs = s.toCharArray();
        int[][] dp = new int[n][n];
        // len = 1;
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // len = 2;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = (cs[i] == cs[i + 1]) ? 2 : 1;
        }
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (cs[i] == cs[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        printn(dp, 0, 0);
        return dp[0][n - 1];
    }

    private void printn(int[][] dp, int m, int n) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}