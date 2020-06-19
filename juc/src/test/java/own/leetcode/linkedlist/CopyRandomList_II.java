package own.leetcode.linkedlist;

/*
// Definition for a Node.
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
*/
public class CopyRandomList_II {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        node.next = node2;
        node.random = node2;

        node2.random = node2;

        CopyRandomList_II randomList = new CopyRandomList_II();
        randomList.copyRandomList(node);
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        copyNode(head);
        copyRandom(head);
        Node newHead = split(head);
        head.val = 10;

        System.out.println(newHead.val);
        print(newHead);
        return newHead;
    }

    private Node split(Node head) {
        Node newHead = new Node(0);

        Node cur = head;
        Node newCur = newHead;
        while (cur != null) {
            Node temp = cur.next.next;

            newCur.next = cur.next;
            newCur = newCur.next;

            cur = temp;
        }

        return newHead.next;
    }

    private void copyRandom(Node head) {
        Node cur = head;

        while (cur != null) {
            if (cur.random != null && cur.random.next != null) {
                // System.out.print(cur.random.next.val + ",  cur : " + cur.val + "  | ");
                cur.next.random = new Node(cur.random.next.val);
            }
            cur = cur.next.next;
        }
    }

    private void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.val + "  ");
            cur = cur.next;
        }
    }

    private void copyNode(Node head) {
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);

            Node next = cur.next;
            cur.next = newNode;
            newNode.next = next;

            cur = next;
        }
    }
}