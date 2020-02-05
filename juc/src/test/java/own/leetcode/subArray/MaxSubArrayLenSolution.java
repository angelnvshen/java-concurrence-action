package own.leetcode.subArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 911. 最大子数组之和为k
 * 中文English
 * 给一个数组nums和目标值k，找到数组中最长的子数组，使其中的元素和为k。如果没有，则返回0。
 * <p>
 * 样例
 * 样例1
 * <p>
 * 输入: nums = [1, -1, 5, -2, 3], k = 3
 * 输出: 4
 * 解释:
 * 子数组[1, -1, 5, -2]的和为3，且长度最大
 */
public class MaxSubArrayLenSolution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1, -1, 5, -2, 3
        // 1,  0, 5,  3, 6
        // prefix[i] == 3; len = i + 1 -> eq : i - j (j = -1);
        // prefix[i] - prefix[j] = 3; len = i - j;

        int sum = 0;
        int ans = 0;
        // prefix : index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ans = Math.max(ans, i - map.get(sum - k));
            }
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        MaxSubArrayLenSolution solution = new MaxSubArrayLenSolution();

        System.out.println(solution.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }
}
