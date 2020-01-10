package own.stu.code.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQS {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("+++++++++");
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            sleep(1);
            print();
            lock.unlock();
        }, "t-1").start();


        new Thread(() -> {
            lock.lock();
            print();
            lock.unlock();
        }, "t-2").start();

        /*new Thread(() -> {
            lock.lock();
            print();
            lock.unlock();
        }, "t-3").start();*/

        Thread.currentThread().join();
    }

    private static void print() {
        System.out.println(" ============ " + Thread.currentThread().getName());
        try {
            sleep(60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int sec){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
