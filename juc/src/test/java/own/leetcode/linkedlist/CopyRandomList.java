package own.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyRandomList {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        node.next = node2;
        node.random = node2;

        node2.random = node2;

        CopyRandomList randomList = new CopyRandomList();
        randomList.copyRandomList(node);
    }

    public Node copyRandomList(Node head) {
        // write your code here
        if (head == null) {
            return head;
        }

        // 确定 节点位置和地址的关系
        Map<Node, Integer> oldIndexMap = new HashMap<>();
        Node cur = head;

        Node temp = null;

        int index = 0;
        while (cur != null) {
            temp = cur.next;
            cur.next = null;

            oldIndexMap.put(cur, index++);

            cur = temp;
        }

        Node newHead = new Node(-1);
        Node newCur = newHead;
        // 
        Map<Integer, Node> newIndexMap = new HashMap<>();
        cur = head;
        while (cur != null) {

            Node newNode = new Node(cur.val);
            newIndexMap.put(oldIndexMap.get(cur), newNode);

            newCur.next = newNode;

            newCur = newCur.next;
            cur = cur.next;
        }

        cur = head;
        newCur = newHead.next;
        while (cur != null) {
            newCur.random = newIndexMap.get(oldIndexMap.get(cur));
            newCur = newCur.next;
            cur = cur.next;
        }
        return newHead.next;
    }
}