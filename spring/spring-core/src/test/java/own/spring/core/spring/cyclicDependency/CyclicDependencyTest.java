package own.spring.core.spring.cyclicDependency;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.spring.core.config.cyclicDependency.BeanConfig;
import own.spring.core.part.cyclicDependency.Tea;
import own.spring.core.part.cyclicDependency.Water;

public class CyclicDependencyTest {

  @Test
  public void test() throws Exception {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);

    Tea tea = (Tea) applicationContext.getBean("tea");
    System.out.println(tea + " - water(" + tea.getWater() + ")");

    Water water = (Water) applicationContext.getBean("water");
    System.out.println(water + " - tea(" + water.getTea() + ")");

  }
}
