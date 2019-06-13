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
@ComponentScan(value = "own.stu.spring.springsource.less",
    includeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
    }, useDefaultFilters = false)

@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class BeanRegisterConfig {

  @Bean
  public Person person01() {
    return new Person("qin", 10);
  }

  // 默认 singleton，容器启动时创建对象，后续调用同一个对象时，从map中获取。
  // prototype ,容器启动时不会创建对象，调用时再创建对象，多次调用，创建多次。
//  @Scope("prototype")

  // @Lazy 容器启动时不会创建对象，调用时才创建
  @Scope("singleton")
  @Lazy
  @Bean
  public City city() {
    System.out.println("创建对象");
    return new City("多罗");
  }

  @Conditional({WindowsCondition.class})
  @Bean("bill")
  public Person person02() {
    return new Person("bill", 10);
  }

  @Conditional({MacCondition.class})
  @Bean("mac")
  public Person person03() {
    return new Person("mac", 10);
  }

  @Bean("myFactoryBean")
  public MyFactoryBean myFactoryBean(){
    return new MyFactoryBean();
  }

}
