package own.jdk.sleep;

public class InterruptedSleep {

  public static void main(String[] args) throws InterruptedException {
    Thread sleep = new Thread(new SleepThread());
    sleep.start();

    Thread.currentThread().sleep(1000);
    sleep.interrupt();
    System.out.println("sleepThread isInterrupted: " + sleep.isInterrupted());
  }

  static class SleepThread implements Runnable {

    @Override
    public void run() {
      try {
        System.out.println("go to sleep ");
        System.out.println(Thread.currentThread().getName() + " Interrupted " + Thread.currentThread().isInterrupted());
        Thread.currentThread().sleep(20000);
      } catch (InterruptedException e) {
        System.out.println(Thread.currentThread().getName() + " Interrupted " + Thread.currentThread().isInterrupted());
      }
      System.out.println("after interrupted: i will go on");
      for (int i = 0; i < 10; i++) {
        System.out.print(i + "->");
      }
    }
  }
}
