package own.stu.learn.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.stream.IntStream;

import static own.stu.learn.base.BaseProductDemo.sleep;

/**
 * 发送延迟消息
 */
public class DelayProductDemo {
    public static void main(String[] args) throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();

        IntStream.range(0, 10).forEach((i) -> {

            Message message = new Message("hello-world", "tag-1", ("hello-world-delay" + i).getBytes());
            message.setDelayTimeLevel(2);
            try {
                producer.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sleep(10);
        producer.shutdown();
    }
}
