package own.spring.core.spring.util;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import own.spring.core.config.InjectBeanConfig;

public class AnnotationUtilsTest {

  @Test
  public void test() {

    Configuration annotation = AnnotationUtils.findAnnotation(InjectBeanConfig.class, Configuration.class);
    System.out.println(annotation == null ? "" : annotation.annotationType());
  }
}
