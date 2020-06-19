package own.leetcode.math;

public class TrailingZeroes {

    public static void main(String[] args) {
        TrailingZeroes zeroes = new TrailingZeroes();
        System.out.println(zeroes.trailingZeroes(2147483647));
    }

    public int trailingZeroes(int n) {
        if (n <= 0) {
            return 0;
        }
        /* 统计 5出现的次数即可*/
        int count = 0;
        // for(int i = 5; i <= n ; i += 5){

        //     int dividor = 5;
        //     while(i % dividor == 0){
        //         count += 1;
        //         dividor *= 5;
        //     }
        // }

        long dividor = 5;
        while (n >= dividor) {
            count += n / dividor;
            dividor *= 5;
        }
        return count;
    }
}