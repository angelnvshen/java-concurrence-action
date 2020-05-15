package own.leetcode;

import static own.leetcode.Sorting.less;
import static own.leetcode.Sorting.swap;

public class Sorting {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{4, 2, 8, 6, 0, 5, 1, 7, 3, 9};
//        Selection.sort(nums);
//        Insertion.sort(nums);
//        Merge.sort(nums);
        Merge.sort_ii(nums);
        show(nums);
    }

    public static void show(Comparable[] a) {
        for (Comparable i : a) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void swap(Comparable[] nums, int i, int j) {
        Comparable temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Selection {
    public static void sort(Comparable[] a) {
        if (a == null || a.length == 0) return;

        int n = a.length, min = 0;
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[i], a[j])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }
}

class Insertion {
    public static void sort(Comparable[] a) {
        if (a == null || a.length == 0) return;
        int n = a.length;
        // 第一个元素肯定是已排好序的
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (less(a[j], a[j - 1])) swap(a, j, j - 1);
            }
        }
    }
}

class Merge {
    public static void sort(Comparable[] a) {
        if (a == null || a.length == 0) return;
        int n = a.length;
        sort(a, a.clone(), 0, a.length - 1);
    }

    //自底向上
    public static void sort_ii(Comparable[] a) {
        if (a == null || a.length == 0) return;
        int n = a.length;
        for (int sz = 1; sz < n; sz += sz) { // sz: 子数组大小
            for (int lo = 0; lo < n - sz; lo += sz + sz) {    // i: 子数组索引
                merge(a, new Comparable[n], lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    // 自顶向下
    private static void sort(Comparable[] a, Comparable[] temp, int start, int end) {
        if (start >= end) return;

        int mid = (end - start) / 2 + start;
        sort(a, temp, start, mid);
        sort(a, temp, mid + 1, end);
        merge(a, temp, start, mid, end);
    }

    private static void merge(Comparable[] a, Comparable[] temp, int start, int mid, int end) {
        if (start >= end) return;

        int i = start;
        int j = mid + 1;
        int index;
        for (index = start; index <= end; index++) {
            if (j > end) temp[index] = a[i++]; //右半边用尽，取左边的元素
            else if (i > mid) temp[index] = a[j++]; //左半边用尽，取右边的元素
            else if (less(a[i], a[j])) temp[index] = a[i++];// 左半边当前的元素小于右半边当前元素，取走左半边的当前元素
            else temp[index] = a[j++];
        }

        for (index = start; index <= end; index++) {
            a[index] = temp[index];
        }
    }
}
