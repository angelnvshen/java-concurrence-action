package own.leetcode.game;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.waysToChange(1));
        System.out.println(solution.waysToChange(2));
        System.out.println(solution.waysToChange(3));
        System.out.println(solution.waysToChange(4));
        System.out.println(solution.waysToChange(5));
        System.out.println(solution.waysToChange(6));
        System.out.println(solution.waysToChange(10));
    }

    private static int[] coins = {1, 5, 10, 25};
    private static int MOD = 1000000007;

    public int waysToChange(int n) {
        if (n <= 0) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = 1; i <= n; i ++){
                if(i - coin >= 0)
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        return dp[n];
    }

}