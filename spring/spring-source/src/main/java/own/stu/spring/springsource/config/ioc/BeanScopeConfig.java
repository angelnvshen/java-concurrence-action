package own.stu.spring.springsource.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import own.stu.spring.springsource.config.ioc.condition.MacCondition;
import own.stu.spring.springsource.config.ioc.condition.WindowsCondition;
import own.stu.spring.springsource.model.City;
import own.stu.spring.springsource.model.Color;

@Configuration
@Import({MyBeanPostProcessor.class, Color.class})
@PropertySource("classpath:property/keys.properties")
public class BeanScopeConfig {

  @Bean(initMethod="init", destroyMethod = "destroy_1")
  public City city() {
    System.out.println("调用创建city对象");
    return new City("多罗");
  }
}