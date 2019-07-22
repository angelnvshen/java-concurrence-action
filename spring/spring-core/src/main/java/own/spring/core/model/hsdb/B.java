package own.spring.core.model.hsdb;

import java.io.Serializable;

public class B extends A implements Serializable, Cloneable {

  private String xx = "xx";
  private static Integer xxx = 1;
  private final String xxxx = "xxxx";

  @Override
  public void sayHello() {
    System.out.println("hello, i am child B");
  }

}