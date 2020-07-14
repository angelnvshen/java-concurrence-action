package own.leetcode.stack;

import java.util.List;
import java.util.Stack;

public class ExclusiveTime {

    public static void main(String[] args) {
        ExclusiveTime time = new ExclusiveTime();

        /*int[] ans = time.exclusiveTime(3, Arrays.asList("0:start:0", "1:start:2", "2:start:3", "2:end:4", "1:end:5", "0:end:6", "1:start:7", "1:end:10"));
        IntStream.of(ans).boxed().forEach(a -> {
            System.out.print(a + " ");
        });*/
        System.out.println(time.gcd(-12, 4));
        System.out.println(time.gcd(12, -4));
        System.out.println(time.gcd(-12, 3));
        System.out.println(time.gcd(12, -3));
        System.out.println(time.gcd(-12, 5));
        System.out.println(time.gcd(12, -5));

    }

    /**
     * @param n:    a integer
     * @param logs: a list of integers
     * @return: return a list of integers
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        // write your code here
        if (logs == null || logs.size() == 0 || n <= 0) {
            return new int[0];
        }

        int[] ans = new int[n];

        Stack<String[]> stack = new Stack<>();
        String[] cs = null;

        int interval = 0;

        //"0:start:0", "1:start:2", "2:start:3", "2:end:4", "1:end:5", "0:end:6", "1:start:7", "1:end:10"
        for (String log : logs) {
            cs = log.split(":");

            if (cs[1].equals("start")) {
                stack.push(cs);
            } else {
                String[] tmp = stack.pop();

                int cost = Integer.valueOf(cs[2]) - Integer.valueOf(tmp[2]) + 1;
                ans[Integer.valueOf(cs[0])] += cost - interval;

                interval += cost;
            }
        }

        return ans;
    }

    class Pair {

    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        int tmp = b;
        b = a % b;
        a = tmp;
        return gcd(a, b);
    }
}