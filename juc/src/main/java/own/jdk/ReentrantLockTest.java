package own.jdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

  public static void main(String[] args) throws InterruptedException {

    ReentrantLock lock = new ReentrantLock();

    new Thread(() -> print(lock), "thread-1").start();
    TimeUnit.SECONDS.sleep(5);
    new Thread(() -> print(lock), "thread-2").start();
    TimeUnit.MINUTES.sleep(45);
    new Thread(() -> print(lock), "thread-3").start();

  }

  private static void print(Lock lock) {
    lock.lock();
    try {
      TimeUnit.HOURS.sleep(1);
      System.out.println(Thread.currentThread().getName() + "hello, world");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}


