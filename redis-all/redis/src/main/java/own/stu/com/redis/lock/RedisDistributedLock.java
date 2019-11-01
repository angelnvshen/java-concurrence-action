package own.stu.com.redis.lock;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import redis.clients.jedis.Jedis;

public class RedisDistributedLock implements Lock {

  private static String lock_name = "LOCK";

  private ThreadLocal<String> lockContext = new ThreadLocal<>();

  //默认超时时间
  private long time = 100;

  @Override
  public void lock() {
    while (!tryLock()){
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    if(Thread.interrupted())
      throw new InterruptedException();

    while (!tryLock())
      TimeUnit.MILLISECONDS.sleep(100);
  }

  @Override
  public boolean tryLock() {
   return tryLock(time, TimeUnit.MILLISECONDS);
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit){
    String id = UUID.randomUUID().toString();
    Jedis jedis = null; //RedisClient.getClient();
    Thread t = Thread.currentThread();

    if(jedis.setnx(lock_name, id) == 1){
      jedis.pexpire(lock_name, unit.toMillis(time));
      lockContext.set(id);
//      setExclusiveOwnerThread(t);
      return true;
//    }else if(exclusiveOwnerThread == t){ //当前线程获取锁，可重入
//      return true;
    }
    return false;
  }

  @Override
  public void unlock() {
    Jedis jedis = null; //RedisClient.getClient();
    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    Object result = jedis.eval(script, Collections.singletonList(lock_name), Collections.singletonList(lockContext.get()));
    lockContext.remove();
  }

  @Override
  public Condition newCondition() {
    return null;
  }
}
