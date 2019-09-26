package own.spring.core.part.cyclicDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tea {

  @Autowired
  private Water water;

  private String name = "tea";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Water getWater() {
    return water;
  }
}