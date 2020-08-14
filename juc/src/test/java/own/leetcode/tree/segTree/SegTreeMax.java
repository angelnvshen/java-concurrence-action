package own.leetcode.tree.segTree;

import java.util.LinkedList;
import java.util.Queue;

public class SegTreeMax {


    private int[] arr;
    private int size = 0;

    private SegmentTreeNode root;

    public SegTreeMax(int[] arr) {
        this.arr = arr;
        this.size = arr.length;

        root = buildTree(0, size - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 3};
//        int[] arr = {10, 1, 2, 5, 1, 9, 3};
        SegTreeMax max = new SegTreeMax(arr);

        print(max.root);

//        System.out.println(max.query(max.root, 1, 5));


        max.modify(max.root, 2, 5);
        print(max.root);

    }

    public void modify(SegmentTreeNode root, int index, int value) {

        if (root.start > index || root.end < index) {
            return;
        }

        if (root.start == root.end) {
            arr[index] = value;
            root.max = value;
            return;
        }

        int mid = (root.start + root.end) >> 1;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }

        root.max = Math.max(root.right == null ? 0 : root.right.max, root.left == null ? 0 : root.left.max);
    }

    public int query(SegmentTreeNode root, int start, int end) {
        System.out.println(root.start + ", " + root.end);
        if (start > end) return 0;
        if (start > root.end || end < root.start) return 0;
        if (start <= root.start && root.end <= end) {
            return root.max;
        }

        int mid = (root.start + root.end) >> 1;
        if (end <= mid) {
            return query(root.left, start, end);
        }
        if (start > mid) {
            return query(root.right, start, end);
        }

        int leftNodeMax = query(root.left, start, end);

        int rightNodeMax = query(root.right, start, end);

        return Math.max(leftNodeMax, rightNodeMax);
    }

    private SegmentTreeNode buildTree(int start, int end) {
        if (start > end) return null;

        if (start == end) {
            return new SegmentTreeNode(start, end, arr[start]);
        }

        int mid = (start + end) >> 1;
        SegmentTreeNode leftNode = buildTree(start, mid);
        SegmentTreeNode rightNode = buildTree(mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.max(leftNode.max, rightNode.max));
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }


    static class SegmentTreeNode {

        public int start, end;

        public int max;

        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + ", max = " + max + ']';
        }
    }

    public static void print(SegmentTreeNode root) {
        if (root == null) return;

        Queue<SegmentTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                SegmentTreeNode node = queue.poll();
                System.out.print(node + " ,");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println();
        }
    }

}
