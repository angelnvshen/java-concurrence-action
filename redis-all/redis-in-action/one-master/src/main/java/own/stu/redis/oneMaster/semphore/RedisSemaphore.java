package own.stu.redis.oneMaster.semphore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
public class RedisSemaphore {

    private static String with_prefix = "semaphore:%s";
    private int limit;
    private int timeout = 10;
    private RedisTemplate<String, String> redisTemplate;

    public RedisSemaphore(int limit, RedisTemplate<String, String> redisTemplate) {
        this.limit = limit;
        this.redisTemplate = redisTemplate;
    }

    public RedisSemaphore(int limit, int timeout, RedisTemplate<String, String> redisTemplate) {
        this.limit = limit;
        this.timeout = timeout;
        this.redisTemplate = redisTemplate;
    }

    private String getSemaphore(String semaphoreName) {
        Assert.notNull(semaphoreName, "semaphoreName is null");
        return String.format(with_prefix, semaphoreName);
    }

    public String acquire(String semaphoreName) {
        String uuid = UUID.randomUUID().toString();
        long now = Instant.now().getEpochSecond();
        semaphoreName = getSemaphore(semaphoreName);

        String finalSemaphoreName = semaphoreName;

        List<Object> objects = redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                BoundZSetOperations zSetOperations = operations.boundZSetOps(finalSemaphoreName);
                zSetOperations.removeRangeByScore(0, now - timeout);
                zSetOperations.add(uuid, now);
                return zSetOperations.rank(uuid);
            }
        });

        if (objects != null) {
            if (objects.get(objects.size() - 1) != null &&
                    (Long) objects.get(objects.size() - 1) < limit) {
                return uuid;
            }
        }

        redisTemplate.boundZSetOps(semaphoreName).remove(uuid);
        return null;
    }

    public boolean release(String semaphoreName, String identifier) {
        semaphoreName = getSemaphore(semaphoreName);
        return redisTemplate.boundZSetOps(semaphoreName).remove(identifier) == 1;
    }
}
