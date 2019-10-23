package own.jdk.interrupted.demo;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    private volatile boolean cancel;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger one = BigInteger.ONE;
        try {
            while (!Thread.currentThread().interrupted()) {
                one = one.nextProbablePrime();
                queue.put(one);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 线程中断了");
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }

    public void cancel() {
        interrupt();
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(3);
        PrimeProducer producer = new PrimeProducer(queue);
        producer.start();

        while (true) {
            BigInteger integer = queue.poll(1, TimeUnit.SECONDS);
            if (integer == null) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + " 消费 " + integer);
//            TimeUnit.MILLISECONDS.sleep(10);
            producer.cancel();
        }
    }
}
