package own.leetcode.subArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 838. 子数组和为K
 * 中文English
 * 给定一个整数数组和一个整数k，你需要找到连续子数列的和为k的总个数。
 * <p>
 * 样例
 * 样例1
 * <p>
 * 输入: nums = [1,1,1] 和 k = 2
 * 输出: 2
 * 解释:
 * 子数组 [0,1] 和 [1,2]
 */
public class subarraySumEqualsKSolution {

    public int subarraySumEqualsK(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)){
                ans += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}
