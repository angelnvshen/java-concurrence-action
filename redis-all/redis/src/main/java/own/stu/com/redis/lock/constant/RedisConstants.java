package own.stu.com.redis.lock.constant;

/**
 * Created by zhoulonghua on 2017/4/9.
 */
public class RedisConstants {

  public static final String REDIS_HOST_LION = "cache-service.redis.host";
  public static final String REDIS_HOST_PORT_LION = "cache-service.redis.port";
  public static final String REDIS_MAXWAIT_LION = "cache-service.redis.config.maxWaitMillis";
  public static final String REDIS_LOCK_PATH_LION = "ls-common-utils.lockUtils.redisLockPath";
  public static final String MAX_TOTAL = "cache-service.redis.config.maxTotal";
  public static final String MINIDLE = "cache-service.redis.config.minIdle";
  public static final String MAXIDLE = "cache-service.redis.config.maxIdle";
  public static final String TURE_ON_BORROW = "cache-service.redis.config.testOnBorrow";
  public static final String TURE_ON_RETURN = "cache-service.redis.config.testOnReturn";

  public static final int DEFAULT_SLEEP_MILLIS = 100;
  public static final int DEFAULT_MAX_TOTAL = 1000;
  public static final int DEFAULT_MIN_IDLE = 20;
  public static final int DEFAULT_MAX_IDLE = 100;
  public static final int DEFAULT_MAXWAITMILLIS = 2000;
  public static final boolean DEFAULT_ONBORROW = true;
  public static final boolean DEFAULT_ONRETURN = true;

  public static final long REDIS_LOCK_AUTO_RELEASE = 10;
}
