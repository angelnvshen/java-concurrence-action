package own.spring.core.model;

import java.io.Serializable;

public class Car implements Serializable {

  private String name;

  private Integer speed;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSpeed() {
    return speed;
  }

  public void setSpeed(Integer speed) {
    this.speed = speed;
  }

  public void init() {
    System.out.println("Book -> init .... ");
  }

  public void destroy() {
    System.out.println("Book -> destroy .... ");
  }

  public Car() {
  }

  public Car(String name, Integer speed) {
    this.name = name;
    this.speed = speed;
  }
}
