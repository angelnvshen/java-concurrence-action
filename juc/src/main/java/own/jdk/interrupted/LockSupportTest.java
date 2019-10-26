package own.jdk.interrupted;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport就是通过控制变量_counter来对线程阻塞唤醒进行控制的。原理有点类似于信号量机制。
 * <p>
 * 1: 当调用park()方法时，会将_counter置为0，同时判断前值，小于1说明前面被unpark过,则直接退出，否则将使该线程阻塞。
 * 2: 当调用unpark()方法时，会将_counter置为1，同时判断前值，小于1会进行线程唤醒，否则直接退出。
 * <p>
 * 形象的理解，线程阻塞需要消耗凭证(permit)，这个凭证最多只有1个。当调用park方法时，如果有凭证，则会直接消耗掉这个凭证然后正常退出；
 * 但是如果没有凭证，就必须阻塞等待凭证可用；而unpark则相反，它会增加一个凭证，但凭证最多只能有1个。
 * <p>
 * 为什么可以先唤醒线程后阻塞线程？
 * 因为unpark获得了一个凭证,之后调用park因为有凭证消费，故不会阻塞。
 * <p>
 * 为什么唤醒两次后阻塞两次会阻塞线程。
 * 因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证；而调用两次park却需要消费两个凭证。
 */
public class LockSupportTest {

    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(" ----- ");
            LockSupport.park();
            System.out.println(" ----- " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("=====");
        LockSupport.unpark(thread);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(" ----- ");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" ----- " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println("=====");

    }
}
