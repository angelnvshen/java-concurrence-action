package own.jdk;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {

  static ThreadLocal<Integer> value = ThreadLocal.withInitial(() -> 0);

  public static void main(String[] args) {
    for (int i = 0; i < 1; i++) {
      new Thread(() -> System.out.println(Thread.currentThread().getName() + " : " + ThreadId.get()), "thread-" + i).start();
    }
  }

  static class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.incrementAndGet());

    public static Integer get() {
      return threadId.get();
    }
  }

}
