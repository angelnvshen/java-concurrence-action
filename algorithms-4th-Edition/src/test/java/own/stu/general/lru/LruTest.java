package own.stu.general.lru;

import java.util.Random;
import org.junit.Test;

public class LruTest {

  @Test
  public void test() {
    LRUCache cache = new LRUCache(10);
    Random random = new Random();
    for (int i = 0; i < 20; i++) {
      int value  = random.nextInt(20);
      cache.set(value + "", value);
      cache.print();
    }

  }


}
