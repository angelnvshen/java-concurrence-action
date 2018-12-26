package own.stu.sourcecore.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;

public class SortTest {

  @Test
  public void test() {
    List<String> list = Arrays.asList("1", "3", "3", "2");
    list.add("4"); //java.lang.UnsupportedOperationException, reason : Arrays 内部有个私有的静态的内部类 ArrayList
    System.out.println(list);
  }
  @Test
  public void test1() {
    List<String> list = Arrays.asList("1", "3", "3", "2");
    /*list.sort(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });*/

    /*list.sort((o1, o2) -> o1.compareTo(o2));*/

    /*list.sort(String::compareTo);*/

    list.sort(Comparator.naturalOrder());

    System.out.println(list);
  }
}