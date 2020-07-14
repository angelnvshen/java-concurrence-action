package own.leetcode.search.dfs;

import java.util.Arrays;

public class CanPartition {

    public static void main(String[] args) {
        CanPartition partition = new CanPartition();
//        System.out.println(partition.canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(partition.canPartition(new int[]{1, 2, 3, 5, 7, 1, 5, 11, 5}));
//        System.out.println(partition.canPartition(new int[]{1, 2, 3, 5}));
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum & 1) == 1) return false;

        int target = sum / 2;
        Arrays.sort(nums);
        return helper(nums, 0, target);
    }

    private boolean helper(int[] nums, int index, int target) {
        if (target == 0 || index >= nums.length) {
            if (target == 0) return true;
            else return false;
        }

        boolean find = false;
        for (int i = index; i < nums.length; i++) {
            if (target < nums[i]) break;

            if (i > index && nums[i] == nums[i - 1]) continue;

            find |= helper(nums, i + 1, target - nums[i]);
        }

        return find;
    }
}