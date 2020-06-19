package own.leetcode.dp.split;

public class PalinDromePartition {

    public static void main(String[] args) {
        PalinDromePartition partition = new PalinDromePartition();
        System.out.println(partition.minCut("a"));
    }

    /**
     * @param s: A string
     * @return: An integer
     */
    public int minCut(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();

        boolean[][] isPalin = helper(s.toCharArray());
        
        /*
        dp[i] 表示前i个字符能够最少切割成多少个回文子串
        dp[i] = min(dp[j] + 1) j in 0 ~ i - 1; s(j ~ i - 1) 是回文串
        */
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalin[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n] - 1; // 分割次数 = 最小的回文子串数 - 1；
    }

    private boolean[][] helper(char[] s) {
        int n = s.length;
        boolean[][] isPalin = new boolean[n][n];

        // odd length
        // 中心是字符
        int i, j, c;
        for (c = 0; c < n; c++) { // n个字符
            i = j = c;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }

        // even length
        // 中心是线
        for (c = 0; c < n - 1; c++) { // n个字符中有 n -1 条线
            i = c;
            j = c + 1;
            while (i >= 0 && j < n && s[i] == s[j]) {
                isPalin[i][j] = true;
                i--;
                j++;
            }
        }

        return isPalin;
    }
}