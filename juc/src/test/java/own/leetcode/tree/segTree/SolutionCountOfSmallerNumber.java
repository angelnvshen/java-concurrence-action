package own.leetcode.tree.segTree;

import own.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SolutionCountOfSmallerNumber {

    public static void main(String[] args) {
        SolutionCountOfSmallerNumber number = new SolutionCountOfSmallerNumber();
//        int[] a = new int[]{1, 2, 7, 8, 5};
        int[] a = new int[]{7, 8, 2, 1, 3};
        System.out.println(number.countOfSmallerNumberII(a));
    }

    /**
     * 249. 统计前面比自己小的数的个数
     * <p>
     * 给定一个整数数组（下标由 0 到 n-1， n 表示数组的规模，取值范围由 0 到10000）。
     * 对于数组中的每个 ai 元素，请计算 ai 前的数中比它小的元素的数量。
     *
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        if (A == null || A.length == 0) {
            return ans;
        }
//        Printer.print(A);
        discretzation(A);
//        Printer.print(A);

        SegTree tree = new SegTree(A);
        for (int i = 0; i < A.length; i++) {
            ans.add(tree.query(0, A[i] - 1));
            tree.modify(A[i], i);
            Printer.print(tree.tree);
        }

        return ans;
    }

    private void discretzation(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : arr) {
            set.add(i);
        }

        int[] deDup = new int[set.size()];
        int idx = 0;
        for (int i : set) {
            deDup[idx++] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = lowBound(deDup, arr[i]);
        }
    }

    private int lowBound(int[] arr, int target) {

        int l = 0;
        int r = arr.length - 1;
        int mid = 0;
        while (l + 1 < r) {
            mid = (r - l) / 2 + l;
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

    class SegTree {
        int[] arr;
        int[] tree;
        int size;

        public SegTree(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            this.tree = new int[4 * size];
        }

        // 初始化都是默认0，modify时再更新
        private void buildTree(int node, int start, int end) {

        }

        public int query(int L, int R) {
            return query(0, 0, size - 1, L, R);
        }

        private int query(int node, int start, int end, int L, int R) {

            if (R < start || end < L) {
                return 0;
            }

            if (L <= start && end <= L) {
                return tree[node];
            }

            if (start == end) {
                return tree[node];
            }

            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            int mid = (start + end) >> 1;

            if (mid < L) {
                return query(rightNode, mid + 1, end, L, R);
            }
            if (R <= mid) {
                return query(leftNode, start, mid, L, R);
            }

            int leftNodeCount = query(leftNode, start, mid, L, R);
            int rightNodeCount = query(rightNode, mid + 1, end, L, R);

            return leftNodeCount + rightNodeCount;
        }

        private void modify(int node, int start, int end, int index, int val) {

            if (start > end || index < start || end < index) return;

            if (start == end) {
//                arr[index] = val;
                tree[node] += 1;
                return;
            }

            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            int mid = (start + end) >> 1;

            if (start <= index && index <= mid) {
                modify(leftNode, start, mid, index, val);
            } else {
                modify(rightNode, mid + 1, end, index, val);
            }

            tree[node] = tree[leftNode] + tree[rightNode];
        }

        public void modify(int index, int val) {
            modify(0, 0, size - 1, index, val);
        }
    }

}