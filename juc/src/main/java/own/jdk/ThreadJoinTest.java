package own.jdk;

import java.util.concurrent.TimeUnit;

public class ThreadJoinTest {

  public void normal() {
    System.out.println("hello ...... ");
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("world ...... ");

  }

  public void join(Thread threadJoin) {
    System.out.println("join in ....");
    try {
      threadJoin.start();
      threadJoin.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("join out ....");
  }

  public static void main(String[] args) {

    ThreadJoinTest test = new ThreadJoinTest();
    Thread normal = new Thread(() -> test.normal());
    new Thread(()-> test.join(normal)).start();
  }
}
