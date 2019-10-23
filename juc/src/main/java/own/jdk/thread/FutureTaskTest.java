package own.jdk.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println(" run the task ");
            TimeUnit.HOURS.sleep(5);
            return Thread.currentThread().getName();
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        new Thread(() -> {
            try {
                System.out.println("in " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
                System.out.println("after sleep  " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " -> " + futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, "thread - 1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(120);
                System.out.println("after sleep  " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " -> cancel " + futureTask.cancel(true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread - 2").start();

        TimeUnit.SECONDS.sleep(1);
//        System.out.println(futureTask.cancel(true));
        System.out.println("after sleep  " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " -> " + futureTask.get());
    }

    public static void main2(String[] args) {

        FutureTask futureTask = new FutureTask(() -> {
            System.out.println(" run the task ");
            TimeUnit.HOURS.sleep(5);
            return Thread.currentThread().getName();
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(futureTask.cancel(true));
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
