package own.stu.redis.withslave.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("sentinel")
@RestController
public class WriteToMasterReadFromReplicaController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("set")
    public String setKey(String key, String value) {
        Assert.notNull(key, "key is null");
        Assert.notNull(value, "value is null");

        redisTemplate.opsForValue().set(key, value);
        return "success";
    }

    @RequestMapping("get")
    public String getKey(String key) {
        Assert.notNull(key, "key is null");

        return redisTemplate.opsForValue().get(key);
    }
}
