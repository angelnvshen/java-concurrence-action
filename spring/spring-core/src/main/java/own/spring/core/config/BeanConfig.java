package own.spring.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import own.spring.core.service.MessageService;
import own.spring.core.service.impl.MessageServiceImpl;

@Configuration
public class BeanConfig {

  // bean的name 默认 方法名称
  @Bean
  public MessageService messageService() {
    return new MessageServiceImpl();
  }
}
