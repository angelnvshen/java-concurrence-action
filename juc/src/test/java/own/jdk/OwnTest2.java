package own.jdk;

import org.junit.Test;
import own.leetcode.TreeNode;

import java.util.*;

public class OwnTest2 {

    @Test
    public void test1() {

//        System.out.println(countUnivalSubtrees(TreeNode.geneNode('1,2,3,4,5')));
        System.out.println(countUnivalSubtrees(TreeNode.geneNode("5,1,5,5,5,#,5")));
//        System.out.println(countUnivalSubtrees(TreeNode.geneNode("5,#,5")));
    }

    public static int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        return help(root).count;
    }

    static class Node {
        TreeNode node;
        boolean isUni;
        int count;
        int val; // 相同的值

        Node(TreeNode node, boolean isUni, int count, int val) {
            this.node = node;
            this.isUni = isUni;
            this.count = count;
            this.val = val;
        }
    }

    private static Node help(TreeNode node) {
        if (node == null) {
            return new Node(null, false, 0, 0);
        }
        if (node.left == null && node.right == null) {
            return new Node(node, true, 1, node.val);
        }
        Node left = help(node.left);
        Node right = help(node.right);
        int count = 0;
        boolean isUni = false;
        if (node.left != null && node.right == null) {

        }

        if (left.isUni || right.isUni) {

            isUni = left.isUni && right.isUni && node.val == left.val && node.val == right.val;
            if (node.val == left.val && node.val == right.val) {
                count = 1 + left.count + right.count;
            } else {
                count = left.count + right.count;
            }
        } else {
            count = left.count + right.count;
        }

        return new Node(node, isUni, count, node.val);
    }

    @Test
    public void test3() {
//        System.out.println(hasPathSum(TreeNode.geneNode("1,2"), 1));
//        System.out.println(hasPathSum(TreeNode.geneNode("5,4,8,11,#,13,4,7,2,#,#,#,1"), 22));
        System.out.println(hasPathSum(TreeNode.geneNode(""), 0));
    }


    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return help(root, sum);
    }

    public static boolean help(TreeNode root, int sum) {

        if (root == null) return false;
        if (root.left == null && root.right == null) {
            if (sum == root.val) return true;
            else return false;
        }

        Boolean left = null;
        if (root.left != null) {
            left = help(root.left, sum - root.val);
        }

        Boolean right = null;
        ;
        if (root.right != null) {
            right = help(root.right, sum - root.val);
        }


        if (left != null && left) {
            return left;
        }

        if (right != null && right) {
            return right;
        }

        return false;
    }

    @Test
    public void test4() {
//        System.out.println(minimumSize(new int[]{2, 3, 1, 2}, 7));
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
        System.out.println(longestOnes(new int[]{1, 0, 0, 0, 1, 1, 0}, 2));
        System.out.println(longestOnes(new int[]{0, 0, 0, 1}, 4));
    }

    public static int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) return -1;

        int r = 0, n = nums.length, ans = n + 1, sum = 0;

        for (int i = 0; i < n; i++) {
            while (r < n && sum < s) {
                sum += nums[r];
                r++;
            }

            if (sum >= s) {
                ans = Math.min(ans, r - i);
                sum -= nums[i];
            }
        }

        return ans > n ? -1 : ans;
    }

    public static String minWindow(String s, String t) {

        if (s == null || s.length() == 0) return "";
        if (s.length() < t.length()) return "";

        // 双指针
        int r = 0, count = 0, left = 0, right = 0;
        int n = s.length(), m = t.length(), ans = n + 1;

        // char : count
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        char c = ' ';
        for (int l = 0; l < n; l++) {
            while (r < n && count < m) {
                c = s.charAt(r);
                map.put(c, map.getOrDefault(c, 0) - 1);
                if (map.get(c) >= 0) {
                    count += 1;
                }
                r++;
            }

            if (count >= m) {
                if (ans > r - l) {
                    ans = r - l;
                    left = l;
                    right = r;
                }
                c = s.charAt(l);
                map.put(c, map.get(c) + 1);
                if (map.get(c) > 0) {
                    count -= 1;
                }
            }
        }

        return s.substring(left, right);
    }

    public static int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        if (A.length < K || K < 0) return 0;

        int count = 0, r = 0, n = A.length, ans = 0;

        for (int l = 0; l < n; l++) {
            while (r < n) {
                if (A[r] == 0) {
                    if (count == K) {
                        break;
                    } else {
                        count += 1;
                        r++;
                    }

                } else {
                    r++;
                }
            }

            ans = Math.max(ans, r - l);
            if (A[l] == 0) {
                count -= 1;
            }
        }

        return ans;
    }

    @Test
    public void test5() {
//        System.out.println(totalFruit(new int[]{0,1,2,2}));
//        System.out.println(totalFruit(new int[]{1,2,3,2,2}));
//        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
        Integer[] integers = {1, 2};
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList(integers)));

        Integer[] temp = null;


        char c = (char) (1 + 48);
        System.out.println(c);
    }

    public static int totalFruit(int[] tree) {
        //理解为求只包含两种元素的最长连续子序列

        if (tree == null || tree.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();

        int n = tree.length, k = 2;
        int ans = 0, count = 0, r = 0, temp = 0;

        // 双指针
        for (int l = 0; l < n; l++) {
            while (r < n && count <= k) {
                temp = tree[r];
                if (map.getOrDefault(temp, 0) == 0) {
                    if (count == k) {
                        break;
                    }
                }
                map.put(temp, map.getOrDefault(temp, 0) + 1);
                if (map.get(temp) == 1) {
                    count += 1;
                }
                r++;
            }

            ans = Math.max(r - l, ans);
            map.put(tree[l], map.get(tree[l]) - 1);
            if (map.get(tree[l]) == 0) {
                count -= 1;
            }
        }

        return ans;
    }

    @Test
    public void test6() {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compress(new char[]{'a', 'a', 'a', 'b', 'b', 'a', 'a'}));
        System.out.println(compress(new char[]{'a'}));
    }

    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }

        int index = 0;
        int pre = 0, length = 0;
        int n = chars.length;

        // 双指针基础
        for (int i = 1; i < n; i++) {
            if (chars[i] != chars[i - 1]) {
                length = i - pre;
                chars[index++] = chars[i - 1];

                if (length > 1) {
                    if (length < 9) {
                        chars[index++] = (char) (length + 48);
                    } else {
                        chars[index++] = (char) (length / 10 + 48);
                        chars[index++] = (char) (length % 10 + 48);
                    }
                }

                pre = i;
            }
        }

        length = n - pre;
        chars[index++] = chars[n - 1];
        if (length > 1) {
            if (length < 9) {
                chars[index++] = (char) (length + 48);
            } else {
                chars[index++] = (char) (length / 10 + 48);
                chars[index++] = (char) (length % 10 + 48);
            }
        }
        return index;
    }
}
