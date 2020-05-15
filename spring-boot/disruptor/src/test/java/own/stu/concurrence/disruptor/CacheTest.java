package own.stu.concurrence.disruptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class CacheTest {

    @Test
    public void test() {
        Cache<Object, Object> build = CacheBuilder.newBuilder()
                .concurrencyLevel(16)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(20)
                .build();
        build.put("hello", "world");

        System.out.println(build.getIfPresent("hello"));
        System.out.println(build.getIfPresent("hello1"));
    }
}
