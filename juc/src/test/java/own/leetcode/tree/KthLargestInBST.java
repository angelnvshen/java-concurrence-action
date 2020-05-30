package own.leetcode.tree;

import own.leetcode.StringGeneratorTree;
import own.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class KthLargestInBST {

    public static void main(String[] args) {
        KthLargestInBST bst = new KthLargestInBST();

        TreeNode treeNode = StringGeneratorTree.deserialize("5,3,6,2,4,null,null,1", "null");

        System.out.println(bst.kthLargest(treeNode, 3));

        int[][] ans = new int[2][3];
        List<int[]> list = new ArrayList<>();
        list.toArray(ans);

    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) return -1;
        K = k;
        helper(root);
        return ans;
    }

    int ans = 0;
    int K = 0;
    int index = 0;

    private void helper(TreeNode root) {
        if (root == null) return;

        helper(root.right);
        index += 1;
        System.out.println(root.val + ", " + index);
        if(index > K) return;
        if (K == index) {
            ans = root.val;
            return;
        }
        helper(root.left);
    }
}