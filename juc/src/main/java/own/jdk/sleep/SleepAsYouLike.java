package own.jdk.sleep;

import java.util.concurrent.TimeUnit;

public class SleepAsYouLike {

  public static void main(String[] args) throws InterruptedException {
    Thread sleepThread = new Thread(new SleepThread());
    sleepThread.start();
    TimeUnit.SECONDS.sleep(3);
    sleepThread.interrupt();
  }

  static class SleepThread implements Runnable {

    @Override
    public void run() {
      int i = 0;
      System.out.println(Thread.currentThread().isInterrupted());
      while (!Thread.currentThread().isInterrupted() && i < 10) {
        try {
          System.out.println("sleep");
          sleep();
          i++;
        } catch (InterruptedException e) {
          // 1 :System.out.println("even interrupted.. i will go on ");
          System.out.println(" interrupted.. i quit ");
          Thread.currentThread().interrupt(); //重新设置中断标示
        }
      }
      System.out.println("out loop");
    }

    private void sleep() throws InterruptedException {
      Thread.currentThread().sleep(1000);
    }
  }
}
