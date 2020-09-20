package own.leetcode.dp;

public class IntegerBreak {

    public static void main(String[] args) {
        IntegerBreak breaks = new IntegerBreak();
        System.out.println(breaks.integerBreak(4));
//        System.out.println(breaks.integerBreak(10));
    }

    public int integerBreak(int n) {
        if(n <= 0) return 0;
        if(n <= 2) return 1;
        /*
        划分型dp
        dp[i] = max(j * dp[i - j] | 1 <= j < i);
        */
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for(int i = 3; i <= n; i ++){
            for(int j = 1; j < i; j ++)
                dp[i] = Math.max(Math.max(dp[i - j] * j, (i - j) * j), dp[i]);
        }
        return dp[n];
    }
}