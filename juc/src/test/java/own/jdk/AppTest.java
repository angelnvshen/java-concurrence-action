package own.jdk;

import org.junit.Test;

import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.IdentityHashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {

    assertTrue(true);
    System.out.println(0xf0000000);
    System.out.println(0xc0000000);
    System.out.println(0x80000000);
    System.out.println(0x00010000);
    System.out.println(0x0000ffff);
    System.out.println(Integer.MAX_VALUE);
    System.out.println(Integer.MIN_VALUE);

  }

  @Test
  public void test() {

    Map<String, String> map = new IdentityHashMap<>();
    String a = "asb";
    map.put(new String("a"), "1");
    map.put(new String("a"), "2");
    map.put(new String("a"), "3");
    map.put(a, "4");

    System.out.println(map.size());
    System.out.println(map.get(a));

    int i = 1_00_0000;

  }

  static class Demo implements Serializable {

    private int i;

    public Demo(int i) {
      this.i = i;
    }

    @Override
    public String toString() {
      return "Demo{" +
          "i=" + i +
          '}';
    }
  }

  @Test
  public void test2() {

    ReferenceQueue<Demo> rq = new ReferenceQueue<>();
    Demo demo = new Demo(1);
    SoftReference<Demo> reference = new SoftReference<>(demo, rq);

    SoftReference ref = null;
    while ((ref = (SoftReference) rq.poll()) != null) {
      Object o = ref.get();
      System.out.println(o);
    }


  }
}

