package own.stu.com.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.com.redis.cache.service.IRedisCacheService;
import own.stu.com.redis.model.User;

@RequestMapping("redis")
@RestController
public class RedisController {

 @Autowired
 private IRedisCacheService redisCacheService;

  @RequestMapping("set")
  public String setTest(User user) {
    if (user == null) {
      return "";
    }

    redisCacheService.set(user.getUsername(), user.getPassword());
    return "success";
  }
}
