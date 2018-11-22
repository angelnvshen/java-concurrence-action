package own.stu.com.guava.controller;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cache")
public class LoadingCacheController {

  Logger LOGGER = LoggerFactory.getLogger(getClass());

  private Integer limit = 3;

  @Autowired
  private LoadingCache counter;

  /**
   * 判断是否需要报警
   */
  @RequestMapping("check")
  public void checkAlert(Long key) {
    try {
      AtomicLong value = (AtomicLong) counter.get(key);
      System.out.println("value : " + value);
      System.out.println("asMap : " + counter.asMap());
      System.out.println("size : " + counter.size());
      if (value.incrementAndGet() >= limit) {
        LOGGER.info("***********报警***********");

        //将缓存清空
        value.getAndSet(0L);
      }
    } catch (ExecutionException e) {
      LOGGER.error("Exception", e);
    }
  }

  private Random random = new Random(100);

  @RequestMapping("put")
  public void put(Long key) {
    try {
      counter.put(key, new AtomicLong(random.nextInt(100)));
      System.out.println("asMap : " + counter.asMap());
      System.out.println("size : " + counter.size());
    } catch (Exception e) {
      LOGGER.error("Exception", e);
    }
  }
}
