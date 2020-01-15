package own.leetcode.binaryTreeAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeII {

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * 69. 二叉树的层次遍历
     * <p>
     * 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）
     * <p>
     * 样例
     * <p>
     * 输入：{1,2,3}
     * 输出：[[1],[2,3]]
     * 解释：
     * 1
     * / \
     * 2   3
     * 它将被序列化为{1,2,3}
     * 层次遍历
     * <p>
     * 利用 队列 保存每一层的元素
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() != 0) {

            int cur_level_size = queue.size(); // 核心 保存当前层的数目
            List<Integer> cur_level_value = new ArrayList<>();

            for (int i = 0; i < cur_level_size; i++) {

                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }

                cur_level_value.add(poll.val);

                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }

            result.add(cur_level_value);
        }
        return result;
    }

    public static void main(String[] args) {
        // ========================================
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        levelOrder(treeNode).stream().forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + " "));
            System.out.println();
        });
    }
}
