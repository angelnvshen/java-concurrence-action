package own.spring.core.spring.beanfactory;

import java.util.Arrays;
import org.junit.Test;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import own.spring.core.model.Car;
import own.spring.core.model.hsdb.A;
import own.spring.core.model.hsdb.B;

public class StaticListableBeanFactoryTest {

  @Test
  public void test() {
    StaticListableBeanFactory staticListableBeanFactory = new StaticListableBeanFactory();
    staticListableBeanFactory.addBean("ferri", new Car("ferri", 300));
    staticListableBeanFactory.addBean("fute", new Car("fute", 200));
    staticListableBeanFactory.addBean("b", new B());

    System.out.println(staticListableBeanFactory.containsBean("fute"));
    System.out.println(staticListableBeanFactory.containsBeanDefinition("fute"));
    System.out.println(staticListableBeanFactory.getBeanDefinitionCount());
    System.out.println(Arrays.asList(staticListableBeanFactory.getBeanNamesForType(Car.class)).toString());
    System.out.println(staticListableBeanFactory.isTypeMatch("b", A.class));
    System.out.println(staticListableBeanFactory.isTypeMatch("b", B.class));

  }
}
