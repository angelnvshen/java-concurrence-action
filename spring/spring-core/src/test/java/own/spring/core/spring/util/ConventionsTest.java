package own.spring.core.spring.util;

import org.junit.Test;
import org.springframework.core.Conventions;

public class ConventionsTest {

  @Test
  public void test(){

    System.out.println(Conventions.attributeNameToPropertyName("org.spring-framework.core.convenTions"));
  }
}
