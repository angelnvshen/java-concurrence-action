package own.stu.com.redis.dataStructure.list;

/**
 * 无环
 * 双端链表结构
 */
public class List {

  ListNode head;

  ListNode tail;

  void dup() {

  }

  void free() {

  }

  int match() {
    return 0;
  }

  Integer len;

  /**
   * 节点
   */
  public static class ListNode {

    // 前置节点
    ListNode prev;

    // 后置节点
    ListNode next;

    // 节点的值
    Object value;
  }
}