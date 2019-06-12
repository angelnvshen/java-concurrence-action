package own.stu.spring.springsource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import own.stu.spring.springsource.model.Person;

@Configuration
@ComponentScan("own.stu.spring.springsource.less")
public class BeanRegisterConfig {

  @Bean
  public Person person01(){
    return new Person("qin", 10);
  }
}
