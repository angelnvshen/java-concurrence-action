package own.leetcode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class FlattenSolution {

    public static void main(String[] args) {

        Codec codec = new Codec();
        TreeNode treeNode = codec.deserialize("1,2,5,3,4,#,6");

        FlattenSolution solution = new FlattenSolution();
        solution.flatten(treeNode);
    }

    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        
        flattenToList(root);
    }

    // 分治
    
    ReturnType flattenToList(TreeNode node){

        if(node != null){
            System.out.println(node.val);
        }

        if(node == null ||
            (node.left == null && node.right == null)){

            return new ReturnType(node, node);
        }
        
        ReturnType left = null;
        ReturnType right = null;

        if(node.left != null && node.right == null){
            left = flattenToList(node.left);
            node.left = null;
            node.right = left.leftLast;
            return new ReturnType(node, left.rightLast);
        }
        if(node.left == null && node.right != null){
            right = flattenToList(node.right);
            node.right = right.leftLast;
            return new ReturnType(node, right.rightLast);
        }
        // left != null && right != null
        left = flattenToList(node.left);
        right = flattenToList(node.right);
        
        node.left = null;
        node.right = left.leftLast;
        left.rightLast.right = right.leftLast;

        return new ReturnType(node, right.rightLast);
    }


    // 以某个节点为根的子树，展开为列表后，最左的节点和最后的节点
    class ReturnType{

        TreeNode leftLast;
        TreeNode rightLast;

        ReturnType(TreeNode leftLast, TreeNode rightLast){
            this.leftLast = leftLast;
            this.rightLast = rightLast;
        }
    }
}