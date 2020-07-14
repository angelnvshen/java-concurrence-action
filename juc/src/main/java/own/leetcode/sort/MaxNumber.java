package own.leetcode.sort;

import own.Printer;

import java.util.Deque;
import java.util.LinkedList;

public class MaxNumber {

    public static void main(String[] args) {
        MaxNumber number = new MaxNumber();
//        Printer.print(number.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5));
//        Printer.print(number.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5));
        Printer.print(number.maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3));
    }

    /**
     * @param nums1: an integer array of length m with digits 0-9
     * @param nums2: an integer array of length n with digits 0-9
     * @param k:     an integer and k <= m + n
     * @return: an integer array
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // write your code here
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        int n = nums1.length;
        int m = nums2.length;
        if (k > n + m) k = m + n;

        int[] ans = new int[k];
        for (int i = 0; i <= k; i++) {
            int k1 = k - i;
            if (i > n || k1 > m) continue;
            int[] new1 = getKMax(nums1, n, i);
            int[] new2 = getKMax(nums2, m, k - i);
            int[] newTmp = merge(new1, new2);
//            Printer.print(new1);
//            Printer.print(new2);
//            Printer.print(newTmp);
//            System.out.println(" ====== ");
            if (isGreater(newTmp, 0, ans, 0)) {
                ans = newTmp;
            }
        }
        return ans;
    }

    private boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        for (; i < nums1.length && j < nums2.length; i++, j++) {
            if (nums1[i] > nums2[j]) {
                return true;
            }
            if (nums1[i] < nums2[j]) {
                return false;
            }
        }
        return i != nums1.length;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0) {
            return nums2;
        }
        if (l2 == 0) {
            return nums1;
        }
        int n = l1 + l2;
        int[] ans = new int[n];
        int i = 0;
        int p1 = 0;
        int p2 = 0;
        while (i < n) {
            if (isGreater(nums1, p1, nums2, p2)) {
                ans[i++] = nums1[p1++];
            } else {
                ans[i++] = nums2[p2++];
            }
        }
        return ans;
    }

    private int[] getKMax(int[] nums, int n, int k) {
        // n >= k
        if (k == 0) return new int[0];
        int countDel = n - k;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i] && countDel > 0) {
                stack.pop();
                countDel--;
            }
            stack.push(nums[i]);
        }
        while (countDel > 0) {
            stack.pop();
            countDel--;
        }
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}