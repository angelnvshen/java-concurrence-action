package own.stu.com.redis.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import own.stu.com.redis.lock.constant.RedisConstants;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisLock {

  static private final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

  private static String REDIS_LOCK_PATH = "ls-common-utils:redislock";
  private static String host = "172.16.2.163";
  private static Integer port = 26319;
  private static Integer maxTotal = null;
  private static Integer minIdle = null;
  private static Integer maxIdle = null;
  private static Integer maxWaitMillis = null;
  private static Boolean testOnBorrow = null;
  private static Boolean testOnReturn = null;

  private String lockPath;

  private static final Object lock = new Object();
  private static ShardedJedisPool shardedJedisPool;
  private ShardedJedis shardedJedis = null;
  private String value = null;

  public RedisLock(String business, String key) {
    getShardedJedisPool();
    this.lockPath = String.format("%s:%s:%s", REDIS_LOCK_PATH, business, key);
    shardedJedis = shardedJedisPool.getResource();
  }

  public boolean acquire(long timeout, TimeUnit timeUnit) {

    try {
      long acquireTimeout = timeUnit.toNanos(timeout);

      while (acquireTimeout > 0) {
        value = String.valueOf(System.nanoTime() + timeUnit.toNanos(timeout) + 1);

        if (shardedJedis.setnx(lockPath, value) == 1) {
          return true;
        }

        if (compareAndSet(value)) {
          return true;
        }

        acquireTimeout = acquireTimeout - RedisConstants.DEFAULT_SLEEP_MILLIS;

        Thread.sleep(0, RedisConstants.DEFAULT_SLEEP_MILLIS);
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return false;
    }
    return false;
  }

  private boolean compareAndSet(String value) {
    String currentValue = shardedJedis.get(lockPath);
    boolean isExpire = currentValue != null
        && Long.valueOf(currentValue).longValue() < System.nanoTime();
    if (isExpire) {
      String oldValue = shardedJedis.getSet(lockPath, value);

      if (oldValue != null && oldValue.equals(currentValue)) {
        return true;
      }
    }
    return false;
  }

  public void release() {
    if (value != null && value.equals(shardedJedis.get(lockPath))) {
      shardedJedis.del(lockPath);
    }
    if (shardedJedis != null) {
      shardedJedis.close();
    }
  }

  private ShardedJedisPool getShardedJedisPool() {
    if (shardedJedisPool == null) {
      synchronized (lock) {
        if (shardedJedisPool == null) {
          shardedJedisPool = initShardedJedisPool();
        }
      }
    }

    return shardedJedisPool;
  }

  private static ShardedJedisPool initShardedJedisPool() {
    JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();

    List<JedisShardInfo> jedisShardInfoList = getJedisShardInfoList();

    ShardedJedisPool newShardedJedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfoList);

    return newShardedJedisPool;
  }

  private static List<JedisShardInfo> getJedisShardInfoList() {

    LOGGER.info("redis: host is {}, port is {}", host, port);

    JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);

    List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
    jedisShardInfoList.add(jedisShardInfo);

    return jedisShardInfoList;
  }

  private static JedisPoolConfig getJedisPoolConfig() {

    JedisPoolConfig config = new JedisPoolConfig();


    if (maxTotal == null) {
      maxTotal = RedisConstants.DEFAULT_MAX_TOTAL;
    }

    if (minIdle == null) {
      minIdle = RedisConstants.DEFAULT_MIN_IDLE;
    }
    if (maxIdle == null) {
      maxIdle = RedisConstants.DEFAULT_MAX_IDLE;
    }
    if (maxWaitMillis == null) {
      maxWaitMillis = RedisConstants.DEFAULT_MAXWAITMILLIS;
    }
    if (testOnBorrow == null) {
      testOnBorrow = RedisConstants.DEFAULT_ONBORROW;
    }
    if (testOnReturn == null) {
      testOnReturn = RedisConstants.DEFAULT_ONRETURN;
    }

    LOGGER.info("maxTotal is {}, minIdle is {}, maxIdle is {}, maxWaitMillis is {}, "
        + "testOnBorrow is {}, testOnReturn is {}"
        , maxTotal, minIdle, maxIdle, maxWaitMillis, testOnBorrow, testOnReturn);

    config.setMaxTotal(maxTotal);
    config.setMinIdle(minIdle);
    config.setMaxIdle(maxIdle);
    config.setMaxWaitMillis(maxWaitMillis);
    config.setTestOnBorrow(testOnBorrow);
    config.setTestOnReturn(testOnReturn);

    return config;
  }
}
