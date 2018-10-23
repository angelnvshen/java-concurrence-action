package own.stu.jkd8.optional;

import java.util.Optional;
import org.junit.Test;

public class OptionalTest {

  @Test
  public void test1(){
    Optional<String> op = Optional.empty();
    if(op.isPresent())
      System.out.println(op.get());

    System.out.println(op.orElse(""));
    System.out.println(op.orElseGet(()->""));

    Optional<String> op2 = Optional.of("SNI");
    if(op2.isPresent())
      System.out.println(op2.get());

  }
}
