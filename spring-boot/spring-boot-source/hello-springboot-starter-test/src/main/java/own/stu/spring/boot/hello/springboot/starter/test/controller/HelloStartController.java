package own.stu.spring.boot.hello.springboot.starter.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.springboot.hello.autoconfigurer.service.HelloService;

@RestController
public class HelloStartController {

  @Autowired
  private HelloService helloService;

  @RequestMapping("hello")
  public String hello(String name){

    return helloService.sayHello(name);
  }
}
