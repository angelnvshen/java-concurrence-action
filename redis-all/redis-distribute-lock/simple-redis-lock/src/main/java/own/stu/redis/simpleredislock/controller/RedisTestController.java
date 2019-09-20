package own.stu.redis.simpleredislock.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.redis.simpleredislock.util.SimpleRedisLock;

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

  @RequestMapping("sell-ticket")
  public String sellTicket() {
    String ticket = "ticket";
    String lock = "ticket-lock";
    Integer ticketNum = Integer.valueOf(redisTemplate.opsForValue().get(ticket));
    Boolean getLock = redisTemplate.opsForValue()
        .setIfAbsent(lock, UUID.randomUUID().toString(), 10, TimeUnit.SECONDS);
    if (!getLock) {
      return "not getLock";
    }
    if (ticketNum > 0) {
      System.out.println("剩余库存：" + (ticketNum - 1));
      redisTemplate.opsForValue().set(ticket, (ticketNum - 1) + "");
    } else {
      System.out.println("库存不足...");
    }
    redisTemplate.delete(lock);
    return "SUCCESS";
  }

  @Autowired
  private SimpleRedisLock simpleRedisLock;

  private AtomicInteger count = new AtomicInteger();

  String ticket = "ticket";

  @RequestMapping("sell-ticket-2")
  public String sellTicket2() {

    String lock = "ticket-lock";

    simpleRedisLock.lock(lock);
    Integer ticketNum = Integer.valueOf(redisTemplate.opsForValue().get(ticket));
    if (ticketNum > 0) {
      System.out.println(count.incrementAndGet() + ": 剩余库存：" + (ticketNum - 1));
      redisTemplate.opsForValue().set(ticket, (ticketNum - 1) + "");
    } else {
      System.out.println(count.incrementAndGet() + "库存不足...");
    }
    simpleRedisLock.unlock(lock);
    return "SUCCESS";
  }


  @Autowired
  private RedissonClient redissonClient;

  @RequestMapping("sell-ticket-3")
  public String sellTicket3() {

    String lock = "ticket-lock";

    RLock rLock = redissonClient.getLock(lock);
    rLock.lock();
    Integer ticketNum = Integer.valueOf(redisTemplate.opsForValue().get(ticket));
    if (ticketNum > 0) {
      System.out.println(count.incrementAndGet() + ": 剩余库存：" + (ticketNum - 1));
      redisTemplate.opsForValue().set(ticket, (ticketNum - 1) + "");
    } else {
      System.out.println(count.incrementAndGet() + "库存不足...");
    }
    rLock.unlock();
    return "SUCCESS";
  }

  @RequestMapping("reset-count")
  public String resetCount() {
    count.set(0);
    setValue(ticket, "100");
    return "SU";
  }
}
