package own.leetcode.search.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        /*solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
                new String[]{"hit"}
        );*/

        System.out.println(solution.mostCommonWord("abb",
                new String[]{}
        ));
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0 || banned == null) {
            return "";
        }
        paragraph = paragraph.toLowerCase();
        Set<String> except = new HashSet<>();
        for (String s : banned) {
            except.add(s);
        }

        char[] cs = paragraph.toCharArray();
        int n = paragraph.length();
        int l = -1;
        int r = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (notWordSet.contains(cs[i])) {

                if (l + 1 <= r) {
                    String tmp = paragraph.substring(l + 1, r + 1);
                    if (!except.contains(tmp))
                        map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                }

                l = i;
                continue;
            } else {
                r = i;
            }
        }
        if(!notWordSet.contains(cs[n - 1])){
            String tmp = paragraph.substring(l + 1, r + 1);
            if (!except.contains(tmp))
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        if (map.size() == 0) return "";
        int maxLen = 0;
        String ans = "";
        for (Map.Entry<String, Integer> en : map.entrySet()) {
            if (maxLen < en.getValue()) {
                maxLen = en.getValue();
                ans = en.getKey();
            }
        }
        return ans;
    }

    private static char[] notWords = "!?',;. ".toCharArray();
    private static Set<Character> notWordSet = new HashSet<>();

    {
        for (char c : notWords) {
            notWordSet.add(c);
        }
    }
}