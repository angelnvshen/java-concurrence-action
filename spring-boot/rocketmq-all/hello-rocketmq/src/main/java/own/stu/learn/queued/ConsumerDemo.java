package own.stu.learn.queued;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        consumer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");

        consumer.subscribe("hello-world", "tag-1");

        // 默认是集群模式，负载均衡；可设置广播模式
        // consumer.setMessageModel(MessageModel.BROADCASTING);

        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

                msgs.stream().forEach(msg -> {

                    System.out.println(new String(msg.getBody()));
                });

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
    }
}
