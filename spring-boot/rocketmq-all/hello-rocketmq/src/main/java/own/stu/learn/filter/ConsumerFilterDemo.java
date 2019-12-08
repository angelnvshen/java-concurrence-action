package own.stu.learn.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 消费者过滤接受消息
 */
public class ConsumerFilterDemo {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");

        //consumer.subscribe("hello-world", "*");
        consumer.subscribe("hello-world", "tag-1 || tag-2");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.stream().forEach(msg -> {

                // System.out.println(new String(msg.getBody()) + " - " + System.currentTimeMillis() + " - " + msg.getBornTimestamp() + " - " + msg.getStoreTimestamp());
                System.out.println(new String(msg.getBody()) + " - " + msg.getTags());
            });

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
    }
}
