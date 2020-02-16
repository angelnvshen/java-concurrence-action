package own;

import lombok.Data;

@Data
public class TreeNode<T> {
    public T val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(T x) {
        val = x;
    }
}