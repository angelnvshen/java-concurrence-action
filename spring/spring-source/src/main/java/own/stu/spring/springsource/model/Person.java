package own.stu.spring.springsource.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Person implements Serializable {

  private String name;

  private Integer age;

  public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public Person() {
  }
}
