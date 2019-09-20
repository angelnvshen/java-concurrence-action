package own.stu.redis.simpleredislock.config.standalone;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {

  /*@Bean
  public JedisConnectionFactory redisConnectionFactory() {

    String server = "172.16.2.163";
    Integer port = 6319;
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(server, port);
    return new JedisConnectionFactory(config);
  }*/

  @Value("${spring.redis.host}")
  private String redisAddress;

  @Value("${spring.redis.port}")
  private String redisAddressPort;

  @Bean
  public RedissonClient redissonClient() {

    // 1. Create config object
    Config config = new Config();
    config.useSingleServer()
        .setAddress("redis://" + redisAddress + ":" + redisAddressPort)
        .setDatabase(0);

    // 2. Create Redisson instance
    // Sync and Async API
    RedissonClient redisson = Redisson.create(config);
    return redisson;
  }
}
