package own.jdk.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueueDemo {

  public static void main(String[] args) throws InterruptedException {
    ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(10);
    for (int i = 0; i < 20; i++) {
      // integers.add(i); //java.lang.IllegalStateException: Queue full
      // integers.offer(i); // return false if this queue is full.
      integers.put(i); // waiting for space to become available if the queue is full.
    }
    System.out.println(integers);
  }
}
