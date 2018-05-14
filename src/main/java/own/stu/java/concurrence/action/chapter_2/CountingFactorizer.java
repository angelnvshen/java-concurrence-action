package own.stu.java.concurrence.action.chapter_2;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer {
    private AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    private void test(int i) {
        long count_now = count.getAndIncrement();
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
        CountingFactorizer stateless = new CountingFactorizer();
        for (int i = 0; i < 100; i++)
            service.submit(() -> {
                stateless.test(new Random().nextInt(100));
            });
        service.shutdown();
    }
}
