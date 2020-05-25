package own.leetcode;

public class AddStrings {

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        System.out.println(addStrings.addStrings("11", "99"));

    }

    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return "0";
        if (num2 == null || num2.length() == 0) return "0";

        int n = num1.length();
        int m = num2.length();
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();

        int[] ans = new int[n + m];

        int i = n - 1;
        int j = m - 1;
        int candi = 0;
        int index = ans.length - 1;
        while (i >= 0 && j >= 0) {

            // 要判断上一次是否有进位
            int sum = char1[i] - '0' + char2[j] - '0' + ans[index] + candi;

            ans[index] = sum % 10;

            candi = sum >= 10 ? 1 : 0;
            i--;
            j--;
            index--;
        }

        while (i >= 0) {
            int sum = char1[i] - '0' + ans[index] + candi;

            ans[index] = sum % 10;

            candi = sum >= 10 ? 1 : 0;
            i--;
            index--;
        }

        while (j >= 0) {
            int sum = char2[j] - '0' + ans[index] + candi;

            ans[index] = sum % 10;

            candi = sum >= 10 ? 1 : 0;
            j--;
            index--;
        }

        if (candi == 1) {
            ans[index] = (ans[index] + candi) % 10;
        }

        index = 0;
        for (; index < ans.length; index++) {
            if (ans[index] == 0) continue;
            else break;
        }
        StringBuilder sb = new StringBuilder();
        for (; index < ans.length; index++) {
            sb.append(ans[index]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}