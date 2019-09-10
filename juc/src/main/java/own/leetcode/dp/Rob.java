package own.leetcode.dp;

public class Rob {

  public static int rob(int[] nums) {

    if (nums.length == 0) {
      return 0;
    }

    if (nums.length == 1) {
      return nums[0];
    }

    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(dp[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }

    return dp[dp.length - 1];
  }

  public static void main(String[] args) {
    int[] num = new int[]{5, 2, 6, 3, 1, 7};
    System.out.println(rob(num));
  }
}
