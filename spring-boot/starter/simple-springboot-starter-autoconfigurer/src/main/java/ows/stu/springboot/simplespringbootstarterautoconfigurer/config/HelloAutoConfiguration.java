package ows.stu.springboot.simplespringbootstarterautoconfigurer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ows.stu.springboot.simplespringbootstarterautoconfigurer.service.HelloService;

@ConditionalOnWebApplication
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

  @Autowired
  private HelloProperties helloProperties;

  @Bean
  public HelloService getHelloService() {
    HelloService helloService = new HelloService();
    helloService.setHelloProperties(helloProperties);
    return helloService;
  }
}
