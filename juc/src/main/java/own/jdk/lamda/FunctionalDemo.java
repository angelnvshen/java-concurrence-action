package own.jdk.lamda;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.function.Consumer;

public class FunctionalDemo {

  public static void main(String[] args) {
    ArrayList<Integer> list = Lists.newArrayList(1, 2, 5, 5, 1);
    Consumer<Integer> consumer = x -> {
      System.out.println("------");
    };
    consumer = consumer.andThen(x -> {
      System.out.println("******");
    });
    list.stream().forEach(consumer);
  }
}
