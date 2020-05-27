package own.leetcode;

public class RotateArray {

    public static void main(String[] args) {
        RotateArray array = new RotateArray();
        int[] nums = {-1,-100,3,99};
        array.rotate(nums, 2);
        System.out.println(nums);
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;

        int n = nums.length;
        if (k > n) k %= n;
        rotate(nums, 0, n - 1 - k);
        rotate(nums, n - k, n - 1);
        rotate(nums, 0, n - 1);
    }

    private void rotate(int[] nums, int s, int e) {
        while (s < e) {
            swap(nums, s++, e--);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}