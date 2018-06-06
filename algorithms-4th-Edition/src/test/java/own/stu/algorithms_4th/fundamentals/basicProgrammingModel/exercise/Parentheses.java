package own.stu.algorithms_4th.fundamentals.basicProgrammingModel.exercise;

import edu.princeton.cs.algs4.Stack;

public class Parentheses {

    public static String llP = "(";
    public static String lrP = ")";
    public static String mlP = "[";
    public static String mrP = "]";
    public static String blP = "{";
    public static String brP = "}";

    public static boolean isBalanced(String[] strs) {

        if (strs == null || strs.length <= 0)
            return false;

        Stack<String> stack = new Stack<>();
        for (String s : strs) {

            if (s.equals(llP) || s.equals(mlP) || s.equals(blP))
                stack.push(s);
            if (s.equals(lrP) || s.equals(mrP) || s.equals(brP)) {
                if(stack.isEmpty())
                    return false;
                String tmp = stack.peek();
                if (tmp.equals(llP) && s.equals(lrP) ||
                        tmp.equals(mlP) && s.equals(mrP) ||
                        tmp.equals(blP) && s.equals(brP))
                    stack.pop();
                else
                    return false;
            }
        }

//        if (stack.isEmpty()) return true;
//        return false;
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String msg = "[[()]{}{[()()]()}}";
        System.out.println(isBalanced(msg.split("")));
    }
}
