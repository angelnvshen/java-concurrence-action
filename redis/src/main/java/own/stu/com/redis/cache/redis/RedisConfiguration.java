package own.stu.com.redis.cache.redis;

import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.sentinel.master}")
	private String sentinelMasterName ;
	@Value("${spring.redis.sentinel.nodes}")
	private String hostAndPorts;
	private Integer maxIdle = 	40;
	private Integer minIdl = 8;
	private Integer maxTotal = 500;

	@Bean
	public MkJedisConnectionFactory connectionFactory() {

		Set<String> sentinelHostAndPorts = StringUtils.commaDelimitedListToSet(hostAndPorts);
		RedisSentinelConfiguration sc = new RedisSentinelConfiguration(sentinelMasterName, sentinelHostAndPorts);
		JedisPoolConfig pc = this.createPoolConfig(maxIdle, minIdl, maxTotal);

		MkJedisConnectionFactory factory = new MkJedisConnectionFactory(sc, pc);
		return factory;
	}

	private JedisPoolConfig createPoolConfig(Integer maxIdle, Integer minIdl, Integer maxTotal) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();

		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdl);
		poolConfig.setMaxTotal(maxTotal);

		return poolConfig;
	}
}
