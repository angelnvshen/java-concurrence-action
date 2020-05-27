package own.leetcode.rotateArray;

public class SearchRotateArrayII {

    public static void main(String[] args) {
        SearchRotateArrayII arrayII = new SearchRotateArrayII();
        System.out.println(arrayII.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(arrayII.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return true;

        int l = 0;
        int r = nums.length - 1;
        int m = -1;
        while (l + 1 < r) {
            m = (r - l) / 2 + l;

            if (nums[m] == target) return true;

            //前部分有序
            if (nums[l] == nums[m]) {
                l++;
            }else if (nums[l] < nums[m]) {
                // target在前部分
                if (nums[l] <= target && target < nums[m]) {
                    r = m;
                } else {
                    l = m;
                }
            } else {
                // 后半部分有序
                if (nums[m] < target && target <= nums[r]) {
                    l = m;
                } else {
                    r = m;
                }
            }
        }

        return nums[l] == target || nums[r] == target;
    }
}