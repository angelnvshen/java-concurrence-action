package own.stu.com.redis.cache.impl;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import own.stu.com.redis.cache.redis.RedisUtil;
import own.stu.com.redis.cache.service.IRedisCacheService;
import redis.clients.jedis.Jedis;

/**
 * Created by walker on 16/3/26.
 */
@Service
public class RedisCacheServiceImpl implements IRedisCacheService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

  @Autowired
  RedisUtil redisUtil;

  @Override
  public String getKey(String key) {

    String value = "";
    Jedis client = null;

    try {
      client = redisUtil.getJedis();
      if (client.exists(key)) {
        value = client.get(key);
      }


    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return value;
  }


  /**
   * 自增
   */
  public long incr(String key) {
    Jedis client = null;

    long result = -1;

    try {
      client = redisUtil.getJedis();
      result = client.incr(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  public Long expire(String key, int seconds) {

    Jedis client = null;
    long result = -1;

    try {
      client = redisUtil.getJedis();
      result = client.expire(key, seconds);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Boolean setValue(String key, String value, int seconds) {

    Jedis client = null;
    Boolean result = false;

    try {
      client = redisUtil.getJedis();
      if (seconds > 0) {
        client.setex(key, seconds, value);
      } else {
        client.set(key, value);
      }


    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Long deleteKey(String key) {

    Long result = null;
    Jedis client = null;

    try {
      client = redisUtil.getJedis();
      result = client.del(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Boolean exists(String key) {

    Boolean isExisted = null;
    Jedis client = null;

    try {
      client = redisUtil.getJedis();
      isExisted = client.exists(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return isExisted;
  }

  @Override
  public void sadd(String key, String value, int seconds) {

    Jedis client = null;
    try {
      client = redisUtil.getJedis();
      long existsCount = scard(key);
      long result = client.sadd(key, value);
      if (seconds > 0) {
        client.expire(key, seconds);
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }
  }

  @Override
  public Set<String> smembers(String key) {

    Jedis client = null;

    Set<String> result = null;

    try {
      client = redisUtil.getJedis();
      result = client.smembers(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Long scard(String key) {

    Jedis client = null;

    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.scard(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Boolean sismember(String key, String member) {

    Jedis client = null;

    boolean result = false;

    try {
      client = redisUtil.getJedis();
      result = client.sismember(key, member);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public String spop(String key) {

    Jedis client = null;

    String result = null;

    try {
      client = redisUtil.getJedis();
      result = client.spop(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Long hset(String key, String field, String value, int seconds) {

    Jedis client = null;

    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.hset(key, field, value);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }
    }

    return result;
  }

  @Override
  public Set<String> hkeys(String key) {

    Jedis client = null;
    Set<String> result = null;

    try {
      client = redisUtil.getJedis();
      result = client.hkeys(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Boolean hexists(String key, String field) {

    Jedis client = null;
    Boolean result = null;

    try {
      client = redisUtil.getJedis();
      result = client.hexists(key, field);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public String hget(String key, String field) {

    Jedis client = null;
    String result = null;

    try {
      client = redisUtil.getJedis();
      result = client.hget(key, field);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Long hdel(String key, String field) {

    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.hdel(key, field);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 在key对应list的头部添加字符串元素，返回1表示成功，0表示key存在且不是list类型
   */
  public long lpush(String key, String... values) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.lpush(key, values);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  public long rpush(String key, String... values) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.rpush(key, values);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 返回key对应list的长度，key不存在返回0,如果key对应类型不是list返回错误
   */
  public long llen(String key) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();
      result = client.llen(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 返回指定区间内的元素，下标从0开始，负值表示从后面计算，-1表示倒数第一个元素 ，key不存在返回空列表
   */
  public List<String> lrange(String key, long start, long end) {
    Jedis client = null;
    List<String> result = null;

    try {
      client = redisUtil.getJedis();
      result = client.lrange(key, start, end);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }
    return result;
  }

  /**
   * 截取list，保留指定区间内元素，成功返回1，key不存在返回错误
   */
  public String ltrim(String key, long start, long end) {
    Jedis client = null;
    String result = null;

    try {
      client = redisUtil.getJedis();
      result = client.ltrim(key, start, end);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }
    return result;

  }

  @Override
  public String set(String key, String value) {

    Jedis client = null;
    String result = null;

    try {
      client = redisUtil.getJedis();

      result = client.set(key, value);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  @Override
  public Long ttl(String key) {

    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();

      result = client.ttl(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 添加元素
   */
  public Long zadd(String key, double score, String member) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zadd(key, score, member);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 删除元素
   */
  public Long zrem(String key, String... member) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zrem(key, member);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 查看元素的权重
   */
  public Double zscore(String key, String member) {
    Jedis client = null;
    Double result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zscore(key, member);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 集合中的元素
   */
  public Long zcard(String key) {
    Jedis client = null;
    Long result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zcard(key);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 查看下标范围的元素信息
   */
  public Set<String> zrange(String key, long start, long end) {
    Jedis client = null;
    Set<String> result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zrange(key, start, end);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }

  /**
   * 查看score范围的元素信息
   */
  public Set<String> zrangeByScore(String key, double start, double end) {
    Jedis client = null;
    Set<String> result = null;

    try {
      client = redisUtil.getJedis();

      result = client.zrangeByScore(key, start, end);

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();

    } finally {
      if (client != null) {
        client.close();
      }

    }

    return result;
  }
}
