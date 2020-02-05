package own.leetcode;

public class Root2LeaveSumSolution {

    private int result = 0;

    public int sumNumbers(TreeNode root) {
    
        if(root == null){
            return result;
        }

        sumNumbers(root, 0);
        
        return result;
    }    

    private void sumNumbers(TreeNode node, int parentSum){
        if(node == null){
            return;
        }
        
        int cur = parentSum * 10 + node.val;
        
        if(node.left == null && node.right == null){
            result += cur;
            return;
        }

        if(node.left != null && node.right == null){
//            result += cur;
            sumNumbers(node.left, cur);
            return;
        }

        if(node.left == null && node.right != null){
//            result += cur;
            sumNumbers(node.right, cur);
            return;
        }
        
        sumNumbers(node.left, cur);
        sumNumbers(node.right, cur); 
    }

    public static void main(String[] args) {

        Codec codec = new Codec();
        TreeNode treeNode = codec.deserialize("4,9,0,#,1");

        Root2LeaveSumSolution solution = new Root2LeaveSumSolution();
        System.out.println(solution.sumNumbers(treeNode));
    }
}