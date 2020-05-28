package own.leetcode.array;

public class FindMedian {

    public static void main(String[] args) {
        FindMedian findMedian = new FindMedian();
//        findMedian.median(new int[]{2,1,3,4});
        findMedian.median(new int[]{7, 9, 4, 5});
    }

    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public int median(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        boolean odd = (len & 1) == 1;
        if (odd) {
            return findKthElement(nums, len / 2 + 1); // 第K大，索引在+ 1
        } else {
            int l = findKthElement(nums, len / 2);
            int r = findKthElement(nums, len / 2 + 1);


            return (l + r) / 2;
        }

    }

    private int findKthElement(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;

        while (true) {
            int p = partition(nums, l, r);
            System.out.println(p + ", " + nums[p] + "," + l + "," + r);
            if (p == k - 1) {
                return nums[p];
            } else if (p < k - 1) {
                l = p + 1;
            } else {
                r = p - 1;
            }
        }
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; j++) {
            if (nums[j] <= pivot) {
                i += 1;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}