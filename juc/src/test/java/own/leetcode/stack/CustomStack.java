package own.leetcode.stack;

import java.util.Stack;

public class CustomStack {

    public static void main(String[] args) {
        /*
        ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
        [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]

        ["CustomStack","pop","increment","push","increment","increment","increment","pop","increment"]
        [[30],[],[3,40],[30],[4,63],[2,79],[5,57],[],[5,32]]
        */
        CustomStack stack = new CustomStack(30);
        System.out.println(stack.pop());
        stack.increment(3, 40);

        stack.push(30);
        stack.increment(4, 63);
        stack.increment(2, 79);
        stack.increment(5, 57);

        System.out.println(stack.pop());
        stack.increment(5, 32);

    }

    private Stack<Integer> stack;
    private int[] inc;
    private int maxSize;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new Stack<>();
        inc = new int[maxSize];
    }

    public void push(int x) {
        if (maxSize == stack.size()) {
            // throw new RuntimeException("not supported");
            return;
        }
        stack.push(x);
    }

    public int pop() {
        int idx = stack.size();
        if (idx == 0) {
            return -1;
        }
        int increment = 0;
        if (idx == 1) {
            increment = inc[0];
            inc[0] = 0;
        } else {
            increment = inc[idx - 1];
            inc[idx - 2] += inc[idx - 1];
            inc[idx - 1] = 0;
        }
        int val = stack.pop() + increment;
        return val;
    }

    public void increment(int k, int val) {
        int idx = stack.size();
        if (idx == 0) return;
        if (idx < k) k = idx;
        inc[k - 1] += val;
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */