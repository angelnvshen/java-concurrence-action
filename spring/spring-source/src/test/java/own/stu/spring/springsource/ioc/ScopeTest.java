package own.stu.spring.springsource.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.stu.spring.springsource.config.ioc.BeanScopeConfig;
import own.stu.spring.springsource.model.City;
import own.stu.spring.springsource.model.Color;

public class ScopeTest {

  @Test
  public void test_init_and_destroy() {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        BeanScopeConfig.class);

    City city = annotationConfigApplicationContext.getBean(City.class);
    System.out.println(city);

    annotationConfigApplicationContext.close();
  }

  @Test
  public void test_Value() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        BeanScopeConfig.class);

    Color color = annotationConfigApplicationContext.getBean(Color.class);
    System.out.println(color);
  }
}
