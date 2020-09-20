package own.stu.concurrence.disruptor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class LogTest {

    Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++)
            executorService.execute(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                for (int j = 0; j < 100; j++) {
                    logger.error(Thread.currentThread().getName() + " -> hello " + j);
//                    logger.info(Thread.currentThread().getName() + " -> hello " + j);
                }
            });

        latch.countDown();
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdown();
    }
}
