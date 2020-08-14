package own.leetcode.tree.segTree;

public class SegTree_i {

    //实现一个 build 方法，接受 start 和 end 作为参数, 然后构造一个代表区间 [start, end] 的线段树，返回这棵线段树的根。
    public SegmentTreeNode build(int start, int end) {

        if (start > end) return null;

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start == end) return root;

        int mid = (start + end) >> 1;

        root.left = build(start, mid);
        root.right = build(mid + 1, end);

        return root;
    }

    static class SegmentTreeNode {
        public int start, end;
        public SegmentTreeNode left, right;

        public SegmentTreeNode() {
        }

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = this.right = null;
        }
    }

}
