package own.stu.redis.oneMaster.logging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import own.stu.redis.oneMaster.logging.enums.Severity;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
public class LoggingService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static Integer max_log_recent = 100;

    // 思路：将日志加入list，然后修剪到规定大小
    public void log_recent(String type, String severityCode, String message) {

        Assert.notNull(message, "message is null");
        Assert.notNull(type, "type is null");

        if (severityCode == null || !Severity.contain(severityCode)) {
            severityCode = Severity.INFO.getCode();
        }

        String destination = String.format("recent:%s:%s", type, severityCode);
        message = System.currentTimeMillis() + " " + message;

        String finalMessage = message;
        redisTemplate.executePipelined((RedisCallback<List<String>>) connection -> {
            connection.lPush(destination.getBytes(), finalMessage.getBytes());
            connection.lTrim(destination.getBytes(), 0, max_log_recent - 1);
            return null;
        });
    }

    public void log_common(String type, String severityCode, String message, Integer timeOut) {
        if (timeOut == null) {
            timeOut = 5;
        }

        Assert.notNull(message, "message is null");
        Assert.notNull(type, "type is null");

        if (severityCode == null || !Severity.contain(severityCode)) {
            severityCode = Severity.INFO.getCode();
        }

        String destination = String.format("common:%s:%s", type, severityCode);
        // 当前所处小时数
        String start_key = destination + ":start"; // common:日志名称：日志级别：start

        Integer finalTimeOut = timeOut;

        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();

        String finalSeverityCode = severityCode;
        redisTemplate.executePipelined((RedisCallback<List<String>>) connection -> {

            long endTime = Instant.now().getEpochSecond() + finalTimeOut;
            while (Instant.now().getEpochSecond() < endTime) {
                try {


                    connection.watch(start_key.getBytes());
                    int hour = Instant.now().atZone(ZoneId.systemDefault()).getHour();

                    Object oldHourValue = valueSerializer.deserialize(connection.get(start_key.getBytes()));
                    connection.multi();

                    if (oldHourValue != null && Integer.valueOf((String) oldHourValue) < hour) {
                        connection.rename(destination.getBytes(), (destination + ":last").getBytes());
                        connection.rename(start_key.getBytes(), (destination + ":pstart").getBytes());
                        connection.set(start_key.getBytes(), (hour + "").getBytes());
                    } else if (oldHourValue == null) {
                        connection.set(start_key.getBytes(), (hour + "").getBytes());
                    }

                    connection.zIncrBy(destination.getBytes(), 1, message.getBytes());
                    log_recent(type, finalSeverityCode, message);
                    break;
                } catch (Exception e) { // watche exception ?
                    continue;
                }
            }

            return null;
        }, redisTemplate.getValueSerializer());
    }

}
