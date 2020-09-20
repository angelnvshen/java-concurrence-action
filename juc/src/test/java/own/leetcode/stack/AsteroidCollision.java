package own.leetcode.stack;

import java.util.Stack;

public class AsteroidCollision {


    public static void main(String[] args) {
        AsteroidCollision collision = new AsteroidCollision();
//        collision.asteroidCollision(new int[]{5, 10, -5});
        collision.asteroidCollision(new int[]{-1,5, 10, -5});
    }

    /**
     * @param asteroids: a list of integers
     * @return: return a list of integers
     */
    public int[] asteroidCollision(int[] asteroids) {
        // write your code here
        if (asteroids == null || asteroids.length == 0) {
            return asteroids;
        }

        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        for (int i : asteroids) {
            boolean canIn = true;
            while (!stack.isEmpty() && stack.peek() > 0 && i < 0) {
                if (stack.peek() < Math.abs(i)) {
                    stack.pop();
                } else {
                    canIn = false;
                    break;
                }
            }
            if (canIn)
                stack.push(i);
        }

        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}