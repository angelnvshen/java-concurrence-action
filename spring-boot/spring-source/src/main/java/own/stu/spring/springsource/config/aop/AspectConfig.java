package own.stu.spring.springsource.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import own.stu.spring.springsource.config.aop.aspect.LogAspect;
import own.stu.spring.springsource.less.service.MathCalculation;

@EnableAspectJAutoProxy
@Configuration
public class AspectConfig {

  @Bean
  public LogAspect logAspect() {
    return new LogAspect();
  }

  @Bean
  public MathCalculation mathCalculation() {
    return new MathCalculation();
  }
}
