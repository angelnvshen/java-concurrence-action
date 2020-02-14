package own;

import org.junit.Test;

public class SpiderForTxt {
    @Test
    public void test() {
        int[][]dp = new int[1][2];
        dp[0][1] = dp[0][0] = 12;
        System.out.println(dp);

        System.out.println('A' - 64);
        System.out.println((int)'A');
        System.out.println((int)'Z');
    }


}
