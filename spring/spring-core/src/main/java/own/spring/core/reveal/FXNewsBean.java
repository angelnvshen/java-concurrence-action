package own.spring.core.reveal;

public class FXNewsBean {

  private String name;

  public FXNewsBean(String name) {
    this.name = name;
  }

  public FXNewsBean() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "FXNewsBean{" +
        "name='" + name + '\'' +
        '}';
  }
}
