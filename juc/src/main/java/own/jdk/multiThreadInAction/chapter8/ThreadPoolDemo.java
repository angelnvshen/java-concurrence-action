package own.jdk.multiThreadInAction.chapter8;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        int processor = Runtime.getRuntime().availableProcessors() / 2;
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        /*Future<?> future = poolExecutor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " ---in--- ");
            int i = 10 / 0;
            System.out.println(Thread.currentThread().getName() + " ---out--- ");
            return;
        });

        poolExecutor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " -------- ");
        });

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*System.out.println(" ++++++++++ ");
        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " ---in--- ");
            int i = 10 / 0;
            System.out.println(Thread.currentThread().getName() + " ---out--- ");
            return;
        });

        poolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " -------- ");
        });*/
        poolExecutor.shutdown();
        poolExecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
