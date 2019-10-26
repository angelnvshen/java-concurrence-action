package own.jdk.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EnqTest {
    public static void main(String[] args) throws InterruptedException {
        Enq enq = new Enq();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 2; i++)
            executorService.submit(() -> {
                enq.addWaiter(Enq.Node.EXCLUSIVE);
            });

        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println(enq);
    }
}
