package own.leetcode.dp;

public class MaxSubArray {

  public static int maxSubArray(int[] nums) {

    if (nums.length == 0) {
      return 0;
    }

    if (nums.length == 1) {
      return nums[0];
    }

    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    int max_res = dp[0];
    for (int i = 1; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
      if (max_res < dp[i]) {
        max_res = dp[i];
      }
    }

    return max_res;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(maxSubArray(nums));
  }
}
