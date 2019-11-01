package own.stu.redis.oneMaster.autoComplete.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutoCompleteService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String allChars = "`abcdefghijklmnopqrstuvwxyz{";

    private static String groupIdPatten = "members:%s";

    // abc -> 前驱 abb{  后继 abc{
    // 即：将给定前缀的最后一个字符替换为第一个排在该字符前面的字符，再加上{，即可以得到前缀
    //      将给定前缀加上{,可以得到该字符的后继。
    public List<String> autoCompleteOnPrefix(String groupId, String prefix) {

        String uuid = UUID.randomUUID().toString();

        String start = getPrefix(prefix) + uuid;
        String end = getSuffix(prefix) + uuid;

        String zsetName = String.format(groupIdPatten, groupId);

        BoundZSetOperations zSetOperations = redisTemplate.boundZSetOps(zsetName);
        zSetOperations.add(start, 0);
        zSetOperations.add(end, 0);

        List<Object> execute = (List<Object>) redisTemplate.execute(new SessionCallback<List<String>>() {
            @Override
            public List<String> execute(RedisOperations operations) throws DataAccessException {
                List<String> result = null;
                do {
                    operations.watch(zsetName);
                    BoundZSetOperations setOperations = operations.boundZSetOps(zsetName);

                    Long startRank = setOperations.rank(start);
                    Assert.notNull(startRank, "startRank can't be null");

                    Long endRank = setOperations.rank(end);
                    Assert.notNull(startRank, "endRank can't be null");

                    try {
                        operations.multi();
                        setOperations.remove(start, end);
                        long range = Math.min(startRank + 9, endRank - 2);
                        if (range > 0) {
                            setOperations.range(startRank, range);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        result = operations.exec(); // 返回所有 multi命令的执行结果
                    }
                } while (result.size() == 0); //如果失败则重试

                return result;
            }
        });

        return execute.size() > 1 ? new ArrayList<>((Set<String>) execute.get(execute.size() - 1)) : new ArrayList<>();
    }

    private static String getSuffix(String prefix) {
        return prefix + "{";
    }

    private static String getPrefix(String prefix) {
        Assert.notNull(prefix, "prefix is null");
        char lastChar = prefix.charAt(prefix.length() - 1);
        char newLastChar = allChars.charAt(allChars.indexOf(lastChar) - 1);
        if (prefix.length() == 1) {
            return newLastChar + "{";
        }
        return new StringBuffer(prefix.substring(0, prefix.length() - 1)).append(newLastChar).append("{").toString();
    }

    public boolean joinGroup(String groupId, String user) {
        Assert.notNull(user, "user is null");
        Assert.notNull(groupId, "groupId is null");
        String zsetName = String.format(groupIdPatten, groupId);
        return redisTemplate.boundZSetOps(zsetName).add(user, 0);
    }

    public long leaveGroup(String groupId, String user) {
        Assert.notNull(user, "user is null");
        Assert.notNull(groupId, "groupId is null");
        String zsetName = String.format(groupIdPatten, groupId);
        return redisTemplate.boundZSetOps(zsetName).remove(user);
    }
}
