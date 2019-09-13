package own.leetcode.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashFunction {

    static int hash_func(int key, int table_len) {
        return key % table_len;
    }

    public static void insert(ListNode[] listTable, ListNode node, int table_len) {
        int key = hash_func(node.value, table_len);
        node.next = listTable[key];
        listTable[key] = node;
    }

    public static boolean search(ListNode[] listTable, int value, int table_len) {
        int key = hash_func(value, table_len);
        ListNode nodeHead = listTable[key];
        while (nodeHead != null) {
            if (nodeHead.value == value) {
                return true;
            }
            nodeHead = nodeHead.next;
        }
        return false;
    }

    // 最长回文字符串 - 长度
    public static int longestPalindrome(String s) {
        int[] map = new int[128];
        int max_length = 0;
        int flag = 0; // 是否有中心店
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] % 2 == 0) {
                max_length += map[i];
            } else {
                max_length += map[i] - 1;
                flag = 1;
            }
        }
        return max_length + flag;
    }

    public static boolean wordPattern(String pattern, String str) {

        String word = "";
        Map<String, Character> strMap = new HashMap<>();
        char[] used = new char[128];
        str += " "; //末尾补上空格，空格隔开每个单词
        int position = 0; // 当前指向pattern的字符
        for (int i = 0; i < str.length(); i++) {
            if (position == pattern.length()) { // 分隔出一个单词，但是已无一个pattern字符对应
                return false;
            }
            if (str.charAt(i) == ' ') {
                Character s = strMap.get(word);
                if (s != null) {
                    if (!Objects.equals(pattern.charAt(position), s)) {
                        return false;
                    }
                } else {
                    if (used[pattern.charAt(position)] == 1) {
                        return false;
                    }
                    used[pattern.charAt(position)] = 1;
                    strMap.put(word, pattern.charAt(position));
                }
                position++;
                word = "";
            } else {
                word += str.charAt(i);
            }
        }

        if (position != pattern.length()) {
            return false;
        }

        return true;
    }

    public static int lengthOfLongestSubString(String str) {
        int result = 0;
        int begin = 0;
        String word = "";
        int[] char_map = new int[128];

        for (int i = 0; i < str.length(); i++) {
            char_map[str.charAt(i)]++;
            if (char_map[str.charAt(i)] == 1) { // word中未出现该字符
                word += str.charAt(i);
                if (result < word.length()) {
                    result = word.length();
                }
            } else {
                while (begin < i && char_map[str.charAt(i)] > 1) {
                    char_map[str.charAt(begin)]--;
                    begin++;
                }
                word = "";
                for (int j = begin; j < i; j++) {
                    word += str.charAt(j);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("aassdsdccc"));
        System.out.println(lengthOfLongestSubString("abcbadab"));
    }

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
}
