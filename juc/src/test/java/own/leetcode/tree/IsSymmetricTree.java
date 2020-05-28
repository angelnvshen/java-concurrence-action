package own.leetcode.tree;

import own.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class IsSymmetricTree {

    public static void main(String[] args) {
        IsSymmetricTree tree = new IsSymmetricTree();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;

        left.left = new TreeNode(3);
        left.right = new TreeNode(4);
        right.left = new TreeNode(4);
        right.right = new TreeNode(3);

        System.out.println(tree.isSymmetric(root));
    }
    /**
     *
     * 二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        // Write your code here
        if(root == null) return true;

        List<Integer> pre = new ArrayList<>();
        preOrder(pre, root);

        List<Integer> post = new ArrayList<>();
        postOrder(post, root);

        int i = 0;
        int j = 0;
        while(i < pre.size() && j < post.size()){
            if(pre.get(i ++) == post.get(j ++)){
                continue;
            }
            return false;
        }
        return true;
    }

    private void preOrder(List<Integer> list, TreeNode root){
        if(root == null) return;

        preOrder(list, root.left);
        list.add(root.val);
        preOrder(list, root.right);
    }

    private void postOrder(List<Integer> list, TreeNode root){
        if(root == null) return ;

        postOrder(list, root.right);
        list.add(root.val);
        postOrder(list, root.left);
    }
}