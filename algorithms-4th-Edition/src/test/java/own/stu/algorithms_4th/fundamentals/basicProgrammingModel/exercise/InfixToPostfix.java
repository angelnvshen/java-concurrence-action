package own.stu.algorithms_4th.fundamentals.basicProgrammingModel.exercise;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class InfixToPostfix {

    public static String plus = "+";
    public static String sub = "-";
    public static String muil = "*";
    public static String div = "/";

    public static String parenthessL = "(";
    public static String parenthessR = ")";

    public static void infixToPostfix(String str){

        String [] strs = str.split("");

        Stack<String> stack = new Stack<>();

        String symbol = "" ;
        for(String s : strs){

            if      (s.equals("+")) stack.push(s);
            else if (s.equals("*")) stack.push(s);
            else if (s.equals(")")) StdOut.print(stack.pop() + " ");
            else if (s.equals("(")) StdOut.print("");
            else                    StdOut.print(s + " ");
        }
    }

    public static void main(String[] args) {
//        infixToPostfix("(2+((3+4)*(5*6)))");
        infixToPostfix("(2+3*3+4)*(2+2+4)");
    }

}
