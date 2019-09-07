package own.jdk;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

  public static void main(String[] args) throws InterruptedException {

    int n = 20;

    CountDownLatch driver = new CountDownLatch(1);
    CountDownLatch worker = new CountDownLatch(n);

    for (int i = 0; i < 2*n; i++) {
      new Thread(new Worker(driver, worker)).start();
    }
    System.out.println("ready the driver ... ");
    driver.countDown();
    System.out.println("worker will start ...");
    worker.await();
    System.out.println("worker done ");
  }


  static class Worker implements Runnable {

    private CountDownLatch driver;
    private CountDownLatch worker;

    public Worker(CountDownLatch driver, CountDownLatch worker) {
      this.driver = driver;
      this.worker = worker;
    }

    @Override
    public void run() {
      try {
        driver.await();

        System.out.println(Thread.currentThread().getName() + " -> " + "working");

        worker.countDown();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
