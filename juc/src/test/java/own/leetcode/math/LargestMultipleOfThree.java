package own.leetcode.math;

import java.util.*;
import java.util.stream.Collectors;

public class LargestMultipleOfThree {

    public static void main(String[] args) {
        LargestMultipleOfThree three = new LargestMultipleOfThree();

        System.out.println(three.largestMultipleOfThree(new int[]{8, 6, 7, 1, 0}));
    }

    public String largestMultipleOfThree(int[] digits) {
        if (digits == null || digits.length == 0) {
            return "";
        }
        // 1 <= digits[i] <= 9
        /*
        sum % 3 == 0 : 可以取所有数字
        sum % 3 == 1 : 需要删除一个 mod(3) == 1的数字
                       或者删除两个 mod(3) == 2的数字{ (2 + 2) % 3 == 1}
        sum % 3 == 2 : 需要删除一个 mod(3) == 2的数字
                       或者删除两个 mod(3) == 1的数字{ (1 + 1) % 3 == 1}  
        */
        int n = digits.length;
        List<Integer> list = new ArrayList<>();

        int sum = 0;
        for (int i : digits) {
            sum += i;
            list.add(i);
        }
        int left = sum % 3;

        if (left == 0) {
            return gene(helper(list));
        }

        Collections.sort(list);

        List<Integer> candi = null;
        List<Integer> candi_2 = null;

        String s1 = null;
        String s2 = null;

        if (left == 1) {
            // delete 一个 mod(3) == 1 的数字
            candi = new ArrayList<>(list);
            removeEle(candi, 1, 1);
            s1 = gene(helper(candi));

            candi_2 = new ArrayList<>(list);
            removeEle(candi_2, 2, 2);
            s2 = gene(helper(candi_2));
            return s1.length() > s2.length() ? s1 : s2;
        }


        if (left == 2) {
            // delete 一个 mod(3) == 2 的数字
            candi = new ArrayList<>(list);
            removeEle(candi, 2, 1);
            s1 = gene(helper(candi));

            candi_2 = new ArrayList<>(list);
            removeEle(candi_2, 1, 2);
            s2 = gene(helper(candi_2));
            return s1.length() > s2.length() ? s1 : s2;
        }
        return "";
    }

    private String gene(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        boolean header = true;
        for (Integer d : list) {
            if (header) {
                if (d == 0) {
                    continue;
                }
            }
            header = false;

            sb.append(d);
        }
        return sb.toString();
    }

    private void removeEle(List<Integer> list, int times, int left) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 3 == left) {
                iterator.remove();
                times -= 1;
                if (times == 0) {
                    break;
                }
            }
        }
        if(times > 0){
            list.clear();
        }
    }

    private List<Integer> helper(List<Integer> list) {
        return list.stream()
                .sorted(new Comparators())
                .collect(Collectors.toList());
    }

    class Comparators implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return (o2 + "" + o1).compareTo(o1 + "" + o2);
        }
    }
}