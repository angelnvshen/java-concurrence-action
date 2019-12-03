package own.guava.base.util;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static own.jdk.executorService.scheduled.TimerExample.sleep;

public class RateLimterTest {

    @Test
    public void test() {
        RateLimiter rateLimiter = RateLimiter.create(2);

        AtomicInteger atomicInteger = new AtomicInteger(1);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 3).forEach(i -> executorService.submit(() -> {
            for (; ; ) {
                rateLimiter.acquire();
                System.out.println(Thread.currentThread().getName()
                        + " -> " + System.currentTimeMillis() + " -> " + atomicInteger.getAndIncrement());

                if (atomicInteger.get() >= 100)
                    break;
//                sleep(30, TimeUnit.MILLISECONDS);
            }
        }));

        sleep(60);
    }

    /**
     * deal-power: 2个request/1 sec
     */
    @Test
    public void testBucketLimiter() {
        BucketLimiter<Integer> bucketLimiter = new BucketLimiter(2, 10);
        AtomicInteger request = new AtomicInteger(1);
        IntStream.range(0, 3).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    int r = request.getAndIncrement();
                    try {
                        bucketLimiter.request(r);
                        System.out.println(Thread.currentThread().getName() + " -> " + r);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    sleep(1, TimeUnit.SECONDS);
                }
            }, "thread - " + i).start();
        });


        IntStream.range(5, 6).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    Integer response = bucketLimiter.response();
                    System.out.println(Thread.currentThread().getName() + " <- " + response);
                }
            }, "thread - " + i).start();
        });

        sleep(1, TimeUnit.HOURS);
    }

    @Test
    public void testTokenBucket() {
        TokenLimiter tokenLimiter = new TokenLimiter(10, 100);
        IntStream.range(0, 200).forEach(i ->
                new Thread(() ->
                {
                    sleep(ThreadLocalRandom.current().nextInt(50), TimeUnit.MILLISECONDS);
                    tokenLimiter.request();
                }, "thread - " + i).start());

        sleep(2, TimeUnit.MINUTES);
    }

    @Test
    public void testIssue() {
        RateLimiter limiter = RateLimiter.create(5000);
        Stopwatch stopwatch = Stopwatch.createStarted();

        List<byte[]> list = Arrays.asList(new byte[3000], new byte[4999], new byte[5000], new byte[20_000], new byte[4500]);
        for (byte[] bytes : list) {
            submitPacket(limiter, bytes, stopwatch);
        }
        stopwatch.stop();
    }

    void submitPacket(RateLimiter limiter, byte[] packet, Stopwatch stopwatch) {

        limiter.acquire(packet.length);
        System.out.println(" deal " + packet.length + " " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
