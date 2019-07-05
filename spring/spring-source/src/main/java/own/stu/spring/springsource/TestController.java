package own.stu.spring.springsource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

  @RequestMapping()
  public String test(){
    return "SUCCESS";
  }
}
