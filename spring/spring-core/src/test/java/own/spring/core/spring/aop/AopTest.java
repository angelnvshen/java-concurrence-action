package own.spring.core.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.spring.core.config.aop.BeanConfig;
import own.spring.core.part.aop.service.HelloWorld;

public class AopTest {

  @Test
  public void test() {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
    HelloWorld object = (HelloWorld) applicationContext.getBean("helloWorld");
    System.out.println(object);
    object.sayHi("wof");
  }
}
