package own.leetcode.math;

public class CountDigitOne {

    public static void main(String[] args) {
        CountDigitOne one = new CountDigitOne();
        System.out.println(one.countDigitOne(13));
    }

    public int countDigitOne(int n) {
        if (n <= 0) return 0;

        int count = 0;
        StringBuilder sb = new StringBuilder(n + "");
        String str = sb.reverse().toString();
        // 123 - > "321";

        for (int i = 0; i < str.length(); i++) {
            int power = (int) Math.pow(10, i);

            int prePart = n / (int) Math.pow(10, i + 1);
            count += prePart * power;

            int cur = str.charAt(i) - '0';
            if (cur > 1) {
                count += power;
            } else if (cur == 1) {
                count += n % power + 1;
            }
        }
        return count;
    }
}
/*
XXX [Y] XX
345 [Y] XX
 统计百位上的1出现的次数：
(1)
 前三位是 < 345
000 [Y] 00
001     01
002     02
...     ..
344     99

 => 345 * 100
(2)
345 [Y] XX
 前三位是 = 345
    (2.1)
    百位是 0
        => 0
    (2.2)
    百位是 > 1
        => XX -> 00 ~ 99  共100个
    (2.3)
    百位是 = 1
        => XX -> 00 ~ XX 共 xx + 1个
    
*/