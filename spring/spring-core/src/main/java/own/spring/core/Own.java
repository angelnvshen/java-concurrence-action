package own.spring.core;

import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2019/7/15.
 */
public class Own {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(":" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("before:" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("after:" + thread.isInterrupted());

    }
}
