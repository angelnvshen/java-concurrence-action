package own.spring.core.part.aop.service.impl;

import own.spring.core.part.aop.service.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

  public void sayHi(String message) {
    System.out.println(message);
  }
}
