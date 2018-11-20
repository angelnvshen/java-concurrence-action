package own.stu.com.redis.service;

public interface ICacheService {

  String set(String key, String value);

  Long incrBy(String key, long increament);

  String get(String key);
}