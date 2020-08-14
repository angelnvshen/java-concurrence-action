package own;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Printer {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(SECONDS.toMicros(1L));
        /*RateLimiter limiter = RateLimiter.create(3);
        System.out.println(limiter.acquire(6));
//        limiter.toString()
        Stopwatch started = Stopwatch.createStarted();
        System.out.println("-" + started.elapsed().getNano());
        Thread.sleep(4);
        System.out.println("-" + started.elapsed().getNano());
        System.out.println(limiter.acquire(2));
        System.out.println("-" + started.elapsed().getNano());
*/
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(() -> System.out.println(1));
        pool.awaitTermination(1, TimeUnit.SECONDS);
    }


    public static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void print(long[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void print(double[] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    public static void print(char[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void print(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
