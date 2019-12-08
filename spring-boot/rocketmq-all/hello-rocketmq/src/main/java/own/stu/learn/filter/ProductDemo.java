package own.stu.learn.filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ProductDemo {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();
        // producer.setCreateTopicKey("hello-world");

        IntStream.range(0, 10).forEach((i) -> {

            Message message = new Message("hello-world", "tag-2", ("hello-world-filter" + i).getBytes());

            // 消息自定义属性，可用于客户端的过滤
            message.putUserProperty("i", i + "");
            try {
                // 同步发送
                SendResult result = producer.send(message);
                System.out.println(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sleep(10);
        producer.shutdown();
    }

    public static void sleep(int seconds) {
        sleep(seconds, SECONDS);
    }

    public static void sleep(int timeout, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
