package own.leetcode;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
    }

    public int numRabbits(int[] answers) {
        if(answers == null || answers.length == 0){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : answers){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> en : map.entrySet()){
            int val = en.getKey();
            int t = en.getValue();

            val += 1;
            ans += (t % val == 0 ? t : (t / val + 1) * val);

        }
        return ans;
    }
}