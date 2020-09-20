package own.leetcode.search;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsets(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        helper(ans, nums, 0, new ArrayList<>());
        return ans;
    }

    private void helper(List<List<Integer>> ans, int[] nums, int index, List<Integer> result) {
        ans.add(new ArrayList<>(result));
        System.out.println("cur : " + result);
        for (int i = index; i < nums.length; i++) {
            result.add(nums[i]);
            System.out.println("cur " + index + " - add " + nums[i] + " = > " + result);
            helper(ans, nums, i + 1, result);
            result.remove(result.size() - 1);
            System.out.println("cur " + index + " - remove " + nums[i] + " = > " + result);
        }
    }
}