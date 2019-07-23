package own.spring.core.spring.util;

import org.junit.Test;
import org.springframework.util.SerializationUtils;
import own.spring.core.model.Car;

public class SerializationUtilsTest {

  @Test
  public void test(){
    Car ferri1 = new Car("ferri", 320);
    Car ferri = (Car) SerializationUtils.deserialize(SerializationUtils.serialize(ferri1));
    System.out.println(ferri);
    System.out.println(ferri1);
  }
}
