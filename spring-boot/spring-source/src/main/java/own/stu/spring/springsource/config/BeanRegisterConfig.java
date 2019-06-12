package own.stu.spring.springsource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import own.stu.spring.springsource.model.City;
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

  // 默认 singleton，容器启动时创建对象，后续调用同一个对象时，从map中获取。
  // prototype ,容器启动时不会创建对象，调用时再创建对象，多次调用，创建多次。
//  @Scope("prototype")

  // @Lazy 容器启动时不会创建对象，调用时才创建
  @Scope("singleton")
  @Lazy
  @Bean
  public City city(){
    System.out.println("创建对象");
    return new City("多罗");
  }
}
