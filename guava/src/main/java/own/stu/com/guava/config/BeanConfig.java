package own.stu.com.guava.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {

  @Value("${alert.in.time:2}")
  private int time ;

  /**
   * CacheLoader 的定义比较宽泛， 是针对整个cache定义的，可以认为是统一的根据key值load value的方法
   * @return
   */
  @Bean
  public LoadingCache<Long, AtomicLong> buildCache() {
    LoadingCache<Long, AtomicLong> cache = CacheBuilder.newBuilder()
        .expireAfterWrite(time, TimeUnit.SECONDS) // 给定时间内没有被读/写访问，则回收。
        .build(new CacheLoader<Long, AtomicLong>() {
          /**
           * 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
           **/
          @Override
          public AtomicLong load(Long key) throws Exception {
            return new AtomicLong(0);
          }
        });

    return cache;
  }

  /**
   * callable的方式较为灵活，允许你在get的时候指定。
   *
   * {@link own.stu.com.guava.PureTest}
   *
   * @see own.stu.com.guava.PureTest #testCallableCache
   *
   * @see 标签允许用户引用其他类的文档。
   * 这个只能单独一行顶头写，如果不顶头写就不管用了，没了链接的效果。
   * 但是，{@link}这个却可以随便放。
   */
}
