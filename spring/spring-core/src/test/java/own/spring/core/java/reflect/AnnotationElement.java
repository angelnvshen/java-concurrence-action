package own.spring.core.java.reflect;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class AnnotationElement {

  @Test
  public void test() {

    Annotation[] declaredAnnotations = Configuration.class.getDeclaredAnnotations();
    Arrays.asList(declaredAnnotations).forEach(annotation -> {
      System.out.println(annotation.annotationType().getName());
    });
    System.out.println(" ======== ");
    Component[] declaredAnnotationsByType = Configuration.class.getDeclaredAnnotationsByType(Component.class);
    Arrays.asList(declaredAnnotationsByType).forEach(System.out::println);
  }
}
