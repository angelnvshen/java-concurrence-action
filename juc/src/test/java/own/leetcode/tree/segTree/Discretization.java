package own.leetcode.tree.segTree;

import own.Printer;

import java.util.TreeSet;

public class Discretization {

    public static void main(String[] args) {
        int[] a = {10000000, 2, 2, 123123213};
        solve(a);
        Printer.print(a);
    }

    private static void solve(int[] a) {

        // 排序 去重 找到对应的排名
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : a) {
            set.add(i);
        }

        int[] ans = new int[set.size()];
        int idx = 0;
        for (int i : set) {
            ans[idx++] = i;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = low_bound(ans, a[i]) + 1;
        }
    }

    //找到第一个 大于或等于 target的数字的位置
    private static int low_bound(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int mid = (r - l) / 2 + l;
        while (l + 1 < r) {
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (arr[l] >= target) return l;
        if (arr[r] >= target) return r;
        return -1;
    }
}
