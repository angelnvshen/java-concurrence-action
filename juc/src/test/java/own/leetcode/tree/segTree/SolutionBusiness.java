package own.leetcode.tree.segTree;

import own.Printer;

public class SolutionBusiness {

    public static void main(String[] args) {
        SolutionBusiness solution = new SolutionBusiness();
        Printer.print(solution.business(new int[]{1, 3, 2, 1, 5}, 2));
    }

    /**
     * @param A: The prices [i]
     * @param k:
     * @return: The ans array
     */
    public int[] business(int[] A, int k) {
        if (A == null || A.length == 0) {
            return new int[0];
        }

        int n = A.length;
        int[] ans = new int[n];
        SegTree tree = new SegTree(A);
        Printer.print(tree.tree);

        for (int i = 0; i < n; i++) {
            int l = i - k < 0 ? 0 : i - k;
            int r = i + k >= n ? n - 1 : i + k;

            ans[i] = A[i] - tree.query(l, r);
        }
        return ans;
    }

    static class SegTree {

        int[] arr;
        int[] tree;
        int size;

        public SegTree(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            this.tree = new int[4 * size];
            buildTree(0, 0, size - 1);
        }

        private void buildTree(int node, int start, int end) {

            if (start > end) {
                return;
            }

            if (start == end) {
                tree[node] = arr[start];
                return;
            }

            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            int mid = (start + end) >> 1;
            buildTree(leftNode, start, mid);
            buildTree(rightNode, mid + 1, end);

            tree[node] = Math.min(tree[leftNode], tree[rightNode]);
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
            if (start == end) {
                return tree[node];
            }

            int mid = (start + end) >> 1;
            int leftNode = 2 * node + 1;
            int rightNode = 2 * node + 2;
            if (R <= mid) {
                return query(leftNode, start, mid, L, R);
            }
            if (mid < L) {
                return query(rightNode, mid + 1, end, L, R);
            }

            int leftNodeSum = query(leftNode, start, mid, L, R);
            int rightNodeSum = query(rightNode, mid + 1, end, L, R);

            return Math.min(leftNodeSum, rightNodeSum);
        }
    }
}