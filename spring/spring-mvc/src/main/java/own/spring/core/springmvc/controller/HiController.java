package own.spring.core.springmvc.controller;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Maps;

@RequestMapping
@RestController
public class HiController {

  @RequestMapping("hello")
  public String hello() {
    return "<html><body><h1>hello world</h1></body></html>";
  }

  @RequestMapping("hello-2")
  public String hello2() {
    return "success";
  }

  @RequestMapping("hello-3")
  public Map hello3() {
    Map<String, String> map = new HashMap<>();
    map.put("hello", "world");
    return map;
  }

  @RequestMapping("hello-4")
  public String hello4() {
    Map<String, String> map = new HashMap<>();
    map.put("hello", "world");
    return JSON.toJSONString(map);
  }
}
