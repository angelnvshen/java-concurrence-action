package own.spring.core.model.inject;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class JuiceObjectFactoryCreatingFactoryBean extends ObjectFactoryCreatingFactoryBean {

  public JuiceObjectFactoryCreatingFactoryBean() {
    setTargetBeanName("juice");
  }
}
