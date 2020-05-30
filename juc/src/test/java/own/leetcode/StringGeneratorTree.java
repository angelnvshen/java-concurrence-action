package own.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
public class StringGeneratorTree {

    private static String NULL = "#";

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        List<String> dataList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        int cur_level_size = 0;
        while (list.size() > 0) {
            cur_level_size = list.size();
            for (int i = 0; i < cur_level_size; i++) {
                TreeNode node = list.pop();
                if (node == null) {
                    dataList.add(NULL);
                } else {
                    dataList.add(Integer.toString(node.val));
                    list.add(node.left);
                    list.add(node.right);
                }
            }
        }
        int index = dataList.size() - 1;
        while (dataList.size() > 0) {
            if (dataList.get(index).equals(NULL)) {
                index -= 1;
            } else {
                break;
            }
        }
        return String.join(",", dataList.subList(0, index + 1));
    }

    public static TreeNode deserialize(String data) {
        return deserialize(data, NULL);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data, String nullStr) {

        if (data == null || data.length() == 0) {
            return null;
        }
        System.out.println(data);
        String[] dataStrs = data.split(",");
        int n = dataStrs.length;
        if (n == 0) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(dataStrs));
        LinkedList<String> parents = new LinkedList<>();
        //将第一个节点加入到parents (第一个节点肯定是root节点)
        parents.add(nodes.pop());

        LinkedList<TreeNode> parentList = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(parents.peek()));
        parentList.add(root);

        TreeNode cur = null;

        // 1,2,3,#,#,4,5,#,#,#,#
        while (parentList.size() > 0) {
            int cur_level_size = parentList.size();
            for (int i = 0; i < cur_level_size; i++) {
                cur = parentList.pop();
                if (nodes.size() <= 0) {
                    continue;
                }
                String left = nodes.pop();
                if (!nullStr.equals(left)) {
                    cur.left = new TreeNode(Integer.valueOf(left));
                    parentList.add(cur.left);
                }

                if (nodes.size() <= 0) {
                    continue;
                }
                String right = nodes.pop();
                if (!nullStr.equals(right)) {
                    cur.right = new TreeNode(Integer.valueOf(right));
                    parentList.add(cur.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        StringGeneratorTree stringGeneratorTree = new StringGeneratorTree();
//        codec.deserialize(codec.serialize(root));
        String serialize = stringGeneratorTree.serialize(stringGeneratorTree.deserialize("1,2"));
        System.out.println(serialize);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));