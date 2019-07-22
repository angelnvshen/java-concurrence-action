package own.spring.core;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.core.ResolvableType;
import own.spring.core.model.Car;

public class ResolvableTypeTest {

  private HashMap<Integer, List<String>> myMap;

  @Test
  public void test() {
    try {
      ResolvableType resolvableType = ResolvableType.forField(getClass().getDeclaredField("myMap"));
      Type type = resolvableType.getType();
      System.out.println(type);
      ResolvableType superType = resolvableType.getSuperType();
      System.out.println(superType);
      ResolvableType resolvableType1 = resolvableType.asMap();
      System.out.println(resolvableType1);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }
}
