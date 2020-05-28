package own.leetcode.tree;

import own.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

    public static void main(String[] args) {

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(8);

        node.left.left = new TreeNode(7);

        node.right.left = new TreeNode(1);
        node.right.right = new TreeNode(3);


        PathSum sum = new PathSum();
        sum.pathSum(node, 16);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) return ans;

        helper(root, sum, ans, new ArrayList<>());
        return ans;
    }

    private void helper(TreeNode root, int sum,
                        List<List<Integer>> ans, List<Integer> result) {
        System.out.println(root.val + " , " + sum);
        if (root.left == null && root.right == null) {
            if (sum != root.val) {
                return;
            }
            result.add(root.val);
            ans.add(new ArrayList<>(result));
            // 回溯
            result.remove(result.size() - 1);
            return;
        }
        result.add(root.val);
        helper(root.left, sum - root.val, ans, result);
        if (root.right != null)
            helper(root.right, sum - root.val, ans, result);
        result.remove(result.size() - 1);
    }
}