package own.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class MaxNumber {

    /*
    *
    [2,5,6,4,4,0]
    [7,3,8,0,6,5,7,6,2]
    15
    * */
    public static void main(String[] args) {
        MaxNumber maxNumber = new MaxNumber();
//        int[] ints = maxNumber.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        int[] ints = maxNumber.maxNumber(new int[]{2,5,6,4,4,0}, new int[]{7,3,8,0,6,5,7,6,2}, 15);
        IntStream.of(ints).boxed().forEach(a -> System.out.print(a + " "));


    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        if (n + m <= k) {
            merge(nums1, nums2, ans);
            return ans;
        }
        int[] t1 = null;
        int[] t2 = null;
        int[] temp = new int[k];

        for (int i = 0; i <= k; i++) {
            t1 = findMaxK(nums1, i);
            t2 = findMaxK(nums2, k - i);
            merge(t1, t2, temp);
            ans = compare(ans, temp);
        }

        return ans;
    }

    private int[] compare(int[] a, int[] b) {
        if(a.length > b.length){
            return a.clone();
        }else if(a.length < b.length){
            return b.clone();
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) continue;
            if (a[i] > b[i]) return a.clone();
            else return b.clone();
        }
        return a.clone();
    }

    private int[] findMaxK(int[] a, int k) {
        int n = a.length;
        if (n <= k) return a;

        int countDel = n - k;

        // 单调递减栈
        Stack<Integer> stack = new Stack<>();
        for (int i : a) {
            while (!stack.isEmpty() && countDel > 0
                    && stack.peek() < i) {

                stack.pop();
                countDel -= 1;
            }
            stack.push(i);
        }
        for (int i = 0; i < countDel; i++) {
            stack.pop();
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }

    private void merge(int[] a, int[] b, int[] ans) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                ans[k++] = b[j++];
            } else {
                ans[k++] = a[i++];
            }
        }

        while (i < a.length) {
            ans[k++] = a[i++];
        }

        while (j < b.length) {
            ans[k++] = b[j++];
        }
    }
}