package own;

public class Demo extends Printer{
    public static void print(int[] dp){
        System.out.println(dp[0]);
    }

    public static void main(String[] args) {
        Demo dmeo = new Demo();
        int[] dp = {1,2,3,};
        Printer.print(dp);
    }
}
