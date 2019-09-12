package own.leetcode.dp;

public class CoinChange {

  public static int coinChange(int[] coins, int money) {
    int[] dp = new int[money + 1];

    for (int i = 0; i <= money; i++) {
      dp[i] = -1;
    }
    dp[0] = 0;

    for (int i = 1; i <= money; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (i >= coins[j] && dp[i - coins[j]] > -1) {
          if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
            dp[i] = dp[i - coins[j]] + 1;
          }
        }
      }
    }

    return dp[money];
  }

  public static void main(String[] args) {
    int[] coins = new int[]{1, 2, 5, 7, 10};
    for (int i = 0; i <= 14; i++) {
      System.out.println(i + " -> " + coinChange(coins, i));
    }
  }

}
