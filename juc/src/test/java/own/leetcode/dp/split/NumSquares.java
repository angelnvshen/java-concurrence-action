package own.leetcode.dp.split;

public class NumSquares {

    public static void main(String[] args) {
        NumSquares squares = new NumSquares();
        System.out.println(squares.numSquares(1000000000));
        boolean x = true;
        boolean y = false;
        boolean z = y || x;
    }
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        
        /*
        dp[i] = dp[i - j * j] + 1 // j in (1 ~ sqrt(i))
        */
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}