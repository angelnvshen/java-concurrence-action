package own.leetcode;

public class MaxPathSumInBST {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        helper(root);
        return ans;
    }

    private int helper(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));

        ans = Math.max(ans, left + right + root.val);
        int rootSum = Math.max(left, right) + root.val;
        System.out.println(left + ", " + right + "," + ans + ", " +  rootSum);
        return rootSum;
        // return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(20);
        treeNode.left = new TreeNode(15);
        treeNode.right = new TreeNode(7);

        MaxPathSumInBST maxPathSumInBST = new MaxPathSumInBST();
        System.out.println(maxPathSumInBST.maxPathSum(treeNode));
    }
}
