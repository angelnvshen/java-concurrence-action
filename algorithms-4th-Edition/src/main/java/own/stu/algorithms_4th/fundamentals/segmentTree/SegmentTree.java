package own.stu.algorithms_4th.fundamentals.segmentTree;

public class SegmentTree {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 7, 9, 11};
        int size = nums.length;
        int[] tree = new int[size * 4];
        build(nums, 0, size - 1, tree, 0);
        showTree(tree);
        update_node(nums, 0, size - 1, tree, 0, 4, 6);
        showTree(tree);

        System.out.println(query(tree, 2, 5, 0, size - 1, 0));
    }

    private static void showTree(int[] tree) {
        for (int i : tree) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static int query(int[] tree, int start, int end, int l, int r, int node) {
        if (r < start || l > end) {
            return 0;
        }
        if (start <= l && r <= end) {
            return tree[node];
        }
        int mid = (r - l) / 2 + l;
        int ans = 0;
        // 左分支上有需要查询的区间
        ans += query(tree, start, end, l, mid, node * 2 + 1);
        // 右分支上有需要查询的区间
        ans += query(tree, start, end, mid + 1, r, node * 2 + 2);
        return ans;
    }

    //更新nums中index下标的值，出发tree的更新
    private static void update_node(int[] nums, int l, int r, int[] tree, int node, int index, int value) {
        if (l == r) {
            nums[index] = value;
            tree[node] = value;
            return;
        }
        int mid = (r - l) / 2 + l;
        // 需要更新的节点在左分支上
        if (l <= index && mid >= index) {
            update_node(nums, l, mid, tree, node * 2 + 1, index, value);
        } else {
            update_node(nums, mid + 1, r, tree, node * 2 + 2, index, value);
        }
        update(tree, node);
    }

    private static void build(int[] nums, int l, int r, int[] tree, int node) {
        if (l == r) {
            // 叶子节点， left == right
            tree[node] = nums[l];
            return;
        }
        int mid = (r - l) / 2 + l;
        // parent节点的两个孩子节点是 2*node + 1, 2*node + 2, 类似于 heap
        build(nums, l, mid, tree, node * 2 + 1);
        build(nums, mid + 1, r, tree, node * 2 + 2);

        update(tree, node);
    }

    //如果节点保存的是和
    private static void update(int[] tree, int node) {
        tree[node] = tree[node * 2 + 1] + tree[2 * node + 2];
    }
}
