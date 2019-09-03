package own.jdk;

import java.util.Arrays;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OwnSyn extends AbstractQueuedSynchronizer {

  public void abd() {

  }

  static final int SHARED_SHIFT   = 16;
  static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
  static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
  static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

  public static void main(String[] args) throws Exception {
    /*Class<?>[] declaredClasses = AbstractQueuedSynchronizer.class.getDeclaredClasses();
    for (Class aClass : declaredClasses) {
      if (aClass.getName().contains("AbstractQueuedSynchronizer$Node")) {
        Object newInstance = aClass.newInstance();
        System.out.println(newInstance);
      }
    }*/

    System.out.println(SHARED_SHIFT);
    System.out.println(SHARED_UNIT);
    System.out.println(MAX_COUNT);
    System.out.println(EXCLUSIVE_MASK);

    System.out.println(sharedCount(65536 * 8));
  }

  static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
}
