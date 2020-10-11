package own.jdk.interrupted;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class UnknownTest {

    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            /*for(int i = 0; i < 10; i ++){
                if(i == 8){
                    Thread.currentThread().interrupt();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
            }*/

            /*try {
                Thread.currentThread().interrupt();
                System.out.println(" --- before sleep ---- ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(" --- after sleep ---- ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().isInterrupted();
            }*/

            try {
                System.out.println(" --- before sleep ---- ");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(" --- after sleep ---- ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        thread.start();

        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("-------");
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());

        thread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        interruptedStateTimeout();

//        lockSupport();
    }

    private static void interruptedStateTimeout() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(" --- before sleep ---- " + Thread.currentThread().isInterrupted());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(" --- after sleep ---- " + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        thread.start();

        thread.join();
    }

    private static void lockSupport() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程一正在执行，将进入等待状态，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("线程一从等待状态中醒来，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("线程一从等待状态中醒来，当前时间= " + System.currentTimeMillis() + ", 此时的中断标志位：" + Thread.currentThread().isInterrupted());
        });

        System.out.println("主线程正在执行");
        thread.start();
        System.out.println("主线程等待，睡眠两秒");
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
