package own.spring.core.spring.other;

import org.junit.Test;
import org.springframework.core.SimpleAliasRegistry;

public class SimpleAliasRegistryTest {

  @Test
  public void test() {
    SimpleAliasRegistry registry = new SimpleAliasRegistry();
    registry.registerAlias("x1", "ferri");
//    registry.registerAlias("x1", "ferri");
//    registry.registerAlias("x2", "ferri");
    registry.registerAlias("ferri", "x1");

//    registry.canonicalName()
  }
}
