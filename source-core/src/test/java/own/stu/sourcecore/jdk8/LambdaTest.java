package own.stu.sourcecore.jdk8;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.Data;
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