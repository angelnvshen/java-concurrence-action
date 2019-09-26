package own.spring.core.config.factoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import own.spring.core.part.factoryBean.MilkTea;
import own.spring.core.part.factoryBean.MilkTeaFactory;

@ComponentScan("own.spring.core.part.factoryBean")
public class BeanConfig {

  @Bean
  public MilkTeaFactory milkTeaFactory() {
    MilkTeaFactory factory = new MilkTeaFactory();
    return factory;
  }

  // @Bean(value = {"milkTea", "oneDot"}, name = {"milkTea", "oneDot"})
  // @Bean(value = {"milkTea", "oneDot", "milkTea1"})
  /*@Bean("milkTea")
  public MilkTea milkTea() {
    MilkTea tea = new MilkTea();
    tea.setName("LECHA");
    return new MilkTea();
  }*/
}
