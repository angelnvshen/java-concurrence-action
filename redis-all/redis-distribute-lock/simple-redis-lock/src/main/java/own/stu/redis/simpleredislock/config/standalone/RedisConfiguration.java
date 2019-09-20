package own.stu.redis.simpleredislock.config.standalone;

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
}
