package own.stu.learn.rocketmqwithspring.consumerListener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        consumerGroup = "${rocketmq.producer.group}",
        topic = "${common.topic}",
        selectorExpression = "world-tag"
)
public class CusConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}
