package own.stu.com.redis.cache.service;

import java.util.List;
import java.util.Set;

public interface IRedisCacheService {

    String getKey(String key);

    Boolean setValue(String key, String value, int seconds);

    Long deleteKey(String key);

    Boolean exists(String key);

    void sadd(String key, String value, int seconds);

    Set<String> smembers(String key);

    Long scard(String key);

    Boolean sismember(String key, String member);

    String spop(String key);

    Long hset(String key, String field, String value, int seconds);

    Set<String> hkeys(String key);

    Boolean hexists(String key, String field);

    String hget(String key, String field);

    Long hdel(String key, String field);

    long incr(String key);

    Long expire(String key, int seconds);

    /**
     * 在key对应list的头部添加字符串元素，返回1表示成功，0表示key存在且不是list类型
     * @param key
     * @param values
     * @return
     */
    long lpush(String key, String... values);

    long rpush(String key, String... values);

    /**
     * 返回key对应list的长度，key不存在返回0,如果key对应类型不是list返回错误
     * @param key
     * @return
     */
    long llen(String key);

    /**
     * 返回指定区间内的元素，下标从0开始，负值表示从后面计算，-1表示倒数第一个元素 ，key不存在返回空列表
     * @param key
     * @param start
     * @param end
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 截取list，保留指定区间内元素，成功返回1，key不存在返回错误
     * @param key
     * @param start
     * @param end
     * @return
     */
    String ltrim(String key, long start, long end);

    Long ttl(String key);
    
    String set(String key, String value);

    /**
     * 添加元素
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key, double score, String member);

    /**
     * 删除元素
     * @param var1
     * @param var2
     * @return
     */
    Long zrem(String var1, String... var2);

    /**
     * 查看元素的权重
     * @param key
     * @param member
     * @return
     */
    Double zscore(String key, String member);

    /**
     * 集合中的元素
     * @param key
     * @return
     */
    Long zcard(String key);

    /**
     * 查看下标范围的元素信息
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zrange(String key, long start, long end);


    /**
     * 查看score范围的元素信息
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zrangeByScore(String key, double start, double end);
}
