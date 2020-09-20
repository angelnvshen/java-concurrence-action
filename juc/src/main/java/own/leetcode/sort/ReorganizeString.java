package own.leetcode.sort;

import java.util.Arrays;

public class ReorganizeString {

    public static void main(String[] args) {
        ReorganizeString string = new ReorganizeString();
        System.out.println(string.reorganizeString("aab"));
    }

    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        int n = S.length();
        char[] cs = S.toCharArray();
        int[][] map = new int[26][2];
        for (int i = 0; i < map.length; i++) {
            map[i][1] = i;
        }
        for (int i = 0; i < n; i++) {
            map[cs[i] - 'a'][0]++;
        }

        Arrays.sort(map, (a, b) -> b[0] - a[0]);
        if (((n & 1) == 0 && map[0][0] > n / 2) ||
                (n & 1) == 1 && map[0][0] > (n + 1) / 2) {
            return "";
        }

        int odd = 0;
        int even = 1;
        for (int i = 0; i < map.length; i++) {
            if (map[i][0] == 0) break;

            char c = (char) (map[i][1] + 'a');
            for (int j = 0; j < map[i][0]; j++) {
                if (odd < n) {
                    cs[odd] = c;
                    odd += 2;
                } else {
                    cs[even] = c;
                    even += 2;
                }
            }
        }
        return new String(cs);
    }
}