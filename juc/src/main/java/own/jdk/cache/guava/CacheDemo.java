package own.jdk.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> build = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();

        try {
            System.out.println(build.get("x", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    TimeUnit.SECONDS.sleep(1);
                    return "hello";
                }
            }));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(4);
        System.out.println(build.asMap());

        System.out.println(build.getIfPresent("x"));
        System.out.println(build.getIfPresent("xx"));
    }
}
