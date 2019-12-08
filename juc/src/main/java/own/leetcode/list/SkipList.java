package own.leetcode.list;

import lombok.Data;

import static own.leetcode.list.SkipList.NodeType.DATA;

public class SkipList<T extends Comparable> implements IList<T> {

    @Data
    class Node<T> {
        T value;
        Node left, right, down, up;
        byte type;

        public Node(T value, byte type) {
            this.value = value;
            this.type = type;
        }
    }

    Node<T> head = null;
    Node<T> tail = null;

    public SkipList() {

        Node<T> head = new Node<>(null, NodeType.HEAD.value);
        Node<T> tail = new Node<>(null, NodeType.TAIL.value);
        head.right = tail;
        tail.left = head;
    }

    enum NodeType {
        HEAD((byte) 1),
        DATA((byte) 0),
        TAIL((byte) -1),
        ;

        byte value;

        NodeType(byte i) {
            value = i;
        }
    }

    @Override
    public void add(T t) {
        Node<T> node = new Node<>(t, DATA.value);
        if (head.right == tail) {
            head.right = node;
            tail.left = node;

            node.left = head;
            node.right = tail;
        } else {
            Node temp = head.right;
            while (temp != tail){

            }
        }
    }

    @Override
    public T get(T t) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
