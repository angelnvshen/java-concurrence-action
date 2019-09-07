package own.spring.core.spring.util;

import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.springframework.core.CollectionFactory;

public class CollectionFactoryTest {

  @Test
  public void test(){
    Collection<Object> collection = CollectionFactory.createCollection(List.class, 10);
    System.out.println(collection);
  }
}
