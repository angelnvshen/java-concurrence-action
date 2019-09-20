package own.stu.redis.simpleredislock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("redis-hello")
@RestController
public class RedisTestController {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

//  @Resource(name = "redisTemplate")
//  private ValueOperations valueOperations;

  @RequestMapping("set")
  public String setValue(String key, String value) {
    Assert.notNull(key, "key is null");
    Assert.notNull(value, "value is null");
    redisTemplate.opsForValue().set(key, value);
    return "SUCCESS";
  }
}
