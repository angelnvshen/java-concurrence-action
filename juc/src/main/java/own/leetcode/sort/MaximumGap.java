package own.leetcode.sort;

public class MaximumGap {

    public static void main(String[] args) {
        MaximumGap gap = new MaximumGap();
//        System.out.println(gap.maximumGap(new int[]{1, 9, 2, 5}));
//        System.out.println(gap.maximumGap(new int[]{2147483625, 0, 22}));

//        int avgGap = (int) Math.ceil((double) (9 - 1) / (4 - 1));
//        System.out.println(avgGap);

        System.out.println(Math.abs(2147483647 + 2147483647 + 0l) > 2147483647l);

    }

    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        // write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        if (n == 2) return Math.abs(nums[0] - nums[1]);
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        if (min == max) return 0; // 数组中的值都是一样的，间隔为0
        int bucketSize = (int) ((long) (max - min + n - 2) / (n - 1));

        Bucket[] buckets = new Bucket[n - 1];// 桶的个数比数字个数少一个
        for (int i = 0; i < n - 1; i++) {
            buckets[i] = new Bucket();
        }

        for (int i : nums) {
            int bucketIdx = getIndex(bucketSize, i, n - 1, min);
            // System.out.println(bucketIdx);
            buckets[bucketIdx].updateValue(i);
        }

        int ans = 0;
        int curMin = min;
        for (Bucket b : buckets) {
            if (b.min == Integer.MAX_VALUE) continue;
            // System.out.println(b.min + ", " + b.max + " | " + curMin);
            ans = Math.max(ans, b.min - curMin);
            curMin = Math.max(curMin, b.max);
        }
        return ans;
    }

    private int getIndex(int bucketSize, int num, int bucketNum, int min) {

        return Math.min(bucketNum - 1, (num - min) / bucketSize);
    }

    class Bucket {
        int min;
        int max;

        public Bucket() {
            min = Integer.MAX_VALUE;
            max = 0;
        }

        public void updateValue(int v) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }
    }
}