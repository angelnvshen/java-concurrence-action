package own.leetcode.binaryTreeAndGraph;

import java.util.*;

public class BinaryTree {

  // ================================= 深度遍历 =================================
  static void printTree(TreeNode tree, int layer) {

    if (tree == null) {
      return;
    }

//    for (int i = 0; i < layer; i++) {
//      System.out.print("---- ");
//    }
//    System.out.print(tree.value + "\n"); // preOrder

    printTree(tree.left, layer + 1);
    for (int i = 0; i < layer; i++) {
      System.out.print("---- ");
    }
     System.out.print(tree.value + "\n"); // midOrder
    printTree(tree.right, layer + 1);
//    for (int i = 0; i < layer; i++) {
//      System.out.print("---- ");
//    }
//    System.out.print(tree.value + "\n"); // postOrder
  }

  static List<List<Integer>> pathSum(TreeNode tree, int sum) {

    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> path = new Stack<>();
    int pathValue = 0;
    preOrder(tree, sum, path, pathValue, result);
    return result;
  }

  private static void preOrder(TreeNode tree, int sum, Stack<Integer> path, int pathValue, List<List<Integer>> result) {

    if (tree == null) {
      return;
    }

    path.push(tree.value);
    pathValue += tree.value;

    if (tree.right == null && tree.left == null && pathValue == sum) {
      result.add(new ArrayList<>(path));
    }
    preOrder(tree.left, sum, path, pathValue, result);
    preOrder(tree.right, sum, path, pathValue, result);

    path.pop();
    pathValue -= tree.value;
  }

  public static TreeNode lowestCommonAncestorOfABinaryTree(TreeNode root, TreeNode p, TreeNode g) {

    Stack<TreeNode> path = new Stack<>();
    List<TreeNode> result_p = new ArrayList<>();
    List<TreeNode> result_g = new ArrayList<>();
    getPathFromRoot(root, p, path, result_p, 0);
    path.clear();
    getPathFromRoot(root, g, path, result_g, 0);

    int path_len = 0; // 较小path路径的长度
    if (result_p.size() > result_g.size()) {
      path_len = result_g.size();
    } else {
      path_len = result_p.size();
    }

    TreeNode treeNode = null;
    for (int i = 0; i < path_len; i++) {
      if (Objects.equals(result_p.get(i), result_g.get(i))) {
        treeNode = result_p.get(i);
      }
    }

    return treeNode;
  }

  /**
   * 获取目标节点到指定节点的路径
   *
   * @param root 目标节点
   * @param search 指定节点
   * @param path 遍历时节点的路径栈
   * @param result 最终搜索到指定节点的路径
   * @param finish 是否找到指定节点的标记，找到为1
   */
  public static void getPathFromRoot(TreeNode root, TreeNode search, Stack<TreeNode> path, List<TreeNode> result,
      int finish) {

    if (root == null || finish == 1) {
      return;
    }

    path.push(root);
    if (search == root) {
      finish = 1;
      result.addAll(path);
    }
    getPathFromRoot(root.left, search, path, result, finish);
    getPathFromRoot(root.right, search, path, result, finish);

    path.pop();
  }

  public static void main_queue(String[] args) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1);
    queue.add(2);
    queue.add(3);
    System.out.println(queue);
    while (!queue.isEmpty()) {
      System.out.println(queue.remove());
    }
  }

  public static void main_sum(String[] args) {
    TreeNode a = new TreeNode(5);
    TreeNode b = new TreeNode(4);
    TreeNode c = new TreeNode(8);
    TreeNode d = new TreeNode(11);
    TreeNode e = new TreeNode(13);
    TreeNode f = new TreeNode(4);
    TreeNode g = new TreeNode(7);
    TreeNode h = new TreeNode(2);
    TreeNode i = new TreeNode(5);
    TreeNode j = new TreeNode(1);

    a.left = b;
    a.right = c;
    b.left = d;
    d.left = g;
    d.right = h;
    c.left = e;
    c.right = f;
    f.left = i;
    f.right = j;

//    System.out.println(pathSum(a, 22));

    TreeNode treeNode = lowestCommonAncestorOfABinaryTree(a, e, j);
    System.out.println(treeNode);
  }

  public static void flattenBinaryTreeToLinkedList(TreeNode tree) {

  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(1);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(3);
    TreeNode d = new TreeNode(4);
    TreeNode e = new TreeNode(5);
    TreeNode f = new TreeNode(6);

    a.left = b;
    a.right = e;

    b.left = c;
    b.right = d;

    e.right = f;

//    printTree(a, 0);
//    bfs_printTree(a);
    System.out.println(rightSideView(a));
  }

  // ================================= 层次遍历 =================================

  static void bfs_printTree(TreeNode treeNode) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(treeNode);
    while (!queue.isEmpty()) {
      TreeNode node = queue.remove();
      System.out.println(node.value + " -> ");
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }

  static List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();

    int size = 1; // 每一层的节点个数 ，第一层 为 1；
    int child;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {

      child = 0;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.remove();
        if (i == size - 1) {
          result.add(node.value);
        }
        if (node.left != null) {
          queue.add(node.left);
          child++;
        }
        if (node.right != null) {
          queue.add(node.right);
          child++;
        }
      }

      size = child;
    }

    return result;
  }

  public static class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
      this.value = value;
      left = null;
      right = null;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "value=" + value +
          '}';
    }
  }
}
