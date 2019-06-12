package own.stu.spring.springsource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import own.stu.spring.springsource.model.Person;

@Configuration
@ComponentScan(value = "own.stu.spring.springsource.less",
    includeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
    }, useDefaultFilters = false)
public class BeanRegisterConfig {

  @Bean
  public Person person01(){
    return new Person("qin", 10);
  }
}
