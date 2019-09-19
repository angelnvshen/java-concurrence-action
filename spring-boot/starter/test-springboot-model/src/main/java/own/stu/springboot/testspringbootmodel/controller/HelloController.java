package own.stu.springboot.testspringbootmodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ows.stu.springboot.simplespringbootstarterautoconfigurer.logAspect.ControllerLog;
import ows.stu.springboot.simplespringbootstarterautoconfigurer.service.HelloService;

@RestController
@RequestMapping
public class HelloController {

  @Autowired
  private HelloService helloService;

  @ControllerLog(module = "hello-starter-xjoix")
  @RequestMapping("hello-starter")
  public String hello(String message) {
    return helloService.hello(message);
  }
}
