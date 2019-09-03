package own.jdk.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    User user = new User("100");
    ReferenceQueue<User> q = new ReferenceQueue<>();
    WeakReference<User> reference = new WeakReference<>(user, q);

    System.out.println(reference.get());
    System.out.println(reference.isEnqueued());

    user = null;
    System.out.println(reference.get());
    System.out.println(reference.isEnqueued());

    System.gc();
//    TimeUnit.SECONDS.sleep(1);
    System.out.println(reference.get());
    System.out.println(reference.isEnqueued());
    System.out.println(q.poll());
  }
}










