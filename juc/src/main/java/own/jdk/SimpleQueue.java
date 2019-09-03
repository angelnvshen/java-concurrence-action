package own.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleQueue {

  private int max_capacity = 5;

  private List<String> queue = new ArrayList<>(max_capacity);

  private Lock lock = new ReentrantLock();

  Condition full = lock.newCondition();
  Condition empty = lock.newCondition();

  public void enq(String value) {
    lock.lock();
    try {

      while (queue.size() == max_capacity) {
        full.await();
      }

      queue.add(value);

      empty.signal();

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public String deq() {
    lock.lock();

    String result = null;
    try {

      while (queue.size() == 0) {
        empty.await();
      }

      Iterator<String> iterator = queue.iterator();

      while (iterator.hasNext()) {
        result = iterator.next();
        if (result != null) {
          queue.remove(result);
          System.out.println("deq -> " + result);
          full.signalAll();
          break;
        }
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return result;
  }

  public static void main(String[] args) {
    SimpleQueue queue = new SimpleQueue();

    new Thread(() -> {
      while (true){
        queue.deq();
      }
    }).start();

    new Thread(() -> {
      while (true){
        queue.deq();
      }
    }).start();

    AtomicInteger i = new AtomicInteger();
    new Thread(() -> {
      while (true){
        queue.enq(String.valueOf(i.incrementAndGet()));
      }
    }).start();

    AtomicInteger j = new AtomicInteger(10000);
    new Thread(() -> {
      while (true){

        queue.enq(String.valueOf(j.incrementAndGet()));
      }
    }).start();
  }

}
