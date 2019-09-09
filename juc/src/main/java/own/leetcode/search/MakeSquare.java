package own.leetcode.search;

import java.util.Arrays;

public class MakeSquare {

  public static boolean makeSquare(int[] nums) {

    if (nums.length < 4) {
      return false;
    }

    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }

    if (sum % 4 != 0) {
      return false;
    }

    Arrays.sort(nums);
    int[] bucket = new int[4];

    return generate(0, nums, sum / 4, bucket);
  }

  private static boolean generate(int i, int[] nums, int target, int[] bucket) {

    if (i >= nums.length) {
      return bucket[0] == target && bucket[1] == target && bucket[2] == target && bucket[3] == target;
    }

    for (int j = 0; j < 4; j++) {
      if (bucket[j] + nums[i] > target) {
        continue;
      }
      bucket[j] += nums[i];
      if (generate(i + 1, nums, target, bucket)) {
        return true;
      }
      bucket[j] -= nums[i];
    }
    return false;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 1, 2, 3};
    System.out.println(makeSquare(nums));
  }
}