package own.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PancakeSort {

    // 记录反转操作序列
    LinkedList<Integer> res = new LinkedList<>();

    public static void main(String[] args) {
//        PancakeSort sort = new PancakeSort();
//        System.out.println(sort.pancakeSort(new int[]{3, 2, 4, 1}));

        TreeSet<String> set = new TreeSet<>(String::compareTo);
        set.add("OO");
        set.add("AA");
        set.add("CC");
        System.out.println(set);
        System.out.println(set.last());
    }

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        if (A == null || A.length == 0) return ans;

        // 找到最大的元素，翻煎饼，最大到顶部，在整个翻动，最大得到底部
        // 求 0 ~ n - 2 的子集
        sort(A, A.length - 1);

        return res;
    }

    private void sort(int[] a, int n) {
        if (n == 0) return;
        int maxIndex = -1;
        int max = 0;
        for (int i = 0; i <= n; i++) {
            if (max < a[i]) {
                max = a[i];
                maxIndex = i;
            }
        }
        exch(a, 0, maxIndex);
        res.add(maxIndex + 1);
        exch(a, 0, n);
        res.add(n + 1);
        sort(a, n - 1);
    }

    private void exch(int[] a, int s, int e) {
        while (s < e) {
            swap(a, s, e);
            s++;
            e--;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}