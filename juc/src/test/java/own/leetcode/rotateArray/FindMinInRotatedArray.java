package own.leetcode.rotateArray;

public class FindMinInRotatedArray {

    public static void main(String[] args) {
        FindMinInRotatedArray array = new FindMinInRotatedArray();
        System.out.println(array.findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(array.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        // 找到第一个比最后一个元素小的元素即可
        int l = 0;
        int r = nums.length - 1;
        int m = -1;
        while (l + 1 < r) {
            m = (r - l) / 2 + l;
            if (nums[m] <= nums[r]) {
                r = m;
            } else {
                l = m;
            }
        }

        return Math.min(nums[l], nums[r]);
    }
}