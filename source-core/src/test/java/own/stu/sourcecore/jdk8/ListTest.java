package own.stu.sourcecore.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

public class ListTest {

  List<Integer> list;

  @Before
  public void testBefore(){
    Random random = new Random(10);

    list = Lists.newArrayList();
    for(int i = 0; i< 20; i ++)
      list.add(random.nextInt(200));
  }

  @Test
  public void testSubList() {

    List<String> list = Arrays.asList("1", "3", "3", "2");

    List<String> subList = list.subList(2,4);

    System.out.println(subList);
  }

  @Test
  public void testSpliterator() {

    System.out.println(list);

    Spliterator spliterator = list.spliterator();
    Spliterator spliterator1 = spliterator.trySplit();
    Spliterator spliterator2 = spliterator.trySplit();
    Spliterator spliterator3 = spliterator1.trySplit();

    List<Spliterator> spliteratorList =
        Arrays.asList(spliterator, spliterator2, spliterator1, spliterator3);

    ExecutorService service = Executors.newFixedThreadPool(4);
    for(Spliterator s : spliteratorList) {
      service.submit(() ->
          s.forEachRemaining((t) ->
              System.out.println(Thread.currentThread().getName() + " === " + t)));
    }
  }

  @Test
  public void testRemoveIf() {
    System.out.println(list);

    list.removeIf((num) -> num % 2 == 0);

    System.out.println(list);
  }
}
