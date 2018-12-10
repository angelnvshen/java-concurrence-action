package own.stu.com.plugin.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.com.plugin.demo.annotation.StatisticsTime;

@StatisticsTime
@RestController
@RequestMapping
public class HelloController {

  @RequestMapping
  public String hello(){
    return "hello";
  }
}
