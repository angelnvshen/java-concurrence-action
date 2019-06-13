package own.stu.spring.springsource.config.ioc.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    Environment environment = context.getEnvironment();
    String osName = environment.getProperty("os.name");
    if (osName.contains("Windows")) {
      return true;
    }
    return false;
  }
}
