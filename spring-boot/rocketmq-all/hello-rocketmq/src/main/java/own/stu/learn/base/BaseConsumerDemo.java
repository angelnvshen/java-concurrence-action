package own.stu.learn.base;

import com.google.common.base.Stopwatch;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者和生产者
 */
public class BaseConsumerDemo {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");

        consumer.subscribe("hello-world", "tag-1");

        // 默认是集群模式，负载均衡；可设置广播模式
        // consumer.setMessageModel(MessageModel.BROADCASTING);

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(msg -> {

                System.out.println(new String(msg.getBody()) + " - " + System.currentTimeMillis() + " - " + msg.getBornTimestamp() + " - " + msg.getStoreTimestamp());
            });

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
    }
}
