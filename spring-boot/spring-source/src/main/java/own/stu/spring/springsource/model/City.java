package own.stu.spring.springsource.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class City implements Serializable {

  private String name;

  public City(String name) {
    this.name = name;
  }

  public City() {
  }
}
