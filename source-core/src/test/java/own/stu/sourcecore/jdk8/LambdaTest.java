package own.stu.sourcecore.jdk8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.Test;

public class LambdaTest {

  @Test
  public void testSupplier() {
    Supplier<Apple> supplier = Apple::new;
    Apple apple = supplier.get();
    System.out.println(apple);

    Supplier<Apple> supplier2 = ()-> new Apple();
    System.out.println(supplier2.get());
  }

  @Test
  public void testFunction(){
    Function<Integer, Apple> function = Apple::new ;
    System.out.println(function.apply(1));

    Function<Integer, Apple> function2 = (weight) -> new Apple(weight);
    System.out.println(function2.apply(20));
  }

  @Test
  public void testBiFunction(){
    BiFunction<Integer, String, Apple> function = Apple::new ;
    System.out.println(function.apply(1, "BLUE"));

    BiFunction<Integer, String, Apple> function2 = (weight, color) -> new Apple(weight, color);
    System.out.println(function2.apply(20, "GREEN"));
  }

  static List<String> list;
  static {
    list = Lists.newArrayList("H", "E", "L", "L", "O");
  }

  @Test
  public void testCollector(){
    List<String> result = list.stream()
        .collect(Collectors.toList());
    System.out.println(result);

    String strResult = list.stream()
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
        .toString();
    System.out.println(strResult);

    strResult = list.stream()
        .collect(() -> new StringBuilder(), (l, x) -> l.append(x), (l1, l2) -> l1.append(l2))
        .toString();
    System.out.println(strResult);

    Integer[] resultInteger = Lists.newArrayList(1, 2, 3, 4, 5).stream()
        .collect(() -> new Integer[]{0}, (a, x) -> a[0] += x, (a1, a2) -> a1[0] += a2[0]);
    System.out.println(resultInteger[0]);

    Map<String, List<Apple>> mapResult = Lists.newArrayList(new Apple(10, "RED"),
        new Apple(20, "GREEN"),
        new Apple(22, "GREEN"),
        new Apple(30, "YELLOW"),
        new Apple(35, "YELLOW")).stream()
        .collect(() -> new HashMap<>(), (map, apple) -> {
          List<Apple> appleList = map.getOrDefault(apple.getColor(), Lists.newArrayList());
          appleList.add(apple);
          map.put(apple.getColor(), appleList);
        }, (map1, map2) -> map1.putAll(map2));

    System.out.println(mapResult);

  }

  @Data
  class Apple {

    private int weight = 10;
    private String color = "RED";

    public Apple() {
    }

    public Apple(int weight) {
      this.weight = weight;
    }

    public Apple(int weight, String color) {
      this.weight = weight;
      this.color = color;
    }
  }

}