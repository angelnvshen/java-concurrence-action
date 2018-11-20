package own.stu.com.redis.controller;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.com.redis.cache.service.IRedisCacheService;
import own.stu.com.redis.lock.RedisLock;
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

  @RequestMapping("lock")
  public String lock(User user) {
    if (user == null) {
      return "";
    }
    RedisLock lock = new RedisLock("test", "redis");
    try {
      lock.acquire(1, TimeUnit.MINUTES);
      redisCacheService.set(user.getUsername(), user.getPassword());
      return "success";
    }finally {
      lock.release();
    }
  }


}
