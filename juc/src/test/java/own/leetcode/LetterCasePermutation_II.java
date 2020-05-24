package own.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation_II {

    public static void main(String[] args) {
        LetterCasePermutation_II casePermutation = new LetterCasePermutation_II();
//        System.out.println(casePermutation.letterCasePermutation("JcTNPT1AsvC"));
        System.out.println(casePermutation.letterCasePermutation("Jw"));
    }

    int dis = 'A' - 'a';

    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        if (S == null || S.length() == 0) return ans;

        helper(S.toCharArray(), 0, ans);

        return ans;
    }


    private void helper(char[] chars, int index, List<String> ans){

        ans.add(new String(chars));

        for (int i = index; i < chars.length; i++) {
            char c = chars[i];
            if (!Character.isLetter(c)) continue;

            if (Character.isLowerCase(c)) {
                chars[i] = (c = (char) (c + dis));
                helper(chars, i + 1, ans);
                chars[i] = (char) (c - dis);
            } else {
                chars[i] = (c = (char) (c - dis));
                helper(chars, i + 1, ans);
                chars[i] = (char) (c + dis);
            }
        }
    }

    /*private void helper(char[] chars, int index, List<String> ans) {

        ans.add(String.valueOf(chars));

        for (int i = index; i < chars.length; i++) {
            if (!Character.isLetter(chars[i])) continue;

            if (Character.isLowerCase(chars[i])) {
                chars[i] = (char) (chars[i] + dis);
                helper(chars, i + 1, ans);
                chars[i] = (char) (chars[i]- dis);
            } else {
                chars[i] = (char) (chars[i] - dis);
                helper(chars, i + 1, ans);
                chars[i] = (char) (chars[i] + dis);
            }

        }
    }*/
}