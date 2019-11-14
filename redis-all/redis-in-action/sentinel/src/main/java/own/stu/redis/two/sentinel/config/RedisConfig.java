package own.stu.redis.two.sentinel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {

   /* @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.master("mymaster")
                .sentinel("127.0.0.1", 21679)
                .sentinel("127.0.0.1", 22679)
                .sentinel("127.0.0.1", 23679);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration);

        return jedisConnectionFactory;
    }*/
}
