package own.stu.algorithms_4th.fundamentals.basicProgrammingModel.exercise;

public class MoveToFront<Item> {
    private class Node<Item>{
        Item item;
        Node next;
    }

    private Node first;

    public boolean isEmpty(){
        return first == null;
    }

    public void add(Item item){
        Node node = new Node();
        node.item = item;

        if(first != null && first.item == item)
            return;

        for(Node cur = first; cur != null && cur.next != null ; cur = cur.next){
            if(cur.next.item == item){
                Node temp = cur.next;
                cur.next = cur.next.next;
                temp = null;
                break;
            }
        }

        node.next = first;
        first = node;
    }
}
