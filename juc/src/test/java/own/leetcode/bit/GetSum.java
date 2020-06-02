package own.leetcode.bit;

public class GetSum {

    public static void main(String[] args) {
        GetSum sum = new GetSum();
        System.out.println(sum.getSum(-2147483648, -2147483648));
    }

    public int getSum(int a, int b) {
        int diff = a ^ b, carry = (a & b) << 1;
        if(carry == 0){
            return diff;
        }
        return getSum(diff, carry);
    }
}