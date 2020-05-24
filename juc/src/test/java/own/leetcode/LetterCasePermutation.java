package own.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LetterCasePermutation {

    public static void main(String[] args) {
        LetterCasePermutation casePermutation = new LetterCasePermutation();
        System.out.println(casePermutation.letterCasePermutation("JcTNPT1AsvC"));
    }

    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        if(S == null || S.length() == 0) return ans;
        Set<String> set = new HashSet<>();

        helper(S.toCharArray(), 0, set, S.toCharArray());
        ans.addAll(set);
        return ans;
    }

    private void helper(
        char[] chars, int index, Set<String> ans, char[] result){

            ans.add(new String(result));

            for(int i = index; i < chars.length; i ++){
                char c = chars[i];
                if(isNum(c)) continue;                
                result[i] = (c = changeWord(c));
                helper(chars, index + 1, ans, result);
                result[i] = changeWord(c);
            }
    }

    private char changeWord(char c){
        int distance = c - 'a';
        if(distance >= 0 && distance <= 26){
            return (char)(distance + 'A');
        }else{
            distance = c - 'A';
            return (char)(distance + 'a');
        }
    }

    private boolean isNum(char c){
        return c - '0' >= 0 && c - '0' <= 9;
    }
}