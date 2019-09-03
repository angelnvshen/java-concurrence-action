package own.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

  private int i = 0;

  private AtomicInteger atomicI = new AtomicInteger(0);

  public static void main(String[] args) {
    final Counter cas = new Counter();
    List<Thread> ts = new ArrayList<>(600);
    long start = System.currentTimeMillis();

    for (int t = 0; t < 100; t++) {
      Thread thread = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
          cas.safeCount();
          cas.count();
        }
      });
      ts.add(thread);
    }

    ts.forEach(thread -> thread.start());

    ts.stream().forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    System.out.println(cas.i);
    System.out.println(cas.atomicI.get());
    System.out.println(System.currentTimeMillis() - start);
  }

  private void safeCount() {
    for (; ; ) {
      int i = atomicI.get();
      if (atomicI.compareAndSet(i, ++i)) {
        break;
      }
    }
  }

  private void count() {
    i++;
  }
}
