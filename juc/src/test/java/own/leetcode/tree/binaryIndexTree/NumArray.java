package own.leetcode.tree.binaryIndexTree;

public class NumArray {

    public static void main(String[] args) {
        /*
        Given nums = [1, 3, 5]

        sumRange(0, 2) -> 9
        update(1, 2)
        sumRange(0, 2) -> 8
        * */
        int[] nums = {1, 3, 5};
        NumArray array = new NumArray(nums);
        System.out.println(array.sumRange(0, 2));
        array.update(1, 2);
        System.out.println(array.sumRange(0, 2));
    }

    private int[] nums;
    private int[] tree;
    private int size;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.size = nums.length;
        this.tree = new int[size + 1]; // 从1开始
        built();
    }

    private void built() {
        for (int i = 0; i < size; i++) {
            updateTree(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {
        updateTree(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return preSum(j + 1) - preSum(i);
    }

    private void updateTree(int idx, int val) {
        for (; idx < tree.length; idx += lowBit(idx)) {
            tree[idx] += val;
        }
    }

    private int preSum(int i) {
        int ans = 0;
        for (; i > 0; i -= lowBit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    private int lowBit(int x) {
        return x & -x;
    }
}