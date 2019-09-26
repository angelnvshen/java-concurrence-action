package own.spring.core.part.cyclicDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Water {

  @Autowired
  private Tea tea;
  private String name = "pureWater";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Tea getTea() {
    return tea;
  }
}