package own.stu.java.concurrence.action.chapter_2;

import net.jcip.annotations.NotThreadSafe;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 竞态条件 count
 *
 *  先检查后执行
 */
@NotThreadSafe
public class UnsafeCountingFactorizer {

    private long count = 0;

    public long getCount() {
        return count;
    }

    private void test(int i) {
        long count_now = count++;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + ", count : " + count_now);
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        UnsafeCountingFactorizer stateless = new UnsafeCountingFactorizer();
        for (int i = 0; i < 100; i++)
            service.submit(() -> {
                stateless.test(new Random().nextInt(100));
            });
        service.shutdown();
    }
}
