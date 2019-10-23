package own.jdk.interrupted;

import own.jdk.SleepUtils;

import java.util.concurrent.TimeUnit;

public class Interrupted {

  public static void main(String[] args) throws Exception {
    // sleepThread
    Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
    sleepThread.setDaemon(true);
    // busyThread
    Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
    busyThread.setDaemon(true);
    sleepThread.start();
    busyThread.start();
    //   5   sleepThread busyThread
    TimeUnit.SECONDS.sleep(5);
    sleepThread.interrupt();
    busyThread.interrupt();
    System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
    System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
    //   防止sleepThread busyThread中断后立即退出
    SleepUtils.second(2);
  }

  static class SleepRunner implements Runnable {

    @Override
    public void run() {
      while (true) {
        SleepUtils.second(100);
      }
    }
  }

  static class BusyRunner implements Runnable {

    @Override
    public void run() {
      while (true) {
      }
    }
  }
}