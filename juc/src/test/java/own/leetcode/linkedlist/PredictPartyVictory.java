package own.leetcode.linkedlist;

import java.util.LinkedList;

public class PredictPartyVictory {

    public static void main(String[] args) {
        PredictPartyVictory victory = new PredictPartyVictory();
        System.out.println(victory.predictPartyVictory("RD"));
    }

    /**
     * @param senate: a string
     * @return: return a string
     */
    public String predictPartyVictory(String senate) {
        // write your code here
        if (senate == null || senate.length() == 0) {
            return senate;
        }

        LinkedList<Character> list = new LinkedList<>();
        char[] cs = senate.toCharArray();
        for (char c : cs) {
            list.add(c);
        }

        int diffCount = 0; // 正数是R 负数是D
        boolean find = false;

        LinkedList<Character> newList = new LinkedList<>();
        while (!find) {

            int size = list.size();

            for (int i = 0; i < size; i++) {

                char v = list.get(i);
                if (v == 'R') {
                    diffCount += 1;
                    if (diffCount > 0) {
                        newList.add(v);
                    }
                } else {
                    diffCount -= 1;
                    if (diffCount < 0) {
                        newList.add(v);
                    }
                }
            }
            if (Math.abs(diffCount) >= size) {
                find = true;
                break;
            }
            list = newList;
        }
        return list.getLast() == 'D' ? "Dire" : "Radiant";
    }
}