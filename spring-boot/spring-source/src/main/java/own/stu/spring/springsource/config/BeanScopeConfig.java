package own.stu.spring.springsource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import own.stu.spring.springsource.config.condition.MacCondition;
import own.stu.spring.springsource.config.condition.WindowsCondition;
import own.stu.spring.springsource.model.City;
import own.stu.spring.springsource.model.Color;
import own.stu.spring.springsource.model.Person;

@Configuration
@Import({MyBeanPostProcessor.class})
public class BeanScopeConfig {

  @Bean(initMethod="init", destroyMethod = "destroy_1")
  public City city() {
    System.out.println("调用创建city对象");
    return new City("多罗");
  }
}