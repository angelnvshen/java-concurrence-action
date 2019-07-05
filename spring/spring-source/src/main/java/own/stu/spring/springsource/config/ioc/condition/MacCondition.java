package own.stu.spring.springsource.config.ioc.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

    // 获取ioc beanFactory
    ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

    // 获取 当前类的类加载器
    ClassLoader classLoader = context.getClassLoader();

    // 获取 类的注册器
    BeanDefinitionRegistry beanDefinitionRegistry = context.getRegistry();

    // 获取当前系统环境变量
    Environment environment = context.getEnvironment();
    String osName = environment.getProperty("os.name");
    if (osName.contains("Mac")) {
      return true;
    }
    return false;
  }
}
