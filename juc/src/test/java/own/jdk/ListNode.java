package own.jdk;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode next(ListNode next) {
        this.next = next;
        return next;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }
}