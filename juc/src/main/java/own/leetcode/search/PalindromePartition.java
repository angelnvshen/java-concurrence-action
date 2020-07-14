package own.leetcode.search;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {

    public static void main(String[] args) {
        PalindromePartition partition = new PalindromePartition();
        /*boolean[][] p1 = partition.isPalin("aab");
        boolean[][] p2 = partition.isPalin_ii("aab");
        Printer.print(p1);
        System.out.println(" ==== ");
        Printer.print(p2);*/

        System.out.println(partition.partition("aabxvvcc"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null) {
            return ans;
        }
        int n = s.length();
        if (n == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }

        boolean[][] palins = isPalin(s);

        helper(ans, s, 0, new ArrayList<>(), palins);
        return ans;
    }

    private void helper(List<List<String>> ans, String s, int index, List<String> result, boolean[][] palins) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(result));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (!palins[index][i]) {
                continue;
            }
            result.add(s.substring(index, i + 1));
            helper(ans, s, i + 1, result, palins);
            result.remove(result.size() - 1);
        }
    }

    public boolean[][] isPalin(String s) {
        int n = s.length();

        boolean[][] palins = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            palins[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            palins[i][i + 1] = s.charAt(i) == s.charAt(i + 1); // len = 1;
        }

        for (int len = 2; len < n; len++) {
            for (int j = 0; j < n - len; j++) {
                if (s.charAt(j) == s.charAt(j + len) && palins[j + 1][j + len - 1]) {
                    palins[j][j + len] = true;
                }
            }
        }
        return palins;
    }

    public boolean[][] isPalin_ii(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        boolean[][] palins = new boolean[n][n];
        // 以字符为分隔
        for (int i = 0; i < n; i++) {
            int j = i;
            int k = i;
            while (j >= 0 && k < n) {
                if (cs[j] == cs[k]) {
                    palins[j][k] = true;
                    j--;
                    k++;
                } else {
                    break;
                }
            }
        }
        // 以中心线为分隔
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = i + 1;
            while (j >= 0 && k < n) {
                if (cs[j] == cs[k]) {
                    palins[j][k] = true;
                    j--;
                    k++;
                } else {
                    break;
                }
            }
        }
        return palins;
    }
}