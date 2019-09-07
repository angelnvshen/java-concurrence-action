package own.spring.core.common;

import java.util.Arrays;

public class Util {

  public static <T> void printArrays(T[] array){
    Arrays.asList(array).forEach(System.out::println);
  }
}
