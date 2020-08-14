package own.leetcode.stack;

import java.util.*;

public class DinnerPlates {

    public static void main(String[] args) {

        //["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
        //[[2],           [1],   [2],    [3],   [4],   [7],   [8],        [20],   [21],  [0],         [2],        [],[],[],[],[]]
        DinnerPlates D = new DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
        D.push(1);
        D.push(2);
        D.push(3);
        D.push(4);
        D.push(7);
        D.push(8);

        D.popAtStack(8);

        D.push(20);
        D.push(21);

        D.popAtStack(0);
        D.popAtStack(2);

        D.pop();
        D.pop();
        D.pop();
        D.pop();
    }

    public static void main_2(String[] args) {
        DinnerPlates D = new DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
        D.push(1);
        D.push(2);
        D.push(3);
        D.push(4);
        D.push(5);              // 栈的现状为：    2  4
//                                                    1  3  5
//                                                    ﹈ ﹈ ﹈
        D.popAtStack(0);      // 返回 2。栈的现状为：      4
//                                                          1  3  5
//                                                          ﹈ ﹈ ﹈
        D.push(20);             // 栈的现状为：  20  4
//                                                   1  3  5
//                                                  ﹈ ﹈ ﹈
        D.push(21);             // 栈的现状为：  20  4 21
//                                                  1  3  5
//                                                  ﹈ ﹈ ﹈
        D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
//                                                         1  3  5
//                                                         ﹈ ﹈ ﹈
//        D.popAtStack(2);   // 返回 21。栈的现状为：            4
//                                                         1  3  5
//                                                         ﹈ ﹈ ﹈
        D.pop();                  // 返回 5。栈的现状为：        4
//                                                          1  3
//                                                          ﹈ ﹈
        D.pop();           // 返回 4。栈的现状为：      1  3
//                                                   ﹈ ﹈
        D.pop();            // 返回 3。栈的现状为：    1
//                                                  ﹈
        D.pop();            // 返回 1。现在没有栈。
        D.pop();            // 返回 -1。仍然没有栈。

    }

    /*
    因为push是从左到右
    pop可以指定位置，如果是从非最后一个非空的栈中pop元素，中间会空出很多空栈，我们要记住，而且下次push要从最小的非满栈开始push
    */
    private int capacity;
    private TreeSet<Integer> indexSet;//栈索引（只保存非满栈）
    private List<Deque<Integer>> list; //栈列表

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
        indexSet = new TreeSet<>();
        list = new ArrayList<>();
    }

    public void push(int val) {
        // 找到一个非满栈的下标
        Deque<Integer> stack = null;
        Integer index = null;
        if (list.size() == 0 || indexSet.size() == 0) {

            if (list.size() == 0)
                index = 0;
            else
                index = list.size();

            stack = new LinkedList<>();
            list.add(stack);

        } else {
            index = indexSet.first();
            stack = list.get(index);
        }

        stack.push(val);
        if (stack.size() == capacity) {// 栈已满
            indexSet.remove(index);
        } else {
            indexSet.add(index); // 栈未满
        }
    }

    public int pop() {
        return popAtStack(list.size() - 1);
    }

    public int popAtStack(int index) {
        Deque<Integer> stack = null;
        if (index == -1 || index > list.size() - 1 || (stack = list.get(index)).isEmpty()) {
            return -1;
        }
        int result = stack.pop();
        if (!stack.isEmpty()) {
            indexSet.add(index);
        } else {
            if (index == list.size() - 1) {
                ListIterator<Deque<Integer>> iterator = list.listIterator(list.size());
                while (iterator.hasPrevious()) {
                    stack = iterator.previous();
                    if (stack.size() == 0) {
                        iterator.remove();
                        indexSet.remove(index--);
                    } else {
                        break;
                    }
                }
            } else {
                indexSet.add(index);
            }
        }

        return result;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */