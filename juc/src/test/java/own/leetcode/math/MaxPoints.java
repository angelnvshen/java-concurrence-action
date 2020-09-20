package own.leetcode.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Definition for a point.
 * class Point {
 * int x;
 * int y;
 * Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; }
 * }
 */

public class MaxPoints {

    public static void main(String[] args) {
        MaxPoints points = new MaxPoints();

        System.out.println(points.maxPoints(new Point[]{
                /*new Point(1, 1),
                new Point(3, 2),
                new Point(5, 3),
                new Point(4, 1),
                new Point(2, 3),
                new Point(1, 4),*/

                /*new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),*/

                /*new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2),
                new Point(2, 2),*/
                new Point(1, 1),
                new Point(1, 1),
        }));
    }

    /**
     * @param points: an array of point
     * @return: An integer
     */
    public int maxPoints(Point[] points) {
        // write your code here
        if (points == null || points.length == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            Point cur = points[i];
            int sameCur = 1;

            /*
            Pair 保存的是两个点的斜率（以分数形式保存，x 分子，y 分母）
            */
            Map<Pair, Integer> count = new HashMap<>();

            int curMax = 0;
            Point tmp = null;
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                tmp = points[j];
                if (tmp.x == cur.x && tmp.y == cur.y) {
                    sameCur += 1;
                    continue;
                }
                int a = tmp.x - cur.x;
                int b = tmp.y - cur.y;

                Pair pair2 = toPair(a, b);
                count.put(pair2, count.getOrDefault(pair2, 0) + 1);
                curMax = Math.max(curMax, count.get(pair2));
                System.out.println(count);
            }
            ans = Math.max(ans, curMax + sameCur);
        }
        return ans;
    }

    private Pair toPair(int a, int b) {
        Pair pair = null;
        if (b == 0)
            return Pair.of(1, 0);

        int gcd = Math.abs(gcd(a, b));
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            pair = Pair.of((Math.abs(a) / gcd) * -1,
                    Math.abs(b) / gcd);
        } else {
            pair = Pair.of(Math.abs(a) / gcd,
                    Math.abs(b) / gcd);
        }

        return pair;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    static class Pair {
        int x;
        int y;

        Pair(int a, int b) {
            x = a;
            y = b;
        }

        static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "[" +
                    "x=" + x +
                    ", y=" + y +
                    ']';
        }
    }
}