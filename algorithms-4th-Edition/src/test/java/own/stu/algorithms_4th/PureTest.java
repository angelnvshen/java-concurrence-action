package own.stu.algorithms_4th;

import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.Random;
import org.junit.Test;

public class PureTest {

  @Test
  public void test() {
    int size = 10;
    IndexMinPQ<Integer> pq = new IndexMinPQ<>(size);

    Random random = new Random(100);
    for (int i = 0; i < size; i++) {
      int value = random.nextInt(100);
      System.out.println(value);
      pq.insert(i, value);
    }

    for (int i = 0; i < size; i++) {
      System.out.print(pq.delMin() + " - ");
    }
  }
}
