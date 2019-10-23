package own.jdk.executorService;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3));

        try {
            threadPoolExecutor.submit(getRunnable("t1"));
            threadPoolExecutor.submit(getRunnable("t2"));
            threadPoolExecutor.submit(getRunnable("t3"));
            threadPoolExecutor.submit(getRunnable("t4"));
            threadPoolExecutor.submit(getRunnable("t5"));
            threadPoolExecutor.submit(getRunnable("t6"));

        } finally {

            threadPoolExecutor.shutdown();
        }

    }

    private static Runnable getRunnable(String taskName) {
        return () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + taskName);
        };
    }
}
