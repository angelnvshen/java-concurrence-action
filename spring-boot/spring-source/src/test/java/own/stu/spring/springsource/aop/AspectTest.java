package own.stu.spring.springsource.aop;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import own.stu.spring.springsource.config.aop.AspectConfig;
import own.stu.spring.springsource.config.ioc.BeanAutoWireConfig;
import own.stu.spring.springsource.less.service.MathCalculation;

public class AspectTest {

  @Test
  public void test_autowire() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
        AspectConfig.class);

    MathCalculation mathCalculation = annotationConfigApplicationContext.getBean(MathCalculation.class);
    mathCalculation.div(1, 0);

    annotationConfigApplicationContext.close();
  }
}
