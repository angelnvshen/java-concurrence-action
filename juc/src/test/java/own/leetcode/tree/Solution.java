package own.leetcode.tree;

import own.leetcode.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val < val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.left;
        if (leftNode == null) {
            root.left = insertIntoMaxTree(root.left, val);
        } else if (rightNode == null) {
            root.right = insertIntoMaxTree(root.right, val);
        } else if (leftNode.val > val) {
            root.left = insertIntoMaxTree(root.left, val);
        } else {
            root.right = insertIntoMaxTree(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.braceExpansionII("{a,b}{c,{d,e}}"));
    }

    public List<String> braceExpansionII(String expression) {
        List<String> ans = new ArrayList<>();
        if (expression == null || expression.length() == 0) {
            return ans;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(expression);

        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            String str = queue.poll();

            if (str.indexOf("{") < 0) {
                result.add(str);
                continue;
            }

            // 找到表达式中第一对 {}
            int i = 0;
            int left = 0;
            int right = 0;
            while (str.charAt(i) != '}') {
                if (str.charAt(i) == '{') left = i;
                i++;
            }
            right = i;


            // 拿到第一对括号中的前面部分 (不包括 {)
            String post = str.substring(0, left);
            // 拿到第一对括号中的后面部分 (不包括 })
            String after = str.substring(right + 1);

            // 按照 , 分割第一对括号中的元素 (不包括 {})
            String[] strArr = str.substring(left + 1, right).split(",");
            for(String s : strArr){
                sb.setLength(0);
                queue.add(sb.append(post).append(s).append(after).toString());
            }
        }

        // 结果处理
        ans.addAll(result);
        Collections.sort(ans);

        return ans;
    }
}