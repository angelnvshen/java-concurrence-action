package own.leetcode.dp;

import java.util.*;

public class LargestNumber {

    public static void main(String[] args) {
//        LargestNumber number = new LargestNumber();
//        System.out.println(number.largestNumber(new int[]{3, 30, 34, 5, 9}));
        String str = "000001500030904768070080091300158002500400370090063800800210600701000209620509187";
        System.out.println(str.length());
        List<List<String>> list = new ArrayList<>();
        for(int i = 0; i < 9; i ++){
            String tmp = str.substring(i * 9, i * 9 + 9);
            list.add(Arrays.asList(tmp.split("")));
        }
        System.out.println(list);
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }
        List<String> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i + "");
        }

        Collections.sort(list, new Compare());
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }

        return sb.toString();
    }

    private class Compare implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o2 + o1).compareTo(o1 + o2);
        }
    }

}