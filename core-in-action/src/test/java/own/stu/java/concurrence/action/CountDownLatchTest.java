package own.stu.java.concurrence.action;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2018/5/19.
 *
 * countDownLatch是一种灵活的闭锁实现，可以使一个或者多个线程等待一组事件发生。
 *  闭锁状态包括一个计数器，该计数器被初始化为一个正数，表示需要等待的事件数量。
 *  countDown方法递减计数器，表示有一个事件已经发生了，而await方法等待计数器
 *  到达零，表示所有需要等待的事件都已经发生。如果计数器的值费零，那么await会
 *  一直阻塞知道计数器为零，或者等待中线程中断，或者等待超时。
 */
public class CountDownLatchTest {

    /**
     * 方法中使用 countDownLatch的原因：计算方法耗时，不希望一开始线程就处于执行状态，而是在所有线程准备好，
     * 因为先启动的线程将“领先”于后启动的线程，并且活跃线程数量会随着时间的推移而增加或者减少，竞争程度也在
     * 不断的发生变化，所以需要主线程能够同时释放所有工作线程。
     */
    private static long timeTasks(int nThreads, Runnable runnable) throws InterruptedException {

        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0;i<nThreads;i++){
            new Thread(()->{
                try {
                    startGate.await();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endGate.countDown();
                }
            }).start();
        }

        long start = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(timeTasks(10, getRunnable()));
    }

    private static Runnable getRunnable(){
        return () -> {
            try {
                int sleepTime = new Random().nextInt(3);
                TimeUnit.SECONDS.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + ": " + sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    @Test
    public void test() throws InterruptedException {
        System.out.println(timeTasks(10, getRunnable()));
    }
}
