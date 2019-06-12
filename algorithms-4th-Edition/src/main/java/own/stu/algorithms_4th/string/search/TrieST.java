package own.stu.algorithms_4th.string.search;

import edu.princeton.cs.algs4.Queue;

public class TrieST<T> {

  private static int R = 256; // 基数

  private Node root; // 单词查找树的根节点 root

  private static class Node<T> {

    private T val;

    private Node[] next = new Node[R];
  }

  public T get(String key) {
    Node node = get(root, key, 0);
    if (node == null) {
      return null;
    }
    return (T) node.val;
  }

  private Node get(Node x, String key, int d) {

    if (x == null) {
      return null;
    }
    if (d == key.length()) {
      return x;
    }
    Node node = x.next[key.charAt(d)]; // 找到第d个字符所对应的子单词查找树
    return get(node, key, d + 1);
  }

  public void put(String key, String value) {

    root = put(root, key, value, 0);
  }

  private Node put(Node x, String key, String value, int d) {
    if (x == null) {
      x = new Node();
    }
    if (d == key.length()) {
      x.val = value;
      return x;
    }
    char c = key.charAt(d);
    x.next[c] = put(x.next[c], key, value, d + 1);
    return x;
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  public Iterable<String> keysWithPrefix(String pre) {

    Queue<String> queue = new Queue<>();
    collect(queue, root, pre);
    return queue;
  }

  private void collect(Queue<String> queue, Node<T> node, String pre) {
    if (node == null) {
      return;
    }

    if (node.val != null) {
      queue.enqueue(pre);
    }

    for (char c = 0; c < R; c++) {
      collect(queue, node.next[c], pre + c);
    }
  }
}
