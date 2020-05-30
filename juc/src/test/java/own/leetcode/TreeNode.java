package own.leetcode;

public class TreeNode {

    private static StringGeneratorTree cod = new StringGeneratorTree();

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode geneNode(String node) {
        return cod.deserialize(node);
    }
}