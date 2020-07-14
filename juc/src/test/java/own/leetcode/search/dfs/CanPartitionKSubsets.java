package own.leetcode.search.dfs;

import java.util.Arrays;

public class CanPartitionKSubsets {

    public static void main(String[] args) {
        CanPartitionKSubsets subsets = new CanPartitionKSubsets();
//        System.out.println(subsets.canPartitionKSubsets(new int[]{4, 3, 1}, 2));
//        System.out.println(subsets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(subsets.canPartitionKSubsets(new int[]{18, 20, 39, 73, 96, 99, 101, 111, 114, 190, 207, 295, 471, 649, 700, 1037}, 4));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        int max = 0;
        for (int i : nums) {
            sum += i;
            max = Math.max(max, i);
        }
        if (sum % k != 0) return false;
        perSum = sum / k;
        if (perSum < max) return false;
        Arrays.sort(nums);
        return helper(nums, perSum, k, 0, new boolean[nums.length]);
    }

    private int perSum = 0;//每个子集的和

    private boolean helper(int[] nums, int target, int left, int index, boolean[] visited) {
        if (left == 0) return true;
        if (target == 0) {
            return helper(nums, perSum, left - 1, 0, visited);
        }
        for (int i = index; i < nums.length; i++) {
            if (target < nums[i]) break;
            if (visited[i]) continue;

            visited[i] = true;
            if (helper(nums, target - nums[i], left, i + 1, visited)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }
}