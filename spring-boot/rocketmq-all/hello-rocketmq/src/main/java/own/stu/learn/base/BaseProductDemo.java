package own.stu.learn.base;

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

/**
 * 生产者：
 *  1：同步发送
 *  2：异步回调发送
 *  3：发送单向消息,不考虑消息丢失
 */
public class BaseProductDemo {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.116:9876;192.168.0.117:9876;");
        producer.start();
        // producer.setCreateTopicKey("hello-world");

        IntStream.range(0, 10).forEach((i) -> {

            Message message = new Message("hello-world", "tag-1", ("hello-world-oneway" + i).getBytes());
            try {
                // 同步发送
                /*SendResult result = producer.send(message);
                System.out.println(result);*/

                // 异步回调发送
                /*producer.send(message, new SendCallback() {

                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.println(e);
                    }
                });*/
                // 发送单向消息,不考虑消息丢失
                producer.sendOneway(message);
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
