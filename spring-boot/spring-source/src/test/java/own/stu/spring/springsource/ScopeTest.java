package own.stu.spring.springsource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.stu.spring.springsource.config.BeanScopeConfig;
import own.stu.spring.springsource.model.City;

public class ScopeTest {

  @Test
  public void test_init_and_destroy() {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        BeanScopeConfig.class);

    City city = annotationConfigApplicationContext.getBean(City.class);
    System.out.println(city);

    annotationConfigApplicationContext.close();
  }
}
