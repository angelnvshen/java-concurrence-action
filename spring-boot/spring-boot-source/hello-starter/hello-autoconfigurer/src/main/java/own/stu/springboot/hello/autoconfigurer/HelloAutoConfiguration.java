package own.stu.springboot.hello.autoconfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import own.stu.springboot.hello.autoconfigurer.model.Hello;
import own.stu.springboot.hello.autoconfigurer.service.HelloService;
import own.stu.springboot.hello.autoconfigurer.service.impl.HelloServiceImpl;

@ConditionalOnWebApplication
@EnableConfigurationProperties(Hello.class)
@Configuration
public class HelloAutoConfiguration {

  @Autowired
  private Hello hello;

  @Bean
  public HelloService helloService() {
    return new HelloServiceImpl(hello);
  }
}
