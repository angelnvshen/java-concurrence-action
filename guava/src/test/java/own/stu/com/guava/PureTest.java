package own.stu.com.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class PureTest {

  @Test
  public void test() {
    int i = 0, j = -1;
    Preconditions.checkArgument(true);
    Preconditions.checkArgument(false, "xxxx");
    Preconditions.checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
  }

  @Test
  public void test2() {
    System.out.println(Objects.equals("x", "x"));
    System.out.println(Objects.equals("x", null));
    System.out.println(Objects.equals(null, "x"));
    System.out.println(Objects.equals(null, null));
    System.out.println(Objects.hashCode("jpieojfiwefwef"));

    System.out.println(MoreObjects.toStringHelper(this)
        .add("xxx", 1)
        .toString()
    );
  }

  @Test
  public void testCache() throws ExecutionException {
    Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100).build();

    String resultVal = cache.get("key01", new Callable<String>() {
      public String call() throws Exception {
        return "key01-value";
      }
    });
    System.out.println(resultVal);
  }

  @Test
  public void testCache2() {
    LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder()
        .initialCapacity(10)
        .maximumSize(100)
        .recordStats()
        .concurrencyLevel(8)
        .expireAfterAccess(2, TimeUnit.SECONDS)
        .build(new CacheLoader<String, Object>() {

          @Override
          public Object load(String key) throws Exception {
            System.out.println(System.currentTimeMillis() + " 缓存失效。。。");
            return key + ": cache-value";
          }
        });

    /**获取缓存值，这里get方法会抛出ExecutionException异常，如果不想让他抛出异常使用getUnchecked方法*/
    System.out.println(loadingCache.getUnchecked("111"));
    /**打印缓存命中率*/
    System.out.println(loadingCache.stats().toString());
  }
}
