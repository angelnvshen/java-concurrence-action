package own.jdk.sleep;

public class InterruptTest {

  public static void main(String[] args) throws Exception {
    for (int i = 1; i <= 500; i++) {
      Thread t = new Interrupt();
      t.setName("thread" + i);
      t.start();
    }
    Thread.currentThread().join();
  }

  private static class Interrupt extends Thread {

    public void run() {
      Thread t = new Sleep();
      t.setName(getName() + "-sleep");
      t.start();
      t.interrupt();
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(System.currentTimeMillis() + " " + t.getName() + " " + t.isInterrupted());
    }
  }

  private static class Sleep extends Thread {

    public void run() {
      try {
        Thread.sleep(100000);
      } catch (Exception e) {
        return;
      }
      System.err.println(System.currentTimeMillis() + " No! " + getName() + " " + isInterrupted());
    }
  }

}