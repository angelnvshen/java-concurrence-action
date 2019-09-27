package own.spring.core.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import own.spring.core.part.aop.AnnotationAopCode;
import own.spring.core.part.aop.service.HelloWorld;
import own.spring.core.part.aop.service.impl.HelloWorldImpl;
import own.spring.core.part.factoryBean.MilkTeaFactory;

@EnableAspectJAutoProxy
@ComponentScan("own.spring.core.part.aop")
public class BeanConfig {

  @Bean
  public HelloWorld helloWorld() {
    HelloWorld h = new HelloWorldImpl();
    return h;
  }

  @Bean
  public AnnotationAopCode annotationAopCode() {
    return new AnnotationAopCode();
  }
}
