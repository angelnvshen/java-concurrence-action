package own.stu.learn.batch;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static own.stu.learn.base.BaseProductDemo.sleep;

/**
 * 批量发送消息： 注意批量消息，一次不能超过4M，可以考虑filter 截取，多余的 send again。
 */
public class BatchProductDemo {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();

        List<Message> messageList = IntStream.range(0, 10)
                .mapToObj((i) -> new Message("hello-world", "tag-1", ("hello-world-batch" + i).getBytes()))
                .collect(Collectors.toList());

        SendResult result = producer.send(messageList);
        System.out.println(result);

        sleep(10);
        producer.shutdown();
    }
}
