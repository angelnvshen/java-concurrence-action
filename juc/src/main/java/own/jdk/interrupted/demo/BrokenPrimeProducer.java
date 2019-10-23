package own.jdk.interrupted.demo;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancel;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancel) {
            p = p.nextProbablePrime();
            try {
                queue.put(p);
                System.out.println(Thread.currentThread().getName() + "生产数字：" + p);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "线程中断");
                e.printStackTrace();
            }
        }
    }

    public void cancel() {
        cancel = true;
    }

    //不可靠的取消操作将把生产者置于阻塞的操作中
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(3);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(queue);
        producer.start();

        while (true) {
            System.out.println(Thread.currentThread().getName() + " 消费 " + queue.take());
            TimeUnit.MILLISECONDS.sleep(10);
            producer.cancel();
        }
//        TimeUnit.SECONDS.sleep(100);


    }
}
