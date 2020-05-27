package own.leetcode.rotateArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWords {

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        reverseWords.reverseWords("a good   example");
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;

        /*s = s.trim();
        String[] strs = s.split("\\s+");

        int l = 0;
        int r = strs.length - 1;
        String temp;
        while(l < r){
            temp = strs[l];
            strs[l] = strs[r];
            strs[r] = temp;
            l ++;
            r --;
        }
        return String.join(" ", strs);*/

        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);

    }
}