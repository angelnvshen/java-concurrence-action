package own.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class ValidSquare {

    public static void main(String[] args) {
        ValidSquare square = new ValidSquare();
        System.out.println(square.validSquare(
                new int[]{0, 0},
                new int[]{1, 1},
                new int[]{1, 0},
                new int[]{0, 1}
        ));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }
        Map<Long, Integer> map = new HashMap<>();// 边长(平方)个数总计
        int[][] ps = new int[][]{p1, p2, p3, p4};
        for (int i = 0; i < ps.length; i++) {
            for (int j = i + 1; j < ps.length; j++) {
                long distance = distance(ps[i], ps[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
        }

        if (map.size() != 2) return false;

        for (int s : map.values()) {
            if (s != 2 && s != 4) return false;
        }

        Long s1 = null, s2 = null;
        for (long s : map.keySet()) {
            if (s1 == null) {
                s1 = s;
            } else {
                s2 = s;
            }
        }
        if (Math.abs(s1 - s2) != Math.min(s1, s2)) return false;
        return true;
    }

    private long distance(int[] p1, int[] p2) {
        return 1l * (p1[0] - p2[0]) * (p1[0] - p2[0]) +
                1l * (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}