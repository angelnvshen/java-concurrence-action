### 1: 复习 spring-redis 连接方式

#### A: 单机模式

```ymal
spring.redis.host=172.16.2.163
spring.redis.port=6319
```

### 2:redis lock

#### A: simple redis lock

Lock : setnx 

```java
redisTemplate.opsForValue()
    .setIfAbsent(key, UUID.randomUUID().toString(), 10, TimeUnit.SECONDS)
```

Unlock:getAndDel （lua)

```java
"if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
```

坑：超时处理？redis服务器宕机？

### B: redission

