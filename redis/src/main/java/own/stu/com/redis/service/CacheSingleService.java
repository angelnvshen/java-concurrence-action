package own.stu.com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class CacheSingleService implements ICacheService {

  @Autowired
  private JedisPool jedisPool;

  public String set(String key, String value) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.set(key, value);
    } finally {
      jedis.close();
    }
  }

  public String get(String key) {
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      return jedis.get(key);
    } finally {
      jedis.close();
    }
  }

  public Long incrBy(String key, long increament) {
    Long i = null;
    Jedis jedis = null;
    try {
      jedis = jedisPool.getResource();
      i = jedis.incrBy(key, increament);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      jedis.close();
    }
    return i;
  }
}