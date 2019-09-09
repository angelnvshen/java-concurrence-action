package own.leetcode.binaryTreeAndGraph;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree extends BinaryTree {


    public static void BST_insert(BinaryTree.TreeNode node, BinaryTree.TreeNode insertNode) {
        if (node.value > insertNode.value) {
            if (node.left != null) {
                BST_insert(node.left, insertNode);
            } else {
                node.left = insertNode;
            }
        } else {
            if (node.right != null) {
                BST_insert(node.right, insertNode);
            } else {
                node.right = insertNode;
            }
        }
    }

    public static void main(String[] args) {
        List<TreeNode> nodes = new ArrayList<>();
        int test[] = new int[]{8, 3, 10, 1, 6, 15};
        for (int i = 0; i < test.length; i++) {
            nodes.add(new TreeNode(test[i]));
        }
        for (int i = 1; i < nodes.size(); i++) {
            BST_insert(nodes.get(0), nodes.get(i));
        }
        printTree(nodes.get(0), 0);
    }
}
