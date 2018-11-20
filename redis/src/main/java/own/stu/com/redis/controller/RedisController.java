package own.stu.com.redis.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.com.redis.model.User;

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
}
