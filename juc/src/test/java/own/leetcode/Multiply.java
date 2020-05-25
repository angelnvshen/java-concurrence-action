package own.leetcode;

public class Multiply {

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        System.out.println(multiply.multiply("222", "2344"));
    }
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 ||
                num2 == null || num2.length() == 0) {
            return "";
        }

        int n = num1.length();
        int m = num2.length();
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();

        int[] ans = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mul = (char1[i] - '0') * (char2[j] - '0');
                //num1[i] 和 num2[j] 的乘积对应的就是
                // ans[i+j] 和 ans[i+j+1] 这两个位置。
                int p = i + j, q = i + j + 1;
                int sum = mul + ans[p];
                ans[p] = sum % 10;
                ans[q] += sum / 10;
            }
        }
        System.out.println(" ======= ");
        // ans 可能会有前缀0
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 0) continue;
            sb.append(ans[i]);
        }
        return sb.toString();
    }
}