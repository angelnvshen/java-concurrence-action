package own.stu.redis.simpleredislock.util;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SimpleRedisLock {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;
  ThreadLocal<String> valueThreadLocal = new ThreadLocal<>();

  public boolean lock(String key) {

    for (; ; ) {
      if (redisTemplate.opsForValue()
          .setIfAbsent(key, UUID.randomUUID().toString(), 10, TimeUnit.SECONDS)) {
        valueThreadLocal.set(redisTemplate.opsForValue().get(key));
        return true;
      }

      String value = redisTemplate.opsForValue().get(key);
      if (Objects.equals(valueThreadLocal.get(), value)) {
        return true;
      }
    }

  }

  /**
   * 释放锁成功返回值
   */
  private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;
  private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

  public boolean unlock(String key) {

    String value = valueThreadLocal.get();
    if (StringUtils.isEmpty(value)) {
      value = "";
    }
    String finalValue = value;
    return redisTemplate.execute(
        (RedisConnection connection) -> connection.eval(
            RELEASE_LOCK_LUA_SCRIPT.getBytes(),
            ReturnType.INTEGER,
            1,
            key.getBytes(),
            finalValue.getBytes())
    ).equals(RELEASE_LOCK_SUCCESS_RESULT);
  }
}
