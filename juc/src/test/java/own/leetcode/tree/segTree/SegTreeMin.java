package own.leetcode.tree.segTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition of Interval:
 * public classs Interval {
 * int start, end;
 * Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 * }
 */

public class SegTreeMin {


    public static void main(String[] args) {
        SegTreeMin min = new SegTreeMin();
        System.out.println(min.intervalMinNumber(
                new int[]{1, 2, 7, 8, 5},
                Arrays.asList(new Interval(1, 2), new Interval(0, 4), new Interval(2, 4))));
    }

    /**
     * @param A:       An integer array
     * @param queries: An query list
     * @return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        if (A == null || A.length == 0) {
            return ans;
        }

        SegTree tree = new SegTree(A);
        for (int i = 0; i < queries.size(); i++) {
            Interval interval = queries.get(i);
            if (interval.start > interval.end) {
                ans.add(null);
            } else {
                ans.add(tree.query(interval.start, interval.end));
            }
        }
        return ans;
    }

    static class SegTree {

        int[] arr;
        Integer[] tree;
        int size;

        public SegTree(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            this.tree = new Integer[4 * size];
            buildTree(0, 0, size - 1);
        }

        private void buildTree(int node, int start, int end) {
            if (start > end) return;

            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int mid = (start + end) >> 1;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            buildTree(leftNode, start, mid);
            buildTree(rightNode, mid + 1, end);

            tree[node] = Math.min(
                    tree[leftNode] == null ? Integer.MAX_VALUE : tree[leftNode],
                    tree[rightNode] == null ? Integer.MAX_VALUE : tree[rightNode]);
        }

        public int query(int L, int R) {
            return query(0, 0, size - 1, L, R);
        }

        private int query(int node, int start, int end, int L, int R) {

            if (L > end || R < start) {
                return 0;
            }

            if (L <= start && end <= R) {
                return tree[node];
            }

            if (start > end) return 0;
            int mid = (start + end) >> 1;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            if (R <= mid) {
                return query(leftNode, start, mid, L, R);
            }
            if (L > mid) {
                return query(rightNode, mid + 1, end, L, R);
            }

            int leftNodeMin = query(leftNode, start, mid, L, R);
            int rightNodeMin = query(rightNode, mid + 1, end, L, R);
            return Math.min(leftNodeMin, rightNodeMin);
        }
    }

    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}