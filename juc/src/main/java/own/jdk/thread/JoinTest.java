package own.jdk.thread;

import java.util.concurrent.TimeUnit;

public class JoinTest {

    private static boolean flag ;
    public static void main(String[] args) {
        System.out.println(flag);
    }

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " in ");
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " out ");
        }, " Thread - 1 ");
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        thread.join(5000);

        System.out.println("main out");
    }
}
