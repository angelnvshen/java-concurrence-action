package own.spring.core.model.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Lazy
@Component
public class LazyComputer {

  private String name;

  @Autowired
  private ComputerScreen screen;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ComputerScreen getScreen() {
    return screen;
  }

  public void setScreen(ComputerScreen screen) {
    this.screen = screen;
  }
}
