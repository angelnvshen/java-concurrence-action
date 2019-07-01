package own.stu.springboot.hello.autoconfigurer.service.impl;

import own.stu.springboot.hello.autoconfigurer.model.Hello;
import own.stu.springboot.hello.autoconfigurer.service.HelloService;

//@Service
public class HelloServiceImpl implements HelloService {

//  @Autowired
  private Hello hello;

  public HelloServiceImpl(Hello hello) {
    this.hello = hello;
  }

  @Override
  public String sayHello(String name) {
    return String.format("%s-%s-%s", hello.getPrefix(), name, hello.getSuffix());
  }
}
