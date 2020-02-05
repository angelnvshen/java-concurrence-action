package own.leetcode.subArray;

/**
 * 41. 最大子数组
 * 中文English
 * 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
 *
 * 样例
 * 样例1:
 *
 * 输入：[−2,2,−3,4,−1,2,1,−5,3]
 * 输出：6
 * 解释：符合要求的子数组为[4,−1,2,1]，其最大和为 6。
 *
 */
public class MaxSubArraySolution {

    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        /**


         −2, 2, −3, 4, −1, 2, 1, −5, 3
         -2, 0, -3, 1,  0, 2, 3, -2, 1  prefix sum

         */

        int result = 0;
        int ans = Integer.MIN_VALUE;
        int min = 0;

        int sum = 0;
        for(int num : nums){
            sum += num;
            ans = Math.max(ans, sum - min);
            min = Math.min(min, sum);
        }

        return ans;
    }

}
