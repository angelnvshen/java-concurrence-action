package own.stu.spring.springsource;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.stu.spring.springsource.config.BeanAutoWireConfig;
import own.stu.spring.springsource.config.BeanProfileConfig;

public class ProfileTest {

  @Test
  public void test_profile() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

    annotationConfigApplicationContext.getEnvironment().addActiveProfile("dev");
    annotationConfigApplicationContext.register(BeanProfileConfig.class);
    annotationConfigApplicationContext.refresh();

    String[] beanNamesForType = annotationConfigApplicationContext.getBeanDefinitionNames();
    for (String name : beanNamesForType) {
      System.out.println(name);
    }

  }
}
