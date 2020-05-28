package own.leetcode.array;

import java.util.Random;

public class KthLargestElement {
    public static void main(String[] args) {
        KthLargestElement element = new KthLargestElement();
        System.out.println(element.kthLargestElement(7 / 2 + 1, new int[]{4,5,1,2,3,7,6}));
    }

    /**
     * @param k:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here

        if (nums == null || nums.length == 0 ||
                nums.length < k || k < 0) return -1;

        int l = 0;
        int r = nums.length - 1;
        while (true) {
//            int pivot = partition(nums, l, r);
            int pivot = random_partition(nums, l, r);
            if (pivot == k - 1)
                return nums[pivot];
            else if (pivot < k)
                l = pivot + 1;
            else
                r = pivot - 1;
        }
    }

    private static Random random = new Random();
    private int random_partition(int[] nums, int l, int r){
        int randomIndex = random.nextInt(r - l + 1) + l;
        swap(nums, randomIndex, r);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l , int r){
        int pivot = nums[r];
        int i = l - 1;
        for(int j = l; j <= r - 1; j ++){
            if(nums[j] <= pivot){
                i += 1;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, r);
        return i + 1;
    }

    private int partition_ii(int[] nums, int l, int r) {
        int pivot = nums[l];
        int lt = l + 1;
        int gt = r;
        // 降序排序
        // all in [left + 1, lt) >= pivot
        // all in (gt .. r] <= pivot
        while (true) {
            while (lt <= r && nums[lt] > pivot) {
                lt++;
            }
            while (gt > l && nums[gt] < pivot) {
                gt--;
            }

            if (lt > gt) break;

            swap(nums, lt, gt);
            lt++;
            gt--;
        }

        swap(nums, l, gt);
        return gt;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}