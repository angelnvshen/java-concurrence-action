package own.leetcode.list;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

public class ReserveList {

  /**
   * 1: list 反序
   *
   * 5 -> 4 -> 3 -> 2 -> 1
   *
   * 1 -> 2 -> 3 -> 4 -> 5
   */
  public static Node reverseList(Node head) {
    Node newHead = null; // 指向新list的头节点

    while (head != null) {

      Node head_next = head.next;

      head.next = newHead;
      newHead = head;

      head = head_next;
    }

    return newHead;
  }

  /**
   * 2: 部分反序list
   */
  public static Node reverseListBetween(Node head, int m, int n) {
    int change_len = n - m + 1; // 计算需要逆置的节点个数
    Node pre_head = null; // 初始化开始逆置节点的前驱
    Node result = head; // 最终转换后链表的头结点，非特殊情况下未head
    while (head != null && --m > 0) { // 将head 移动m-1个位置
      pre_head = head; // 记录head的前驱
      head = head.next;
    }
    Node modify_list_tail = head; // 逆置后的链表尾
    Node newHead = null; // 指向新list的头节点
    while (head != null && change_len > 0) { // 逆置 change_len 个节点
      Node head_next = head.next;
      head.next = newHead;
      newHead = head;
      head = head_next;
      change_len--;
    }
    if (head != null) {
      modify_list_tail.next = head; //连接逆置后的尾节点与逆置端的后一个节点
    }

    if (pre_head != null) { // pre_head不为空，说明不是从第一个节点开始逆置 ， m>1
      pre_head.next = newHead;
    } else {
      result = newHead;
    }

    return result;
  }

  /**
   * 3: 求出相交节点 - 1
   */
  public static Node intersectionOfTwoList_sec(Node first, Node second) {

    int len_first = length(first);
    int len_second = length(second);
    if (len_first > len_second) {
      first = forwardLongList(len_first, len_second, first);
    }

    if (len_second > len_first) {
      first = forwardLongList(len_second, len_first, second);
    }

    while (first != null && second != null) {
      if (first.val == second.val) {
        return first;
      }

      first = first.next;
      second = second.next;
    }

    return null;
  }

  /**
   * 3: 求出相交节点 - 2
   */
  public static Node intersectionOfTwoList(Node first, Node second) {

    Set<Node> nodeSet = new HashSet<>();
    while (first != null) {
      nodeSet.add(first);
      first = first.next;
    }

    while (second != null) {
      if (nodeSet.contains(second)) {
        return second;
      }
      second = second.next;
    }
    return null;
  }

  /**
   * 4: 检查链表是否有环，如果有，返回环的节点值 - 1
   *
   * set集合
   */
  public static Node detectCycle(Node node) {

    Set<Node> nodeSet = new HashSet<>();
    while (node != null) {
      if (nodeSet.contains(node)) {
        return node;
      }
      nodeSet.add(node);
      node = node.next;
    }

    return null;
  }

  /**
   * 4: 检查链表是否有环，如果有，返回环的节点值 - 2
   *
   * 快慢指针
   */
  public static Node detectCycle_sec(Node head) {

    Node slow = head;
    Node fast = head;
    Node meet = null;

    while (fast != null) {
      slow = slow.next;
      fast = fast.next;

      if (fast == null) {
        return null; // fast遇见list节点尾
      }

      fast = fast.next;
      if (slow == fast) {
        meet = fast;
        break;
      }
    }

    if (meet == null) {
      return null;
    }

    while (meet != null && head != null) {
      if (meet == head) {
        return head;
      }
      head = head.next;
      meet = meet.next;
    }
    return null;
  }


  /**
   * 4: 链表切分
   *
   * (根据3) 1 4 6 5 2 3 -> 1 2 3 4 6 5
   */
  public static Node partition(Node head, int x) {

    Node lessList = new Node(-1);
    Node moreList = new Node(-1);

    Node less_p = lessList;
    Node more_p = moreList;
    while (head != null) {
      if (head.val <= x) {
        less_p.next = head;
        less_p = head;
      } else {
        more_p.next = head;
        more_p = head;
      }
      head = head.next;
    }
    less_p.next = moreList.next;
    more_p.next = null;

    return lessList.next;
  }

  public static void print(Node head) {
    while (head != null) {
      System.out.print(head.val + " -> ");
      head = head.next;
    }
    System.out.println();
  }

  public static int length(Node node) {
    int len = 0;
    while (node != null) {
      len++;
      node = node.next;
    }
    return len;
  }

  public static Node forwardLongList(int longLen, int shortLen, Node node) {

    int delta = longLen - shortLen;
    while (node != null && delta > 0) { // 将指正向前移动至多出节点个数后的位置
      node = node.next;
      delta--;
    }

    return node;
  }

  public static void main2(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);

    node6.next = node5;
    node5.next = node4;
    node4.next = node3;
    node3.next = node2;
    node2.next = node1;
    node1.next = null;

//    Node head = node6;
//    print(head);

//    Node newHead = reverseList(head);
//    print(newHead);

//    Node reverseListBetween = reverseListBetween(head, 2, 4);
//    print(reverseListBetween);

    Node node8 = new Node(8);
    Node node7 = new Node(7);

    node8.next = node7;
    node7.next = node3;

//    print(node6);
//    print(node8);

//    Node node = intersectionOfTwoList(node6, node8);
//    Node node = intersectionOfTwoList_sec(node6, node8);
//    print(node);

    Node node9 = new Node(9);
    node9.next = node8;
    node1.next = node7;
//    Node detectCycle = detectCycle(node9);
    Node detectCycle = detectCycle_sec(node9);
    System.out.println(detectCycle != null ? detectCycle.val : "null");

  }

  public static void main(String[] args) {

    // 1 4 6 5 2 3
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);

    node1.next = node4;
    node4.next = node6;
    node6.next = node5;
    node5.next = node2;
    node2.next = node3;

    print(partition(node1, 3));
  }
  // @Data
  static class Node {

    int val;
    Node next;

    public Node(int val) {
      this.val = val;
      next = null;
    }
  }
}
