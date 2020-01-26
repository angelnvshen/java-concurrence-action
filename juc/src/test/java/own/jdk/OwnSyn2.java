package own.jdk;

import org.junit.Test;

public class OwnSyn2 {

    @Test
    public void test1(){
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] map = new char[128];
        int result = 0;
        int max_result = 0;

        char[] chars = s.toCharArray();
        int p = 0;

        for (int i = 0; i < chars.length; i++) {
            map[chars[i]]++;
            if (map[chars[i]] == 1) {
                result += 1;
            }
            if (map[chars[i]] == 2) {
                max_result = Math.max(max_result, result);
                while (p <= i) {
                    map[chars[p]]--;
                    if (chars[p] == chars[i]) {
                        p++;
                        result -= 1;
                        break;
                    }
                }
            }
        }
        return Math.max(max_result, result);
    }
}

