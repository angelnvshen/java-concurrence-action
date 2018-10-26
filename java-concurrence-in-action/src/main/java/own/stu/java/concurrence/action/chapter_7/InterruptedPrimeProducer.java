package own.stu.java.concurrence.action.chapter_7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class InterruptedPrimeProducer extends Thread {

  private final BlockingQueue<BigInteger> queue;

  InterruptedPrimeProducer(BlockingQueue<BigInteger> queue){
    this.queue = queue;
  }

  public void run() {

    try {

      BigInteger p = BigInteger.ONE;
      while (!Thread.currentThread().isInterrupted()) {
        queue.put(p.nextProbablePrime());
      }
      System.out.println(" ======= ");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void cancel(){
    interrupt();
  }

  static void consumePrimes() throws InterruptedException {
    BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(10);
    InterruptedPrimeProducer producer = new InterruptedPrimeProducer(primes);
    producer.start();
    try {
      TimeUnit.SECONDS.sleep(1);
      System.out.println(primes.take());
      System.out.println("quit");
    } finally {
      producer.cancel();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    consumePrimes();
  }

}
