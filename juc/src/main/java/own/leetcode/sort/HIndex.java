package own.leetcode.sort;

import java.util.Arrays;

public class HIndex {

    public static void main(String[] args) {
        HIndex index = new HIndex();
        System.out.println(index.hIndex(new int[]{3, 4, 6, 1, 5}));
    }

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        Arrays.sort(citations);
        int ans = n;
        /*
        3, 0, 6, 1, 5
        0  1  3  5  6  <-
         */
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] < (n - i)) {
                ans--;
            }
        }
        return ans;
    }
}