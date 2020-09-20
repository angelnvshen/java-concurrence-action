package own.leetcode.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ContainsNearbyAlmostDuplicate {

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate duplicate = new ContainsNearbyAlmostDuplicate();
//        System.out.println(duplicate.containsNearbyAlmostDuplicate(new int[]{2147483647, -2147483647}, 1, 2147483647));
//        System.out.println(duplicate.containsNearbyAlmostDuplicate(new int[]{2147483647, 0}, 1, 2147483647));
//        System.out.println(duplicate.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        System.out.println(duplicate.containsNearbyAlmostDuplicate(new int[]{-1, -1}, 1, -1));
    }

    public boolean containsNearbyAlmostDuplicate_ii(int[] nums, int posDistance, int valueDistance) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;

        TreeSet<Integer> bst = new TreeSet<>();


        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            Integer s = bst.ceiling(cur); // 后继
            if (s != null && inDistance(s, cur, valueDistance)) {
                return true;
            }
            Integer g = bst.floor(cur);// 前驱
            if (g != null && inDistance(g, cur, valueDistance)) {
                return true;
            }
            bst.add(cur);
            if (bst.size() > posDistance) {
                bst.remove(nums[i - posDistance]);
            }
        }
        return false;
    }

    private boolean inDistance(int a, int b, int valueDistance) {
        return Math.abs(0l + a - b) <= valueDistance;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        int bucketLen = t + 1;
        Map<Long, Integer> bucket = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Long bucketIdx = getBucketId(nums[i], min, bucketLen);
            if (bucket.get(bucketIdx) != null) return true;

            Integer pre = bucket.get(bucketIdx - 1);
            if (pre != null && inDistance(pre, nums[i], t)) {
                return true;
            }
            Integer post = bucket.get(bucketIdx + 1);
            if (post != null && inDistance(post, nums[i], t)) {
                return true;
            }
            bucket.put(bucketIdx, nums[i]);
            if (bucket.size() > k) {
                bucket.remove(getBucketId(nums[i - k], min, bucketLen));
            }
        }

        return false;
    }

    private Long getBucketId(int i, int min, int bucketLen) {
        return (0l + i - min) / bucketLen;
    }

    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate_i(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in nei***or buckets
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
}