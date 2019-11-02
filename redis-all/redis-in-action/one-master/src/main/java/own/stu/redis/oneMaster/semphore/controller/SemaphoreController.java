package own.stu.redis.oneMaster.semphore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import own.stu.redis.oneMaster.semphore.RedisSemaphore;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RequestMapping("sema")
@Slf4j
@RestController
public class SemaphoreController {

    private RedisTemplate<String, String> redisTemplate;

    private RedisSemaphore redisSemaphore;

    @Autowired
    public SemaphoreController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisSemaphore = new RedisSemaphore(5, redisTemplate);
    }

    @RequestMapping("acquire")
    public String testSemaphore(@RequestParam(value = "sema", defaultValue = "hello-world", required = false) String sema) throws InterruptedException {
        String identifier = redisSemaphore.acquire(sema);
        if (identifier != null) {
            return "success: " + identifier;
        } else {
            return "fail acquire semaphore";
        }
    }
}
