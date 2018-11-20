package own.stu.com.redis.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.com.redis.model.User;
import own.stu.com.redis.service.ICacheService;

@RequestMapping("redis")
@RestController
public class RedisController{

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @RequestMapping("set")
  public String setTest(User user){
    if(user == null){
      return "";
    }

    stringRedisTemplate.opsForValue().set(user.getUsername(), user.getPassword());

    return "SUCCESS";
  }

  @Autowired
  ICacheService cacheService;

  @RequestMapping(value = "test2")
  public String test2() throws Exception {
    cacheService.set("mjt01","测试jedis");

    return cacheService.get("mjt01");
  }
}
