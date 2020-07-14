package own.leetcode.array.twoSum;

public class TwoSumVII {

    public static void main(String[] args) {
        String s = "ACGT";
        for(int i = 0; i < s.length(); i ++){
            System.out.println(s.charAt(i) & 7);
        }
    }

    public static void main2(String[] args) {
        TwoSumVII twoSum = new TwoSumVII();
        int[] nums = new int[]{0, -1, 2, -3, 4, 5, -7, -8, 9, -10};

        int n = nums.length;
        int right = 0; // 最大值得索引
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[right]) right = i;
        }

        System.out.println(right);
        while (right != -1) {
            System.out.print(nums[right] + "  ");
            right = twoSum.nextRight(nums, right);
        }
        System.out.println();
        int left = 0;
        for (int i = 0; i < n; i++) {
            if (nums[left] > nums[i]) left = i;
        }

        while (left != -1) {
            System.out.print(nums[left] + "  ");
            left = twoSum.nextLeft(nums, left);
        }
    }

    // right 当前最大值的索引
    private int nextRight(int[] nums, int right) {
        int n = nums.length;
        if (nums[right] > 0) {
            for (int i = right - 1; i >= 0; i--) {
                if (nums[i] > 0) return i;
            }
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0) return i;
            }
            return -1;
        }
        for (int i = right + 1; i < n; i++) {
            if (nums[i] <= 0) return i;
        }

        return -1;
    }

    // left 当前的最小值的索引
    private int nextLeft(int[] nums, int left) {
        int n = nums.length;
        if (nums[left] < 0) {
            for (int i = left - 1; i >= 0; i--) {
                if (nums[i] < 0) return i;
            }
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) return i;
            }
            return -1;
        }
        for (int i = left + 1; i < n; i++) {
            if (nums[i] >= 0) return i;
        }
        return -1;
    }
}
