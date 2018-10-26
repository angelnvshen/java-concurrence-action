package own.stu.java.concurrence.action.chapter_7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BrokenPrimeProducer extends Thread {

  private final BlockingQueue<BigInteger> queue;
  private volatile boolean cancelled = false;

  BrokenPrimeProducer(BlockingQueue<BigInteger> queue){
    this.queue = queue;
  }

  public void run() {

    try {

      BigInteger p = BigInteger.ONE;
      while (!cancelled) {
        queue.put(p.nextProbablePrime());
      }
      System.out.println(" ======= ");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void cancel(){
    cancelled = true;
  }

  static void consumePrimes() throws InterruptedException {
    BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(10);
    BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
    producer.start();
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println(primes.take());
    } finally {
      producer.cancel();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    consumePrimes();
  }

}
