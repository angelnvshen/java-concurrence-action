package own.jdk.executorService;

import java.util.concurrent.*;

public class ExecutorServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        threadPoolExecutor.prestartCoreThread();

        threadPoolExecutor.getQueue().put(()-> System.out.println("hello ......"));

        threadPoolExecutor.shutdown();
    }

    public static void main1(String[] args) {
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
